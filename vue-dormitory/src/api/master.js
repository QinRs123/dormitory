import request from '@/utils/request.js'

export const masterListService = ()=>{
    return request.get('/master/getAll')
}

export const masterAddService = (masterModel)=>{
    return request.post('/master/add',masterModel)
}

export const masterUpdateService = (roomModel)=>{
    return request.post('/master/update',roomModel)
}

export const deleteService = (id)=>{
    return request.get('/master/delete'+'/'+id)
}