export default {
    getToken(){
        return sessionStorage.getItem("Token");
    },
    //获取当前用户信息
    getUser(){
        return JSON.parse(sessionStorage.getItem("User"));
    },
    //获取当前用户id
    getId(){
        return this.getUser().id;
    },
    //获取当前用户账号
    getLoginUser(){
        return this.getUser().loginUser;
    },  
    //获取当前用户名称
    getUserName(){
        return this.getUser().userName;     
    },
    getButtonUrlList(){
        return this.getUser().buttonUrlList;
    },
    getMenuData(){
        return this.getUser().menuData;
    }


}