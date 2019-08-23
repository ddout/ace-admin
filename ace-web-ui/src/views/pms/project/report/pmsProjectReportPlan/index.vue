<template>
  <div class="app-container calendar-list-container">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item" placeholder="姓名或账户" v-model="listQuery.name"> </el-input>
      <el-button class="filter-item" type="primary" v-waves icon="search" @click="handleFilter">搜索</el-button>
      <el-button class="filter-item" v-if="pmsProjectReportPlanManager_btn_add" style="margin-left: 10px;" @click="handleCreate" type="primary" icon="edit">添加</el-button>
    </div>
    <el-table :key='tableKey' :data="list" v-loading.body="listLoading" border fit highlight-current-row style="width: 100%">

      <el-table-column align="center" label="id" v-if="false">
      <template scope="scope">
        <span>{{scope.row.id}}</span>
      </template>
    </el-table-column>
        <el-table-column  align="center" label="工作内容">
      <template scope="scope">
        <span>{{scope.row.context}}</span>
      </template>
    </el-table-column>
        <el-table-column  align="center" label="开始时间">
      <template scope="scope">
        <span>{{scope.row.beginTime}}</span>
      </template>
    </el-table-column>
        <el-table-column  align="center" label="结束时间">
      <template scope="scope">
        <span>{{scope.row.endTime}}</span>
      </template>
    </el-table-column>
        <el-table-column  align="center" label="立项报告">
      <template scope="scope">
        <span>{{scope.row.projectReportId}}</span>
      </template>
    </el-table-column>
        <el-table-column fixed="right" align="center" label="操作" width="150">
        <template scope="scope">
            <el-button v-if="pmsProjectReportPlanManager_btn_edit" size="small" type="success" @click="handleUpdate(scope.row)">编辑
            </el-button>
            <el-button v-if="pmsProjectReportPlanManager_btn_del" size="small" type="danger" @click="handleDelete(scope.row)">删除
            </el-button>
        </template>
      </el-table-column>
    </el-table>

    <div v-show="!listLoading" class="pagination-container">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="listQuery.page" :page-sizes="[10,20,30, 50]" :page-size="listQuery.limit" layout="total, sizes, prev, pager, next, jumper" :total="total"> </el-pagination>
    </div>
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form :model="form" :rules="rules" ref="form" label-width="100px">
        <el-form-item label="工作内容" prop="context">
      <el-input v-model="form.context" placeholder="请输入工作内容"></el-input>
    </el-form-item>
        <el-form-item label="开始时间" prop="beginTime">
      <el-input v-model="form.beginTime" placeholder="请输入开始时间"></el-input>
    </el-form-item>
        <el-form-item label="结束时间" prop="endTime">
      <el-input v-model="form.endTime" placeholder="请输入结束时间"></el-input>
    </el-form-item>
        <el-form-item label="立项报告" prop="projectReportId">
      <el-input v-model="form.projectReportId" placeholder="请输入立项报告"></el-input>
    </el-form-item>
        </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel('form')">取 消</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="create('form')">确 定</el-button>
        <el-button v-else type="primary" @click="update('form')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {
      page,
      addObj,
      getObj,
      delObj,
      putObj
  } from 'api/pms/project/report/pmsProjectReportPlan/index';
  import { mapGetters } from 'vuex';
  export default {
    name: 'pmsProjectReportPlan',
    data() {
      return {
        form: {
        context : undefined,        beginTime : undefined,        endTime : undefined,        projectReportId : undefined          },
        rules: {
    context: [
  {
    required: true,
    message: '请输入工作内容',
    trigger: 'blur'
  },
  {
    min: 3,
    max: 20,
    message: '长度在 3 到 20 个字符',
    trigger: 'blur'
  }
],   beginTime: [
  {
    required: true,
    message: '请输入开始时间',
    trigger: 'blur'
  },
  {
    min: 3,
    max: 20,
    message: '长度在 3 到 20 个字符',
    trigger: 'blur'
  }
],   endTime: [
  {
    required: true,
    message: '请输入结束时间',
    trigger: 'blur'
  },
  {
    min: 3,
    max: 20,
    message: '长度在 3 到 20 个字符',
    trigger: 'blur'
  }
],   projectReportId: [
  {
    required: true,
    message: '请输入立项报告',
    trigger: 'blur'
  },
  {
    min: 3,
    max: 20,
    message: '长度在 3 到 20 个字符',
    trigger: 'blur'
  }
]        },
        list: null,
        total: null,
        listLoading: true,
        listQuery: {
          page: 1,
          limit: 20,
          name: undefined
        },
        dialogFormVisible: false,
        dialogStatus: '',
        pmsProjectReportPlanManager_btn_edit: false,
        pmsProjectReportPlanManager_btn_del: false,
        pmsProjectReportPlanManager_btn_add: false,
        textMap: {
          update: '编辑',
          create: '创建'
        },
        tableKey: 0
      }
    },
    created() {
      this.getList();
      this.pmsProjectReportPlanManager_btn_edit = this.elements['pmsProjectReportPlanManager:btn_edit'];
      this.pmsProjectReportPlanManager_btn_del = this.elements['pmsProjectReportPlanManager:btn_del'];
      this.pmsProjectReportPlanManager_btn_add = this.elements['pmsProjectReportPlanManager:btn_add'];
    },
    computed: {
      ...mapGetters([
        'elements'
      ])
    },
    methods: {
      getList() {
        this.listLoading = true;
        page(this.listQuery)
            .then(response => {
          this.list = response.data.rows;
        this.total = response.data.total;
        this.listLoading = false;
      })
      },
      handleFilter() {
        this.getList();
      },
      handleSizeChange(val) {
        this.listQuery.limit = val;
        this.getList();
      },
      handleCurrentChange(val) {
        this.listQuery.page = val;
        this.getList();
      },
      handleCreate() {
        this.resetTemp();
        this.dialogStatus = 'create';
        this.dialogFormVisible = true;
      },
      handleUpdate(row) {
        getObj(row.id)
            .then(response => {
          this.form = response.data;
        this.dialogFormVisible = true;
        this.dialogStatus = 'update';
      });
      },
      handleDelete(row) {
        this.$confirm('此操作将永久删除, 是否继续?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'warning'
            })
            .then(() => {
          delObj(row.id)
      .then(() => {
          this.$notify({
          title: '成功',
          message: '删除成功',
          type: 'success',
          duration: 2000
        });
        const index = this.list.indexOf(row);
        this.list.splice(index, 1);
      });
      });
      },
      create(formName) {
        const set = this.$refs;
        set[formName].validate(valid => {
        if (valid) {
        addObj(this.form)
        .then(() => {
        this.dialogFormVisible = false;
        this.getList();
        this.$notify({
        title: '成功',
        message: '创建成功',
        type: 'success',
        duration: 2000
        });
        })
        } else {
        return false;
        }
        });
      },
      cancel(formName) {
        this.dialogFormVisible = false;
        const set = this.$refs;
        set[formName].resetFields();
      },
      update(formName) {
        const set = this.$refs;
        set[formName].validate(valid => {
        if (valid) {
        this.dialogFormVisible = false;
        putObj(this.form.id, this.form).then(() => {
        this.dialogFormVisible = false;
        this.getList();
        this.$notify({
        title: '成功',
        message: '创建成功',
        type: 'success',
        duration: 2000
        });
        });
        } else {
        return false;
        }
        });
      },
      resetTemp() {
        this.form = {
                                                //工作内容
              context : undefined,                                        //开始时间
              beginTime : undefined,                                        //结束时间
              endTime : undefined,                                        //立项报告
              projectReportId : undefined                          };
      }
    }
  }
</script>
