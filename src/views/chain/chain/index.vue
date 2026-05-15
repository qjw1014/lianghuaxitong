<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="链名称" prop="chainName">
        <el-input
          v-model="queryParams.chainName"
          placeholder="请输入链名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="主币名称" prop="mainCoinName">
        <el-input
          v-model="queryParams.mainCoinName"
          placeholder="请输入主币名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户名" prop="userName">
        <el-input
          v-model="queryParams.userName"
          placeholder="请输入用户名"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable size="small">
          <el-option
            v-for="dict in statusOptions"
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
          v-hasPermi="['chain:chain:add']"
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
          v-hasPermi="['chain:chain:edit']"
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
<!--          v-hasPermi="['chain:chain:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['chain:chain:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="chainList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="链编号" align="center" prop="id" v-if="false" />
      <el-table-column label="链名称" align="center" prop="chainName" />
      <el-table-column label="主币名称" align="center" prop="mainCoinName" />
      <el-table-column label="链节点连接" align="center" prop="chainNodeUrl" />
      <el-table-column label="同步高度" align="center" prop="synchronousHeight" />
      <el-table-column label="用户名" align="center" prop="userName" />
      <el-table-column label="状态" align="center" prop="status" :formatter="statusFormat" >
      <template slot-scope="scope">
        <el-switch
          v-model="scope.row.status"
          active-value="Y"
          inactive-value="N"
          @change="handleStatusChange(scope.row)"
        ></el-switch>
      </template>
      </el-table-column>
<!--      <el-table-column label="密码" align="center" prop="password" />-->
      <el-table-column label="区块高度更新时间" align="center" prop="blockTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.blockTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="图片" align="center" prop="chainIconUrlAbs" >
        <template slot-scope="scope">
          <el-image
            style="width: 120px; height: 120px"
            :src="scope.row.chainIconUrlAbs"
            @click="previewImage(scope.row.chainIconUrlAbs)"
            :preview-src-list="srcList"
          ></el-image>
        </template>
      </el-table-column>
<!--      <el-table-column label="图片相对路径" align="center" prop="chainIconUrlAbsRelative" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['chain:chain:edit']"
          >修改</el-button>
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['chain:chain:remove']"-->
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

    <!-- 添加或修改链对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="链名称" prop="chainName">
          <el-input v-model="form.chainName" placeholder="请输入链名称" />
        </el-form-item>
        <el-form-item label="链节点连接" prop="chainNodeUrl">
          <el-input v-model="form.chainNodeUrl" placeholder="请输入链节点连接" />
        </el-form-item>
        <el-form-item label="同步高度" prop="synchronousHeight">
          <el-input v-model="form.synchronousHeight" placeholder="请输入同步高度" />
        </el-form-item>
        <el-form-item label="主币名称" prop="mainCoinName">
          <el-input v-model="form.mainCoinName" placeholder="请输入主币名称" />
        </el-form-item>
        <el-form-item label="用户名" prop="userName">
          <el-input v-model="form.userName" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" placeholder="请输入密码" />
        </el-form-item>
<!--        <el-form-item label="图片绝对路径" prop="chainIconUrlAbs">-->
<!--          <el-input v-model="form.chainIconUrlAbs" placeholder="请输入图片绝对路径" />-->
<!--        </el-form-item>-->
<!--        <el-form-item label="图片">-->
<!--          <el-upload-->
<!--            class="avatar-uploader"-->
<!--            action-->
<!--            :http-request="uploadChainIconUrl"-->
<!--            :show-file-list="false"-->
<!--            :before-upload="beforeAvatarUpload">-->
<!--            <img v-if="form.chainIconUrlAbs" :src="form.chainIconUrlAbs" class="avatar" style="width: 150px" height="150px">-->
<!--            <i v-else class="el-icon-plus avatar-uploader-icon"></i>-->
<!--          </el-upload>-->
<!--        </el-form-item>-->
        <el-form-item label="图片">
          <el-upload
            class="avatar-uploader"
            :show-file-list="false"
            :action="uploadUrl()"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload"
            :data="{path:'ad'}"
          >
            <img v-if="form.chainIconUrlAbs" :src="form.chainIconUrlAbs" class="avatar" style="width: 150px" height="150px">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
