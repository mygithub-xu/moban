<template>
    <div class="userSettingDiv">
        <el-menu class="el-menu-demo"  mode="horizontal" :background-color="bgColor">
            <el-submenu index="2"  popper-class="infoItem">
                <template slot="title">
                    <el-avatar :src="avatar" class="avatarDiv" ></el-avatar>
                    <div class="menuUserName">
                        <span v-if="username">您好：{{username}}  </span>
                        <span v-else></span>
                    </div>
                </template>
                <el-menu-item index="2-1" @click="setDialogInfo('info')">个人信息</el-menu-item>
                <el-menu-item index="2-2" @click="setDialogInfo('xiugai')">修改信息</el-menu-item>
                <el-menu-item index="2-3" @click="setDialogInfo('clear')">清除缓存</el-menu-item>

            </el-submenu>
        </el-menu>
    </div>
</template>

<script>
export default {
  name: 'userSetting',
  data(){
      return {
        height:'',
      }
  },
  props:{
      bgColor:{
          type:String,
          default:"#ffffff"
      },
      username:{
          type:String,
          default:""
      },
      avatar:{
          type:String,
          default:""
      },
  },
  methods:{
        //用户下拉功能菜单
        setDialogInfo(type){

            if(type=="info"){

            }
            if(type=="xiugai"){
                // 跳转到用户信息，传是否刷新的参数
                this.$router.push({path:'/views/user/userinfo'});
            }
            //清除缓存
            if(type=="clear"){
                // var openTab=[
                //     {
                //         name:"系统首页",
                //         path:"/page/Dashboard"
                //     }
                // ]
                // this.$store.dispatch('changeTabFun',openTab);
            }
        },
  }

}
</script>

<style lang="scss" scoped>
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