<template>
    <div class="app-container">
        <!-- 查询区域 -->
        <div class="container-query">
            <queryItem label="测试名：" width="100%">
                <el-input v-model="queryContion.name" placeholder="请输入测试名"></el-input>
            </queryItem>
            <queryItem label="测试状态：" width="100%">
            <el-date-picker
              v-model="queryContion.createTime"
              type="daterange"
              align="right"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              value-format="yyyy-MM-dd HH:mm:ss"
            ></el-date-picker>
            </queryItem>
            <query-item isButton>
                <span style="margin-left:30px">
                    <el-button type="primary"  @click="search" icon="el-icon-search">搜索</el-button>
                    <el-button  plain @click="reset" icon="el-icon-refresh">重置</el-button>
                </span>
            </query-item>
        </div>
        <!-- 数据表格 -->
        <div class="container-table">
            <div class="common-table-style">

            </div>
        </div>
    </div>
    
</template>
<script>
export default {
    name: "oneQuery",
    data() {
      return {
        // 数据
        pageData: {
          list: [],
          pageNumber: 1,
          pageSize: 10,
          totalCount: 0,
          totalPage: 0
        },
        // 查询
        queryContion: {
          name: "",
          createTime: ""
        },
      }
    },
    methods:{
        // 清空
        empty(){
            
        },
        // 查询
        search(){

        },
        // 重置
        reset(){

        }
    }
}
</script>