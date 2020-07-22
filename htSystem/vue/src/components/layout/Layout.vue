<template>
<!-- 模板一 -->
  <div
    class="wrapper"
    @mousemove="walk()"
    v-loading="isShow"
    element-loading-text="数据加载中"
    element-loading-spinner="el-icon-loading"
    element-loading-background="rgba(0, 0, 0, 0.8)"
  >
 <!-- <div class="app-wrapper"> -->
   <el-container class="app-wrapper">
    <!-- <el-header>Header</el-header> -->
    <!-- 左 -->
    <el-container>
    <sidebar class="sidebar-container" :style="{width:sidebar.width+'px'}"/>
    <!-- 右 -->
    <div  :class="[sidebar.width==200?'main-container':'main-container-active']">
      <navbar/>
      <!-- 打开的面板记录 -->
      <!-- <tags-view/> -->
      <!-- 主页面 -->
      <app-main/>
      
    </div>
    </el-container>
    </el-container>
  <!-- </div> -->
  </div>
</template>

<script>
// 模板一
import Navbar from '../common/Navbar.vue';
import Sidebar from '../common/Sidebar.vue';
import AppMain from '../common/AppMain.vue';
import TagsView from '../common/TagsView.vue';
import { mapGetters } from 'vuex';
import { clearInterval } from "timers";

export default {
  name: 'Layout',
  components: {
    // 模板一
    Navbar,
    Sidebar,
    AppMain,
    TagsView

  },
  computed: {
    ...mapGetters([
      'sidebar',
    ])
  },
  data(){
      return {
        isShow: false,
        timer: null,
      }
  },
  created() {
    
  },
  mounted (){
  },
  watch:{
    
  },
  methods: {
    getWidthChage(){
      console.log(this.sidebar.width);
    },
    handleClickOutside() {
      this.$store.dispatch('closeSideBar', { withoutAnimation: false })
    },
    walk(){
      window.clearInterval(this.timer);
      this.time = 0;

      this.timer = setInterval(() => {
      this.time += 1;

      if(this.time>1000){

      if (!sessionStorage.getItem("Token")) {
        window.clearInterval(this.timer);
        return false;
      } 
      this.$alert("登录已超时，请重新登录", "操作提示", {
        confirmButtonText: "确定",
        callback: () => {
          this.$http.post(this.api.logout).then(res => {});
          this.$router.push("/login");
        }
      });
      
      sessionStorage.removeItem("Token");
      sessionStorage.removeItem("Token");
      sessionStorage.removeItem("userId");
      sessionStorage.removeItem("menuData");
      sessionStorage.removeItem("menuList");
      sessionStorage.removeItem("buttonUrlList");

      }

      },30000);
    }
  }

}
</script>
<style  lang="scss" scoped>

  .el-container{
    position: relative;
    height: 100%;
    width: 100%;
    &.mobile.openSidebar{
      position: fixed;
      top: 0;
    }
  }
  .main-container {
    width: calc(100% - 200px);
  }
  .main-container-active {
    width: calc(100% - 50px);
  }
  .app-wrapper{
    height: 100%;
  }
  // .drawer-bg {
  //   opacity: 0.3;
  //   width: 100%;
  //   top: 0;
  //   height: 100%;
  //   position: absolute;
  //   z-index: 999;
  // }
  .sidebar-container{
    background-color: #333744;
  }
</style>