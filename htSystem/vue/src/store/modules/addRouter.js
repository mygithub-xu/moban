const addRouter = { 
    state :{
        //路由list
        routerList:[],
        //当前路由 true:第一次登录,false:已经登录
        loginStatus:true
    },
    getters:{
        getRouterList:state=>{
            return state.routerList
        },
        getLoginStatus:state=>{
            return state.loginStatus
        },
    },
    mutations:{

        changeRouterList(state,data) {
            state.routerList=data
        },
        changeLoginStatus (state,data) {
            state.loginStatus=data
        },
    },
    actions:{
        changeRouterListFun(context,data){
            context.commit("changeRouterList",data);
        },
        changeLoginStatusFun(context,data){
            context.commit("changeLoginStatus",data);
        },

    }
    
  }
  
  
  export default addRouter;