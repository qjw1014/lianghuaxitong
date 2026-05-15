<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="子账号" prop="apiAccountId">
        <el-input
          v-model="queryParams.apiAccountId"
          placeholder="请输入子账号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="交易所" prop="exchange">
        <el-input
          v-model="queryParams.exchange"
          placeholder="请输入交易所"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="交易对" prop="symbol">
        <el-input
          v-model="queryParams.symbol"
          placeholder="请输入交易对"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="方向 buy sell" prop="positionSide">
        <el-input
          v-model="queryParams.positionSide"
          placeholder="请输入方向 buy sell"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="数量" prop="quantity">
        <el-input
          v-model="queryParams.quantity"
          placeholder="请输入数量"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开始价格" prop="openPrice">
        <el-input
          v-model="queryParams.openPrice"
          placeholder="请输入开始价格"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="当前价格" prop="currenPrice">
        <el-input
          v-model="queryParams.currenPrice"
          placeholder="请输入当前价格"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="清算价格" prop="liquidationPrice">
        <el-input
          v-model="queryParams.liquidationPrice"
          placeholder="请输入清算价格"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="adl指标" prop="adl">
        <el-input
          v-model="queryParams.adl"
          placeholder="请输入adl指标"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="等级" prop="leverage">
        <el-input
          v-model="queryParams.leverage"
          placeholder="请输入等级"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="期货价值增损" prop="positionValue">
        <el-input
          v-model="queryParams.positionValue"
          placeholder="请输入期货价值增损"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="添加时间" prop="cretaeTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.cretaeTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择添加时间">
        </el-date-picker>
      </el-form-item>
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
          v-hasPermi="['strategy:strageyPositionInfo:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['strategy:strageyPositionInfo:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['strategy:strageyPositionInfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['strategy:strageyPositionInfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="strageyPositionInfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="流水编号" align="center" prop="id" />
      <el-table-column label="子账号" align="center" prop="apiAccountId" />
      <el-table-column label="交易所" align="center" prop="exchange" />
      <el-table-column label="交易对" align="center" prop="symbol" />
      <el-table-column label="方向 buy sell" align="center" prop="positionSide" />
      <el-table-column label="数量" align="center" prop="quantity" />
      <el-table-column label="开始价格" align="center" prop="openPrice" />
      <el-table-column label="当前价格" align="center" prop="currenPrice" />
      <el-table-column label="清算价格" align="center" prop="liquidationPrice" />
      <el-table-column label="adl指标" align="center" prop="adl" />
      <el-table-column label="等级" align="center" prop="leverage" />
      <el-table-column label="期货价值增损" align="center" prop="positionValue" />
      <el-table-column label="添加时间" align="center" prop="cretaeTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.cretaeTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['strategy:strageyPositionInfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['strategy:strageyPositionInfo:remove']"
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

    <!-- 添加或修改策略仓位对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="子账号" prop="apiAccountId">
          <el-input v-model="form.apiAccountId" placeholder="请输入子账号" />
        </el-form-item>
        <el-form-item label="交易所" prop="exchange">
          <el-input v-model="form.exchange" placeholder="请输入交易所" />
        </el-form-item>
        <el-form-item label="交易对" prop="symbol">
          <el-input v-model="form.symbol" placeholder="请输入交易对" />
        </el-form-item>
        <el-form-item label="方向 buy sell" prop="positionSide">
          <el-input v-model="form.positionSide" placeholder="请输入方向 buy sell" />
        </el-form-item>
        <el-form-item label="数量" prop="quantity">
          <el-input v-model="form.quantity" placeholder="请输入数量" />
        </el-form-item>
        <el-form-item label="开始价格" prop="openPrice">
          <el-input v-model="form.openPrice" placeholder="请输入开始价格" />
        </el-form-item>
        <el-form-item label="当前价格" prop="currenPrice">
          <el-input v-model="form.currenPrice" placeholder="请输入当前价格" />
        </el-form-item>
        <el-form-item label="清算价格" prop="liquidationPrice">
          <el-input v-model="form.liquidationPrice" placeholder="请输入清算价格" />
        </el-form-item>
        <el-form-item label="adl指标" prop="adl">
          <el-input v-model="form.adl" placeholder="请输入adl指标" />
        </el-form-item>
        <el-form-item label="等级" prop="leverage">
          <el-input v-model="form.leverage" placeholder="请输入等级" />
        </el-form-item>
        <el-form-item label="期货价值增损" prop="positionValue">
          <el-input v-model="form.positionValue" placeholder="请输入期货价值增损" />
        </el-form-item>
        <el-form-item label="添加时间" prop="cretaeTime">
          <el-date-picker clearable size="small"
            v-model="form.cretaeTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择添加时间">
          </el-date-picker>
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
import { listStrageyPositionInfo, getStrageyPositionInfo, delStrageyPositionInfo, addStrageyPositionInfo, updateStrageyPositionInfo, exportStrageyPositionInfo } from "@/api/strategy/strageyPositionInfo";

export default {
  name: "StrageyPositionInfo",
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
      // 策略仓位表格数据
      strageyPositionInfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
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
    this.getList();
  },
  methods: {
    /** 查询策略仓位列表 */
    getList() {
      this.loading = true;
      listStrageyPositionInfo(this.queryParams).then(response => {
        this.strageyPositionInfoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加策略仓位";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getStrageyPositionInfo(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改策略仓位";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateStrageyPositionInfo(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStrageyPositionInfo(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除策略仓位编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delStrageyPositionInfo(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有策略仓位数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportStrageyPositionInfo(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
