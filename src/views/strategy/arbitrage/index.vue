<template>
  <div class="app-container">
    <!-- 引擎状态卡片 -->
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

    <!-- 策略列表 -->
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
        <el-table-column label="交易对" prop="symbol" min-width="140" />
        <el-table-column label="方向" prop="positionSide" width="100">
          <template slot-scope="scope">
            <el-tag :type="scope.row.positionSide === 'BUY' || scope.row.positionSide === 'LONG' ? 'danger' : 'success'" size="small">
              {{ scope.row.positionSide }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="数量" prop="quantity" width="120" />
        <el-table-column label="开仓价" prop="openPrice" width="120" />
        <el-table-column label="当前价" prop="currenPrice" width="120" />
        <el-table-column label="杠杆" prop="leverage" width="80" />
      </el-table>
    </el-card>

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
            <div style="font-size:20px;font-weight:bold;margin-top:8px;color:#00A346">{{ (statsData.totalProfit || 0).toFixed(4) }}</div>
            <div style="font-size:12px;color:#999;margin-top:4px">{{ ((statsData.totalProfitFloat || 0) * 100).toFixed(2) }}% 累计年化</div>
          </div>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="6">
          <div style="text-align:center;padding:15px 0">
            <div style="font-size:12px;color:#999">7天收益</div>
            <div style="font-size:20px;font-weight:bold;margin-top:8px">{{ (statsData.days7Profit || 0).toFixed(4) }}</div>
            <div style="font-size:12px;margin-top:4px">
              <span :style="{color: (statsData.days7ProfitFloat || 0) >= 0 ? '#00A346' : '#C03639'}">{{ ((statsData.days7ProfitFloat || 0) * 100).toFixed(2) }}%</span>
              <span style="color:#999"> 7日年化</span>
            </div>
          </div>
        </el-col>
        <el-col :span="6">
          <div style="text-align:center;padding:15px 0">
            <div style="font-size:12px;color:#999">30天收益</div>
            <div style="font-size:20px;font-weight:bold;margin-top:8px">{{ (statsData.days30Profit || 0).toFixed(4) }}</div>
            <div style="font-size:12px;margin-top:4px">
              <span :style="{color: (statsData.days30ProfitFloat || 0) >= 0 ? '#00A346' : '#C03639'}">{{ ((statsData.days30ProfitFloat || 0) * 100).toFixed(2) }}%</span>
              <span style="color:#999"> 30日年化</span>
            </div>
          </div>
        </el-col>
      </el-row>
    </el-card>

    <!-- 最近收益 -->
    <el-card shadow="hover">
      <div slot="header"><span>收益明细</span></div>
      <el-table v-loading="loadingRevenue" :data="revenueList" border>
        <el-table-column label="日期" prop="dataTime" width="120" />
        <el-table-column label="收益(USDT)" prop="profit" width="120">
          <template slot-scope="scope">
            <span :style="{color: parseFloat(scope.row.profit) >= 0 ? '#00A346' : '#C03639'}">
              {{ parseFloat(scope.row.profit).toFixed(2) }}
            </span>
          </template>
        </el-table-column>
        <el-table-column label="记录时间" prop="createTime" min-width="160">
          <template slot-scope="scope">{{ parseTime(scope.row.createTime) }}</template>
        </el-table-column>
      </el-table>
      <pagination v-show="revenueTotal > 0" :total="revenueTotal" :page.sync="revenuePage" :limit.sync="revenuePageSize" @pagination="loadRevenue" :autoScroll="false" />
    </el-card>
  </div>
</template>

<script>
import { listStrategyInfo } from "@/api/strategy/strategyInfo";
import { updateStrategyInfo } from "@/api/strategy/strategyInfo";
import { listStrageyPositionByStrategy } from "@/api/strategy/strageyPositionInfo";
import { listRevenueDetail } from "@/api/strategy/revenueDetail";
import { getArbStatistics } from "@/api/strategy/statisticsAccount";

export default {
  name: "Arbitrage",
  data() {
    return {
      engineOnline: true,
      activeCount: 0,
      trackedPairs: 'BTCDOM/ETH',
      cumulativePnl: 0,
      // 策略
      loadingStrategy: false,
      strategyList: [],
      // 仓位
      loadingPosition: false,
      positionList: [],
      // 统计
      statsData: {},
      // 收益
      loadingRevenue: false,
      revenueList: [],
      revenueTotal: 0,
      revenuePage: 1,
      revenuePageSize: 10,
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
    this.loadStrategyList();
    this.loadPositionList();
    this.loadRevenue();
    this.loadStats();
  },
  methods: {
    loadStrategyList() {
      this.loadingStrategy = true;
      listStrategyInfo({ strategyType: 7 }).then(res => {
        this.strategyList = (res.rows || []).filter(s => s.strategyStatus === 1);
        this.activeCount = this.strategyList.length;
        this.loadingStrategy = false;
      }).catch(() => { this.loadingStrategy = false; });
    },
    loadPositionList() {
      this.loadingPosition = true;
      listStrageyPositionByStrategy({ apiAccountId: this.accountId }).then(res => {
        // 只显示套利引擎的仓位（exchange=arb_binance）
        this.positionList = (res.rows || []).filter(p => p.exchange === 'arb_binance');
        this.loadingPosition = false;
      }).catch(() => { this.loadingPosition = false; });
    },
    loadRevenue() {
      this.loadingRevenue = true;
      listRevenueDetail({
        pageNum: this.revenuePage,
        pageSize: this.revenuePageSize,
        apiAccountId: this.accountId
      }).then(res => {
        this.revenueList = res.rows || [];
        this.revenueTotal = res.total || 0;
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
