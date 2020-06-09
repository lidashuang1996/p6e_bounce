<template>
  <div class="account">
    <v-breadcrumbs :items="breadcrumbsConfig"></v-breadcrumbs>
    <div class="account-content p-table">
      <div class="p-table-header">
        <div class="p-table-header-left"><p class="p-table-title">账号信息列表</p></div>
        <div class="p-table-header-right">
          <v-text-field
            v-model="table.search"
            append-icon="mdi-magnify"
            label="请输入 编号/账号/名称/别名 搜索"
            single-line
            hide-details
            @keyup.enter="searchData"
          ></v-text-field>
          <v-btn depressed color="primary" class="p-table-button" @click.stop="searchData">搜索</v-btn>
        </div>
      </div>
      <v-data-table
        :loading="table.loading"
        :headers="table.headers"
        :items="table.list">
        <template v-slot:item.operate2="{ item }">
          <v-btn text small :disabled="item.id === 1" color="warning" @click.stop="updateConfirm(item)">修改</v-btn>
          <v-btn text small :disabled="item.id === 1" color="error" @click.stop="deleteConfirm(item)">删除</v-btn>
        </template>
      </v-data-table>
    </div>
  </div>
</template>
<script lang="ts">
import types from '../../../store/types';
import http from '../../../http/main';
import { Component, Vue } from 'vue-property-decorator';
@Component
export default class Account extends Vue {
  /** 面包屑的配置 */
  protected breadcrumbsConfig = [
    {
      text: '首页'
    },
    {
      text: '账号管理'
    }
  ];

  /** 数据表格的配置 */
  protected table = {
    loading: false,
    search: '', // 搜索的文本对象
    list: [],
    page: 1,
    size: 16,
    total: 0,
    headers: [
      {
        text: '编号',
        align: 'start',
        sortable: false,
        value: 'id'
      },
      { text: '账号', value: 'account' },
      { text: '状态', value: 'status' },
      { text: '安全组', value: 'group' },
      { text: '名称', value: 'name' },
      { text: '别名', value: 'alias' },
      { text: '性别', value: 'sex' },
      { text: '生日', value: 'birthday' },
      { text: '创建时间', value: 'createDate' },
      { text: '更新时间', value: 'updateDate' },
      { text: '操作人', value: 'operate' },
      { text: '操作', align: 'center', value: 'operate2' }
    ]
  };

  /**  VUE 钩子函数 */
  protected async mounted () {
    await this.obtainData();
  }

  /** 获取数据的方法 */
  protected async obtainData () {
    const data: HttpUserListParam = { page: this.table.page, size: this.table.size };
    if (this.table.search !== '') data.search = this.table.search;
    this.table.list = [];
    this.table.loading = true;
    const res = await http.apiUserList(data);
    this.table.loading = false;
    if (res.code === 200) {
      this.table.list = res.data.list;
    } else this.networkError(res.message);
  }

  /** 搜索数据的方法 */
  protected async searchData () {
    this.table.page = 1;
    this.table.size = 16;
    await this.obtainData();
  }

  /** 修改账号数据的方法 */
  protected async updateConfirm () {
    alert(1);
  }

  /** 删除账号数据的方法 */
  protected async deleteConfirm (data) {
    this.callbackTips('删除', '你确定删除账号 ' + data.account + ' 吗？', {
      confirm: async () => {
        const res = await http.apiDeleteUser({ id: data.id });
        if (res.code === 200) {
          await this.obtainData();
        } else this.networkError(res.message);
      },
      revert: () => { /** 回调返回的函数 */ }
    });
  }

  /** 网络异常弹出窗方法 */
  protected networkError (text = '网络异常，请稍后重试') {
    this.$store.commit(types.GLOBAL_DIALOG_MUTATION,
      { type: 'error', title: '错误', text: text });
  }

  /** 删除提示的弹出层 */
  protected callbackTips (title, text, callback) {
    this.$store.commit(types.GLOBAL_DIALOG_MUTATION, { style: 'CALLBACK', type: 'info', title, text, callback });
  }
}
</script>
