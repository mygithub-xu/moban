// "use strict";
import Vue from 'vue';
import axios from "axios";

// Vue.prototype.$post=post; process.env.API_ROOT
axios.defaults.baseURL = process.env.API_ROOT
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
