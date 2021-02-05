<template>
<div class="edit-frame">
    <div class="edit-frame-left">
        <!--上下结构 start-->
        <template v-if="needParam.layoutType == '1'">
            <template v-if="needParam.isShowQuery">
            <!--查询区域 -->
            <div class="container-query" >
                <template v-for="(item,index) in needParam.queryList" >
                    <!--普通查询框 -->
                    <queryItem v-if="item.type == '1'" :label="item.title+'：'" :key="index">
                        <el-input  clearable></el-input>
                    </queryItem>
                    <!--下拉选择器 -->
                    <queryItem v-if="item.type == '2'" :label="item.title+'：'" :key="index">
                        <el-select v-model="currencyValue" placeholder="请选择"  clearable>
                        <el-option
                            v-for="item in currencyList"
                            :key="item.id"
                            :label="item.fieldName"
                            :value="item.id"
                        ></el-option>
                        </el-select>
                    </queryItem>
                    <!--时间选择器 -->
                    <queryItem v-if="item.type == '3'" :label="item.title+'：'" :key="index">
                        <el-date-picker
                            type="daterange"
                            align="right"
                            range-separator="至"
                            start-placeholder="开始日期"
                            end-placeholder="结束日期"
                            value-format="yyyy-MM-dd HH:mm:ss"
                            ></el-date-picker>
                    </queryItem>

                </template>
                <query-item isButton style="width:100%">
                     <!--按钮 -->
                    <template v-for="(item,index) in needParam.queryList">
                       <el-button style="margin-left:10px" v-if="item.type == '4'&&item.value == '4'" :key="index" @click="handleAdd">{{item.title}}</el-button>
                       <el-button style="margin-left:10px" v-if="item.type == '4'&&item.value != '4'" :key="index">{{item.title}}</el-button>
                    </template>
                </query-item>
            </div>
            </template>
            <!--表格区域 -->
            <template v-show="needParam.isShowTable">
                <div class="container-table">
                    <div class="common-table-style">
                        
                        <el-table :data="pageData.list" border style="width: 100%">
                            <el-table-column type="selection" width="55" v-if="needParam.isShowCheckTable" ></el-table-column>
                            <el-table-column type="index" width="50" label="#"></el-table-column>
                            <el-table-column
                            v-for="(item,index) in needParam.tableList"
                            :key="index"
                            :prop="item.value"
                            :label="item.title">
                            aaaaa
                            </el-table-column>
                            <el-table-column v-if="needParam.isShowOperaTable"  width="200px" label="操作">
                                <slot slot-scope="scope">
                                    <el-button v-for="(item,index) in needParam.tableButtonList"  :key="index">{{item.title}}</el-button>
                                </slot>
                            </el-table-column>
                        </el-table>
                    </div>
                    <div class="pagination" v-if="needParam.isShowPage">
                        <pagination :page-list="pageData"></pagination>
                    </div>
                </div>
            </template>
        </template>
        <editTem ref="editTem" :editList="needParam.editList"></editTem>
         <!--上下结构 end-->
    </div>

    <div class="edit-frame-right" :style="{height:frameRight+'px'}">
    <el-scrollbar style="height:100%">
        <div class="line-div">
            <el-button class="line-div-button" @click="back">返回</el-button>
            <el-button class="line-div-button" @click="saveParm">保存</el-button>
            <el-button class="line-div-button" @click="openDigCode">代码生成</el-button>
            <el-button class="line-div-button" @click="openDigTem">生成模板</el-button>
            <div>
                <span>选择模板</span>
                <el-select v-model="currencyValue" placeholder="请选择"  clearable>
                <el-option
                    v-for="item in currencyList"
                    :key="item.id"
                    :label="item.fieldName"
                    :value="item.id"
                ></el-option>
                </el-select>
            </div>
        </div>

        <div class="line-div">
            <el-radio v-model="needParam.layoutType" label="1">上查下表</el-radio>
            <el-radio v-model="needParam.layoutType" label="2">左树右表</el-radio>
        </div>

        <div class="line-div">
            <el-checkbox v-model="needParam.isShowQuery">查询区域</el-checkbox>
            <el-checkbox v-model="needParam.isShowTable">列表区域</el-checkbox>
            <el-checkbox v-model="needParam.isShowPage">分页区域</el-checkbox>
            <el-checkbox v-model="needParam.isShowEdit">编辑区域</el-checkbox>
        </div>


        <el-collapse class="data-area" v-model="activeName" accordion>
        <el-collapse-item v-show="needParam.isShowQuery" title="查询区域" name="1">
            <div class="add-query">
                <div class="add-query-input">
                    <el-select v-model="addType" placeholder="请选择"  clearable>
                    <el-option
                        v-for="item in addTypeList"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                    ></el-option>
                    </el-select>
                </div>
                <div class="add-query-button">
                    <el-button @click="addQueryItem">添加</el-button>
                </div>
            </div>
            <div class="data-area-item" v-for="(item,index) in needParam.queryList" :key="index">
                <i type="text" class="data-area-button el-icon-delete" @click="delItem(index)"></i>
                <template v-if="item.type != '4'">
                    <el-input class="data-area-input" placeholder="请输入查询标题" v-model="item.title"></el-input>
                    <el-select class="data-area-input" v-model="item.value" placeholder="关联字段"  clearable>
                    <el-option
                        v-for="item in tableFileds"
                        :key="item.id"
                        :label="item.fieldName"
                        :value="item.id"
                    ></el-option>
                    </el-select>
                </template>
                <template v-if="item.type == '4'">
                    <el-input class="data-area-input" placeholder="请输入按钮名称" v-model="item.title"></el-input>
                    <el-select class="data-area-input"  v-model="item.value" placeholder="请选择"  clearable>
                    <el-option
                        v-for="item in buttonDataSource"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                    ></el-option>
                    </el-select>
                </template>
            </div>
        </el-collapse-item>
        <el-collapse-item v-show="needParam.isShowTable" title="列表区域" name="2">
            <div class="add-query">
                <div class="add-query-button">
                    <el-button @click="addTableItem">添加</el-button>
                    <el-checkbox style="margin-left:10px" v-model="needParam.isShowCheckTable">多选</el-checkbox>
                    <el-checkbox v-model="needParam.isShowOperaTable">操作</el-checkbox>
                </div>
            </div>
            <div class="data-area-item" v-for="(item,index) in needParam.tableList" :key="index">
                <i type="text" class="data-area-button el-icon-delete" @click="delTableItem(index)"></i>
                <el-input class="data-area-input" placeholder="请输入查询标题" v-model="item.title"></el-input>
                <el-select class="data-area-input" v-model="item.value" placeholder="请选择"  clearable>
                <el-option
                    v-for="item2 in tableFileds"
                    :key="item2.id"
                    :label="item2.fieldName"
                    :value="item2.id"
                ></el-option>
                </el-select>
            </div>
            <div v-show="needParam.isShowOperaTable">
            <el-button @click="addOperaTable">表格操作按钮添加</el-button>
            <div  class="data-area-item" v-for="(item,index) in needParam.tableButtonList" :key="'operaTablekey-'+item.paramIndex">
                <i type="text" class="data-area-button el-icon-delete" @click="delOperaTableItem(index)"></i>
                <el-input class="data-area-input" placeholder="请输入按钮名称" v-model="item.title"></el-input>
                <el-select class="data-area-input" v-model="item.value" placeholder="请选择"  clearable>
                <el-option
                    v-for="item2 in tableButtonCheckList"
                    :key="item2.value"
                    :label="item2.label"
                    :value="item2.value"
                ></el-option>
                </el-select>
            </div>
            </div>
        </el-collapse-item>

        <el-collapse-item v-show="needParam.isShowEdit" title="编辑区域" name="3">
            <div class="add-query">
                <div class="add-query-input">
                    <el-select v-model="addEditType" placeholder="请选择"  clearable>
                    <el-option
                        v-for="item in addTypeList"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                    ></el-option>
                    </el-select>
                </div>
                <div class="add-query-button">
                    <el-button @click="addEditItem">添加</el-button>
                </div>
            </div>
            <div class="data-area-item" v-for="(item,index) in needParam.editList" :key="index">
                <i type="text" class="data-area-button el-icon-delete" @click="delEditItem(index)"></i>
                <template v-if="item.type != '4'">
                    <el-input class="data-area-input" placeholder="请输入查询标题" v-model="item.title"></el-input>
                    <el-select class="data-area-input" v-model="item.value" placeholder="请选择"  clearable>
                    <el-option
                        v-for="item in tableFileds"
                        :key="item.id"
                        :label="item.fieldName"
                        :value="item.id"
                    ></el-option>
                    </el-select>
                </template>
                <template v-if="item.type == '4'">
                    <el-input class="data-area-input" placeholder="请输入按钮名称" v-model="item.title"></el-input>
                    <el-select class="data-area-input"  v-model="item.value" placeholder="请选择"  clearable>
                    <el-option
                        v-for="item in editButtonDataSource"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                    ></el-option>
                    </el-select>
                </template>
            </div>
        </el-collapse-item>
        </el-collapse>
    </el-scrollbar>
    </div>

    <el-dialog title="提示" :visible.sync="dialogVisible" width="30%">
        <div>
            <el-form :inline="true" :model="projModel">
                <el-form-item label="包路径">
                    <el-input v-model="projModel.packageName"></el-input>
                </el-form-item>
                <el-form-item label="项目分类路径">
                    <el-input v-model="projModel.projectName"></el-input>
                </el-form-item>
            </el-form>
        </div>
        <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="greatCode">确 定</el-button>
        </span>
    </el-dialog>
    <el-dialog title="提示" :visible.sync="输入模板名" width="30%">
    <div>
        <el-form :inline="true" :model="projModel">
            <el-form-item label="">
                <el-input v-model="projModel.packageName"></el-input>
            </el-form-item>
            <el-form-item label="项目分类路径">
                <el-input v-model="projModel.projectName"></el-input>
            </el-form-item>
        </el-form>
    </div>
    <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="greatCode">确 定</el-button>
    </span>
    </el-dialog>
