<script setup>
import {
    Edit,
    Delete
} from '@element-plus/icons-vue'
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
const dorm = ref([
    {
        "id": 1,
        "fnum": 4,
        'location':'a区',
        "mid": "1191",
       
    }
])

import { dormListService,dormAddService,dormUpdateService,deleteService} from '@/api/dorm.js'
const getAllDorm = async () => {
    let result = await dormListService();
    console.log(result.data)
    dorm.value = result.data;
}
getAllDorm();


import { dormInfo} from '@/stores/dorm.js'
import {useRouter} from 'vue-router'
const router =useRouter()
const showDorm=(row)=>{
    let dormStore = dormInfo()
    dormStore.setDorm(row.id)
    console.log(dormStore.dorm)
    router.push('/DormShowVue')
}

//控制添加分类弹窗
const dialogVisible = ref(false)
//添加分类数据模型
const dormdeModel = ref({
    id: '',
    fnum: '',
    location: '',
    mid: ''
})

const title =ref('')
const setNull=()=>{
    dormdeModel.value.id='',
    dormdeModel.value.fnum='',
    dormdeModel.value.location='',
    dormdeModel.value.mid='',
    dialogVisible.value=false;
}

const showDialog=(row)=>{
    title.value='修改',
    dialogVisible.value=true
    dormdeModel.value.id=row.id,
    dormdeModel.value.fnum=row.fnum,
    dormdeModel.value.location=row.location,
    dormdeModel.value.mid=row.mid
}


const addDorm=async()=>{
    let result = await dormAddService(dormdeModel.value);
    console.log(result.data)
    console.log("add...")
    getAllDorm();
    setNull();
}

const updateDorm= async()=>{
    let result = await dormUpdateService(dormdeModel.value);
    console.log("update...")
    getAllDorm();
    setNull();
    
}

const dormDelete=async(row)=>{
    let result = await deleteService(row.id)
    console.log(result.data)
    ElMessage.success(result.msg ? result.msg:'成功')
    getAllDorm();
}
</script>



<template>
    <el-card class="page-container">

        <template #header>
            <div class="header">
                <span>宿楼表</span>
                <div class="extra">
                    <el-button type="primary" @click="dialogVisible=true,title='添加'">添加</el-button>
                </div>
            </div>
        </template>

        <el-table :data="dorm" style="width: 100%">  
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="楼号" prop="id"></el-table-column>
            <el-table-column label="楼层数" prop="fnum"></el-table-column>
            <el-table-column label="地理位置" prop="location"></el-table-column>
            <el-table-column label="管理员号" prop="mid"></el-table-column>
            <el-table-column label="操作" width="200">
                <template #default="{ row }">
                    <el-button :icon="Edit" circle plain type="button"  @click="showDorm(row)"></el-button>
                    <el-button :icon="Edit" circle plain type="primary"  @click="showDialog(row)"></el-button>
                    <el-button :icon="Delete" circle plain type="danger" @click="dormDelete(row)"></el-button>
                </template>
            </el-table-column>
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>
            <!-- 添加分类弹窗 id, fnum, location, mid-->
            <el-dialog v-model="dialogVisible" :title="title" width="30%">
                <el-form :model="dormdeModel" :rules="rules" label-width="100px" style="padding-right: 30px">
                    
                    <el-form-item label="楼层数" prop="fnum">
                        <el-input v-model="dormdeModel.fnum" minlength="1" maxlength="10"></el-input>
                    </el-form-item>

                    <el-form-item label="位置" prop="location">
                        <el-input v-model="dormdeModel.location" minlength="1" maxlength="15"></el-input>
                    </el-form-item>
                    <el-form-item label="宿管" prop="mid">
                        <el-input v-model="dormdeModel.mid" minlength="1" maxlength="15"></el-input>
                    </el-form-item>
                    
                </el-form>
                <template #footer>
                    <span class="dialog-footer">
                        <el-button @click="setNull()">取消</el-button>
                        <el-button type="primary" @click="title==='添加'?addDorm():updateDorm()"> 确认  </el-button>
                    </span>
                </template>
            </el-dialog>
    </el-card>
</template>

<style lang="scss" scoped>
.page-container {
    min-height: 100%;
    box-sizing: border-box;

    .header {
        display: flex;
        align-items: center;
        justify-content: space-between;
    }
}
</style>