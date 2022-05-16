<template>
<!-- 菜单组件 -->
    <div>

        <template v-for="item in menuData"  >
            <template v-if="!item.children">
                <el-menu-item  :index="item.url" :key="item.url">{{item.menuName}}</el-menu-item>
            </template>
            <template v-else>
                <el-submenu :index="item.url" :key="item.url" >
                    <template slot="title">{{item.menuName}}</template>
                    <menuTreeHor :menuData="item.children"></menuTreeHor>
                </el-submenu>
            </template>
            
        </template>

    </div>
</template>
<script>
import menuTreeHor from '@/components/layout/otherLayoutCom/menuTreeHor.vue';
export default {
  props:['menuData'],
  name: 'menuTreeHor',
    components:{
        menuTreeHor
    },
  data(){
    return{

    }
  }
  
}
</script>
<style lang="scss" scoped>
// .el-submenu__title span{
    
// }
// .el-menu-item{
//     margin-left: 15px;
// }
</style>