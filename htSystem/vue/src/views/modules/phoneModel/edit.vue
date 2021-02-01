<template>
    <div class="show-dialog">
        <el-dialog :visible.sync="editVisible" :show-close="false" :modal="false" fullscreen>
            <!--按钮区域-->
            <div class="dialog-button">
                <el-button  @click="backHandle">返回</el-button>
                <el-button type="primary" @click="saveHandle">保存</el-button>

            </div>
            <div class="dialog-form">
                <el-form ref="form" label-width="150px" :rules="rules" :model="form">

                    <el-form-item label="终端型号" prop="terminalModel">
                        <el-input v-model="form.terminalModel"></el-input>
                    </el-form-item>

                    <el-form-item label="终端名称" prop="terminalName">
                        <el-input v-model="form.terminalName"></el-input>
                    </el-form-item>

                    <el-form-item label="终端编号" prop="terminalNumber">
                        <el-input v-model="form.terminalNumber"></el-input>
                    </el-form-item>

                    <el-form-item label="终端型号名称" prop="terminalNameType">
                        <el-input v-model="form.terminalNameType"></el-input>
                    </el-form-item>
                </el-form>
            </div>
        </el-dialog>
    </div>
</template>
<script>
export default {
    name: "phoneModelEdit",
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
                terminalModel:"",
                terminalName:"",
                terminalNumber:"",
                terminalNameType:"",
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
            this.$http.get("api/system/phoneModel/queryById/" + row.id).then(res => {
                this.form = res.data.body
            });
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
                    this.$http.post("api/system/phoneModel/saveOrUpdate",this.form).then(res => {
                      if(res.data.code == '200'){
                        this.$message.success(res.data.message)
                        this.editVisible = false
                        this.$emit("back")
                      }
                    });
                })
              }
            })
        }

    }

}
</script>