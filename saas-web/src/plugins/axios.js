// "use strict";
import Vue from 'vue';
import axios from "axios";
import router from '@/router'
import { Message } from 'element-ui';
import BASE_URL from '@/config'

axios.defaults.baseURL = BASE_URL
Vue.prototype.$http = axios;

let loading;
//改变请求的head内容
axios.interceptors.request.use(
  config => {
    //取到token
    const token = sessionStorage.getItem("Token")
    // 将tokexn设置到headers中
    if (token) {
        config.headers['dh-Token'] = token;
    }
    //开启loading
    loading = Vue.prototype.$loading({
      lock:true,
      text:'Loading',
      spinner:'el-icon-loading',
      background:'rgba(0,0,0,0,7)'
    })
    return config
  },
  error => {
    return Promise.reject(error)
  })

  axios.interceptors.response.use(
    response => {
      //解出loading
      if(loading){
        loading.close()
      }
      // 下载&&验证码
      let url = response.config.url
      if(url.indexOf("down") != -1){
        return response;
      }
      if(response.data.code == "200"){
        return response;
      }
      return Message.error(response.data.message)
    },
    error => {
      //解出loading
      if(loading){
        loading.close()
      }
      let message="";
      //检查网络是否连接
      if(error.message=='Network Error'){
        return Message.error("未能连接到服务器")
      }

      //检查是否请求时出错
      if(error.response){
        switch (error.response.status){
          case 400: message = '请求错误'; 
            break;
          case 403: message = '拒绝访问';
            break;
          case 404: message = '请求地址失效'; 
            break;
          case 408: message = '请求超时'; 
            break;
          case 501: message = '服务未实现'; 
            break;
          case 502: message = '网络错误'; 
            break;
          case 503: message = '服务不可用'; 
            break;
          case 504: message = '网络超时'; 
            break;
          case 505: message = 'HTTP版本不受支持';
            break;
        }
      }
      
      if(message){
        return Message.error(message)
      }

      if(error.response.data.message&&error.response.data.message == "Token失效请重新登录"){

        //这里防止其多次弹出，
        if(sessionStorage.getItem("morePOp") == "1"){
          Message.error(error.response.data.message)
          router.push("/login")
          sessionStorage.setItem("morePOp", "2");
        }else{
          return;
        }
      }
      return Promise.reject(error.response.data)
  })