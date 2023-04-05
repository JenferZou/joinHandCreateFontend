<template>
    <div class="layout">
        <div class="layout-header">
            <i-row>
                <i-col span="23">
                    <h1>项目管理——教师端</h1>
                </i-col>
                <i-col span="1">
                    <div style="padding-top: 10px;">
                        <MyAvatar/>
                        <!--            <el-dropdown  size="small">
                                <el-avatar  icon="el-icon-user-solid"></el-avatar>
                                      <el-dropdown-menu slot="dropdown">
                                        <el-dropdown-item>个人主页</el-dropdown-item>
                                        <el-dropdown-item>退出登录</el-dropdown-item>
                                      </el-dropdown-menu>
                                    </el-dropdown>-->
                    </div>
                </i-col>
            </i-row>
        </div>
        <i-row type="flex">
            <i-col span="4" class="layout-menu-left">
                <i-menu @on-select="handleSelect" :active-name="sidebarItem"  width="auto">
                    <!--          <div class="layout-logo-left"><h1>menu</h1></div>-->
                    <i-submenu name="1">
                        <template slot="title">
                            <i-icon type="ios-keypad"></i-icon>
                            我的项目
                        </template>
                        <i-menu-item name="active">项目发布</i-menu-item>
                        <i-menu-item name="project">项目管理</i-menu-item>
                    </i-submenu>
                    <i-submenu name="2">
                        <template slot="title">
                            <i-icon type="ios-navigate"></i-icon>
                            申请列表
                        </template>
                        <i-menu-item name="index" >基本资料</i-menu-item>
                    </i-submenu>
                </i-menu>
            </i-col>
            <i-col span="20">
                <!--        <div class="layout-header"><h1>信息管理系统</h1></div>-->
                <div class="layout-breadcrumb">
                    <i-breadcrumb>
                        <i-breadcrumb-item href="/">首页</i-breadcrumb-item>
                        <i-breadcrumb-item href="#" v-show="item.firstTitle!==''">{{ item.firstTitle}}</i-breadcrumb-item>
                        <i-breadcrumb-item href="#" v-show="item.title!==''">{{ item.title }}</i-breadcrumb-item>
                    </i-breadcrumb>
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
import MyAvatar from "@/components/views/teacher/MyAvatar";
export default {
    name: "TeacherIndex",
    components: {MyAvatar},
    data() {
        return{
            item:{
                firstTitle:'',
                title:'',
                url:''
            },
            sidebarItem:'',
        }
    },
    mounted() {
        this.sidebarItem = this.$route.name;
    },
    methods: {
        change(){
            this.item.firstTitle=''
            this.item.title = ''
        },
        handleSelect(path) {
            if(path==='index') {
                this.item.firstTitle='学生资料'
                this.item.title = '基本资料'
            }
            else if(path==='active') {
                this.item.firstTitle='我的项目'
                this.item.title = '项目信息发布'
            }
            else if(path==='project') {
                this.item.firstTitle='我的项目'
                this.item.title = '项目管理'
            }
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
    height: 820px;
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
