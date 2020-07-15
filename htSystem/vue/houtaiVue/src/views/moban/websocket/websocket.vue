<template>
    <div>
      <input v-model="chat.data"/>
      <button @click="send">发送</button>
      <div>收到消息:</div>
    </div>

    
</template>
<script>
export default {
  data () {
        return {
            path:"ws://localhost:4040/moban/websocket/",
            socket:"",
            chat:{
                fromId:"",
                toId:"",
                time:"",
                data:"",
                type:""
            }
        }
    },
    created(){
        this.init()
    },
    methods:{
        init(){
          if(typeof(WebSocket) === "undefined"){
                alert("您的浏览器不支持socket")
            }else{
                this.chat.fromId=sessionStorage.getItem("userId");
                // 实例化socket
                this.socket = new WebSocket(this.path+this.chat.fromId);
                // 监听socket连接
                this.socket.onopen = this.open
                // 监听socket错误信息
                this.socket.onerror = this.error
                // 监听socket消息
                this.socket.onmessage = this.getMessage
            }
        },
        open() {
            console.log("socket连接成功")
        },
        error() {
            console.log("连接错误")
        },
        getMessage(msg) {
            var aa=JSON.parse(msg.data);;
            console.log("收到"+aa);
            console.log("收到"+aa.toId+"信息"+aa.data);
        },
        send() {
            this.chat.toId="1";
            this.$http.post(this.api.mbSysChatSend,this.chat).then(res=>{
                if(res.data.code=="200"){
                 this.$message.success(res.data.body);
              } else {
                this.$message.error(res.data.body);
              }
            });
        },
        close() {
            console.log("socket已经关闭")
        }
    },
    destroyed () {
        // 销毁监听
        this.socket.onclose = this.close
    }
}
</script>
<style lang="less" scoped>

</style>