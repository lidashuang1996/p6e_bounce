import Vue from 'vue';
import Base, { BaseHttp } from './base';
import config from '../config/main';

export default class Http {
  /**
   * VUE 注入对象
   */
  private static $V: Vue;

  /**
   * Http 请求基础对象
   */
  private static readonly $http: BaseHttp = new Base();

  /**
   * 读取 URL
   */
  private static readonly URL: string = config.URL;

  /**
   * 初始化注入 Vue 对象
   * @param v Vue 对象
   */
  public static init (v: Vue) {
    this.$V = v;
  }

  /**
   * 延迟返回
   * @param promise Promise 请求方法
   * @param time 延迟的时间 毫秒
   */
  private static delayRequest<T> (promise: Promise<T>, time: number): Promise<T> {
    // 时间的回调函数
    const timeFunction = (t: number, callback: () => void) => {
      if (t > 0 && t < time) setTimeout(() => { callback(); }, (time - t));
      else callback();
    };
    return new Promise<T>((resolve: (res: T) => void, reject: (e: any) => void) => {
      const dateTime = new Date().getTime();
      promise
        .then((res: T) => {
          timeFunction((new Date().getTime() - dateTime), () => { resolve(res); });
        })
        .catch((e: any) => {
          timeFunction((new Date().getTime() - dateTime), () => { reject(e); });
        });
    });
  }

  /**
   * API 登录接口
   */
  public static async apiLogin (data: HttpLoginParam): Promise<HttpLoginResult> {
    try {
      return await this.delayRequest<HttpLoginResult>(
        this.$http.post<HttpLoginResult>(this.URL + '/sign/in/', data), 3000);
    } catch (e) {
      return { code: 500, message: e.toString() };
    }
  }

  public static apiClient (): Promise<HttpClientResult> {
    return this.$http.get<HttpClientResult>(this.URL + '/client/?_=' + new Date().getTime());
  }

  public static apiVerificationCodeFirst (): Promise<any> {
    return this.$http.get<any>(this.URL + '/geetest/first?_=' + new Date().getTime());
  }

  /**
   * 获取谚语的接口
   */
  public static async apiProverb (): Promise<HttpProverbResult> {
    try {
      return this.$http.get<HttpProverbResult>(this.URL + '/proverb/');
    } catch (e) {
      return { code: 500, message: e.toString() };
    }
  }

  /**
   * 获取用户列表数据的方法
   */
  public static async apiUserList (data: HttpUserListParam): Promise<HttpUserListResult> {
    try {
      return this.$http.post<HttpUserListResult>(this.URL + '/user/list', data);
    } catch (e) {
      return { code: 500, message: e.toString() };
    }
  }

  /**
   * 删除用户数据的方法
   */
  public static async apiDeleteUser (data: HttpDeleteUserParam): Promise<HttpDeleteUserResult> {
    try {
      return this.$http.post<HttpDeleteUserResult>(this.URL + '/user/delete', data);
    } catch (e) {
      return { code: 500, message: e.toString() };
    }
  }

  public static apiVerificationCodeSecond (data: any) {
    return this.$http.post(this.URL + '/geetest/second', data,{
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded;'
      },
      transformRequest: (d) => {
        if (d !== undefined && d !== null) {
          let result = '';
          for (const key in data) {
            if (!Object.prototype.hasOwnProperty.call(data, key)) continue;
            result += `&${encodeURIComponent(key)}=${encodeURIComponent(data[key])}`;
          }
          return result.substring(1);
        }
      }
    });
  }

  public static initAuth (auth: string) {
    this.$http.initAuth(auth);
  }

  public static initClient (client: string) {
    this.$http.initClient(client);
  }
}
