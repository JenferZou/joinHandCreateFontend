<template>
  <el-dialog :title="title" :visible.sync="dialogVisible" :closeOnClickModal="false">
    <el-form :inline="true" :model="studentData" ref="studentData" class="demo-form-inline" label-width="100px" size="small" :rules="rules">
      <i-row>
        <i-col :span="8">
          <el-form-item label="学号" label-width="auto" prop="sno">
            <el-input v-model.number="studentData.sno" placeholder="学号" maxlength="12" style="width: 150px"
                      :disabled="isdisabled"></el-input>
          </el-form-item>
        </i-col>
        <i-col :span="8">
          <el-form-item label="姓名" label-width="auto" prop="sName">
            <el-input v-model="studentData.sName" placeholder="姓名" size="small" style="width: 130px"></el-input>
          </el-form-item>
        </i-col>
        <i-col :span="8">
          <el-form-item label="班级" label-width="auto" prop="className">
            <el-input style="width: 130px" v-model="studentData.className" placeholder="班级" size="small"></el-input>
          </el-form-item>
        </i-col>
      </i-row>
      <i-row>
        <i-col :span="6">
          <el-form-item label="电话" label-width="auto" prop="sPhone">
            <el-input style="width: 110px" v-model.number="studentData.sPhone" placeholder="电话" size="small"
                      maxlength="12"></el-input>
          </el-form-item>
        </i-col>
        <i-col :span="8">
          <el-form-item label="所属学院" label-width="auto" prop="sDepartment">
            <el-input v-model="studentData.sDepartment" placeholder="所属学院" size="small" style="width: 130px"></el-input>
          </el-form-item>
        </i-col>
        <i-col :span="6">
          <el-form-item label="专业" label-width="auto" prop="sMajor">
            <el-input v-model="studentData.sMajor" placeholder="专业" size="small" style="width: 120px"></el-input>
          </el-form-item>
        </i-col>
        <i-col :span="4">
          <el-form-item label="性别" label-width="auto" >
            <el-select size="small" v-model="studentData.gender" placeholder="性别" style="width: 60px">
              <el-option label="男" value="男"></el-option>
              <el-option label="女" value="女"></el-option>
            </el-select>
          </el-form-item>
        </i-col>
      </i-row>
      <i-row>
        <i-col :span="8">
          <el-form-item label="指导老师" label-width="auto" >
            <el-input style="width: 130px" v-model="studentData.mentor" placeholder="指导老师" size="small"
                      maxlength="12"></el-input>
          </el-form-item>
        </i-col>
        <i-col :span="7">
          <el-form-item label="联系人" label-width="auto" prop="relatives">
            <el-input style="width: 130px" v-model="studentData.relatives" placeholder="联系人姓名" size="small"></el-input>
          </el-form-item>
        </i-col>
        <i-col :span="9">
          <el-form-item label="联系人电话" label-width="auto" prop="rPhone">
            <el-input style="width: 130px" v-model.number="studentData.rPhone" placeholder="联系人电话" size="small"
                      maxlength="12"></el-input>
          </el-form-item>
        </i-col>
      </i-row>
      <i-row>
        <i-col :span="6">
          <el-form-item label="积分" label-width="auto" prop="score">
            <el-input style="width: 120px" v-model="studentData.score" placeholder="积分" size="small"></el-input>
          </el-form-item>
        </i-col>
        <i-col :span="5">
          <el-form-item label="星座" label-width="auto" >
            <el-select size="small" v-model="studentData.star" placeholder="星座" style="width: 80px">
              <el-option label="白羊" value="白羊"></el-option>
              <el-option label="金牛" value="金牛"></el-option>
              <el-option label="双子" value="双子"></el-option>
              <el-option label="巨蟹" value="巨蟹"></el-option>
              <el-option label="狮子" value="狮子"></el-option>
              <el-option label="处女" value="处女"></el-option>
              <el-option label="天秤" value="天秤"></el-option>
              <el-option label="天蝎" value="天蝎"></el-option>
              <el-option label="射手" value="射手"></el-option>
              <el-option label="魔羯" value="魔羯"></el-option>
              <el-option label="水瓶" value="水瓶"></el-option>
              <el-option label="双鱼" value="双鱼"></el-option>
            </el-select>
          </el-form-item>
        </i-col>
        <i-col :span="6">
          <el-form-item label="血型" >
            <el-select size="small" v-model="studentData.blood" placeholder="血型" style="width: 70px">
              <el-option label="O" value="O"></el-option>
              <el-option label="AB" value="AB"></el-option>
              <el-option label="A" value="A"></el-option>
              <el-option label="B" value="B"></el-option>
            </el-select>
          </el-form-item>
        </i-col>
        <i-col :span="7">
          <el-form-item label="状态" prop="isGraduate">
            <el-select size="small" v-model="studentData.isGraduate" placeholder="状态" style="width: 100px">
              <el-option label="在校" value="在校"></el-option>
              <el-option label="已毕业" value="已毕业"></el-option>
            </el-select>
          </el-form-item>
        </i-col>
      </i-row>
      <i-row type="flex" justify="end">
        <i-col :span="3">
          <el-form-item>
            <el-button @click="cancel" size="small">取消</el-button>
          </el-form-item>
        </i-col>
        <i-col :span="3">
          <el-form-item>
            <el-button type="primary" @click="modify('studentData')" size="small">{{ title === '添加员工' ? '保存' : '修改' }}</el-button>
          </el-form-item>
        </i-col>

      </i-row>
    </el-form>
  </el-dialog>
