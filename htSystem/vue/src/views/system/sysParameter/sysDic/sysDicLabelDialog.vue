<template>
  <div class="show-dialog">
    <el-dialog :visible.sync="labelVisible"
               :show-close="false"
               :modal="false"
               fullscreen>
      <div class="dialog-button">
        <el-button type="primary"
                   @click="handleAdd">新增</el-button>
        <el-button @click="handleClose()">返 回</el-button>
        <el-button type="danger"
                   v-has="'dicType:bacthDelete'"
                   :disabled="dicDisabled"
                   @click="handleDeleteBatch">删除</el-button>
      </div>
      <div class="common-table-style">
        <el-table :data="dataList"
                  border
                  class="table"
                  ref="multipleTable"
                  align="center"
                  @selection-change="handleSelectionChange">
          <el-table-column type="selection"
                           width="55"
                           align="center"></el-table-column>
          <el-table-column type="index"
                           width="280"
                           label="序号"
                           align="center"></el-table-column>
          <el-table-column prop="dicLabel"
                           label="字典标签"
                           align="center"></el-table-column>
          <el-table-column prop="dicValue"
                           label="字典键值"
                           sortable
                           align="center"></el-table-column>
          <el-table-column prop="status"
                           label="状态"
                           align="center"
                           width="100px">
            <template slot-scope="scope">
              <el-tag v-if="scope.row.status==='0'"
                      type="danger"
                      style="height: 22px; line-height: 0; padding:10px 8px;margin: 0;">禁用
              </el-tag>
              <el-tag v-else
                      type="success"
                      style="height: 22px; line-height: 0; padding:10px 8px;margin: 0;">启用
              </el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="description"
                           label="备注"
                           align="center">
          </el-table-column>
          <el-table-column label="操作"
                           width="250"
                           align="center">
            <template slot-scope="scope">
              <el-tooltip effect="dark"
                          content="编辑"
                          placement="top">
                <el-button type="text"
                           icon="el-icon-edit"
                           @click="handleEdit(scope.row)">
                </el-button>
              </el-tooltip>
              <el-tooltip effect="dark"
                          content="删除"
                          placement="top">
                <el-button type="text"
                           icon="el-icon-delete"
                           v-has="'dicType:deleteID'"
                           class="red"
                           @click="handleDelete(scope.row.id)">
                </el-button>
              </el-tooltip>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </el-dialog>
    <!-- 弹窗 -->
    <template v-if="editVisible">
      <div class="dic_label">
        <el-dialog style="margin-top: 20px;"
                   :rules="rules"
                   :show-close="false"
                   :close-on-click-modal="false"
                   :append-to-body="false"
                   :visible.sync="editVisible"
                   width="45%"
                   @close="cancelSave"
                   :modal="false">
          <el-form ref="form"
                   :model="form"
                   label-width="80px"
                   style="margin-top:10px">
            <el-form-item label="字典标签">
              <el-input v-model="form.dicLabel"
                        style="width: 13rem">
              </el-input>
            </el-form-item>
            <el-form-item label="字典键值">
              <el-input v-model="form.dicValue"
                        style="width: 13rem">
              </el-input>
            </el-form-item>
            <el-form-item label="字典类型">
              <el-input v-model="data.dicType"
                        disabled
                        style="width: 13rem">
              </el-input>
            </el-form-item>
            <el-form-item label="描述">
              <el-input v-model="form.description"
                        style="width: 13rem">
              </el-input>
            </el-form-item>
            <el-form-item label="状态">
              <el-select v-model="form.status"
                         placeholder="请选择">
                <el-option clearable
                           v-for="item in options"
                           :key="item.value"
                           :label="item.label"
                           :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-form>
          <el-button type="primary"
                     v-has="'dicType:save'"
                     @click="handleSave">保 存</el-button>
          <el-button @click="cancelSave">返 回</el-button>
        </el-dialog>
      </div>
    </template>
  </div>
</template>

<script>
export default {
  name: "sysDicLabel",
  props: {
  },
  data () {
    return {
      rules: {

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
  props: {
    labelVisible: {
      type: Boolean,
      default: false
    },
    desId: {
      type: String,
      require: true
    },
    data: {
      type: Object,
      require: true
    }
  },
  watch: {
    labelVisible () {
      if (this.labelVisible) {
        this.getData(this.desId);
      }
    }

  },
  methods: {
    empty () {
      this.form = {
        description: "",
        dicLabel: "",
        dicTypeId: "",
        dicValue: "",
        id: "",
        status: "1"
      };
    },
    getData (id) {
      this.$http.get(this.api.dicQueryByPageAndDicTypeID + id).then(res => {
        if (res.data.code == "200") {
          this.dataList = res.data.body;
        }
      })
    },
    handleAdd () {
      this.empty();
      this.editVisible = true;
    },
    handleClose () {
      this.$emit("cancelLabelVisible");
      this.labelVisible = false;
    },
    handleEdit (row) {
      this.editVisible = true;
      this.form = Object.assign({}, row);
    },
    handleSelectionChange (val) {
      if (val.length > 0) {
        this.multipleSelection = val;
        this.dicDisabled = false;
      } else {
        this.dicDisabled = true;
      }
    },
    handleDeleteBatch () {
      let deletebatch = [];
      this.multipleSelection.forEach(i => {
        deletebatch.push(i.id);
      })
      this.$http.post(this.api.dicBatchDelete, deletebatch).then(res => {
        if (res.data.code == "200") {
          this.$message.success("批量删除成功");
          this.getData(this.data.id);
        }
      })
    },
    handleDelete (id) {
      this.$http.delete(this.api.dicDelete + id).then(res => {
        if (res.data.code == "200") {
          this.$message.success("删除成功");
          this.getData(this.data.id);
        }
      })
    },
    handleSave () {
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
      })
    },
    cancelSave () {
      this.editVisible = false;
      this.empty();
    }
  }
};
</script>

<style >
.dialog-table .el-dialog__body {
  height: calc(100% - 30px);
}
.dic_label {
}
</style>

