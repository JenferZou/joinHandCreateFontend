import Vue from 'vue'
import Router from 'vue-router'
import AdminIndex from "@/components/index/AdminIndex";
import StudentInformation from "@/components/views/admin/StudentInformation";
import StudentIndex from "@/components/index/StudentIndex";
import StudentMessageForm from "@/components/views/StudentView/StudentMessageForm"


import TeacherMessageForm from "@/components/views/teacher/TeacherMessageForm.vue";
import StudentResume from "@/components/views/StudentView/StudentResume"
import UserManagement from "@/components/views/admin/UserManagement";
import ActiveManagement from "@/components/views/admin/ActiveManagement";
import LoginIndex from "@/components/index/LoginIndex";
import ForbidIndex from "@/components/views/ForbidIndex";
import ActiveSearch from "@/components/views/StudentView/ActiveSearch";
import CertificateSearch from "@/components/views/StudentView/CertificateSearch";
import StudentResumePreview from "@/components/views/StudentView/StudentResumePreview";
import StudentMessage from "@/components/views/StudentView/StudentMessage";
import projectSearch from "@/components/views/StudentView/projectSearch";
import myProjectSearch from "@/components/views/StudentView/myProjectSearch";

import ProjectManger from "@/components/views/admin/projectManger";

import TeacherIndex from "@/components/index/TeacherIndex.vue";
import tStudentInformation from "@/components/views/teacher/tStudentInformation";
import tActiveManagement from "@/components/views/teacher/tActiveManagement";
// import tStudentSearch from "@/components/views/teacher/tStudentSearch"
import tUserManagement from "@/components/views/teacher/tUserManagement"
import tInformationManagement from "@/components/views/teacher/tInformationManagement";
import tProjectManager from "@/components/views/teacher/tProjectManger"
import StudentDelieverResume from "@/components/views/teacher/StudentDelieverResume"
import myPublishProject from "@/components/views/teacher/myPublishProject"
import TeacherManager from "@/components/views/admin/TeacherManager.vue";
import StudentNotify from "@/components/views/StudentView/StudentNotify.vue";
import SystemAnalysis from "@/components/views/admin/SystemAnalysis.vue";
import ProjectDetail from "@/components/views/StudentView/ProjectDetail.vue";

Vue.use(Router)

