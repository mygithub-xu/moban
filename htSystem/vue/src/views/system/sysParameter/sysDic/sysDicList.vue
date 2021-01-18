<template>
<!-- 系统配置---全局参数---字典管理 -->
  <div class="app-container">
    <div class="container-table">

      <div class="container-btn">
        <span class="container-btn-left">
          <el-button type="primary" size="small" @click="handleTypeAdd">新增</el-button>
          <!--<el-button type="warning" size="small" @click="typeEditVisible = true">弹框测试</el-button>-->
          <el-button
            type="danger"
            size="small"
            :disabled="dicTypeDisabled"
            @click="handleTypeDeleteBatch"
            v-has="'sysdic:batchDelete'"
          >删除</el-button>
        </span>

        <span class="container-btn-right">
          <el-input
            v-model="selectWord"
            placeholder="字典名称/类型"
            style="width:200px"
            clearable
          ></el-input>
          <el-button type="primary" size="small" icon="search"  @click="search" >查询</el-button>
          <el-button size="small" type="text" @click="reset" >重置</el-button>
        </span>
      </div>


      <div class="common-table-style">
      <el-table
        :data="pageData.list"
        border
        ref="multipleTable"
        @selection-change="handleTypeSelectionChange"
        height="100%"
      >
        <el-table-column type="selection" min-width="35" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column type="index" min-width="35" label="序号" align="center" show-overflow-tooltip></el-table-column>
        <el-table-column prop="dicType" label="字典类型" align="center" v-if="btnType" show-overflow-tooltip></el-table-column>
        <el-table-column prop="status" label="状态" align="center" min-width="100px" v-if="btnStatus" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-tag
              v-if="scope.row.status==='0'"
              type="danger"
              style="height: 22px; line-height: 0; padding:10px 8px;margin: 0;"
            >禁用</el-tag>
            <el-tag
              v-else
              type="success"
              style="height: 22px; line-height: 0; padding:10px 8px;margin: 0;"
            >启用</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="description" label="备注" align="center" v-if="btnDes" show-overflow-tooltip></el-table-column>
        <el-table-column label="操作" min-width="250" align="center" show-overflow-tooltip>
          <template slot-scope="scope">
            <el-tooltip effect="dark" content="编辑" placement="top">
              <el-button
                type="text"
                class="blue"
              
                @click="handleTypeEdit(scope.row)"
              >
                <i class="el-icon-edit"></i>
              </el-button>
            </el-tooltip>
            <el-tooltip effect="dark" content="删除" placement="top">
              <el-button
                type="text"
                class="red"
                v-has="'sysdic:deleteID'"
                @click="handleTypeDelete(scope.row.id)"
              >
                <i class="el-icon-delete"></i>
              </el-button>
            </el-tooltip>
            <el-tooltip effect="dark" content="列表" placement="top">
              <el-button
                type="text"
                icon="el-icon-edit"
                
                @click="handleLabelOpen(scope.row)"
              ></el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>
      </div>
      <div class="pagination">
        <pagination :page-list="pageData" @pagesearch="handlePage"></pagination>
      </div>

    </div>

      <div class="show-dialog">
          <el-dialog title="添加/修改类型" :visible.sync="typeEditVisible" fullscreen width="45%" @close="cancelTypeSave" :modal="false" :modal-append-to-body="false">
        <div class="dialogFix">
            <el-button type="primary" size="small"  v-has="'sysdic:save'" @click="handleTypeSave('typeform')">保 存</el-button>
            <el-button size="small" @click="cancelTypeSave">返 回</el-button>
        </div>
          <el-form :rules="rules" ref="typeform" class="padT" :model="typeform" size="small" label-width="80px" style="margin-top: 42px;" >
            <el-form-item label="字典类型"  prop="dicType" style="width:400px;">
              <el-input v-model="typeform.dicType" ></el-input>
            </el-form-item>
            <el-form-item label="使用状态" style="width:400px;">
              <el-select v-model="typeform.status" placeholder="请选择" style="width:100%;">
                <el-option clearable v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
              <el-form-item label="描述" prop="description" style="width:500px;">
                  <el-col :span="24">
                  <el-input maxlength="200"  :rows="8" type="textarea" style="max-width: 35rem;" @input="descInput_fou()" v-model="typeform.description"></el-input>
                  </el-col>
                  <span>{{remnant_fou}}/200</span>
              </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
          </span>
          </el-dialog>
      </div>

    
<sysDicLabel ref="edit" :labelVisible="labelVisible" :desId="desId"  @cancelLabelVisible="cancelLabelVisible()" :data="currentType"></sysDicLabel>
  </div>
