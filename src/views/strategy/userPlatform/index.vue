<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="90px">
<!--      <el-form-item label="用户随机id" prop="userUuid">-->
<!--        <el-input-->
<!--          v-model="queryParams.userUuid"-->
<!--          placeholder="请输入用户随机id"-->
<!--          clearable-->
<!--          size="small"-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="用户手机号" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入用户手机号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="平台名称" prop="platformName">
        <el-input
          v-model="queryParams.platformName"
          placeholder="请输入平台名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="key" prop="appkey">
        <el-input
          v-model="queryParams.appkey"
          placeholder="请输入key"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否开启" prop="isEnabled">
        <el-select v-model="queryParams.isEnabled" placeholder="请选择是否开启" clearable size="small">
          <el-option
            v-for="dict in isEnabledOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
<!--      <el-form-item label="是否删除 N否 Y是" prop="isDelete">-->
<!--        <el-select v-model="queryParams.isDelete" placeholder="请选择是否删除 N否 Y是" clearable size="small">-->
<!--          <el-option-->
<!--            v-for="dict in isDeleteOptions"-->
<!--            :key="dict.dictValue"-->
<!--            :label="dict.dictLabel"-->
<!--            :value="dict.dictValue"-->
<!--          />-->
<!--        </el-select>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="状态 N否 Y是" prop="syncStatus">-->
<!--        <el-select v-model="queryParams.syncStatus" placeholder="请选择状态 N否 Y是" clearable size="small">-->
<!--          <el-option-->
<!--            v-for="dict in syncStatusOptions"-->
<!--            :key="dict.dictValue"-->
<!--            :label="dict.dictLabel"-->
<!--            :value="dict.dictValue"-->
<!--          />-->
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
          v-hasPermi="['strategy:userPlatform:add']"
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
<!--          v-hasPermi="['strategy:userPlatform:edit']"-->
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
<!--          v-hasPermi="['strategy:userPlatform:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['strategy:userPlatform:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="userPlatformList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="流水编号" align="center" prop="id" />
<!--      <el-table-column label="用户随机id" align="center" prop="userUuid" />-->
      <el-table-column label="名称" align="center" prop="name" />
      <el-table-column label="用户手机号" align="center" prop="phone" />
      <el-table-column label="平台名称" align="center" prop="platformName" />
      <el-table-column label="API信息" align="center" prop="appkey"  width="330">
        <template slot-scope="scope">
          <tr>
            <td>
              <span>key：{{scope.row.appkey}}&nbsp;&nbsp;&nbsp;&nbsp;</span>
            </td>
          </tr>
          <tr>
            <td>
              <span>secretKey：{{scope.row.appsecret}}&nbsp;</span>
            </td>
          </tr>
          <tr  v-if="scope.row.tradePassword!=null && scope.row.tradePassword!=''">
            <td>
              <span>passphrase：{{scope.row.tradePassword}}&nbsp;</span>
            </td>
          </tr>
        </template>
      </el-table-column>
      <el-table-column label="到期时间" align="center" prop="expirationDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.expirationDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
