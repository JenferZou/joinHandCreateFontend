<template>


  <div class="layout">
    <i-menu mode="horizontal" theme="dark" active-name="1"  on-select="ed">
      <div class="layout-logo"></div>
      <div class="layout-nav" >
        <i-menu-item name="1">
          <i-icon type="ios-navigate"></i-icon>
          导航一
        </i-menu-item>
        <i-menu-item name="2">
          <i-icon type="ios-keypad"></i-icon>
          导航二
        </i-menu-item>
        <i-menu-item name="3">
          <i-icon type="ios-analytics"></i-icon>
          导航三
        </i-menu-item>
        <i-menu-item name="4">
          <i-icon type="ios-paper"></i-icon>
          导航四
        </i-menu-item>
      </div>
    </i-menu>
    <i-menu mode="horizontal" active-name="1" @on-select="editMes">
      <div class="layout-assistant">
        <i-menu-item name="StudentIndex">主页</i-menu-item>
        <i-menu-item name="StudentMessage">个人信息</i-menu-item>
        <i-menu-item name="StudentResumePreview">个人简历</i-menu-item>
        <i-menu-item name="5">消息通知</i-menu-item>
      </div>
    </i-menu>
    <div class="layout-content">
      <i-row>
        <i-col span="5">
          <i-menu active-name="1-2" width="auto" :open-names="['1']" @on-select="goWhere">
            <i-submenu name="1">
              <template slot="title">
                <i-icon type="ios-navigate"></i-icon>
                个人信息
              </template>
              <i-menu-item name="StudentMessageForm">修改个人信息</i-menu-item>
              <i-menu-item name="StudentResume">填充个人简历</i-menu-item>

            </i-submenu>
            <i-submenu name="2">
              <template slot="title">
                <i-icon type="ios-keypad"></i-icon>
                项目管理
              </template>
              <i-menu-item name="projectSearch">项目大厅</i-menu-item>
              <i-menu-item name="myProjectSearch">项目申请</i-menu-item>
            </i-submenu>
            <i-submenu name="3">
              <template slot="title">
                <i-icon type="ios-analytics"></i-icon>
                考证与实践活动信息
              </template>
              <i-menu-item name="CertificateSearch">考证俱乐部</i-menu-item>
              <i-menu-item name="ActiveSearch">实践活动查询</i-menu-item>
            </i-submenu>
          </i-menu>
        </i-col>
        <i-col span="19">
          <div class="layout-content-main">
            <div class="in-layout-content">



              <div>
                <i-row class="search">
                  <i-col :span="12">
                    <i-row>
                      <i-col :span="16">
                        <el-input @keyup.enter.native="search" size="small" v-model="title" placeholder="请输入标题" clearable prefix-icon="el-icon-search">
                        </el-input>
                      </i-col>
                      <i-col :span="3">
                        <el-button type="primary" size="small" @click="search">
                          <i-icon type="search" size="small"></i-icon>
                          搜索
                        </el-button>
                      </i-col>
                    </i-row>
                  </i-col>

                </i-row>

                <el-table
                    ref="singleTable"
                    :data="activeData"
                    highlight-current-row
                    style="width: 100%">
                  <el-table-column
                      type="index"
                      width="50">
                  </el-table-column>
                  <el-table-column
                      property="title"
                      label="活动名称"
                      width="120">
                  </el-table-column>
                  <el-table-column
                      property="activeTime"
                      label="活动时间"
                      width="120">
                  </el-table-column>
                  <el-table-column
                      property="activeDuration"
                      label="活动时长(h)"
                      width="120">
                  </el-table-column>
                  <el-table-column
                      property="type"
                      label="活动类型"
                      width="120">
                  </el-table-column>
                  <el-table-column
                      property="points"
                      label="可获积分"
                      width="120">
                  </el-table-column>
                  <el-table-column
                      property="remark"
                      label="备注"
                      width="120">
                  </el-table-column>
                  <el-table-column
                      prop="content"
                      label="内容"
                      show-overflow-tooltip>
                    <!--        <template v-slot="scope">
                              <div v-html='scope.row.content'></div>
                            </template>-->
                  </el-table-column>

                </el-table>
                <el-pagination
                    class="page"
                    @current-change="change"
                    background
                    layout="prev, pager, next"
                    :page-count="pageNum">
                </el-pagination>


              </div>



            </div></div>
        </i-col>
      </i-row>
    </div>
    <div class="layout-copy">
      2011-2016 &copy; TalkingData
    </div>
  </div>



</template>

<script>

export default {
  name: "ActiveSearch",
  data() {
    return {

      multiDeleteVisible:false,
      currentPage: 1,
      pageSize: 8,
      pageNum: 1,
      activeData:[],
      title:'',
      deleteData:'',
      active:{
        title:'',
        type:'',
        activeTime:'',
        activeDuration:'',
        content:'',
        points:'',
        remark:'',
      },
      rules: {
        pass: [
          {  trigger: 'blur' }
        ]
      },
      dialogVisible: false
    };
  },
  methods: {

    cancle(){
      this.dialogVisible = false
      this.active=this.reset(this.active)
    },



    change(){

    },
    search(){

    },
    display(){
      this.dialogVisible=true
    },
    // 失去焦点
    onEditorBlur(editor) {
      console.log(editor)
    },
    // 获得焦点
    onEditorFocus(editor) {
      console.log(editor)
    },
    // 开始
    onEditorReady(editor) {
      console.log(editor)
    },
    // 值发生变化
    onEditorChange(editor) {
      this.content = editor.html;
      console.log(editor);
    },
    getActive(){
      let params = {
        page: this.currentPage,
        limit: this.pageSize
      }
      this.$http({
        url: this.$http.adornUrl('/student/getActive'),
        method: 'get',
        params: this.$http.adornParams(params),
        headers:{
          'UserToken':window.sessionStorage.getItem('Token'),
        }
      }).then(({data}) => {
        if (data) {
          this.pageNum = data.totalPages
          this.activeData = data.content
        }
      }).catch(() => {
        console.log('出错啦！！！！')
      })
    },


    goWhere(name){
      this.$router.push({name})
    },

    editMes(name){
      // this.$router.push({
      //   path: '/StudentMessageForm'
      // })
      this.$router.push({name})
    },
    ediRes(name){
      this.$router.push({name})
    }

  },
  mounted() {
    this.getActive();
  }
}






</script>

<style scoped lang="scss">
.search {
  padding-top: 10px;
  padding-bottom: 10px;
}

.el-dialog-div{
  max-height: 80vh;//如果高度过高，可用max-height
  overflow: auto;
}
.item {
  height: 500px;
}
.editor {
  height: 400px;
}
.dialog-footer{
  text-align: right;
}
.page{
  text-align: center;
}







.layout{
  border: 1px solid #d7dde4;
  background: #f5f7f9;
}
.layout-logo{
  width: 100px;
  height: 30px;
  border-radius: 3px;
  background: #5b6270;
  float: left;
  position: relative;
  top: 15px;
  left: 20px;
}
.layout-nav{
  width: 420px;
  margin: 0 auto;
}
.layout-assistant{
  width: 500px;
  margin: 0 auto;
  height: inherit;

}
.layout-breadcrumb{
  padding: 10px 15px 0;
}
.layout-content{
  min-height: 600px;
  margin: 15px;
  overflow: hidden;
  background: #fff;
  border-radius: 4px;
}
.layout-content-main{
  padding: 10px;
  background-color: #f5f7f9;
}
.layout-copy{
  text-align: center;
  padding: 10px 0 20px;
  color: #9ea7b4;
}
</style>
