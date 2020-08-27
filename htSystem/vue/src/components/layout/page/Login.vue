<template>
  <div  class="login-body">
    <el-form ref="loginForm" :model="form" :rules="rules" label-width="80px" class="login-box">
      <h3 class="login-title">欢迎登录</h3>
      <el-form-item label="账号" prop="userName">
        <el-input type="text" placeholder="请输入账号" v-model="form.userName"/>
      </el-form-item>
      <el-form-item label="密码" prop="passWord">
        <el-input type="password" placeholder="请输入密码" v-model="form.passWord" @keyup.enter.native="onSubmit('loginForm')"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit('loginForm')" >登录</el-button>
      </el-form-item>
    </el-form>


  </div>
</template>

<script>
  export default {
    name: "Login",

    data() {
      return {
        form: {
          userName: '',
          passWord: '',
          fullscreenLoading:false
        },

        // 表单验证，需要在 el-form-item 元素中增加 prop 属性
        rules: {
          userName: [
            {required: true, message: '账号不可为空', trigger: 'blur'}
          ],
          passWord: [
            {required: true, message: '密码不可为空', trigger: 'blur'}
          ]
        },

        flag:1,
      }
    },
    methods: {

      onSubmit(formName) {
        
        // 为表单绑定验证功能
        this.$refs[formName].validate((valid) => {
          if (valid) {
            // 使用 vue-router 路由到指定页面，该方式称之为编程式导航
        //开启loading
        const loading = this.$loading({
          lock: true,
          text: 'Loading',
          spinner: 'el-icon-loading',
          background: 'rgba(0, 0, 0, 0.7)'
        });

            this.$http.post(this.api.login,this.form).then(res=>{
              if (res.data.code == '200') {
                //更新登录状态,true为第一次登录。
                this.$store.dispatch('changeLoginStatusFun',true);
                sessionStorage.setItem("menuData",JSON.stringify(res.data.body.menuData));
                sessionStorage.setItem("User", JSON.stringify(res.data.body.user));
                sessionStorage.setItem("menuList",JSON.stringify(res.data.body.menuList));
                sessionStorage.setItem("userId", res.data.body.userId);
                sessionStorage.setItem("Token", res.data.body.token);
                sessionStorage.setItem("buttonUrlList",JSON.stringify(res.data.body.buttonUrlList));
                //更新头像
                this.$message.success("登入成功");
                this.$router.push("/page/Dashboard");
              }

              else{

              if (res.data.code === "410") {
                  if(this.flag<3){
                    this.flag++;
                  }
                  this.$message({
                    message: "登陆失败，账号或密码错误!!",
                    type: "error"
                  });
                 
                  
                }
              if (res.data.code === "500") {
                this.$message({
                  message: "登陆失败",
                  type: "error"
                });
                
              }

              }

            loading.close();
              // this.fullscreenLoading=false;
            }).catch(() => {
                loading.close();
              });
          } 

        });
      }
    }
  }
</script>

<style lang="scss" scoped>
  .login-box {
    position: absolute;
    top: 50%;
    left: 50%;
    margin-top: -200px;
    margin-left: -175px;
    padding: 35px 35px 15px 35px;
    border: 1px solid #DCDFE6;
    width: 350px;
    border-radius: 5px;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    box-shadow: 0 0 25px #909399;
    background-color: #ffffff;
    opacity:0.7;
  }
  .login-body{
    width: 100%;
    height: 100%;
    position: relative;
    background-size:100% 100%;
  }

  .login-title {
    text-align: center;
    margin: 0 auto 40px auto;
    color: #303133;
  }
</style>