<template>
  <!-- 系统配置---权限管理---角色管理 -->
  <div class="app-container">
    <el-scrollbar style="width:100%">
      <div class="container-table">
        <div class="container-btn">
          <span class="container-btn-left">
            <el-button type="primary"
                       @click="handleAdd">创建角色</el-button>
            <el-button type="primary"
                       v-has="'sysRole:updateStatus'"
                       @click="saveEnable('Y')">启用</el-button>
            <el-button type="danger"
                       :disabled="dicDisabled"
                       v-has="'sysRole:updateStatus'"
                       @click="saveEnable('N')">禁用</el-button>
            <el-button type="danger"
                       :disabled="dicDisabled"
                       @click="handleDeleteBatch"
                       v-has="'sysRole:batchDelete'">删除</el-button>
          </span>
          <span class="container-btn-right">
            <el-input v-model="condition.name"
                      placeholder="请输入角色名"
                      style="width: 200px;"></el-input>
            <el-button type="primary"
                       icon="search"
                       @click="handleSearch">查询</el-button>
            <el-button type="text"
                       @click="handlereset">重置</el-button>
          </span>
        </div>
        <div class="common-table-style">
          <el-table :data="pageData.list"
                    border
                    ref="multipleTable"
                    align="center"
                    @selection-change="handleSelectionChange">
            <el-table-column type="selection"
                             width="55"
                             align="center"></el-table-column>
            <el-table-column type="index"
                             label="序号"
                             sortable
                             width="55"
                             align="center"> </el-table-column>
            <el-table-column prop="name"
                             label="角色名"
                             align="center"></el-table-column>
            <el-table-column prop="remark"
                             label="备注"
                             align="center"></el-table-column>
            <el-table-column prop="status"
                             label="状态"
                             align="center">
              <template slot-scope="scope">
                <div v-for="item in options"
                     :key="item.value">
                  <el-tag :type="item.label=='启用'?'success':'danger'"
                          v-if="scope.row.status==item.value">{{item.label}}</el-tag>
                </div>
              </template>
            </el-table-column>
            <el-table-column align="center"
                             label="操作">
              <template slot-scope="scope">
                <el-button icon="el-icon-edit"
                           type="text"
                           class="green"
                           @click="handleEdit(scope.row)"></el-button>
                <el-button icon="el-icon-delete"
                           v-has="'sysRole:delete'"
                           type="text"
                           class="red"
                           @click="handleDelete(scope.row.id)"></el-button>
                <el-button icon="el-icon-edit"
                           type="text"
                           class="green"
                           @click="handleRole(scope.row.id)">权限配置</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div class="pagination">
          <pagination :page-list="pageData"
                      @pagesearch="handlePage"></pagination>
        </div>
      </div>
    </el-scrollbar>
    <div class="show-dialog">
      <el-dialog fullscreen
                 :visible.sync="treeVisible"
                 :modal="false"
                 :show-close="false"
                 :modal-append-to-body="false"
                 @close="cancelTreeVisible">
        <div class="dialog-button">
          <el-button type="primary"
                     v-has="'rolePermission:save'"
                     @click="saveCheckedNodes">保 存</el-button>
          <el-button @click="cancelTreeVisible">返 回</el-button>
        </div>
        <el-tree :data="treeData"
                 ref="menuTree"
                 :visible.sync="treeVisible"
                 highlight-current
                 show-checkbox
                 :props="defaultProps"
                 node-key="id"></el-tree>
      </el-dialog>
    </div>
    <!-- 权限树提示框 end -->

    <!-- 编辑弹出框 -->
    <div class="show-dialog"
         v-if="editVisible">
      <el-dialog fullscreen
                 :show-close="false"
                 :close-on-click-modal="false"
                 :visible.sync="editVisible"
                 width="45%"
                 :modal="false"
                 :modal-append-to-body="false">
        <div class="dialog-button">
          <el-button type="primary"
                     v-has="'sysRole:save'"
                     @click="saveEdit('form')">保 存</el-button>
          <el-button @click="editVisible = false">返 回</el-button>
        </div>
        <el-form class="padT"
                 ref="form"
                 :model="form"
                 :rules="rules"
                 label-width="80px">
          <el-form-item label="角色名"
                        prop="name"
                        style="width:25rem">
            <el-input v-model="form.name"></el-input>
          </el-form-item>
          <el-form-item label="使用状态">
            <el-select v-model="form.status"
                       placeholder="请选择"
                       style="width:25rem">
              <el-option clearable
                         v-for="item in options"
                         :key="item.value"
                         :label="item.label"
                         :value="item.value"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="备注">
            <el-col :span="24">
              <el-input maxlength="200"
                        :autosize="{ minRows: 6, maxRows: 10}"
                        :rows="8"
                        type="textarea"
                        style="max-width: 35rem;"
                        @input="descInput_fou()"
                        v-model="form.remark"></el-input>
            </el-col>
            <el-col>
              <span>{{remnant_fou}}/200</span>
            </el-col>
          </el-form-item>
        </el-form>
      </el-dialog>
    </div>

  </div>

