<template>
  <div class="resume-form">
    <h1 class="title">在线生成简历</h1>
    <div class="form">
      <el-form :model="resume" label-width="100px" ref="resumeForm">
        <el-form-item label="姓名">
          <el-input v-model="resume.name" placeholder="请输入姓名"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="resume.gender">
            <el-radio label="男">男</el-radio>
            <el-radio label="女">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input-number v-model="resume.age" :min="1" :max="120"></el-input-number>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="resume.phone" placeholder="请输入电话号码"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="resume.email" placeholder="请输入电子邮箱"></el-input>
        </el-form-item>
        <el-form-item label="教育经历">
          <el-input v-model="resume.education" type="textarea" :autosize="{ minRows: 2, maxRows: 6 }" placeholder="请输入教育经历"></el-input>
        </el-form-item>
        <el-form-item label="工作经历">
          <el-input v-model="resume.experience" type="textarea" :autosize="{ minRows: 2, maxRows: 6 }" placeholder="请输入工作经历"></el-input>
        </el-form-item>
        <el-form-item label="技能特长">
          <el-input v-model="resume.skills" type="textarea" :autosize="{ minRows: 2, maxRows: 6 }" placeholder="请输入技能特长"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">生成简历</el-button>
          <el-button @click="onReset">重置表单</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="resume-preview">
      <h2 class="preview-title">{{ resume.name }} 的个人简历</h2>
      <el-row>
        <el-col :span="8">
          <div class="preview-item">
            <h3>个人信息</h3>
            <p>姓名：{{ resume.name }}</p>
            <p>性别：{{ resume.gender }}</p>
            <p>年龄：{{ resume.age }}</p>
            <p>电话：{{ resume.phone }}</p>
            <p>邮箱：{{ resume.email }}</p>
          </div>
        </el-col>
        <el-col :span="16">
          <div class="preview-item">
            <h3>教育经历</h3>
            <p>{{ resume.education }}</p>
          </div>
          <div class="preview-item">
            <h3>工作经历</h3>
            <p>{{ resume.experience }}</p>
          </div>
          <div class="preview-item">
            <h3>技能特长</h3>
            <p>{{ resume.skills }}</p>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>


<script>
export default {
  name: "StudentResume",
  data() {
    return {
      resume: {
        name: '',
        gender: '男',
        age: 18,
        phone: '',
        email: '',
        education: '',
        experience: '',
        skills: ''
      }
    };
  },
  methods: {
    onSubmit() {
      this.$refs.resumeForm.validate(valid => {
        if (valid) {
          alert('生成简历成功');
        } else {
          alert('表单校验失败');
          return false;
        }
      });
    },
    onReset() {
      this.$refs.resumeForm.resetFields();
    }
  }
}
</script>

<style scoped>
.resume-form {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 50px;
}
.title {
  margin-bottom: 30px;
  font-size: 24px;
  font-weight: bold;
  text-align: center;
}
.form {
  width: 60%;
  margin-bottom: 50px;
}
.resume-preview {
  width: 60%;
  background-color: #f5f5f5;
  padding: 30px;
}
.preview-title {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 20px;
  text-align: center;
}
.preview-item {
  margin-bottom: 20px;
}
</style>tyle>