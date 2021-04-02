<template>
  <div class="app-container">
    <div class="show-dialog">
      <el-dialog :visible.sync="editVisible"
                 :show-close="false"
                 :modal="false"
                 fullscreen>
        <div class="dialog-button">
          <el-button icon="el-icon-back">返回</el-button>
          <el-button type="primary"
                     icon="el-icon-check">保存草稿</el-button>
          <el-button type="primary"
                     icon="el-icon-check">提交审核</el-button>
        </div>

        <editComp width="50%"
                  title="基本信息">
          <el-form label-width="90px">
            <el-form-item label="测试状态："
                          style="width:50%;">
              <el-select v-model="orgValue"
                         placeholder="请选择">
                <el-option v-for="item in orgList"
                           :key="item.value"
                           :label="item.label"
                           :value="item.value"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="用户部门：">
              科技信息部
            </el-form-item>
            <el-form-item label="负责人：">
              赵大
            </el-form-item>
            <el-form-item label="预算年度：">
              赵大
            </el-form-item>

            <el-form-item label="*项目性质：">
              <el-select v-model="orgValue"
                         placeholder="请选择"
                         clearable>
                <el-option v-for="item in orgList"
                           :key="item.value"
                           :label="item.label"
                           :value="item.value"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="管理部门：">
              赵大
            </el-form-item>
            <el-form-item label="负责人：">
              赵大
            </el-form-item>
            <el-form-item label="编制人：">
              赵大
            </el-form-item>
            <el-form-item label="编制日期：">
              赵大
            </el-form-item>
            <el-form-item label="技术部门1：">
              赵大
            </el-form-item>
            <el-form-item label="负责人：">
              赵大
            </el-form-item>
            <el-form-item label="状态："
                          style="width:50%;">
              赵大
            </el-form-item>
            <el-form-item label="技术部门2：">
              赵大
            </el-form-item>
            <el-form-item label="负责人：">
              赵大
            </el-form-item>
            <el-form-item label="立即报告："
                          style="width:100%;">
              <div style="line-height: 22px;margin-top:5px">
                <el-link type="primary">上传</el-link>
                <el-link type="primary">下载</el-link>
                <el-link type="danger">删除</el-link>
              </div>
            </el-form-item>
            <el-form-item label="其他附件："
                          style="width:100%;">
              <div style="line-height: 22px;margin-top:5px">
                <el-link type="primary">上传</el-link>
                <el-link type="primary">下载</el-link>
                <el-link type="danger">删除</el-link>
              </div>

            </el-form-item>

          </el-form>
        </editComp>

        <editComp width="50%"
                  title="预算汇总"
                  style="height:320px;">
          <el-table :data="tableData"
                    show-summary
                    border
                    align="center">
            <el-table-column prop="name"></el-table-column>
            <el-table-column prop="totalPrice"
                             label="预算总金额"></el-table-column>
            <el-table-column prop="thisYearPrice"
                             label="当年预算金额"></el-table-column>
            <el-table-column prop="paidPrice"
                             label="已支付金额"></el-table-column>
            <el-table-column prop="nextYearPrice"
                             label="明年支付金额"></el-table-column>
            <el-table-column prop="needPaid"
                             label="明年以后支付金额"></el-table-column>
          </el-table>
        </editComp>

        <editComp width="100%"
                  title="预算明细">

          <div class="container-btn">
            <el-button icon="el-icon-plus"
                       type="primary"
                       @click="addList"></el-button>
            <el-button icon="el-icon-minus"
                       type="danger"
                       @click="delList"></el-button>
          </div>
          <div class="tab-table">
            <el-table @selection-change="handleSelectionChangeDetil"
                      :data="budgetDetailsList"
                      border
                      align="center">

              <el-table-column type="selection"
                               width="45"
                               align="center"></el-table-column>
              <el-table-column type="index"
                               width="55"
                               align="center"></el-table-column>
              <el-table-column prop="budgetSubject"
                               label="预算科目"
                               align="center">
                <template slot="header"
                          slot-scope="scope">
                  <span style="color:red">*</span>
                  <span>{{scope.column.label}}</span>
                </template>
                <template slot-scope="scope">
                  <el-select v-model="scope.row.budgetSubject"
                             placeholder="请选择">
                    <el-option v-for="item in subjectList"
                               :key="item.value"
                               :label="item.label"
                               :value="item.value"></el-option>
                  </el-select>
                </template>
              </el-table-column>

              <el-table-column prop="budgetNum"
                               label="预算数量">
                <template slot="header"
                          slot-scope="scope">
                  <span style="color:red">*</span>
                  <span>{{scope.column.label}}</span>
                </template>
                <template slot-scope="scope">
                  <el-input v-model="scope.row.budgetNum"></el-input>
                </template>
              </el-table-column>
              <el-table-column prop="budgetTotal"
                               label="预算总金额"
                               align="center">

                <template slot-scope="scope">
                  {{scope.row.budgetTotal}}
                </template>
              </el-table-column>

              <el-table-column prop="thisYearBudgetPrice"
                               label="当年预算金额"
                               align="center">
                <template slot="header"
                          slot-scope="scope">
                  <span style="color:red">*</span>
                  <span>{{scope.column.label}}</span>
                </template>
                <template slot-scope="scope">
                  <el-input v-model="scope.row.thisYearBudgetPrice"></el-input>
                </template>
              </el-table-column>
              <el-table-column prop="paidPrice"
                               label="已支付金额"
                               align="center">
                <template slot="header"
                          slot-scope="scope">
                  <span style="color:red">*</span>
                  <span>{{scope.column.label}}</span>
                </template>
                <template slot-scope="scope">
                  <el-input v-model="scope.row.paidPrice"></el-input>
                </template>
              </el-table-column>
              <el-table-column prop="nextpayPrice"
                               label="明年支付金额"
                               align="center">
                <template slot="header"
                          slot-scope="scope">
                  <span style="color:red">*</span>
                  <span>{{scope.column.label}}</span>
                </template>
                <template slot-scope="scope">
                  <el-input v-model="scope.row.nextpayPrice"></el-input>
                </template>
              </el-table-column>
              <el-table-column prop="otherYearpayPrice"
                               label="明年后支付金额"
                               align="center">
                <template slot="header"
                          slot-scope="scope">
                  <span style="color:red">*</span>
                  <span>{{scope.column.label}}</span>
                </template>
                <template slot-scope="scope">
                  <el-input v-model="scope.row.otherYearpayPrice"></el-input>
                </template>
              </el-table-column>
              <el-table-column prop="remark"
                               label="备注"
                               align="center">
                <template slot-scope="scope">
                  <el-input v-model="scope.row.remark"></el-input>
                </template>
              </el-table-column>

            </el-table>
          </div>

        </editComp>
      </el-dialog>
    </div>
  </div>
