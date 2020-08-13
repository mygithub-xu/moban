import Vue from 'vue'

//管理全局js文件，可以在此统一管理引入，也可以在main.js中引入
//时间工具类
import timeUtils from '@utils/globalJs/timeUtils'//暴露https;
Vue.prototype.$timeUtils=timeUtils;

//跳转
import geturlpara from '@utils/globalJs/geturlpara'//暴露https;
Vue.prototype.$geturlpara=geturlpara;

//自定义指令
import preventReClick from '@utils/globalJs/preventReClick'//暴露https;
Vue.use(preventReClick)

//全局组件
import globalComponents from '@/components/index'
Vue.use(globalComponents)

//全局api接口
import api from "@/api/api-config";//这里规定统一一个页面放入后台接口
Vue.prototype.api = api;//全局使用api

//引入动态路由js
import '@utils/dynamicRouter'

//引入数字精确计算js
import '@utils/globalJs/precision'