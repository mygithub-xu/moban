<template>
    <div>
        正在跳转
    </div>
</template>
<script>
import Axios from 'axios';
export default {
    data() {
        return {

        }
    },
    created(){
        //请求地址
        let reqUrl="http://newlogintest.intra.cntaiping.com/profile/oauth2";
        //项目地址
        let url="";
        //获取code的值，请求CODE
        let returnCode=this.$geturlpara.getUrlKey('code',window.location.href);

        //如果code和returnToken的值为空，说明时第一次进入系统，需发送我们系统的信息过去 请求CODE
        if(!returnCode){
            //第一步
            let url=window.location.href
            //系统参数
            let param1={
                client_id:"123",
                response_type:"code",
                redirect_uri:url,
            }
            //重定向到网址加参数
            let url1="http://localhost:8090?client_id="+param1.client_id+"&redirect_uri="+param1.redirect_uri+"&response_type="+param1.response_type
            window.location.href=url1
        }else{
            //第二步，请求token
            //当code存在，需去请求token
            let param2={
                code:returnCode,
                client_id:"123",
                client_secret:"123",
                redirect_uri:url,
            }
            let url2="http://localhost:4040/moban/api/blog/blogBlog/text"
            this.$http({
                url: url2,
                method: "get",
                params:param2
            }).then(res=>{
                let returnData=this.$geturlpara.getUrlParam(res.data);
                
                if(returnData.access_token){
                    //第三步，换取UserInfo
                    let url3="http://localhost:4040/moban/api/blog/blogBlog/text2"
                    this.$http({
                        url: url3,
                        method: "get",
                        params:returnData.access_token
                    }).then(res=>{
                        //判断error是否存在
                        if(!res.data.error){
                            //存在该用户，下一步需要接收menu的树形数据，并保存在内存中。


                            //更新登录状态
                            this.$store.dispatch('changeLoginStatusFun',true);
                            this.$router.push("/page/Dashboard").catch(()=>{});
                        }else{
                            console.log("该用户没有系统账号")
                        }
                    }).catch(error=>{

                        console.log("访问接口出错2")
                    })
                }

            }).catch(error=>{
                console.log("访问接口出错")
            })

            let url="https://www.baidu.com/authorize?code="+param2.code+"&client_id="+param2.client_id+"&client_secret="+param2.client_secret+"&redirect_uri="+param2.redirect_uri



        }
    },
}
</script>
<style lang="stylus">

</style>