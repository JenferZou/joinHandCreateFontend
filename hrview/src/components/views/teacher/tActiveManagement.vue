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
                    <el-date-picker v-model="searchDate" type="date" placeholder="选择日期"
                                    value-format="yyyy-MM-dd HH:mm:ss" format="yyyy-MM-dd"
                                    style="margin-left: 10px;;width: 12%">
                    </el-date-picker>
                    <span style="margin-left: 10px;font-size: 14px">
            所需专业:
          </span>
                    <el-input placeholder="输入所需专业" v-model="searchNeedMajor" style="width: 10%"/>
                    <span style="margin-left: 10px;font-size: 14px">
            对标比赛:
          </span>
                    <el-input placeholder="输入对标比赛" v-model="searchExpectedCompetition" style="width: 10%"/>
                    <el-button type="primary" size="medium" icon="el-icon-search" @click="search(true)"
                               style="margin-left: 10px">查询
                    </el-button>
                    <el-button size="medium" icon="el-icon-delete" @click="resetSearch">重置</el-button>
                    <el-button type="primary" icon="el-icon-plus" @click="display" style="float: right">发布项目
                    </el-button>
                </div>
            </el-header>
            <el-main>
                <!--添加加载 -->
                <el-table ref="singleTable" v-loading="isLoading" :data="projectData" highlight-current-row
                          style="width: 100%">
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
                            label="操作"
                            width="150">
                        <template v-slot="scope">
                            <el-button type="primary" size="mini" @click="updateContest(scope.row)">修改</el-button>
                            <el-button type="danger" size="mini" @click="deleteContest(scope.row)">删除</el-button>
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

        <el-dialog title="确认删除" :visible.sync="multiDeleteVisible" width="30%">
            <span>确认删除该项目?</span>
            <span slot="footer" class="dialog-footer">
    <el-button @click="multiDeleteVisible = false">取 消</el-button>
    <el-button type="primary" @click="multiDelete">确 定</el-button>
  </span>
        </el-dialog>

        <el-dialog
                title="发布项目"
                :visible.sync="dialogVisible"
                width="80%"
                @close='closeDialog'

        >
            <div class="el-dialog-div">
                <el-form :model="project" :rules="rules" ref="ruleForm" label-width="100px" class="demo-project">
                    <el-form-item label="项目名称" prop="name">
                        <el-input type="text" v-model="project.name" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="指导老师" prop="mentor">
                        <el-input type="text" v-model="project.mentor" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="开始时间" prop="startTime">
                        <el-date-picker type="date" placeholder="选择日期" v-model="project.startTime"
                                        style="width: 100%;"
                                        value-format="yyyy-MM-dd HH:mm:ss"></el-date-picker>
                    </el-form-item>
                    <el-form-item label="专业需求" prop="needMajor">
                        <el-input type="text" v-model="project.needMajor" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="对标比赛" prop="expectedCompetition">
                        <el-input type="text" v-model="project.expectedCompetition" autocomplete="off"></el-input>
                    </el-form-item>
<!--                    <el-form-item label="上传附件">
                        <i-row>
                            <i-col :span="22">
                                <el-upload
                                        ref="fileUpload"
                                        class="upload-demo"
                                        action="https://jsonplaceholder.typicode.com/posts/"
                                        :limit="1"
                                        :auto-upload="false"
                                        :file-list="fileList"
                                        :on-success="uploadSuccessFile"
                                        :before-upload="beforeUploadFile"
                                        name="file">
                                    <el-button size="small">上传附件</el-button>
                                </el-upload>
                            </i-col>
                            <i-col :span="2">
                                <el-button size="small" @click="up">上传</el-button>
                            </i-col>
                        </i-row>
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

                        <quill-editor class="editor" ref="myTextEditor"
                                      style="min-width: 650px"
                                      v-model="project.content"
                                      :options="editorOption"
                                      @blur="onEditorBlur($event)"
                                      @focus="onEditorFocus($event)"
                                      @ready="onEditorReady($event)"
                                      @change="onEditorChange($event)">
                        </quill-editor>
                    </el-form-item>
                    <el-form-item>
                        <el-button @click="cancle">取 消</el-button>
                        <el-button type="primary" @click="add()">保存</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </el-dialog>


        <el-dialog title="修改项目" :visible.sync="updateDialogVisible" width="80%" @close='closeDialog'>
            <div class="el-dialog-div">
                <el-form :model="project" :rules="rules" ref="ruleForm" label-width="100px" class="demo-project">
                    <el-form-item label="项目名称" prop="name">
                        <el-input type="text" v-model="project.name" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="指导老师" prop="mentor">
                        <el-input type="text" v-model="project.mentor" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="开始时间" prop="startTime">
                        <el-date-picker type="date" placeholder="选择日期" v-model="project.startTime"
                                        style="width: 100%;"
                                        value-format="yyyy-MM-dd HH:mm:ss"></el-date-picker>
                    </el-form-item>
                    <el-form-item label="专业需求" prop="needMajor">
                        <el-input type="text" v-model="project.needMajor" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="对标比赛" prop="expectedCompetition">
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
                    <el-form-item>
                        <el-button @click="cancle">取 消</el-button>
                        <el-button type="primary" @click="update()">立即修改</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </el-dialog>

    </div>
