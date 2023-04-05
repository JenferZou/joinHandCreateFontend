<template>
    <div>
        <i-row>
            <i-col :span="12">
                <i-row>
                    <i-col :span="16" >
                        <el-input size="small" v-model="studentName" placeholder="请输入学生姓名..." clearable prefix-icon="el-icon-search">
                        </el-input>
                    </i-col>
                    <i-col :span="3">
                        <el-button type="primary" size="small" @click="search"><i-icon type="search" size="small"></i-icon>搜索</el-button>
                    </i-col>
                    <i-col :span="5">
                        <el-button @click="clicktrue" type="primary" size="small">
                            <i-icon v-show="searchIcon" type="chevron-up"></i-icon>
                            <i-icon type="chevron-down" v-show="!searchIcon"></i-icon>
                            高级搜索</el-button>
                    </i-col>
                </i-row>
            </i-col>
            <i-col :span="12">
                <i-row type="flex" justify="end">
                    <i-col :span="5">
                        <el-button type="success" size="small"> <i-icon size="15" type="arrow-down-a"></i-icon>导入数据</el-button>
                    </i-col>
                    <i-col :span="5">
                        <el-button type="success" size="small"><i-icon size="15" type="arrow-up-a"></i-icon>导出数据</el-button>
                    </i-col>
                    <i-col :span="5">
                        <el-button size="small" type="primary" @click="addStudent">
                            <i-icon type="plus-round" size="15"></i-icon>
                            添加学生</el-button>
                        <EditForm ref="addstudent"></EditForm>
                    </i-col>
                </i-row>
            </i-col>
        </i-row>
        <advanced-search v-show="show"></advanced-search>
    </div>
</template>

<script>
import AdvancedSearch from "@/components/views/admin/AdvancedSearch";
import EditForm from "@/components/views/admin/EditForm";
export default {
    name: "StudentSearch",
    components: {EditForm, AdvancedSearch},
    data(){
        return{
            studentData:{
                id:'',
                sno:'',
                sName:'',
                className:'',
                sPhone:'',
                sDepartment:'',
                sMajor:'',
                gender:'',
                mentor:'',
                relatives:'',
                rPhone:'',
                score:'',
                star:'',
                blood:'',
                isGraduate:''
            },
            show:false,
            searchIcon:false,
            studentName:''
        }
    },
    methods:{
        reset(data){
            Object.keys(data).forEach(key=>(data[key]=''))
            return data
        },
        addStudent(){
            this.$nextTick(() => {
                // 弹框打开时初始化表单
                this.$refs.addstudent.init(this.studentData,false,'添加员工')
                this.studentData=this.reset(this.studentData)
            })
        },
        clicktrue(){
            this.searchIcon=!this.searchIcon
            this.show=!this.show
        }
    }
}
</script>

<style scoped>

</style>
