<template>
  <div class="app-container calendar-list-container">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item" placeholder="名称" v-model="listQuery.name"> </el-input>
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item" placeholder="代码" v-model="listQuery.code"> </el-input>
      <el-button class="filter-item" type="primary" v-waves icon="search" @click="handleFilter">搜索</el-button>
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
        <el-table-column  align="left" label="名称">
      <template scope="scope">
        <span>{{scope.row.name}}</span>
      </template>
    </el-table-column>
        <el-table-column  align="left" label="代码">
      <template scope="scope">
        <span>{{scope.row.code}}</span>
      </template>
    </el-table-column>
        <el-table-column fixed="right" align="center" label="操作" width="150">
        <template scope="scope">
            <el-button  size="small" type="success" @click="handleUpdate(scope.row)">选择
            </el-button>
        </template>
      </el-table-column>
    </el-table>

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
    props:['deptName','dept','dialogShow'],
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
      },
      handleUpdate(row) {
        this.$emit('update:dialogShow', false)
        this.$emit('update:deptName', row.name)
        this.$emit('update:dept', row.id)
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
