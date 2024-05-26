//导入request.js请求工具
import request from '@/utils/request.js'

//调用注册接口的函数
export const adminRegisterService =(registerData)=>{
    //借助UrlSearcheParams完成传递
    const params =new URLSearchParams()
    for(let key in registerData){
        params.append(key,registerData[key])
    }

    return request.post('/login',params)
}

export const adminLoginService =(LoginData)=>{
    //借助UrlSearcheParams完成传递
    const params =new URLSearchParams()
    for(let key in LoginData){
        params.append(key,LoginData[key])
    }

    return request.post('/login',params)
}