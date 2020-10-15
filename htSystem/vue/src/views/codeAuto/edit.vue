<template>
  <div class="show-dialog">
    <el-dialog :visible.sync="editVisible" :show-close="false" :modal="false" fullscreen>
         <div class="dialog-button" >
            <el-button  icon="el-icon-back" @click="back">返回</el-button>
        </div>

        <div>
            <el-form >
                <div class="edit-area">
                <el-form-item label="表名" prop="tableName">
                    <el-input v-model="form.tableName"></el-input>
                </el-form-item>
                <el-form-item label="表类型" prop="tableType">
                    <el-input v-model="form.tableType"></el-input>
                </el-form-item>
                <el-form-item label="表状态" prop="type">
                    <el-input v-model="form.type"></el-input>
                </el-form-item>
                <el-form-item label="备注" prop="remark">
                    <el-input v-model="form.remark"></el-input>
                </el-form-item>


                </div>
            </el-form>
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
        },
      }
    },
    props:{

    },
    created() {

    },
    methods:{
        back(){
            this.editVisible = false
        },
    }
  }
</script>
<style scoped>

</style>