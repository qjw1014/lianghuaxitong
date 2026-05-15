<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="100px">
      <el-form-item label="功能类型：" prop="feeType">
        <el-select v-model="queryParams.feeType" placeholder="请选择类型：" clearable size="small">
          <el-option
            v-for="dict in feeTypeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="费率类型：" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择类型：" clearable size="small">
          <el-option
            v-for="dict in typeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
<!--      <el-form-item label="手续费" prop="fee">-->
<!--        <el-input-->
<!--          v-model="queryParams.fee"-->
<!--          placeholder="请输入手续费"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="最小数量" prop="minNums">-->
<!--        <el-input-->
<!--          v-model="queryParams.minNums"-->
<!--          placeholder="请输入最小数量"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="最大数量" prop="maxNums">-->
<!--        <el-input-->
<!--          v-model="queryParams.maxNums"-->
<!--          placeholder="请输入最大数量"-->
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
          v-hasPermi="['sysDict:AuFee:add']"
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
          v-hasPermi="['sysDict:AuFee:edit']"
        >修改</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="danger"-->
<!--          plain-->
<!--          icon="el-icon-delete"-->
<!--          size="mini"-->
<!--          :disabled="multiple"-->
<!--          @click="handleDelete"-->
<!--          v-hasPermi="['sysDict:AuFee:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['sysDict:AuFee:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="AuFeeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="功能类型" align="center" prop="feeType" :formatter="feeTypeFormat" />
      <el-table-column label="费率类型" align="center" prop="type" :formatter="typeFormat" />
      <el-table-column label="手续费" align="center" prop="fee" />
      <el-table-column label="最小数量" align="center" prop="minNums" />
      <el-table-column label="最大数量" align="center" prop="maxNums" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['sysDict:AuFee:edit']"
          >修改</el-button>
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['sysDict:AuFee:remove']"-->
<!--          >删除</el-button>-->
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

    <!-- 添加或修改手续费配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="功能类型：" prop="feeType">
          <el-select v-model="form.feeType" placeholder="请选择类型：release释放，nftSale 出售nft">
            <el-option
              v-for="dict in feeTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="费率类型：" prop="type">
          <el-select v-model="form.type" placeholder="请选择类型：pen：按笔，percentage：百分比">
            <el-option
              v-for="dict in typeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="手续费" prop="fee">
          <el-input v-model="form.fee" placeholder="请输入手续费" />
        </el-form-item>
        <el-form-item label="最小数量" prop="minNums">
          <el-input v-model="form.minNums" placeholder="请输入最小数量" />
        </el-form-item>
        <el-form-item label="最大数量" prop="maxNums">
          <el-input v-model="form.maxNums" placeholder="请输入最大数量" />
        </el-form-item>
<!--        <el-form-item label="创建时间" prop="createTime">-->
<!--          <el-date-picker clearable size="small"-->
<!--            v-model="form.createTime"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="选择创建时间">-->
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
import { listAuFee, getAuFee, delAuFee, addAuFee, updateAuFee, exportAuFee } from "@/api/sysDict/AuFee";

export default {
  name: "AuFee",
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
      // 手续费配置表格数据
      AuFeeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 类型：release释放，nftSale 出售nft字典
      feeTypeOptions: [],
      // 类型：pen：按笔，percentage：百分比字典
      typeOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        feeType: null,
        type: null,
        fee: null,
        minNums: null,
        maxNums: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        feeType: [
          { required: true, message: "类型：release释放，nftSale 出售nft不能为空", trigger: "change" }
        ],
        type: [
          { required: true, message: "类型：pen：按笔，percentage：百分比不能为空", trigger: "change" }
        ],
        fee: [
          { required: true, message: "手续费不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("fee_type").then(response => {
      this.feeTypeOptions = response.data;
    });
    this.getDicts("au_fee_type").then(response => {
      this.typeOptions = response.data;
    });
  },
  methods: {
    /** 查询手续费配置列表 */
    getList() {
      this.loading = true;
      listAuFee(this.queryParams).then(response => {
        this.AuFeeList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 类型：release释放，nftSale 出售nft字典翻译
    feeTypeFormat(row, column) {
      return this.selectDictLabel(this.feeTypeOptions, row.feeType);
    },
    // 类型：pen：按笔，percentage：百分比字典翻译
    typeFormat(row, column) {
      return this.selectDictLabel(this.typeOptions, row.type);
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
        feeType: null,
        type: null,
        fee: null,
        minNums: null,
        maxNums: null,
        createTime: null,
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加手续费配置";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getAuFee(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改手续费配置";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateAuFee(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAuFee(this.form).then(response => {
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
      this.$confirm('是否确认删除手续费配置编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delAuFee(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有手续费配置数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportAuFee(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
