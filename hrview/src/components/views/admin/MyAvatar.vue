<template>
  <el-dropdown  size="small" @command="handleCommand">
    <el-avatar  icon="el-icon-user-solid"></el-avatar>
    <el-dropdown-menu slot="dropdown">
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
      }
    },
  }
}
</script>

<style scoped>

</style>
