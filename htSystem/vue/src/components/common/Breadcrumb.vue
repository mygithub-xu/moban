<template>
  <el-breadcrumb class="app-breadcrumb" separator="/">
    <!-- <transition-group name="breadcrumb"> -->
      <!-- <el-breadcrumb-item v-for="(item, index) in levelList" :key="item.path">
        <span v-if="item.redirect === 'noredirect' || index == levelList.length - 1" class="no-redirect">{{ item.meta.title }}</span>
        <a v-else @click.prevent="handleLink(item)">{{ item.meta.title }}</a>
      </el-breadcrumb-item> -->

     
          <el-breadcrumb-item v-for="(item, index) in levelList" :key="index">
              <a v-if="item.path" @click="handleLink(item)">{{item.name}}</a>
              <span v-else>{{item.name}}</span>
          </el-breadcrumb-item>
     


    <!-- </transition-group> -->
  </el-breadcrumb>
</template>

<script>
import pathToRegexp from 'path-to-regexp'

export default {
  name: 'breadcrumb',
  data() {
    return {
      levelList: null
    }
  },
  watch: {
    $route(route) {

      this.getBreadcrumb()
    }
  },
  created() {
    this.getBreadcrumb()
  },
  methods: {
    getBreadcrumb() {
      this.levelList=[];
      let matched = this.$route.matched;
      var shouye={
        name:"首页",
        path:"/page/Dashboard"
      }
      this.levelList.push(shouye);
      if(matched[1].path=="/page/Dashboard"){
        return;
      }
      var end={
        name:matched[1].name,
        path:matched[1].path
      }
      if(matched[1].meta.length>0){
        this.levelList=this.levelList.concat(matched[1].meta);
      }
      this.levelList.push(end);
      // let matched = this.$route.matched.filter(
      //   item => item.meta && item.meta.title
      // )
      // const first = matched[0]
      // first.path='/page/Dashboard';
      // if(matched[1].path==first.path){
      //    matched.splice(1,1);
      // }
      // this.levelList = matched.filter(
      //   item => item.meta && item.meta.title && item.meta.breadcrumb !== false
      // )
    },
    isDashboard(route) {
      const name = route && route.name
      if (!name) {
        return false
      }
      return (
        name.trim().toLocaleLowerCase() === 'Dashboard'.toLocaleLowerCase())
    },
    pathCompile(path) {
      // To solve this problem https://github.com/PanJiaChen/vue-element-admin/issues/561
      const { params } = this.$route
      var toPath = pathToRegexp.compile(path)
      return toPath(params)
    },
    handleLink(item) {
      // const { redirect, path } = item
      // if (redirect) {
      //   this.$router.push(redirect)
      //   return
      // }
      // this.$router.push(this.pathCompile(path))
      // this.$router.push(item)
      this.$router.push(item.path)
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.app-breadcrumb.el-breadcrumb {
  display: inline-block;
  font-size: 14px;
  line-height: 50px;
  margin-left: 10px;
  .no-redirect {
    color: #97a8be;
    cursor: text;
  }
}
.app-breadcrumb{
  padding-top: 5px
}
</style>
