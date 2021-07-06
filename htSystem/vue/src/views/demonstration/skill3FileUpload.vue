<template>
    <!-- 导入导出案例 -->
    <div class="app-container">
      <el-scrollbar>
      <div class="container-table">
      <h2 class="skill-h2">1.文件上传组件演示实例带进度条</h2>
      <div class="skill">
        <div class="skill-item">
            <upload showDown style="margin-left:100px"></upload>
        </div>
        <div class="skill-item">
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
        code: "",
        captchaPath:""
    }
  },
  created(){
  },
  methods:{
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