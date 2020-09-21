'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  // 开发环境
  EVN_CONFIG:'"dev"',
  API_ROOT:'"/api"',

  //该环境下各种变量在此处编写

})