<!--     <el-table-column label="分润比例" align="center" prop="shareRate" />-->
<!--      <el-table-column label="key" align="center" prop="appkey" />-->
<!--      <el-table-column label="secretKey" align="center" prop="appsecret" />-->
<!--      <el-table-column label="passphrase" align="center" prop="tradePassword" />-->
<!--      <el-table-column label="是否开启" align="center" prop="isEnabled" :formatter="isEnabledFormat" />-->
<!--      <el-table-column label="是否删除 N否 Y是" align="center" prop="isDelete" :formatter="isDeleteFormat" />-->
<!--      <el-table-column label="创建时间" align="center" prop="createDate" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.createDate) }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column label="初始资金" align="center" prop="initUsdt" />
<!--      <el-table-column label="当前资金" align="center" prop="currentUsdt" />-->
<!--      <el-table-column label="累计收益" align="center" prop="totalIncome" />-->
<!--      <el-table-column label="周收益" align="center" prop="currentIncome" />-->
<!--      <el-table-column label="周分润" align="center" prop="shareIncome" />-->
<!--      <el-table-column label="支付分润" align="center" >-->
<!--        <template slot-scope="scope">-->
<!--          <el-button v-if="scope.row.shareIncome>0"-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleShare(scope.row)"-->
<!--            v-hasPermi="['strategy:userPlatform:edit']"-->
<!--          >支付</el-button>-->
<!--          <el-button  v-if="scope.row.shareIncome<=0"-->
<!--            size="mini"-->
<!--            type="text"-->
<!--          >已分润</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="策略控制" align="center" prop="crateTime" width="400">-->
<!--        <template slot-scope="scope">-->
<!--          <tr v-for="item in scope.row.strategyApiList">-->
<!--            <td>-->
<!--              <span>{{item.strategyTypeName}}：首单金额比例：<el-input size="mini" v-model="item.baseRate" style="width: 100px;"></el-input>-->
<!--                <el-button-->
<!--                  size="mini"-->
<!--                  type="text"-->
<!--                  icon="el-icon-edit"-->
<!--                  @click="handleUpdateBaseRate(item)"-->
<!--                  v-hasPermi="['strategy:userPlatform:edit']"-->
<!--                >修改</el-button> &nbsp;-->
<!--               <el-button v-if="item.strategyStatus==0 || item.strategyStatus==2"-->
<!--                 size="mini"-->
<!--                 type="text"-->
<!--                 icon="el-icon-edit"-->
<!--                 @click="startMartinStrategy(item)"-->
<!--                 v-hasPermi="['strategy:userPlatform:edit']"-->
<!--               >开启</el-button>-->
<!--                <el-button v-if="item.strategyStatus==1"-->
<!--                           size="mini"-->
<!--                           type="text"-->
<!--                           icon="el-icon-edit"-->
<!--                           @click="stopMartinStrategy(item)"-->
<!--                           v-hasPermi="['strategy:userPlatform:edit']"-->
<!--                >关闭</el-button>-->
<!--              </span>-->
<!--            </td>-->
<!--          </tr>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="更新时间" align="center" prop="updateDate" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.updateDate, '{y}-{m}-{d}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="同步时间" align="center" prop="syncDate" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.syncDate, '{y}-{m}-{d}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="状态 N否 Y是" align="center" prop="syncStatus" :formatter="syncStatusFormat" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['strategy:userPlatform:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['strategy:userPlatform:remove']"
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

    <!-- 添加或修改用户平台私钥信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
<!--        <el-form-item label="用户随机id" prop="userUuid">-->
<!--          <el-input v-model="form.userUuid" placeholder="请输入用户随机id" />-->
<!--        </el-form-item>-->
        <el-form-item label="用户手机号" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入用户手机号" />
        </el-form-item>
        <el-form-item label="名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="平台名称" prop="platformName">
<!--          <el-input v-model="form.platformName" placeholder="请输入平台名称" />-->
          <el-select v-model="form.platformName" placeholder="请输入币种">
            <el-option
              v-for="dict in platformListAll"
              :key="dict.name"
              :label="dict.name"
              :value="dict.name"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="初始资金(USDT)" prop="initUsdt">
          <el-input v-model="form.initUsdt" placeholder="请输入初始资金" />
        </el-form-item>
<!--        <el-form-item label="分润比例" prop="shareRate">-->
<!--          <el-input v-model="form.shareRate" placeholder="请输入分润比例" />-->
<!--        </el-form-item>-->
        <el-form-item label="key" prop="appkey">
          <el-input v-model="form.appkey" placeholder="请输入key"/>
<!--          <el-input v-model="form.appkey" placeholder="请输入key" disabled="disabled" v-if="form.id!=null"/>-->
        </el-form-item>
        <el-form-item label="secretKey" prop="appsecret">
          <el-input v-model="form.appsecret" type="textarea" placeholder="请输入内容"/>
<!--          <el-input v-model="form.appsecret" type="textarea" placeholder="请输入内容" disabled="disabled" v-if="form.id!=null"/>-->
        </el-form-item>
        <el-form-item label="passphrase" prop="tradePassword">
          <el-input v-model="form.tradePassword" type="textarea" placeholder="请输入内容"/>
<!--          <el-input v-model="form.tradePassword" type="textarea" placeholder="请输入内容" disabled="disabled"  v-if="form.id!=null"/>-->
        </el-form-item>
<!--        <el-form-item label="是否开启">-->
<!--          <el-radio-group v-model="form.isEnabled">-->
<!--            <el-radio-->
<!--              v-for="dict in isEnabledOptions"-->
<!--              :key="dict.dictValue"-->
<!--              :label="dict.dictValue"-->
<!--            >{{dict.dictLabel}}</el-radio>-->
<!--          </el-radio-group>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="是否删除 N否 Y是">-->
<!--          <el-radio-group v-model="form.isDelete">-->
<!--            <el-radio-->
<!--              v-for="dict in isDeleteOptions"-->
<!--              :key="dict.dictValue"-->
<!--              :label="dict.dictValue"-->
<!--            >{{dict.dictLabel}}</el-radio>-->
<!--          </el-radio-group>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="创建时间" prop="createDate">-->
<!--          <el-date-picker clearable size="small"-->
<!--            v-model="form.createDate"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="选择创建时间">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="更新时间" prop="updateDate">-->
<!--          <el-date-picker clearable size="small"-->
<!--            v-model="form.updateDate"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="选择更新时间">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
          <el-form-item label="到期时间" prop="expirationDate">
            <el-date-picker clearable size="small"
              v-model="form.expirationDate"
              type="date"
              value-format="yyyy-MM-dd"
              placeholder="选择到期时间">
            </el-date-picker>
          </el-form-item>
<!--        <el-form-item label="同步时间" prop="syncDate">-->
<!--          <el-date-picker clearable size="small"-->
<!--            v-model="form.syncDate"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="选择同步时间">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="状态 N否 Y是">-->
<!--          <el-radio-group v-model="form.syncStatus">-->
<!--            <el-radio-->
<!--              v-for="dict in syncStatusOptions"-->
<!--              :key="dict.dictValue"-->
<!--              :label="dict.dictValue"-->
<!--            >{{dict.dictLabel}}</el-radio>-->
<!--          </el-radio-group>-->
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
import { listUserPlatform, getUserPlatform, delUserPlatform, addUserPlatform, updateUserPlatform, exportUserPlatform,updateDelete,updateShare } from "@/api/strategy/userPlatform";
import { listPlatformAll} from "@/api/strategy/platform";
import { updateMartinStrategyApi,startMartinStrategyApi,stopMartinStrategyApi,updateBaseRate } from "@/api/strategy/martinStrategyApi";

export default {
  name: "UserPlatform",
  components: {
  },
  data() {
    return {
      strategyInfoList:[],
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
      // 用户平台私钥信息表格数据
      userPlatformList: [],
      // 交易所列表
      platformListAll: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否开启 N否 Y是字典
      isEnabledOptions: [],
      // 是否删除 N否 Y是字典
      isDeleteOptions: [],
      // 状态 N否 Y是字典
      syncStatusOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userUuid: null,
        phone: null,
        platformName: null,
        name: null,
        appkey: null,
        isEnabled: null,
        isDelete: null,
        syncStatus: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userUuid: [
          { required: true, message: "用户随机id不能为空", trigger: "blur" }
        ],
        phone: [
          { required: true, message: "用户手机号不能为空", trigger: "blur" }
        ],
        platformName: [
          { required: true, message: "平台名称不能为空", trigger: "blur" }
        ],
        name: [
          { required: true, message: "名称不能为空", trigger: "blur" }
        ],
        appkey: [
          { required: true, message: "key不能为空", trigger: "blur" }
        ],
        // appsecret: [
        //   { required: true, message: "平台私钥不能为空", trigger: "blur" }
        // ],
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("sys_yes_no").then(response => {
      this.isEnabledOptions = response.data;
    });
    this.getDicts("sys_yes_no").then(response => {
      this.isDeleteOptions = response.data;
    });
    this.getDicts("sys_yes_no").then(response => {
      this.syncStatusOptions = response.data;
    });
  },
  methods: {
    /** 查询用户平台私钥信息列表 */
    getList() {
      this.loading = true;
      listUserPlatform(this.queryParams).then(response => {
        this.userPlatformList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    getPlatformListAll(){
      listPlatformAll().then(response => {
        this.platformListAll = response.data;
      });
    },
    getStrategyInfoListAll(){
      listPlatformAll().then(response => {
        this.strategyInfoList = response.data;
      });
    },
    // 是否开启 N否 Y是字典翻译
    isEnabledFormat(row, column) {
      return this.selectDictLabel(this.isEnabledOptions, row.isEnabled);
    },
    // 是否删除 N否 Y是字典翻译
    isDeleteFormat(row, column) {
      return this.selectDictLabel(this.isDeleteOptions, row.isDelete);
    },
    // 状态 N否 Y是字典翻译
    syncStatusFormat(row, column) {
      return this.selectDictLabel(this.syncStatusOptions, row.syncStatus);
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
        userUuid: null,
        phone: null,
        platformName: null,
        name: null,
        appkey: null,
        appsecret: null,
        tradePassword: null,
        isEnabled: "Y",
        isDelete: "0",
        createDate: null,
        updateDate: null,
        expirationDate: null,
        syncDate: null,
        syncStatus: "0",
        initUsdt:0,
        shareRate:0.2,
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
      this.title = "添加用户平台私钥信息";
      this.getPlatformListAll();
    },
    /** 修改首单金额 */
    handleUpdateBaseRate(row) {
      console.log(row);
      updateBaseRate(row).then(response => {
        this.msgSuccess("修改首单金额");
        this.getList();
      });
    },
    /** 支付金额操作 */
    handleShare(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认该账号已支付分润?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        return updateShare(row);
      }).then(() => {
        this.getList();
        this.msgSuccess("操作成功");
      })
    },

    /** 开启 */
    startMartinStrategy(row) {
      console.log(row);
      startMartinStrategyApi(row).then(response => {
        this.msgSuccess("开启策略成功");
        this.getList();
      });
    },

    /** 停止 */
    stopMartinStrategy(row) {
      console.log(row);
      stopMartinStrategyApi(row).then(response => {
        this.msgSuccess("停止策略成功");
        this.getList();
      });
    },

    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.getPlatformListAll();
      const id = row.id || this.ids
      getUserPlatform(id).then(response => {
        this.form = response.data;
        this.form.appsecret = null;
        this.form.tradePassword = null;
        this.open = true;
        this.title = "修改用户平台私钥信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateUserPlatform(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addUserPlatform(this.form).then(response => {
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
      this.$confirm('是否确认删除用户平台私钥信息编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return updateDelete(ids);
          // return delUserPlatform(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有用户平台私钥信息数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportUserPlatform(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
