<template>
  <div class="app-container">
    <div class="container-query">
      <el-scrollbar class="query-scrollbar">
      <el-form :model="condition" ref="form" label-width="100px" size="small">

              <el-form-item label="登录地址：" prop="loginAddress">
                <el-input  v-model="condition.loginAddress" autocomplete="off"></el-input>
              </el-form-item>

              <el-form-item label="登录名称：" prop="loginAddress">
                <el-input  v-model="condition.loginAddress" autocomplete="off"></el-input>
              </el-form-item>

             <el-form-item label="登录状态：">
                <el-select v-model="condition.loginStatus"  placeholder="请选择">
                  <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  ></el-option>
                </el-select>
             </el-form-item>

              <el-form-item label="登录时间：">
                <div>
                  <el-date-picker
                    v-model="condition.startTime"
                    value-format="yyyy-MM-dd"
                    type="date"
                    placeholder="开始时间"
                    style="width:47%;float: left;"
                  ></el-date-picker>
                  <span class="span_align">-</span>
                  <el-date-picker
                    type="date"
                    v-model="condition.endTime"
                    value-format="yyyy-MM-dd"
                    placeholder="结束时间"
                    style="width:47%;float: right;"
                  ></el-date-picker>
                </div>
              </el-form-item>

              <el-button style="margin: 0 0 0 18px;" type="primary" icon="el-icon-search" @click="getdata"  size="small">搜索</el-button>
              <el-button size="small" @click="empty" icon="el-icon-refresh"  >重置</el-button>
      </el-form>
      </el-scrollbar>
    </div>
    <div class="container-table">
      <div>
        <span>
            <el-button style="margin: 0 0 0 18px;" type="danger" icon="el-icon-delete" @click="handleDeleteBatch" :disabled="dicDisabled"  size="small">删除</el-button>
            <el-button  size="small" @click="handleExport" icon="icon-upload"  >导出</el-button>
        </span>
      </div>
      <div class="common-table-style">
        <el-table :data="pageData.list" :row-style="{height:'50px'}" border class="table" ref="multipleTable" 
        align="center" @selection-change="handleSelectionChange" height="100%">
            <el-table-column type="selection"  fixed width="55"  align="center" show-overflow-tooltip></el-table-column>
            <el-table-column min-width="100" prop="userName" label="登录名称" align="center" show-overflow-tooltip></el-table-column>
            <el-table-column min-width="150" prop="loginIp" label="登录地址" align="center" show-overflow-tooltip></el-table-column>
            <el-table-column min-width="100" prop="browserName" label="浏览器" align="center" show-overflow-tooltip></el-table-column>
            <el-table-column min-width="100" prop="loginStatus" label="登录状态" align="center"  show-overflow-tooltip>
              <template slot-scope="scope">
                <div v-for="item in options" :key="item.value">
                  <el-tag
                    :type="item.value=='1'?'success':'danger'"
                    v-if="scope.row.loginStatus==item.value"
                  >{{item.label}}</el-tag>
                </div>
              </template>
            </el-table-column>
        </el-table>
      </div>
    </div>
    <div class="pagination">
        <pagination :page-list="pageData" @pagesearch="handlePostPage"></pagination>
    </div>
  </div>
</template>
<script>
export default {
  name:"sysLoginLog",
  data(){
    return{
      condition:{
        loginAddress: "",
        userName: "",
        loginStatus:"",
        startTime: "",
        endTime: "",
      },
      options:[],//下拉数据
      dicDisabled:true,
      multipleSelection:[],//选中的数据集合
      pageData: {
        list: [],
        pageNumber: 1,
        pageSize: 10,
        totalCount: 0,
        totalPage: 0
      },
    }
  },
  created(){
    this.getdata();
    this.getxiala();
  },
  methods:{
    getdata(){
      this.$http
        .post(this.api.LoginLogQuery, {
          condition:this.condition,
          number: this.pageData.pageNumber,
          size: this.pageData.pageSize
        })
        .then(res => {
          this.pageData.list = res.data.body.records;
          this.pageData.totalCount = res.data.body.total;
          this.pageData.totalPage = res.data.body.pages;
        });
    },
    empty(){
      this.condition={
        loginAddress: "",
        userName: "",
        startTime: "",
        endTime: "",
      }
      this.dicDisabled = true;
    },
    //勾选数据集合
    handleSelectionChange(val){
      if (val.length > 0) {
        this.multipleSelection = val;
        this.dicDisabled = false;
      } else {
        this.dicDisabled = true;
      }
    },
    handlePostPage(number, size){
      this.pageData.pageNumber=number;
      this.pageData.pageSize=size;
      this.getdata();
    },
    handleDeleteBatch(){
      let deletebatch = [];
      this.multipleSelection.forEach(i => {
        deletebatch.push(i.id);
      });
      this.$confirm("此操作将永久删除, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$http .post(this.api.LoginLogBatchdelete, deletebatch).then(res => {
              if (res.data.code == "200") {
                this.$message.success(res.data.message);
              }else{
                this.$message.warning(res.data.message);
              }
              this.getdata();
              this.dicDisabled=true;
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消批量删除"
          });
        });
        
    },
    handleExport(){
            //     this.$http.get(this.api.exceldaochu).then(res => {
            // });

      this.$axios({
          method: 'get',
          url:this.api.exceldaochu,
          responseType: 'blob'
        })
      
        .then(response => {
          let blob = new Blob([response.data], {
            type:
              "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
          });
          let url = window.URL.createObjectURL(blob); 
          let a = document.createElement("a");
          a.href = url;
          a.download = "用户注册表.xlsx";
          a.click();
          window.URL.revokeObjectURL(url);
        })


    },
    getxiala(){
      this.$http.get(this.api.dicTypeGetType + "loginStatus").then(res => {
        if (res.data.code == 200) {
          this.options = res.data.body;
        }
      });
    }

  },

}
</script>
<style lang="less" scoped>
.container2{
  border: 0px solid #ddd;
  border-radius: 5px;
  height: 100%;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  background-color: #f0f0f0;
  // position: relative;
  padding:10px 10px;
}
.container2-search{
  border-radius: 5px;
  box-shadow: 1px 1px 3px rgba(0,0,0,0.2);
  padding: 7px 10px;
  background: #fff;
  height: 80px;
}
.container2-body{
  border-radius: 5px;
  height: 100%;
  width: 100%;
  float: left;
  padding-bottom: 45px;
  border-radius: 5px;
  box-shadow: 1px 1px 3px rgba(0,0,0,0.2);
  background: #fff;
}
.container2-page{
  position: absolute;
  bottom: 15px;
  right: 20px;
}
.container2-button{
  height: 40px;
  width: 100%;
  margin-top: 5px;
}
.container2-table{
  height: 100%;
  padding: 10px 10px;
}
//表头高度调整
.el-table__header th {
  padding: 0;
  height: 50px ;
}
</style>