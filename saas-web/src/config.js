let BASE_URL = ""
let webSocker_URL = ""
switch (process.env.NODE_ENV) {
    case 'development':
        BASE_URL = "http://localhost:9000/saas/" //这里是本地的请求
        webSocker_URL = "localhost:9000/saas/"
        break
    case 'test':
        BASE_URL = "http://123.60.5.25/saas/" // 测试环境
        webSocker_URL = "123.60.5.25/saas/"
        break
    case 'production':
        BASE_URL = "http://123.60.5.25/saas/" //生产环境
        webSocker_URL = "123.60.5.25/saas/"
        break
}

export default BASE_URL;