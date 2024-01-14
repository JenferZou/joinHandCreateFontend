<template>
  <div>
    <i-row class="search">
      <i-col :span="12">
        <i-row>
          <i-col :span="13">
            <el-input @keyup.enter.native="search" size="small" v-model="searchName" placeholder="请输入学生姓名" clearable
                      prefix-icon="el-icon-search">
            </el-input>
          </i-col>
          <i-col :span="6">
            <el-button type="primary" size="small" @click="search(true)">
              <i-icon type="search" size="small"></i-icon>
              搜索
            </el-button>
            <el-button size="small" @click="research">
              重置
            </el-button>
          </i-col>
        </i-row>
      </i-col>
    </i-row>
    <el-table border ref="multipleTable" :data="project" tooltip-effect="dark" style="width: 100%">
      <!--  字段 -->
      <el-table-column prop="pid" label="项目id" width="120"></el-table-column>
      <el-table-column prop="projectName" label="项目名称" width="120"></el-table-column>
      <el-table-column prop="sname" label="学生姓名" width="120"></el-table-column>
      <el-table-column prop="sno" label="学生学号" width="120"></el-table-column>
      <el-table-column prop="smajor" label="学生专业" width="120"></el-table-column>
      <el-table-column prop="content" label="内容" show-overflow-tooltip></el-table-column>

      <el-table-column label="学生简历" width="100">
        <template v-slot="scope">
          <el-button type="text" size="small" style="margin: 20px" @click="queryStudentResume(scope.row)">
            查看
          </el-button>
        </template>
      </el-table-column>

      <el-table-column fixed="right" label="操作" width="120">
        <template #default="scope">
          <el-button type="text" size="small" style="margin: 5px" @click="agreeForm(scope.row)">
            同意加入申请
          </el-button>
          <el-button type="text" size="small" style="color:red; margin: 5px" @click="refuseForm(scope.row)">
            拒绝加入申请
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--  导航条  -->
    <el-card class="box-card" shadow="never">
      <el-pagination class="page" @current-change="change" background layout="prev, pager, next" :page-count="pageNum" :current-page="currentPage"/>
    </el-card>
    <i-row>
      <!-- 弹窗 -->
      <el-dialog :visible.sync="refuseVisible" title="提示" width="30%">
        <span>确定要拒绝该学生的项目加入申请吗</span>
        <span slot="footer">
          <el-button type="primary" @click="refuseStudent" size="small">确 定</el-button>
          <el-button @click="refuseVisible = false" size="small">取 消</el-button>
        </span>
      </el-dialog>
      <!-- 弹窗 -->
      <el-dialog :visible.sync="agreeVisible1" title="提示" width="30%">
        <span>确定要同意该学生的项目加入申请吗</span>
        <span slot="footer">
          <el-button type="primary" @click="agreestudent" size="small">确 定</el-button>
          <el-button @click="agreeVisible1 = false" size="small">取 消</el-button>
        </span>
      </el-dialog>
    </i-row>

    <!-- 学生简历弹框 -->
    <el-dialog :visible.sync="isVisible" title="查看" >
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
                    </div>
                    <!--  简历内容-->
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
                      <li class="advantage" v-if="resumeInfo.personalAdvantage">
                        <div  class="project-title">
                          <div  class="left">
                            <i-icon type="email" style="color: #02B28B;"></i-icon>
                            <h5  class="title">个人优势</h5>
                          </div>
                          <div  class="right">
                          </div>
                        </div>
                        <div  class="advantage-con" v-if="resumeInfo.personalAdvantage"><p  class="edit-show-content" style="max-width: 718px;">
                          {{ resumeInfo.personalAdvantage }}
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
                        <div  class="advantage-con" v-if="resumeInfo.projectExperience"><p  class="edit-show-content" style="max-width: 718px;">
                          {{ resumeInfo.projectExperience }}
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
                        <div  class="advantage-con" v-if="resumeInfo.internshipExperience"><p  class="edit-show-content" style="max-width: 718px;">
                          {{ resumeInfo.internshipExperience }}
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
                        <div  class="advantage-con" v-if="resumeInfo.certificate"><p  class="edit-show-content" style="max-width: 718px;">
                          {{ resumeInfo.certificate }}
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
                        <div  class="advantage-con" v-if="resumeInfo.awardExperience"><p  class="edit-show-content" style="max-width: 718px;">
                          {{ resumeInfo.awardExperience }}
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
      </div>
    </el-dialog>
  </div>
</template>

<script>

