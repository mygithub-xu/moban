<template>

    <div class="app-container">
        <!-- 查询区域 -->
        <el-scrollbar style="width:100%">
        <div class="container-query">
            <queryItem label="测试字段：">
                <el-input v-model="queryContion.yyyccccc" placeholder="请输入测试字段"></el-input>
            </queryItem>
            <queryItem label="状态：">
                <el-select v-model="queryContion.status" placeholder="请选择"  clearable>
                  <el-option
                    v-for="item in selectData.statusList"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  ></el-option>
                </el-select>
            </queryItem>
            <queryItem label="用户：">
                <el-select v-model="queryContion.userId" placeholder="请选择"  clearable>
                  <el-option
                    v-for="item in selectData.userIdList"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                  ></el-option>
                </el-select>
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
                    <el-table-column prop="yyyccccc"  label="测试字段"></el-table-column>
                    <el-table-column prop="status"  label="状态"></el-table-column>
                    <el-table-column prop="userId"  label="用户名"></el-table-column>
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
        <yyyyEdit ref="yyyyEdit" @back="getData" :selectData="selectData"></yyyyEdit>
        </el-scrollbar>
    </div>
</template>
<script>
import yyyyEdit from "./edit"
export default {
    components: { yyyyEdit },
    name: "yyyyQuery",
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
          yyyccccc:"",
          status:"",
          userId:"",
        },
        //下拉框数据
        selectData: {
          statusList:[],
          userIdList:[],
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
                this.$http.get(this.api.dicTypeGetType + "status").then(res => {
                    this.selectData.statusList = res.data.body
                })
                this.$http.post("",{
                       condition: {},
                       number: 1,
                       size: 999
                   }).then(res => {
                  this.selectData.userIdList = res.data.body.records.map(item => ({
                     label: item.userName,
                     value: item.id
                   }))
                });
        },
        // 清空
        empty(){
            this.queryContion = {
                yyyccccc:"",
                status:"",
                userId:"",
            }
        },
        //获取数据
        getData(row){
            let queryContion = {
                condition: this.queryContion,
                number: this.pageData.pageNumber,
                size: this.pageData.pageSize
            }
            this.$http.post("api/system/yyyy/query",queryContion)
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
          this.$refs.yyyyEdit.openByEdit(row)
        },
        //删除
        handleDelete(row){
          this.$confirm("此操作将永久删除, 是否继续?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }).then(() =>
          {
              this.$http.delete("api/system/yyyy/deleteById/" + row.id)
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
          this.$refs.yyyyEdit.openByNew()
        },
        handleDeleteBatch(){
          this.$confirm("此操作将永久删除, 是否继续?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          })
          .then(() => {
            let deletebatch = this.$utils.findIds(this.delVal)
            this.$http.post("api/system/yyyy/batchDelete",deletebatch)
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