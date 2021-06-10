<template>
  <div class="app-container"
       style="flex-direction: column;">
    <div class="container">
      <div class="container_btn">
        <el-button type="primary"
                   size="small"
                   buttonCode="TY020"
                   @click="handleSave('form')">保 存</el-button>
        <el-button size="small"
                   @click="cancelSave">取 消</el-button>
      </div>

      <el-tabs v-model="activeName"
               @tab-click="handleClick">
        <el-tab-pane label="用户信息"
                     name="first">
          <el-form ref="form"
                   :model="form"
                   :rules="rules"
                   label-width="150px"
                   size="small"
                   :inline="false">
            <el-col :xs="24"
                    :sm="24"
                    :md="12"
                    :lg="6"
                    :xl="6"
                    :offset="2">
              <el-form-item prop="headPortrait"
                            style="margin-left:-35px">
                <img :src="form.headPortrait"
                     style="height:120px;width:120px;border-radius:50%;" />
              </el-form-item>
              <el-button @click="changeUserImgDialog=true"
                         style="margin-left:120px;"
                         buttonCode="GR001">修改头像</el-button>
              <!-- </div> -->
            </el-col>
            <el-col :xs="24"
                    :sm="24"
                    :md="12"
                    :lg="8"
                    :xl="8">
              <el-form-item label="用户名"
                            prop="userName">
                <el-input v-model="form.userName"></el-input>
              </el-form-item>
              <el-form-item label="账号名"
                            prop="loginUser">
                <el-input v-model="form.loginUser"></el-input>
              </el-form-item>
              <el-form-item label="性别"
                            prop="gender">
                <el-select v-model="form.gender"
                           clearable
                           placeholder="请选择"
                           style="width: 100%">
                  <el-option v-for="item in genderData"
                             :key="item.value"
                             :label="item.label"
                             :value="item.value"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="qq/微信"
                            prop="qqWeixin">
                <el-input v-model="form.qqWeixin"></el-input>
              </el-form-item>
              <el-form-item label="邮箱"
                            prop="email">
                <el-input v-model="form.email"></el-input>
              </el-form-item>
            </el-col>
          </el-form>

        </el-tab-pane>
        <el-tab-pane label="修改密码"
                     name="second">
          <el-col :xs="24"
                  :sm="24"
                  :md="12"
                  :lg="8"
                  :xl="8"
                  style="height:50px">
          </el-col>
          <el-form :model="formPass"
                   ref="formPass"
                   :rules="rules"
                   label-width="100px"
                   :inline="true"
                   size="small">
            <el-col :xs="24"
                    :sm="24"
                    :md="12"
                    :lg="8"
                    :xl="8">
              <el-form-item label="原密码"
                            prop="password"
                            class="password_input">
                <el-input v-model="formPass.password"
                          :type="pwdType1"></el-input>
                <span :class="passIcon1"
                      @click="changeType1()"
                      style="position: absolute;top:10px;right: 8px;"></span>
              </el-form-item>
              <br>
              <el-form-item label="新密码"
                            prop="newPassword"
                            class="password_input">
                <el-input v-model="formPass.newPassword"
                          :type="pwdType2"></el-input>
                <span :class="passIcon2"
                      @click="changeType2()"
                      style="position: absolute;top:10px;right: 8px;"></span>
              </el-form-item>
              <br>
              <el-form-item label="确认密码"
                            prop="confirmPassword"
                            class="password_input">
                <el-input v-model="formPass.confirmPassword"
                          :type="pwdType3"></el-input>
                <span :class="passIcon3"
                      @click="changeType3()"
                      style="position: absolute;top:10px;right: 8px;"></span>
              </el-form-item>
            </el-col>
          </el-form>
        </el-tab-pane>
      </el-tabs>
    </div>

    <!-- 头像上传弹窗 -->
    <el-dialog modal-append-to-body
               title="修改头像"
               :visible.sync="changeUserImgDialog"
               :before-close="handleClose"
               width="30%">
      <!-- 上传头像组件 监听事件从上传头像组件传来的数据，即裁剪好的图片-->
      <uploadHeader v-on:listenTitleImg="showTitleImg"
              :avatarSrc="getAvatar"></uploadHeader>
      <el-button @click="handleClose"
                 class="cancelBtn">取 消</el-button>
    </el-dialog>
  </div>