</template>
<script>
export default {
  name: "sysRole",
  data () {
    return {
      show: true,
      pageData: {
        list: [],
        pageNumber: 1,
        pageSize: 10,
        totalCount: 0,
        totalPage: 0
      },
      //查询
      condition: {
        name: "",
        status: null
      },
      options: [],//状态数据
      dicDisabled: true,
      multipleSelection: [],//选中数组
      //编辑窗口绑定实体
      form: {
        id: "",
        name: "",
        status: "",
        description: ""
      },
      editVisible: false,
      remnant_fou: 200,
      btnName: true,
      btnDes: true,
      btnStatus: true,
      rules: {
        name: [
          {
            required: true, message: "  ", trigger: "blur"
          }
        ]
      },
      treeData: [],
      treeVisible: false,
      defaultProps: {
        children: "children",
        label: "menuName"
      },
      currentId: "",
    }
  },
  created () {
    this.getData();
  },

  methods: {
    //数据
    getData () {

      this.$http.post(this.api.roleQuerybycondition, {
        condition: this.condition,
        number: this.pageData.pageNumber,
        size: this.pageData.pageSize
      })
        .then(res => {
          if (res.data.code === "200") {
            this.pageData.list = res.data.body.records;
            this.pageData.totalCount = res.data.body.total;
            this.pageData.totalPage = res.data.body.pages;
          }
        });
      this.getType("enabled");
    },
    //分页
    handlePage (number, size) {
      this.pageData.pageNumber = number;
      this.pageData.pageSize = size;
      this.getData();
    },
    //搜索
    handleSearch () {
      this.getData();
    },
    //重置
    handlereset () {
      condition = {
        name: "",
        status: null
      }
      this.getData();
    },
    //编辑
    handleEdit (row) {
      // 同步列表数据
      this.form = Object.assign({}, row);
      this.editVisible = true;
    },
    //删除
    handleDelete (id) {
      this.$confirm("此操作将永久删除, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.$http.delete(this.api.roleDeleteById + id).then(res => {
          if (res.data && res.data.code == "200") {
            this.$message({
              message: "删除数据成功",
              type: "success"
            });
          } else {
            this.$message.warning(`${(res.data && res.data.body) || '删除数据失败'}`);
          }
          this.handleSearch();
        });
      })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });

    },
    //权限分配---start
    handleRole (id) {
      //获取全部菜单及按钮
      this.$http.get(this.api.roleGetButMenu).then(res => {
        if (res.data.code == "200") {
          this.treeData = res.data.body;
          this.treeVisible = true;
          this.currentId = id;
        }
        //获取已配置按钮
        this.$http.get(this.api.roleGetButByRoleId + id).then(res => {
          if (res.data.code == "200") {
            this.$refs.menuTree.setCheckedKeys(res.data.body);
          }
        });
      });
    },
    //关闭权限配置窗口
    cancelTreeVisible () {
      this.treeVisible = false;
    },
    //保存用户权限
    saveCheckedNodes () {
      let data = this.$refs.menuTree.getCheckedNodes(false, true);
      let saveData = [];
      data.forEach(i => {
        if (i.sysType == "menu") {
          saveData.push({
            roleId: this.currentId,
            menuId: i.id,
            parentId: i.parentId
          });
        } else {
          saveData.push({
            roleId: this.currentId,
            buttonId: i.id,
            menuId: i.parentId
          });
        }
      });
      this.$http
        .put(this.api.rolePermissionBatchsave + this.currentId, saveData)
        .then(res => {
          if (res.data.code == "200") {
            this.$message.success("保存权限成功！");
            this.treeVisible = false;
          }
        });
    },
    //权限分配---end
    //勾选表体
    handleSelectionChange (val) {
      if (val.length > 0) {
        this.multipleSelection = val;
        this.dicDisabled = false;
      } else {
        this.dicDisabled = true;
      }
    },
    //新增
    handleAdd () {
      this.form = {
        roleName: "",
        roleCode: "",
        status: "1",
        description: "",
        id: ""
      };
      this.editVisible = true;
    },
    //修改状态
    saveEnable (type) {
      let changeData;
      if (type === "Y") {
        changeData = this.multipleSelection.filter(i => {
          if (i.status === "0") {
            i.status = "1";
            return i;
          }
        });
      } else {
        changeData = this.multipleSelection.filter(i => {
          if (i.status === "1") {
            i.status = "0";
            return i;
          }
        });
      }
      this.$http.put(this.api.roleBatchUpdate, changeData).then(res => {
        this.getData();
      });

    },
    // 保存编辑
    saveEdit (formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$http.post(this.api.roleSaveorupdate, this.form).then(res => {
            if (res.data.code === "200") {
              this.$message.success(res.data.body);
              this.getData();
              this.editVisible = false;
            } else {
              this.$message.error(res.data.body);
            }
          });
        } else {
          return false;
        }
      });
    },
    //剩余字数
    descInput_fou () {
      var txtVal = this.form.remark.length;
      this.remnant_fou = 200 - txtVal;
    },
    //批量删除
    handleDeleteBatch () {

      const deldata = [];
      this.multipleSelection.filter(i => {
        deldata.push(i.id);
      });
      this.$http.post(this.api.roleBatchDelete, deldata).then(res => {
        if (res.body && res.body.code === '200') {
          this.$message({
            message: "批量删除数据成功",
            type: "success"
          });
        } else {
          this.$message.warning(`${(res.data && res.data.body) || '批量删除数据失败'}`);
        }
        this.getData();
      });

    },
    //获取状态
    getType (type) {
      this.$http.get(this.api.dicTypeGetType + type).then(res => {
        if (res.data.code == 200) {
          this.options = res.data.body;
        }
      });
    },

  }

}
</script>
<style lang="scss" scoped>
</style>