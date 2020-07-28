<template>
  <section>
    <slot></slot>
    <transition name="fade-transform" mode="out-in">
      
      <router-view></router-view>
    </transition>
  </section>
</template>

<script>
export default {
  name: 'AppMain2',
  computed: {
    cachedViews() {
    
    },
    key() {
      
    }
  },
  data(){
      return {
        height:'',
      }
  },
  mounted() {
    // vm.$nextTick(function(){
    //     this.height=this.$refs.MenuListSpan.clientHeight-36;
    //     console.log(this.height);
    // })
  }

}
</script>

<style lang="scss" scoped>




</style>


