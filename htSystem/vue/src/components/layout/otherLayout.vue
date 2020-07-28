<template>
    <div class="layoutDiv">
        <!-- 头部 -->
        <div class="layout-header" :style="{'background-color':headerBgcolor}">
            <div class="layout-header-title" :style="{'width':menuWidth+'px','background-color':menuBgcolor}">
                <span  :style="{'width':menuWidth+'px'}">
                    <img src="../../assets/logo.png" class="layout-header-title-img"/>
                    <template v-if="!isCollapse">{{houtaiName}}</template>
                </span>
            </div>
            <div class="layout-header-left">
                <span class="layout-header-left-icon">
                    <i class="icon iconfont" :class="isCollapse?'icon-zhedie2':'icon-zhedie1'" @click="changeWidth"></i>
                </span>
                
            </div>
            <div class="layout-header-right">
                <span class="layout-header-right-icon">
                    <el-tooltip  effect="light" content="退出" placement="bottom">
                        <i class="icon iconfont icon-tuichu" @click="exitSys"> </i>
                    </el-tooltip>
                </span>

                <span class="layout-header-right-icon">

                    <el-menu class="el-menu-demo"  mode="horizontal" >
                        <el-submenu index="2"  popper-class="infoItem">
                             <template slot="title">
                                <el-avatar :src="avatar" class="avatarDiv" ></el-avatar>
                                <div class='welcome'>
                                    <span v-if="username">您好：{{username}}  </span>
                                    <span v-else></span>
                                </div>
                            </template>
                            <el-menu-item index="2-1" @click="setDialogInfo('info')">个人信息</el-menu-item>
                            <el-menu-item index="2-2" @click="setDialogInfo('xiugai')">修改信息</el-menu-item>
                            <el-menu-item index="2-3" @click="setDialogInfo('logout')">退出</el-menu-item>

                        </el-submenu>
                    </el-menu>
                </span>

                <span class="layout-header-right-icon">
                    <el-tooltip  effect="light" content="全屏" placement="bottom">
                        <i class="icon iconfont icon-quanping" @click="fullScreen"> </i>
                    </el-tooltip>
                </span>
                
                <span class="layout-header-right-icon">
                    <el-tooltip  effect="light" :content="isChinese?'中文':'英文'" placement="bottom">
                        <i class="icon iconfont " :class="isChinese?'icon-zhongwen':'icon-yingwen'" @click="changeLanguage"> </i>
                    </el-tooltip>
                </span>

            </div>

        </div>

        <div class="layout-body">
            <!-- 侧菜单 -->
            <sidebar2 class="menu_container" :style="{'width':menuWidth+'px','background-color':menuBgcolor}" :width="menuWidth+'px'"  :isCollapse="isCollapse" :bgcolor="menuBgcolor"></sidebar2>
            <AppMain2 class="app-main" :style="{'width': 'calc(100% - ' +menuWidth2+'px)'}">
                <!-- 面包屑 -->
                <slot>
                    <div class="breadcrumbDiv">
                        <el-breadcrumb  separator="/">
                        
                            <el-breadcrumb-item v-for="(item, index) in levelList" :key="index">
                                <a v-if="item.path" @click="handleLink(item)">{{item.name}}</a>
                                <span v-else>{{item.name}}</span>
                            </el-breadcrumb-item>
                        </el-breadcrumb>
                    </div>
                </slot>

            </AppMain2>
        </div>

    </div>
