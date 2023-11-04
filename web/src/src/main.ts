import './assets/main.css'
import 'ant-design-vue/dist/reset.css';
import { createPinia } from 'pinia'

import { createApp } from 'vue'
import Antd from 'ant-design-vue';
import App from './App.vue'
import router from './router'

const app = createApp(App)
app.use(createPinia())
app.use(router)

app.use(Antd).mount('#app');
