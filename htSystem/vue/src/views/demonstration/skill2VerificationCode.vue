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
            
        </div>
        <div class="skill-item">
            <el-button @click="verCode">生成验证码</el-button>
        </div>
      </div>

      </div>
      </el-scrollbar>
    </div>
</template>

<script>
export default {
  name: "excelOpera",
  data () {
    return {
        code: ""
    }
  },
  computed:{
  },
  methods:{
      verCode(){

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
</style>