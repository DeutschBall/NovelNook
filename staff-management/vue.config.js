var webpack = require('webpack');
const {
  defineConfig
} = require('@vue/cli-service')
module.exports = defineConfig({
      lintOnSave: false,
      transpileDependencies: true,
      devServer: {

        port: 8081 // 此处修改你想要的端口号

      },
      configureWebpack: config => {
        config.plugins.push(new webpack.NormalModuleReplacementPlugin(/element-ui\/lib\/locale\/lang\/zh-CN/, 'element-ui/lib/locale/lang/en'))

      }
})