export default {
  name: "projectManger",
  data() {
    return {
      agreeVisible1: false,
      refuseVisible: false,
      project: [],
      searchName: '',
      // 当前页
      currentPage: 1,
      // 总记录数
      pageSize: 1,
      // 总分页数
      pageNum: 1,
      contest: {
        name: '',
        startTime: '',
        content: '',
      },
      idParams: [],
      dcontest: '',
      delievers: '',
      // 是否显示弹框
      isVisible: false,
      // 学生个人信息
      studentInfo: {},
      // 学生的简历信息
      resumeInfo: {
        resumeId: '',
        wechatId: '',
        politicsStatus: '',
        personalAdvantage: '...',
        internshipExperience: '',
        projectExperience: '',
        awardExperience: '',
        certificate: ''
      },
    }
  },
  methods: {
    // 改变分页
    change(nowPage) {
      console.log("改变分页码:",nowPage);
      this.currentPage = nowPage;
      if (this.searchName.trim() !== ''){
        this.search(false);
      } else {
        this.getAllInformation();
      }
    },
    // 拒绝加入表单
    refuseForm(data) {
      this.$nextTick(() => {
        // 弹框打开时初始化表单
        this.delievers = data
        this.refuseVisible = true
      })
    },
    // 同意表单
    agreeForm(data) {
      this.$nextTick(() => {
        // 弹框打开时初始化表单
        this.delievers = data
        this.agreeVisible1 = true
      })
    },
    // 拒绝学生
    refuseStudent() {
      console.log("拒绝被调用",this.delievers)
      this.$http({
        url: this.$http.adornUrl('/teacher/refuseDeliever'),
        method: 'post',
        data: {
          sno:this.delievers.sno,
          pid:this.delievers.pid
        },
      }).then((res) => {
        console.log(res);
        // 判断返回状态
        if (res && res.status == 200) {
          this.$message.success(`拒绝了${this.delievers.sname}的项目加入申请`);
        } else {
          this.$message.error(res.data.message);
        }
      }).catch(() => {
        console.log('请求失败');
      }).finally(()=>{
        // 重新设置数据
        this.delievers = '';
        // 关闭弹框
        this.refuseVisible = false;
        // 重新查询数据
        this.getAllInformation();
      })
    },
    // 同意学生
    agreestudent() {
      console.log("同意被调用",this.delievers)
      this.$http({
        url: this.$http.adornUrl('/teacher/agreeDeliever'),
        method: 'post',
        data: {
          sno:this.delievers.sno,
          pid:this.delievers.pid
        },
      }).then((res) => {
        console.log(res);
        // 判断返回状态
        if (res && res.status == 200) {
          this.$message.success(`同意了${this.delievers.sname}的项目加入申请`);
        } else {
          this.$message.error(res.data.message);
        }
      }).catch(() => {
        console.log('请求失败');
      }).finally(()=>{
        // 重新设置数据
        this.delievers = '';
        // 关闭弹框
        this.agreeVisible1 = false;
        // 重新查询数据
        this.getAllInformation();
      })
    },
    // TODO 查询学生简历
    queryStudentResume(row) {
      console.log(row);
      // 获取信息
      this.$http({
        url: this.$http.adornUrl('/teacher/lookStudentResume'),
        method: 'post',
        data: {sno:row.sno},
      }).then((res) => {
        console.log("学生的信息:",res);
        if (res.data.success == true){
          // 属性赋值
          this.studentInfo = res.data.data.studentInfo;
          this.resumeInfo = res.data.data.resumeInfo;
          // 打开弹框
          this.isVisible = true;
        }else{
          // 失败提示信息
          this.$message.error(res.data.message);
        }
      }).catch(() => {
        console.log('提交失败!')
      })
    },
    // 搜索
    search(index) {
      if (index == true){
        this.currentPage = 1;
      }
      this.$http({
        url: this.$http.adornUrl('/teacher/searchDeliever'),
        method: 'post',
        data:{
          page: this.currentPage,
          limit: 4,
          searchName: this.searchName
        }
      }).then(res => {
        // 当前页
        this.currentPage = res.data.current;
        // 总记录数
        this.pageSize = res.data.size;
        // 总分页数
        this.pageNum = res.data.pages;
        // 设置数据
        this.project = res.data.data;
      }).catch(() => {
        console.log('请求失败')
      })
    },
    // 初始化信息
    getAllInformation() {
      this.$http({
        url: this.$http.adornUrl('/teacher/getDeliever'),
        method:'post',
        data:{
          page:  this.currentPage,
          limit: 4
        }
      }).then((res) => {
        // 当前页
        this.currentPage = res.data.current;
        // 总记录数
        this.pageSize = res.data.size;
        // 总分页数
        this.pageNum = res.data.pages;
        // 设置数据
        this.project = res.data.data;
      }).catch(() => {
        console.log('请求失败')
      })
    },
    // 清除搜索
    research(){
      this.searchName = '';
      // 初始化信息
      this.getAllInformation();
    }
  },
  created() {
    // 初始化信息
    this.getAllInformation();
  }
}
</script>

<style scoped>
.search {
  padding-top: 10px;
  padding-bottom: 10px;
}

.page {
  text-align: center;
}

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
