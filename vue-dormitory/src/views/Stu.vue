<script setup>
import {
    Edit,
    Delete
} from '@element-plus/icons-vue'
import { computed, onMounted, ref, watch } from 'vue'
import { ElMessage } from 'element-plus'
const stu = ref([
    {
        "stno": "2200311099",
        "stname": "my",
        "gender": 1,
        "grad": "2022",
        "isReside": 0,
        "major": "major",
        "hometown": "hometown",
        "birthday": "birthday"

    },
])

import { stuListService, stuAddService, updateAddService, deleteService, getRoommates,getIsReside,getNoReside } from '@/api/stu.js'
const getAllStu = async () => {
    let result = await stuListService();
    console.log(result.data)
    stu.value = result.data;
}
getAllStu();

const hasReside =async () => {
    let result = await getIsReside();
    console.log(result.data)
    stu.value = result.data;
}
const noReside =async () => {
    let result = await getNoReside();
    console.log(result.data)
    stu.value = result.data;
}


import { Plus } from '@element-plus/icons-vue'
//控制抽屉是否显示
const visibleDrawer = ref(false)
//添加表单数据模型 stno, stname, gender, grad, major, hometown, birthday,isReside
const stuModel = ref({
    stno: '',
    stname: '',
    gender: '',
    grad: '',
    major: '',
    hometown: '',
    birthday: '',
})

const title = ref('')
const setNull = () => {
    stuModel.value.stno = '',
        stuModel.value.stname = '',
        stuModel.value.gender = '',
        stuModel.value.grad = '',
        stuModel.value.major = '',
        stuModel.value.hometown = '',
        stuModel.value.birthday = '',
        visibleDrawer.value = false;
}

const showDialog = (row) => {
    title.value = '修改',
        visibleDrawer.value = true
    stuModel.value.stno = row.stno,
        stuModel.value.stname = row.stname,
        stuModel.value.gender = row.gender,
        stuModel.value.grad = row.grad,
        stuModel.value.major = row.major,
        stuModel.value.hometown = row.hometown,
        stuModel.value.birthday = row.birthday

}

const roommates = ref();

const addStu = async () => {
    let result = await stuAddService(stuModel.value);
    console.log(result.data)
    console.log("add...")
    getAllStu();
    setNull();

}

const updateStu = async () => {
    let result = await updateAddService(stuModel.value);
    console.log("update...")
    getAllStu();
    setNull();

}

const stuDelete = async (row) => {
    let result = await deleteService(row.stno)
    console.log(result.data)
    ElMessage.success(result.msg ? result.msg : '成功')
    getAllStu();
}

const enterRow = async (row, culumn, cell, event) => {
    try {
        const { data } = await getRoommates(row);
        // console.log(data)
        if(data==0){
            // Vue.set(roommates, 'value', {});
        }
        else if (data.length != 0) {
            data.unshift({ "stname": `宿舍号:${data[0].did} ${data[0].rid} ` });
            roommates.value = data;
            box.style.display = "block"
            box.style.position = "fixed";
        }

    } catch {
        console.log("error");
    }

}
const leaveRow = async(row, culumn, cell, event) => {
    try{
        box.style.display = "none";
    }catch{
        console.log("error");
    }
}


document.addEventListener('DOMContentLoaded', function () {
    watch(stu, (newValue, oldValue) => {
        let rowList = document.querySelectorAll('.rowList');
        rowList.forEach(list => {
            list.onmousemove = (e) => {
                list.style.zIndex = "0";
                let x = e.clientX;
                let y = e.clientY;
                box.style.top = y + 15 + "px";
                box.style.left = x + 15 + "px";
            }
        });
    })
})

watch(roommates, (newValue, oldValue) => {
        let rowList = document.querySelectorAll('.rowList');
        rowList.forEach(list => {
            list.onmousemove = (e) => {
                list.style.zIndex = "0";
                let x = e.clientX;
                let y = e.clientY;
                box.style.top = y + 15 + "px";
                box.style.left = x + 15 + "px";
            }
        });
    },{deep:true})



