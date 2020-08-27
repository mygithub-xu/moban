<template>
    <div class="app-container">
        <div class="dashboardStatistic">

                <div class="dashboardStatisticItem" @click="userCountClick">
                    <div class="icon iconfont icon-caiwuguanli-dianji"></div>
                    <div class="statisticBody">
                        <div class="statisticItemName">用户数量</div>
                        <div class="statisticItemNum">{{count.userCount}}</div>
                    </div>
                </div>

                <div class="dashboardStatisticItem" @click="userCountClick">
                    <div class="icon iconfont icon-caiwuguanli-dianji"></div>
                    <div class="statisticBody">
                        <div class="statisticItemName">动态数量</div>
                        <div class="statisticItemNum">{{count.dynamicCount}}</div>
                    </div>
                </div>

                <div class="dashboardStatisticItem" @click="userCountClick">
                    <div class="icon iconfont icon-caiwuguanli-dianji"></div>
                    <div class="statisticBody">
                        <div class="statisticItemName">资讯数量</div>
                        <div class="statisticItemNum">{{count.articleCount}}</div>
                    </div>
                </div>
<!-- <el-button @click="getdata">点击</el-button> -->

            </div>

            
            <div class="echatsDiv">
                <el-col :xs="24" :sm="24" :md="24" :lg="16" :xl="16" >
                    
                    <div style="height:500px" id="aaa"></div>
                </el-col>
                <el-col :xs="24" :sm="24" :md="24" :lg="8" :xl="8" >
                    <div style="height:500px" id="bbb"></div>
                </el-col>
            </div>

        </div>
</template>

<script>
    export default {
        name:"Dashboard",
        data(){
            return{
                count:{
                    userCount:0,
                    dynamicCount:0,
                    articleCount:0,
                },
                avatar:"",
                echatData:{
                    orderPeopleList:{
                        orderList:[],
                        peopleList:[],
                        dateList:[]
                    },
                    productTypeList:{
                        proName:[],
                        proData:[]
                    }
                },

            }
        },
        created(){
        },
        mounted(){
            
        },
        methods:{
            getEchatbbb(){
                let myChart = this.$echarts.init(document.getElementById("bbb"))
                myChart.setOption({
                        title: {
                            text: '',
                            left: 'center'
                        },
                        tooltip: {
                            trigger: 'item',
                            
                            formatter: '{a} <br/>{b} : {c} ({d}%)'
                        },
                        legend: {
                            // orient: 'vertical',
                            // top: 'middle',
                            bottom: 10,
                            left: 'center',
                            data: this.echatData.productTypeList.proName
                        },
                        series: [
                            {
                                type: 'pie',
                                radius: '65%',
                                center: ['50%', '50%'],
                                data:this.echatData.productTypeList.proData,
                                emphasis: {
                                    itemStyle: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                    }
                                }
                            }
                        ]
                })
            },
            getEchataaa(){
                let myChart = this.$echarts.init(document.getElementById("aaa"))
                myChart.setOption({
                        title: {
                            text: '用户-动态统计'
                        },
                        tooltip: {
                            trigger: 'axis'
                        },
                        legend: {
                            data: ['用户', '动态']
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
                            data: this.echatData.orderPeopleList.dateList
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [
                            {
                                name: '用户',
                                type: 'line',
                                data: this.echatData.orderPeopleList.peopleList
                            },
                            {
                                name: '动态',
                                type: 'line',
                                data: this.echatData.orderPeopleList.orderList
                            }
                        ]
                })
            },

            getdata(){

                this.$http.post(this.api.applyQueryApply,{
                    pageCondition: {
                        pageNo: 0,
                        pageSize: 0
                    }
                }).then(res => {

                });
            },
            userCountClick(){
                
                // this.$router.push({path:'/views/app/appUser/appUserList'});
            },
            dynamicCountClick(){
                // this.$router.push({path:'/views/app/appProduct/appProductList'});
            },
            articleCountClick(){
                // this.$router.push({path:'/views/app/appOrder/appOrderList'});
            },
            productAlertClick(){
                
            }
        },

    }
</script>
<style scoped>

.dashboardStatistic{
    width: 100%;
    height: 100%;
}
.dashboardStatisticItem{
    width: 26%;
    margin-left: 6%;
    float: left;
    box-shadow: 0 0 10px #909399;
    height: 100px;
    border-radius: 5px;
    background-color: #ffffff;
}
.dashboardStatisticItem .iconfont{
        width: 40%;
        height: 100px;
        /* background-color: #CCCC66; */
        float: left;
        text-align: center;
        font-size: 90px;
        color: #00a6ac;
        line-height: 100px;
}
.dashboardDivBody{
    float: left;
    width: 60%;
    height: 100px;
}
.statisticBody div{
    font-size: 25px;
    width: 60%;
    height: 50px;
    float: left;
    text-align: center;
    line-height: 50px;
}
.dashboardStatisticItemName{
    font-size: 30px;
}
.dashboardStatisticItemNum{
    font-size: 20px;
}
</style>