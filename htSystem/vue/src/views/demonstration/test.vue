<template>

  <div class="app-container">
    <div class="x-scroll">
      <ul class="wrap"
          :style="{'margin-left':tableft+'px'}">
        <li>1</li>
        <li>2</li>
        <li>3</li>
        <li>4</li>
        <li>5</li>
      </ul>
    </div>

    <el-button @click="goleft">goleft</el-button>
    <el-button @click="goRight">goRight</el-button>
  </div>

</template>
<script>
export default {
  name: "test",
  data () {
    return {
      tableft: 0
    };
  },
  methods: {
    goleft () {
      this.tableft = this.tableft - 30
    },
    goRight () {
      this.tableft = this.tableft + 30
    }
  }
}
</script>
<style lang="scss" scoped>
.x-scroll {
  width: 1060px;
  margin: 0 auto;
  overflow-x: auto;
  background: black;
  .wrap {
    width: 1600px;
    li {
      display: inline-block;
      width: 300px;
      height: 300px;
      margin-right: 10px;
      background: yellow;
    }
  }
}
</style>
<style scoped>
/* .x-scroll .wrap {
        width: 1600px; 
    }
    .x-scroll .wrap li {
        display: inline-block;
        width: 300px;
        height: 300px;
        margin-right: 10px;
        background: yellow; 
    } */
</style>
