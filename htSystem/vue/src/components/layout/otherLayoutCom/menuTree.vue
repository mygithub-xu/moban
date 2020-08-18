<template>
<!-- 菜单组件 -->
    <div>
        <template v-for="item in menuData"  >
            <el-submenu :index="item.url" :key="item.url" v-if="item.children" >
                <template slot="title">
                    <i class="icon iconfont" :class="item.icon" style="font-size:18px;width: 24px;"></i>
                    <span slot="title" style="font-size: 13px;margin-left: 12px;">{{item.menuName}}</span>
                </template>
                <div class="treeCss" style="background:blue}">
                <menuTree :menuData="item.children"></menuTree>
                </div>
            </el-submenu>
            <el-menu-item :index="item.url" :key="item.url" v-else>
                <i class="icon iconfont" :class="item.icon" style="font-size:18px;width: 14px;"></i>
                <span slot="title" style="font-size: 13px;margin-left: 12px;">{{item.menuName}}</span>
            </el-menu-item>
        </template>

    </div>
</template>
<script>
export default {
  props:['menuData'],
  name: 'menuTree',
  data(){
    return{

    }
  }
  
}
</script>
<style lang="scss" scoped>
</style>