<template>
    <div class="app-container">
        <div class="container">
            <!-- 区域一 查询区域 -->
            <div class="container_btn" >
            <span>
            <el-button type="warning" size="small"   @click="handleAdd">新增</el-button>
            <el-button type="danger" size="small" :disabled="dicDisabled"  @click="handleDeleteBatch">删除</el-button>
            <el-button type="primary" plain size="small"   @click="search">刷新</el-button>
			<el-button type="info" plain size="small" @click="reset">重置搜索</el-button>
            </span>
            </div>
            <!-- 区域二---表格+分页 -->
            <el-table :data="pageData.list" style="width: 100%" height="100%" :row-style="{height:'50px'}" border class="table" ref="multipleTable" align="center" @selection-change="handleSelectionChange" >
                <el-table-column type="selection"  fixed width="45"  align="center" show-overflow-tooltip></el-table-column>
                <el-table-column type="index" width="55" label="序号" align="center" v-if="xuhao" show-overflow-tooltip></el-table-column>
                        <el-table-column prop="title" min-width="140" label="标题" align="center" show-overflow-tooltip>
                                <template slot="header" slot-scope="scope">
                                    <span class="col-filter">
                                    {{scope.column.label}}
                                    <el-popover placement="bottom-end" width="200" trigger="click" v-model="queryVisible.title">
                                        <div>
                                        <el-input
                                                type="text"
                                                v-model="condition.title"
                                                placeholder="请输入"
                                        ></el-input>
                                        </div>
                                        <hr
                                                style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"
                                        />
                                        <div style="display: flex; justify-content: space-evenly;">
                                        <el-button type="text" @click="handleSimpleFilterOk('title')">确定</el-button>
                                        <el-button type="text" @click="handleSimpleFilterCancel('title')">重置</el-button>
                                        </div>
                                        <i slot="reference" class="icon-filter"  @click.stop></i>
                                    </el-popover>
                                    </span>
                                </template>

                        </el-table-column>
                        <!-- <el-table-column prop="content" min-width="140" label="内容" align="center" show-overflow-tooltip>
                                <template slot="header" slot-scope="scope">
                                    <span class="col-filter">
                                    {{scope.column.label}}
                                    <el-popover placement="bottom-end" width="200" trigger="click" v-model="queryVisible.content">
                                        <div>
                                        <el-input
                                                type="text"
                                                v-model="condition.content"
                                                placeholder="请输入"
                                        ></el-input>
                                        </div>
                                        <hr
                                                style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"
                                        />
                                        <div style="display: flex; justify-content: space-evenly;">
                                        <el-button type="text" @click="handleSimpleFilterOk('content')">确定</el-button>
                                        <el-button type="text" @click="handleSimpleFilterCancel('content')">重置</el-button>
                                        </div>
                                        <i slot="reference" class="icon-filter"  @click.stop></i>
                                    </el-popover>
                                    </span>
                                </template>

                        </el-table-column> -->
                        <el-table-column prop="category" min-width="140" label="分类" align="center" show-overflow-tooltip>
                                <template slot="header" slot-scope="scope">
                                    <span class="col-filter">
                                    {{scope.column.label}}
                                    <el-popover placement="bottom-end" width="200" trigger="click" v-model="queryVisible.category">
                                        <div>
                                        <el-input
                                                type="text"
                                                v-model="condition.category"
                                                placeholder="请输入"
                                        ></el-input>
                                        </div>
                                        <hr
                                                style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"
                                        />
                                        <div style="display: flex; justify-content: space-evenly;">
                                        <el-button type="text" @click="handleSimpleFilterOk('category')">确定</el-button>
                                        <el-button type="text" @click="handleSimpleFilterCancel('category')">重置</el-button>
                                        </div>
                                        <i slot="reference" class="icon-filter"  @click.stop></i>
                                    </el-popover>
                                    </span>
                                </template>

                                <template slot-scope="scope">
                                    <div >
                                        <div v-for="item in checkBoxList.categoryList" :key="item.value">
                                            <el-tag
                                                    type="success"
                                                    v-if="scope.row.category==item.value"
                                            >{{item.label}}</el-tag>
                                        </div>
                                    </div>
                                </template>

                        </el-table-column>
                        <el-table-column prop="description" min-width="140" label="描述" align="center" show-overflow-tooltip>
                                <template slot="header" slot-scope="scope">
                                    <span class="col-filter">
                                    {{scope.column.label}}
                                    <el-popover placement="bottom-end" width="200" trigger="click" v-model="queryVisible.description">
                                        <div>
                                        <el-input
                                                type="text"
                                                v-model="condition.description"
                                                placeholder="请输入"
                                        ></el-input>
                                        </div>
                                        <hr
                                                style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"
                                        />
                                        <div style="display: flex; justify-content: space-evenly;">
                                        <el-button type="text" @click="handleSimpleFilterOk('description')">确定</el-button>
                                        <el-button type="text" @click="handleSimpleFilterCancel('description')">重置</el-button>
                                        </div>
                                        <i slot="reference" class="icon-filter"  @click.stop></i>
                                    </el-popover>
                                    </span>
                                </template>

                        </el-table-column>
                        <el-table-column prop="reading" min-width="140" label="阅读数量" align="center" show-overflow-tooltip>
                                <template slot="header" slot-scope="scope">
                                    <span class="col-filter">
                                    {{scope.column.label}}
                                    <el-popover placement="bottom-end" width="200" trigger="click" v-model="queryVisible.reading">
                                        <div>
                                        <el-input
                                                type="text"
                                                v-model="condition.reading"
                                                placeholder="请输入"
                                        ></el-input>
                                        </div>
                                        <hr
                                                style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"
                                        />
                                        <div style="display: flex; justify-content: space-evenly;">
                                        <el-button type="text" @click="handleSimpleFilterOk('reading')">确定</el-button>
                                        <el-button type="text" @click="handleSimpleFilterCancel('reading')">重置</el-button>
                                        </div>
                                        <i slot="reference" class="icon-filter"  @click.stop></i>
                                    </el-popover>
                                    </span>
                                </template>

                        </el-table-column>
                        <el-table-column prop="likes" min-width="140" label="喜欢数量" align="center" show-overflow-tooltip>
                                <template slot="header" slot-scope="scope">
                                    <span class="col-filter">
                                    {{scope.column.label}}
                                    <el-popover placement="bottom-end" width="200" trigger="click" v-model="queryVisible.likes">
                                        <div>
                                        <el-input
                                                type="text"
                                                v-model="condition.likes"
                                                placeholder="请输入"
                                        ></el-input>
                                        </div>
                                        <hr
                                                style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"
                                        />
                                        <div style="display: flex; justify-content: space-evenly;">
                                        <el-button type="text" @click="handleSimpleFilterOk('likes')">确定</el-button>
                                        <el-button type="text" @click="handleSimpleFilterCancel('likes')">重置</el-button>
                                        </div>
                                        <i slot="reference" class="icon-filter"  @click.stop></i>
                                    </el-popover>
                                    </span>
                                </template>

                        </el-table-column>
                        <el-table-column prop="createTime" min-width="140" label="创建时间" align="center" show-overflow-tooltip>
                                <template slot="header" slot-scope="scope">
                                    <span class="col-filter">
                                    {{scope.column.label}}
                                    <el-popover placement="bottom-end" width="400" trigger="click"  v-model="queryVisible.createTime">
                                        <el-date-picker
                                                v-model="condition.createTime"
                                                type="daterange"
                                                align="right"
                                                unlink-panels
                                                range-separator="至"
                                                start-placeholder="开始日期"
                                                end-placeholder="结束日期"
                                                :picker-options="pickerOptions"
                                                value-format="yyyy-MM-dd HH:mm:ss"
                                        ></el-date-picker>
                                        <hr
                                                style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"
                                        />
                                        <div style="display: flex; justify-content: space-evenly;">
                                        <el-button
                                                type="text"
                                                @click="handleDateRangeListFilterOk('createTime')"
                                        >确定</el-button>
                                        <el-button
                                                type="text"
                                                @click="handleDateRangeListFilterCancel('createTime')"
                                        >重置</el-button>
                                        </div>
                                        <i slot="reference" class="icon-filter" @click.stop></i>
                                    </el-popover>
                                    </span>
                                </template>

                        </el-table-column>
                        <el-table-column prop="updateTime" min-width="140" label="更新时间" align="center" show-overflow-tooltip>
                                <template slot="header" slot-scope="scope">
                                    <span class="col-filter">
                                    {{scope.column.label}}
                                    <el-popover placement="bottom-end" width="400" trigger="click"  v-model="queryVisible.updateTime">
                                        <el-date-picker
                                                v-model="condition.updateTime"
                                                type="daterange"
                                                align="right"
                                                unlink-panels
                                                range-separator="至"
                                                start-placeholder="开始日期"
                                                end-placeholder="结束日期"
                                                :picker-options="pickerOptions"
                                                value-format="yyyy-MM-dd HH:mm:ss"
                                        ></el-date-picker>
                                        <hr
                                                style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"
                                        />
                                        <div style="display: flex; justify-content: space-evenly;">
                                        <el-button
                                                type="text"
                                                @click="handleDateRangeListFilterOk('updateTime')"
                                        >确定</el-button>
                                        <el-button
                                                type="text"
                                                @click="handleDateRangeListFilterCancel('updateTime')"
                                        >重置</el-button>
                                        </div>
                                        <i slot="reference" class="icon-filter" @click.stop></i>
                                    </el-popover>
                                    </span>
                                </template>

                        </el-table-column>
                        <el-table-column prop="coverImg" min-width="140" label="封面图" align="center" show-overflow-tooltip>
                                <template slot="header" slot-scope="scope">
                                    <span class="col-filter">
                                    {{scope.column.label}}
                                    <el-popover placement="bottom-end" width="200" trigger="click" v-model="queryVisible.coverImg">
                                        <div>
                                        <el-input
                                                type="text"
                                                v-model="condition.coverImg"
                                                placeholder="请输入"
                                        ></el-input>
                                        </div>
                                        <hr
                                                style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"
                                        />
                                        <div style="display: flex; justify-content: space-evenly;">
                                        <el-button type="text" @click="handleSimpleFilterOk('coverImg')">确定</el-button>
                                        <el-button type="text" @click="handleSimpleFilterCancel('coverImg')">重置</el-button>
                                        </div>
                                        <i slot="reference" class="icon-filter"  @click.stop></i>
                                    </el-popover>
                                    </span>
                                </template>

                        </el-table-column>

                <el-table-column label="操作" fixed="right" min-width="220" align="center" show-overflow-tooltip>
                    <template slot-scope="scope">
                        <el-button type="text" icon="el-icon-view" class="yellow"  @click="handlePre(scope.row)" >预览</el-button>
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
                                                <el-form-item label="标题" prop="title" >
                                                    <el-input v-model="form.title" :disabled="preVisiable"></el-input>
                                                </el-form-item>
                                            </el-col>

                                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                                <el-form-item label="分类" prop="category" >
                                                    <el-select v-model="form.category"  placeholder="请选择"  style="width:100%;" :disabled="preVisiable">
                                                        <el-option
                                                                v-for="item in checkBoxList.categoryList"
                                                                :key="item.value"
                                                                :label="item.label"
                                                                :value="item.value"
                                                        ></el-option>
                                                    </el-select>
                                                </el-form-item>
                                            </el-col>

                                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                                <el-form-item label="封面图" prop="coverImg" >
                                                        <template v-if="form.coverImg">
                                                            <img :src="form.coverImg"  @click="openPhotoDig" style="height:100px;width:200px"/>
                                                        </template>
                                                        <template v-else>
                                                            <el-button type="primary" size="small" @click="openPhotoDig">选择图片</el-button>
                                                        </template>
                                                </el-form-item>
                                            </el-col>

                                            <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12" >
                                                <el-form-item label="描述" prop="description">
                                                    <el-input
                                                    type="textarea"
                                                    :autosize="{ minRows: 2, maxRows: 4}"
                                                    placeholder="请输入内容"
                                                    v-model="form.description">
                                                    </el-input>
                                                </el-form-item>
                                            </el-col>

                                            <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24" >
                                                <el-form-item label="内容" prop="content">
                                                     <editor ref="myTextEditor" :value="form.content"  @onEditorChange="onEditorChange"></editor>
                                                </el-form-item>
                                            </el-col>

                            </el-form>

                        </div>
                    </el-col>
                </el-row>
            </el-dialog>
        </div>
