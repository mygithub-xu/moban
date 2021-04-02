export default {
  //单个参数
  getUrlKey (name, url) {

    return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(url) || [, ""])[1].replace(/\+/g, '%20')) || null;
  },
  //多个参数s
  getUrlKeys (list, url) {
    if (!list) {
      return null;
    }
    let returnList = [];
    list.forEach(v => {
      let aaa = decodeURIComponent((new RegExp('[?|&]' + v + '=' + '([^&;]+?)(&|#|;|$)').exec(url) || [, ""])[1].replace(/\+/g, '%20')) || null;
      let vs = {
        v: aaa
      }
      returnList.push(vs)
    });

    return returnList;
  },

  //解析url后面的参数,返回json
  getUrlParam (paramsString) {
    if (!paramsString) {
      return "";
    }
    var result = {},
      reg = /([^&=]+)=([^&]*)/g, m;
    while (m = reg.exec(paramsString)) {
      result[m[1]] = m[2];
    }
    return result;
  }
}