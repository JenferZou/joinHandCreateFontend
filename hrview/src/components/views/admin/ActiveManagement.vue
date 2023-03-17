<template>
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
      title="添加信息"
      :visible.sync="dialogVisible"
      width="80%"

     >
    <div class="el-dialog-div">
    <el-form :model="active" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="活动名称" prop="title">
        <el-input type="text" v-model="active.title" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="活动时间" prop="activeTime">
        <el-date-picker type="date" placeholder="选择日期" v-model="active.activeTime" style="width: 100%;" value-format="yyyy-MM-dd HH:mm:ss"></el-date-picker>
      </el-form-item>
      <el-form-item label="活动时长" prop="activeDuration">
        <el-input type="text" v-model="active.activeDuration" autocomplete="off" placeholder="以小时为单位"></el-input>
      </el-form-item>
      <el-form-item label="活动类型" prop="type">
        <el-input type="text" v-model="active.type" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="可获积分" prop="points">
        <el-input type="text" v-model="active.points" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="备注" prop="remark">
        <el-input type="text" v-model="active.remark" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item class="item">
        <quill-editor class="editor"
                      ref="myTextEditor"
                      v-model="active.content"
                      :options="editorOption"
                      @blur="onEditorBlur($event)"
                      @focus="onEditorFocus($event)"
                      @ready="onEditorReady($event)"
                      @change="onEditorChange($event)">
        </quill-editor>
      </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
    <el-button @click="cancle">取 消</el-button>
    <el-button type="primary" @click="sure">确 定</el-button>
  </span>
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
  name: "ActiveManagement",
  data() {
    return {
      editorOption: {
        modules: {
          toolbar: toolbarOptions
        },
        theme: 'snow',
        placeholder: '请输入正文'
        // Some Quill optiosn...
      },
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
        url: this.$http.adornUrl('/admin/deleteActive'),
        method: 'post',
        data:this.$http.adornData(this.deleteData),
        headers: {
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
      this.active=this.reset(this.active)
    },
    reset(active){
      Object.keys(active).forEach(key=>{
        active[key]=''
      })
      return active
    },
    add(){
      this.$http({
        url: this.$http.adornUrl('/admin/saveActive'),
        method: 'post',
        data:this.$http.adornData(this.active),
        headers: {
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
    sure(){
      this.dialogVisible = false
      this.add();
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
        url: this.$http.adornUrl('/admin/getActive'),
        method: 'get',
        params: this.$http.adornParams(params)
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
