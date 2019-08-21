<template>
  <div>
    <el-menu class="navbar" mode="horizontal">

      <hamburger class="hamburger-container" :toggleClick="toggleSideBar" :isActive="sidebar.opened"></hamburger>
      <levelbar></levelbar>
      <tabs-view></tabs-view>
      <error-log v-if="log.length>0" class="errLog-container" :logsList="log"></error-log>
      <!--<screenfull class='screenfull'></screenfull>-->


      <el-dropdown class="avatar-container" trigger="click">
        <div class="avatar-wrapper"> <span>{{name}}</span><i class="el-icon-caret-bottom"></i> </div>
        <el-dropdown-menu class="user-dropdown" slot="dropdown">
          <router-link class='inlineBlock' to="/">
            <el-dropdown-item> 首页 </el-dropdown-item>
          </router-link>
          <el-dropdown-item divided><span @click="dialogFormVisible=true" style="display:block;">修改密码</span></el-dropdown-item>
          <el-dropdown-item divided><span @click="logout" style="display:block;">退出登录</span></el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </el-menu>

    <el-dialog title="修改密码" :visible.sync="dialogFormVisible">
      <el-form :model="form" :rules="rules" ref="form" label-width="100px">
        <el-form-item label="原密码" placeholder="请输入原密码" prop="passwordOld">
          <el-input type="password" v-model="form.passwordOld"></el-input>
        </el-form-item>
        <el-form-item label="新密码" placeholder="请输入新密码" prop="passwordNew">
          <el-input type="password" v-model="form.passwordNew"></el-input>
        </el-form-item>
        <el-form-item label="确认新密码" placeholder="请确认新密码" prop="passwordNew2">
          <el-input type="password" v-model="form.passwordNew2"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="updatePassword">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>


<script>
import {
  mapGetters
} from 'vuex';
import Levelbar from './Levelbar';
import TabsView from './TabsView';
import Hamburger from 'components/Hamburger';
import ErrorLog from 'components/ErrLog';
import errLogStore from 'store/errLog';
import {
  updatePassword
} from 'api/admin/user/index';
import md5 from 'js-md5';


export default {
  components: {
    Levelbar,
    TabsView,
    Hamburger,
    ErrorLog
    // ,Screenfull
  },
  data() {
    return {
      log: errLogStore.state.errLog,
      dialogFormVisible: false,
      name: this.$store.getters.name,
      form: {
        passwordOld: undefined,
        passwordNew: undefined,
        passwordNew2: undefined
      },
      rules: {
        passwordOld: [
          {
            required: true,
            message: '请输入原密码',
            trigger: 'blur'
          },
          {
            min: 5,
            max: 20,
            message: '长度在 5 到 20 个字符',
            trigger: 'blur'
          }
        ],
        passwordNew: [
          {
            required: true,
            message: '请输入新密码',
            trigger: 'blur'
          },
          {
            min: 5,
            max: 20,
            message: '长度在 5 到 20 个字符',
            trigger: 'blur'
          }
        ],
        passwordNew2: [
          {
            required: true,
            message: '请确认新密码',
            trigger: 'blur'
          },
          {
            min: 5,
            max: 20,
            message: '长度在 5 到 20 个字符',
            trigger: 'blur'
          }
        ]
      },
    }
  },
  computed: { ...mapGetters([
      'sidebar',
      'name',
      'avatar'
    ])
  },
  methods: {
    toggleSideBar() {
      this.$store.dispatch('ToggleSideBar')
    },
    logout() {
      this.$store.dispatch('FedLogOut')
        .then(() => {
          location.reload(); // 为了重新实例化vue-router对象 避免bug
        });
    },
    cancel() {
      this.dialogFormVisible = false;
      this.$refs['form'].resetFields();
    },
    updatePassword(){
      let _this = this;
      let token = _this.$store.getters.token
      _this.$refs['form'].validate(valid => {
        if (valid) {
          // this.dialogFormVisible = false;
          updatePassword(token, md5(_this.form.passwordOld), md5(_this.form.passwordNew), md5(_this.form.passwordNew2)).then(() => {
            // this.dialogFormVisible = false;
            // this.getList();
            _this.$notify({
              title: '成功',
              message: '修改成功',
              type: 'success',
              duration: 2000
            });
            setTimeout(function(){
              _this.cancel();
              _this.dialogFormVisible = false;
              _this.logout();
            },2300)
          });
        } else {
          return false;
        }
      });

    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.navbar {
    height: 50px;
    line-height: 50px;
    border-radius: 0 !important;
    .hamburger-container {
        line-height: 58px;
        height: 50px;
        float: left;
        padding: 0 10px;
    }
    .errLog-container {
        display: inline-block;
        position: absolute;
        right: 150px;
    }
    .screenfull {
        position: absolute;
        right: 90px;
        top: 16px;
        color: red;
    }
    .avatar-container {
        height: 50px;
        display: inline-block;
        position: absolute;
        right: 35px;
        .avatar-wrapper {
            cursor: pointer;
            margin-top: 5px;
            position: relative;
            .user-avatar {
                width: 40px;
                height: 40px;
                border-radius: 10px;
            }
            .el-icon-caret-bottom {
                position: absolute;
                right: -20px;
                top: 25px;
                font-size: 12px;
            }
        }
    }
}
</style>
