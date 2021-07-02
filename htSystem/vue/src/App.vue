<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>

export default {
  name: 'App',
  //解决store刷新值重置问题
  created () {
    // // 在页面加载时读取localStorage里的状态信息
    // if (localStorage.getItem("store") ) {

    //     this.$store.replaceState(Object.assign({}, this.$store.state,JSON.parse(localStorage.getItem("store"))))
    // } 

    // //在页面刷新前将vuex里的信息保存到localStorage里
    // window.addEventListener("beforeunload",()=>{
    //     localStorage.setItem("store",JSON.stringify(this.$store.state))
    // })
  }
}
</script>


<style lang="less">
	html,body,#app{
 		 height: 100%;
     width: 100%;
     margin: 0;
     padding: 0;
  }
  body .el-table th.gutter{
    display: table-cell!important;
 }
.el-table td{
  border-bottom: 1px solid #C0C4CC!important;
}
.el-table--border td{
  border-right: 1px solid #C0C4CC!important;
}
.el-table--border th{
      border-right: 1px solid #C0C4CC!important;
}
.el-table th.is-leaf{
  border-bottom: 1px solid #C0C4CC!important;
}
.el-table--border{
  border: 1px solid #C0C4CC!important;
  // border-bottom: 0.1px solid #C0C4CC!important;
}
</style>

