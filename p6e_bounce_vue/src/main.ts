import Vue from 'vue';
import App from './App.vue';
import './registerServiceWorker';
import router from './router';
import store from './store';
import Vuetify from 'vuetify';
import 'vuetify/dist/vuetify.min.css';
import './styles/main.scss';

Vue.use(Vuetify);
Vue.config.productionTip = false;

new Vue({
  router,
  store,
  vuetify: new Vuetify({
    theme: {
      themes: {
        light: {
          primary: '#1867C0',
          secondary: '#009688',
          accent: '#9c27b0',
          error: '#f44336',
          warning: '#ff5722',
          info: '#607d8b',
          success: '#4caf50'
        }
      }
    }
  }),
  render: h => h(App)
}).$mount('#app');