import { StuInfo} from '@/stores/stu.js'
import {useRouter} from 'vue-router'
const router =useRouter()
const showRoom=(row)=>{
    let StuStore = StuInfo()
    StuStore.setStno(row.stno)
    StuStore.setGender(row.gender)
    StuStore.setStname(row.stname)
    console.log(StuStore.stno)
    console.log(StuStore.gender)
    console.log(StuStore.stname)
    router.push('/stuRoomShowVue')
}




</script>

<template>
    <el-card class="page-container">
        <template #header>
            <div class="header">
                <span>学生表</span>
                <div class="extra">
                    <el-button type="primary" @click="hasReside">已入住</el-button>
                    <el-button type="primary" @click="noReside">未已入住</el-button>
                    <el-button type="primary" @click="visibleDrawer = true, title = '添加'">添加</el-button>
                </div>
            </div>
        </template>
        <el-table :data="stu" style="width: 100%" @cell-mouse-enter="enterRow" @cell-mouse-leave="leaveRow"
            row-class-name="rowList">
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="学号" prop="stno"></el-table-column>
            <el-table-column label="姓名" prop="stname"></el-table-column>
            <el-table-column label="姓别" prop="gender">
                <template #default="{ row }">
                    {{ row.gender===1?"男":"女" }}
                </template>
            </el-table-column>
            <el-table-column label="年级" prop="grad"></el-table-column>
            <el-table-column label="是否入住" prop="isReside">
                <template #default="{ row }">
                    {{ row.isReside===1?"是":"否" }}
                </template>
            </el-table-column>
            <el-table-column label="操作" width="200">
                <template #default="{ row }">
                    <el-button :icon="Edit" circle plain type="button" @click="showRoom(row)"></el-button>
                    <el-button :icon="Edit" circle plain type="primary" @click="showDialog(row)"></el-button>
                    <el-button :icon="Delete" circle plain type="danger" @click="stuDelete(row)"></el-button>
                </template>
            </el-table-column>
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>



        <!-- 抽屉 -->
        <el-drawer v-model="visibleDrawer" :title="title" direction="rtl" size="50%">
            <!-- 添加文章表单 -->
            <el-form :model="stuModel" label-width="100px">
                <el-form-item label="学号">
                    <el-input v-model="stuModel.stno" placeholder="请输入学号"></el-input>
                </el-form-item>

                <el-form-item label="姓名">
                    <el-input v-model="stuModel.stname" placeholder="请输入姓名"></el-input>
                </el-form-item>

                <el-form-item label="性别">
                    <el-input v-model="stuModel.gender" placeholder="请输入性别"></el-input>
                </el-form-item>

                <el-form-item label="年级">
                    <el-input v-model="stuModel.grad" placeholder="请输入年级"></el-input>
                </el-form-item>

                <el-form-item label="专业">
                    <el-input v-model="stuModel.major" placeholder="请输入专业"></el-input>
                </el-form-item>

                <el-form-item label="来着">
                    <el-input v-model="stuModel.hometown" placeholder="请输入来着"></el-input>
                </el-form-item>
                <el-form-item label="生日">
                    <el-input v-model="stuModel.birthday" placeholder="请输入生日"></el-input>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" @click="title === '添加' ? addStu() : updateStu()">确定</el-button>
                    <el-button @click="setNull()">取消</el-button>
                </el-form-item>
            </el-form>
        </el-drawer>
    </el-card>
    <div id="box" >
        <ul>
            <li v-for="roommate in roommates">
                {{ roommate.sid }} {{ roommate.stname }}
            </li>
        </ul>
    </div>
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

.detail {
    width: 100px;
    height: 100px;
    position: fixed;
    background-color: red;
    top: 0;
    left: 0;
    z-index: 9999999999999;
    display: none;
}

#box {
    background-color: white;
    box-shadow: 0 0 9px rgba(0, 0, 0, 0.2);
    width: 150px;
    border-radius: 5px;
    position: fixed;
    z-index: 1000;
    display: none;
}

#box ul {
    margin: 0;
    padding: 10px;
    list-style-type: none;
}

#box ul li {
    text-align: left;
}

#box ul li+li {
    font-size: 12px;
}
</style>