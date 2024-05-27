//导入vue-router
import { createRouter, createWebHistory } from 'vue-router'
//导入组件
import LoginVue from '@/views/Login.vue'
import LayoutVue from '@/views/Layout.vue'
import MasterVue from '@/views/Master.vue'

import dormitoryVue from '@/views/dormitory.vue'
import RoomVue from '@/views/Room.vue'
import StuVue from '@/views/Stu.vue'
import ResideVue from '@/views/Reside.vue'
import RoomShowVue from '@/views/show/RoomShow.vue'
import DormShowVue from '@/views/show/dormShow.vue'


//定义路由关系
const routes = [
    { path: '/', component: LoginVue },
    { path: '/admin', component: LayoutVue ,redirect: '/reside',children:[
        {path:'/master', component :MasterVue},
        {path:'/dormitory', component: dormitoryVue },
        {path: '/room', component: RoomVue },
        {path: '/stu', component: StuVue },
        { path: '/reside', component: ResideVue },
        { path: '/RoomShowVue', component: RoomShowVue },
        { path: '/DormShowVue', component: DormShowVue },
    ]  },
    // { path: '/dormitory', component: dormitoryVue },
    // { path: '/room', component: RoomVue },
    // { path: '/stu', component: StuVue },
    // { path: '/reside', component: resideVue },
]

//创建路由器
const router = createRouter({
    history: createWebHistory(),
    routes: routes
});

export default router