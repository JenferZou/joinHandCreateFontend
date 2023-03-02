<template>
  <el-dialog title="编辑" :visible.sync="dialogVisible" :closeOnClickModal="false">
  <el-form :inline="true" :model="formInline" class="demo-form-inline" label-width="80px">
<i-row>
  <i-col :span="8">
    <el-form-item label="学号" label-width="auto">
      <el-input v-model="studentData.sno" placeholder="学号" size="small" maxlength="12" ></el-input>
    </el-form-item>
  </i-col>
  <i-col :span="8">
    <el-form-item label="姓名" label-width="auto">
      <el-input v-model="studentData.sName" placeholder="姓名" size="small"></el-input>
    </el-form-item>
  </i-col>
  <i-col :span="8">
    <el-form-item label="班级" label-width="auto">
      <el-input style="width: 130px" v-model="studentData.className" placeholder="班级" size="small"></el-input>
    </el-form-item>
  </i-col>
</i-row>

    <i-row>
      <i-col :span="6" >
        <el-form-item label="电话" label-width="auto">
          <el-input style="width: 130px" v-model="studentData.sPhone" placeholder="电话" size="small" maxlength="12"></el-input>
        </el-form-item>
      </i-col>
      <i-col :span="10">
        <el-form-item label="所属学院" label-width="auto">
          <el-input v-model="studentData.sDepartment" placeholder="所属学院" size="small"></el-input>
        </el-form-item>
      </i-col>
      <i-col :span="8">
        <el-form-item label="专业" label-width="auto">
          <el-input v-model="studentData.sMajor" placeholder="专业" size="small"></el-input>
        </el-form-item>
      </i-col>
    </i-row>
    <i-row>
      <i-col :span="8">
        <el-form-item label="指导老师" label-width="auto">
          <el-input style="width: 130px" v-model="studentData.mentor" placeholder="指导老师" size="small" maxlength="12"></el-input>
        </el-form-item>
      </i-col>
      <i-col :span="7">
        <el-form-item label="联系人" label-width="auto">
          <el-input style="width: 130px" v-model="studentData.relatives" placeholder="联系人姓名" size="small"></el-input>
        </el-form-item>
      </i-col>
      <i-col :span="9">
        <el-form-item label="联系人电话" label-width="auto">
          <el-input style="width: 130px" v-model="studentData.rPhone" placeholder="联系人电话" size="small" maxlength="12"></el-input>
        </el-form-item>
      </i-col>
    </i-row>
    <i-row>
      <i-col :span="7">
        <el-form-item label="积分" label-width="auto">
          <el-input style="width: 130px" v-model="studentData.score" placeholder="积分" size="small" ></el-input>
        </el-form-item>
      </i-col>
      <i-col :span="7">
        <el-form-item label="星座" label-width="auto">
          <el-input style="width: 130px" v-model="studentData.star" placeholder="星座" size="small"></el-input>
        </el-form-item>
      </i-col>
      <i-col :span="10">
        <el-form-item label="血型" size="small">
          <el-select v-model="studentData.blood" placeholder="血型">
            <el-option label="O" value="O"></el-option>
            <el-option label="AB" value="AB"></el-option>
            <el-option label="A" value="A"></el-option>
            <el-option label="B" value="B"></el-option>
          </el-select>
        </el-form-item>
      </i-col>
    </i-row>
    <i-row type="flex" justify="end" >
      <i-col :span="3">
        <el-form-item>
          <el-button  @click="cancel" size="small">取消</el-button>
        </el-form-item>
      </i-col>
      <i-col :span="3">
        <el-form-item>
          <el-button type="primary" @click="modify" size="small">修改</el-button>
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
      studentData:{},
      dialogVisible: false,
      formInline: {
        user: '',
        region: ''
      }
    }
  },
  methods: {
    init(data){
      this.dialogVisible=true
      this.studentData=data
    },
    cancel(){
      this.dialogVisible=false
    },
    modify() {
      this.$http({
        url: this.$http.adornUrl('/admin/modify'),
        method: 'post',
        data:this.$http.adornData(this.studentData),
        headers: {
          'Content-Type': 'application/json',
          'charset': 'utf-8'
        }
      }).then(({data}) => {
        if (data&&data.status===200) {
           this.$message.success(data.msg)
        }
      }).catch((error) => {
        this.$message.error('修改失败')
        console.log('出错啦！！！！')
        console.log(error)
      })
      this.dialogVisible=false
    }
  }
}
</script>

<style scoped>

</style>
