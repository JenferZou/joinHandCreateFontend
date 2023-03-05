<template>
  <div class="layout" :class="{'layout-hide-text': spanLeft < 5}">
    <i-row type="flex">
      <i-col :span="spanLeft" class="layout-menu-left">
        <i-menu active-name="1" theme="dark" width="auto">
          <div class="layout-logo-left"></div>
          <i-menu-item name="1">
            <i-icon type="ios-navigate" :size="iconSize"></i-icon>
            <span class="layout-text">选项 1</span>
          </i-menu-item>
          <i-menu-item name="2">
            <i-icon type="ios-keypad" :size="iconSize"></i-icon>
            <span class="layout-text">选项 2</span>
          </i-menu-item>
          <i-menu-item name="3">
            <i-icon type="ios-analytics" :size="iconSize"></i-icon>
            <span class="layout-text">选项 3</span>
          </i-menu-item>
        </i-menu>
      </i-col>
      <i-col :span="spanRight">
        <div class="layout-header">
          <el-button type="text" @click="toggleClick">
            <i-icon type="navicon" size="32"></i-icon>
          </el-button>
        </div>
        <div class="layout-breadcrumb">
          <i-breadcrumb>
            <i-breadcrumb-item href="#">首页</i-breadcrumb-item>
            <i-breadcrumb-item href="#">应用中心</i-breadcrumb-item>
            <i-breadcrumb-item>某应用</i-breadcrumb-item>
          </i-breadcrumb>
        </div>
        <div class="layout-content">

          <i-row :gutter="16" class="headcontent">
            <i-col span="6">
              <div class="headmes" style="height: 100px"  >实践经历</div>
            </i-col>
            <i-col span="6">
              <div class="headmes" style="height: 100px" @click="editMes">个人信息</div>
            </i-col>
            <i-col span="6">
              <div class="headmes" style="height: 100px">积分</div>
            </i-col>
            <i-col span="6">
              <div class="headmes" style="height: 100px">消息通知</div>
            </i-col>
          </i-row>

          <i-row  class="centercontent">
            <i-col span="24">
              <div class="mes" style="height: 300px">
                <i-col style="height: 50px">
                  <i-col span="8">col-8</i-col>
                  <i-col span="8">col-8</i-col>
                  <i-col span="8">col-8</i-col>
                </i-col>

                <i-col style="height: 50px">
                  <i-col span="8">col-8</i-col>
                  <i-col span="8">col-8</i-col>
                  <i-col span="8">col-8</i-col>
                </i-col>

                <i-col style="height: 50px">
                  <i-col span="8">col-8</i-col>
                  <i-col span="8">col-8</i-col>
                  <i-col span="8">col-8</i-col>
                </i-col>


                <i-col style="height: 50px">
                  <i-col span="8">col-8</i-col>
                  <i-col span="8">col-8</i-col>
                  <i-col span="8">col-8</i-col>
                </i-col>

                <i-col style="height: 50px">
                  <i-col span="8">col-8</i-col>
                  <i-col span="8">col-8</i-col>
                  <i-col span="8">col-8</i-col>
                </i-col>
              </div>
            </i-col>
          </i-row>


          <i-row  class="footcontent" :gutter="16">
            <i-col span="12">
              <div class="mes" style="height: 100px">col-6</div>
            </i-col>
            <i-col span="12">
              <div class="pra" style="height: 100px">col-6</div>
            </i-col>
          </i-row>









        </div>
        <div class="layout-copy">
          2011-2016 &copy; TalkingData
        </div>
      </i-col>
    </i-row>
  </div>
</template>


<script>

export default {
  data () {
    return {
      spanLeft: 5,
      spanRight: 19,

    }
  },
  computed: {
    iconSize () {
      return this.spanLeft === 5 ? 14 : 24;
    }
  },
  methods: {
    toggleClick () {
      if (this.spanLeft === 5) {
        this.spanLeft = 2;
        this.spanRight = 22;
      } else {
        this.spanLeft = 5;
        this.spanRight = 19;
      }
    },

    editMes(){
      this.$router.push({
          path: '/StudentMessageForm'
      })
    }
  }
}
</script>

<style scoped>
.layout{
  border: 1px solid #d7dde4;
  background: #f5f7f9;
  position: relative;
  border-radius: 4px;
  overflow: hidden;
}
.layout-breadcrumb{
  padding: 10px 15px 0;
}
.layout-content{
  min-height: 700px;
  margin: 15px;
  overflow: hidden;
  background: #fff;
  border-radius: 4px;
}


.headcontent{
  height: 100px;
  margin-bottom: 10px;
  margin-top: 10px;
}

.centercontent{
  height: 300px;
  margin-bottom: 10px;
}

.footcontent{
  height: 100px;
}


.headmes{
  background-color: #91d5ff;
  /*border-image: linear-gradient(45deg, #90cf5b, #00afee) 1;*/
  clip-path: inset(0 round 10px);
}

.headmes:hover{
  background-color: #1890ff;
}
.mes{
  background-color: coral;

}
.pra{
  background-color: darkgray;

}

/*.layout-content-main{*/
/*  padding: 10px;*/
/*}*/
.layout-copy{
  text-align: center;
  padding: 10px 0 20px;
  color: #9ea7b4;
}
.layout-menu-left{
  background: #464c5b;
}
.layout-header{
  height: 60px;
  background: #fff;
  box-shadow: 0 1px 1px rgba(0,0,0,.1);
}
.layout-logo-left{
  width: 90%;
  height: 30px;
  background: #5b6270;
  border-radius: 3px;
  margin: 15px auto;
}
.layout-ceiling-main a{
  color: #9ba7b5;
}
.layout-hide-text .layout-text{
  display: none;
}
.ivu-col{
  transition: width .2s ease-in-out;
}
</style>>