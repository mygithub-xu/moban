<template>
 <div :class="[getLayoutType == '1'?'siderMenu':'horMenu']">
    <div class="sys-name" :style="{'width':getSiderParam.menuCloseWidth+'px','background-color':getLayoutParam.headerBGColor}">
      <span v-if="getSiderParam.isCollapse||getLayoutType == '2'" :style="{'color':getLayoutParam.headerFontColor}">x</span>
      <span v-else>xxx系统 </span>
    </div>
   <template v-if="getLayoutType == '1'">
    <el-scrollbar class="menu-scrollbar" :style="{'width':getSiderParam.menuWidth+'px','background-color':getLayoutParam.siderBGColor}" >
      <el-menu
            :background-color="getLayoutParam.siderBGColor"
            :text-color="getLayoutParam.siderFontColor"
            :active-text-color="getLayoutParam.activeFontColor"
            :show-timeout="200"
            unique-opened
            router
            :collapse="getSiderParam.isCollapse"
            :default-active="$route.path">
            <div>
              <template v-for="item in homePageData">
                <template>    
                    <el-menu-item :index="item.url" :key="item.id" >
                    <i :class="item.icon" style="font-size: 18px;width:20px"> </i>
                    <span slot="title"  style="font-size: 13px;margin-left:7px">{{item.menuName}}</span>
                    </el-menu-item>
                </template>
              </template>
            </div>
                <menuTree :menuData="menuData"></menuTree>
        </el-menu>
      </el-scrollbar >
    </template>

    <template v-if="getLayoutType == '2'">
    <div class="horizontal-menu" id="horizontal-menu" >
            <el-menu
              :background-color="getLayoutParam.headerBGColor"
              :text-color="getLayoutParam.headerFontColor"
              :active-text-color="getLayoutParam.activeFontColor"
              :show-timeout="200"
               mode="horizontal"
              router
              :default-active="$route.path">
            
              <template v-for="item in homePageData">
                    <el-menu-item :index="item.url" :key="item.id" >
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
                    <menuTreeHor :menuData="item.children"></menuTreeHor>
                  </el-submenu>

                </template>
              </template>

              <el-submenu index="more" key="more"  v-if="isShowMore" style="height: 50px;line-height: 50px;width:70px">
                <template slot="title">更多</template>
                <menuTreeHor :menuData="menuDataMore"></menuTreeHor>
              </el-submenu>

            </el-menu>

        </div>
    </template>

    </div>
</template>

<script>
import menuTreeHor from '@comp/layout/otherLayoutCom/menuTreeHor.vue';
import menuTree from '@comp/layout/otherLayoutCom/menuTree.vue';
import { mapGetters } from 'vuex';
export default {
  name:"sidebar",
    components:{
        menuTree,
        menuTreeHor
    },
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
        menuDataMore:[],

        //判断是否超出长度
        isShowMore:false,
        canUserWidth:200,
      };
    },
    computed:{
        ...mapGetters([
            'getLayoutType',
            'getLayoutParam',
            'getSiderParam',
        ]),
    },
    props:{
        headerCenterWidth:{
            type:Number,
            default:600
        },
    },
    watch:{
        headerCenterWidth(newData, oldData){
          this.getCenterWith()
        }
    },
  created(){
    this.getMenu();
  },
  methods: {
   getMenu(){
      //获取菜单数据
      this.menuData = this.getMenuData()
   },
   getCenterWith(){
    if(this.getLayoutType=='2'){
      this.showMore();
    } 
   },
   showMore(){
     
     this.$nextTick(()=>{

          this.isShowMore=false;
          this.menuData = this.getMenuData();
          this.menuDataMore=[];


          this.canUserWidth=this.headerCenterWidth-70;
          //menu总长度
          let allMenu=document.getElementById("horizontal-menu");

          //过滤出显示的每个菜单节点
          let menuParents=allMenu.getElementsByTagName("ul");
          let menuItemList=menuParents[0].children;

          //如果超过menu总长度，那么记录时第几个。
          let index=-1;
          let allItemWidth=0;
          
          for(let i=0;i<menuItemList.length;i++){
            allItemWidth=allItemWidth+menuItemList[i].clientWidth;
            if(allItemWidth>this.canUserWidth){
              index=i-1;
              break;
            }
          }

          if(index>0){
            this.isShowMore=true;
            let allData= this.getMenuData();
            this.menuData=allData.slice(0,index);
            this.menuDataMore=allData.slice(index);
          }

          if(index==0){
            this.isShowMore=true;
            this.menuDataMore=this.getMenuData();
            this.menuData=[]
          }

     })

   },

   //获取菜单数据
   getMenuData(){
     
     return this.$User.getMenuData()
   }

  }
}
</script>
<style lang="scss" scoped>
.siderMenu{
  height: 100%;
  float: left;
  border-right: solid 1px #e6e6e6;
  overflow: hidden;
  transition: width 0.5s; 
}
.menu-scrollbar{
  height: 100%;
  transition: width 0.5s; 
}
.horMenu{
  height: 50px;
  display: flex;
}
.el-menu{
  border-right: solid 0px #e6e6e6;
}
.el-menu--collapse{
  width: 100%;
}
.horizontal-menu{
    flex: 1;
    flex-direction: column;
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
.sys-name{
  width: 100%;
  line-height: 50px;
  font-size: 20px;
  color: aliceblue;
  text-align: center;
}
</style>
<style>

.horizontal-menu .el-menu--horizontal>.el-submenu .el-submenu__title{
  height: 50px!important;
  line-height: 50px!important;
} 
.siderMenu .el-scrollbar__wrap{
  overflow-x: hidden;
}

</style>