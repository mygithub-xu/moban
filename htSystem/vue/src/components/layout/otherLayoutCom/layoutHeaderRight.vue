<template>
    <div class="layout-header-right" id="layout-header-right" :style="{'color':getLayoutParam.headerFontColor}">
        <span class="layout-header-right-icon">
            <el-tooltip  effect="light" content="退出" placement="bottom">
                <i class="icon iconfont icon-tuichu" @click="headRightClick('exit')"> </i>
            </el-tooltip>
        </span>

        <span class="layout-header-right-icon">
            <div class="userSettingDiv">
                <el-menu class="el-menu-demo"  mode="horizontal" :background-color="getLayoutParam.headerBGColor" 
                :text-color="getLayoutParam.headerFontColor" 
                :active-text-color="getLayoutParam.headerFontColor"
                >
                    <el-submenu index="2"  popper-class="infoItem">
                        <template slot="title">
                            <!-- <el-avatar :src="getAvatar" class="avatarDiv" ></el-avatar> -->
                            <div class="menugetUserName" :style="{'color':getLayoutParam.headerFontColor}">
                                <span v-if="getUserName">您好: {{getUserName}}</span>
                                <span v-else>未登录</span>
                            </div>
                        </template>
                        <el-menu-item index="2-2" @click="headRightClick('changeUserInfo')">修改信息</el-menu-item>
                        <el-menu-item index="2-3" @click="headRightClick('clear')">清除缓存</el-menu-item>
                    </el-submenu>
                </el-menu>
            </div>
        </span>

        <span class="layout-header-right-icon">
            <el-tooltip  effect="light" content="全屏" placement="bottom">
                <i class="icon iconfont icon-quanping" @click="headRightClick('fullScreen')"></i>
            </el-tooltip>
        </span>
        <span class="layout-header-right-icon">
            <i class="el-icon-setting" @click="headRightClick('openDrawer')"> </i>
        </span>

        <!-- <span class="layout-header-right-icon">
            <i class="icon iconfont  icon-xiaoxitongzhi" > </i>
        </span> -->
    </div>
</template>
<script>
import { mapGetters } from 'vuex';
import screenfull from 'screenfull';
export default {
    name: 'layoutHeaderRight',
    data(){
        return {
        }
    },
    props:{
    },
    computed:{
        ...mapGetters([
            'getAvatar',
            'getLayoutParam'
        ]),
        getUserName(){
            
            return this.$User.getName()
        }
    },
    methods:{
        headRightClick(data){
            
            switch(data){
                case 'exit': this.exitSys()
                break;
                case 'fullScreen': this.fullScreen()
                break;
                case 'changeUserInfo': this.changeUserInfo()
                break;
                case 'clear': this.clear()
                break;
                case 'openDrawer': this.$emit("headRightClick",data)
                break;
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
        changeUserInfo(){

        },
        clear(){

        }
    }
}
</script>
<style scoped>
    .layout-header-right{
        float: right;
        height: 50px;
        min-width: 300px;
    }
    .layout-header-right ,.layout-header-right-icon {
        float: right;
        height: 50px;
    }
    .layout-header-right-icon i{
        font-size: 25px;
        line-height: 50px;
        margin-right: 15px;
        cursor: pointer;
    }
    .layout-header-right-icon .el-dropdown{
        margin-right: 15px;
    }
    .avatarDiv{
        height: 34px;
        width: 34px;
        margin-left: 10px;
        margin-top: 8px;
        float: right;
    }
    .menuUserName{
        float: left;
        height: 50px;
    }

</style>
<style lang="less">
    .userSettingDiv  .el-submenu__title{
        height: 50px;
    }
    .userSettingDiv .el-menu--horizontal>.el-submenu .el-submenu__title{
        height: 50px!important;
        line-height: 50px!important;
    }
    .userSettingDiv .el-menu--horizontal>.el-submenu.is-active .el-submenu__title{
        border-bottom:0px solid #409EFF
    }

    .userSettingDiv .el-submenu .el-icon-arrow-down{
        display: none;
    }
    .userSettingDiv .el-menu--horizontal{
        border-bottom:solid 0px #e6e6e6;
    }
    .userSettingDiv  .el-submenu__title{
        padding-left: 0px;
    }
</style>