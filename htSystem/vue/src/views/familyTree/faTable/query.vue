<template>

    <div class="app-container">
        <!-- 查询区域 -->
        <el-scrollbar style="width:100%">
        <div class="container-query">
            <queryItem label="族谱号：">
                <el-input v-model="queryContion.code" placeholder="请输入族谱号"></el-input>
            </queryItem>
            <query-item isButton style="width:100%;padding-left:30px">
                <el-button type="primary"  @click="search" >搜索</el-button>
                <el-button  plain @click="reset" >重置</el-button>
                <el-button  plain @click="handleAdd" >新增</el-button>
                <el-button  plain @click="handleDeleteBatch" type="danger">删除</el-button>
            </query-item>
        </div>
        <!-- 数据表格 -->
        <div class="container-table">
            <div class="common-table-style">
                <el-table :data="pageData.list" border
                 @selection-change="handleSelectionChange"
                >
                    <el-table-column type="selection"></el-table-column>
                    <el-table-column type="index" width="50" label="序号"></el-table-column>
                    <el-table-column prop="name"  label="族谱名">
                    </el-table-column>
                    <el-table-column prop="code"  label="族谱号">
                    </el-table-column>
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
        <faTableEdit ref="faTableEdit" @back="getData" :selectData="selectData"></faTableEdit>
        </el-scrollbar>
    </div>
</template>
<script>
import faTableEdit from "./edit"
export default {
    components: { faTableEdit },
    name: "faTableQuery",
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
          code:"",
        },
        //下拉框数据
        selectData: {
        },
        //批量删除
        delVal:""
      }
    },
    created() {
      //获取下拉框数据
      this.getDropData()
      //获取表格数据
      this.getData()
    },
    methods:{
        //获取下拉框数据
        getDropData(){
        },
        // 清空
        empty(){
            this.queryContion = {
                code:"",
            }
        },
        //获取数据
        getData(row){
            let queryContion = {
                condition: this.queryContion,
                number: this.pageData.pageNumber,
                size: this.pageData.pageSize
            }
            this.$http.post("api/familyTree/faTable/query",queryContion)
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
        //分页查询
        handlePage(number, size){
            this.pageData.pageNumber = number
            this.pageData.pageSize = size
            this.getData()
        },

        //编辑
        handleEdit(row) {
          this.$refs.faTableEdit.openByEdit(row)
        },
        //删除
        handleDelete(row){
          this.$confirm("此操作将永久删除, 是否继续?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }).then(() =>
          {
              this.$http.delete("api/familyTree/faTable/deleteById/" + row.id)
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
          this.$refs.faTableEdit.openByNew()
        },
        handleDeleteBatch(){
          this.$confirm("此操作将永久删除, 是否继续?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          })
          .then(() => {
            let deletebatch = this.$utils.findIds(this.delVal)
            this.$http.post("api/familyTree/faTable/batchDelete",deletebatch)
            .then(res => {
              if(res.data.code == '200'){
                this.$message.success(res.data.message)
                this.getData()
              }
            })
          })
        },
        //勾选
        handleSelectionChange(val){
          this.delVal = val
        }
    }
}
</script>