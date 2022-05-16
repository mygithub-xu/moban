<template>

  <el-select v-model="selectId"
             placeholder="请选择"
             @change="change">
    <el-option v-for="item in options"
               :key="item.bud_summ_id"
               :label="item.year"
               :value="item.bud_summ_id">
    </el-option>
  </el-select>
</template>

<script>
export default {
  name: "yearSelection",
  props: {
    value: {
      type: String,
      default: ''
    },
  },
  data () {
    return {
      selectId: "",
      options: []
    }
  },
  mounted () {
    this.selectId = this.value;
    this.$http.get('budSummary/listYear').then(res => {
      this.options = res
      this.exportYear()
    })
  },
  watch: {
    value () {
      this.selectId = this.value
    }
  },
  methods: {
    change () {
      this.$emit('input', this.selectId)
      this.$emit('handleChange', this.selectId)
      this.exportYear();
    },
    exportYear () {
      var optionItem = ""
      this.options.forEach(v => {
        if (v.bud_summ_id == this.selectId) {
          optionItem = v
        }
      })
      this.$emit('change', optionItem)
    }
  }

}
</script>

<style scoped>
</style>
