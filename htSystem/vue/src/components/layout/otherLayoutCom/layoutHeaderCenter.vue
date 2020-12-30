<template>
        <div class="layout-header-center">
            <template v-if="getLayoutType=='1'">
                <span class="layout-header-center-icon" :style="{'color':getLayoutParam.headerFontColor}">
                    <i class="icon iconfont" :class="getSiderParam.isCollapse?'icon-zhedie2':'icon-zhedie1'" @click="changeWidth"></i>
                </span>
            </template>
            <template v-if="getLayoutType=='2'">
                <topSide></topSide>
            </template>
        </div>
</template>
<script>
import { mapGetters } from 'vuex';
import topSide from '@comp/layout/otherLayoutCom/topSide.vue';
export default {
    name:"layoutHeaderCenter",
    components:{
        topSide
    },
    data(){
        return {

        }
    },
    computed:{
        ...mapGetters([
            'getLayoutParam',
            'getLayoutType',
            'getSiderParam'
        ]),

    },
    props:{
        headerCenterWidth:{
            type:Number,
            default:100
        },
    },
    methods:{
        changeWidth(){
            let isCollapse=this.getSiderParam.isCollapse
            this.$store.dispatch('changeSiderParamFun',!isCollapse);
        }
    }
}
</script>
<style scoped>
    .layout-header-center{
        height: 50px;
        overflow: hidden;
        flex: 1;
    }
    .layout-header-center-icon i{
        font-size: 25px;
        line-height: 50px;
        margin-left: 15px;
        cursor: pointer;
    }
    .menu_container2{
        float: left;
        height: 50px;
        text-align: center;
        overflow: hidden;
    }
</style>