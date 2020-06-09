/**
 * 请求的基础返回对象
 */
declare interface HttpBaseResult<T> {
  data?: T;
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


/**
 * 谚语的类型
 */
declare interface HttpProverbDataResult {
  author: string;
  content: string;
}

declare type HttpProverbResult = HttpBaseResult<HttpProverbDataResult>;

/**
 * 用户列表
 */
declare interface HttpUserListParam {
  search?: string;
  page?: number;
  size?: number;
}

declare interface HttpUserListDataResult {
  list: HttpUserListItemDataResult[];
  page: number;
  size: number;
  total: number;
}

declare interface HttpUserListItemDataResult {
  id: number;
  account: string;
  password: string;
  status: number;
  group: number;
  alias: string;
  avatar: string;
  name: string;
  sex: string;
  birthday: string;
  operate: string;
}

declare type HttpUserListResult = HttpBaseResult<HttpUserListDataResult>;


declare interface HttpDeleteUserParam {
  id: number;
}

declare interface HttpDeleteUserDataResult {
  id: number;
  account: string;
  password: string;
  status: number;
  group: number;
  alias: string;
  avatar: string;
  name: string;
  sex: string;
  birthday: string;
  operate: string;
}
declare type HttpDeleteUserResult = HttpBaseResult<HttpDeleteUserDataResult>;
