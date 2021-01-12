<template>
    <div class="app-container">
        <template v-if="frameFontvisible">
        <div class="container-table" > 
            <div class="container-btn">
                    <span class="container-btn-left">
                        <el-button type="primary"  icon="el-icon-plus" @click="handleCreateTable">创建表</el-button>
                        <el-button type="primary"  icon="el-icon-edit" @click="handleEdit">从数据库导入表</el-button>
                        <!-- <el-button type="danger"  icon="el-icon-delete" @click="handleDeleteBatch">删除</el-button> -->
                    </span>
                    <span class="container-btn-right">
                        <el-button plain icon="el-icon-refresh" @click="getdata()"></el-button>
                    </span>
            </div>
            <div class="common-table-style">
                <el-table :data="pageData.list"  border @selection-change="handleSelectionChange">
                    <el-table-column  type="selection"  ></el-table-column>
                    <el-table-column type="index" width="50" label="序号"></el-table-column>
                    <el-table-column prop="tableName" align="center"  label="表名" show-overflow-tooltip ></el-table-column>
                    <el-table-column prop="parentId"  align="center" label="父表" show-overflow-tooltip>
                        <template slot-scope="scope">
                             <span v-if="scope.row.parentId">无</span>
                             <span v-else>{{scope.row.parentId}}</span>
                        </template>

                    </el-table-column>

                    <el-table-column prop="status" align="center" label="状态"  show-overflow-tooltip>
                        <template slot-scope="scope">
                            <span v-if="scope.row.status=='0'">启用</span>
                            <span v-if="scope.row.status=='1'">禁用</span>
                        </template>
                    </el-table-column>

                    <el-table-column prop="type"  align="center" label="模板类型" show-overflow-tooltip>
                        <template slot-scope="scope">
                            <span v-if="scope.row.type=='0'">启用</span>
                            <span v-if="scope.row.type=='1'">禁用</span>
                        </template>
                    </el-table-column>

                    <el-table-column prop="remark" align="center" label="备注" show-overflow-tooltip>
                    </el-table-column>

                    <el-table-column label="操作" width="250" align="center">
                        <template slot-scope="scope">
                            <el-button type="text" icon="el-icon-edit" @click="handleEdit(scope.row)">编辑</el-button>
                            <el-button type="text" icon="el-icon-delete" @click="handleDelete(scope.row)" style="color:red">删除</el-button>
                            <el-button type="text"  @click="handleGenerateFront(scope.row)">模板编制</el-button>
                        </template>
                    </el-table-column>

                </el-table>
            </div>
            <div class="pagination" v-if="frameFontvisible">
                <pagination :page-list="pageData" @pagesearch="handlePage"></pagination>
            </div>
        </div>
        </template>

        <template v-else>
            <editLayout ref="editLayout" @backfont="backfont"></editLayout>
        </template>

        <codeEdit ref="edit" :xiala="xiala"></codeEdit>

    </div>
</template>
<script>
    import codeEdit from "./edit";
    import editLayout from "./editLayout";
    export default {
        name:"codeAuto",
        components: {codeEdit,editLayout},
        data(){
            return{
                pageData: {
                    list: [],
                    pageNumber: 1,
                    pageSize: 10,
                    totalCount: 0,
                    totalPage: 0
                },
                xiala:{
                    tableTypeList:[],
                    templateList:[],
                    statusList:[],
                    tableData:[],
                    fieldTypeList:[]
                },
                frameFontvisible: true

            }
        },
        created(){
            this.getdata();
            this.getxiala();
        },
        methods:{
            getxiala(){
                //生成状态
                this.$http.get(this.api.dicTypeGetType + "autoTableStatus").then(res => {
                    this.xiala.statusList = res.data.body
                })
                //表类型
                this.$http.get(this.api.dicTypeGetType + "autoTableType").then(res => {
                    this.xiala.tableTypeList = res.data.body
                })
                //模板类型
                this.$http.get(this.api.dicTypeGetType + "autoTableTemplate").then(res => {
                    this.xiala.templateList = res.data.body
                })
                //字段类型
                this.$http.get(this.api.dicTypeGetType + "fieldType").then(res => {
                    this.xiala.fieldTypeList = res.data.body
                })
                
            },

            handleEdit(row){
                this.$refs['edit'].editInit(row);
            },
            handleDelete(row){
                this.$confirm("此操作将永久删除, 是否继续?", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
                }).then(() => {
                    this.$http.delete(this.api.sysAutoTabledeleteById + row.id).then(res => {
                    if (res.data.code == "200") {
                        this.$message.success( "删除数据成功");
                    }
                    this.getdata();
                    });
                }) .catch(() => {
                    this.$message.info("已取消删除");
                });
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
                    this.$http.post(this.api.sysAutoTableBatchDelete, deletebatch).then(res => {
                    if (res.data.code == "200") {
                        this.$message({
                        message: "批量删除数据成功",
                        type: "success"
                        });
                    }
                    this.getdata();
                    });
                })
                .catch(() => {
                    this.$message({
                    type: "info",
                    message: "已取消批量删除"
                    });
                });
            },
            handleSelectionChange(){

            },
            handleCreateTable(){
                this.$refs['edit'].empty();
            },
            handlePage(){

            },
            getdata(){
                this.$http.post(this.api.sysAutoTableQuerybycondition,{
                    condition: {},
                    number: this.pageData.pageNumber,
                    size: this.pageData.pageSize
                }).then(res => {
                    if (res.data.code == "200") {
                        this.pageData.list = res.data.body.records
                        this.pageData.totalCount = res.data.body.total
                        this.pageData.totalPage = res.data.body.pages
                    }
                    
                })
            },

            handleGenerateFront(row){
                this.frameFontvisible = false;
                this.$nextTick(()=>{
                    this.$refs['editLayout'].editInit(row);
                })
                
            },
            backfont(){
                this.frameFontvisible = true;
            }
        }

    }
</script>
<style  scope>

</style>