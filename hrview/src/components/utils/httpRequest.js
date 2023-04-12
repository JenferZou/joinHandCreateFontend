import axios from 'axios'
import qs from 'qs' // 字符串处理
import merge from 'lodash/merge' // 合并对象工具
const http = axios.create({
    timeout: 1000 * 30,
    withCredentials: true, // 当前请求为跨域类型时是否在请求中协带cookie

    /* headers: {
      'Content-Type': 'application/json;charset=utf-8'
    } */
})
/*axios.interceptors.request.use((config) => {
    // config 是 axios 配置对象
    // 获取token
    let token = window.sessionStorage.getItem('Token');
    console.log(token)
    // 添加token
    //Bearer为token类型，根据自己的类型更改
    token && (config.headers.Authorization = 'Bearer ' + JSON.parse(token));
    return config;
}, (error) => {
    // 请求出错
    return Promise.reject(error);
});*/

/**
 * 请求地址处理
 * @param {*} actionName action方法名称
 */
http.adornUrl = (actionName) => {

    // 非生产环境 && 开启代理, 接口前缀统一使用[/proxyApi/]前缀做代理拦截!
    //return (process.env.NODE_ENV !== 'production' && process.env.OPEN_PROXY ? '/proxyApi/' : window.SITE_CONFIG.baseUrl) + actionName
    return 'http://localhost:8081'+actionName
    //return 'http://120.78.205.174:8081'+actionName
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
    /* console.log(params) */
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

export default http
