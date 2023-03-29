//用户名匹配
export function nameRule(rule, value, callback){
    // 请输入5-15位的昵称
    let reg = /(^[a-zA-Z0-9]{5,15})$/
    if(value === ''){
        callback(new Error('请输入用户名'))
    }else if (!reg.test(value)){
        callback(new Error('请输入5-15位用户名'))
    }else{
        callback()
    }
}

//密码正则匹配
export function passwordRule(rule, value, callback){
    //6-20位密码需要包含大小写字母和数字以及特殊符号
    let pass = /^\S*(?=\S{6,20})(?=\S*\d)(?=\S*[A-Z])(?=\S*[a-z])(?=\S*[!@#$%^&*? ])\S*$/
        if(value === ""){
            callback(new Error('请输入密码'))
        }else if (!pass.test(value)){
            callback(new Error('输入6-20位密码，且需要包含大小写字母、数字以及特殊符号'))
        }else{
            callback()
        }
}