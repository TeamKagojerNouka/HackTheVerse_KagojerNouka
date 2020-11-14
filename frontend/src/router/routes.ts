import { RouteConfig } from "vue-router";

const routes: Array<RouteConfig> = [
    {
        path: "/",
        name: "home",
        component: () => import(/* webpackChunkName: "home" */ "@/views/Home.vue")
    },
    {
        path: "/qrcode",
        name: "qrcode",
        component: () => import(/* webpackChunkName: "qrcode" */ "@/views/QRcode.vue")
    },
    {
        path: "/dashboard",
        name: "dashboard",
        component: () => import(/* webpackChunkName: "qrcode" */ "@/views/Dashboard.vue")
    },
    {
        path: "/profile",
        name: "profile",
        component: () => import(/* webpackChunkName: "qrcode" */ "@/views/Profile.vue")
    },
    {
        path: "/delivery-service",
        name: "delivery",
        component: () => import(/* webpackChunkName: "qrcode" */ "@/views/QRcode.vue")
    },
]

export default routes