<template>
    <div class="layout-body">
        <template v-if="getLayoutType=='1'">
            <sidebar></sidebar>
        </template>

        <AppMain class="app-main"  :style="{'width': 'calc(100% - ' +appMainWidth+'px)'}">
            <!-- 面包屑 -->
            <slot>
                <div class="mainTopDiv">
                    <headTabs  :tableTabs="getOpenTab" :indexTab="getIndexTab"></headTabs>
                </div>
            </slot>

        </AppMain>
    </div>
</template>
<script>
import { mapGetters } from 'vuex';
import AppMain from '@comp/layout/otherLayoutCom/AppMain.vue';
import sidebar from '@comp/layout/otherLayoutCom/sidebar.vue';
import headTabs from '@comp/layout/otherLayoutCom/headTabs.vue';
export default {
    name: 'layoutBody',
    components:{
        sidebar,
        headTabs,
        AppMain
    },
    data(){
        return {

        }
    },
    computed:{
        ...mapGetters([
            'getOpenTab',
            'getIndexTab',
            'getLayoutType',
            'getLayoutParam',
            'getSiderParam',
        ]),
        appMainWidth(){
            if(this.getLayoutType=='1'){
                return this.getSiderParam.menuWidth+1
            }
            return 0;
        }
    },
    props:{
    },
    methods:{
        
    }
}
</script>
<style scoped>
    .layout-body{
        height: calc(100% - 50px);
        width: 100%;
    }
    .app-main {
        height: 100%;
        overflow: hidden;
        float: left;
        background-color: #f0f0f0;
        transition: width 0.5s;
    }
    .mainTopDiv{
        height: 48px;
        width: 100%;
        float: left;
        background-color: #ffffff;
        border-top: 1px solid #f0f0f0;
        border-bottom: 1px solid #f0f0f0;
    }
</style>