import {createRouter, createWebHistory} from 'vue-router/dist/vue-router.esm-bundler'
import Test from '../components/Test.vue'
import MainPage from "@/components/statics/MainPage.vue";
import Donate from "@/components/statics/Donate.vue";
import FAQ from "@/components/statics/FAQ.vue";
import AboutUs from "@/components/statics/AboutUs.vue";
import SignUp from "@/components/site-content/SignUp.vue";
import Login from "@/components/site-content/Login.vue";
import Profile from "@/components/site-content/Profile.vue";
import Daily from "@/components/site-content/Daily.vue";
import Habits from "@/components/site-content/Habits.vue";
import Verified from "@/components/site-content/Verified.vue";
import verified from "@/components/site-content/Verified.vue";

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
    {
        path: '/daily',
        component: Daily
    },
    {
        path: '/habits',
        component: Habits
    },
    {
        path: '/verify',
        // query:{verification: verified},
        component: Verified
    },
    {path: '/:pathMatch(.*)', component: Test}
]

export default () => createRouter({
    history: createWebHistory(),
    routes: routes
})