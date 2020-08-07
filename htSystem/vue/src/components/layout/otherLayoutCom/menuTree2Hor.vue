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
                    <menuTree2Hor :menuData="item.children"></menuTree2Hor>
                </el-submenu>
            </template>
            
        </template>

    </div>
</template>
<script>
export default {
  props:['menuData'],
  name: 'menuTree2Hor',
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