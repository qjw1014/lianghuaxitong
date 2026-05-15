<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="状态" prop="strategyStatus">
        <el-select v-model="queryParams.strategyStatus" placeholder="请选择状态" clearable size="small">
          <el-option
            v-for="dict in strategyStatusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
<!--      <el-form-item label="用户编号" prop="userUuid">-->
<!--        <el-input-->
<!--          v-model="queryParams.userUuid"-->
<!--          placeholder="请输入用户编号"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="交易所" prop="exchange">
        <el-input
          v-model="queryParams.exchange"
          placeholder="请输入交易所"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="子账号编号" prop="apiAccountId">-->
<!--        <el-input-->
<!--          v-model="queryParams.apiAccountId"-->
<!--          placeholder="请输入子账号编号"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="交易对" prop="symbol">-->
<!--        <el-input-->
<!--          v-model="queryParams.symbol"-->
<!--          placeholder="请输入交易对"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="执行币种" prop="coin">-->
<!--        <el-input-->
<!--          v-model="queryParams.coin"-->
<!--          placeholder="请输入执行币种"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="买方" prop="buyer">-->
<!--        <el-input-->
<!--          v-model="queryParams.buyer"-->
<!--          placeholder="请输入买方"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="卖方" prop="seller">-->
<!--        <el-input-->
<!--          v-model="queryParams.seller"-->
<!--          placeholder="请输入卖方"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="是否一边" prop="isMaker">-->
<!--        <el-input-->
<!--          v-model="queryParams.isMaker"-->
<!--          placeholder="请输入是否一边maker一边taker"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['strategy:strategyInfo:add']"
        >新增</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['strategy:strategyInfo:edit']"-->
<!--        >修改</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="danger"-->
<!--          plain-->
<!--          icon="el-icon-delete"-->
<!--          size="mini"-->
<!--          :disabled="multiple"-->
<!--          @click="handleDelete"-->
<!--          v-hasPermi="['strategy:strategyInfo:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['strategy:strategyInfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="strategyInfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="策略编号" align="center" prop="strategyId" />
      <el-table-column label="策略类型" align="center" prop="strategyType" :formatter="strategyTypeFormat"/>
      <el-table-column label="启动状态" align="center" prop="strategyStatus" :formatter="strategyStatusFormat" />
<!--      <el-table-column label="用户编号" align="center" prop="userUuid" />-->
      <el-table-column label="交易所" align="center" prop="exchange" />
      <el-table-column label="子账号编号" align="center" prop="apiAccountId" />
<!--      <el-table-column label="交易对" align="center" prop="symbol" />-->
      <el-table-column label="执行币种" align="center" prop="coin" />
<!--      <el-table-column label="买方" align="center" prop="buyer" />-->
<!--      <el-table-column label="卖方" align="center" prop="seller" />-->
<!--      <el-table-column label="每张对应的面值" align="center" prop="value" />-->
<!--      <el-table-column label="每笔下单量" align="center" prop="everyAmount" />-->
<!--      <el-table-column label="总下单量" align="center" prop="totalAmount" />-->
<!--      <el-table-column label="下单次数" align="center" prop="tradeCount" />-->
<!--      <el-table-column label="是否一边maker一边taker" align="center" prop="isMaker" />-->
      <el-table-column label="杠杆倍数" align="center" prop="leverage" />
      <el-table-column label="投入的本金" align="center" prop="initUsdt" />
