<template>
    <div class="app-container">
        <el-scrollbar class="commonScrollbar">
            
            
              <el-col :span="20"  >
                  <div class="common-div">
                <span>树形下拉框组件（带查询）：</span>
                <div class="treeSelect" >
                    <treeSelect v-model="id"  :data="data" label="label" />
                </div>
                </div>
              </el-col>
            
              <el-col :span="20"  >
                <div class="common-div">
                    <span>富文本组件（优化了图片插入）：</span>
                    <editor ref="myTextEditor" :value="fuwenben" @onEditorChange2="onEditorChange2" height="500"></editor>
                </div>
              </el-col>
            
              <el-col :span="20"  >
                <div class="common-div">
                    <span>图片上传组件（可多图上传）：</span>
                <div class="uploadMore">
                    <el-button type="warning" size="small"   @click="uploadpho">上传图片</el-button>
                </div>
                </div>
              </el-col>

        </el-scrollbar>

        <el-dialog
          title="图片上传"
          :visible.sync="editVisible"
          width="50%"
          :before-close="handleClose">
          <uploadMore ref="upload3" ></uploadMore>
        </el-dialog>
    </div>
</template>
<script>
export default {
        data(){
            return{
                fuwenben:"我是富文本Quill",
                id:'aaa',
                data: [{
                    label: '一级 1',
                    children: [{
                        label: '二级 1-1',
                        children: [{
                        label: '三级 1-1-1'
                        }]
                    }]
                    }, {
                    label: '一级 2',
                    children: [{
                        label: '二级 2-1',
                        children: [{
                        label: '三级 2-1-1'
                        }]
                    }, {
                        label: '二级 2-2',
                        children: [{
                        label: '三级 2-2-1'
                        }]
                    }]
                    }, {
                    label: '一级 3',
                    children: [{
                        label: '二级 3-1',
                        children: [{
                        label: '三级 3-1-1'
                        }]
                    }, {
                        label: '二级 3-2',
                        children: [{
                        label: '三级 3-2-1'
                        }]
                    }]
                }],
                editVisible:false,

            }
        },
        mounted(){
            this.getdata();
        },
        methods:{
            onEditorChange2(content){
                console.log(content);
            },
            getdata(){

            },
            handleClose(){
                this.editVisible=false;
            },
            uploadpho(){
                this.editVisible=true;
            }
        }
    
}
</script>
<style scoped>
.app-container{
    background-color: #ffffff;
    padding: 10px 0;
}
.commonScrollbar{
    height: 100%;
    padding-left: 50px;
}
.common-introduction-area{
    width: 100%;
    min-height: 150px;
}
.common-div{
    margin-bottom: 20px;
    margin-top: 20px;
    float: left;
}

.treeSelect{
    width: 300px;
    height: 33px;
    margin-top: 5px;
    line-height: 20px;
}
.common-div span{
    margin-bottom: 10px;
    
}
.uploadMore{
    width: 100%;
    float: left;
    height: 200px;
    margin-top: 20px;
}
</style>