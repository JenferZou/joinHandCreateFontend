<template>

  <div class="layout">
    <div class="layout-content">
      <i-row>
        <i-col span="19">
          <div class="layout-content-main">

            <div class="in-layout-content">
              <!--简历头部-->
              <div class="resumewrapp">
                <div class="page-title">
                  <h5>简历预览</h5>
                  <el-button type="primary" @click="returnhome" style="margin-bottom: 2px;margin-left: 150px" size="small">返回</el-button>
                </div>
                <!--                简历内容-->
                <div class="resumecontent">
                  <li class="presonBase">
                    <img data-v-b8fe8cbc="" src="https://zcplan.oss-cn-shenzhen.aliyuncs.com/man.png" alt="" class="avatar">
                    <div class="baseMes">
                      <h5  class="name" style="font-size: 20px;color: #2a303c">
                        {{ studentInfo.sname }}
                        <span class="student"
                              style="color: #dcb93c;
                              border: 1px solid #fed305;
                             background: -webkit-linear-gradient(left, #fcfff0, #fcfec4);
                             background: linear-gradient(90deg, #fcfff0, #fcfec4);margin-left: 10px">学生</span>
                      </h5>
                      <div  class="personal-mes">{{ studentInfo.gender }}<span style="margin: 0 14px;color: #e6e6e6">|</span>
                        {{ studentInfo.sdepartment }}<span style="margin: 0 14px;color: #e6e6e6">|</span>{{studentInfo.smajor }}
                        <span style="margin: 0 14px;color: #e6e6e6">|</span> {{studentInfo.className}}</div>
                      <div  class="contact-mes">
                        <i-icon type="ios-telephone"></i-icon> +86{{ studentInfo.sphone }} </div>
                    </div>
                    <div  class="edit-mes" style="color: #02B28B;display: flex;margin-left: auto;font-size: 12px; cursor: pointer">
                    </div>
                  </li>
                  <li class="advantage" v-if="resume.personalAdvantage">
                    <div  class="project-title">
                      <div  class="left">
                        <i-icon type="email" style="color: #02B28B;"></i-icon>
                        <h5  class="title">个人优势</h5>
                      </div>
                      <div  class="right">
                      </div>
                    </div>
                    <div  class="advantage-con" v-if="resume.personalAdvantage"><p  class="edit-show-content" style="max-width: 718px;">
                      {{ resume.personalAdvantage }}
                    </p> <div class="fixed-edit"><div class="edit-show-action-wrap">
                      </div></div></div>
                  </li>
                  <li>
                    <div  class="project-title">
                      <div  class="left">
                        <i-icon type="email" style="color: #02B28B" ></i-icon>
                        <h5  class="title">项目经历</h5>
                      </div>
                      <div  class="right">
                      </div>
                    </div>
                    <div  class="advantage-con" v-if="resume.projectExperience"><p  class="edit-show-content" style="max-width: 718px;">
                      {{ resume.projectExperience }}
                    </p> <div class="fixed-edit"><div class="edit-show-action-wrap">
                      </div></div></div>
                  </li>
                  <li>
                    <div  class="project-title">
                      <div  class="left">
                        <i-icon type="email" style="color: #02B28B" ></i-icon>
                        <h5  class="title">实践经历</h5>
                      </div>
                      <div  class="right">
                      </div>
                    </div>
                    <div  class="advantage-con" v-if="resume.internshipExperience"><p  class="edit-show-content" style="max-width: 718px;">
                      {{ resume.internshipExperience }}
                    </p> <div class="fixed-edit"><div class="edit-show-action-wrap">
                      </div></div></div>
                  </li>
                  <li>
                    <div  class="project-title">
                      <div  class="left">
                        <i-icon type="email" style="color: #02B28B" ></i-icon>
                        <h5  class="title">资格证书</h5>
                      </div>
                      <div  class="right">
                      </div>
                    </div>
                    <div  class="advantage-con" v-if="resume.certificate"><p  class="edit-show-content" style="max-width: 718px;">
                      {{ resume.certificate }}
                    </p> <div class="fixed-edit"><div class="edit-show-action-wrap">
                      </div></div></div>
                  </li>
                  <li>
                    <div  class="project-title">
                      <div  class="left">
                        <i-icon type="email" style="color: #02B28B" ></i-icon>
                        <h5  class="title">获奖经历</h5>
                      </div>
                      <div  class="right">
                      </div>
                    </div>
                    <div  class="advantage-con" v-if="resume.awardExperience"><p  class="edit-show-content" style="max-width: 718px;">
                      {{ resume.awardExperience }}
                    </p> <div class="fixed-edit"><div class="edit-show-action-wrap">
                      </div></div></div>
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


export default {
  name: "StudentResumePreview",
  data() {
    return {
      data: 0,
      resume: {
        resumeId: '',
        wechatId: '',
        politicsStatus: '',
        personalAdvantage: '...',
        internshipExperience: '',
        projectExperience: '',
        awardExperience: '',
        certificate: ''
      },
      studentInfo: {
      },

    };
  },
  created() {
      this.loadStudent()
    this.loadResume()
  },
  methods: {
  returnhome(){
  this.$router.push({
    name:"StudentResume",
  })
},
    loadStudent() {
      this.$http({
        url: this.$http.adornUrl('/student/selectById'),
        method: 'get',
        headers: {
          'UserToken':window.sessionStorage.getItem('Token'),
          'Content-Type': 'application/json',
          'charset': 'utf-8'
        }
      }).then(({data}) => {
        this.studentInfo = data.data
      }).catch(() => {
        console.log('出错啦！！！！')
      })
    },
    loadResume(){
      this.$http({
        url: this.$http.adornUrl('/student/StudentResume'),
        method: 'get',
        headers: {
          'UserToken':window.sessionStorage.getItem('Token'),
          'Content-Type': 'application/json',
          'charset': 'utf-8'
        }
      }).then(({data}) => {
          if(!data.data){
              return;
          }
        this.resume=data.data
      }).catch(() => {
        console.log('出错啦！！！！')
      })

    },
  }
}
</script>


<style scoped>
.layout{
  border: 1px solid #d7dde4;
  background: #f5f7f9;
}


.layout-content{
  min-height: 400px;
  margin: 20px auto;
  overflow: hidden;
  background: #fff;
  border-radius: 4px;
  max-width: 800px;

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




