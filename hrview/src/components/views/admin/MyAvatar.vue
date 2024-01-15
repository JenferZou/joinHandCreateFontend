<template>
  <el-dropdown  size="small" @command="handleCommand">
    <el-avatar  icon="el-icon-user-solid"></el-avatar>
    <el-dropdown-menu slot="dropdown">
<!--      <el-dropdown-item>个人主页</el-dropdown-item>-->
      <el-dropdown-item command="logout">退出登录</el-dropdown-item>
    </el-dropdown-menu>
  </el-dropdown>
</template>

<script>

export default {
  name: "MyAvatar",
  methods:{
    handleCommand(command){
      if(command==='logout') {
          sessionStorage.removeItem("Token")
          sessionStorage.removeItem("role")
          this.$router.push({
              path: '/'
          })
/*        this.$http({
          url: this.$http.adornUrl('/logout'),
          method: 'post',
        }).then(({data}) => {
          if (data && data.status === 200) {
            /!* console.log(data)*!/
            sessionStorage.removeItem("Token")
            sessionStorage.removeItem("role")
            this.$message.success(data.msg)
            this.$router.push({
              path: '/'
            })
          } else {
            this.$message.error(data.msg)
          }
        }).catch((res) => {
          this.$message.error(res.response.data.message)
        })*/
      }
    },
  }
}
</script>

<style scoped>

</style>
