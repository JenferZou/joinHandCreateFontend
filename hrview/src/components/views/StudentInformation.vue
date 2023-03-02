<template>
  <div>
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
        fixed="right"
        label="操作"
        width="100">
      <template v-slot="scope">
        <el-button type="text" size="small" @click="editForm(scope.row)">编辑</el-button>
        <el-button @click="handleClick(scope.row)" type="text" size="small" style="color:red;">删除</el-button>
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
export default {
  name: "StudentInformation",
  components: {EditForm},
  methods: {
    editForm(row){
      this.$nextTick(() => {
        // 弹框打开时初始化表单
        this.$refs.editfrom.init(row)
      })
    },
    change(page){
      this.currentPage=page;
      this.getStudent();
    },
    handleClick() {
      console.log(this.pageNum);
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
    }
  },
  created: function () {
    this.getStudent()
  },
  data() {
    return {
      pageNum:1,
      pageSize:8,
      currentPage:1,
      studentData:[],
    }
  }
}
</script>

<style scoped>
.StudentTable{
}
.page{
  text-align: center;
}
</style>
