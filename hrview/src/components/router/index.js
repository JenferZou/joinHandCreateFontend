import Vue from 'vue'
import Router from 'vue-router'
import AdminContent from "@/components/views/AdminContent";
import AdminIndex from "@/components/index/AdminIndex";


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
                    path: '/',
                    name: 'index',
                    components: {
                        adminContent:AdminContent
                    },
                    meta: {
                        title: 'admin'
                    }
                }
            ]
        }
    ]
})
export default router
