<template>
    <div class="app-container2">
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
                        <el-table-column prop="createUser" min-width="140" label="创建人" align="center" show-overflow-tooltip>
                                    <template slot="header" slot-scope="scope">
                                        <span class="col-filter">
                                        {{scope.column.label}}
                                        <el-popover placement="bottom-end" width="200" trigger="click" v-model="queryVisible.createUser">
                                            <div style="max-height: 300px; overflow-y: auto;">
                                            <ul style="list-style: none;">
                                                <li
                                                        v-for="item in checkBoxList.createUserList"
                                                        :key="item.value"
                                                        :label="item.label"
                                                >
                                                <el-checkbox :label="item.label" v-model="item.checked"></el-checkbox>
                                                </li>
                                            </ul>
                                            </div>
                                            <hr style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"/>
                                            <div style="display: flex; justify-content: space-evenly;">
                                                <el-button type="text" @click="handleCheckboxListFilterOk('createUser')">确定</el-button>
                                                <el-button type="text" @click="handleCheckboxListFilterCancel('createUser')">重置</el-button>
                                            </div>
                                            <i slot="reference" class="icon-filter"  @click.stop></i>
                                        </el-popover>
                                        </span>
                                    </template>

                                    <template slot-scope="scope">
                                        <div >
                                            <div v-for="item in checkBoxList.createUserList" :key="item.value">
                                                <el-tag
                                                        type="success"
                                                        v-if="scope.row.createUser==item.value"
                                                >{{item.label}}</el-tag>
                                            </div>
                                        </div>
                                    </template>



                        </el-table-column>
                        <el-table-column prop="parentId" min-width="140" label="主表id" align="center" show-overflow-tooltip>
                                    <template slot="header" slot-scope="scope">
                                        <span class="col-filter">
                                        {{scope.column.label}}
                                        <el-popover placement="bottom-end" width="200" trigger="click" v-model="queryVisible.parentId">
                                            <div style="max-height: 300px; overflow-y: auto;">
                                            <ul style="list-style: none;">
                                                <li
                                                        v-for="item in checkBoxList.parentIdList"
                                                        :key="item.value"
                                                        :label="item.label"
                                                >
                                                <el-checkbox :label="item.label" v-model="item.checked"></el-checkbox>
                                                </li>
                                            </ul>
                                            </div>
                                            <hr style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"/>
                                            <div style="display: flex; justify-content: space-evenly;">
                                                <el-button type="text" @click="handleCheckboxListFilterOk('parentId')">确定</el-button>
                                                <el-button type="text" @click="handleCheckboxListFilterCancel('parentId')">重置</el-button>
                                            </div>
                                            <i slot="reference" class="icon-filter"  @click.stop></i>
                                        </el-popover>
                                        </span>
                                    </template>

                                    <template slot-scope="scope">
                                        <div >
                                            <div v-for="item in checkBoxList.parentIdList" :key="item.value">
                                                <el-tag
                                                        type="success"
                                                        v-if="scope.row.parentId==item.value"
                                                >{{item.label}}</el-tag>
                                            </div>
                                        </div>
                                    </template>



                        </el-table-column>
                        <el-table-column prop="tableName" min-width="140" label="表名" align="center" show-overflow-tooltip>
                                    <template slot="header" slot-scope="scope">
                                        <span class="col-filter">
                                        {{scope.column.label}}
                                        <el-popover placement="bottom-end" width="200" trigger="click" v-model="queryVisible.tableName">
                                            <div style="max-height: 300px; overflow-y: auto;">
                                            <ul style="list-style: none;">
                                                <li
                                                        v-for="item in checkBoxList.tableNameList"
                                                        :key="item.value"
                                                        :label="item.label"
                                                >
                                                <el-checkbox :label="item.label" v-model="item.checked"></el-checkbox>
                                                </li>
                                            </ul>
                                            </div>
                                            <hr style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"/>
                                            <div style="display: flex; justify-content: space-evenly;">
                                                <el-button type="text" @click="handleCheckboxListFilterOk('tableName')">确定</el-button>
                                                <el-button type="text" @click="handleCheckboxListFilterCancel('tableName')">重置</el-button>
                                            </div>
                                            <i slot="reference" class="icon-filter"  @click.stop></i>
                                        </el-popover>
                                        </span>
                                    </template>

                                    <template slot-scope="scope">
                                        <div >
                                            <div v-for="item in checkBoxList.tableNameList" :key="item.value">
                                                <el-tag
                                                        type="success"
                                                        v-if="scope.row.tableName==item.value"
                                                >{{item.label}}</el-tag>
                                            </div>
                                        </div>
                                    </template>



                        </el-table-column>
                        <el-table-column prop="tableRemark" min-width="140" label="备注" align="center" show-overflow-tooltip>
                                <template slot="header" slot-scope="scope">
                                    <span class="col-filter">
                                    {{scope.column.label}}
                                    <el-popover placement="bottom-end" width="200" trigger="click" v-model="queryVisible.tableRemark">
                                        <div>
                                        <el-input
                                                type="text"
                                                v-model="condition.tableRemark"
                                                placeholder="请输入"
                                        ></el-input>
                                        </div>
                                        <hr
                                                style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"
                                        />
                                        <div style="display: flex; justify-content: space-evenly;">
                                        <el-button type="text" @click="handleSimpleFilterOk('tableRemark')">确定</el-button>
                                        <el-button type="text" @click="handleSimpleFilterCancel('tableRemark')">重置</el-button>
                                        </div>
                                        <i slot="reference" class="icon-filter"  @click.stop></i>
                                    </el-popover>
                                    </span>
                                </template>


                        </el-table-column>
                        <el-table-column prop="tableType" min-width="140" label="表类型" align="center" show-overflow-tooltip>
                                    <template slot="header" slot-scope="scope">
                                        <span class="col-filter">
                                        {{scope.column.label}}
                                        <el-popover placement="bottom-end" width="200" trigger="click" v-model="queryVisible.tableType">
                                            <div style="max-height: 300px; overflow-y: auto;">
                                            <ul style="list-style: none;">
                                                <li
                                                        v-for="item in checkBoxList.tableTypeList"
                                                        :key="item.value"
                                                        :label="item.label"
                                                >
                                                <el-checkbox :label="item.label" v-model="item.checked"></el-checkbox>
                                                </li>
                                            </ul>
                                            </div>
                                            <hr style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"/>
                                            <div style="display: flex; justify-content: space-evenly;">
                                                <el-button type="text" @click="handleCheckboxListFilterOk('tableType')">确定</el-button>
                                                <el-button type="text" @click="handleCheckboxListFilterCancel('tableType')">重置</el-button>
                                            </div>
                                            <i slot="reference" class="icon-filter"  @click.stop></i>
                                        </el-popover>
                                        </span>
                                    </template>

                                    <template slot-scope="scope">
                                        <div >
                                            <div v-for="item in checkBoxList.tableTypeList" :key="item.value">
                                                <el-tag
                                                        type="success"
                                                        v-if="scope.row.tableType==item.value"
                                                >{{item.label}}</el-tag>
                                            </div>
                                        </div>
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
                        <el-table-column prop="updateUser" min-width="140" label="更新人" align="center" show-overflow-tooltip>
                                    <template slot="header" slot-scope="scope">
                                        <span class="col-filter">
                                        {{scope.column.label}}
                                        <el-popover placement="bottom-end" width="200" trigger="click" v-model="queryVisible.updateUser">
                                            <div style="max-height: 300px; overflow-y: auto;">
                                            <ul style="list-style: none;">
                                                <li
                                                        v-for="item in checkBoxList.updateUserList"
                                                        :key="item.value"
                                                        :label="item.label"
                                                >
                                                <el-checkbox :label="item.label" v-model="item.checked"></el-checkbox>
                                                </li>
                                            </ul>
                                            </div>
                                            <hr style="background-color: lightgray; height: 1px; border: none; margin-top: 1em;"/>
                                            <div style="display: flex; justify-content: space-evenly;">
                                                <el-button type="text" @click="handleCheckboxListFilterOk('updateUser')">确定</el-button>
                                                <el-button type="text" @click="handleCheckboxListFilterCancel('updateUser')">重置</el-button>
                                            </div>
                                            <i slot="reference" class="icon-filter"  @click.stop></i>
                                        </el-popover>
                                        </span>
                                    </template>

                                    <template slot-scope="scope">
                                        <div >
                                            <div v-for="item in checkBoxList.updateUserList" :key="item.value">
                                                <el-tag
                                                        type="success"
                                                        v-if="scope.row.updateUser==item.value"
                                                >{{item.label}}</el-tag>
                                            </div>
                                        </div>
                                    </template>



                        </el-table-column>

                <el-table-column label="操作" fixed="right" min-width="160" align="center" show-overflow-tooltip>
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
                                                <el-form-item label="创建时间" prop="createTime">
                                                    <el-date-picker
                                                            :disabled="preVisiable"
                                                            v-model="form.createTime"
                                                            type="datetime"
                                                            placeholder="选择日期时间"
                                                            default-time="12:00:00"
                                                            style="width:100%">
                                                    </el-date-picker>
                                                </el-form-item>
                                            </el-col>




                                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                                <el-form-item label="创建人" prop="createUser" >
                                                    <el-select v-model="form.createUser"  placeholder="请选择"  style="width:100%;" :disabled="preVisiable">
                                                        <el-option
                                                                v-for="item in checkBoxList.createUserList"
                                                                :key="item.value"
                                                                :label="item.label"
                                                                :value="item.value"
                                                        ></el-option>
                                                    </el-select>
                                                </el-form-item>
                                            </el-col>





                                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                                <el-form-item label="主表id" prop="parentId" >
                                                    <el-select v-model="form.parentId"  placeholder="请选择"  style="width:100%;" :disabled="preVisiable">
                                                        <el-option
                                                                v-for="item in checkBoxList.parentIdList"
                                                                :key="item.value"
                                                                :label="item.label"
                                                                :value="item.value"
                                                        ></el-option>
                                                    </el-select>
                                                </el-form-item>
                                            </el-col>





                                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                                <el-form-item label="表名" prop="tableName" >
                                                    <el-select v-model="form.tableName"  placeholder="请选择"  style="width:100%;" :disabled="preVisiable">
                                                        <el-option
                                                                v-for="item in checkBoxList.tableNameList"
                                                                :key="item.value"
                                                                :label="item.label"
                                                                :value="item.value"
                                                        ></el-option>
                                                    </el-select>
                                                </el-form-item>
                                            </el-col>




                                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                                <el-form-item label="备注" prop="tableRemark" style="width:35rem;height:32px">
                                                    <el-input v-model="form.tableRemark" :disabled="preVisiable"></el-input>
                                                </el-form-item>
                                            </el-col>





                                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                                <el-form-item label="表类型" prop="tableType" >
                                                    <el-select v-model="form.tableType"  placeholder="请选择"  style="width:100%;" :disabled="preVisiable">
                                                        <el-option
                                                                v-for="item in checkBoxList.tableTypeList"
                                                                :key="item.value"
                                                                :label="item.label"
                                                                :value="item.value"
                                                        ></el-option>
                                                    </el-select>
                                                </el-form-item>
                                            </el-col>




                                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                                <el-form-item label="更新时间" prop="updateTime">
                                                    <el-date-picker
                                                            :disabled="preVisiable"
                                                            v-model="form.updateTime"
                                                            type="datetime"
                                                            placeholder="选择日期时间"
                                                            default-time="12:00:00"
                                                            style="width:100%">
                                                    </el-date-picker>
                                                </el-form-item>
                                            </el-col>




                                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                                <el-form-item label="更新人" prop="updateUser" >
                                                    <el-select v-model="form.updateUser"  placeholder="请选择"  style="width:100%;" :disabled="preVisiable">
                                                        <el-option
                                                                v-for="item in checkBoxList.updateUserList"
                                                                :key="item.value"
                                                                :label="item.label"
                                                                :value="item.value"
                                                        ></el-option>
                                                    </el-select>
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
                preVisiable:false,
                condition: {
                    id: "",
                        createTime: [],
                        createUser: [],
                        parentId: [],
                        tableName: [],
                        tableRemark: "",
                        tableType: [],
                        updateTime: [],
                        updateUser: [],
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
                    createTime: "",
                    createUser: "",
                    id: "",
                    parentId: "",
                    tableName: "",
                    tableRemark: "",
                    tableType: "",
                    updateTime: "",
                    updateUser: "",
            },
            editVisible:false,
            rules: {
                createTime: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],
                createUser: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],
                parentId: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],
                tableName: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],
                tableRemark: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],
                tableType: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],
                updateTime: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],
                updateUser: [
                    {
                        required: true,
                        message: "  ",
                        trigger: "blur"
                    }
                ],

            },
            queryVisible:{
                        createTime:false,
                        createUser:false,
                        parentId:false,
                        tableName:false,
                        tableRemark:false,
                        tableType:false,
                        updateTime:false,
                        updateUser:false,
            },
            checkBoxList:{
                                createUserList:[],
                                parentIdList:[],
                                tableNameList:[],
                                tableTypeList:[],
                                updateUserList:[],
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
            //新增
            handleAdd(){
                this.editVisible=true;
                this.empty();
                this.$nextTick(()=>{
                    this.$refs.form.resetFields();
                })
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
                    this.$http .post(this.api.sysTableBatchDelete, deletebatch).then(res => {
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
                this.$nextTick(()=>{
                    this.$refs.form.resetFields();
                })
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
                    .delete(this.api.sysTableDeletebyid + id).then(res => {
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

        if(!!this.form.createTime){
            var d=this.form.createTime;
            this.form.createTime=this.dateUtils(d);
        }
        if(!!this.form.updateTime){
            var d=this.form.updateTime;
            this.form.updateTime=this.dateUtils(d);
        }
                        this.$http
                                .post(this.api.sysTableSaveOrUpdate, this.form).then(res=> {
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
                    createTime: "",
                    createUser: "",
                    id: "",
                    parentId: "",
                    tableName: "",
                    tableRemark: "",
                    tableType: "",
                    updateTime: "",
                    updateUser: "",
            };
                this.condition= {
                    id: "",
                            createTime: [],
                            createUser: [],
                            parentId: [],
                            tableName: [],
                            tableRemark: "",
                            tableType: [],
                            updateTime: [],
                            updateUser: [],
            }
            },

            //获取下拉数据
            getxiala(){
                //下拉数据模板
                // this.$http
                //        .get(this.api.dicTypeGetType + "orderStatus").then(res => {
                //         if (res.data.code == 200) {
                //             this.orderStatusList = res.data.body.map(item => ({
                //                 label: item.label,
                //                 value: parseInt(item.value),
                //                 checked: false
                //             }));;
                // }
                // });

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

                    sysTableQueryByCondition: `/moban/api/system/sysTable/querybycondition`,//查询
                    sysTableDeletebyid: `/moban/api/system/sysTable/deleteById/`,//单个删除
                    sysTableBatchDelete: `/moban/api/system/sysTable/batchdelete`,//批量删除
                    sysTableSaveOrUpdate: `/moban/api/system/sysTable/saveOrUpdate`,//更新或新增
                    sysTableListFieldQuery: `/moban/api/system/sysTable/listFieldQuery`,//列表查询
            },

            //获取数据
            getTableData(){
                this.$http
                        .post(this.api.sysTableListFieldQuery, {
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
        background-image: url("../../../assets/img/souSuo.png");
        background-size:20px 20px;
        background-repeat: no-repeat;
        background-position: center;
    }
</style>