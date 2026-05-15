<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="1.开启 2.停止" prop="strategyStatus">
        <el-select v-model="queryParams.strategyStatus" placeholder="请选择1.开启 2.停止" clearable size="small">
          <el-option
            v-for="dict in strategyStatusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="策略类型" prop="strategyType">
        <el-select v-model="queryParams.strategyType" placeholder="请选择策略类型" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="API对应的id" prop="accountId">
        <el-input
          v-model="queryParams.accountId"
          placeholder="请输入API对应的id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="首单金额比例" prop="baseRate">
        <el-input
          v-model="queryParams.baseRate"
          placeholder="请输入首单金额比例"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="添加时间" prop="crateTime">
        <el-date-picker clearable size="small"
          v-model="queryParams.crateTime"
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
          v-hasPermi="['strategy:martinStrategyApi:add']"
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
          v-hasPermi="['strategy:martinStrategyApi:edit']"
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
          v-hasPermi="['strategy:martinStrategyApi:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['strategy:martinStrategyApi:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="martinStrategyApiList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="流水编号" align="center" prop="id" />
      <el-table-column label="1.开启 2.停止" align="center" prop="strategyStatus" :formatter="strategyStatusFormat" />
      <el-table-column label="策略类型" align="center" prop="strategyType" />
      <el-table-column label="API对应的id" align="center" prop="accountId" />
      <el-table-column label="首单金额比例" align="center" prop="baseRate" />
      <el-table-column label="添加时间" align="center" prop="crateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.crateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['strategy:martinStrategyApi:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['strategy:martinStrategyApi:remove']"
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

    <!-- 添加或修改api对应策略信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="1.开启 2.停止">
          <el-radio-group v-model="form.strategyStatus">
            <el-radio
              v-for="dict in strategyStatusOptions"
              :key="dict.dictValue"
              :label="parseInt(dict.dictValue)"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="策略类型" prop="strategyType">
          <el-select v-model="form.strategyType" placeholder="请选择策略类型">
            <el-option label="请选择字典生成" value="" />
          </el-select>
        </el-form-item>
        <el-form-item label="API对应的id" prop="accountId">
          <el-input v-model="form.accountId" placeholder="请输入API对应的id" />
        </el-form-item>
        <el-form-item label="首单金额比例" prop="baseRate">
          <el-input v-model="form.baseRate" placeholder="请输入首单金额比例" />
        </el-form-item>
        <el-form-item label="策略编号" prop="strategyId">
          <el-input v-model="form.strategyId" placeholder="请输入策略编号" />
        </el-form-item>
        <el-form-item label="添加时间" prop="crateTime">
          <el-date-picker clearable size="small"
            v-model="form.crateTime"
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
import { listMartinStrategyApi, getMartinStrategyApi, delMartinStrategyApi, addMartinStrategyApi, updateMartinStrategyApi, exportMartinStrategyApi } from "@/api/strategy/martinStrategyApi";

export default {
  name: "MartinStrategyApi",
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
      // api对应策略信息表格数据
      martinStrategyApiList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 1.开启 2.停止字典
      strategyStatusOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        strategyStatus: null,
        strategyType: null,
        accountId: null,
        baseRate: null,
        crateTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("strategy_status").then(response => {
      this.strategyStatusOptions = response.data;
    });
  },
  methods: {
    /** 查询api对应策略信息列表 */
    getList() {
      this.loading = true;
      listMartinStrategyApi(this.queryParams).then(response => {
        this.martinStrategyApiList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 1.开启 2.停止字典翻译
    strategyStatusFormat(row, column) {
      return this.selectDictLabel(this.strategyStatusOptions, row.strategyStatus);
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
        strategyStatus: 0,
        strategyType: null,
        accountId: null,
        strategyId: null,
        baseRate: null,
        crateTime: null
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
      this.title = "添加api对应策略信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getMartinStrategyApi(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改api对应策略信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          // 空字符串转null，避免后端JSON解析报错
          if (this.form.accountId === '' || this.form.accountId === null || this.form.accountId === undefined) {
            this.form.accountId = null;
          } else if (typeof this.form.accountId === 'string') {
            this.form.accountId = parseInt(this.form.accountId);
          }
          if (this.form.strategyId === '' || this.form.strategyId === null || this.form.strategyId === undefined) {
            this.form.strategyId = null;
          } else if (typeof this.form.strategyId === 'string') {
            this.form.strategyId = parseInt(this.form.strategyId);
          }
          if (this.form.baseRate === '' || this.form.baseRate === null) this.form.baseRate = null;
          if (this.form.strategyType === '' || this.form.strategyType === null) this.form.strategyType = null;
          if (this.form.crateTime === '' || this.form.crateTime === null) this.form.crateTime = null;
          if (this.form.id != null) {
            updateMartinStrategyApi(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMartinStrategyApi(this.form).then(response => {
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
      this.$confirm('是否确认删除api对应策略信息编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delMartinStrategyApi(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有api对应策略信息数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportMartinStrategyApi(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
