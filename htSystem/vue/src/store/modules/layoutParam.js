const layoutParam = { 
    state :{
        layoutType: "1",
        layoutParam:{
            siderBGColor:"#333744",// 侧边栏颜色
            siderFontColor:"#ffffff",// 侧边栏字体颜色
            headerBGColor:"#ffffff",// 头部颜色
            headerFontColor:"#000000",// 头部字体颜色(右侧用户字体颜色)
            activeFontColor:"#3b9eff",// menu当前字体颜色
            activeHoverBGColor:"#000000"// menu悬停颜色
        },
        
        sysType:{
            sysTypeStatus:true, //默认true为系统风格
            modelType: "1", //默认系统风格
            modeTypeList:[
                  {
                    value: '1',
                    label: '经典（默认）'
                  }, {
                    value: '2',
                    label: '简洁白'
                  },  {
                    value: '3',
                    label: '永恒之蓝'
                  }, 
            ]
        },
        siderParam:{
            menuWidth: 200,
            isCollapse: false
        },
        companyAtt:{
            imgSrc:require("@/assets/logo.png"),
            name:"后台管理系统"
        },
        //侧边栏颜色
        siderMenuColorList:["#409EFF","#67C23A","#ffffff","#E6A23C","#F56C6C","#909399","#303133","#333744"],
        //顶部导航栏颜色
        // topMenuColorList:["#409EFF","#67C23A","#ffffff","#E6A23C","#F56C6C","#909399"],
        topMenuColorList:["#303133","#ffffff"],
    },
    getters:{
        getLayoutType:state=>{
            return state.layoutType
        },
        getLayoutParam:state=>{
            return state.layoutParam
        },
        getModelType:state=>{
            return state.modelType
        },
        getSiderParam:state=>{
            return state.siderParam
        },
        getCompanyAtt:state=>{
            return state.companyAtt
        },
        getSiderMenuColorList:state=>{
            return state.siderMenuColorList
        },
        getTopMenuColorList:state=>{
            return state.topMenuColorList
        },
        getSysType:state=>{
            return state.sysType
        },

    },
    mutations:{
        changeLayoutType(state,data) {
            state.layoutType=data
        },
        changeLayoutParam (state,data) {
            //侧边栏颜色
            if(data.name=='siderBGColor'){
                //更新菜单颜色
                state.layoutParam.siderBGColor=data.value
                //菜单为白色则为黑色，其他均为白色
                state.layoutParam.siderFontColor=data.value=='#ffffff'?'#000000':'#ffffff'
            }
            //头部颜色
            if(data.name=='headerBGColor'){
                //更新头部颜色
                state.layoutParam.headerBGColor=data.value
                //头部背景为白色则为黑色，其他均为白色
                state.layoutParam.headerFontColor=data.value=='#ffffff'?'#000000':'#ffffff'
            }
        },
        initLayoutParam(state){
            state.layoutParam = {
                siderBGColor:"#333744",// 侧边栏颜色
                siderFontColor:"#ffffff",// 侧边栏字体颜色
                headerBGColor:"#ffffff",// 头部颜色
                headerFontColor:"#000000",// 头部字体颜色(右侧用户字体颜色)
                activeFontColor:"#3b9eff",// menu当前字体颜色
                activeHoverBGColor:"#000000"// menu悬停颜色
            }
            state.layoutType = '1'
        },
        changeModelType(state,data){
            if(data){
                switch (data){
                    case '1': state.layoutParam={
                                siderBGColor:"#333744",// 侧边栏颜色
                                siderFontColor:"#ffffff",// 侧边栏字体颜色
                                headerBGColor:"#ffffff",// 头部颜色
                                headerFontColor:"#000000",// 头部字体颜色
                                activeFontColor:"#3b9eff",// menu当前字体颜色
                                activeHoverBGColor:"#000000"// menu悬停颜色
                        }
                    break;
                    case '2': state.layoutParam={
                                siderBGColor:"#333744",// 侧边栏颜色
                                siderFontColor:"#ffffff",// 侧边栏字体颜色
                                headerBGColor:"#ffffff",// 头部颜色
                                headerFontColor:"#000000",// 头部字体颜色
                                activeFontColor:"#3b9eff",// menu当前字体颜色
                                activeHoverBGColor:"#000000"// menu悬停颜色
                        }
                      break;
                    default : data='1';
                      break;
                  }
            }
            state.modelType=data
        },
        changeSiderParam (state,data) {
            //true是收缩
            if(data){
                state.siderParam={
                    menuWidth: 50,
                    isCollapse: true
                }
            }else{
                state.siderParam={
                    menuWidth: 200,
                    isCollapse: false
                }
            }
        },
        changeSystemBGColor(state,data){

            if(data){
                switch (data){
                    case '#ffffff': state.layoutParam={
                                        siderBGColor:"#ffffff",// 侧边栏颜色
                                        siderFontColor:"#000000",// 侧边栏字体颜色
                                        headerBGColor:"#ffffff",// 头部颜色
                                        headerFontColor:"#000000",// 头部字体颜色
                                        activeFontColor:"#3b9eff",// menu当前字体颜色
                                        activeHoverBGColor:"#000000"// menu悬停颜色
                                    }
                    break;
                    case '#303133': state.layoutParam={
                                        siderBGColor:"#303133",// 侧边栏颜色
                                        siderFontColor:"#ffffff",// 侧边栏字体颜色
                                        headerBGColor:"#303133",// 头部颜色
                                        headerFontColor:"#ffffff",// 头部字体颜色
                                        activeFontColor:"#3b9eff",// menu当前字体颜色
                                        activeHoverBGColor:"#40E0D0"// menu悬停颜色
                                    }
                    break;
                    case '#409EFF': state.layoutParam={
                                        siderBGColor:"#409EFF",// 侧边栏颜色
                                        siderFontColor:"#ffffff",// 侧边栏字体颜色
                                        headerBGColor:"#409EFF",// 头部颜色
                                        headerFontColor:"#ffffff",// 头部字体颜色
                                        activeFontColor:"#3b9eff",// menu当前字体颜色
                                        activeHoverBGColor:"#ffffff"// menu悬停颜色
                                    }
                    break;
                  }
            }


            if(data=='#ffffff'){
                state.layoutParam={
                    siderBGColor:"#ffffff",// 侧边栏颜色
                    siderFontColor:"#000000",// 侧边栏字体颜色
                    headerBGColor:"#ffffff",// 头部颜色
                    headerFontColor:"#000000",// 头部字体颜色
                    activeFontColor:"#3b9eff",// menu当前字体颜色
                    activeHoverBGColor:"#000000"// menu悬停颜色
                }
            }
            if(data=='#303133'){
                state.layoutParam={
                    siderBGColor:"#303133",// 侧边栏颜色
                    siderFontColor:"#ffffff",// 侧边栏字体颜色
                    headerBGColor:"#303133",// 头部颜色
                    headerFontColor:"#ffffff",// 头部字体颜色
                    activeFontColor:"#3b9eff",// menu当前字体颜色
                    activeHoverBGColor:"#ffffff"// menu悬停颜色
                }
            }
        }

    },
    actions:{
        //改变布局类型
        changeLayoutTypeFun(context,data){
            context.commit("changeLayoutType",data);
        },
        //改变布局参数
        changeLayoutParamFun(context,data){
            context.commit("changeLayoutParam",data);
        },
        //初始化布局参数
        initLayoutParamFun(context){
            context.commit("initLayoutParam");
        },
        SystemBGColorFun(context,data){
            context.commit("changeSystemBGColor",data);
        },

        changeModelTypeFun(context,data){
            context.commit("changeModelType",data);
        },
        changeSiderParamFun(context,data){
            context.commit("changeSiderParam",data);
        },
        changeSystemBGColorFun(context,data){
            context.commit("changeSystemBGColor",data);
        },
    }
    
  }
  
  export default layoutParam;