<template>
    <el-dialog title="发布"  :visible.sync="dialogTableVisible" @close="close" width="75%">
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
                        <el-date-picker type="date" placeholder="选择日期" v-model="contest.startTime"
                                        style="width: 100%;" value-format="yyyy-MM-dd HH:mm:ss"></el-date-picker>
                    </i-col>
                    <i-col class="line" :span="2"></i-col>
                    <i-col :span="11">
                        <!--          <el-date-picker type="date" placeholder="选择日期" v-model="contest.endTime" style="width: 100%;" value-format="yyyy-MM-dd HH:mm:ss"></el-date-picker>-->
                    </i-col>
                </el-form-item>
<!--                <el-form-item label="上传附件">
                                    <el-upload
                                            class="upload-demo"
                                            action="https://jsonplaceholder.typicode.com/posts/"
                                            :limit="1"
                                            :auto-upload="false"
                                            :file-list="fileList"
                                            name="file">
                                            <el-button size="small" >点击上传</el-button>
                                        </el-upload>
                </el-form-item>-->
                <el-form-item class="item">
                    <!-- 图片上传组件辅助，组件内添加v-show=“false”属性，把该组件隐藏起来。-->
                    <el-upload
                        v-show="false"
                        class="avatar-uploader"
                               :action="uploadUrl"
                               name="file"
                               :accept="'image/*'"
                               :data="qiniuForm"
                               :show-file-list="false"
                               :on-success="uploadSuccess"
                               :before-upload="beforeUpload">
                    </el-upload>
                    <quill-editor class="editor"
                                  style="min-width: 650px"
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
import {toolbarOptions} from "@/components/utils";
import {userHandler} from "@/components/utils/back"


export default {
    name: "InformationEdit",
    data() {
        return {
            fileList: [],
            qiniuForm: {
                key: "",
                token: "",
                showUrl: "",
            },
            uploadUrl:"http://upload-z2.qiniup.com",
            editorOption: {
                modules: {
                    toolbar:{
                        container: toolbarOptions,
                        handlers: {
                            image: function (value) { //编辑器-上传图片
                                if (value) {
                                        // 调用antd图片上传upload
                                        document.querySelector('.avatar-uploader input').click()
                                } else {
                                    this.quill.format('image', false)
                                }
                            },
                        }
                    },
                },
                theme: 'snow',
                placeholder: '请输入正文',
                // Some Quill optiosn...
            },
            dialogTableVisible: false,
            contest: {},
            upToken:'',
        }
    },
    methods: {
        beforeUpload(file) {
            console.log("上传前")
            const isLt3M = file.size / 1024 / 1024 < 3;  // 判断文件大小是否小于3M
            if (!isLt3M) {
                this.$message.error('上传图片大小不能超过3M');
                return false;
            }
            return new Promise((resolve, reject) => {
                userHandler.getUpToken().then((res)=>{
                    if (res && res.errorCode === "200") {
                        this.qiniuForm.token = res.data.upToken
                        this.qiniuForm.key = res.data.fileName
                        this.qiniuForm.showUrl = res.data.url
                        resolve(true)
                    }else{
                        reject(false)
                    }
                    console.log(this.qiniuForm)
                }).catch((err)=>{
                    console.log(err)
                    reject(false)
                })
            })
        },
        getUpLoadToken(){

        },
        uploadSuccess(res) {
            // 获取富文本组件实例
            let quill = this.$refs.myTextEditor.quill
            // 如果上传成功
            if (res) {
                console.log(this.qiniuForm.showUrl)
                // 获取光标所在位置
                let length = quill.getSelection().index;
                // 插入图片，res为服务器返回的图片链接地址
                quill.insertEmbed(length, 'image', this.qiniuForm.showUrl)
                // 调整光标到最后
                quill.setSelection(length + 1)

            } else {
                // 提示信息，需引入Message
                this.$message.error('图片插入失败！')
            }
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
        cancel() {
            this.dialogTableVisible = false
        },
        close() {

        },
        init(data) {
            this.dialogTableVisible = true
            this.$http({
                url: this.$http.adornUrl('/admin/project/get/' + data.id),
                method: 'get',
            }).then(({data}) => {
                if (data && data.errorCode === "200") {
                    this.contest = data.data
                } else {
                    this.$message.error(data.message)
                }
            }).catch(() => {
                console.log('出错啦！！！！')
            })
        }
    },
}
</script>

<style scoped lang="scss">
.el-dialog-div {
  height: 80vh; //如果高度过高，可用max-height
  overflow: auto;
}

.item {
  height: 500px;
}

.editor {
  height: 400px;
}
</style>
