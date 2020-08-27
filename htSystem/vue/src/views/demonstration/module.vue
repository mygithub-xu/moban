<template>
    <div class="app-container" style="flex-direction: column;">
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

    <div style="height:400px;width:400px;float:left;margin-bottom:100px">

        <draggable v-model='getOpenTab' >
            <div
            class="list-group-item"
            v-for="element in getOpenTab"
            :key="element.name"
            >
            {{ element.name }}
            </div>
        </draggable>

    </div>

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
import draggable from 'vuedraggable'
export default {
        components:{
            draggable
        },
    computed:{
        getOpenTab: {
            get() {
                return this.$store.state.tabRouter.openTab
            },
            set(value) {
                this.$store.dispatch('changeTabFun', value)
            }
        }
    },
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

                enabled: true,
                list: [
                    { name: "John0", id: 0 },
                    { name: "Joao1", id: 1 },
                    { name: "Jean2", id: 2 }
                ],
                dragging: false

            }
        },
        mounted(){
            this.getdata();
        },
        methods:{
            onEditorChange2(content){
                // console.log(content);
            },
            getdata(){

            },
            handleClose(){
                this.editVisible=false;
            },
            uploadpho(){
                this.editVisible=true;
            },
            checkMove(e){
                console.log(this.list)

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
.buttons {
  margin-top: 35px;
}
.ghost {
  opacity: 0.5;
  background: #c8ebfb;
}
</style>