import Vue from 'vue'
import Router from 'vue-router'
import AdminIndex from "@/components/index/AdminIndex";
import StudentInformation from "@/components/views/StudentInformation";
import SystemManage from "@/components/views/SystemManage";


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
                    path: '/index1',
                    name: 'index1',
                    component:SystemManage,
                    meta: {
                        title: 'admin'
                    }
                }
            ]
        }
    ]
})
export default router
const VueRouterPush = Router.prototype.push
Router.prototype.push = function push (to) {
    return VueRouterPush.call(this, to).catch(err => err)
}
