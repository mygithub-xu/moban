<template>
  <div class="app-container">
    <el-scrollbar style="width:100%">
      <div class="container-table">
        <!-- 区域一 查询区域 -->
        <div class="container-btn">
          <span class="container-btn-left">
            <el-button type="primary"
                       size="small"
                       @click="handleAdd">新增</el-button>
            <el-button type="danger"
                       size="small"
                       v-has="'sysUser:batchDelete'"
                       @click="handleDeleteBatch">删除</el-button>
          </span>
          <span class="container-btn-right">
            <el-input v-model="queryContent"
                      placeholder="请输入查询内容"
                      style="width: 200px;"
                      clearable></el-input>
            <el-button type="primary"
                       size="small"
                       icon="search"
                       @click="search">查询</el-button>
            <el-button size="small"
                       type="text"
                       @click="reset">重置</el-button>
          </span>
        </div>
        <!-- 区域二---表格+分页 -->
        <div class="common-table-style">
          <el-table :data="pageData.list"
                    border
                    ref="multipleTable"
                    align="center"
                    @selection-change="handleSelectionChange">
            <el-table-column type="selection"
                             width="55"
                             align="center"
                             show-overflow-tooltip></el-table-column>
            <el-table-column type="index"
                             width="55"
                             label="序号"
                             align="center"
                             show-overflow-tooltip></el-table-column>
            <el-table-column prop="userName"
                             label="用户名"
                             align="center"
                             v-if="indexUserName"
                             show-overflow-tooltip></el-table-column>
            <el-table-column prop="loginUser"
                             label="账号名"
                             align="center"
                             v-if="indexloginUser"
                             show-overflow-tooltip></el-table-column>
            <el-table-column prop="status"
                             label="状态"
                             align="center"
                             v-if="IndexStatus"
                             show-overflow-tooltip>
              <template slot-scope="scope">
                <div v-for="item in statusData"
                     :key="item.value">
                  <el-tag :type="item.label=='启用'?'success':'danger'"
                          v-if="scope.row.status==item.value">{{item.label}}</el-tag>
                </div>
              </template>
            </el-table-column>
            <el-table-column prop="roleIds"
                             label="管理员类型"
                             align="center"
                             show-overflow-tooltip>
              <template slot-scope="scope">
                <div v-for="item in titleData"
                     :key="item.id">
                  <el-tag type="success"
                          v-for="roleId in scope.row.roleIds"
                          :key="roleId"
                          v-if="roleId == item.id">{{item.name}}</el-tag>
                </div>
              </template>
            </el-table-column>

            <el-table-column label="操作"
                             align="center">
              <template slot-scope="scope">
                <el-button type="text"
                           icon="el-icon-edit"
                           @click="handleEdit(scope.row)">编辑</el-button>
                <el-button type="text"
                           icon="el-icon-delete"
                           v-has="'sysUser:deleteID'"
                           style="color:red"
                           @click="handleDelete(scope.row.id)">删除</el-button>
                <el-button type="text"
                           icon="el-icon-unlock"
                           v-has="'sysUser:resetPassword'"
                           @click="resetPassword(scope.row)">重置密码</el-button>
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
    <!-- 区域三---弹出框，覆盖全部 -->
    <div class="show-dialog">
      <el-dialog :visible.sync="editVisible"
                 :show-close="false"
                 :modal="false"
                 fullscreen>
        <div class="dialog-button">
          <el-button type="primary"
                     size="small"
                     v-has="'sysUser:save'"
                     @click="handleSave('form')">保 存</el-button>
          <el-button size="small"
                     @click="cancelSave">返 回</el-button>
        </div>
        <el-form ref="AddForm"
                 :model="AddForm"
                 :rules="rules"
                 label-width="150px"
                 size="small"
                 style="margin-top:10px">

          <div class="edit-area">
            <el-form-item label="账号名"
                          prop="loginUser">
              <el-input v-model="AddForm.loginUser"></el-input>
            </el-form-item>

            <el-form-item label="用户名"
                          prop="userName">
              <el-input v-model="AddForm.userName"></el-input>
            </el-form-item>

            <el-form-item label="性别"
                          prop="gender">
              <el-select v-model="AddForm.gender"
                         clearable
                         placeholder="请选择"
                         style="width: 100%">
                <el-option v-for="item in genderData"
                           :key="item.value"
                           :label="item.label"
                           :value="item.value"></el-option>
              </el-select>
            </el-form-item>

            <el-form-item label="管理员类型"
                          prop="roleIds">
              <el-select v-model="AddForm.roleIds"
                         multiple
                         placeholder="请选择"
                         style="width: 100%">
                <el-option v-for="item in titleData"
                           :key="item.id"
                           :label="item.name"
                           :value="item.id" />
              </el-select>
            </el-form-item>

            <el-form-item label="生日"
                          prop="birthday">
              <div class="block">
                <el-date-picker style="width:100%;"
                                v-model="AddForm.birthday"
                                type="date"
                                placeholder="选择日期"
                                format="yyyy 年 MM 月 dd 日"
                                value-format="yyyy-MM-dd"></el-date-picker>
              </div>
            </el-form-item>

            <el-form-item label="状态">
              <el-select v-model="AddForm.status"
                         placeholder="请选择"
                         style="width: 100%">
                <el-option clearable
                           v-for="item in statusData"
                           :key="item.label"
                           :label="item.label"
                           :value="item.value"></el-option>
              </el-select>
            </el-form-item>

            <el-form-item label="备注"
                          prop="description">
              <el-input maxlength="200"
                        :autosize="{ minRows: 6, maxRows: 10}"
                        :rows="8"
                        type="textarea"
                        @input="descInput_fou()"
                        v-model="AddForm.description"></el-input>
              <span>{{remnant_fou}}/200</span>
            </el-form-item>
          </div>

        </el-form>
      </el-dialog>
    </div>

  </div>