</template>

<script>
import sysDicLabel from "./sysDicLabelDialog.vue";

export default {
  name: "sysDic",
  components: {
    sysDicLabel: sysDicLabel
  },
  data() {
    return {
      rules: {
        dicType: [
          {
            //validate: validateParentId,
            required: true,
            message: "  ",
            trigger: "blur"
          }
        ]
      },
      remnant_fou: 200,
      btnType: true,
      btnStatus: true,
      btnDes: true,
      pageData: {
        list: [],
        pageNumber: 1,
        pageSize: 10,
        totalCount: 0,
        totalPage: 0
      },
      selectWord: "",
      options: [
        {
          value: "1",
          label: "启用"
        },
        {
          value: "0",
          label: "禁用"
        }
      ],
      typeEditVisible: false,
      labelVisible: false,
      dicTypeDisabled: true,
      multipleDicTypeSelection: [],
      typeform: {
        description: "",
        dicType: "",
        id: "",
        status: "1"
      },
      currentType: {},
      desId:""
    };
  },
  created() {
    this.search();
  },
  activated() {
    if (this.$route.meta.idj === true) {
      this.typeEditVisible = false
      Object.assign(this.$data, this.$options.data());
      this.reset();
      this.search();
      // this.dataInit()
    }
  },
  deactivated() {
    // this.$route.meta.idj = false
  },
  computed: {},
  methods: {
    descInput_fou(){
      var txtVal = this.typeform.description.length;
      this.remnant_fou = 200 - txtVal;
    },
    dataInit() {
      (this.btnType = true),
        (this.btnStatus = true),
        (this.btnDes = true),

        (this.typeEditVisible = false),
        (this.labelVisible = false),
        (this.dicTypeDisabled = true),
        (this.multipleDicTypeSelection = []),
        (this.currentType = {});
    },

    search() {
      this.handlePage(this.pageData.pageNumber, this.pageData.pageSize);
    },
    reset() {
      this.selectWord = "";
    },
    empty() {
      this.typeform = {
        description: "",
        dicType: "",

        id: "",
        status: "1"
      };
    },
    handlePage(number, size) {
      this.$http
        .post(this.api.dicTypeQueryByPage, {
          condition: {
            dicType: this.selectWord
          },
          number: number,
          size: size
        })
        .then(res => {
          if (res.data.code == "200") {
            this.pageData.list = res.data.body.records;
            this.pageData.totalCount = res.data.body.total;
            this.pageData.totalPage = res.data.body.pages;
          }
        });
    },
    handleTypeAdd() {
      this.typeEditVisible = true;
      this.empty();
      this.remnant_fou = 200
    },
    cancelTypeSave() {
      this.typeEditVisible = false;
      this.currentType = {};
      this.empty();
      this.$refs.typeform.clearValidate()
    },
    handleTypeEdit(row) {
      this.typeEditVisible = true;
      this.typeform = Object.assign({}, row);
      this.remnant_fou = 200 - this.typeform.description.length
    },
    handleTypeSave(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$http.post(this.api.dicTypeSave, this.typeform).then(res => {
            if (res.data.code == "200") {
              if (res.data.body == -1) {
                this.$message.error("字典类型存在重复！");
              } else if (res.data.body == 1) {
                this.$message.success("更新成功");
                this.typeEditVisible = false;
                this.empty();
                this.search();
              } else {
                this.$message.success("保存成功");
                this.typeEditVisible = false;
                this.empty();
                this.search();
              }
            }
          });
        }else {
          return false;
        }
      })
    },
    handleTypeDelete(id) {
      this.$http.delete(this.api.dicTypeDelete + id).then(res => {
        if (res.data.code == "200") {
          this.$message.success("删除成功");
          this.search();
        }
      });
    },
    handleTypeDeleteBatch() {
      let deletebatch = [];
      this.multipleDicTypeSelection.forEach(i => {
        deletebatch.push(i.id);
      });
      this.$http.post(this.api.dicTypeBatchDelete, deletebatch).then(res => {
        if (res.data.code == "200") {
          this.$message.success("批量删除成功");
          this.search();
        }
      });
    },
    handleTypeSelectionChange(val) {
      if (val.length > 0) {
        this.multipleDicTypeSelection = val;
        this.dicTypeDisabled = false;
      } else {
        this.dicTypeDisabled = true;
      }
    },
    handleLabelOpen(row) {
      this.desId = row.id
      Object.assign(this.currentType, row);
      this.labelVisible = true;
      

    },
    cancelLabelVisible(val) {
      this.labelVisible = false
    }
  }
};
</script>

<style lang="scss" scoped>
</style>