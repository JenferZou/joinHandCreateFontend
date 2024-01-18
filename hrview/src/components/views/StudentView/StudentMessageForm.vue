
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
          <div class="layout">
            <div style="height: 585px">

                <div class="page-container">
                <div class="info-table">
                  <el-card class="info-card">
                    <div slot="header" class="info-header">
                      <span class="info-title" style="font-size: 25px;font-weight: bolder">个人信息表</span>
                      <el-button type="success" icon="el-icon-edit" @click="modify" style="margin-left:770px">修改密码</el-button>
                      <el-button type="primary" icon="el-icon-edit" @click="editInfo">编辑</el-button>
                    </div>
                    <div class="info-content">
                      <i-row>
                        <i-col :span="12">
                          <el-form :model="info" label-position="left" label-width="100px" readonly>
                            <el-form-item label="姓名">
                              <span>{{ info.sname }}</span>
                            </el-form-item>
                            <el-form-item label="性别">
                              <span>{{ info.gender }}</span>
                            </el-form-item>
                            <el-form-item label="学号">
                              <span>{{ info.sno }}</span>
                            </el-form-item>
                            <el-form-item label="联系电话">
                              <span>{{ info.sphone }}</span>
                            </el-form-item>
                          </el-form>
                        </i-col>
                        <i-col :span="12">
                          <el-form :model="info" label-position="left" label-width="100px" readonly>
                            <el-form-item label="专业">
                              <span>{{ info.smajor }}</span>
                            </el-form-item>
                            <el-form-item label="所属学院">
                              <span>{{ info.sdepartment }}</span>
                            </el-form-item>
                            <el-form-item label="所属班级">
                              <span>{{ info.className }}</span>
                            </el-form-item>
                          </el-form>
                        </i-col>


                      </i-row>


                    </div>

                  </el-card>


                  <el-dialog
                      title="提示"
                      :visible.sync="pwdVisible"
                      width="30%"
                      :before-close="beforeClose">
                    <el-form :model="pwd" :rules="rules1" ref="formRef">
                      <el-form-item prop="oldPassword">
                        <el-input prefix-icon="el-icon-lock" placeholder="请输入原密码" show-password  v-model="pwd.oldPassword"></el-input>
                      </el-form-item>
                      <el-form-item prop="newPassword">
                        <el-input prefix-icon="el-icon-lock" placeholder="请输入新密码" show-password  v-model="pwd.newPassword"></el-input>
                      </el-form-item>
                      <el-form-item prop="confirmPass">
                        <el-input prefix-icon="el-icon-lock" placeholder="请确认新密码" show-password  v-model="pwd.confirmPass"></el-input>
                      </el-form-item>
                    </el-form>
                    <div slot="footer" class="dialog-footer">
                      <el-button @click="cancle1">取消</el-button>
                      <el-button type="primary" @click="savepwd">保存</el-button>
                    </div>
                  </el-dialog>

                  <el-dialog :visible.sync="editDialogVisible" title="编辑个人信息" :before-close="beforeCloseInfo">
                    <el-form :model="info" label-position="left" label-width="100px" :rules="rules" ref="infoForm">
                      <el-form-item label="姓名" prop="sname">
                        <el-input v-model="info.sname"></el-input>
                      </el-form-item>
                      <el-form-item label="性别" prop="gender">
                        <el-radio-group v-model="info.gender">
                          <el-radio label="男" >男</el-radio>
                          <el-radio label="女" >女</el-radio>
                        </el-radio-group>
                      </el-form-item>
                      <el-form-item label="学号" prop="sno">
                        <el-input v-model="info.sno" :disabled="true"></el-input>
                      </el-form-item>
                      <el-form-item label="联系电话" prop="sphone">
                        <el-input v-model="info.sphone"></el-input>
                      </el-form-item>
                      <el-form-item label="所属学院" prop="sdepartment">
                        <el-input v-model="info.sdepartment" ></el-input>
                      </el-form-item>
                      <el-form-item label="专业" prop="smajor">
                        <el-input v-model="info.smajor" ></el-input>
                      </el-form-item>
                      <el-form-item label="所属班级" prop="className">
                        <el-input v-model="info.className"></el-input>
                      </el-form-item>

                    </el-form>
                    <div slot="footer" class="dialog-footer">
                      <el-button @click="cancle">取消</el-button>
                      <el-button type="primary" @click="saveInfo">保存</el-button>
                    </div>
                  </el-dialog>



                </div>
                  </div>

            </div>
            <div class="layout-copy">
            </div>
          </div>
      </i-row>

    </div>
    <div class="layout-copy" style="text-align: center;background-color: white">
      2024 &copy; AtXBaiYa
    </div>
  </div>

