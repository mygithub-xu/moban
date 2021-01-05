import Vue from 'vue'

//管理全局js文件，可以在此统一管理引入，也可以在main.js中引入
//时间工具类
import dateUtils from '@utils/common/dateUtils'//暴露https;
Vue.prototype.$dateUtils=dateUtils;

//跳转
import geturlpara from '@utils/common/geturlpara'//暴露https;
Vue.prototype.$geturlpara=geturlpara;

//自定义指令
import preventReClick from '@utils/common/preventReClick'//暴露https;
Vue.use(preventReClick)

//全局组件
import globalComponents from '@/components/index'
Vue.use(globalComponents)

//全局api接口
import api from "@/api/api-config";//这里规定统一一个页面放入后台接口
Vue.prototype.api = api;//全局使用api

//引入动态路由js
import '@utils/dynamicRouter'

//引入动态路由js
import '@utils/filters'

//获取用户信息js
import User from "@utils/common/UserContext"
Vue.prototype.$User = User;

//操作数组，转化类型等等，，工具类
import utils from "@utils/common/utils"
Vue.prototype.$utils = utils;

//引入bus
import bus from "@utils/common/bus"
Vue.prototype.$bus = bus;