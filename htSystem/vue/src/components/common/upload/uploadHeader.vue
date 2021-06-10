<template>
  <div class="uploadTitleImg">
    <div class="crop-demo">
      <img :src="cropImg"
           class="pre-img">
    </div>
    <div slot="tip"
         class="el-upload__tip">只能上传jpg/png/jpeg格式且大小小于1000kb</div>
    <div type="primary"
         class="crop-demo-btn">上传图片
      <input class="crop-input"
             type="file"
             name="image"
             accept="image"
             @change="setImage" />
    </div>
    <el-button type="primary"
               @click="handleSave">保 存</el-button>
    <el-dialog title="裁剪图片"
               append-to-body
               lock-scroll
               :visible.sync="dialogVisible"
               width="39%"
               style="margin-left:45%;">
      <vue-cropper ref='cropper'
                   :src="imgSrc"
                   :ready="cropImage"
                   :zoom="cropImage"
                   :cropmove="cropImage"
                   :fixedBox:=true
                   style="width:100%;height:200px;">
      </vue-cropper>
      <span slot="footer"
            class="dialog-footer">
        <el-button @click="cancelCrop">取 消</el-button>
        <el-button type="primary"
                   @click="dialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import VueCropper from 'vue-cropperjs';
export default {
  name: 'uploadHeader',
  data: function () {
    return {
      defaultSrc: '', // 初始的头像
      fileList: [],
      imgSrc: '',
      cropImg: '',
      dialogVisible: false,
      imgCropperData: { // 接受图片的格式
        accept: "image/jpg,image/png,image/jpeg"
      }
    }
  },
  components: {
    VueCropper
  },
  props: {
    avatarSrc: {
      type: String,
      default: () => "http://a0.att.hudong.com/56/12/01300000164151121576126282411.jpg"
    }
  },
  methods: {
    // 从缓存获取头像
    showImg () {
      this.cropImg = this.avatarSrc;
    },
    // 点击保存,把裁剪好的图片传给个人中心
    handleSave () {
      this.$emit('listenTitleImg', this.cropImg);
      this.dialogVisible = false;
    },
    setImage (e) {
      const file = e.target.files[0];
      // console.log(file);
      // 图片格式的判断
      // if (!file.type.includes('image/jpg') && !file.type.includes('image/png') && !file.type.includes('image/jpeg')) {
      //     alert('请选择我们支持的图片格式！');
      //     return false;
      // }
      if (file === undefined) {
        return false;
      }
      const type = file.type; //文件的类型，判断是否是图片
      const size = file.size; //文件的大小，判断图片的大小
      if (this.imgCropperData.accept.indexOf(type) == -1) {
        this.$message.warning({
          message: '请选择支持的图片格式'
        })
        return false;
      }
      // 图片大小的判断
      if (size > 1000000) {
        this.$message.warning({
          message: '请选择1000kb以内的图片'
        })
        return false;
      }
      const reader = new FileReader();
      reader.onload = (event) => {
        this.dialogVisible = true;
        this.imgSrc = event.target.result;
        this.$refs.cropper && this.$refs.cropper.replace(event.target.result);
      }
      reader.readAsDataURL(file);
    },
    cropImage () {
      this.cropImg = this.$refs.cropper.getCroppedCanvas().toDataURL();

    },
    cancelCrop () {
      this.dialogVisible = false;
      this.cropImg = this.defaultSrc;
    },
    imageuploaded (res) {
      // console.log(res)
    },
    handleError () {
      this.$notify.error({
        title: '上传失败',
        message: '图片上传接口上传失败，可更改为自己的服务器接口'
      });
    }
  },
  created () {
    // this.cropImg = this.defaultSrc;
    this.showImg();
  }

}
</script>

<style scoped lang='scss'>
.uploadTitleImg /deep/ .el-upload__tip {
  font-size: 12px;
  color: #606266;
  margin-top: 20px;
}
.uploadTitleImg {
  height: 200px;
  width: 100%;
  // border: 1px solid #eee;
  padding: 10px;
}
.content-title {
  font-weight: 400;
  line-height: 50px;
  margin: 10px 0;
  font-size: 22px;
  color: #1f2f3d;
}
.pre-img {
  max-width: 100%;
  width: 120px;
  height: 120px;
  background: #f8f8f8;
  border: 1px solid #eee;
  border-radius: 5px;
}
.crop-demo {
  display: flex;
  align-items: flex-end;
}
.crop-demo-btn {
  display: inline-block;
  margin-top: 14px;
  /* margin-left: 250px; */
  position: relative;
  width: 100px;
  height: 32px;
  line-height: 32px;
  padding: 0 20px;
  /* margin-left: 250px; */
  margin-right: 13px;
  background-color: #409eff;
  color: #fff;
  font-size: 14px;
  border-radius: 4px;
  box-sizing: border-box;
}
.crop-input {
  position: absolute;
  width: 100px;
  height: 32px;
  left: 0;
  top: 0;
  opacity: 0;
  cursor: pointer;
}
</style>
