axios.defaults.baseURL = 'http://localhost:8080/exercise_library'

// 添加请求拦截器
axios.interceptors.request.use(
  function (config) {
    // 在发送请求之前做些什么
    let userid = sessionStorage.getItem('userid')
    if (userid != null) {
      config.headers.auth = userid
    }
    return config
  },
  function (error) {
    // 对请求错误做些什么
    return Promise.reject(error)
  }
)

// 添加响应拦截器
axios.interceptors.response.use(
  function (response) {
    // 2xx 范围内的状态码都会触发该函数。
    // 对响应数据做点什么
    //我们的Http响应状态码都是200的，403是我们自己定义的码，与Http状态码没关系
    if (response.data.code == 403) {
      location.href = 'login.html'
    }
    return response
  },
  function (error) {
    // 超出 2xx 范围的状态码都会触发该函数。
    // 对响应错误做点什么
    return Promise.reject(error)
  }
)