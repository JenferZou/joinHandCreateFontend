

<template>
  <div class="layout">
    <i-menu mode="horizontal" theme="dark" active-name="1"  on-select="ed">
      <div class="layout-logo"></div>
      <div class="layout-nav" >
        <i-menu-item name="1">
          <i-icon type="ios-navigate"></i-icon>
          导航一
        </i-menu-item>
        <i-menu-item name="2">
          <i-icon type="ios-keypad"></i-icon>
          导航二
        </i-menu-item>
        <i-menu-item name="3">
          <i-icon type="ios-analytics"></i-icon>
          导航三
        </i-menu-item>
        <i-menu-item name="4">
          <i-icon type="ios-paper"></i-icon>
          导航四
        </i-menu-item>
      </div>
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



              <i-row  class="headcontent" :gutter="16">

                <i-col span="14">
                  <div class="mes" style="background-color: #fff">

                    <i-col style="height: 295px;background-color: #fff;text-align: center; border-bottom: 1px dashed #c0c0c0">
                      <i-col span="24" >

                        <li class="presonBase" style="height: 250px;padding-top: 10px">
                          <img data-v-b8fe8cbc="" src="https://zcplan.oss-cn-shenzhen.aliyuncs.com/man.png" alt="" class="avatar" style="height: 80px">
                          <div class="baseMes">
                            <h5  class="name" style="font-size: 20px;color: #2a303c">
                              {{ studentInfo.sName }}
                              <span class="student"
                                    style="color: #dcb93c;
                              border: 1px solid #fed305;
                             background: -webkit-linear-gradient(left, #fcfff0, #fcfec4);
                             background: linear-gradient(90deg, #fcfff0, #fcfec4);margin-left: 10px">学生</span>
                            </h5>
                            <div  class="personal-mes"> {{ studentInfo.gender }}<span style="margin: 0 14px;color: #e6e6e6">|</span>
                              {{ studentInfo.sDepartment }}<span style="margin: 0 14px;color: #e6e6e6">|</span> {{ studentInfo.sMajor }}
                              <span style="margin: 0 14px;color: #e6e6e6">|</span> {{ studentInfo.className }}</div>
                            <div  class="contact-mes">
                              <i-icon type="ios-telephone"></i-icon>+86{{ studentInfo.sPhone }}</div>
                          </div>
                        </li>
                      </i-col>
                    </i-col>


                  </div>
                </i-col>

                <i-col span="10">
                  <div class="mes" style="background-color: #fff;height: 295px">
                    <i-col style="height: 295px;background-color: #fff;text-align: center; border-bottom: 1px dashed #c0c0c0">

                    <i-col style="height: 45px">
                      <i-col span="24" class="headmainmes" style="height: 45px;font-size: 14px"><i-icon type="email-unread"></i-icon> 消息通知</i-col>
                    </i-col>



                    <el-empty v-if="active1 == undefined ||active1 == null || active1.length <= 0 " description="暂无信息" style="size: 10px"></el-empty>

                    <i-col   v-for="item in active1" :key="item.id" style="height: 50px;background-color: #fff;text-align: center;border-bottom: 1px dashed #c0c0c0;">
                      <i-col span="8">{{ item.title }}</i-col>
                      <i-col span="8">{{ item.content }}</i-col>
                      <i-col span="8">{{item.activeTime}}</i-col>

                    </i-col>
                    </i-col>
                  </div>


                </i-col>
              </i-row>




              <i-row  class="centercontent">
                <i-col style="height: 295px;background-color: #fff;text-align: center; border-bottom: 1px dashed #c0c0c0">

                <i-col span="24">
                  <div class="mes" >
                    <i-col style="height: 45px">
                      <i-col span="24" class="headmainmes" style="height: 45px;font-size: 14px"><i-icon type="email-unread">
                      </i-icon>   最新项目发布通知</i-col>
                    </i-col>

                    <i-col style="height: 50px;background-color: #fff;text-align: center;border-bottom: 1px dashed #c0c0c0;">
                      <i-col span="6">项目名称</i-col>
                      <i-col span="6">专业需求</i-col>
                      <i-col span="6">项目指导老师</i-col>
                      <i-col span="6">报名时间</i-col>
                    </i-col>

                    <el-empty  v-if="project == undefined ||project == null || project.length <= 0" description="暂无信息"  style="size: 10px"></el-empty>

                    <i-col  v-for="item in project" :key="item.id" style="height: 50px;background-color: #fff;text-align: center;border-bottom: 1px dashed #c0c0c0;">
                      <i-col span="6">{{ item.name }}</i-col>
                      <i-col span="6">{{item.needMajor}}</i-col>
                      <i-col span="6">{{ item.mentor }}</i-col>
                      <i-col span="6">{{item.startTime}}</i-col>
                    </i-col>


                  </div>
                </i-col>
                </i-col>
              </i-row>


              <i-row  class="footcontent" :gutter="16" >

                <i-col span="12">
                  <div class="mes" style="background-color: #fff;min-height: 295px">
                    <i-col style="height: 45px">
                      <i-col span="24" class="headmainmes" style="height: 45px;font-size: 14px"><i-icon type="email-unread">

                      </i-icon>    最新考试报名信息</i-col>

                    </i-col>


                    <i-col style="height: 50px;background-color: #fff;text-align: center; border-bottom: 1px dashed #c0c0c0">
                      <i-col span="8" >考试类别</i-col>
                      <i-col span="8">报名时间</i-col>
                      <i-col span="8">截止时间</i-col>
                    </i-col>


                    <el-empty  v-if="contest1 == undefined ||contest1 == null || contest1.length <= 0" description="暂无信息" style="size: 10px"></el-empty>


                    <i-col   v-for="item in contest1" :key="item.id" style="height: 50px;background-color: #fff;text-align: center;border-bottom: 1px dashed #c0c0c0;">
                      <i-col span="8">{{ item.title }}</i-col>
                      <i-col span="8">{{ item.startTime }}</i-col>
                      <i-col span="8">{{item.endTime}}</i-col>

                    </i-col>



                  </div>
                </i-col>


                <i-col span="12">
                  <div class="mes" style="background-color: #fff;min-height: 295px">
                    <i-col style="height: 45px">
                      <i-col span="24" class="headmainmes" style="height: 45px;font-size: 14px"><i-icon type="email-unread"></i-icon>    最新实践活动信息</i-col>
                    </i-col>
                    <i-col style="height: 50px;background-color: #fff;text-align: center;border-bottom: 1px dashed #c0c0c0">
                      <i-col span="8">活动名称</i-col>
                      <i-col span="8">活动报名时间</i-col>
                      <i-col span="8">活动时长</i-col>
                    </i-col>


                    <el-empty v-if="active1 == undefined ||active1 == null || active1.length <= 0 " description="暂无信息" ></el-empty>

                    <i-col   v-for="item in active1" :key="item.id" style="height: 50px;background-color: #fff;text-align: center;border-bottom: 1px dashed #c0c0c0;">
                      <i-col span="8">{{ item.title }}</i-col>
                      <i-col span="8">{{ item.activeTime }}</i-col>
                      <i-col span="8">{{item.activeDuration}}</i-col>

                    </i-col>
                  </div>
                </i-col>

              </i-row>






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
  data(){
    return{
      contest:{
        id:'',
        title:'',
        remark:'',
        startTime:'',
        endTime:'',
        content:''
      },
      project:[],
      company:[],
      contest1:[],
      active1:[],
      active:{
        id:'',
        title:'',
        type:'',
        activeTime:'',
        activeDuration:'',
        content:'',
        points:'',
        remark:''
      },
      studentInfo:{
        sName: '张三',
        gender: '男',
        sno: '123123',
        sMajor: '软件工程',
        sPhone: '11011100',
        sDepartment:'计算机与智能教育学院',
        className:'软件2班'
      },
    }
  },
  created(){
    this.loadContest()
    this.loadActive()
    this.loadStudent()
    this.loadProject()
  },
  methods: {
    loadProject() {
      this.$http({
        url: this.$http.adornUrl('/student/project'),
        method: 'get',
        params:this.$http.adornParams(),
        headers: {
          'UserToken':window.sessionStorage.getItem('Token'),
          'Content-Type': 'application/json',
          'charset': 'utf-8'
        }
      }).then(({data}) => {
        console.log(data)
        this.project=data

      }).catch(() => {
        console.log('出错啦！！！！')
      })
    },
    loadContest() {
      this.$http({
        url: this.$http.adornUrl('/student/contest'),
        method: 'get',
        params:this.$http.adornParams(),
        headers: {
          'UserToken':window.sessionStorage.getItem('Token'),
          'Content-Type': 'application/json',
          'charset': 'utf-8'
        }
      }).then(({data}) => {
        this.contest1=data

      }).catch(() => {
        console.log('出错啦！！！！')
      })
    },
    loadActive() {

      this.$http({
        url: this.$http.adornUrl('/student/active'),
        method: 'get',
        params:this.$http.adornParams(),
        headers: {
          'UserToken':window.sessionStorage.getItem('Token'),
          'Content-Type': 'application/json',
          'charset': 'utf-8'
        }
      }).then(({data}) => {
        console.log(data)
        this.active1=data
      }).catch(() => {
        console.log('出错啦！！！！')
      })
    },
    loadStudent() {
      this.$http({
        url: this.$http.adornUrl('/student/StudentMessageForm'),
        method: 'get',
        headers: {
          'UserToken':window.sessionStorage.getItem('Token'),
          'Content-Type': 'application/json',
          'charset': 'utf-8'
        }
      }).then(({data}) => {
          this.studentInfo=data
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

  }
}
</script>


<style scoped>
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
  min-height: 400px;
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


.headcontent{
  background-color: #f5f7f9;
  line-height: 50px;
  margin-bottom: 10px;

}


.centercontent{
  margin-bottom: 10px;
  background-color: #fff;
  border-bottom: 1px dashed #c0c0c0;
  line-height: 50px;

}


/*.headmes{*/
/*  !*border-image: linear-gradient(45deg, #90cf5b, #00afee) 1;*!*/
/*  !*clip-path: inset(0 round 10px);*!*/
/*  background-color: rgb(64, 158, 255);*/
/*  font-size: 14px;*/
/*  text-align: center;*/
/*  padding: 45px;*/
/*  color: rgba(255,255,255,.7);*/
/*}*/

.headmes:hover{
  background-color: #91d5ff;
}

.footcontent{
  background-color: #f5f7f9;
  line-height: 50px;
}

.pra{
  background-color: darkgray;

}



.headmainmes{
  background-color: #e3e8ee;
  /*background-color: #5cadff;*/
  /*color: rgba(255,255,255,.7)*/
}



li{
  list-style: none;
}

.el-empty{
  padding: 0;
}
</style>
