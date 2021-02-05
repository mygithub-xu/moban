<template>
  <div class="show-dialog">
    <el-dialog :visible.sync="editVisible" :show-close="false" :modal="false" fullscreen>

        <div class="dialog-button" >
            <el-button  @click="handleSave">保存</el-button>
            <el-button  icon="el-icon-back" @click="back">返回</el-button>
        </div>
        
        <div>
            <el-form :model="form" ref="tableForm" label-width="120px" :rules="rules">
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

                  <el-form-item label="备注" prop="remark" style="width:50%">
                      <el-input
                        maxlength="200"
                        :autosize="{ minRows: 3, maxRows: 4}"
                        :rows="3"
                        type="textarea"
                        style="min-width: 300px;"
                        @input="descInput_fou()"
                        v-model="form.remark"
                        show-word-limit
                      ></el-input>
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
                <el-table @selection-change="handleSelectionChangeDetil" ref="tableData" :data="form.autoFieldList"
                          border align="center">

                  <el-table-column :selectable="checkSelect" type="selection"  width="45" align="center"
                                   show-overflow-tooltip></el-table-column>
                  <el-table-column type="index" width="45" label="#" align="center"
                                   ></el-table-column>

                  <el-table-column prop="fieldName" min-width="200"  align="center" label="字段名" >
                    <template slot-scope="scope">
                      <div>
                        <el-input  v-model="scope.row.fieldName" :disabled="isNotId(scope.row)"></el-input>
                      </div>
                    </template>
                  </el-table-column>

                  <el-table-column prop="fieldType" min-width="200" label="字段类型" align="center" >
                    <template slot-scope="scope">
                      <div>
                        <el-select v-model="scope.row.fieldType" filterable  placeholder="请选择" :disabled="isNotId(scope.row)" style="width:100%;" clearable >
                          <el-option
                            v-for="item in xiala.fieldTypeList"
                            :key="item.label"
                            :label="item.label"
                            :value="item.label"
                          ></el-option>
                        </el-select>
                      </div>
                    </template>
                  </el-table-column>

                  <el-table-column prop="fieldLength" min-width="200" label="字段长度" align="center">
                    <template slot-scope="scope">
                      <NumberInput v-model="scope.row.fieldLength" :disabled="isNotId(scope.row)"></NumberInput>
                    </template>
                  </el-table-column>

                  <el-table-column prop="fieldDecimal" min-width="200" label="小数点" align="center">
                    <template slot-scope="scope">
                      <NumberInput v-model="scope.row.fieldDecimal" :disabled="isNotId(scope.row)"></NumberInput>
                    </template>
                  </el-table-column>

                  <el-table-column prop="fieldIsNullBoo" min-width="90" label="不是null" align="center">
                    <template slot-scope="scope">
                      <el-checkbox v-model="scope.row.fieldIsNullBoo" :disabled="isNotId(scope.row)"></el-checkbox>
                    </template>
                  </el-table-column>

                  <el-table-column prop="fieldPrimaryBoo" min-width="90" label="键" align="center">
                    <template slot-scope="scope">
                      <el-checkbox v-model="scope.row.fieldPrimaryBoo" :disabled="isNotId(scope.row)"></el-checkbox>
                    </template>
                  </el-table-column>

                  <el-table-column prop="fieldDes" min-width="200" label="注释" align="center">
                    <template slot-scope="scope">
                      <el-input v-model="scope.row.fieldDes" :disabled="isNotId(scope.row)"></el-input>
                    </template>
                  </el-table-column>

                  <el-table-column prop="dicId" min-width="200" label="字典" align="center">
                    <template slot-scope="scope">
                      <div>
                        <el-select v-model="scope.row.dicId" filterable  placeholder="请选择" :disabled="isNotId(scope.row)" clearable >
                          <el-option
                            v-for="item in xiala.dicList"
                            :key="item.id"
                            :label="item.dicType"
                            :value="item.id"
                          >
                          <span style="float: left">{{ item.dicType }}</span>
                          <span style="float: right; color: #8492a6; font-size: 13px">{{ item.description }}</span>
                          </el-option>
                          
                        </el-select>
                      </div>
                    </template>
                  </el-table-column>
                  <el-table-column prop="fieldIsBeRelatedBoo" min-width="90" label="是否关联表" align="center">
                    <template slot-scope="scope">
                      <el-checkbox v-model="scope.row.fieldIsBeRelatedBoo" :disabled="isNotId(scope.row)"></el-checkbox>
                    </template>
                  </el-table-column>
                  <el-table-column prop="fieldRelatedTableName" min-width="200" label="关联表名" align="center">
                    <template slot-scope="scope">
                      <el-input v-model="scope.row.fieldRelatedTableName" :disabled="isNotId(scope.row)||!scope.row.fieldIsBeRelatedBoo"></el-input>
                    </template>
                  </el-table-column>
                  <el-table-column prop="fieldRelatedField" min-width="200" label="关联字段" align="center">
                    <template slot-scope="scope">
                      <el-input v-model="scope.row.fieldRelatedField" :disabled="isNotId(scope.row)||!scope.row.fieldIsBeRelatedBoo"></el-input>
                    </template>
                  </el-table-column>
                  <el-table-column prop="fieldRelatedFieldShow" min-width="200" label="关联字段别名" align="center">
                    <template slot-scope="scope">
                      <el-input v-model="scope.row.fieldRelatedFieldShow" :disabled="isNotId(scope.row)||!scope.row.fieldIsBeRelatedBoo"></el-input>
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
            autoFieldList:[]
        },
        remnant_fou: 200,
        activeName: "first",
        rules:{
          tableName: [
            { required: true, message: '表名不能为空', trigger: 'blur' }
          ],
          tableType:[
            { required: true, message: '表类型不能为空', trigger: 'blur' }
          ]
        },
      }
    },
    computed:{
      isNotId(){
        return (row) => {
          return row.fieldName == 'id'
        }
      }
    },
    props:{
      xiala:{
          type:Object,
          require: true
      }
    },
    methods:{
      //id无法选择
      checkSelect(row,index){
        if(row.fieldName == "id"){
          return false;
        }
        return true;
      },
      //编辑时初始化数据
      editInit(row){
        Object.assign(this.form,row);
        this.$http.get(this.api.sysAutoTableFindByID + row.id).then(res => {
          if (res.data.code == "200") {
            this.form = res.data.body
          } else {
            this.empty()
            this.$message.warning("获取子数据失败");
          }
          this.editVisible = true;
        });
        this.editVisible = true;
      },
      //新增时
      addInit(){
        this.empty()
        this.editVisible = true;
      },
      //初始化数据
      empty(){
        this.form = {
          id: "",
          tableName: "",
          tableType: "",
          type: "",
          remark: "",
          autoFieldList:[]
        }
        this.form.autoFieldList = [
          {
            fieldName: "id",
            fieldType: "VARCHAR",
            fieldLength: 36,
            fieldDecimal: 0,
            fieldIsNullBoo: true,
            fieldPrimaryBoo: true,
            fieldDes: "主键",
            fieldIndex: "0",
            dicId:"",
            fieldIsBeRelatedBoo:false,
            fieldRelatedTableName:"",
            fieldRelatedField:"",
            fieldRelatedFieldShow:"",
          },
          {
            fieldName: "create_time",
            fieldType: "DATETIME",
            fieldLength: 0,
            fieldDecimal: 0,
            fieldIsNullBoo: false,
            fieldPrimaryBoo: false,
            fieldDes: "创建时间",
            fieldIndex: "1",
            dicId:"",
            fieldIsBeRelatedBoo:false,
            fieldRelatedTableName:"",
            fieldRelatedField:"",
            fieldRelatedFieldShow:"",
          },
          {
            fieldName: "create_user",
            fieldType: "VARCHAR",
            fieldLength: 36,
            fieldDecimal: 0,
            fieldIsNullBoo: false,
            fieldPrimaryBoo: false,
            fieldDes: "创建人",
            fieldIndex: "2",
            dicId:"",
            fieldIsBeRelatedBoo:false,
            fieldRelatedTableName:"",
            fieldRelatedField:"",
            fieldRelatedFieldShow:"",
          },
          {
            fieldName: "update_time",
            fieldType: "DATETIME",
            fieldLength: 0,
            fieldDecimal: 0,
            fieldIsNullBoo: false,
            fieldPrimaryBoo: false,
            fieldDes: "更新时间",
            fieldIndex: "3",
            dicId:"",
            fieldIsBeRelatedBoo:false,
            fieldRelatedTableName:"",
            fieldRelatedField:"",
            fieldRelatedFieldShow:"",
          },
          {
            fieldName: "update_user",
            fieldType: "VARCHAR",
            fieldLength: 36,
            fieldDecimal: 0,
            fieldIsNullBoo: false,
            fieldPrimaryBoo: false,
            fieldDes: "更新人",
            fieldIndex: "4",
            dicId:"",
            fieldIsBeRelatedBoo:false,
            fieldRelatedTableName:"",
            fieldRelatedField:"",
            fieldRelatedFieldShow:"",
          }
        ]
      },
      //保存/修改
      handleSave() {
          this.$confirm("确定是否生成表", "提示", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
            }).then(() => {
                  this.saveTableData()
            }).catch(() => {
                this.$message.info("已取消");
            });
        

        // this.$refs.tableForm.validate((valid) => {
        //   if(valid){
        //     this.$http.post(this.api.sysAutoTableExistable, this.form).then(res => {
        //       if (res.data.code == "200") {
        //         this.saveTableData()
        //       }
              
        //     });
        //   }
        // })

      },
      saveTableData(){
          this.$http.post(this.api.sysAutoTableSaveOrUpdate, this.form).then(res => {
            if (res.data.code == "200") {
              this.$message.success(res.data.message);
              this.editVisible = false;
              this.$parent.getdata();
            } else {
              this.$message.warning(res.data.message);
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
          this.form.autoFieldList = this.$utils.minus(this.form.autoFieldList, this.delVal);
          //刷新dom
          this.$forceUpdate();
      },
      addList(){

        if (!this.form.autoFieldList) {
          this.form.autoFieldList = [];
        }
        var aa = {
          fieldName: "",
          fieldType: "",
          fieldLength: '',
          fieldDecimal: 0,
          fieldIsNullBoo: false,
          fieldPrimaryBoo: false,
          fieldDes: "",
          dicId:"",
          fieldIsBeRelatedBoo:false,
          fieldRelatedTableName:"",
          fieldRelatedField:"",
          fieldRelatedFieldShow:"",
          fieldIndex:this.form.autoFieldList.length+1
        }
        this.form.autoFieldList.push(aa);
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
.tab-table{
  margin-top: 10px;
}
.tab-table .el-table--scrollable-x .el-table__body-wrapper {
    overflow-x: auto;
    min-height: 200px;
}

</style>