</div>

</template>

<script>
import editTem from "./editTem"
export default {
    name:"editLayout",
    components: { editTem },
    data(){
        return{
            currencyList:[],//下拉框通用list
            currencyValue:'',//下拉框通用值
            projModel:{
                tableId:"",
                packageName:"",
                projectName:""
            },
            saveFlag:false,
            needParam:{
                tableId:"",
                layoutType:'1',//1.上查下表，2.左树右表
                isShowQuery:true,//是否展示查询区域
                isShowTable:true,//是否展示表格区域
                isShowPage:true,//是否显示分页区域
                isShowEdit:true,//是否显示编辑区域
                isShowOperaTable:true,//是否显示表格区域的table
                isShowCheckTable:true,
                queryList:[],//查询区域元素集合
                tableList: [],//表格元素
                tableButtonList:[],//表格按钮
                editList:[]
            },
            activeName: '1',
            tableFileds:[],
            addType:'',//添加查询页面元素
            addTypeList:[
                {value:'1',label:'输入框'},
                {value:'2',label:'选择框'},
                {value:'3',label:'日期选择框'},
                {value:'4',label:'按钮'}
            ],
            dropDataSource:[
                {value:'1',label:'状态'}
            ],
            editButtonDataSource:[
                {value:'1',label:'保存',fun:''},
                {value:'5',label:'自定义',fun:''}
            ],
            buttonDataSource:[
                {value:'1',label:'查询',fun:''},
                {value:'2',label:'重置',fun:''},
                {value:'3',label:'批量删除',fun:''},
                {value:'4',label:'新增',fun:'handleAdd'},
                {value:'5',label:'自定义',fun:''}
            ],
            pageData: {
                list: [],
                pageNumber: 1,
                pageSize: 10,
                totalCount: 1,
                totalPage: 1
            },
            editTableId:"",
            addEditType:"",
            dialogVisible:false,
            tableButtonCheckList:[
                {value:'1',label:'编辑'},
                {value:'2',label:'删除'},
                {value:'3',label:'自定义'}
            ],
        }
    },
    computed:{
        frameRight(){
            return document.getElementsByClassName('right-body')[0].clientHeight - 40;
        }
    },
    methods:{
        openDigTem(){

        },
        generateTemplate(){

        },
        updateInput(){
            this.$forceUpdate()
        },
        //新增
        handleAdd() {
            if(this.needParam.isShowEdit){
                this.$refs.editTem.openByNew()
            }
        },
        delTableItem(index){
            this.needParam.tableList.splice(index,1)
        },
        delItem(index){
            this.needParam.queryList.splice(index,1)
        },
        delOperaTableItem(index){
            this.needParam.tableButtonList.splice(index,1)
            this.$forceUpdate()
        },
        delEditItem(index){
            this.needParam.editList.splice(index,1)
        },
        //编辑区域
        addEditItem(){
            if(!this.addEditType){
                return this.$message.warning("请选择添加类型");
            }
            if(!this.needParam.editList){
                this.needParam.editList = []
            }
            var item = {
                type:this.addEditType,
                title:"",
                value:"",
                paramIndex:this.needParam.editList.length
            }
            this.needParam.editList.push(item)
            this.$forceUpdate()
        },
        //操作
        addOperaTable(){
            if(!this.needParam.tableButtonList){
                this.needParam.tableButtonList = []
            }
            var item = {
                title:"",
                value:"",
                paramIndex: this.needParam.tableButtonList.length
            }
            this.needParam.tableButtonList.push(item)
            this.$forceUpdate()
        },
        addTableItem(){
            if(!this.needParam.tableList){
                this.needParam.tableList = []
            }
            var item = {
                title:"",
                value:"",
                paramIndex: this.needParam.tableList.length
            }
            this.needParam.tableList.push(item)
        },
        addQueryItem(){
            if(!this.addType){
                return this.$message.warning("请选择添加类型");
            }
            if(!this.needParam.queryList){
                this.needParam.queryList = []
            }
            var item = {
                type:this.addType,
                title:"",
                value:"",
                paramIndex:this.needParam.queryList.length
            }
            this.needParam.queryList.push(item)
        },
        //打开
        openDigCode(){
            this.projModel = {
                tableId:"",
                packageName:"com.dhlg",
                projectName:"system"
            }

            if(!this.editTableId){
                return this.$message.error("错误");
            }
            this.dialogVisible = true
        },
        greatCode(){
            //生成代码
            this.$confirm("确定是否生成代码", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
            }).then(() => {
                this.projModel.tableId = this.editTableId
                this.$http.post(this.api.sysAutoTableCodeGeneration,this.projModel).then(res => {
                    if (res.data.code == "200") {
                        this.$message.success(res.data.message);
                    }
                })
            }).catch(() => {
                this.$message.info("已取消");
            });
        },
        saveParm(){
            console.log(this.needParam.id)
            this.needParam.tableId = this.editTableId;
            if(!this.needParam.tableId){
                return this.$message.warning("错误")
            }
            this.$confirm("确定是否保存", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
            }).then(() => {
                this.$http.post(this.api.sysAutoParamSaveOrUpdate,this.needParam).then(res => {
                    if (res.data.code == "200") {
                        this.saveFlag = true
                        this.$message.success("保存成功");
                    }
                })
            }).catch(() => {
                this.$message.info("已取消");
            });

        },
        editInit(row){
            //获取下拉框数据
            this.getdropData(row.id)
            this.editTableId = row.id
            //通过id获取字段
            this.$http.get(this.api.sysAutoParamFindByTableID + row.id).then(res => {
                if (res.data.code == "200") {
                    let data = res.data.body
                    if(!data){
                        this.empty()
                    }else{
                        this.needParam = data
                    }
                    
                }
            })
        },
        getdropData(id){
            this.$http.get(this.api.sysAutoFieldFindByTableID + id).then(res => {
                if (res.data.code == "200") {
                    this.tableFileds = res.data.body
                }
            })
        },
        back(){
            this.$emit("backfont")
        },

        empty(){
            
            this.needParam = {
                tableId:"",
                layoutType:'1',//1.上查下表，2.左树右表
                isShowQuery:true,//是否展示查询区域
                isShowTable:true,//是否展示表格区域
                isShowPage:true,//是否显示分页区域
                isShowEdit:true,//是否显示编辑区域
                isShowOperaTable:true,//是否显示表格区域的table
                isShowCheckTable:true,
                queryList:[],//查询区域元素集合
                tableList: [],//表格元素
                tableButtonList:[],//表格按钮
                editList:[]
            }
        },

    }
}
</script>
<style  scoped>
.line-div-button{
    margin-bottom: 5px;
}
.edit-frame{
    display: flex;
    flex-direction: row;
    margin-bottom: 10px;
}
.edit-frame-left{
    display: flex;
    flex: 1;
    flex-direction: column;
    margin-right: 10px;
    position: relative;
}
.edit-frame-right{
    width: 300px;
    background: #fff;
    padding: 10px 10px;
    display: flex;
    flex-direction: column;
}
.container-query2{
    margin-bottom: 10px;
    border-radius: 5px;
    box-shadow: 1px 1px 3px rgba(0,0.2,0,0.2);
    background: #fff;
    padding: 5px 30px;
}
.container-table2{
    height: 100%;
    background-color: #ffffff;
    box-shadow: 1px 1px 3px rgba(0,0,0,0.2);
    padding: 10px 10px;
    border: 1px solid #ddd;
    border-radius: 5px;
}
.line-div{
    margin-bottom: 10px;
}
.data-area{
    margin-top: 20px;
}
.data-area-checkbox{
    width: 120px;
    height: 33px;
    overflow:hidden; /*超出的部分隐藏起来。*/ 
}
.data-area-input{
    display: flex;
    flex: 1;
}
.data-area-item{
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    line-height: 32px;
}
.data-area-item >.data-area-input{
    margin: 5px 5px;
    width: 100px;
}
.add-query{
    display: flex;
    flex-direction: row;
    margin-bottom: 10px;
}
.add-query-input{
    display: flex;
    flex: 1;
}
.add-query-button{
    display: flex;
    justify-content: flex-end;
    margin-right: 20px;
}
.data-area-button{
    width: 32px;
    height: 32px;
    font-size: 18px;
    color: red;
    text-align: center;
    margin-top: 10px;
    cursor:pointer
}
</style>