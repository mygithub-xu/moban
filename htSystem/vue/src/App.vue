<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>

export default {
  name: 'App'
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

