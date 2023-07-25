import VueRouter from "vue-router";

const routes = [
    {
        path: '/',
        name: 'login',
        component: ()=> import('../components/LoginPage')
    },
    {
        path: '/WebIndex',
        name: 'index',
        component: ()=> import('../components/WebIndex'),
        children: [
            {
                path: '/HomePage',
                name: 'home',
                meta: {
                    title: '首页'
                },
                component: ()=> import('../components/HomePage')
            },
            {
                path: '/AdminManage',
                name: 'admin',
                meta: {
                    title: '管理员管理'
                },
                component: ()=> import('../components/admin/AdminManage')
            },
            {
                path: '/UserManage',
                name: 'user',
                meta: {
                    title: '用户管理'
                },
                component: ()=> import('../components/user/UserManage')
            },
        ]
    }
]

const router = new VueRouter({
    mode: 'history',
    routes
})

const VueRouterPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(to) {
    return VueRouterPush.call(this, to).catch(err => err)
}

export default router;