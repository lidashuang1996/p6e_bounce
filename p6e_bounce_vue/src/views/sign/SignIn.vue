<template>
  <!-- 登录的页面 -->
  <div class="sign sign-in">
    <div class="sign-content">
      <div class="sign-in-left">
        <div class="sign-in-logo">
          <img src="../../assets/img/sign_logo.jpg"  alt="SIGN_IN_LOGO"/>
        </div>
        <div class="sign-in-saying">
          <p class="text" v-text="saying.text"></p>
          <p class="author" v-text="saying.author"></p>
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
            @focus="inputAccount"
            @keyup.enter="confirmSignIn"
            v-model="account"
            prepend-icon="account_circle"
            :error-messages="accountError"
            label="邮箱/电话号码"></v-text-field>
        </div>
        <!-- append-icon="visibility" -->
        <div class="sign-in-input">
          <v-text-field
            @focus="inputPassword"
            @keyup.enter="confirmSignIn"
            v-model="password"
            type="password"
            prepend-icon="lock"
            append-icon="visibility_off"
            hint="密码长度不能低于6位且不能高于32位"
            :error-messages="passwordError"
            label="密码"></v-text-field>
        </div>
        <div class="sign-in-geetest" id="geetest"></div>
        <div class="sign-in-button">
          <v-btn large color="primary"
                 :elevation="0"
                 dark block rounded
                 :loading="isLogin"
                 @click.stop="confirmSignIn">登 录</v-btn>
        </div>
      </div>
    </div>
  </div>
</template>
<script lang="ts">
import http from '../../http/main';
import cache from '../../utils/cache';
import { Component, Vue } from 'vue-property-decorator';

@Component({})
export default class SignIn extends Vue {
  /* 账号 */
  protected account = '';
  protected accountError = '';
  /* 密码 */
  protected password = '';
  protected passwordError = '';
  /* 是否登录加载中 */
  protected isLogin = false;
  /* 验证的缓存对象 */
  protected captchaObj = null;

  /* 头部提示语 */
  protected alert = {
    message: '欢迎，来到管理系统',
    type: 'info'
  };

  /* 谚语 */
  protected saying = {
    text: '',
    author: ''
  };

  /**
   * 生命周期的钩子函数
   */
  protected async mounted () {
    this.initAlert();
    /** 获取谚语的文本的内容 */
    const proverbResult = await http.apiProverb();
    if (proverbResult.code === 200) {
      this.saying.text = proverbResult.data.content;
      this.saying.author = '—— ' + proverbResult.data.author;
    } else {
      this.saying.text = '盛年不重来，一日难再晨。及时宜自勉，岁月不待人。';
      this.saying.author = '—— 陶渊明';
    }
    /** GEETEST 验证的（第一步） */
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
   * 初始化 提示的内容
   */
  protected initAlert () {
    // 渲染提示语
    this.alert.type = 'info';
    const hours = new Date().getHours();
    if (hours >= 5 && hours < 12) this.alert.message = '欢迎，早上好！';
    else if (hours >= 12 && hours < 18) this.alert.message = '欢迎，下午好！';
    else if (hours >= 18 && hours < 23) this.alert.message = '欢迎，晚上好！';
    else this.alert.message = '欢迎，深夜请注意休息！';
  }

  /**
   * 输入账号密码事件
   */
  protected inputAccount () {
    this.accountError = '';
  }
  protected inputPassword () {
    this.passwordError = '';
  }
  /**
   * 确认登录的操作
   * 提交登录的请求的数据
   * 如果登录成功，缓存认证的数据，并调转页面
   */
  protected async confirmSignIn () {
    if (this.isLogin) return;
    let bool = true;
    if (this.account === '') {
      bool = false;
      this.accountError = '账号不能为空';
    }
    if (!/^1[3456789]\d{9}$/.test(this.account)) {
      if (!/^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/.test(this.account)) {
        bool = false;
        this.accountError = '账号为邮箱或者手机号码';
      }
    }
    if (bool) this.accountError = '';
    if (this.password === '') {
      bool = false;
      this.passwordError = '密码不能为空';
    }
    if (this.password.length < 6 || this.password.length > 32) {
      bool = false;
      this.passwordError = '密码长度不能低于6位且不能高于32位';
    }
    if (bool) this.passwordError = '';
    if (bool) {
      const validate = this.captchaObj.getValidate();
      if (validate !== undefined && validate !== null) {
        console.log(await http.apiVerificationCodeSecond(validate));
        this.initAlert();
        this.isLogin = true;
        const result = await http.apiLogin({ account: this.account, password: this.password });
        this.isLogin = false;
        if (result.code === 200) {
          await cache.setUser(result.data).init();
        } else {
          this.alert.type = 'error';
          if (result.code == 500) this.alert.message = '服务器异常，请稍后重试';
          else {
            if ('ERROR_ACCOUNT_OR_PASSWORD' === result.message) this.alert.message = '账号或者密码错误';
            else this.alert.message = this.alert.message = result.message;
          }
        }
      } else {
        this.alert.type = 'error';
        this.alert.message = '请完成验证后再操作登录';
      }
    }
  }
}
</script>
