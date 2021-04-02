<template>
    <div class="headTabsDivCom" id="headTabsDivCom">
        
        <!-- <el-tabs v-model="indexTabTrue" closable  type="border-card"  @tab-remove="removeTab" @tab-click="tabclick" @contextmenu.prevent.native="openpop($event)">
            <el-tab-pane
                v-for="item in getOpenTab"
                :key="item.path"
                :label="item.name"
                :name="item.path">
            </el-tab-pane>
        </el-tabs> -->

        <div v-show="isShowSide" class="tab-left el-icon-arrow-left" @click="goLeft"></div> 
        
        <div id="tab-comp" class="tab-comp" :style="{'margin-left':tableft+'px'}">
            <draggable v-model='getOpenTab' >
                <transition-group type="transition" name="flip-list">
                    <div class="tab-items" v-for="item in getOpenTab" :key="item.path" @click="tabclick(item)"  @contextmenu.prevent.stop="openpop1($event)">
                        <span class="tab-item-span" :class="{'active': item.path == indexTabTrue}">{{item.name}}
                            <span class="el-icon-close tabItemsClose" @click.stop.prevent="removeTab(item.path)"></span>
                        </span>
                    </div>
                </transition-group>
            </draggable>
        </div>

       <div v-show="isShowSide" class="tab-right el-icon-arrow-right" @click="goRight"></div> 

        <div v-show="contextMenuVisible">
            <customPopBox :left="left" :top="top" :isShowLeft="isShowLeft" :isShowRight="isShowRight" :isShowReflash="isShowReflash" @popClick="popClick"></customPopBox>
        </div>

    </div>
</template>
<script>
import { mapGetters } from 'vuex'
import draggable from 'vuedraggable'
import customPopBox from '@/components/layout/otherLayoutCom/customPopBox.vue';
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
            tableft:0,
            //固定左边距
            fixedLeft:35,
            //每次向左滑动距离
            onceWidth:150,
            tabItemWidth: 0,
            isShowSide: false,
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
        },

    },
    mounted(){
        this.init()
         window.onresize = () =>{
            this.init()
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
        init(){
            
            this.isShowSide = false
            let aa = 0;
            let allwidthList= document.getElementsByClassName('tab-items');
            let allWidth= document.getElementById('headTabsDivCom').clientWidth -2*this.fixedLeft;
            for(var i=0;i<allwidthList.length;i++){
                let once = allwidthList[i].offsetWidth + allwidthList[0].offsetLeft;
                aa = aa + once
            }
            this.tabItemWidth = aa + allwidthList[0].offsetLeft;
            if(allWidth < aa){
                this.tableft = this.fixedLeft;
                this.isShowSide = true;
            }
            this.tableft = this.isShowSide?this.fixedLeft : 0;
            
        },
        goRight(){
            
            let allWidth= document.getElementById('headTabsDivCom').clientWidth;

            if(this.tabItemWidth-(allWidth - this.fixedLeft - this.tableft) > this.onceWidth){
                this.tableft = this.tableft - this.onceWidth
            }else{
                this.tableft = this.tableft - (this.tabItemWidth-(allWidth - this.fixedLeft - this.tableft))
            }
        },
        goLeft(){
            //当在左距大于tableft和在一次左滑动距离时
            // if(this.fixedLeft-this.onceWidth>=this.tableft>=this.fixedLeft){
            //     this.tableft = this.fixedLeft;
            //     return;
            // }

            //大于
            if(this.tableft<this.fixedLeft-this.onceWidth){
                this.tableft = this.tableft + this.onceWidth;
            }else{
                this.tableft = this.fixedLeft;
            }
            
        },
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
             console.log("结束"+ (new Date()).getTime())
        },
        //删除选中tab，更新tab路由，返回删除的第几个
        tabPathChange(tabPath){
            let getOpenTabList = this.getOpenTab;
            let index=0;
            let res = getOpenTabList.some((item,i) =>{
                if(item.path == tabPath){
                    index = i
                    return true;
                }
            })
            getOpenTabList.splice(index, 1);
            this.$store.dispatch('changeTabFun',getOpenTabList);
            return index;
        },
        openpop1(e){
            let obj = e.srcElement ? e.srcElement : e.target;
            if(obj.innerText){
                //弹出框的位置
                this.left = e.layerX+ 10;
                this.top = e.layerY + 10;
                this.contextMenuVisible = true;
                //查找该tab的位置
                let tabs=this.getOpenTab;
                for(let i=0;i<tabs.length;i++){
                    if(tabs[i].name == obj.innerText){
                        this.openTabIndex=i;
                        this.path = tabs[i].path
                    }
                    //记录当前页面位置
                    if(tabs[i].path==this.indexTabTrue){
                        this.indexTabTrueCount=i;
                    }
                }
                //初始化弹出框的显示
                this.restorePopShow();
                if(this.openTabIndex == 0){
                    this.isShowLeft=true
                }
                if(this.openTabIndex == tabs.length-1){
                    this.isShowRight=true
                }
                if(this.path == this.indexTabTrue){
                    this.isShowReflash=true
                }
            }
            
           
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
        overflow: hidden;
        position: relative;
    }
    .tab-comp{
        width: 1600px;
        height: 100%;
        margin-right: 30px;
        overflow-x:auto;
    }
    .tab-items{
        background-color: #fff;
        line-height: 34px;
        margin-top:2px;
        margin-left: 10px;
        border: 1px solid #ddd;
        border-radius: 4px;
        display: inline-block;
        cursor:pointer;
        text-align: center;
        min-width: 65px;
        transition: width 2s;
    }
    .tab-item-span{
        margin: 0 10px;
        font-size: 13px;
        cursor:pointer;
    }
    .tab-items .tabItemsClose{
        line-height: 35px;
        float: right;
        display: none;
    }
    .tab-items:hover .tabItemsClose{
        display: block;
        padding: 0;
    }
    .tab-items .active{
        color: #409EFF;
    }
    .tab-left{
        position: absolute;
        top: 0;
        left: 0;
        width: 25px;
        height: 100%;
        padding-left: 10px;
        font-size: 25px;
        line-height: 42px;
        cursor:pointer;
        background-color: white;
    }
    .tab-right{
        position: absolute;
        top: 0;
        right: 0;
        width: 25px;
        height: 100%;
        padding-right: 10px;
        font-size: 25px;
        line-height: 42px;
        cursor:pointer;
        background-color: white;
    }
    .flip-list-move {
        transition: transform 0.3s;
    }
    .flip-list-enter-active, .flip-list-leave-active {
        transition: all 0.3s ease;
    }
    .flip-list-enter, .flip-list-leave-to{
        opacity: 0;
        transform: translateX(40px);
    }
</style>