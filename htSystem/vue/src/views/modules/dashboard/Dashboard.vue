<template>
    <div class="app-container">
        <!--区域一---查询区域-->
        <div class="container-query">
            
            <el-form ref="fond-form" label-width="120px">
                <div class="query-area">
                    <el-form-item label="动支单号" >
                        <el-input v-model="requestData.condition.code" clearable></el-input>
                    </el-form-item>
                    <el-form-item label="动支标题">
                        <el-input v-model="requestData.condition.title" clearable></el-input>
                    </el-form-item>
                    <el-form-item label="状态" >
                        <el-input v-model="requestData.condition.title" clearable></el-input>
                    </el-form-item>
                    <el-form-item label="申请人" >
                        <el-input v-model="requestData.condition.apply_user_name" clearable></el-input>
                    </el-form-item>
                    <el-form-item label="申请日期" style="width: 50%">
                        <div style="display:flex; max-width: 500px;">
                            <el-date-picker
                                v-model="requestData.condition.apply_date_from"
                                type="date"
                                placeholder="选择日期">
                            </el-date-picker>
                            <span style="margin:0 5px">至</span>
                            <el-date-picker
                            
                                v-model="requestData.condition.apply_date_to"
                                type="date"
                                placeholder="选择日期">
                            </el-date-picker>
                        </div>
                    </el-form-item>
                    <div class="query-button" style="width:100%;padding-left:30px">
                        <el-button type="primary" @click="query">搜索</el-button>
                        <el-button type="warning" @click="resetForm">重置</el-button>
                        <el-button type="primary" @click="mergerHandle" :disabled="disableMerge">合并提交OA</el-button>
                    </div>
                </div>
            </el-form>
        </div>

        <!-- 区域二---数据展示区域 -->
        <div class="container-table ">
            
            <div class="common-table-style table">
                <el-table  :data="tableData" border  @selection-change="handleSelectionChange">
                <el-table-column type="selection"  width="55"  :selectable="checkboxInit"> </el-table-column>
                <el-table-column  type="index"  width="50" align="center"></el-table-column>

                <el-table-column prop="code" label="动支单号"  header-align="center">
                    <template slot-scope="scope">
                    <el-link type="primary" @click="goEdit(scope.row)">{{ scope.row.code}}</el-link>
                    </template>
                </el-table-column>

                <el-table-column prop="title" label="动支标题"  header-align="center"> </el-table-column>

                <el-table-column prop="status_desc" label="状态" align="center" max-width="160"></el-table-column>

                <el-table-column  prop="proj_attr_desc" label="动支金额" align="right" header-align="center"> </el-table-column>

                <el-table-column  prop="org_name"  label="申请机构"  header-align="center"></el-table-column>

                <el-table-column  prop="dept_name"  label="申请部门" header-align="center"></el-table-column>

                <el-table-column prop="apply_user_name"  label="申请人" header-align="center"></el-table-column>

                <el-table-column prop="apply_date" label="申请日期" header-align="center">
                    <template slot-scope="scope">
                        {{ scope.row.apply_date | getLongDate}}
                    </template>
                </el-table-column>
                </el-table>
            </div>
            <div class="pagination">
                <pagination :page-list="requestData.pageCondition" @pagesearch="handlePage"></pagination>
            </div>
        </div>
    </div>
</template>
<script>

export default {
    data() {
        return {
            requestData: {
                condition:{
                    code:"",
                    title:"",
                    status:"",
                    apply_user_name:"",
                    apply_date_from:"",
                    apply_date_from:""
                },
                pageCondition: {
                    pageNo: 1,
                    pageSize: 10,
                    totalRows: 0
                }

            },
            tableData:[],
            multipleSelection: []
        }
    },
    computed:{
        disableMerge(){
            
            return this.multipleSelection.length==0
        }
    },
    created(){
        this.query()
    },
    methods:{
        handlePage(pageNo, pageSize) {
            this.requestData.pageCondition.pageNo = pageNo
            this.requestData.pageCondition.pageSize = pageSize
            this.query()
        },
        //查询表格数据
        query(){
            this.$http.post("/apply/daily/query", this.requestData).then(res => {
                this.tableData = res.resultList
                this.requestData.pageCondition.totalRows = res.totalRows
            })
        },
        //重置搜索
        resetForm(){
            this.requestData={
                condition:{
                    code:"",
                    title:"",
                    status:"",
                    apply_user_name:"",
                    apply_date_from:"",
                    apply_date_from:""
                },
                pageCondition: {
                    pageNo: 1,
                    pageSize: 10,
                    totalRows: 0
                }

            },
            this.query()
        },
        //合并与提交
        mergerHandle(){
            this.$refs['mergerEdit'].openMerger(this.multipleSelection)
        },
        //打开详情页
        goEdit(row){
            this.$refs['edit'].openPre(row)
        },
        handleSelectionChange(val){
            this.multipleSelection = val
        },
        checkboxInit(row,index){
            if(row.status == '1'){
                return 0
            }
            return 1
        }
    }
}
</script>
<style scoped>
</style>