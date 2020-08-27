    const avatar = { 
    state :{
        avatarUrl:"https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
        userName:"aaaa"
    },
    getters:{

        getAvatar:state=>{
            return state.avatarUrl
        },
        getUserName:state=>{
            return state.userName
        },
    },
    mutations:{
        changeUrl (state,url) {
           state.avatarUrl = url
        },
        changeUsername (state,name) {
            state.userName = name
        },
    },
    actions:{
        changeUrlFun(context,url){
            context.commit("changeUrl",url);
        },
        changeUsernameFun(context,url){
            context.commit("changeUsername",url);
        }
    }
    
  }
  
  
  export default avatar;