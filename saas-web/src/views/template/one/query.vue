<template>
  <div class="app-container">
    <el-scrollbar style="width:100%">
      <!-- 查询区域 -->
      <div class="container-query">
        <queryItem label="测试名：">
          <el-input v-model="queryContion.testName"
                    placeholder="请输入测试名"></el-input>
        </queryItem>
        <queryItem label="测试状态">
          <el-select v-model="queryContion.testStatus"
                     placeholder="请选择"
                     clearable>
            <el-option v-for="item in selectData.testStatusList"
                       :key="item.value"
                       :label="item.label"
                       :value="item.value"></el-option>
          </el-select>
        </queryItem>
        <queryItem label="测试日期："
                   style="width:50%">
          <div class="is_data">
            <el-date-picker v-model="queryContion.createTimeFrom"
                            type="date"
                            placeholder="选择日期">
            </el-date-picker>
            <span style="margin: 0 10px;line-height: 36px;"> 至 </span>
            <el-date-picker v-model="queryContion.createTimeTo"
                            type="date"
                            placeholder="选择日期">
            </el-date-picker>
          </div>
        </queryItem>
        <query-item isButton
                    style="width:100%;padding-left:30px">
          <el-button type="primary"
                     @click="search">搜索</el-button>
          <el-button plain
                     @click="reset">重置</el-button>
          <el-button plain
                     @click="handleAdd">新增</el-button>
          <el-button type="danger"
                     @click="handleDeleteBatch">删除</el-button>
        </query-item>

      </div>
      <!-- 数据表格 -->
      <div class="container-table">
        <div class="common-table-style">
          <el-table :data="pageData.list"
                    border
                    @selection-change="handleSelectionChange">
            <el-table-column type="selection"></el-table-column>
            <el-table-column type="index"
                             width="50"
                             label="序号"></el-table-column>
            <el-table-column prop="createTime"
                             label="创建时间"></el-table-column>
            <el-table-column prop="testName"
                             label="测试名"></el-table-column>
            <el-table-column prop="testStatus"
                             label="测试状态"
                             align="center">
              <template slot-scope="scope">
                <el-tag v-for="item in selectData.testStatusList"
                        :key="item.id"
                        v-show="item.value == scope.row.testStatus">{{item.label}}</el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="testNum"
                             label="数量">
              <template slot-scope="scope">
                <span>{{scope.row.testNum | 10}}</span>
              </template>
            </el-table-column>
            <el-table-column prop="testUnit"
                             label="单价">
              <template slot-scope="scope">
                <span>{{scope.row.testUnit | 1000}}</span>
              </template>
            </el-table-column>
            <el-table-column prop="testTotal"
                             label="总额">
              <template slot-scope="scope">
                <span>{{scope.row.testTotal | 1000}}</span>
              </template>
            </el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button @click="handleEdit(scope.row)"
                           type="text">编辑</el-button>
                <el-button @click="handleDelete(scope.row)"
                           type="text"
                           style="color:red">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div class="pagination">
          <pagination :page-list="pageData"
                      @pagesearch="handlePage"></pagination>
        </div>
      </div>
      <oneEdit ref="oneEdit"></oneEdit>
    </el-scrollbar>

  </div>
</template>
<script>
import oneEdit from "./edit"
export default {
  components: { oneEdit },
  name: "oneQuery",
  data () {
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
        testName: "",
        createTimeFrom: "",
        createTimeTo: "",
        testStatus: ""
      },
      //下拉框数据
      selectData: {
        testStatusList: [],

      },

      //批量删除
      delVal: ""

    }
  },
  created () {
    //获取下拉框数据
    this.getDropData()
    //获取表格数据
    this.getData()

  },
  methods: {
    //获取表格数据
    getDropData () {
      this.$http.get(this.api.dicTypeGetType + "enabled").then(res => {
        this.selectData.testStatusList = res.data.body
      })
    },
    // 清空
    empty () {
      this.queryContion = {
        name: "",
        createTime: ""
      }
    },
    //获取数据
    getData () {
      let queryContion = {
        condition: this.queryContion,
        number: this.pageData.pageNumber,
        size: this.pageData.pageSize
      }
      this.$http.post(this.api.sysTestquery, queryContion).then(res => {
        this.pageData.list = res.data.body.records;
        this.pageData.totalCount = res.data.body.total;
        this.pageData.totalPage = res.data.body.pages;
      })
    },

    // 查询
    search () {
      this.getData()
    },
    // 重置
    reset () {
      this.empty()
      this.getData()
    },

    //分页查询
    handlePage (number, size) {
      this.pageData.pageNumber = number;
      this.pageData.pageSize = size;
      this.getData();
    },

    //新增
    handleAdd () {
      this.$refs.oneEdit.openByNew()
    },

    //编辑
    handleEdit (row) {
      this.$refs.oneEdit.openByEdit(row)
    },
    //删除
    handleDelete (row) {
      this.$confirm("此操作将永久删除, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        this.$http.delete(this.api.sysTestdeleteById + row.id).then(res => {
          if (res.data.code == '200') {
            this.$message.success(res.data.message)
            this.getData()
          }
        })
      })
    },
    handleDeleteBatch () {
      this.$confirm("此操作将永久删除, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(() => {
        let deletebatch = this.$utils.findIds(this.delVal)
        this.$http.post(this.api.sysTestbatchDelete, deletebatch).then(res => {
          if (res.data.code == '200') {
            this.$message.success(res.data.message)
            this.getData()
          }
        })
      })
    },
    //勾选
    handleSelectionChange (val) {
      this.delVal = val;
    }
  }
}
</script>