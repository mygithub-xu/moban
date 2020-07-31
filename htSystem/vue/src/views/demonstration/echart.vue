<template>
    <div class="app-container">
        <!-- <div class="aaaa" id="echat1"></div>
        <div class="aaaa" id="echat2"></div>
        <div class="aaaa" id="echat3"></div>

        <div class="aaaa">
        <img id="barcode" style="height:100px;width:250px"/>

        <img class="qrcodeImg" :src="qrcodeImg" style="height:250px;width:250px"/>
        </div> -->

<div style="width:700px">
<el-form ref="form" :model="form" label-width="80px">
  <el-form-item label="活动名称">
    <el-input v-model="form.name"></el-input>
  </el-form-item>
  <el-form-item label="活动区域">
    <el-select v-model="form.region" placeholder="请选择活动区域">
      <el-option label="区域一" value="shanghai"></el-option>
      <el-option label="区域二" value="beijing"></el-option>
    </el-select>
  </el-form-item>
  <el-form-item label="活动时间" style="text-align: center;">
    <el-col :span="11">
      <el-date-picker type="date" placeholder="选择日期" v-model="form.date1" style="width: 100%;"></el-date-picker>
    </el-col>
    <el-col class="line" :span="2">-</el-col>
    <el-col :span="11">
      <el-time-picker placeholder="选择时间" v-model="form.date2" style="width: 100%;"></el-time-picker>
    </el-col>
  </el-form-item>
  <el-form-item label="即时配送">
    <el-switch v-model="form.delivery"></el-switch>
  </el-form-item>
  <el-form-item label="活动性质">
    <el-checkbox-group v-model="form.type">
      <el-checkbox label="美食/餐厅线上活动" name="type"></el-checkbox>
      <el-checkbox label="地推活动" name="type"></el-checkbox>
      <el-checkbox label="线下主题活动" name="type"></el-checkbox>
      <el-checkbox label="单纯品牌曝光" name="type"></el-checkbox>
    </el-checkbox-group>
  </el-form-item>
  <el-form-item label="特殊资源">
    <el-radio-group v-model="form.resource">
      <el-radio label="线上品牌商赞助"></el-radio>
      <el-radio label="线下场地免费"></el-radio>
    </el-radio-group>
  </el-form-item>
  <el-form-item label="活动形式">
    <el-input type="textarea" v-model="form.desc"></el-input>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="onSubmit">立即创建</el-button>
    <el-button>取消</el-button>
  </el-form-item>
</el-form>
</div>
    </div>
</template>
<script>
import JsBarcode from 'jsbarcode'
import jrQrcode from "jr-qrcode"
export default {
        data(){
            return{
        form: {
          name: '',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
        },

                qrcodeImg:""
            }
        },
        mounted(){
            this.getdata();
            
        },
        created(){
            this.$nextTick(()=>{
                this.getdata()
            })
        },
        methods:{
            getEchataaa(){
                let myChart = this.$echarts.init(document.getElementById("echat1"))
                myChart.setOption({
    title: {
        text: '折线图堆叠'
    },
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data: ['邮件营销', '联盟广告', '视频广告', '直接访问', '搜索引擎']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    toolbox: {
        feature: {
            saveAsImage: {}
        }
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
    },
    yAxis: {
        type: 'value'
    },
    series: [
        {
            name: '邮件营销',
            type: 'line',
            stack: '总量',
            data: [120, 132, 101, 134, 90, 230, 210]
        },
        {
            name: '联盟广告',
            type: 'line',
            stack: '总量',
            data: [220, 182, 191, 234, 290, 330, 310]
        },
        {
            name: '视频广告',
            type: 'line',
            stack: '总量',
            data: [150, 232, 201, 154, 190, 330, 410]
        },
        {
            name: '直接访问',
            type: 'line',
            stack: '总量',
            data: [320, 332, 301, 334, 390, 330, 320]
        },
        {
            name: '搜索引擎',
            type: 'line',
            stack: '总量',
            data: [820, 932, 901, 934, 1290, 1330, 1320]
        }
    ]
                })
            },
            getdata(){
                this.getEchataaa()
                this.getma();
                this.geterweima();
                // this.getEchatbbb()
                // this.getEchatccc()
            },

            getma(){

            this.$nextTick(()=>{
                JsBarcode("#barcode", "6921489013609", {
                    
                    width:1,//设置条之间的宽度
                    height:100,//高度
                    displayValue:false,//是否在条形码下方显示文字
                    fontOptions:"bold italic",//使文字加粗体或变斜体
                    font:"fantasy",//设置文本的字体
                    textAlign:"left",//设置文本的水平对齐方式
                    textPosition:"top",//设置文本的垂直位置
                    textMargin:5,//设置条形码和文本之间的间距
                    fontSize:15,//设置文本的大小
                    background:"#eee",//设置条形码的背景
                    lineColor:"#000000",//设置条和文本的颜色。
                    margin:15//设置条形码周围的空白边距
                    });
                // JsBarcode("#barcode", "6921489013609");
            })

            },


            /**
            @param: text: 要生成二维码的字符，支持中文
            @param: options: {
            padding       : 10,   // 二维码四边空白（默认为10px）
            width         : 256,  // 二维码图片宽度（默认为256px）
            height        : 256,  // 二维码图片高度（默认为256px）
            correctLevel  : QRErrorCorrectLevel.H,    // 二维码容错level（默认为高）
            reverse       : false,        // 反色二维码，二维码颜色为上层容器的背景颜色
            background    : "#ffffff",    // 二维码背景颜色（默认白色）
            foreground    : "#000000"     // 二维码颜色（默认黑色）
            }
            @return: 生成的二维码Base64 URL
            */
            geterweima(){
                let options={
                    padding       : 10,   // 二维码四边空白（默认为10px）
                    width         : 256,  // 二维码图片宽度（默认为256px）
                    height        : 256,  // 二维码图片高度（默认为256px）
                    correctLevel  : QRErrorCorrectLevel.H,    // 二维码容错level（默认为高）
                    reverse       : false,        // 反色二维码，二维码颜色为上层容器的背景颜色
                    background    : "#ffffff",    // 二维码背景颜色（默认白色）
                    foreground    : "#000000"     // 二维码颜色（默认黑色）
                }
                let text = "zzzz"
                let imgBase64 = jrQrcode.getQrBase64(text, options);  //options为配置对象，支持以下配置参数：
                this.qrcodeImg=imgBase64
            }
            
        }
}
</script>
<style scoped>
    .aaaa{
        height: 500px;
        width: 700px;
        float: left;
    }
</style>