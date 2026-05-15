<template>
  <div :class="className"  v-if="isReloadData">
    <h4>收益明细</h4>
    <el-table v-loading="loading" :data="revenueDetailList">
      <el-table-column label="账户名" align="center" prop="name" />
      <el-table-column label="时间" align="center" prop="dataTime" width="180" />
      <el-table-column label="收益" align="center" prop="profit">
        <template slot-scope="scope">
          <span :style="{color: scope.row.profit >= 0 ? '#00A346' : '#C03639'}">{{ scope.row.profit }}</span>
        </template>
      </el-table-column>
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
      allDailyData: [],
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
    /** 查询每日收益（按天聚合） */
    getList() {
      this.loading = true;
      this.queryParams.apiAccountId = this.accountId;
      // 查全部数据，前端按天聚合
      listRevenueDetail({ apiAccountId: this.accountId, pageNum: 1, pageSize: 9999 }).then(response => {
        const rows = response.rows || [];
        // 按日期分组累加收益
        const dayMap = {};
        let name = '';
        rows.forEach(r => {
          const date = (r.dataTime || '').substring(0, 10);
          if (!date) return;
          const profit = parseFloat(r.profit) || 0;
          if (!dayMap[date]) {
            dayMap[date] = 0;
          }
          dayMap[date] += profit;
          if (!name && r.name) name = r.name;
        });
        // 转为数组，按日期倒序
        const list = Object.keys(dayMap).map(date => ({
          name: name || '未知',
          dataTime: date,
          profit: parseFloat(dayMap[date].toFixed(2)),
          profitCoin: 'USDT'
        })).sort((a, b) => b.dataTime.localeCompare(a.dataTime));
        // 过滤掉当天（北京时间未过0点不显示当天收益）
        const todayStr = this.getBeijingDateStr();
        this.allDailyData = list.filter(item => item.dataTime !== todayStr);
        this.total = list.length;
        this.updatePageData();
        this.loading = false;
      }).catch(() => { this.loading = false; });
    },
    getBeijingDateStr() {
      const now = new Date();
      const beijing = new Date(now.getTime() + 8 * 60 * 60 * 1000);
      const y = beijing.getUTCFullYear();
      const m = String(beijing.getUTCMonth() + 1).padStart(2, '0');
      const d = String(beijing.getUTCDate()).padStart(2, '0');
      return `${y}-${m}-${d}`;
    },
    updatePageData() {
      const start = (this.queryParams.pageNum - 1) * this.queryParams.pageSize;
      const end = start + this.queryParams.pageSize;
      this.revenueDetailList = this.allDailyData.slice(start, end);
    },
    /** 搜索按钮操作 */
    updateTest() {
      document.body.scrollTop = 2510;
      document.documentElement.scrollTop = 2510;
    },
  }
}
</script>