</template>
<script>
export default {
  name: "sysUserList",
  data () {
    // 手机号验证
    var validateMobilePhone = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('手机号不能为空'));
      } else {
        const reg = /^1[3|4|5|7|8][0-9]\d{8}$/
        if (reg.test(value)) {
          callback();
        } else {
          return callback(new Error('请输入正确的手机号'));
        }
      }
    };
    return {

      queryContent: "",//查询内容
      indexUserName: true,
      indexloginUser: true,
      IndexStatus: true,
      // 区域一--end
      //区域二--表格数据--start
      pageData: {
        list: [],
        pageNumber: 1,
        pageSize: 10,
        totalCount: 0,
        totalPage: 0
      },
      multipleSelection: [],//选中的数据集合
      AddForm: {
        birthday: "", // 生日
        email: "", // 邮箱
        gender: "", // 性别
        identityCard: "", // 身份证
        roleIds: "", // 管理员类型
        loginUser: "", // 用户名
        mobilePhone: "", // 手机
        status: "1", // 状态
        userName: "", // 人员名称
        description: "", // 描述
        qqWeixin: "", // qq/微信
        password: "", // 密码
        id: ""
      },
      editVisible: false,
      rules: {
        loginUser: [
          {
            required: true,
            message: "  ",
            trigger: "blur"
          },
        ],
        userName: [
          {
            required: true,
            message: "  ",
            trigger: "blur"
          }
        ]

      },
      remnant_fou: 200,
      genderData: [],
      titleData: [],
      statusData: [],
      //区域三--end

    }
  },
  created () {
    this.getdata();
    this.getxiala();//下拉框数据
  },
  methods: {
    //新增
    handleAdd () {
      this.editVisible = true;
      //清除校验
      this.$nextTick(() => {
        this.$refs["AddForm"].clearValidate();
      })

    },
    //批量删除--不需要批量删除请将其注释，，
    handleDeleteBatch () {
      let deletebatch = [];
      this.multipleSelection.forEach(i => {
        deletebatch.push(i.id);
      })
      this.$confirm("此操作将永久删除, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$http.post(this.api.userBatchDelete, deletebatch).then(res => {
            if (res.data.code == "200") {
              this.$message({
                message: "批量删除数据成功",
                type: "success"
              })
            }
            this.getdata();
          })
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消批量删除"
          })
        })

    },
    //查询
    search () {
      this.getdata();
    },
    //重置
    reset () {
      this.queryContent = "";
      this.getdata();
    },
    //勾选数据集合
    handleSelectionChange (val) {
      if (val.length > 0) {
        this.multipleSelection = val;
        this.dicDisabled = false;
      } else {
        this.dicDisabled = true;
      }
    },
    //编辑
    handleEdit (row) {
      this.editVisible = true;
      this.AddForm = Object.assign({}, row);
      if (!!this.AddForm.roleIds) {
        //将String转数组
        this.AddForm.roleIds = this.AddForm.roleIds.split(',')
      }
      this.$nextTick(() => {
        this.$refs["AddForm"].clearValidate();
      })
    },

    //单个删除
    handleDelete (id) {
      this.$confirm("此操作将永久删除, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$http.delete(this.api.userDeletebyid + id).then(res => {
            if (res.data.code == "200") {
              this.$message({
                message: "删除数据成功",
                type: "success"
              })
            }
            this.getdata();
          })
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          })
        })
    },
    //页数
    handlePage (number, size) {
      this.pageData.pageNumber = number;
      this.pageData.pageSize = size;
      this.getdata();
    },
    //表格数据
    getdata () {
      this.$http
        .post(this.api.userQueryByCondition, {
          condition: {
            queryContent: this.queryContent,
          },
          number: this.pageData.pageNumber,
          size: this.pageData.pageSize
        })
        .then(res => {
          if (res.data.code == "200") {
            this.pageData.list = res.data.body.records;
            this.pageData.list.forEach(item => {
              //将String转数组
              item.roleIds = item.roleIds.split(',');
            })


            this.pageData.totalCount = res.data.body.total;
            this.pageData.totalPage = res.data.body.pages;
          } else {
            if (res.data.code == "401") {
              this.$message.warning(res.data.message);
            }
          }
        })
    },
    //保存/修改
    handleSave () {
      if (!!this.AddForm.roleIds) {
        this.AddForm.roleIds = this.AddForm.roleIds.join(",")
      } else {
        this.AddForm.roleIds = ''
      }
      this.$refs["AddForm"].validate(valid => {
        if (valid) {
          this.$http.post(this.api.userSaveOrUpdate, this.AddForm).then(res => {
            if (res.data.code == "200") {
              this.$message.success(res.data.body);
              this.editVisible = false;
              this.empty();
              this.search();
            } else {
              this.$message.warning(res.data.body);
            }
          })
        } else {
          return false;
        }
      })
    },
    //关闭弹框
    cancelSave () {
      this.editVisible = false;
      this.empty();
    },
    //剩余字数
    descInput_fou () {
      var txtVal = this.AddForm.description.length;
      this.remnant_fou = 200 - txtVal;
    },
    //下拉数据
    getxiala () {
      this.$http.get(this.api.dicTypeGetType + "enabled").then(res => {
        if (res.data.code == 200) {
          this.statusData = res.data.body;
        }
      })

      this.$http.post(this.api.roleGetList).then(res => {
        if (res.data.code == 200) {
          this.titleData = res.data.body;
        }
      })

      this.$http.get(this.api.dicTypeGetType + "gender").then(res => {
        if (res.data.code == 200) {
          this.genderData = res.data.body;
        }
      })

    },
    //清空
    empty () {
      this.AddForm = {
        birthday: "", // 生日
        email: "", // 邮箱
        gender: "", // 性别
        identityCard: "", // 身份证
        roleIds: "", // 管理员类型
        loginUser: "", // 用户名
        mobilePhone: "", // 手机
        status: "1", // 状态
        userName: "", // 人员名称
        description: "", // 描述
        qqWeixin: "", // qq/微信
        password: "", // 密码
        id: ""
      }
    },
    //重置密码
    resetPassword (row) {
      this.$confirm("确定要重置密码?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$http
            .post(this.api.userResetPassword + row.id)
            .then(res => {
              if (res.data.code === "200") {
                this.$message.success(res.data.body);
              } else {
                this.$message.error(res.data.body);
              }
            })
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消重置密码"
          })
        })
    },

  },

}
</script>
<style lang="scss" scope>
</style>