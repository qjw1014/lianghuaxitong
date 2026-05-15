<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="90px">
      <el-form-item label="是否是合约" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择是否是合约 Y是 N否" clearable size="small">
          <el-option
            v-for="dict in typeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="钱包地址" prop="address">
        <el-input
          v-model="queryParams.address"
          placeholder="请输入钱包地址"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="排序" prop="sort">
        <el-input
          v-model="queryParams.sort"
          placeholder="请输入排序"
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
          v-hasPermi="['sysDict:WalletConfig:add']"
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
          v-hasPermi="['sysDict:WalletConfig:edit']"
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
<!--          v-hasPermi="['sysDict:WalletConfig:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['sysDict:WalletConfig:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="WalletConfigList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="流水编号" align="center" prop="id" />
      <el-table-column label="业务类型" align="center" prop="code" :formatter="walletCodeFormat"/>
      <el-table-column label="是否是合约" align="center" prop="type" :formatter="typeFormat" />
      <el-table-column label="钱包地址" align="center" prop="address" />
      <el-table-column label="合约地址" align="center" prop="contractAddress" />
      <el-table-column label="秘钥" align="center" prop="privateKey" />
<!--      <el-table-column label="加密后秘钥" align="center" prop="encryKey" />-->
      <el-table-column label="描述" align="center" prop="tips" />
      <el-table-column label="排序" align="center" prop="sort" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['sysDict:WalletConfig:edit']"
          >修改</el-button>
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['sysDict:WalletConfig:remove']"-->
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

    <!-- 添加或修改钱包配置信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="业务类型" prop="code">
<!--          <el-input v-model="form.code" placeholder="请选择业务类型" />-->
          <el-select v-model="form.code" placeholder="请选择业务类型">
            <el-option
              v-for="dict in walletCodeConfigOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否是合约" prop="type">
          <el-select v-model="form.type" placeholder="请选择是否是合约">
            <el-option
              v-for="dict in typeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="钱包地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入钱包地址" />
        </el-form-item>
        <el-form-item label="合约地址" prop="contractAddress" v-if="form.type=='Y'">
          <el-input v-model="form.contractAddress" placeholder="请输入合约地址" />
        </el-form-item>
        <el-form-item label="秘钥" prop="privateKey">
          <el-input v-model="form.privateKey" type="textarea" placeholder="请输入内容" />
        </el-form-item>
<!--        <el-form-item label="加密后秘钥" prop="encryKey">-->
<!--          <el-input v-model="form.encryKey" type="textarea" placeholder="请输入内容" />-->
<!--        </el-form-item>-->
        <el-form-item label="描述" prop="tips">
          <el-input v-model="form.tips" placeholder="请输入描述" />
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-input v-model="form.sort" placeholder="请输入排序" />
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
import { listWalletConfig, getWalletConfig, delWalletConfig, addWalletConfig, updateWalletConfig, exportWalletConfig } from "@/api/sysDict/WalletConfig";

export default {
  name: "WalletConfig",
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
      // 钱包配置信息表格数据
      WalletConfigList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否是合约 Y是 N否字典
      typeOptions: [],
      // 业务类型 字典钱包
      walletCodeConfigOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        code: null,
        type: null,
        address: null,
        contractAddress: null,
        privateKey: null,
        encryKey: null,
        tips: null,
        sort: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        code: [
          { required: true, message: "唯一标识不能为空", trigger: "blur" }
        ],
        type: [
          { required: true, message: "是否是合约 Y是 N否不能为空", trigger: "change" }
        ],
        address: [
          { required: true, message: "钱包地址不能为空", trigger: "blur" }
        ],
        contractAddress: [
          { required: true, message: "合约地址不能为空", trigger: "blur" }
        ],
        privateKey: [
          { required: true, message: "秘钥不能为空", trigger: "blur" }
        ],
        encryKey: [
          { required: true, message: "加密后秘钥不能为空", trigger: "blur" }
        ],
        updateTime: [
          { required: true, message: "添加修改时间不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("sys_yes_no").then(response => {
      this.typeOptions = response.data;
    });
    this.getDicts("wallet_code_config").then(response => {
      this.walletCodeConfigOptions = response.data;
    });
  },
  methods: {
    /** 查询钱包配置信息列表 */
    getList() {
      this.loading = true;
      listWalletConfig(this.queryParams).then(response => {
        this.WalletConfigList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 是否是合约 Y是 N否字典翻译
    typeFormat(row, column) {
      return this.selectDictLabel(this.typeOptions, row.type);
    },
    // 业务类型字典翻译
    walletCodeFormat(row, column) {
      return this.selectDictLabel(this.walletCodeConfigOptions, row.code);
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
        code: null,
        type: null,
        address: null,
        contractAddress: null,
        privateKey: null,
        encryKey: null,
        tips: null,
        sort: null,
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
      this.title = "添加钱包配置信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getWalletConfig(id).then(response => {
        this.form = response.data;
        this.form.privateKey = null;
        this.open = true;
        this.title = "修改钱包配置信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateWalletConfig(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addWalletConfig(this.form).then(response => {
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
      this.$confirm('是否确认删除钱包配置信息编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delWalletConfig(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有钱包配置信息数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportWalletConfig(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
