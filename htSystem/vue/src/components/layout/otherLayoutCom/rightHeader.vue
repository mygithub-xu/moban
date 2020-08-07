<template>
    <div class="layout-header-right" id="layout-header-right" >
        <span class="layout-header-right-icon">
            <el-tooltip  effect="light" content="退出" placement="bottom">
                <i class="icon iconfont icon-tuichu" @click="exitSys"> </i>
            </el-tooltip>
        </span>

        <span class="layout-header-right-icon">
                <userSetting :bgColor="bgColor"  :username="username"  :avatar="avatar" :fontColor="fontColor"></userSetting>
        </span>

        <span class="layout-header-right-icon">
            <el-tooltip  effect="light" content="全屏" placement="bottom">
                <i class="icon iconfont icon-quanping" @click="fullScreen"></i>
            </el-tooltip>
        </span>
        <span class="layout-header-right-icon">
            <i class="el-icon-setting" @click="changeSysType"> </i>
        </span>
    </div>
</template>
<script>
export default {
    name: 'rightHeader',
    data(){
        return {
        }
    },
    props:{
        bgColor:{
            type:String,
            default:""
        },
        username:{
            type:String,
            default:""
        },
        avatar:{
            type:String,
            default:""
        },
        fontColor:{
            type:String,
            default:"" 
        }

    },
    methods:{
        exitSys(){
            this.$emit("exitSys")
        },
        fullScreen(){
            this.$emit("fullScreen")
        },
        changeSysType(){
            this.$emit("changeSysType")
        },

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
    }

    .layout-header-right-icon .el-dropdown{
        margin-right: 15px;
    }
</style>