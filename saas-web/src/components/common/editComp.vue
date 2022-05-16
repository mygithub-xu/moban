<template>
  <div class="editComp"
       :style="{'width':width}">
    <div class="editComp-inline">
      <div class="editComp-title">
        {{title}}
      </div>
      <div class="editComp-body">
        <slot></slot>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "editComp",
  data () {
    return {

    }
  },
  props: {
    width: {
      type: String,
      default: "100%"
    },
    title: {
      type: String,
      default: ""
    }
  },
  methods: {

  }
}
</script>
<style scoped>
.editComp {
  margin: 10px 0;
  float: left;
}
.editComp-inline {
  height: 100%;
  width: calc(100% - 12px);
  border: 1px solid #a1a0a0;
  margin: 0 5px;
  float: left;
}
.editComp-title {
  height: 20px;
  line-height: 20px;
  text-align: left;
  font-size: 15px;
  color: #409eff;
}
.editComp-body {
  height: calc(100% - 20px);
  width: calc(100% - 20px);
  margin: 0 10px 10px 10px;
}
</style>