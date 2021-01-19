
/**
 * 操作数组，转化类型等方法 2020.10.19
 */
export default {

    //集合相减的方法
    minus(arr1, arr2) { 
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
    dateUtils(d){
        var year=d.getFullYear();
        var month=prefixInteger(d.getMonth());
        var date=prefixInteger(d.getDate());
        var hours=prefixInteger(d.getHours());
        var minutes=prefixInteger(d.getMinutes());
        var seconds=prefixInteger(d.getSeconds());
        return year + '-' + month + '-'  + date + ' '  + hours + ':'  + minutes + ':'  + seconds;
    },
    //自动补零
    prefixInteger(num) {
        if(num>9){
            return num;
        }
        return (Array(2).join(0) + num).slice(-2);
    },

    findIds(list) {
      let deletebatch = []
      list.forEach(i => {
        deletebatch.push(i.id)
      });
      return deletebatch
    }

}