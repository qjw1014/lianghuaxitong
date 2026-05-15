<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="交易对" prop="symbol">
        <el-input
          v-model="queryParams.symbol"
          placeholder="请输入交易对"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="订单编号" prop="orderId">-->
<!--        <el-input-->
<!--          v-model="queryParams.orderId"-->
<!--          placeholder="请输入订单编号"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="买卖方向 BUY SELL" prop="side">-->
<!--        <el-input-->
<!--          v-model="queryParams.side"-->
<!--          placeholder="请输入买卖方向 BUY SELL"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="成交价" prop="price">-->
<!--        <el-input-->
<!--          v-model="queryParams.price"-->
<!--          placeholder="请输入成交价"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="成交量" prop="qty">-->
<!--        <el-input-->
<!--          v-model="queryParams.qty"-->
<!--          placeholder="请输入成交量"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="实现盈亏" prop="realizedPnl">-->
<!--        <el-input-->
<!--          v-model="queryParams.realizedPnl"-->
<!--          placeholder="请输入实现盈亏"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="保证金币种" prop="marginAsset">-->
<!--        <el-input-->
<!--          v-model="queryParams.marginAsset"-->
<!--          placeholder="请输入保证金币种"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="成交额" prop="quoteQty">-->
<!--        <el-input-->
<!--          v-model="queryParams.quoteQty"-->
<!--          placeholder="请输入成交额"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="手续费" prop="commission">-->
<!--        <el-input-->
<!--          v-model="queryParams.commission"-->
<!--          placeholder="请输入手续费"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="手续费计价币" prop="commissionAsset">-->
<!--        <el-input-->
<!--          v-model="queryParams.commissionAsset"-->
<!--          placeholder="请输入手续费计价币"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="交易时间" prop="orderTime">-->
<!--        <el-date-picker clearable size="small"-->
<!--          v-model="queryParams.orderTime"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="选择交易时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="持仓方向" prop="positionSide">-->
<!--        <el-input-->
<!--          v-model="queryParams.positionSide"-->
<!--          placeholder="请输入持仓方向"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="是否是买方" prop="buyer">-->
<!--        <el-input-->
<!--          v-model="queryParams.buyer"-->
<!--          placeholder="请输入是否是买方"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="是否是挂单方" prop="maker">-->
<!--        <el-input-->
<!--          v-model="queryParams.maker"-->
<!--          placeholder="请输入是否是挂单方"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="子账号" prop="accountId">
        <el-input
          v-model="queryParams.accountId"
          placeholder="账号编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否已结算" prop="isSettled">
        <el-input
          v-model="queryParams.isSettled"
          placeholder="请输入是否已结算"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['strategy:martinStrategyOrder:add']"-->
<!--        >新增</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['strategy:martinStrategyOrder:edit']"-->
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
<!--          v-hasPermi="['strategy:martinStrategyOrder:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['strategy:martinStrategyOrder:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="martinStrategyOrderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="流水编号" align="center" prop="serialId" />
      <el-table-column label="交易对" align="center" prop="symbol" />
      <el-table-column label="订单编号" align="center" prop="orderId" />
      <el-table-column label="买卖方向" align="center" prop="side" :formatter="sideFormat" />
      <el-table-column label="成交价" align="center" prop="price" />
      <el-table-column label="成交量" align="center" prop="qty" />
      <el-table-column label="实现盈亏" align="center" prop="realizedPnl" />
      <el-table-column label="保证金币种" align="center" prop="marginAsset" />
      <el-table-column label="成交额" align="center" prop="quoteQty" />
      <el-table-column label="手续费" align="center" prop="commission" />
      <el-table-column label="手续费计价币" align="center" prop="commissionAsset" />
      <el-table-column label="交易时间" align="center" prop="orderTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.orderTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="持仓方向" align="center" prop="positionSide" />
      <el-table-column label="是否是买方" align="center" prop="buyer" :formatter="buyerFormat" />
      <el-table-column label="是否是挂单方" align="center" prop="maker" :formatter="makerFormat" />
      <el-table-column label="子账号编号" align="center" prop="accountId" />
      <el-table-column label="是否已结算" align="center" prop="isSettled" />
