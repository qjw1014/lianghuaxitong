<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="策略状态" prop="strategyStatus">
        <el-select v-model="queryParams.strategyStatus" placeholder="请选择策略状态" clearable size="small">
          <el-option
            v-for="dict in strategyStatusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
<!--      <el-form-item label="用户uuid" prop="userUuid">-->
<!--        <el-input-->
<!--          v-model="queryParams.userUuid"-->
<!--          placeholder="请输入用户uuid"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="交易所" prop="platform">
        <el-input
          v-model="queryParams.platform"
          placeholder="请输入交易所"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="交易对" prop="symbol">-->
<!--        <el-input-->
<!--          v-model="queryParams.symbol"-->
<!--          placeholder="请输入交易对"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="子账号id" prop="apiAccountId">-->
<!--        <el-input-->
<!--          v-model="queryParams.apiAccountId"-->
<!--          placeholder="请输入子账号id"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="币种" prop="coin">
        <el-input
          v-model="queryParams.coin"
          placeholder="请输入币种"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="杠杆倍数" prop="leverage">-->
<!--        <el-input-->
<!--          v-model="queryParams.leverage"-->
<!--          placeholder="请输入杠杆倍数"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="多方向 True为启动，False为关闭" prop="longOpen">-->
<!--        <el-input-->
<!--          v-model="queryParams.longOpen"-->
<!--          placeholder="请输入多方向 True为启动，False为关闭"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="多方向次数 once为单次，cycle为循环" prop="longCount">-->
<!--        <el-input-->
<!--          v-model="queryParams.longCount"-->
<!--          placeholder="请输入多方向次数 once为单次，cycle为循环"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="空方向 True为启动，False为关闭" prop="shortOpen">-->
<!--        <el-input-->
<!--          v-model="queryParams.shortOpen"-->
<!--          placeholder="请输入空方向 True为启动，False为关闭"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="空方向次数 once为单次，cycle为循环" prop="shortCount">-->
<!--        <el-input-->
<!--          v-model="queryParams.shortCount"-->
<!--          placeholder="请输入空方向次数 once为单次，cycle为循环"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="默认备用 True为启动，False为关闭" prop="common">-->
<!--        <el-input-->
<!--          v-model="queryParams.common"-->
<!--          placeholder="请输入默认备用 True为启动，False为关闭"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="一键平多比例" prop="sellLongRate">-->
<!--        <el-input-->
<!--          v-model="queryParams.sellLongRate"-->
<!--          placeholder="请输入一键平多比例"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="一键平空比例" prop="buyShortRate">-->
<!--        <el-input-->
<!--          v-model="queryParams.buyShortRate"-->
<!--          placeholder="请输入一键平空比例"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="一键开多比例" prop="buyLongRate">-->
<!--        <el-input-->
<!--          v-model="queryParams.buyLongRate"-->
<!--          placeholder="请输入一键开多比例"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="一键开空比例" prop="sellShortRate">-->
<!--        <el-input-->
<!--          v-model="queryParams.sellShortRate"-->
<!--          placeholder="请输入一键开空比例"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="是否删除" prop="isDelete">
        <el-input
          v-model="queryParams.isDelete"
          placeholder="请输入是否删除"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="策略类型" prop="strategyType">-->
<!--        <el-select v-model="queryParams.strategyType" placeholder="请选择策略类型 1.策略一" clearable size="small">-->
<!--          <el-option label="请选择字典生成" value="" />-->
<!--        </el-select>-->
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
          v-hasPermi="['strategy:martinStrategyInfo:add']"
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
<!--          v-hasPermi="['strategy:martinStrategyInfo:edit']"-->
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
<!--          v-hasPermi="['strategy:martinStrategyInfo:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['strategy:martinStrategyInfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="martinStrategyInfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="策略编号" align="center" prop="strategyId" />
      <el-table-column label="策略类型" align="center" prop="strategyType" :formatter="strategyTypeFormat"/>
      <el-table-column label="策略状态" align="center" prop="strategyStatus" :formatter="strategyStatusFormat" />
