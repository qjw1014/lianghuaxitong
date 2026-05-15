<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="类型" prop="terminalType">
        <el-select v-model="queryParams.pid" placeholder="请选择类型" clearable size="small">
          <el-option
            v-for="dict in parentTypeOptions"
            :key="dict.id"
            :label="dict.tips"
            :value="dict.id"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="提示" prop="tips">
        <el-input
          v-model="queryParams.tips"
          placeholder="请输入提示"
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
          v-hasPermi="['sysDict:sysDict:add']"
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
          v-hasPermi="['sysDict:sysDict:edit']"
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
<!--          v-hasPermi="['sysDict:sysDict:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['sysDict:sysDict:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="sysDictList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="id" />
      <el-table-column label="排序" align="center" prop="num" />
      <el-table-column label="类型" align="center" prop="pid" :formatter="parentTypeFormat" />
      <el-table-column label="值" align="center" prop="name" />
<!--      <el-table-column label="图片" align="center" prop="projectIconUrlAbs" >-->
<!--        <template width="90" slot-scope="scope">-->
<!--          <el-image-->
<!--            style="height: 80px"-->
<!--            :src="scope.row.name"-->
<!--            @click="previewImage(scope.row.name)"-->
<!--            :preview-src-list="srcList"-->
<!--          ></el-image>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column label="提示" align="center" prop="tips" />
      <el-table-column label="标识" align="center" prop="code" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['sysDict:sysDict:edit']"
          >修改</el-button>
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['sysDict:sysDict:remove']"-->
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

    <!-- 添加或修改参数配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="排序" prop="num">
          <el-input v-model="form.num" placeholder="请输入排序" />
        </el-form-item>
        <el-form-item label="类型" prop="pid">
          <el-select v-model="form.pid" placeholder="请选择类型" clearable size="small">
            <el-option
              v-for="dict in parentTypeOptions"
              :key="dict.id"
              :label="dict.tips"
              :value="dict.id"
            />
          </el-select>
<!--          <el-input v-model="form.pid" placeholder="请输入上级编号" disabled="disabled" />-->
        </el-form-item>
        <el-form-item label="值" prop="name" v-if="form.code!='DEFAULT_ADDRESS_LOGO_URL'">
          <el-input v-model="form.name" placeholder="请输入值" />
        </el-form-item>
        <el-form-item label="请上传头像" v-if="form.code=='DEFAULT_ADDRESS_LOGO_URL'">
          <el-upload
            class="avatar-uploader"
            :show-file-list="false"
            :action="uploadUrl()"
            :on-success="pojectAddSuccess"
            :before-upload="beforeAvatarUpload"
            :data="{path:'nft'}"
          >
            <img v-if="form.name" :src="form.name" class="avatar" style="height:150px">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="提示" prop="tips">
          <el-input v-model="form.tips" placeholder="请输入提示" />
        </el-form-item>
        <el-form-item label="标识" prop="code">
          <el-input v-model="form.code" placeholder="请输入标识" disabled="disabled" v-if="form.id!=null" />
          <el-input v-model="form.code" placeholder="请输入标识" v-if="form.id==null" />
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
import { listSysDict, getSysDict, delSysDict, addSysDict, updateSysDict, exportSysDict,parentList } from "@/api/sysDict/sysDict";

export default {
  name: "SysDict",
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
      // 参数配置表格数据
      sysDictList: [],
      //父级分类列表
      parentTypeOptions:[],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        num: null,
        pid: null,
        name: null,
        tips: null,
        code: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        num: [
          { required: true, message: "排序不能为空", trigger: "blur" }
        ],
        name: [
          { required: true, message: "值不能为空", trigger: "blur" }
        ],
        tips: [
          { required: true, message: "提示不能为空", trigger: "blur" }
        ],
        code: [
          { required: true, message: "标识不能为空", trigger: "blur" }
        ]
      },
      // 预览
      srcList: ["http://118.25.187.239:9099/img/adImg/557097620301025280.jpg"],
    };
  },
  created() {
    parentList().then(response => {
      this.parentTypeOptions = response.rows;
    });
    this.getList();
  },
  methods: {
    uploadUrl(){
      // 地址
      return process.env.VUE_APP_BASE_API+"/system/ftpfile/uploadFile"
    },
    /** 查询参数配置列表 */
    getList() {
      this.loading = true;
      listSysDict(this.queryParams).then(response => {
        this.sysDictList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 状态字典翻译
    parentTypeFormat(row, column) {
      return this.selectParentTypeList(this.parentTypeOptions, row.pid);
    },
    selectParentTypeList(datas, value) {
      var actions = [];
      Object.keys(datas).some((key) => {
        if (datas[key].id == ('' + value)) {
          actions.push(datas[key].tips);
          return true;
        }
      })
      return actions.join('');
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
        num: null,
        pid: null,
        name: null,
        tips: null,
        code: null
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
      this.title = "添加参数配置";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getSysDict(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改参数配置";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateSysDict(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSysDict(this.form).then(response => {
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
      this.$confirm('是否确认删除参数配置编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delSysDict(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有参数配置数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportSysDict(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    },
    // 预览图片
    previewImage(abUrl) {
      this.srcList[0] = abUrl;
    },
    // 项目图片上传组件自带的成功回调函数
    pojectAddSuccess(res) {
      console.log(res);
      this.form.name = res.data.url;
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
    }
  }
};
</script>
