<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="策略编号" prop="strategyId">
        <el-input
          v-model="queryParams.strategyId"
          placeholder="请输入策略编号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="补仓比例" prop="addPriceRate">-->
<!--        <el-input-->
<!--          v-model="queryParams.addPriceRate"-->
<!--          placeholder="请输入补仓比例"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="多单止盈比例" prop="longStopRate">-->
<!--        <el-input-->
<!--          v-model="queryParams.longStopRate"-->
<!--          placeholder="请输入多单止盈比例"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="空单止盈比例" prop="shortStopRate">-->
<!--        <el-input-->
<!--          v-model="queryParams.shortStopRate"-->
<!--          placeholder="请输入空单止盈比例"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="止损比例" prop="lossRate">-->
<!--        <el-input-->
<!--          v-model="queryParams.lossRate"-->
<!--          placeholder="请输入止损比例"-->
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
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['strategy:martinStrategySettings:add']"-->
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
<!--          v-hasPermi="['strategy:martinStrategySettings:edit']"-->
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
<!--          v-hasPermi="['strategy:martinStrategySettings:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['strategy:martinStrategySettings:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="martinStrategySettingsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="比例设置" align="center" prop="id" />
      <el-table-column label="策略编号" align="center" prop="strategyId" />
      <el-table-column label="补仓倍数" align="center" prop="addAmountMultiple" />
      <el-table-column label="补仓比例" align="center" prop="addPriceRate" />
      <el-table-column label="多单止盈比例" align="center" prop="longStopRate" />
      <el-table-column label="空单止盈比例" align="center" prop="shortStopRate" />
      <el-table-column label="止损比例" align="center" prop="lossRate" />
<!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">-->
<!--        <template slot-scope="scope">-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleUpdate(scope.row)"-->
<!--            v-hasPermi="['strategy:martinStrategySettings:edit']"-->
<!--          >修改</el-button>-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['strategy:martinStrategySettings:remove']"-->
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

    <!-- 添加或修改策略比例设置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="策略编号" prop="strategyId">
          <el-input v-model="form.strategyId" placeholder="请输入策略编号" />
        </el-form-item>
        <el-form-item label="补仓比例" prop="addPriceRate">
          <el-input v-model="form.addPriceRate" placeholder="请输入补仓比例" />
        </el-form-item>
        <el-form-item label="多单止盈比例" prop="longStopRate">
          <el-input v-model="form.longStopRate" placeholder="请输入多单止盈比例" />
        </el-form-item>
        <el-form-item label="空单止盈比例" prop="shortStopRate">
          <el-input v-model="form.shortStopRate" placeholder="请输入空单止盈比例" />
        </el-form-item>
        <el-form-item label="止损比例" prop="lossRate">
          <el-input v-model="form.lossRate" placeholder="请输入止损比例" />
        </el-form-item>
<!--        <el-form-item label="添加时间" prop="createTime">-->
<!--          <el-date-picker clearable size="small"-->
<!--            v-model="form.createTime"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="选择添加时间">-->
<!--          </el-date-picker>-->
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
import { listMartinStrategySettings, getMartinStrategySettings, delMartinStrategySettings, addMartinStrategySettings, updateMartinStrategySettings, exportMartinStrategySettings } from "@/api/strategy/martinStrategySettings";

export default {
  name: "MartinStrategySettings",
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
      // 策略比例设置表格数据
      martinStrategySettingsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        strategyId: null,
        addPriceRate: null,
        longStopRate: null,
        shortStopRate: null,
        lossRate: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        strategyId: [
          { required: true, message: "策略编号不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询策略比例设置列表 */
    getList() {
      this.loading = true;
      listMartinStrategySettings(this.queryParams).then(response => {
        this.martinStrategySettingsList = response.rows;
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
        strategyId: null,
        addAmountMultiple: null,
        addPriceRate: null,
        longStopRate: null,
        shortStopRate: null,
        lossRate: null,
        createTime: null
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
      this.title = "添加策略比例设置";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getMartinStrategySettings(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改策略比例设置";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateMartinStrategySettings(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMartinStrategySettings(this.form).then(response => {
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
      this.$confirm('是否确认删除策略比例设置编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delMartinStrategySettings(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有策略比例设置数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportMartinStrategySettings(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
