<template>

  <div class="layout">
    <i-menu mode="horizontal" theme="dark" active-name="1"  on-select="ed">
      <div class="layout-logo"></div>
      <h5 style="color: #fff;margin-left:520px">    携手创-学生端</h5>
    </i-menu>
    <i-menu mode="horizontal" active-name="1" @on-select="editMes">
      <div class="layout-assistant">
        <i-menu-item name="StudentIndex">主页</i-menu-item>
        <i-menu-item name="StudentMessage">个人信息</i-menu-item>
        <i-menu-item name="StudentResumePreview">个人简历</i-menu-item>
        <i-menu-item name="5">消息通知</i-menu-item>
      </div>
    </i-menu>
    <div class="layout-content">
      <i-row>
        <i-col span="5">
          <i-menu active-name="1-2" width="auto" :open-names="['1']" @on-select="goWhere">
            <i-submenu name="1">
              <template slot="title">
                <i-icon type="ios-navigate"></i-icon>
                个人信息
              </template>
              <i-menu-item name="StudentMessageForm">修改个人信息</i-menu-item>
              <i-menu-item name="StudentResume">填充个人简历</i-menu-item>

            </i-submenu>
            <i-submenu name="2">
              <template slot="title">
                <i-icon type="ios-keypad"></i-icon>
                项目管理
              </template>
              <i-menu-item name="projectSearch">项目大厅</i-menu-item>
              <i-menu-item name="myProjectSearch">项目申请</i-menu-item>
            </i-submenu>
            <i-submenu name="3">
              <template slot="title">
                <i-icon type="ios-analytics"></i-icon>
                考证与实践活动信息
              </template>
              <i-menu-item name="CertificateSearch">考证俱乐部</i-menu-item>
              <i-menu-item name="ActiveSearch">实践活动查询</i-menu-item>
            </i-submenu>
          </i-menu>
        </i-col>
        <i-col span="19">
          <div class="layout-content-main">
            <div class="in-layout-content">




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
                <div style="background-color: #fff;padding: 10px;">
                  <el-tag type="warning" style="margin-right: 55px;margin-left: 240px">简历审核中</el-tag>
                  <el-tag type="success" style="margin-right: 55px">简历通过</el-tag>
                  <el-tag type="danger">简历未通过</el-tag>
                </div>
                <el-table
                    border
                    ref="multipleTable"
                    :data="project"
                    tooltip-effect="dark"
                    style="width: 100%"
                    :cell-style="tableRowClassName">
                  <el-table-column
                      width="55">
                  </el-table-column>
                  <el-table-column
                      prop="projectName"
                      label="项目名称"
                      width="120">
                  </el-table-column>
                  <el-table-column
                      prop="mentor"
                      label="指导老师"
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
                      prop="content"
                      label="内容"
                      show-overflow-tooltip>
                  </el-table-column>

                  <el-table-column
                      prop="mark"
                      label="状态"
                      width="120"
                      :formatter="switchnum">
                  </el-table-column>
                </el-table>

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





            </div></div>
        </i-col>
      </i-row>
    </div>
    <div class="layout-copy">
      2011-2016 &copy; TalkingData
    </div>
  </div>




</template>

<script>

export default {
  name: "projectSearch",
  data() {
    return {
      multiDeleteVisible1:false,
      multipleSelectionFlag: false,
      multiDeleteVisible: false,
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
        url: this.$http.adornUrl('/student/searchDeliever'),
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




    switchnum(row){
      if(row.mark=== 0){
        return '审核中'
      }else if(row.mark === 1){
        return '简历通过'
      }else if(row.mark === -1) {
        return  '简历未通过'
      }
    },


    getAllInformation() {
      let params = {
        page: this.currentPage,
        limit: this.pageSize
      }
      this.$http({
        url: this.$http.adornUrl('/student/Deliever'),
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

    tableRowClassName({row}){
      if(row.mark==1){
        return {
          background:'#f0f9eb'
        }
      }
      else if(row.mark==0){
        return {
          background: '#fdf6ec'
        }
      }
      else{
        return {
          background: '#fef0f0'
        }
      }

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

.el-table .warning-row {
  background: #e6a23c;
}

.el-table .success-row {
  background: #67c23a;
}

.el-table .failure-row{
background: #f56c6c;
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
