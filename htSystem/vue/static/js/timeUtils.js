export default {
      //时间转换器---start
      dateUtils(d){
        var year=d.getFullYear();
        var month=this.prefixInteger(d.getMonth());
        var date=this.prefixInteger(d.getDate());
        var hours=this.prefixInteger(d.getHours());
        var minutes=this.prefixInteger(d.getMinutes());
        var seconds=this.prefixInteger(d.getSeconds());
        return year + '-' + month + '-'  + date + ' '  + hours + ':'  + minutes + ':'  + seconds;
    },

    //自动补零
    prefixInteger(num) {
        if(num>9){
            return num;
        }
        return (Array(2).join(0) + num).slice(-2);
    },
    //时间转换器---end
  }