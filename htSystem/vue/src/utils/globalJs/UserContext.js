export default {
    setToken(Token){
        let UserInfo=this.getUser();
        UserInfo.token = Token;
        sessionStorage.setItem("User", JSON.stringify(UserInfo))
    },
    getToken(){
        let UserInfo=this.getUser();
        return !UserInfo?'':UserInfo.token;
    },
    //获取当前用户信息
    getUser(){
        return JSON.parse(sessionStorage.getItem("User"));
    },
    //获取当前用户id
    getUserId(){
        return this.getUser().id;
    },
    //获取当前用户名称
    getName(){
        return this.getUser().userName;
    },  
    //获取当前用户账号
    getAccount(){
        return this.getUser().loginName;     
    },
    //获取当前用户组织
    getOrg(){
        return this.getUser().org;     
    },
    //获取当前用户部门
    getDept(){
        return this.getUser().dept;     
    },
    //获取当前用户组织id
    getOrgId(){
        return this.getOrg().org_id;     
    },
    //获取当前用户组织机构代码
    getOrgCode(){
        return this.getOrg().org_code;     
    },
    //获取当前用户部门id
    getDeptId(){
        return this.getDept().dept_id;     
    },
    //获取当前用户部门机构代码
    getDeptCode(){
        return this.getDept().dept_code;     
    },

}