</template>

<script>
import { mapGetters } from 'vuex';
import bus from "@/utils/common/bus";
export default {
  name: "userInfo",
  data () {
    // 手机号验证
    var checkPhone = (rule, value, callback) => {
      const phoneReg = /^1[3|4|5|6|7|8][0-9]{9}$/
      if (!value) {
        return callback(new Error('手机号码不能为空'))
      }
      setTimeout(() => {

        if (!Number.isInteger(+value)) {
          callback(new Error('手机号码不正确！'))
        } else {
          if (phoneReg.test(value)) {
            callback()
          } else {
            callback(new Error('手机号码不正确！'))
          }
        }
      }, 100)
    };
    // 邮箱验证
    var checkEmail = (rule, value, callback) => {
      const mailReg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
      if (!value) {
        return callback(new Error('邮箱不能为空'))
      }
      setTimeout(() => {
        if (mailReg.test(value)) {
          callback()
        } else {
          callback(new Error('邮箱格式错误'))
        }
      }, 100)
    };
    // 新密码验证
    var checkNewPassword = (rule, value, callback) => {
      const newPasswordReg = /^[0-9A-Za-z]{6,}$/
      setTimeout(() => {
        if (newPasswordReg.test(value)) {
          callback()
        } else {
          this.$message.warning(`(6位及以上字母或数字) 格式有误`);
          callback(new Error('(6位及以上字母或数字) 格式有误'))
        }
      }, 100)
    };
    return {
      form: {
        id: "",
        userName: "",
        loginUser: "",
        mobilePhone: "",
        qqWeixin: "",
        email: "",
        gender: "",
        headPortrait: "", // 默认
        headPortraitSrc: "" //用于存放头像的临时base64流
      },
      rules: {
        userName: [
          { required: true, message: ' ', trigger: 'blur' }
        ],
        loginUser: [
          { required: true, message: ' ', trigger: 'blur' }
        ],
        mobilePhone: [
          { required: true, message: '手机号码不正确！', trigger: 'change' },
          { validator: checkPhone, trigger: 'change' }
        ],
        email: [
          { required: true, message: '邮箱格式错误', trigger: 'change' },
          { validator: checkEmail, trigger: 'change' }
        ],
        gender: [
          { required: true, message: ' ', trigger: 'change' }
        ],
        password: [
          { required: true, message: ' ', trigger: 'blur' }
        ],
        newPassword: [
          { required: true, message: ' ', trigger: 'blur' },
          { required: true, validator: checkNewPassword, trigger: 'blur' }
        ],
        confirmPassword: [
          { required: true, message: ' ', trigger: 'blur' }
        ]
      },
      activeName: "first",//tab栏
      genderData: [],//性别
      formPass: {
        password: "",
        newPassword: "",
        confirmPassword: ""
      },
      passIcon1: 'icon-mimabukejian', // 默认是不可见图标
      passIcon2: 'icon-mimabukejian', // 默认是不可见图标
      passIcon3: 'icon-mimabukejian', // 默认是不可见图标
      pwdType1: 'password', // 密码类型
      pwdType2: 'password', // 密码类型
      pwdType3: 'password', // 密码类型
      changeUserImgDialog: false, // 头像弹窗
    }
  },
  computed: {
    ...mapGetters([
      'getAvatar'
    ])
  },

  created () {
    //展示
    this.showImg()
    this.getData();
    this.getxiala();
  },
  methods: {
    //保存
    handleSave () {
      //判断当前tab，调用不同的接口
      var activeName = this.activeName;
      if (activeName == "first") {
        this.handleSaveInfo(this.form);
      } else {
        this.handleSavePass(this.formPass);
      }

    },

    //保存用户信息
    handleSaveInfo (form) {
      this.form.headPortraitSrc = this.form.headPortrait;
      this.form.headPortrait = "";
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.$http
            .post(this.api.userUpdateUserInfo, this.form)
            .then(res => {
              if (res.data.code == "200") {
                this.$message.success(res.data.message);
                //更新头像
                // this.$store.dispatch('changeUrlFun',res.data.body);
                sessionStorage.setItem("user", JSON.stringify(res.data.body));
                //使用bus通知更换头像
                bus.$emit("changeHead", "change");

                this.showImg();
              } else {
                this.$message.warning(res.data.message);
                return false;
              }
            });
        } else {
          return false;
        }
        this.activeName = "first";
      });
    },


    //用户修改密码
    handleSavePass (formPass) {
      //判断2次密码输入是否一致
      if (this.formPass.newPassword != this.formPass.confirmPassword) {
        this.$message.error("两次密码输入不一致，请重新输入！");
        formPass.newPassword = "";
        formPass.confirmPassword = "";
      }
      //取出当前登录用户id
      var id = this.$User.getId()
      var name = this.$User.getLoginUser();
      var parameter = {
        condition: {
          id: id,
          password: this.formPass.password,
          newPassword: this.formPass.newPassword
        }
      };
      this.$refs["formPass"].validate(valid => {
        if (valid) {
          this.$http
            .post(this.api.userUpdatePasswordById, parameter)
            .then(res => {
              if (res.data.pages == "200") {
                this.$message.success(res.data.message);
              } else {
                this.$message.warning(res.data.message);
                return false;
              }
            });
        } else {
          return false;
        }
        this.activeName = "second";
      });
    },

    //取消
    cancelSave () {
      this.empty();
      this.$router.push("/page/Dashboard");
    },
    //清空
    empty () {
      this.form = {
        id: "",
        userName: "",
        titleId: "",
        mobilePhone: "",
        email: "",
        gender: "",
      };
      this.formPass = {
        password: "",
        newPassword: "",
        confirmPassword: ""
      };
      //清除校验
      this.$nextTick(() => {
        this.$refs["form"].clearValidate();
        this.$refs["formPass"].clearValidate();
      });
    },
    getData () {
      //取出当前登录用户id
      var id = this.$User.getId();
      this.$http.post(this.api.userQueryById + id).then(res => {
        if (res.data.code == "200") {
          this.form = res.data.body;
        } else {
          this.$message.warning(res.data.body);
          return false;
        }
      });
    },
    //显示头像
    showImg () {
      let user = this.$User.getUser();
      var avatar = user.headPortrait;
      this.form.headPortrait = avatar
    },
    //下拉数据
    getxiala () {
      this.$http.get(this.api.dicTypeGetType + "gender").then(res => {
        if (res.data.code == 200) {
          this.genderData = res.data.body;
        }
      });
    },
    // 点击切换密码可见或不可见
    changeType1 () {
      this.passIcon1 = this.passIcon1 === 'icon-mimabukejian' ? 'icon-mimabukejian_1' : 'icon-mimabukejian';
      this.pwdType1 = this.pwdType1 === 'password' ? 'text' : 'password';
    },
    changeType2 () {
      this.passIcon2 = this.passIcon2 === 'icon-mimabukejian' ? 'icon-mimabukejian_1' : 'icon-mimabukejian';
      this.pwdType2 = this.pwdType2 === 'password' ? 'text' : 'password';
    },
    changeType3 () {
      this.passIcon3 = this.passIcon3 === 'icon-mimabukejian' ? 'icon-mimabukejian_1' : 'icon-mimabukejian';
      this.pwdType3 = this.pwdType3 === 'password' ? 'text' : 'password';
    },
    //切换tab
    handleClick () {
      if (this.activeName == "first") {
        this.activeName == "second"
      }
    },
    // 关闭修改头像弹窗
    handleClose () {
      this.changeUserImgDialog = false;
    },
    // 显示裁剪好的头像
    showTitleImg (data) {
      this.form.headPortrait = data;
      this.changeUserImgDialog = false;
    },

  },

}
</script>
<style lang="scss" scoped>
</style>