<template>
    <div class="layout-header-title" :style="{'width':bodyWidth+'px','background-color':bodyBGColor}">
        <span  :style="{'color':bodyFontColor}">
            <!-- <img :src="getCompanyAtt.imgSrc" class="layout-header-title-img"/> -->
            <template v-if="!getSiderParam.isCollapse">{{getCompanyAtt.name}}</template>
        </span>
    </div>
</template>
<script>
import { mapGetters } from 'vuex';
export default {
    name:"layoutHeaderLeft",
    data(){
        return {
            
        }
    },
    computed:{
        ...mapGetters([
            'getLayoutType',
            'getLayoutParam',
            'getSiderParam',
            'getCompanyAtt'
        ]),
        bodyWidth(){
            if(this.getLayoutType=='2'){
                return 200
            }
            return this.getSiderParam.menuWidth
        },
        bodyBGColor(){
            if(this.getLayoutType=='2'){
                return this.getLayoutParam.headerBGColor
            }
            return this.getLayoutParam.siderBGColor
        },
        bodyFontColor(){
            if(this.getLayoutType=='2'){
                return this.getLayoutParam.headerFontColor
            }
            return this.getLayoutParam.siderFontColor 
        }

    },
    methods:{

    }
}
</script>
<style scoped>
    .layout-header-title{
        height: 50px;
        text-align: center;
        float: left;
        overflow: hidden;
        transition: width 0.5s;
    }
    .layout-header-title span{
        width: 100%;
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
</style>