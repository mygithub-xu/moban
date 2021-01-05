<template>
<div class="edit-frame">
    <div class="edit-frame-left">
        <!--上下结构 start-->
        <template v-if="needParam.layoutType == '1'">
            <template v-if="needParam.isShowQuery">
            <!--查询区域 -->
            <div class="container-query" >
                <template v-for="(item,index) in queryList" >
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
                <query-item isButton>
                     <!--按钮 -->
                    <template v-for="(item,index) in queryList">
                       <el-button style="margin-left:10px" v-if="item.type == '4'" :key="index">{{item.title}}</el-button> 
                    </template>
                </query-item>
            </div>
            </template>
            <!--表格区域 -->
            <template v-if="needParam.isShowTableData">
                <div class="container-table">
                <div class="common-table-style">
                    <el-table :data="tableData" style="width: 100%">
                        <el-table-column
                        v-for="(item,index) in tableList"
                        :key="index"
                        :prop="item.value"
                        :label="item.label"
                        width="180">
                        </el-table-column>
                    </el-table>
                </div>
                </div>
            </template>
        </template>
         <!--上下结构 end-->
    </div>

    <div class="edit-frame-right" :style="{height:frameRight+'px'}">
    <el-scrollbar style="height:100%">
        <div class="line-div">
            <el-button @click="back">返回</el-button>
            <el-button>返回xxxx</el-button>
        </div>

        <div class="line-div">
            <el-radio v-model="needParam.layoutType" label="1">上查下表</el-radio>
            <el-radio v-model="needParam.layoutType" label="2">左树右表</el-radio>
        </div>

        <div class="line-div">
            <el-checkbox v-model="needParam.isShowQuery">查询区域</el-checkbox>
            <el-checkbox v-model="needParam.isShowTableData">列表区域</el-checkbox>
            <el-checkbox v-model="needParam.isShowPage">分页区域</el-checkbox>
            <el-checkbox v-model="needParam.isShowButton">按钮区域</el-checkbox>
        </div>


        <el-collapse class="data-area" v-model="activeName" accordion>
        <el-collapse-item title="查询区域" name="1">
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
            <div class="data-area-item" v-for="(item,index) in queryList" :key="index">
                <i type="text" class="data-area-button el-icon-delete" @click="delItem(index)"></i>
                <template v-if="item.type == '1' || item.type == '2'">
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
                <template v-if="item.type == '3'">
                    <el-input class="data-area-input" placeholder="请选择数据来源" v-model="item.title"></el-input>
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
                        v-for="item in buttonDataSource"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                    ></el-option>
                    </el-select>
                </template>
            </div>

        </el-collapse-item>
        <el-collapse-item title="列表区域" name="2">
            <div class="data-area-item" v-for="item in tableFileds" :key="item.id">
                <el-checkbox class="data-area-checkbox" v-model="item.fieldIsquery">{{item.fieldName}}</el-checkbox>
                <el-input class="data-area-input" placeholder="请输入查询标题" v-model="item.fieldIsShowTable"></el-input>
            </div>
        </el-collapse-item>
        <el-collapse-item title="xxxx" name="3">
            <div>简化流程：设计简洁直观的操作流程；</div>
            <div>清晰明确：语言表达清晰且表意明确，让用户快速理解进而作出决策；</div>
            <div>帮助用户识别：界面简单直白，让用户快速识别而非回忆，减少用户记忆负担。</div>
        </el-collapse-item>
        <el-collapse-item title="可控 Controllability" name="4">
            <div>用户决策：根据场景可给予用户操作建议或安全提示，但不能代替用户进行决策；</div>
            <div>结果可控：用户可以自由的进行操作，包括撤销、回退和终止当前操作等。</div>
        </el-collapse-item>
        </el-collapse>
    </el-scrollbar>
    </div>
</div>

</template>

<script>
export default {
    name:"editLayout",
    data(){
        return{
            needParam:{
                layoutType:'1',//1.上查下表，2.左树右表
                isShowQuery:true,//是否展示查询区域
                isShowTableData:true,//是否展示表格区域
                isShowEdit:true,//是否展示编辑区域
                isShowPage:true,//是否显示分页区域
                isShowButton:true
            },
            activeName: '1',
            tableFileds:[],
            addType:'',//添加查询页面元素
            addTypeList:[
                {value:'1',label:'输入框'},
                {value:'2',label:'下拉框'},
                {value:'3',label:'日期下拉框'},
                {value:'4',label:'按钮'}
            ],
            queryList:[],//查询区域元素集合
            currencyList:[],//下拉框通用list
            currencyValue:'',//下拉框通用值
            dropDataSource:[
                {value:'1',label:'状态'}
            ],
             buttonDataSource:[
                {value:'1',label:'查询'},
                {value:'2',label:'重置'},
                {value:'3',label:'自定义'}
            ],
            tableList: [],//表格元素
            tableData: []
        }
    },
    computed:{
        frameRight(){
            return document.getElementsByClassName('right-body')[0].clientHeight - 40;
        }
    },
    methods:{
        empty(){
        },
        editInit(row){
            //置空
            this.empty()
            //通过id获取字段
            this.$http.get("system/sysAutoField/findByTableID/"+row.id).then(res => {
                if (res.data.code == "200") {
                    this.tableFileds = this.changeFields(res.data.body);
                }
                
            })
        },
        changeFields(data){
            data.forEach(e => {
                //是否为查询区域
                if(e.fieldIsquery == '1'){
                    e.fieldIsquery = true
                }
                //是否为表格区域
                if(e.fieldIsShowTable == '1'){
                    e.fieldIsShowTable = true
                }
                //是否为表单区域
                if(e.fieldIsShowTable == '1'){
                    e.fieldIsShowFrom = true
                }
            });
            return data
        },
        //根据
        back(){
            this.$emit("backfont")
        },
        addQueryItem(){
            if(!this.addType){
                return this.$message.warning("请选择添加类型");
            }
            var item = {
                type:this.addType,
                title:"",
                value:""
            }
            this.queryList.push(item)
        },
        empty(){
            this.queryList = []
            let queryItem = [
                {value:'1',label:'查询'},
                {value:'2',label:'重置'}
            ]
        },
        delItem(index){
            this.queryList.splice(index,1)
        }
    }
}
</script>
<style  scoped>
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
    width: auto;
    margin-right: 10px;
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