<template>
    <div class="layout">
        <i-menu mode="horizontal" theme="dark" active-name="1" on-select="ed">
            <h5 style="color: #fff;margin-left:50px;font-weight: bolder;font-size: 20px">
                携手创-学生端
            </h5>
        </i-menu>
        <i-menu mode="horizontal" active-name="1" @on-select="editMes">
            <div class="layout-assistant">
                <i-menu-item name="StudentIndex">主页</i-menu-item>
                <i-menu-item name="StudentMessageForm">个人信息</i-menu-item>
                <i-menu-item name="StudentResumePreview">个人简历</i-menu-item>
                <i-menu-item name="notify">消息通知</i-menu-item>
                <div style="padding-left: 900px">
                    <el-button size="small" @click="logout()">退出登录</el-button>
                </div>
            </div>
        </i-menu>
        <div class="layout-content">
            <i-row>
                <i-col span="5">
                    <i-menu active-name="1-2" width="auto" :open-names="['1','2']" @on-select="goWhere">
                        <i-submenu name="1">
                            <template slot="title">
                                <i-icon type="ios-navigate"></i-icon>
                                个人信息
                            </template>
                            <i-menu-item name="StudentMessageForm">修改个人信息</i-menu-item>
                            <i-menu-item name="StudentResume">填充个人简历</i-menu-item>

                        </i-submenu>
                        <i-submenu name="2">
                            <template slot="title">
                                <i-icon type="ios-keypad"></i-icon>
                                项目管理
                            </template>
                            <i-menu-item name="projectSearch">项目大厅</i-menu-item>
                            <i-menu-item name="myProjectSearch">项目申请</i-menu-item>
                        </i-submenu>
                    </i-menu>
                </i-col>
                <i-col span="19">
                    <div class="layout-content-main">
                        <div class="in-layout-content">

                            <i-row class="headcontent" :gutter="16">

                                <i-col span="14">
                                    <div class="mes" style="background-color: #fff">

                                        <i-col style="height: 295px;background-color: #fff;text-align: center; border-bottom: 1px dashed #c0c0c0">
                                            <i-col span="24">

                                                <li class="presonBase" style="height: 250px;padding-top: 10px">
                                                    <img data-v-b8fe8cbc=""
                                                         src="https://zcplan.oss-cn-shenzhen.aliyuncs.com/man.png"
                                                         alt="" class="avatar" style="height: 80px">
                                                    <div class="baseMes">
                                                        <h5 class="name" style="font-size: 20px;color: #2a303c">
                                                            {{ studentInfo.sname }}
                                                            <span class="student"
                                                                  style="color: #dcb93c;
                              border: 1px solid #fed305;
                             background: -webkit-linear-gradient(left, #fcfff0, #fcfec4);
                             background: linear-gradient(90deg, #fcfff0, #fcfec4);margin-left: 10px">学生</span>
                                                        </h5>
                                                        <div class="personal-mes"> {{ studentInfo.gender }}<span
                                                                style="margin: 0 14px;color: #e6e6e6">|</span>
                                                            {{ studentInfo.sdepartment }}<span
                                                                    style="margin: 0 14px;color: #e6e6e6">|</span>
                                                            {{ studentInfo.smajor }}
                                                            <span style="margin: 0 14px;color: #e6e6e6">|</span>
                                                            {{ studentInfo.className }}
                                                        </div>
                                                        <div class="contact-mes">
                                                            <i-icon type="ios-telephone"></i-icon>
                                                            +86 {{ studentInfo.sphone }}
                                                        </div>
                                                    </div>
                                                </li>
                                            </i-col>
                                        </i-col>

                                    </div>
                                </i-col>

                                <i-col span="10">
                                    <div class="mes" style="background-color: #fff;height: 295px">
                                        <i-col style="height: 295px;background-color: #fff;text-align: center; border-bottom: 1px dashed #c0c0c0">

                                            <i-col style="height: 45px">
                                                <i-col span="24" class="headmainmes"
                                                       style="height: 45px;font-size: 14px">
                                                    <i-icon type="email-unread"></i-icon>
                                                    消息通知
                                                </i-col>
                                            </i-col>

                                            <i-col style="height: 50px; font-weight:bold;background-color: #fff;text-align: center;border-bottom: 1px dashed #c0c0c0;">
                                                <i-col span="8">消息发送人</i-col>
                                                <i-col span="8">接收时间</i-col>
                                                <i-col span="8">消息内容</i-col>
                                            </i-col>

                                            <el-empty
                                                    v-if="message == undefined ||message == null || message.length <= 0 "
                                                    description="暂无信息" style="size: 10px"></el-empty>

                                            <i-col v-for="item in message" :key="item.id"
                                                   style="height: 50px;background-color: #fff;text-align: center;border-bottom: 1px dashed #c0c0c0;">
                                                <i-col span="8">{{ item.mentor }}</i-col>
                                                <i-col span="8">{{ item.createTime }}</i-col>
                                                <i-col span="8"
                                                       style="text-overflow: ellipsis; overflow: hidden; white-space: nowrap;">
                                                    {{ item.content }}
                                                </i-col>

                                            </i-col>
                                        </i-col>
                                    </div>


                                </i-col>
                            </i-row>


                            <i-row class="centercontent">
                                <i-col style="height: 295px;background-color: #fff;text-align: center; border-bottom: 1px dashed #c0c0c0">

                                    <i-col span="24">
                                        <div class="mes">
                                            <i-col style="height: 45px">
                                                <i-col span="24" class="headmainmes"
                                                       style="height: 45px;font-size: 14px">
                                                    <i-icon type="email-unread">
                                                    </i-icon>
                                                    最新项目发布通知
                                                </i-col>
                                            </i-col>
                                            <i-row>
                                                <i-col style="height: 50px; font-weight:bold;background-color: #fff;text-align: center;border-bottom: 1px dashed #c0c0c0;">
                                                    <i-col span="6">项目名称</i-col>
                                                    <i-col span="6">专业需求</i-col>
                                                    <i-col span="6">项目指导老师</i-col>
                                                    <i-col span="6">报名时间</i-col>
                                                </i-col>
                                            </i-row>
                                            <el-empty
                                                    v-if="project == undefined ||project == null || project.length <= 0"
                                                    description="暂无信息" style="size: 10px"></el-empty>
                                            <i-row v-for="item in project" :key="item.id">
                                                <i-col
                                                        style="height: 50px;background-color: #fff;text-align: center;border-bottom: 1px dashed #c0c0c0;">
                                                    <i-col span="6" style="text-overflow: ellipsis;white-space: nowrap;overflow: hidden;">{{ item.name }}</i-col>
                                                    <i-col span="6" style="text-overflow: ellipsis;white-space: nowrap;overflow: hidden;">{{ item.needMajor }}
                                                    </i-col>
                                                    <i-col span="6" style="text-overflow: ellipsis;white-space: nowrap;overflow: hidden;">{{ item.mentor }}</i-col>
                                                    <i-col span="6" style="text-overflow: ellipsis;white-space: nowrap;overflow: hidden;">{{ item.startTime }}</i-col>
                                                </i-col>
                                            </i-row>

                                        </div>
                                    </i-col>
                                </i-col>
                            </i-row>

                        </div>
                    </div>
                </i-col>
            </i-row>
        </div>
        <div class="layout-copy" style="text-align: center;background-color: white">
            2024 &copy; AtXBaiYa
        </div>
    </div>
</template>

<script>

export default {
    data() {
        return {
            text: 'ufiefiowejfowejfoijwef',
            contest: {
                id: '',
                title: '',
                remark: '',
                startTime: '',
                endTime: '',
                content: ''
            },
            project: [],
            company: [],
            message: [],
            currentPage: 1,
            pageSize: 4,
            pageNum: 10,
            active1: [],
            active: {
                id: '',
                title: '',
                type: '',
                activeTime: '',
                activeDuration: '',
                content: '',
                points: '',
                remark: ''
            },
            studentInfo: {},
        }
    },
    created() {
        this.loadMessage()
        this.loadStudent()
        this.getAllInformation()
    },
    methods: {
        logout() {
            localStorage.clear()
            this.$router.push('/')
        },
        loadStudent() {
            this.$http({
                url: this.$http.adornUrl('/student/selectById'),
                method: 'get',
                headers: {
                    'UserToken': window.sessionStorage.getItem('Token'),
                    'Content-Type': 'application/json',
                    'charset': 'utf-8'
                }
            }).then(({data}) => {
                console.log(data)
                this.studentInfo = data.data
            }).catch(() => {
                console.log('出错啦！！！！')
            })
        },
        getAllInformation() {
            let params = {
                pageNo: this.currentPage,
                pageSize: this.pageSize
            }
            this.$http({
                url: this.$http.adornUrl('/project/getProject'),
                method: 'get',
                params: this.$http.adornParams(params),
            }).then(({data}) => {
                if (data) {
                    this.pageNum = data.pages
                    this.currentPage = data.current
                    this.project = data.data
                    this.pageSize = data.size

                }
            }).catch(() => {
                console.log('出错啦！！！！')
            })
        },
        loadMessage() {
            let params = {
                pageNo: this.currentPage,
                pageSize: this.pageSize
            }
            this.$http({
                url: this.$http.adornUrl('/message/getMessageBySno'),
                method: 'get',
                params: this.$http.adornParams(params),
                headers: {
                    'UserToken': window.sessionStorage.getItem('Token'),
                    'Content-Type': 'application/json',
                    'charset': 'utf-8'
                }
            }).then(({data}) => {
                this.message = data.data
                this.message.forEach(item => {
                    // 检查pid是否为0
                    if (item.tno == 0) {
                        // 设置mentor为管理员
                        this.$set(item, 'mentor', '管理员');
                    }
                });
                this.pageNum = data.pages
                this.currentPage = data.current
                this.pageSize = data.size
            }).catch(() => {
                console.log('出错啦！！！！')
            })
        },

        goWhere(name) {
            this.$router.push({name})
        },

        editMes(name) {
            this.$router.push({name})
        },
        ediRes(name) {
            this.$router.push({name})
        }

    }
}
</script>


<style scoped>
.layout {
    border: 1px solid #d7dde4;
    background: #f5f7f9;
}

.layout-logo {
    width: 100px;
    height: 30px;
    border-radius: 3px;
    background: #5b6270;
    float: left;
    position: relative;
    top: 15px;
    left: 20px;
}

.layout-nav {
    width: 420px;
    margin: 0 auto;
}

.layout-assistant {
    width: 500px;
    margin: 0 auto;
    height: inherit;

}

.layout-breadcrumb {
    padding: 10px 15px 0;
}

.layout-content {
    min-height: 400px;
    margin: 15px;
    overflow: hidden;
    background: #fff;
    border-radius: 4px;
}

.layout-content-main {
    padding: 10px;
    background-color: #f5f7f9;
}

.layout-copy {
    text-align: center;
    padding: 10px 0 20px;
    color: #9ea7b4;
}


.headcontent {
    background-color: #f5f7f9;
    line-height: 50px;
    margin-bottom: 10px;

}


.centercontent {
    margin-bottom: 10px;
    background-color: #fff;
    border-bottom: 1px dashed #c0c0c0;
    line-height: 50px;

}


/*.headmes{*/
/*  !*border-image: linear-gradient(45deg, #90cf5b, #00afee) 1;*!*/
/*  !*clip-path: inset(0 round 10px);*!*/
/*  background-color: rgb(64, 158, 255);*/
/*  font-size: 14px;*/
/*  text-align: center;*/
/*  padding: 45px;*/
/*  color: rgba(255,255,255,.7);*/
/*}*/

.headmes:hover {
    background-color: #91d5ff;
}

.footcontent {
    background-color: #f5f7f9;
    line-height: 50px;
}

.pra {
    background-color: darkgray;

}


.headmainmes {
    background-color: #e3e8ee;
    /*background-color: #5cadff;*/
    /*color: rgba(255,255,255,.7)*/
}


li {
    list-style: none;
}

.el-empty {
    padding: 0;
}
</style>
