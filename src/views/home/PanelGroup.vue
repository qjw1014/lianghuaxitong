<template>
  <el-row :gutter="40" class="panel-group"  :data="incomeList">
<!--    <el-col :xs="12" :sm="12" :lg="4" class="card-panel-col">-->
<!--      <div class="card-panel" @click="handleSetLineChartData('newVisitis')">-->
<!--        <div style="color: #C03639" >-->
<!--          <svg-icon icon-class="money" style="width: 15px;height: 15px"/>-->
<!--        </div>-->
<!--        <div class="card-panel-description" style="float: left;margin: 0px 0px 0px 30px ">-->
<!--          <div class="card-panel-text">-->
<!--            本金-->
<!--          </div>-->
<!--           <count-to :start-val="0" :end-val="statistics.initUsdt" :duration="1000" :decimals="4" class="card-panel-num" />-->
<!--        </div>-->
<!--      </div>-->
<!--    </el-col>-->
    <el-col :xs="12" :sm="12" :lg="8" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('newVisitis')">
        <div class="card-panel-icon-wrapper icon-money">
          <svg-icon icon-class="money" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            用户总余额
          </div>
          <count-to :start-val="0" :end-val="statistics.initUsdt" :duration="1000" :decimals="2" class="card-panel-num" />
        </div>
      </div>
    </el-col>
    <el-col :xs="12" :sm="12" :lg="8" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('messages')">
        <div class="card-panel-icon-wrapper icon-money">
          <svg-icon icon-class="money" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            近30天充提
          </div>
          <count-to :start-val="0" :end-val="statistics.charge" :duration="1000" :decimals="4" class="card-panel-num" />
        </div>
      </div>
    </el-col>
    <el-col :xs="12" :sm="12" :lg="8" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('purchases')">
        <div class="card-panel-icon-wrapper icon-money">
          <svg-icon icon-class="money" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description">
          <div class="card-panel-text">
            最新净值
          </div>
          <count-to :start-val="0" :end-val="statistics.currentUsdt"  :duration="1000" :decimals="4" class="card-panel-num" />
        </div>
      </div>
    </el-col>
    <el-col :xs="12" :sm="12" :lg="8" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('shoppings')">
        <div class="card-panel-icon-wrapper icon-money">
          <svg-icon icon-class="money" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description" >
          <div class="card-panel-text">
            7天收益
          </div>
          <count-to :start-val="0" :end-val="statistics.days7Profit"  :duration="1000" :decimals="4" class="card-panel-num" :style="{color: statistics.days7Profit >= 0 ? '#00A346' : '#C03639'}" />
          <div>
            <span style="color: #00A346" v-if="statistics.days7ProfitFloat>0">{{(statistics.days7ProfitFloat*100).toFixed(2)}}%</span>
            <span style="color: #C03639" v-if="statistics.days7ProfitFloat<0">{{(statistics.days7ProfitFloat*100).toFixed(2)}}%</span>
            7日年化收益
          </div>
        </div>
      </div>
    </el-col>
    <el-col :xs="12" :sm="12" :lg="8" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('purchases')">
        <div class="card-panel-icon-wrapper icon-money">
          <svg-icon icon-class="money" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description" >
          <div class="card-panel-text">
            30天收益
          </div>
          <count-to :start-val="0" :end-val="statistics.days30Profit"  :duration="1000" :decimals="4" class="card-panel-num" :style="{color: statistics.days30Profit >= 0 ? '#00A346' : '#C03639'}" />
          <div>
            <span style="color: #00A346" v-if="statistics.days30ProfitFloat>0">{{(statistics.days30ProfitFloat*100).toFixed(2)}}%</span>
            <span style="color: #C03639" v-if="statistics.days30ProfitFloat<0">{{(statistics.days30ProfitFloat*100).toFixed(2)}}%</span>
            30日年化收益
          </div>
        </div>
      </div>
    </el-col>
    <el-col :xs="12" :sm="12" :lg="8" class="card-panel-col">
      <div class="card-panel" @click="handleSetLineChartData('shoppings')">
        <div class="card-panel-icon-wrapper icon-money">
          <svg-icon icon-class="money" class-name="card-panel-icon" />
        </div>
        <div class="card-panel-description" >
          <div class="card-panel-text">
            累计收益
          </div>
          <count-to :start-val="0" :end-val="statistics.totalProfit"  :duration="1000" :decimals="4" class="card-panel-num" :style="{color: statistics.totalProfit >= 0 ? '#00A346' : '#C03639'}" />
          <div>
            <span style="color: #00A346" v-if="statistics.totalProfitFloat>0">{{(statistics.totalProfitFloat*100).toFixed(2)}}%</span>
            <span style="color: #C03639" v-if="statistics.totalProfitFloat<0">{{(statistics.totalProfitFloat*100).toFixed(2)}}%</span>
            累计年化收益
          </div>
        </div>
      </div>
    </el-col>
  </el-row>
