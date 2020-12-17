<template>
    <div class="app-main">
        <!-- 左侧菜单 -->
        <div class="left-main">
            <el-scrollbar class="right-body-scrollar">
            <sidebar></sidebar>
            </el-scrollbar>
        </div>
        <!-- 右侧 -->
        <div class="right-main">
            <!-- 右侧头部 -->
            <div class="right-header">

            </div>
            <!-- 右侧tab -->
            <div class="right-tab">
                <headTabs  :tableTabs="getOpenTab" :indexTab="getIndexTab"></headTabs>
            </div>
            <!-- 右侧主界面 -->
            <div class="right-body">
                <el-scrollbar class="right-body-scrollar">
                </el-scrollbar>
               
            </div>
        </div>
    </div>
</template>
<script>
import headTabs from '@comp/layout/otherLayoutCom/headTabs.vue';
import sidebar from '@comp/layout/otherLayoutCom/sidebar.vue';
import { mapGetters } from 'vuex';
export default {
    name:"text",
    components:{
        headTabs,
        sidebar
    },
    data(){
        return {

        }
    },
    mounted(){
        //  window.onresize = () =>{
        //     this.init()
        // }
        
    },
    computed:{
        ...mapGetters([
            'getOpenTab',
            'getIndexTab',
            'getLayoutType',
            'getLayoutParam',
            'getSiderParam',
        ])
    },
}
</script>
<style scss scoped>
.app-main{
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: row;
}
.left-main{
    width: 200px;
    height: 100%;
    background-color: #333744;
}
.right-main{
    width: 100%;
    height: 100%;
    background-color: #f3f3f3;
    display: flex;
    flex-direction: column;
    overflow: hidden;
}
.right-header{
    height: 50px;
    width: 100%;
    background-color: #ffffff;
    border-bottom: solid 1px #e6e6e6;
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
    width: 100%;
}

</style>