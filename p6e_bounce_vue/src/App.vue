<template>
  <div id="app">
    <!-- v-app 是 Vuetify 里面最基础的盒子 -->
    <v-app>
      <!-- 登录页面 -->
      <sign-in v-if="GLOBAL_STATUS === 0"></sign-in>
      <!-- 登录之后的页面 -->
      <home v-if="GLOBAL_STATUS === 1"></home>
    </v-app>
  </div>
</template>
<script lang="ts">
import types from './store/types';
import cache from './utils/cache';
import Home from './views/home/Home.vue';
import SignIn from './views/sign/SignIn.vue';
import { Component, Vue } from 'vue-property-decorator';
@Component({
  components: { SignIn, Home }
})
export default class App extends Vue {
  /** 读取全局数据  GLOBAL_STATUS */
  protected get GLOBAL_STATUS () {
    return this.$store.getters[types.GLOBAL_STATUS];
  }

  /**
   * 生命周期钩子函数
   */
  protected async created () {
    await cache.init(this);
  }
}
</script>
