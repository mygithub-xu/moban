<template>
  <!--区域四---预览区域-->
  <div class="show-dialog">
    <el-dialog :visible.sync="preViewVisible" :show-close="false" :modal="false" fullscreen @close="close">
      <div class="dialog-button">
        <el-button size="small" @click="cancelView" icon="el-icon-back">返回</el-button>
      </div>

      <div class="dialog-form">
        <!--申请单-->
        <el-form ref="form" :model="previewData" label-width="150px"
                 style="margin-top:10px">
          <div class="edit-area">

            <el-form-item label="标题">
              <span>{{previewData.title}}</span>
            </el-form-item>

            <el-form-item label="申请单号">
              <span>{{previewData.apply_code}}</span>
            </el-form-item>

            <el-form-item label="是否签报OA">
              <span v-if="previewData.is_send_oa==1">是</span>
              <span v-if="previewData.is_send_oa==0">否</span>
            </el-form-item>

            <el-form-item label="OA签报号">
              <span>{{previewData.oa_code}}</span>
            </el-form-item>

            <el-form-item label="签报日期">
              <span>{{previewData.sent_oa_date | getLongDate}}</span>
            </el-form-item>

            <el-form-item label="状态">
              <span v-if="previewData.state==1">待申请</span>
              <span v-if="previewData.state==2">申请中</span>
              <span v-if="previewData.state==3">待审核</span>
              <span v-if="previewData.state==4">已审核</span>
              <span v-if="previewData.state==5">已作废</span>
            </el-form-item>

            <el-form-item label="事由">
              <el-input readonly type="textarea" v-model="previewData.reason"></el-input>
            </el-form-item>
          </div>
        </el-form>
      </div>

      <!--申请单明细-->
      <div class="dialog-tab">
        <el-tabs v-model="activeName">
          <el-tab-pane label="明细表" name="first">
            <div class="tableBody">
              <el-table ref="tableData" :data="previewData.applyDetailVOList" border align="center">
                <el-table-column type="index" width="55" label="序号" align="center"
                                 show-overflow-tooltip></el-table-column>
                <el-table-column prop="addr_post_code" min-width="140" label="收件邮编" align="left"
                                 show-overflow-tooltip></el-table-column>
                <el-table-column prop="app_amount" min-width="140" label="申请数量" align="left"
                                 show-overflow-tooltip></el-table-column>
                <el-table-column prop="app_price" min-width="140" label="申请价格" align="right" show-overflow-tooltip>
                  <template slot-scope="scope">
                    <span>{{scope.row.app_price | 1000}}</span>
                  </template>
                </el-table-column>
                <el-table-column prop="prod_code" min-width="140" label="商品代码" align="left"
                                 show-overflow-tooltip></el-table-column>
                <el-table-column prop="prod_name" min-width="140" label="商品名称" align="left"
                                 show-overflow-tooltip></el-table-column>
                <el-table-column prop="state" min-width="140" label="状态" align="center" show-overflow-tooltip>
                  <template slot-scope="scope">
                    <span v-if="scope.row.state == 11">申请</span>
                    <span v-if="scope.row.state == 12">待下单</span>
                    <span v-if="scope.row.state == 13">已作废</span>
                    <span v-if="scope.row.state == 14">退回</span>
                    <span v-if="scope.row.state == 20">待验收</span>
                    <span v-if="scope.row.state == 21">部分验收</span>
                    <span v-if="scope.row.state == 22">全部验收</span>
                    <span v-if="scope.row.state == 23">退单</span>
                  </template>
                </el-table-column>
              </el-table>
            </div>
          </el-tab-pane>
        </el-tabs>
      </div>

    </el-dialog>
  </div>
</template>

<script>
  export default {
    name: "preview",
    props: {
      dialogView: {
        type: Boolean,
        default: false
      }
    },
    data() {
      return {
        preViewVisible: false,
        activeName: 'first',
        previewData: {}
      }
    },
    methods: {
      //关闭dialog触发的方法
      close() {
        this.$emit("update:dialogView", false)
      },
      //预览页返回
      cancelView() {
        this.preViewVisible = false
      },
      //显示数据
      showData(apply_id) {
        this.$http.get('apply/getApply/' + apply_id).then(res => {
          this.previewData = res
        })
      }
    },
    watch: {
      dialogView: {
        handler(newValue) {
          this.preViewVisible = newValue
        }
      }
    }
  }
</script>

<style scoped>

</style>
