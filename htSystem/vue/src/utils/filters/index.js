const filter = {
        // 自定义过滤器

        
        '1000': function (val,unit) {
        if(!unit){
            unit=''
        }
            if (typeof (val) === 'number') {
                return (val).toFixed(2).replace(/(\d)(?=(\d{3})+\.)/g, '$1,')+""
            }
    
        },
        //使用实例：{{price | 10('个')}}，
        '10': function (val,unit) {
        if(!unit){
            unit=''
        }
                if (typeof (val) === 'number') {
        
                    return (val).toFixed(0).replace(/(\d)(?=(?:\d{3})+$)/g, '$1,')+unit
        
                }
        
            }
    
    }
    
    export default filter