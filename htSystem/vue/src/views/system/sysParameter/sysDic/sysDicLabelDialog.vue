<template>
  <div class="DicMask">
    <!-- 列表 -->
    <div style="height: 100%;">
      <div class="dialogFix">
        <el-button type="primary" size="small" @click="handleAdd" buttonCode="ZD002" >新增</el-button>
        <el-button size="small" @click="handleClose()">返 回</el-button>
        <el-button type="danger" size="small" v-has="'dicType:bacthDelete'" :disabled="dicDisabled" @click="handleDeleteBatch">删除</el-button>
      </div>
      <div class="DicTable">
        <el-table
          :data="dataList"
          border
          class="table"
          ref="multipleTable"
          align="center"
          height="100%"
          @selection-change="handleSelectionChange"
        >
          <el-table-column type="selection" width="55" align="center"></el-table-column>

          <el-table-column type="index" width="280" label="序号" align="center">
          </el-table-column>
          <el-table-column prop="dicLabel" label="字典标签" align="center">
          </el-table-column>
          <el-table-column prop="dicValue" label="字典键值" align="center">
          </el-table-column>
          <el-table-column prop="status" label="状态" align="center" width="100px">
            <template slot-scope="scope">
              <el-tag v-if="scope.row.status==='0'" type="danger"
                style="height: 22px; line-height: 0; padding:10px 8px;margin: 0;">禁用
              </el-tag>
              <el-tag v-else type="success" style="height: 22px; line-height: 0; padding:10px 8px;margin: 0;">启用
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="description" label="备注" align="center">
          </el-table-column>
          <el-table-column label="操作" width="250" align="center">
            <template slot-scope="scope">
              <el-tooltip effect="dark" content="编辑" placement="top">
                <el-button type="text" icon="el-icon-edit" @click="handleEdit(scope.row)"  buttonCode="ZD005" >
                </el-button>
              </el-tooltip>
              <el-tooltip effect="dark" content="删除" placement="top">
                <el-button type="text" icon="el-icon-delete" v-has="'dicType:deleteID'" class="red" @click="handleDelete(scope.row.id)">
                </el-button>
              </el-tooltip>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <!-- 弹窗 -->
    <div class="maskC" v-if="editVisible">
      <el-dialog
        style="margin-top: 20px;"
        :rules="rules" title="添加数据" :show-close="false" :close-on-click-modal="false" :append-to-body="false" :visible.sync="editVisible" width="45%"  @close="cancelSave"  :modal="false" >
        <el-form
          ref="form"
          :model="form"
          label-width="80px" style="margin-top:10px">
          <el-form-item label="字典标签">
            <el-input
              v-model="form.dicLabel" style="width: 13rem">
            </el-input>
          </el-form-item>
          <el-form-item label="字典键值">
            <el-input
              v-model="form.dicValue" style="width: 13rem">
            </el-input>
          </el-form-item>
          <el-form-item label="字典类型">
            <el-input
              v-model="data.dicType"
              disabled
              style="width: 13rem">
            </el-input>
          </el-form-item>
          <el-form-item label="描述">
            <el-input
              v-model="form.description" style="width: 13rem">
            </el-input>
          </el-form-item>
          <el-form-item label="状态">
            <el-select
            v-model="form.status"
            placeholder="请选择">
              <el-option
                clearable v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button
          type="primary"
          buttonCode="ZD006"
          v-has="'dicType:save'"
          @click="handleSave">保 存
          </el-button>
          <el-button @click="cancelSave">返 回</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
export default {
  name: "sysDicLabel",
  props: {
    data: {
      type: Object,
      default: 1
    }
  },
  data() {
    return {
      rules: {
        // dicType: [
        //   {
        //     //validate: validateParentId,
        //     required: true,
        //     message: "  ",
        //     trigger: "blur"
        //   }
        // ]
      },
      multipleSelection: [],
      dataList: [],
      form: {
        description: "",
        dicLabel: "",
        dicTypeId: "",
        dicValue: "",
        id: "",
        status: "1"
      },
      dicDisabled: true,
      editVisible: false,
      options: [
        {
          value: "1",
          label: "启用"
        },
        {
          value: "0",
          label: "禁用"
        }
      ]
    };
  },
  created() {
    this.getData(this.data.id);
  },
  watch: {
    data() {
      this.getData(this.data.id);
    }
  },
  methods: {
    empty() {
      this.form = {
        description: "",
        dicLabel: "",
        dicTypeId: "",
        dicValue: "",
        id: "",
        status: "1"
      };
    },
    getData(id) {
      this.$http.get(this.api.dicQueryByPageAndDicTypeID + id).then(res => {
        if (res.data.code == "200") {
          this.dataList = res.data.body;
        }
      });
    },
    handleAdd() {
      this.editVisible = true;
    },
    handleClose() {
      this.$emit("cancelLabelVisible");
      this.labelVisible = false;
    },
    handleEdit(row) {
      this.editVisible = true;
      this.form = Object.assign({}, row);
    },
    handleSelectionChange(val) {
      if (val.length > 0) {
        this.multipleSelection = val;
        this.dicDisabled = false;
      } else {
        this.dicDisabled = true;
      }
    },
    handleDeleteBatch() {
      let deletebatch = [];
      this.multipleSelection.forEach(i => {
        deletebatch.push(i.id);
      });
      this.$http.post(this.api.dicBatchDelete, deletebatch).then(res => {
        if (res.data.code == "200") {
          this.$message.success("批量删除成功");
          this.getData(this.data.id);
        }
      });
    },
    handleDelete(id) {
      this.$http.delete(this.api.dicDelete + id).then(res => {
        if (res.data.code == "200") {
          this.$message.success("删除成功");
          this.getData(this.data.id);
        }
      });
    },
    handleSave() {
      this.form.dicTypeId = this.data.id;
      this.$http.post(this.api.dicSave, this.form).then(res => {
        if (res.data.code == "200") {
          if (res.data.body == 1) {
            this.$message.success("更新成功");
          } else if (res.data.body == 0) {
            this.$message.success("保存成功");
          } else {
            this.$message.error("字典值重复，请重新输入！");
          }
          if (res.data.body != -1) {
            this.editVisible = false;
            this.empty();
            this.getData(this.data.id);
          }
        }
      });
    },
    cancelSave() {
      this.editVisible = false;
      this.empty();
    }
  }
};
</script>

<style scoped lang="less">

</style>
<style lang="scss" scoped>
.DicMask{
  height: 100%;
}
.DicTable{
  margin-top: 10px;
  height: 100%;
}
.el-table__body-wrapper{
  height: 100%;
}
.dicPost .el-dialog__body{
  height: 100% !important;
}
//表头高度调整
.el-table__header th {
  padding: 0;
  height: 50px !important;
}
</style>
