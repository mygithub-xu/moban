<template>
  <div class="edit-frame-dialog">
    <el-dialog :visible.sync="editVisible"
               :show-close="false"
               :modal="false"
               fullscreen>
      <!--按钮区域-->
      <div class="dialog-button">
        <el-button @click="backHandle">返回</el-button>
        <template v-for="(item,index) in editList">
          <el-button style="margin-left:10px"
                     :key="'button-'+index"
                     v-if="item.type == '4'">{{item.title}}</el-button>
        </template>
      </div>
      <div class="dialog-form">
        <el-form ref="form"
                 label-width="150px"
                 :rules="rules"
                 :model="form">
          <template v-for="(item,index) in editList">
            <!--普通查询框 -->
            <el-form-item v-if="item.type == '1'"
                          :label="item.title+'：'"
                          :key="index">
              <el-input clearable></el-input>
            </el-form-item>
            <!--下拉选择器 -->
            <el-form-item v-if="item.type == '2'"
                          :label="item.title+'：'"
                          :key="index">
              <el-select v-model="currencyValue"
                         placeholder="请选择"
                         clearable>
                <el-option v-for="item in currencyList"
                           :key="item.id"
                           :label="item.fieldName"
                           :value="item.id"></el-option>
              </el-select>
            </el-form-item>
            <!--时间选择器 -->
            <el-form-item v-if="item.type == '3'"
                          :label="item.title+'：'"
                          :key="index">
              <el-date-picker type="daterange"
                              align="right"
                              range-separator="至"
                              start-placeholder="开始日期"
                              end-placeholder="结束日期"
                              value-format="yyyy-MM-dd HH:mm:ss"></el-date-picker>
            </el-form-item>
          </template>
        </el-form>
      </div>

    </el-dialog>
  </div>
</template>
<script>
export default {
  name: "oneEdit",
  data () {
    return {
      currencyList: [],
      currencyValue: "",
      editVisible: false,
      //表单数据
      form: {},
      //下拉框数据
      selectData: {
      },
      //校验
      rules: {
      }

    }
  },
  props: {
    editList: Array
  },
  methods: {
    //清除校验
    clearCheck () {
      this.$refs['form'].resetFields()
    },
    //清空
    empty () {
      this.form = {
        id: "",
        testName: "",
        testType: "",
        testStatus: "",
        createTime: "",
        createUser: "",
        parentId: "",
        testArea: "",
        testNum: "",
        testUnit: "",
      }
    },
    //新增
    openByNew () {
      this.editVisible = true
      this.empty()
    },
    //返回
    backHandle () {
      this.clearCheck()
      this.editVisible = false
    },
    //保存
    saveHandle () {
    }
  }

}
</script>
<style>
/*弹出框*/
.edit-frame-dialog .el-dialog__wrapper {
  position: absolute !important;
  border-radius: 5px;
}
.edit-frame-dialog .el-dialog__header {
  padding: 0;
}
.edit-frame-dialog .el-dialog {
  background-color: #f3f3f3;
}
.edit-frame-dialog .el-dialog__body {
  padding: 10px;
  border-radius: 5px;
  box-shadow: 1px 1px 3px rgba(0, 0.2, 0, 0.2);
  background: #fff;
}
.edit-frame-dialog .dialog-button {
  margin-bottom: 10px;
}
.edit-frame-dialog .el-form {
  flex-direction: row;
  flex-wrap: wrap;
  display: flex;
}
</style>