<template>
  <div class="home">
    <v-app-bar app color="primary">
      <home-head></home-head>
    </v-app-bar>
    <v-navigation-drawer app color="primary">
    </v-navigation-drawer>
    <v-content>

      <!-- Provides the application the proper gutter -->
      <v-container fluid>

        <!-- If using vue-router -->
        <router-view></router-view>
      </v-container>
    </v-content>
    <!-- 弹出窗 -->
    <div>
      <dialog-component></dialog-component>
    </div>
  </div>
</template>
<script lang="ts">
import HomeHead from './HomeHead.vue';
import { Component, Vue } from 'vue-property-decorator';
import DialogComponent from '../../components/DialogComponent.vue';
@Component({
  components: { DialogComponent, HomeHead }
})
export default class Home extends Vue {
  protected app = {
    bar: 50,
    bottom: 50,
    footer: 50,
    footerInset: 50,
    left: 50,
    right: 50,
    top: 50
  };
}
</script>
