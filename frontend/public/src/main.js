// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import 'babel-polyfill'
import Vue from 'vue';
import App from './App';
import router from './router';
import 'vuetify/dist/vuetify.min.css';
import Vuetify from 'vuetify';
import 'material-design-icons-iconfont/dist/material-design-icons.css';
import './styles/global.css';
import VuetifyToast from 'vuetify-toast-snackbar'
import axios from 'axios'
import VueAxios from 'vue-axios'
import { setupComponents } from './config/setup-components';
import constants from "@/utils/constants.js";
import commons from "@/utils/commons.js";
import Loading from 'vue-loading-overlay';
import 'vue-loading-overlay/dist/vue-loading.css';
import store from '@/store/store.js';


setupComponents(Vue);
Vue.use(Vuetify);
Vue.use(VueAxios, axios);
Vue.use(Loading);
Vue.use(VuetifyToast, {
  x: 'right', // default
  y: 'top', // default
})

Vue.config.productionTip = false

const plugin = {
  install() {
    Vue.commons = commons
    Vue.prototype.$commons = commons
    Vue.constants = constants
    Vue.prototype.$constants = constants
    Vue.store = store
    Vue.prototype.$store = store
  }
}

Vue.use(plugin)

/* eslint-disable no-new */
var vueIns = new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>',
  data: {
    themeColor: '#1D2939'
  }
})
