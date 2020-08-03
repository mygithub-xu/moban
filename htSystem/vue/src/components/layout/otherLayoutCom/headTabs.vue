<template>
    <div class="headTabsDivCom">
        <el-tabs v-model="indexTabTrue" closable  type="card"  @tab-remove="removeTab" @tab-click="tabclick" @contextmenu.prevent.native="openpop($event)">
            <el-tab-pane
                v-for="item in tableTabs"
                :key="item.path"
                :label="item.name"
                :name="item.path"
            >
            </el-tab-pane>
        </el-tabs>
        <div v-show="contextMenuVisible">
            <customPopBox :left="left" :top="top" :isShowLeft="isShowLeft" :isShowRight="isShowRight" :isShowReflash="isShowReflash" @popClick="popClick"></customPopBox>
        </div>
    </div>
</template>
<script>

export default {
    name: 'headTabs',
    data(){
        return {
            indexTabTrue:"",
            left:50,
            top:50,
            contextMenuVisible:false,
            isShowRight:false,
            isShowLeft:false,
            isShowReflash:false,
            path:"",
            openTabIndex:0,
            indexTabTrueCount:0,
        }
    },

    props:{
        tableTabs:{
            type:Array,
            default:[]
        },
        indexTab:{
            type:String,
            default:""
        }
    },
    created(){
        //解决刷新时当前tab未加载bug
        if(this.$store.state.tabRouter.indexTab){
            this.indexTabTrue=this.$store.state.tabRouter.indexTab;
        }
        
    },
    watch:{
        //解决点击tab时v-model双向绑定bug
        indexTab(newName, oldName){
            this.indexTabTrue=newName;
        },
        contextMenuVisible(value) {
            if (this.contextMenuVisible) {
                document.body.addEventListener("click", this.closeContextMenu);
            } else {
                document.body.removeEventListener("click", this.closeContextMenu);
            }
        },
    },
    methods:{

        tabclick(tab){
            this.$router.push(tab.name);
        },
        removeTab(tabPath) {
            this.$emit("removeTab",tabPath);
        },
        openpop(e){

            let obj = e.srcElement ? e.srcElement : e.target;
            if(obj.id){
                //弹出框的位置
                let currentContextTabId = obj.id.split("-")[1];
                this.path=currentContextTabId;
                this.left = e.clientX+ 10;
                this.top = e.clientY + 10;
                this.contextMenuVisible=true;
                //查找该tab的位置
                let tabs=this.tableTabs;
                for(let i=0;i<tabs.length;i++){
                    if(tabs[i].path==this.path){
                        this.openTabIndex=i;
                    }
                    if(tabs[i].path==this.indexTabTrue){
                        this.indexTabTrueCount=i;
                    }
                }
                //初始化弹出框的显示
                this.restorePopShow();
                if(this.openTabIndex==0){
                    this.isShowLeft=true
                }
                if(this.openTabIndex==tabs.length-1){
                    this.isShowRight=true
                }
                if(this.path==this.indexTabTrue){
                    this.isShowReflash=true
                }

            }
            
           
        },
        closeContextMenu() {
            this.contextMenuVisible = false;
        },
        popClick(type){
            console.log("type"+type+" 路径"+this.path);
            if(!this.path){
                return this.$message({message: '请选择一个选项', type: 'warning'});
            }
            
            if(type=='reflash'){
                //刷新
                this.$router.push(this.path);
            }
            if(type=='closeAll'){
                // 关闭所有
                let openTab=[]
                this.$store.dispatch('changeTabFun',openTab);
                this.$router.push("/page/Dashboard");
            }
            if(type=='closeLeft'){
                // 关闭左边
                let tabs=this.tableTabs;
                tabs.splice(0,this.openTabIndex);
                this.$store.dispatch('changeTabFun',tabs);
                //判断当前页面是否在左，在左改变状态
                if(this.indexTabTrueCount<this.openTabIndex){
                    this.$router.push(this.path);
                }
            }
            if(type=='closeRight'){
                // 关闭右边
                let tabs=this.tableTabs;
                tabs.splice(this.openTabIndex+1);
                this.$store.dispatch('changeTabFun',tabs);
                //判断当前页面是否在右，在左改变状态
                if(this.indexTabTrueCount>this.openTabIndex){
                    this.$router.push(this.path);
                }
            }
            if(type=='closeOther'){
                // 关闭其他
                let tabs=this.tableTabs;
                let openTab=[
                    {
                        name:tabs[this.openTabIndex].name,
                        path:tabs[this.openTabIndex].path
                    }
                ]
                this.$store.dispatch('changeTabFun',openTab);
                //是否为当前页面，不是则改变
                if(this.indexTabTrueCount!=this.openTabIndex){
                    this.$router.push(this.path);
                }
            }

        },
        restorePopShow(){
            this.isShowLeft=false;
            this.isShowRight=false;
            this.isShowReflash=false;
        }

    }
    
}
</script>
<style scoped>
    .headTabsDivCom{
        height: 48px;
        width: 100%;
        float: left;
    }

</style>
<style >

    .headTabsDivCom .el-tabs__header{
        margin: 0;
        border-bottom: 0px solid #E4E7ED;
    }
    .headTabsDivCom .el-tabs--card>.el-tabs__header .el-tabs__nav{
        border: 0px solid #E4E7ED;
    }
    .headTabsDivCom .el-tabs--card>.el-tabs__header .el-tabs__item{
        border: 0px solid #E4E7ED;
    }
</style>