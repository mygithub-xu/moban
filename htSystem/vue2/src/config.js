let BASE_URL = ""; //这里是一个默认的url，可以没有
switch (process.env.NODE_ENV) {
    case 'development':
        // BASE_URL = "http://localhost:9000/moban/" //这里是本地的请求
        BASE_URL = "https://www.whfch.icu/moban/v1/" 
        break
    case 'test':
        BASE_URL = "https://www.whfch.icu/moban/v1/" // 测试环境
        break
    case 'production':
        BASE_URL = "https://www.whfch.icu/moban/v1/" //生产环境
        break
}

export default BASE_URL;