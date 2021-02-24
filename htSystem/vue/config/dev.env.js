'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  // 开发环境
  EVN_CONFIG:'"dev"',
  API_ROOT:'"http://localhost:4040/moban/"',
  // API_ROOT:'"http://47.94.8.32:4040/moban/"',

  //该环境下各种变量在此处编写

})
