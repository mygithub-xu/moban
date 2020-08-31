<template>
        <div class="layout-header-center" :style="{'width':getLayoutType=='1'?100:headerCenterWidth+'px'}">
            <template v-if="getLayoutType=='1'">
                <span class="layout-header-center-icon" :style="{'color':getLayoutParam.headerFontColor}">
                    <i class="icon iconfont" :class="getSiderParam.isCollapse?'icon-zhedie2':'icon-zhedie1'" @click="changeWidth"></i>
                </span>
            </template>
            <template v-if="getLayoutType=='2'">
                <sidebar class="menu_container2"  :headerCenterWidth="headerCenterWidth"></sidebar>
            </template>
        </div>
</template>
<script>
import { mapGetters } from 'vuex';
import sidebar from '@comp/layout/otherLayoutCom/sidebar.vue';
export default {
    name:"layoutHeaderCenter",
    components:{
        sidebar
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
        float: left;
        overflow: hidden;
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