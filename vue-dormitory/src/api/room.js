import request from '@/utils/request.js'

export const roomListService = ()=>{
    return request.get('/room/getAll')
}

export const roomAddService = (roomModel)=>{
    return request.post('/room/add',roomModel)
}

export const roomUpdateService = (roomModel)=>{
    return request.post('/room/update',roomModel)
}

export const deleteService = (id)=>{
    return request.get('/room/delete'+'/'+id)
}

export const getnotFull = (search)=>{
    return request.post('/room/getnotFull',search)
}

export const getByDidService = (id)=>{
    return request.get('/room/getByDid'+'/'+id)
}
export const getByGenderService = (gender)=>{
    return request.get('/room/getByGender'+'/'+gender)
}
