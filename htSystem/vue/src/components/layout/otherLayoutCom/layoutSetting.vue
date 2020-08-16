<template>
        <div class="drawer-body">

            <div class="common-drawer-item">
                <h4>布局模式:</h4>
                <el-radio-group v-model="typeClicktrue" @change="typeClick()" style="margin-top:20px">
                    <el-radio :label="'1'">左右布局</el-radio>
                    <el-radio :label="'2'">上下布局</el-radio>
                </el-radio-group>
            </div>

            <template v-if="typeClicktrue=='1'">
            <div   class="common-drawer-item">
                <h4>侧边菜单颜色:</h4>
                <div class="sider-menu-color">
                    <div class="sider-menu-color-item" v-for="(item,index) in siderMenuColorList" :key="index" :style="{'background-color':item}" @click="changeMenuColor(item)">
                            <i v-if="layoutType1Param.menuBgcolor==item" class="icon iconfont icon-queding" :style="{'color':item=='#ffffff'?'#000000':'#ffffff'}"></i>
                    </div>
                </div>
            </div>
            </template>
            <div class="common-drawer-item">
                <h4>顶部导航栏颜色:</h4>
                <div class="sider-menu-color">
                    <div class="sider-menu-color-item" v-for="(item,index) in TopMenuColorList" :key="index" :style="{'background-color':item}" @click="changeTopColor(item)">
                        <template v-if="typeClicktrue=='1'">
                            <i v-if="layoutType1Param.headerBgcolor==item" class="icon iconfont icon-queding" :style="{'color':item=='#ffffff'?'#000000':'#ffffff'}"></i>
                        </template>
                        <template v-if="layoutType=='2'">
                            <i v-if="layoutType2Param.headerBgcolor==item" class="icon iconfont icon-queding" :style="{'color':item=='#ffffff'?'#000000':'#ffffff'}"></i>
                        </template>
                    </div>
                </div>
            </div>

        </div>


</template>

<script>
export default {
  name: 'layoutSetting',
  data(){
      return {
          typeClicktrue:"1"
      }
  },
  props:{
      layoutType:{
          type:String,
          default:"1"
      },
      siderMenuColorList:{
          type:Array,
          default:[]
      },
      TopMenuColorList:{
          type:Array,
          default:[]
      },
      layoutType1Param:{
          type:Object,
          require: true 
      },
      layoutType2Param:{
          type:Object,
          require: true
      }
  },
  created(){
      this.typeClicktrue=this.layoutType
  },
  watch:{
    layoutType(newValue, oldValue){
      this.typeClicktrue=newValue
    }
  },
  methods:{
      typeClick(){
          this.$emit("typeClick",this.typeClicktrue);
      },
      changeMenuColor(item){
          this.$emit("changeMenuColor",item);
      },
      changeTopColor(item){
          this.$emit("changeTopColor",item);
      }
  }

}
</script>

<style lang="scss" scoped>
    .drawer-body{
        width: 100%;
        height: 100%;
        padding: 24px 10px;
        font-size: 14px;
        line-height: 1.5;
        word-wrap: break-word;
    }
    .common-drawer-item{
        margin: 20px 0;
        width: 100%;
        float: left;
    }
    .sider-menu-color{
        width: 100%;
        min-height: 50px;
        float: left;
    }
    .sider-menu-color-item{
        width: 24px;
        height: 24px;
        float: left;
        margin-left: 10px;
        margin-top: 5px;
        border-radius: 50%;
        border: 1px solid rgb(238, 238, 238);
        text-align: center;
        position: relative;
    }
    .sider-menu-color-item .icon-queding{
        position:absolute;
        top: 5px;
        right: 4px;
    }
</style>