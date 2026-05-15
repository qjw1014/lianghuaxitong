<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="本金" prop="initUsdt">
        <el-input
          v-model="queryParams.initUsdt"
          placeholder="请输入本金"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="充提" prop="charge">
        <el-input
          v-model="queryParams.charge"
          placeholder="请输入充提"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="最新净值" prop="currentUsdt">
        <el-input
          v-model="queryParams.currentUsdt"
          placeholder="请输入最新净值"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="7天收益" prop="days7Profit">
        <el-input
          v-model="queryParams.days7Profit"
          placeholder="请输入7天收益"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="7日年化收益率" prop="days7ProfitFloat">
        <el-input
          v-model="queryParams.days7ProfitFloat"
          placeholder="请输入7日年化收益率"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="30天收益" prop="days30Profit">
        <el-input
          v-model="queryParams.days30Profit"
          placeholder="请输入30天收益"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="30日年化收益率" prop="days30ProfitFloat">
        <el-input
          v-model="queryParams.days30ProfitFloat"
          placeholder="请输入30日年化收益率"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="累计收益" prop="totalProfit">
        <el-input
          v-model="queryParams.totalProfit"
          placeholder="请输入累计收益"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="累计年化收益率" prop="totalProfitFloat">
        <el-input
          v-model="queryParams.totalProfitFloat"
          placeholder="请输入累计年化收益率"
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
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['strategy:statisticsAccount:add']"
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
          v-hasPermi="['strategy:statisticsAccount:edit']"
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
          v-hasPermi="['strategy:statisticsAccount:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['strategy:statisticsAccount:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="statisticsAccountList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="子账号" align="center" prop="apiAccountId" />
      <el-table-column label="本金" align="center" prop="initUsdt" />
      <el-table-column label="充提" align="center" prop="charge" />
      <el-table-column label="最新净值" align="center" prop="currentUsdt" />
      <el-table-column label="7天收益" align="center" prop="days7Profit" />
      <el-table-column label="7日年化收益率" align="center" prop="days7ProfitFloat" />
      <el-table-column label="30天收益" align="center" prop="days30Profit" />
      <el-table-column label="30日年化收益率" align="center" prop="days30ProfitFloat" />
      <el-table-column label="累计收益" align="center" prop="totalProfit" />
      <el-table-column label="累计年化收益率" align="center" prop="totalProfitFloat" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['strategy:statisticsAccount:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['strategy:statisticsAccount:remove']"
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

    <!-- 添加或修改统计账户信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="本金" prop="initUsdt">
          <el-input v-model="form.initUsdt" placeholder="请输入本金" />
        </el-form-item>
        <el-form-item label="充提" prop="charge">
          <el-input v-model="form.charge" placeholder="请输入充提" />
        </el-form-item>
        <el-form-item label="最新净值" prop="currentUsdt">
          <el-input v-model="form.currentUsdt" placeholder="请输入最新净值" />
        </el-form-item>
        <el-form-item label="7天收益" prop="days7Profit">
          <el-input v-model="form.days7Profit" placeholder="请输入7天收益" />
        </el-form-item>
        <el-form-item label="7日年化收益率" prop="days7ProfitFloat">
          <el-input v-model="form.days7ProfitFloat" placeholder="请输入7日年化收益率" />
        </el-form-item>
        <el-form-item label="30天收益" prop="days30Profit">
          <el-input v-model="form.days30Profit" placeholder="请输入30天收益" />
        </el-form-item>
        <el-form-item label="30日年化收益率" prop="days30ProfitFloat">
          <el-input v-model="form.days30ProfitFloat" placeholder="请输入30日年化收益率" />
        </el-form-item>
        <el-form-item label="累计收益" prop="totalProfit">
          <el-input v-model="form.totalProfit" placeholder="请输入累计收益" />
        </el-form-item>
        <el-form-item label="累计年化收益率" prop="totalProfitFloat">
          <el-input v-model="form.totalProfitFloat" placeholder="请输入累计年化收益率" />
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
import { listStatisticsAccount, getStatisticsAccount, delStatisticsAccount, addStatisticsAccount, updateStatisticsAccount, exportStatisticsAccount } from "@/api/strategy/statisticsAccount";

export default {
  name: "StatisticsAccount",
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
      // 统计账户信息表格数据
      statisticsAccountList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        initUsdt: null,
        charge: null,
        currentUsdt: null,
        days7Profit: null,
        days7ProfitFloat: null,
        days30Profit: null,
        days30ProfitFloat: null,
        totalProfit: null,
        totalProfitFloat: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    console.log(this.$route.path)
    this.getList();
  },
  methods: {
    /** 查询统计账户信息列表 */
    getList() {
      this.loading = true;
      listStatisticsAccount(this.queryParams).then(response => {
        this.statisticsAccountList = response.rows;
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
        apiAccountId: null,
        initUsdt: null,
        charge: null,
        currentUsdt: null,
        days7Profit: null,
        days7ProfitFloat: null,
        days30Profit: null,
        days30ProfitFloat: null,
        totalProfit: null,
        totalProfitFloat: null,
        updateTime: null
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
      this.ids = selection.map(item => item.apiAccountId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加统计账户信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const apiAccountId = row.apiAccountId || this.ids
      getStatisticsAccount(apiAccountId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改统计账户信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.apiAccountId != null) {
            updateStatisticsAccount(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStatisticsAccount(this.form).then(response => {
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
      const apiAccountIds = row.apiAccountId || this.ids;
      this.$confirm('是否确认删除统计账户信息编号为"' + apiAccountIds + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delStatisticsAccount(apiAccountIds);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有统计账户信息数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportStatisticsAccount(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
