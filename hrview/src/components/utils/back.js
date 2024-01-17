import httpRequest from "./httpRequest"
export const userHandler= {
    //获取图片上传token
    getUpToken: function (){
        return new Promise((resolve, reject)=>{
            httpRequest({
                url: '/api/admin/upLoadToken',
                method: 'get',
                headers: {
                    'Content-Type': 'application/json', // 设置内容类型为JSON
                }
            }).then(response =>{
                resolve(response.data)
            }).catch((error)=>{
                reject(error)
            })
        })
    },
}
export default {
    userHandler
}