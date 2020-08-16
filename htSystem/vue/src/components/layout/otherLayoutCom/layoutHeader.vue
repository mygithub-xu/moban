<template>
    <div class="layout-header" id="layout-header" :style="{'background-color':layoutTypeParam.headerBgcolor}">
        
        <template v-if="layoutType=='1'">
            <div class="layout-header-title" :style="{'width':layoutTypeParam.headWidth+'px','background-color':layoutTypeParam.menuBgcolor}">
                <span  :style="{'width':layoutTypeParam.headWidth+'px','color':layoutTypeParam.menuFontcolor}">
                    <img :src="companyAtt.imgSrc" class="layout-header-title-img"/>
                    <template v-if="!layoutTypeParam.isCollapse">{{companyAtt.name}}</template>
                </span>
            </div>
            <div class="layout-header-left" >
                    <span class="layout-header-left-icon">
                        <i class="icon iconfont" :class="layoutTypeParam.isCollapse?'icon-zhedie2':'icon-zhedie1'" @click="changeWidth"></i>
                    </span>
            </div>
            <slot name="rightHeader"></slot>
        </template>
        
        <template v-if="layoutType=='2'">
            <div class="layout-header-title" :style="{'width':layoutTypeParam.headWidth+'px'}">
                <span  :style="{'width':layoutTypeParam.headWidth+'px','color':layoutTypeParam.menuFontcolor}">
                    <img :src="companyAtt.imgSrc" class="layout-header-title-img"/>
                    <template>{{companyAtt.name}}</template>
                </span>
            </div>

            <div class="layout-header-left" :style="{'width':layoutTypeParam.menuWidth+'px'}" >
                    <!-- 菜单 -->
                    <sidebar class="menu_container2"  :bgcolor="layoutTypeParam.headerBgcolor" 
                    :layoutType="layoutType" :textColor="layoutTypeParam.menuFontcolor" :activeTextColor="layoutTypeParam.menuActiveFontcolor"
                    :menuWidth="layoutTypeParam.menuWidth"
                    ></sidebar>
            </div>
            <slot name="rightHeader"></slot>
        </template>
    </div>
</template>
<script>
export default {
    name: 'layoutHeader',
    data(){
        return {

        }
    },
    props:{
        layoutType:{
            type:String,
            default:"1"
        },
        layoutTypeParam:{
            type:Object,
            require:true
        },
        companyAtt:{
            type:Object,
            require:true
        },
    },
    methods:{
        changeWidth(){
            this.$emit("changeWidth")
        }
    }
}
</script>
<style scoped>
    .layout-header{
        height: 50px;
        /* border-bottom: solid 1px #e6e6e6; */
    }
    .layout-header-title{
        height: 50px;
        text-align: center;
        float: left;
        overflow: hidden;
        transition: width 0.5s;
    }
    .layout-header-title span{
        font-size: 23px;
        color: #ffffff;
        float: left;
        line-height: 50px;
    }
    .layout-header-title-img{
        width:32px;
        height:32px;
        float:left;
        margin-top: 9px;
        margin-left: 15px;
    }
    .layout-header-left{
        height: 50px;
        float: left;
    }
    .layout-header-left-icon i{
        font-size: 25px;
        line-height: 50px;
        margin-left: 15px;
    }
    .menu_container2{
        float: left;
        height: 50px;
        text-align: center;
        overflow: hidden;
    }
</style>