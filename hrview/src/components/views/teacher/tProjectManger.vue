<template>
    <div>
        <i-row class="search">
            <i-col :span="12">
                <i-row>
                    <i-col :span="16">
                        <el-input @keyup.enter.native="search" size="small" v-model="title" placeholder="请输入项目名称..." clearable prefix-icon="el-icon-search">
                        </el-input>
                    </i-col>
                    <i-col :span="3">
                        <el-button type="primary" size="small" @click="search">
                            <i-icon type="search" size="small"></i-icon>
                            搜索
                        </el-button>
                    </i-col>
                </i-row>
            </i-col>
        </i-row>
        <el-table
                border
                ref="multipleTable"
                :data="project"
                tooltip-effect="dark"
                style="width: 100%"
                @selection-change="handleSelectionChange">
            <el-table-column
                    type="selection"
                    width="55">
            </el-table-column>
            <el-table-column
                    prop="projectName"
                    label="项目名称"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="sName"
                    label="学生姓名"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="sno"
                    label="学生学号"
                    width="120">
            </el-table-column>

          <el-table-column
              prop="sMajor"
              label="学生专业"
              width="120">
          </el-table-column>



          <el-table-column
              prop="content"
              label="内容"
              show-overflow-tooltip>
          </el-table-column>

          <el-table-column
              label="学生简历"
              width="120">
            <template v-slot="scope">
              <el-button type="text" size="small" @click="queryStudentResume(scope.row)">查看</el-button>
            </template>
          </el-table-column>


            <el-table-column
                    fixed="right"
                    label="操作"
                    width="100">
                <template v-slot="scope">
                    <el-button type="text" size="small" @click="agreeForm(scope.row)">同意加入申请</el-button>
                    <el-button type="text" size="small" @click="refuseStudent(scope.row)">拒绝加入申请</el-button>
                </template>
            </el-table-column>



        </el-table>
        <i-row >
            <el-dialog :visible.sync="refuseVisible" title="提示" width="30%">
                <span>确定要拒绝该学生的项目加入申请吗</span>
              <span slot="footer">
          <el-button type="primary" @click="refuseStudent" size="small">确 定</el-button>
          <el-button @click="refuseVisible = false" size="small">取 消</el-button>
        </span>
            </el-dialog>

            <el-dialog :visible.sync="agreeVisible1" title="提示" width="30%">
                <span>确定要同意该学生的项目加入申请吗</span>
                <span slot="footer">
          <el-button type="primary" @click="agreestudent" size="small">确 定</el-button>
          <el-button @click="agreeVisible1 = false" size="small">取 消</el-button>
        </span>
            </el-dialog>

            <el-pagination
                    class="page"
                    @current-change="change"
                    background
                    layout="prev, pager, next"
                    :page-count="pageNum">
            </el-pagination>
        </i-row>
    </div>
</template>

<script>

export default {
    name: "projectManger",
    data() {
        return {
            agreeVisible1:false,
            multipleSelectionFlag: false,
          refuseVisible: false,
            multipleSelection: [],
            project: [],
            title: '',
            currentPage: 1,
            pageSize: 8,
            pageNum: 1,
            contest:{
                name:'',
                startTime:'',
                content:'',
            },
            idParams:[],
            dcontest:'',
          delievers:''
        }
    },
    methods: {
        change(page) {
            this.currentPage=page;
            if(this.title!=='')
                this.search()
            else
                this.getAllInformation()
        },
        refuseForm(data) {
            this.$nextTick(() => {
                // 弹框打开时初始化表单
              this.delievers=data
              this.refuseVisible=true

            })
        },
      agreeForm(data){
        this.$nextTick(() => {
          // 弹框打开时初始化表单
          this.delievers=data
          this.agreeVisible1=true

        })
      },


      refuseStudent(){

      },
      agreestudent(){

      },



        deleteContest(contest) {
            this.multiDeleteVisible1=true
            this.dcontest=contest
        },
        reset(data){
            Object.keys(data).forEach(key=>(data[key]=''))
            return data
        },

        search() {
            let params = {
                page: this.currentPage,
                limit: this.pageSize,
                title:this.title
            }
            this.$http({
                url: this.$http.adornUrl('/teacher/searchProject'),
                method: 'get',
                params: this.$http.adornParams(params),
                headers: {
                    'UserToken':window.sessionStorage.getItem('Token'),
                }
            }).then(({data}) => {
                if (data) {
                    this.pageNum = data.totalPages
                    this.information = data.content
                }
            }).catch(() => {
                console.log('出错啦！！！！')
            })
        },
        popDelete(){
            this.multiDeleteVisible=true
        },
        handleSelectionChange(val) {
            // console.log(val);
            this.multipleSelection = val;
            this.multipleSelectionFlag = this.multipleSelection.length !== 0;
        },
        deleteOneProject(project,projectData){
            for(let i=0; i<projectData.length; i++){
                if(project.id===projectData[i].id){
                    projectData.splice(i,1)
                }
            }
            return projectData;
        },
        multiDelete1(){
            this.$http({
                url: this.$http.adornUrl('/teacher/deleteProject'),
                method: 'post',
                data:this.$http.adornData(this.dcontest),
                headers: {
                    'UserToken':window.sessionStorage.getItem('Token'),
                    'Content-Type': 'application/json',
                    'charset': 'utf-8'
                }
            }).then(({data}) => {
                if (data&&data.status===200) {
                    this.$message.success(data.msg)
                    this.project=this.deleteOneProject(this.dcontest,this.project)
                }else{
                    this.$message.error(data.msg)
                }
            }).catch(() => {
                console.log('出错啦！！！！')
            })
            this.multiDeleteVisible1=false
        },
        multiDelete() {
            this.multipleSelection.forEach((contest)=>{
                this.idParams.push(contest.id)
            })
            this.$http({
                url: this.$http.adornUrl('/teacher/deleteProjectBatch'),
                method: 'post',
                data:this.idParams,//this.$http.adornData(this.idParams),
                headers: {
                    'UserToken':window.sessionStorage.getItem('Token'),
                    'Content-Type': 'application/json',
                    'charset': 'utf-8'
                }
            }).then(({data}) => {
                if (data&&data.status===200) {
                    this.$message.success(data.msg)
                    this.multiDeleteVisible = false
                    this.multipleSelection.forEach((contest)=>{
                        this.information=this.deleteOneProject(contest,this.information)
                    })
                }else{
                    this.$message.error(data.msg)
                }
            }).catch(() => {
                console.log('出错啦！！！！')
            })
        },
        getAllInformation() {
            let params = {
                page: this.currentPage,
                limit: this.pageSize
            }
            this.$http({
                url: this.$http.adornUrl('/teacher/studentDeliever'),
                method: 'get',
                params: this.$http.adornParams(params),
                headers: {
                    'UserToken':window.sessionStorage.getItem('Token'),
                }
            }).then(({data}) => {
                if (data) {
                    this.pageNum = data.totalPages
                    this.project = data.content
                }
            }).catch(() => {
                console.log('出错啦！！！！')
            })
        }
    },
    created() {
        this.getAllInformation();
    }
}
</script>

<style scoped>
.search {
    padding-top: 10px;
    padding-bottom: 10px;
}

.page {
    text-align: center;
}
</style>
