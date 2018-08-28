// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
// add to css
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import 'material-design-icons-iconfont/dist/material-design-icons.css'
// add to vuex
import store from './store/index'
import {sync} from 'vuex-router-sync'

// change background and color primaries
Vue.use(Vuetify, {
  theme: {
    primary: '#E91E63',
    secondary: '#FCE4EC',
    accent: '#8c9eff',
    error: '#0D47A1'
  }
});

Vue.config.productionTip = false;

// enable sync router
sync(store, router);

// create the object vue
new Vue({
  el: '#app',
  router,
  components: {App},
  template: '<App/>',
  // add
  store
});
