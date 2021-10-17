import Vue from 'vue'
import VueRouter from 'vue-router'
import router from './router'
import App from './App'

//插件(vue.use引入插件，Vue.prototype使用方法)
import '@/plugins/element'
import '@/plugins/axios'


//管理全局js文件
import '@/utils/index'

// 安装路由
Vue.use(VueRouter);
Vue.config.productionTip = false

new Vue({
  // 启用路由
  router,
  //启用状态管理
  render: h => h(App),
}).$mount('#app')
