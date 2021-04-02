import router from '@/router/index'
import store from '@/store/index'

import Layout from '@/components/layout/text'
import page404 from '@/components/layout/page/404'
import Dashboard from '@/views/modules/dashboard/Dashboard'
import oneTemplate from '@/views/template/one/query'
// import oneTemplate from '@/views/modules/phoneModel/query'
import User from "@/utils/common/UserContext"

var getRouter = null //暂存菜单数据

router.beforeEach((to, from, next) => {
  //过滤静态路由
  if (to.path == '/login' || to.path == '/page/404') {
    return next();
  }
  //获取token,如果token为空，则返回登录页面
  if (!getToken()) {
    return next('/login')
  }
  //登录状态
  var loginStatus = getLoginStatus();
  //判断是否第一次登录，是，则更新路由，不是，则无需更新
  if (loginStatus) {
    getRouter = getObjArr()
    routerGo(to, next)
  } else {
    try {
      next()
    } catch (e) {
      next('/page/404')
    }
  }

})


function routerGo (to, next) {
  var loginStatus = getLoginStatus();
  if (loginStatus) {
    //过滤路由
    getRouter = filterAsyncRouter(getRouter)
    //动态添加路由
    router.addRoutes(getRouter)
    //改变登录状态
    changeLoginStatus()
  }

  next(to.path)
}

//获取菜单数据
function getObjArr () {
  return User.getMenuData()
}
//改变登录状态
function changeLoginStatus () {
  store.state.addRouter.loginStatus = false
}
// 获取登录状态
function getLoginStatus () {
  return store.state.addRouter.loginStatus
}
//过滤菜单，使其可以被路由添加
function filterAsyncRouter (routers) {

  //固定的主页面Layout，副页面Dashboard，有其他固定父页面，应在children处添加。
  let fakeRouter = [
    {
      path: "/Layoutpage",
      component: Layout,
      children: [
        {
          path: "/page/Dashboard",
          name: "首页",
          component: Dashboard,
        },
        {
          path: "/page/404",
          name: "404",
          component: page404,
        },
        {
          path: "/one",
          name: "one",
          component: oneTemplate,
        },
      ]
    },
    {
      path: "*",
      redirect: "/login"
    },
  ]

  //迭代菜单，形成路由
  if (routers) {
    let fiterRouter = treeIteration(routers)
    //将路由合并成一个list
    let baseRouter = fakeRouter[0].children
    if (!!fiterRouter) {
      baseRouter = baseRouter.concat(fiterRouter)
    }
    fakeRouter[0].children = baseRouter;
  }
  return fakeRouter
}

//迭代树形数据
function treeIteration (list) {
  var kidRouters = []
  list.forEach(v => {
    //当该菜单没有下级时，为其添加component，当component中文件路径错误时，添加404的路径
    if (!v.children) {
      var comp;
      var routerItem;
      try {
        comp = loadView(v.url);
      } catch (e) {
        comp = page404;
      }
      var routerItem = {
        path: v.url,
        name: v.menuName,
        component: comp
      }
      kidRouters.push(routerItem);
    }
    //当该菜单含有下级时，迭代，并合并迭代后传出的list
    if (!!v.children) {
      let treeList = treeIteration(v.children)
      kidRouters = kidRouters.concat(treeList)
    }

  });

  return kidRouters;
}

function loadView (view) {
  // 判断是否存在此文件,不存在则被catch到
  require('@/views' + view + '.vue');

  return () => import('@/views' + view + '.vue');

}
function getToken () {
  return User.getToken()
}
