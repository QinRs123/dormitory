<script setup>
import {
    Edit,
    Delete
} from '@element-plus/icons-vue'
import { ref } from 'vue'
import { ElMessage } from 'element-plus'

// id, name, phone, gender
const master = ref([     
    {
        "id": '9527',
        "name": '1212',
        'phone':'1100',
        "gender": 1
    }
])


//控制添加分类弹窗
const dialogVisible = ref(false)
//添加分类数据模型// id, name, phone, gender
const masterModel = ref({
    id: '',
    name: '',
    phone: '',
    gender: ''
})
const title=ref('')


import { masterListService,masterAddService,masterUpdateService,deleteService} from '@/api/master.js'
const getAllMaster = async () => {
    let result = await masterListService();
    console.log(result.data)
    master.value = result.data;
}
getAllMaster();

const setNull=()=>{
    masterModel.value.id='',
    masterModel.value.name='',
    masterModel.value.phone='',
    masterModel.value.gender='',
    dialogVisible.value=false;
}

const showDialog=(row)=>{
    title.value='修改',
    dialogVisible.value=true
    masterModel.value.id=row.id,
    masterModel.value.name=row.name,
    masterModel.value.phone=row.phone,
    masterModel.value.gender=row.gender
}


const addMaster=async()=>{
    let result = await masterAddService(masterModel.value);
    console.log(result.data)
    console.log("add...")
    getAllMaster();
    setNull();
}

const updateMaster= async()=>{
    let result = await masterUpdateService(masterModel.value);
    console.log("update...")
    getAllMaster();
    setNull();
    
}

const deleteMaster= async(row)=>{
    let result = await deleteService(row.id)
    console.log(result.data)
    ElMessage.success(result.msg ? result.msg:'成功')
    getAllMaster();
}
 
</script>



<template>
    <el-card class="page-container">

        <template #header>
            <div class="header">
                <span>宿管表</span>
                <div class="extra">
                    <el-button type="primary" @click="dialogVisible=true,title='添加'">添加</el-button>
                </div>
            </div>
        </template>

        <el-table :data="master" style="width: 100%"> 
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="宿管编号" prop="id"></el-table-column>
            <el-table-column label="宿管姓名" prop="name"></el-table-column>
            <el-table-column label="宿管电话" prop="phone"></el-table-column>
            <el-table-column label="宿管性别" prop="gender"></el-table-column>
            <el-table-column label="操作" width="100">
                <template #default="{ row }">
                    <el-button :icon="Edit" circle plain type="primary"  @click="showDialog(row)"></el-button>
                    <el-button :icon="Delete" circle plain type="danger" @click="deleteMaster(row)"></el-button>
                </template>
            </el-table-column>
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>


         <!-- 添加分类弹窗 id, fnum, location, mid-->
         <el-dialog v-model="dialogVisible" :title="title" width="30%">
                <el-form :model="masterModel" :rules="rules" label-width="100px" style="padding-right: 30px">
                    
                    <el-form-item label="宿管编号" prop="id">
                        <el-input v-model="masterModel.id" minlength="1" maxlength="10"></el-input>
                    </el-form-item>

                    <el-form-item label="宿管姓名" prop="name">
                        <el-input v-model="masterModel.name" minlength="1" maxlength="15"></el-input>
                    </el-form-item>
                    <el-form-item label="宿管电话" prop="phone">
                        <el-input v-model="masterModel.phone" minlength="1" maxlength="15"></el-input>
                    </el-form-item>

                    <el-form-item label="宿管性别" prop="gender">
                        <el-input v-model="masterModel.gender" minlength="1" maxlength="15"></el-input>
                    </el-form-item>
                    
                </el-form>
                <template #footer>
                    <span class="dialog-footer">
                        <el-button @click="setNull()">取消</el-button>
                        <el-button type="primary" @click="title==='添加'?addMaster():updateMaster()"> 确认  </el-button>
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