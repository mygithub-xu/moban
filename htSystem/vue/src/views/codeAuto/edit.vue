<template>
  <div class="show-dialog">
    <el-dialog :visible.sync="editVisible" :show-close="false" :modal="false" fullscreen>
         <div class="dialog-button" >
            <el-button   @click="handleSave">保存</el-button>
            <el-button  icon="el-icon-back" @click="back">返回</el-button>
        </div>
        <div>
            <el-form >
                <div class="edit-area">
                  <el-form-item label="表名" prop="tableName">
                      <el-input v-model="form.tableName"></el-input>
                  </el-form-item>
                  
                  <el-form-item label="表类型" prop="tableType">
                      <el-select v-model="form.tableType" placeholder="请选择"  clearable>
                        <el-option
                          v-for="item in xiala.tableTypeList"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value"
                        ></el-option>
                      </el-select>
                  </el-form-item>

                  <template v-if="form.tableType == '2'">
                    <el-form-item label="主表" prop="parentId">
                        <el-select v-model="form.parentId" placeholder="请选择"  clearable>
                          <el-option
                            v-for="item in xiala.tableData"
                            :key="item.id"
                            :label="item.tableName"
                            :value="item.id"
                          ></el-option>
                        </el-select>
                    </el-form-item>
                  </template>

                  <el-form-item label="生成模板" prop="type">
                      <el-select v-model="form.type" placeholder="请选择"  clearable>
                        <el-option
                          v-for="item in xiala.templateList"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value"
                        ></el-option>
                      </el-select>
                  </el-form-item>

                  <el-form-item label="备注" prop="remark">
                      <el-input
                        maxlength="200"
                        :autosize="{ minRows: 3, maxRows: 6}"
                        :rows="3"
                        type="textarea"
                        style="max-width: 35rem;"
                        @input="descInput_fou()"
                        v-model="form.remark"
                      ></el-input>
                      <span>{{remnant_fou}}/200</span>
                  </el-form-item>

                </div>
            </el-form>
        </div>

        <div class="dialog-tab">
          <el-tabs v-model="activeName">
            <el-tab-pane label="字段" name="first">
              <div class="container-btn">
                
                <el-button icon="el-icon-plus"  type="primary" @click="addList"></el-button>
                <el-button icon="el-icon-minus"  type="danger" @click="delList"></el-button>
              </div>
              <div class="tab-table">
                <el-table @selection-change="handleSelectionChangeDetil" ref="tableData" :data="form.testSysTetailList"
                          border align="center">

                  <el-table-column type="selection" fixed width="45" align="center"
                                   show-overflow-tooltip></el-table-column>
                  <el-table-column type="index" width="55" label="#" align="center"
                                   show-overflow-tooltip></el-table-column>

                  <el-table-column prop="fieldName" min-width="200"  align="center" label="字段名" show-overflow-tooltip>
                    <template slot-scope="scope">
                      <div>
                        <el-input  v-model="scope.row.fieldName"></el-input>
                      </div>
                    </template>
                  </el-table-column>

                  <el-table-column prop="fieldType" min-width="200" label="字段类型" align="center" show-overflow-tooltip>
                    <template slot-scope="scope">
                      <div>
                        <el-select v-model="scope.row.fieldType"  placeholder="请选择" style="width:100%;">
                          <el-option
                            v-for="item in xiala.templateList"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value"
                          ></el-option>
                        </el-select>
                      </div>
                    </template>
                  </el-table-column>

                  <el-table-column prop="fieldLength" min-width="200" label="字段长度" align="center">
                    <template slot-scope="scope">
                      <el-input v-model="scope.row.fieldLength"></el-input>
                    </template>
                  </el-table-column>

                  <el-table-column prop="fieldDecimal" min-width="200" label="小数点" align="center">
                    <template slot-scope="scope">
                      <NumberInput v-model="scope.row.fieldDecimal"></NumberInput>
                    </template>
                  </el-table-column>

                  <el-table-column prop="fieldIsNull" min-width="200" label="不是null" align="center">
                    <template slot-scope="scope">
                      <el-checkbox v-model="scope.row.fieldIsNull"></el-checkbox>
                    </template>
                  </el-table-column>

                  <el-table-column prop="fieldPrimary" min-width="200" label="键" align="center">
                    <template slot-scope="scope">
                      <el-checkbox v-model="scope.row.fieldPrimary"></el-checkbox>
                    </template>
                  </el-table-column>

                  <el-table-column prop="fieldDes" min-width="200" label="注释" align="center">
                    <template slot-scope="scope">
                      <el-input v-model="scope.row.fieldDes"></el-input>
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
    name: "edit",
    data() {
      return {
        editVisible:false,
        form: {
            id: "",
            tableName: "",
            tableType: "",
            type: "",
            remark: "",
            testSysTetailList:[]
        },
        remnant_fou: 200,
        activeName: "first"
        

      }
    },
    props:{
      xiala:{
          type:Object,
          require: true
      }
    },
    methods:{
        empty(){
          
        },
        //保存/修改
        handleSave() {
            this.$http.post("/user", this.form).then(res => {
              if (res.data.code == "200") {
                this.$message.success(res.data.message);
                this.empty();
                this.editVisible = false;

              } else {
                this.$message.success(res.data.message);
              }

            });
        },
        handleSelectionChangeDetil(val){
            this.delVal = val;
        },
        delList(){
            if (this.delVal.length == 0) {
              return this.$message.warning("请选择一条数据");
            }

            this.form.testSysTetailList = this.$utils.minus(this.form.testSysTetailList, this.delVal);
            //刷新dom
            this.$forceUpdate();
        },
        addList(){
          var aa = {
            id: "",
            name: "",
            status: "",
            money: 0,
          }
          if (!this.form.testSysTetailList) {
            this.form.testSysTetailList = [];
          }
          this.form.testSysTetailList.push(aa);
          this.$forceUpdate();
        },
        descInput_fou() {
          var txtVal = this.form.remark.length;
          this.remnant_fou = 200 - txtVal;
        },
        back(){
            this.editVisible = false
        },
    }
  }
</script>
<style>

.tab-table .el-table--scrollable-x .el-table__body-wrapper {
    overflow-x: auto;
    min-height: 200px;
}

</style>