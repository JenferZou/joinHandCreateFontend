<template>
              <div>
                <i-row class="search">
                  <i-col :span="12">
                    <i-row>
                      <i-col :span="16">
                        <el-input @keyup.enter.native="search" size="small" v-model="title" placeholder="请输入标题" clearable prefix-icon="el-icon-search">
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
                      width="55">
                  </el-table-column>
                  <el-table-column
                      prop="name"
                      label="项目名称"
                      width="120">
                  </el-table-column>
                  <el-table-column
                      prop="mentor"
                      label="指导老师"
                      width="120">
                  </el-table-column>

                  <el-table-column
                      prop="startTime"
                      label="开始时间"
                      width="120">
                  </el-table-column>

                  <el-table-column
                      prop="expectedCompetition"
                      label="作品预期要参加的比赛"
                      show-overflow-tooltip
                  >
                  </el-table-column>

                  <el-table-column
                      prop="needMajor"
                      label="学生专业需求"
                      show-overflow-tooltip
                  >
                  </el-table-column>

                  <el-table-column
                      prop="content"
                      label="内容"
                      show-overflow-tooltip>
                  </el-table-column>
                  <el-table-column
                      fixed="right"
                      label="项目组成员"
                      width="100">
                    <template v-slot="scope">
                      <el-button type="text" size="small" @click="searchStudent(scope.row)">查看</el-button>
                    </template>
                  </el-table-column>
                </el-table>

                <el-dialog title="小组成员" :visible.sync="menmberVisible" style="size: 100px">
                  <el-table :data="students"   style="width: 100%"
                            max-height="550">
                    <el-table-column  fixed property="projectName" label="项目名称" ></el-table-column>
                    <el-table-column property="sName" label="学生姓名" ></el-table-column>
                    <el-table-column property="sno" label="学生学号"></el-table-column>
                    <el-table-column property="sMajor" label="学生专业"></el-table-column>
                    <el-table-column
                      label="操作"
                      width="100"
                    >
                      <template v-slot="scope">
                        <el-button type="text" size="small" @click="emailStudent(scope.row)">发送通知</el-button>
                      </template>
                    </el-table-column>

                  </el-table>
                </el-dialog>


                <i-row >

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
  name: "myPublishProject",
  data() {
    return {
      multiDeleteVisible1:false,
      multipleSelectionFlag: false,
      multiDeleteVisible: false,
      menmberVisible:false,
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
      students:[],
      idParams:[],
      dcontest:'',
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
          this.project = data.content
        }
      }).catch(() => {
        console.log('出错啦！！！！')
      })
    },

    searchStudent(data) {
      this.$http({
        url: this.$http.adornUrl('/teacher/searchAccessStudent'),
        method: 'get',
        params: this.$http.adornParams(data),
        headers: {
          'UserToken':window.sessionStorage.getItem('Token'),
          'Content-Type': 'application/json',
          'charset': 'utf-8'
        }
      }).then(({data}) => {
        this.students=data
        console.log(this.students)
      }).catch(() => {
        console.log('出错啦！！！！')
      })
      this.menmberVisible=true

    },


    emailStudent(){

    },
    handleSelectionChange(val) {
      // console.log(val);
      this.multipleSelection = val;
      this.multipleSelectionFlag = this.multipleSelection.length !== 0;
    },


    getAllInformation() {
      let params = {
        page: this.currentPage,
        limit: this.pageSize
      }
      this.$http({
        url: this.$http.adornUrl('/teacher/getMypublicPro'),
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
    },
    goWhere(name){
      this.$router.push({name})
    },

    editMes(name){
      // this.$router.push({
      //   path: '/StudentMessageForm'
      // })
      this.$router.push({name})
    },
    ediRes(name){
      this.$router.push({name})
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





.layout{
  border: 1px solid #d7dde4;
  background: #f5f7f9;
}
.layout-logo{
  width: 100px;
  height: 30px;
  border-radius: 3px;
  background: #5b6270;
  float: left;
  position: relative;
  top: 15px;
  left: 20px;
}
.layout-nav{
  width: 420px;
  margin: 0 auto;
}
.layout-assistant{
  width: 500px;
  margin: 0 auto;
  height: inherit;

}
.layout-breadcrumb{
  padding: 10px 15px 0;
}
.layout-content{
  min-height: 600px;
  margin: 15px;
  overflow: hidden;
  background: #fff;
  border-radius: 4px;
}
.layout-content-main{
  padding: 10px;
  background-color: #f5f7f9;
}
.layout-copy{
  text-align: center;
  padding: 10px 0 20px;
  color: #9ea7b4;
}



</style>
