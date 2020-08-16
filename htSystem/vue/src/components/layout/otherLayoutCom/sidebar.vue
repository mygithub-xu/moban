<template>
 <div >
   <template v-if="layoutType == '1'">
     <el-scrollbar class="menu-scrollbar">
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
                <menuTree :menuData="menuData"></menuTree>
        </el-menu>
      </el-scrollbar >
    </template>

    <template v-if="layoutType == '2'">
    <div class="horizontal-menu" id="horizontal-menu">
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


export default {
  name:"sidebar",
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
        },
        menuWidth:{
          type:Number,
          default:200,
        }
  },
  watch:{
    menuWidth(newValue, oldValue){
      
      this.showMore();
    }
  },
  created(){
    this.getMenu();
    if(this.layoutType=='2'){
      this.showMore();
    }    
  },
  methods: {
   getMenu(){
      this.menuData = JSON.parse(sessionStorage.getItem("menuData"));
   },
   showMore(){
     
     this.$nextTick(()=>{
      
      this.canUserWidth=this.menuWidth-70;
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

      this.isShowMore=false;
      this.menuData=JSON.parse(sessionStorage.getItem("menuData"));
      this.menuDataMore=[];
      if(index>0){
        this.isShowMore=true;
        let allData= JSON.parse(sessionStorage.getItem("menuData"));
        this.menuData=allData.slice(0,index);
        this.menuDataMore=allData.slice(index);

      }

      if(index==0){
        this.isShowMore=true;
        this.menuDataMore=JSON.parse(sessionStorage.getItem("menuData"));
        this.menuData=[]
      }

     })

   }

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

    //设置鼠标悬停时el-menu-item的样式
//  .horizontal-menu .el-menu-item:hover{
//     background-color: #E2EFF9 !important;
//     color: #38B2FF !important;
//     }

.menu-scrollbar{
  height: 100%;
  width: 100%;
}
</style>
<style>

.horizontal-menu .el-menu--horizontal>.el-submenu .el-submenu__title{
  height: 50px!important;
  line-height: 50px!important;
} 
</style>