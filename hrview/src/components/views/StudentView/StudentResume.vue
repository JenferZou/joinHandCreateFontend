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
    <i-menu mode="horizontal" active-name="1">
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
                导航一
              </template>
              <i-menu-item name="1-1">选项 1</i-menu-item>
              <i-menu-item name="1-2">选项 2</i-menu-item>
              <i-menu-item name="1-3">选项 3</i-menu-item>
            </i-submenu>
            <i-submenu name="2">
              <template slot="title">
                <i-icon type="ios-keypad"></i-icon>
                导航二
              </template>
              <i-menu-item name="2-1">选项 1</i-menu-item>
              <i-menu-item name="2-2">选项 2</i-menu-item>
            </i-submenu>
            <i-submenu name="3">
              <template slot="title">
                <i-icon type="ios-analytics"></i-icon>
                导航三
              </template>
              <i-menu-item name="3-1">选项 1</i-menu-item>
              <i-menu-item name="3-2">选项 2</i-menu-item>
            </i-submenu>
          </i-menu>
        </i-col>
        <i-col span="19">
          <div class="layout-content-main">
            <div class="in-layout-content">
<!--简历头部-->
              <div class="resumewrapp">
                <div class="page-title">
                  <h5>我的在线简历</h5>
                  <button class="preview">预览&下载</button>
                </div>
                <ResumeEdit ref="ResumeEdit"></ResumeEdit>

                <!--                简历内容-->
                <div class="resumecontent">
                  <li class="presonBase">
                    <img data-v-b8fe8cbc="" src="https://zcplan.oss-cn-shenzhen.aliyuncs.com/man.png" alt="" class="avatar">
                    <div class="baseMes">
                      <h5  class="name" style="font-size: 20px;color: #2a303c">
                        {{ studentInfo.sName }}
                        <span class="student"
                              style="color: #dcb93c;
                              border: 1px solid #fed305;
                             background: -webkit-linear-gradient(left, #fcfff0, #fcfec4);
                             background: linear-gradient(90deg, #fcfff0, #fcfec4);margin-left: 10px">学生</span>
                      </h5>
                    <div  class="personal-mes">{{ studentInfo.gender }}<span style="margin: 0 14px;color: #e6e6e6">|</span>
                      {{ studentInfo.sDepartment }}<span style="margin: 0 14px;color: #e6e6e6">|</span>{{studentInfo.sMajor }}
                      <span style="margin: 0 14px;color: #e6e6e6">|</span> {{studentInfo.className}}</div>
                      <div  class="contact-mes">
                        <i-icon type="ios-telephone"></i-icon> +8613043491174 <i-icon type="android-chat"></i-icon>
                        微信：{{ resume.wechatId }}</div>
                    </div>


                  </li>

                  <li class="advantage">
                    <div  class="project-title">
                      <div  class="left">
                      <span  class="line">
                      </span>
                        <h5  class="title">个人优势</h5>
                      <span  class="desc">(必填)</span>
                      </div>
                      <div class="right">
                        <button class="preview">编辑</button>
                      </div>

                    </div>
                    <div  class="advantage-con" v-if="resume.personalAdvantage"><p  class="edit-show-content" style="max-width: 718px;">
                      {{ resume.personalAdvantage }}
                    </p>
                    </div>
                  </li>


                  <li>
                    <div  class="project-title">
                      <div  class="left">
                      <span  class="line">
                      </span>
                        <h5  class="title">申请原因</h5>
                        <span  class="desc">(必填)</span>
                      </div>

                      <div class="right">
                        <button class="preview">编辑</button>
                      </div>

                    </div>
                    <div  class="advantage-con" v-if="resume.expectedPosition"><p  class="edit-show-content" style="max-width: 718px;">
                      {{ resume.expectedPosition }}
                    </p>
                    </div>
                  </li>



                  <li>
                    <div  class="project-title">
                      <div  class="left">
                      <span  class="line">
                      </span>
                        <h5  class="title">项目经历</h5>
                        <span  class="desc">(必填)</span>
                      </div>

                      <div class="right">
                        <button class="preview">编辑</button>
                      </div>
                    </div>
                    <div  class="advantage-con" v-if="resume.projectExperience"><p  class="edit-show-content" style="max-width: 718px;">
                      {{ resume.projectExperience }}
                    </p>
                    </div>
                  </li>



                  <li>
                    <div  class="project-title">
                      <div  class="left">
                      <span  class="line">
                      </span>
                        <h5  class="title">实践经历</h5>
                        <span  class="desc">(必填)</span>
                      </div>

                      <div class="right">
                        <button class="preview">编辑</button>
                      </div>
                    </div>
                    <div  class="advantage-con" v-if="resume.educationExperience"><p  class="edit-show-content" style="max-width: 718px;">
                      {{ resume.educationExperience }}
                    </p>
                    </div>
                  </li>




                  <li>
                    <div  class="project-title">
                      <div  class="left">
                      <span  class="line">
                      </span>
                        <h5  class="title">资格证书</h5>
                        <span  class="desc">(必填)</span>
                      </div>

                      <div class="right">
                        <button class="preview">编辑</button>
                      </div>
                    </div>
                    <div  class="advantage-con" v-if="resume.educationExperience"><p  class="edit-show-content" style="max-width: 718px;">
                      {{ resume.educationExperience }}
                    </p>
                    </div>
                  </li>


                  <li>
                    <div  class="project-title">
                      <div  class="left">
                      <span  class="line">
                      </span>
                        <h5  class="title">获奖经历</h5>
                        <span  class="desc">(必填)</span>
                      </div>

                      <div class="right">
                        <button class="preview">编辑</button>
                      </div>
                    </div>
                    <div  class="advantage-con" v-if="resume.awardExperience"><p  class="edit-show-content" style="max-width: 718px;">
                      {{ resume.awardExperience }}
                    </p>
                    </div>
                  </li>


                </div>
              </div>
            </div>
          </div>

        </i-col>
      </i-row>
    </div>
    <div class="layout-copy">
      2011-2016 &copy; TalkingData
    </div>
  </div>
</template>
<script>
import ResumeEdit from "@/components/views/StudentView/ResumeEdit";

export default {
  name: "StudentResume",
  components:{ResumeEdit},
  data() {
    return {
      data: 0,
      resume:{
        resumeId:'',
        expectedPosition:'....',
        expectedCity:'',
        expectedSalary:'',
        wechatId:'',
        politicsStatus:'',
        personalAdvantage:'...',
        educationExperience:'',
        internshipExperience:'',
        projectExperience:'',
        awardExperience:''
      },
      studentInfo:{
        sName: '张三',
        gender: '男',
        sno: '123123',
        sMajor: '软件工程',
        sPhone: '11011100',
        sDepartment:'计算机与智能教育学院',
        className:'软件2班'
      }

    };
  },
  methods:{
    edit(){
      this.$refs.ResumeEdit.init()
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
  background-color: #fff;
}
.layout-copy{
  text-align: center;
  padding: 10px 0 20px;
  color: #9ea7b4;
}





li{
  list-style: none;
}

.resumewrapp .page-title{
  width: 100%;
  height: 50px;
  border-bottom: 1px solid #e6e6e6;
  box-sizing: border-box;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 40px;}


.preview {
  color: #fff;
  font-size: 12px;
  width: 80px;
  height: 30px;
  line-height: 30px;
  text-align: center;
  background: #02B28B;
  border-radius: 4px;
  cursor: pointer;
  border: 0 none;
}


.presonBase{
  width: 100%;
  min-height: 170px;
  display: flex;
  align-items: center;
}

.baseMes{
  display: flex;
  flex-direction: column;
  margin-left: 40px;

}

.personal-mes{
  margin-top: 20px;
  display: flex;
  align-items: center;
  color: #6a6e74;
  font-size: 14px;
}

.contact-mes{
  font-size: 14px;
  color: #6a6e74;
  margin-top: 14px;
}

.project-title{
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 56px;
}

.title{
  font-size: 16px;
  color: #2a303c;
  font-weight: 700;
  margin-left: 12px;
}
.left{
  display: flex;
  align-items: center;
}
.project-title .line{
  width: 4px;
  height: 17px;
  background: #02B28B;
}




.advantage-con{
  width: 100%;
  padding: 12px 16px;
  box-sizing: border-box;
  color: #6a6e74;
  text-align: justify;
  display: flex;
  position: relative;
  margin-bottom: 20px;
}



</style>




