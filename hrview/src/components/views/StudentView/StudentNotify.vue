<template>

  <div class="layout">
    <i-menu mode="horizontal" theme="dark" active-name="1"  on-select="ed">
      <h5 style="color: #fff;margin-left:50px;font-weight: bolder;font-size: 20px">    携手创-学生端</h5>
    </i-menu>
    <i-menu mode="horizontal" active-name="1" @on-select="editMes">
      <div class="layout-assistant">
        <i-menu-item name="StudentIndex">主页</i-menu-item>
        <i-menu-item name="StudentMessageForm">个人信息</i-menu-item>
        <i-menu-item name="StudentResumePreview">个人简历</i-menu-item>
        <i-menu-item name="notify">消息通知</i-menu-item>
        <div style="padding-left: 900px"><el-button size="small" @click="logout()">退出登录</el-button></div>
      </div>
    </i-menu>
    <div class="layout-content">
      <i-row>
        <i-col span="5">
          <i-menu active-name="1-2" width="auto" :open-names="['1','2']" @on-select="goWhere">
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
          </i-menu>
        </i-col>
        <i-col span="19">
          <div class="layout-content-main">
            <div class="in-layout-content">




              <div>
                <el-table
                    border
                    ref="multipleTable"
                    :data="project"
                    tooltip-effect="dark"
                    style="width: 100%"
                    >
                    <el-table-column
                            prop="createTime"
                            label="消息接收时间"
                            width="180">
                    </el-table-column>
                  <el-table-column
                      prop="name"
                      label="项目名称"
                      width="180">
                  </el-table-column>

                  <el-table-column
                      prop="mentor"
                      label="消息发送者"
                      width="180">
                  </el-table-column>
                  <el-table-column
                      prop="sname"
                      label="学生姓名"
                      width="180">
                  </el-table-column>
                  <el-table-column
                      prop="content"
                      label="消息内容"
                      show-overflow-tooltip
                   style="text-overflow: ellipsis; overflow: hidden; white-space: nowrap;">
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
  name: "StudentNotify",
  data() {
    return {
      notify: [],
      currentPage: 1,
      pageSize: 10,
      pageNum: 10,
        project: [],
      // contest:{
      //   name:'',
      //   startTime:'',
      //   content:'',
      // },
      // idParams:[],
      // dcontest:'',
    }
  },
  methods: {
    logout(){
      this.$router.push('/')
    },
    change(page) {
      this.currentPage=page;
      if(this.title!=='')
        this.search()
      else
        this.getAllInformation()
    },

    getAllInformation() {
      let params = {
        pageNo: this.currentPage,
        pageSize: this.pageSize
      }
      this.$http({
        url: this.$http.adornUrl('/message/getMessageBySno'),
        method: 'get',
        params: this.$http.adornParams(params),
      }).then(({data}) => {
        if (data) {
          this.pageNum = data.pages
          this.currentPage = data.current
          this.project = data.data
            this.project.forEach(item => {
                // 检查pid是否为0
                if (item.tno == 0) {
                    // 设置mentor为管理员
                    this.$set(item, 'mentor', '管理员');
                }});
          this.pageSize = data.size
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
