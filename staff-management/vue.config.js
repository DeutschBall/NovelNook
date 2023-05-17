var webpack = require('webpack');
module.exports = {
  parallel: false,
  publicPath: "/admin",
  configureWebpack: config => {
    config.plugins.push(new webpack.NormalModuleReplacementPlugin(/element-ui\/lib\/locale\/lang\/zh-CN/, 'element-ui/lib/locale/lang/en'))

  }
}