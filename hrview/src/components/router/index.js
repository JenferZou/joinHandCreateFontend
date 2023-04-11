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
import ActiveSearch from "@/components/views/StudentView/ActiveSearch";
import CertificateSearch from "@/components/views/StudentView/CertificateSearch";
import StudentResumePreview from "@/components/views/StudentView/StudentResumePreview";
import StudentMessage from "@/components/views/StudentView/StudentMessage";
import projectSearch from "@/components/views/StudentView/projectSearch";
import myProjectSearch from "@/components/views/StudentView/myProjectSearch";

import ProjectManger from "@/components/views/admin/projectManger";

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
            name: 'StudentIndex',
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
            path: '/student/myProjectSearch',
            name: 'myProjectSearch',
            component: myProjectSearch
        },
        {
            path: '/student/ActiveSearch',
            name: 'ActiveSearch',
            component: ActiveSearch
        },
        {
            path: '/student/CertificateSearch',
            name: 'CertificateSearch',
            component: CertificateSearch
        },
        {
            path: '/student/StudentResumePreview',
            name: 'StudentResumePreview',
            component: StudentResumePreview
        },
        {
            path: '/student/StudentMessage',
            name: 'StudentMessage',
            component: StudentMessage
        },
        {
            path: '/student/projectSearch',
            name: 'projectSearch',
            component: projectSearch
        },
        {
            path: '/adsac',
            name: 'forbid',
            component: ForbidIndex,
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
// router.beforeEach((to, from, next) => {
//     let role = window.sessionStorage.getItem("role")
//     let i = to.path.indexOf('/') + 1
//     let j = to.path.substring(1).indexOf('/') === -1 ? to.path.substring(1).length : to.path.substring(1).indexOf('/')
//     j += 1
//     if (to.path === '/') {
//         //window.sessionStorage.removeItem('Token')
//         //window.sessionStorage.removeItem('role')
//         next()
//     } else {
//         let user = window.sessionStorage.getItem('Token')
//         if (!user) {
//             next({
//                 path: '/'
//             })
//         } else if (to.path === '/adsac') {
//             next()
//         } else {
//             if (role === '管理员' && to.path.substring(i, j) !== 'admin') {
//                 next({
//                     path: '/adsac'
//                 })
//             } else if (role === '用户' && to.path.substring(i, j) !== 'student') {
//                 next({
//                     path: '/adsac'
//                 })
//             } else {
//                 next()
//             }
//         }
//     }
// });
