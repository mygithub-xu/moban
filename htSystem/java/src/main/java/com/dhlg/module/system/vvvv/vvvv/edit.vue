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

                    <el-form-item label="测试字段" prop="vvvv">
                        <el-input v-model="form.vvvv"></el-input>
                    </el-form-item>

                    <el-form-item label="测试选择框" prop="createUser">
                        <el-input v-model="form.createUser"></el-input>
                    </el-form-item>

                    <el-form-item label="测试日期选择框" prop="updateTime" >
                        <el-date-picker value-format="yyyy-MM-dd HH:mm:ss" v-model="form.createTime" type="datetime" placeholder="选择日期时间"></el-date-picker>
                    </el-form-item>


                </el-form>
            </div>

        </el-dialog>
    </div>
</template>
<script>
export default {
    name: "vvvvEdit",
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
                vvvv:"",
                createUser:"",
                updateTime:"",
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
            this.$http.get("api/system/vvvv/queryById/" + row.id).then(res => {
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
                    this.$http.post("api/system/vvvv/saveOrUpdate",this.form).then(res => {
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