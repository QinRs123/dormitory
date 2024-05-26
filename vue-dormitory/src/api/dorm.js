import request from '@/utils/request.js'

export const dormListService = ()=>{
    return request.get('/dorm/getAll')
}

export const dormAddService = (roomModel)=>{
    return request.post('/dorm/add',roomModel)
}

export const dormUpdateService = (roomModel)=>{
    return request.post('/dorm/update',roomModel)
}

export const deleteService = (id)=>{
    return request.get('/dorm/delete'+'/'+id)
}