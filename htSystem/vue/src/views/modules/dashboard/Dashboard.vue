<template>
  <div class="app-container">
    <!-- <mavon-editor v-model="content" @change="change"></mavon-editor> -->
    <editor></editor>
  </div>
</template>
<script>


export default {
  name: "dashBoard",
  data(){
    return {
      content: ""
    }
  },
  methods: {
      change(value, html) {
          console.log(value, html)
      }
  }
}
</script>