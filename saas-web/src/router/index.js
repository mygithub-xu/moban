import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)
var constantRouterMap=[
  {
    path: '/',
    redirect: '/login',
    name: ''
  },
  {
    path: '/404',
    redirect: '/page/404',
    name: '404'
  },
  {
    path: '/login',
    component: resolve => require(['@/components/layout/page/Login.vue'], resolve),
    name: '登录'
  },
  {
    path: '/text',
    component: resolve => require(['@/components/layout/text.vue'], resolve),
    name: 'text'
  }
]

export default new Router({
  routes: constantRouterMap
})
