<template>
    <div class="app-main">
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
                <el-scrollbar class="right-body-scrollar">
                    <AppMain class="app-main"></AppMain>
                </el-scrollbar>
            </div>
        </div>

        <el-drawer :visible.sync="show" :with-header="false"  @close="closeDrawer" size="320px">
            <layoutSetting></layoutSetting>
        </el-drawer>
    </div>
</template>
<script>
import AppMain from '@comp/layout/otherLayoutCom/AppMain.vue';
import headTabs from '@comp/layout/otherLayoutCom/headTabs.vue';
import leftSide from '@comp/layout/otherLayoutCom/leftSide.vue';
import layoutHeaderCenter from '@comp/layout/otherLayoutCom/layoutHeaderCenter.vue';
import layoutHeaderRight from '@comp/layout/otherLayoutCom/layoutHeaderRight.vue';
import layoutSetting from '@comp/layout/otherLayoutCom/layoutSetting.vue';
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
.app-main{
    width: 100%;
    height: 100%;
    display: flex;
}
.left-main{
    height: 100%;
    background-color: #333744;
    overflow: hidden;
    transition: width 0.5s;
}
.right-main{
    width: 100%;
    height: 100%;
    background-color: #f3f3f3;
    flex: 1;
    flex-direction: column;
    overflow: hidden;
}
.right-header{
    height: 50px;
    width: 100%;
    background-color: #ffffff;
    border-bottom: solid 1px #e6e6e6;
    flex-wrap: nowrap;
    display: flex;
}
.right-tab{
    height: 40px;
    width: 100%;
    background-color: #ffffff;
}
.right-body{
    height: calc(100% - 95px);
    width: 100%;
    margin-top: 5px;
    /* background-color: royalblue; */
    
}
.right-body-scrollar{
    height: 100%;
}

</style>