<template>
    <div class="app-container">
        <div class="query-container">
          <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12"  style="margin-top:10px">
            <el-select v-model="queryContion.status"  placeholder="请选择"   size="small" style="width:25rem;" clearable>
                <el-option
                        v-for="item in statusList"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                ></el-option>
            </el-select>
          </el-col>
          <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12" style="margin-top:10px">
            <el-date-picker
             size="small" style="width:25rem;"
                    v-model="queryContion.createTimeList"
                    type="daterange"
                    align="right"
                    range-separator="至"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期"
                    value-format="yyyy-MM-dd HH:mm:ss"
            ></el-date-picker>
          </el-col>

          <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12" style="margin-top:10px" >
            <el-input v-model="queryContion.testName" placeholder="请输入测试名" size="small" style="width:25rem;" clearable></el-input>
          </el-col>

          <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12" style="margin-top:10px">
            <span>
                  <el-button type="warning" size="small"   @click="search">搜索</el-button>
                  <el-button type="primary" plain size="small"   @click="reset">重置</el-button>
            </span>

          </el-col>


        </div>
        <div class="container">

          <div class="container_btn" >
            <span>
            <el-button type="warning" size="small"   @click="handleAdd">新增</el-button>
            <el-button type="danger" size="small" :disabled="dicDisabled"  @click="handleDeleteBatch">删除</el-button>
            <el-button type="primary" plain size="small"   @click="search">刷新</el-button>
            </span>
          </div>
          <!-- 区域二---表格+分页 -->
          <el-table :data="pageData.list" style="width: 100%" height="100%" :row-style="{height:'50px'}" border class="table" ref="multipleTable" align="center" @selection-change="handleSelectionChange" >
                <el-table-column type="selection"  fixed width="45"  align="center" show-overflow-tooltip></el-table-column>
                <el-table-column type="index" width="55" label="序号" align="center" v-if="xuhao" show-overflow-tooltip></el-table-column>
                <el-table-column prop="createTime" min-width="140" label="创建时间" align="center" show-overflow-tooltip></el-table-column>
                <el-table-column prop="createUser" min-width="140" label="创建人" align="center" show-overflow-tooltip></el-table-column>
                <el-table-column prop="testName" min-width="140" label="测试名" align="center" show-overflow-tooltip></el-table-column>
                <el-table-column prop="testStatus" min-width="140" label="测试状态" align="center" show-overflow-tooltip></el-table-column>
                <el-table-column prop="testType" min-width="140" label="测试类型" align="center" show-overflow-tooltip></el-table-column>

                <el-table-column label="操作" fixed="right" min-width="160" align="center" show-overflow-tooltip>
                    <template slot-scope="scope">
                        <el-button type="text" icon="el-icon-edit" @click="handleEdit(scope.row)" >编辑</el-button>
                        <el-button type="text" icon="el-icon-delete" class="red"  @click="handleDelete(scope.row.id)" >删除</el-button>
                    </template>
                </el-table-column>
            </el-table>

          <div class="pagination">
            <pagination :page-list="pageData" @pagesearch="handlePage"></pagination>
          </div>
        </div>
        <!-- 弹出框--start -->
        <div class="showDialog">
            <el-dialog  :visible.sync="editVisible"  :show-close="false" :modal="false" fullscreen>
              <div class="dialogFix">
                  <el-button type="primary" size="small"  @click="handleSave('form')">保 存</el-button>
                  <el-button size="small" @click="cancelSave">返 回</el-button>
              </div>
              <el-row class="dialogForm">
                  <el-col :span="24">
                    <div style="margin-top: 42px;">
                         <el-form  ref="form" :model="form" :rules="rules" label-width="150px" size="small" style="margin-top:10px">
                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                  <el-form-item label="创建时间" prop="createTime">
                                          <el-date-picker
                                                  v-model="form.createTime"
                                                  type="datetime"
                                                  placeholder="选择日期时间"
                                                  default-time="12:00:00"
                                                  style="width:100%">
                                          </el-date-picker>
                                  </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                <el-form-item label="测试名" prop="testName" >
                                    <el-input v-model="form.testName" ></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                <el-form-item label="测试状态"  >
                                  <el-select v-model="form.testStatus"  placeholder="请选择"  style="width:100%;" >
                                      <el-option
                                              v-for="item in statusList"
                                              :key="item.value"
                                              :label="item.label"
                                              :value="item.value"
                                      ></el-option>
                                  </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="8" :xl="8" >
                                <el-form-item label="测试类型"  >
                                    <el-select v-model="fromTypeList"  placeholder="请选择" multiple style="width:100%;" @change="fromTypeChange">
                                      <el-option
                                        v-for="item in typeList"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value"
                                        >
                                      </el-option>
                                    </el-select>
                                </el-form-item>
                            </el-col>

                          </el-form>
                    </div>
                  </el-col>
              </el-row>


              <div class="tabDiv">
                <el-tabs v-model="activeName">
                  <el-tab-pane label="商品sku" name="first">

                      <div class="buttonSku">
                              <el-button icon="el-icon-plus" size="small" type="primary" @click="addList" ></el-button>
                              <el-button icon="el-icon-minus" size="small" type="danger" @click="delList" ></el-button>
                      </div>
                      <div class="formBody">
                        <el-table @selection-change="handleSelectionChangeDetil" ref="tableData" :data="form.testSysTetailList" border align="center">
                                  <el-table-column type="selection"  fixed width="45"  align="center" show-overflow-tooltip></el-table-column>
                                    <el-table-column type="index" width="55" label="序号" align="center"  show-overflow-tooltip ></el-table-column>

                                    <el-table-column prop="name" min-width="140" label="名称" align="center" show-overflow-tooltip>
                                        <template slot-scope="scope">
                                            <div>
                                                <el-input size="small" v-model="scope.row.name" ></el-input>
                                            </div>
                                        </template>
                                    </el-table-column>


                                    <el-table-column prop="status" min-width="140" label="状态" align="center" show-overflow-tooltip>
                                        <template slot-scope="scope">
                                            <div>
                                              
                                              <el-select v-model="scope.row.status" size="small" placeholder="请选择"  style="width:100%;">
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

                                    <el-table-column prop="money" min-width="140" label="金额" align="center" show-overflow-tooltip>
                                        <template slot-scope="scope">
                                            <div>
                                                <currencyInput v-model="scope.row.money" ></currencyInput>
                                            </div>
                                        </template>
                                    </el-table-column>

                        </el-table>
                      </div>

                  </el-tab-pane>
                </el-tabs>
              </div>
            </el-dialog>
        </div>
        <!-- 弹出框-- -->

    </div>