</template>
<script>
export default {
  name: "editDemo",
  data () {
    return {
      editVisible: true,
      orgValue: '1',
      orgList: [
        {
          value: '1',
          label: '第三方测试项目'
        }, {
          value: '2',
          label: '测试项目'
        }
      ],
      tableData: [
        {
          name: "硬件",
          totalPrice: 1000000,
          thisYearPrice: 1000000,
          nextYearPrice: 0,
          paidPrice: 0,
          needPaid: 0
        },
        {
          name: "软件",
          totalPrice: 1000000,
          thisYearPrice: 1000000,
          nextYearPrice: 0,
          paidPrice: 0,
          needPaid: 0
        },
        {
          name: "开发实施",
          totalPrice: 1000000,
          thisYearPrice: 1000000,
          nextYearPrice: 0,
          paidPrice: 0,
          needPaid: 0
        }
      ],
      subjectList: [
        {
          value: '1',
          label: '科目一'
        }, {
          value: '2',
          label: '科目二'
        }
      ],
      budgetDetailsList: [],
      delVal: "",

    }
  },
  created () {

  },
  methods: {

    handleSelectionChangeDetil (val) {
      this.delVal = val;
    },
    addList () {
      var budgetDetailsListItem = {
        budgetSubject: "",
        budgetNum: null,
        budgetTotal: null,
        thisYearBudgetPrice: null,
        paidPrice: null,
        nextpayPrice: null,
        otherYearpayPrice: null,
        remark: ""
      }
      if (!this.budgetDetailsList) {
        this.budgetDetailsList = [];
      }
      this.budgetDetailsList.push(budgetDetailsListItem);
    },
    delList () {
      if (this.delVal.length == 0) {
        return this.$message.warning("请选择一条数据");
      }
      this.budgetDetailsList = this.minus(this.budgetDetailsList, this.delVal);
    },
    //集合相减的方法
    minus (arr1, arr2) {
      let arr3 = [];
      for (var i = 0; i < arr1.length; i++) {
        var flag = true;
        for (var j = 0; j < arr2.length; j++) {
          if (arr1[i] == arr2[j]) flag = false;
        }
        if (flag) {
          arr3.push(arr1[i]);
        }
      }
      return arr3;
    }

  }
}
</script>
<style scoped>
</style>