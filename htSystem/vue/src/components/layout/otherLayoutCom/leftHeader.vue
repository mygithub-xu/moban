<template>
            <div class="layout-header-title" :style="{'width':layoutType1Param.headWidth+'px','background-color':layoutType1Param.menuBgcolor}">
                <span  :style="{'width':layoutType1Param.headWidth+'px','color':layoutType1Param.menuFontcolor}">
                    <img :src="companyAtt.imgSrc" class="layout-header-title-img"/>
                    <template v-if="!layoutType1Param.isCollapse">{{companyAtt.houtaiName}}</template>
                </span>
            </div>
</template>
<script>
export default {
    name: 'leftHeader',
    data(){
        return {
        }
    },
    props:{
        layoutType:{
            type:Object,
            required:true
        },
        companyAtt:{
            type:Object,
            required:true
        },

    },
    methods:{


    }
}
</script>
<style scoped>

</style>