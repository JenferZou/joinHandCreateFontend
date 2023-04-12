<template>
  <div>
      <i-row class="search">
        <i-col :span="10" offset="6">
          <el-input @keyup.enter.native="search" size="small" v-model="title" placeholder="默认展示部分操作员，请通过用户名搜索" clearable prefix-icon="el-icon-search">
          </el-input>
        </i-col>
        <i-col :span="2">
          <el-button type="primary" size="small" @click="search">
            <i-icon type="search" size="small"></i-icon>
            搜索
          </el-button>
        </i-col>
        <i-row type="flex" justify="end">
        <i-col :span="7">
          <el-button size="small" type="primary" @click="add">
            <i-icon type="plus-round" size="15"></i-icon>
            添加</el-button>
        </i-col>
          <el-dialog title="添加" :visible.sync="dialogVisible" :closeOnClickModal="false">
            <el-form :inline="true" :model="userData" ref="studentData" class="demo-form-inline" label-width="100px" size="small" >
              <i-row>
                <i-col :span="12">
                  <el-form-item label="工号" label-width="auto" prop="sno">
                    <el-input v-model.number="userData.gno" placeholder="工号" maxlength="12" style="width: 150px"></el-input>
                  </el-form-item>
                </i-col>
                <i-col :span="12">
                  <el-form-item label="姓名" label-width="auto" prop="sName">
                    <el-input v-model="userData.name" placeholder="姓名" size="small" style="width: 130px"></el-input>
                  </el-form-item>
                </i-col>
              </i-row>
              <i-row>
                <i-col :span="12">
                  <el-form-item label="电话" label-width="auto" prop="className">
                    <el-input style="width: 130px" v-model="userData.gPhone" placeholder="电话" size="small"></el-input>
                  </el-form-item>
                </i-col>
                <i-col :span="12">
                  <el-form-item label="角色" label-width="auto" >
                    <el-select size="small" v-model="userData.role" placeholder="角色" style="width: 100px">
                      <el-option label="老师" value="老师"></el-option>
                      <el-option label="管理员" value="管理员"></el-option>
                    </el-select>
                  </el-form-item>
                </i-col>
              </i-row>
              <i-row type="flex" justify="end">
                <i-col :span="3">
                  <el-form-item>
                    <el-button @click="cancel" size="small">取消</el-button>
                  </el-form-item>
                </i-col>
                <i-col :span="3">
                  <el-form-item>
                    <el-button type="primary" @click="modify" size="small">保存</el-button>
                  </el-form-item>
                </i-col>

              </i-row>
            </el-form>
          </el-dialog>
        </i-row>
      </i-row>
  <i-row :gutter="48" class="card" justify="space-around" type="flex">
    <i-col :span="6" v-for="item in user.slice(0,3)" :key="item.no" type="flex">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>{{ item.name }}</span>
          <el-button style="float: right;padding: 0px 0;color:red;" type="text" @click="del(item.no)">删除</el-button>
        </div>
        <div  class="text item">
          用户名:{{item.name}}
        </div>
        <div  class="text item">
          电话:

          {{item.phone}}
        </div>
        <div  class="text item">
          用户状态:
          禁用
          <el-switch @change="changeStatus"
              v-model="item.status"
              active-color="#13ce66"
              inactive-color="#ff4949"
              :active-value="1"
              :inactive-value="0">
          </el-switch>

          启用
        </div>
        <div  class="text item">
          用户角色:{{item.roles[0].title}}
        </div>
        <div  class="text item">
          备注:{{item.remark}}
        </div>
      </el-card>
    </i-col>
  </i-row>
    <i-row :gutter="48" class="card" justify="space-around" type="flex">
      <i-col :span="6" v-for="item in user.slice(3,6)" :key="item.no" type="flex">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>{{ item.name }}</span>
            <el-button style="float: right;padding: 0px 0;color:red;" type="text" @click="del(item.no)">删除</el-button>
          </div>
          <div  class="text item">
            用户名:{{item.name}}
          </div>
          <div  class="text item">
            电话:

            {{item.phone}}
          </div>
          <div  class="text item">
            用户状态:
            禁用
            <el-switch
                v-model="item.status"
                active-color="#13ce66"
                inactive-color="#ff4949"
                :active-value="1"
                :inactive-value="0">
            </el-switch>

            启用
          </div>
          <div  class="text item">
            用户角色:{{item.roles[0].title}}
          </div>
          <div  class="text item">
            备注:{{item.remark}}
          </div>
        </el-card>
      </i-col>
    </i-row>
    <el-dialog :visible.sync="multiDeleteVisible" title="提示" width="30%">
      <span>确定要删除吗</span>
      <span slot="footer">
          <el-button type="primary" @click="multiDelete" size="small">确 定</el-button>
          <el-button @click="multiDeleteVisible = false" size="small">取 消</el-button>
        </span>
    </el-dialog>
    <el-pagination
        class="page"
        @current-change="change"
        background
        layout="prev, pager, next"
        :page-count="pageNum">
    </el-pagination>
  </div>
