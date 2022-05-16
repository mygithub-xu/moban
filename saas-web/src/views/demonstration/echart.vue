<template>
  <div class="app-container">
    <div class="aaaa"
         id="echat1"></div>
    <div class="echat-family"
         id="echat2"></div>
    <div class="aaaa"
         id="echat3"></div>

    <!-- <div class="aaaa">
        <img id="barcode" style="height:100px;width:250px"/>

        <img class="qrcodeImg" :src="qrcodeImg" style="height:250px;width:250px"/>
        </div> -->
  </div>
</template>
<script>
// import JsBarcode from 'jsbarcode'
// import jrQrcode from "jr-qrcode"
export default {
  data () {
    return {
    }
  },
  mounted () {
    this.getdata()

  },
  methods: {
    echatFamily () {
      let option = {
        title: {
          text: 'Graph 简单示例'
        },
        tooltip: {},
        animationDurationUpdate: 1500,
        animationEasingUpdate: 'quinticInOut',
        series: [
          {
            type: 'graph',
            layout: 'none',
            symbolSize: 30,
            roam: true,
            label: {
              show: true
            },
            edgeSymbol: ['circle'],
            edgeSymbolSize: [4, 10],
            edgeLabel: {
              fontSize: 15
            },
            data: [{
              id: "11",
              name: '节点1',
              x: 500,
              y: 300
            }, {
              id: "21",
              name: '节点2',
              x: 600,
              y: 300
            }, {
              id: "31",
              name: '节点3',
              x: 700,
              y: 300
            }, {
              id: "41",
              name: '节点4',
              x: 600,
              y: 350
            }],
            links: [{
              source: '11',
              target: '31'
            }, {
              source: '31',
              target: '21'
            }, {
              source: '21',
              target: '41'
            }],
            lineStyle: {
              opacity: 0.9,
              width: 2,
              curveness: 0
            }
          }
        ]
      }
      let myChart = this.$echarts.init(document.getElementById("echat2"))
      myChart.setOption(option)
      myChart.on('click', function (params) {
        console.log(params);
      })
    },
    getEchataaa () {
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
    getdata () {
      //echart家庭关系图
      this.echatFamily()
      //echat练习
      this.getEchataaa()
      //获取条形码
      // this.getma();
      //获取二维码
      // this.geterweima();
    },
    //   //获取条形码
    //   getma(){
    //   this.$nextTick(()=>{
    //       JsBarcode("#barcode", "6921489013609", {

    //           width:1,//设置条之间的宽度
    //           height:100,//高度
    //           displayValue:false,//是否在条形码下方显示文字
    //           fontOptions:"bold italic",//使文字加粗体或变斜体
    //           font:"fantasy",//设置文本的字体
    //           textAlign:"left",//设置文本的水平对齐方式
    //           textPosition:"top",//设置文本的垂直位置
    //           textMargin:5,//设置条形码和文本之间的间距
    //           fontSize:15,//设置文本的大小
    //           background:"#eee",//设置条形码的背景
    //           lineColor:"#000000",//设置条和文本的颜色。
    //           margin:15//设置条形码周围的空白边距
    //           })
    //       // JsBarcode("#barcode", "6921489013609");
    //   })

    //   },


    //   /**
    //   @param: text: 要生成二维码的字符，支持中文
    //   @param: options: {
    //   padding       : 10,   // 二维码四边空白（默认为10px）
    //   width         : 256,  // 二维码图片宽度（默认为256px）
    //   height        : 256,  // 二维码图片高度（默认为256px）
    //   correctLevel  : QRErrorCorrectLevel.H,    // 二维码容错level（默认为高）
    //   reverse       : false,        // 反色二维码，二维码颜色为上层容器的背景颜色
    //   background    : "#ffffff",    // 二维码背景颜色（默认白色）
    //   foreground    : "#000000"     // 二维码颜色（默认黑色）
    //   }
    //   @return: 生成的二维码Base64 URL
    //   */
    //   geterweima(){
    //       let options={
    //           padding       : 10,   // 二维码四边空白（默认为10px）
    //           width         : 256,  // 二维码图片宽度（默认为256px）
    //           height        : 256,  // 二维码图片高度（默认为256px）
    //           correctLevel  : QRErrorCorrectLevel.H,    // 二维码容错level（默认为高）
    //           reverse       : false,        // 反色二维码，二维码颜色为上层容器的背景颜色
    //           background    : "#ffffff",    // 二维码背景颜色（默认白色）
    //           foreground    : "#000000"     // 二维码颜色（默认黑色）
    //       }
    //       let text = "zzzz"
    //       let imgBase64 = jrQrcode.getQrBase64(text, options);  //options为配置对象，支持以下配置参数：
    //       this.qrcodeImg=imgBase64
    //   }

  }
}
</script>
<style scoped>
.aaaa {
  height: 500px;
  width: 700px;
  float: left;
}
.echat-family {
  height: 700px;
  width: 200px;
  float: left;
}
</style>