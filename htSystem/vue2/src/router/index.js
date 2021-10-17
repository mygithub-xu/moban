import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)
var constantRouterMap=[
  {
    path: '/',
    redirect: '/aaaa',
    name: ''
  },
  {
    path: '/aaaa',
    component: resolve => require(['@/views/edit.vue'], resolve),
    name: 'aaaa'
  }
]

export default new Router({
  routes: constantRouterMap
})
