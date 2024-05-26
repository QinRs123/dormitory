import request from '@/utils/request.js'

export const stuListService = ()=>{
    return request.get('/stu/getAll')
}

export const stuAddService = (stuModel)=>{
    return request.post('/stu/add',stuModel)
}

export const updateAddService = (stuModel)=>{
    return request.post('/stu/update',stuModel)
}

export const deleteService = (id)=>{
    return request.get('/stu/delete'+'/'+id)
}

export const getStu = (search)=>{

    return request.post("/stu/getStu",search);
}
export const getRoommates = (row)=>{
    return request.post('/stu/getRoommates',row)
}