</template>
<script>
export default {
  data(){
    return{
      // 区域一--start
      queryContent:"",//查询内容
      xuhao:true,
      dicDisabled:true,
      // 区域一--end
      //区域二--表格数据--start
        pageData: {
          list: [],
          pageNumber: 1,
          pageSize: 10,
          totalCount: 0,
          totalPage: 0
        },
        multipleSelection:[],//选中的数据集合
      //区域二--表格数据--end
        
        //区域三--start
        form :{
            createTime: "",
            createUser: "",
            id: "",
            testName: "",
            testStatus: "",
            testType: "",
            testSysTetailList:[]
        },
        queryContion:{
            testName:"",
            status:"",
            typeList:[],
            createTimeList:[]
        },
        fromTypeList:[],
        editVisible:false,
        rules: {

        },
        remnant_fou:200,

        //多选
        typeList: [
            {
              value: '1',
              label: '黄金糕'
            }, {
              value: '2',
              label: '双皮奶'
            }, {
              value: '3',
              label: '蚵仔煎'
            }, {
              value: '4',
              label: '龙须面'
            }, {
              value: '5',
              label: '北京烤鸭'
            }
          ],

          //单选
          statusList: [
            {
              value: '1',
              label: '启用'
            }, {
              value: '2',
              label: '禁用'
            }
          ],
          activeName:'first',
          delVal:"",
        }
  },
  created(){
    this.getdata();
  },
  methods:{
      handleSelectionChangeDetil(val){
          this.delVal = val;
      },
      delList(){
          if(this.delVal.length==0){
              return this.$message.warning("请选择一条数据");
          };
          this.form.testSysTetailList=this.minus(this.form.testSysTetailList,this.delVal);
          //刷新dom
          this.$forceUpdate();
      },

      minus(arr1, arr2) { //集合相减的方法
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
      },

      addList(){
        //添加一列表格
        var aa= {
                id: "",
                name: "",
                status: "",
                money: 0,
            }
        this.form.testSysTetailList.push(aa);
    },

    fromTypeChange(){
      if(!this.fromTypeList){
        this.form.testType="";
      }else{
        this.form.testType=this.fromTypeList.join(",");
      }
    },
    //新增
    handleAdd(){
      this.editVisible=true;
    },
    //批量删除
    handleDeleteBatch(){
      let deletebatch = [];
      this.multipleSelection.forEach(i => {
        deletebatch.push(i.id);
      });
      this.$confirm("此操作将永久删除, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$http .post(this.api.sysTestBatchDelete, deletebatch).then(res => {
              if (res.data.code == "200") {
                this.$message({
                  message: "批量删除数据成功",
                  type: "success"
                });
              }
              this.getdata();
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消批量删除"
          });
        });

    },
    //查询
    search(){
      this.getdata();
    },
    //重置
    reset(){
      this.empty();
      this.getdata();
    },
    //勾选数据集合
    handleSelectionChange(val){
      if (val.length > 0) {
        this.multipleSelection = val;
        this.dicDisabled = false;
      } else {
        this.dicDisabled = true;
      }
    },
    //编辑
    handleEdit(row){
      this.editVisible=true;
      // 不能使用this.form=row;数据会双向绑定
      Object.assign(this.form,row);
      if(!!this.form.testType){
        this.fromTypeList=this.form.testType.split(',');
      }
      this.$http.post(this.api.sysTestDetailQueryByCondition, {
                  condition: {
                      parentId:row.id
                  },
                  number: 1,
                  size: 9999
              })
              .then(res => {
              if (res.data.code == "200") {
                  this.form.testSysTetailList= res.data.body.records;
              }
          });

    },
    //单个删除
    handleDelete(id){
      this.$confirm("此操作将永久删除, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$http.delete(this.api.sysTestDeletebyid + id).then(res => {
            if (res.data.code == "200") {
              this.$message({
                message: "删除数据成功",
                type: "success"
              });
            }
            this.getdata();
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },
    //页数
    handlePage(number, size){
      this.pageData.pageNumber=number;
      this.pageData.pageSize=size;
      this.getdata();
    },
    //表格数据
    getdata(){
      this.$http
        .post(this.api.sysTestQueryByCondition, {
          condition:this.queryContion,
          number: this.pageData.pageNumber,
          size: this.pageData.pageSize
        })
        .then(res => {
          if (res.data.code == "200") {
            this.pageData.list = res.data.body.records;

            this.pageData.totalCount = res.data.body.total;
            this.pageData.totalPage = res.data.body.pages;
          }
        });
    },
    //保存/修改
    handleSave(){
      
        this.$refs['form'].validate(valid => {
          if (valid) {

            if(!!this.form.createTime){
                var d=this.form.createTime;
                this.form.createTime=this.$timeUtils.dateUtils(d);
            }

            this.$http.post(this.api.sysTestSaveOrUpdate, this.form).then(res => {
              if (res.data.code == "200") {
                  this.$message.success(res.data.message);
                  this.editVisible = false;
                  this.empty();
                  this.getdata();
              }
            });
          }else {
            this.$message.success(res.data.message);
          }
        })
    },


    //取消
    cancelSave(){
      this.editVisible=false;
    },
    //箭头剩余字数
    descInput_fou(){
      var txtVal = this.form.remark.length;
      this.remnant_fou = 200 - txtVal;
    },
    //
    empty(){
      this.form={
            createTime: "",
            createUser: "",
            id: "",
            testName: "",
            testStatus: "",
            testType: "",
        }
        this.fromTypeList=[];
        this.queryContion={
            testName:"",
            status:"",
            typeList:[],
            createTimeList:[]
        }
    }


  },
  
}
</script>
<style lang="scss" scope>
//表头高度调整
.el-table__header th {
  padding: 0;
  height: 50px ;
}
// 弹出框样式
.showDialog .el-dialog__body{
  padding: 15px 20px;
}
.query-container{
  border-radius: 5px;
  box-shadow: 1px 1px 3px rgba(0,0.2,0,0.2);
  padding: 7px 10px;
  background: #fff;
  height: 100px;
  margin-bottom: 10px;
}
.dialogForm{
    min-height: 300px;
    width: 100%;

}
.tabDiv{
    min-height: 500px;
    width: 100%;
}
</style>