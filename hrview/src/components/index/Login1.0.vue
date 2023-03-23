<template>
    <div class="login">

<el-card class="box-card">
    <div slot="header" class="clearfix">
        <span>人力资源管理系统</span>
        <el-button style="float: right; padding: 3px 0" type="text"></el-button>
    </div>
<el-form label-width="80px" 
        :model="form" 
        ref="form" 
        :rules="rules">
    <el-form-item label="用户名" prop="username">
        <el-input v-model="form.username"></el-input>
    </el-form-item>

    <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="form.password"></el-input>
    </el-form-item>

    
        <el-button type="primary" @click="login('form')">登录</el-button>
    
</el-form>

</el-card>


    </div>
</template>

<script>
import{nameRule, passwordRule}from '../utils/vaildate.js'
export default{
    data(){
        return{
            form:{
                username:"",
                password:"",
            },
            rules:{
                username:[{validator: nameRule, trigger:'blur'}],
                password:[{validator: passwordRule, trigger:'blur'}]
        }
        };
        
    },
    methods:{
        login(form){
            this.$refs[form].validate((valid) => {
                if(valid){
                    console.log(this.form)
                    // this.axios.post('https://rapserver.sunmi.com/app/mock/340/login',this.form)
                    // .then(res => {
                    //     console.log(res)
                    //     if(res.data.status === 200){
                    //         localStorage.setItem('username',res.data.username)
                    //         this.$message({message:res.data.message, type: 'success'})
                    //         this.$router.push('/home')
                    //     }
                    // })
                    // .catch(err =>{
                    //     console.error(err)
                    // });
                }else{
                    console.error(this.form)
                }
            })
        }
    }
}
</script>
<style lang="scss">
.login{
    width: 100%;
    height: 100%;
    position: absolute;
    background-color: #409EFF;
    .box-card{
        width: 450px;
        margin: 200px auto;
        .box-card__header{
            font-size: 45px;
        }
        .el-button{
            width: 100%;
            
        }
    }
}
</style>