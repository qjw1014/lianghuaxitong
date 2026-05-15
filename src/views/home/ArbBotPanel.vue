<template>
  <el-row style="background:#fff;padding:16px 16px 10px;margin-bottom:32px;">
    <h4>统计套利引擎</h4>
    <el-row :gutter="20" v-loading="loading">
      <el-col :span="6">
        <div class="stat-item">
          <div class="stat-label">状态</div>
          <div><el-tag :type="engineRunning ? 'success' : 'danger'" size="medium">
            {{ engineRunning ? '运行中' : '已停止' }}
          </el-tag></div>
          <div style="font-size:12px;color:#999;margin-top:4px">模拟模式</div>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="stat-item">
          <div class="stat-label">活跃策略</div>
          <div style="font-size:24px;font-weight:bold">{{ activeCount }}</div>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="stat-item">
          <div class="stat-label">当前交易对</div>
          <div v-if="currentStrategy">
            <div style="font-size:16px;font-weight:bold">{{ currentStrategy.symbol1 }}/{{ currentStrategy.symbol2 }}</div>
            <div style="font-size:12px;color:#666">比值: {{ currentRatio.toFixed(6) }}</div>
          </div>
          <div v-else style="color:#999">-</div>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="stat-item">
          <div class="stat-label">累计模拟收益</div>
          <div style="font-size:20px;font-weight:bold;color:#00A346">{{ cumulativePnl.toFixed(2) }} USDT</div>
        </div>
      </el-col>
    </el-row>
  </el-row>
</template>

<script>
import { listStrategyInfo } from "@/api/strategy/strategyInfo";
import { getRevenueCurveData } from "@/api/strategy/revenueCurve";
import { getStatisticsAccount } from "@/api/strategy/statisticsAccount";

export default {
  name: 'ArbBotPanel',
  data() {
    return {
      loading: false,
      engineRunning: true,
      activeCount: 0,
      currentStrategy: null,
      currentRatio: 0,
      cumulativePnl: 0,
      strategies: [],
      accountId: 8,
    };
  },
  created() {
    this.loadData();
  },
  methods: {
    loadData() {
      this.loading = true;
      // 加载策略信息
      listStrategyInfo({ strategyType: 7 }).then(response => {
        const rows = response.rows || [];
        this.strategies = rows.filter(s => s.strategyStatus === 1);
        this.activeCount = this.strategies.length;
        if (this.strategies.length > 0) {
          this.currentStrategy = {
            symbol1: this.strategies[0].symbol1,
            symbol2: this.strategies[0].symbol2,
            initLine: this.strategies[0].initLine,
            gridGap: this.strategies[0].gridGap,
          };
        }
        this.loading = false;
      }).catch(() => { this.loading = false; });

      // 获取累计收益
      getStatisticsAccount(this.accountId).then(response => {
        if (response.data) {
          this.cumulativePnl = response.data.totalProfit || 0;
        }
      }).catch(() => {});
    },
  }
};
</script>

<style scoped>
.stat-item {
  padding: 10px;
  background: #f8f9fa;
  border-radius: 6px;
  min-height: 70px;
}
.stat-label {
  font-size: 13px;
  color: #999;
  margin-bottom: 6px;
}
</style>
