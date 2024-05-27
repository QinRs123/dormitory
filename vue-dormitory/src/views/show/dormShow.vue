<script setup>
import {
    Edit,
    Delete
} from '@element-plus/icons-vue'
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
const room = ref([
    {
        "id": "1-101",
        "num": 4,
        "cnum": 0,
        "did": 1,
        "type":"随机",
    },
])

import { roomListService,roomAddService,roomUpdateService,deleteService,getByDidService} from '@/api/room.js'




import { dormInfo} from '@/stores/dorm.js'
import {useRouter} from 'vue-router'
const router =useRouter()
const dormStore = dormInfo()
const goback= ()=>{
    dormStore.removeDorm()
    console.log(dormStore.dorm)
    router.push('/dormitory')
}

const getAllRoom = async () => {
    let result = await getByDidService(dormStore.dorm);
    console.log(result.data)
    room.value = result.data;
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

const title =ref('')
const setNull=()=>{
    roomModel.value.id='',
    roomModel.value.num='',
    // roomModel.value.cnum='',
    roomModel.value.did='',
    roomModel.value.type='',
    visibleDrawer.value=false;
}

const showDialog=(row)=>{
    title.value='修改',
    visibleDrawer.value=true
    roomModel.value.id=row.id,
    roomModel.value.num=row.num,
    // roomModel.value.cnum=row.cnum,
    roomModel.value.did=row.did,
    roomModel.value.type=row.type
}


const addStu=async()=>{
    let result = await roomAddService(roomModel.value);
    console.log(result.data)
    console.log("add...")
    getAllRoom();
    setNull();
}

const updateStu= async()=>{
    let result = await roomUpdateService(roomModel.value);
    console.log("update...")
    getAllRoom();
    setNull();
    
}

const stuDelete=async(row)=>{
    let result = await deleteService(row.id)
    console.log(result.data)
    ElMessage.success(result.msg ? result.msg:'成功')
    getAllRoom();
}


</script>

<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span> 第{{ dormStore.dorm }}号宿舍楼 </span>
                <div class="extra">
                    <el-button type="button"
                        @click="goback">返回</el-button>
                    <el-button type="primary" @click="visibleDrawer = true,title='添加'">添加</el-button>
                </div>
            </div>
        </template>
        <el-table :data="room" style="width: 100%">
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="宿舍号" prop="id"></el-table-column>
            <el-table-column label="床位数" prop="num"></el-table-column>
            <el-table-column label="已入住人数" prop="cnum"></el-table-column>
            <el-table-column label="宿舍楼号" prop="did"></el-table-column>
            <el-table-column label="类型" prop="type">
                <template #default="{ row }">
                    {{ row.type===1?"男寝":"女寝" }}
                </template>
            </el-table-column>
            <el-table-column label="操作" width="200">
                <template #default="{ row  }">
                    <el-button :icon="Edit" circle plain type="button" @click="showRoom(row)" ></el-button>
                    <el-button :icon="Edit" circle plain type="primary" @click="showDialog(row)" ></el-button>
                    <el-button :icon="Delete" circle plain type="danger" @click= "stuDelete(row)"></el-button>
                </template>
            </el-table-column>
            
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>



        <!-- 抽屉 -->
        <el-drawer v-model="visibleDrawer" :title="title" direction="rtl" size="50%">
            <!-- 添加文章表单 -->
            <el-form :model="roomModel" label-width="100px" >
                <el-form-item label="宿舍号" >
                    <el-input v-model="roomModel.id" placeholder="宿舍号"></el-input>
                </el-form-item>

                <el-form-item label="床位数" >
                    <el-input v-model="roomModel.num" placeholder="床位数"></el-input>
                </el-form-item>

                <el-form-item label="宿舍楼号" >
                    <el-input v-model="roomModel.did" placeholder="宿舍楼号"></el-input>
                </el-form-item>
                
                <el-form-item label="类型" >
                    <el-input v-model="roomModel.type" placeholder="类型"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="title==='添加'? addStu():updateStu()" >确定</el-button>
                    <el-button @click="setNull()">取消</el-button>
                </el-form-item>
            </el-form>
        </el-drawer>
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