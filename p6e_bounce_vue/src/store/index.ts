import Vue from 'vue';
import Vuex from 'vuex';
import types from './types';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    globalStatus: 0, // 0 未登录 1 登录
    globalDialog: null // 全局弹出窗
  },
  getters: {
    [types.STATE]: state => state,
    [types.GLOBAL_STATUS]: state => state.globalStatus,
    [types.GLOBAL_DIALOG]: state => state.globalDialog
  },
  mutations: {
    [types.GLOBAL_DIALOG_MUTATION]: (state, data) => {
      state.globalDialog = data;
    }
  },
  actions: {
  },
  modules: {
  }
});