</template>

<script>

export default {
  name: "UserManagement",
  data(){
    return{
      dialogVisible:false,
      multiDeleteVisible:false,
      currentPage: 1,
      title:'',
      pageSize: 6,
      pageNum: 1,
      user:'',
      id:'',
      userData:{
        gno:'',
        name:'',
        gPhone:'',
        role:''
      }
    }
  },
  methods:{
    modify(){
      this.$http({
        url: this.$http.adornUrl('/admin/addUser'),
        method: 'post',
        data: this.$http.adornData(this.userData),
        headers: {
          'UserToken':window.sessionStorage.getItem('Token'),
          'Content-Type': 'application/json',
          'charset': 'utf-8'
        }
      }).then(({data}) => {
        if (data && data.status === 200) {
          this.$message.success(data.msg)
        } else {
          this.$message.error(data.msg)
        }
      }).catch(() => {
        console.log('出错啦！！！！')
      })
      this.dialogVisible=false
      this.userData=''
    },
    cancel(){
      this.dialogVisible=false
    },
    add(){
      this.dialogVisible=true
    },
    del(id){
      this.multiDeleteVisible=true
      this.id=id
    },
    multiDelete(){
      let params={
        id:this.id
      }
      this.$http({
        url: this.$http.adornUrl('/admin/deleteUser'),
        method: 'post',
        data:this.$http.adornData(params),
        headers: {
          'UserToken':window.sessionStorage.getItem('Token'),
          'Content-Type': 'application/json',
          'charset': 'utf-8'
        }
      }).then(({data}) => {
        if (data&&data.status===200) {
          this.$message.success(data.msg)
          this.id=''
          this.multiDeleteVisible=false
        }else{
          this.$message.error(data.msg)
        }
      }).catch(() => {
        console.log('出错啦！！！！')
      })
    },
    changeStatus(){
      console.log(this.user)
    },
    change(page){
      this.currentPage=page
      this.getAllUser()
    },
    search(){

    },
    getAllUser(){
      let params = {
        page: this.currentPage,
        limit: this.pageSize
      }
      this.$http({
        url: this.$http.adornUrl('/admin/getAllUser'),
        method: 'get',
        params: this.$http.adornParams(params),
        headers: {
          'UserToken':window.sessionStorage.getItem('Token'),
        }
      }).then(({data}) => {
        if (data) {
          this.pageNum = data.totalPages
          this.user = data.content
        }
      }).catch(() => {
        console.log('出错啦！！！！')
      })
    },
  },
  created() {
    this.getAllUser()
  }
}
</script>

<style scoped>
.search{
  padding-top: 10px;
  text-align: center;
}
.card{
  padding-top: 10px;
}
.page{
  text-align: center;
  padding-top: 10px;
}
</style>
