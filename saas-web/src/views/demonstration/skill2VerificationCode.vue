<template>
    <!-- 导入导出案例 -->
    <div class="app-container">
      <el-scrollbar>
      <div class="container-table">
      <h2 class="skill-h2">1.前端生成验证码，并校验</h2>
      <div class="skill">
        <div class="skill-item">

        </div>
        <div class="skill-item">
        </div>
      </div>
      <h2 class="skill-h2">2.后端生成验证码并校验</h2>
      <div class="skill">
        <div class="skill-item">
            <img class="loginImg" :src="captchaPath" @click="getCaptcha" alt="" >
        </div>
        <div class="skill-item">
            <el-button @click="getCaptcha">生成验证码</el-button>
            <el-input style="margin-top:10px" v-model="code"></el-input>
            <el-button style="margin-top:10px" @click="checkCode">验证</el-button>
        </div>
      </div>
      </div>
      </el-scrollbar>
    </div>
</template>

<script>
import BASE_URL from '@/config'
export default {
  name: "excelOpera",
  data () {
    return {
        code: "",
        captchaPath:""
    }
  },
  created(){
    this.getCaptcha()
  },
  methods:{
    checkCode(){
      this.$http.get(this.api.userGetCheckCode +'/'+ this.code).then(res => {
        if (res.data.code == "200") {
          this.$message.success("通过")
        }
      })
    },
    getCaptcha(){
      this.captchaPath = BASE_URL + this.api.userGetVerificationCode+'/' +this.S4()
    },
    S4() {
      return (((1+Math.random())*0x10000)|0).toString(16).substring(1)
    }
  }
}
</script>
<style scoped>
.skill{
  width: 100%;
  padding: 10px,10px;
  display: flex;
}
.skill .skill-item{
  width: 40%;
  height: 150px;
}
.main{
  background-color: #ffffff;
  width: calc(100% - 20px);
}
.skill-h2{
  margin-top: 20px;
}
.loginImg{
  width: 100px;
  height: 30px;
  background-color: rebeccapurple;
}
</style>