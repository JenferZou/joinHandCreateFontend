const {defineConfig} = require('@vue/cli-service')


module.exports = defineConfig({
    transpileDependencies: true,
    devServer: {
        proxy: {
            '/api': {
                target: 'http://localhost:8088',
                changeOrigin: true,
                pathRewrite: {
                    '^/api': ''
                }
            },
        }
    },

    // publicPath: './',
    outputDir: "dist", // 输出文件目录
    lintOnSave: false, // eslint 是否在保存时检查
    assetsDir: 'static', // 配置js、css静态资源二级目录的位置
})






