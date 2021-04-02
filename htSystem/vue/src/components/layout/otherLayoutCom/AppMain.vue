<template>
  <section>
    <transition name="fade-transform"
                mode="out-in">
      <keep-alive :include="components">
        <router-view></router-view>
      </keep-alive>
    </transition>
  </section>
</template>

<script>
export default {
  name: 'AppMain',
  computed: {
    components () {
      return this.$store.state.tabRouter.keepTab
    },
  },
  data () {
    return {

    }
  },

}
</script>


