<template>
    <el-dialog :title="title" :visible.sync="dialogVisible" :closeOnClickModal="false">
        <el-form :model="studentData" ref="studentData" class="demo-form-inline" label-width="80px" :rules="rules">
            <el-form-item label="学号" prop="sno">
                <el-input v-model.number="studentData.sno" placeholder="学号" maxlength="12"
                          :disabled="isdisabled"></el-input>
            </el-form-item>

            <el-form-item label="姓名" prop="sname">
                <el-input v-model="studentData.sname" placeholder="姓名" size="small"></el-input>
            </el-form-item>

            <el-form-item label="班级" prop="classname">
                <el-input v-model="studentData.className" placeholder="班级" size="small"></el-input>
            </el-form-item>

            <el-form-item label="电话" prop="sphone">
                <el-input v-model.number="studentData.sphone" placeholder="电话" size="small"
                          maxlength="12"></el-input>
            </el-form-item>

            <el-form-item label="所属学院" prop="sdepartment">
                <el-input v-model="studentData.sdepartment" placeholder="所属学院" size="small"></el-input>
            </el-form-item>

            <el-form-item label="专业" prop="smajor">
                <el-input v-model="studentData.smajor" placeholder="专业" size="small"></el-input>
            </el-form-item>

            <el-form-item label="性别">
                <el-select size="small" v-model="studentData.gender" placeholder="性别">
                    <el-option label="男" value="男"></el-option>
                    <el-option label="女" value="女"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button @click="cancel" size="small">取消</el-button>
                <el-button type="primary" @click="modify('studentData')" size="small">
                    {{ title === '添加员工' ? '保存' : '修改' }}
                </el-button>
            </el-form-item>
        </el-form>
    </el-dialog>
</template>

<script>
export default {
    name: "EditForm",
    data() {
        return {
            title: '',
            isdisabled: '',
            studentData: '',
            dialogVisible: false,
            rules: {
                sno: [
                    {required: true, message: '学号不能为空', trigger: 'blur'},
                    {pattern: /\b\d{6}\b/, message: '请输入正确的学号', trigger: 'blur'}
                ],
                sname: [
                    {required: true, message: '姓名不能为空', trigger: 'blur'}
                ],
                className: [
                    {required: true, message: '班级不能为空', trigger: 'blur'}
                ],
                sphone: [
                    {required: true, message: '电话不能为空', trigger: 'blur'},
                    {pattern: /^1[3|5|7|8|9]\d{9}$/, message: '请输入正确的号码格式', trigger: 'blur'}
                ],
                sdepartment: [
                    {required: true, message: '学院信息不能为空', trigger: 'blur'}
                ],
                smajor: [
                    {required: true, message: '专业不能为空', trigger: 'blur'}
                ],
                rphone: [
                    {required: true, message: '电话不能为空', trigger: 'blur'},
                    {pattern: /^1[3|5|7|8|9]\d{9}$/, message: '请输入正确的号码格式', trigger: 'blur'}
                ]
            }
        }
    },
    methods: {
        init(data, isdisabled, title) {
            this.dialogVisible = true
            this.studentData = data
            this.isdisabled = isdisabled
            this.title = title
        },
        cancel() {
            this.dialogVisible = false
        },
        modify(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    if ('' === this.studentData.id) {
                        this.$http({
                            url: this.$http.adornUrl('/admin/add'),
                            method: 'post',
                            data: this.$http.adornData(this.studentData),
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
                        }).catch(() => {
                            console.log('出错啦！！！！')
                        })
                    } else {
                        this.$http({
                            url: this.$http.adornUrl('/admin/modify'),
                            method: 'post',
                            data: this.$http.adornData(this.studentData),
                            headers: {
                                'Content-Type': 'application/json',
                                'charset': 'utf-8'
                            }
                        }).then(({data}) => {
                            console.log(data)
                            if (data && data.errorCode === "200") {
                                this.$message.success(data.message)
                            } else {
                                this.$message.error(data.message)
                            }
                        }).catch(() => {
                            console.log('出错啦！！！！')
                        })
                    }
                    this.dialogVisible = false
                } else {
                    return false;
                }
            });
        }
    }
}
</script>

<style scoped>

</style>
