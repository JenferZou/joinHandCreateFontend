<template>
  <div>

    <el-container>
      <el-header>
        <div class="tableBar" style="margin-left: 1%;margin-top: 1%">
          <span style="margin-right: 5px;font-size: 14px">
            项目名称:
          </span>
          <el-input placeholder="输入项目名称" v-model="searchName" style="width: 10%"/>
          <span style="margin-left: 10px;font-size: 14px">
            开始时间:
          </span>
          <el-date-picker
              v-model="searchDate"
              type="date"
              placeholder="选择日期"
              value-format="yyyy-MM-dd HH:mm:ss"
              format="yyyy-MM-dd"
              style="margin-left: 10px;;width: 12%"
          >
          </el-date-picker>
          <span style="margin-left: 10px;font-size: 14px">
            所需专业:
          </span>
          <el-input placeholder="输入所需专业" v-model="searchNeedMajor" style="width: 10%"/>
          <span style="margin-left: 10px;font-size: 14px">
            对标比赛:
          </span>
          <el-input placeholder="输入对标比赛" v-model="searchExpectedCompetition" style="width: 10%"/>
          <el-button type="primary" size="medium" icon="el-icon-search" @click="search(true)" style="margin-left: 10px">
            查询
          </el-button>
          <el-button size="medium" icon="el-icon-delete" @click="resetSearch">重置</el-button>
        </div>
      </el-header>
      <el-main>
        <el-table
            ref="singleTable"
            :data="projectData"
            highlight-current-row
            style="width: 100%"
            v-loading="loading"
        >
          <el-table-column
              type="index"
              label="序号">
          </el-table-column>
          <el-table-column
              property="name"
              label="项目名称"
              width="120">
          </el-table-column>
          <el-table-column
              property="mentor"
              label="指导老师"
              width="120"
              sortable>
          </el-table-column>
          <el-table-column
              property="startTime"
              label="开始时间"
              width="120"
              sortable>
          </el-table-column>
          <el-table-column
              property="needMajor"
              label="项目需要的专业"
              width="120">
          </el-table-column>
          <el-table-column
              property="expectedCompetition"
              label="对标比赛"
              width="120">
          </el-table-column>
          <el-table-column
              prop="content"
              label="内容"
              :formatter="limitText">
            <!--        <template v-slot="scope">
                      <div v-html='scope.row.content'></div>
                    </template>-->
          </el-table-column>
          <el-table-column
              fixed="right"
              label="项目成员"
              width="100">
            <template v-slot="scope">
              <el-button type="primary" size="small" @click="searchStudent(scope.row)">管理</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-main>
      <el-footer>
        <el-pagination
            class="page"
            @size-change="handleSizeChange"
            @current-change="change"
            :current-page.sync="currentPage"
            :page-sizes="[2,5, 10, 50, 100]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next"
            :total="total">
        </el-pagination>
      </el-footer>
    </el-container>

    <el-dialog title="查看项目成员" :visible.sync="memberVisible" style="size: 100px">
      <el-table :data="students" style="width: 100%"
                max-height="550">
        <el-table-column fixed property="projectName" label="项目名称"></el-table-column>
        <el-table-column property="sname" label="学生姓名"></el-table-column>
        <el-table-column property="sno" label="学生学号"></el-table-column>
        <el-table-column property="smajor" label="学生专业"></el-table-column>
        <el-table-column
            label="操作"
            width="100"
        >
          <template v-slot="scope">
            <el-button type="primary" size="small" @click="emailStudent(scope.row)">踢出学生</el-button>
          </template>
        </el-table-column>

      </el-table>
    </el-dialog>


    <i-row>

      <el-pagination
          class="page"
          @current-change="change"
          background
          layout="prev, pager, next"
          :page-count="pageNum">
      </el-pagination>
    </i-row>
  </div>


</template>

<script>

