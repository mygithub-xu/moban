<template>
    <div class="app-container2">
        <div class="dashboardContainer">
            <div class="shouyeHeard">
                <el-col :xs="12" :sm="12" :md="8" :lg="8" :xl="8" >
                    <div class="shouyeShowDiv" @click="userCountClick">
                        <div class="shouyeShowDivBody icon iconfont icon-caiwuguanli-dianji"></div>
                        <div class="shouyeShowDivBody">

                            <div class="shouyeShowDivBodyNum">
                                <div class="userCountTitle">用户数量</div>
                                </div>
                            <div class="shouyeShowDivBodyNum">
                                <div class="userCount">{{count.userCount}}</div>
                            </div>

                        </div>
                    </div>
                </el-col>
                <el-col :xs="12" :sm="12" :md="8" :lg="8" :xl="8" >
                    <div class="shouyeShowDiv" @click="dynamicCountClick">
                        <div class="shouyeShowDivBody icon iconfont icon-yuangongguanli-moren"></div>
                        <div class="shouyeShowDivBody">

                            <div class="shouyeShowDivBodyNum">
                                <div class="userCountTitle">动态数量</div>
                                </div>
                            <div class="shouyeShowDivBodyNum">
                                <div class="userCount">{{count.dynamicCount}}</div>
                            </div>

                        </div>

                    </div>
                </el-col>
                <el-col :xs="12" :sm="12" :md="8" :lg="8" :xl="8" >
                    <div class="shouyeShowDiv" @click="articleCountClick">
                        <div class="shouyeShowDivBody icon iconfont icon-dangkouziliao-moren"></div>
                        <div class="shouyeShowDivBody">

                            <div class="shouyeShowDivBodyNum">
                                <div class="userCountTitle">资讯数量</div>
                                </div>
                            <div class="shouyeShowDivBodyNum">
                                <div class="userCount">{{count.articleCount}}</div>
                            </div>

                        </div>

                    </div>
                </el-col>
                <!-- <el-col :xs="12" :sm="12" :md="6" :lg="6" :xl="6" >
                    <div class="shouyeShowDiv" @click="productAlertClick">
                        <div class="shouyeShowDivBody icon iconfont icon-shishishuju-moren"></div>
                        <div class="shouyeShowDivBody">

                            <div class="shouyeShowDivBodyNum">
                                <div class="userCountTitle">缺货提醒</div>
                                </div>
                            <div class="shouyeShowDivBodyNum">
                                <div class="userCount">{{count.productAlertCount}}</div>
                            </div>

                        </div>

                    </div>
                </el-col> -->
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


    </div>
</template>

<script>
// import bus from "@utils/bus";
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
                }
            }
        },

        mounted(){
            // this.getdata();
        },
        methods:{
            aaaaa(){
                console.log("dianjile")
            },
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
                this.echatData={
                    orderPeopleList:{
                        orderList:[],
                        peopleList:[],
                        dateList:[]
                    },
                    productTypeList:{
                        proName:[],
                        proData:[]
                    }
                }

                this.$http.post(this.api.mbSysUserGetCount).then(res => {
                if (res.data.code == "200") {
                    this.count.userCount=res.data.body.userCount;
                    this.count.dynamicCount=res.data.body.dynamicCount;
                    this.count.articleCount=res.data.body.articleCount;

                    //图数据
                    var aa=res.data.body.userDynamic;
                    aa.forEach(element => {
                        element.commonInt2=!element.commonInt2?0:element.commonInt2;
                        element.commonInt=!element.commonInt?0:element.commonInt;
                        this.echatData.orderPeopleList.orderList.push(element.commonInt2);
                        this.echatData.orderPeopleList.peopleList.push(element.commonInt);
                        this.echatData.orderPeopleList.dateList.push(element.commonKeyString);
                    });

                    // var bb=res.data.body.productTypeList;
                    // bb.forEach(element => {
                    //     this.echatData.productTypeList.proName.push(element.commonKeyString);
                    //     var proData={value: element.commonInt, name: element.commonKeyString};
                    //     this.echatData.productTypeList.proData.push(proData);
                    // });


                }
                this.getEchataaa();
                this.getEchatbbb();

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
    .shouyeUser{
        width: 30%;
        height: 200px;
        background-color: #CCCC66;
        /* box-shadow: 0 0 10px #909399; */
        float: left;
    }
    .shouyeHeard{
        width: 100%;
        margin-top: 50px;
        float: left;
    }
    .shouyeShowDiv{
        background-color: #ffffff;
        margin-right: 20px;
        margin-left: 20px;
        height: 100px;
        border-radius: 5px;
        box-shadow: 0 0 10px #909399;
    }
    .dashboardContainer{
        height: 100%;
        width: 100%;
    }
    .shouyeShowDivBody{
        width: 50%;
        height: 100%;
        float: left;
    }
    .shouyeShowDivBodyNum{
        height: 50%;
        width: 100%;
        float: left;
        padding-top: 10px;
    }
    .shouyeShowDiv .iconfont{
        font-size: 80px;
        margin-top: 10px;
        color: #00a6ac;
    }
    .userCountTitle{
        font-size: 18px;
        color: #6b473c;
    }
    .userCount{
        font-size: 16px;
        color: #281f1d;
    }
    .shouyeUserTouxiang{
        width: 50%;
        height: 100%;
        float: left;
    }
    .echatsDiv{
        height: 500px;
        width: 100%;
        float: left;
    }
</style>