<!--        <el-form-item label="图片相对路径" prop="chainIconUrlAbsRelative">-->
<!--          <el-input v-model="form.chainIconUrlAbsRelative" placeholder="请输入图片相对路径" />-->
<!--        </el-form-item>-->
        <el-form-item label="状态">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in statusOptions"
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
import {changeStatus, listChain, getChain, delChain, addChain, updateChain, exportChain } from "@/api/chain/chain";
import {client, getFileNameUUID} from "@/api/ali-oss"; //前面的ali-js文件内的两个封装函数

export default {
  name: "Chain",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      chainNames:[],
      // 状态字典
      statusOptions: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 链表格数据
      chainList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        chainName: null,
        chainNodeUrl: null,
        synchronousHeight: null,
        userName: null,
        status: null,
        password: null,
        blockTime: null,
        chainIconUrlAbs: null,
        chainIconUrlAbsRelative: null,
        mainCoinName: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        chainName: [
          { required: true, message: "链名称不能为空", trigger: "blur" }
        ],
        chainNodeUrl: [
          { required: true, message: "链节点连接不能为空", trigger: "blur" }
        ],
        synchronousHeight: [
          { required: true, message: "同步高度不能为空", trigger: "blur" }
        ],
      },
      // 预览
      srcList: ["http://118.25.187.239:9099/img/adImg/557097620301025280.jpg"],
    };
  },
  created() {
    this.getList();
    this.getDicts("sys_yes_no").then(response => {
      this.statusOptions = response.data;
    });
  },
  methods: {
    uploadUrl(){
      // 地址
      return process.env.VUE_APP_BASE_API+"/system/ftpfile/uploadFile"
    },
    // 预览图片
    previewImage(abUrl) {
      this.srcList[0] = abUrl;
    },
    /** 查询链列表 */
    getList() {
      this.loading = true;
      listChain(this.queryParams).then(response => {
        this.chainList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 状态字典翻译
    statusFormat(row, column) {
      return this.selectDictLabel(this.statusOptions, row.status);
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
        chainName: null,
        chainNodeUrl: null,
        synchronousHeight: null,
        userName: null,
        status: "Y",
        password: null,
        blockTime: null,
        createTime: null,
        chainIconUrlAbs: null,
        chainIconUrlAbsRelative: null,
        mainCoinName: null
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
      this.chainNames = selection.map(item => item.chainName)
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加链";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getChain(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改链";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateChain(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addChain(this.form).then(response => {
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
      const names = row.chainName || this.chainNames;
      this.$confirm('是否确认删除链名称为"' + names + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delChain(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有链数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportChain(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    },
    uploadChainIconUrl(file) {
      let temporary = file.file.name.lastIndexOf(".");
      let fileNameLength = file.file.name.length;
      let fileFormat = file.file.name.substring(
        temporary + 1,
        fileNameLength
      );
      const that = this;
      let fileName = getFileNameUUID() + "." + fileFormat;
      const ossClient = client();

      async function put() {
        try {
          // 填写Object完整路径和本地文件的完整路径。Object完整路径中不能包含Bucket名称。
          // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件。
          let result = await ossClient.put(`/admin/${fileName}`, file.file);
          console.log(result);
          that.form.chainIconUrlAbs = result.url;
        } catch (e) {
          console.log(e);
        }
      }
      put();
      console.log(this.form.chainIconUrlAbs)
    },
    // 图片上传组件自带的成功回调函数
    handleAvatarSuccess(res) {
      console.log(res);
      this.form.chainIconUrlAbs = res.data.url;
      this.form.chainIconUrlAbsRelative = res.data.path;
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg' || file.type === 'image/png';
      const isLt2M = file.size / 1024 / 1024 < 10;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG或png 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 10MB!');
      }
      return isJPG && isLt2M;
    },
    // 状态修改
    handleStatusChange(row) {
      let text = row.status === "N" ? "禁用" : "启用";
      this.$confirm('确认要' + text + '"' +row.chainName + '链吗?', "警告", {
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
