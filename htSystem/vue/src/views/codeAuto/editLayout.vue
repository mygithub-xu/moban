<template>
<div class="edit-frame">
    <div class="edit-frame-left">
        <template v-if="needParam.layoutType == '1'">
            <template v-if="needParam.isShowQuery">
                <div class="container-query2" >
               
            <el-form ref="fond-form" label-width="120px">
                <div class="query-area">
                    <el-form-item label="动支单号" >
                        <el-input  clearable></el-input>
                    </el-form-item>
                    <el-form-item label="动支标题">
                        <el-input  clearable></el-input>
                    </el-form-item>
                    <el-form-item label="状态" >
                        <el-input  clearable></el-input>
                    </el-form-item>
                    <el-form-item label="申请人" >
                        <el-input  clearable></el-input>
                    </el-form-item>
                    <el-form-item label="申请日期" style="width: 50%">
                        <div style="display:flex; max-width: 500px;">
                            <el-date-picker

                                type="date"
                                placeholder="选择日期">
                            </el-date-picker>
                            <span style="margin:0 5px">至</span>
                            <el-date-picker
                            

                                type="date"
                                placeholder="选择日期">
                            </el-date-picker>
                        </div>
                    </el-form-item>
                    <div class="query-button" style="width:100%;padding-left:30px">
                        <el-button type="primary" >搜索</el-button>
                        <el-button type="warning" >重置</el-button>
                        <el-button type="primary" >合并提交OA</el-button>
                    </div>
                </div>
            </el-form>
                </div>
            </template>
            <template v-if="needParam.isShowTableData">
                <div class="container-table2">
                    
                </div>
            </template>
        </template>
    </div>

    <div class="edit-frame-right">
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

    <div class="data-area-item" v-for="item in tableFileds" :key="item.id">
        <el-checkbox class="data-area-checkbox" v-model="item.fieldIsquery">{{item.fieldName}}</el-checkbox>
        <el-input placeholder="请输入查询标题" v-model="item.fieldIsShowTable"></el-input>
    </div>

  </el-collapse-item>
  <el-collapse-item title="列表区域" name="2">
    <div>控制反馈：通过界面样式和交互动效让用户可以清晰的感知自己的操作；</div>
    <div>页面反馈：操作后，通过页面元素的变化清晰地展现当前状态。</div>
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
            query:{

            }
            
        }
    },
    methods:{
        handlePage(){

        },
        editInit(row){
            //通过id获取字段
            this.$http.get("system/sysAutoField/findByTableID/"+row.id).then(res => {
                if (res.data.code == "200") {
                    
                    this.tableFileds = this.changeFields(res.data.body);
                    console.log(res.data.body)
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

    }
}
</script>
<style  scoped>
.edit-frame{
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: row;
    margin-bottom: 10px;
}
.edit-frame-left{
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    margin-right: 10px;
    
}
.edit-frame-right{
    width: 300px;
    height: 100%;
    background: #fff;
    padding: 10px 10px;
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
.data-area-item{
    display: flex;
    flex-direction: row;
    line-height: 32px;
}
.data-area-checkbox{
    width: 100px;
    height: 33px;
    overflow:hidden; /*超出的部分隐藏起来。*/ 

}
.data-area-item .el-input{
    margin: 0 10px;
    width: 100px;
}
</style>