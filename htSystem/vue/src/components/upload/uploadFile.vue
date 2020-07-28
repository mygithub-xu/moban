<template>
    <div class="uploadDiv">

        <el-button :type="type" @click="openUploadDialog" size="small">上传</el-button>
        <el-dialog
          :visible.sync="uploadVisible"
          width="500px"
          append-to-body
          :before-close="closeDialog">
            <el-upload
                class="upload-demo"
                ref="upload"
                :action="uploadUrl"
                :on-preview="handlePreview"
                :on-remove="handleRemove"
                :file-list="fileList"
                :auto-upload="false">
                <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
                <!-- <el-button  size="small" type="primary" @click="closeDialog">关闭</el-button> -->
                <div slot="tip" class="el-upload__tip">只能上传xlsx文件，且不超过500kb</div>
            </el-upload>
        </el-dialog>
    </div>
</template>
<script>
export default {
    name:"uploadFile",
    data() {
      return {
        fileList: [],
        uploadVisible:false
      };
    },
    props:{
        uploadUrl:{
            type:String,
            require: true
        },
        type:{
            type:String,
            default: "primary"
        }
    },
    methods: {
        closeDialog(){
            this.uploadVisible=false;
        },
        openUploadDialog(){
            this.uploadVisible=true
            this.$emit("click");
        },
        beforeUpload(file){

            var flag=true;
            var isJPG=false;
            if(file.type=='application/vnd.ms-excel'){
                isJPG=true;
            }
            const isLt5M = file.size / 1024 / 1024 < 5;

            if (!isJPG) {
                this.$message.error('上传文件只能是xlsx 格式!');
                flag=false;
            }
            if (!isLt5M) {
                this.$message.error('上传文件大小不能超过 5MB!');
                flag=false;
            }
            return flag;
        },

        submitUpload() {
            this.$refs.upload.submit();
        },
        handleRemove(file, fileList) {
            console.log(file, fileList);
        },
        handlePreview(file) {
            console.log(file);
        }

    }
}
</script>
<style scoped>
.uploadDiv{
    margin-left: 10px;
    margin-right: 10px;
    display: inline-block;
}
.upload-demo{
    height: 200px;
}
</style>