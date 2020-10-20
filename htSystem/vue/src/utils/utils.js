
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


}