<!--      <el-table-column label="用户uuid" align="center" prop="userUuid" />-->
      <el-table-column label="交易所" align="center" prop="platform" />
<!--      <el-table-column label="交易对" align="center" prop="symbol" />-->
<!--      <el-table-column label="子账号id" align="center" prop="apiAccountId" />-->
      <el-table-column label="币种" align="center" prop="coin" />
      <el-table-column label="杠杆倍数" align="center" prop="leverage" />
      <el-table-column label="多方向" align="center" prop="longOpen" :formatter="longOpenFormat" />
      <el-table-column label="多方向次数" align="center" prop="longCount" :formatter="longCountFormat" />
      <el-table-column label="空方向" align="center" prop="shortOpen" :formatter="shortOpenFormat" />
      <el-table-column label="空方向次数" align="center" prop="shortCount" :formatter="shortCountFormat" />
      <el-table-column label="默认备用" align="center" prop="common" :formatter="commonFormat" />
      <el-table-column label="平多比例" align="center" prop="sellLongRate" />
      <el-table-column label="平空比例" align="center" prop="buyShortRate" />
      <el-table-column label="开多比例" align="center" prop="buyLongRate" />
      <el-table-column label="开空比例" align="center" prop="sellShortRate" />
      <el-table-column label="添加时间" align="center" prop="crateTime" width="180">
        <template slot-scope="scope">
          <span>{{parseTime(scope.row.crateTime) }}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="修改时间" align="center" prop="stopTime" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.stopTime) }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="是否删除" align="center" prop="isDelete" :formatter="isDeleteFormat" />-->
