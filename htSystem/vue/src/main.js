// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import VueRouter from 'vue-router'
import router from './router'
import App from './App'

//全局组件
import globalComponents from './components/index';

//传值
import store from './store/index'

//图标

import './icons' // 自定义图标
import '../static/style.css'//图标样式

//阿里图标
import './assets/css/iconfont.css'

//默认样式
import '../static/css/reset.css'//覆盖浏览器默认样式
import '../static/css/style.css'//自定义全局样式


// 导入 ElementUI
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

//quill富文本样式
import 'quill/dist/quill.core.css';
import 'quill/dist/quill.snow.css';
import 'quill/dist/quill.bubble.css';

//使用echarts
import echarts from 'echarts'
Vue.prototype.$echarts = echarts

//封装axios
import axios from 'axios';
import { get, post,del,patch} from '../static/js/http'//暴露https;
Vue.prototype.$post=post;

axios.defaults.baseURL = 'http://127.0.0.1:4040/moban/';//定义统一的请求url，，后面接接口名称即可
//等同于Vue.prototype.$axios = axios，命名习惯，全局使用$http
Vue.prototype.$http = axios;

//全局api接口
import api from "./api/api-config";//这里规定统一一个页面放入后台接口
Vue.prototype.api = api;//全局使用api

//时间工具类
import timeUtils from '../static/js/timeUtils'//暴露https;
Vue.prototype.$timeUtils=timeUtils;

//国际化
import i18n from './i18n/index'
import locale from 'element-ui/lib/locale/lang/en'

Vue.use({locale});

Vue.config.productionTip = false


// 安装路由
Vue.use(VueRouter);

// 安装 ElementUI
Vue.use(ElementUI);

//全局组件
Vue.use(globalComponents);



//改变请求的head内容
axios.interceptors.request.use(
  config => {
    //这里取到token，可能你们不是这样取的。
    const token = sessionStorage.getItem("Token")
    if (token) {
      // 这里将tokexn设置到headers中，header的key是authToken，这个key值根据你的需要进行修改即可
      // config.headers.authToken = token
      config.headers['Authorization'] =token;
    }
    return config
  },
  error => {
    return Promise.reject(error)
  });


  // axios.interceptors.response.use(
  //   response => {
  //     return response;
  //   },
  //   error => {
  //     if (error.response) {
  //       console.log(error.response.status)
  //       //统一定义401为登录失败状态码
  //       if(error.response.status == 401){
  //         alt.error("登陆失效,请重新登录!");
  //         //移除token
  //         sessionStorage.removeItem("Token");
  //       }
  //     }
  //     // return Promise.reject(error.response.data)
  // })


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