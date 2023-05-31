

import {createRouter, createWebHistory} from 'vue-router/dist/vue-router.esm-bundler'
import Test from '../components/Test.vue'
import MainPage from "@/components/MainPage.vue";
import Donate from "@/components/Donate.vue";
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



    {path: '/:pathMatch(.*)', component: Test}
]

export default () => createRouter({
    history: createWebHistory(),
    routes: routes
})