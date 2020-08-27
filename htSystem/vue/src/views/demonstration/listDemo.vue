<template>
  <div class="app-container" style="flex-direction: column;">
    <!--区域一---查询区域-->
    <div class="container-query">

      <el-form label-width="50px">

        <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" style="height:40px;">
          <el-form-item label="开始申请日期" size="small" label-width="100px">
            <el-date-picker
              v-model="requestData.condition.apply_date_from"
              type="date"
              placeholder="选择日期"
              style="width: 300px;">
            </el-date-picker>
          </el-form-item>
        </el-col>

        <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" style="height:40px;">
          <el-form-item label="标题" size="small" label-width="75px">
            <el-input v-model="requestData.condition.title" placeholder="请输入标题" style="width: 300px;"></el-input>
          </el-form-item>
        </el-col>

        <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" style="height:40px;">
          <el-form-item label="申请单号" size="small" label-width="75px">
            <el-input v-model="requestData.condition.apply_code" placeholder="请输入申请单号" style="width: 300px;"></el-input>
          </el-form-item>
        </el-col>


        <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" style="height:40px;">
          <el-form-item label="结束申请日期" size="small" label-width="100px">
            <el-date-picker
              v-model="requestData.condition.apply_date_to"
              type="date"
              placeholder="选择日期"
              style="width: 300px;">
            </el-date-picker>
          </el-form-item>
        </el-col>

        <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" style="height:40px;">
          <el-form-item label="状态" size="small" label-width="75px">
            <el-select v-model="requestData.condition.state" placeholder="请选择状态" style="width: 300px;">
              <el-option label="待申请" value="1"></el-option>
              <el-option label="申请中" value="2"></el-option>
              <el-option label="待审核" value="3"></el-option>
              <el-option label="已审核" value="4"></el-option>
              <el-option label="已作废" value="5"></el-option>
            </el-select>
          </el-form-item>
        </el-col>

          <el-col :xs="24" :sm="24" :md="24" :lg="24" :xl="24" style="height:40px;">
            <el-form-item>
                  <span>
                    <el-button type="primary" size="small" @click="findByTime" icon="el-icon-search">搜索</el-button>
                    <el-button size="small" plain icon="el-icon-refresh" @click="restSearch">重置</el-button>
                  </span>
            </el-form-item>
          </el-col>
      </el-form>
    </div>

    <!-- 区域二---表格+分页 -->
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
          <el-table-column prop="apply_code" min-width="140" label="申请单号" align="left" show-overflow-tooltip></el-table-column>
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
             {{scope.row.oa_code === null  ? '无' : scope.row.oa_code}}
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

    <!-- 区域三---弹出框，覆盖全部 -->
    <div class="show-dialog">
      <el-dialog :visible.sync="dialogVisible" :show-close="false" :modal="false" fullscreen>
        <!--按键区域-->
        <div>
          <el-button size="small" @click="cancelSave" icon="el-icon-back">返回</el-button>
          <el-button type="primary" size="small" icon="el-icon-check" @click="sendForm" v-prevent-re-click>保存
          </el-button>
        </div>

        <!---->
        <el-row class="dialog-formm">

            <div style="margin-top: 42px;">
              <!--申请单-->
              <el-form ref="form" :model="condition" :rules="rules" label-width="150px" size="small"
                       style="margin-top:10px">

                <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8">
                  <el-form-item label="标题" prop="title">
                    <el-input v-model="condition.title"></el-input>
                  </el-form-item>
                </el-col>

                <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8">
                  <el-form-item label="申请单号" prop="apply_code">
                    <el-input v-model="condition.apply_code" oninput="value=value.replace(/[^\d]/g,'')"></el-input>
                  </el-form-item>
                </el-col>

                <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8">
                  <el-form-item label="是否签报OA" prop="is_send_oa">
                    <el-switch
                      v-model="condition.is_send_oa"
                      active-value="1"
                      inactive-value="0">
                    </el-switch>
                    <span v-if="condition.is_send_oa == 1">是</span>
                    <span v-if="condition.is_send_oa == 0">否</span>
                  </el-form-item>
                </el-col>

                <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8">
                  <el-form-item label="OA签报号" prop="oa_code">
                    <el-input v-model="condition.oa_code" oninput="value=value.replace(/[^\d]/g,'')"></el-input>
                  </el-form-item>
                </el-col>

                <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8">
                  <el-form-item label="签报日期" prop="sent_oa_date">
                    <el-date-picker type="date" placeholder="选择日期" v-model="condition.sent_oa_date"
                                    style="width: 100%;"></el-date-picker>
                  </el-form-item>
                </el-col>

                <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8">
                  <el-form-item label="状态" prop="state">
                    <el-select v-model="condition.state" placeholder="请选择">
                      <el-option label="待申请" value="1"></el-option>
                      <el-option label="申请中" value="2"></el-option>
                      <el-option label="待审核" value="3"></el-option>
                      <el-option label="已审核" value="4"></el-option>
                      <el-option label="已作废" value="5"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>

                <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8">
                  <el-form-item label="事由" prop="reason">
                    <el-input type="textarea" v-model="condition.reason"></el-input>
                  </el-form-item>
                </el-col>
              </el-form>
            </div>
        </el-row>
        <!--申请单明细-->
        <div class="dialog-tab">
          <el-tabs v-model="activeName">
            <el-tab-pane label="明细表" name="first">
              <div class="buttonSku">
                <el-button icon="el-icon-plus" size="small" type="primary" @click="addList"></el-button>
                <el-button icon="el-icon-minus" size="small" type="danger" @click="delList"></el-button>
              </div>

              <div class="tableBody">
                <el-form ref="formDetail" :model="condition" :rules="ruleDetail" style="margin-top:10px">
                  <el-table ref="tableData" :data="condition.applyDetailVOList" border
                            @selection-change="handleSelectionChange" align="center">
                    <el-table-column type="selection" fixed width="45" align="center"
                                     show-overflow-tooltip></el-table-column>
                    <el-table-column type="index" width="55" label="序号" align="center"
                                     show-overflow-tooltip></el-table-column>
                    <el-table-column prop="addr_post_code" min-width="140" label="收件邮编" align="left"
                                     show-overflow-tooltip>
                      <template slot-scope="scope">
                        <el-form-item :prop="'applyDetailVOList.'+scope.$index+'.addr_post_code'"
                                      :rules="ruleDetail.addr_post_code">
                          <el-input v-model="scope.row.addr_post_code" oninput="value=value.replace(/[^\d]/g,'')"></el-input>
                        </el-form-item>
                      </template>
                    </el-table-column>
                    <el-table-column prop="app_amount" min-width="140" label="申请数量" align="left" show-overflow-tooltip>
                      <template slot-scope="scope">
                        <el-form-item :prop="'applyDetailVOList.'+scope.$index+'.app_amount'"
                                      :rules="ruleDetail.app_amount">
                          <el-input v-model="scope.row.app_amount" oninput="value=value.replace(/[^\d]/g,'')"></el-input>
                        </el-form-item>
                      </template>
                    </el-table-column>

                    <el-table-column prop="app_price" min-width="140" label="申请价格" align="right" show-overflow-tooltip>
                      <template slot-scope="scope">
                        <el-form-item :prop="'applyDetailVOList.'+scope.$index+'.app_price'"
                                      :rules="ruleDetail.app_price">
                          <el-input v-model="scope.row.app_price" oninput="value=value.replace(/[^\d]/g,'')"></el-input>
                        </el-form-item>
                      </template>
                    </el-table-column>

                    <el-table-column prop="prod_code" min-width="140" label="商品代码" align="left" show-overflow-tooltip>
                      <template slot-scope="scope">
                        <el-form-item :prop="'applyDetailVOList.'+scope.$index+'.prod_code'"
                                      :rules="ruleDetail.prod_code">
                          <el-input v-model="scope.row.prod_code"></el-input>
                        </el-form-item>
                      </template>
                    </el-table-column>

                    <el-table-column prop="prod_name" min-width="140" label="商品名称" align="left" show-overflow-tooltip>
                      <template slot-scope="scope">
                        <el-form-item :prop="'applyDetailVOList.'+scope.$index+'.prod_name'"
                                      :rules="ruleDetail.prod_name">
                          <el-input v-model="scope.row.prod_name"></el-input>
                        </el-form-item>
                      </template>
                    </el-table-column>

                    <el-table-column prop="state" min-width="140" label="状态" align="center" show-overflow-tooltip>
                      <template slot-scope="scope">
                        <el-form-item :prop="'applyDetailVOList.'+scope.$index+'.state'" :rules="ruleDetail.state">
                          <el-select v-model="scope.row.state" placeholder="请选择">
                            <el-option label="申请" value="11"></el-option>
                            <el-option label="待下单" value="12"></el-option>
                            <el-option label="已作废" value="13"></el-option>
                            <el-option label="退回" value="14"></el-option>
                            <el-option label="待验收" value="20"></el-option>
                            <el-option label="部分验收" value="21"></el-option>
                            <el-option label="全部验收" value="22"></el-option>
                            <el-option label="退单" value="23"></el-option>
                          </el-select>
                        </el-form-item>
                      </template>
                    </el-table-column>

                  </el-table>
                </el-form>
              </div>
            </el-tab-pane>
          </el-tabs>
        </div>

      </el-dialog>
    </div>

    <!--区域四---预览区域-->
    <div class="show-dialog">
      <el-dialog :visible.sync="dialogView" :show-close="false" :modal="false" fullscreen>
        <div>
          <el-button size="small" @click="cancelView" icon="el-icon-back">返回</el-button>
        </div>

        <el-row class="dialog-formm">
            <div style="margin-top: 42px;">
              <!--申请单-->
              <el-form ref="form" :model="condition" label-width="150px"
                       style="margin-top:10px">

                <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8">
                  <el-form-item label="标题">
                    <span>{{condition.title}}</span>
                  </el-form-item>
                </el-col>

                <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8">
                  <el-form-item label="申请单号">
                    <span>{{condition.apply_code}}</span>
                  </el-form-item>
                </el-col>

                <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8">
                  <el-form-item label="是否签报OA">
                    <span v-if="condition.is_send_oa==1">是</span>
                    <span v-if="condition.is_send_oa==0">否</span>
                  </el-form-item>
                </el-col>

                <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8">
                  <el-form-item label="OA签报号">
                    <span>{{condition.oa_code}}</span>
                  </el-form-item>
                </el-col>

                <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8">
                  <el-form-item label="签报日期">
                    <span>{{condition.sent_oa_date | getLongDate}}</span>
                  </el-form-item>
                </el-col>

                <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8">
                  <el-form-item label="状态">
                    <span v-if="condition.is_send_oa==1">待申请</span>
                    <span v-if="condition.is_send_oa==2">申请中</span>
                    <span v-if="condition.is_send_oa==3">待审核</span>
                    <span v-if="condition.is_send_oa==4">已审核</span>
                    <span v-if="condition.is_send_oa==5">已作废</span>
                  </el-form-item>
                </el-col>

                <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8">
                  <el-form-item label="事由">
                    <el-input readonly type="textarea" v-model="condition.reason"></el-input>
                  </el-form-item>
                </el-col>
              </el-form>
            </div>
        </el-row>

        <!--申请单明细-->
        <div class="dialog-tab">
          <el-tabs v-model="activeName">
            <el-tab-pane label="明细表" name="first">
              <div class="tableBody">
                <el-table ref="tableData" :data="condition.applyDetailVOList" border align="center">
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

    <!--区域五---编辑区域-->
    <div class="show-dialog">
      <el-dialog :visible.sync="dialogEdit" :show-close="false" :modal="false" fullscreen>
        <div>
          <el-button size="small" @click="cancelEdit" icon="el-icon-back">返回</el-button>
          <el-button type="primary" size="small" icon="el-icon-check" @click="sendEdit" v-prevent-re-click>保存
          </el-button>
        </div>

        <el-row class="dialog-formm">
            <div style="margin-top: 42px;">
              <!--申请单-->
              <el-form ref="form" :model="condition" :rules="rules" label-width="150px" size="small"
                       style="margin-top:10px">

                <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8">
                  <el-form-item label="标题" prop="title">
                    <el-input v-model="condition.title"></el-input>
                  </el-form-item>
                </el-col>

                <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8">
                  <el-form-item label="申请单号" prop="apply_code">
                    <el-input v-model="condition.apply_code"></el-input>
                  </el-form-item>
                </el-col>

                <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8">
                  <el-form-item label="是否签报OA">
                    <span v-if="condition.is_send_oa==1">是</span>
                    <span v-if="condition.is_send_oa==0">否</span>
                  </el-form-item>
                </el-col>

                <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8">
                  <el-form-item label="OA签报号">
                    <span>{{condition.oa_code}}</span>
                  </el-form-item>
                </el-col>

                <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8">
                  <el-form-item label="签报日期">
                    <span>{{condition.sent_oa_date | getLongDate}}</span>
                  </el-form-item>
                </el-col>

                <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8">
                  <el-form-item label="状态" prop="state">
                    <el-select v-model="condition.state" placeholder="请选择">
                      <el-option label="待申请" value="1"></el-option>
                      <el-option label="申请中" value="2"></el-option>
                      <el-option label="待审核" value="3"></el-option>
                      <el-option label="已审核" value="4"></el-option>
                      <el-option label="已作废" value="5"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>

                <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8">
                  <el-form-item label="事由">
                    <el-input type="textarea" v-model="condition.reason" readonly></el-input>
                  </el-form-item>
                </el-col>
              </el-form>
            </div>
        </el-row>

        <!--申请单明细-->
        <div class="dialog-tab">
          <el-tabs v-model="activeName">
            <el-tab-pane label="明细表" name="first">
              <div class="tableBody">
                <el-table ref="tableData" :data="condition.applyDetailVOList" border align="center">
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

  </div>

