
import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue';
import '../public/global.css'
import axios from "axios";

Vue.use(ElementUI, {size: 'small'});
Vue.prototype.$axios = axios;
Vue.prototype.$httpUrl = 'http://localhost:8081'


new Vue({
  el: '#app',
  render: h => h(App)
});
