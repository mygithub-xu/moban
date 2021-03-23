<template>
  <div  class="login-contain">
    <div class="login-body">
      <div class="login-box">
      </div>
      <div class="login-out-form">
      <div class="login-form">
      <h3 class="login-title">欢迎登录</h3>
      <el-form ref="loginForm" :model="form" :rules="rules" label-width="80px" style="margin: 80px 10px;">
            <el-form-item label="账号ccc" prop="userName">
              <el-input type="text" placeholder="请输入账号" v-model="form.userName"/>
            </el-form-item>
            <el-form-item label="密码ccc" prop="passWord">
              <el-input  show-password placeholder="请输入密码" v-model="form.passWord" @keyup.enter.native="onSubmit('loginForm')"/>
            </el-form-item>
            <div class="login-button">
              <el-button type="primary" @click="onSubmit('loginForm')" style="width:40%">登录</el-button>
            </div>
              
      </el-form>
      </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: "Login",
    data() {
      return {
        form: {
          userName: 'whfch',
          passWord: 'whfch123',
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

                //防止多次弹出框，记录初始值
                sessionStorage.setItem("morePOp", "1");
                sessionStorage.setItem("Token", res.data.body.token);
                sessionStorage.setItem("User", JSON.stringify(res.data.body.user));

                //更新头像
                this.$message.success("登入成功");

                let indexPath = this.$store.state.tabRouter.indexTab;
                console.log(indexPath);
                if(indexPath != "/page/Dashboard"){
                    this.$router.push(indexPath).catch(()=>{});
                }else{
                  this.$router.push("/page/Dashboard").catch(()=>{});
                }
                
              }else{
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

<style scoped>
  .login-contain{
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center; /* 水平居中 */
    align-items: center;     /* 垂直居中 */
    background-color: #f3f3f3;
  }
  .login-body{
    height: 80%;
    width: 60%;
    max-height:  500px;;
    max-width: 820px;
    border-radius: 5px;
    display: flex;
    flex-direction: row;
    background-image: url('https://www.whfch.icu/resources/8476443.jpg');
    background-size:100% 100%;
  }
  .login-box {
    flex: 1;
  }
  .login-out-form{
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  .login-form{
    height: 80%;
    width: 90%;
    max-height: 450px;
    background-color: #f3f3f3;
    opacity: 0.9;
  }
  .login-in-form{
    display: flex;
    flex-direction: column;
    height: 100%;
    width: 100%;
    align-items: center;
  }
  .login-title {
    text-align: center;
    margin: 20px 0;
  }
  .login-button{
    justify-content: center;
    display: flex;
  }
</style>