<photoShow ref="photoView" @handleSure="handleSure"></photoShow>
    </div>
</template>
<script>
    export default {
        data(){
            return{
                preVisiable:false,
                condition: {
                    id: "",
                        title: "",
                        content: "",
                        category: "",
                        description: "",
                        reading: "",
                        likes: "",
                        createTime: [],
                        updateTime: [],
                        coverImg: "",
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
                    id: "",
                    title: "",
                    content: "",
                    category: "",
                    description: "",
                    reading: "",
                    likes: "",
                    coverImg: "",
            },
            editVisible:false,
            rules: {
                title: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],
                content: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],
                category: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],
                description: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],
                reading: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],
                likes: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],
                coverImg: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],

            },
            queryVisible:{
                        title:false,
                        content:false,
                        category:false,
                        description:false,
                        reading:false,
                        likes:false,
                        createTime:false,
                        updateTime:false,
                        coverImg:false,
            },
            checkBoxList:{
                                categoryList:[],
            },
            pickerOptions: {
                shortcuts: [
                    {
                        text: "最近一周",
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
                            picker.$emit("pick", [start, end]);
                        }
                    },
                    {
                        text: "最近一个月",
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
                            picker.$emit("pick", [start, end]);
                        }
                    },
                    {
                        text: "最近三个月",
                        onClick(picker) {
                            const end = new Date();
                            const start = new Date();
                            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
                            picker.$emit("pick", [start, end]);
                        }
                    }
                ]
            }
        }
        },

        created(){

            this.getdata();
        },
        methods:{
            onEditorChange2(content){
                this.form.description=content;
            },
            onEditorChange(content){
                this.form.content=content;
            },
            handleSure(photoData){
                if(!photoData){
                    return this.$message({
                    message: '请选择你要选择的图片',
                    type: 'warning'
                    });
                }
                this.form.coverImg=photoData.url;
            },
            openPhotoDig(){
                this.$refs.photoView.dialogVisible=true;
            },
            //新增
            handleAdd(){
                this.editVisible=true;
                this.empty();
                // this.$nextTick(()=>{
                //     this.$refs.form.resetFields();
                // })
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
                    this.$http .post(this.api.blogBlogBatchDelete, deletebatch).then(res => {
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
                // this.$nextTick(()=>{
                //     this.$refs.form.resetFields();
                // })
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
                    .delete(this.api.blogBlogDeletebyid + id).then(res => {
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
                this.getTableData();
            },
            //保存/修改
            handleSave(){
                this.$refs
                        .form.validate(valid => {if (valid) {


                        this.$http
                                .post(this.api.blogBlogSaveOrUpdate, this.form).then(res=> {
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
                    id: "",
                    title: "",
                    content: "",
                    category: "",
                    description: "",
                    reading: "",
                    likes: "",
                    createTime: "",
                    updateTime: "",
                    coverImg: "",
            };
                this.condition= {
                    id: "",
                    title: "",
                    content: "",
                    category: "",
                    description: "",
                    reading: "",
                    likes: "",
                    createTime: [],
                    updateTime: [],
                    coverImg: "",
            }
            this.preVisiable=false
            },

            //获取下拉数据
            getxiala(){
                //下拉数据模板
                this.$http
                       .get(this.api.dicTypeGetType + "blogType").then(res => {
                        if (res.data.code == 200) {
                            this.checkBoxList.categoryList = res.data.body.map(item => ({
                                label: item.label,
                                value: item.value,
                                checked: false
                            }));;
                        }
                });


                // this.$http
                //     .post(this.api.appUserQueryByCondition, {
                //         condition:  {},
                //         number: 1,
                //         size: 99999
                //     })
                //     .then(res => {
                //     if (res.data.code == "200") {
                //     this.checkBoxList.parendIdList=res.data.body.records.map(item => ({
                //         label: item.storeName,
                //         value: item.id,
                //         checked: false
                //     }));
                //     this.checkBoxList.userIdList=this.checkBoxList.parendIdList;

                //     }
                // });

            },

            //获取数据
            getTableData(){
                this.$http
                        .post(this.api.blogBlogListFieldQuery, {
                            ...this.condition,
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

            //时间转换器---start
            dateUtils(d){
                var year=d.getFullYear();
                var month=this.prefixInteger(d.getMonth());
                var date=this.prefixInteger(d.getDate());
                var hours=this.prefixInteger(d.getHours());
                var minutes=this.prefixInteger(d.getMinutes());
                var seconds=this.prefixInteger(d.getSeconds());
                return year + '-' + month + '-'  + date + ' '  + hours + ':'  + minutes + ':'  + seconds;
            },

            //自动补零
            prefixInteger(num) {
                if(num>9){
                    return num;
                }
                return (Array(2).join(0) + num).slice(-2);
            },
            //时间转换器---end

            //普通查询及取消
            handleSimpleFilterOk(filterName){

                if(!this.condition[filterName]){
                    return this.$message.error("未输入查询内容")
                }
                this.queryVisible[filterName] = false;
                this.getTableData();
            },
            handleSimpleFilterCancel(filterName){
                this.condition[filterName] = "";
                this.queryVisible[filterName] = false;
                this.getTableData();
            },
            //数字框查询及取消
            handleNumFilterOk(filterName){

                if(this.condition[filterName].length==0){ return this.$message.error("未填入查询数字")}
                if(!this.condition[filterName][0]||!this.condition[filterName][1]){ return this.$message.error("未填入查询数字")}

                this.queryVisible[filterName] = false;
                this.getTableData();
            },
            handleNumFilterCancel(filterName){
                this.condition[filterName] = [];
                this.queryVisible[filterName] = false;
                this.getTableData();
            },

            //多选框查询及取消
            handleCheckboxListFilterOk(filterName) {

                var aa=[];
                this.checkBoxList[filterName+"List"].forEach(v => {
                    if(v.checked){
                    aa.push(v.value)
                }
            });
                if(aa.length==0){ return this.$message.error("未勾选查询选框")}
                this.condition[filterName]=aa;
                this.queryVisible[filterName] = false;
                this.getTableData();
            },
            handleCheckboxListFilterCancel(filterName) {
                this.checkBoxList[filterName+"List"].forEach(item => (item.checked = false));
                this.condition[filterName]=[];
                this.queryVisible[filterName] = false;
                this.getTableData();
            },
            //日期查询
            handleDateRangeListFilterOk(filterName) {

                const [minDate, maxDate] = this.condition[filterName];
                this.condition[filterName] = [
                    minDate.replace(/(\s.+)$/, " 00:00:00"),
                    maxDate.replace(/(\s.+)$/, " 23:59:59")
                ];
                this.queryVisible[filterName] = false;
                console.log(this.queryVisible.unfreezeTime);
                this.getTableData();
            },
            handleDateRangeListFilterCancel(filterName) {
                this.condition[filterName] = [];
                this.queryVisible[filterName] = false;
                this.getTableData();
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
        background-size:20px 20px;
        background-repeat: no-repeat;
        background-position: center;
    }
</style>