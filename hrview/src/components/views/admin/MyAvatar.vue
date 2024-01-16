<template>
  <el-dropdown  size="small" @command="handleCommand">
    <el-avatar
      src="https://ts2.cn.mm.bing.net/th?id=OIP-C.0ym0bg0dFWwJCA1S01_PGQHaNK&w=187&h=333&c=8&rs=1&qlt=90&o=6&dpr=1.3&pid=3.1&rm=2"></el-avatar>
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

<style >
.el-avatar img{
    display: block;
    height: 100%;
    vertical-align: middle;
    width: 40px;
}
</style>