</template>


<script>


export default {
  sName: 'StudentMessageForm',
  data() {
    const validatePassword = (rule, confirmPass, callback) => {
      if (confirmPass === '') {
        callback(new Error('请确认密码'))
      } else if (confirmPass !== this.pwd.newPassword) {
        callback(new Error('两次输入的密码不一致'))
      } else {
        callback()
      }
    }
    return {
      info: {
      },
      rules: {
        sname: [{required: true, message: '请输入姓名', trigger: 'blur'}],
        gender: [{required: true, message: '请选择性别', trigger: 'change'}],
        sno: [{required: true, message: '请输入学号', trigger: 'blur'}],
        sphone: [
          {required: true, message: '请输入联系电话', trigger: 'blur'},
        ],
        smajor: [{request: true, message: '请输入专业', trigger: 'blur'}],
        sdepartment: [{required: true, message: '请输入学院名称', trigger: 'blur'}],
        className: [{required: true, message: '请输入班级', trigger: 'change'}],
      },
      rules1: {
        oldpassword: [
          { required: true, message: '请输入原密码', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
        ],
        confirmPass: [
          { validator: validatePassword, trigger: 'blur' }
        ]
      },
      editDialogVisible: false,
      pwdVisible: false,
      pwd:{},
    }
  },
  created() {
    this.load()
  },

  methods: {
    logout(){
      this.$router.push('/')
    },
    modify(){
      this.pwdVisible = true;
    },
    returnhome(){
      this.$router.push({
        name:"StudentIndex",
      })
    },
    // load() {
    //   // let parms={
    //   //   sno:'123123'
    //   // }
    //   this.$http({
    //     url: this.$http.adornUrl('/student/StudentMessageForm'),
    //     method: 'get',
    //     params:this.$http.adornParams(this.info),
    //     headers: {
    //       'UserToken':window.sessionStorage.getItem('Token'),
    //     }
    //   }).then(({data}) => {
    //       console.log(data)
    //       this.info=data
    //   }).catch(() => {
    //     console.log('出错啦！！！！')
    //   })
    // },
    load() {
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
        this.info = data.data
      }).catch(() => {
        console.log('出错啦！！！！')
      })
    },

    cancle(){
      this.editDialogVisible = false
      this.load()

    },
    cancle1(){
      this.pwdVisible = false
      this.load()

    },

    editInfo() {
      this.editDialogVisible = true
    },
    beforeCloseInfo(done) {
          this.$refs.infoForm.validate(valid => {
              if (valid) {
                  done()
              } else {
                  this.editDialogVisible = false
                  this.load()
                  // this.$message.error('表单验证不通过，请检查输入项')
              }
          })
      },

      beforeClose() {
              this.pwdVisible = false;
              this.$refs['formRef'].clearValidate();
              this.pwd = {oldPassword:'', newPassword:'', confirmPassword:''};
              this.load();
      },
    saveInfo() {
      this.$refs.infoForm.validate(valid => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl('/student/editMessage'),
            method: 'put',
            data:this.$http.adornData(this.info),
            headers: {
              'UserToken':window.sessionStorage.getItem('Token'),
              'Content-Type': 'application/json',
              'charset': 'utf-8'
            }
          }).then(({data}) => {
            if (data.errorCode=="200") {
              // this.$message.success(data.msg)
              // this.awardExperiencedialog=false
              this.$message.success('个人信息已保存')
              this.load()

            }else {
              this.$message.error('修改失败')
            }
          }).catch(() => {
            console.log('出错啦！！！！')
          })

          this.editDialogVisible = false
        } else {
          this.$message.error('表单验证不通过，请检查输入项')
        }
      })
    },
    savepwd(){
      this.$refs.formRef.validate(valid => {
          let params ={
              oldPassword:this.pwd.oldPassword,
              newPassword:this.pwd.newPassword,
          }
        if (valid) {
          this.$http({
            url: this.$http.adornUrl('/student/editpass'),
            method: 'post',
            data:this.$http.adornData(params),
            headers: {
              'UserToken':window.sessionStorage.getItem('Token'),
              'Content-Type': 'application/json',
              'charset': 'utf-8'
            }
          }).then(({data}) => {
            if (data.errorCode=="200") {
              // this.$message.success(data.message)
              this.load()
              this.$message.success('修改密码成功')
                this.$refs.formRef.resetFields();
            }else {
              this.$message.error('修改失败')
            }
          }).catch(() => {
            console.log('出错啦！！！！')
          })

          this.pwdVisible = false
        } else {
          this.$message.error('表单验证不通过，请检查输入项')
        }
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






