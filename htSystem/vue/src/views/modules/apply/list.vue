<template>
  <div class="app-container" style="flex-direction: column;">
    <!--区域一---查询区域-->
    <div class="container-query">
      <el-form label-width="50px">
        <div class="query-area">
          <el-form-item label="开始申请日期" size="small" label-width="100px">
            <el-date-picker v-model="requestData.condition.apply_date_from" type="date" placeholder="选择日期" style="width: 300px;">
            </el-date-picker>
          </el-form-item>

          <el-form-item label="标题" size="small" label-width="75px">
            <el-input v-model="requestData.condition.title" placeholder="请输入标题" style="width: 300px;"></el-input>
          </el-form-item>

          <el-form-item label="申请单号" size="small" label-width="75px">
            <el-input v-model="requestData.condition.apply_code" placeholder="请输入申请单号" style="width: 300px;"></el-input>
          </el-form-item>


          <el-form-item label="结束申请日期" size="small" label-width="100px">
            <el-date-picker
              v-model="requestData.condition.apply_date_to"
              type="date"
              placeholder="选择日期"
              style="width: 300px;">
            </el-date-picker>
          </el-form-item>

          <el-form-item label="状态" size="small" label-width="75px">
            <selection ref="selection" keyword="APPLY_STATE" :sel-data.sync="requestData.condition.state"></selection>
          </el-form-item>

          <el-form-item>
                  <span>
                    <el-button type="primary" size="small" @click="query" icon="el-icon-search">搜索</el-button>
                    <el-button size="small" plain icon="el-icon-refresh" @click="restSearch">重置</el-button>
                  </span>
          </el-form-item>
        </div>
      </el-form>
    </div>

    <!-- 区域二---数据展示区域 -->
    <div class="container-table">
      <div class="container-btn">
                <span class="container-btn-left">
                  <el-button type="primary" size="small" @click="handleAdd">新 增</el-button>
                  <el-button type="primary" size="small" @click="handleView">详 情</el-button>
                  <el-button type="primary" size="small" @click="handleEdit">编 辑</el-button>
                  <el-button type="danger" size="small" @click="handleDel">删 除</el-button>
                  <el-button type="primary" size="small" @click="handleChooseData">获取选中的内容</el-button>
                </span>
      </div>
      <div class="common-table-style">
        <el-table :data="tableData" style="width: 100%" height="100%" :row-style="{height:'50px'}" border
                  class="table" ref="table" align="center" @selection-change="handleTableChange">
          <el-table-column type="selection" width="50" align="center"></el-table-column>
          <el-table-column type="index" width="50" label="序号" align="center"></el-table-column>
          <el-table-column prop="apply_date" min-width="140" label="申请日期" align="left"
                           show-overflow-tooltip>
            <template slot-scope="scope">
              {{scope.row.apply_date | getLongDate}}
            </template>
          </el-table-column>
          <el-table-column prop="apply_code" min-width="140" label="申请单号" align="left"
                           show-overflow-tooltip></el-table-column>
          <el-table-column prop="title" min-width="140" label="标题" align="left" show-overflow-tooltip></el-table-column>
          <el-table-column prop="state" min-width="140" label="状态" align="center" show-overflow-tooltip>
            <template slot-scope="scope">
              <span v-if="scope.row.state == 1">待申请</span>
              <span v-if="scope.row.state == 2">申请中</span>
              <span v-if="scope.row.state == 3">待审核</span>
              <span v-if="scope.row.state == 4">已审核</span>
              <span v-if="scope.row.state == 5">已作废</span>
            </template>
          </el-table-column>
          <el-table-column prop="oa_code" min-width="140" label="OA签报号" align="left" show-overflow-tooltip>
            <template slot-scope="scope">
              {{scope.row.oa_code === null ? '无' : scope.row.oa_code}}
            </template>
          </el-table-column>
          <el-table-column prop="is_send_oa" min-width="140" label="是否签报OA" align="center"
                           show-overflow-tooltip>
            <template slot-scope="scope">
              <span v-if="scope.row.is_send_oa == 1">是</span>
              <span v-if="scope.row.is_send_oa == 0">否</span>
            </template>
          </el-table-column>
          <el-table-column prop="sent_oa_date" min-width="140" label="签报日期" align="left" show-overflow-tooltip>
            <template slot-scope="scope">
              <span v-if="scope.row.sent_oa_date === null">无</span>
              <span v-else>{{scope.row.sent_oa_date | getLongDate}}</span>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div class="pagination">
        <el-pagination class="page"
                       @size-change="handleSizeChange"
                       @current-change="handleCurrentChange"
                       :current-page="requestData.pageCondition.pageNo"
                       :page-sizes="[10, 20, 50, 100]"
                       :page-size="10"
                       layout="total, sizes, prev, pager, next, jumper"
                       :total="responseData.totalRows">
        </el-pagination>
      </div>
    </div>

    <!-- 区域三---新增编辑区域 -->
    <edit ref="edit" :dialogVisible.sync="dialogVisible" :apply_id="apply_id"></edit>

    <!--区域四---预览区域-->
    <preview ref="preview" :dialogView.sync="dialogView"></preview>

  </div>

</template>

