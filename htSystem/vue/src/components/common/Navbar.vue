<template>
   <div class="navbar">
   <!-- <span @click="handleLefeMenu" class="el-icon-s-fold"> 
		  <icon-svg icon-class="iconmenu-fold" :class="{isactive:changeBarDirection}" /> 
    </span> -->
    <hamburger :toggle-click="handleLefeMenu" :is-active="sidebar.opened" class="hamburger-container"/>
    <breadcrumb class="breadcrumb-container"/>

    <div class="right-menu">
    <template>

      <!-- <div class="right-menu-item aaa">
      <el-tooltip  effect="dark" content="访问github" placement="bottom">
            <svg-icon icon-class="github" class-name="icon"></svg-icon>
      </el-tooltip>
      </div>
      <div class="right-menu-item aaa">
            <svg-icon icon-class="wechat" class-name="icon" target="_blank" v-popover:weixinCode ></svg-icon>
            <el-popover
                ref="weixinCode"
                popper-class="qcodepopper"
                placement="bottom"
                trigger="hover">
                    <div class="img-area cflex">
                        <p class="titles">进入微信群</p>
                        <img :src="wechat" alt="加我微信"  />
                    </div>
            </el-popover>
      </div>
      <div class="right-menu-item aaa">
            <svg-icon icon-class="qq" class-name="icon" target="_blank" v-popover:qqCode ></svg-icon>
            <el-popover
                ref="qqCode"
                popper-class="qcodepopper"
                placement="bottom"
                trigger="hover">
                    <div class="img-area cflex">
                        <p class="titles">进入qq群</p>
                        <img :src="wechat" alt="加我qq"  />
                    </div>
            </el-popover>
      </div> -->
      <div class="right-menu-item aaa">
      <el-tooltip content="全屏" effect="dark" placement="bottom">
        <screenfull class="screenfull "/>
      </el-tooltip>
      </div>

      <!-- <div class="right-menu-item">
            <svg-icon icon-class="qq" class-name="icon" target="_blank" v-popover:ce ></svg-icon>
            <el-popover
                ref="ce"
                popper-class="qcodepopper"
                placement="bottom"
                trigger="hover">
                    <div >
                      <el-menu >
                            <el-menu-item  index="1-1">选项1</el-menu-item>
                            <el-menu-item index="1-2">选项2</el-menu-item>
                      </el-menu>
                    </div>
            </el-popover>
      </div>

            <div class="right-menu-item">
            <svg-icon icon-class="qq" class-name="icon" target="_blank" v-popover:xinxi ></svg-icon>
            <el-popover
                ref="xinxi"
                popper-class="qcodepopper"
                placement="bottom"
                trigger="hover">
                    <div >
                       <ul>
                         <li>个人信息</li>
                         <li>修改信息</li>
                         <li>退出</li>
                       </ul>
                    </div>
            </el-popover>
      </div> -->

      <div class="right-menu-item">
            <el-menu 
                class="el-menu-demo" 
                mode="horizontal" 
                >
                <el-submenu index="1" popper-class="langItem">
                    <template slot="title">
                        <img :src="langLogo" class='langAvatar' alt="">
                    </template>
                    <el-menu-item index="1-1" @click="changeLocale('zh')">
                         <img :src="chinaImg" class='langAvatar' alt="">
                        <span class="intro">中文</span>
                    </el-menu-item>
                    <el-menu-item index="1-2" @click="changeLocale('en')">
                         <img :src="americaImg" class='langAvatar' alt=""> 
                        <span class="intro">EngList</span>
                    </el-menu-item>
                </el-submenu>

                <el-submenu index="2"  popper-class="infoItem">
                    <template slot="title">
                        <div class='welcome'>
                            <span class="name">您好,</span>
                            <span class='name avatarname'> 管理员</span>
                        </div>
                        <img :src="avatar" class='avatar' alt="">
                    </template>
                    <el-menu-item index="2-1" @click="setDialogInfo('info')">个人信息</el-menu-item>
                    <el-menu-item index="2-2" @click="setDialogInfo('xiugai')">修改信息</el-menu-item>
                    <el-menu-item index="2-3" @click="setDialogInfo('logout')">退出</el-menu-item>
                </el-submenu>
            </el-menu>
      </div>


      <!-- <el-tooltip content="布局大小" effect="dark" placement="bottom">
        <size-select class="international right-menu-item"/>
      </el-tooltip> -->

    </template>
    </div>
  </div>

  
</template>

