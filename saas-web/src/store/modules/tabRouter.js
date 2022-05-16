const tabRouter = { 
    state :{
        //tab路由集合
        openTab:[
            {
                name:"系统首页",
                path:"/page/Dashboard",
                component:"Dashboard"
            }
        ],
        keepTab:[],
        closeTab:[],
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
        getCloseTab:state=>{
            return state.closeTab
        },
        getKeepTab:state=>{
            return state.keepTab
        },
    },
    mutations:{
        initTab(state){
            state = {
                //tab路由集合
                openTab:[
                    {
                        name:"系统首页",
                        path:"/page/Dashboard",
                        component:"Dashboard"
                    }
                ],
                keepTab:[],
                closeTab:[],
                //当前路由
                indexTab:"/page/Dashboard"
            }
        },
        changeTab(state,data) {
            let keep=[]
            data.forEach(v => {
                if(!!v.component){
                    keep.push(v.component);
                }
            })
            state.keepTab=keep;
            state.openTab=data;
        },
        addTab (state,data) {
            state.openTab.push(data)
        },
        changeIndexTab(state,indexTab){
            state.indexTab=indexTab;
        },

        addCloseTab(state,tab){
            let aaa=state.closeTab;
            let flag=false;
            for(let i=0;i<aaa.length;i++){
                if(aaa[i].path==tab.path){
                    flag=true;
                    break;
                }
            }
            if(!flag){
                aaa.push(tab);
                state.closeTab=aaa;
            }
        },

        changeCloseTab(state,tabs){
            state.closeTab=tabs;
        }
    },
    actions:{
        initTabFun(context){
            context.commit("initTab");
        },
        addTabFun(context,data){
            context.commit("addTab",data);
        },
        changeTabFun(context,data){
            context.commit("changeTab",data);
        },
        changeIndexTabFun(context,indexTab){
            context.commit("changeIndexTab",indexTab);
        },
        addCloseTabFun(context,tab){
            context.commit("addCloseTab",tab);
        },
        changeCloseTabFun(context,tabs){
            context.commit("changeCloseTab",tabs);
        },

    }
    
  }
  
  
  export default tabRouter;