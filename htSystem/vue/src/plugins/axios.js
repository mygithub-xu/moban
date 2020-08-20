// "use strict";
import Vue from 'vue';
import axios from "axios";
import { Message } from 'element-ui';

// Vue.prototype.$post=post; process.env.API_ROOT
axios.defaults.baseURL = process.env.API_ROOT
//等同于Vue.prototype.$axios = axios，命名习惯，全局使用$http
Vue.prototype.$http = axios;


axios.interceptors.request.use(
  config => {
    //这里取到token，可能你们不是这样取的。
    const token = sessionStorage.getItem("Token")
    if (token) {
      config.headers['Authorization'] =token;
    }
    return config
  },
  error => {
    return Promise.reject(error)
  });

  axios.interceptors.response.use(
    response => {
      return response
    },
    error => {
      if(error.message=='Network Error'){
        Message.error("请检查网络是否连接")
        return;
      }
      if(error.response.status=='404'){
        Message.error("请求地址失效")
        return;
      }
      return Promise.reject(error.response.data)
  })
