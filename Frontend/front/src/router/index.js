

import {createRouter, createWebHistory} from 'vue-router/dist/vue-router.esm-bundler'
import Test from '../components/Test.vue'
import MainPage from "@/components/MainPage.vue";
import Donate from "@/components/Donate.vue";
import FAQ from "@/components/FAQ.vue";
import AboutUs from "@/components/AboutUs.vue";
import SignUp from "@/components/SignUp.vue";
import Login from "@/components/Login.vue";
import Profile from "@/components/site-content/Profile.vue";
const routes = [
    {
        path: '/',
        redirect: '/main'
    },
    {
      path: '/main',
      component: MainPage
    },
    {
        path: '/donate',
        component: Donate
    },
    {
        path: '/faq',
        component: FAQ
    },
    {
        path: '/aboutus',
        component: AboutUs
    },
    {
        path: '/signup',
        component: SignUp
    },
    {
        path: '/login',
        component: Login
    },
    {
        path: '/profile',
        component: Profile
    },
    {path: '/:pathMatch(.*)', component: Test}
]

export default () => createRouter({
    history: createWebHistory(),
    routes: routes
})