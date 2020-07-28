<template>
 <div>
 <el-menu
      :background-color="bgcolor"
      text-color="#fff"
      :active-text-color="activeText"
      :show-timeout="200"
      unique-opened
      router
      :collapse="isCollapse"
      :style="width"
      :default-active="$route.path">
      <div>
        <template v-for="item in homePageData">
          <template>    
              <el-menu-item :index="item.url" :key="item.id">
              <i :class="item.icon" style="font-size: 18px;width:20px"></i>
              <span slot="title"  style="font-size: 13px;margin-left:7px">{{item.menuName}}</span>
              </el-menu-item>
          </template>
        </template>
      </div>
          <menuTree :menuData="menuData"></menuTree>
    </el-menu>
    </div>
</template>

<script>


export default {
  name:"sidebar2",
   data() {
      return {
        activeMenu:"",
        menuObj:{
          bgColor:'#fff',
          textColor:'#666',
          activeTextColor:'#ff6428',
        },
        homePageData:[
          {
          "icon": "el-icon-setting",
          "url": "/page/Dashboard",
          "menuName": "系统首页"
        },
        ],
        menuData:[],
      };
    },
    props:{
        width: {
          type: String,
          default: "下拉树选择框"
        },
        isCollapse:{
          type: Boolean,
          default: false
        },
        bgcolor:{
          type: String,
          default: "#333744"
        },
        isCollapse:{
          type: Boolean,
          default: false
        },
        activeText:{
          type: String,
          default: "#409EFF"
        }
    },
  computed:{

  },
  created(){
    this.getMenu();

  },
  methods: {
   getMenu(){
      this.menuData = JSON.parse(sessionStorage.getItem("menuData"))
   },
  }
}
</script>
<style lang="scss" scoped>
.el-menu{
  border-right: solid 0px #e6e6e6;
}
.el-menu--collapse{
  width: 100%;
}
</style>