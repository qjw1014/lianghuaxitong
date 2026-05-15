<template>
  <div class="app-container">
    <!-- 账户统计 -->
    <el-card shadow="hover" style="margin-bottom:20px">
      <div slot="header"><span>账户统计</span></div>
      <el-row :gutter="20">
        <el-col :span="6">
          <div style="text-align:center;padding:15px 0">
            <div style="font-size:12px;color:#999">用户总余额</div>
            <div style="font-size:24px;font-weight:bold;margin-top:8px">{{ (statsData.initUsdt || 0).toFixed(2) }}</div>
          </div>
        </el-col>
        <el-col :span="6">
          <div style="text-align:center;padding:15px 0">
            <div style="font-size:12px;color:#999">近30天充提</div>
            <div style="font-size:24px;font-weight:bold;margin-top:8px">{{ (statsData.charge || 0).toFixed(4) }}</div>
          </div>
        </el-col>
        <el-col :span="6">
          <div style="text-align:center;padding:15px 0">
            <div style="font-size:12px;color:#999">最新净值</div>
            <div style="font-size:24px;font-weight:bold;margin-top:8px">{{ (statsData.currentUsdt || 0).toFixed(4) }}</div>
          </div>
        </el-col>
        <el-col :span="6">
          <div style="text-align:center;padding:15px 0">
            <div style="font-size:12px;color:#999">累计收益</div>
            <div :style="{fontSize:'20px',fontWeight:'bold',marginTop:'8px',color:(statsData.totalProfit||0) >= 0 ? '#00A346' : '#C03639'}">{{ (statsData.totalProfit || 0).toFixed(4) }}</div>
            <div style="font-size:12px;color:#999;margin-top:4px">{{ ((statsData.totalProfitFloat || 0) * 100).toFixed(2) }}% 累计年化</div>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="6">
          <div style="text-align:center;padding:15px 0">
            <div style="font-size:12px;color:#999">7天收益</div>
            <div :style="{fontSize:'20px',fontWeight:'bold',marginTop:'8px',color:(statsData.days7Profit||0) >= 0 ? '#00A346' : '#C03639'}">{{ (statsData.days7Profit || 0).toFixed(4) }}</div>
            <div style="font-size:12px;margin-top:4px">
              <span :style="{color: (statsData.days7ProfitFloat || 0) >= 0 ? '#00A346' : '#C03639'}">{{ ((statsData.days7ProfitFloat || 0) * 100).toFixed(2) }}%</span>
              <span style="color:#999"> 7日年化</span>
            </div>
          </div>
        </el-col>
        <el-col :span="6">
          <div style="text-align:center;padding:15px 0">
            <div style="font-size:12px;color:#999">30天收益</div>
            <div :style="{fontSize:'20px',fontWeight:'bold',marginTop:'8px',color:(statsData.days30Profit||0) >= 0 ? '#00A346' : '#C03639'}">{{ (statsData.days30Profit || 0).toFixed(4) }}</div>
            <div style="font-size:12px;margin-top:4px">
              <span :style="{color: (statsData.days30ProfitFloat || 0) >= 0 ? '#00A346' : '#C03639'}">{{ ((statsData.days30ProfitFloat || 0) * 100).toFixed(2) }}%</span>
              <span style="color:#999"> 30日年化</span>
            </div>
          </div>
        </el-col>
      </el-row>
    </el-card>

    <!-- 引擎状态 -->
    <el-row :gutter="20" style="margin-bottom:20px">
      <el-col :span="6">
        <el-card shadow="hover">
          <div slot="header">引擎状态</div>
          <div>
            <el-tag :type="engineOnline ? 'success' : 'danger'" size="medium">
              {{ engineOnline ? '运行中' : '已停止' }}
            </el-tag>
            <el-tag type="warning" style="margin-left:8px">模拟模式</el-tag>
          </div>
          <div style="margin-top:8px;font-size:12px;color:#999">扫描间隔: 15秒</div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover">
          <div slot="header">活跃策略</div>
          <div style="font-size:28px;font-weight:bold">{{ activeCount }}</div>
          <div style="font-size:12px;color:#999">个策略运行中</div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover">
          <div slot="header">跟踪交易对</div>
          <div style="font-size:14px;font-weight:bold">{{ trackedPairs }}</div>
          <div style="font-size:12px;color:#999;margin-top:4px">{{ activeCount > 0 ? '策略运行中' : '无活跃策略' }}</div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card shadow="hover">
          <div slot="header">累计模拟收益</div>
          <div style="font-size:24px;font-weight:bold;color:#00A346">{{ cumulativePnl.toFixed(2) }} USDT</div>
          <div style="font-size:12px;color:#999">平仓已实现</div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 策略配置 -->
    <el-card shadow="hover" style="margin-bottom:20px">
      <div slot="header"><span>策略配置</span></div>
      <el-table v-loading="loadingStrategy" :data="strategyList" border>
        <el-table-column label="策略ID" prop="strategyId" width="80" />
        <el-table-column label="交易对" min-width="180">
          <template slot-scope="scope">{{ scope.row.symbol1 }} / {{ scope.row.symbol2 }}</template>
        </el-table-column>
        <el-table-column label="基准比值" prop="initLine" width="120">
          <template slot-scope="scope">{{ parseFloat(scope.row.initLine).toFixed(6) }}</template>
        </el-table-column>
        <el-table-column label="网格间距" prop="gridGap" width="120">
          <template slot-scope="scope">{{ parseFloat(scope.row.gridGap).toFixed(6) }}</template>
        </el-table-column>
        <el-table-column label="每笔金额" prop="everyVolume" width="120">
          <template slot-scope="scope">{{ scope.row.everyVolume }} USDT</template>
        </el-table-column>
        <el-table-column label="杠杆" prop="leverage" width="80" />
        <el-table-column label="状态" width="100">
          <template slot-scope="scope">
            <el-tag :type="scope.row.strategyStatus === 1 ? 'success' : 'info'">
              {{ scope.row.strategyStatus === 1 ? '启用' : '停用' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="200">
          <template slot-scope="scope">
            <el-button size="mini" type="text" @click="viewStrategyRatio(scope.row)">查看比值</el-button>
            <el-button size="mini" type="text" icon="el-icon-edit" @click="handleEditLeverage(scope.row)">修改</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 修改杠杆弹窗 -->
    <el-dialog title="修改策略配置" :visible.sync="dialogLeverageVisible" width="500px" append-to-body>
      <el-form ref="leverageForm" :model="leverageForm" label-width="120px">
        <el-form-item label="交易对">
          <span>{{ leverageForm.symbol1 }} / {{ leverageForm.symbol2 }}</span>
        </el-form-item>
        <el-form-item label="杠杆倍数" prop="leverage">
          <el-input-number v-model="leverageForm.leverage" :min="1" :max="125" :step="1" style="width:200px" />
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button @click="dialogLeverageVisible = false">取消</el-button>
        <el-button type="primary" @click="saveLeverage" :loading="saving">保存</el-button>
      </div>
    </el-dialog>

    <!-- 当前仓位 -->
    <el-card shadow="hover" style="margin-bottom:20px">
      <div slot="header"><span>当前仓位</span></div>
      <el-table v-loading="loadingPosition" :data="positionList" border>
        <el-table-column label="交易对" prop="pair" width="160" />
        <el-table-column label="类型" prop="legType" width="60" />
        <el-table-column label="Exchange" prop="exchange" width="120" />
        <el-table-column label="Symbol" prop="symbol" min-width="130" />
        <el-table-column label="Position Type" prop="positionSide" width="120">
          <template slot-scope="scope">
            <el-tag :type="scope.row.positionSide === 'BUY' || scope.row.positionSide === 'LONG' ? 'danger' : 'success'" size="small">
              {{ scope.row.positionSide }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="Quantity" prop="quantity" width="120" />
        <el-table-column label="Open Price" prop="openPrice" width="120" />
        <el-table-column label="Curren Price" prop="currenPrice" width="120" />
        <el-table-column label="Estimated Liquidation Price" prop="liquidationPrice" width="180" />
        <el-table-column label="ADL" prop="adl" width="70" />
        <el-table-column label="Leverage" prop="leverage" width="80" />
        <el-table-column label="Position Value" prop="positionValue" width="130" />
      </el-table>
    </el-card>

    <!-- 日收益 -->
    <el-card shadow="hover" style="margin-bottom:20px">
      <div slot="header"><span>日收益</span></div>
      <el-table v-loading="loadingRevenue" :data="revenueList" border style="width:100%">
        <el-table-column label="Alias" prop="alias" min-width="120" />
        <el-table-column label="Date" prop="date" min-width="100" />
        <el-table-column label="Equity" prop="equity" min-width="100">
          <template slot-scope="scope">{{ scope.row.equity.toFixed(2) }}</template>
        </el-table-column>
        <el-table-column label="Nav" prop="nav" min-width="80">
          <template slot-scope="scope">{{ scope.row.nav.toFixed(4) }}</template>
        </el-table-column>
        <el-table-column label="Pnl" prop="pnl" min-width="100">
          <template slot-scope="scope">
            <span :style="{color: scope.row.pnl >= 0 ? '#00A346' : '#C03639'}">
              {{ scope.row.pnl.toFixed(2) }}
            </span>
          </template>
        </el-table-column>
        <el-table-column label="Yield" prop="yield" min-width="80">
          <template slot-scope="scope">
            <span :style="{color: scope.row.yieldPct >= 0 ? '#00A346' : '#C03639'}">
              {{ scope.row.yieldPct.toFixed(2) }}%
            </span>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 充提记录 -->
    <charge-history :account-id="accountId" />
  </div>
</template>

<script>
import { listStrategyInfo } from "@/api/strategy/strategyInfo";
import { updateStrategyInfo } from "@/api/strategy/strategyInfo";
import { listStrageyPositionByStrategy } from "@/api/strategy/strageyPositionInfo";
import { listRevenueDetail } from "@/api/strategy/revenueDetail";
import { getArbStatistics } from "@/api/strategy/statisticsAccount";
import { getUserPlatform } from "@/api/strategy/userPlatform";
import ChargeHistory from "@/views/home/ChargeHistory";

export default {
  name: "Arbitrage",
  components: {
    ChargeHistory
  },
  data() {
    return {
      engineOnline: true,
      activeCount: 0,
      cumulativePnl: 0,
      // 策略
      loadingStrategy: false,
      strategyList: [],
      // 仓位
      loadingPosition: false,
      positionList: [],
      _rawPositionList: null,
      // 统计
      statsData: {},
      // 收益
      loadingRevenue: false,
      revenueList: [],
      accountAlias: '',
      accountId: 8,
      // 修改杠杆弹窗
      dialogLeverageVisible: false,
      leverageForm: { strategyId: null, leverage: 1, symbol1: '', symbol2: '' },
      saving: false,
    };
  },
  created() {
    const pathParts = this.$route.path.split('/');
    const lastPart = pathParts[pathParts.length - 1];
    if (lastPart && /^\d+$/.test(lastPart)) {
      this.accountId = parseInt(lastPart);
    }
    this.loadStats();
    this.loadStrategyList();
    this.loadPositionList();
    this.loadRevenue();
    // 每30秒自动刷新
    this._timer = setInterval(() => {
      this.loadPositionList();
      this.loadRevenue();
      this.loadStats();
    }, 30000);
  },
  computed: {
    trackedPairs() {
      return this.strategyList.map(s => `${s.symbol1}/${s.symbol2}`).join(', ') || '—';
    }
  },
  beforeDestroy() {
    clearInterval(this._timer);
  },
  methods: {
    loadStrategyList() {
      this.loadingStrategy = true;
      listStrategyInfo({ strategyType: 7 }).then(res => {
        this.strategyList = (res.rows || []).filter(s => s.strategyStatus === 1);
        this.activeCount = this.strategyList.length;
        this.loadingStrategy = false;
        this.enrichPositionList();
      }).catch(() => { this.loadingStrategy = false; });
    },
    loadPositionList() {
      this.loadingPosition = true;
      listStrageyPositionByStrategy({ apiAccountId: this.accountId }).then(res => {
        this._rawPositionList = (res.rows || []).filter(p => p.exchange === 'arb_binance');
        this.enrichPositionList();
        this.loadingPosition = false;
      }).catch(() => { this.loadingPosition = false; });
    },
    enrichPositionList() {
      if (!this._rawPositionList) return;
      const map = {};
      (this.strategyList || []).forEach(s => {
        const s1 = (s.symbol1 || '').toUpperCase().replace('USDT', '');
        const s2 = (s.symbol2 || '').toUpperCase().replace('USDT', '');
        // symbol_1 是现货腿，symbol_2 是合约腿
        map[s1 + 'USDT'] = { pair: `${s.symbol1}/${s.symbol2}`, legType: '现货' };
        map[s2 + 'USDT'] = { pair: `${s.symbol1}/${s.symbol2}`, legType: '合约' };
      });
      this.positionList = this._rawPositionList.map(p => {
        const info = map[p.symbol] || {};
        return { ...p, pair: info.pair || '—', legType: info.legType || '—' };
      });
    },
    loadRevenue() {
      this.loadingRevenue = true;
      // 获取账户别名
      getUserPlatform(this.accountId).then(platRes => {
        this.accountAlias = (platRes.data && platRes.data.name) || '未知';
      }).catch(() => {});
      // 从收益明细按天聚合
      listRevenueDetail({ apiAccountId: this.accountId, pageNum: 1, pageSize: 9999 }).then(res => {
        const rows = res.rows || [];
        const dayMap = {};
        let prevValue = null;
        rows.forEach(r => {
          const date = (r.dataTime || '').substring(0, 10);
          if (!date) return;
          const profit = parseFloat(r.profit) || 0;
          dayMap[date] = (dayMap[date] || 0) + profit;
        });
        // 转为数组，按日期倒序
        const list = Object.keys(dayMap).map(date => ({
          alias: this.accountAlias,
          date: date,
          equity: 0,
          nav: 0,
          pnl: parseFloat(dayMap[date].toFixed(2)),
          yieldPct: 0
        })).sort((a, b) => b.date.localeCompare(a.date));
        // 计算累计净值和收益率
        const initUsdt = this.statsData.initUsdt || 10000;
        let cumPnl = 0;
        for (let i = list.length - 1; i >= 0; i--) {
          cumPnl += list[i].pnl;
          list[i].equity = parseFloat((initUsdt + cumPnl).toFixed(2));
          list[i].nav = parseFloat(((initUsdt + cumPnl) / initUsdt).toFixed(4));
        }
        // 计算每日收益率
        for (let i = 0; i < list.length; i++) {
          const prevEquity = i < list.length - 1 ? list[i + 1].equity : (this.statsData.initUsdt || 10000);
          list[i].yieldPct = prevEquity > 0 ? parseFloat(((list[i].equity - prevEquity) / prevEquity * 100).toFixed(2)) : 0;
        }
        // 过滤掉当天（北京时间未过0点不显示当天收益）
        const todayStr = this.getBeijingDateStr();
        this.revenueList = list.filter(item => item.date !== todayStr);
        this.loadingRevenue = false;
      }).catch(() => { this.loadingRevenue = false; });
    },
    loadStats() {
      getArbStatistics(this.accountId).then(res => {
        if (res.data) {
          const parsed = {};
          Object.keys(res.data).forEach(k => {
            const v = res.data[k];
            parsed[k] = (typeof v === 'string' && !isNaN(Number(v))) ? Number(v) : v;
          });
          this.statsData = parsed;
          this.cumulativePnl = parseFloat(parsed.totalProfit) || 0;
        }
      }).catch(() => {});
    },
    getBeijingDateStr() {
      const now = new Date();
      const beijing = new Date(now.getTime() + 8 * 60 * 60 * 1000);
      const y = beijing.getUTCFullYear();
      const m = String(beijing.getUTCMonth() + 1).padStart(2, '0');
      const d = String(beijing.getUTCDate()).padStart(2, '0');
      return `${y}-${m}-${d}`;
    },
    viewStrategyRatio(row) {
      this.$alert(
        `<b>${row.symbol1} / ${row.symbol2}</b><br/>
         基准比值: ${parseFloat(row.initLine).toFixed(6)}<br/>
         网格间距: ${parseFloat(row.gridGap).toFixed(6)}<br/>
         开仓条件: 比值偏离基准超过 ${parseFloat(row.gridGap).toFixed(6)}`,
        '策略详情', { dangerouslyUseHTMLString: true }
      );
    },
    handleEditLeverage(row) {
      this.leverageForm = {
        strategyId: row.strategyId,
        leverage: row.leverage || 1,
        symbol1: row.symbol1 || '',
        symbol2: row.symbol2 || '',
      };
      this.dialogLeverageVisible = true;
    },
    saveLeverage() {
      this.saving = true;
      updateStrategyInfo({
        strategyId: this.leverageForm.strategyId,
        leverage: this.leverageForm.leverage,
      }).then(() => {
        this.$message.success('修改成功');
        this.dialogLeverageVisible = false;
        this.loadStrategyList();
      }).catch(() => {
        this.$message.error('修改失败');
      }).finally(() => {
        this.saving = false;
      });
    },
  }
};
</script>
