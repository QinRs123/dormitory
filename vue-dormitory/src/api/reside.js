import request from '@/utils/request.js'

//导入@/stores/token.js
import { useTokenStore } from '../stores/token'

export const resideListService = ()=>{

    const tokenStore = useTokenStore()
    //通过请求头Authorization携带token
    return request.get('/reside/getAll')
}


export const resideAddService = (ResideModel)=>{
    return request.post('/reside/add',ResideModel)
}

export const resideUpdateService = (ResideModel)=>{
    return request.post('/reside/update',ResideModel)
}
export const resideChangeService = (ResideModel)=>{
    return request.post('/reside/change',ResideModel)
}

export const resideDeleteService = (id)=>{
    return request.get('/reside/delete'+'/'+id)
}

export const resideShowBoysService = ()=>{
    return request.get('/reside/getBoys')
}

export const resideShowGirlsService = ()=>{
    return request.get('/reside/getGirls')
}

export const resideByRidService = (id,did)=>{
    return request.get('/reside/getByRid'+'/'+id+'/'+did)
}

export const resideBySidService = (sid)=>{
    return request.get('/reside/getBySid'+'/'+sid)
}