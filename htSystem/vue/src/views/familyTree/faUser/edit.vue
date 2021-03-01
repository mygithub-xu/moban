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


                    <el-form-item label="昵称" prop="name">
                        <el-input v-model="form.name"></el-input>
                    </el-form-item>

                    <el-form-item label="姓名" prop="realName">
                        <el-input v-model="form.realName"></el-input>
                    </el-form-item>

                    <el-form-item label="性别" prop="gender">
                        <el-select v-model="form.gender" placeholder="请选择"  clearable>
                          <el-option
                            v-for="item in selectData.genderList"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value"
                          ></el-option>
                        </el-select>
                    </el-form-item>

                    <el-form-item label="电话" prop="phone">
                        <el-input v-model="form.phone"></el-input>
                    </el-form-item>

                    <el-form-item label="状态" prop="status">
                        <el-select v-model="form.status" placeholder="请选择"  clearable>
                          <el-option
                            v-for="item in selectData.statusList"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value"
                          ></el-option>
                        </el-select>
                    </el-form-item>

                </el-form>
            </div>

        </el-dialog>
    </div>
</template>
<script>
export default {
    name: "faUserEdit",
    data() {
      return {
        editVisible: false,
        //表单数据
        form: {},
        //校验
        rules:{
        }

      }
    },
    props:{
        selectData:Object
    },
    methods:{
        //清除校验
        clearCheck(){
            this.$refs['form'].resetFields()
        },
        //清空
        empty(){
            this.form = {
                name:"",
                realName:"",
                gender:"",
                phone:"",
                status:"",
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
            this.$http.get("api/familyTree/faUser/queryById/" + row.id).then(res => {
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
                    this.$http.post("api/familyTree/faUser/saveOrUpdate",this.form).then(res => {
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