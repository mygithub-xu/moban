let publicPath = './'
if (process.env.NODE_ENV === 'production'||process.env.NODE_ENV === 'test') {
  // 生产公共路径
  publicPath = './'
}
module.exports = {
  publicPath: publicPath, // 构建好的文件输出到哪里
  outputDir: "dist",
  lintOnSave: true,
  runtimeCompiler: false, 
  transpileDependencies: [
  ], 
  productionSourceMap: false,
  chainWebpack: () => {},
  configureWebpack: () => {},
  css: {
      extract: true,
      sourceMap: false,
      loaderOptions: {},
      modules: false
  }, 
  parallel: require("os").cpus().length > 1,
  pwa: {},
  devServer: {
      open: true,
      host: "0.0.0.0",
      port: 8000,
      hotOnly: false, 
      proxy: null,
      before: app => {}
  }, // 第三方插件配置

  pluginOptions: {
      // ...
  }
}