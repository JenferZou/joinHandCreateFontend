
<template>
  <div class="layout">
    <div class="layout-ceiling">
      <h5 style="color: #fff;margin-left:520px">    携手创-学生端</h5>
    </div>
    <div class="layout-header" @click="returnhome">
    </div>
    <div style="height: 1000px">

        <div class="page-container">
        <div class="info-table">
          <el-card class="info-card">
            <div slot="header" class="info-header">
              <span class="info-title">个人信息表</span>
              <el-button type="primary" icon="el-icon-edit" @click="editInfo">编辑</el-button>
            </div>
            <div class="info-content">
              <i-row>
                <i-col :span="12">
                  <el-form :model="info" label-position="left" label-width="100px" readonly>
                    <el-form-item label="姓名">
                      <span>{{ info.sName }}</span>
                    </el-form-item>
                    <el-form-item label="性别">
                      <span>{{ info.gender }}</span>
                    </el-form-item>
                    <el-form-item label="学号">
                      <span>{{ info.sno }}</span>
                    </el-form-item>
                    <el-form-item label="联系电话">
                      <span>{{ info.sPhone }}</span>
                    </el-form-item>
                  </el-form>
                </i-col>
                <i-col :span="12">
                  <el-form :model="info" label-position="left" label-width="100px" readonly>
                    <el-form-item label="专业">
                      <span>{{ info.sMajor }}</span>
                    </el-form-item>
                    <el-form-item label="亲属联系人">
                      <span>{{ info.relatives }}</span>
                    </el-form-item>
                    <el-form-item label="亲属电话">
                      <span>{{ info.rPhone }}</span>
                    </el-form-item>
                    <el-form-item label="指导老师">
                      <span>{{ info.mentor }}</span>
                    </el-form-item>
                  </el-form>
                </i-col>


              </i-row>


              <i-row>
                <i-col :span="12">
                  <el-form :model="info" label-position="left" label-width="100px" readonly>
                    <el-form-item label="导师电话">
                      <span>{{ info.mentorPhone }}</span>
                    </el-form-item>
                    <el-form-item label="星座">
                      <span>{{ info.star }}</span>
                    </el-form-item>
                  </el-form>
                </i-col>

                <i-col :span="12">
                  <el-form :model="info" label-position="left" label-width="100px" readonly>
                    <el-form-item label="血型">
                      <span>{{ info.blood }}</span>
                    </el-form-item>

                  </el-form>
                </i-col>

                <i-col :span="12">
                  <el-form :model="info" label-position="left" label-width="100px" readonly>
                    <el-form-item label="积分">
                      <span>{{ info.score }}</span>
                    </el-form-item>

                  </el-form>
                </i-col>
              </i-row>
            </div>

          </el-card>

          <el-dialog :visible.sync="editDialogVisible" title="编辑个人信息" :before-close="beforeClose">
            <el-form :model="info" label-position="left" label-width="100px" :rules="rules" ref="infoForm">
              <el-form-item label="姓名" prop="sName">
                <el-input v-model="info.sName" :disabled="true"></el-input>
              </el-form-item>
              <el-form-item label="性别" prop="gender">
                <el-radio-group v-model="info.gender">
                  <el-radio label="男" :disabled="true">男</el-radio>
                  <el-radio label="女" :disabled="true">女</el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="学号" prop="sno">
                <el-input v-model="info.sno" :disabled="true"></el-input>
              </el-form-item>
              <el-form-item label="联系电话" prop="sPhone">
                <el-input v-model="info.sPhone"></el-input>
              </el-form-item>
              <el-form-item label="亲属联系人" prop="relatives">
                <el-input v-model="info.relatives"></el-input>
              </el-form-item>
              <el-form-item label="亲属电话" prop="rPhone">
                <el-input v-model="info.rPhone"></el-input>
              </el-form-item>
              <el-form-item label="专业" prop="sMajor">
                <el-input v-model="info.sMajor" :disabled="true"></el-input>
              </el-form-item>
              <el-form-item label="指导教师" prop="mentor">
                <el-input v-model="info.mentor"></el-input>
              </el-form-item>
              <el-form-item label="导师电话" prop="mentorPhone">
                <el-input v-model="info.mentorPhone"></el-input>
              </el-form-item>
              <el-form-item label="星座" prop="star">
                <el-select v-model="info.star" placeholder="请选择星座">
                  <el-option
                      v-for="star in stars"
                      :key="star"
                      :label="star"
                      :value="star">
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="血型" prop="blood">
                <el-select v-model="info.blood" placeholder="请选择血型">
                  <el-option
                      v-for="blood in bloods"
                      :key="blood"
                      :label="blood"
                      :value="blood">
                  </el-option>
                </el-select>
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
    return {
      info: {
        sName: '',
        gender: '',
        sno: '123123',
        sMajor: '',
        sPhone: '',
        relatives: '',
        rPhone: '',
        mentor: '',
        mentorPhone: '',
        blood: '',
        star: '',
        score: 10
      },
      stars: [
        '白羊座',
        '金牛座',
        '双子座',
        '巨蟹座',
        '狮子座',
        '处女座',
        '天秤座',
        '天蝎座',
        '射手座',
        '摩羯座',
        '水瓶座',
        '双鱼座',
      ],
      bloods: ['A', 'B', 'AB', 'O']
      ,
      rules: {
        sName: [{required: true, message: '请输入姓名', trigger: 'blur'}],
        gender: [{required: true, message: '请选择性别', trigger: 'change'}],
        sno: [{required: true, message: '请输入学号', trigger: 'blur'}],
        sPhone: [
          {required: true, message: '请输入联系电话', trigger: 'blur'},
          {pattern: /^1[3456789]\d{9}$/, message: '请输入正确的手机号码', trigger: 'blur'},
        ],
        sMajor: [{request: true, message: '请输入专业', trigger: 'blur'}],
        relatives: [{required: true, message: '请输入亲属姓名', trigger: 'blur'}],
        rPhone: [
          {required: true, message: '请输入亲属联系电话', trigger: 'blur'},
          {pattern: /^1[3456789]\d{9}$/, message: '请输入正确的手机号码', trigger: 'blur'},
        ],
        mentor: [{required: true, message: '请输入指导教师', trigger: 'blur'}],
        mentorPhone: [
          {required: true, message: '请输入指导教师联系电话', trigger: 'blur'},
          {pattern: /^1[3456789]\d{9}$/, message: '请输入正确的手机号码', trigger: 'blur'},
        ],
        star: [{required: true, message: '请输入星座', trigger: 'change'}],
        blood: [{required: true, message: '请输入血型', trigger: 'change'}]
      },
      editDialogVisible: false,

    }
  },
  created() {
    this.load()
  },

  methods: {
    returnhome(){
      this.$router.push({
        name:"StudentIndex",
      })
    },
    load() {
      // let parms={
      //   sno:'123123'
      // }
      this.$http({
        url: this.$http.adornUrl('/student/StudentMessageForm'),
        method: 'get',
        params:this.$http.adornParams(this.info),
        headers: {
          'UserToken':window.sessionStorage.getItem('Token'),
        }
      }).then(({data}) => {
          console.log(data)
          this.info=data
      }).catch(() => {
        console.log('出错啦！！！！')
      })
    },

    cancle(){
      this.editDialogVisible = false
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
            method: 'post',
            data:this.$http.adornData(this.info),
            headers: {
              'UserToken':window.sessionStorage.getItem('Token'),
              'Content-Type': 'application/json',
              'charset': 'utf-8'
            }
          }).then(({data}) => {
            if (data&&data.status===200) {
              this.$message.success(data.msg)
              this.awardExperiencedialog=false

            }else{
              this.$message.error(data.msg)
            }
          }).catch(() => {
            console.log('出错啦！！！！')
          })

          this.editDialogVisible = false
          this.$message.success('个人信息已保存')
        } else {
          this.$message.error('表单验证不通过，请检查输入项')
        }
      })
    },



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
