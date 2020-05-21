/**
 * 请求的基础返回对象
 */
declare interface HttpBaseResult<T> {
  data: T;
  code: number;
  message: string;
}

















/**
 * 请求的登录参数对象
 */
declare interface HttpLoginParam {
  account: string;
  password: string;
}

/**
 * 请求的登录返回对象
 */
declare interface HttpLoginDataResult {
  id: string;
  group: string;
  role: string;
  account: string;
  alias: string;
  name: string;
  avatar: string;
  sex: string;
  birthday: string;
  token: string;
  refreshToken: string;
  expiration: number;
  timeStamp: number;
}

/**
 * 请求的登录返回封装对象
 */
declare type HttpLoginResult = HttpBaseResult<HttpLoginDataResult>;





declare interface HttpClientDataResult {
  id: string;
}
/**
 * 请求的登录返回封装对象
 */
declare type HttpClientResult = HttpBaseResult<HttpClientDataResult>;
