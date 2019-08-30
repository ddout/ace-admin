<template>
  <div class="app-container calendar-list-container">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item" placeholder="姓名或账户" v-model="listQuery.projectName"> </el-input>
      <el-button class="filter-item" type="primary" v-waves icon="search" @click="handleFilter">搜索</el-button>
      <el-button class="filter-item" v-if="pmsProjectReportManager_btn_add" style="margin-left: 10px;" @click="handleCreate" type="primary" icon="edit">添加</el-button>
    </div>
    <el-table :key='tableKey' :data="list" v-loading.body="listLoading" border fit highlight-current-row style="width: 100%">

      <el-table-column align="center" label="id" v-if="false">
      <template scope="scope">
        <span>{{scope.row.id}}</span>
      </template>
    </el-table-column>
        <el-table-column  align="center" label="项目编号">
      <template scope="scope">
        <span>{{scope.row.projectCode}}</span>
      </template>
    </el-table-column>
        <el-table-column  align="center" label="项目名称">
      <template scope="scope">
        <span>{{scope.row.projectName}}</span>
      </template>
    </el-table-column>
        <el-table-column  align="center" label="项目类型">
      <template scope="scope">
        <span>{{getProjectTypeName(scope.row.projectType)}}</span>
      </template>
    </el-table-column>
        <el-table-column  align="center" label="客户名称">
      <template scope="scope">
        <span>{{scope.row.customer}}</span>
      </template>
    </el-table-column>
    <el-table-column  align="center" label="项目发起人">
      <template scope="scope">
        <span>{{getUserOptionsName(scope.row.sponsor)}}</span>
      </template>
    </el-table-column>
        <el-table-column  align="center" label="项目负责人">
      <template scope="scope">
        <span>{{getUserOptionsName(scope.row.projectManager)}}</span>
      </template>
    </el-table-column>
        <el-table-column  align="center" label="研发负责人">
      <template scope="scope">
        <span>{{getUserOptionsName(scope.row.devManager)}}</span>
      </template>
    </el-table-column>
        <el-table-column  align="center" label="立项时间">
      <template scope="scope">
        <span>{{scope.row.projectReportTime}}</span>
      </template>
    </el-table-column>
        <el-table-column  align="center" label="是否签订销售合同">
      <template scope="scope">
        <span>{{scope.row.salesContract}}</span>
      </template>
    </el-table-column>
        <el-table-column  align="center" label="项目开始日期">
      <template scope="scope">
        <span>{{scope.row.projectBeginTime}}</span>
      </template>
    </el-table-column>
        <el-table-column  align="center" label="项目结束日期">
      <template scope="scope">
        <span>{{scope.row.projectEndTime}}</span>
      </template>
    </el-table-column>

        <el-table-column  align="center" label="人员人工预算">
      <template scope="scope">
        <span>{{scope.row.humanBudget}}</span>
      </template>
    </el-table-column>


        <el-table-column fixed="right" align="center" label="操作" width="150">
        <template scope="scope">
            <el-button v-if="pmsProjectReportManager_btn_edit" size="small" type="success" @click="handleUpdate(scope.row)">编辑
            </el-button>
            <el-button v-if="pmsProjectReportManager_btn_del" size="small" type="danger" @click="handleDelete(scope.row)">删除
            </el-button>
        </template>
      </el-table-column>
    </el-table>

    <div v-show="!listLoading" class="pagination-container">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page.sync="listQuery.page" :page-sizes="[10,20,30, 50]" :page-size="listQuery.limit" layout="total, sizes, prev, pager, next, jumper" :total="total"> </el-pagination>
    </div>
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :fullscreen="false" width="89%" top="2vh" :close-on-press-escape="false" :close-on-click-modal="false">
      <el-form :model="form" :rules="rules" ref="form" label-width="145px" :inline="false" >
        <el-row>
          <el-col :span="12">
            <el-form-item label="项目名称" prop="projectName">
              <el-input v-model="form.projectName" placeholder="请输入项目名称"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="项目编号" prop="projectCode">
              <el-input v-model="form.projectCode" placeholder="请输入项目编号"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="项目类型" prop="projectType">
              <el-select class="filter-item" v-model="form.projectType" placeholder="请选择">
                <el-option v-for="item in  projectTypeList" :key="item.value" :label="item.name" :value="item.value"> </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="客户名称" prop="customer">
              <el-input v-model="form.customer" placeholder="请输入客户名称"></el-input>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="项目发起人" prop="sponsor">
              <el-select v-model="form.sponsor" filterable placeholder="请选择">
                <el-option
                  v-for="item in userOptions"
                  :key="item.id"
                  :label="item.name + ' [' + (item.attr1?item.attr1:'') + ']'"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="项目负责人" prop="projectManager">
              <el-select v-model="form.projectManager" filterable placeholder="请选择">
                <el-option
                  v-for="item in userOptions"
                  :key="item.id"
                  :label="item.name + ' [' + (item.attr1?item.attr1:'') + ']'"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="研发负责人" prop="devManager">
              <el-select v-model="form.devManager" filterable placeholder="请选择">
                <el-option
                  v-for="item in userOptions"
                  :key="item.id"
                  :label="item.name + ' [' + (item.attr1?item.attr1:'') + ']'"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="是否签订销售合同" prop="salesContract">
              <el-select v-model="form.salesContract" placeholder="请选择">
                <el-option key="N" label="未签订" value="N"></el-option>
                <el-option key="Y" label="已签订" value="Y"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="立项时间" prop="projectReportTime">
              <el-date-picker
                v-model="form.projectReportTime"
                align="right"
                type="date"
                placeholder="请输入立项时间"
                format="yyyy-MM-dd"
                value-format="yyyy-MM-dd">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="项目开始日期" prop="projectBeginTime">
              <el-date-picker
                v-model="form.projectBeginTime"
                align="right"
                type="date"
                placeholder="请输入项目开始日期"
                format="yyyy-MM-dd"
                value-format="yyyy-MM-dd">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="项目结束日期" prop="projectEndTime">
              <el-date-picker
                v-model="form.projectEndTime"
                align="right"
                type="date"
                placeholder="请输入项目结束日期"
                format="yyyy-MM-dd"
                value-format="yyyy-MM-dd">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="人员人工预算" prop="humanBudget">
              <el-input-number v-model="form.humanBudget" controls-position="right" :precision="2"  :step="100" :min="0"></el-input-number>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="直接投入预算" prop="directInputBudget">
              <el-input-number v-model="form.directInputBudget" controls-position="right" :precision="2"  :step="100" :min="0"></el-input-number>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="设计费用预算" prop="designBudget">
              <el-input-number v-model="form.designBudget" controls-position="right" :precision="2"  :step="100" :min="0"></el-input-number>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="其他费用预算" prop="otherBudget">
              <el-input-number v-model="form.otherBudget" controls-position="right" :precision="2"  :step="100" :min="0"></el-input-number>
            </el-form-item>
          </el-col>
        </el-row>

        <el-form-item label="项目立项内容" prop="projectContext">
          <el-input type="textarea" v-model="form.projectContext" placeholder="请输入项目立项内容" maxlength="30000" show-word-limit></el-input>
        </el-form-item>
        <el-form-item label="主要研究内容" prop="devContext">
          <el-input type="textarea" v-model="form.devContext" placeholder="请输入主要研究内容"  maxlength="30000" show-word-limit></el-input>
        </el-form-item>
        <el-form-item label="关键技术" prop="keyTechnology">
          <el-input type="textarea" v-model="form.keyTechnology" placeholder="请输入关键技术"  maxlength="30000" show-word-limit></el-input>
        </el-form-item>
        <el-form-item label="创新点" prop="innovation">
          <el-input type="textarea" v-model="form.innovation" placeholder="请输入创新点"  maxlength="30000" show-word-limit></el-input>
        </el-form-item>

        <el-form-item label="结论和建议" prop="peojectReportConclusion">
          <el-input type="textarea" v-model="form.peojectReportConclusion" placeholder="请输入结论和建议"  maxlength="30000" show-word-limit></el-input>
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
      putObj,
      getProjectTypeList,
      getUserOptions
  } from 'api/pms/project/report/pmsProjectReport/index';
  import { mapGetters } from 'vuex';
  export default {
    name: 'pmsProjectReport',
    data() {
      return {
        form: {
          projectCode: undefined,
          projectName: undefined,
          projectType: undefined,
          customer: undefined,
          sponsor: undefined,
          projectManager: undefined,
          devManager: undefined,
          projectReportTime: undefined,
          salesContract: undefined,
          projectBeginTime: undefined,
          projectEndTime: undefined,
          projectContext: undefined,
          devContext: undefined,
          keyTechnology: undefined,
          innovation: undefined,
          projectBeginPeriod: undefined,
          projectEndPeriod: undefined,
          humanBudget: 0,
          directInputBudget: 0,
          designBudget: 0,
          otherBudget: 0,
          peojectReportConclusion: undefined
        },
        rules: {
    projectCode: [
  {
    required: true,
    message: '请输入项目编号',
    trigger: 'blur'
  }
],   projectName: [
  {
    required: true,
    message: '请输入项目名称',
    trigger: 'blur'
  }
],   projectType: [
  {
    required: true,
    message: '请输入项目类型',
    trigger: 'blur'
  }
],   customer: [
  {
    required: true,
    message: '请输入客户名称',
    trigger: 'blur'
  }
],   sponsor: [
  {
    required: true,
    message: '请输入项目发起人',
    trigger: 'blur'
  }
],   projectManager: [
  {
    required: true,
    message: '请输入项目负责人',
    trigger: 'blur'
  }
],   devManager: [
  {
    required: true,
    message: '请输入研发负责人',
    trigger: 'blur'
  }
],   projectReportTime: [
  {
    required: true,
    message: '请输入立项时间',
    trigger: 'blur'
  }
],   salesContract: [
  {
    required: true,
    message: '请输入是否签订销售合同',
    trigger: 'blur'
  }
]
        },
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
        pmsProjectReportManager_btn_edit: false,
        pmsProjectReportManager_btn_del: false,
        pmsProjectReportManager_btn_add: false,
        textMap: {
          update: '编辑',
          create: '创建'
        },
        tableKey: 0,
        projectTypeList:[],
        userOptions:[]
      }
    },
    created() {
      this.getProjectTypeList();
      this.getUserOptions();
      this.getList();
      this.pmsProjectReportManager_btn_edit = this.elements['pmsProjectReportManager:btn_edit'];
      this.pmsProjectReportManager_btn_del = this.elements['pmsProjectReportManager:btn_del'];
      this.pmsProjectReportManager_btn_add = this.elements['pmsProjectReportManager:btn_add'];
    },
    computed: {
      ...mapGetters([
        'elements'
      ])
    },
    methods: {
      getProjectTypeList(){
        getProjectTypeList()
        .then(response => {
          this.projectTypeList = response.data.rows;
        })
      },
      getProjectTypeName(val){
        for(let i=0; i< this.projectTypeList.length; i++){
          if(this.projectTypeList[i].value == val){
            return this.projectTypeList[i].name
          }
        }
      },
      getUserOptions(){
        getUserOptions()
        .then(response => {
          this.userOptions = response.data.rows;
        })
      },
      getUserOptionsName(val){
        for(let i=0; i< this.userOptions.length; i++){
          if(this.userOptions[i].id == val){
            return this.userOptions[i].name
          }
        }
      },
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
                                                //项目编号
              projectCode : undefined,                                        //项目名称
              projectName : undefined,                                        //项目类型
              projectType : undefined,                                        //客户名称
              customer : undefined,                                        //项目发起人
              sponsor : undefined,                                        //项目负责人
              projectManager : undefined,                                        //研发负责人
              devManager : undefined,                                        //立项时间
              projectReportTime : undefined,                                        //是否签订销售合同
              salesContract : undefined,                                        //项目开始日期
              projectBeginTime : undefined,                                        //项目结束日期
              projectEndTime : undefined,                                        //项目立项内容
              projectContext : undefined,                                        //主要研究内容
              devContext : undefined,                                        //关键技术
              keyTechnology : undefined,                                        //创新点
              innovation : undefined,                                        //项目开始期间
              projectBeginPeriod : undefined,                                        //项目结束期间
              projectEndPeriod : undefined,                                        //人员人工预算
              humanBudget : 0,                                        //直接投入预算
              directInputBudget : 0,                                        //设计费用预算
              designBudget : 0,                                        //其他费用预算
              otherBudget : 0,                                        //结论和建议
              peojectReportConclusion : undefined                          };
      }
    }
  }
</script>

<style>
  .el-date-editor.el-input, .el-date-editor.el-input__inner {
    width: 100%;
  }
  .el-select {
    width: 100%;
  }
  .el-input-number {
    width: 100%;
  }
</style>
