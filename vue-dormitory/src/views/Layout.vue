<script setup>
import {
    Management,
    Promotion,
    UserFilled,
    User,
    Crop,
    EditPen,
    SwitchButton,
    CaretBottom
} from '@element-plus/icons-vue'

import avatar from '@/assets/default.png'

import { useTokenStore } from '@/stores/token.js'

//调用useTokenStore得到状态
const tokenStore = useTokenStore();
import {useRouter} from 'vue-router'
const router =useRouter()
const exit=()=>{
    tokenStore.removeToken();
    router.push('/')
}
</script>

<template>
    <el-container class="layout-container">
        <!-- 左侧菜单 -->
        <el-aside width="200px">
            <div class="el-aside__logo"></div>
            <el-menu active-text-color="#ffd04b" background-color="#232323"  text-color="#fff"
                router>
                <el-menu-item index="/master">
                    <el-icon>
                        <Management />
                    </el-icon>
                    <span>宿管管理</span>
                </el-menu-item>

                <el-menu-item  index="/dormitory">
                    <el-icon>
                        <Promotion />
                    </el-icon>
                    <span>宿舍楼</span>
                </el-menu-item>
                <el-menu-item index="/room">
                    <el-icon>
                        <Management />
                    </el-icon>
                    <span>宿舍</span>
                </el-menu-item>
                <el-menu-item index="/stu" >
                    <el-icon>
                        <Management />
                    </el-icon>
                    <span>学生</span>
                </el-menu-item>
                <el-menu-item index="/reside">
                    <el-icon>
                        <Management />
                    </el-icon>
                    <span>学生入住</span>
                </el-menu-item>
            </el-menu>
        </el-aside>
        <!-- 右侧主区域 -->
        <el-container>
            <!-- 头部区域 -->
            <el-header>
                <div></div>
                <el-dropdown placement="bottom-end">
                    <span class="el-dropdown__box">
                        <el-avatar :src="avatar" />
                        <el-icon>
                            <CaretBottom />
                        </el-icon>
                    </span>
                    <template #dropdown>
                        <el-dropdown-menu>
                            <el-dropdown-item command="profile" :icon="User">基本资料</el-dropdown-item>
                            <el-dropdown-item command="avatar" :icon="Crop">更换头像</el-dropdown-item>
                            <el-dropdown-item command="password" :icon="EditPen">重置密码</el-dropdown-item>
                            <el-dropdown-item command="logout" :icon="SwitchButton" @click="exit">退出登录</el-dropdown-item>
                        </el-dropdown-menu>
                    </template>
                </el-dropdown>
            </el-header>
            <!-- 中间区域 -->
            <el-main>
                <div style="width: 1000px; height: 570px;border: 1px solid white;">
                    <router-view></router-view>
                </div>
            </el-main>
        </el-container>
    </el-container>
</template>

<style lang="scss" scoped>
.layout-container {
    height: 100vh;

    .el-aside {
        background-color: #232323;

        &__logo {
            height: 120px;
            background: url('https://upload-test-qrs.oss-cn-hangzhou.aliyuncs.com/47387c05-f6d0-43ae-9164-634ab47fea93.jpg') no-repeat center / 120px auto;
        }

        .el-menu {
            border-right: none;
        }
    }

    .el-header {
        background-color: #fff;
        display: flex;
        align-items: center;
        justify-content: space-between;

        .el-dropdown__box {
            display: flex;
            align-items: center;

            .el-icon {
                color: #999;
                margin-left: 10px;
            }

            &:active,
            &:focus {
                outline: none;
            }
        }
    }

    .el-footer {
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 14px;
        color: #666;
    }
}
</style>