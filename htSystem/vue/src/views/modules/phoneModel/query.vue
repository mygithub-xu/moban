<template>

    <div class="app-container">
        <!-- 查询区域 -->
        <el-scrollbar style="width:100%">
        <div class="container-query">
            <queryItem label="终端型号：">
                <el-input v-model="queryContion.terminalModel" placeholder="请输入终端型号"></el-input>
            </queryItem>
            <queryItem label="终端名称：">
                <el-input v-model="queryContion.terminalName" placeholder="请输入终端名称"></el-input>
            </queryItem>
            <query-item isButton style="width:100%;padding-left:30px">
                <el-button type="primary"  @click="search" >搜索</el-button>
                <el-button  plain @click="reset" >重置</el-button>
                <el-button  plain @click="handleAdd" >新增</el-button>
            </query-item>
        </div>
        <!-- 数据表格 -->
        <div class="container-table">
            <div class="common-table-style">
                <el-table :data="pageData.list" border
                >
                    <el-table-column type="index" width="50" label="序号"></el-table-column>
                    <el-table-column prop="terminalModel"  label="终端型号"></el-table-column>
                    <el-table-column prop="terminalName"  label="终端名称"></el-table-column>
                    <el-table-column prop="terminalNumber"  label="终端编号"></el-table-column>
                    <el-table-column prop="terminalNameType"  label="终端型号名称"></el-table-column>
                    <el-table-column  label="操作">
                      <template slot-scope="scope">
                            <el-button @click="handleEdit(scope.row)" type="text">编辑</el-button>
                            <el-button @click="handleDelete(scope.row)" type="text" style="color:red">删除</el-button>
                      </template>
                    </el-table-column>
                  </el-table>
            </div>
            <div class="pagination">
              <pagination :page-list="pageData" @pagesearch="handlePage"></pagination>
            </div>
        </div>
        
        </el-scrollbar>
        <phoneModelEdit ref="phoneModelEdit" @back="getData"></phoneModelEdit>
    </div>
</template>
<script>
import phoneModelEdit from "./edit"
export default {
    components: { phoneModelEdit },
    name: "phoneModelQuery",
    data() {
      return {
        // 数据
        pageData: {
          list: [],
          pageNumber: 1,
          pageSize: 10,
          totalCount: 0,
          totalPage: 0
        },
        // 查询
        queryContion: {
          terminalModel:"",
          terminalName:"",
        },
        //下拉框数据
        selectData: {

        },
      }
    },
    created() {
      //获取表格数据
      this.getData()
      //获取下拉框数据
      this.getDropData()
    },
    methods:{

        //分页查询
        handlePage(number, size){
            this.pageData.pageNumber = number
            this.pageData.pageSize = size
            this.getData()
        },
        //获取下拉框数据
        getDropData(){

        },
        // 清空
        empty(){
            this.queryContion = {
                terminalModel:"",
                terminalName:"",
            }
        },
        //获取数据
        getData(row){
            let queryContion = {
                condition: this.queryContion,
                number: this.pageData.pageNumber,
                size: this.pageData.pageSize
            }
            this.$http.post("api/system/phoneModel/query",queryContion)
            .then(res => {
              this.pageData.list = res.data.body.records;
              this.pageData.totalCount = res.data.body.total;
              this.pageData.totalPage = res.data.body.pages;
            })
        },

        // 查询
        search(){
            this.getData()
        },
        // 重置
        reset(){
            this.empty()
            this.getData()
        },

        //编辑
        handleEdit(row) {
          this.$refs.phoneModelEdit.openByEdit(row)
        },
        //删除
        handleDelete(row){
          this.$confirm("此操作将永久删除, 是否继续?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }).then(() =>
          {
              this.$http.delete("api/system/phoneModel/deleteById/" + row.id)
              .then(res => {
                if(res.data.code == '200'){
                  this.$message.success(res.data.message)
                  this.getData()
                }
              })
          })
        },

        //新增
        handleAdd() {
          this.$refs.phoneModelEdit.openByNew()
        },
    }
}
</script>