
<template>
  <div class="layout">
    <div class="layout-ceiling">
      <h5 style="color: #fff;margin-left:50px;font-weight: bolder;font-size: 20px">    携手创-学生端</h5>
    </div>
    <div class="layout-header">
      <el-button type="primary" @click="returnhome" style="margin-top: 10px;margin-left: 20px">返回</el-button>
    </div>
    <div style="height: 1000px">

        <div class="page-container">
        <div class="info-table">
          <el-card class="info-card">
            <div slot="header" class="info-header">
              <span class="info-title">个人信息表</span>
              <el-button type="success" icon="el-icon-edit" @click="modify" style="margin-left:370px">修改密码</el-button>
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
<!--                    <el-form-item label="指导老师">-->
<!--                      <span>{{ info.mentor }}</span>-->
<!--                    </el-form-item>-->
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
              <el-form-item prop="oldpassword">
                <el-input prefix-icon="el-icon-lock" placeholder="请输入原密码" show-password  v-model="pwd.oldpassword"></el-input>
              </el-form-item>
              <el-form-item prop="password">
                <el-input prefix-icon="el-icon-lock" placeholder="请输入新密码" show-password  v-model="pwd.password"></el-input>
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

          <el-dialog :visible.sync="editDialogVisible" title="编辑个人信息" :before-close="beforeClose">
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
</template>


<script>


export default {
  sName: 'StudentMessageForm',
  data() {
    const validatePassword = (rule, confirmPass, callback) => {
      if (confirmPass === '') {
        callback(new Error('请确认密码'))
      } else if (confirmPass !== this.pwd.password) {
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
    beforeClose(done) {
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
      this.$refs.infoForm.validate(valid => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl('/student/'),
            method: 'put',
            data:this.$http.adornData(this.pwd),
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
            }else {
              this.$message.success('修改失败')
            }
          }).catch(() => {
            console.log('出错啦！！！！')
          })

          this.pwdVisible = false
        } else {
          this.$message.error('表单验证不通过，请检查输入项')
        }
      })
    }



  }
}
</script>


<style scoped>
.layout{
  border: 1px solid #d7dde4;
  background: #f5f7f9;
  position: relative;
  border-radius: 4px;
  overflow: hidden;
}
.layout-logo{
  width: 100px;
  height: 30px;
  background: #5b6270;
  border-radius: 3px;
  float: left;
  position: relative;
  top: 15px;
  left: 20px;
  text-align: center;
  padding: 10px 0 20px;
  color: #d7dde4;
}

.layout-header{
  height: 60px;
  background: #fff;
  box-shadow: 0 1px 1px rgba(0,0,0,.1);
}
.layout-copy{
  text-align: center;
  padding: 10px 0 20px;
  color: #9ea7b4;
}
.layout-ceiling{
  background: #464c5b;
  padding: 10px 0;
  overflow: hidden;
}
.layout-ceiling-main{
  float: right;
  margin-right: 15px;
}
.layout-ceiling-main a{
  color: #9ba7b5;
}



.info-table {
  margin: 20px auto;
  max-width: 800px;

}

.info-card {
  padding: 20px;

}

.info-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.info-title {
  font-size: 24px;
  font-weight: bold;
}

.info-content {
  margin-top: 20px;
}

.dialog-footer {
  text-align: center;
}



</style>














<!--<template>-->

<!--</template>-->

<!--<style scoped>-->


<!--</style>-->
