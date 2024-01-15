<template>
    <div>
        <div class="layout-search">
            <i-row>
                <i-col :span="19">
                    <i-row>
                        <i-col :span="5">
                            <el-input @keyup.enter.native="search" size="small" v-model="highSearchData.keyword"
                                      placeholder="请通过学生名字或者学号搜索学生" clearable>
                            </el-input>
                        </i-col>
                        <i-col :span="5" style="padding-left: 5px">
                            <el-input size="small" placeholder="所属学院"
                                      v-model="highSearchData.sDepartment"></el-input>
                        </i-col>
                        <i-col :span="5" style="padding-left: 5px">
                            <el-input size="small" placeholder="专业"
                                      v-model="highSearchData.sMajor"></el-input>
                        </i-col>
                        <i-col :span="5" style="padding-left: 5px">
                            <el-input size="small" placeholder="班级"
                                      v-model="highSearchData.className"></el-input>
                        </i-col>
                        <i-col :span="2" style="padding-left: 5px">
                            <el-button type="primary" size="small" @click="search">
                                查询
                            </el-button>
                        </i-col>
                        <i-col :span="2" style="padding-left: 5px">
                            <el-button type="primary" size="small" @click="resetHighSearchData">
                                重置
                            </el-button>
                        </i-col>
                    </i-row>
                </i-col>
                <i-col :span="4">
                    <i-row type="flex" justify="end">
                        <!--  <i-col :span="5">
                           <el-dialog :visible.sync="upload" title="上传" width="30%">
                                  <el-upload
                                          ref="upload"
                                          :auto-upload="false"
                                          :file-list="fileList"
                                          class="upload-demo"
                                          action="string"
                                          :limit="1"
                                          :http-request="upLoadHandle"
                                  >
                                      <el-button size="small" type="primary">点击上传</el-button>
                                  </el-upload>
                                  <span slot="footer">
            <el-button type="primary" @click="inExcel" size="small">确 定</el-button>
            <el-button @click="cancelUp" size="small">取 消</el-button>
          </span>
                              </el-dialog>-->
                        <!--                            <el-button type="success" size="small" @click="popdig">
                                                        <i-icon size="15" type="arrow-down-a"></i-icon>
                                                        导入数据
                                                    </el-button>
                                                </i-col>
                                                <i-col :span="5">
                                                    <el-button type="success" size="small" @click="exportExcel">
                                                        <i-icon size="15" type="arrow-up-a"></i-icon>
                                                        导出数据
                                                    </el-button>
                                                </i-col>-->

                        <i-col :span="5">
                            <el-button size="small" type="primary" @click="addStudent">
                                <i-icon type="plus-round" size="15"></i-icon>
                                添加学生
                            </el-button>
                            <EditForm ref="addstudent"></EditForm>
                        </i-col>
                    </i-row>
                </i-col>
            </i-row>

        </div>
        <el-table class="StudentTable"
                  :data="studentData"
                  tooltip-effect="dark"
                  border
                  style="width: 100%">
            <el-table-column
                    fixed
                    prop="sno"
                    label="学号"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="sname"
                    label="姓名"
                    width="100">
            </el-table-column>
            <el-table-column
                    prop="gender"
                    label="性别"
                    width="50">
            </el-table-column>
            <el-table-column
                    prop="className"
                    label="班级"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="smajor"
                    label="专业"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="sdepartment"
                    label="所属学院"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="sphone"
                    label="电话"
                    width="200">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="250">
                <template v-slot="scope">
                    <el-button type="text" size="small" @click="check(scope.row)">简历信息</el-button>
                    <el-button type="text" size="small" @click="changePassword(scope.row)">重置密码</el-button>
                    <el-button type="text" size="small" @click="editForm(scope.row)">编辑</el-button>
                    <el-button @click="deleteStudent(scope.row)" type="text" size="small" style="color:red;">删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination class="page"
                       @current-change="change"
                       background
                       layout="prev, pager, next"
                       :page-count="pageNum">
        </el-pagination>
        <EditForm ref="editfrom">
        </EditForm>
        <el-dialog :visible.sync="multiDeleteVisible" title="提示" width="30%">
            <span>确定要删除吗</span>
            <span slot="footer">
          <el-button type="primary" @click="multiDelete" size="small">确 定</el-button>
          <el-button @click="cancel1" size="small">取 消</el-button>
        </span>
        </el-dialog>
        <el-dialog @close="resetResume" :visible.sync="vis" title="查看">
            <div class="layout">
                <div class="layout-content">
                    <i-row>
                        <i-col span="24">
                            <div class="layout-content-main">
                                <div class="in-layout-content">
                                    <!--简历头部-->
                                    <div class="resumewrapp">
                                        <div class="page-title">
                                            <h5>简历预览</h5>
                                            <el-button type="text" v-if="resume.resumeId!==''" @click="save">保存</el-button>
                                        </div>
                                        <!--  简历内容-->
                                        <div class="resumecontent">
                                            <li class="presonBase">
                                                <img data-v-b8fe8cbc=""
                                                     src="https://zcplan.oss-cn-shenzhen.aliyuncs.com/man.png" alt=""
                                                     class="avatar">
                                                <div class="baseMes">
                                                    <h5 class="name" style="font-size: 20px;color: #2a303c">
                                                        {{ studentInfo.sname }}
                                                        <span class="student"
                                                              style="color: #dcb93c;
                              border: 1px solid #fed305;
                             background: -webkit-linear-gradient(left, #fcfff0, #fcfec4);
                             background: linear-gradient(90deg, #fcfff0, #fcfec4);margin-left: 10px">学生</span>
                                                    </h5>
                                                    <div class="personal-mes">{{ studentInfo.gender }}<span
                                                            style="margin: 0 14px;color: #e6e6e6">|</span>
                                                        {{ studentInfo.sdepartment }}<span
                                                                style="margin: 0 14px;color: #e6e6e6">|
                                                        </span>
                                                        {{studentInfo.smajor }}
                                                        <span style="margin: 0 14px;color: #e6e6e6">|</span>
                                                        {{ studentInfo.className }}
                                                    </div>
                                                    <div class="contact-mes">
                                                        <i-icon type="ios-telephone"></i-icon>
                                                        +86{{ studentInfo.sphone }}
                                                    </div>
                                                </div>
                                                <div class="edit-mes"
                                                     style="color: #02B28B;display: flex;margin-left: auto;font-size: 12px; cursor: pointer">
                                                </div>
                                            </li>
                                            <li class="advantage" >
                                                <div class="project-title">
                                                    <div class="left">
                                                        <i-icon type="email" style="color: #02B28B;"></i-icon>
                                                        <h5 class="title">个人优势</h5>
                                                    </div>
                                                    <div class="right">
                                                        <el-button type="text" size="small"
                                                                   @click="editadvan=!editadvan">编辑
                                                        </el-button>
                                                    </div>
                                                </div>
                                                <div class="advantage-con">
                                                    <p v-if="!editadvan" class="edit-show-content"
                                                       style="max-width: 718px;">
                                                        {{ resume.personalAdvantage }}
                                                    </p>
                                                    <el-input
                                                            v-if="editadvan"
                                                            type="textarea"
                                                            :rows="4"
                                                            placeholder="请输入内容"
                                                            v-model="resume.personalAdvantage">
                                                    </el-input>
                                                    <div class="fixed-edit">
                                                        <div class="edit-show-action-wrap">
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li>
                                                <div class="project-title">
                                                    <div class="left">
                                                        <i-icon type="email" style="color: #02B28B"></i-icon>
                                                        <h5 class="title">项目经历</h5>
                                                    </div>
                                                    <div class="right">
                                                        <el-button type="text" size="small"
                                                                   @click="editexper=!editexper">编辑
                                                        </el-button>
                                                    </div>
                                                </div>
                                                <div class="advantage-con">
                                                    <p v-if="!editexper"
                                                       class="edit-show-content" style="max-width: 718px;">
                                                        {{ resume.projectExperience }}
                                                    </p>
                                                    <el-input
                                                            v-if="editexper"
                                                            type="textarea"
                                                            :rows="4"
                                                            placeholder="请输入内容"
                                                            v-model="resume.projectExperience">
                                                    </el-input>
                                                    <div class="fixed-edit">
                                                        <div class="edit-show-action-wrap">
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li>
                                                <div class="project-title">
                                                    <div class="left">
                                                        <i-icon type="email" style="color: #02B28B"></i-icon>
                                                        <h5 class="title">实践经历</h5>
                                                    </div>
                                                    <div class="right">
                                                        <el-button type="text" size="small"
                                                                   @click="editInterexper=!editInterexper">编辑
                                                        </el-button>
                                                    </div>
                                                </div>
                                                <div class="advantage-con">
                                                    <p v-if="!editInterexper"
                                                       class="edit-show-content" style="max-width: 718px;">
                                                        {{ resume.internshipExperience }}
                                                    </p>
                                                    <el-input
                                                            v-if="editInterexper"
                                                            type="textarea"
                                                            :rows="4"
                                                            placeholder="请输入内容"
                                                            v-model="resume.internshipExperience">
                                                    </el-input>
                                                    <div class="fixed-edit">
                                                        <div class="edit-show-action-wrap">
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li>
                                                <div class="project-title">
                                                    <div class="left">
                                                        <i-icon type="email" style="color: #02B28B"></i-icon>
                                                        <h5 class="title">资格证书</h5>
                                                    </div>
                                                    <div class="right">
                                                        <el-button type="text" size="small" @click="editcer=!editcer">
                                                            编辑
                                                        </el-button>
                                                    </div>
                                                </div>
                                                <div class="advantage-con"><p
                                                        v-if="!editcer"
                                                        class="edit-show-content" style="max-width: 718px;">
                                                    {{ resume.certificate }}
                                                </p>
                                                    <el-input
                                                            v-if="editcer"
                                                            type="textarea"
                                                            :rows="4"
                                                            placeholder="请输入内容"
                                                            v-model="resume.certificate">
                                                    </el-input>
                                                    <div class="fixed-edit">
                                                        <div class="edit-show-action-wrap">
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                            <li>
                                                <div class="project-title">
                                                    <div class="left">
                                                        <i-icon type="email" style="color: #02B28B"></i-icon>
                                                        <h5 class="title">获奖经历</h5>
                                                    </div>
                                                    <div class="right">
                                                        <el-button type="text" size="small"
                                                                   @click="editaward=!editaward">编辑
                                                        </el-button>
                                                    </div>
                                                </div>
                                                <div class="advantage-con"><p
                                                        v-if="!editaward"
                                                        class="edit-show-content" style="max-width: 718px;">
                                                    {{ resume.awardExperience }}
                                                </p>
                                                    <el-input
                                                            v-if="editaward"
                                                            type="textarea"
                                                            :rows="4"
                                                            placeholder="请输入内容"
                                                            v-model="resume.awardExperience">
                                                    </el-input>
                                                    <div class="fixed-edit">
                                                        <div class="edit-show-action-wrap">
                                                        </div>
                                                    </div>
                                                </div>
                                            </li>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </i-col>
                    </i-row>
                </div>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import EditForm from "@/components/views/admin/EditForm";
import {deleteOneStudent} from "@/components/utils";

export default {
    name: "StudentInformation",
    components: {EditForm},
    data() {
        return {
            //简历
            vis: false,
            editadvan: false,
            editexper: false,
            editInterexper: false,
            editcer: false,
            editaward: false,
            resume: {
                resumeId: '',
                wechatId: '',
                politicsStatus: '',
                personalAdvantage: '无',
                internshipExperience: '无',
                projectExperience: '无',
                awardExperience: '无',
                certificate: '无'
            },
            studentInfo: {
                sName: '张三',
                gender: '男',
                sno: '123123',
                sMajor: '软件工程',
                sPhone: '11011100',
                sDepartment: '计算机与智能教育学院',
                className: '软件2班'
            },
            fileList: [],
            upload: false,
            multiDeleteVisible: false,
            pageNum: 1,
            pageSize: 9,
            currentPage: 1,
            studentData: [],
            studentData1: {
                id: '',
                sno: '',
                sName: '',
                className: '',
                sPhone: '',
                sDepartment: '',
                sMajor: '',
                gender: '',
            },
            show: false,
            searchIcon: false,
            studentName: '',
            highSearchData: {
                keyword: "",
                className: "",
                sMajor: "",
                sDepartment: "",
            },
            fileData: ''
        }
    },
    methods: {
        resetResume() {
            this.editcer = false
            this.editadvan = false
            this.editexper = false
            this.editInterexper = false
            this.editaward = false
            this.resume = {
                resumeId: '',
                wechatId: '',
                politicsStatus: '',
                personalAdvantage: '无',
                internshipExperience: '无',
                projectExperience: '无',
                awardExperience: '无',
                certificate: '无'
            }
            this.studentInfo = {}
        },
        popdig() {
            this.upload = true
        },
        cancelUp() {
            this.upload = false
            this.fileList = []
        },
        inExcel() {
            this.$refs.upload.submit();
        },
        upLoadHandle(fileObject) {
            let fd = new FormData();
            fd.append("file", fileObject.file);
            this.$http({
                url: this.$http.adornUrl('/excel/leadExcel'),
                method: 'post',
                data: this.$http.adornParams(fd),
                headers: {
                    'UserToken': window.sessionStorage.getItem('Token'),
                }
            }).then(({data}) => {
                if (data && data.status === 200) {
                    this.$message.success(data.msg)
                } else {
                    this.$message.error(data.msg)
                }
            }).catch((error) => {
                console.log(error)
                console.log('出错啦！！！！')
            })
            this.fileList = []
            this.upload = false
        },
        exportExcel() {
            this.$axios({
                method: 'get',
                url: this.$http.adornUrl('/excel/exportBankCheckInfo')/*'http://localhost:8081/excel/exportBankCheckInfo'*/,
                params: '',
                responseType: 'blob',
                headers: {
                    'UserToken': window.sessionStorage.getItem('Token'),
                }
            }).then(res => {
                const link = document.createElement('a')
                let blob = new Blob([res.data], {type: 'application/vnd.ms-excel'});
                link.style.display = 'none'
                link.href = URL.createObjectURL(blob);
                console.log("href:" + link.href)
                let num = ''
                for (let i = 0; i < 10; i++) {
                    num += Math.ceil(Math.random() * 10)
                }
                link.setAttribute('download', num + '.xls')
                document.body.appendChild(link)
                link.click()
                document.body.removeChild(link)
            }, err => {
                console.log(err);
            })
        },
        editForm(row) {
            this.$nextTick(() => {
                // 弹框打开时初始化表单
                this.$refs.editfrom.init(row, true, '编辑')
            })
        },
        change(page) {
            this.currentPage = page;
            if (this.highSearchData.keyword !== '' || this.highSearchData.className !== '' || this.highSearchData.sMajor !== '' || this.highSearchData.sDepartment !== '') {
                this.search()
            } else {
                this.getStudent()
            }
        },
        cancel1() {
            this.multiDeleteVisible = false
            this.student = ''
        },
        multiDelete() {
            this.$http({
                url: this.$http.adornUrl('/admin/delete'),
                method: 'post',
                data: this.student.id,
                headers: {
                    'Content-Type': 'application/json',
                    'charset': 'utf-8'
                }
            }).then(({data}) => {
                if (data && data.errorCode === "200") {
                    this.$message.success(data.message)
                    this.studentData = deleteOneStudent(this.student, this.studentData)
                } else {
                    this.$message.error(data.message)
                }
            }).catch(() => {
                console.log('出错啦！！！！')
            })
            this.multiDeleteVisible = false
        },
        deleteStudent(student) {
            this.multiDeleteVisible = true
            this.student = student
        },
        getStudent() {
            let params = {
                page: this.currentPage,
                limit: this.pageSize
            }
            this.$http({
                url: this.$http.adornUrl('/admin/listStudent'),
                method: 'get',
                params: this.$http.adornParams(params),
            }).then(({data}) => {
                console.log(data)
                if (data && data.errorCode === "200") {
                    this.pageNum = data.data.page
                    this.studentData = data.data.students
                } else {
                    this.$message.error(data.message)
                }
            }).catch(() => {
                console.log('出错啦！！！！')
            })
        },
        reset(data) {
            Object.keys(data).forEach(key => (data[key] = ''))
            return data
        },
        addStudent() {
            this.$nextTick(() => {
                // 弹框打开时初始化表单
                this.$refs.addstudent.init(this.studentData1, false, '添加学生')
                this.studentData1 = this.reset(this.studentData1)
            })
        },
        resetHighSearchData() {
            Object.keys(this.highSearchData).forEach((key) => {
                    this.highSearchData[key] = ''
                }
            )
        },
        search() {
            this.currentPage = 1
            let params = {
                keyword: this.highSearchData.keyword,
                className: this.highSearchData.className,
                sMajor: this.highSearchData.sMajor,
                sDepartment: this.highSearchData.sDepartment,
                page: this.currentPage,
                limit: this.pageSize
            }
            this.$http({
                url: this.$http.adornUrl('/admin/search'),
                method: 'get',
                params: this.$http.adornParams(params),
                headers: {
                    'Content-Type': 'application/json',
                    'charset': 'utf-8'
                }
            }).then(({data}) => {
                //console.log(data)
                if (data && data.errorCode === "200") {
                    this.pageNum = data.data.page
                    this.studentData = data.data.students
                } else {
                    this.$message.error(data.message)
                }
            }).catch((error) => {
                console.log('出错啦！！！！')
                console.log(error)
            })
        },
        check(d) {

            this.$http({
                url: this.$http.adornUrl('/admin/preview/resume/' + d.sno),
                method: 'get',
                headers: {
                    'Content-Type': 'application/json',
                    'charset': 'utf-8'
                }
            }).then(({data}) => {
                console.log(data)
                if (data && data.errorCode === "200") {
                    this.vis = true
                    this.studentInfo = data.data.student
                    if (data.data.resume !== null)
                        this.resume = data.data.resume
                } else {
                    this.$message.error(data.message)
                }
            }).catch((error) => {
                console.log('出错啦！！！！')
                console.log(error)
            })
        },
        changePassword(stu) {
            this.$http({
                url: this.$http.adornUrl('/admin/student/changePassword'),
                method: 'post',
                data: stu.id,
                headers: {
                    'Content-Type': 'application/json',
                    'charset': 'utf-8'
                }
            }).then(({data}) => {
                if (data && data.errorCode === "200") {
                    this.$message.success(data.message)
                } else {
                    this.$message.error(data.message)
                }
            }).catch((error) => {
                console.log('出错啦！！！！')
                console.log(error)
            })
        },
        betterSearch() {
            this.highSearchData.currentPage = this.currentPage
            this.highSearchData.pageSize = this.pageSize
            this.$http({
                url: this.$http.adornUrl('/admin/highSearch'),
                method: 'get',
                params: this.$http.adornParams(this.highSearchData),
                headers: {
                    'UserToken': window.sessionStorage.getItem('Token'),
                }
            }).then(({data}) => {
                if (data) {
                    this.pageNum = data.totalPages
                    this.studentData = data.content
                }
            }).catch((error) => {
                console.log('出错啦！！！！')
                console.log(error)
            })
        },
        cancel() {
            this.show = !this.show
            Object.keys(this.highSearchData).forEach((key) => {
                    this.highSearchData[key] = ''
                }
            )
        },
        save(){
            this.$http({
                url: this.$http.adornUrl('/admin/resume/update'),
                method: 'post',
                data: this.resume,
                headers: {
                    'Content-Type': 'application/json',
                    'charset': 'utf-8'
                }
            }).then(({data}) => {
                if (data && data.errorCode === "200") {
                    this.$message.success(data.message)
                } else {
                    this.$message.error(data.message)
                }
            }).catch((error) => {
                console.log('出错啦！！！！')
                console.log(error)
            })
        }
    },
    created: function () {
        this.getStudent()
    },

}
</script>

<style scoped>
.layout-search {
    min-height: 80px;
    margin: 15px;
    overflow: hidden;
    background: #fff;
    border-radius: 4px;
}

.search {
    padding-top: 20px;
    text-align: center;
}

.StudentTable {
}

.page {
    text-align: center;
}

/*简历*/
.layout {
    border: 1px solid #d7dde4;
    background: #f5f7f9;
}

.layout-content {
    min-height: 400px;
    margin: 20px auto;
    overflow: hidden;
    background: #fff;
    border-radius: 4px;
    max-width: 800px;

}

.layout-content-main {
    padding: 10px;
    background-color: #fff;
}

.layout-copy {
    text-align: center;
    padding: 10px 0 20px;
    color: #9ea7b4;
}

li {
    list-style: none;
}

.resumewrapp .page-title {
    width: 100%;
    height: 50px;
    border-bottom: 1px solid #e6e6e6;
    box-sizing: border-box;
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 0 40px;
}


.preview {
    color: #fff;
    font-size: 12px;
    width: 80px;
    height: 30px;
    line-height: 30px;
    text-align: center;
    background: #02B28B;
    border-radius: 4px;
    cursor: pointer;
    border: 0 none;
}


.presonBase {
    width: 100%;
    min-height: 170px;
    display: flex;
    align-items: center;
}

.baseMes {
    display: flex;
    flex-direction: column;
    margin-left: 40px;

}

.personal-mes {
    margin-top: 20px;
    display: flex;
    align-items: center;
    color: #6a6e74;
    font-size: 14px;
}

.contact-mes {
    font-size: 14px;
    color: #6a6e74;
    margin-top: 14px;
}

.project-title {
    display: flex;
    align-items: center;
    justify-content: space-between;
    height: 56px;
}

.title {
    font-size: 16px;
    color: #2a303c;
    font-weight: 700;
    margin-left: 12px;
}

.left {
    display: flex;
    align-items: center;
}

.project-title .line {
    width: 4px;
    height: 17px;
    background: #02B28B;
}
.advantage-con {
    width: 100%;
    padding: 12px 16px;
    box-sizing: border-box;
    color: #6a6e74;
    text-align: justify;
    display: flex;
    position: relative;
    margin-bottom: 20px;
}

</style>