<!--      <el-table-column label="收益数量" align="center" prop="allProfit" />-->
<!--      <el-table-column label="扣除数量" align="center" prop="deductProfit" />-->
<!--      <el-table-column label="周收益" align="center" prop="weekProfit" />-->
<!--      <el-table-column label="周分润比例" align="center" prop="weekProfitRate" />-->
<!--      <el-table-column label="已分润数量" align="center" prop="shareProfit" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['strategy:martinStrategyInfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['strategy:martinStrategyInfo:remove']"
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
    <el-dialog :title="title" :visible.sync="open" width="1200px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-form-item label="策略状态">
          <el-radio-group v-model="form.strategyStatus">
            <el-radio
              v-for="dict in strategyStatusOptions"
              :key="dict.dictValue"
              :label="parseInt(dict.dictValue)"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
<!--        <el-form-item label="用户uuid" prop="userUuid">-->
<!--          <el-input v-model="form.userUuid" placeholder="请输入用户uuid" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="交易对" prop="symbol">-->
<!--          <el-input v-model="form.symbol" placeholder="请输入交易对" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="子账号id" prop="apiAccountId">-->
<!--          <el-input v-model="form.apiAccountId" placeholder="请输入子账号id" />-->
<!--        </el-form-item>-->
        <el-row>
          <el-col :span="12" >
            <el-form-item label="交易所" prop="platform" >
<!--              <el-input v-model="form.platform" placeholder="请输入交易所" />-->
              <el-select v-model="form.platform" placeholder="请输入交易所">
                <el-option
                  v-for="dict in platformListAll"
                  :key="dict.name"
                  :label="dict.name"
                  :value="dict.name"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="币种" prop="coin">
<!--              <el-input v-model="form.coin" placeholder="请输入币种" />-->
              <el-select v-model="form.coin" placeholder="请输入币种" @change="handleSelectCoinUpdate">
                <el-option
                  v-for="dict in coinInfoListAll"
                  :key="dict.name"
                  :label="dict.name"
                  :value="dict.name"
                ></el-option>
              </el-select>
              <el-input v-model="form.coinFixed" v-if="isCoin" placeholder="请输入临时币种" style="width: 217px;" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12" >
            <el-form-item label="策略类型" prop="strategyType">
              <el-select v-model="form.strategyType" placeholder="请选择策略类型">
                <el-option
                  v-for="dict in strategyTypeListAll"
                  :key="dict.id"
                  :label="dict.name"
                  :value="dict.id"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12" >
            <el-form-item label="杠杆倍数" prop="leverage">
              <el-input-number v-model="form.leverage" placeholder="请输入杠杆倍数" :min="1"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12" >
            <el-form-item label="多方向" prop="longOpen">
              <el-radio-group v-model="form.longOpen">
                <el-radio
                  v-for="dict in longOpenOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue"
                >{{dict.dictLabel}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12" >
            <el-form-item label="多方向次数" prop="longCount">
              <el-radio-group v-model="form.longCount">
                <el-radio
                  v-for="dict in longCountOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue"
                >{{dict.dictLabel}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12" >
            <el-form-item label="空方向" prop="shortOpen">
              <el-radio-group v-model="form.shortOpen">
                <el-radio
                  v-for="dict in shortOpenOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue"
                >{{dict.dictLabel}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12" >
            <el-form-item label="空方向次数" prop="shortCount">
    <!--          <el-input v-model="form.shortCount" placeholder="请输入空方向次数 once为单次，cycle为循环" />-->
              <el-radio-group v-model="form.shortCount">
                <el-radio
                  v-for="dict in shortCountOptions"
                  :key="dict.dictValue"
                  :label="dict.dictValue"
                >{{dict.dictLabel}}</el-radio>
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="信号保护" prop="common">
          <el-radio-group v-model="form.common">
            <el-radio
              v-for="dict in commonOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-row>
          <el-col :span="12" >
            <el-form-item label="平多比例%" prop="sellLongRate">
              <el-input v-model="form.sellLongRate" placeholder="请输入一键平多比例"/>
            </el-form-item>
          </el-col>
          <el-col :span="12" >
            <el-form-item label="平空比例%" prop="buyShortRate">
              <el-input v-model="form.buyShortRate" placeholder="请输入一键平空比例"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12" >
            <el-form-item label="开多比例%" prop="buyLongRate">
              <el-input v-model="form.buyLongRate" placeholder="请输入一键开多比例"/>
            </el-form-item>
          </el-col>
          <el-col :span="12" >
            <el-form-item label="开空比例%" prop="sellShortRate">
              <el-input v-model="form.sellShortRate" placeholder="请输入一键开空比例"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row v-for="item  in this.form.rowList">
          <el-col :span="4" >
            <el-form-item label="补仓倍数" prop="addAmountMultiple">
              <el-input v-model="item.addAmountMultiple" placeholder="请输入补仓倍数" style="width:100px" />
            </el-form-item>
          </el-col>
          <el-col style="width: 18%">
            <el-form-item label="补仓比例%" prop="addPriceRate">
              <el-input v-model="item.addPriceRate" placeholder="请输入补仓比例" style="width:100px"/>
            </el-form-item>
          </el-col>
          <el-col style="width: 18%">
            <el-form-item label="回调比例%" prop="longStopRate">
              <el-input v-model="item.longStopRate" placeholder="多单止盈比例" style="width:100px"/>
            </el-form-item>
          </el-col>
          <el-col style="width: 18%">
            <el-form-item label="多空止盈%" prop="shortStopRate">
              <el-input v-model="item.shortStopRate" placeholder="空单止盈比例" style="width:100px"/>
            </el-form-item>
          </el-col>
          <el-col style="width: 18%">
            <el-form-item label="止损比例" prop="lossRate">
              <el-input v-model="item.lossRate" placeholder="止损比例" style="width:100px"/>
            </el-form-item>
          </el-col>
          <el-col :span="1.5" style="margin-left: 20px">
            <el-button
              size="mini"
              type="danger"
              plain
              icon="el-icon-delete"
              @click="handleDeleteHtml"
            >删除</el-button>
          </el-col>
        </el-row>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
              type="primary"
              plain
              icon="el-icon-plus"
              size="mini"
              @click="handleAddHtml"
            >新增</el-button>
          </el-col>
        </el-row>

<!--        <el-form-item label="添加时间" prop="crateTime">-->
<!--          <el-date-picker clearable size="small"-->
<!--            v-model="form.crateTime"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="选择添加时间">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="修改时间" prop="stopTime">-->
<!--          <el-date-picker clearable size="small"-->
<!--            v-model="form.stopTime"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="选择修改时间">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="是否删除 Y是 N否" prop="isDelete">-->
<!--          <el-input v-model="form.isDelete" placeholder="请输入是否删除 Y是 N否" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="收益数量" prop="allProfit">-->
<!--          <el-input v-model="form.allProfit" placeholder="请输入收益数量" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="扣除数量" prop="deductProfit">-->
<!--          <el-input v-model="form.deductProfit" placeholder="请输入扣除数量" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="周收益" prop="weekProfit">-->
<!--          <el-input v-model="form.weekProfit" placeholder="请输入周收益" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="周分润比例" prop="weekProfitRate">-->
<!--          <el-input v-model="form.weekProfitRate" placeholder="请输入周分润比例" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="已分润数量" prop="shareProfit">-->
<!--          <el-input v-model="form.shareProfit" placeholder="请输入已分润数量" />-->
<!--        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listMartinStrategyInfo, getMartinStrategyInfo, delMartinStrategyInfo,updateDelete, addMartinStrategyInfo, updateMartinStrategyInfo, exportMartinStrategyInfo } from "@/api/strategy/martinStrategyInfo";
import { listCoinInfoAll} from "@/api/strategy/coinInfo";
import { listPlatformAll} from "@/api/strategy/platform";
import { listStrategyTypeAll } from "@/api/strategy/strategyType";

export default {
  name: "MartinStrategyInfo",
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
      martinStrategyInfoList: [],
      // 币种列表
      coinInfoListAll: [],
      // 交易所列表
      platformListAll: [],
      // 策略类型列表
      strategyTypeListAll: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 策略状态 0未开始 1.开启 2.关闭字典
      strategyStatusOptions: [],
      // 多方向 True为启动，False为关闭字典
      longOpenOptions: [],
      // 多方向次数 "once"为单次，"cycle"为循环字典
      longCountOptions: [],
      // 空方向 True为启动，False为关闭字典
      shortOpenOptions: [],
      // 空方向次数 "once"为单次，"cycle"为循环字典
      shortCountOptions: [],
      // 默认备用 True为启动，False为关闭字典
      commonOptions: [],
      // 是否删除 Y是 N否字典
      isDeleteOptions: [],
      //是否显示
      isCoin : false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        strategyStatus: null,
        userUuid: null,
        platform: null,
        symbol: null,
        apiAccountId: null,
        coin: null,
        leverage: null,
        longOpen: null,
        longCount: null,
        shortOpen: null,
        shortCount: null,
        common: null,
        sellLongRate: null,
        buyShortRate: null,
        buyLongRate: null,
        sellShortRate: null,
        crateTime: null,
        stopTime: null,
        isDelete: null,
        allProfit: null,
        deductProfit: null,
        weekProfit: null,
        weekProfitRate: null,
        shareProfit: null,
        strategyType: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        strategyStatus: [
          { required: true, message: "策略状态 0未开始 1.开启 2.关闭不能为空", trigger: "blur" }
        ],
        userUuid: [
          { required: true, message: "用户uuid不能为空", trigger: "blur" }
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
    this.getDicts("open_status").then(response => {
      this.longOpenOptions = response.data;
    });
    this.getDicts("strategy_count").then(response => {
      this.longCountOptions = response.data;
    });
    this.getDicts("open_status").then(response => {
      this.shortOpenOptions = response.data;
    });
    this.getDicts("strategy_count").then(response => {
      this.shortCountOptions = response.data;
    });
    this.getDicts("common_status").then(response => {
      this.commonOptions = response.data;
    });
    this.getDicts("sys_yes_no").then(response => {
      this.isDeleteOptions = response.data;
    });
  },
  methods: {
    //下来选择型号，将算计显示出来
    handleSelectCoinUpdate(event, item){
       if(event=='临时币种'){
          this.isCoin = true;
       }else{
         this.isCoin = false;
       }
    },
    /** 查询策略信息列表 */
    getList() {
      this.loading = true;
      listMartinStrategyInfo(this.queryParams).then(response => {
        this.martinStrategyInfoList = response.rows;
        this.total = response.total;
        this.loading = false;
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
    getStrategyTypeListAll(){
      listStrategyTypeAll().then(response => {
        this.strategyTypeListAll = response.data;
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
    // 回显数据字典

    // 策略状态 0未开始 1.开启 2.关闭字典翻译
    strategyStatusFormat(row, column) {
      return this.selectDictLabel(this.strategyStatusOptions, row.strategyStatus);
    },
    // 多方向 True为启动，False为关闭字典翻译
    longOpenFormat(row, column) {
      return this.selectDictLabel(this.longOpenOptions, row.longOpen);
    },
    // 多方向次数 "once"为单次，"cycle"为循环字典翻译
    longCountFormat(row, column) {
      return this.selectDictLabel(this.longCountOptions, row.longCount);
    },
    // 空方向 True为启动，False为关闭字典翻译
    shortOpenFormat(row, column) {
      return this.selectDictLabel(this.shortOpenOptions, row.shortOpen);
    },
    // 空方向次数 "once"为单次，"cycle"为循环字典翻译
    shortCountFormat(row, column) {
      return this.selectDictLabel(this.shortCountOptions, row.shortCount);
    },
    // 默认备用 True为启动，False为关闭字典翻译
    commonFormat(row, column) {
      return this.selectDictLabel(this.commonOptions, row.common);
    },
    // 是否删除 Y是 N否字典翻译
    isDeleteFormat(row, column) {
      return this.selectDictLabel(this.isDeleteOptions, row.isDelete);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        strategyId: null,
        strategyStatus: 0,
        userUuid: null,
        platform: null,
        symbol: null,
        apiAccountId: null,
        coin: null,
        leverage: null,
        longOpen: null,
        longCount: null,
        shortOpen: null,
        shortCount: null,
        common: null,
        sellLongRate: null,
        buyShortRate: null,
        buyLongRate: null,
        sellShortRate: null,
        crateTime: null,
        updateTime: null,
        stopTime: null,
        isDelete: null,
        allProfit: null,
        deductProfit: null,
        weekProfit: null,
        weekProfitRate: null,
        shareProfit: null,
        strategyType: 1,
        addAmountMultiple:null,
        addPriceRate:null,
        longStopRate:null,
        shortStopRate:null,
        lossRate:null,
        rowList:[],
        coinFixed:null,
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
      this.reset();
      this.open = true;
      this.title = "添加策略信息";
      this.form.rowList = [];
      this.getCoinInfoListAll();
      this.getPlatformListAll();
      this.getStrategyTypeListAll();
      this.isCoin=false;
      this.form.rowList.push({addAmountMultiple:"", addPriceRate:"",  longStopRate:"", shortStopRate:"",lossRate:""});
    },
    /** 新增设置 */
    handleAddHtml() {
      this.form.rowList.push({addAmountMultiple:"", addPriceRate:"",  longStopRate:"", shortStopRate:"",lossRate:""});
    },
    /** 新增设置 */
    handleDeleteHtml() {
      this.form.rowList.pop();
    },

    /** 修改按钮操作 */
    handleUpdate(row) {
      this.getCoinInfoListAll();
      this.getPlatformListAll();
      this.getStrategyTypeListAll();
      this.reset();
      const strategyId = row.strategyId || this.ids
      getMartinStrategyInfo(strategyId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改策略信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.strategyId != null) {
            updateMartinStrategyInfo(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMartinStrategyInfo(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const strategyIds = row.strategyId || this.ids;
      this.$confirm('是否确认删除策略信息编号为"' + strategyIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          // return delMartinStrategyInfo(strategyIds);
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
          return exportMartinStrategyInfo(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
