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

export const resideDeleteService = (id)=>{
    return request.get('/reside/delete'+'/'+id)
}