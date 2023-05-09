import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import '@/assets/global.css'
import Toast from 'vue-toastification'
import 'vue-toastification/dist/index.css'
import axios from "axios"

Vue.prototype.$bus=new Vue()
Vue.config.productionTip = false;
Vue.use(ElementUI);
Vue.prototype.$axios = axios;
new Vue({
  beforeCreate() {
    Vue.prototype.$bus = this  //挂载$bus
  },
  router,
  render: h => h(App)
}).$mount('#app');

const options = {
  transition: 'Vue-Toastification__bounce',
  maxToasts: 3,
  newestOnTop: true,
  position: 'top-left',
  timeout: 4000,
  closeOnClick: true,
  pauseOnFocusLoss: true,
  pauseOnHover: false,
  draggable: true,
  draggablePercent: 0.7,
  showCloseButtonOnHover: false,
  hideProgressBar: false,
  closeButton: 'button',
  icon: true,
  rtl: false
};

Vue.use(Toast, options);
