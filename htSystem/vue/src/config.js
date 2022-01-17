let BASE_URL = ""
let webSocker_URL = ""
switch (process.env.NODE_ENV) {
    case 'development':
        // BASE_URL = "http://localhost:9000/moban/" //这里是本地的请求
        BASE_URL = "http://123.60.5.25/moban/"
        webSocker_URL = "localhost:9000/moban/"
        break
    case 'test':
        BASE_URL = "http://123.60.5.25/moban/" // 测试环境
        webSocker_URL = "123.60.5.25/moban/"
        break
    case 'production':
        BASE_URL = "http://123.60.5.25/moban/" //生产环境
        webSocker_URL = "123.60.5.25/moban/"
        break
}

export default BASE_URL;