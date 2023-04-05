import Vue from 'vue'
import Router from 'vue-router'
import AdminIndex from "@/components/index/AdminIndex";
import StudentInformation from "@/components/views/admin/StudentInformation";
import StudentIndex from "@/components/index/StudentIndex";
import StudentMessageForm from "@/components/views/StudentView/StudentMessageForm"
import InformationManagement from "@/components/views/admin/InformationManagement";

import StudentResume from "@/components/views/StudentView/StudentResume"
import UserManagement from "@/components/views/admin/UserManagement";
import ActiveManagement from "@/components/views/admin/ActiveManagement";
import LoginIndex from "@/components/index/LoginIndex";
import ForbidIndex from "@/components/views/ForbidIndex";
import ProjectManger from "@/components/views/admin/projectManger";

import TeacherIndex from "@/components/index/TeacherIndex.vue";
import tStudentInformation from "@/components/views/teacher/tStudentInformation";
import tActiveManagement from "@/components/views/teacher/tActiveManagement";
// import tStudentSearch from "@/components/views/teacher/tStudentSearch"
import tUserManagement from "@/components/views/teacher/tUserManagement"
import tInformationManagement from "@/components/views/teacher/tInformationManagement";
import tProjectManager from "@/components/views/teacher/tProjectManger"
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
                        title: 'admin'
                    }
                },
                {
                    path: '/admin/contest',
                    name: 'contest',
                    component: InformationManagement,
                    meta: {
                        title: 'admin'
                    }
                },
                {
                    path: '/admin/userManagement',
                    name: 'userManagement',
                    component: UserManagement,
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
                }
            ]
        },
        {
            path: '/student',
            name: 'student',
            component: StudentIndex,
        },
        {
            path: '/student/StudentMessageForm',
            name: 'StudentMessageForm',
            component: StudentMessageForm
        },
        {
            path: '/student/StudentResume',
            name: 'StudentResume',
            component: StudentResume
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
                    name: 'index',
                    component: tStudentInformation,
                    meta: {
                        title: 'teacher'
                    }
                },
                {
                    path: '/teacher/contest',
                    name: 'contest',
                    component: tInformationManagement,
                    meta: {
                        title: 'teacher'
                    }
                },
                {
                    path: '/teacher/userManagement',
                    name: 'userManagement',
                    component: tUserManagement,
                },
                {
                    path: '/teacher/active',
                    name: 'active',
                    component: tActiveManagement,
                },
                {
                    path: '/teacher/project',
                    name: 'project',
                    component: tProjectManager,
                }
            ]
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
            } else if (role === '用户' && to.path.substring(i, j) !== 'student') {
                next({
                    path: '/adsac'
                })
            } else {
                next()
            }
        }
    }
});