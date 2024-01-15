<template>
  <div class="layout">
    <div style="height: 1000px">

      <div class="page-container">
        <div class="info-table">
          <el-card class="info-card">
            <div slot="header" class="info-header">
              <span class="info-title">教师信息表</span>
              <el-button type="primary" icon="el-icon-edit" @click="editInfo">编辑</el-button>
            </div>
            <div class="info-content">
              <i-row>
                <i-col :span="12">
                  <el-form :model="info" label-position="left" label-width="100px" readonly>
                    <el-form-item label="教师号">
                      <span>{{ info.no }}</span>
                    </el-form-item>
                    <el-form-item label="学院信息">
                      <span>{{ info.department }}</span>
                    </el-form-item>
                    <el-form-item label="密码">
                      <span>******</span>
                    </el-form-item>
                  </el-form>
                </i-col>

                <i-col :span="12">
                  <el-form :model="info" label-position="left" label-width="100px" readonly>
                    <el-form-item label="姓名">
                      <span>{{ info.name }}</span>
                    </el-form-item>
                    <el-form-item label="所属专业">
                      <span>{{ info.major }}</span>
                    </el-form-item>
                    <el-form-item label="联系电话">
                      <span>{{ info.phone }}</span>
                    </el-form-item>
                  </el-form>
                </i-col>
              </i-row>
            </div>
          </el-card>

          <el-dialog :visible.sync="editDialogVisible" title="编辑个人信息" :before-close="beforeClose">
            <el-form :model="info" label-position="left" label-width="100px" :rules="rules" ref="infoForm">
              <el-form-item label="姓名" prop="name">
                <el-input v-model="info.name"></el-input>
              </el-form-item>
              <el-form-item label="学号" prop="no">
                <el-input v-model="info.no" :disabled="true"></el-input>
              </el-form-item>
              <el-form-item label="联系电话" prop="phone">
                <el-input v-model="info.phone"></el-input>
              </el-form-item>
              <el-form-item label="学院信息" prop="department">
                <el-input v-model="info.department" ></el-input>
              </el-form-item>
              <el-form-item label="所属专业" prop="major">
                <el-input v-model="info.major" ></el-input>
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
  name: "TeacherMessageForm",
  data() {
    return {
      info: {},
      rules: {
        no: [{required: true, message: '请输入学号', trigger: 'blur'}],
        name: [{required: true, message: '请输入姓名', trigger: 'blur'}],
        major: [{request: true, message: '请输入专业', trigger: 'blur'}],
        phone: [{required: true, message: '请输入联系电话', trigger: 'blur'},],
        department: [{required: true, message: '请输入学院名称', trigger: 'blur'}],
      },
      editDialogVisible: false,
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      this.$http({
        url: this.$http.adornUrl('/teacher/selectByInfo'),
        method: 'get',
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
        }
      })
    },
    saveInfo() {
      this.$refs.infoForm.validate(valid => {
        if (valid) {
          this.$http({
            url: this.$http.adornUrl('/teacher/editMessage'),
            method: 'post',
            data:this.info,

          }).then(({data}) => {
            if (data&&data.status===200) {
              this.$message.success(data.msg)
              this.load()
            }
          }).catch(() => {
            console.log('请求失败')
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