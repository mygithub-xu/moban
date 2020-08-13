<template>
    <div class="app-container2">
        <div class="container">
          <!-- 区域一 查询区域 -->
          <div class="container_btn" >
            <span>
            <el-button type="primary" size="small"   @click="handleAdd">上传图片</el-button>
            <el-button type="danger" size="small" :disabled="dicDisabled"  @click="handleDeleteBatch">删除</el-button>
            </span>
            <span class="handle-box" style="float:right">
              <el-input
                v-model="queryContent" placeholder="请输入查询内容" class="handle-input mr10" size="small" style="width: 200px;" clearable>
              </el-input>
                <el-button type="primary" icon="search" size="small"  @click="search">查询</el-button>
                <el-button size="small" type="text" @click="reset">重置</el-button>
                <el-row class="tac">
                  <el-col :span="24">
                    <el-dropdown trigger="click" :hide-on-click="false">
                      <div class="el-icon-menu"></div>
                      <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item>
                          <el-checkbox v-model="xuhao">序号</el-checkbox>
                        </el-dropdown-item>
                      </el-dropdown-menu>
                    </el-dropdown>
                  </el-col>
                </el-row>
            </span>
          </div>
          <!-- 区域二---表格+分页 -->
          <el-table :data="pageData.list" :row-style="{height:'50px'}" border class="table" ref="multipleTable" align="center" @selection-change="handleSelectionChange" height="100%">
            <el-table-column type="selection"  fixed width="55"  align="center" show-overflow-tooltip></el-table-column>
            <el-table-column type="index" width="55" label="序号" align="center" v-if="xuhao" show-overflow-tooltip></el-table-column>
            <el-table-column prop="url" min-width="75" label="图片缩略图" align="center" show-overflow-tooltip>
              <template slot-scope="scope">
                <div>
                  <img :src="scope.row.url" @click="openPhoto(scope.row.url)" fit="contain" style="height:70px"/>
                </div>
              </template>
            </el-table-column>
            <el-table-column prop="url" min-width="75" label="图片路径" align="center" show-overflow-tooltip></el-table-column>

            <el-table-column prop="createTime" min-width="75" label="创建时间" align="center" show-overflow-tooltip></el-table-column>
            <el-table-column label="操作" min-width="75" align="center" show-overflow-tooltip>
              <template slot-scope="scope">
                <el-button type="text" icon="el-icon-delete" class="red"  @click="handleDelete(scope.row.id)" >删除</el-button>
              </template>
             </el-table-column>
          </el-table>
          <div class="pagination">
            <pagination :page-list="pageData" @pagesearch="handlePage"></pagination>
          </div>
        </div>

        <el-dialog
          title="图片上传"
          :visible.sync="editVisible"
          width="50%"
          :before-close="handleClose">
          <uploadMore ref="upload3" ></uploadMore>
        </el-dialog>

        <el-dialog
          :visible.sync="editVisible2"
          width="50%"
          :before-close="handleClose2">
          <img :src="lineImgSrc" style="height:300px;width:500px"/>
          <span slot="footer" class="dialog-footer">
            <el-button @click="handleClose2">关闭</el-button>
          </span>
        </el-dialog>
        
    </div>
</template>
<script>
export default {
  name:"sysFilesList",
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
        editVisible:false,
        lineImgSrc:"",
        editVisible2:false
    }
  },
  created(){
    this.getdata();
  },
  methods:{
    //新增
    handleAdd(){
      this.editVisible=true;
    },
    //批量删除--不需要批量删除请将其注释，，
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
          this.$http .post(this.api.sysFileBatchdelete, deletebatch).then(res => {
              if (res.data.code == "200") {
                this.$message({
                  message: "批量删除数据成功",
                  type: "success"
                });
              }else{
                  this.$message({
                  message: "批量删除数据失败",
                  type: "error"
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
      this.queryContent="";
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
    },
    //单个删除
    handleDelete(id){
      this.$confirm("此操作将永久删除, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$http.post(this.api.sysFileDeleteFile+id).then(res => {
            if (res.data.code == "200") {
              this.$message({
                message: "删除数据成功",
                type: "success"
              });
            }else{
                  this.$message({
                  message: "删除数据失败",
                  type: "error"
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
        .post(this.api.sysFileQuerybycondition, {
          condition: {
            queryContent: this.queryContent,
          },
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

    handleClose(){
      this.editVisible=false;
      this.$refs.upload3.clearFiles();
      this.getdata();
      // window.location.reload()
    },
    removePhoto(id){
      this.$http.post(this.api.sysFileDeleteFile+id).then(res => {
        if (res.data.code == "200") {
          this.$message({
            message: "删除数据成功",
            type: "success"
          });
        }else{
              this.$message({
              message: "删除数据失败",
              type: "error"
            });
        }
        this.getdata();
      });
    },
    openPhoto(url){
      this.editVisible2=true;
      this.lineImgSrc=url;
    },
    handleClose2(){
       this.editVisible2=false;
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
.red{
  color: red;
}
</style>