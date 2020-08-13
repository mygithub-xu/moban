'use strict'
const merge = require('webpack-merge')
const devEnv = require('./dev.env')

module.exports = merge(devEnv, {
  NODE_ENV: '"testing"',
    // 正式环境
  API_ROOT:'"http://120.25.197.11/api/"'
})
