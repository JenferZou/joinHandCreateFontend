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
                style="width: 100%">

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
              width="100">
            <template v-slot="scope">
              <el-button type="text" size="small"  style="margin: 20px" @click="queryStudentResume(scope.row)">查看</el-button>
            </template>
          </el-table-column>


            <el-table-column
                    fixed="right"
                    label="操作"
                    width="120">
                <template v-slot="scope">
                    <el-button type="text" size="small" style="margin: 5px" @click="agreeForm(scope.row)">同意加入申请</el-button>
                    <el-button type="text" size="small" style="color:red; margin: 5px" @click="refuseForm(scope.row)">拒绝加入申请</el-button>
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
          delievers:'',
          delievermap:[],
          studentInfo:'',
          resumeInfo:'',
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
        this.$http({
          url: this.$http.adornUrl('/teacher/refuseDeliever'),
          method: 'post',
          data:this.$http.adornData(this.delievers),
          headers: {
            'UserToken':window.sessionStorage.getItem('Token'),
            'Content-Type': 'application/json',
            'charset': 'utf-8'
          }
        }).then(({data}) => {
          if (data&&data.status===200) {
            this.$message.success(data.msg)
          }else{
            this.$message.error(data.msg)
          }
        }).catch(() => {
          console.log('出错啦！！！！')
        })
        this.refuseVisible=false
        this.getAllInformation()
      },
      agreestudent(){
        this.$http({
          url: this.$http.adornUrl('/teacher/agreeDeliever'),
          method: 'post',
          data:this.$http.adornData(this.delievers),
          headers: {
            'UserToken':window.sessionStorage.getItem('Token'),
            'Content-Type': 'application/json',
            'charset': 'utf-8'
          }
        }).then(({data}) => {
          if (data&&data.status===200) {
            this.$message.success(data.msg)
          }else{
            this.$message.error(data.msg)
          }
        }).catch(() => {
          console.log('出错啦！！！！')
        })
        this.agreeVisible1=false
        this.getAllInformation()
      },


      queryStudentResume(row){
        this.$http({
          url: this.$http.adornUrl('/teacher/lookStudentResume'),
          method: 'post',
          data:this.$http.adornData(row),
          headers: {
            'UserToken':window.sessionStorage.getItem('Token'),
            'Content-Type': 'application/json',
            'charset': 'utf-8'
          }
        }).then(({data}) => {
          console.log(data.studentInfo)
          console.log(data.resumeInfo)

          this.$router.push({
            name:'StudentDelieverResume',
            params:{
              studentInfo:data.studentInfo,
              resumeInfo:data.resumeInfo
            }
          })
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
