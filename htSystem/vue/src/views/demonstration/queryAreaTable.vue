<template>
    <div class="app-container">
      
        <div class="container-query">
          <el-scrollbar class="query-scrollbar">

            <el-form  label-width="100px">
                <el-form-item label="测试状态：" >
                  <el-select v-model="queryContion.status"  placeholder="请选择"   size="small" clearable>
                      <el-option
                              v-for="item in statusList"
                              :key="item.value"
                              :label="item.label"
                              :value="item.value"
                      ></el-option>
                  </el-select>
                </el-form-item>

                <el-form-item label="创建日期：" >
                  <el-date-picker
                  size="small"
                          v-model="queryContion.createTimeList"
                          type="daterange"
                          align="right"
                          range-separator="至"
                          start-placeholder="开始日期"
                          end-placeholder="结束日期"
                          value-format="yyyy-MM-dd HH:mm:ss"
                  ></el-date-picker>
                </el-form-item>
                <el-form-item label="测试名：" >
                    <el-input size="small" v-model="queryContion.testName"  placeholder="请输入测试名"></el-input>
                </el-form-item>
                <el-form-item >
                  <span >
                    <el-button type="primary"  size="small"   @click="search" icon="el-icon-search">搜索</el-button>
                    <el-button  size="small" plain  @click="reset" icon="el-icon-refresh">重置</el-button>
                  </span>
                </el-form-item>
            </el-form>
          </el-scrollbar>

        </div>

        <div class="container-table">
          <div>
            <span>
              <el-button type="primary"  size="small" icon="el-icon-plus"  @click="handleAdd">新增</el-button>
              <el-button type="primary"  size="small" icon="el-icon-edit" @click="handleEdit">编辑</el-button>
              <el-button type="danger" size="small" icon="el-icon-delete"  @click="handleDeleteBatch">删除</el-button>
              <el-button type="primary"  size="small" icon="el-icon-view" >预览</el-button>
              <el-button size="small"  @click="handleExport" icon="el-icon-download">导出</el-button>
              <uploadFile   @click="handleImport" :uploadUrl="uploadUrl" icon="el-icon-upload2"></uploadFile>
              <el-button size="small"  icon="el-icon-coordinate">审批</el-button> <!-- el-icon-s-check -->
              <el-button size="small"  icon="el-icon-printer">打印</el-button>
              <el-button size="small" @click="cancelSave" icon="el-icon-close">取消</el-button>
            </span>
          </div>

          <div class="common-table-style">
            <el-table :data="pageData.list" border align="center" @selection-change="handleSelectionChange" :row-style="{height:'50px'}"  height="100%" >
                  <el-table-column fixed type="selection" width="50" align="center"> </el-table-column>
                  <el-table-column type="index" width="46" align="center" label="序号"></el-table-column>
                  <el-table-column prop="createTime" min-width="140" label="创建时间" align="center" show-overflow-tooltip></el-table-column>
                  <el-table-column prop="testName" min-width="140" label="测试名"  show-overflow-tooltip></el-table-column>
                  <el-table-column prop="testStatus" min-width="140" label="测试状态" align="center" show-overflow-tooltip>
                    <template  slot-scope="scope">
                      <span v-if="scope.row.testStatus=='1'"  >启用</span>
                      <span v v-if="scope.row.testStatus=='2'"  >禁用</span>
                    </template>
                  </el-table-column>

                  <el-table-column prop="testNum" min-width="140" label="数量" align="right" show-overflow-tooltip>
                     <template slot-scope="scope">
                       <span>{{scope.row.testNum | 10}}</span>
                     </template>
                  </el-table-column>
                  <el-table-column prop="testUnit" min-width="140" label="单价" align="right" show-overflow-tooltip>
                      <template slot-scope="scope">
                       <span>{{scope.row.testUnit | 1000}}</span>
                     </template>
                  </el-table-column>
                  <el-table-column prop="testTotal" min-width="140" label="总额" align="right" show-overflow-tooltip>
                      <template slot-scope="scope">
                       <span>{{scope.row.testTotal | 1000}}</span>
                     </template>
                  </el-table-column>

                  <el-table-column  min-width="140" label="表格一" show-overflow-tooltip>
                    表格一
                  </el-table-column>
                  <el-table-column  min-width="140" label="表格二"  show-overflow-tooltip>
                    表格二
                  </el-table-column>
                  <el-table-column prop="testType" min-width="140" label="表格三" show-overflow-tooltip>
                    表格三
                  </el-table-column>
                  <el-table-column prop="testType" min-width="140" label="表格四"  show-overflow-tooltip>
                    表格四
                  </el-table-column>
                  <el-table-column prop="testType" min-width="140" label="表格五" show-overflow-tooltip>
                    表格五
                  </el-table-column>
              </el-table>
            </div>

          <div class="pagination">
            <pagination :page-list="pageData" @pagesearch="handlePage"></pagination>
          </div>

        </div>
 
        <div class="showDialog">
            <el-dialog  :visible.sync="editVisible"  :show-close="false" :modal="false" fullscreen>
              <div >
                <el-button size="small" @click="cancelSave" icon="el-icon-back">返回</el-button>
                <el-button type="primary" size="small" icon="el-icon-check" @click="handleSave('form')">保存</el-button>
              </div>

              <el-row class="dialogForm">
                    <div class="tableFrom">
                         <el-form  ref="form" :model="form" :rules="rules" label-width="100px" size="small" style="margin-top:10px">
                            <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12" style="height:45px">
                                  <el-form-item label="创建时间" prop="createTime" style="width:32.5rem;" >
                                          <el-date-picker
                                                  v-model="form.createTime"
                                                  type="datetime"
                                                  placeholder="选择日期时间"
                                                  style="width:100%">
                                          </el-date-picker>
                                  </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12" style="height:45px">
                                <el-form-item label="测试名" prop="testName" style="width:32.5rem;">
                                    <el-input v-model="form.testName"   ></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12" >
                                <el-form-item label="测试状态"  style="width:32.5rem;">
                                  <!-- <el-select v-model="form.testStatus"  placeholder="请选择"  style="width:100%;" >
                                      <el-option
                                              v-for="item in statusList"
                                              :key="item.value"
                                              :label="item.label"
                                              :value="item.value"
                                      ></el-option>
                                  </el-select> -->
                                <el-radio-group v-model="form.testStatus" size="small"  style="height:45px">
                                  <el-radio v-for="item in statusList" :key="item.value" :label="item.value" border size="medium">{{item.label}}</el-radio>
                                </el-radio-group>

                                </el-form-item>
                            </el-col>
                            <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12">
                                <el-form-item label="测试类型"  style="width:45rem;">
                                    <!-- <el-select v-model="fromTypeList"  placeholder="请选择" multiple style="width:100%;" @change="fromTypeChange">
                                      <el-option
                                        v-for="item in typeList"
                                        :key="item.value"
                                        :label="item.label"
                                        :value="item.value"
                                        >
                                      </el-option>
                                    </el-select> -->
                                        
                                <el-checkbox v-for="item in typeList" :key="item.value" :label="item.label" v-model="item.checked" border @change="changebox"></el-checkbox>
                                        
                                </el-form-item>

                            </el-col>

                            <el-col :xs="24" :sm="24" :md="20" :lg="20" :xl="20" >
                                <el-form-item label="测试文本" prop="testArea" style="width:600px;margin-top:5px">
                                  

                                    <el-input maxlength="200"  :rows="4" type="textarea" style="max-width: 35rem;" 
                                    @input="descInput_fou()" v-model="form.testArea"  ></el-input>
                                    <span style="float: right;">{{remnant_fou}}/200</span>
                                </el-form-item>
                            </el-col>


                            <el-col :xs="24" :sm="24" :md="20" :lg="20" :xl="20" >
                                <el-form-item label="附件" style="width:600px;margin-top:5px">
                                  <el-upload
                                      class="upload-demo"
                                      action="https://jsonplaceholder.typicode.com/posts/"
                                      multiple
                                      :limit="3"
                                      :file-list="fileList">
                                      <el-button size="small" type="primary">点击上传</el-button>
                                      <!-- <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div> -->
                                    </el-upload>
                                </el-form-item>
                            </el-col>
                          </el-form>
                    </div>
              </el-row>


              <div class="tabDiv">
                <el-tabs v-model="activeName">
                  <el-tab-pane label="明细表" name="first">

                      <div class="buttonSku">
                              <el-button icon="el-icon-plus" size="small" type="primary" @click="addList" ></el-button>
                              <el-button icon="el-icon-minus" size="small" type="danger" @click="delList" ></el-button>
                      </div>
                      <div class="tableBody">
                        <el-table @selection-change="handleSelectionChangeDetil" ref="tableData" :data="form.testSysTetailList" border align="center" >
                        
                                  <el-table-column type="selection"  fixed width="45"  align="center" show-overflow-tooltip></el-table-column>
                                    <el-table-column type="index" width="55" label="序号" align="center"  show-overflow-tooltip ></el-table-column>

                                    <el-table-column prop="name" min-width="200" label="名称" show-overflow-tooltip>
                                        <template slot-scope="scope">
                                            <div>
                                                <el-input size="small" v-model="scope.row.name" ></el-input>
                                            </div>
                                        </template>
                                    </el-table-column>


                                    <el-table-column prop="status" min-width="200" label="状态" align="center" show-overflow-tooltip>
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

                                    <el-table-column prop="money" min-width="200" label="金额" align="center" >
                                        <template slot-scope="scope">
                                              <currencyInput v-model="scope.row.money" ></currencyInput>
                                        </template>
                                    </el-table-column>


                  <el-table-column  min-width="200" label="表格一" align="center" show-overflow-tooltip>
                    表格一
                  </el-table-column>
                  <el-table-column  min-width="200" label="表格二" align="center" show-overflow-tooltip>
                    表格二
                  </el-table-column>
                  <el-table-column  min-width="200" label="表格三" align="center" show-overflow-tooltip>
                    表格三
                  </el-table-column>
                  <el-table-column  min-width="200" label="表格四" align="center" show-overflow-tooltip>
                    表格四
                  </el-table-column>
                  <el-table-column  min-width="200" label="表格五" align="center" show-overflow-tooltip>
                    表格五
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
  name:"queryAreaTable2",
  data(){
    return{
      // 区域一--start
      uploadUrl:"http://127.0.0.1:4040/moban/api/test/sysTest/importExcel",
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
              label: '黄金糕',
              checked:true,
              
            }, {
              value: '2',
              label: '双皮奶',
              checked:false,
            }, {
              value: '3',
              label: '蚵仔煎',
              checked:true,
            }, {
              value: '4',
              label: '龙须面',
              checked:true,
            }, {
              value: '5',
              label: '北京烤鸭',
              checked:true,
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
          fileList:[]
        }
  },
  created(){
    this.getdata();
  },
  methods:{
    changebox(item){
    },
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
            if(!this.form.testSysTetailList){
              this.form.testSysTetailList=[];
            }
        this.form.testSysTetailList.push(aa);
        this.$forceUpdate();
    },

    fromTypeChange(){
      if(!this.fromTypeList){
        this.form.testType="";
      }else{
        this.form.testType=this.fromTypeList.join(",");
        this.form.testType=this.form.testType.map(item => ({
                        label: item.storeName,
                        value: item.id,
                        checked: false
                    }));
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
    handleEdit(){

      if(this.multipleSelection.length!=1){
          return this.$message({ type: "warning", message: "请选择一条数据"});
      }

      this.editVisible=true;
      // 不能使用this.form=row;数据会双向绑定
      Object.assign(this.form,this.multipleSelection[0]);
      if(!!this.form.testType){
        this.fromTypeList=this.form.testType.split(',');
      }

      this.$nextTick(() => {

          this.$http.post(this.api.sysTestDetailQueryByCondition, {
                  condition: {
                      parentId:row.id
                  },
                  number: 1,
                  size: 9999
                  }).then(res => {
                  if (res.data.code == "200") {
                      this.form.testSysTetailList= res.data.body.records;
                      this.$forceUpdate();
                  }
              });
          })


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
      
      //   this.$refs['form'].validate(valid => {
      //     if (valid) {
      //  }
      //   })
     
            if(!!this.form.createTime){
                var d=this.form.createTime;
                this.form.createTime=this.$timeUtils.dateUtils(d);
                this.$forceUpdate();
            }

            this.$http.post(this.api.sysTestSaveOrUpdate, this.form).then(res => {
              if (res.data.code == "200") {
                  this.$message.success(res.data.message);
                  this.editVisible = false;
                  this.empty();
                  this.getdata();
              }
              else {
                this.$message.success(res.data.message);
              }

            });
   
    },


    //取消
    cancelSave(){
      this.empty();
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
        this.pageData.pageNumber=1;
        this.pageData.pageSize=10;
    },

    descInput_fou(){
        if(!this.form.testArea){
            this.remnant_fou=200;
        }else{
            var txtVal = this.form.testArea.length;
            this.remnant_fou = 200 - txtVal;
        }
      
    },

    //导出
    handleExport(){
          this.$http.get(this.api.sysTestExportExcel, {
          responseType: 'blob',
        }).then(res => { 

          if (res) {
            //eslint-disable-next-line
            const blob = new Blob([res.data]);
            //对于<a>标签，只有 Firefox 和 Chrome（内核） 支持 download 属性
            //IE10以上支持blob但是依然不支持download
            if ('download' in document.createElement('a')) { 
           //支持a标签download的浏览器
            const link = document.createElement('a')//创建a标签
            var fileName="测试.xlsx";
              link.download = fileName//a标签添加属性
              link.style.display = 'none'
              link.href = URL.createObjectURL(blob)
              document.body.appendChild(link)
              link.click()//执行下载
              URL.revokeObjectURL(link.href) //释放url
              document.body.removeChild(link)//释放标签
          
           }else{
               navigator.msSaveBlob(blob, fileName)
          }
          } else {
            t.$message.warning('转化word文件失败，请检查文件并且重试！');
          }

        });
    },

    //导入
    handleImport(){
      
    }

  },
  
}
</script>
<style lang="scss" scope>

.dialogForm{
    min-height: 250px;
    width: calc(100% - 20px);
    margin-top: 10px;
    margin-right: 20px;
}
.container-table{
  float: left;
  width: 100%;
  height: calc(100% - 120px);
  border: 1px solid #ddd;
  border-radius: 5px;
  box-sizing: border-box;
  background-color: #ffffff;
  padding: 10px 10px;
  box-shadow: 1px 1px 3px rgba(0,0,0,0.2);
}
.tableBody{
  margin-top: 10px;
  height: calc(100% - 120px);
  width: 100%;
}
.el-dialog__header{
  padding: 0;
}
.el-radio{
  margin-right: 0px;
}
.el-checkbox{
  margin-right: 0px;
}
</style>