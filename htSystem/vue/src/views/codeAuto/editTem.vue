<template>
    <div class="edit-frame-dialog">
        <el-dialog :visible.sync="editVisible" :show-close="false" :modal="false" fullscreen>
            <!--按钮区域-->
            <div class="dialog-button">
                <el-button  @click="backHandle">返回</el-button>
                <el-button type="primary" @click="saveHandle">保存</el-button>
            </div>
            <div class="dialog-form">
                <el-form ref="form" label-width="150px" :rules="rules" :model="form">
                    <el-form-item label="测试名" prop="testName">
                        <el-input v-model="form.testName"></el-input>
                    </el-form-item>

                    <el-form-item label="时间测试" prop="createTime">
                        <el-date-picker value-format="yyyy-MM-dd HH:mm:ss" v-model="form.createTime" type="datetime" placeholder="选择日期时间"></el-date-picker>
                    </el-form-item>

                    <el-form-item label="选择框测试" prop="testSelect">
                        <el-input v-model="form.testSelect"></el-input>
                    </el-form-item>
                </el-form>
            </div>

    <!-- <div class="dialog-tab">
          <el-tabs v-model="activeName">
            <el-tab-pane label="明细表" name="first">
              <div class="container-btn">
                <el-button icon="el-icon-plus"  type="primary" @click="addList"></el-button>
                <el-button icon="el-icon-minus"  type="danger" @click="delList"></el-button>
              </div>
              <div class="tab-table">
                <el-table @selection-change="handleSelectionChangeDetil" ref="tableData" :data="form.testSysTetailList"
                          border align="center">

                  <el-table-column type="selection" fixed width="45" align="center"
                                   show-overflow-tooltip></el-table-column>
                  <el-table-column type="index" width="55" label="序号" align="center"
                                   show-overflow-tooltip></el-table-column>

                  <el-table-column prop="name" min-width="200" label="名称" show-overflow-tooltip>
                    <template slot-scope="scope">
                      <div>
                        <el-input  v-model="scope.row.name"></el-input>
                      </div>
                    </template>
                  </el-table-column>
                  <el-table-column prop="status" min-width="200" label="状态" align="center" show-overflow-tooltip>
                    <template slot-scope="scope">
                      <div>
                        <el-select v-model="scope.row.status"  placeholder="请选择" style="width:100%;">
                          <el-option
                            v-for="item in statusList"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value"
                          ></el-option>
                        </el-select>

                      </div>
                    </template>
                  </el-table-column>

                  <el-table-column prop="money" min-width="200" label="金额" align="center">
                    <template slot-scope="scope">
                      <currencyInput v-model="scope.row.money"></currencyInput>
                    </template>
                  </el-table-column>


                  <el-table-column min-width="200" label="表格一" align="center" show-overflow-tooltip>
                    表格一
                  </el-table-column>
                </el-table>
              </div>
            </el-tab-pane>
          </el-tabs>
        </div> -->

        </el-dialog>
    </div>
</template>
<script>
export default {
    name: "oneEdit",
    data() {
      return {
        editVisible: false,
        //表单数据
        form: {},
        //下拉框数据
        selectData: {
        },
        //校验
        rules:{
        }
        
      }
    },
    methods:{
        //清除校验
        clearCheck(){
            this.$refs['form'].resetFields()
        },
        //清空
        empty(){
            this.form = {
                id:"",
                testName:"",
                testType:"",
                testStatus:"",
                createTime:"",
                createUser:"",
                parentId:"",
                testArea:"",
                testNum:"",
                testUnit:"",
            }
        },
        //新增
        openByNew(){
            this.editVisible = true
            this.empty()
        },
        //返回
        backHandle(){
            this.clearCheck()
            this.editVisible = false
        },
        //保存
        saveHandle(){
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
.edit-frame-dialog .el-dialog__header{
  padding: 0;
}
.edit-frame-dialog .el-dialog{
  background-color: #f3f3f3;
}
.edit-frame-dialog .el-dialog__body{
  padding: 10px;
  border-radius: 5px;
  box-shadow: 1px 1px 3px rgba(0,0.2,0,0.2);
  background: #fff;
}
.edit-frame-dialog .dialog-button{
  margin-bottom: 10px;
}
.edit-frame-dialog .el-form{
  flex-direction: row;
  flex-wrap: wrap;
  display: flex;
}
</style>