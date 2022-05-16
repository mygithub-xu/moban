<template>
        <div class="drawer-body">

            <div class="common-drawer-item">
                <h4>布局模式:</h4>
                <el-radio-group v-model="layoutType" @change="typeClick()" style="margin-top:20px">
                    <el-radio :label="'1'">左右布局</el-radio>
                    <el-radio :label="'2'"  style="margin-left:20px">上下布局</el-radio>
                </el-radio-group>
            </div>

            <!-- <div class="common-drawer-item">
                <div style="width:100%">
                    <h4>系统推荐风格:</h4>
                </div>
               <div style="width:100%;margin-top:10px">
                    <el-select v-model="getSysType.modelType" placeholder="请选择" size="small">
                        <el-option
                        v-for="item in getSysType.modeTypeList"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value">
                        </el-option>
                    </el-select>
                </div>
            </div> -->
            <!-- <div class="common-drawer-item">
                <div style="width:100%">
                    <el-switch v-model="value1" active-text="自定义风格"> </el-switch>
                </div>

            </div> -->

            <div  class="common-drawer-item">
                <h4>颜色:</h4>
                <div class="sider-menu-color">
                    <div class="sider-menu-color-item" v-for="(item,index) in getTopMenuColorList" :key="index" :style="{'background-color':item}" @click="changeSystemColor(item)">
                            <i v-if="getLayoutParam.siderBGColor==item" class="icon iconfont icon-queding" :style="{'color':item=='#ffffff'?'#000000':'#ffffff'}"></i>
                    </div>
                </div>
            </div>

            <!-- <template v-if="getLayoutType=='1'">
            <div  class="common-drawer-item">
                <h4>侧边菜单颜色:</h4>
                <div class="sider-menu-color">
                    <div class="sider-menu-color-item" v-for="(item,index) in getSiderMenuColorList" :key="index" :style="{'background-color':item}" @click="changeMenuColor(item)">
                            <i v-if="getLayoutParam.siderBGColor==item" class="icon iconfont icon-queding" :style="{'color':item=='#ffffff'?'#000000':'#ffffff'}"></i>
                    </div>
                </div>
            </div>
            </template>
            <div class="common-drawer-item">
                <h4>顶部导航栏颜色:</h4>
                <div class="sider-menu-color">
                    <div class="sider-menu-color-item" v-for="(item,index) in getTopMenuColorList" :key="index" :style="{'background-color':item}" @click="changeTopColor(item)">

                    <i v-if="getLayoutParam.headerBGColor==item" class="icon iconfont icon-queding" :style="{'color':item=='#ffffff'?'#000000':'#ffffff'}"></i>

                    </div>
                </div>
            </div> -->

        </div>


</template>

<script>
import { mapGetters } from 'vuex';
export default {
  name: 'layoutSetting',
  data(){
      return {
          layoutType:"1",
          value1:true
      }
  },
  computed: {
        ...mapGetters([
            'getLayoutType',
            'getLayoutParam',
            'getModelType',
            'getSiderParam',
            'getSiderMenuColorList',
            'getTopMenuColorList',
            'getSysType'
        ])
  },
  props:{

  },
  created(){
      this.layoutType=this.getLayoutType
  },
  methods:{
      typeClick(){
          this.$store.dispatch('changeLayoutTypeFun',this.layoutType);
      },
      changeSystemColor(item){
          if(item!=this.getLayoutParam.siderBGColor){

              this.$store.dispatch('changeSystemBGColorFun',item);
          }
      },
      changeMenuColor(item){
          if(item!=this.getLayoutParam.siderBGColor){
              var siderBGColor={
                  name:'siderBGColor',
                  value:item
              }
              this.$store.dispatch('changeLayoutParamFun',siderBGColor);
          }
      },
      changeTopColor(item){
          if(item!=this.getLayoutParam.headerBGColor){
                var headerBGColor={
                  name:'headerBGColor',
                  value:item
                }
              this.$store.dispatch('changeLayoutParamFun',headerBGColor);
          }
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