<!--<template>-->
<!--  <div class="resume-form">-->
<!--    <h1 class="title">在线生成简历</h1>-->
<!--    <div class="form">-->
<!--      <el-form :model="resume" label-width="100px" ref="resumeForm">-->
<!--        <el-form-item label="姓名">-->
<!--          <el-input v-model="resume.name" placeholder="请输入姓名"></el-input>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="性别">-->
<!--          <el-radio-group v-model="resume.gender">-->
<!--            <el-radio label="男">男</el-radio>-->
<!--            <el-radio label="女">女</el-radio>-->
<!--          </el-radio-group>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="年龄">-->
<!--          <el-input-number v-model="resume.age" :min="1" :max="120"></el-input-number>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="电话">-->
<!--          <el-input v-model="resume.phone" placeholder="请输入电话号码"></el-input>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="邮箱">-->
<!--          <el-input v-model="resume.email" placeholder="请输入电子邮箱"></el-input>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="教育经历">-->
<!--          <el-input v-model="resume.education" type="textarea" :autosize="{ minRows: 2, maxRows: 6 }" placeholder="请输入教育经历"></el-input>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="工作经历">-->
<!--          <el-input v-model="resume.experience" type="textarea" :autosize="{ minRows: 2, maxRows: 6 }" placeholder="请输入工作经历"></el-input>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="技能特长">-->
<!--          <el-input v-model="resume.skills" type="textarea" :autosize="{ minRows: 2, maxRows: 6 }" placeholder="请输入技能特长"></el-input>-->
<!--        </el-form-item>-->
<!--        <el-form-item>-->
<!--          <el-button type="primary" @click="onSubmit">生成简历</el-button>-->
<!--          <el-button @click="onReset">重置表单</el-button>-->
<!--        </el-form-item>-->
<!--      </el-form>-->
<!--    </div>-->
<!--    <div class="resume-preview">-->
<!--      <h2 class="preview-title">{{ resume.name }} 的个人简历</h2>-->
<!--      <i-row>-->
<!--        <i-col :span="8">-->
<!--          <div class="preview-item">-->
<!--            <h3>个人信息</h3>-->
<!--            <p>姓名：{{ resume.name }}</p>-->
<!--            <p>性别：{{ resume.gender }}</p>-->
<!--            <p>年龄：{{ resume.age }}</p>-->
<!--            <p>电话：{{ resume.phone }}</p>-->
<!--            <p>邮箱：{{ resume.email }}</p>-->
<!--          </div>-->
<!--        </i-col>-->
<!--        <i-col :span="16">-->
<!--          <div class="preview-item">-->
<!--            <h3>教育经历</h3>-->
<!--            <p>{{ resume.education }}</p>-->
<!--          </div>-->
<!--          <div class="preview-item">-->
<!--            <h3>工作经历</h3>-->
<!--            <p>{{ resume.experience }}</p>-->
<!--          </div>-->
<!--          <div class="preview-item">-->
<!--            <h3>技能特长</h3>-->
<!--            <p>{{ resume.skills }}</p>-->
<!--          </div>-->
<!--        </i-col>-->
<!--      </i-row>-->
<!--    </div>-->
<!--  </div>-->
<!--</template>-->


<!--<script>-->
<!--export default {-->
<!--  name: "StudentResume",-->
<!--  data() {-->
<!--    return {-->
<!--      resume: {-->
<!--        name: '',-->
<!--        gender: '男',-->
<!--        age: 18,-->
<!--        phone: '',-->
<!--        email: '',-->
<!--        education: '',-->
<!--        experience: '',-->
<!--        skills: ''-->
<!--      }-->
<!--    };-->
<!--  },-->
<!--  methods: {-->
<!--    onSubmit() {-->
<!--      this.$refs.resumeForm.validate(valid => {-->
<!--        if (valid) {-->
<!--          alert('生成简历成功');-->
<!--        } else {-->
<!--          alert('表单校验失败');-->
<!--          return false;-->
<!--        }-->
<!--      });-->
<!--    },-->
<!--    onReset() {-->
<!--      this.$refs.resumeForm.resetFields();-->
<!--    }-->
<!--  }-->
<!--}-->
<!--</script>-->