</template>
<script>
// import { mapGetters } from 'vuex';
import screenfull from 'screenfull';
import bus from "../common/js/bus";
export default {
    data(){
        return {
            houtaiName:"某某某后台",
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
            //默认菜单颜色
            menuBgcolor:"#333744",
            //默认头部颜色
            headerBgcolor:"#ffffff",
            //头像
            avatar:"",
            //昵称
            username:"",
            isChinese:true,
            levelList:[]
        }
    },
    created(){
        bus.$on('changeHead',val=>{
            this.showImg();
        })

        this.showImg();
        this.getBreadcrumb();
    },
    //动态检测路由变化
    watch: {
        $route(route) {
            this.getBreadcrumb()
        }

    },
    computed: {
        // ...mapGetters([
        //     'getAvatar',
        //     'getUserName'
        // ])
    },
    methods:{
        //缩放菜单
        changeWidth(){
            this.isCollapse=!this.isCollapse;
            if(this.isCollapse==false){
                this.menuWidth=this.openWidth;
                this.isCollapse=false;
            }else{
                this.menuWidth=this.closeWidth;
                this.isCollapse=true;
            }
            this.menuWidth2=this.menuWidth+1;
        },
        // 从缓存获取头像和姓名
        showImg() {
            //获取头像
            let user = JSON.parse(sessionStorage.getItem('user')); 
            this.avatar=user.headPortrait;
            this.username=user.userName;
            // console.log("showImg");

            //此处不需要vuex的方式来传递变量，因为一刷新值就没了。按储在本地，然后在axios前检测有没有token，后台用返回特定的code来判断在后台中有没有失效
            // this.avatar=this.getAvatar;
            // this.username=this.getUserName;

        },
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
        handleLink(item) {
            this.$router.push(item.path)
        },
        changeLanguage(){
            this.isChinese=!this.isChinese;
            if(this.isChinese){
                this.$i18n.locale="zh";
            }else{
                this.$i18n.locale="en";
            }
        },
        setDialogInfo(type){

            if(type=="info"){

            }
            if(type=="xiugai"){
                // 跳转到用户信息，传是否刷新的参数
                this.$router.push({path:'/views/user/userinfo'});
            }
        },
        exitSys(){
            this.$http.post(this.api.logout).then(res => {});
            sessionStorage.removeItem("Token");
            sessionStorage.removeItem("userId");
            sessionStorage.removeItem("menuData");
            sessionStorage.removeItem("menuList");
            sessionStorage.removeItem("buttonUrlList");
            this.$router.push("/login");
        },
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

    }

}
</script>
<style scoped>
    .layoutDiv{
        height: 100%;
        width: 100%;
    }

    .layout-header{
        height: 50px;
        /* border-bottom: solid 1px #e6e6e6; */
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
    .layout-header-title{
        height: 50px;
        text-align: center;
        float: left;
        overflow: hidden;
        transition: width 0.5s;
    }
    .layout-header-title span{
        font-size: 23px;
        color: #ffffff;
        float: left;
        line-height: 50px;
    }
    .layout-header-title-img{
        width:32px;
        height:32px;
        float:left;
        margin-top: 9px;
        margin-left: 15px;
    }


    .layout-header-left{
        height: 50px;
        float: left;
    }
    .layout-header-left-icon i{
        font-size: 25px;
        line-height: 50px;
        margin-left: 15px;
    }

    .layout-header-right ,.layout-header-right-icon {
        float: right;
        height: 50px;
    }
    .layout-header-right-icon i{
        font-size: 25px;
        line-height: 50px;
        margin-right: 15px;
    }
    .avatarDiv{
        height: 34px;
        width: 34px;
        margin-left: 10px;
        margin-top: 8px;
        float: right;
    }
    .layout-header-right-icon .el-dropdown{
        margin-right: 15px;
    }
    .breadcrumbDiv{
        height: 48px;
        width: 100%;
        float: left;
        background-color: #ffffff;
        border-top: 1px solid #f0f0f0;
        border-bottom: 1px solid #f0f0f0;
    }
    .breadcrumbDiv .el-breadcrumb{
        line-height: 48px;
        margin-left: 15px;
    }

</style>

<style lang="less">
    .layout-header-right .layout-header-right .el-submenu__title{
        height: 50px;
    }
    .layout-header-right .el-menu--horizontal>.el-submenu .el-submenu__title{
        height: 50px!important;
        line-height: 50px!important;
    }
    .layout-header-right .el-menu--horizontal>.el-submenu.is-active .el-submenu__title{
        border-bottom:0px solid #409EFF
    }

    .layout-header-right .el-submenu .el-icon-arrow-down{
        display: none;
    }
    .layout-header-right .el-menu--horizontal{
        border-bottom:solid 0px #e6e6e6;
    }
    .layout-header-right  .el-submenu__title{
        padding-left: 0px;
    }
</style>