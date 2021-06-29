<template>
    <div class="siderMenu" :style="{'width':getSiderParam.menuWidth+'px','background-color':getLayoutParam.siderBGColor}">
        <div class="sys-name" :style="{'color':getLayoutParam.siderFontColor}">
            <span v-if="getSiderParam.isCollapse">x</span>
            <span v-else>xxx系统 </span>
        </div>
        <el-scrollbar class="menu-scrollbar">
        <el-menu
                :background-color="getLayoutParam.siderBGColor"
                :text-color="getLayoutParam.siderFontColor"
                :active-text-color="getLayoutParam.activeFontColor"
                unique-opened
                router
                :collapse="getSiderParam.isCollapse"
                :default-active="$route.path">
                    <menuTree :menuData="menuData"></menuTree>
        </el-menu>
        </el-scrollbar >
    </div>
</template>
<script>
import menuTree from '@/components/layout/otherLayoutCom/menuTree.vue';
import { mapGetters } from 'vuex';
export default {
    name:"leftSide",
    components:{
        menuTree
    },
    data() {
        return {
        };
    },
    computed:{
        ...mapGetters([
            'getLayoutParam',
            'getSiderParam'
        ]),
        menuData(){
            return this.$User.getMenuData()
        }
    }
}
</script>
<style scoped>
.siderMenu{
    height: 100%;
    overflow: hidden;
}
.sys-name{
    width: 100%;
    line-height: 50px;
    font-size: 20px;
    color: aliceblue;
    text-align: center;
}
.menu-scrollbar{
    height: calc(100% - 50px);
    transition: width 0.5s; 
}
.el-scrollbar__wrap .el-menu{
    width:100%
}
</style>