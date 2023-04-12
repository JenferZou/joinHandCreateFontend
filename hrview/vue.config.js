const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer:{
    open:true,
    //host:'120.78.205.174',
    host:'localhost',
    port:'80'
  },

 // publicPath: './',
  outputDir: "dist", // 输出文件目录
  lintOnSave: false, // eslint 是否在保存时检查
  assetsDir: 'static', // 配置js、css静态资源二级目录的位置
})




