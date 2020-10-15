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
                        <el-button  icon="el-icon-close">取消</el-button>
                    </span>
            </div>
            <div class="common-table-style">
                <el-table :data="pageData.list" height="100%" border header-align="center" @selection-change="handleSelectionChange">
                    <el-table-column fixed type="selection"  ></el-table-column>
                    <el-table-column type="index" width="50" label="序号"></el-table-column>
                    <el-table-column prop="tableName"  label="表名" show-overflow-tooltip ></el-table-column>
                    <el-table-column prop="tableType"  label="表类型" show-overflow-tooltip></el-table-column>

                    <el-table-column prop="type"  label="状态"  show-overflow-tooltip>
                        <template slot-scope="scope">
                        <span v-if="scope.row.type=='1'">启用</span>
                        <span v v-if="scope.row.type=='2'">禁用</span>
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

        <codeEdit ref="edit" ></codeEdit>
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
            }
        },
        created(){
            this.getdata();
        },
        methods:{
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

            }
        }

    }
</script>
<style lang="scss" scope>

</style>