//用户名匹配
export function nameRule(rule, value, callback){
    // 请输入5-15位的昵称
    let reg = /(^[a-zA-Z0-9]{6,15})$/
    if(value === ''){
        callback(new Error('请输入用户名'))
    }else if (!reg.test(value)){
        callback(new Error('请输入6-15位用户名'))
    }else{
        callback()
    }
}

//密码正则匹配
export function passwordRule(rule, value, callback){
        if(value === ""){
            callback(new Error('请输入密码'))
        }else{
            callback()
        }
}