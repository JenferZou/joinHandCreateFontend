<template>
    <div class="layout" style="min-width: 1300px">
        <div class="layout-header">
            <i-row>
                <i-col span="23">
                    <h5 style="color: #fff;margin-top:15px;margin-left:50px;font-weight: bolder;font-size: 20px">
                        携手创-管理员端
                    </h5>
                </i-col>
                <i-col span="1">
                    <div style="padding-top: 10px;">

                        <MyAvatar/>
                    </div>
                </i-col>
            </i-row>
        </div>
        <i-row type="flex">
            <i-col span="4" class="layout-menu-left">
                <i-menu @on-select="handleSelect" :active-name="sidebarItem" width="auto">
                    <i-submenu name="2">
                        <template slot="title">
                            <i-icon type="ios-keypad"></i-icon>
                            信息管理
                        </template>
                        <i-menu-item name="index">学生信息</i-menu-item>
                        <i-menu-item name="project">项目管理</i-menu-item>
                        <i-menu-item name="teacher">老师信息</i-menu-item>
                        <i-menu-item name="analysis">数据分析</i-menu-item>
                    </i-submenu>
                    <i-submenu name="3">
                        <template slot="title">
                            <i-icon type="ios-person-outline"></i-icon>
                            系统管理
                        </template>
                        <i-menu-item name="userManagement">用户管理</i-menu-item>
                    </i-submenu>
                </i-menu>
            </i-col>
            <i-col span="20">
                <div class="layout-breadcrumb">
                    <i-breadcrumb>
                        <i-breadcrumb-item href="#" v-show="item.firstTitle!==''">{{ item.firstTitle }}
                        </i-breadcrumb-item>
                        <i-breadcrumb-item href="#" v-show="item.title!==''">{{ item.title }}</i-breadcrumb-item>
                    </i-breadcrumb>
                </div>

        <div class="layout-content" style="min-height: 550px">
          <router-view></router-view>
        </div>
        <div class="layout-copy" style="text-align: center;background-color: white">
          2024 &copy; AtXBaiYa
        </div>
      </i-col>
    </i-row>
  </div>
</template>

<script>
import MyAvatar from "@/components/views/admin/MyAvatar";
export default {
    name: "AdminIndex",
    components: {MyAvatar},
    data() {
        return {
            menuActive: false,
            item: {
                firstTitle: '',
                title: '',
                url: ''
            },
            sidebarItem: '',
        }
    },
    mounted() {
        this.sidebarItem = this.$route.name;
    },
    methods: {
        toggleMenu() {
            this.menuActive = !this.menuActive;
        },
        change() {
            this.item.firstTitle = ''
            this.item.title = ''
        },
        handleSelect(path) {
            if (path === 'index') {
                this.item.firstTitle = '信息管理'
                this.item.title = '学生信息'
            } else if (path === 'teacher') {
                this.item.firstTitle = '信息管理'
                this.item.title = '老师信息'
            } else if (path === 'project') {
                this.item.firstTitle = '信息管理'
                this.item.title = '项目管理'
            } else if (path === 'analysis') {
                this.item.firstTitle = '信息管理'
                this.item.title = '数据分析'
            } else if (path === 'userManagement') {
                this.item.firstTitle = ' 系统管理'
                this.item.title = '用户管理'
            }
            this.sidebarItem = path;
            this.$router.push({
                name: path
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
.layout-content{
  min-height: 620px;
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
  height: 600px;
}
.layout-header h1{
  padding-top: 10px;
  color: white;
}
.layout-header{
  height: 60px;
  background: #1093e8;
  box-shadow: 0 1px 1px rgba(0,0,0,.1);
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