export default {
  name: "myPublishProject",
  data() {
    return {
      loading: true,


      currentPage: 1,//初始页
      pageSize: 5,////    每页的数据
      total: 10,
      projectData: [],


      multiDeleteVisible1: false,
      multipleSelectionFlag: false,
      multiDeleteVisible: false,
      memberVisible: false,
      multipleSelection: [],

      searchName: '',
      searchDate: '',
      searchNeedMajor: '',
      searchExpectedCompetition: '',

      project: [],
      title: '',
      pageNum: 1,
      contest: {
        name: '',
        startTime: '',
        content: '',
      },
      students: [],
      idParams: [],
      dcontest: '',
      textLimit: 28, // 设置显示的字数限制为10个字符
    }
  },
  methods: {
    getProject() {
      let params = {
        page: this.currentPage,
        pageSize: this.pageSize
      }
      this.$http({
        url: this.$http.adornUrl('/project'),
        method: 'post',
        data: this.$http.adornParams(params)
        // 使用get请求，时间无法在后端完成反序列化
        // params: this.$http.adornParams(params),
      }).then(({data}) => {
        if (data) {
          this.projectData = data.data
          this.total = data.total
          this.loading = false
        }
      }).catch(() => {
        console.log('出错啦！！！！')
      })
    },//分页
    limitText(row) {
      // 使用字符串切片方法对 description 属性值进行处理
      return row.content.length > this.textLimit ? `${row.content.slice(0, this.textLimit)}...` : row.content;
    },
    change(page) {
      this.page = page
      //如果搜索框不为空，走搜索的分页, 去除开头前后的空格
      if (this.searchExpectedCompetition.trim() !== '' || this.searchName.trim() !== '' || this.searchDate.trim() !== '' || this.searchNeedMajor !== '') {
        this.search(false)
      } else {
        this.getProject()
      }

    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      //如果搜索框不为空，走搜索的分页
      if (this.searchExpectedCompetition.trim() !== '' || this.searchName.trim() !== '' || this.searchDate.trim() !== '' || this.searchNeedMajor !== '') {
        this.search(false)
      } else {
        this.getProject()

      }
    },
    reset(data) {
      Object.keys(data).forEach(key => (data[key] = ''))
      return data
    },

    //条件查询
    search(flag) {
      if (flag == true) {
        this.currentPage = 1//重置当前页面为1
      }
      let params = {
        page: this.currentPage,
        pageSize: this.pageSize,
        name: this.searchName,
        startTime: this.searchDate,
        needMajor: this.searchNeedMajor,
        expectedCompetition: this.searchExpectedCompetition,
      }
      this.$http({
        url: this.$http.adornUrl('/project'),
        method: 'post',
        data: this.$http.adornParams(params),
      }).then(({data}) => {
        if (data) {
          this.total = data.total
          this.projectData = data.data

        }
      }).catch(() => {
        console.log('出错啦！！！！')
      })
    },
    resetSearch() {
      this.searchName = ''
      this.searchDate = ''
      this.searchNeedMajor = ''
      this.searchExpectedCompetition = ''
      this.getProject()
    },

    searchStudent(data) {
      let params = {
        pid: data.id,
        mark:1
/*        pageSize: -1,//不分页
        pageNo: 1,*/
      }
      this.$http({
        url: this.$http.adornUrl('/delieverResume/getdelieverResumeByPidAndMark'),
        method: 'get',
        params: this.$http.adornParams(params),
      }).then(({data}) => {
        if (data.data != '') {
          this.students = data.data
          this.memberVisible = true
        } else {
          this.memberVisible = false
          this.$message.error("暂无成员加入")

        }
      }).catch(() => {
        console.log('出错啦！！！！')
      })
    },


    emailStudent(data) {

      let tempData = {
        id:data.pid
      }

      let params = {
        sno: data.id,
        tno: data.tno,
        pid: data.pid,
        mentor: data.mentor,
        projectName: data.projectName
      }

      this.$confirm('此操作踢出并通知该学生, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        //确认按钮后事件
        this.kickOutStudent(data,tempData,params)

      })

    },
    kickOutStudent(data,tempData,params){
      //发送踢人请求，实际更新mark为 -2
      this.$http({
        url: this.$http.adornUrl('/delieverResume/updateMarkById'),
        method: 'post',
        data: {
          id: data.id,
          mark: -2
        }
      }).then(({data}) => {
        //操作成功的通知
        if (data.errorCode == 200) {
          this.$message({
            type: 'success',
            message: '该学生被踢出!'
          });
          //成功踢出后继续发送通知
          this.sendMessage(params)
        }
        this.searchStudent(tempData)

      })//发送删除请求
    },
    sendMessage(data){
      //发送通知请求
      this.$http({
        url: this.$http.adornUrl('/message/saveMessageBySno'),
        method: 'post',
        data: this.$http.adornParams(data),
      }).then(({data}) => {
        //操作成功的通知
        if (data.errorCode == 200) {
          this.$message({
            type: 'success',
            message: '通知发送成功!'
          });
        } else
          this.$message.error("错误")
      })//发送通知请求
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
        url: this.$http.adornUrl('/teacher/getMypublicPro'),
        method: 'get',
        params: this.$http.adornParams(params),
        headers: {
          'UserToken': window.sessionStorage.getItem('Token'),
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
    goWhere(name) {
      this.$router.push({name})
    },

    editMes(name) {
      // this.$router.push({
      //   path: '/StudentMessageForm'
      // })
      this.$router.push({name})
    },
    ediRes(name) {
      this.$router.push({name})
    }
  },
  created() {
    this.getProject();

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


.layout {
  border: 1px solid #d7dde4;
  background: #f5f7f9;
}

.layout-logo {
  width: 100px;
  height: 30px;
  border-radius: 3px;
  background: #5b6270;
  float: left;
  position: relative;
  top: 15px;
  left: 20px;
}

.layout-nav {
  width: 420px;
  margin: 0 auto;
}

.layout-assistant {
  width: 500px;
  margin: 0 auto;
  height: inherit;

}

.layout-breadcrumb {
  padding: 10px 15px 0;
}

.layout-content {
  min-height: 600px;
  margin: 15px;
  overflow: hidden;
  background: #fff;
  border-radius: 4px;
}

.layout-content-main {
  padding: 10px;
  background-color: #f5f7f9;
}

.layout-copy {
  text-align: center;
  padding: 10px 0 20px;
  color: #9ea7b4;
}


</style>
