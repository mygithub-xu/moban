<template>

    <div class="app-container">
        <!-- 查询区域 -->
        <el-scrollbar style="width:100%">
        <div class="container-query">
            <queryItem label="族谱：">
                <el-select v-model="queryContion.tableId" placeholder="请选择"  clearable>
                  <el-option
                    v-for="item in selectData.tableIdList"
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
                <el-button type="primary"  @click="search" >查询</el-button>
                <el-button  plain @click="reset" >重置</el-button>
                <el-button  plain @click="handleDeleteBatch" type="danger">删除</el-button>
                <el-button  plain @click="handleAdd" >新增</el-button>
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
                    <el-table-column prop="tableId"  label="族谱">
                          <template slot-scope="scope">
                            <template v-for="item in selectData.tableIdList" >
                              <span :key="item.value" v-if="scope.row.userId == item.value">{{item.label}}</span>
                            </template>
                          </template>
                    </el-table-column>
                    <el-table-column prop="userId"  label="用户">
                          <template slot-scope="scope">
                            <template v-for="item in selectData.userIdList" >
                              <span :key="item.value" v-if="scope.row.userId == item.value">{{item.label}}</span>
                            </template>
                          </template>
                    </el-table-column>
                    <el-table-column  label="操作">
                      <template slot-scope="scope">
                            <el-button @click="handleDelete(scope.row)" type="text" style="color:red">删除</el-button>
                            <el-button @click="handleEdit(scope.row)" type="text">编辑</el-button>
                      </template>
                    </el-table-column>
                  </el-table>
            </div>
            <div class="pagination">
              <pagination :page-list="pageData" @pagesearch="handlePage"></pagination>
            </div>
        </div>
        <tableUserEdit ref="tableUserEdit" @back="getData" :selectData="selectData"></tableUserEdit>
        </el-scrollbar>
    </div>
</template>
<script>
import tableUserEdit from "./edit"
export default {
    components: { tableUserEdit },
    name: "tableUserQuery",
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
          tableId:"",
          userId:"",
        },
        //下拉框数据
        selectData: {
          userIdList:[],
          tableIdList:[],
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
                this.$http.post("api/familyTree/faUser/query",{
                       condition: {},
                       number: 1,
                       size: 999
                   }).then(res => {
                  this.selectData.userIdList = res.data.body.records.map(item => ({
                     label: item.realName,
                     value: item.id
                   }))
                });
                this.$http.post("api/familyTree/faTable/query",{
                       condition: {},
                       number: 1,
                       size: 999
                   }).then(res => {
                  this.selectData.tableIdList = res.data.body.records.map(item => ({
                     label: item.name,
                     value: item.id
                   }))
                });
        },
        // 清空
        empty(){
            this.queryContion = {
                tableId:"",
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
            this.$http.post("api/familyTree/tableUser/query",queryContion)
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

        //删除
        handleDelete(row){
          this.$confirm("此操作将永久删除, 是否继续?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }).then(() =>
          {
              this.$http.delete("api/familyTree/tableUser/deleteById/" + row.id)
              .then(res => {
                if(res.data.code == '200'){
                  this.$message.success(res.data.message)
                  this.getData()
                }
              })
          })
        },
        //编辑
        handleEdit(row) {
          this.$refs.tableUserEdit.openByEdit(row)
        },

        handleDeleteBatch(){
          this.$confirm("此操作将永久删除, 是否继续?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          })
          .then(() => {
            let deletebatch = this.$utils.findIds(this.delVal)
            this.$http.post("api/familyTree/tableUser/batchDelete",deletebatch)
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
          this.$refs.tableUserEdit.openByNew()
        },
        //勾选
        handleSelectionChange(val){
          this.delVal = val
        }
    }
}
</script>