<!--      <el-table-column label="每一档的开仓金额" align="center" prop="everyVolume" />-->
<!--      <el-table-column label="币种1" align="center" prop="symbol1" />-->
<!--      <el-table-column label="币种2" align="center" prop="symbol2" />-->
<!--      <el-table-column label="币1的下单数量精度" align="center" prop="amountDecimal1" />-->
<!--      <el-table-column label="币2的下单数量精度" align="center" prop="amountDecimal2" />-->
<!--      <el-table-column label="币1的下单价格精度" align="center" prop="priceDecimal1" />-->
<!--      <el-table-column label="币2的下单价格精度" align="center" prop="priceDecimal2" />-->
<!--      <el-table-column label="是否一键全平 no否 yes是" align="center" prop="isCloseAll" />-->
<!--      <el-table-column label="K线粒度，如15m" align="center" prop="klinePeriod" />-->
<!--      <el-table-column label="初始均线， coin1/coin2的价格比例在初始均线之上则coin1做空，coin2做多，在下则反之" align="center" prop="initLine" />-->
<!--      <el-table-column label="网格间距" align="center" prop="gridGap" />-->
<!--      <el-table-column label="价格上限" align="center" prop="lowLimitPrice" />-->
<!--      <el-table-column label="价格下限" align="center" prop="highLimitPrice" />-->
<!--      <el-table-column label="其他参数" align="center" prop="detailInfo" />-->
      <el-table-column label="添加时间" align="center" prop="crateTime" width="180">
        <template slot-scope="scope">
          <span>{{parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope" >
          <el-button v-if="scope.row.strategyStatus==0 || scope.row.strategyStatus==2"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="updateStatus(scope.row,1,'开启')"
            v-hasPermi="['strategy:strategyInfo:edit']"
          >开启</el-button>
          <el-button v-if="scope.row.strategyStatus==1"
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="updateStatus(scope.row,2,'停止')"
            v-hasPermi="['strategy:strategyInfo:edit']"
          >停止</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['strategy:strategyInfo:edit']"
          >修改</el-button>
          <el-button v-if="scope.row.strategyStatus==2 || scope.row.strategyStatus==3||scope.row.strategyStatus==5"
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['strategy:strategyInfo:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改策略信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="900px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="140px">
<!--        <el-form-item label="状态">-->
<!--          <el-radio-group v-model="form.strategyStatus">-->
<!--            <el-radio-->
<!--              v-for="dict in strategyStatusOptions"-->
<!--              :key="dict.dictValue"-->
<!--              :label="parseInt(dict.dictValue)"-->
<!--            >{{dict.dictLabel}}</el-radio>-->
<!--          </el-radio-group>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="用户编号" prop="userUuid">-->
<!--          <el-input v-model="form.userUuid" placeholder="请输入用户编号" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="交易所" prop="exchange" v-if="">-->
<!--          <el-input v-model="form.exchange" placeholder="请输入交易所" />-->
<!--        </el-form-item>-->
        <el-row>
          <el-col :span="12" >
            <el-form-item label="交易所" prop="exchange" >
              <!--              <el-input v-model="form.platform" placeholder="请输入交易所" />-->
              <el-select v-model="form.exchange" placeholder="请输入交易所" @change="handleSelectStrategyExchange">
                <el-option
                  v-for="dict in platformListAll"
                  :key="dict.name"
                  :label="dict.name"
                  :value="dict.name"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12" >
          <el-form-item label="子账号编号" prop="apiAccountId" v-if="form.exchange!=null && form.exchange!=''">
            <!--          <el-input v-model="form.apiAccountId" placeholder="请输入子账号编号" />-->
            <el-select v-model="form.apiAccountId" placeholder="请输入子账号编号" >
              <el-option
                v-for="dict in apiAccountList"
                :key="dict.id"
                :label="dict.name"
                :value="dict.id"
              ></el-option>
            </el-select>
          </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12" >
            <el-form-item label="策略类型" prop="strategyType">
              <el-select v-model="form.strategyType" placeholder="请选择策略类型"  @change="handleSelectStrategyTypeUpdate">
                <el-option
                  v-for="dict in strategyTypeListAll"
                  :key="dict.id"
                  :label="dict.name"
                  :value="dict.id"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>

<!--        <el-form-item label="交易对" prop="symbol">-->
<!--          <el-input v-model="form.symbol" placeholder="请输入交易对" />-->
<!--        </el-form-item>-->
          <el-col :span="12" >
<!--            v-if="this.selectStrategyField('coin')"-->
            <el-form-item label="币种" prop="coin" >
              <el-select v-model="form.coin" placeholder="请输入币种" >
                <el-option
                  v-for="dict in coinInfoListAll"
                  :key="dict.name"
                  :label="dict.name"
                  :value="dict.name"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12" >
            <el-form-item label="买方" prop="buyer" v-if="this.selectStrategyField('buyer')">
<!--              <el-input v-model="form.buyer" placeholder="请输入买方" style=width:218px  />-->
              <el-select v-model="form.buyer" placeholder="请选择买方" >
                <el-option
                  v-for="dict in buySellOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12" >
            <el-form-item label="卖方" prop="seller" v-if="this.selectStrategyField('seller')">
<!--              <el-input v-model="form.seller" placeholder="请输入卖方" style=width:218px />-->
              <el-select v-model="form.seller" placeholder="请选择卖方" >
                <el-option
                  v-for="dict in buySellOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12" >
            <el-form-item label="每张对应面值" prop="value" v-if="this.selectStrategyField('value')">
              <el-input v-model="form.value" placeholder="每张对应面值" style=width:218px />
            </el-form-item>
          </el-col>
          <el-col :span="12" >
            <el-form-item label="开平仓" prop="action" v-if="this.selectStrategyField('action')">
              <!--              <el-input v-model="form.action" placeholder="请输入open开仓，close平仓" style=width:218px />-->
              <el-select v-model="form.action" placeholder="请输入open开仓，close平仓" >
                <el-option
                  v-for="dict in openCloseOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12" >
            <el-form-item label="每笔下单量" prop="everyAmount" v-if="this.selectStrategyField('every_amount')">
              <el-input v-model="form.everyAmount" placeholder="请输入每笔下单量" style=width:218px />
            </el-form-item>
          </el-col>
          <el-col :span="12" >
            <el-form-item label="总下单量" prop="totalAmount" v-if="this.selectStrategyField('total_amount')">
              <el-input v-model="form.totalAmount" placeholder="请输入总下单量" style=width:218px />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12" >
            <el-form-item label="下单次数" prop="tradeCount" v-if="this.selectStrategyField('trade_count')">
              <el-input v-model="form.tradeCount" placeholder="请输入下单次数" style=width:218px />
            </el-form-item>
          </el-col>
          <el-col :span="12" >
            <el-form-item label="是否一边maker一边taker" prop="isMaker" v-if="this.selectStrategyField('is_maker')">
<!--              <el-input v-model="form.isMaker" placeholder="请输入是否一边maker一边taker" />-->
              <el-select v-model="form.isMaker" placeholder="请输入是否一边maker一边taker" >
                <el-option
                  v-for="dict in isMakerOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12" >
            <el-form-item label="投入的本金" prop="initUsdt" v-if="this.selectStrategyField('init_usdt')">
              <el-input v-model="form.initUsdt" placeholder="请输入投入的本金" style=width:218px />
            </el-form-item>
          </el-col>
          <el-col :span="12" >
            <el-form-item label="杠杆倍数" prop="leverage">
              <el-input-number v-model="form.leverage" :min="1" :max="125" :step="1" style="width:218px" placeholder="杠杆倍数" />
            </el-form-item>
          </el-col>
          <el-col :span="12" >
            <el-form-item label="每一档的开仓金额" prop="everyVolume" v-if="this.selectStrategyField('every_volume')">
              <el-input v-model="form.everyVolume" placeholder="请输入每一档的开仓金额" style=width:218px />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12" >
            <el-form-item label="币种1" prop="symbol1" v-if="this.selectStrategyField('symbol_1')">
<!--              <el-input v-model="form.symbol1" placeholder="请输入币种1" style=width:218px />-->
              <el-select v-model="form.symbol1" placeholder="请输入币种1" >
                <el-option
                  v-for="dict in coinInfoListAll"
                  :key="dict.name"
                  :label="dict.name"
                  :value="dict.name"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12" >
            <el-form-item label="币种2" prop="symbol2" v-if="this.selectStrategyField('symbol_2')">
<!--              <el-input v-model="form.symbol2" placeholder="请输入币种2" style=width:218px />-->
              <el-select v-model="form.symbol2" placeholder="请输入币种2" >
                <el-option
                  v-for="dict in coinInfoListAll"
                  :key="dict.name"
                  :label="dict.name"
                  :value="dict.name"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12" >
            <el-form-item label="币1的下单数量精度" prop="amountDecimal1" v-if="this.selectStrategyField('amount_decimal_1')">
              <el-input v-model="form.amountDecimal1" placeholder="请输入币1的下单数量精度" style=width:218px />
            </el-form-item>
          </el-col>
          <el-col :span="12" >
            <el-form-item label="币2的下单数量精度" prop="amountDecimal2" v-if="this.selectStrategyField('amount_decimal_2')">
              <el-input v-model="form.amountDecimal2" placeholder="请输入币2的下单数量精度" style=width:218px />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12" >
            <el-form-item label="币1的下单价格精度" prop="priceDecimal1" v-if="this.selectStrategyField('price_decimal_1')">
              <el-input v-model="form.priceDecimal1" placeholder="请输入币1的下单价格精度" style=width:218px />
            </el-form-item>
          </el-col>
          <el-col :span="12" >
            <el-form-item label="币2的下单价格精度" prop="priceDecimal2" v-if="this.selectStrategyField('price_decimal_2')">
              <el-input v-model="form.priceDecimal2" placeholder="请输入币2的下单价格精度" style=width:218px />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12" >
            <el-form-item label="是否一键全平" prop="isCloseAll" v-if="this.selectStrategyField('is_close_all')">
<!--              <el-input v-model="form.isCloseAll" placeholder="请输入是否一键全平 no否 yes是" />-->
              <el-select v-model="form.isCloseAll" placeholder="请输入是否一键全平 no否 yes是" >
                <el-option
                  v-for="dict in yesNoOptions"
                  :key="dict.dictValue"
                  :label="dict.dictLabel"
                  :value="dict.dictValue"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12" >
            <el-form-item label="K线粒度" prop="klinePeriod" v-if="this.selectStrategyField('kline_period')">
              <el-input v-model="form.klinePeriod" placeholder="请输入K线粒度，如15m" style=width:218px />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12" >
            <el-form-item label="初始均线" prop="initLine" v-if="this.selectStrategyField('init_line')">
              <el-input v-model="form.initLine" placeholder="请输入初始均线" style=width:218px />
            </el-form-item>
          </el-col>
          <el-col :span="12" >
            <el-form-item label="网格间距" prop="gridGap" v-if="this.selectStrategyField('grid_gap')">
              <el-input v-model="form.gridGap" placeholder="请输入网格间距" style=width:218px />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12" >
            <el-form-item label="价格上限" prop="lowLimitPrice" v-if="this.selectStrategyField('low_limit_price')">
              <el-input v-model="form.lowLimitPrice" placeholder="请输入价格上限" style=width:218px />
            </el-form-item>
          </el-col>
          <el-col :span="12" >
            <el-form-item label="价格下限" prop="highLimitPrice" v-if="this.selectStrategyField('high_limit_price')">
              <el-input v-model="form.highLimitPrice" placeholder="请输入价格下限" style=width:218px />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12" >
            <el-form-item label="其他参数" prop="detailInfo" v-if="this.selectStrategyField('detail_info')">
              <el-input v-model="form.detailInfo" placeholder="请输入其他参数"  style=width:218px  />
            </el-form-item>
          </el-col>

        </el-row>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listStrategyInfo, getStrategyInfo,updateDelete,updateStatus, delStrategyInfo, addStrategyInfo, updateStrategyInfo, exportStrategyInfo } from "@/api/strategy/strategyInfo";
import { listStrategyTypeAll } from "@/api/strategy/strategyType";
import { listCoinInfoAll} from "@/api/strategy/coinInfo";
import { listPlatformAll} from "@/api/strategy/platform";
import { listCommonField,typeList, getCommonField, delCommonField, addCommonField, updateCommonField, exportCommonField } from "@/api/strategy/commonField";
import { listUserPlatformAll } from "@/api/strategy/userPlatform";

export default {
  name: "StrategyInfo",
  components: {
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
      // 策略信息表格数据
      strategyInfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 状态0等待开启，1开启，2停止，3已完成字典
      strategyStatusOptions: [],
      // 策略类型列表
      strategyTypeListAll: [],
      // 币种列表
      coinInfoListAll: [],
      // 交易所列表
      platformListAll: [],
      //策略类型选中字段
      allSelectedList:[],
      //所有子账号api
      apiAccountList:[],
      //是否一边maker一边taker（1是，0否）
      isMakerOptions:[],
      //是否
      yesNoOptions:[],
      //买方（现货spot，杠杆margin，coinswap币本位合约，  usdtswap U本位 合约）
      buySellOptions:[],
      // 开平仓，open开仓，close平仓
      openCloseOptions:[],
      // 查询参数
      queryAccountParams: {
        platformName: null
      },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        strategyStatus: null,
        userUuid: null,
        exchange: null,
        apiAccountId: null,
        symbol: null,
        coin: null,
        buyer: null,
        seller: null,
        value: null,
        everyAmount: null,
        totalAmount: null,
        tradeCount: null,
        isMaker: null,
        leverage: null,
        initUsdt: null,
        everyVolume: null,
        symbol1: null,
        symbol2: null,
        amountDecimal1: null,
        amountDecimal2: null,
        priceDecimal1: null,
        priceDecimal2: null,
        isCloseAll: null,
        klinePeriod: null,
        initLine: null,
        gridGap: null,
        lowLimitPrice: null,
        highLimitPrice: null,
        detailInfo: null,
        strategyType: null,
        action:null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        strategyStatus: [
          { required: true, message: "状态0等待开启，1开启，2停止，3已完成不能为空", trigger: "blur" }
        ],
        exchange: [
          { required: true, message: "交易所不能为空", trigger: "blur" }
        ],
        apiAccountId: [
          { required: true, message: "子账号不能为空", trigger: "blur" }
        ],
        strategyType: [
          { required: true, message: "类型不能为空", trigger: "blur" }
        ],
        coin: [
          { required: true, message: "币种不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getStrategyTypeListAll();
    this.getDicts("strategy_status").then(response => {
      this.strategyStatusOptions = response.data;
    });
    this.getDicts("is_maker").then(response => {
      this.isMakerOptions = response.data;
    });
    this.getDicts("yes_no").then(response => {
      this.yesNoOptions = response.data;
    });
    this.getDicts("buy_sell").then(response => {
      this.buySellOptions = response.data;
    });
    this.getDicts("open_close").then(response => {
      this.openCloseOptions = response.data;
    });


  },
  methods: {
    /** 查询策略信息列表 */
    getList() {
      this.loading = true;
      listStrategyInfo(this.queryParams).then(response => {
        this.strategyInfoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getApiAccountListAll(platformName){
      this.apiAccountList=[];
      if(platformName){
        this.queryAccountParams.platformName =  platformName;
      }
      listUserPlatformAll(this.queryAccountParams).then(response => {
        this.apiAccountList = response.data;
      });
    },
    getStrategyTypeListAll(){
      listStrategyTypeAll().then(response => {
        this.strategyTypeListAll = response.data;
      });
    },
    getCoinInfoListAll(){
      listCoinInfoAll().then(response => {
        this.coinInfoListAll = response.data;
      });
    },
    getPlatformListAll(){
      listPlatformAll().then(response => {
        this.platformListAll = response.data;
      });
    },
    getFileListAll(id){
      typeList(id).then(response => {
        this.allSelectedList = response.data;
      });
    },
    // 策略类型
    strategyTypeFormat(row, column) {
      return this.selectStrategyType(this.strategyTypeListAll, row.strategyType);
    },
    // 回显数据字典
    selectStrategyType(datas, value) {
      var actions = [];
      Object.keys(datas).some((key) => {
        if (datas[key].id == ('' + value)) {
          actions.push(datas[key].name);
          return true;
        }
      })
      return actions.join('');
    },
    // 状态0等待开启，1开启，2停止，3已完成字典翻译
    strategyStatusFormat(row, column) {
      return this.selectDictLabel(this.strategyStatusOptions, row.strategyStatus);
    },
    //下拉查询当前可显示字段
    handleSelectStrategyTypeUpdate(event, item){
      this.getFileListAll(event);
    },

    //选择交易所，筛选对应的api
    handleSelectStrategyExchange(event, item){
      this.form.apiAccountId=null;
      console.log("选择名称"+event)
      this.getApiAccountListAll(event);
    },

    // 判断是否有选中字段
    selectStrategyField(value) {
      var actions = [];
      Object.keys(this.allSelectedList).some((key) => {
        if (this.allSelectedList[key].name == ('' + value)) {
          actions.push(this.allSelectedList[key].name);
          return true;
        }
      })
      return actions.join('')==value;
      // return true;
    },


    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.allSelectedList =[];
      this.form = {
        strategyId: null,
        strategyStatus: 0,
        userUuid: null,
        exchange: null,
        apiAccountId: null,
        symbol: null,
        createTime: null,
        updateTime: null,
        coin: null,
        buyer: null,
        seller: null,
        value: null,
        everyAmount: null,
        totalAmount: null,
        tradeCount: null,
        isMaker: null,
        leverage: null,
        initUsdt: null,
        everyVolume: null,
        symbol1: null,
        symbol2: null,
        amountDecimal1: null,
        amountDecimal2: null,
        priceDecimal1: null,
        priceDecimal2: null,
        isCloseAll: null,
        klinePeriod: null,
        initLine: null,
        gridGap: null,
        lowLimitPrice: null,
        highLimitPrice: null,
        detailInfo: null,
        strategyType: null,
        action:null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.strategyId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.getCoinInfoListAll();
      this.getPlatformListAll();
      this.getApiAccountListAll();
      this.reset();
      this.open = true;
      this.title = "添加策略信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.getCoinInfoListAll();
      this.getPlatformListAll();
      this.reset();
      const strategyId = row.strategyId || this.ids
      getStrategyInfo(strategyId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改策略信息";
        this.getFileListAll(row.strategyType);
        this.getApiAccountListAll(this.form.exchange);
      });

    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.strategyId != null) {
            updateStrategyInfo(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStrategyInfo(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },


    /** 开启策略 */
    updateStatus(row,strategyStatus,name) {
      const strategyIds = row.strategyId || this.ids;
      this.$confirm('是否确认"'+name+'"策略信息编号为"' + strategyIds + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        // return delStrategyInfo(strategyIds);
        return updateStatus(strategyIds,strategyStatus);
      }).then(() => {
        this.getList();
        this.msgSuccess("开启成功");
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const strategyIds = row.strategyId || this.ids;
      this.$confirm('是否确认删除策略信息编号为"' + strategyIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          // return delStrategyInfo(strategyIds);
          return updateDelete(strategyIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有策略信息数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportStrategyInfo(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
