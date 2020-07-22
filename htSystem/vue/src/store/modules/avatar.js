    const avatar = { 
    state :{
        avatarUrl:"",
    },
    getters:{

        getAvatar:state=>{
            return state.avatarUrl
        }
    },
    mutations:{
        changeUrl (state,url) {
           state.avatarUrl = url
        },
       
    },
    actions:{
        changeUrlFun(context,url){
            context.commit("changeUrl",url);
        }
    }
    
  }
  
  
  export default avatar;