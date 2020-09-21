<template>
    <div class="app-container">
        <div class="container">
            <!-- 区域一 查询区域 -->
            <div class="container_btn" >
            <span>
            <el-button type="warning" size="small"   @click="handleAdd">新增</el-button>
            <el-button type="danger" size="small" :disabled="dicDisabled"  @click="handleDeleteBatch">删除</el-button>
            <el-button type="primary" plain size="small"   @click="search">刷新</el-button>
            </span>
                <span class="handle-box" style="float:right">
              <el-input
                      v-model="condition.queryContent"  size="small" placeholder="请输入查询内容" class="handle-input mr10" style="width: 250px;">
              </el-input>
                <el-button type="primary" plain icon="el-icon-search"  size="small"  @click="search">查询</el-button>
                <el-button type="info" plain size="small" @click="reset">重置</el-button>
                <el-row class="tac">
                  <el-col :span="24">
                    <el-dropdown trigger="click" :hide-on-click="false">
                      <div class="el-icon-menu"></div>
                      <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item>
                          <el-checkbox v-model="xuhao">序号</el-checkbox>
                        </el-dropdown-item>
                      </el-dropdown-menu>
                    </el-dropdown>
                  </el-col>
                </el-row>
            </span>
            </div>
            <!-- 区域二---表格+分页 -->
            <el-table :data="pageData.list" style="width: 100%" height="100%" :row-style="{height:'50px'}" border class="table" ref="multipleTable" align="center" @selection-change="handleSelectionChange" >
                <el-table-column type="selection"  fixed width="45"  align="center" show-overflow-tooltip></el-table-column>
                <el-table-column type="index" width="55" label="序号" align="center" v-if="xuhao" show-overflow-tooltip></el-table-column>

                <el-table-column label="操作" fixed="right" min-width="160" align="center" show-overflow-tooltip>
                    <template slot-scope="scope">
                        <el-button type="text" icon="el-icon-edit" @click="handleEdit(scope.row)" >编辑</el-button>
                        <el-button type="text" icon="el-icon-delete" class="red"  @click="handleDelete(scope.row.id)" >删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="pagination">
                <pagination :page-list="pageData" @pagesearch="handlePage"></pagination>
            </div>
        </div>
        <!-- 区域三---弹出框，覆盖全部 -->
        <div class="showDialog">
            <el-dialog  :visible.sync="editVisible"  :show-close="false" :modal="false" fullscreen>
                <div class="dialogFix">
                    <el-button type="primary" size="small"  @click="handleSave('form')">保 存</el-button>
                    <el-button size="small" @click="cancelSave">返 回</el-button>
                </div>
                <el-row class="dialogForm">
                    <el-col :span="24">
                        <div style="margin-top: 42px;">

                            <el-form  ref="form" :model="form" :rules="rules" label-width="150px" size="small" style="margin-top:10px">

                            </el-form>

                        </div>
                    </el-col>
                </el-row>
            </el-dialog>
        </div>

    </div>
</template>
<script>
    export default {
        data(){
            return{
                condition: {
                    queryContent: "",//查询内容
                },
                xuhao:true,
                dicDisabled:true,
                pageData: {
                    list: [],
                    pageNumber: 1,
                    pageSize: 10,
                    totalCount: 0,
                    totalPage: 0
                },
                multipleSelection:[],//选中的数据集合
                form :{
                },
                editVisible:false,
                rules: {
                },
            }
        },

        created(){
            this.getdata();
        },
        methods:{
            //新增
            handleAdd(){
                this.editVisible=true;
                this.empty();
            },
            //批量删除--不需要批量删除请将其注释，，
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
                    this.$http .post(this.api.sysTestBatchDelete, deletebatch).then(res => {
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
            //查询
            search(){
                this.getdata();
            },
            //重置
            reset(){
                this.condition.queryContent="";
                this.pageData.pageNumber=1;
                this.pageData.pageSize=10;
                this.getdata();
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
            //编辑
            handleEdit(row){
                this.editVisible=true;
                this.form=row;
            },
            //单个删除
            handleDelete(id){
                this.$confirm("此操作将永久删除, 是否继续?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                })
                .then(() => {
                    this.$http
                    .delete(this.api.sysTestDeletebyid + id).then(res => {
                        if (res.data.code == "200") {
                    this.$message({
                        message: "删除数据成功",
                        type: "success"
                    });
                }
                this.getdata();
            });
            })
            .catch(() => {
                    this.$message({
                        type: "info",
                        message: "已取消删除"
                    });
            });
            },
            //页数
            handlePage(number, size){
                this.pageData.pageNumber=number;
                this.pageData.pageSize=size;
                this.getdata();
            },
            //表格数据
            getdata(){
                this.getxiala();
                this.$http
                        .post(this.api.sysTestQueryByCondition, {
                            condition: this.condition,
                            number: this.pageData.pageNumber,
                            size: this.pageData.pageSize
                        })
                        .then(res => {
                    if (res.data.code == "200") {
                    this.pageData.list = res.data.body.records;
                    this.pageData.totalCount = res.data.body.total;
                    this.pageData.totalPage = res.data.body.pages;
                }
            });
            },
            //保存/修改
            handleSave(){
                this.$refs
                     .form.validate(valid => {if (valid) {
                this.$http
                     .post(this.api.sysTestSaveOrUpdate, this.form).then(res=> {
                    if (res.data.code == "200") {
                    this.$message
                            .success(res.data.message);
                    this.editVisible = false;
                    this.empty();
                    this.getdata();
                    }else{
                    this.$message
                            .error(res.data.message);
                    }
                    });
                }
                })
            },
            cancelSave(){
                this.editVisible=false;
            },
            empty(){
                this.form={
                }
            },
            //获取下拉数据
            getxiala(){
                //用户等级
                //this.$http
                //        .get(this.api.dicTypeGetType + "membershipLevel").then(res => {
                //if (res.data.code == 200) {
                //this.membershipLevelList = res.data.body;
                //this.membershipLevelList.forEach(v => {
                //v.value=parseInt(v.value);
                // });
                // }
                // });

            },


        },

    }
</script>
<style lang="scss" scope>
    //表头高度调整
    .el-table__header th {
        padding: 0;
        height: 50px ;
    }
    // 弹出框样式
       .showDialog .el-dialog__body{
           padding: 5px 20px;
       }
    .red{
        color: rgb(243, 81, 94);
    }
    .table{
        height:100%;
        width: 100%;
    }
</style>