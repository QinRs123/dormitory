<script setup>
import {
    Edit,
    Delete
} from '@element-plus/icons-vue'
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
import { getStu } from '@/api/stu';
import { getnotFull } from '@/api/room';
const reside = ref([
    {
        "id": 3,
        "sid": "美食",
        'stname': 'hh',
        "rid": "my",
        "type": "noful",
        "did" : 1

    },
    {
        "id": 4,
        "sid": "娱乐",
        'stname': 'hh',
        "rid": "yl",
        "type": "noful",
        "did" : 1
    },
])
const snoList = ref();
const rnoList = ref();

//获取所有文章分类数据
import { resideListService, resideAddService, resideUpdateService, resideDeleteService ,resideByRidService} from '@/api/reside.js'
import { roomInfo} from '@/stores/room.js'
import {useRouter} from 'vue-router'
const router =useRouter()
const roomStore = roomInfo()
const goback= ()=>{
    roomStore.removeRoom()
    roomStore.removeDid()
    console.log(roomStore.room)
    router.push('/room')
}

const getAllReside = async () => {
    let result = await resideByRidService(roomStore.room,roomStore.roomDid);
    console.log(roomStore.room)
    console.log(roomStore.roomDid)
    console.log(result.data)
    reside.value = result.data;
}
getAllReside();

//控制添加分类弹窗
const dialogVisible = ref(false)
const dialogVisible1 = ref(false)
//添加分类数据模型
const ResideModel = ref({
    sid: '',
    rid: ''
})

const setNull = () => {
    ResideModel.value.sid = '',
        ResideModel.value.rid = ''
    dialogVisible.value = false
}


const addReside = async () => {
    ResideModel.value.rid=roomStore.room
    ResideModel.value.did=roomStore.roomDid
    let result = await resideAddService(ResideModel.value)
    console.log(result.data)
    ElMessage.success(result.msg ? result.msg : '成功')
    getAllReside();
    setNull();
    dialogVisible.value = false
}


const title = ref('')



const showDialog = (row) => {
    dialogVisible.value = true;
    title.value = '修改';
    ResideModel.value.sid = row.sid
    ResideModel.value.rid = row.rid
    ResideModel.value.id = row.id
}

const updateReside = async (row) => {
    let result = await resideUpdateService(ResideModel.value)
    console.log(result.data)
    ElMessage.success(result.msg ? result.msg : '成功')
    getAllReside();
    setNull();
    dialogVisible.value = false
}

const deleteResdie = async (row) => {
    let result = await resideDeleteService(row.sid)
    console.log(result.data)
    ElMessage.success(result.msg ? result.msg : '成功')
    getAllReside();
}

let timer1 = null;
const timeout1 = () => {
    clearTimeout(timer1);
    timer1 = setTimeout(getStuList, 300);
}

const getStuList = async () => {
    const search = {};
    search.isReside = 0;
    search.stno = ResideModel.value.sid;
    try {
        const { data } = await getStu(search);

        snoList.value = data;
    } catch {
        console.log("error")
    }
}

let timer2 = null;
const timeout2 = () => {
    clearTimeout(timer2);
    timer2 = setTimeout(getRoomList, 300);
}
const getRoomList=async()=>{
    const search={};
    search.id=ResideModel.value.rid;
    try{
        const {data}=await getnotFull(search);
        rnoList.value=data;
        console.log(data);
    }catch{
        console.log("error");
    }
}

const hisid = () => {
    const timeout = setTimeout("sidList.style.display = 'none';", 100);
}
const showsid = () => {
    sidList.style.display = 'block';
}
const hirid = () => {
    const timeout = setTimeout("ridList.style.display = 'none';", 100);
}
const showrid = () => {
    ridList.style.display = 'block';
}
const choose = (sno) => {
    ResideModel.value.sid = sno;
}
const chooserid=(rno)=>{
    ResideModel.value.rid = rno;
}



</script>



<template>
    <el-card class="page-container">

        <template #header>
            <div class="header">
                <span>宿舍号{{ roomStore.roomDid  }} {{roomStore.room  }}</span>
                <div class="extra">
                    <el-button type="button"
                        @click="goback">返回</el-button>
                    <el-button type="primary"
                        @click="dialogVisible = true, dialogVisible1 = true, title = '添加'">添加</el-button>
                    </div>
            </div>
        </template>

        <el-table :data="reside" style="width: 100%">
            <el-table-column label="序号" width="100" type="index"> </el-table-column>
            <el-table-column label="学号" prop="sid"></el-table-column>
            <el-table-column label="学生姓名" prop="stname"></el-table-column>
            <el-table-column label="宿舍号" prop="rid"></el-table-column>
            <el-table-column label="楼号" prop="did"></el-table-column>
            <el-table-column label="类型" prop="type">
                <template #default="{ row }">
                    {{ row.type===1?"男寝":"女寝" }}
                </template>
            </el-table-column>
            <el-table-column label="宿舍楼号" prop="did"></el-table-column>
            <el-table-column label="操作" width="100">
                <template #default="{ row }">
                    <!-- <el-button :icon="Edit" circle plain type="primary" @click="showDialog(row)"></el-button> -->
                    <el-button :icon="Delete" circle plain type="danger" @click="deleteResdie(row)"></el-button>
                </template>
            </el-table-column>
            <template #empty>
                <el-empty description="没有数据" />
            </template>
        </el-table>

        <!-- 添加分类弹窗 -->
        <el-dialog v-model="dialogVisible" :title="title" width="30%">
            <el-form :model="ResideModel" :rules="rules" label-width="100px" style="padding-right: 30px">
                <el-form-item calss="inputBox" label="学生学号" prop="sid">
                    <el-input @blur="hisid()" @focus="showsid()" @input="timeout1()" v-model="ResideModel.sid"
                        minlength="1" maxlength="10"></el-input>
                    <ul id="sidList" class="list">
                        <li v-for="sno in snoList" @click="choose(sno)">
                            {{ sno }}
                        </li>
                    </ul>
                </el-form-item>
                <!-- <el-form-item calss="inputBox" label="宿舍号" prop="rid">
                    <el-input @blur="hirid()" @focus="showrid()" @input="timeout2()" v-model="ResideModel.rid" minlength="1"
                        maxlength="15"></el-input>
                    <ul id="ridList" class="list" style="display: none;">
                        <li v-for="rno in rnoList" @click="chooserid(rno)">
                            {{ rno }}
                        </li>
                    </ul>
                </el-form-item> -->
            </el-form>
            <template #footer>
                <span class="dialog-footer">
                    <el-button @click="setNull()">取消</el-button>
                    <el-button type="primary" @click="title === '添加' ? addReside() : updateReside()"> 确认 </el-button>
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

.inputBox {
    position: relative;
}

.list {
    position: absolute;
    padding: 0;
    width: 100%;
    max-height: 200px;
    text-decoration: none;
    list-style-type: none;
    background-color: rgb(234, 234, 234);
    top: 60%;
    z-index: 10;
    // border: 1px solid gray;
    border-radius: 5px;
    overflow-y: scroll;
    // display: none;
}

.list li {
    padding-left: 5px;
}

.list li:hover {
    background-color: rgb(203, 203, 203);
    cursor: default;
}

</style>