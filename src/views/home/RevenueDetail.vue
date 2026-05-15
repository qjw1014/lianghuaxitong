<template>
  <div :class="className"  v-if="isReloadData">
    <h4>收益明细</h4>
    <el-table v-loading="loading" :data="revenueDetailList">
<!--      <el-table-column type="selection" width="55" align="center" />-->
<!--      <el-table-column label="订单流水" align="center" prop="id" />-->
      <el-table-column label="账户名" align="center" prop="name" />
      <el-table-column label="时间" align="center" prop="dataTime" width="180" />
      <el-table-column label="收益" align="center" prop="profit" />
      <el-table-column label="收益币种" align="center" prop="profitCoin" />
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
      :autoScroll="false"
    />

  </div>
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from './mixins/resize'
import { listRevenueDetail } from "@/api/strategy/revenueDetail";


export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '300px'
    }
  },
  props: {
    accountId: { type: Number, default: null }
  },
  data() {
    return {
      //隐藏
      isReloadData:true,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 收益明细	表格数据
      revenueDetailList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      scroll:"",
      accountId:null,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        apiAccountId: null,
        dataTime: null,
        profit: null,
        profitCoin: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    if (!this.accountId) {
      if(this.$route.path=="/"){
        this.accountId=8;
      }else{
        const str = this.$route.path.split("/");
        this.accountId=str[str.length-1];
      }
    }
    this.getList();
    // 每30秒自动刷新
    this._timer = setInterval(() => { this.getList(); }, 30000);
  },
  beforeDestroy() {
    clearInterval(this._timer);
  },



  methods: {
    /** 查询策略仓位列表 */
    getList() {
      this.loading = true;
      this.queryParams.apiAccountId=this.accountId;
      listRevenueDetail(this.queryParams).then(response => {
        this.revenueDetailList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 搜索按钮操作 */
    updateTest() {
      document.body.scrollTop = 2510;
      document.documentElement.scrollTop = 2510;
    },
  }
}
</script>
