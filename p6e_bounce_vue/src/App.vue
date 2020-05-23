<template>
  <div id="app">
    <!-- v-app 是 Vuetify 里面最基础的盒子 -->
    <v-app>
      <!--<home></home>-->
      <sign-in></sign-in>
    </v-app>
  </div>
</template>
<script lang="ts">
import cache from './utils/cache';
import Home from './views/home/Home.vue';
import SignIn from './views/sign/SignIn.vue';
import { Component, Vue } from 'vue-property-decorator';
@Component({
  components: { SignIn, Home }
})
export default class App extends Vue {
  /**
   * 生命周期钩子函数
   */
  protected async created () {
    await cache.init(this);
  }
}
</script>
