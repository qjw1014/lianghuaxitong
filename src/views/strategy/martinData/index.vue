<template>
  <div class="dashboard-editor-container">
    <panel-group @handleSetLineChartData="handleSetLineChartData" :account-id="accountId" />

    <el-row style="background:#fff;padding:16px 16px 10px;margin-bottom:32px;">
      <Position-chart :account-id="accountId" />
    </el-row>

    <el-row style="background:#fff;padding:16px 0px 10px;margin-bottom:32px;">
      <line-chart :chart-data="lineChartData" :account-id="accountId" />
    </el-row>

    <el-row style="background:#fff;padding:16px 16px 10px;margin-bottom:32px;">
      <Revenue-detail :account-id="accountId" />
    </el-row>

    <el-row style="background:#fff;padding:16px 16px 10px;margin-bottom:32px;">
      <Charge-history :account-id="accountId" />
    </el-row>
  </div>
</template>

<script>
import PanelGroup from '@/views/home/PanelGroup'
import LineChart from '@/views/home/LineChart'
import PositionChart from '@/views/home/PositionChart'
import RevenueDetail from '@/views/home/RevenueDetail'
import ChargeHistory from '@/views/home/ChargeHistory'

const lineChartData = {
  newVisitis: {
    expectedData: [100, 120, 161, 134, 105, 160, 165],
    actualData: [120, 82, 91, 154, 162, 140, 145]
  },
  messages: {
    expectedData: [200, 192, 120, 144, 160, 130, 140],
    actualData: [180, 160, 151, 106, 145, 150, 130]
  },
  purchases: {
    expectedData: [80, 100, 121, 104, 105, 90, 100],
    actualData: [120, 90, 100, 138, 142, 130, 130]
  },
  shoppings: {
    expectedData: [130, 140, 141, 142, 145, 150, 160],
    actualData: [120, 82, 91, 154, 162, 140, 130]
  }
}

export default {
  name: 'MartinData',
  components: {
    PanelGroup,
    LineChart,
    PositionChart,
    RevenueDetail,
    ChargeHistory
  },
  data() {
    return {
      lineChartData: lineChartData.newVisitis,
      accountId: 8
    }
  },
  created() {
    // 从 URL 路径提取 accountId
    const pathParts = this.$route.path.split('/')
    const lastPart = pathParts[pathParts.length - 1]
    if (lastPart && /^\d+$/.test(lastPart)) {
      this.accountId = parseInt(lastPart)
    }
  },
  methods: {
    handleSetLineChartData(type) {
      this.lineChartData = lineChartData[type]
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;
}
</style>
