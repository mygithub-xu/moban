<template>
  <el-select :placeholder="placeholder"
             v-model="value"
             @change="change">
    <el-option v-for="opt in options"
               :key="opt.value"
               :label="opt.text_content"
               :value="opt.value"></el-option>
  </el-select>
</template>

<script>
export default {
  name: "index",
  props: {
    keyword: {
      type: String,
      required: true
    },
    placeholder: {
      type: String,
      default: '请选择'
    },
    defaultVal: String,
    selData: String,
    includeVals: Array,
    excludeVals: Array,
  },
  data () {
    return {
      //查询得到的结果
      options: [],
      value: ''
    }
  },
  methods: {
    change () {
      this.$emit("update:selData", this.value)
    }
  },
  watch: {
    keyword: {
      handler () {
        let param = {
          keyword: this.keyword,
          includeVals: this.includeVals,
          excludeVals: this.excludeVals
        };
        this.$http.post('common/getOptions/', param).then((data) => {
          this.options = data;
        }).catch((error) => {
          console.log(error);
        })
      },
      immediate: true
    },
    selData: {
      handler () {
        this.value = this.selData
      }
    }
  }
}
</script>

<style scoped>
</style>
