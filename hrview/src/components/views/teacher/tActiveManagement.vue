<template>
    <div>
        <i-row class="search">
            <i-col :span="12">
                <i-row>
                    <i-col :span="16">
                        <el-input @keyup.enter.native="search" size="small" v-model="title" placeholder="搜索历史发布" clearable prefix-icon="el-icon-search">
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
            <i-col :span="12">
                <i-row type="flex" justify="end">
                    <i-col :span="5">
                        <el-button type="primary" @click="display" size="small">
                            <i-icon type="plus-round" size="15"></i-icon>
                            发布</el-button>
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
                    property="name"
                    label="项目名称"
                    width="120">
            </el-table-column>
          <el-table-column
              property="mentor"
              label="指导老师"
              width="120">
          </el-table-column>
            <el-table-column
                    property="startTime"
                    label="开始时间"
                    width="120">
            </el-table-column>
            <el-table-column
                    property="needMajor"
                    label="项目需要的专业"
                    width="120">
            </el-table-column>
          <el-table-column
              property="expectedCompetition"
              label="预计参加的比赛"
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
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="100">
                <template v-slot="scope">
                    <el-button @click="deleteContest(scope.row)" type="text" size="small" style="color:red;">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination
                class="page"
                @current-change="change"
                background
                layout="prev, pager, next"
                :page-count="pageNum">
        </el-pagination>
        <el-dialog
                title="发布项目"
                :visible.sync="dialogVisible"
                width="80%"

        >
            <div class="el-dialog-div">
                <el-form :model="project" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item label="项目名称" prop="name">
                        <el-input type="text" v-model="project.name" autocomplete="off"></el-input>
                    </el-form-item>
                  <el-form-item label="指导老师" prop="mentor">
                    <el-input type="text" v-model="project.mentor" autocomplete="off"></el-input>
                  </el-form-item>
                    <el-form-item label="开始时间" prop="startTime">
                        <el-date-picker type="date" placeholder="选择日期" v-model="project.startTime" style="width: 100%;" value-format="yyyy-MM-dd HH:mm:ss"></el-date-picker>
                    </el-form-item>
                    <el-form-item label="项目需要的专业" prop="needMajor">
                        <el-input type="text" v-model="project.needMajor" autocomplete="off"></el-input>
                    </el-form-item>
                  <el-form-item label="预计参加的比赛" prop="expectedCompetition">
                    <el-input type="text" v-model="project.expectedCompetition" autocomplete="off"></el-input>
                  </el-form-item>
                    <el-form-item class="item">
                        <quill-editor class="editor"
                                      ref="myTextEditor"
                                      v-model="project.content"
                                      :options="editorOption"
                                      @blur="onEditorBlur($event)"
                                      @focus="onEditorFocus($event)"
                                      @ready="onEditorReady($event)"
                                      @change="onEditorChange($event)">
                        </quill-editor>
                    </el-form-item>
                </el-form>
                <el-card class="UpSet1">
                    <div style="text-align: center;">
                        <el-button @click="cancle" >取 消</el-button>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <el-button type="primary" @click="sure">确 定</el-button>
                    </div>
                </el-card>


            </div>
        </el-dialog>
        <el-dialog :visible.sync="multiDeleteVisible" title="提示" width="30%">
            <span>确定要删除吗</span>
            <span slot="footer">
          <el-button type="primary" @click="multiDelete" size="small">确 定</el-button>
          <el-button @click="multiDeleteVisible = false" size="small">取 消</el-button>
        </span>
        </el-dialog>
    </div>
</template>

<script>
import { toolbarOptions} from "@/components/utils";

export default {
    name: "tActiveManagement",
    data() {
        return {
            editorOption: {
                modules: {
                    toolbar: toolbarOptions
                },
                theme: 'snow',
                placeholder: '项目内容'
                // Some Quill optiosn...
            },
            multiDeleteVisible:false,
            currentPage: 1,
            pageSize: 8,
            pageNum: 1,
            activeData:[],
            title:'',
            deleteData:'',
          project:{
                name:'',
                mentor:'',
                startTime:'',
                needMajor:'',
                content:'',
                expectedCompetition:'',
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
        deleteOne(active,activeData){
            for(let i=0; i<activeData.length; i++){
                if(active.id===activeData[i].id){
                    activeData.splice(i,1)
                }
            }
            return activeData
        },
        multiDelete(){
            this.$http({
                url: this.$http.adornUrl('/teacher/deleteActive'),
                method: 'post',
                data:this.$http.adornData(this.deleteData),
                headers: {
                    'UserToken':window.sessionStorage.getItem('Token'),
                    'Content-Type': 'application/json',
                    'charset': 'utf-8'
                }
            }).then(({data}) => {
                if (data&&data.status===200) {
                    this.$message.success(data.msg)
                    this.activeData=this.deleteOne(this.deleteData,this.activeData)
                    this.deleteData=''
                }else{
                    this.$message.error(data.msg)
                }
            }).catch(() => {
                console.log('出错啦！！！！')
            })
            this.multiDeleteVisible=false
        },
        deleteContest(data){
            this.deleteData=data
            this.multiDeleteVisible=true
        },
        cancle(){
            this.dialogVisible = false
            this.project=this.reset(this.project)
        },
        reset(project){
            Object.keys(project).forEach(key=>{
              project[key]=''
            })
            return project
        },
        add(){
            this.$http({
                url: this.$http.adornUrl('/teacher/saveProject'),
                method: 'post',
                data:this.$http.adornData(this.project),
                headers: {
                    'Content-Type': 'application/json',
                    'charset': 'utf-8'
                }
            }).then(({data}) => {
                if (data&&data.errorCode==200) {
                    this.$message.success(data.data)
                }else{
                    this.$message.error(data.data)
                }
            }).catch(() => {
                console.log('出错啦！！！！')
            })
        },
        sure(){
            this.dialogVisible = false
            this.add();
            this.project=this.reset(this.project)
        },
        change(page){
          this.currentPage=page;
          if(this.title!=='')
            this.search()
          else
            this.getActive()
        },
        search(){
          let params = {
            page: this.currentPage,
            limit: this.pageSize,
            title:this.title
          }
          this.$http({
            url: this.$http.adornUrl('/teacher/searchProject'),
            method: 'get',
            params: this.$http.adornParams(params),
            headers: {
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
            url: this.$http.adornUrl('/teacher/project'),
            method: 'get',
            params: this.$http.adornParams(params),
            headers: {
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
</style>
