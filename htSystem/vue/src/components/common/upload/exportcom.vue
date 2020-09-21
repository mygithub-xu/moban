<template>
    <el-button :type="type" @click="handleExport"  icon="el-icon-download">
        <slot></slot>
    </el-button>
</template>
<script>
export default {
    name:"exportcom",
    data() {
      return {
      };
    },
    props:{
        type:{
            type:String,
            default: "primary"
        },
        value: {
            type: String,
            default: ""
        },
        fileName: {
            type:String,
            default: "测试.txt"
        }
    },
    watch:{
      value(){
      }
    },
    methods:{
      //导出
      handleExport() {
        if(!this.value){
            return;
        }
        this.$http.get(this.value, {
          responseType: 'blob',
        }).then(res => {
          if (res) {
            //eslint-disable-next-line
            const blob = new Blob([res.data]);
            //对于<a>标签，只有 Firefox 和 Chrome（内核） 支持 download 属性
            //IE10以上支持blob但是依然不支持download
            if ('download' in document.createElement('a')) {
              //支持a标签download的浏览器
              const link = document.createElement('a')//创建a标签
              var fileName = this.fileName;
              link.download = fileName//a标签添加属性
              link.style.display = 'none'
              link.href = URL.createObjectURL(blob)
              document.body.appendChild(link)
              link.click()//执行下载
              URL.revokeObjectURL(link.href) //释放url
              document.body.removeChild(link)//释放标签
            } else {
              navigator.msSaveBlob(blob, fileName)
            }
          } else {
            this.$message.warning('下载失败');
          }

        });
      },
    }
}
</script>
<style scoped>

</style>