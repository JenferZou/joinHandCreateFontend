<template>
  <el-dialog title="发布" :visible.sync="dialogTableVisible" @close="close" width="80%">
    <div class="el-dialog-div">
    <el-form ref="form" :model="contest" label-width="auto">
        <el-form-item label="id" style="display: none">
            <el-input v-model="contest.id"></el-input>
        </el-form-item>
      <el-form-item label="标题">
        <el-input v-model="contest.name"></el-input>
      </el-form-item>
        <el-form-item label="指导老师">
            <el-input v-model="contest.mentor"></el-input>
        </el-form-item>
        <el-form-item label="所需专业">
            <el-input v-model="contest.needMajor"></el-input>
        </el-form-item>
      <el-form-item label="时间">
        <i-col :span="11">
          <el-date-picker type="date" placeholder="选择日期" v-model="contest.startTime" style="width: 100%;" value-format="yyyy-MM-dd HH:mm:ss"></el-date-picker>
        </i-col>
        <i-col class="line" :span="2"></i-col>
        <i-col :span="11">
<!--          <el-date-picker type="date" placeholder="选择日期" v-model="contest.endTime" style="width: 100%;" value-format="yyyy-MM-dd HH:mm:ss"></el-date-picker>-->
        </i-col>
      </el-form-item>
      <el-form-item class="item">
        <quill-editor class="editor"
                      ref="myTextEditor"
                      v-model="contest.content"
                      :options="editorOption"
                      @blur="onEditorBlur($event)"
                      @focus="onEditorFocus($event)"
                      @ready="onEditorReady($event)"
                      @change="onEditorChange($event)">
        </quill-editor>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">确认</el-button>
        <el-button @click="cancel">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
  </el-dialog>
</template>

<script>
import { toolbarOptions} from "@/components/utils";

export default {
  name: "InformationEdit",
  data(){
    return{
      editorOption: {
        modules: {
          toolbar: toolbarOptions
        },
        theme: 'snow',
        placeholder: '请输入正文'
        // Some Quill optiosn...
      },
      dialogTableVisible:false,
      contest:{},
    }
  },
  methods:{
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
    onSubmit() {
      this.$http({
        url: this.$http.adornUrl('/admin/project/modify'),
        method: 'post',
        data: this.$http.adornData(this.contest),
        headers: {
          'Content-Type': 'application/json',
          'charset': 'utf-8'
        }
      }).then(({data}) => {
          console.log(data)
        if (data && data.errorCode === "200") {
          this.$message.success(data.message)
            this.$parent.getAllInformation()
        } else {
          this.$message.error(data.message)
        }
      }).catch(() => {
        console.log('出错啦！！！！')
      })
      this.dialogTableVisible = false
    },
    cancel(){
      this.dialogTableVisible = false
    },
    close(){

    },
    init(data){
      this.dialogTableVisible = true
        this.$http({
            url: this.$http.adornUrl('/admin/project/get/'+data.id),
            method: 'get',
        }).then(({data}) => {
            if (data && data.errorCode === "200") {
               this.contest=data.data
            } else {
                this.$message.error(data.message)
            }
        }).catch(() => {
            console.log('出错啦！！！！')
        })
    }
  }
}
</script>

<style scoped lang="scss">
.el-dialog-div{
  height: 80vh;//如果高度过高，可用max-height
  overflow: auto;
}
.item {
  height: 500px;
}
.editor {
  height: 400px;
}
</style>
