import Vue from 'vue';
import Router from 'vue-router';
import Dashboard from '../pages/Dashboard.vue';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: '',
      component: Dashboard,
      props(route) { return route.query || {} },
      meta: {
    
      }
    }
  ]
});
