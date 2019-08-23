<template>
<div class="app-container calendar-list-container">
    <el-row>
        <el-col :span="10">
            <el-card class="box-card">
                <span>字典类型</span>
                <base-dictType ref="CBaseDictType" :currentId.sync="currentId"></base-dictType>
            </el-card>
        </el-col>
        <el-col :span="14">
            <el-card class="box-card">
                <span>字典值</span>
                <base-dict ref="CBaseDict" :currentId="currentId"></base-dict>
            </el-card>
        </el-col>
    </el-row>
</div>
</template>

<script>
import {
    mapGetters
} from 'vuex';

export default {
    name: 'baseDictManager',
    components: {
        'base-dictType': () => import('./components/baseDictType/index'),
        'base-dict': () => import('./components/baseDict/index')
    },
    data() {
        return {
            currentId: -1
        }
    }
  }
</script>
