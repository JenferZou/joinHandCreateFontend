<template>
  <div>
  <el-table class="StudentTable"
      :data="tableData"
      border
      style="width: 100%">
    <el-table-column
        fixed
        prop="date"
        label="日期"
        width="150">
    </el-table-column>
    <el-table-column
        prop="name"
        label="姓名"
        width="120">
    </el-table-column>
    <el-table-column
        prop="province"
        label="省份"
        width="120">
    </el-table-column>
    <el-table-column
        prop="city"
        label="市区"
        width="120">
    </el-table-column>
    <el-table-column
        prop="address"
        label="地址"
        width="300">
    </el-table-column>
    <el-table-column
        prop="zip"
        label="邮编"
        width="120">
    </el-table-column>
    <el-table-column
        prop="zip"
        label="邮编"
        width="120">
    </el-table-column>
    <el-table-column
        prop="zip"
        label="邮编"
        width="120">
    </el-table-column>
    <el-table-column
        fixed="right"
        label="操作"
        width="100">
      <template v-slot="scope">
        <el-button type="text" size="small">编辑</el-button>
        <el-button @click="handleClick(scope.row)" type="text" size="small" style="color:red;">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
  <el-pagination class="page"
                 @current-change="change"
      background
      layout="prev, pager, next"
      :total="1000">
  </el-pagination>
  </div>
</template>

<script>
export default {
  name: "StudentInformation",
  methods: {
    change(page){
      this.page=page;
      console.log(page)
    },
    handleClick(row) {
      console.log(row);
    },
    getStudent(){
      this.$http({
        url: this.$http.adornUrl('/admin'),
        method: 'get',
        params:this.$http.adornParams(this.page)
      }).then(({data}) => {
        if (data && data.code === 200) {
          this.studentData = data.obj
        }
      })
    }
  },
  created: function () {
    this.getStudent()
  },
  data() {
    return {
      page:1,
      studentData:[
        {
          sno:'',
          name:'',
          className:'',
          department:'',
          score:'',
          major:'',
          phone:'',
          relatives:'',
          relativesPhone:'',
          mentor:'',
          star:'',
          blood:'',
        }
      ],
      tableData: [{
        date: '2016-05-02',
        name: '王小虎',
        province: '上海',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1518 弄',
        zip: 200333
      }, {
        date: '2016-05-04',
        name: '王小虎',
        province: '上海',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1517 弄',
        zip: 200333
      }, {
        date: '2016-05-01',
        name: '王小虎',
        province: '上海',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1519 弄',
        zip: 200333
      }, {
        date: '2016-05-03',
        name: '王小虎',
        province: '上海',
        city: '普陀区',
        address: '上海市普陀区金沙江路 1516 弄',
        zip: 200333
      },
        {
          date: '2016-05-03',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1516 弄',
          zip: 200333
        },
        {
          date: '2016-05-03',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1516 弄',
          zip: 200333
        },
        {
          date: '2016-05-03',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1516 弄',
          zip: 200333
        }
      ]
    }
  }
}
</script>

<style scoped>
.StudentTable{
  margin-top: 20px;
}
.page{
  margin-top: 15px;
  text-align: center;
}
</style>
