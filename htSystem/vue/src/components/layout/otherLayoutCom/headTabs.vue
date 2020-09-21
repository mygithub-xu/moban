<template>
    <div class="headTabsDivCom">
        
                <!-- <el-tabs v-model="indexTabTrue" closable  type="border-card"  @tab-remove="removeTab" @tab-click="tabclick" @contextmenu.prevent.native="openpop($event)">
                        <el-tab-pane
                            v-for="item in getOpenTab"s
                            :label="item.name"
                            :name="item.path">
                        </el-tab-pane>
                </el-tabs> -->

                <draggable v-model='getOpenTab' >
                    <transition-group type="transition" :name="'flip-list'">
                    <div class="tabItems" v-for="item in getOpenTab" :key="item.path" @click="tabclick(item)">
                        <span>{{item.name}}</span>
                        <div class="el-icon-close tabItemsClose" @click="removeTab(item.path)"></div>
                    </div>
                    </transition-group>
                </draggable>
        <div v-show="contextMenuVisible">
            <customPopBox :left="left" :top="top" :isShowLeft="isShowLeft" :isShowRight="isShowRight" :isShowReflash="isShowReflash" @popClick="popClick"></customPopBox>
        </div>
    </div>
</template>
<script>
import { mapGetters } from 'vuex'
import draggable from 'vuedraggable'
import customPopBox from '@comp/layout/otherLayoutCom/customPopBox.vue';
export default {
    name: 'headTabs',
    components:{
        draggable,
        customPopBox
    },
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
    computed:{
        ...mapGetters([
            'getIndexTab',
        ]),
        getOpenTab: {
            get() {
                return this.$store.state.tabRouter.openTab
            },
            set(value) {
                this.$store.dispatch('changeTabFun', value)
            }
        }
    },
    props:{
        indexTab:{
            type:String,
            default:""
        }
    },
    created(){
        //解决刷新时当前tab未加载bug
        if(this.getIndexTab){
            this.indexTabTrue = this.getIndexTab;
        }
        
    },
    watch:{
        //动态检测路由变化
         $route(route) {
            this.changeTabRouter()
        },
        //解决点击tab时v-model双向绑定bug
        getIndexTab(newName, oldName){
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
        //路由变化，相应的tab也要改变
        changeTabRouter(){
            //获取要进入的路由
            let matched = this.$route.matched;
            
            //判断tab中是否存在该路由，存在不更新，不存在更新
            let nowRouterList=this.getOpenTab;
            let isExist=false;
            nowRouterList.forEach(v => {
                if(matched[1].path==v.path){
                    isExist=true;
                }
            });
            if(!isExist){
                let componet=!matched[1].components.default.name?'':matched[1].components.default.name;
                let newTab={
                    name: matched[1].name,
                    path: matched[1].path,
                    component:componet
                }
                nowRouterList.push(newTab);
                this.$store.dispatch('changeTabFun',nowRouterList);
            }
            //更新当前路由
            this.$store.dispatch('changeIndexTabFun',matched[1].path);
                
        },
        tabclick(tab){
            this.$router.push(tab.path);
        },
        removeTab(tabPath){
            if(this.getOpenTab.length==1&&tabPath=="/page/Dashboard"){
                  return this.$message({
                        type: "error",
                        message: "首页不能删除"
                    });
            }
            if(tabPath==this.indexTab){
                //确定关闭当前页面？？

                this.$confirm("是否关掉当前页面?", "提示", {
                    confirmButtonText: "确定",
                    cancelButtonText: "取消",
                    type: "warning"
                }).then(() => {

                        let index=this.tabPathChange(tabPath);

                        //更新当前选定的tab
                        if(this.getOpenTab.length==0){

                            this.$router.push("/page/Dashboard");
                        }else if(this.getOpenTab.length<=index){
                            this.$router.push(this.getOpenTab[this.getOpenTab.length-1].path);
                        }else{
                            this.$router.push(this.getOpenTab[index].path);
                        }

                    }).catch(() => {
                        this.$message({type: "info", message: "已取消"});
                    });


            }else{
                let index=this.tabPathChange(tabPath);   
            }

        },
        //删除选中tab，更新tab路由，返回删除的第几个
        tabPathChange(tabPath){
                let getOpenTabList=this.getOpenTab;
                let index=0;
                for(let i=0;i<getOpenTabList.length;i++){
                    if(tabPath==getOpenTabList[i].path){
                        getOpenTabList.splice(i, 1);
                        index=i;
                        break;
                    }
                }
                this.$store.dispatch('changeTabFun',getOpenTabList);
                return index;
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
                let tabs=this.getOpenTab;
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
                let tabs=this.getOpenTab;
                tabs.splice(0,this.openTabIndex);
                this.$store.dispatch('changeTabFun',tabs);
                //判断当前页面是否在左，在左改变状态
                if(this.indexTabTrueCount<this.openTabIndex){
                    this.$router.push(this.path);
                }
            }
            if(type=='closeRight'){
                // 关闭右边
                let tabs=this.getOpenTab;
                tabs.splice(this.openTabIndex+1);
                this.$store.dispatch('changeTabFun',tabs);
                //判断当前页面是否在右，在左改变状态
                if(this.indexTabTrueCount>this.openTabIndex){
                    this.$router.push(this.path);
                }
            }
            if(type=='closeOther'){
                // 关闭其他
                let tabs=this.getOpenTab;
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
        height: 100%;
        width: 100%;
        float: left;
    }
    .el-tabs--border-card{
        box-shadow:0 0px 0px 0 rgba(0,0,0,.12), 0 0 0px 0 rgba(0,0,0,.04)
    }
</style>
<style >

    /* .headTabsDivCom .el-tabs__header{
        margin: 0;
        border-bottom: 0px solid #E4E7ED;
    }
    .headTabsDivCom .el-tabs--card>.el-tabs__header .el-tabs__nav{
        border: 0px solid #E4E7ED;
    }
    .headTabsDivCom .el-tabs--card>.el-tabs__header .el-tabs__item{
        border: 0px solid #E4E7ED;
    } */

    .headTabsDivCom .el-tabs--border-card>.el-tabs__content{
        padding: 0!important;
    }
    .headTabsDivCom .el-tabs--border-card>.el-tabs__header{
        border-bottom: 0px solid #E4E7ED;
    }
    .headTabsDivCom .el-tabs--border-card>.el-tabs__header .el-tabs__item.is-active{
        border-radius: 10px;
        box-shadow: 0px 0px 2px rgb(219, 216, 216);
    }
    .tabItems{
        background-color: #fff;
        line-height: 34px;
        margin-top:2px;
        margin-left: 10px;
        border: 1px solid #ddd;
        border-radius: 4px;
        float: left;
        cursor:pointer;
        transition:width 5s;
    }
    .tabItems span{
        margin: 0 5px;
        font-size: 13px;
        cursor:pointer
    }
    .flip-list-move {
        transition: transform 0.5s;
    }
    .tabItems .tabItemsClose{
        float: right;
        display: none;
        
    }
    .tabItems:hover .tabItemsClose{
        
        display: block;
    }
</style>