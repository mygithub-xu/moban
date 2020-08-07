import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const router = new Router({

  mode: 'history',
  
  routes: [
    {
      path: '/',
      redirect: '/login',
      name: '登录'
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
      path: '/Layoutpage',
      component: resolve => require(['../components/layout/otherLayout.vue'], resolve),
      children:[
      {
        path: '/page/Dashboard',
        component: resolve => require(['../components/page/Dashboard.vue'], resolve),
        meta: {
          name: '系统首页',
          path: ''
        },
        name: '系统首页'
      },

      //系统配置---start
      {
        path: '/views/system/sysFiles/sysFilesList',
        component: resolve => require(['../views/system/sysFiles/sysFilesList'], resolve),
        meta: [],
        name: '文件上传'
      },
      
      {
        // 菜单配置
        path: '/views/system/sysMenu/sysMenuList',
        component: resolve => require(['../views/system/sysMenu/sysMenuList.vue'], resolve),
        meta: [],
        name: '菜单配置'
      },
  
      {
        // 权限管理---角色管理
        path: '/views/system/sysPrivilege/sysRole/sysroleList',
        component: resolve => require(['../views/system/sysPrivilege/sysRole/sysRoleList.vue'], resolve),
        meta: [
          {
            name: '权限管理',
            path: ''
          }
        ],
        name: '角色管理'
      },
  
      {
        // 权限管理---账号管理
        path: '/views/system/sysPrivilege/sysUser/sysUserList',
        component: resolve => require(['../views/system/sysPrivilege/sysUser/sysUserList.vue'], resolve),
        meta: [
          {
            name: '权限管理',
            path: ''
          }
        ],
        name: '账号管理'
      },
  
      {
        // 全局参数---字典管理
        path: '/views/system/sysParameter/sysDic/sysDicList',
        component: resolve => require(['../views/system/sysParameter/sysDic/sysDicList.vue'], resolve),
        meta: [
          {
            name: '全局参数',
            path: ''
          }
        ],
        name: '字典管理'
      },
      {
        // 全局参数---按钮配置
        path: '/views/system/sysParameter/sysBut/sysButList',
        component: resolve => require(['../views/system/sysParameter/sysBut/sysButList.vue'], resolve),
        meta: [
          {
            name: '全局参数',
            path: ''
          }
        ],
        name: '按钮管理'
      },
  
      //基本信息---start
      {
        // 登入日志
        path: '/views/system/syslog/sysLoginLog',
        component: resolve => require(['../views/system/syslog/sysLoginLog.vue'], resolve),
        meta: [
          {
            name: '基本信息',
            path: ''
          }
        ],
        name: '登入日志'
      },
      //基本信息---end
      
      //个人信息---start
      {
        // 个人中心
        path: '/views/user/userinfo',
        component: resolve => require(['../views/system/sysPrivilege/sysUser/modifyInformation.vue'], resolve),
        meta: [
          {
            name: '个人中心',
            path: ''
          }
        ],
        name: '个人信息'
      },
      //个人信息---end
        
      {
        path: '/page/404',
        component: resolve => require(['../components/page/404.vue'], resolve),
        meta: [],
        name: '404'
      },

      {
        path: '/views/system/sysCodeGeneration/sysCodeGeneration',
        component: resolve => require(['../views/system/sysCodeGeneration/sysCodeGeneration.vue'], resolve),
        meta: {
          name: '代码生成',
          path: ''
        },
        name: '代码生成'
      },

      //系统配置---end

      //演示---start
      {
        // 演示实例 --- 左树右表
        path: '/views/demonstration/treeTable',
        component: resolve => require(['../views/demonstration/treeTable.vue'], resolve),
        meta: [
          {
            name: '演示实例',
            path: ''
          }
        ],
        name: '左树右表'
      },
  
      {
        // 查询区表格
        path: '/views/demonstration/queryAreaTable',
        component: resolve => require(['../views/demonstration/queryAreaTable.vue'], resolve),
        meta: [
          {
            name: '演示实例',
            path: ''
          }
        ],
        name: '查询区表格'
      },

      {
        // 全表格
        path: '/views/demonstration/allTable',
        component: resolve => require(['../views/demonstration/allTable.vue'], resolve),
        meta: [
          {
            name: '演示实例',
            path: ''
          }
        ],
        name: '全表格'
      },

      {
        // 组件演示
        path: '/views/demonstration/module',
        component: resolve => require(['../views/demonstration/module.vue'], resolve),
        meta: [
          {
            name: '演示实例',
            path: ''
          }
        ],
        name: '组件演示'
      },

      {
        // echart图表
        path: '/views/demonstration/echart',
        component: resolve => require(['../views/demonstration/echart.vue'], resolve),
        meta: [
          {
            name: '演示实例',
            path: ''
          }
        ],
        name: 'echart图表'
      },
      //演示---start
  
    ]
    }
    
  ]
  
  })

export default router;
