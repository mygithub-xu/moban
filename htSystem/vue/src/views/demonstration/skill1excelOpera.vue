<template>
    <!-- 导入导出案例 -->
    <div class="app-container">
      <el-scrollbar>
      <div class="container-table">
      <h2 class="skill-h2">1.纯前端，导出组件(json转excel)：基于vue-json-excel实现的简单导出功能，只需在json中写数据，就能导出相应字段数据</h2>
      <div class="skill">
        <div class="skill-item">
          <el-input
            style="width:350px;margin-left:50px;margin-top:50px"
            type="textarea"
            :rows="10"
            placeholder="请输入内容"
            v-model="textarea">
          </el-input>
        </div>
        <div class="skill-item">
          <ja-export 
          style="margin-top:50px"
            :data ="getdata"
            :fields ="json_fields"
            name = "测试.xls">
            导出
          </ja-export>
        </div>
      </div>
      <h2 class="skill-h2">2.纯前端，导入组件(excel转json)：基于XLSX的导入组件做出修改。导入相应xls，自定义字段。形成相应的数据</h2>
      <div class="skill">
        <div class="skill-item">
          <el-input
            style="width:350px;margin-left:50px;margin-top:50px"
            type="textarea"
            :rows="10"
            placeholder="请输入内容"
            v-model="importData">
          </el-input>
        </div>
        <div class="skill-item">
          <ja-import 
            style="margin-top:50px"
            :fields ="json_fields"
            @success = "getImportData"
            >
            导入
          </ja-import>
        </div>
      </div>
      <h2 class="skill-h2">3.后端，将excel作为流的形式传给后台，后台使用阿里的easyExcel对其进行处理。一般这样的处理为样式比较复杂，且定制化的的excel。</h2>
      <div class="skill">
        <div class="skill-item">
          <el-input
            style="width:350px;margin-left:50px;margin-top:50px"
            type="textarea"
            :rows="10"
            placeholder="请输入内容"
            v-model="textarea">
          </el-input>
        </div>
        <div class="skill-item">
          <el-button @click = "down">导出简单xlsx</el-button>
          <el-button @click = "down">导出复杂且定制化xlsx</el-button>
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
      importData:'',
      json_fields:{
        '创建时间': 'createTime',
        '测试名称': 'createTime',
        '测试单价': 'testUnit',
        '测试总金额': 'testTotal',
        '测试状态': {
          field: "testStatus",
          callback: value => {
            return value == 1 ?'启用' : '禁用';
          },
          getVal: value => {
            return value == '启用' ?1 : 2;
          }
        },
        '测试数量': 'testNum'
      },
      textarea:'[{"id":"3a127478b37948088236bb421167bae3","testName":"测试二三111","createTime":"2020-06-15 00:00:00","createUser":"4fcee6c3f7164300ab75e67b32871145","testUnit":0.99,"testTotal":98.01,"testType":"5,3","testStatus":"1","testNum":22,"parentId":null,"testArea":"bbbb"},{"id":"79df40025f324e0cad949c470fa4b428","testName":"pppp","createTime":"2021-01-12 02:00:00","createUser":"","testUnit":null,"testTotal":null,"testType":"","testStatus":"","testNum":null,"parentId":"","testArea":""}]'
    }
  },
  computed:{
    getdata(){
      return JSON.parse(this.textarea)
    }
  },
  methods:{
    down () {
      this.$utils.downFile(this.api.sysTestDown,JSON.parse(this.textarea),'测试.xlsx')
    },
    getImportData (data) {
      this.importData = JSON.stringify(data)
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