<template>
  <div :class="className"  >
    <h4>仓位</h4>
    <el-table v-loading="loading" :data="strageyPositionInfoList">
<!--      <el-table-column type="selection" width="55" align="center" />-->
<!--      <el-table-column label="流水编号" align="center" prop="id" />-->
<!--      <el-table-column label="子账号" align="center" prop="apiAccountId" />-->
      <el-table-column label="Exchange" align="center" prop="exchange" />
      <el-table-column label="Symbol" align="center" prop="symbol" />
      <el-table-column label="Position Type" align="center" prop="positionSide" />
      <el-table-column label="Quantity" align="center" prop="quantity" />
      <el-table-column label="Open Price" align="center" prop="openPrice" />
      <el-table-column label="Curren Price" align="center" prop="currenPrice" />
      <el-table-column label="Estimated Liquidation Price" align="center" prop="liquidationPrice" />
      <el-table-column label="ADL" align="center" prop="adl" />
      <el-table-column label="Leverage" align="center" prop="leverage" />
      <el-table-column label="Position Value" align="center" prop="positionValue" />
<!--      <el-table-column label="添加时间" align="center" prop="cretaeTime" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.cretaeTime) }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
    </el-table>

  </div>
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import resize from './mixins/resize'
import { listStrageyPositionByStrategy } from "@/api/strategy/strageyPositionInfo";

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
      // 策略仓位表格数据
      strageyPositionInfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      accountId:null,
      // 查询参数
      queryParams: {
        apiAccountId: null,
        exchange: null,
        symbol: null,
        positionSide: null,
        quantity: null,
        openPrice: null,
        currenPrice: null,
        liquidationPrice: null,
        adl: null,
        leverage: null,
        positionValue: null,
        cretaeTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        apiAccountId: [
          { required: true, message: "子账号不能为空", trigger: "blur" }
        ],
        exchange: [
          { required: true, message: "交易所不能为空", trigger: "blur" }
        ],
        symbol: [
          { required: true, message: "交易对不能为空", trigger: "blur" }
        ],
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
  },

  methods: {
    /** 查询策略仓位列表 */
    getList() {
      this.loading = true;
      this.queryParams.apiAccountId=this.accountId;
      listStrageyPositionByStrategy(this.queryParams).then(response => {
        // 过滤掉套利引擎的仓位（exchange=arb_binance），只显示马丁仓位
        this.strageyPositionInfoList = (response.rows || []).filter(p => p.exchange !== 'arb_binance');
        this.total = this.strageyPositionInfoList.length;
        this.loading = false;
      });
    },
  }
}
</script>
