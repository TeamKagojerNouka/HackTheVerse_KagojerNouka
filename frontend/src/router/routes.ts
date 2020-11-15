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
        path: "/business-profile",
        name: "profile",
        component: () => import(/* webpackChunkName: "profile" */ "@/views/Profile.vue")
    },
    {
        path: "/delivery-services",
        name: "services",
        component: () => import(/* webpackChunkName: "services" */ "@/views/Services.vue")
    },
]

export default routes