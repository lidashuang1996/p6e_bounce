import Vue from 'vue';
import VueRouter, { RouteConfig } from 'vue-router';

Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
  {
    path: '/',
    name: 'account',
    component: () => import('../views/home/account/Account.vue')
  },
  {
    path: '/user',
    name: 'user',
    component: () => import('../views/home/user/User.vue')
  }
];

export default new VueRouter({
  routes
});
