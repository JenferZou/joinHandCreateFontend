<template>
  <div>
    <i-row>
      <i-col :span="12">
        <i-row>
          <i-col :span="16" >
            <el-input size="small" v-model="studentName" placeholder="请通过学生名字搜索学生" clearable prefix-icon="el-icon-search">
            </el-input>
          </i-col>
          <i-col :span="3">
            <el-button type="primary" size="small"><i-icon type="search" size="small"></i-icon>搜索</el-button>
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
            <el-button size="small" type="primary">
              <i-icon type="plus-round" size="15"></i-icon>
              添加学生</el-button>
          </i-col>
        </i-row>
      </i-col>
    </i-row>
    <advanced-search v-show="show"></advanced-search>
  </div>
</template>

<script>
import AdvancedSearch from "@/components/views/AdvancedSearch";
export default {
  name: "StudentSearch",
  components: {AdvancedSearch},
  data(){
    return{
      show:false,
      searchIcon:false,
      studentName:''
    }
  },
  methods:{
    clicktrue(){
      this.searchIcon=!this.searchIcon
      this.show=!this.show
    }
  }
}
</script>

<style scoped>

</style>
