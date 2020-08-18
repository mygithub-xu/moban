<template>
  <el-scrollbar style="width: 100%;height: 100%">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px"
             class="demo-ruleForm">
      <el-row>
        <el-col :span="12">
          <el-form-item label="用户名" prop="name">
            <el-input v-model="ruleForm.name"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="年龄" prop="age">
            <el-input v-model.number="ruleForm.age"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="12">
          <el-form-item label="密码" prop="password">
            <el-input :type="passwordVisible" v-model="ruleForm.password" autocomplete="off">
              <i slot="suffix" :class="icon" @mouseover="showPass" @mouseleave="showPass"></i>
            </el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="确认密码" prop="checkPass">
            <el-input :type="passwordVisible" v-model="ruleForm.checkPass" autocomplete="off">
              <i slot="suffix" :class="icon" @mouseover="showPass" @mouseleave="showPass"></i>
            </el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="12">
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="ruleForm.email"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="身份证号" prop="idNum">
            <el-input v-model="ruleForm.idNum" oninput="value=value.replace(/[^\d]/g,'')"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row>
        <el-col :span="12">
          <el-form-item label="手机号" prop="phone">
            <el-input v-model="ruleForm.phone" oninput="value=value.replace(/[^\d]/g,'')"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="金额" prop="money">
            <el-input v-model="ruleForm.money" oninput="value=value.replace(/[^\d]/g,'')"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <h1 class="detail">表单明细</h1>
      <div class="detail-table">
        <el-row>
          <el-col :span="5">
            <p class="detail-name">名称</p>
          </el-col>
          <el-col :span="5">
            <p class="detail-name">状态1</p>
          </el-col>
          <el-col :span="5">
            <p class="detail-name">状态2</p>
          </el-col>
          <el-col :span="5">
            <p class="detail-name">金额</p>
          </el-col>
        </el-row>

        <el-row v-for="(item,index) in ruleForm.childList" :key="item.key">
          <el-col :span="5" :pull="1">
            <el-form-item :prop="'childList.' + index + '.name'">
              <el-input class="detail-el" v-model="item.name"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="5" :pull="1">
            <el-form-item :prop="'childList.' + index + '.status1'">
              <el-select class="detail-el" v-model="item.status1" placeholder="请选择状态">
                <el-option label="状态一" value="1"></el-option>
                <el-option label="状态二" value="2"></el-option>
                <el-option label="状态三" value="3"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="5" :pull="1">
            <el-form-item :prop="'childList.' + index + '.status2'">
              <el-select class="detail-el" v-model="item.status2" placeholder="请选择状态">
                <el-option label="状态一" value="1"></el-option>
                <el-option label="状态二" value="2"></el-option>
                <el-option label="状态三" value="3"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="5" :pull="1">
            <el-form-item :prop="'childList.' + index + '.money'">
              <el-input class="detail-el" v-model="item.money" oninput="value=value.replace(/[^\d]/g,'')"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="4" :push="1" v-if="index === 0">
            <el-button type="primary" @click="addDetail">+</el-button>
          </el-col>

          <el-col :span="4" :push="1" v-else>
            <el-button class="detail-el" type="danger" @click.prevent="removeItem(item)">-</el-button>
          </el-col>
        </el-row>
      </div>

      <el-form-item style="margin-top: 30px">
        <el-button type="primary" @click="addForm()">填充数据</el-button>
        <el-button type="primary" @click="submitForm('ruleForm')" v-preventReClick>立即创建</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
        <el-button @click="aaa()" v-prevent-re-click="5">防止重复提交</el-button>
      </el-form-item>
    </el-form>
  </el-scrollbar>
</template>

