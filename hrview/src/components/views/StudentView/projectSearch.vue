<template>

  <div class="layout">
    <i-menu mode="horizontal" theme="dark" active-name="1"  on-select="ed">
      <div class="layout-logo"></div>
      <h5 style="color: #fff;margin-left:520px">    携手创-学生端</h5>
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


<!--                <el-table-->
<!--                    border-->
<!--                    ref="multipleTable"-->
<!--                    :data="project"-->
<!--                    tooltip-effect="dark"-->
<!--                    style="width: 100%"-->
<!--                    @selection-change="handleSelectionChange">-->
<!--                  <el-table-column-->
<!--                      width="55">-->
<!--                  </el-table-column>-->
<!--                  <el-table-column-->
<!--                      prop="name"-->
<!--                      label="项目名称"-->
<!--                      width="120">-->
<!--                  </el-table-column>-->
<!--                  <el-table-column-->
<!--                      prop="mentor"-->
<!--                      label="指导老师"-->
<!--                      width="120">-->
<!--                  </el-table-column>-->

<!--                  <el-table-column-->
<!--                      prop="startTime"-->
<!--                      label="开始时间"-->
<!--                      width="120">-->
<!--                  </el-table-column>-->

<!--                  <el-table-column-->
<!--                      prop="expectedCompetition"-->
<!--                      label="作品预期要参加的比赛"-->
<!--                      show-overflow-tooltip-->
<!--                  >-->
<!--                  </el-table-column>-->

<!--                  <el-table-column-->
<!--                      prop="needMajor"-->
<!--                      label="学生专业需求"-->
<!--                      show-overflow-tooltip-->
<!--                  >-->
<!--                  </el-table-column>-->