<script>
import { mapGetters } from 'vuex';
import bus from "../common/js/bus";
export default {
  components: {
   
  },
  computed: {
    ...mapGetters([
      'sidebar',
      'getAvatar'
    ])
  },

  data(){
    return{
    //  changeBarDirection:false,
    wechat:"../../../static/img/wechat.png",
    avatar:"",
    chinaImg:"../../../static/img/china.svg",
    americaImg:"../../../static/img/america.svg",
    langLogo:"../../../static/img/china.svg",
    }
  },
  created(){
    bus.$on('changeHead',val=>{
      console.log("jinruchangeHead")
      this.avatar = val;
    });
    this.showImg();
  },

   methods: {
    handleLefeMenu(){
      // this.changeBarDirection=!this.changeBarDirection;
      this.$store.dispatch('setLeftCollapse');  // 折叠菜单
      this.$store.dispatch('handleLeftMenu');  // 改变菜单宽度 200->50/50-200
    },
    changeLocale(type){
      if(type === 'en'){
          this.langLogo = this.americaImg;
      }else{
          this.langLogo = this.chinaImg;
      }
    },
    setDialogInfo(type){

      if(type=="info"){

      }
      if(type=="xiugai"){
        // 跳转到用户信息，传是否刷新的参数
        this.$router.push({path:'/views/user/userinfo'});
      }
      if(type=="logout"){
        let userName = sessionStorage.getItem("ms_username");
        //console.log(userName)
        // this.$http
        //   .post(this.api.removeSession, { userName: userName })
        //   .then(res => {});
        this.$http.post(this.api.logout).then(res => {});
        sessionStorage.removeItem("Token");
        sessionStorage.removeItem("userId");
        sessionStorage.removeItem("menuData");
        sessionStorage.removeItem("menuList");
        sessionStorage.removeItem("buttonUrlList");
        this.$router.push("/login");
      }
    },
    // 从缓存获取头像
    showImg() {
      //获取头像
      this.avatar=this.getAvatar
    },

  }

}
</script>
<style lang="scss" scoped>
.navbar{
  height: 60px;
  line-height: 60px;
  border-radius: 1px !important;
  border-bottom:solid 1px #e6e6e6;
}
.el-icon-s-fold{
  font-size: 30px;
}

.hamburger-container {
  line-height: 58px;
  height: 50px;
  float: left;
  padding: 5px 10px;
}
.breadcrumb-container{
  float: left;
}

  .right-menu {
    float: right;
    height: 100%;
    &:focus{
     outline: none;
    }
    .right-menu-item {
      display: inline-block;
      margin: 0 10px;
    }
    .screenfull {
      height: 20px;
    }
    .international{
      vertical-align: top;
    }
    .avatar-container {
      height: 50px;
      margin-right: 30px;
      .avatar-wrapper {
        cursor: pointer;
        margin-top: 5px;
        position: relative;
        .user-avatar {
          width: 40px;
          height: 40px;
          border-radius: 10px;
        }
        .el-icon-caret-bottom {
          position: absolute;
          right: -20px;
          top: 25px;
          font-size: 12px;
        }
      }
    }
  }
</style>
<style scoped>
  .icon {
    font-size: 20px;
    color: rgb(81, 195, 50);
    /* vertical-align:10px; */
}
.img-area{
    align-items: center;
}
.img-area .titles{
  font-size: 14px;
  background: #a9d86e;
  color:#fff;
  height: 30px;
  line-height: 30px;
  width:100px;
  text-align: center;
  border-radius: 20px;
}
.img-area img{
  width:100px;
  height:100px;
}
.cflex{
    display: flex;
    flex-direction: column;
}
.right-menu-item {
  height: 50px;
  float: left;
}
.right-menu-item .avatar{
    width: 32px;
    height: 32px;
    border-radius: 50%;
    vertical-align: middle;
    display: inline-block;
}
.userinfo {
    line-height: 60px;
    text-align:right;
}
    .avatar{
        width: 32px;
        height: 32px;
        border-radius: 50%;
        vertical-align: middle;
        display: inline-block;
    }
    .langAvatar{
        width: 24px;
        height: 24px;
        border-radius: 50%;
        vertical-align: middle;
        display: inline-block;
    }
    .welcome{
        display: inline-block;
        vertical-align: middle;
        padding: 0 5px;
    }
    .welcome .name{
        line-height: 20px;
        text-align: center;
        font-size: 12px;
    }
    .welcome .avatarname{
        color:#a9d86e;
        font-weight:bolder;
        font-size: 13px;
    }
    .aaa{
      padding-top: 17px;

    }

</style>
<style lang="less">
  .right-menu-item .el-submenu .el-icon-arrow-down{
    display: none;
  }
  .right-menu-item .el-menu--horizontal{
    border-bottom:solid 0px #e6e6e6;
  }
  .right-menu-item    .el-submenu__title{
      padding-left: 0px;
    }
</style>
