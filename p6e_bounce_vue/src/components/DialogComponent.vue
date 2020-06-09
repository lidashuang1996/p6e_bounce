<template>
  <div class="dialog-component">
    <v-dialog
      v-model="config.bool"
      max-width="320">
      <v-card>
        <v-card-title>
          <v-icon v-if="config.ico !== ''"></v-icon>
          <span v-text="config.title"></span>
        </v-card-title>
        <v-card-text><span v-text="config.text"></span></v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" text @click.stop="revert" v-if="config.style === 'CALLBACK'">返回</v-btn>
          <v-btn color="primary" text @click.stop="confirm">确认</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script lang="ts">
import types from '../store/types';
import { Component, Watch, Vue } from 'vue-property-decorator';
@Component
export default class DialogComponent extends Vue {
  protected get globalDialog () {
    return this.$store.getters[types.GLOBAL_DIALOG];
  };

  protected config = {
    bool: false,
    ico: '',
    style: 'NORMAL', // Normal callback
    type: 'info',
    title: '提示',
    text: '我是提示的内容',
    callback: {
      confirm: () => { /** 回调确认的函数 */ },
      revert: () => { /** 回调返回的函数 */ }
    }
  };

  @Watch('globalDialog')
  protected watchGlobalDialog (newData) {
    this.config.bool = true;
    this.config.ico = '';
    this.config.style = 'NORMAL';
    this.config.type = 'info';
    this.config.title = '提示';
    this.config.text = '我是提示的内容';
    this.config.callback = {
      confirm: () => { /** 回调确认的函数 */ },
      revert: () => { /** 回调返回的函数 */ }
    };
    if (newData !== null && newData !== undefined) {
      if (newData.style !== null && newData.style !== undefined) this.config.style = newData.style.toUpperCase();
      if (newData.type !== null && newData.type !== undefined) this.config.type = newData.type;
      if (newData.title !== null && newData.title !== undefined) this.config.title = newData.title;
      if (newData.text !== null && newData.text !== undefined) this.config.text = newData.text;
      if (newData.callback !== null && newData.callback !== undefined) this.config.callback = newData.callback;
    }
  }

  /** 失败的方法 */
  protected revert () {
    this.config.bool = false;
    if (this.config.style === 'CALLBACK' && this.config.callback.revert !== null && this.config.callback.revert !== undefined) {
      this.config.callback.revert();
    }
  }

  /** 确认的方法 */
  protected confirm () {
    this.config.bool = false;
    if (this.config.style === 'CALLBACK' && this.config.callback.confirm !== null && this.config.callback.confirm !== undefined) {
      this.config.callback.confirm();
    }
  }
}
</script>
