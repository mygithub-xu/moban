import Vue from 'vue'
import VueRouter from 'vue-router'
import router from './router'
import App from './App'

//插件(vue.use引入插件，Vue.prototype使用方法)
import '@/plugins/element'
import '@/plugins/axios'
import '@/plugins/echarts'

import JsonExcel from 'vue-json-excel'
Vue.component('downloadExcel', JsonExcel)

//引入markdown 富文本编辑器（markDown，quill）
import '@/plugins/markDown'
import '@/plugins/quill'

//管理全局js文件
import '@/utils/index'

//全局变量
import store from '@/store/index'

//阿里图标
import '@/assets/css/iconfont.css'

//覆盖浏览器默认样式
import '@/assets/css/reset.css'

//自定义全局样式
import '@/assets/css/style.css'

Vue.config.productionTip = false
// 安装路由
Vue.use(VueRouter);
Vue.config.productionTip = false

new Vue({
  // 启用路由
  router,
  //启用状态管理
  store,
  render: h => h(App),
}).$mount('#app')
