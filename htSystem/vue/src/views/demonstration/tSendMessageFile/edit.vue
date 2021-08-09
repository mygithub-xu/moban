<template>
    <div class="show-dialog">
        <el-dialog :visible.sync="editVisible" :show-close="false" :modal="false" fullscreen>
            <!--按钮区域-->
            <div class="dialog-button">
                <el-button  @click="backHandle">返回</el-button>
                <el-button type="primary" @click="saveHandle">获取提测文件</el-button>

            </div>
            <div class="dialog-form">
                <el-form ref="form" label-width="150px" :rules="rules" :model="form" style="width:50%">
                    <el-form-item label="需求号" prop="demandNumber" style="width:60%">
                        <el-input v-model="form.demandNumber"></el-input>
                    </el-form-item>

                    <el-form-item label="需求名称" prop="demandName" style="width:60%">
                        <el-input v-model="form.demandName"></el-input>
                    </el-form-item>

                    <el-form-item label="安卓版本" prop="androidVersion" style="width:60%">
                        <el-input v-model="form.androidVersion"></el-input>
                    </el-form-item>
                    <el-button  @click="getVersion" style="height:32px;margin-left:20px">一键获取版本号</el-button>

                    <el-form-item label="ios版本" prop="iosVersion" style="width:60%">
                        <el-input v-model="form.iosVersion"></el-input>
                    </el-form-item>
                    
                </el-form>
            </div>

        </el-dialog>
    </div>
</template>
<script>
export default {
    name: "tSendMessageFileEdit",
    data() {
      return {
        editVisible: false,
        //表单数据
        form: {},
        //校验
        rules:{
            demandNumber: [
                {
                    required: true,
                    message: "需求编号不能为空",
                    trigger: "blur"
                },
            ],
            demandName: [
                {
                    required: true,
                    message: "需求名称不能为空",
                    trigger: "blur"
                },
            ],
            androidVersion: [
                {
                    required: true,
                    message: "版本号不能为空",
                    trigger: "blur"
                },
            ],
            iosVersion: [
                {
                    required: true,
                    message: "版本号不能为空",
                    trigger: "blur"
                },
            ]
        }

      }
    },
    props:{
        selectData:Object
    },
    methods:{
        getVersion(){
            this.$http.get("api/test/tSendMessageFile/getVersion",this.form).then(res => {
                this.form.androidVersion = res.data.body.android
                this.form.iosVersion = res.data.body.ios
            })
        },
        //清除校验
        clearCheck(){
            this.$refs['form'].resetFields()
        },
        //清空
        empty(){
            this.form = {
                demandNumber:"",
                demandName:"",
                androidVersion:"",
                iosVersion:"",
            }
        },
        //新增
        openByNew(){
            this.editVisible = true
            this.empty()
        },
        //编辑
        openByEdit(row){
            this.openByNew()
            this.$http.get("api/test/tSendMessageFile/queryById/" + row.id).then(res => {

            })
        },
        //返回
        backHandle(){
            this.clearCheck()
            this.editVisible = false
        },
        //保存
        saveHandle(){
            this.$refs['form'].validate((valid)=>{
              if(valid){
                this.$confirm("是否保存?", "提示", {
                  confirmButtonText: "确定",
                  cancelButtonText: "取消",
                  type: "warning"
                }).then(() => {
                    this.$http.post("api/test/tSendMessageFile/saveOrUpdate",this.form).then(res => {
                      if(res.data.code == '200'){
                        this.$message.success(res.data.message)
                        this.editVisible = false
                        this.$emit("back")
                      }
                    })
                })
              }
            })
        }
    }
    }
</script>