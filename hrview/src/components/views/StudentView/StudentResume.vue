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
<!--            <i-submenu name="3">-->
<!--              <template slot="title">-->
<!--                <i-icon type="ios-analytics"></i-icon>-->
<!--                考证与实践活动信息-->
<!--              </template>-->
<!--              <i-menu-item name="CertificateSearch">考证俱乐部</i-menu-item>-->
<!--              <i-menu-item name="ActiveSearch">实践活动查询</i-menu-item>-->
<!--            </i-submenu>-->
          </i-menu>
        </i-col>
        <i-col span="19">
          <div class="layout-content-main">
            <div class="in-layout-content">
<!--简历头部-->
              <div class="resumewrapp">
                <div class="page-title">
                  <h5>我的在线简历</h5>
                  <button class="preview" @click="preview">预览</button>
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
                        <i-icon type="ios-telephone"></i-icon> +86{{ studentInfo.sphone }}

                      </div>


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
                        <span class="editspan" @click="editadvantage">编辑</span>
                      </div>
                      <el-dialog :visible.sync="advantagedialog" width="70%">
                        <el-form :model="resume" >
                          <el-form-item label="个人优势" prop="strength">
                            <el-input type="textarea" :rows="10" v-model="resume.personalAdvantage"></el-input>
                          </el-form-item>
                        </el-form>
                        <div slot="footer">
                          <el-button @click="cancleadvantage">取 消</el-button>
                          <el-button type="primary" @click="submitadvantage">确 定</el-button>
                        </div>
                      </el-dialog>
                    </div>
                    <div  class="advantage-con" v-if="resume?.personalAdvantage"><p  class="edit-show-content" style="max-width: 718px;">
                      {{ resume?.personalAdvantage }}
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
                        <span class="editspan" @click="editprojectExp">编辑</span>
                      </div>
                      <el-dialog :visible.sync="projectExperiencedialog" width="70%">
                        <el-form :model="resume" >
                          <el-form-item label="项目经历" prop="strength">
                            <el-input type="textarea" :rows="10" v-model="resume.projectExperience"></el-input>
                          </el-form-item>
                        </el-form>
                        <div slot="footer">
                          <el-button @click="cancleprojectExp">取 消</el-button>
                          <el-button type="primary" @click="submitprojectExp">确 定</el-button>
                        </div>
                      </el-dialog>

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
                        <span class="editspan" @click="editinnternshipExpe">编辑</span>
                      </div>
                      <el-dialog :visible.sync="internshipExperiencedialog" width="70%">
                        <el-form :model="resume" >
                          <el-form-item label="实践经历" prop="strength">
                            <el-input type="textarea" :rows="10" v-model="resume.internshipExperience"></el-input>
                          </el-form-item>
                        </el-form>
                        <div slot="footer">
                          <el-button @click="cancleinnternshipExpe">取 消</el-button>
                          <el-button type="primary" @click="submitinnternshipExpe">确 定</el-button>
                        </div>
                      </el-dialog>

                    </div>
                    <div  class="advantage-con" v-if="resume.internshipExperience"><p  class="edit-show-content" style="max-width: 718px;">
                      {{ resume.internshipExperience }}
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
                        <span class="editspan" @click="editcertificate">编辑</span>
                      </div>

                      <el-dialog :visible.sync="certificatedialog" width="70%">
                        <el-form :model="resume" >
                          <el-form-item label="资格证书" prop="strength">
                            <el-input type="textarea" :rows="4" v-model="resume.certificate"></el-input>
                          </el-form-item>
                        </el-form>
                        <div slot="footer">
                          <el-button @click="canclecertificate">取 消</el-button>
                          <el-button type="primary" @click="submitcertificate">确 定</el-button>
                        </div>
                      </el-dialog>

                    </div>
                    <div  class="advantage-con" v-if="resume.certificate"><p  class="edit-show-content" style="max-width: 718px;">
                      {{ resume.certificate }}
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
                        <span class="editspan" @click="editaward">编辑</span>
                      </div>

                      <el-dialog :visible.sync="awardExperiencedialog" width="70%">
                        <el-form :model="resume" >
                          <el-form-item label="获奖经历" prop="strength">
                            <el-input type="textarea" :rows="10" v-model="resume.awardExperience"></el-input>
                          </el-form-item>
                        </el-form>
                        <div slot="footer">
                          <el-button @click="cancleaward">取 消</el-button>
                          <el-button type="primary" @click="submitaward">确 定</el-button>
                        </div>
                      </el-dialog>

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
    <div class="layout-copy" style="text-align: center;background-color: white">
      2024 &copy; AtXBaiYa
    </div>
  </div>