<!--                  <el-table-column-->
<!--                      prop="content"-->
<!--                      label="内容"-->
<!--                      show-overflow-tooltip>-->
<!--                  </el-table-column>-->
<!--                  <el-table-column-->
<!--                      fixed="right"-->
<!--                      label="操作"-->
<!--                      width="100">-->
<!--                    <template v-slot="scope">-->
<!--                      <el-button type="text" size="small" @click="delieverResume(scope.row)">投递简历</el-button>-->
<!--                    </template>-->
<!--                  </el-table-column>-->
<!--                </el-table>-->
                <div class="card" >
                  <div style="display: flex;">
                    <!--                  文字部分-->
                    <div style="flex: 1">
                      <div style="font-size: 25px;font-weight: bolder">水果助农计划</div>
                      <div style="margin-top: 10px;font-weight: bold;font-size: 17px">计算机设计大赛</div>
                      <div style="font-size:10px;margin-top: 10px">
                        在古老的森林中，阳光透过茂密的树叶洒落在地面上，给人一种宁静而祥和的感觉。空气中弥漫着泥土的芬芳，鸟儿在枝头欢快地歌唱，仿佛在庆祝自然的美妙。
                        这里是一个与世隔绝的角落，没有城市的喧嚣，没有繁忙的车辆，只有大自然的声音和生命的存在。站在这片森林中，你可以感受到时间的缓慢流动，仿佛世界的喧嚣与纷扰都远离了这里。
                        身处其中，你会发现每一个细微的变化。风吹过树梢，树叶会轻轻摇曳，发出沙沙的声音。小溪在山间流淌，水流清澈见底，草木在水中倒影出美丽的景色。微风轻拂脸颊，带来一丝凉意，让人心旷神怡。
                        在这个宁静的环境中，你可以与自然融为一体。闭上眼睛，倾听大自然的声音，感受生命的跳动。这里没有压力，没有烦恼，只有内心的宁静与平静。
                        走出森林，回到城市的喧嚣中，你会发现内心得到了片刻的放松和治愈。在忙碌的生活中，我们常常忽略了内心的需求，而大自然正是我们寻找平衡与安宁的最好去处。
                        不论是在森林中散步，还是在花海中漫步，大自然都能给我们带来宁静与喜悦。让我们拋开烦恼与压力，回归自然，重新找回内心的平静与美好。
                      </div>
                      <div style="margin-top: 10px;color: gray">项目导师:梁老师</div>
                      <div style="color: gray">需要专业:计算机相关专业，销售相关知识</div>
                    </div>
                    <!--                    按钮部分-->
                    <div style="width: 130px;display:flex;padding-left: 40px;align-items: flex-end;">
                      <el-button type="primary" plain size="small">投递简历</el-button>
                    </div>
                  </div>
                </div>

                <div class="card" >
                  <div style="display: flex;">
                    <!--                  文字部分-->
                    <div style="flex: 1">
                      <div style="font-size: 25px;font-weight: bolder">水果助农计划</div>
                      <div style="margin-top: 10px;font-weight: bold;font-size: 17px">计算机设计大赛</div>
                      <div style="font-size:10px;margin-top: 10px">
                        在古老的森林中，阳光透过茂密的树叶洒落在地面上，给人一种宁静而祥和的感觉。空气中弥漫着泥土的芬芳，鸟儿在枝头欢快地歌唱，仿佛在庆祝自然的美妙。
                        这里是一个与世隔绝的角落，没有城市的喧嚣，没有繁忙的车辆，只有大自然的声音和生命的存在。站在这片森林中，你可以感受到时间的缓慢流动，仿佛世界的喧嚣与纷扰都远离了这里。
                        身处其中，你会发现每一个细微的变化。风吹过树梢，树叶会轻轻摇曳，发出沙沙的声音。小溪在山间流淌，水流清澈见底，草木在水中倒影出美丽的景色。微风轻拂脸颊，带来一丝凉意，让人心旷神怡。
                        在这个宁静的环境中，你可以与自然融为一体。闭上眼睛，倾听大自然的声音，感受生命的跳动。这里没有压力，没有烦恼，只有内心的宁静与平静。
                        走出森林，回到城市的喧嚣中，你会发现内心得到了片刻的放松和治愈。在忙碌的生活中，我们常常忽略了内心的需求，而大自然正是我们寻找平衡与安宁的最好去处。
                        不论是在森林中散步，还是在花海中漫步，大自然都能给我们带来宁静与喜悦。让我们拋开烦恼与压力，回归自然，重新找回内心的平静与美好。
                      </div>
                      <div style="margin-top: 10px;color: gray">项目导师:梁老师</div>
                      <div style="color: gray">需要专业:计算机相关专业，销售相关知识</div>
                    </div>
                    <!--                    按钮部分-->
                    <div style="width: 130px;display:flex;padding-left: 40px;align-items: flex-end;">
                      <el-button type="primary" plain size="small">投递简历</el-button>
                    </div>
                  </div>
                </div>

                <i-row >

                  <el-pagination
                      class="page"
                      @current-change="change"
                      background
                      layout="prev, pager, next"
                      :page-count="pageNum">
                  </el-pagination>
                </i-row>
              </div>



            </div>
          </div>
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
  name: "projectSearch",
  data() {
    return {
      multiDeleteVisible1:false,
      multipleSelectionFlag: false,
      multiDeleteVisible: false,
      multipleSelection: [],
      project: [],
      title: '',
      currentPage: 1,
      pageSize: 8,
      pageNum: 1,
      contest:{
        name:'',
        startTime:'',
        content:'',
      },
      idParams:[],
      dcontest:'',
    }
  },
  methods: {
    change(page) {
      this.currentPage=page;
      if(this.title!=='')
        this.search()
      else
        this.getAllInformation()
    },
    delieverResume(data) {
      this.$http({
        url: this.$http.adornUrl('/student/adddeliever'),
        method: 'post',
        data:this.$http.adornData(data),
        headers: {
          'UserToken':window.sessionStorage.getItem('Token'),
          'Content-Type': 'application/json',
          'charset': 'utf-8'
        }
      }).then(({data}) => {
        if (data&&data.status===200) {
          this.$message.success(data.msg)
        }else{
          this.$message.error(data.msg)
        }
      }).catch(() => {
        console.log('出错啦！！！！')
      })
    },

    reset(data){
      Object.keys(data).forEach(key=>(data[key]=''))
      return data
    },

    search() {
      let params = {
        page: this.currentPage,
        limit: this.pageSize,
        title:this.title
      }
      this.$http({
        url: this.$http.adornUrl('/student/searchProject'),
        method: 'get',
        params: this.$http.adornParams(params),
        headers: {
          'UserToken':window.sessionStorage.getItem('Token'),
        }
      }).then(({data}) => {
        if (data) {
          this.pageNum = data.totalPages
          this.project = data.content
        }
      }).catch(() => {
        console.log('出错啦！！！！')
      })
    },

    handleSelectionChange(val) {
      // console.log(val);
      this.multipleSelection = val;
      this.multipleSelectionFlag = this.multipleSelection.length !== 0;
    },


    getAllInformation() {
      let params = {
        page: this.currentPage,
        limit: this.pageSize
      }
      this.$http({
        url: this.$http.adornUrl('/student/projects'),
        method: 'get',
        params: this.$http.adornParams(params),
        headers: {
          'UserToken':window.sessionStorage.getItem('Token'),
        }
      }).then(({data}) => {
        if (data) {
          this.pageNum = data.totalPages
          this.project = data.content
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
  created() {
    this.getAllInformation();
  }
}
</script>

<style scoped>
.search {
  padding-top: 10px;
  padding-bottom: 10px;
}

.page {
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

.card {
  padding: 20px;
  background-color: #fff;
  border-radius: 5px;
  box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.1);
  margin-bottom: 10px;
}


</style>
