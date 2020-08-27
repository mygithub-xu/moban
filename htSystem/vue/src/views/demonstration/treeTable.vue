<template>
    <div class="app-container">
    
                    <div class="tree-area">
                        <div class="common-header">
                            <span class="common-header-title">
                                我是标题
                            </span>
                        </div>

                        <el-tree :data="treeData" :props="defaultProps" @node-click="handleNodeClick"> </el-tree>
                    </div>

                    <div class="table-area">
                        <div class="common-header">
                            <span class="common-header-title">
                                我是标题
                            </span>
                        </div>

                        <div class="treeTable-button" >

                            <span v-if="editModel">
                                <el-button type="primary" size="small"   @click="handleAdd">新增</el-button>
                                <el-button type="danger" size="small" :disabled="delable"  @click="handleDelete">删除</el-button>
                                <el-button type="primary"  size="small"   @click="handleEdit">编辑</el-button>
                            </span>
                            <span v-else>
                                <el-button type="primary" size="small"   @click="handleSave">保存</el-button>
                                <el-button  plain size="small"   @click="handleCancel">取消</el-button>
                            </span>

                        </div>

                            <div style="margin-top: 42px;width:500px">
                                <el-form  ref="form" :model="form" :rules="rules" label-width="150px" size="small" style="margin-top:10px">


                                        <el-form-item label="上级节点" prop="testName" >
                                            <treeSelect v-model="form.parentId" :disabled="lookModel" :data="treeData" label="testName"/>
                                        </el-form-item>

                                        <el-form-item label="创建时间（时间）" prop="createTime">
                                                <el-date-picker
                                                        v-model="form.createTime"
                                                        type="datetime"
                                                        placeholder="选择日期时间"
                                                        default-time="12:00:00"
                                                        style="width:100%"
                                                        :disabled="lookModel"
                                                        >
                                                </el-date-picker>
                                        </el-form-item>

                                        <el-form-item label="测试名（普通框）" prop="testName" >
                                            <el-input v-model="form.testName" :disabled="lookModel" ></el-input>
                                        </el-form-item>


                                        <el-form-item label="测试状态（单选）"  prop="testStatus">
                                        <el-select v-model="form.testStatus"  placeholder="请选择"  style="width:100%;" :disabled="lookModel">
                                            <el-option
                                                    v-for="item in statusList"
                                                    :key="item.value"
                                                    :label="item.label"
                                                    :value="item.value"
                                            ></el-option>
                                        </el-select>
                                        </el-form-item>
                
                                        <el-form-item label="测试类型（多选）"  >
                                            <el-select v-model="form.testType"  placeholder="请选择" multiple style="width:100%;" :disabled="lookModel">
                                            <el-option
                                                v-for="item in typeList"
                                                :key="item.value"
                                                :label="item.label"
                                                :value="item.value"
                                                >
                                            </el-option>
                                            </el-select>
                                        </el-form-item>
                                </el-form>
                            </div>

                    </div>
        </div>
</template>
<script>
export default {
    name:"treeTable",
    data(){
        return{
            defaultProps:{
                children: 'children',
                label: 'testName'
            },
            treeData:[],
            form :{
                createTime: "",
                createUser: "",
                id: "",
                testName: "",
                testStatus: "",
                testType: "",
                parentId:"",
                testArea:""
            },
            rules:{},
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

          remnant_fou: 200,
          delable:false,
          lookModel:true,
          editModel:true,
        }
    },
    created(){
        this.getData();
    },
    methods:{

        //树节点点击触发事件
        handleNodeClick(data, node){
            Object.assign(this.form,data);
            var duoxuan=data.testType.split(',');
            this.form.testType=duoxuan;
            this.descInput_fou();
            this.delable=false;
        },

        //获取数据
        getData(){
        
            this.$http.get(this.api.sysTestGetTreeData)
            .then(res => {
            if (res.data.code == "200") {
                this.treeData = res.data.body;
            }
            });

        },

        //计算剩余字数
        descInput_fou(){
            if(!this.form.testArea){
                this.remnant_fou=200;
            }else{
                var txtVal = this.form.testArea.length;
                this.remnant_fou = 200 - txtVal;
            }
         
        },

        //新增
        handleAdd(){
            this.statusOpen(false)
            this.empty();

        },

        //编辑
        handleEdit(){
            this.statusOpen(false)
        },

        //删除
        handleDelete(){
            if(!this.form.id){
             
             return this.$message.error("请选择一条数据");
            }

            if(!!this.form.children){
             
             return this.$message.error("有下级节点不能删除");
            }

      this.$confirm("此操作将永久删除, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$http.delete(this.api.sysTestDeletebyid + this.form.id).then(res => {
            if (res.data.code == "200") {
              this.$message({
                message: "删除数据成功",
                type: "success"
              });
            }
            this.empty();
            this.getData();
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });



        },

        //保存
        handleSave(){
            var aa=this.form.createTime;
            var flag=typeof aa=='string'
            if(!!aa&&!flag){
                this.form.createTime=this.$dateUtils.dateUtils(this.form.createTime);
            }

            var flag2=typeof this.form.testType=='string';
            if(!!this.form.testType&&!flag2){
                this.form.testType=this.form.testType.join(",")
            }

            this.$http.post(this.api.sysTestSaveOrUpdate, this.form).then(res => {
              if (res.data.code == "200") {
                  this.$message.success(res.data.message);
                  this.empty();
                  this.statusOpen(true);
                  this.getData();
              }
            });
        },

        //取消
        handleCancel(){
            this.statusOpen(true)
        },

        statusOpen(boo){
            this.lookModel=boo;
            this.editModel=boo;
        },

        empty(){
            this.form ={
                createTime: "",
                createUser: "",
                id: "",
                testName: "",
                testStatus: "",
                testType: "",
                parentId:"",
                testArea:""
            }
        }


    }
    
}
</script>
<style scoped>

</style>