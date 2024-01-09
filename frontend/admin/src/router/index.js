import Vue from 'vue';
import Router from 'vue-router';
import Login from '../pages/Login.vue';
import Dashboard from '../pages/Dashboard.vue';
import DataExport from '../pages/DataExport.vue';
import NotificationEditor from '../pages/NotificationEditor.vue';
import SiteManagement from '../pages/SiteManagement.vue';

var store = require('@/store/store');

Vue.use(Router);

const router = new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login,
      meta: {
        allowAnonymous: true
      }
    },
    {
      path: '/dashboard',
      name: 'Dashboard',
      component: Dashboard,
      props(route) { return route.query || {} },
      meta: {
        allowAnonymous: false,
        breadcrumb: [
          { name: '报考数据管理' }
        ]
      }
    },
    {
      path: '/export',
      name: 'DataExport',
      component: DataExport,
      props(route) { return route.query || {} },
      meta: {
        allowAnonymous: false,
        breadcrumb: [
          { name: '报考数据导出' }
        ]
      }
    },
    {
      path: '/notifyeditor',
      name: 'NotificationEditor',
      component: NotificationEditor,
      props(route) { return route.query || {} },
      meta: {
        allowAnonymous: false,
        breadcrumb: [
          { name: '编辑通知内容' }
        ]
      }
    },
    {
      path: '/siteManage',
      name: 'SiteManagement',
      component: SiteManagement,
      props(route) { return route.query || {} },
      meta: {
        allowAnonymous: false,
        breadcrumb: [
          { name: '网站全局管理' }
        ]
      }
    }
  ]
});

router.beforeEach((to, from, next) => {
  if (!to.meta.allowAnonymous) {
    if (store.default.state.token&&store.default.state.userName) {
      next();
    } else {
      window.location.href = "/";
      // next({name: 'Login'});
    }
  }
  next();
})

export default router;
