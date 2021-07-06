
import http from "axios";
import {Message} from 'element-ui';
/**
 * 操作数组，转化类型等方法 2020.10.19
 */
export default {

  //集合相减的方法
  minus (arr1, arr2) {
    let arr3 = [];
    for (var i = 0; i < arr1.length; i++) {
      var flag = true;
      for (var j = 0; j < arr2.length; j++) {
        if (arr1[i] == arr2[j]) flag = false;
      }
      if (flag) {
        arr3.push(arr1[i]);
      }
    }
    return arr3;
  },

  //时间转换器---start
  dateUtils (d) {
    var year = d.getFullYear();
    var month = prefixInteger(d.getMonth());
    var date = prefixInteger(d.getDate());
    var hours = prefixInteger(d.getHours());
    var minutes = prefixInteger(d.getMinutes());
    var seconds = prefixInteger(d.getSeconds());
    return year + '-' + month + '-' + date + ' ' + hours + ':' + minutes + ':' + seconds;
  },
  //自动补零
  prefixInteger (num) {
    if (num > 9) {
      return num;
    }
    return (Array(2).join(0) + num).slice(-2);
  },

  findIds (list) {
    let deletebatch = []
    list.forEach(i => {
      deletebatch.push(i.id)
    });
    return deletebatch
  },

  downFile(url, data,file_name) {
    const token = localStorage.getItem("Token")
    //下载路径
    file_name = !file_name ? '文件.txt' : file_name
    http({
      method: 'post',
      url: url,
      headers:{'dh-Token':token},
      data: data,
      responseType: 'blob'
    }).then(res => {
      if (res.status && res.status == 200) {
        //eslint-disable-next-line
        const blob = new Blob([res.data]);
        //对于<a>标签，只有 Firefox 和 Chrome（内核） 支持 download 属性
        //IE10以上支持blob但是依然不支持download
        if ('download' in document.createElement('a')) {
          //支持a标签download的浏览器
          const link = document.createElement('a')//创建a标签
          link.download = file_name
          link.style.display = 'none'
          link.href = URL.createObjectURL(blob)
          document.body.appendChild(link)
          link.click()//执行下载
          URL.revokeObjectURL(link.href) //释放url
          document.body.removeChild(link)//释放标签
        } else {
          navigator.msSaveBlob(blob, file_name)
        }
      } else {
        Message.warning('下载失败');
      }
    }).catch(res => {
      Message.error("下载失败")
    })
  }
  
}