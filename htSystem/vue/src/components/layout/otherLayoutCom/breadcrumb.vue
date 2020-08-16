<template>
    <div class="breadcrumbDivCom">
        <el-breadcrumb  separator="/">
        
            <el-breadcrumb-item v-for="(item, index) in levelList" :key="index">
                <a v-if="item.path" @click="handleLink(item)">{{item.name}}</a>
                <span v-else>{{item.name}}</span>
            </el-breadcrumb-item>
        </el-breadcrumb>
    </div>
</template>
<script>
export default {
    name: 'breadcrumb',
    data(){
        return {

        }
    },
    props:{
        levelList:{
            type:Array,
            require: true
        },
        
    }
    
}
</script>
<style scoped>
    .breadcrumbDivCom{
        height: 48px;
        width: 100%;
        float: left;
    }
    .breadcrumbDivCom .el-breadcrumb{
        line-height: 48px;
        margin-left: 15px;
    }

</style>