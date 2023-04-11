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
          <el-input type="password" v-model="form.password" show-password></el-input>
        </el-form-item>
        <el-button type="primary" @click="login('form')">登录</el-button>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import{nameRule, passwordRule}from '../utils/vaildate.js'
import qs from "qs";
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
                  //登录请求采用form-data 或 x-www-form-urlencoded 形式传入参数才行，以下采用form-data
                  this.$http({
                    url: this.$http.adornUrl('/login'),
                    method: 'post',
                    data: qs.stringify(this.form),
                    headers:{
                      'UserToken':window.sessionStorage.getItem('Token'),
                    }
                  }).then(({data}) => {
                    if (data&&data.status===200) {
                      /* console.log(data)*/
                      sessionStorage.setItem("Token",data.obj.token)
                      this.$message.success(data.msg)
                      data.obj.Role.forEach((item)=>{
                        if(item.title==='管理员'){
                          sessionStorage.setItem("role",item.title)
                          this.$router.push({
                            name:'adminIndex'
                          })
                        }else if(item.title==='用户'){
                          sessionStorage.setItem("role",item.title)
                          this.$router.push({
                            name:'StudentIndex'
                          })
                        }else if(item.title==='教师'){
                          sessionStorage.setItem("role",item.title)
                          this.$router.push({
                            name:'teacher'
                          })
                        }
                      })
                    }else{
                      this.$message.error(data.msg)
                    }
                  }).catch((res) => {
                    this.$message.error(res.response.data.message)
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
