// "use strict";

import Vue from 'vue';
import axios from "axios";
// import { get, post,del,patch} from '../static/js/http'//暴露https;
// Vue.prototype.$post=post;

axios.defaults.baseURL = 'http://120.25.197.11/moban/';//定义统一的请求url，，后面接接口名称即可
// axios.defaults.baseURL = 'http://127.0.0.1:4040/moban';
//等同于Vue.prototype.$axios = axios，命名习惯，全局使用$http
Vue.prototype.$http = axios;


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
