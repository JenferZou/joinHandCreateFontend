<template>
  <div class="login">

    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>携手创项目平台</span>
        <el-button style="float: right; padding: 3px 0" type="text"></el-button>
      </div>
      <el-form label-width="80px" :model="form" :rules="rules" ref="form">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username"  placeholder="请输入用户名" clearable></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="form.password" placeholder="请输入用户名" show-password clearable></el-input>
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
                  this.$http({
                    url: this.$http.adornUrl('/login'),
                    method: 'post',
                    data: this.form,//qs.stringify(this.form),
                    headers:{
                      'UserToken':window.sessionStorage.getItem('Token'),
                    }
                  }).then(({data}) => {
                    if (data&&data.errorCode==="200") {
                      /* console.log(data)*/
                      sessionStorage.setItem("Token",data.data.token)
                      this.$message.success("登录成功")
                        if(data.data.title==='管理员'){
                            sessionStorage.setItem("role",data.data.title)
                            this.$router.push({
                                name:'adminIndex'
                            })
                        }else if(data.data.title==='学生'){
                            sessionStorage.setItem("role",data.data.title)
                            this.$router.push({
                                name:'StudentIndex'
                            })
                        }else if(data.data.title==='教师'){
                            sessionStorage.setItem("role",data.data.title)
                            this.$router.push({
                                name:'teacherIndex'
                            })
                        }
                    }else{
                      console.log(data);
                      this.$message.error(data.message);
                    }
                  })
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
    //background-color: #409EFF;
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
