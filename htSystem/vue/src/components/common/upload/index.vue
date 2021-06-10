<template>
  <div>
    <el-upload
    v-show="!readOnly"
      class="upload-demo"
      :action="uploadUrl"
      :headers="importHeaders"
      :on-error="error"
      :on-success="success"
      :show-file-list="isShowFiles"
      :before-upload="beforeUpload"
      :on-progress="progress"
    >
      <el-link type="primary">{{ lable }}</el-link>
    </el-upload>
    <ul class="upload-files">
      <li class="upload-files-item" v-for="(item, index) in fileListsdata" :key="index">
        <i class="el-icon-document"></i>
        <span>{{ item.file_name }}</span>
        <i class="el-icon-delete" v-if="showDelete&&!readOnly" @click="deleteFile(index)"></i>
        <i class="el-icon-download" v-if="showDown" @click="downFile(item)"></i>
      </li>

      <div v-for="item2 in indown" :key="item2.uid">
      <li class="upload-files-item"  v-if="item2.status === 'uploading'">
        <i class="el-icon-document"></i>
        <span>{{ item2.name }}</span>
        <el-progress class="progress" :percentage="parseInt(item2.percentage)"></el-progress>
      </li>
      </div>
      <li class="upload-check" v-if="isNoPass">{{ message }}</li>
    </ul>
  </div>
</template>
<script>
import BASE_URL from '@/config'
export default {
  name: "upload",
  data() {
    return {
      fileListsdata: [],//文件的信息
      isShowFiles: false, // 是否显示上传文件
      indown:[],
      message: "暂无附件"
    };
  },
  props: {
    type: {
      type: String,
      default: "primary"
    },
    lable: {
      type: String,
      default: "点击上传"
    },
    value: Array,
    showDelete: {
      type: Boolean,
      default: true
    },
    showDown: {
      type: Boolean,
      default: false
    },
    readOnly:{
      type: Boolean,
      default: false
    }
  },
  watch: {
    value() {
      this.fileListsdata = !this.value ? [] : this.value
    }
  },
  computed: {
    uploadUrl() {
      return BASE_URL + this.api.sysFileUploadFile
    },
    importHeaders(){
      return {'dh-Token':sessionStorage.getItem("Token")}
    },
    isNoPass() {
      let aaa = this.fileListsdata.length
      return aaa == 0 && this.message
    }
  },
  methods: {
    progress(event, file, fileList){
      this.indown = fileList
    },
    beforeUpload(file) {
      const fileSize = file.size / 1024 / 1024
      // let limitSize = Number(process.env.FILE_LIMIT_SIZE)
      let limitSize = 10
      if(fileSize > limitSize){
        this.$message.error("上传文件大小不能超过" + limitSize + "M")
        return false;
      }
    },
    error(err, file, fileList) {
      let data = JSON.parse(err.message)
      this.$message.error(data.message)
    },
    success(data, file, fileList) {
      this.indown = fileList
      if (!this.fileListsdata) {
        this.value = []
      }
      this.fileListsdata.forEach(f => {
        if(f.file_name == file.name){

        }
      });
      this.fileListsdata.push(data.data)
      this.$emit('input', this.fileListsdata)
      this.$emit("change")
    },
    deleteFile(index) {
      this.fileListsdata.splice(index, 1);
    },
    downFile(item) {
      this.$utils.downFile(item.att_id, item.file_name)
    }
  }
}
</script>
<style scoped>
.upload-files {
  width: 100%;
  min-width: 250px;
  list-style-type: none;
  margin: 0;
}

.upload-files-item {
  width: 100%;
  line-height: 26px;
  margin-top: 5px;
  font-size: 14px;
  transition: all 0.5s cubic-bezier(0.55, 0, 0.1, 1);
  color: #606266;
  border-radius: 4px;
  margin-left: -40px;
  cursor: pointer;
}

.upload-files-item:hover {
  background-color: #f3f3f3;
}

.upload-files-item span {
  margin-left: 10px;
}

.upload-files-item .el-icon-delete,
.upload-files-item .el-icon-download {
  margin-right: 10px;
  float: right;
  line-height: 26px;
}

.upload-files-item .el-icon-delete:hover {
  color: #f56c6c;
}

.upload-files-item .el-icon-download:hover {
  color: #409eff;
}

.upload-check {
  font-size: 12px;
  line-height: 14px;
  margin-left: -40px;
  margin-top: 9px;
}

</style>>
