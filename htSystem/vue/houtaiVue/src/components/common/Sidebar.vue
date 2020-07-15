<template>
 <div class="menu_container">
 <el-menu
      background-color="#333744"
      text-color="#fff"
      active-text-color="#409EFF"
      :show-timeout="200"
      unique-opened
      router
      :collapse="isCollapse"
      :style="{width:sidebar.width+'px'}"
      :default-active="$route.path">
      <div>
        <template v-for="item in homePageData">
          <!-- <template slot="title" v-if="item.children" >
               <el-menu-item :index="'/'+item.url" :key="item.id">
                    <template slot="title">
                        <i :class="item.icon"></i>
                        <span slot="title">{{ item.menuName }}</span>
                    </template>
              </el-menu-item>

          </template> -->

          <template>    
              <el-menu-item :index="item.url" :key="item.id">
              <i :class="item.icon"></i>
              <span slot="title">{{item.menuName}}</span>
              </el-menu-item>
          </template>
        </template>
      </div>
          <menuTree :menuData="menuData"></menuTree>
    </el-menu>
    </div>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
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
  watch: {
      // $route(to, from) {
      //   this.$nextTick(() => {
      //     this.activeMenu=this.$route.path;
      //     console.log(this.activeMenu)
      //   })
      // }
  },
  computed:{
     ...mapGetters([
        'isCollapse',
        'sidebar',
        'menuIndex'
      ]),
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

</style>