<template>
  <el-dialog  :visible.sync="dialogVisible" width="50%">
    <el-form :model="resume"  ref="form">
      <el-form-item label="姓名" prop="name">
        <el-input v-model="studentInfo.sName"></el-input>
      </el-form-item>
      <el-form-item label="政治面貌" prop="politicsStatus">
        <el-input v-model="resume.politicsStatus"></el-input>
      </el-form-item>
      <el-form-item label="地址" prop="address">
        <el-input v-model="resume.address"></el-input>
      </el-form-item>
      <el-form-item label="年龄" prop="age">
        <el-input v-model.number="resume.age"></el-input>
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input v-model.number="resume.phone"></el-input>
      </el-form-item>
      <el-form-item label="个人优势" prop="strength">
        <el-input type="textarea" v-model="resume.personalAdvantage"></el-input>
      </el-form-item>
      <el-form-item label="申请原因" prop="reason">
        <el-input type="textarea" v-model="resume.reason"></el-input>
      </el-form-item>
      <el-form-item label="项目经历" prop="project">
        <el-input type="textarea" v-model="resume.projectExperience"></el-input>
      </el-form-item>
      <el-form-item label="实践经历" prop="experience">
        <el-input type="textarea" v-model="resume.internshipExperience"></el-input>
      </el-form-item>
      <el-form-item label="资格证书" prop="certificate">
        <el-input v-model="resume.certificate"></el-input>
      </el-form-item>
      <el-form-item label="获奖经历" prop="award">
        <el-input type="textarea" v-model="resume.awardExperience"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer">
      <el-button @click="dialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="submitForm">确 定</el-button>
    </div>
  </el-dialog>



</template>

<script>
export default {
  name: "ResumeEdit",
  data(){
    return {
    resume:{
      name:'',
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
      sName:'',
      sMajor:'',
      sPhone:'',
      relatives:'',
      rPhone:'',
      mentor:'',
      star:'',
      blood:'',
      sDepartment:'',
      score:'',
      sno:'',
      resumeIdclassName:'',
      genderid:'',
      isGraduate:'',
      mentorPhone:''
    },
      dialogVisible:false,
      namedialogVisible:false

    };
},
  methods:{
    init(){
      this.dialogVisible = true
    },
    cancel(){
      this.dialogVisible = false
    },
    close(){

    },
    submitForm(){

    }
  }
}
</script>

<style scoped>

</style>