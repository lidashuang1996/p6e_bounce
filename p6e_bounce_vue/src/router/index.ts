import Vue from 'vue';
import VueRouter, { RouteConfig } from 'vue-router';

Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
  // {
  //   path: '/',
  //   name: 'SignIn',
  //   component: () => import('../views/sign/SignIn.vue')
  // }
];

export default new VueRouter({
  routes
});
