<template>
    <div>
        <i-row class="search">
            <i-col :span="12">
                <i-row>
                    <i-col :span="16">
                        <el-input @keyup.enter.native="search" size="small" v-model="keyword"
                                  placeholder="请通过工号或者姓名搜索"
                                  clearable prefix-icon="el-icon-search">
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
                <i-col :offset="6" :span="2">
                    <el-button size="small" type="primary" @click="uploadDialogVisible=true">
                        <i-icon type="ios-cloud-upload-outline" size="15"></i-icon>
                        通过excel文件导入
                    </el-button>
                </i-col>
                <i-col :offset="4" :span="2">
                    <el-button size="small" type="success" @click="downloadTeacherExcel"
                               v-loading.fullscreen.lock="downloadTeacherExcelLoading">
                        <i-icon type="ios-cloud-download-outline" size="15"></i-icon>
                        导出老师到excel
                    </el-button>
                </i-col>
                <i-col :offset="5" :span="3">
                    <el-button size="small" type="primary" @click="dialogVisible=true">
                        <i-icon type="plus-round" size="15"></i-icon>
                        添加老师
                    </el-button>
                </i-col>
            </i-col>
        </i-row>
        <el-table
                border
                ref="multipleTable"
                v-loading="loading"
                :data="teacher"
                tooltip-effect="dark"
                style="width: 100%"
                @selection-change="handleSelectionChange">
            <el-table-column
                    prop="no"
                    label="工号"
                    width="200">
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="姓名"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="department"
                    label="学院"
                    width="200">
            </el-table-column>
            <el-table-column
                    prop="major"
                    label="专业"
                    width="230">
            </el-table-column>
            <el-table-column
                    prop="phone"
                    label="电话"
                    width="200"
            >
            </el-table-column>

            <el-table-column
                    fixed="right"
                    label="操作"
                    width="200">
                <template v-slot="scope">
                    <el-button type="text" size="small" @click="changePassword(scope.row)">重置密码</el-button>
                    <el-button type="text" size="small" @click="editForm(scope.row)">编辑</el-button>
                    <el-button @click="deleteContest(scope.row)" type="text" size="small" style="color:red;">删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <i-row>
            <el-dialog :visible.sync="multiDeleteVisible1" title="提示" width="30%">
                <span>确定要删除吗</span>
                <span slot="footer">
          <el-button type="primary" @click="multiDelete1" size="small">确 定</el-button>
          <el-button @click="multiDeleteVisible1 = false" size="small">取 消</el-button>
        </span>
            </el-dialog>
            <el-pagination
                    class="page"
                    @current-change="change"
                    background
                    layout="prev, pager, next"
                    :page-count="pageNum">
            </el-pagination>
        </i-row>
        <el-dialog @close="cancel" :title="title" :visible.sync="dialogVisible" :closeOnClickModal="false">
            <el-form :model="editData" ref="studentData" class="demo-form-inline" label-width="80px">
                <el-form-item label="工号" prop="no">
                    <el-input v-model.number="editData.no" placeholder="工号" maxlength="12"
                              disabled></el-input>
                </el-form-item>

                <el-form-item label="姓名" prop="name">
                    <el-input v-model="editData.name" placeholder="姓名" size="small"></el-input>
                </el-form-item>

                <el-form-item label="电话" prop="phone">
                    <el-input v-model.number="editData.phone" placeholder="电话" size="small"
                              maxlength="12"></el-input>
                </el-form-item>

                <el-form-item label="所属学院" prop="department">
                    <el-input v-model="editData.department" placeholder="所属学院" size="small"></el-input>
                </el-form-item>

                <el-form-item label="专业" prop="major">
                    <el-input v-model="editData.major" placeholder="专业" size="small"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button @click="cancel" size="small">取消</el-button>
                    <el-button v-if="tag===1" type="primary" @click="modify" size="small">
                        保存
                    </el-button>
                    <el-button v-if="tag===0" type="primary" @click="add" size="small">
                        保存
                    </el-button>
                </el-form-item>
            </el-form>
        </el-dialog>

        <el-dialog
                title="提示"
                :visible.sync="uploadDialogVisible"
                width="30%"
        >
            <i class="el-icon-warning"></i>
            <span>请确认上传的excel表头正确，以下为示例表头</span>
            <br>
            <br>
            <span>姓名 | 专业 | 电话 | 所属部门</span>
            <span slot="footer" class="dialog-footer">
    <el-button @click="uploadDialogVisible = false">取 消</el-button>
        <br>
        <br>
    <el-upload
            class="upload-demo"
            action=""
            accept=".xlsx,.xls"
            :http-request="uploadTeacherExcel"
            :show-file-list="false"
            :multiple="false"
            :before-upload="beforeUpload"
    >
      <el-button type="primary">
        <i-icon type="ios-cloud-upload-outline" size="15"></i-icon>
        开始导入
      </el-button>
    </el-upload>
        </span>
        </el-dialog>
    </div>
</template>

<script>

