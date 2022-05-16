let respData = {
  flag: true,
  message: '',
  data: ''
}

//输入字符串长度校验
export function validLength (str, min, max, callback) {
  if (str.toString().length >= min && str.toString().length <= max) {
    return callback()
  } else {
    return callback(new Error('长度需要在' + min + '-' + max + '之间'))
  }
}

//输入数字大小校验
export function validSize (num, min, max, callback) {
  if (num >= min && num <= max) {
    return callback()
  } else {
    return callback(new Error('大小需要在' + min + '-' + max + '之间'))
  }
}

//校验手机号
export function validPhoneNum (num, callback) {
  let zz = /^1[345678]\d{9}$/
  let match = num.toString().match(zz);
  if (match === null) {
    return callback(new Error('您输入的手机号有误'))
  } else {
    return callback()
  }
}

//校验邮箱正确与否
export function validEmail (num, callback) {
  let zz = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
  let match = num.toString().match(zz);
  if (match === null) {
    return callback(new Error('您输入的邮箱号有误'))
  } else {
    return callback()
  }
}

//校验身份证号正确与否
export function validIdNum (num, callback) {
  let zz = /^[1-9]\d{5}(?:18|19|20)\d{2}(?:(?:0[1-9])|(?:1[0-2]))(?:(?:[0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/
  let match = num.toString().match(zz);
  if (match === null) {
    return callback(new Error('您输入的身份证号码有误'))
  } else {
    return callback()
  }
}
//金额校验
export function validMoney (money, callback) {
  let zz = /^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$/
  let match = money.toString().match(zz);
  if (match === null) {
    return callback(new Error('您输入的金额有误'))
  } else {
    return callback()
  }
}

