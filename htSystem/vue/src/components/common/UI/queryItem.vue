<template>

        <div class="query-item" :style="{'width':width}">
            <template v-if="!isButton">
                <div class="query-title" :style="{'width':labelWidth}">{{label}}</div>
            </template>
            <div class="query-input"><slot></slot></div>
        </div>

</template>
<script>
export default {
    name:"queryItem",
    props:{
        label:String,
        labelWidth:{
            type:String,
            default:'150px'
        },
        width:{
            type:String,
            default:'33%'
        },
        isButton:{
            type:Boolean,
            default:false
        }

    },
    data(){
        return {

        }
    },
}
</script>
<style scoped>
.query-item{
    display: flex;
    flex-direction: row;
    min-width: 300px;
    margin-bottom: 10px;
}
.query-title{
    line-height: 33px;
    text-align: right;
    font-size: 14px;
}
.query-input{
    flex: 1;
}
.query-input >.is_data{
    min-width: 200px;
    max-width: 500px!important;
    width: 90%;
    display: flex;
}
.query-input >div:first-child{
    min-width: 100px;
    max-width: 300px;
    width: 90%;
    display: flex;
}

</style>