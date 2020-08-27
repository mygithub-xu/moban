<template>
  <!-- 区域三---弹出框，覆盖全部 -->
  <div class="show-dialog">
    <el-dialog :visible.sync="addVisible" :show-close="false" :modal="false" fullscreen @close="close">

      <!--按键区域-->
      <div class="dialog-button">
        <el-button size="small" @click="cancelSave" icon="el-icon-back">返回</el-button>
        <el-button type="primary" size="small" icon="el-icon-check" @click="sendForm" v-prevent-re-click>保存</el-button>
      </div>

      <!--申请单-->
      <div class="dialog-form">
        <el-form ref="form" :model="editData" :rules="rules" size="small">
          <div class="edit-area">
            <el-form-item label="标题" prop="title">
              <el-input v-model="editData.title"></el-input>
            </el-form-item>

            <el-form-item label="申请单号" prop="apply_code">
              <el-input v-model="editData.apply_code" oninput="value=value.replace(/[^\d]/g,'')"></el-input>
            </el-form-item>

            <el-form-item v-if="flag === '1'" label="是否签报OA" prop="is_send_oa">
              <el-switch
                v-model="editData.is_send_oa"
                active-value="1"
                inactive-value="0"
                active-text="是"
                inactive-text="否">
              </el-switch>
            </el-form-item>

            <el-form-item v-if="flag === '2'" label="是否签报OA">
              <span v-if="editData.is_send_oa==1">是</span>
              <span v-if="editData.is_send_oa==0">否</span>
            </el-form-item>

            <el-form-item v-if="flag === '1'" label="OA签报号" prop="oa_code">
              <el-input v-model="editData.oa_code" oninput="value=value.replace(/[^\d]/g,'')"></el-input>
            </el-form-item>

            <el-form-item v-if="flag === '2'" label="OA签报号">
              <span>{{editData.oa_code}}</span>
            </el-form-item>

            <el-form-item v-if="flag === '1'" label="签报日期" prop="sent_oa_date">
              <el-date-picker type="date" placeholder="选择日期" v-model="editData.sent_oa_date"
                              style="width: 100%;"></el-date-picker>
            </el-form-item>

            <el-form-item v-if="flag === '2'" label="签报日期">
              <span>{{editData.sent_oa_date | getLongDate}}</span>
            </el-form-item>

            <el-form-item label="状态" prop="state2">
              <selection keyword="APPLY_STATE" :sel-data.sync="editData.state"></selection>
            </el-form-item>

            <el-form-item label="事由" prop="reason">
              <el-input v-if="flag=== '1'" type="textarea" v-model="editData.reason"></el-input>
              <el-input v-if="flag=== '2'" type="textarea" v-model="editData.reason" readonly></el-input>
            </el-form-item>
          </div>
        </el-form>
      </div>
      <!--申请单明细-->
      <div class="dialog-tab" >
        <el-tabs v-model="activeName">
          <el-tab-pane label="明细表" name="first">
            <div class="buttonSku">
              <el-button icon="el-icon-plus" size="small" type="primary" @click="addList"></el-button>
              <el-button icon="el-icon-minus" size="small" type="danger" @click="delList"></el-button>
            </div>
            <div class="tableBody">
              <el-form ref="formDetail" :model="editData" :rules="ruleDetail" style="margin-top:10px">
                <el-table ref="tableData" :data="editData.applyDetailVOList" border
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
                        <el-input v-model="scope.row.addr_post_code"
                                  oninput="value=value.replace(/[^\d]/g,'')"></el-input>
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
</template>

<script>
  import selection from "../../../components/common/selection/selection";

  export default {
    name: "Edit",
    components: {selection},
    props: {
      dialogVisible: {
        type: Boolean,
        default: false
      },
      apply_id: {
        type: String,
        default: ''
      }
    },
    data() {
      //自定义校验OA签报号
      let validateOaCode = (rule, value, callback) => {
        if (this.editData.is_send_oa === '1') {
          if (value === '') {
            callback(new Error('当确定签报OA时，OA签报号不能为空！'));
          } else {
            callback()
          }
        } else {
          callback()
        }
      }
      //自定义校验签报时间
      let validateSentOaDate = (rule, value, callback) => {
        if (this.editData.is_send_oa === '1') {
          if (value === '') {
            callback(new Error('当确定签报OA时，签报时间不能为空！'));
          } else {
            callback()
          }
        } else {
          callback()
        }
      }
      return {
        //表单参数
        editData: {
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
          oa_code: [
            {validator: validateOaCode, trigger: 'blur'},
          ],
          sent_oa_date: [
            {validator: validateSentOaDate, trigger: 'blur'},
          ],
          state: [
            {required: true, message: '此项不能为空！', trigger: ['blur', 'change']}
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
            {required: true, message: '此项不能为空！', trigger: ['blur', 'change']}
          ],
        },
        activeName: 'first',
        delVal: [],//明细详情被选中的数据的信息,
        addVisible: false,//控制新增的显示与隐藏
        flag: '0',//控制显示新增页面还是编辑页面 1表示为新增页面 2 表示为编辑页面
      }
    },
    methods: {
      //清空数据
      empty() {
        this.editData = {
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
      //dialog关闭事件触发时执行的方法
      close() {
        this.$emit("update:dialogVisible", false)
      },
      //保存编辑页返回
      cancelSave() {
        this.addVisible = false
        this.empty()
        if (this.flag === '1') {
          this.$refs['form'].resetFields();
          this.$refs['formDetail'].resetFields();
        }
      },
      //保存之前的校验
      sendForm() {
        this.$refs['formDetail'].validate((valid) => {
          if (valid) {
            this.$refs['form'].validate((valid) => {
              if (valid) {
                this.saveData();
              } else {
                this.$message.error("请确认信息输入是否在正确！")
              }
            })
          } else {
            this.$message.error("请确认信息输入是否在正确！")
          }
        })
      },
      //保存数据
      saveData() {
        this.editData.del_flag = 0
        if (this.editData.applyDetailVOList) {
          this.editData.applyDetailVOList.forEach((item) => {
            item.del_flag = 0
          })
        }
        this.$http.post('apply/saveApply', this.editData).then(res => {
          this.$message.success('保存成功！')
          this.$emit("update:dialogVisible", false)
          this.empty()
          this.$parent.query()
        })
      },
      //添加明细行
      addList() {
        this.editData.applyDetailVOList.push({
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
      //删除选中行
      delList() {
        if (this.delVal.length == 0) {
          return this.$message.warning("请选择一条数据");
        }
        ;
        this.editData.applyDetailVOList = this.minus(this.editData.applyDetailVOList, this.delVal);
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
      //选明细列表中的行
      handleSelectionChange(val) {
        this.delVal = val;
      },
      //编辑之前回显数据
      echoData(apply_id) {
        if (apply_id !== '') {
          this.flag = '2'
          this.$http.get('apply/getApply/' + apply_id).then(res => {
            this.editData = res
          })
        } else {
          this.flag = '1'
        }
      }
    },
    watch: {
      dialogVisible: {
        handler(newValue) {
          this.addVisible = newValue
        }
      },
      /*   apply_id:{
           handler(apply_id){
             if (apply_id !== ''){
               this.flag = '2'
               this.$http.get('apply/getApply/' + apply_id).then(res => {
                 this.editData = res
               })
             }else{
               this.flag = '1'
             }
           }
         }*/
    }
  }
</script>

<style scoped>

</style>
