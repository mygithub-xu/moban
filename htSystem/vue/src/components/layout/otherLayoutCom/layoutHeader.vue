<template>
    <div class="layout-header" id="layout-header" :style="{'background-color':getLayoutParam.headerBGColor}">

        <layoutHeaderLeft ></layoutHeaderLeft>
        <layoutHeaderCenter :headerCenterWidth="headerCenterWidth"></layoutHeaderCenter>
        <layoutHeaderRight @headRightClick="headRightClick"></layoutHeaderRight>
    </div>
</template>
<script>
import { mapGetters } from 'vuex';
export default {
    name: 'layoutHeader',
    data(){
        return {
            headerCenterWidth:200
        }
    },
    computed: {
        ...mapGetters([
            'getLayoutParam',
            'getLayoutType'
        ])
    },
    mounted(){
        window.onresize = () =>{
            this.getData()
        }
    },
    watch:{
        getLayoutType(newData, oldData){
            this.getData()
        }
    },
    created(){
        this.getData()
    },
    methods:{
        getData(){
            if(this.getLayoutType=='2'){
                this.getCanUserWidth();
            }
        },
        //type2时样式数据
        getCanUserWidth(){
            this.$nextTick(()=>{
                //动态获取宽度
                let leftwidth=200;
                var rightwidth= document.getElementById('layout-header-right').clientWidth; //右边宽度    headWidth左边宽度   
                var allwidth= document.getElementById('layout-header').clientWidth;
                this.headerCenterWidth =allwidth - rightwidth - leftwidth;
            })

        },
        headRightClick(data){
            this.$emit("headRightClick",data)
        },

    }
}
</script>
<style scoped>
    .layout-header{
        height: 50px;
        /* border-bottom: solid 1px #e6e6e6; */
    }

    .layout-header-left{
        height: 50px;
        float: left;
    }
    .layout-header-left-icon i{
        font-size: 25px;
        line-height: 50px;
        margin-left: 15px;
        cursor: pointer;
    }
    .menu_container2{
        float: left;
        height: 50px;
        text-align: center;
        overflow: hidden;
    }
</style>