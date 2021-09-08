<template>
    <div class="show-dialog">
        <el-dialog :visible.sync="editVisible" :show-close="false" :modal="false" fullscreen>
            <!--按钮区域-->
            <div class="dialog-button">
                <el-button  @click="backHandle">返回</el-button>
                <el-button type="primary" @click="saveHandle">获取提测文件</el-button>

            </div>
            <div class="dialog-form">
                <el-form ref="form" label-width="150px" :rules="rules" :model="form" >
                    <el-form-item label="作者" prop="codeAuthor" style="width:80%;min-width:600px">
                        <el-input v-model="form.codeAuthor" style="width:400px"></el-input>
                    </el-form-item>
                    <el-form-item label="需求号" prop="demandNumber" style="width:80%;min-width:600px">
                        <el-input v-model="form.demandNumber" style="width:400px;min-width:600p"></el-input>
                    </el-form-item>

                    <el-form-item label="需求名称" prop="demandName" style="width:80%;min-width:600px">
                        <el-input v-model="form.demandName"  style="width:400px"></el-input>
                    </el-form-item>
                    
                    <el-form-item label="安卓版本" prop="androidVersion" style="width:50%;min-width:600px">
                        <el-input v-model="form.androidVersion"  style="width:400px"></el-input>
                    </el-form-item>
                    <el-button  @click="getVersion" style="height:32px;">一键获取版本号</el-button>
                  
                    <el-form-item label="ios版本" prop="iosVersion" style="width:80%;min-width:600px">
                        <el-input v-model="form.iosVersion"  style="width:400px"></el-input>
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
            codeAuthor: [
                {
                    required: true,
                    message: "作者不能为空",
                    trigger: "blur"
                },
            ],
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
                this.form = res.data.body
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
                    const name = this.form.demandNumber + this.form.demandName + '.zip'
                    this.$utils.downFile("api/test/tSendMessageFile/downZip",this.form,name)
              }
            })
        }
    }
    }
</script>