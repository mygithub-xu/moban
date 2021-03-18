<template>
    <div class="app-container">
        <!-- 查询区域 -->

        <el-scrollbar style="width:100%">
        <template v-show="frameFontvisible">
        <div class="container-query">
            <queryItem label="模板名：">
                <el-input v-model="queryContion.temName" placeholder="请输入模板名"></el-input>
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
                    <el-table-column prop="temPlateName"  label="模板名"></el-table-column>
                    <el-table-column prop="createTime"  label="创建时间"></el-table-column>
                    <el-table-column prop="createUser"  label="创建人"></el-table-column>
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
        </template>
        </el-scrollbar>
        <template v-if="!frameFontvisible">
            <editLayout ref="editLayout" isFromTem @backfont="backfont"></editLayout>
        </template>
    </div>
</template>
<script>
import editLayout from "../codeAuto/editLayout"
export default {
    components: { editLayout },
    name: "sysAutoTemQuery",
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
          temName:"",
        },
        //下拉框数据
        selectData: {
        },
        frameFontvisible:true
      }
    },
    created() {
      //获取表格数据
      this.getData()
      //获取下拉框数据
      this.getDropData()
    },
    methods:{
        //获取下拉框数据
        getDropData(){
        },
        // 清空
        empty(){
            this.queryContion = {
                temName:"",
            }
        },
        //获取数据
        getData(){
          this.queryContion.isTemplate = '1'
            let queryContion = {
                condition: this.queryContion,
                number: this.pageData.pageNumber,
                size: this.pageData.pageSize
            }
            this.$http.post(this.api.sysAutoTableQuerybycondition,queryContion)
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
          debugger
          this.frameFontvisible = false
          this.$refs.editLayout.editInit(row)
        },
        //删除
        handleDelete(row){
          this.$confirm("此操作将永久删除, 是否继续?", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }).then(() =>
          {
              this.$http.delete(this.api.sysAutoTabledeleteById + row.id)
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
          this.frameFontvisible = false
        },
        backfont(){
          this.frameFontvisible = true
          this.getData()
        }
    }
}
</script>