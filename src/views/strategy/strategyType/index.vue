<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
<!--      <el-form-item label="类型" prop="type">-->
<!--        <el-select v-model="queryParams.type" placeholder="请选择类型" clearable size="small">-->
<!--          <el-option label="请选择字典生成" value="" />-->
<!--        </el-select>-->
<!--      </el-form-item>-->
      <el-form-item label="类型名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入类型名称"
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
          v-hasPermi="['strategy:strategyType:add']"
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
          v-hasPermi="['strategy:strategyType:edit']"
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
          v-hasPermi="['strategy:strategyType:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['strategy:strategyType:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="strategyTypeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="流水编号" align="center" prop="id" />
<!--      <el-table-column label="类型" align="center" prop="type" />-->
      <el-table-column label="类型名称" align="center" prop="name" />
      <el-table-column label="是否开启" align="center" prop="isEnabled" :formatter="isEnabledFormat" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['strategy:strategyType:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-circle-check"
            @click="choose(scope.row)"
            v-hasPermi="['system:role:edit']"
          >字段配置</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['strategy:strategyType:remove']"
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

    <!-- 添加或修改策略类型对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
<!--        <el-form-item label="类型" prop="type">-->
<!--          <el-select v-model="form.type" placeholder="请选择类型">-->
<!--            <el-option label="请选择字典生成" value="" />-->
<!--          </el-select>-->
<!--        </el-form-item>-->
        <el-form-item label="类型名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入类型名称" />
        </el-form-item>
        <el-form-item label="是否开启">
          <el-radio-group v-model="form.isEnabled">
            <el-radio
              v-for="dict in isEnabledOptions"
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

<!--    <p>-->
<!--      <el-tag v-for="(item,index) in allSelectedUserList" :key="index"-->
<!--              @close="removeUser(index)"-->
<!--              style="margin-right: 10px"-->
<!--              closable>-->
<!--        {{item.name}}-->
<!--      </el-tag>-->
<!--    </p>-->
   <!--弹框列表-->
    <el-dialog :visible.sync="dialogVisible" title="选择字段" v-if="dialogVisible" width="50%" append-to-body>
      <el-table ref="table"  @selection-change="selectionChange" @loadData="loadData" :data="tableData">
        <el-table-column type="selection" width="55" align="center" :reserve-selection="true"/>
        <el-table-column label="编号" align="center" prop="id" width="120" />
        <el-table-column label="字段" align="center" prop="name" width="200" />
        <el-table-column label="备注" align="center" prop="remarks" width="500" />
      </el-table>
      <span slot="footer">
      <el-button @click="dialogVisible = false" size="mini">取 消</el-button>
      <el-button @click="chooseConfirm" size="mini" type="primary">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { listStrategyType, getStrategyType, delStrategyType, addStrategyType, updateStrategyType, exportStrategyType } from "@/api/strategy/strategyType";
import { listCommonField,typeList, getCommonField, delCommonField, addCommonField, updateCommonField, exportCommonField } from "@/api/strategy/commonField";
import { batchAddStrategyField } from "@/api/strategy/strategyField";

export default {
  name: "StrategyType",
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
      // 策略类型表格数据
      strategyTypeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否开启字典
      isEnabledOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        type: null,
        name: null,
        isEnabled: null,
      },
      // 查询参数
      queryParamsFieId: {

      },
      // 表单参数
      addField: {
        fields:[],
        strategyTypeId:null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        type: [
          { required: true, message: "类型不能为空", trigger: "change" }
        ],
        name: [
          { required: true, message: "类型名称不能为空", trigger: "blur" }
        ],
      },
      dialogVisible: false,
      // 最终选中的所有数据
      allSelectedUserList: [],
      // 最终选中的全部唯一字段列表
      allUniquePropList: [],
      // 唯一字段
      uniqueProp: 'id',
      // 每次弹窗中选中的所有数据
      allSelectedList: [],
      tableData: [],
    };
  },
  mounted() {

  },
  created() {
    this.getList();
    this.getDicts("sys_yes_no").then(response => {
      this.isEnabledOptions = response.data;
    });
  },
  methods: {
    /** 查询策略类型列表 */
    getList() {
      this.loading = true;
      listStrategyType(this.queryParams).then(response => {
        this.strategyTypeList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 是否开启字典翻译
    isEnabledFormat(row, column) {
      return this.selectDictLabel(this.isEnabledOptions, row.isEnabled);
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
        type: null,
        name: null,
        isEnabled: "Y",
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
      this.title = "添加策略类型";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getStrategyType(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改策略类型";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateStrategyType(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStrategyType(this.form).then(response => {
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
      this.$confirm('是否确认删除策略类型编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delStrategyType(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有策略类型数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportStrategyType(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    },
    // 点击选择按钮
    choose(row) {
      // 表单参数
      this.addField = {
        fields:[],
        strategyTypeId:null
      },
      // this.allSelectedList = [];
      console.log("选择type:"+row.id)
      this.loadData(row);
      this.allUniquePropList = [];
      this.allSelectedUserList.forEach(
        item => {
          this.allUniquePropList.push(item[this.uniqueProp])
        }
      )
      this.dialogVisible = true;
      this.addField.strategyTypeId= row.id;
    },
    // 点击选择弹窗的确定按钮
    chooseConfirm() {
      this.allSelectedUserList = this.allSelectedUserList.concat(this.allSelectedList);
      this.dialogVisible = false;
      this.allSelectedUserList.forEach(row => {
        this.addField.fields.push(row.id);
      });
      batchAddStrategyField(this.addField).then(response => {
        this.msgSuccess("新增成功");
        this.open = false;
        this.getList();
      });
    },
    // 加载数据
    loadData(row) {
      this.allSelectedUserList =[];
      listCommonField(this.queryParamsFieId).then(response => {
        this.tableData = response.rows;
        this.total = response.total;
        this.loading = false;
      })
      typeList(row.id).then(response => {
        this.allSelectedList = response.data;
        this.updateSelectedMark()
      });
    },
    // 改变多选列的选中状态时，更新选中列表
    selectionChange(pageSelectedList) {
      // 当页唯一字段组成的列表
      let uniquePropList = []
      this.tableData.forEach(row => {
        uniquePropList.push(row[this.uniqueProp])
      })
      // 从全部选中的数据中，过滤掉当前页的数据，再添加当前页选中的数据
      this.allSelectedList = this.allSelectedList.filter(row =>
        !uniquePropList.includes(row[this.uniqueProp])
      ).concat(pageSelectedList)
    },
    // 更新选中标记--选中数据与当页数据的交集，标记为选中状态
    updateSelectedMark() {
      this.allSelectedList.forEach(row => {
        console.log("进来标识"+row.id)
      });
      this.$nextTick(
        () => {
          let pageSelectedList = []
          this.allSelectedList.forEach(row1 => {
            this.tableData.forEach(row2 => {
              if (row1[this.uniqueProp] === row2[this.uniqueProp]) {
                pageSelectedList.push(row2)
              }
            })
          })
          pageSelectedList.forEach(row => {
            this.$refs.table.toggleRowSelection(row,true);
          })
        }
      )
    },
    // 移除选择的用户
    removeUser(index) {
      this.allSelectedUserList.splice(index, 1);
    },
  }
};
</script>
