<template>
<!-- 多图上传组件 -->
  <div class="uploadDiv">
      <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12" >
        <el-select v-model="param.type" size="small" placeholder="请选择类型"  style="">
            <el-option
                v-for="item in photoTypeData"
                :key="item.value"
                :label="item.label"
                :value="item.value"
            ></el-option>
        </el-select>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="12" :xl="12" >
          <el-upload
          ref="my-upload"
            class="upload-demo"
            action="http://120.25.197.11/moban/api/system/sysFile/uploadFileMore"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :before-remove="beforeRemove"
            multiple
            :limit="20"
            :data="param"
            :on-exceed="handleExceed"
            :file-list="fileList"
            :before-upload="beforeUpload">
            <el-button size="small" type="primary" plain>点击上传</el-button>
            <div slot="tip" class="el-upload__tip">只能上传JPG，png，jpeg文件，最多20张，且单张不超过5MB</div>
          </el-upload>
      </el-col>
  </div>
</template>

<script>
  export default {
    name:"uploadMore",
    data() {
      return {
        fileList:[],
        photoTypeData:[],
        param:{
          type:''
        },
        dialogImageUrl: '',
        dialogVisible: false
      };
    },
    mounted(){
        // this.xiala();
    },
    methods: {
      xiala(){
                //种类
            this.$http
                .post(this.api.appProductcategoryQueryByCondition, {
                    condition: {},
                    number: 1,
                    size: 99999
                })
                .then(res => {
                if (res.data.code == "200") {
                this.photoTypeData=res.data.body.records.map(item => ({
                    label: item.categoryName,
                    value: item.id.toString(),
                }));
                var aa={
                  label:"全部",
                  value: '0',
                }
                this.photoTypeData.push(aa);
                }
            });
      },
      beforeUpload(file){

        // if(!this.param.type){
        //   this.$message.error('请选择图片类型'); 
        //   return false
        // }
        var flag=true;
        var isJPG=false;
        if(file.type=='image/jpeg'||file.type=='image/jpg'||file.type=='image/png'){
          isJPG=true;
        }
        const isLt2M = file.size / 1024 / 1024 < 5;

        if (!isJPG) {
          this.$message.error('上传图片只能是 JPG，png，jpeg 格式!');
          flag=false;
        }
        if (!isLt2M) {
          this.$message.error('上传图片大小不能超过 5MB!');
          flag=false;
        }
        if(flag&&!!this.imageUrl){
          console.log("删除图片",this.imageUrl)
        }
        return flag;
      },
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
      },
      handleExceed(files, fileList) {
        this.$message.warning(`当前限制选择 20个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
      },
      beforeRemove(file, fileList) {
        return this.$confirm(`确定移除 ${ file.name }？`);
      },
      clearFiles () {
        this.param.type="";
        this.$refs['my-upload'].clearFiles();
      }
    }
  }
</script>
<style scoped>
    .uploadDiv{
      height: 250px;
      width: 100%;
    }
</style>