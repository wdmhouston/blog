import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'
import axios from "axios";

axios.defaults.baseURL = process.env.VUE_APP_SERVER;
/**
 * axios拦截器
 */
axios.interceptors.request.use(function (config) {
    console.log('Request parameters：', config);
    // const token = store.state.user.token;
    // if (Tool.isNotEmpty(token)) {
    //     config.headers.token = token;
    //     console.log("请求headers增加token:", token);
    // }
    return config;
}, error => {
    return Promise.reject(error);
});
axios.interceptors.response.use(function (response) {
    console.log('Response parameters：', response);
    return response;
}, error => {
    console.log('Response error：', error);
    // const response = error.response;
    // const status = response.status;
    // if (status === 401) {
    //     // 判断状态码是401 跳转到首页或登录页
    //     console.log("未登录，跳到首页");
    //     store.commit("setUser", {});
    //     message.error("未登录或登录超时");
    //     router.push('/');
    // }
    return Promise.reject(error);
});


const app = createApp(App);
app.use(store).use(router).use(Antd).mount('#app');

console.log('Enviroment NODE_ENV:', process.env.NODE_ENV);
console.log('Enviroment VUE_APP_SERVER:', process.env.VUE_APP_SERVER);


