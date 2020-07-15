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
                            v-model="queryContent"  size="small" placeholder="请输入名称查询" class="handle-input mr10" style="width: 200px;">
                    </el-input>
                        <el-button type="primary" plain icon="el-icon-search"  size="small"  @click="search">查询</el-button>
                        <el-button type="info" plain size="small" @click="reset">重置</el-button>
                    </span>
            </div>
            <!-- 区域二---表格+分页 -->
            <el-table :data="pageData.list" style="width: 100%" height="100%" :row-style="{height:'50px'}" border class="table" ref="multipleTable" align="center" @selection-change="handleSelectionChange" >
                <el-table-column type="selection"  fixed width="45"  align="center" show-overflow-tooltip></el-table-column>
                <el-table-column type="index" width="55" label="序号" align="center" v-if="xuhao" show-overflow-tooltip></el-table-column>
                        <el-table-column prop="address" min-width="140" label="地址" align="center" show-overflow-tooltip>

                        </el-table-column>
                        <el-table-column prop="gender" min-width="140" label="性别" align="center" show-overflow-tooltip>
                            
                                <template slot-scope="scope">
                                   
                                        <div v-for="item in genderList" :key="item.value">
                                            <el-tag
                                                    type="success"
                                                    v-if="scope.row.gender==item.value"
                                            >{{item.label}}</el-tag>
                                        </div>
                                <!-- <el-tag type="success" v-if="scope.row.gender==1"  >男</el-tag>
                                <el-tag type="success" v-if="scope.row.gender==2"  >女</el-tag> -->
                                </template>

                        </el-table-column>
                        <el-table-column prop="name" min-width="140" label="名称" align="center" show-overflow-tooltip>

                        </el-table-column>

                <el-table-column label="操作" fixed="right" min-width="160" align="center" show-overflow-tooltip>
                    <template slot-scope="scope">

                        <el-button type="text" icon="el-icon-edit" @click="handleEdit(scope.row)" >编辑</el-button>
                        <el-button type="text" icon="el-icon-delete" class="red"  @click="handleDelete(scope.row)" >删除</el-button>
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
                    <template v-if="!preVisiable">
                        <el-button type="primary" size="small"  @click="handleSave('form')">保 存</el-button>
                    </template>
                    <el-button size="small" @click="cancelSave">返 回</el-button>
                </div>
                <el-row class="dialogForm">
                    <el-col :span="24">
                        <div style="margin-top: 42px;">

                            <el-form  ref="form" :model="form" :rules="rules" label-width="150px" size="small" style="margin-top:10px">   

                                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                                <el-form-item label="名称" prop="name" >
                                                    <el-input v-model="form.name" :disabled="preVisiable"></el-input>
                                                </el-form-item>
                                            </el-col>
                      


                                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                                <el-form-item label="性别" prop="gender" >
                                                    <el-select v-model="form.gender"  placeholder="请选择"  style="width:100%;" :disabled="preVisiable">
                                                        <el-option
                                                                v-for="item in genderList"
                                                                :key="item.value"
                                                                :label="item.label"
                                                                :value="item.value"
                                                        ></el-option>
                                                    </el-select>

                                                </el-form-item>
                                            </el-col>

                                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                                <el-form-item label="地址" prop="address" >
                                                    <el-input v-model="form.address" :disabled="preVisiable"></el-input>
                                                </el-form-item>
                                            </el-col>

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
                queryContent:"",
                preVisiable:false,
                condition: {
                    id: "",
                        address: "",
                        gender: "",
                        name: "",
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
                    address: "",
                    gender: "",
                    id: "",
                    name: "",
            },
            editVisible:false,
            rules: {


            },
            queryVisible:{
                        address:false,
                        gender:false,
                        name:false,
            },
            genderList:[
                {
                    value:"1",
                    label:"男"
                },
                {
                    value:"2",
                    label:"女"
                }
            ]

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
                    this.$http .post(this.api.textUserBatchDelete, deletebatch).then(res => {
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
                this.empty();
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
            //预览
            handlePre(row){
                this.editVisible=true;
                this.preVisiable=true
                this.form=row;

            },
            //编辑
            handleEdit(row){
                this.editVisible=true;
                this.preVisiable=false
                this.form=row;

            },
            //单个删除
            handleDelete(body){

                var url='http://localhost:8080/rsm4/text/deleteText';
                this.$confirm("此操作将永久删除, 是否继续?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                })
                .then(() => {
                    this.$http.post(url , body).then(res => {
                    if (res.data.status == "200") {
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
                
                var url='http://localhost:8080/rsm4/text/queryText';
                this.$http.post(url, {
                    condition: {
                        address: "",
                        gender: "",
                        id: "",
                        name: this.queryContent
                    },
                    pageCondition: {
                        pageNo: this.pageData.pageNumber,
                        pageSize: this.pageData.pageSize
                    }
                })
                .then(res => {

                    if (res.data.status == "200") {
                        this.pageData.list = res.data.data.resultList;
                       
                        this.pageData.totalPage = res.data.data.totalPages;
                    }
                });


            },
            //保存/修改
            handleSave(){

                var url='http://localhost:8080/rsm4/text/saveAndUpdateText';
                this.$http
                        .post(url, this.form).then(res=> {
                    if (res.data.status == "200") {
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

            },
            cancelSave(){
                this.editVisible=false;
            },
            empty(){
                this.form={
                    address: "",
                    gender: "",
                    id: "",
                    name: "",
            };
                this.condition= {
                    id: "",
                            address: "",
                            gender: "",
                            name: "",
            }
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
    .icon-filter {
        display: inline-block;
        height: 34px;
        width: 24px;
        vertical-align: middle;
        cursor: pointer;
        background-image: url("../../../assets/img/souSuo.png");
        background-size:20px 20px;
        background-repeat: no-repeat;
        background-position: center;
    }
</style>