<template>
    <!-- 导入导出案例 -->
    <div class="app-container">
      <el-scrollbar>
      <div class="container-table">
      <h2 class="skill-h2">1.RabbitTest发送消息测试</h2>
      <div class="skill" style="height: 300px;">
        <div class="skill-item">
            发送消息：
            <el-input v-model="message" placeholder="输入发送的消息" style="max-width:300px"></el-input><br>
            <el-button @click="sendMessage">发送消息</el-button>
        </div>
        <div class="skill-item">
            接收消息：<el-input type="textarea" v-model="returnMessage" style="max-width:300px"></el-input><br>
            <el-button @click="getMessage">接收消息</el-button>
        </div>
        <div class="skill-item">
           
            <el-button @click="miaosha">开始秒杀</el-button>
        </div>
      </div>
      <div class="skill" style="height: 300px;">
        <div class="skill-item">
            测试线程池
        </div>
        <div class="skill-item">
            <el-button @click="nouserxc">不使用线程池</el-button>
            时间：{{time}}
        </div>
        <div class="skill-item">
           
            <el-button @click="userxc">使用线程池</el-button>
            时间：{{time1}}
        </div>
      </div>
      </div>
      </el-scrollbar>
    </div>
</template>

<script>
export default {
  name: "skill4RabbitTest",
  data () {
    return {
        message:'',
        returnMessage:'',
        time:"0ms",
        time1:"0ms",
    }
  },
  created(){
  },
  methods:{
    nouserxc(){
       this.$http.get('/api/test/tProduct/testxc').then(res => {
          if (res.data.code == "200") {
              // this.$message.success("通过")
              let body = res.data.body
              this.time = body.time+'ms'
          }
      })
    },
    userxc(){
      this.$http.get('/api/test/tProduct/testxc2').then(res => {
          if (res.data.code == "200") {
              let body = res.data.body
              this.time1 = body.time+'ms'
          }
      })
    },
    miaosha(){
      this.$http.get('/api/test/tProduct/begin').then(res => {
          if (res.data.code == "200") {
              // this.$message.success("通过")
              console.log(res.data)
          }
      })
    },
    getMessage(){

    },
    sendMessage(){
      this.$http.get('/api/test/tProduct/sendMessage/'+ this.message).then(res => {
          if (res.data.code == "200") {
              // this.$message.success("通过")
              console.log(res.data)
          }
      })
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
  height: 300px;
}
.main{
  background-color: #ffffff;
  width: calc(100% - 20px);
}
.skill-h2{
  margin-top: 20px;
}
</style>