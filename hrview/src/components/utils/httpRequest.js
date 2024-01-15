import axios from 'axios'
import qs from 'qs' // 字符串处理
import merge from 'lodash/merge' // 合并对象工具
const http = axios.create({
    timeout: 1000 * 30,
    withCredentials: true, // 当前请求为跨域类型时是否在请求中协带cookie
})

http.interceptors.request.use(
    function (config) {
        config.headers['Authorization'] = 'Bearer '+window.sessionStorage.getItem('Token')/* 定义全局token */
        // console.log('请求头:',config.headers['Authorization']);
        return config
    },
    function (error) {
        return Promise.reject(error)
    }
)

// 响应拦截器
http.interceptors.response.use(function (response){
    return response;
},function (error) {
    // 服务器出错，做数据判断
    console.log("错误信息:",error);
    error.response.data.message = error.response.data.message || '请求失败';
    return Promise.reject(error);
})

/**
 * 请求地址处理
 * @param {*} actionName action方法名称
 */
http.adornUrl = (actionName) => {
  return '/api'+actionName
}

/**
 * get 请求参数处理
 * @param params
 * @param openDefaultParams
 * @returns {*}
 */
http.adornParams = (params = {}, openDefaultParams = false) => {
    const defaluts = {
        't': new Date().getTime()
    }

    return openDefaultParams ? merge(defaluts, params) : params
}
/**
 * post请求参数处理
 * @param data
 * @param openDefaultdata
 * @param contentType
 * @returns {string}
 */
http.adornData = (data = {}, openDefaultdata = true, contentType = 'json') => {
    const defaults = {
        't': new Date().getTime()
    }
    data = openDefaultdata ? merge(defaults, data) : data
    return contentType === 'json' ? JSON.stringify(data) : qs.stringify(data)
}


export default http;
