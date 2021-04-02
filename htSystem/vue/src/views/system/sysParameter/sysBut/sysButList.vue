<template>
  <!-- 系统配置---全局参数---按钮配置 -->
  <div class="app-container"
       style="flex-direction: column;">
    <el-scrollbar style="width:100%">
      <div class="container-table">
        <div class="container-btn">
          <span class="container-btn-left">
            <el-button type="primary"
                       size="small"
                       @click="handleAdd">新增</el-button>
            <el-button type="danger"
                       size="small"
                       :disabled="dicDisabled"
                       v-has="'buttonConfig:batchDelete'"
                       @click="handleDeleteBatch">删除</el-button>
          </span>
          <span class="container-btn-right">
            <el-input v-model="selectWord"
                      placeholder="按钮名称/编码"
                      class="handle-input"
                      style="width: 200px;"
                      clearable></el-input>
            <el-button type="primary"
                       size="small"
                       icon="search"
                       style="margin-left:10px"
                       @click="search">查询</el-button>
            <el-button size="small"
                       type="text"
                       @click="reset">重置</el-button>
          </span>
        </div>
        <div class="common-table-style">
          <el-table :data="pageData.list"
                    border
                    class="table"
                    ref="multipleTable"
                    align="center"
                    @selection-change="handleSelectionChange">
            <el-table-column type="selection"
                             min-width="35"
                             align="center"
                             show-overflow-tooltip></el-table-column>
            <el-table-column type="index"
                             width="55"
                             label="序号"
                             align="center"
                             show-overflow-tooltip></el-table-column>
            <el-table-column prop="buttonCode"
                             label="按钮编码"
                             align="center"
                             show-overflow-tooltip></el-table-column>
            <el-table-column prop="buttonName"
                             label="按钮名称"
                             align="center"
                             show-overflow-tooltip></el-table-column>
            <el-table-column prop="description"
                             label="备注"
                             align="center"
                             show-overflow-tooltip></el-table-column>
            <el-table-column label="操作"
                             min-width="250"
                             align="center"
                             show-overflow-tooltip>
              <template slot-scope="scope">
                <el-button type="text"
                           icon="el-icon-edit"
                           @click="handleEdit(scope.row)">编辑</el-button>
                <el-button type="text"
                           icon="el-icon-delete"
                           class="red"
                           v-has="'buttonConfig:deleteID'"
                           @click="handleDelete(scope.row.id)"
                           buttonCode="TY011">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div class="pagination">
          <pagination :page-list="pageData"
                      @pagesearch="handlePage"></pagination>
        </div>
      </div>
      <div class="show-dialog">
        <el-dialog title="按钮配置"
                   :visible.sync="editVisible"
                   width="45%"
                   :modal-append-to-body="false"
                   :show-close="false"
                   :close-on-click-modal="false"
                   :modal="false"
                   fullscreen>
          <div class="dialogFix">
            <el-button type="primary"
                       size="small"
                       v-has="'buttonConfig:save'"
                       @click="handleSave('form')">保 存</el-button>
            <el-button size="small"
                       @click="cancelSave">返 回</el-button>
          </div>
          <el-row class="explain padT">
            <el-col :span="16">
              <div style="margin-top: 30px;">
                <el-form ref="form"
                         :model="form"
                         :rules="rules"
                         label-width="80px"
                         style="margin-top:10px"
                         size="small">
                  <el-form-item label="按钮编码"
                                prop="buttonCode"
                                style="width:25rem">
                    <el-input v-model="form.buttonCode"></el-input>
                  </el-form-item>
                  <el-form-item label="按钮名称"
                                prop="buttonName"
                                style="width:25rem">
                    <el-input v-model="form.buttonName"></el-input>
                  </el-form-item>
                  <el-form-item label="描述"
                                style="width:30rem">
                    <el-col :span="24">
                      <el-input maxlength="200"
                                :autosize="{ minRows: 6, maxRows: 10}"
                                :rows="8"
                                type="textarea"
                                style="max-width: 35rem;"
                                @input="descInput_fou()"
                                v-model="form.description"></el-input>
                    </el-col>
                    <span>{{remnant_fou}}/200</span>
                  </el-form-item>
                </el-form>
                <span slot="footer"
                      class="dialog-footer"
                      style="float: right;margin-right: 4rem;margin-top: 2rem;"></span>
              </div>
            </el-col>
          </el-row>
        </el-dialog>
      </div>
    </el-scrollbar>
  </div>