</template>

<script>
  export default {
    name: "ListDemo",
    created() {
      //进行数据的填充工作
      this.findList();
    },
    data() {
      //自定义校验OA签报号
     let validateOaCode = (rule, value, callback) => {
        if (this.condition.is_send_oa === '1'){
          if (value === ''){
            callback(new Error('当确定签报OA时，OA签报号不能为空！'));
          }else{
            callback()
          }
        }else {
          callback()
        }
     }
     //自定义校验签报时间
      let validateSentOaDate = (rule, value, callback) => {
        if (this.condition.is_send_oa === '1'){
          if (value === ''){
            callback(new Error('当确定签报OA时，签报时间不能为空！'));
          }else{
            callback()
          }
        }else {
          callback()
        }
      }
      return {
        multipleSelectionAll: [], // 所有选中的数据包含跨页数据
        multipleSelection: [],//table区域被选中的数据
        idKey: "apply_id", // 标识列表数据中每一行的唯一键的名称
        delVal: [],//明细详情被选中的数据的信息
        dialogView: false,//控制预览区域的显示与隐藏
        dialogEdit: false,//控制编辑区域的显示与隐藏
        hiddenReason: true,//控制reason的隐藏
        activeName: 'first', //被选中面包屑表单名称
        dialogVisible: false,//控制弹窗的显示与隐藏
        tableData: [],//列表展示数据
        //表单参数
        condition: {
          apply_code: "",//申请单号
          apply_date: "", //申请日期
          apply_id: "",//编号（此值不需要从前端传递到后端）
          create_date: "",//创建时间（此值不需要从前端传递到后端）
          creator_id: "",//创建人编号（此值不需要从前端传递到后端）
          del_flag: '0',//删除标记位
          is_send_oa: "",//是否签报OA
          modifier_id: "",//修改人编号
          modify_date: "",//修改时间
          oa_code: "",//OA签报号
          reason: "",//事由
          sent_oa_date: "",//签报日期
          state: "",//状态
          state_desc: "",//状态描述
          title: "",//标题
          applyDetailVOList: []
        },
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
        //校验规则
        rules: {
          title: [
            {required: true, message: '此项不能为空！', trigger: 'blur'}
          ],
          apply_code: [
            {required: true, message: '此项不能为空！', trigger: 'blur'}
          ],
          apply_date: [
            {required: true, message: '此项不能为空！', trigger: 'blur'}
          ],
          is_send_oa: [
            {required: true, message: '此项不能为空！', trigger: 'blur'}
          ],
          oa_code: [
            { validator: validateOaCode, trigger: 'blur' },
          ],
          sent_oa_date: [
            { validator: validateSentOaDate, trigger: 'blur' },
          ],
          state: [
            {required: true, message: '此项不能为空！', trigger: 'blur'}
          ],
          reason: [
            {required: true, message: '此项不能为空！', trigger: 'blur'}
          ]
        },
        //校验明细的规则
        ruleDetail: {
          addr_post_code: [
            {required: true, message: '此项不能为空！', trigger: 'blur'}
          ],
          app_amount: [
            {required: true, message: '此项不能为空！', trigger: 'blur'}
          ],
          app_price: [
            {required: true, message: '此项不能为空！', trigger: 'blur'}
          ],
          prod_code: [
            {required: true, message: '此项不能为空！', trigger: 'blur'}
          ],
          prod_name: [
            {required: true, message: '此项不能为空！', trigger: 'blur'}
          ],
          state: [
            {required: true, message: '此项不能为空！', trigger: ['blur']}
          ],

        },
      }
    },
    methods: {
      //新增
      handleAdd() {
        this.dialogVisible = true;
        this.empty();
        this.$nextTick(() => {
          this.$refs['form'].resetFields();
        })
      },
      //预览
      handleView() {
        if (this.multipleSelection.length == 0) {
          this.$message.error('请选中一条数据！');
        } else if (this.multipleSelection.length == 1) {
          this.watchCondition(this.multipleSelection[0].apply_id)
        } else {
          this.$message.error('只允许选中一条进行预览！')
        }
      },
      //编辑
      handleEdit() {
        if (this.multipleSelection.length == 0) {
          this.$message.error('请选中一条数据！');
        } else if (this.multipleSelection.length == 1) {
          this.editCondition(this.multipleSelection[0].apply_id)
        } else {
          this.$message.error('只允许选中一条进行编辑！')
        }
      },
      //删除
      handleDel() {

      },
      //渲染列表数据
      findList() {
        this.$http.post("apply/queryApply", this.requestData).then(res => {
          console.log(res);
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
        this.findList()
        this.changePageCoreRecordData();
      },
      //当前页发生改变时
      handleCurrentChange(val) {
        this.requestData.pageCondition.pageNo = val
        this.findList()
        this.changePageCoreRecordData();
      },
      //添加明细行
      addList() {
        this.condition.applyDetailVOList.push({
          addr_post_code: "",//收件邮编
          address: "",//地址（作废）
          app_amount: '',//申请数量
          app_price: '',//申请价格
          apply_detail_id: "",//编号（此值不需要从前端传递到后端）
          apply_id: "",//申请表编号（此值不需要从前端传递到后端）
          create_date: "",//创建时间（此值不需要从前端传递到后端）
          creator_id: "",//创建人id（此值不需要从前端传递到后端）
          del_flag: 0,//删除标记位
          modifier_id: "",//修改人编号
          modify_date: "",//修改时间
          prod_code: "",//商品代码
          prod_name: "",//商品名称
          state: "",//状态
          state_desc: "",//状态描述
        })
      },
      //保存数据
      sendForm() {
        this.$refs['formDetail'].validate((valid) => {
          if (valid) {
            this.$refs['form'].validate((valid) => {
              if (valid) {
                this.dialogVisible = false
                this.condition.del_flag = 0
                if (this.condition.applyDetailVOList) {
                  this.condition.applyDetailVOList.forEach((item) => {
                    item.del_flag = 0
                  })
                }
                console.log(this.condition);
                this.$http.post('apply/saveApply', this.condition).then(res => {
                  console.log(res);
                  this.$message.success('保存成功！')
                  this.findList()
                })
              } else {
                this.$message.error("请确认信息输入是否在正确！")
              }
            })
          } else {
            this.$message.error("请确认信息输入是否在正确！")
          }
        })
      },
      //保存编辑的信息
      sendEdit() {
        this.$refs['form'].validate((valid) => {
          if (valid) {
            this.dialogEdit = false
            this.condition.del_flag = 0
            if (this.condition.applyDetailVOList) {
              this.condition.applyDetailVOList.forEach((item) => {
                item.del_flag = 0
              })
            }
            this.$http.post('apply/saveApply', this.condition).then(res => {
              console.log(res);
              this.$message.success('保存成功！')
              this.findList()
            })
          } else {
            this.$message.error("请确认信息输入是否在正确！")
          }
        })
      },
      //清空数据
      empty() {
        this.condition = {
          apply_code: "",
          apply_date: "",
          apply_id: "",
          create_date: "",
          creator_id: "",
          del_flag: "",
          is_send_oa: "",
          modifier_id: "",
          modify_date: "",
          oa_code: "",
          reason: "",
          sent_oa_date: "",
          state: "",
          state_desc: "",
          title: "",
          applyDetailVOList: []
        }
      },
      //选明细列表中的行
      handleSelectionChange(val) {
        this.delVal = val;
        console.log(this.delVal);
      },
      //选中List列表中的行
      handleTableChange(val) {
        this.multipleSelection = val
        console.log(this.multipleSelection);
      },
      //删除选中行
      delList() {
        if (this.delVal.length == 0) {
          return this.$message.warning("请选择一条数据");
        }
        ;
        this.condition.applyDetailVOList = this.minus(this.condition.applyDetailVOList, this.delVal);
        //刷新dom
        this.$forceUpdate();
      },
      //集合相减的方法
      minus(arr1, arr2) {
        let arr3 = [];
        for (let i = 0; i < arr1.length; i++) {
          let flag = true;
          for (let j = 0; j < arr2.length; j++) {
            if (arr1[i] == arr2[j]) flag = false;
          }
          if (flag) {
            arr3.push(arr1[i]);
          }
        }
        return arr3;
      },
      //获取选中内容
      handleChooseData () {
        // 获取之前需要执行一遍记忆分页处理
        this.changePageCoreRecordData();
        this.$alert(`选中条数为:${this.multipleSelectionAll.length}`, '提示', { confirmButtonText: '确定',
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
      //编辑
      editCondition(id) {
        this.dialogEdit = true
        this.$http.get('apply/getApply/' + id).then(res => {
          this.condition = res
        })
      },
      //保存页返回
      cancelSave() {
        this.dialogVisible = false;
        this.empty();
        this.$refs['form'].resetFields();
        this.$refs['formDetail'].resetFields();
      },
      //预览页返回
      cancelView() {
        this.dialogView = false
        this.empty();
      },
      //编辑页返回
      cancelEdit() {
        this.dialogEdit = false
        this.empty();
      },
      //预览
      watchCondition(id) {
        // this.dialogVisible = true
        this.dialogView = true
        this.$http.get('apply/getApply/' + id).then(res => {
          console.log(res);
          this.condition = res
        })
      },
      //按条件查询
      findByTime() {
        if (this.requestData.condition.dateTime) {
          this.requestData.condition.apply_date_from = this.requestData.condition.dateTime[0]
          this.requestData.condition.apply_date_to = this.requestData.condition.dateTime[1]
        }
        this.findList()
      },
      //重置搜索
      restSearch() {
        this.requestData = {
          condition: {
            title: '',
            //时间
            dateTime: '',
            //查询条件
            apply_date_from: "",
            apply_date_to: "",
          },
          pageCondition: {
            //当前页
            pageNo: 1,
            //每页显示条数
            pageSize: 10
          }
        }
        this.findList()
      }
    }
  }
</script>

<style scoped>
</style>
