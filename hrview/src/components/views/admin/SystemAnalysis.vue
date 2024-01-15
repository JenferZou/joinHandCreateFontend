<template>
    <div>
        <i-row style="padding-top: 70px;">
            <i-col :span="12">
                <div id="dau"></div>
                <div style="text-align: center;font-size: 15px"><span>用户日活数</span></div>
            </i-col>
            <i-col :span="12">
                <div id="proportion"></div>
                <div style="text-align: center;font-size: 15px"><span>师生比例</span></div>
            </i-col>
        </i-row>

    </div>
</template>

<script>
export default {
    name: "SystemAnalysis",
    data() {
        return {
            nums: [],
            weeks: [],
            studentNum:1,
            teacherNum:1,
        }
    },
    methods: {
        getDau() {
            this.$http({
                url: this.$http.adornUrl('/admin/dauList'),
                method: 'get',
            }).then(({data}) => {
                if (data) {
                    this.nums = data.data.nums
                    let localDates = data.data.localDates
                    this.weeks = localDates.map(dateString => {
                        const date = new Date(dateString);
                        const options = {weekday: 'long'};
                        return new Intl.DateTimeFormat('zh-CN', options).format(date);
                    })
                    this.showCharts()
                } else {
                    this.$message.error(data.message)
                }
            }).catch(() => {
                console.log('出错啦！！！！')
            })
        },
        getProportion() {
            this.$http({
                url: this.$http.adornUrl('/admin/STFInfo'),
                method: 'get',
            }).then(({data}) => {
                if (data) {
                  this.studentNum=data.data.studentNum
                    this.teacherNum=data.data.teacherNum
                    this.showPie()
                } else {
                    this.$message.error(data.message)
                }
                console.log(this.manager.length)
            }).catch(() => {
                console.log('出错啦！！！！')
            })
        },
        //饼图
        showPie() {
            let myChart = this.$echarts.init(document.getElementById('proportion'))
            myChart.setOption({
                /* title: {
                     text: 'Referer of a Website',
                     left: 'center'
                 },*/
                tooltip: {
                    trigger: 'item'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left'
                },
                series: [
                    {
                        name: '数量',
                        type: 'pie',
                        radius: '50%',
                        data: [
                            {value: this.teacherNum, name: '老师'},
                            {value: this.studentNum, name: '学生'},
                        ],
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            })
        },
        // 展示折线图
        showCharts() {
            // 基于准备好的dom，初始化echarts实例
            let myChart = this.$echarts.init(document.getElementById('dau'))//也可以通过$refs.newCharts的方式去获取到dom实例。
            // 绘制图表
            myChart.setOption({
                // X轴
                xAxis: {
                    type: 'category',//坐标轴类型,类目轴，适用于离散的类目数据。为该类型时类目数据可自动从 series.data 或 dataset.source 中取，或者可通过 xAxis.data 设置类目数据
                    //坐标轴刻度相关设置
                    axisTick: {
                        alignWithLabel: true,//为true时，可以让刻度跟底部的标签内容对齐
                    },
                    data:this.weeks,//X轴的刻度数据
                    name: "时间",//坐标轴名称
                    nameLocation: "start",//坐标轴名称显示位置
                    //坐标轴名称的文字样式
                    nameTextStyle: {
                        align: "center",//文字水平对齐方式，默认自动
                        verticalAlign: "top",//文字垂直对齐方式，默认自动
                        lineHeight: 28,//行高
                        fontSize: 10,//字体大小
                        color: "rgba(0, 0, 0, 1)"//字体颜色
                    },
                    //坐标轴刻度标签的相关设置
                    axisLabel: {
                        interval: 'auto'//坐标轴刻度标签的显示间隔，在类目轴中有效。可以设置成 0 强制显示所有标签,如果设置为 1，表示『隔一个标签显示一个标签』，如果值为 2，表示『隔两个标签显示一个标签』，以此类推。
                    }
                },
                // Y轴
                yAxis: {
                    type: 'value',//坐标轴类型,'value' 数值轴，适用于连续数据

                },
                //直角坐标系内绘图网格,简单理解指的就是这个折线图。
                grid: {
                    left: 50//grid 组件离容器左侧的距离
                },
                // 数据
                series: [
                    {
                        data: this.nums,//折线图要展示的数据
                        type: 'line'//数据以什么样的类型展示。line为折线图
                    }
                ]
            })
        }
    },
    mounted() {

    },
    created() {
        this.getDau()
        this.getProportion()
    }
}
</script>

<style scoped>
#dau {
    margin: 0 auto;
    width: 550px;
    height: 400px;
}

#proportion {
    margin: 0 auto;
    width: 550px;
    height: 400px;
}
</style>