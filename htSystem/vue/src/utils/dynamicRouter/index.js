import router from '@router/index'
const _import = require('@router/_import_' + process.env.NODE_ENV) //获取组件的方法
import store from '@/store/index'

import Layout from '@comp/layout/otherLayout'
import page404 from '@comp/layout/page/404'
import Dashboard from '@views/modules/dashboard/Dashboard'

var getRouter //暂存菜单数据

router.beforeEach((to, from, next) => {
  
    //过滤静态路由
    if(to.path=='/login'||to.path=='/page/404'||to.path=='/checkAuthority'){
        return next();
    }
    
     //登录状态
    var loginStatus=store.state.addRouter.loginStatus;
    //判断是否第一次登录，是，则更新路由，不是，则无需更新
    if (loginStatus) {
        getRouter = getObjArr('menuData')
        if (getRouter){
          store.state.addRouter.loginStatus=false
          routerGo(to, next)
        }else{
          next('/login')
        }
    } else {
        next()
    }

})


function routerGo(to, next) {
    getRouter = filterAsyncRouter(getRouter) //过滤路由
    console.log(getRouter);
    router.addRoutes(getRouter) //动态添加路由
    next(to.path)
}

function getObjArr(name) {
  return JSON.parse(sessionStorage.getItem(name));

}

//过滤菜单，使其可以被路由添加
function filterAsyncRouter(routers) {
  
  //固定的主页面Layout，副页面Dashboard，有其他固定父页面，应在children处添加。
  let fakeRouter=[
    {
      path: "/Layoutpage",
      component: Layout,
      children:[
        {
          path: "/page/Dashboard",
          name: "首页",
          component: Dashboard,
        }
      ]
    }
  ]

  //迭代菜单，形成路由
  let fiterRouter=treeIteration(routers)

  //将路由合并成一个list
  let baseRouter=fakeRouter[0].children
  if(!!fiterRouter){
    baseRouter=baseRouter.concat(fiterRouter)
  }
  fakeRouter[0].children=baseRouter;

  return fakeRouter
}

//迭代树形数据
function treeIteration(list) {
  var kidRouters=[]
  list.forEach(v => {

    //当该菜单没有下级时，为其添加component，当component中文件路径错误时，添加404的路径
    if(!v.children){
      var comp;
      var routerItem;
      try{
        comp=_import(v.url);
      }catch(e){
        comp=page404;
      }
      var routerItem={
        path: v.url,
        name: v.menuName,
        component: comp
      }
      kidRouters.push(routerItem);
    }
    //当该菜单含有下级时，迭代，并合并迭代后传出的list
    if(!!v.children){
      let treeList=treeIteration(v.children)
      kidRouters=kidRouters.concat(treeList)
    }
    
  });

  return kidRouters;
}

