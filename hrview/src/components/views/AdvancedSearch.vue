<template>
  <div>
    <el-form>
    <i-row>
      <i-col :span="4">
        <el-form-item label="学号:">
          <el-input style="width: 160px" size="small" placeholder="学号"></el-input>
        </el-form-item>
      </i-col>
      <i-col :span="4">
        <el-form-item label="姓名:">
        <el-input style="width: 160px" size="small" placeholder="姓名"></el-input>
        </el-form-item>
      </i-col>
      <i-col :span="3">
        <el-form-item label="性别:">
          <el-select size="small"  placeholder="性别" style="width: 80px" v-model="studentData.gender">
            <el-option label="男" value="男"></el-option>
            <el-option label="女" value="女"></el-option>
          </el-select>
        </el-form-item>
      </i-col>
      <i-col :span="5">
        <el-form-item label="班级:">
          <el-input style="width: 200px" size="small" placeholder="班级"></el-input>
        </el-form-item>
      </i-col>
      <i-col :span="6">
        <el-form-item label="专业:">
          <el-input style="width: 200px" size="small" placeholder="专业"></el-input>
        </el-form-item>
      </i-col>
    </i-row>
    <i-row>
      <i-col :span="7">
        <el-form-item label="所属学院:">
          <el-input style="width: 230px" size="small" placeholder="所属学院"></el-input>
        </el-form-item>
      </i-col>
      <i-col :span="5">
        <el-form-item label="指导老师:">
          <el-input style="width: 170px" size="small" placeholder="指导老师"></el-input>
        </el-form-item>
      </i-col>
      <i-col :span="6">
        <el-form-item label="联系人:">
          <el-input style="width: 200px" size="small" placeholder="联系人"></el-input>
        </el-form-item>
      </i-col>
    </i-row>
    <i-row type="flex" justify="end">
      <i-col :span="2">
        <el-button size="small" type="primary">
          <i-icon type="search" size="8"></i-icon>
          搜索
        </el-button>
      </i-col>
      <i-col :span="2">
        <el-button size="small">取消</el-button>
      </i-col>
    </i-row>
      <br>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "AdvancedSearch",
  data(){
    return{
      studentData:{
        gender:'',
      }
    }
  }
}
</script>

<style scoped>

</style>
