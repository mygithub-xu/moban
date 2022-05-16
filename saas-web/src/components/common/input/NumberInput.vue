<template>
  <el-input v-model="numberValue"
            placeholder="请输入数字"
            onkeyup="value=value.replace(/[^\d]/g,'')"
            :disabled="disabled"
            @change="change"></el-input>
</template>
<script>
export default {
  name: "NumberInput",

  data () {
    return {
      numberValue: ""
    }
  },
  props: {
    value: {
      type: [Number, String],
      default: 0,
    },
    disabled: {
      type: Boolean,
      default: false,
    }
  },
  created () {
    this.numberValue = this.value
  },
  watch: {
    value () {
      this.numberValue = this.value
    }
  },

  methods: {
    change () {
      this.$emit('input', this.numberValue)
    }
  }
}
</script>
<style scoped>
</style>