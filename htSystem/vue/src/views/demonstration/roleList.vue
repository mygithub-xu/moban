<template>
  <div class="app-container">
    <div class="container-table">
        <div class="container-btn">
                <span class="container-btn-left">
                    <el-button type="primary"  icon="el-icon-plus" @click="handleAdd">创建角色</el-button>
                    <el-button type="primary"  icon="el-icon-edit" @click="handleEdit">编辑</el-button>
                    <el-button type="danger"  icon="el-icon-delete" @click="handleDeleteBatch">删除</el-button>
                </span>
                <span class="container-btn-right">
                    <el-button  @click="cancelSave" icon="el-icon-close">取消</el-button>
                </span>
        </div>
        <div class="common-table-style">
            <el-table :data="pageData.list" height="100%" border @selection-change="handleSelectionChange">
                <el-table-column fixed type="selection"  ></el-table-column>
                <el-table-column type="index"  width="50" label="序号"></el-table-column>
                <el-table-column prop="createTime"  label="测试名"></el-table-column>
                <el-table-column prop="createTime"  label="测试名"></el-table-column>
                <el-table-column prop="createTime"  label="测试名"></el-table-column>
                <el-table-column prop="createTime"  label="测试名"></el-table-column>
            </el-table>
        </div>
        <div class="pagination">
            <pagination :page-list="pageData" @pagesearch="handlePage"></pagination>
        </div>
    </div>

    <div class="show-dialog">
        <el-dialog :visible.sync="editVisible" :show-close="false" :modal="false" fullscreen>
            <div class="dialog-button">
                <el-button  @click="cancelSave" icon="el-icon-back">返回</el-button>
                <el-button type="primary"  icon="el-icon-check" @click="handleSave('form')">保存</el-button>
            </div>
            <div class="dialog-form">
                
            </div>
        </el-dialog>
    </div>

  </div>
</template>
<script>
export default {
    name: "roleList",
    data() {
      return {

      }
    },
    created() {
      this.getdata();
    },
    methods: {
        getdata(){

        }
    }
}
</script>
<style scoped>

</style>