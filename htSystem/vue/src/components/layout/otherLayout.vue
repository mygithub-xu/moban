<template>
    <div class="layoutDiv" @mousemove="walk()">
        <!-- 头部 -->

        <template v-if="layoutType == '1'">
        <layoutHeader :layoutType="layoutType" :layoutTypeParam="layoutType1Param" :companyAtt="companyAtt" @changeWidth="changeWidth">
            <slot slot="rightHeader">
                <rightHeader :bgColor="layoutType1Param.headerBgcolor" :fontColor="layoutType1Param.rightHeadColor" :username="username"  
                :avatar="avatar" @changeSysType="changeSysType" @fullScreen="fullScreen" @exitSys="exitSys">
                </rightHeader>
            </slot>
        </layoutHeader>


        <div class="layout-body">
            <!-- 侧菜单 -->
            <sidebar class="menu_container" :style="{'width':layoutType1Param.menuWidth+'px','background-color':layoutType1Param.menuBgcolor}" 
            :bgcolor="layoutType1Param.menuBgcolor" :layoutType="layoutType" :isCollapse="layoutType1Param.isCollapse"
            :textColor="layoutType1Param.menuFontcolor" :activeTextColor="layoutType1Param.menuActiveFontcolor"
            ></sidebar>

            <AppMain class="app-main"   :style="{'width': 'calc(100% - ' +layoutType1Param.menuWidth2+'px)'}">
                <!-- 面包屑 -->
                <slot>
                    <div class="mainTopDiv">
                        <!-- <breadcrumb :levelList="levelList"></breadcrumb> -->
                        <headTabs  :tableTabs="getOpenTab" :indexTab="getIndexTab" @removeTab="removeTab"></headTabs>
                    </div>
                </slot>

            </AppMain>
        </div>
        </template>

        <template v-if="layoutType == '2'">
        <layoutHeader :layoutType="layoutType" :layoutTypeParam="layoutType2Param" :companyAtt="companyAtt" @changeWidth="changeWidth">

            <slot slot="rightHeader">
                <rightHeader :bgColor="layoutType2Param.headerBgcolor" :fontColor="layoutType2Param.menuFontcolor" :username="username"  
                    :avatar="avatar"  @changeSysType="changeSysType" @fullScreen="fullScreen" @exitSys="exitSys">
                </rightHeader>
            </slot>

        </layoutHeader>


        <div class="layout-body">
            <AppMain class="app-main" style="width:100%">
                <!-- 面包屑 -->
                <slot>
                    <div class="mainTopDiv">
                        <!-- <breadcrumb :levelList="levelList"></breadcrumb> -->
                        <headTabs  :tableTabs="getOpenTab" :indexTab="getIndexTab" @removeTab="removeTab"></headTabs>
                    </div>
                </slot>

            </AppMain>
        </div>


        </template>

        <el-drawer
        title="我是标题"
        :visible.sync="drawer"
        :with-header="false"
        @close="closeDrawer"
        size="320px">
               <layoutSetting 
            :layoutType="layoutType" :siderMenuColorList="siderMenuColorList" :TopMenuColorList="TopMenuColorList" :layoutType1Param="layoutType1Param" 
            :layoutType2Param="layoutType2Param" @typeClick="typeClick" @changeMenuColor="changeMenuColor" @changeTopColor="changeTopColor"></layoutSetting>

        </el-drawer>

    </div>
