const tabRouter = { 
    state :{
        //tab路由集合
        openTab:[
            {
                name:"系统首页",
                path:"/page/Dashboard"
            }
        ],
        //当前路由
        indexTab:"/page/Dashboard"
    },
    getters:{
        getOpenTab:state=>{
            return state.openTab
        },
        getIndexTab:state=>{
            return state.indexTab
        },
    },
    mutations:{

        changeTab(state,data) {
            state.openTab=data;
        },
        addTab (state,data) {
            state.openTab.push(data)
        },
        changeIndexTab(state,indexTab){
            state.indexTab=indexTab;
        }
    },
    actions:{
        addTabFun(context,data){
            context.commit("addTab",data);
        },
        changeTabFun(context,data){
            context.commit("changeTab",data);
        },
        changeIndexTabFun(context,indexTab){
            context.commit("changeIndexTab",indexTab);
        }

    }
    
  }
  
  
  export default tabRouter;