<template>
  <div :class="className">
    <h4>充提记录</h4>
    <el-table v-loading="loading" :data="chargeHistoryList">
<!--      <el-table-column type="selection" width="55" align="center" />-->
<!--      <el-table-column label="订单流水" align="center" prop="id" />-->
      <el-table-column label="账户名" align="center" prop="name" />
      <el-table-column label="添加时间" align="center" prop="crateTime" >
        <template slot-scope="scope">
          <span>{{parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <!--      <el-table-column label="子账号" align="center" prop="apiAccountId" />-->
      <el-table-column label="操作类型" align="center" prop="actionType" :formatter="actionTypeFormat" />
      <el-table-column label="数量" align="center" prop="amount" />
      <el-table-column label="币种" align="center" prop="coin" />
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
import { listChargeHistory } from "@/api/strategy/chargeHistory";


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
      // 充值记录	表格数据
      chargeHistoryList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 操作类型 in：充值，out：提币字典
      actionTypeOptions: [],
      accountId:null,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        apiAccountId: null,
        actionType: null,
        amount: null,
        coin: null
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
    this.getDicts("action_type").then(response => {
      this.actionTypeOptions = response.data;
    });
  },



  methods: {
    /** 查询策略仓位列表 */
    getList() {
      this.loading = true;
      this.queryParams.apiAccountId=this.accountId;
      listChargeHistory(this.queryParams).then(response => {
        this.chargeHistoryList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 操作类型 in：充值，out：提币字典翻译
    actionTypeFormat(row, column) {
      return this.selectDictLabel(this.actionTypeOptions, row.actionType);
    },
  }
}
</script>