</template>

<script>
import { getCountOrder } from "@/api/strategy/martinStrategyOrder";
import { getStatisticsAccount } from "@/api/strategy/statisticsAccount";
import CountTo from 'vue-count-to'

export default {
  props: {
    accountId: { type: Number, default: null }
  },
  data() {
    return {
      incomeList:{},
      // 统计数据
      statistics: {
        charge:0,
        currentUsdt:0,
        days7Profit:0,
        days7ProfitFloat:0,
        days30Profit:0,
        days30ProfitFloat:0,
        initUsdt:0,
        totalProfit:0,
        totalProfitFloat:0,
      },
      }
  },
  components: {
    CountTo
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
   console.log("账号编号="+this.accountId);
   this.findSysIncomeList();
   this.getStatisticsAccountId();
   // 每30秒自动刷新统计数据
   this._timer = setInterval(() => { this.getStatisticsAccountId(); }, 30000);
 },
  beforeDestroy() {
    clearInterval(this._timer);
  },
  methods: {
    handleSetLineChartData(type) {
      this.$emit('handleSetLineChartData', type)
    },

    findSysIncomeList() {
      getCountOrder().then(response => {
        this.incomeList=response.data;
      });
    },
    /** 查询统计数据 */
    getStatisticsAccountId() {
      getStatisticsAccount(this.accountId).then(response => {
        if (response.data) {
          this.statistics = response.data;
        }
      });
    },
  }
}
</script>

<style lang="scss" scoped>
.panel-group {
  margin-top: 18px;

  .card-panel-col {
    margin-bottom: 32px;
  }

  .card-panel {
    height: 108px;
    cursor: pointer;
    font-size: 12px;
    position: relative;
    overflow: hidden;
    color: #666;
    background: #fff;
    box-shadow: 4px 4px 40px rgba(0, 0, 0, .05);
    border-color: rgba(0, 0, 0, .05);

    &:hover {
      .card-panel-icon-wrapper {
        color: #fff;
      }

      .icon-people {
        background: #40c9c6;
      }

      .icon-message {
        background: #36a3f7;
      }

      .icon-money {
        background: #f4516c;
      }

      .icon-shopping {
        background: #34bfa3
      }
    }

    .icon-people {
      color: #40c9c6;
    }

    .icon-message {
      color: #36a3f7;
    }

    .icon-money {
      color: #f4516c;
    }

    .icon-shopping {
      color: #34bfa3
    }

    .card-panel-icon-wrapper {
      float: left;
      margin: 14px 0 0 14px;
      padding: 16px;
      transition: all 0.38s ease-out;
      border-radius: 6px;
    }

    .card-panel-icon {
      float: left;
      font-size: 48px;
    }

    .card-panel-description {
      float: right;
      font-weight: bold;
      margin: 26px;
      margin-left: 0px;

      .card-panel-text {
        line-height: 18px;
        color: rgba(0, 0, 0, 0.45);
        font-size: 16px;
        margin-bottom: 12px;
      }

      .card-panel-num {
        font-size: 20px;
      }
    }
  }
}

@media (max-width:550px) {
  .card-panel-description {
    display: none;
  }

  .card-panel-icon-wrapper {
    float: none !important;
    width: 100%;
    height: 100%;
    margin: 0 !important;

    .svg-icon {
      display: block;
      margin: 14px auto !important;
      float: none !important;
    }
  }
}
</style>
