<template>
    <div>
        <ul :style="{left:left+'px',top:top+'px'}" class="contextmenu">
	    <!-- <li v-if="isShowReflash"><el-button type="text" @click.stop="popClick('reflash')" size="mini">刷新</el-button></li> -->
	    <li><el-button type="text" @click="popClick('closeAll')" size="mini">关闭所有</el-button></li>
	    <li v-if="!isShowLeft"><el-button type="text" @click="popClick('closeLeft')"  size="mini">关闭左边</el-button></li>
	    <li v-if="!isShowRight"><el-button type="text" @click="popClick('closeRight')"  size="mini">关闭右边</el-button></li>
	    <li><el-button type="text" @click="popClick('closeOther')" size="mini">关闭其他</el-button></li>
	</ul>
    </div>
</template>
<script>
    export default {
        name:"customPopBox",
        data(){
            return {

            }
        },
        props:{
            left:{
                type:Number,
                default:50
            },
            top:{
                type:Number,
                default:50
            },
            isShowLeft:{
                type:Boolean,
                default:false
            },
            isShowRight:{
                type:Boolean,
                default:false
            },
            isShowReflash:{
                type:Boolean,
                default:false        
            }
        },
        methods:{
            popClick(type){
                console.log(type)
                this.$emit("popClick",type);
            }
        }
    }
</script>
<style scoped>

    .contextmenu {
 	width: 100px;
    margin: 0;
    border: 1px solid #ccc;
    background: #fff;
    z-index: 3000;
    position: absolute;
    list-style-type: none;
    padding: 5px 0;
    border-radius: 4px;
    font-size: 14px;
    color: #333;
    box-shadow: 2px 2px 3px 0 rgba(0, 0, 0, 0.2);
}
.contextmenu li {
    margin: 0;
    padding: 0px 22px;

}
.contextmenu li:hover {
    background: #f2f2f2;
    cursor: pointer;
}
.contextmenu li button{
   color: #2c3e50;
}
</style>