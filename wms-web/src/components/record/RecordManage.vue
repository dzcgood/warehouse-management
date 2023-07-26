<template>
  <div>
    <!--    查询-->
    <div style="margin-bottom: 5px">
      <el-input v-model="name" placeholder="请输入物品名" suffix-icon="el-icon-search" style="width: 200px"
                @keyup.enter.native="loadPost"></el-input>
      <el-select v-model="storageId" placeholder="请选择仓库" style="margin-left: 5px">
        <el-option
            v-for="item in storageData"
            :key="item.id"
            :label="item.name"
            :value="item.id">
        </el-option>
      </el-select>
      <el-select v-model="goodsTypeId" placeholder="请选择分类" style="margin-left: 5px">
        <el-option
            v-for="item in goodsTypeData"
            :key="item.id"
            :label="item.name"
            :value="item.id">
        </el-option>
      </el-select>
      <el-button type="primary" style="margin-left: 5px" @click="loadPost">查询</el-button>
      <el-button type="success" style="margin-left: 5px" @click="resetParam">重置</el-button>

    </div>
    <!--    table-->
    <el-table :data="tableData"
              :header-cell-style="{background: '#F2F5FC', color: '#555555'}"
              border
    >
      <el-table-column prop="id" label="ID" width="60">
      </el-table-column>
      <el-table-column prop="goodsName" label="物品名" width="180">
      </el-table-column>
      <el-table-column prop="storageName" label="仓库" width="180">
      </el-table-column>
      <el-table-column prop="goodsTypeName" label="分类" width="180">
      </el-table-column>
      <el-table-column prop="count" label="数量" width="180">
      </el-table-column>
      <el-table-column prop="createTime" label="操作时间" width="180">
      </el-table-column>
      <el-table-column prop="adminName" label="操作人" width="180">
      </el-table-column>
      <el-table-column prop="userName" label="申请人" width="180">
      </el-table-column>
      <el-table-column prop="remark" label="备注">
      </el-table-column>

    </el-table>
    <!--    分页-->
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[5, 10, 20, 30]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
    </el-pagination>

  </div>
</template>

<script>
export default {
  name: "RecordManage",
  data() {

    return {
      user: JSON.parse(sessionStorage.getItem("CurUser")),
      tableData: [],
      pageSize: 10,
      pageNum: 1,
      total: 0,
      name: '',
      storageData: [],
      goodsTypeData: [],
      storageId: '',
      goodsTypeId: '',
      centerDialogVisible: false,
      form: {
        id: '',
        name: '',
        storageId: '',
        goodsTypeId: '',
        count: 0,
        remark: '',
      },
    }
  },
  methods: {
    loadStorage() {
      this.$axios.get(this.$httpUrl + '/storage/list')
          .then(res => res.data)
          .then(res => {
            if (res.code == 200) {
              this.storageData = res.data
            } else {
              this.$message({
                message: '操作失败',
                type: 'error'
              });
            }
          })
    },
    loadGoodsType() {
      this.$axios.get(this.$httpUrl + '/goodsType/list')
          .then(res => res.data)
          .then(res => {
            if (res.code == 200) {
              this.goodsTypeData = res.data
            } else {
              this.$message({
                message: '操作失败',
                type: 'error'
              });
            }
          })
    },

    loadPost() {
      this.$axios.post(this.$httpUrl + '/record/listPage', {
        pageSize: this.pageSize,
        pageNum: this.pageNum,
        param: {
          name: this.name,
          storageId: this.storageId,
          goodsTypeId: this.goodsTypeId,
          roleId: this.user.roleId,
          userId: this.user.id
        }
      })
          .then(res => res.data)
          .then(res => {
            console.log(res)
            if (res.code == 200) {
              this.tableData = res.data
              this.total = res.total
            } else {
              alert('获取失败')
            }
          })
    },
    handleSizeChange(val) {
      this.pageSize = val
      this.pageNum = 1
      this.loadPost()
    },
    handleCurrentChange(val) {
      this.pageNum = val
      this.loadPost()
    },
    resetParam() {
      this.name = ''
      this.storageId = ''
      this.goodsTypeId = ''
    },

    resetForm() {
      this.$refs.form.resetFields();
    },

  },
  beforeMount() {
    this.loadStorage()
    this.loadGoodsType()
    this.loadPost()
  }
}
</script>