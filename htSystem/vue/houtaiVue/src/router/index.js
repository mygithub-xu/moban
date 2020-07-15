import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const router = new Router({

  mode: 'history',
  
  routes: [
    {
      path: '/',
      redirect: '/login',
      name: '首页'
    },
  
    {
      path: '*',
      redirect: '/page/404',
      name: '404'
    },
  
    {
      path: '/login',
      component: resolve => require(['../components/page/Login.vue'], resolve),
      name: '登录'
    },
  
  
    {
      path: '/login',
      component: resolve => require(['../components/layout/Layout.vue'], resolve),
      meta: {
        title: '首页'
      },
      children:[
      {
        path: '/page/Dashboard',
        component: resolve => require(['../components/page/Dashboard.vue'], resolve),
        meta: {
          title: '系统首页',
          idj: ''
        },
        name: '首页'
      },
      //系统配置---start
      {
        path: '/page/user',
        component: resolve => require(['../components/page/user.vue'], resolve),
        meta: {
          title: '管理员登入记录',
          idj: ''
        },
        name: '管理员登入记录'
      },
      //系统配置---start
      {
        path: '/views/system/sysFiles/sysFilesList',
        component: resolve => require(['../views/system/sysFiles/sysFilesList'], resolve),
        meta: {
          title: '文件上传',
          idj: ''
        },
        name: '文件上传'
      },
      
      {
        // 菜单配置
        path: '/views/system/sysMenu/sysMenuList',
        component: resolve => require(['../views/system/sysMenu/sysMenuList.vue'], resolve),
        meta: {
          title: '菜单配置',
          permission: false,
          idj: ''
        },
        name: '菜单配置'
      },
  
      {
        // 权限管理---角色管理
        path: '/views/system/sysPrivilege/sysRole/sysroleList',
        component: resolve => require(['../views/system/sysPrivilege/sysRole/sysRoleList.vue'], resolve),
        meta: {
          title: '角色管理',
          permission: false,
          idj: ''
        },
        name: '角色管理'
      },
  
      {
        // 权限管理---账号管理
        path: '/views/system/sysPrivilege/sysUser/sysUserList',
        component: resolve => require(['../views/system/sysPrivilege/sysUser/sysUserList.vue'], resolve),
        meta: {
          title: '账号管理',
          permission: false,
          idj: ''
        },
        name: '账号管理'
      },
  
      {
        // 全局参数---字典管理
        path: '/views/system/sysParameter/sysDic/sysDicList',
        component: resolve => require(['../views/system/sysParameter/sysDic/sysDicList.vue'], resolve),
        meta: {
          title: '字典管理',
          permission: false,
          idj: ''
        },
        name: '字典管理'
      },
      {
        // 全局参数---按钮配置
        path: '/views/system/sysParameter/sysBut/sysButList',
        component: resolve => require(['../views/system/sysParameter/sysBut/sysButList.vue'], resolve),
        meta: {
          title: '按钮配置',
          permission: false,
          idj: ''
        },
        name: '按钮管理'
      },
  
      //基本信息---start
      {
        // 登入日志
        path: '/views/system/syslog/sysLoginLog',
        component: resolve => require(['../views/system/syslog/sysLoginLog.vue'], resolve),
        meta: {
          title: '登入日志',
          permission: false,
          idj: ''
        },
        name: '登入日志'
      },
      //基本信息---end
      
          //基本信息---start
          {
            // 个人中心
            path: '/views/user/userinfo',
            component: resolve => require(['../views/system/sysPrivilege/sysUser/modifyInformation.vue'], resolve),
            meta: {
              title: '个人中心',
              permission: false,
              idj: ''
            },
            name: '个人中心'
          },
          //基本信息---end
          
      //系统配置---end
  
      //模板---start
      {
        // 模板1
        path: '/views/moban/moban1/mobanList',
        component: resolve => require(['../views/moban/moban1/mobanList.vue'], resolve),
        meta: {
          title: '模板1',
          permission: false,
          idj: ''
        },
        name: '模板1'
      },
  
      {
        // 模板2
        path: '/views/moban/moban2/mobanList',
        component: resolve => require(['../views/moban/moban2/mobanList.vue'], resolve),
        meta: {
          title: '模板2',
          permission: false,
          idj: ''
        },
        name: '模板2'
      },
  
      //测试websocket
      {
        // websocket
        path: '/views/moban/websocket/websocket',
        component: resolve => require(['../views/moban/websocket/websocket.vue'], resolve),
        meta: {
          title: 'websocket',
          permission: false,
          idj: ''
        },
        name: 'websocket'
      },
  
      {
        path: '/views/moban/textUser/textUser',
        component: resolve => require(['../views/moban/textUser/textUserList.vue'], resolve),
        meta: {
          title: '测试',
          permission: false,
          idj: ''
        },
        name: '测试'
      },
  
      //blog后台--end
  
      {
        path: '/page/404',
        component: resolve => require(['../components/page/404.vue'], resolve),
        meta: {
          title: '404',
          idj: ''
        },
        name: '404'
      },
  
    ]
    }
    
  ]
  
  })

export default router;

// export default new Router({
//   routes:[



// ]
// })
