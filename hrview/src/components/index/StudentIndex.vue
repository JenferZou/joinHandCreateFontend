

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
        <i-menu-item name="1">主页</i-menu-item>
        <i-menu-item name="StudentMessageForm">个人信息</i-menu-item>
        <i-menu-item name="3" >实践经历</i-menu-item>
        <i-menu-item name="StudentResume">个人简历</i-menu-item>
        <i-menu-item name="5">消息通知</i-menu-item>
      </div>
    </i-menu>
    <div class="layout-content">
      <i-row>
        <i-col span="5">
          <i-menu active-name="1-2" width="auto" :open-names="['1']">
            <i-submenu name="1">
              <template slot="title">
                <i-icon type="ios-navigate"></i-icon>
                个人信息
              </template>
              <i-menu-item name="1-1">选项 1</i-menu-item>
<!--              <i-menu-item name="1-2">选项 2</i-menu-item>-->
<!--              <i-menu-item name="1-3">选项 3</i-menu-item>-->
            </i-submenu>
            <i-submenu name="2">
              <template slot="title">
                <i-icon type="ios-keypad"></i-icon>
                合作单位信息
              </template>
              <i-menu-item name="2-1">选项 1</i-menu-item>
              <i-menu-item name="2-2">选项 2</i-menu-item>
            </i-submenu>
            <i-submenu name="3">
              <template slot="title">
                <i-icon type="ios-analytics"></i-icon>
                考试与实践信息
              </template>
              <i-menu-item name="3-1">选项 1</i-menu-item>
              <i-menu-item name="3-2">选项 2</i-menu-item>
            </i-submenu>
          </i-menu>
        </i-col>
        <i-col span="19">
          <div class="layout-content-main">
            <div class="in-layout-content">

<!--            <i-row type="flex" class="headcontent">-->
<!--              <i-col span="6">-->
<!--                <div class="headmes" style="height: 100px" >实践经历</div>-->
<!--              </i-col>-->
<!--              <i-col span="6">-->
<!--                <div class="headmes" style="height: 100px" @click="editMes">个人信息</div>-->
<!--              </i-col>-->
<!--              <i-col span="6">-->
<!--                <div class="headmes" style="height: 100px" @click="ediRes">个人简历</div>-->
<!--              </i-col>-->
<!--              <i-col span="6">-->
<!--                <div class="headmes" style="height: 100px">消息通知</div>-->
<!--              </i-col>-->
<!--            </i-row>-->

            <i-row  class="centercontent">
              <i-col span="24">
                <div class="mes" >
                  <i-col style="height: 45px">
                    <i-col span="24" class="headmainmes" style="height: 45px;font-size: 14px"><i-icon type="email-unread"></i-icon>   合作单位</i-col>

                  </i-col>

                  <i-col style="height: 50px;background-color: #fff;text-align: center;border-bottom: 1px dashed #c0c0c0;">
                    <i-col span="8">单位名称</i-col>
                    <i-col span="8">招聘岗位</i-col>
                    <i-col span="8">工作薪水</i-col>
                  </i-col>

                  <i-col  v-for="item in company" :key="item.id" style="height: 50px;background-color: #fff;text-align: center;border-bottom: 1px dashed #c0c0c0;">
                    <i-col span="8">{{ item.name }}</i-col>
                    <i-col span="8">{{ item.post }}</i-col>
                    <i-col span="8">{{item.salary}}元</i-col>
                  </i-col>


                </div>
              </i-col>
            </i-row>


            <i-row  class="footcontent" :gutter="16">
              <i-col span="12">
                <div class="mes" style="background-color: #fff">
                  <i-col style="height: 45px">
                    <i-col span="24" class="headmainmes" style="height: 45px;font-size: 14px"><i-icon type="email-unread"></i-icon>    考试报名信息</i-col>

                  </i-col>


                  <i-col style="height: 50px;background-color: #fff;text-align: center; border-bottom: 1px dashed #c0c0c0">
                    <i-col span="8" >考试类别</i-col>
                    <i-col span="8">考试信息</i-col>
                    <i-col span="8">考试时间</i-col>
                  </i-col>


                  <i-col   v-for="item in contest1" :key="item.id" style="height: 50px;background-color: #fff;text-align: center;border-bottom: 1px dashed #c0c0c0;">
                    <i-col span="8">{{ item.title }}</i-col>
                    <i-col span="8">{{ item.content }}</i-col>
                    <i-col span="8">{{item.startTime}}</i-col>

                  </i-col>



                </div>
              </i-col>
              <i-col span="12">
                <i-col style="height: 45px">
                  <i-col span="24" class="headmainmes" style="height: 45px;font-size: 14px"><i-icon type="email-unread"></i-icon>    实践活动信息</i-col>
                </i-col>
                <i-col style="height: 50px;background-color: #fff;text-align: center;border-bottom: 1px dashed #c0c0c0">
                  <i-col span="8">活动名称</i-col>
                  <i-col span="8">活动内容</i-col>
                  <i-col span="8">活动时间</i-col>
                </i-col>

                <i-col   v-for="item in active1" :key="item.id" style="height: 50px;background-color: #fff;text-align: center;border-bottom: 1px dashed #c0c0c0;">
                  <i-col span="8">{{ item.title }}</i-col>
                  <i-col span="8">{{ item.content }}</i-col>
                  <i-col span="8">{{item.activeTime}}</i-col>

                </i-col>


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
    }
  },
  created(){
    this.loadContest()
    this.loadActive()
    this.loadCompany()
  },
  methods: {

    loadContest() {
      this.$http({
        url: this.$http.adornUrl('/student/contest'),
        method: 'get',
        params:this.$http.adornParams()
      }).then(({data}) => {
        console.log(data)
        this.contest1=data

      }).catch(() => {
        console.log('出错啦！！！！')
      })
    },
    loadActive() {

      this.$http({
        url: this.$http.adornUrl('/student/active'),
        method: 'get',
        params:this.$http.adornParams()
      }).then(({data}) => {
        console.log(data)
        this.active1=data
      }).catch(() => {
        console.log('出错啦！！！！')
      })
    },

    loadCompany(){
      this.$http({
        url: this.$http.adornUrl('/student/company'),
        method: 'get',
        params:this.$http.adornParams()
      }).then(({data}) => {
        console.log(data)
        this.company=data
      }).catch((error) => {
        console.log(error)
        console.log('出错啦！！！！')
      })
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


.centercontent{
  height: 300px;
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


</style>