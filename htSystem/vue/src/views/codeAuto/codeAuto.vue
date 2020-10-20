<template>
    <div class="app-container">
        <div class="container-table"> 
            <div class="container-btn">
                    <span class="container-btn-left">
                    <el-button type="primary"  icon="el-icon-plus" @click="handleCreateTable">创建表</el-button>
                    <el-button type="primary"  icon="el-icon-edit" @click="handleEdit">从数据库导入表</el-button>
                    <el-button type="danger"  icon="el-icon-delete" @click="handleDeleteBatch">删除</el-button>
                    </span>
                    <span class="container-btn-right">
                        <!-- <el-button  icon="el-icon-close">取消</el-button> -->
                    </span>
            </div>
            <div class="common-table-style">
                <el-table :data="pageData.list" height="100%" border header-align="center" @selection-change="handleSelectionChange">
                    <el-table-column fixed type="selection"  ></el-table-column>
                    <el-table-column type="index" width="50" label="序号"></el-table-column>
                    <el-table-column prop="tableName"  label="表名" show-overflow-tooltip ></el-table-column>
                    <el-table-column prop="parentId"  label="父表" show-overflow-tooltip></el-table-column>

                    <el-table-column prop="status"  label="状态"  show-overflow-tooltip>
                        <template slot-scope="scope">
                            <span v-if="scope.row.status=='1'">启用</span>
                            <span v-if="scope.row.status=='2'">禁用</span>
                        </template>
                    </el-table-column>
                    <el-table-column prop="type"  label="模板类型" show-overflow-tooltip>
                            <template slot-scope="scope">
                                <span v-if="scope.row.type=='1'">启用</span>
                                <span v-if="scope.row.type=='2'">禁用</span>
                            </template>
                    </el-table-column>
                    <el-table-column prop="remark" label="备注" show-overflow-tooltip>
                    </el-table-column>
                </el-table>
            </div>

        </div>

        <div class="pagination">
            <pagination :page-list="pageData" @pagesearch="handlePage"></pagination>
        </div>

        <codeEdit ref="edit" :xiala="xiala"></codeEdit>
    </div>
</template>
<script>
    import codeEdit from "./edit";
    export default {
        name:"codeAuto",
        components: {codeEdit},
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
                    tableData:[]
                }

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
                
            },
            //新增
            handleAdd() {
                this.editVisible = true;
            },
            handleEdit(){

            },
            handleDeleteBatch(){

            },
            handleSelectionChange(){

            },
            handleCreateTable(){
                this.$refs['edit'].editVisible = true
            },
            handlePage(){

            },
            getdata(){
                this.$http.get(this.api.dicTypeGetType + "autoTableTemplate").then(res => {
                    if (res.data.code == "200") {
                        this.pageData.list = res.data.body.records;
                        this.xiala.tableData = res.data.body.records;
                    }
                    
                })
            }
        }

    }
</script>
<style lang="scss" scope>

</style>