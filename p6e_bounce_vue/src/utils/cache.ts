import http from '../http/main';
/**
 * 缓存数据的类
 */
export class Cache {
  private static self: any; // 缓存 Vue 对象,用来修改 Vuex 里面的数据
  private static USER_NAME = 'P6E_BOUNCE_USER';
  private static CLIENT_NAME = 'P6E_BOUNCE_CLIENT';

  // 初始化数据
  public static async init (self?: any) {
    // 判断本地有没有 client 缓存。如果有就直接使用，没有就重新获取
    let client = window.localStorage.getItem(this.CLIENT_NAME);
    if (client !== undefined && client !== null && client !== '') {
      http.initClient(client);
    } else {
      const result = await http.apiClient();
      console.log(result);
      if (result.code === 200) {
        client = result.data.id;
        http.initClient(client);
        window.localStorage.setItem(this.CLIENT_NAME, client);
      }
    }
    // let bool = false;
    // const user = window.localStorage.getItem(this.USER_NAME);
    // if (user !== undefined && user !== null) bool = true;
    // if (self !== undefined && self != null) this.self = self;
    // if (this.self !== undefined && this.self != null) {
    // }
    return this;
  }

  // 设置用户信息
  public static setUser (value: HttpLoginDataResult) {
    window.localStorage.setItem(this.USER_NAME, JSON.stringify(value));
    return this;
  }

  // 删除用户信息
  public static delUser () {
    window.localStorage.removeItem(this.USER_NAME);
    return this;
  }

  public static logout () {
    this.delUser();
    return this;
  }

  public static clear () {
    window.localStorage.clear();
    return this;
  }
}

export default Cache;
