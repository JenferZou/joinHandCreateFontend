<template>
 <!-- <router-view name="adminSidebar"></router-view> -->
  <div class="layout">
    <i-row type="flex">
      <i-col span="5" class="layout-menu-left">
        <i-menu active-name="1-2" theme="dark" width="auto" :open-names="['1']">
          <div class="layout-logo-left"></div>
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
        <div class="layout-header"></div>
        <div class="layout-breadcrumb">
          <i-breadcrumb>
            <i-breadcrumb-item href="#">首页</i-breadcrumb-item>
            <i-breadcrumb-item href="#">应用中心</i-breadcrumb-item>
            <i-breadcrumb-item>某应用</i-breadcrumb-item>
          </i-breadcrumb>
        </div>
        <div class="layout-content">
          <router-view name="adminContent"></router-view>
<!--          <div class="layout-content-main">内容区域</div>-->
        </div>
        <div class="layout-copy">
          2011-2016 &copy; TalkingData
        </div>
      </i-col>
    </i-row>
  </div>
</template>

<script>
export default {
  name: "AdminIndex"
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
.layout-content{
  height: 800px;
  min-height: 200px;
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
  background: #464c5b;
  height: 900px;
}
.layout-header{
  height: 60px;
  background: #fff;
  box-shadow: 0 1px 1px rgba(0,0,0,.1);
}
.layout-logo-left{
  width: 90%;
  height: 30px;
  background: #5b6270;
  border-radius: 3px;
  margin: 15px auto;
}
</style>
