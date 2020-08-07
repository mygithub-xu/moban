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

import bus from "../../../assets/js/bus";
export default {
    name:"navbar2",
  data(){
    return{
    //  changeBarDirection:false,
    avatar:"",

    }
  },
  created(){
    bus.$on('changeHead',val=>{
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
<style scoped>

</style>