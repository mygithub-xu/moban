<!--图标弹出框控件-->
<template>
 <div class="ui-fas">
  <el-input v-model="name" @change="filterIcons" @input.native="filterIcons" suffix-icon="el-icon-search" placeholder="请输入图标名称"></el-input>
  <ul class="fas-icon-list" style="height:450px;overflow:auto;padding-top:5px">
   <li style="cursor: pointer;" class="iconSpan" v-for="(item, index) in iconList" :key="index" @click="selectedIcon(item)">
    <i class="icon iconfont" :class="['icon-' + item]" />
    <span >{{item}}</span>
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
                   'zhiyuan',
                   'shouye',
                   'dingdan',
                   'weibiaoti-1',
                   'fenxi',
                   'tianjiadingdan',
                   'dingdanguanli',
                   'fenlei1',
                   'weibiaoti-6',
                   'bangongyongpin',
                   'bianjiliucheng',
                   'baoxiao',
                   'chuchashenqing',
                   'boshimao',
                   'caigoushenqing',
                   'chuku',
                   'chajiangongneng',
                   'dianhua',
                   'ding',
                   'chukuchaxun',
                   'dizhibu',
                   'dengpao',
                   'tiaoxiushenqing',
                   'fuwu',
                   'hongbao',
                   'chuzhi',
                   'gerenbangong',
                   'gongwenshouwen',
                   'jiabanshenqing',
                   'fujian',
                   'jujue',
                   'jiaohuan',
                   'dianzan',
                   'kongzhi',
                   'jiekuan',
                   'kaoshifuwu',
                   'lianhuan',
                   'liwu',
                   'jiangzhang',
                   'luyin',
                   'lishuguanxi',
                   'lingyongshenqing',
                   'qianbao',
                   'qingjiashenqing',
                   'laoban',
                   'mingpian',
                   'qipao',
                   'qunzu',
                   'renwuliucheng',
                   'liuchengguanli',
                   'lingdang',
                   'renzheng',
                   'shouji',
                   'tongji',
                   'shequ',
                   'shouye1',
                   'touxiang',
                   'tianjiahaoyou',
                   'wode',
                   'xinfeng',
                   'rili1',
                   'hangzhengguanli',
                   'woderenwu',
                   'yidongbaobiao',
                   'xinjianzu',
                   'xinwenzixun',
                   'yingjian',
                   'zhinan',
                   'xiangji',
                   'xinjian',
                   'xunzhang',
                   'yaoqingxin',
                   'yunlishi',
                   'yunshangchuan',
                   'dianxiangengduo',
                   'biaojirenwu',
                   'diqiu',
                   'erweima',
                   'fenlei2',
                   'jinzhi',
                   'jinggao1',
                   'guanbi',
                   'qizi',
                   'renwu',
                   'kuaizhuanggengduo',
                   'fuzhi',
                   'pao',
                   'shuaxin',
                   'shexiangji',
                   'xiezilou',
                   'zuihou',
                   'zuishang',
                   'zuiqian',
                   'zuixiabeifen',
                   'zuo',
                   'yuehoujifen',
                   'caijian',
                   'daxue',
                   'mubiao',
                   'nanren',
                   'nvren',
                   'xuexiao',
                   'shujuzhexiantu',
                   'biaoqian',
                   'cangku',
                   'dengdaizhong',
                   'dengdai',
                   'huangguan',
                   'kongbaiwendang',
                   'gonggao',
                   'gonggaoban',
                   'pengyouquan',
                   'qitawenjianbeifen',
                   'shanxingtu',
                   'Excel',
                   'EXEwenjian',
                   'qitawenjian',
                   'PDF',
                   'VIPbiaozhi',
                   'shousuo',
                   'PPT',
                   'remen',
                   'Word',
                   'shijian',
                   'lanya',
                   'wifi',
                   'shoucangliebiao',
                   'loudou',
                   'jinqianguanli',
                   'kabao',
                   'lizhi',
                   'xiangmu',
                   'qiche',
                   'yidong',
                   'pintu',
                   'qiehuan',
                   'renyuanliebiao',
                   'maisui',
                   'quanxianguanli',
                   'youhuiquan',
                   'youhuiquanbao',
                   'yingyong',
                   'zuanshi',
                   'zhibiao',
                   'yongcheshenqing',
                   'chehui',
                   'gongwen',
                   'jiaobiao',
                   'kabaoji',
                   'dangan',
                   'tixingjiaobiao',
                   'lanmu',
                   'gongwenjiaohuan',
                   'guhua',
                   'fagongwen',
                   'shougongwen',
                   'HOTbiaoqian',
                   'tuangou',
                   'gerenzhongxin',
                   'gengduo',
                   'shezhi',
                   'fasongtongzhi',
                   'zhanghaoshezhi',
                   'yaoqing',
                 
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
.iconSpan{
    width:60px;/*要显示文字的宽度*/
    height: 22px;
    overflow:hidden; /*超出的部分隐藏起来。*/ 
    white-space:nowrap;/*不显示的地方用省略号...代替*/
    text-overflow:ellipsis;/* 支持 IE */
}


</style>