</template>
<script>

export default {
  name: "StudentResume",
  data() {
    return {
      data: 0,
      resume:{
        resumeId:'',
        wechatId:'',
        politicsStatus:'',
        personalAdvantage:'',
        internshipExperience:'',
        projectExperience:'',
        awardExperience:'',
        certificate:''
      },
      studentInfo:{
        sname: '',
        gender: '',
        sno: '',
        smajor: '',
        sphone: '',
        sdepartment:'',
        className:''
      },

      advantagedialog:false,
      projectExperiencedialog:false,
      internshipExperiencedialog:false,
      certificatedialog:false,
      awardExperiencedialog:false,

    };
  },

  created(){
      this.loadStudent()
      this.loadResume()

  },
  methods:{
    logout(){
      this.$router.push('/')
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
        console.log(data)
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

    editadvantage(){
      this.advantagedialog=true
    },

    editprojectExp(){
      this.projectExperiencedialog=true
    },
    editinnternshipExpe(){
      this.internshipExperiencedialog=true
    },
    editcertificate(){
      this.certificatedialog=true
    },
    editaward(){
      this.awardExperiencedialog=true
    },

    submitadvantage(){
      this.$http({
        url: this.$http.adornUrl('/student/edit'),
        method: 'post',
        data:this.$http.adornData(this.resume),
        headers: {
          'UserToken':window.sessionStorage.getItem('Token'),
          'Content-Type': 'application/json',
          'charset': 'utf-8'
        }
      }).then(({data}) => {
        if (data.errorCode==200) {
          console.log(data)
          this.advantagedialog=false
          this.$message.success("修改成功")
        }
      }).catch(() => {
        console.log('出错啦！！！！')
      })

    },


    submitprojectExp(){
      this.$http({
        url: this.$http.adornUrl('/student/edit'),
        method: 'post',
        data:this.$http.adornData(this.resume),
        headers: {
          'UserToken':window.sessionStorage.getItem('Token'),
          'Content-Type': 'application/json',
          'charset': 'utf-8'
        }
      }).then(({data}) => {
        if (data.errorCode==200) {
          this.$message.success("修改成功")
          this.projectExperiencedialog=false
        }
      }).catch(() => {
        console.log('出错啦！！！！')
      })

    },

    submitinnternshipExpe(){
      this.$http({
        url: this.$http.adornUrl('/student/edit'),
        method: 'post',
        data:this.$http.adornData(this.resume),
        headers: {
          'UserToken':window.sessionStorage.getItem('Token'),
          'Content-Type': 'application/json',
          'charset': 'utf-8'
        }
      }).then(({data}) => {
        if (data&&data.errorCode==200) {
          this.$message.success("修改成功")
          this.internshipExperiencedialog=false
        }
      }).catch(() => {
        console.log('出错啦！！！！')
      })

    },

    submitcertificate(){
      this.$http({
        url: this.$http.adornUrl('/student/edit'),
        method: 'post',
        data:this.$http.adornData(this.resume),
        headers: {
          'UserToken':window.sessionStorage.getItem('Token'),
          'Content-Type': 'application/json',
          'charset': 'utf-8'
        }
      }).then(({data}) => {
        if (data&&data.errorCode==200) {
          this.$message.success("修改成功")
          this.certificatedialog=false
        }
      }).catch(() => {
        console.log('出错啦！！！！')
      })

    },

    submitaward(){
      this.$http({
        url: this.$http.adornUrl('/student/edit'),
        method: 'post',
        data:this.$http.adornData(this.resume),
        headers: {
          'UserToken':window.sessionStorage.getItem('Token'),
          'Content-Type': 'application/json',
          'charset': 'utf-8'
        }
      }).then(({data}) => {
        if (data&&data.errorCode==200) {
          this.$message.success("修改成功")
          this.awardExperiencedialog=false
        }
      }).catch(() => {
        console.log('出错啦！！！！')
      })

    },


    cancleadvantage(){
      this.advantagedialog=false
      this.loadResume()
    },
    cancleprojectExp(){
      this.projectExperiencedialog=false
      this.loadResume()
    },

    cancleinnternshipExpe(){
      this.internshipExperiencedialog=false
      this.loadResume()
    },
    canclecertificate(){
      this.certificatedialog=false
      this.loadResume()
    },
    cancleaward(){
      this.awardExperiencedialog=false
      this.loadResume()
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
    },
    preview(){
      this.$router.push({
        name: 'StudentResumePreview'
      })
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

.editspan{
  font-size: 12px;
  color: #02B28B;
  margin: -2px 0 0 8px;
  cursor: pointer;
}

</style>




