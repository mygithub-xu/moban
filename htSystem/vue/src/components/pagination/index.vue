<template>
  <el-pagination
    @size-change="handleSizeChange"
    @current-change="handleCurrentChange"
    :current-page="pageList.pageNumber"
    :page-sizes="[5, 10, 20, 50, 100]"
    :page-size="pageList.pageSize"
    layout="total, sizes, prev, pager, next, jumper"
    :total="pageList.totalCount">
  </el-pagination>
</template>

<script>
    export default {
      name: 'pagination',
      props: ['pageList'],
      methods: {
        /**
         * 获取 每页数量
         */
        handleSizeChange(val) {
            this.pageList.pageSize = val;
            this.$emit('pagesearch', this.pageList.pageNumber, val);
        },

        /**
         * 获取 当前页
         */
        handleCurrentChange(val) {
            this.pageList.pageNumber = val;
            this.$emit('pagesearch', val, this.pageList.pageSize);
        }
      }
    }
</script>

<style>
</style>
