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
        component: ()=> import('../components/WebIndex')
    }
]

const router = new VueRouter({
    mode: 'history',
    routes
})

export default router;