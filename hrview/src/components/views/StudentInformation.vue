<template>
  <div>
    <div class="layout-search">
        <i-row>
          <i-col :span="12">
            <i-row>
              <i-col :span="16" >
                <el-input size="small" v-model="studentName" placeholder="请通过学生名字搜索学生" clearable prefix-icon="el-icon-search">
                </el-input>
              </i-col>
              <i-col :span="3">
                <el-button type="primary" size="small" @click="search"><i-icon type="search" size="small"></i-icon>搜索</el-button>
              </i-col>
              <i-col :span="5">
                <el-button @click="clicktrue" type="primary" size="small">
                  <i-icon v-show="searchIcon" type="chevron-up"></i-icon>
                  <i-icon type="chevron-down" v-show="!searchIcon"></i-icon>
                  高级搜索</el-button>
              </i-col>
            </i-row>
          </i-col>
          <i-col :span="12">
            <i-row type="flex" justify="end">
              <i-col :span="5">
                <el-button type="success" size="small"> <i-icon size="15" type="arrow-down-a"></i-icon>导入数据</el-button>
              </i-col>
              <i-col :span="5">
                <el-button type="success" size="small"><i-icon size="15" type="arrow-up-a"></i-icon>导出数据</el-button>
              </i-col>
              <i-col :span="5">
                <el-button size="small" type="primary" @click="addStudent">
                  <i-icon type="plus-round" size="15"></i-icon>
                  添加学生</el-button>
                <EditForm ref="addstudent"></EditForm>
              </i-col>
            </i-row>
          </i-col>
        </i-row>
        <advanced-search v-show="show"></advanced-search>
    </div>
  <el-table class="StudentTable"
      :data="studentData"
      border
      style="width: 100%">
    <el-table-column
        fixed
        prop="sno"
        label="学号"
        width="150">
    </el-table-column>
    <el-table-column
        prop="sName"
        label="姓名"
        width="120">
    </el-table-column>
    <el-table-column
        prop="gender"
        label="性别"
        width="50">
    </el-table-column>
    <el-table-column
        prop="className"
        label="班级"
        width="120">
    </el-table-column>
    <el-table-column
        prop="sMajor"
        label="专业"
        width="120">
    </el-table-column>
    <el-table-column
        prop="sDepartment"
        label="所属学院"
        width="120">
    </el-table-column>
    <el-table-column
        prop="blood"
        label="血型"
        width="50">
    </el-table-column>
    <el-table-column
        prop="star"
        label="星座"
        width="120">
    </el-table-column>
    <el-table-column
        prop="mentor"
        label="指导老师"
        width="120">
    </el-table-column>
    <el-table-column
        prop="sPhone"
        label="电话"
        width="120">
    </el-table-column>
    <el-table-column
        prop="relatives"
        label="联系人"
        width="120">
    </el-table-column>
    <el-table-column
        prop="rPhone"
        label="联系人电话"
        width="120">
    </el-table-column>
    <el-table-column
        prop="score"
        label="积分"
        width="120">
    </el-table-column>
    <el-table-column
        prop="isGraduate"
        label="状态(在校/毕业)"
        width="120">
    </el-table-column>
    <el-table-column
        fixed="right"
        label="操作"
        width="100">
      <template v-slot="scope">
        <el-button type="text" size="small" @click="editForm(scope.row)">编辑</el-button>
        <el-button @click="deleteStudent(scope.row)" type="text" size="small" style="color:red;">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
  <el-pagination class="page"
       @current-change="change"
      background
      layout="prev, pager, next"
      :page-count="pageNum">
  </el-pagination>
    <EditForm ref="editfrom">
    </EditForm>
  </div>
</template>

<script>
import EditForm from "@/components/views/EditForm";
import {deleteOneStudent} from "@/components/utils";
import AdvancedSearch from "@/components/views/AdvancedSearch";

export default {
  name: "StudentInformation",
  components: {AdvancedSearch, EditForm},
  data() {
    return {
      pageNum:1,
      pageSize:9,
      currentPage:1,
      studentData:[],
      studentData1:{
        id:'',
        sno:'',
        sName:'',
        className:'',
        sPhone:'',
        sDepartment:'',
        sMajor:'',
        gender:'',
        mentor:'',
        relatives:'',
        rPhone:'',
        score:'',
        star:'',
        blood:'',
        isGraduate:''
      },
      show:false,
      searchIcon:false,
      studentName:''
    }
  },
  methods: {
    editForm(row){
      this.$nextTick(() => {
        // 弹框打开时初始化表单
        this.$refs.editfrom.init(row,true,'编辑')
      })
    },
    change(page){
      this.currentPage=page;
      if(''===this.studentName)
        this.getStudent()
      else
        this.search()
    },
    deleteStudent(student) {
      this.studentData=deleteOneStudent(student,this.studentData)
      this.$http({
        url: this.$http.adornUrl('/admin/delete'),
        method: 'post',
        data:this.$http.adornData(student),
        headers: {
          'Content-Type': 'application/json',
          'charset': 'utf-8'
        }
      }).then(({data}) => {
        if (data&&data.status===200) {
          this.$message.success(data.msg)
          this.studentData=deleteOneStudent(student,this.studentData)
        }else{
          this.$message.error(data.msg)
        }
      }).catch(() => {
        console.log('出错啦！！！！')
      })
    },
    getStudent(){
      let params={
       page : this.currentPage,
       limit : this.pageSize
      }
      this.$http({
        url: this.$http.adornUrl('/admin/listStudent'),
        method: 'get',
        params:this.$http.adornParams(params)
      }).then(({data}) => {
        if (data) {
          this.pageNum=data.totalPages
          this.studentData = data.content
        }
      }).catch(() => {
        console.log('出错啦！！！！')
      })
    },


    reset(data){
      Object.keys(data).forEach(key=>(data[key]=''))
      return data
    },
    addStudent(){
      this.$nextTick(() => {
        // 弹框打开时初始化表单
        this.$refs.addstudent.init(this.studentData1,false,'添加员工')
        this.studentData1=this.reset(this.studentData1)
      })
    },
    clicktrue(){
      this.searchIcon=!this.searchIcon
      this.show=!this.show
    },
    search(){
     let params={
       studentName:this.studentName,
       page : this.currentPage,
       limit : this.pageSize
     }
      this.$http({
        url: this.$http.adornUrl('/admin/search'),
        method: 'get',
        params:this.$http.adornParams(params)
      }).then(({data}) => {
        if (data) {
          this.pageNum=data.totalPages
          this.studentData = data.content
        }
      }).catch((error) => {
        console.log('出错啦！！！！')
        console.log(error)
      })
    }
  },
  created: function () {
    this.getStudent()
  },

}
</script>

<style scoped>
.layout-search{
  min-height: 80px;
  margin: 15px;
  overflow: hidden;
  background: #fff;
  border-radius: 4px;
}
.search{
  padding-top: 20px;
  text-align: center;
}
.StudentTable{
}
.page{
  text-align: center;
}
</style>
