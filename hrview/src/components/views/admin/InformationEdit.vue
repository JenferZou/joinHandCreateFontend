<template>
  <el-dialog title="添加" :visible.sync="dialogTableVisible">

  </el-dialog>

</template>

<script>
export default {
  name: "InformationEdit",
  data(){
    return{
      dialogTableVisible:false
    }
  },
  methods:{
    init(){
      this.dialogTableVisible = true
    }
  }
}
</script>

<style scoped>

</style>
