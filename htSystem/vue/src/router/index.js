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
      
      //个人信息---start
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
      //个人信息---end
        
      {
        path: '/page/404',
        component: resolve => require(['../components/page/404.vue'], resolve),
        meta: {
          title: '404',
          idj: ''
        },
        name: '404'
      },
      //系统配置---end

      //演示---start
      {
        // 左树右表
        path: '/views/demonstration/treeTable',
        component: resolve => require(['../views/demonstration/treeTable.vue'], resolve),
        meta: {
          title: '左树右表',
          permission: false,
          idj: ''
        },
        name: '左树右表'
      },
  
      {
        // 查询区表格
        path: '/views/demonstration/queryAreaTable',
        component: resolve => require(['../views/demonstration/queryAreaTable.vue'], resolve),
        meta: {
          title: '查询区表格',
          permission: false,
          idj: ''
        },
        name: '查询区表格'
      },

      {
        // 全表格
        path: '/views/demonstration/allTable',
        component: resolve => require(['../views/demonstration/allTable.vue'], resolve),
        meta: {
          title: '全表格',
          permission: false,
          idj: ''
        },
        name: '全表格'
      },

      {
        // 组件演示
        path: '/views/demonstration/module.vue',
        component: resolve => require(['../views/demonstration/module.vue'], resolve),
        meta: {
          title: '组件演示',
          permission: false,
          idj: ''
        },
        name: '组件演示'
      },

      {
        // echart图表
        path: '/views/demonstration/echart',
        component: resolve => require(['../views/demonstration/echart.vue'], resolve),
        meta: {
          title: 'echart图表',
          permission: false,
          idj: ''
        },
        name: 'echart图表'
      },
      //演示---start
  
    ]
    }
    
  ]
  
  })

export default router;
