<template>
    <!-- 导出组件 -->
    <download-excel class = "export-excel-wrapper"
    :header = "header"
    :data = "data"
    :fields = "fields"
    :type = "type"
    :name = "name"
    :before-generate = "startDownload">
      <el-button icon="el-icon-download" :type="bottonType">
        <slot></slot>
      </el-button>
    </download-excel>
</template>

<script>
export default {
  name:"jaExport",
  data () {
    return {
      
    }
  },
  props: {
    data: {
      type: Array,
      require: true
    },
    fields: {
      type: Object,
      require: true
    },
    type: {
      type: String,
      default: "xls"
    },
    bottonType: {
      type: String,
      default: ""
    },
    header:{
      type: [String, Array],
      default: ""
    },
    name:{
      type: String,
      default: "data.xls"
    },
  },
  methods:{
    startDownload(){
    }
  }
}
</script>