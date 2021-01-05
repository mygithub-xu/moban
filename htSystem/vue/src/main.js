// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import VueRouter from 'vue-router'
import router from './router'
import App from './App'

//插件(vue.use引入插件，Vue.prototype使用方法)
import '@/plugins/element'
import '@/plugins/axios'
import '@/plugins/echarts'

//管理全局js文件
import '@utils/index'

//全局变量
import store from '@/store/index'


//quill富文本样式
import 'quill/dist/quill.core.css';
import 'quill/dist/quill.snow.css';
import 'quill/dist/quill.bubble.css';

//阿里图标
import '@/assets/css/iconfont.css'

//覆盖浏览器默认样式
import '@/assets/css/reset.css'

//自定义全局样式
import '@/assets/css/style.css'

Vue.config.productionTip = false
// 安装路由
Vue.use(VueRouter);

/* eslint-disable no-new */
new Vue({
  el: '#app',
  // 启用路由
  router,
  //启用状态管理
  store,
  // 启用 ElementUI
  render: h => h(App)
});