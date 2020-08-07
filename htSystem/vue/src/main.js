// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import VueRouter from 'vue-router'
import router from './router'
import App from './App'

//插件(vue.use引入插件，Vue.prototype使用方法)
import './plugins/element'
import './plugins/axios'
import i18n from './plugins/i18n/index'//国际化
import locale from 'element-ui/lib/locale/lang/en'
Vue.use({locale});

//全局组件
import globalComponents from './components/index'
Vue.use(globalComponents)

//全局api接口
import api from "./api/api-config";//这里规定统一一个页面放入后台接口
Vue.prototype.api = api;//全局使用api

//全局变量
import store from './store/index'

//quill富文本样式
import 'quill/dist/quill.core.css';
import 'quill/dist/quill.snow.css';
import 'quill/dist/quill.bubble.css';

//使用echarts
import echarts from 'echarts'
Vue.prototype.$echarts = echarts

//阿里图标
import './assets/css/iconfont2.css'
import './assets/css/iconfont.css'

//默认样式
import './assets/css/reset.css'//覆盖浏览器默认样式
import './assets/css/style.css'//自定义全局样式

//时间工具类
import timeUtils from './assets/js/timeUtils'//暴露https;
Vue.prototype.$timeUtils=timeUtils;

//过滤器
import filter from './assets/js/filter.js'
for (const key in filter) {
    if (filter.hasOwnProperty(key)) {
        const element = filter[key]
        Vue.filter(key, element)
    }
}

Vue.config.productionTip = false
// 安装路由
Vue.use(VueRouter);

  /**
   * 1.“to”: 即将要进入的目标 路由对象;
   * 2."from": 当前导航正要离开的路由对象；
   * “next”: Function: 确保要调用 next 方法,否则钩子就不会被 resolved。
   */
  // router.beforeEach((to,from,next)=>{
  //   console.log(to.path);
  //   const token = sessionStorage.getItem('Token');
  //   if(!token && to.path !== '/login'){
  //     next('/login')
  //   }else if(to.path=='/page/Dashboard'||to.path=='/login'||to.path=='/page/404'){
  //     next();
  //   } else {
  //     //数组形式取出
  //     let menuList = JSON.parse(sessionStorage.getItem('menuList'));
  //     var flag=false;
  //     for (let index in menuList) {
  //       if(menuList[index].url==to.path){
  //         flag=true;
  //         break;
  //       }
  //     }
  //     if(flag){
  //       next();
  //     }else{
  //       next('/page/404');
  //     }
      
  //   }
  // });


  //按钮根据权限显示和影藏
  Vue.directive('has', {
    inserted: function (el, binding) {
      if (!permissionJudge(binding.value)) {
        el.parentNode.removeChild(el);
      }
  
      function permissionJudge(value) {
        let list = JSON.parse(sessionStorage.getItem('buttonUrlList'));
        if(!list){
          return false;
        }
        for (let i=0;i<list.length;i++) {
          if (list[i] === value) {
            return true;
          }
        }
        return false;
      }
    }
  });


  

/* eslint-disable no-new */
new Vue({
  el: '#app',
  //启用国际化
  i18n,
  // 启用路由
  router,
  //启用状态管理
  store,
  // 启用 ElementUI
  render: h => h(App)
});