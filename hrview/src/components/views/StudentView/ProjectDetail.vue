<template>

  <div class="layout">
    <i-menu mode="horizontal" theme="dark" active-name="1"  on-select="ed">
      <!--      <div class="layout-logo"></div>-->
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


                <div class="card" >
                  <div style="display: flex;">
                    <!--                  文字部分-->
                    <div style="flex: 1">
                      <div style="font-size: 30px;font-weight: bolder">{{project.name}}</div>
                      <div style="font-size:16px;margin-top: 10px;color:#777575;font-weight: bold">预期参加的比赛: {{project.expectedCompetition}}</div>
                      <div style="font-size:16px;margin-top: 10px;color:#777575;font-weight: bold">需要专业: {{project.needMajor}}</div>
                      <div style="font-size:16px;margin-top: 20px;color:#000000;font-weight: bold">项目详情</div>
                      <div class="content" style="font-size:17px;margin-top: 5px" v-html="project.content"></div>


                      <div style="margin-top: 20px;color: gray;font-size:17px;" >项目导师:{{project.mentor}}</div>


                    </div>
                    <!--                    按钮部分-->
                    <div style="width: 130px;display:flex;padding-left: 40px;align-items: flex-end;">
                      <el-button type="primary" plain size="small" @click="delieverResume(project.id)">投递简历</el-button>
                    </div>
                  </div>
                </div>


              </div>



            </div>
          </div>
        </i-col>
      </i-row>
    </div>
    <div class="layout-copy" style="text-align: center;background-color: white">
      2024 &copy; AtXBaiYa
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
      project:{},
      title: '',
      currentPage: 1,
      pageSize: 5,
      pageNum: 10,
      contest:{
        name:'',
        startTime:'',
        content:'',
      },
      idParams:[],
      dcontest:'',
    }
  },
  methods: {logout(){
      this.$router.push('/')
    },
    change(page) {
      this.currentPage=page;
      if(this.title!=='')
        this.search()
      else
        this.getAllInformation()
    },
    delieverResume(id) {
      let params = {
        id:id
      }
      this.$http({
        url: this.$http.adornUrl('/delieverResume/addDelieverResume'),
        method: 'post',
        data:this.$http.adornData(params),
        headers: {
          'Content-Type': 'application/json',
          'charset': 'utf-8'
        }
      }).then(({data}) => {
        if (data.errorCode==200) {
          this.$message.success(data.data)
        }else{
          this.$message.error(data.message)
        }
      }).catch(() => {
        console.log('出错啦！！！！')
      })
    },

    reset(data){
      Object.keys(data).forEach(key=>(data[key]=''))
      return data
    },

    search() {
      let params = {
        pageNo: this.currentPage,
        pageSize: this.pageSize,
        name:this.title
      }
      this.$http({
        url: this.$http.adornUrl('/project/getProjectByNameLike'),
        method: 'get',
        params: this.$http.adornParams(params),
      }).then(({data}) => {
        if (data) {
          this.pageNum = data.pages
          this.currentPage = data.current
          this.project = data.data
          this.pageSize = data.size
        }
      }).catch(() => {
        console.log('出错啦！！！！')
      })
    },

    handleSelectionChange(val) {
      // console.log(val);
      this.multipleSelection = val;
      this.multipleSelectionFlag = this.multipleSelection.length !== 0;
    },


    getAllInformation() {
      let params = {
        id : this.$route.params.id
      }
      this.$http({
        url: this.$http.adornUrl('/project/getProjectById'),
        method: 'get',
        params: this.$http.adornParams(params),
      }).then(({data}) => {
        if (data.errorCode==200) {
          this.project = data.data
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

<style lang="scss" scoped>
.content {
    /deep/ img {
        max-width: 40%;
        max-height: 50%;
    }
}
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

.card {
  padding: 20px;
  background-color: #fff;
  border-radius: 5px;
  box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.1);
  margin-bottom: 10px;
}


</style>
