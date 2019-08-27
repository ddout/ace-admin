<template>
  <div class="app-container calendar-list-container">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item" placeholder="名称" v-model="listQuery.name"> </el-input>
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item" placeholder="代码" v-model="listQuery.code"> </el-input>
      <el-button class="filter-item" type="primary" v-waves icon="search" @click="handleFilter">搜索</el-button>
      <el-button class="filter-item" v-if="baseDeptManager_btn_add" style="margin-left: 10px;" @click="handleCreate" type="primary" icon="edit">添加</el-button>
    </div>
    <el-table :key='tableKey'
              :data="list"
              v-loading.body="listLoading"
              row-key="id"
              :load="handleLoadFilter"
              :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
              @row-click="tableClick"
              border lazy fit highlight-current-row style="width: 100%">

      <el-table-column align="center" label="id" v-if="false">
      <template scope="scope">
        <span>{{scope.row.id}}</span>
      </template>
    </el-table-column>
        <el-table-column  align="center" label="名称">
      <template scope="scope">
        <span>{{scope.row.name}}</span>
      </template>
    </el-table-column>
        <el-table-column  align="center" label="代码">
      <template scope="scope">
        <span>{{scope.row.code}}</span>
      </template>
    </el-table-column>
        <el-table-column fixed="right" align="center" label="操作" width="150">
        <template scope="scope">
            <el-button v-if="baseDeptManager_btn_edit" size="small" type="success" @click="handleUpdate(scope.row)">编辑
            </el-button>
            <el-button v-if="baseDeptManager_btn_del" size="small" type="danger" @click="handleDelete(scope.row)">删除
            </el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--<div v-show="!listLoading" class="pagination-container">-->
      <!--<el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="listQuery.page" :page-sizes="[10,20,30, 50]" :page-size="listQuery.limit" layout="total, sizes, prev, pager, next, jumper" :total="total"> </el-pagination>-->
    <!--</div>-->

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form :model="form" :rules="rules" ref="form" label-width="145px" top="2vh" :close-on-click-modal="false">

                  <el-form-item label="名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入名称"></el-input>
        </el-form-item>
        <el-form-item label="代码" prop="code">
          <el-input v-model="form.code" placeholder="自动生成" readonly="readonly"></el-input>
        </el-form-item>
                        <el-form-item label="父部门" prop="pid" v-show="false">
          <el-input v-model="form.pid" placeholder="请输入父部门"></el-input>
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
  } from 'api/admin/dept/baseDept/index';
  import { mapGetters } from 'vuex';
  export default {
    name: 'baseDept',
    data() {
      return {
        form: {
        name : undefined,
          code : undefined,
          pid : undefined
        },
        rules: {
    name: [
  {
    required: true,
    message: '请输入名称',
    trigger: 'blur'
  }
]
        },
        list: [{"code":"","name":"","id":""}],
        total: null,
        listLoading: true,
        listQuery: {
          // page: 1,
          // limit: 20,
          name: undefined,
          code: undefined
        },
        dialogFormVisible: false,
        dialogStatus: '',
        baseDeptManager_btn_edit: false,
        baseDeptManager_btn_del: false,
        baseDeptManager_btn_add: false,
        textMap: {
          update: '编辑',
          create: '创建'
        },
        tableKey: 0
      }
    },
    created() {
      this.getList();
      this.baseDeptManager_btn_edit = this.elements['baseDeptManager:btn_edit'];
      this.baseDeptManager_btn_del = this.elements['baseDeptManager:btn_del'];
      this.baseDeptManager_btn_add = this.elements['baseDeptManager:btn_add'];
    },
    computed: {
      ...mapGetters([
        'elements'
      ])
    },
    methods: {
      getList() {
          this.list = []
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
      handleLoadFilter(tree, treeNode, resolve){
        let childQuery =  {
          name: this.listQuery.name,
          code: this.listQuery.code,
          pid: tree.id
        }
        page(childQuery).then(response => {
          resolve( response.data.rows)
        })


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
      tableClick(row, column, event){
        this.form.pid = row.id

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
        this.form.name = undefined
        this.form.code = undefined
      }
    }
  }
</script>
