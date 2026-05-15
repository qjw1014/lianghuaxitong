<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="客户端" prop="appId">
        <el-select v-model="queryParams.appId" placeholder="请选择" clearable size="small">
          <el-option
            v-for="dict in versionAppOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          ></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="状态" prop="dataStatus">
        <el-select v-model="queryParams.dataStatus" placeholder="请选择状态" clearable size="small">
          <el-option
            v-for="dict in dataStatusOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
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
          v-hasPermi="['version:version:add']"
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
          v-hasPermi="['version:version:edit']"
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
<!--          v-hasPermi="['version:version:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['version:version:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="versionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" />
      <el-table-column label="客户端" align="center" prop="appId" :formatter="versionAppFormat"/>
      <el-table-column label="大版本id" align="center" prop="versionId" />
      <el-table-column label="小版本id" align="center" prop="versionMini" />
      <el-table-column label="版本号" align="center" prop="versionCode" />
      <el-table-column label="升级类型" align="center" prop="type" :formatter="versionTypeFormat"/>
      <el-table-column label="下载地址" align="center" prop="appUrl" />
      <el-table-column label="升级提示" align="center" prop="upgradePoint" />
      <el-table-column label="状态" align="center" prop="dataStatus" :formatter="dataStatusFormat" >
        <template slot-scope="scope">
          <el-switch
            v-model="scope.row.dataStatus"
            active-value="Y"
            inactive-value="N"
            @change="handleStatusChange(scope.row)"
          ></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['version:version:edit']"
          >修改</el-button>
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['version:version:remove']"-->
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

    <!-- 添加或修改app版本升级对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="50%" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="客户端" prop="appId">
<!--          <el-input v-model="form.appId" placeholder="请输入客户端ios android" />-->
          <el-radio-group v-model="form.appId">
            <el-radio
              v-for="dict in versionAppOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="大版本id" prop="versionId">
          <el-input v-model="form.versionId" placeholder="请输入大版本id" />
        </el-form-item>
        <el-form-item label="小版本id" prop="versionMini">
          <el-input v-model="form.versionMini" placeholder="请输入小版本id" />
        </el-form-item>
        <el-form-item label="版本号" prop="versionCode">
          <el-input v-model="form.versionCode" placeholder="请输入版本号" />
        </el-form-item>
        <el-form-item label="升级类型" prop="type">
<!--          <el-select v-model="form.type" placeholder="">-->
<!--            <el-option label="请选择字典生成" value="" />-->
<!--          </el-select>-->
          <el-select v-model="form.type" placeholder="请选择" :rules="rules">
            <el-option
              v-for="dict in versionTypeOptions"
              :key="dict.dictValue"
              :label="dict.dictLabel"
              :value="dict.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="下载地址" prop="appUrl">
          <el-input v-model="form.appUrl" placeholder="请输入下载地址" />
        </el-form-item>
        <el-form-item label="升级提示" prop="upgradePoint">
          <el-input v-model="form.upgradePoint" placeholder="请输入升级提示" />
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="form.dataStatus">
            <el-radio
              v-for="dict in dataStatusOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
            >{{dict.dictLabel}}</el-radio>
          </el-radio-group>
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
import { changeStatus,listVersion, getVersion, delVersion, addVersion, updateVersion, exportVersion } from "@/api/version/version";

export default {
  name: "Version",
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
      // app版本升级表格数据
      versionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 状态 Y 开启 N不开启字典
      dataStatusOptions: [],
      // 1升级，0不升级，2强制升级
      versionTypeOptions: [],
      //iso android
      versionAppOptions:[],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        appId: null,
        versionId: null,
        versionMini: null,
        versionCode: null,
        type: null,
        appUrl: null,
        upgradePoint: null,
        dataStatus: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        appId: [
          { required: true, message: "客户端ios android不能为空", trigger: "blur" }
        ],
        dataStatus: [
          { required: true, message: "状态 Y 开启 N不开启不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("sys_yes_no").then(response => {
      this.dataStatusOptions = response.data;
    });

    this.getDicts("version_type").then(response => {
      this.versionTypeOptions = response.data;
    });

    this.getDicts("version_app").then(response => {
      this.versionAppOptions = response.data;
    });
  },
  methods: {
    /** 查询app版本升级列表 */
    getList() {
      this.loading = true;
      listVersion(this.queryParams).then(response => {
        this.versionList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 状态 Y 开启 N不开启字典翻译
    dataStatusFormat(row, column) {
      return this.selectDictLabel(this.dataStatusOptions, row.dataStatus);
    },

    // 状态 Y 开启 N不开启字典翻译
    versionTypeFormat(row, column) {
      return this.selectDictLabel(this.versionTypeOptions, row.type);
    },

    // 状态 Y 开启 N不开启字典翻译
    versionAppFormat(row, column) {
      return this.selectDictLabel(this.versionAppOptions, row.appId);
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
        appId: null,
        versionId: null,
        versionMini: null,
        versionCode: null,
        type: null,
        appUrl: null,
        upgradePoint: null,
        dataStatus: "0",
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
      this.title = "添加app版本升级";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getVersion(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改app版本升级";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateVersion(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addVersion(this.form).then(response => {
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
      this.$confirm('是否确认删除app版本升级编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delVersion(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有app版本升级数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportVersion(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    },
    // 状态修改
    handleStatusChange(row) {
      let text = row.status === "N" ? "禁用" : "启用";
      this.$confirm('确认要' + text + '"' +row.versionCode + '版本吗?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return changeStatus(row.id, row.status);
      }).then(() => {
        this.msgSuccess(text + "成功");
      }).catch(function() {
        row.status = row.status === "N" ? "Y" : "N";
      });
    }
  }
};
</script>