export default {
    name: "TeacherManager",
    data() {
        return {
            loading: true,
            downloadTeacherExcelLoading: false,
            uploadDialogVisible: false,
            dialogVisible: false,
            editData: {},
            multiDeleteVisible1: false,
            multipleSelectionFlag: false,
            multiDeleteVisible: false,
            multipleSelection: [],
            teacher: [],
            keyword: '',
            title: '',
            currentPage: 1,
            pageSize: 6,
            pageNum: 1,
            tag: 0,
            contest: {
                name: '',
                startTime: '',
                content: '',
            },
            idParams: [],
            dcontest: '',
        }
    },
    methods: {
        change(page) {
            this.currentPage = page;
            if (this.title !== '')
                this.search()
            else
                this.getAllInformation()
        },
        editForm(d) {
            this.$http({
                url: this.$http.adornUrl('/admin/teacher/preedit/' + d.no),
                method: 'get',
            }).then(({data}) => {
                console.log(data)
                if (data && data.errorCode === "200") {
                    this.editData = data.data
                    this.dialogVisible = true
                    this.tag = 1
                } else {
                    this.$message.error(data.message)
                }
            }).catch(() => {
                console.log('出错啦！！！！')
            })
        },
        deleteContest(contest) {
            this.multiDeleteVisible1 = true
            this.dcontest = contest
        },
        reset(data) {
            Object.keys(data).forEach(key => (data[key] = ''))
            return data
        },
        addContest() {
            this.$nextTick(() => {
                // 弹框打开时初始化表单
                this.$refs.informationedit.init(this.contest)
                this.contest = this.reset(this.contest)
            })
        },
        search() {
            this.currentPage = 1
            this.getAllInformation()
        },
        popDelete() {
            this.multiDeleteVisible = true
        },
        handleSelectionChange(val) {
            // console.log(val);
            this.multipleSelection = val;
            this.multipleSelectionFlag = this.multipleSelection.length !== 0;
        },
        deleteOneProject(id, projectData) {
            for (let i = 0; i < projectData.length; i++) {
                if (id === projectData[i].id) {
                    projectData.splice(i, 1)
                }
            }
            return projectData;
        },
        multiDelete1() {
            this.$http({
                url: this.$http.adornUrl('/admin/teacher/delete'),
                method: 'post',
                data: this.dcontest.no,
                headers: {
                    'Content-Type': 'application/json',
                    'charset': 'utf-8'
                }
            }).then(({data}) => {
                if (data && data.errorCode === "200") {
                    this.$message.success(data.message)
                    this.getAllInformation()
                } else {
                    this.$message.error(data.message)
                }
            }).catch(() => {
                console.log('出错啦！！！！')
            })
            this.multiDeleteVisible1 = false
        },
        getAllInformation() {
            let params = {
                page: this.currentPage,
                limit: this.pageSize,
                keyword: this.keyword
            }
            this.$http({
                url: this.$http.adornUrl('/admin/teacher/list'),
                method: 'get',
                params: this.$http.adornParams(params),
            }).then(({data}) => {
                if (data && data.errorCode === "200") {
                    this.pageNum = data.data.page
                    this.teacher = data.data.teachers
                    this.loading = false
                }
            }).catch(() => {
                console.log('出错啦！！！！')
            })
        },
        modify() {
            this.$http({
                url: this.$http.adornUrl('/admin/teacher/modify'),
                method: 'post',
                data: this.editData,
                headers: {
                    'Content-Type': 'application/json',
                    'charset': 'utf-8'
                }
            }).then(({data}) => {
                if (data && data.errorCode === "200") {
                    this.$message.success(data.message)
                    this.dialogVisible = false
                    this.getAllInformation()
                } else {
                    this.$message.error(data.message)
                }

            }).catch(() => {
                console.log('出错啦！！！！')
            })
        },
        add() {
            this.$http({
                url: this.$http.adornUrl('/admin/teacher/add'),
                method: 'post',
                data: this.editData,
                headers: {
                    'Content-Type': 'application/json',
                    'charset': 'utf-8'
                }
            }).then(({data}) => {
                if (data && data.errorCode === "200") {
                    this.$message.success(data.message)
                    this.dialogVisible = false
                    this.getAllInformation()
                } else {
                    this.$message.error(data.message)
                }

            }).catch(() => {
                console.log('出错啦！！！！')
            })
        },
        downloadTeacherExcel() {
            this.downloadTeacherExcelLoading = true;
            this.$http({
                url: this.$http.adornUrl('/admin/excel/download'),
                method: 'get',
            }).then(({data}) => {
                if (data && data.errorCode === "200") {
                    this.downloadTeacherExcelLoading = false;
                    this.$message.success("成功导出数据到桌面")
                } else {
                    this.downloadTeacherExcelLoading = false;
                    this.$message.error(data.message)
                }

            }).catch(() => {
                console.log('出错啦！！！！')
            })
        },
        uploadTeacherExcel(file) {
            let formData = new FormData();
            formData.append('file', file.file);//传文件
            this.$http({
                url: this.$http.adornUrl('/admin/excel/upload'),
                method: 'post',
                data: formData
            }).then(({data}) => {
                if (data && data.errorCode === "200") {
                    this.downloadTeacherExcelLoading = false;
                    this.$message.success("成功导入数据")
                    this.getAllInformation()
                } else {
                    this.downloadTeacherExcelLoading = false;
                    this.$message.error(data.message)
                }
            }).catch(() => {
                console.log('出错啦！！！！')
            })
            return false  //屏蔽了action的默认上传
        },
        beforeUpload(file) {
            //找到文件名中最后一个点的位置。
            // 获取从上一步找到的点位置开始到字符串末尾的子字符串
            const fileSuffix = file.name.substring(file.name.lastIndexOf(".") + 1);

            const whiteList = ["xls", "xlsx"];

            if (whiteList.indexOf(fileSuffix) === -1) {
                this.$message.error('上传文件只能是xls、xlsx格式');
                return false;
            }

            const isLt = file.size / 1024 / 1024 < 5;

            if (!isLt) {
                this.$message.error('上传文件大小不能超过5MB');
                return false;
            }

        },

        cancel() {
            this.dialogVisible = false
            this.editData = {}
            this.tag = 0

        },
        changePassword(tea) {
            this.$http({
                url: this.$http.adornUrl('/admin/teacher/resetPassword'),
                method: 'post',
                data: tea.no,
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
</style>