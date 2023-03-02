<template>
 <!-- <router-view name="adminSidebar"></router-view> -->
  <div class="layout">
    <i-row type="flex">
      <i-col span="4" class="layout-menu-left">
        <i-menu @on-select="handleSelect" :active-name="sidebarItem"  width="auto">
          <div class="layout-logo-left"><h1>menu</h1></div>
          <i-submenu name="1">
            <template slot="title">
              <i-icon type="ios-navigate"></i-icon>
              学生资料
            </template>
            <i-menu-item name="index">基本资料</i-menu-item>
          </i-submenu>
          <i-submenu name="2">
            <template slot="title">
              <i-icon type="ios-keypad"></i-icon>
              人事管理
            </template>
            <i-menu-item name="index1">学生资料</i-menu-item>
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
      <i-col span="20">
        <div class="layout-header"><h1>信息管理系统</h1></div>
        <div class="layout-breadcrumb">
          <i-breadcrumb>
            <i-breadcrumb-item href="#">首页</i-breadcrumb-item>
            <i-breadcrumb-item href="#">应用中心</i-breadcrumb-item>
          </i-breadcrumb>
        </div>
        <div class="layout-search">
          <StudentSearch></StudentSearch>
        </div>
        <div class="layout-content">
          <router-view></router-view>
        </div>
        <div class="layout-copy">
          2011-2016 &copy; TalkingData
        </div>
      </i-col>
    </i-row>
  </div>
</template>

<script>
import StudentSearch from "@/components/views/StudentSearch";
export default {
  name: "AdminIndex",
  components: {StudentSearch},
  data() {
    return{
      sidebarItem:'',
    }
  },
  mounted() {
    this.sidebarItem = this.$route.name;
  },
  methods: {
    handleSelect(path) {
      this.sidebarItem = path;
      this.$router.push({
      name:path
      })
    }
  }
}
</script>

<style scoped>
.layout{
  border: 1px solid #d7dde4;
  background: #f5f7f9;
  position: relative;
}
.layout-breadcrumb{
  padding: 10px 15px 0;
}
.layout-search{
  min-height: 150px;
  margin: 15px;
  overflow: hidden;
  background: #fff;
  border-radius: 4px;
}
.layout-content{
  min-height: 550px;
  margin: 15px;
  overflow: hidden;
  background: #fff;
  border-radius: 4px;
}
.layout-copy{
  text-align: center;
  padding: 10px 0 20px;
  color: #9ea7b4;
}
.layout-menu-left{
  background-color: white;
  height: 820px;
}
.layout-header{
  height: 60px;
  background: #fff;
  box-shadow: 0 1px 1px rgba(0,0,0,.1);
}
。layout-header h1{
  text-align: center;
}
.layout-logo-left{
  width: 90%;
  height: 50px;
  border-radius: 3px;
  margin: 15px auto;
}
.layout-logo-left h1{
  color: white;
  text-align: center;
  background-color: black;
}
</style>