</template>

<script>
import {toolbarOptions} from "@/components/utils";
import {userHandler} from "@/components/utils/back";

export default {
    name: "tProjectManagement",
    data() {
        return {
            fileList: [],
            qiniuForm: {
                key: "",
                token: "",
                showUrl: "",
            },
            uploadUrl: "http://upload-z2.qiniup.com",
            isLoading: false,
            editorOption: {
                modules: {
                    toolbar: {
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
                placeholder: '项目内容'
                // Some Quill optiosn...
            },
            tag: 0,//0 为添加，1为修改
            multiDeleteVisible: false,//确认删除 弹出框d可见性
            updateDialogVisible: false,//update 弹出框得可见性
            currentPage: 1,//初始页
            pageSize: 5,////    每页的数据
            total: 10,
            projectData: [],
            searchName: '',
            searchDate: '',
            searchNeedMajor: '',
            searchExpectedCompetition: '',
            deleteData: '',
            updateData: '',
            project: {
                name: '',
                mentor: '',
                startTime: '',
                needMajor: '',
                content: '',
                expectedCompetition: '',
                remark: '',
            },
            rules: {
                pass: [
                    {trigger: 'blur'}
                ],
                name: [
                    {required: true, message: '请输入项目名称', trigger: 'blur'}
                ],
                mentor: [
                    {required: true, message: '请输入指导老师', trigger: 'blur'}
                ],
                startTime: [
                    {type: 'date', required: true, message: '请选择日期', trigger: 'blur'}
                ],
                needMajor: [
                    {required: true, message: '请输入专业需求', trigger: 'blur'}
                ],
                expectedCompetition: [
                    {required: true, message: '请输入对标比赛', trigger: 'blur'}
                ],
                item: [
                    {required: true, message: '请输入项目内容', trigger: 'blur'}
                ]
            },
            dialogVisible: false, //新增弹出框的可见性
            textLimit: 26, // 设置显示的字数限制为10个字符
        };
    },
    methods: {
        beforeUploadFile(file) {
            const testmsg = file.name.substring(file.name.lastIndexOf(".") + 1);

            const extension = testmsg === "doc" || testmsg === "docx"
            if (!extension) {
                this.$message.error('文件格式错误！请上传doc或docx格式文件')
                return false
            }
            let size200M = file.size / 1024 / 1024 < 10
            if (!size200M) {
                this.$message.error('文件大小不能超过10M')
                return false
            }
            return new Promise((resolve, reject) => {
                userHandler.getUpToken().then((res) => {
                    if (res && res.errorCode === "200") {
                        this.qiniuForm.token = res.data.upToken
                        this.qiniuForm.key = res.data.fileName
                        this.qiniuForm.showUrl = res.data.url
                        resolve(true)
                    } else {
                        reject(false)
                    }
                    console.log(this.qiniuForm)
                }).catch((err) => {
                    console.log(err)
                    reject(false)
                })
            })

        },
        uploadSuccessFile(res){
            if(res){

            }
        },
        beforeUpload(file) {
            console.log("上传前")
            const isLt3M = file.size / 1024 / 1024 < 3;  // 判断文件大小是否小于3M
            if (!isLt3M) {
                this.$message.error('上传图片大小不能超过3M');
                return false;
            }
            return new Promise((resolve, reject) => {
                userHandler.getUpToken().then((res) => {
                    if (res && res.errorCode === "200") {
                        this.qiniuForm.token = res.data.upToken
                        this.qiniuForm.key = res.data.fileName
                        this.qiniuForm.showUrl = res.data.url
                        resolve(true)
                    } else {
                        reject(false)
                    }
                    console.log(this.qiniuForm)
                }).catch((err) => {
                    console.log(err)
                    reject(false)
                })
            })
        },
        getUpLoadToken() {

        },
        up(){
            this.$refs.fileUpload.submit()
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
        deleteOne(project, projectData) {
            for (let i = 0; i < projectData.length; i++) {
                if (project.id === projectData[i].id) {
                    projectData.splice(i, 1)
                }
            }
            return projectData
        },
        //确认删除后事件
        multiDelete() {

            this.$http({
                url: this.$http.adornUrl('/teacher/deleteProject'),
                method: 'post',
                data:{
                 id : this.deleteData.id
                },
              headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
                'charset': 'utf-8'
              }
            }).then(({data}) => {
                if (data && data.errorCode == 200) {
                    this.$message.success("操作成功")
                    this.projectData = this.deleteOne(this.deleteData, this.projectData)
                    this.deleteData = ''

                    /*          //删除后返回初始页
                              this.currentPage = 1//初始页
                              this.pageSize = 5////    每页的数据*/

                    //记录总页数
                    //总页数=删除后的总记录数/分页条数
                    let totalPage = Math.ceil((this.total - 1) / this.pageSize)

                    //记录当前页码
                    //记住删除最后一条数据时当前码是最后一页
                    let pageNum = this.currentPage > totalPage ? totalPage : this.currentPage
                    //重新记录当前页码
                    this.currentPage = pageNum < 1 ? 1 : pageNum

                    this.getProject()
                } else {
                    this.$message.error(data.message)
                }
            }).catch(() => {
                console.log('出错啦！！！！')
            })
            this.multiDeleteVisible = false
        },
        // 修改按钮后事件
        updateContest(data) {
            this.updateData = data
            //发送根据id查询项目请求
            this.$http({
                url: this.$http.adornUrl('/project/getById'),
                method: 'get',
                params: {
                    id: this.updateData.id
                }
            }).then(({data}) => {
                if (data && data.errorCode == 200) {
                    this.project = data.data
                    this.tag = 1
                    this.dialogVisible = true
                } else {
                    this.$message.error(data.message)
                }
            })

        },
        deleteContest(data) {
            this.deleteData = data
            this.multiDeleteVisible = true
            this.getProject()
        },
        cancle() {
            this.dialogVisible = false
            this.updateDialogVisible = false //修改的可见性
            this.project = this.reset(this.project)
        },
        reset(project) {
            Object.keys(project).forEach(key => {
                project[key] = ''
            })
            return project
        },
        //立即发布后事件
        sure() {
            // this.project = this.reset(this.project)
        },
        //确认发布项目事件
        add() {
            if (this.tag === 0) {
                this.$http({
                    url: this.$http.adornUrl('/project/saveProject'),
                    method: 'post',
                    data: this.project,
                }).then(({data}) => {
                    if (data && data.errorCode == 200) {
                        this.$message.success("操作成功")
                        this.project = ''
                        this.dialogVisible = false
                        this.getProject()
                    } else {
                        this.$message.error(data.message)
                    }
                }).catch(() => {
                    console.log('出错啦！！！！')
                })
            } else {
                this.$http({
                    url: this.$http.adornUrl('/project/update'),
                    method: 'post',
                    data: this.project,
                }).then(({data}) => {
                    if (data && data.errorCode == 200) {
                        this.$message.success("操作成功")
                        this.project = ''
                        this.dialogVisible = false
                        this.getProject()
                    } else {
                        this.$message.error(data.message)
                    }
                }).catch(() => {
                    console.log('出错啦！！！！')
                })
            }
        },
        // 立即修改后事件
        update() {
            this.$http({
                url: this.$http.adornUrl('/project/update'),
                method: 'post',
                data: this.project,
            }).then(({data}) => {
                if (data && data.errorCode == 200) {
                    this.$message.success("操作成功")
                    this.project = ''
                    this.getProject()
                } else {
                    this.$message.error(data.message)
                }
            }).catch(() => {
                console.log('出错啦！！！！')
            })
            this.updateDialogVisible = false
        },
        //关闭弹出框事件
        closeDialog() {
            //弹窗消失时，清除表单验证以及内容
            this.$refs.ruleForm.resetFields()
            this.project = {
                name: '',
                mentor: '',
                startTime: '',
                needMajor: '',
                content: '',
                expectedCompetition: '',
                remark: '',
            };//清空数据
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
                // 使用get请求，时间无法在后端完成反序列化
                // params: this.$http.adornParams(params)
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
        display() {
            this.tag = 0
            this.dialogVisible = true
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
        limitText(row) {
            // 使用字符串切片方法对 description 属性值进行处理
            return row.content.length > this.textLimit ? `${row.content.slice(0, this.textLimit)}...` : row.content;
        },
        getProject() {
            let params = {
                page: this.currentPage,
                pageSize: this.pageSize
            }
            this.isLoading = true;
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
                }
            }).catch(() => {
                console.log('出错啦！！！！')
            }).finally(() => {
                this.isLoading = false;
            })
        }
    },
    created() {
        this.getProject();
    }
}
</script>

<style scoped lang="scss">
.search {
  padding-top: 10px;
  padding-bottom: 10px;
}

.el-dialog-div {
  max-height: 80vh; //如果高度过高，可用max-height
  overflow: auto;
}

.item {
  height: 500px;
}

.editor {
  height: 400px;
}

.dialog-footer {
  text-align: right;
}

.page {
  text-align: center;
}
</style>
