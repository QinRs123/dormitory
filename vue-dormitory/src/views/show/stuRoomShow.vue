<script setup>
import {
    Edit,
    Delete
} from '@element-plus/icons-vue'
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
const room = ref([
    {
        "id": "",
        "num": '',
        "cnum": '',
        "did": '',
        "type":"",
    },
])

const reside = ref([
    {
        "sid": "美食",
        'stname': 'hh',
        "rid": "my",
        "type": "noful",
        "did" : 1
    }
])


import { resideBySidService,resideUpdateService,resideChangeService,resideDeleteService} from '@/api/reside.js'
import { getByGenderService} from '@/api/room.js'
import { StuInfo} from '@/stores/stu.js'
let StuStore = StuInfo()
const getAllRoom = async () => {
    console.log(StuStore.stno)
    console.log(StuStore.gender)
    console.log(StuStore.stname)


    
    try {
        let result = await resideBySidService(StuStore.stno);
        reside.value = result.data;
        ElMessage.success(result.msg ? result.msg : '成功')
    } catch (error) {
        
    }finally{
        let result1 =await getByGenderService(StuStore.gender)
        console.log(result1.data)
        room.value=result1.data;
    }
}
getAllRoom();


import {Plus} from '@element-plus/icons-vue'
//控制抽屉是否显示
const visibleDrawer = ref(false)
//添加表单数据模型 id, num, cnum, did, type
const roomModel = ref({
    id: '',
    num: '',
    cnum: '',
    did: '',
    type:'',
})

const ResideModel = ref({
    sid: '',
    rid: '',
    did:''
})
const title =ref('')
const choose=async(row)=>{
    console.log(reside.did)
    ResideModel.value.did=row.did
    ResideModel.value.rid=row.id
    ResideModel.value.sid=StuStore.stno

    let result = await resideChangeService(ResideModel.value)
    console.log(result.data)
    ElMessage.success(result.msg ? result.msg : '成功')
    getAllRoom();
}
const setNull = () => {
    reside.value.did = '',
    reside.value.rid = ''
}
const NoReside=async()=>{
    let result= await resideDeleteService(StuStore.stno)
    console.log(result.data)
    ElMessage.success(result.msg ? result.msg : '成功')
    setNull();
    getAllRoom();
}



import {useRouter} from 'vue-router'
const router =useRouter()
const back=()=>{
    StuStore.removeStname()
    StuStore.removeStno()
    StuStore.removeGender()
    router.push('/stu')
}






</script>

<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>{{ StuStore.stno }} {{StuStore.stname}} {{  }}--当前宿舍:{{reside.did }}{{ reside.rid }}</span>
               
                <div class="extra">
                    <el-button type="primary" @click="NoReside">不住了</el-button>
                </div>
                <div class="extra">
                    <el-button type="primary" @click="back">back</el-button>
                </div>
            </div>
        </template>
        <el-table :data="room" style="width: 100%">
            <el-table-column label="序号" width="100" type="index">
            </el-table-column>
            <el-table-column label="宿舍楼号" prop="did"></el-table-column>
            <el-table-column label="宿舍号" prop="id"></el-table-column>
            <el-table-column label="床位数" prop="num"></el-table-column>
            <el-table-column label="已入住人数" prop="cnum"></el-table-column>
            <el-table-column label="类型" prop="type">
                <template #default="{ row }">
                    {{ row.type===1?"男寝":"女寝" }}
                </template>
            </el-table-column>
            <el-table-column label="操作" width="100">
                <template #default="{ row  }">
                    <el-button :icon="Edit" circle plain type="button" @click="choose(row)" ></el-button>
                </template>
            </el-table-column>
            
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>
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

/* 抽屉样式 */
.avatar-uploader {
    :deep() {
        .avatar {
            width: 178px;
            height: 178px;
            display: block;
        }

        .el-upload {
            border: 1px dashed var(--el-border-color);
            border-radius: 6px;
            cursor: pointer;
            position: relative;
            overflow: hidden;
            transition: var(--el-transition-duration-fast);
        }

        .el-upload:hover {
            border-color: var(--el-color-primary);
        }

        .el-icon.avatar-uploader-icon {
            font-size: 28px;
            color: #8c939d;
            width: 178px;
            height: 178px;
            text-align: center;
        }
    }
}
.editor {
  width: 100%;
  :deep(.ql-editor) {
    min-height: 200px;
  }
}
</style>