</template>
<script>
import { mapGetters } from 'vuex';
import screenfull from 'screenfull';
import bus from "@utils/bus";
export default {
    data(){
        return {
            //窗口宽度
            // screenWidth: "",
            //抽屉状态
            drawer: false,
            //布局类型
            layoutType:'2',
            //风格
            indexStyle:'1',
            //公司属性（名称，商标）
            companyAtt:{
                name:"某某某后台",
                imgSrc:require('../../assets/logo.png')
            },

            //布局1参数
            layoutType1Param:{
                //默认头部颜色
                headerBgcolor:"#ffffff",
                //right头部字体颜色
                rightHeadColor:"#909399",
                //头部默认宽度
                headWidth:200,
                //菜单默认宽度
                menuWidth: 200,
                //菜单关闭时宽度
                closeWidth: 50,
                //菜单打开时宽度
                openWidth:200,
                //主页面默认减去菜单宽度
                menuWidth2:201,
                //菜单状态
                isCollapse: false,
                //菜单颜色
                menuBgcolor:"#333744",
                //菜单字体颜色
                menuFontcolor:"#ffffff",
                //菜单当前字体颜色
                menuActiveFontcolor:"#409EFF",  
            },
            //布局2参数
            layoutType2Param:{
                //默认头部颜色
                headerBgcolor:"#ffffff",
                //right头部字体颜色
                rightHeadColor:"#909399",
                //菜单默认宽度
                menuWidth: 200,
                //头部默认宽度
                headWidth:200,
                //菜单字体颜色
                menuFontcolor:"#000000",
                //菜单当前字体颜色
                menuActiveFontcolor:"#409EFF",
            },

            options:[
                {
                    value: '1',
                    label: '左菜单'
                },
                {
                    value: '2',
                    label: '上菜单'
                }
            ],
            options2:[
                {
                    value: '1',
                    label: '经典模式'
                },
                {
                    value: '2',
                    label: '暗模式'
                }
            ],

            //侧边栏颜色
            siderMenuColorList:["#409EFF","#67C23A","#ffffff","#E6A23C","#F56C6C","#909399","#303133","#333744"],
            //顶部导航栏颜色
            TopMenuColorList:["#409EFF","#67C23A","#ffffff","#E6A23C","#F56C6C","#909399"],
            //头像
            avatar:"",
            //昵称
            username:"",
            isChinese:true,
            levelList:[],

        }
    },
    created(){
        bus.$on('changeHead',val=>{
            this.showImg();
        })
        //样式变化
        this.getdata();
        // this.getBreadcrumb();
    },
    mounted(){
        window.onresize = () => {
            
            if(this.layoutType=='2'){
                this.type2Data();
            }

        }

    },
    //动态检测路由变化
    watch: {
        $route(route) {
            // this.$route.meta.keepAlive
            this.changeTabRouter()
        },
    },
    computed: {
        ...mapGetters([
            'getOpenTab',
            'getIndexTab'
        ])
    },
    methods:{
        closeDrawer(){
            //保存样式
            var layoutStyle={
                layoutType:this.layoutType,
                layoutType1Param:this.layoutType1Param,
                layoutType2Param:this.layoutType2Param
            }
            sessionStorage.setItem("layoutstyle",JSON.stringify(layoutStyle));
        },
        getdata(){
            // this.fuzhi();
            this.getLayoutType();
            this.showImg();
        },
        removeTab(tabPath){
            
            if(this.getOpenTab.length==1&&tabPath=="/page/Dashboard"){
                  return this.$message({
                        type: "error",
                        message: "首页不能删除"
                    });
            }
            if(tabPath==this.getIndexTab){
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
                let tableTabsList=this.getOpenTab;
                let index=0;
                for(let i=0;i<tableTabsList.length;i++){
                    if(tabPath==tableTabsList[i].path){
                        tableTabsList.splice(i, 1);
                        index=i;
                        break;
                    }
                }
                this.$store.dispatch('changeTabFun',tableTabsList);
                return index;
        },


        //缩放菜单
        changeWidth(){
            this.layoutType1Param.isCollapse=!this.layoutType1Param.isCollapse;
            if(this.layoutType1Param.isCollapse==false){
                this.layoutType1Param.menuWidth=this.layoutType1Param.openWidth;
                
                this.layoutType1Param.isCollapse=false;
            }else{
                this.layoutType1Param.menuWidth=this.layoutType1Param.closeWidth;
                this.layoutType1Param.isCollapse=true;
            }
            this.layoutType1Param.menuWidth2=this.layoutType1Param.menuWidth+1;
            this.layoutType1Param.headWidth=this.layoutType1Param.menuWidth;
        },


        // 从缓存获取头像和姓名
        showImg() {
            //获取头像
            let user = JSON.parse(sessionStorage.getItem('user')); 
            this.avatar=user.headPortrait;
            this.username=user.userName;
        },
        //面包屑
        getBreadcrumb() {
            this.levelList=[];
            let matched = this.$route.matched;
            var shouye={
                name:"首页",
                path:"/page/Dashboard"
            }
            this.levelList.push(shouye);
            if(matched[1].path=="/page/Dashboard"){
                return;
            }
            var end={
                name:matched[1].name,
                path:matched[1].path
            }
            if(matched[1].meta.length>0){
                this.levelList=this.levelList.concat(matched[1].meta);
            }
            this.levelList.push(end);
        },
        //由面包屑进入
        handleLink(item) {
            this.$router.push(item.path)
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
        //国际化，改编语言
        changeLanguage(){
            this.isChinese=!this.isChinese;
            if(this.isChinese){
                this.$i18n.locale="zh";
            }else{
                this.$i18n.locale="en";
            }
        },

        //离开系统
        exitSys(){

            this.$http.post(this.api.logout).then(res => {
                if(res.code=="200"){
                    sessionStorage.removeItem("Token");
                }
            });
            
            sessionStorage.removeItem("userId");
            sessionStorage.removeItem("menuData");
            sessionStorage.removeItem("menuList");
            sessionStorage.removeItem("buttonUrlList");
            //将开启的tab关闭
            var openTab=[
                {
                    name:"系统首页",
                    path:"/page/Dashboard",
                    component:"Dashboard"
                }
            ]
            this.$store.dispatch('changeTabFun',openTab);
            this.$router.push("/login");
        },
        //全屏
        fullScreen(){
            if (!screenfull.isEnabled) {
                this.$message({
                message: '浏览器不支持全屏',
                type: 'warning'
                })
                return false
            }
            screenfull.toggle()
        },
        //打开抽屉
        changeSysType(){
            this.drawer=true
        },
        //初始化系统样式
        getLayoutType(type){
            var layoutstyleSession=JSON.parse(sessionStorage.getItem("layoutstyle"));
            if(!layoutstyleSession||layoutstyleSession==null){
                this.layoutType="1";
                this.type1Data();
            }else{
                this.layoutType=layoutstyleSession.layoutType;
                this.layoutType1Param=layoutstyleSession.layoutType1Param;
                this.layoutType2Param=layoutstyleSession.layoutType2Param;
                this.type2Data()
            }
        },

        //type1初始时样式数据
        type1Data(){
            this.layoutType1Param={
                //默认头部颜色
                headerBgcolor:"#ffffff",
                 //right头部字体颜色
                rightHeadColor:"#909399",
                //头部默认宽度
                headWidth:200,
                //菜单默认宽度
                menuWidth: 200,
                //菜单关闭时宽度
                closeWidth: 50,
                //菜单打开时宽度
                openWidth:200,
                //主页面默认减去菜单宽度
                menuWidth2:201,
                //菜单状态
                isCollapse: false,
                //菜单颜色
                menuBgcolor:"#333744",
                //菜单字体颜色
                menuFontcolor:"#ffffff",
                //菜单当前字体颜色
                menuActiveFontcolor:"#409EFF",
            }
        },

        //type2时样式数据
        type2Data(){
            this.$nextTick(()=>{
                //动态获取宽度
                this.layoutType2Param.headWidth=200;
                var rightwidth= document.getElementById('layout-header-right').clientWidth; //右边宽度    headWidth左边宽度   
                var allwidth= document.getElementById('layout-header').clientWidth;
                var leftwidth = this.layoutType2Param.headWidth;
                this.layoutType2Param.menuWidth =allwidth - rightwidth - leftwidth;
            })

        },

        //布局选择
        typeClick(type){
            this.layoutType=type;
            if(this.layoutType=='2'){
                
                if(this.layoutType2Param.headerBgcolor=='#ffffff'){
                    this.layoutType2Param.menuFontcolor="#000000";
                }else{
                    this.layoutType2Param.menuFontcolor="#ffffff";
                }
                this.type2Data();
            }
            
        },
        //侧边栏颜色选择
        changeMenuColor(color){
                if(this.layoutType1Param.menuBgcolor==color){
                    return;
                }else{
                    this.layoutType1Param.menuBgcolor=color;
                    if(this.layoutType1Param.menuBgcolor=="#ffffff"){
                        this.layoutType1Param.menuFontcolor="#000000"
                    }else{
                        this.layoutType1Param.menuFontcolor="#ffffff"
                    }
                }

        },
        //顶部导航栏颜色选择
        changeTopColor(color){
        
            if(this.layoutType=='1'){
                if(color=="#ffffff"){
      
                    this.layoutType1Param.rightHeadColor="#909399"
                }else{
                    this.layoutType1Param.rightHeadColor="#ffffff"
                }

                if(this.layoutType1Param.headerBgcolor==color){
                    return;
                }else{
                    this.layoutType1Param.headerBgcolor=color;
                }  
            }
            if(this.layoutType=='2'){
                if(this.layoutType2Param.headerBgcolor==color){
                    return;
                }else{
                    this.layoutType2Param.headerBgcolor=color;
                    if(this.layoutType2Param.headerBgcolor=="#ffffff"){
                        this.layoutType2Param.menuFontcolor="#000000"
                        this.layoutType2Param.rightHeadColor="#909399"
                    }else{
                        this.layoutType2Param.menuFontcolor="#ffffff"
                        this.layoutType2Param.rightHeadColor="#000000"
                    }
                }   
            }
        },
    walk(){
    //   window.clearInterval(this.timer);
    //   this.time = 0;

    //   this.timer = setInterval(() => {
    //   this.time += 1;

    //   if(this.time>1000){

    //   if (!sessionStorage.getItem("Token")) {
    //     window.clearInterval(this.timer);
    //     return false;
    //   } 
    //   this.$alert("登录已超时，请重新登录", "操作提示", {
    //     confirmButtonText: "确定",
    //     callback: () => {
    //       this.$http.post(this.api.logout).then(res => {});
    //       this.$router.push("/login");
    //     }
    //   });
      
    //   sessionStorage.removeItem("Token");
    //   sessionStorage.removeItem("userId");
    //   sessionStorage.removeItem("menuData");
    //   sessionStorage.removeItem("menuList");
    //   sessionStorage.removeItem("buttonUrlList");

    //   }

    //   },30000);
    }

    }

}
</script>
<style scoped>
    .layoutDiv{
        height: 100%;
        width: 100%;
    }
    .layout-body{
        height: calc(100% - 50px);
        width: 100%;
    }
    .menu_container{
        height: 100%;
        border-right: solid 1px #e6e6e6;
        float: left;
        overflow: hidden;
        transition: width 0.5s;
    }
    .app-main {
        height: 100%;
        overflow: hidden;
        float: left;
        background-color: #f0f0f0;
        transition: width 0.5s;
    }
    .mainTopDiv{
        height: 48px;
        width: 100%;
        float: left;
        background-color: #ffffff;
        border-top: 1px solid #f0f0f0;
        border-bottom: 1px solid #f0f0f0;
    }
</style>
