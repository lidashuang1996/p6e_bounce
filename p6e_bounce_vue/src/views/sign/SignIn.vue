<template>
  <!-- 登录的页面 -->
  <div class="sign sign-in">
    <div class="sign-content">
      <div class="sign-in-left">
        <div class="sign-in-logo">
          <img src="../../assets/img/sign_logo.jpg"  alt="SIGN_IN_LOGO"/>
        </div>
        <div class="sign-in-saying">
          <p class="text">盛年不重来，一日难再晨。及时宜自勉，岁月不待人。</p>
          <p class="author">—— 陶渊明</p>
        </div>
      </div>
      <div class="sign-in-middle"></div>
      <div class="sign-in-right">
        <div class="sign-in-title">P6e Bounce 管理平台</div>
        <div class="sign-in-alert">
          <v-alert :type="alert.type" icon="info">
            <span v-text="alert.message"></span>
          </v-alert>
        </div>
        <div class="sign-in-input">
          <v-text-field
            v-model="account"
            prepend-icon="account_circle"
            hint="31231"
            label="邮箱/电话号码"></v-text-field>
        </div>
        <!-- append-icon="visibility" -->
        <div class="sign-in-input">
          <v-text-field
            v-model="password"
            :color="'red'"
            type="password"
            append-icon="visibility_off"
            prepend-icon="lock"
            label="密码">312312</v-text-field>
        </div>
        <div class="sign-in-geetest" id="geetest"></div>
        <div class="sign-in-button">
          <v-btn large color="primary" :elevation="0" dark block rounded :loading="false" @click.stop="confirmSignIn">登 录</v-btn>
        </div>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import http from '../../http/main';
import { Component, Vue } from 'vue-property-decorator';

@Component({})
export default class SignIn extends Vue {
  /* 账号 */
  protected account = '';
  /* 密码 */
  protected password = '';
  /* 验证的缓存对象 */
  protected captchaObj = null;
  /* 头部提示语 */
  protected alert = {
    message: '欢迎，来到管理系统',
    type: 'info'
  };

  /**
   * 生命周期的钩子函数
   */
  protected async mounted () {
    // 渲染提示语
    const hours = new Date().getHours();
    if (hours >= 5 && hours < 12) this.alert.message = '欢迎，早上好！';
    else if (hours >= 12 && hours < 18) this.alert.message = '欢迎，下午好！';
    else if (hours >= 18 && hours < 23) this.alert.message = '欢迎，晚上好！';
    else this.alert.message = '欢迎，深夜请注意休息！';
    const result = await http.apiVerificationCodeFirst();
    /* eslint-disable */
    (window as any).initGeetest({
      gt: result.gt,
      challenge: result.challenge,
      new_captcha: result.new_captcha,
      offline: !result.success,
      product: 'float',
      width: '100%'
    }, (captchaObj: any) => {
      captchaObj.appendTo("#geetest");
      this.captchaObj = captchaObj;
    });
  }
  /**
   * 确认登录的操作
   * 提交登录的请求的数据
   * 如果登录成功，缓存认证的数据，并调转页面
   */
  protected async confirmSignIn () {
    console.log(this.captchaObj.getValidate());
    console.log(this.account, this.password);
    const validate = this.captchaObj.getValidate();
    if (validate !== undefined && validate !== null) console.log(await http.apiVerificationCodeSecond(validate));

    // const result = await http.apiLogin({ account: this.account, password: this.password });
    // if (result.code === 200) {
    // } else {
    // }
  }
}
</script>
