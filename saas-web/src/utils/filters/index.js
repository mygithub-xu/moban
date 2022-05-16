import dateUtils from '@/utils/common/dateUtils'
import Vue from 'vue'

const filter = {
  // 自定义过滤器
  '1000': function (val, unit) {
    if (!unit) {
      unit = ''
    }
    if (typeof (val) === 'number') {
      return (val).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,') + ""
    }
  },
  //使用实例：{{price | 10('个')}}，
  '10': function (val, unit) {
    if (!unit) {
      unit = ''
    }
    if (typeof (val) === 'number') {
      return (val).toFixed(0).replace(/(\d)(?=(?:\d{3})+$)/g, '$1,') + unit
    }

  },

  'getLongDate': function (val) {
    if (!val) {
      return null;
    }
    return dateUtils.getLongDate(val)
  },


  'getLongDateTime': function (val) {
    if (!val) {
      return null;
    }
    return dateUtils.getLongDateTime(val)
  },

  'getStandardDate': function (val) {
    if (!val) {
      return null;
    }
    return dateUtils.getStandardDate(val)
  },

  'getStandardDateTime': function (val) {
    if (!val) {
      return null;
    }
    return dateUtils.getStandardDateTime(val)
  },
}
for (const key in filter) {
  if (filter.hasOwnProperty(key)) {
    const element = filter[key]
    Vue.filter(key, element)
  }
}