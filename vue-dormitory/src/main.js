import './assets/main.scss'

import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import router from '@/router'
import App from './App.vue'
import { createPinia } from 'pinia'
//导入持久化插件
import {createPersistedState} from'pinia-persistedstate-plugin'
const persist = createPersistedState()
//pinia使用持久化插件


const pinia = createPinia()
pinia.use(persist)
const app=createApp(App)
app.use(pinia)
app.use(pinia)
app.use(router)
app.use(ElementPlus)
app.mount('#app')