<!--<style scoped>-->
<!--.resume-form {-->
<!--  display: flex;-->
<!--  flex-direction: column;-->
<!--  align-items: center;-->
<!--  padding: 50px;-->
<!--}-->
<!--.title {-->
<!--  margin-bottom: 30px;-->
<!--  font-size: 24px;-->
<!--  font-weight: bold;-->
<!--  text-align: center;-->
<!--}-->
<!--.form {-->
<!--  width: 60%;-->
<!--  margin-bottom: 50px;-->
<!--}-->
<!--.resume-preview {-->
<!--  width: 60%;-->
<!--  background-color: #f5f5f5;-->
<!--  padding: 30px;-->
<!--}-->
<!--.preview-title {-->
<!--  font-size: 20px;-->
<!--  font-weight: bold;-->
<!--  margin-bottom: 20px;-->
<!--  text-align: center;-->
<!--}-->
<!--.preview-item {-->
<!--  margin-bottom: 20px;-->
<!--}-->
<!--</style>-->



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
    <i-menu mode="horizontal" active-name="1" @on-select="editMes">
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
<!--                简历内容-->
                <div class="resumecontent">
                  <li class="presonBase">
                    <img data-v-b8fe8cbc="" src="https://zcplan.oss-cn-shenzhen.aliyuncs.com/man.png" alt="" class="avatar">
                    <div class="baseMes">
                      <h5  class="name" style="font-size: 20px;color: #2a303c">
                        邹景峰
                        <span class="student"
                              style="color: #dcb93c;
                              border: 1px solid #fed305;
                             background: -webkit-linear-gradient(left, #fcfff0, #fcfec4);
                             background: linear-gradient(90deg, #fcfff0, #fcfec4);margin-left: 10px">学生</span>
                      </h5>
                    <div  class="personal-mes">本科<span style="margin: 0 14px;color: #e6e6e6">|</span>
                      湛江<span style="margin: 0 14px;color: #e6e6e6">|</span> 19岁
                      <span style="margin: 0 14px;color: #e6e6e6">|</span> 暂无工作经验</div>
                      <div  class="contact-mes">
                        +8613043491174 1634114623@qq.com</div>
                    </div>
                    <div  class="edit-mes" style="color: #02B28B;display: flex;margin-left: auto;font-size: 12px; cursor: pointer">
                      <span >编辑</span>
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
                      <div  class="right">
                      <span class="add-desc">添加</span>
                      </div>
                    </div>
                    <div  class="advantage-con" v-if="data"><p  class="edit-show-content" style="max-width: 718px;">
                      在校有参与项目开发。多次帮助同学解决相关领域问题。热爱算法，认为算法才是程序的根本，每天都要坚持学习和刷算法题提高自身的编程能力和活跃思维。
                    </p> <div class="fixed-edit"><div class="edit-show-action-wrap">
                    <span class="edit-desc">编辑</span></div></div></div>
                  </li>
                  <li>
                    <div  class="project-title">
                      <div  class="left">
                      <span  class="line">
                      </span>
                        <h5  class="title">实习经历</h5>
                        <span  class="desc">(必填)</span>
                      </div>
                      <div  class="right">
                        <span class="add-desc">添加</span>
                      </div>
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
                      <div  class="right">
                        <span class="add-desc">添加</span>
                      </div>
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
                      <div  class="right">
                        <span class="add-desc">添加</span>
                      </div>
                    </div>
                  </li>
                  <li>
                    <div  class="project-title">
                      <div  class="left">
                      <span  class="line">
                      </span>
                        <h5  class="title">教育经历</h5>
                        <span  class="desc">(必填)</span>
                      </div>
                      <div  class="right">
                        <span class="add-desc">添加</span>
                      </div>
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
                      <div  class="right">
                        <span class="add-desc">添加</span>
                      </div>
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
                      <div  class="right">
                        <span class="add-desc">添加</span>
                      </div>
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


export default {
  name: "StudentResume",
  data() {
    return {
      data: 1,
    };
  },
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


.centercontent{
  height: 300px;
  margin-bottom: 10px;
  background-color: #fff;
  border-bottom: 1px dashed #c0c0c0;
  line-height: 50px;

}


/*.headmes{*/
/*  !*border-image: linear-gradient(45deg, #90cf5b, #00afee) 1;*!*/
/*  !*clip-path: inset(0 round 10px);*!*/
/*  background-color: rgb(64, 158, 255);*/
/*  font-size: 14px;*/
/*  text-align: center;*/
/*  padding: 45px;*/
/*  color: rgba(255,255,255,.7);*/
/*}*/

.headmes:hover{
  background-color: #91d5ff;
}

.footcontent{
  background-color: #f5f7f9;
  line-height: 50px;

}
.pra{
  background-color: darkgray;

}



.headmainmes{
  background-color: #e3e8ee;
  /*background-color: #5cadff;*/
  /*color: rgba(255,255,255,.7)*/
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

.add-desc{
  font-size: 12px;
  color: #02B28B;
  margin: -2px 0 0 8px;
  cursor: pointer;
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

.edit-desc{
  color: #02B28B;
  font-size: 12px;
  white-space: nowrap;
  height: 18px;
  min-width: 60px;
  margin-left: 12px;
  cursor: pointer;
}


</style>