</template>
<script>
export default {
  name: "sysButList",
  data () {
    return {
      pageData: {
        list: [],
        pageNumber: 1,
        pageSize: 10,
        totalCount: 0,
        totalPage: 0
      },
      remnant_fou: 200,
      editVisible: false,
      dicDisabled: true,
      btnDes: true,
      btnNum: true,
      btnName: true,
      multipleSelection: [],
      selectWord: "",
      form: {
        buttonCode: "",
        buttonName: "",
        description: "",
        id: ""
      },
      rules: {
        buttonCode: [
          {
            required: true,
            message: "  ",
            trigger: "blur"
          }
        ],
        buttonName: [
          {
            required: true,
            message: "  ",
            trigger: "blur"
          }
        ]
      }
    }
  },
  created () {
    this.search();
  },
  methods: {
    handleAdd () {
      this.remnant_fou = 200;
      this.editVisible = true;
    },
    handleDeleteBatch () {
      let deletebatch = [];
      this.multipleSelection.forEach(i => {
        deletebatch.push(i.id);
      });
      this.$confirm("此操作将永久删除, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$http
            .post(this.api.buttonConfigBatchDelete, deletebatch)
            .then(res => {
              if (res.data.code == "200") {
                this.$message({
                  message: "批量删除数据成功",
                  type: "success"
                });
              }
              this.search();
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消批量删除"
          });
        });
    },
    empty () {
      this.form = {
        buttonCode: "",
        buttonName: "",
        description: "",
        id: ""
      };
    },
    reset () {
      this.selectWord = "";
      this.getData();
    },
    search () {
      this.getData(this.pageData.pageNumber, this.pageData.pageSize);
    },

    getData (number, size) {
      this.$http
        .post(this.api.buttonConfigQueryByPage, {
          condition: {
            selectWord: this.selectWord,
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
    handleSelectionChange (val) {
      if (val.length > 0) {
        this.multipleSelection = val;
        this.dicDisabled = false;
      } else {
        this.dicDisabled = true;
      }
    },
    handleEdit (row) {
      this.editVisible = true;
      this.form = Object.assign({}, row);
      this.remnant_fou = 200 - this.form.description.length;
      this.$nextTick(() => {
        this.$refs.form.validate(valid => { });
      });
    },
    handleDelete (id) {
      this.$confirm("此操作将永久删除, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$http.delete(this.api.buttonConfigDelete + id).then(res => {
            if (res.data.code == "200") {
              this.$message({
                message: "删除数据成功",
                type: "success"
              });
            }
            this.search();
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },

    handleDeleteBatch () {
      let deletebatch = [];
      this.multipleSelection.forEach(i => {
        deletebatch.push(i.id);
      });
      this.$confirm("此操作将永久删除, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$http
            .post(this.api.buttonConfigBatchDelete, deletebatch)
            .then(res => {
              if (res.data.code == "200") {
                this.$message({
                  message: "批量删除数据成功",
                  type: "success"
                });
              }
              this.search();
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消批量删除"
          });
        });
    },
    handlePage (number, size) {
      this.search(number, size);
    },
    descInput_fou () {
      var txtVal = this.form.description.length;
      this.remnant_fou = 200 - txtVal;
    },
    cancelSave () {
      this.editVisible = false;
      this.empty();
    },
    handleSave (from) {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.$http.post(this.api.buttonConfigSave, this.form).then(res => {
            if (res.data.code == "200") {
              if (res.data.body == 1) {
                this.$message.success("更新成功");
              } else {
                this.$message.success("保存成功");
              }
              this.editVisible = false;
              this.empty();
              this.search();
            } else {
              this.$message.error(res.data.message);
            }
          });
        } else {
          return false;
        }
      });
    }
  }
}
</script>
<style lang="scss" scoped>
.el-table__header th {
  padding: 0;
  height: 50px;
}
/deep/.el-input__inner {
  height: 35px !important;
  width: 25rem !important;
}
</style>