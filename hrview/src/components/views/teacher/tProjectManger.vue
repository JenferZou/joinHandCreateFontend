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
      studentInfo: '',
      resumeInfo: '',
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
      this.$http({
        url: this.$http.adornUrl('/teacher/lookStudentResume'),
        method: 'post',
        data: this.$http.adornData(row),
      }).then(({data}) => {

        this.$router.push({
          name: 'StudentDelieverResume',
          params: {
            studentInfo: data.studentInfo,
            resumeInfo: data.resumeInfo
          }
        })
      }).catch(() => {
        console.log('出错啦！！！！')
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
</style>
