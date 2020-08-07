<template>
  <div class="dialog">
    <el-dialog
    width="900px"
    append-to-body
    :show-close="false"
      :visible.sync="dialogVisible">
      <div  class="dialogHead">
          <!-- <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12" >
            <el-select v-model="photoType" size="small" placeholder="请选择类型" @change="typeclick">
                <el-option
                    v-for="item in photoTypeData"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                ></el-option>
            </el-select>
          </el-col> -->
          <el-col :xs="24" :sm="24" :md="24" :lg="12" :xl="12" >
            <el-button size="small" type="primary" plain @click="openUpload">打开上传面板</el-button>
          </el-col>
      </div>
      <div class="dialog-body">
      <div v-for="imgItem in pageData.list" :key="imgItem.value">
        <div class="phntoview" >
          <img :src="imgItem.url" fit="cover" class="imgDiv" @click="selectDiv(imgItem)" :class="{'active': imgItem.id == index}"/>
        </div>
      </div>

      <div class="pagination">
            <pagination :page-list="pageData" @pagesearch="handlePage"></pagination>
      </div>
      </div>
      <div class="dialogFooter">
        <el-button type="primary" plain size="small" @click="handleSure">确定</el-button>
        <el-button type="info" plain size="small" @click="handleClose">取消</el-button>
      </div>
    </el-dialog>

        <el-dialog
          title="图片上传"
          :visible.sync="uploadVisible"
          width="50%"
          append-to-body
          :before-close="uploadhandleClose">
          <uploadMore ref="uploadMore" ></uploadMore>
        </el-dialog>
  </div>
</template>

<script>
  export default {
    name:"photoShow",
    data() {
      return {
        uploadVisible:false,
        photoTypeData:[],
        photoType:"",
        dialogVisible: false,
        pageData: {
          list: [],
          pageNumber: 1,
          pageSize: 10,
          totalCount: 0,
          totalPage: 0
        },
        photoData:"",
        index:0
      };
    },
        props:{
          imgType: {
              type: String,
              default: () => "",
          }
        },
    created(){
      this.getdata();
      // this.getxiala();
    },
    methods: {
      uploadhandleClose(){
        this.uploadVisible=false;
        this.$refs.uploadMore.clearFiles();
        this.getdata();
      },
      openUpload(){
        this.uploadVisible=true;
      },
      typeclick(val){
        this.photoType=val=='0'?'':val;
        this.getdata();
      },
      getxiala(){
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
    handleSure(){
      this.dialogVisible=false;
      this.$emit("handleSure",this.photoData);
      this.$emit("handleSure2",this.photoData,this.imgType);
    },
    selectDiv(imgItem){
      if(this.index==imgItem.id){
        this.index=0;
      }else{
        this.index=imgItem.id;
      }
      this.photoData=imgItem;
    },
    handlePage(number, size){
      this.pageData.pageNumber=number;
      this.pageData.pageSize=size;
      this.getdata();
    },
    getdata(){
      this.$http
        .post(this.api.sysFileQuerybycondition, {
          condition: {
            type:this.photoType
          },
          number: this.pageData.pageNumber,
          size: this.pageData.pageSize
        })
        .then(res => {
          if (res.data.code == "200") {
            this.pageData.list = res.data.body.records;
            this.pageData.totalCount = res.data.body.total;
            this.pageData.totalPage = res.data.body.pages;
          }
        });
    },
    handleClose() {
      this.dialogVisible=false;
    }
    }
  };
</script>
<style scoped>
.dialog{
  position: relative;
}
.dialog-body{
  width: 100%;
  height: 400px;
}
.phntoview{
  float: left;
  height: 140px;
  width: 140px;
}
.active{
  border: 1px solid red;
}
.imgDiv{
  height: 120px;
  max-width: 120px;
  margin-left: 10px;
  margin-top: 10px;
}
.dialogFooter{
  position: absolute;
  bottom: 15px;
}
.dialogHead{
  width: 100%;
  height: 50px
}
</style>