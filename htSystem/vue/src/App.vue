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
    // 在页面加载时读取sessionStorage里的状态信息
    // if (sessionStorage.getItem("store") ) {

    //     this.$store.replaceState(Object.assign({}, this.$store.state,JSON.parse(sessionStorage.getItem("store"))))
    // } 

    // //在页面刷新时将vuex里的信息保存到sessionStorage里
    // window.addEventListener("beforeunload",()=>{
    //     sessionStorage.setItem("store",JSON.stringify(this.$store.state))
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

// table边框颜色
.el-table--border:after,
.el-table--group:after,
.el-table:before {
    background-color: #C0C4CC;
}

.el-table--border,
.el-table--group {
    border-color: #C0C4CC;
}

.el-table td,
.el-table th.is-leaf {
        border-bottom: 1px solid #C0C4CC;
}

.el-table--border th,
.el-table--border th.gutter:last-of-type {
        border-bottom: 1px solid #C0C4CC;
}

.el-table--border td,
.el-table--border th {
        border-right: 1px solid #C0C4CC;
}

.el-table__fixed-right::before, 
.el-table__fixed::before{
  background-color: #C0C4CC;
}
</style>