<!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">-->
<!--        <template slot-scope="scope">-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleUpdate(scope.row)"-->
<!--            v-hasPermi="['strategy:martinStrategyOrder:edit']"-->
<!--          >修改</el-button>-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['strategy:martinStrategyOrder:remove']"-->
<!--          >删除</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改策略订单信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="交易对" prop="symbol">
          <el-input v-model="form.symbol" placeholder="请输入交易对" />
        </el-form-item>
        <el-form-item label="平台交易id" prop="id">
          <el-input v-model="form.id" placeholder="请输入平台交易id" />
        </el-form-item>
        <el-form-item label="订单编号" prop="orderId">
          <el-input v-model="form.orderId" placeholder="请输入订单编号" />
        </el-form-item>
        <el-form-item label="买卖方向 BUY SELL" prop="side">
          <el-input v-model="form.side" placeholder="请输入买卖方向 BUY SELL" />
        </el-form-item>
        <el-form-item label="成交价" prop="price">
          <el-input v-model="form.price" placeholder="请输入成交价" />
        </el-form-item>
        <el-form-item label="成交量" prop="qty">
          <el-input v-model="form.qty" placeholder="请输入成交量" />
        </el-form-item>
        <el-form-item label="实现盈亏" prop="realizedPnl">
          <el-input v-model="form.realizedPnl" placeholder="请输入实现盈亏" />
        </el-form-item>
        <el-form-item label="保证金币种" prop="marginAsset">
          <el-input v-model="form.marginAsset" placeholder="请输入保证金币种" />
        </el-form-item>
        <el-form-item label="成交额" prop="quoteQty">
          <el-input v-model="form.quoteQty" placeholder="请输入成交额" />
        </el-form-item>
        <el-form-item label="手续费" prop="commission">
          <el-input v-model="form.commission" placeholder="请输入手续费" />
        </el-form-item>
        <el-form-item label="手续费计价币" prop="commissionAsset">
          <el-input v-model="form.commissionAsset" placeholder="请输入手续费计价币" />
        </el-form-item>
        <el-form-item label="交易时间" prop="orderTime">
          <el-date-picker clearable size="small"
            v-model="form.orderTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择交易时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="持仓方向" prop="positionSide">
          <el-input v-model="form.positionSide" placeholder="请输入持仓方向" />
        </el-form-item>
        <el-form-item label="是否是买方" prop="buyer">
          <el-input v-model="form.buyer" placeholder="请输入是否是买方" />
        </el-form-item>
        <el-form-item label="是否是挂单方" prop="maker">
          <el-input v-model="form.maker" placeholder="请输入是否是挂单方" />
        </el-form-item>
        <el-form-item label="子账号编号" prop="accountId">
          <el-input v-model="form.accountId" placeholder="请输入子账号编号" />
        </el-form-item>
        <el-form-item label="是否已结算" prop="isSettled">
          <el-input v-model="form.isSettled" placeholder="请输入是否已结算" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listMartinStrategyOrder, getMartinStrategyOrder, delMartinStrategyOrder, addMartinStrategyOrder, updateMartinStrategyOrder, exportMartinStrategyOrder } from "@/api/strategy/martinStrategyOrder";

export default {
  name: "MartinStrategyOrder",
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
      // 策略订单信息表格数据
      martinStrategyOrderList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 买卖方向 BUY SELL字典
      sideOptions: [],
      // 是否是买方字典
      buyerOptions: [],
      // 是否是挂单方字典
      makerOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        symbol: null,
        orderId: null,
        side: null,
        price: null,
        qty: null,
        realizedPnl: null,
        marginAsset: null,
        quoteQty: null,
        commission: null,
        commissionAsset: null,
        orderTime: null,
        positionSide: null,
        buyer: null,
        maker: null,
        accountId: null,
        isSettled: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        symbol: [
          { required: true, message: "交易对不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("order_side").then(response => {
      this.sideOptions = response.data;
    });
    this.getDicts("boolean_yes_no").then(response => {
      this.buyerOptions = response.data;
    });
    this.getDicts("boolean_yes_no").then(response => {
      this.makerOptions = response.data;
    });
  },
  methods: {
    /** 查询策略订单信息列表 */
    getList() {
      this.loading = true;
      listMartinStrategyOrder(this.queryParams).then(response => {
        this.martinStrategyOrderList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 买卖方向 BUY SELL字典翻译
    sideFormat(row, column) {
      return this.selectDictLabel(this.sideOptions, row.side);
    },
    // 是否是买方字典翻译
    buyerFormat(row, column) {
      return this.selectDictLabel(this.buyerOptions, row.buyer);
    },
    // 是否是挂单方字典翻译
    makerFormat(row, column) {
      return this.selectDictLabel(this.makerOptions, row.maker);
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        serialId: null,
        symbol: null,
        id: null,
        orderId: null,
        side: null,
        price: null,
        qty: null,
        realizedPnl: null,
        marginAsset: null,
        quoteQty: null,
        commission: null,
        commissionAsset: null,
        orderTime: null,
        positionSide: null,
        buyer: null,
        maker: null,
        accountId: null,
        isSettled: null
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
      this.ids = selection.map(item => item.serialId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加策略订单信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const serialId = row.serialId || this.ids
      getMartinStrategyOrder(serialId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改策略订单信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.serialId != null) {
            updateMartinStrategyOrder(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMartinStrategyOrder(this.form).then(response => {
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
      const serialIds = row.serialId || this.ids;
      this.$confirm('是否确认删除策略订单信息编号为"' + serialIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delMartinStrategyOrder(serialIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有策略订单信息数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportMartinStrategyOrder(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
