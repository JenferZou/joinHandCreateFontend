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
Vue.use(Router)

let router = new Router({
    mode: 'history',
    scrollBehavior: () => ({ y: 0 }),
    routes: [
        {
            path: '/',
            component: AdminIndex,
            children: [
                {
                    path: '/index',
                    name: 'index',
                    component: StudentInformation,
                    meta: {
                        title: 'admin'
                    }
                },
                {
                    path: '/contest',
                    name: 'contest',
                    component:InformationManagement,
                    meta: {
                        title: 'admin'
                    }
                },
                {
                    path:'/userManagement',
                    name:'userManagement',
                    component:UserManagement,
                },
                {
                    path:'/active',
                    name:'active',
                    component:ActiveManagement,
                }
            ],
            meta: {
                title: 'admin'
            }
        },
        {
            path: '/student',
            name: 'student',
            component: StudentIndex,
        },
        {
            path: '/StudentMessageForm',
            name: 'StudentMessageForm',
            component: StudentMessageForm
        },
        {
            path: '/StudentResume',
            name: 'StudentResume',
            component: StudentResume
        }

    ]
})
export default router
const VueRouterPush = Router.prototype.push
//防止重复跳转同一路由报错
Router.prototype.push = function push (to) {
    return VueRouterPush.call(this, to).catch(err => err)
}
