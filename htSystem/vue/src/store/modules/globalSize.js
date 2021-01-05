const globalSize = { 
    state :{
        globalWidth:"",
        globalHeight:""
    },
    getters:{
        getGlobalWidth:state=>{
            return state.globalWidth
        },
        getGlobalHeight:state=>{
            return state.globalHeight
        },
    },
    mutations:{
        globalChange(state,data) {
           state.globalWidth = data.globalWidth,
           state.globalHeight = data.globalHeight
        },
    },
    actions:{
        globalChangeFun(context,data){
            context.commit("globalChange",data);
        }
    }
    
  }
  export default globalSize;