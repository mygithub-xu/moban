<template>
    <div class="layoutDiv" @mousemove="walk()">

        <layoutHeader @headRightClick="headRightClick"></layoutHeader>
        <layoutBody ></layoutBody>

        <el-drawer
        :visible.sync="drawer"
        :with-header="false"
        @close="closeDrawer"
        size="320px">
               <layoutSetting ></layoutSetting>
        </el-drawer>

    </div>
</template>
<script>
import bus from "@utils/bus";
export default {
    data(){
        return {
            //抽屉状态
            drawer: false,
        }
    },
    methods:{
        headRightClick(data){
            switch(data){
                case 'openDrawer': this.openDrawer()
                break;
            }
        },
        //打开抽屉
        openDrawer(){
            this.drawer=true
        },
        closeDrawer(){
            //保存样式
        },

    walk(){
    }
    }
}
</script>
<style scoped>
    .layoutDiv{
        height: 100%;
        width: 100%;
    }
</style>
