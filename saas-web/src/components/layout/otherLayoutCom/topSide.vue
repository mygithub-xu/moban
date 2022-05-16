<template>
    <div class="horMenu">
        <div class="sys-name" :style="{'width':getSiderParam.menuCloseWidth+'px','background-color':getLayoutParam.headerBGColor}">
            <span  :style="{'color':getLayoutParam.headerFontColor}">x</span>
        </div>

        <div class="horizontal-menu" id="horizontal-menu" >
            <el-menu
              :background-color="getLayoutParam.headerBGColor"
              :text-color="getLayoutParam.headerFontColor"
              :active-text-color="getLayoutParam.activeFontColor"
              :show-timeout="200"
               mode="horizontal"
              router
              :default-active="$route.path">

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
    </div>
</template>
<script>
import menuTreeHor from '@/components/layout/otherLayoutCom/menuTreeHor.vue';
import { mapGetters } from 'vuex';
export default {
    name:"topSide",
    components:{
        menuTreeHor
    },
    data() {
      return {
        activeMenu:"",
        menuDataMore:[],

        //判断是否超出长度
        isShowMore:false,
        canUserWidth:200,
        headerCenterWidth:200
      };
    },
    computed:{
        ...mapGetters([
            'getLayoutParam',
            'getSiderParam'
        ]),
        menuData(){
            return this.$User.getMenuData()
        }
    },
    mounted(){
        window.onresize = () =>{
            if(this.getLayoutType == '2'){
                //获取layout-header-center的宽度
                this.showMore()
                this.headerCenterWidth = document.getElementById('layout-header-right').clientWidth;
            }
        }
    },
    methods:{
        showMore(){
            this.$nextTick(()=>{
                this.isShowMore=false;
                this.menuData = this.getMenuData();
                this.menuDataMore=[];

                this.headerCenterWidth = document.getElementById('layout-header-right').clientWidth;
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
        }
    }
}
</script>
<style scoped>
.horMenu{
    width: 100%;
    height: 50px;
    display: flex;
}
.sys-name{
    width: 100%;
    line-height: 50px;
    font-size: 20px;
    text-align: center;
}
</style>