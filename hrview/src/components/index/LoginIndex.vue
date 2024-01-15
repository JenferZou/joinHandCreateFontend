<template>
  <div class="login" id="building">

    <el-card class="box-card" style=" background-color:transparent;backdrop-filter: blur(4px);">
      <div slot="header" class="clearfix" style="text-align: center;">
        <h1>携手创项目平台</h1>
      </div>
      <el-form label-width="80px" :model="form" :rules="rules" ref="form">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username" placeholder="请输入用户名" clearable></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="form.password" placeholder="请输入用户名" show-password
                    clearable></el-input>
        </el-form-item>
        <el-form-item style="margin-left:75px">
          <el-button type="primary" @click="login()" style="width:100px" :loading="isLoading">登录</el-button>
          <el-button style="width:100px" @click="reset">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import {nameRule, passwordRule} from '../utils/vaildate.js'

export default {
  data() {
    return {
      form: {
        username: "",
        password: "",
      },
      rules: {
        username: [{validator: nameRule, trigger: 'blur'}],
        password: [{validator: passwordRule, trigger: 'blur'}]
      },
      isLoading: false,
    };
  },
  methods: {
    login() {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          this.isLoading = true;
          this.$http({
            url: this.$http.adornUrl('/login'),
            method: 'post',
            data: this.form,
            headers: {
              'UserToken': window.sessionStorage.getItem('Token'),
            }
          }).then(({data}) => {
            if (data && data.errorCode == "200") {
              /* console.log(data)*/
              sessionStorage.setItem("Token", data.data.token)
              this.$message.success("登录成功")
              if (data.data.title === '管理员') {
                sessionStorage.setItem("role", data.data.title)
                this.$router.push({
                  name: 'index'
                })
              } else if (data.data.title === '学生') {
                sessionStorage.setItem("role", data.data.title)
                this.$router.push({
                  name: 'StudentIndex'
                })
              } else if (data.data.title === '教师') {
                sessionStorage.setItem("role", data.data.title)
                this.$router.push({
                  name: 'tactive'
                })
              }
            } else {
              console.log(data);
              this.$message.error(data.message);
            }
          }).catch(err => {
            console.log(err);
            this.$message.error("请求失败了");
          }).finally(()=>{
            this.isLoading = false;
          })
        }
      })
    },
    reset() {
      this.form.username = '';
      this.form.password = '';
    },
    onKeyUp(e) {
      if (e.key == 'Enter') {
        this.login();
      }
    }
  },
  mounted() {
    console.log("设置监听");
    document.addEventListener('keyup', this.onKeyUp);
  },
  beforeDestroy(){
    console.log("被销毁");
    document.removeEventListener("keyup",this.onKeyUp);
  }
}
</script>
<style lang="scss">
.login {
  width: 100%;
  height: 100%;
  position: absolute;

  .box-card {
    width: 500px;
    margin: 200px auto;

    .box-card__header {
      font-size: 45px;
    }

    .el-button {
      width: 100%;

    }
  }
}

#building {
  background: url("../assets/BackImg02.jpg");
  width: 100%; //大小设置为100%
  height: 100%; //大小设置为100%
  position: fixed;
  background-size: 100% 100%;
}
</style>