<script>
  import CurrencyInput from "@comp/common/input/currencyInput";


  //导入方法
  import {
    validLength,
    validSize,
    validPhoneNum,
    validEmail,
    validIdNum,
    validMoney
  } from "@utils/formValidationUtils";

  export default {
    components: {CurrencyInput},
    data() {

      //用户名长度校验
      let validateName = (rule, value, callback) => {
        validLength(value, 6, 8, callback)
      }

      //年龄校验
      let validateAge = (rule, value, callback) => {
        validSize(value, 10, 200, callback)
      }

      //密码二次输入验证
      let validatePass2 = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.ruleForm.password) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };

      //手机号校验
      let validatePhone = (rule, value, callback) => {
        validPhoneNum(value, callback)
      }

      //邮箱校验
      let validateEmail = (rule, value, callback) => {
        validEmail(value, callback)
      };

      //身份证号校验
      let validateIdNum = (rule, value, callback) => {
        validIdNum(value, callback)
      }

      //金额校验
      let validateMoney = (rule, value, callback) => {
        validMoney(value, callback)
      }

      return {
        //不要修改该变量，如要使用数字类型，请自行定义
        num: 0,
        //表单数据
        ruleForm: {
          name: '',
          password: '',
          age: '',
          checkPass: '',
          phone: '',
          email: '',
          idNum: '',
          money: '',
          childList: [{name: '', status1: '', status2: '', money: '', key: Date.now(), flag: false}]
        },
        //子组件参数
        form_item: [
          {
            type: 'Input',
            prop: 'name',
            label: '用户名',
            placeholder: '请输入用户名',
            width: '200px'
          },
          {
            type: 'Radio',
            options: ['选项一', '选项二'],
            prop: '',
            label: '单选框',
            width: '200px'
          },
        ],
        //密码图标
        icon: "el-icon-view",
        //控制密码显示与隐藏
        passwordVisible: 'password',
        //表单校验规则
        rules: {
          name: [
            {required: true, message: '请输入用户名称', trigger: ['blur']},
            //使用公共方法代替校验长度
            {validator: validateName, trigger: ['blur', 'change']}
          ],

          age: [
            {required: true, message: '请输入年龄名称', trigger: ['blur']},
            //使用公共方法代替校验输入值的大小
            {validator: validateAge, trigger: ['blur', 'change']}
          ],

          password: [
            {required: true, message: '请输入密码名称', trigger: 'blur'},
            {
              pattern: /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$/,
              message: '密码中必须且仅能包含字母、数字，且长度在6-18位之间',
              trigger: ['blur', 'change']
            }
          ],

          checkPass: [
            {validator: validatePass2, trigger: 'blur'}
          ],

          phone: [
            {required: true, message: '请输入手机号', trigger: 'blur'},
            //自定义手机号校验
            {validator: validatePhone, trigger: ['blur', 'change']}
          ],

          email: [
            {required: true, message: '请输入邮箱', trigger: 'blur'},
            //自定义邮箱校验
            {validator: validateEmail, trigger: ['blur', 'change']}
          ],

          idNum: [
            {required: true, message: '请输入身份证号', trigger: 'blur'},
            {validator: validateIdNum, trigger: ['blur', 'change']}
          ],

          money: [
            {required: true, message: '请输入金额', trigger: 'blur'},
            {validator: validateMoney, trigger: ['blur', 'change']}

          ],
          'childList.0.name': [
            {required: true, message: '名称不能为空', trigger: 'blur'},
            {
              validator: validateName, trigger: ['blur', 'change']
            }
          ],
          'childList.0.status1': [
            {required: true, message: '状态1不能为空', trigger: 'blur'}
          ],
          'childList.0.status2': [
            {
              validator: (rule, value, callback) => {
                if ('3' === this.ruleForm.childList[0].status1) {
                  return callback()
                } else {
                  if (value === '') {
                    return callback('状态2不能为空')
                  } else {
                    return callback()
                  }
                }
              }, trigger: ['blur', 'change']
            }
          ],
          'childList.0.money': [
            {required: true, message: '金额不能为空', trigger: 'blur'}
          ]
        }
      };
    },
    methods: {
      //填充数据
      addForm() {
        this.ruleForm.name = '丁文军2222'
        this.ruleForm.age = '22'
        this.ruleForm.password = 'ding1998713'
        this.ruleForm.checkPass = 'ding1998713'
        this.ruleForm.phone = '15155170385'
        this.ruleForm.email = '2804345779@qq.com'
        this.ruleForm.idNum = '342401199809017632'
        this.ruleForm.money = '200'
        this.ruleForm.name = '丁文军2222'
        this.ruleForm.childList = [{
          name: '丁文军zzzzz',
          status1: '1',
          status2: '2',
          money: '300',
          key: Date.now(),
          flag: false
        }]
      },
      //提交表单
      submitForm(formName) {
        if (this.ruleForm.childList.length === 0) {
          this.$message.error('至少需要填写一个表单明细！')
          return false
        }
        this.$refs[formName].validate((valid) => {
          console.log(valid);
          if (valid) {
            // alert('submit!');
            this.$message.success("提交成功！")
          } else {
            this.$message.error("信息填写有误，请重新填写！")
            // console.log('error submit!!');
            return false;
          }
        });

      },
      //重置表单
      resetForm(formName) {
        this.isDisabled = false
        this.$refs[formName].resetFields();
        this.ruleForm = {
          name: '',
          password: '',
          age: '',
          checkPass: '',
          phone: '',
          email: '',
          idNum: '',
          money: '',
          childList: [{name: '', status1: '', status2: '', money: '', key: Date.now()}]
        }
      },
      showPass() {
        if (this.passwordVisible === "text") {
          this.passwordVisible = "password";
          //更换图标
          this.icon = "el-icon-view";
        } else {
          this.passwordVisible = "text";
          this.icon = "el-icon-lock";
        }
      },
      //添加明细项
      addDetail() {
        this.ruleForm.childList.push({
          name: '',
          status1: '',
          status2: '',
          money: '',
          key: Date.now(),
          flag: false
        });
        this.num = this.num + 1
        //添加明细名称的校验规则
        this.rules['childList.' + this.num + '.name'] = this.rules["childList.0.name"]
        //添加明细状态1的校验规则
        this.rules['childList.' + this.num + '.status1'] = this.rules["childList.0.status1"]
        //添加明细状态2的校验规则
        const a = this.num
        this.rules['childList.' + this.num + '.status2'] = [
          {
            validator: (rule, value, callback) => {
              if ('3' === this.ruleForm.childList[a].status1) {
                return callback()
              } else {
                if (value === '') {
                  return callback('状态2不能为空')
                } else {
                  return callback()
                }
              }
            }, trigger: ['blur', 'change']
          }
        ]
        //添加金额的校验规则
        this.rules['childList.' + this.num + '.money'] = this.rules["childList.0.money"]
      },
      //删除明细项
      removeItem(item) {
        //判断传入的对象在数组中在第几个元素
        let index = this.ruleForm.childList.indexOf(item)
        //index = -1表示其在数组中不存在
        if (index !== -1) {
          //去除数组中的元素
          this.ruleForm.childList.splice(index, 1)
        }
      },
      aaa() {
        console.log('1111')
      },

    }
  }
</script>

<style scoped>
  .detail-name {
    text-align: center;
    font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
    line-height: 50px;
  }

  .detail-table {
    border: #878784 1px solid;
    border-radius: 4px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1)
  }

  .detail {
    line-height: 50px;
  }

  .demo-ruleForm{
    margin-top: 40px;
  }
</style>
