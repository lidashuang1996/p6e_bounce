/**
 * 导入网络请求的包
 */
import $request, { AxiosInstance, AxiosRequestConfig } from 'axios';

/**
 * 对 axios 封装，暴露的自定义类型
 */
export interface BaseHttp {
  initAuth (token: string): void;
  initClient (client: string): void;
  get<T> (url: string, config?: AxiosRequestConfig): Promise<T>;
  post<T> (url: string, data?: any, config?: AxiosRequestConfig): Promise<T>;
}

export default class Base implements BaseHttp {
  /* 请求对象 */
  private readonly request: AxiosInstance;
  private auth: string | null = null;
  private client: string | null = null;

  constructor () {
    /* 设置全局默认超时的时间 */
    $request.defaults.timeout = 30000;
    /* 设置全局默认请求头 */
    $request.defaults.headers = {
      'Content-Type': 'application/json;'
    };
    /* 创建请求对象 */
    this.request = $request.create();
  }

  public get<T = any> (url: string, config?: AxiosRequestConfig): Promise<T> {
    return new Promise<T>((resolve, reject) => {
      this.request.get<T>(url, config)
        .then((res) => { if (res.data !== undefined && res.data !== null) resolve(res.data); })
        .catch((e) => { console.error(e); reject(e); });
    });
  }

  public post<T = any> (url: string, data: any = {}, config?: AxiosRequestConfig): Promise<T> {
    return new Promise<T>((resolve, reject) => {
      this.request.post<T>(url, data, config)
        .then((res) => { if (res.data !== undefined && res.data !== null) resolve(res.data); })
        .catch((e) => { console.error(e); reject(e); });
    });
  }

  public initAuth (token: string) {
    this.auth = token;
    /* 设置请求对象的 请求 / 返回 拦截处理 */
    this.request.interceptors.request.use((config) => {
      if (this.client !== null) config.headers.Client = this.client;
      if (this.auth !== null) config.headers.Authorization = `Basic ${this.auth}`;
      return config;
    }, (error) => {
      return error;
    });
  }

  public initClient (client: string) {
    this.client = client;
    /* 设置请求对象的 请求 / 返回 拦截处理 */
    this.request.interceptors.request.use((config) => {
      if (this.client !== null) config.headers.Client = this.client;
      if (this.auth !== null) config.headers.Authorization = `Basic ${this.auth}`;
      return config;
    }, (error) => {
      return error;
    });
  }

  // public transformationToXWWWFormUrlencoded (config: AxiosRequestConfig) {
  //   // 由于设置传入的参数是 JSON 对象，但是提交的方式为表单提交的方式，需要转换一下
  //   config.transformRequest = (data: any) => {
  //     if (data !== undefined && data !== null) {
  //       let result = '';
  //       for (const key in data) {
  //         // if (!data.hasOwnProperty(key)) continue;
  //         result += `&${encodeURIComponent(key)}=${encodeURIComponent(data[key])}`;
  //       }
  //       return result.substring(1);
  //     } return undefined;
  //   };
  // }
}
