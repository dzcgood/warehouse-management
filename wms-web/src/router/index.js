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