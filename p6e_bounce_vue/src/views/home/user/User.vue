<template>
  <!-- 用户的信息页面 -->
  <div class="user">
    <v-breadcrumbs :items="items"></v-breadcrumbs>
    <div class="user-content">
      <div class="user-content-item" style="height: 80px;">
        <label class="user-title">
          用户头像：
        </label>
        <label class="user-avatar">
          <img src="../../../assets/img/sign_logo.jpg" alt="USER_AVATAR" />
          <span class="user-avatar-camera material-icons"></span>
        </label>
      </div>
      <div class="user-content-item">
        <label class="user-title">
          用户账号：
        </label>
        <label>
          <v-text-field single-line outlined readonly v-model="uCache.account"></v-text-field>
        </label>
      </div>
      <div class="user-content-item">
        <label class="user-title">
          用户名称：
        </label>
        <label>
          <v-text-field single-line outlined v-model="uCache.name"></v-text-field>
        </label>
      </div>
      <div class="user-content-item">
        <label class="user-title">
          用户别名：
        </label>
        <label>
          <v-text-field single-line outlined v-model="uCache.alias"></v-text-field>
        </label>
      </div>
      <div class="user-content-item">
        <label class="user-title">
          用户性别：
        </label>
        <label>
          <v-text-field single-line outlined v-model="uCache.sex"></v-text-field>
        </label>
      </div>
      <div class="user-content-item">
        <label class="user-title">
          用户生日：
        </label>
        <label>
          <v-text-field single-line outlined v-model="uCache.birthday"></v-text-field>
        </label>
      </div>
      <div class="user-content-item">
        <label class="user-title">
          用户状态：
        </label>
        <label>
          <v-text-field single-line outlined v-model="uCache.status"></v-text-field>
        </label>
      </div>
      <div class="user-content-item">
        <label class="user-title">
          用户安全组：
        </label>
        <label>
          <v-text-field single-line outlined v-model="uCache.group"></v-text-field>
        </label>
      </div>
      <div class="user-content-button">
        <v-btn depressed large color="primary" style="width: 340px;">修改数据</v-btn>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-property-decorator';
@Component
export default class User extends Vue {
  protected items = [
    {
      text: '首页'
    },
    {
      text: '个人信息'
    }
  ];

  /** 用户信息缓存 */
  protected uCache = {
    id: 0,
    account: '1294935733@qq.com',
    status: 1,
    group: 1,
    alias: '我的别名',
    name: '我的名称',
    sex: '我的性别',
    birthday: '2020-12-12'
  };

  /** 修改数据确认 */
  protected updateConfirm () {
    console.log('1');
  }
}
</script>
