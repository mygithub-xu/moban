<template>
    <div>
        <div>
            我是用户A<button @click="login('A')">登录</button>
            <input v-model="chatContentA"/>
            <button @click="send('A')">发送消息给B</button>
            <div class="chatdiv">收到消息：{{chatA}}</div>
        </div>
        <div>
            我是用户B<button @click="login('B')">登录</button>
            <input v-model="chatContentB"/>
            <button @click="send('B')">发送消息给A</button>
            <div class="chatdiv">收到消息：{{chatB}}</div>
        </div>

        <div>
            我是群主发送群消息<button @click="login('All')">登录</button>
            <input v-model="chatContentAll"/>
            <button @click="sendAll()">发送消息给所有人</button>
        </div>
    </div>

    
</template>
<script>
// import webSocker_URL from '@/config'
export default {
  data () {
        return {
            path:"ws://123.60.5.25/moban/websocket/",
            socket:"",
            chatA:"aa",
            chatB:"bb",
            chatContentAll:'',
            chatContentA:'',
            chatContentB:'',
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
        // this.init()
    },
    methods:{
        login(type){
          if(typeof(WebSocket) === "undefined"){
                alert("您的浏览器不支持socket")
            }else{
                this.chat.fromId = type;
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
            var aa=JSON.parse(msg.data)
            if(aa.toId == 'A'){
                this.chatA = "收到"+aa.toId+"的信息："+ aa.data
            }
            if(aa.toId == 'B'){
                this.chatB = "收到"+aa.toId+"的信息："+ aa.data
            }
            if(aa.fromId == 'All'){
                this.chatA = "收到"+aa.toId+"的信息："+ aa.data
                this.chatB = "收到"+aa.toId+"的信息："+ aa.data
            }
            console.log("收到"+aa.toId+"的信息："+ aa.data)
        },
        sendAll(){
            this.chat.toId = 'All';
            this.chat.data = this.chatContentAll
            this.$http.post(this.api.mbSysChatsendAll,this.chat).then(res=>{
                if(res.data.code=="200"){
                 this.$message.success(res.data.body);
              } else {
                this.$message.error(res.data.body);
              }
            })
        },
        send(type) {
            if (type == 'A'){
                this.chat.fromId = 'A'
                this.chat.toId = 'B'
                this.chat.data = this.chatContentA
            }
            if (type == 'B'){
                this.chat.fromId = 'B'
                this.chat.toId = 'A'
                this.chat.data = this.chatContentB
            }
            
            this.$http.post(this.api.mbSysChatSend,this.chat).then(res=>{
                if(res.data.code=="200"){
                 this.$message.success(res.data.body);
              } else {
                this.$message.error(res.data.body);
              }
            })
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
<style  scoped>
.chatdiv{
    width: 400px;
    height: 200px;
}
</style>