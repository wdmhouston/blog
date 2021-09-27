import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'

const app = createApp(App);
app.use(store).use(router).use(Antd).mount('#app');

console.log('Enviroment NODE_ENV:', process.env.NODE_ENV);
console.log('Enviroment VUE_APP_SERVER:', process.env.VUE_APP_SERVER);