<script>
  import Edit from "./edit";
  import Preview from "./preview";
  import selection from "../../../components/common/selection/selection";


  export default {
    name: "list",
    components: { Preview, Edit,selection},
    created() {
      //进行数据的填充工作
      this.query();
    },
    data() {
      return {
        multipleSelectionAll: [], // 所有选中的数据包含跨页数据
        multipleSelection: [],//table区域被选中的数据
        idKey: "apply_id", // 标识列表数据中每一行的唯一键的名称
        dialogView: false,//控制预览区域的显示与隐藏
        dialogEdit: false,//控制编辑区域的显示与隐藏
        dialogVisible: false,//控制新增的显示与隐藏
        tableData: [],//列表展示数据
        apply_id:'',
        //查询参数
        requestData: {
          condition: {
            state: '',
            apply_code: '',
            title: '',
            dateTime: '',//时间
            //查询条件
            apply_date_from: "",
            apply_date_to: "",
          },
          pageCondition: {
            pageNo: 1,//当前页
            pageSize: 10//每页显示条数
          }
        },
        //查询返回的参数
        responseData: {
          totalRows: 0//总记录条数
        },
      }
    },
    methods: {
      //新增
      handleAdd() {
        this.dialogVisible = true;
        this.$refs['edit'].echoData('')
      },
      //预览
      handleView() {
        if (this.multipleSelection.length == 1) {
          this.dialogView = true
          this.$refs['preview'].showData(this.multipleSelection[0].apply_id)
        } else {
          this.$message.error('请选中一条数据进行预览！')
        }
      },
      //编辑
      handleEdit() {
        if (this.multipleSelection.length == 1) {
          this.dialogVisible = true;
          //调用子组件方法查询数据进行回显
          this.$refs['edit'].echoData(this.multipleSelection[0].apply_id)
        } else {
          this.$message.error('请选中一条数据进行编辑！');
        }
      },
      //删除
      handleDel() {

      },
      //渲染列表数据
      query() {
        this.$http.post("apply/queryApply", this.requestData).then(res => {
          this.tableData = res.resultList
          this.responseData.totalRows = res.totalRows
          setTimeout(() => {
            this.setSelectRow();
          }, 20);
        })
      },
      //当每页显示条数发生改变时
      handleSizeChange(val) {
        this.requestData.pageCondition.pageSize = val
        this.query()
        this.changePageCoreRecordData();
      },
      //当前页发生改变时
      handleCurrentChange(val) {
        this.requestData.pageCondition.pageNo = val
        this.query()
        this.changePageCoreRecordData();
      },
      //选中List列表中的行
      handleTableChange(val) {
        this.multipleSelection = val
      },
      //获取选中内容
      handleChooseData() {
        // 获取之前需要执行一遍记忆分页处理
        this.changePageCoreRecordData();
        this.$alert(`选中条数为:${this.multipleSelectionAll.length}`, '提示', {
          confirmButtonText: '确定',
          callback: action => {
            console.log(JSON.stringify(this.multipleSelectionAll));
          }
        });
      },
      // 设置选中的方法
      setSelectRow() {
        if (!this.multipleSelectionAll || this.multipleSelectionAll.length <= 0) {
          return;
        }
        // 标识当前行的唯一键的名称
        let idKey = this.idKey;
        let selectAllIds = [];
        let that = this;
        this.multipleSelectionAll.forEach(row => {
          selectAllIds.push(row[idKey]);
        });
        this.$refs.table.clearSelection();
        for (let i = 0; i < this.tableData.length; i++) {
          if (selectAllIds.indexOf(this.tableData[i][idKey]) >= 0) {
            // 设置选中，记住table组件需要使用ref="table"
            this.$refs.table.toggleRowSelection(this.tableData[i], true);
          }
        }
      },
      // 记忆选择核心方法
      changePageCoreRecordData() {
        // 标识当前行的唯一键的名称
        let idKey = this.idKey;
        let that = this;
        // 如果总记忆中还没有选择的数据，那么就直接取当前页选中的数据，不需要后面一系列计算
        if (this.multipleSelectionAll.length <= 0) {
          this.multipleSelectionAll = this.multipleSelection;
          return;
        }
        // 总选择里面的key集合
        let selectAllIds = [];
        this.multipleSelectionAll.forEach(row => {
          selectAllIds.push(row[idKey]);
        });
        let selectIds = [];
        // 获取当前页选中的id
        this.multipleSelection.forEach(row => {
          selectIds.push(row[idKey]);
          // 如果总选择里面不包含当前页选中的数据，那么就加入到总选择集合里
          if (selectAllIds.indexOf(row[idKey]) < 0) {
            that.multipleSelectionAll.push(row);
          }
        });
        let noSelectIds = [];
        // 得到当前页没有选中的id
        this.tableData.forEach(row => {
          if (selectIds.indexOf(row[idKey]) < 0) {
            noSelectIds.push(row[idKey]);
          }
        });
        noSelectIds.forEach(id => {
          if (selectAllIds.indexOf(id) >= 0) {
            for (let i = 0; i < that.multipleSelectionAll.length; i++) {
              if (that.multipleSelectionAll[i][idKey] == id) {
                // 如果总选择中有未被选中的，那么就删除这条
                that.multipleSelectionAll.splice(i, 1);
                break;
              }
            }
          }
        });
      },
      //重置搜索
      restSearch() {
        this.requestData = {
          condition: {
            title: '',
            dateTime: '',
            apply_date_from: "",
            apply_date_to: "",
          },
          pageCondition: {
            pageNo: 1,
            pageSize: 10
          }
        }
        this.$refs['selection'].value = ''
        this.query()
      },
    },
    watch:{
    }
  }
</script>

<style scoped>
</style>
