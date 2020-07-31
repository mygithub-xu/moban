<template>
 <div >
   
   <template v-if="layoutType == '1'">
      <el-menu
            :background-color="bgcolor"
            :text-color="textColor"
            :active-text-color="activeTextColor"
            :show-timeout="200"
            unique-opened
            router
            :collapse="isCollapse"
            :default-active="$route.path">
            <div>
              <template v-for="item in homePageData">
                <template>    
                    <el-menu-item :index="item.url" :key="item.id">
                    <i :class="item.icon" style="font-size: 18px;width:20px"> </i>
                    <span slot="title"  style="font-size: 13px;margin-left:7px">{{item.menuName}}</span>
                    </el-menu-item>
                </template>
              </template>
            </div>
                <menuTree2 :menuData="menuData"></menuTree2>
        </el-menu>
    </template>

    <template v-if="layoutType == '2'">
    <div class="horizontal-menu">
            <el-menu
              :background-color="bgcolor"
              :text-color="textColor"
              :active-text-color="activeTextColor"
              :show-timeout="200"
               mode="horizontal"
              router
              :default-active="$route.path">
            
              <template v-for="item in homePageData">
                    <el-menu-item :index="item.url" :key="item.id">
                    <span slot="title"  style="font-size: 13px;margin-left:7px">{{item.menuName}}</span>
                    </el-menu-item>
              </template>

              <template v-for="item in menuData"  >
                <template v-if="!item.children">
                  
                  <el-menu-item  :index="item.url" :key="item.url">{{item.menuName}}</el-menu-item>
                </template>
                <template v-else>

                  <el-submenu :index="item.url" :key="item.url" style="height: 50px;line-height: 50px;">
                  <template slot="title">{{item.menuName}}</template>
                  <menuTree2Hor :menuData="item.children"></menuTree2Hor>
                  </el-submenu>

                </template>
              </template>
              
              
              <!-- <el-menu-item index="1">处理中心</el-menu-item>
              <el-submenu index="2" style="height: 50px;line-height: 50px;">
                  <template slot="title">我的工作台</template>
                  <el-menu-item index="2-1">选项1</el-menu-item>
                  <el-menu-item index="2-2">选项2</el-menu-item>
                  <el-menu-item index="2-3">选项3</el-menu-item>
                  <el-submenu index="2-4">
                        <template slot="title">选项4</template>
                        <el-menu-item index="2-4-1">选项1</el-menu-item>
                        <el-menu-item index="2-4-2">选项2</el-menu-item>
                        <el-menu-item index="2-4-3">选项3</el-menu-item>
                  </el-submenu>
              </el-submenu>
              <el-menu-item index="3" disabled>消息中心</el-menu-item>
              <el-menu-item index="4"><a href="https://www.ele.me" target="_blank">订单管理</a></el-menu-item> -->

            </el-menu>

        </div>
    </template>

    </div>
</template>

<script>


export default {
  name:"sidebar2",
   data() {
      return {
        activeMenu:"",

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
        layoutType:{
          type: String,
          default: "1"
        },
        width: {
          type: String,
          default: ""
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
        textColor:{
          type: String,
          default: "#409EFF"
        },
        activeTextColor:{
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
      console.log(this.menuData);
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

.horizontal-menu .el-menu--horizontal{
  height: 50px;
  overflow: hidden;
}
.horizontal-menu .el-menu-item{
  height: 50px;
  line-height: 50px;
}
.horizontal-menu .el-submenu{
    height: 50px;
    line-height: 50px;
}
.horizontal-menu .el-submenu__title{
        height: 50px;
    }
.horizontal-menu .el-menu--horizontal>.el-submenu .el-submenu__title{
    height: 50px!important;
    line-height: 50px!important;
}

    //设置鼠标悬停时el-menu-item的样式
//  .horizontal-menu .el-menu-item:hover{
//     background-color: #E2EFF9 !important;
//     color: #38B2FF !important;
//     }
</style>
<style>

.horizontal-menu .el-menu--horizontal>.el-submenu .el-submenu__title{
  height: 50px!important;
  line-height: 50px!important;
} 
</style>