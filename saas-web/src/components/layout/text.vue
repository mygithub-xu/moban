<template>
    <div class="layout-body">
        <!-- 左侧菜单 -->
        <template v-if="getLayoutType == '1'">
        <div class="left-main" :style="{'width':getSiderParam.menuWidth+'px'}">
                <leftSide></leftSide>
        </div>
        </template>
        <!-- 右侧 -->
        <div class="right-main">
            <!-- 右侧头部 -->
            <div class="right-header">
                <layoutHeaderCenter></layoutHeaderCenter>
                <layoutHeaderRight></layoutHeaderRight>
            </div>
            <!-- 右侧tab -->
            <div class="right-tab">
                <headTabs  :tableTabs="getOpenTab" :indexTab="getIndexTab"></headTabs>
            </div>
            <!-- 右侧主界面 -->
            <div class="right-body">
                <AppMain class="app-main"></AppMain>
            </div>
        </div>

        <el-drawer :visible.sync="show" :with-header="false"  @close="closeDrawer" size="320px">
            <layoutSetting></layoutSetting>
        </el-drawer>
    </div>
</template>
<script>
import AppMain from '@/components/layout/otherLayoutCom/AppMain.vue';
import headTabs from '@/components/layout/otherLayoutCom/headTabs.vue';
import leftSide from '@/components/layout/otherLayoutCom/leftSide.vue';
import layoutHeaderCenter from '@/components/layout/otherLayoutCom/layoutHeaderCenter.vue';
import layoutHeaderRight from '@/components/layout/otherLayoutCom/layoutHeaderRight.vue';
import layoutSetting from '@/components/layout/otherLayoutCom/layoutSetting.vue';
import { mapGetters } from 'vuex';
export default {
    name:"layouttext",
    components:{
        AppMain,
        headTabs,
        leftSide,
        layoutHeaderCenter,
        layoutHeaderRight,
        layoutSetting
    },
    data(){
        return {
            show : false
        }
    },
    mounted(){
        this.show = this.getDrawerState
    },
    computed:{
        ...mapGetters([
            'getOpenTab',
            'getIndexTab',
            'getLayoutType',
            'getLayoutParam',
            'getSiderParam',
            'getDrawerState'
        ])
    },
    watch:{
        getDrawerState(){
            this.show = this.getDrawerState
        }
    },
    methods:{
        closeDrawer(){
            this.$store.dispatch('changeDrawerStateFun',false);
        },
    }
}
</script>
<style scss scoped>
.layout-body{
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: row;
}
.left-main{
    height: 100%;
    background-color: #333744;
    overflow: hidden;
    transition: width 0.5s;
}
.right-main{
    height: 100%;
    background-color: #f3f3f3;
    flex: 1;
    overflow: hidden;
    display: flex;
    flex-direction: column;
}
.right-header{
    height: 50px;
    background-color: #ffffff;
    border-bottom: solid 1px #e6e6e6;
    flex-wrap: nowrap;
    display: flex;
}
.right-tab{
    height: 40px;
    background-color: #ffffff;
}
.right-body{
    display: flex;
    height: calc(100% - 101px);
}
.app-main{
    height: 100%;
    width: 100%;
}
</style>