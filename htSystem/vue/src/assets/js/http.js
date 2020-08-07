import axios from 'axios'

import qs from 'qs'

// import 'es6-promise'

//封装get方法
export function get(url, params) {

   return new Promise((resolve, reject) => { axios.get(url, { params: params

                                 }).then(res => { resolve(res.data)

                                 }).catch(err => { reject(err) })

                })

      }

//封装post方法
export function post(url, data) {

   return new Promise((resolve, reject) => {

    axios({
        url: url,
        method: 'post',
        transformRequest: [function (data) {
          // 对data进行任意转换处理
          return qs.stringify(data)
        }],
        data: data
        }).then(res => { resolve(res.data)

        }).catch(err => { reject(err) })

        })

     }

//封装delete方法
export function del(url,params={}){
return new Promise((resolve,reject) => {
    axios.delete(url,{
    params:params
    })
    .then(response => {
        resolve(response.data);
    })
    .catch(err => {
        reject(err)
    })
})
}

//封装patch方法
export function patch(url,data = {}){
    return new Promise((resolve,reject) => {
      axios.patch(url,data)
        .then(response => {
          resolve(response.data);
        },err => {
          reject(err)
        })
    })
  }