let router = new Router({
    mode: 'history',
    scrollBehavior: () => ({y: 0}),
    routes: [{
        path: '/',
        component: LoginIndex,
    },
        {
            path: '/admin',
            name: 'adminIndex',
            component: AdminIndex,
            children: [
                {
                    path: '/admin/index',
                    name: 'index',
                    component: StudentInformation,
                    meta: {
                        title: '学生信息'
                    }
                },
                {
                    path: '/admin/teacher',
                    name: 'teacher',
                    component: TeacherManager,
                    meta: {
                        title: '老师信息'
                    }
                },
                {
                    path: '/admin/analysis',
                    name: 'analysis',
                    component: SystemAnalysis,
                    meta: {
                        title: '系统分析'
                    }
                },
                {
                    path: '/admin/userManagement',
                    name: 'userManagement',
                    component: UserManagement,
                    meta: {
                        title: '用户管理'
                    }
                },
                {
                    path: '/admin/active',
                    name: 'active',
                    component: ActiveManagement,
                },
                {
                    path: '/admin/project',
                    name: 'project',
                    component: ProjectManger,
                    meta: {
                        title: '项目管理'
                    }
                }
            ]
        },
        {
            path: '/student',
            name: 'StudentIndex',
            component: StudentIndex,
            meta: {
                title: '学生主页框架'
            }
        },
        {
            path: '/student/StudentMessageForm',
            name: 'StudentMessageForm',
            component: StudentMessageForm,
            meta: {
                title: '学生个人信息'
            }
        },
        {
            path: '/student/StudentResume',
            name: 'StudentResume',
            component: StudentResume,
            meta: {
                title: '学生个人简历编辑'
            }
        },
        {
            path: '/student/myProjectSearch',
            name: 'myProjectSearch',
            component: myProjectSearch,
            meta: {
                title: '学生项目申请结果'
            }
        },
        {
            path: '/student/ActiveSearch',
            name: 'ActiveSearch',
            component: ActiveSearch,
            meta: {
                title: '学生实践活动'
            }
        },
        {
            path: '/student/CertificateSearch',
            name: 'CertificateSearch',
            component: CertificateSearch,
            meta: {
                title: '考证信息'
            }
        },
        {
            path: '/student/StudentResumePreview',
            name: 'StudentResumePreview',
            component: StudentResumePreview,
            meta: {
                title: '建立预览'
            }
        },
        {
            path: '/student/StudentMessage',
            name: 'StudentMessage',
            component: StudentMessage,
            meta: {
                title: '学生个人信息预览'
            }
        },
        {
            path: '/student/projectSearch',
            name: 'projectSearch',
            component: projectSearch,
            meta: {
                title: '项目大厅'
            }
        },
        {
            path: '/student/notify',
            name: 'notify',
            component: StudentNotify,
            meta: {
                title: '消息通知'
            }
        },
        {
            path: '/student/detail/:id',
            name: 'detail',
            component: ProjectDetail,
            meta: {
                title: '项目详情'
            }
        },
        {
            path: '/adsac',
            name: 'forbid',
            component: ForbidIndex,
        },




        {
            path: '/teacher',
            name: 'teacherIndex',
            component: TeacherIndex,
            children: [
                {
                    path: '/teacher/index',
                    name: 'tindex',
                    component: tStudentInformation,
                    meta: {
                        title: 'teacher'
                    }
                },
                {
                    path: '/teacher/info',
                    name: 'tinfo',
                    component: TeacherMessageForm,
                    meta: {
                        title: '教师信息'
                    }
                },
                {
                    path: '/teacher/contest',
                    name: 'tcontest',
                    component: tInformationManagement,
                    meta: {
                        title: 'teacher'
                    }
                },
                {
                    path: '/teacher/userManagement',
                    name: 'tuserManagement',
                    component: tUserManagement,
                },
                {
                    path: '/teacher/active',
                    name: 'tactive',
                    meta: {
                        title: '项目发布'
                    },
                    component: tActiveManagement,
                },
                {
                    path: '/teacher/project',
                    name: 'tproject',
                    meta: {
                        title: '简历审批'
                    },
                    component: tProjectManager,
                },
                {
                    path: '/teacher/myPublishProject',
                    name: 'myPublishProject',
                    meta: {
                        title: '项目管理'
                    },
                    component: myPublishProject,
                },


            ]

        },
        {
            path: '/teacher/StudentDelieverResume',
            name: 'StudentDelieverResume',
            component: StudentDelieverResume,
        }

    ]
})
export default router
const VueRouterPush = Router.prototype.push
//防止重复跳转同一路由报错
Router.prototype.push = function push(to) {
    return VueRouterPush.call(this, to).catch(err => err)
}
// 挂载路由导航守卫
// to 将要访问的路径
// from 代表从哪个路径跳转而来
// next 是个函数，表示放行 next() 放行  next('/login') 强制跳转
/*router.beforeEach((to, from, next) => {
    let role = window.sessionStorage.getItem("role")
    let i = to.path.indexOf('/') + 1
    let j = to.path.substring(1).indexOf('/') === -1 ? to.path.substring(1).length : to.path.substring(1).indexOf('/')
    j += 1
    if (to.path === '/') {
        //window.sessionStorage.removeItem('Token')
        //window.sessionStorage.removeItem('role')
        next()
    } else {
        let user = window.sessionStorage.getItem('Token')
        if (!user) {
            next({
                path: '/'
            })
        } else if (to.path === '/adsac') {
            next()
        } else {
            if (role === '管理员' && to.path.substring(i, j) !== 'admin') {
                next({
                    path: '/adsac'
                })
            } else if (role === '学生' && to.path.substring(i, j) !== 'student') {
                next({
                    path: '/adsac'
                })
            } else {
                next()
            }
        }
    }
});*/

router.beforeEach((to, from, next) => {
    let role = window.sessionStorage.getItem("role")
    let i = to.path.indexOf('/') + 1
    let j = to.path.substring(1).indexOf('/') === -1 ? to.path.substring(1).length : to.path.substring(1).indexOf('/')
    j += 1
    if (to.path === '/') {
        //window.sessionStorage.removeItem('Token')
        //window.sessionStorage.removeItem('role')
        next()
    } else {
        let user = window.sessionStorage.getItem('Token')
        if (!user) {
            next({
                path: '/'
            })
        } else if (to.path === '/adsac') {
            next()
        } else {
            if (role === '管理员' && to.path.substring(i, j) !== 'admin') {
                next({
                    path: '/adsac'
                })
            } else if (role === '学生' && to.path.substring(i, j) !== 'student') {
                next({
                    path: '/adsac'
                })
            }else if(role === '教师' && to.path.substring(i, j) !== 'teacher'){
                next({
                    path: '/adsac'
                })
            } else {
                next()
            }
        }
    }
});

router.afterEach((to)=>{
    let title = (to.meta.title ? to.meta.title : '');
    document.title = title;
})
