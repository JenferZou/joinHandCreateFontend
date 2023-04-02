
<template>
  <div class="layout">
    <div class="layout-ceiling">
      <div class="layout-ceiling-main">
        <a href="#">注册登录</a> |
        <a href="#">帮助中心</a> |
        <a href="#">安全中心</a> |
        <a href="#">服务大厅</a>
      </div>
    </div>
    <div class="layout-header">
      <div class="layout-logo">返回</div>
    </div>
    <div style="height: 1000px">

        <div class="page-container">
        <div class="info-table">
          <el-card class="info-card">
            <div slot="header" class="info-header">
              <span class="info-title">个人信息表</span>
            </div>
            <div class="info-content">
              <i-row>
                <i-col :span="12">
                  <el-form :model="info" label-position="left" label-width="100px" readonly>
                    <el-form-item label="姓名">
                      <span>{{ info.sName }}</span>
                    </el-form-item>
                    <el-form-item label="性别">
                      <span>{{ info.gender }}</span>
                    </el-form-item>
                    <el-form-item label="学号">
                      <span>{{ info.sno }}</span>
                    </el-form-item>
                    <el-form-item label="联系电话">
                      <span>{{ info.sPhone }}</span>
                    </el-form-item>
                  </el-form>
                </i-col>
                <i-col :span="12">
                  <el-form :model="info" label-position="left" label-width="100px" readonly>
                    <el-form-item label="专业">
                      <span>{{ info.sMajor }}</span>
                    </el-form-item>
                    <el-form-item label="亲属联系人">
                      <span>{{ info.relatives }}</span>
                    </el-form-item>
                    <el-form-item label="亲属电话">
                      <span>{{ info.rPhone }}</span>
                    </el-form-item>
                    <el-form-item label="指导老师">
                      <span>{{ info.mentor }}</span>
                    </el-form-item>
                  </el-form>
                </i-col>


              </i-row>


              <i-row>
                <i-col :span="12">
                  <el-form :model="info" label-position="left" label-width="100px" readonly>
                    <el-form-item label="导师电话">
                      <span>{{ info.mentorPhone }}</span>
                    </el-form-item>
                    <el-form-item label="星座">
                      <span>{{ info.star }}</span>
                    </el-form-item>
                  </el-form>
                </i-col>

                <i-col :span="12">
                  <el-form :model="info" label-position="left" label-width="100px" readonly>
                    <el-form-item label="血型">
                      <span>{{ info.blood }}</span>
                    </el-form-item>

                  </el-form>
                </i-col>

                <i-col :span="12">
                  <el-form :model="info" label-position="left" label-width="100px" readonly>
                    <el-form-item label="积分">
                      <span>{{ info.score }}</span>
                    </el-form-item>

                  </el-form>
                </i-col>
              </i-row>
            </div>

          </el-card>

        </div>
          </div>

    </div>
    <div class="layout-copy">
    </div>
  </div>
</template>


<script>


export default {
  name: 'StudentMessage',
  data() {
    return {
      info: {
        sName: '',
        gender: '',
        sno: '123123',
        sMajor: '',
        sPhone: '',
        relatives: '',
        rPhone: '',
        mentor: '',
        mentorPhone: '',
        blood: '',
        star: '',
        score: 10
      },
      stars: [
        '白羊座',
        '金牛座',
        '双子座',
        '巨蟹座',
        '狮子座',
        '处女座',
        '天秤座',
        '天蝎座',
        '射手座',
        '摩羯座',
        '水瓶座',
        '双鱼座',
      ],
      bloods: ['A', 'B', 'AB', 'O']
      ,

    }
  },
  created() {
    this.load()
  },

  methods: {
    load() {

      this.$http({
        url: this.$http.adornUrl('/student/StudentMessageForm'),
        method: 'get',
        params:this.$http.adornParams(this.info),
        headers: {
          'UserToken':window.sessionStorage.getItem('Token'),
        }
      }).then(({data}) => {
          console.log(data)
          this.info=data
      }).catch(() => {
        console.log('出错啦！！！！')
      })
    },

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
.layout-logo{
  width: 100px;
  height: 30px;
  background: #5b6270;
  border-radius: 3px;
  float: left;
  position: relative;
  top: 15px;
  left: 20px;
  text-align: center;
  padding: 10px 0 20px;
  color: #d7dde4;
}

.layout-header{
  height: 60px;
  background: #fff;
  box-shadow: 0 1px 1px rgba(0,0,0,.1);
}
.layout-copy{
  text-align: center;
  padding: 10px 0 20px;
  color: #9ea7b4;
}
.layout-ceiling{
  background: #464c5b;
  padding: 10px 0;
  overflow: hidden;
}
.layout-ceiling-main{
  float: right;
  margin-right: 15px;
}
.layout-ceiling-main a{
  color: #9ba7b5;
}



.info-table {
  margin: 20px auto;
  max-width: 800px;

}

.info-card {
  padding: 20px;

}

.info-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.info-title {
  font-size: 24px;
  font-weight: bold;
}

.info-content {
  margin-top: 20px;
}




</style>














<!--<template>-->

<!--</template>-->

<!--<style scoped>-->


<!--</style>-->