</template>

<script>
export default {
  name: "EditForm",
  data() {
    return {
      title: '',
      isdisabled: '',
      studentData: '',
      dialogVisible: false,
      rules:{
        sno:[
          {required:true,message:'学号不能为空',trigger:'blur'},
          { pattern: /\b\d{12}\b/, message: '请输入正确的学号', trigger: 'blur' }
        ],
        sName:[
          {required:true,message:'姓名不能为空',trigger:'blur'}
        ],
        className:[
          {required:true,message:'班级不能为空',trigger:'blur'}
        ],
        sPhone:[
          {required:true,message:'电话不能为空',trigger:'blur'},
          { pattern: /^1[3|5|7|8|9]\d{9}$/, message: '请输入正确的号码格式', trigger: 'blur' }
        ],
        sDepartment:[
          {required:true,message:'学院信息不能为空',trigger:'blur'}
        ],
        sMajor:[
          {required:true,message:'专业不能为空',trigger:'blur'}
        ],
        relatives:[
          {required:true,message:'联系人不能为空',trigger:'blur'}
        ],
        score:[
          {required:true,message:'积分不能为空',trigger:'blur'}
        ],
        isGraduate:[
          {required:true,message:'请重新填写',trigger:'blur'}
        ],
        rPhone:[
          {required:true,message:'电话不能为空',trigger:'blur'},
          { pattern: /^1[3|5|7|8|9]\d{9}$/, message: '请输入正确的号码格式', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    init(data, isdisabled, title) {
      this.dialogVisible = true
      this.studentData = data
      this.isdisabled = isdisabled
      this.title = title
    },
    cancel() {
      this.dialogVisible = false
    },
    modify(formName) {
      this.$refs[formName].validate((valid)=>{
        if (valid) {
          if ('' === this.studentData.id) {
            this.$http({
              url: this.$http.adornUrl('/teacher/add'),
              method: 'post',
              data: this.$http.adornData(this.studentData),
              headers: {
                'UserToken':window.sessionStorage.getItem('Token'),
                'Content-Type': 'application/json',
                'charset': 'utf-8'
              }
            }).then(({data}) => {
              if (data && data.status === 200) {
                this.$message.success(data.msg)
              } else {
                this.$message.error(data.msg)
              }
            }).catch(() => {
              console.log('出错啦！！！！')
            })
          } else {
            this.$http({
              url: this.$http.adornUrl('/teacher/modify'),
              method: 'post',
              data: this.$http.adornData(this.studentData),
              headers: {
                'UserToken':window.sessionStorage.getItem('Token'),
                'Content-Type': 'application/json',
                'charset': 'utf-8'
              }
            }).then(({data}) => {
              if (data && data.status === 200) {
                this.$message.success(data.msg)
              } else {
                this.$message.error(data.msg)
              }
            }).catch(() => {
              console.log('出错啦！！！！')
            })
          }
          this.dialogVisible = false
        } else {
          return false;
        }
      });
    }
  }
}
</script>

<style scoped>

</style>
