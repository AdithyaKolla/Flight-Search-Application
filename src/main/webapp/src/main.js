import { createApp } from 'vue'
import App from './App.vue'
import axios from 'axios'
import VueAxios from 'vue-axios'
import router from './router/index'
import store from './store/store.js'

import 'bootstrap/dist/css/bootstrap.min.css'
import '@/assets/css/main.css'

window.$ = window.jQuery = require('jquery');

createApp(App).use(VueAxios, axios).use(router).use(store).mount('#app')

