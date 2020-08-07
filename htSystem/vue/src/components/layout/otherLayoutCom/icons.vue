<!--图标弹出框控件-->
<template>
 <div class="ui-fas">
  <el-input v-model="name" @change="filterIcons" @input.native="filterIcons" suffix-icon="el-icon-search" placeholder="请输入图标名称"></el-input>
  <ul class="fas-icon-list" style="height:450px;overflow:auto;padding-top:5px">
   <li style="cursor: pointer;" v-for="(item, index) in iconList" :key="index" @click="selectedIcon(item)">
    <i class="fas" :class="['icon-' + item]" />
    <span>{{item}}</span>
   </li>
  </ul>
 </div>
</template>
<script>
export default {
 name: 'vicons',
 data () {
  return {
   name: '',
   iconList: [],
   fontawesome:[
                   '83',
                   '84',
                   'e923',
                   'e924',
                   'e925',
                   'e926',
                   'e927',
                   'e928',
                   'e929',
                   'e92a',
                   'e92b',
                   'e92c',
                   'e92d',
                   'e92e',
                   'e92f',
                   'e930',
                   'e931',
                   'e932',
                   'e933',
                   'e934',
                   'e935',
                   'e936',
                   'e937',
                   'e938',
                   'e939',
                   'e93a',
                   'e93b',
                   'e93c',
                   'e93d',
                   'e93e',
                   'e93f',
                   'e940',
                   'e941',
                   'e942',
                   'e943',
                   'e944',
                   'e945',
                   'e946',
                   'e947',
                   'e948',
                   'e949',
                   'e94b',
                   'e94c',
                   'e94d',
                   'e94e',
                   'e94f',
                   'e950',
                   'e951',
                   'e952',
                   'e953',
                   'e954',
                   'e955',
                   'e956',
                   'e957',
                   'e958',
                   'e959',
                   'e95a',
                   'e95b',
                   'e95c',
                   'e95d',
                   'e95e',
                   'e95f',
                   'e960',
                   'e961',
                   'e962',
                   'e963',
                   'e964',
                   'e965',
                   'e966',
                   'e967',
                   'e968',
                   'e969',
                   'e96a',
                   'e96b',
                   'e96c',
                   'e96d',
                   'e96e',
                   'e96f',
                   'e970',
                   'e971',
                   'e972',
                   'e973',
                   'e974',
                   'e975',
                   'e976',
                   'e977',
                   'e978',
                   'e979',
                   'e97a',
                   'e97b',
                   'e97c',
                   'e97d',
                   'e97e',
                   'e97f',
                   'e980',
                   'e981',
                   'e982',
                   'e983',
                   'e984',
                   'e985',
                   'e986',
                   'e987',
                   'e988',
                   'e989',
                   'e98a',
                   'e98b',
                   'e98c',
                   'e98d',
                   'e98e',
                   'e98f',
                   'e990',
                   'e991',
                   'e992',
                   'e993',
                   'e994',
                   'e995',
                   'e996',
                   'e997',
                   'e998',
                   'e999',
                   'e99a',
                   'e99b',
                   'e900',
                   'e901',
                   'e902',
                   'process',
                   'configure',
                   'use',
                   'application',
                   'preview',
                   'edit',
                   'table',
                   'test',
                   'manage',
                   'dealt',
                   'close',
                   'relation',
                   'secret',
                   'technical',
                   'node',
                   'Permissions',
                   'device',
                   'development',
                   'approval',
                   'diagram',
                   'e917',
                   'message',
                   'authorizatioicon',
                   'gender',
                   'upload',
                   'download',
                   'operate',
                   'e91e',
                   'account',
                   'custom',
                ]
  }
 },
 methods: {
  filterIcons () {
   if (this.name) {
    this.iconList = this.iconList.filter(item => item.includes(this.name))
   } else {
    this.iconList = this.fontawesome;
   }
  },
  selectedIcon (name) {
   this.$emit('selected', name)
    // console.log(name)
  },
  reset () {
   this.name = ''
   this.iconList = this.fontawesome
  }
 },
 created () {
     this.filterIcons()
 }
}
</script>
<style lang="scss" scoped>
.ui-fas{
    width: 100%;
    height: 100%;
    background:white;
    ul{
        width: 100%;
        background:white;
        li{
            float: left;
            list-style: none;
            width:50%;
            i{
                font-size: 20px;
            }
        }
    }
}
</style>
