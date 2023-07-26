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
      <el-button type="primary" style="margin-left: 5px" @click="add">新增</el-button>
      <el-button type="primary" style="margin-left: 5px" @click="inGoods">入库</el-button>
      <el-button type="primary" style="margin-left: 5px" @click="outGoods">出库</el-button>

    </div>
    <!--    table-->
    <el-table :data="tableData"
              :header-cell-style="{background: '#F2F5FC', color: '#555555'}"
              border
              highlight-current-row
              @current-change="selectCurrentChange"
    >
      <el-table-column prop="id" label="ID" width="60">
      </el-table-column>
      <el-table-column prop="name" label="物品名" width="180">
      </el-table-column>
      <el-table-column prop="storageId" label="仓库" width="180" :formatter="formatStorage">
      </el-table-column>
      <el-table-column prop="goodsTypeId" label="分类" width="180" :formatter="formatType">
      </el-table-column>
      <el-table-column prop="count" label="数量" width="180">
      </el-table-column>
      <el-table-column prop="remark" label="备注">
      </el-table-column>

      <el-table-column prop="operate" label="操作">
        <template slot-scope="scope">
          <el-button type="success" size="small" @click="mod(scope.row)" style="margin-right: 5px">编辑</el-button>
          <el-popconfirm title="确定删除吗？" @confirm="del(scope.row.id)">
            <el-button slot="reference" type="danger" size="small">删除</el-button>
          </el-popconfirm>
        </template>
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
    <!--    新增界面-->
    <el-dialog
        title="提示"
        :visible.sync="centerDialogVisible"
        width="30%"
        center>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="物品名" prop="name">
          <el-col :span="20">
            <el-input v-model="form.name"></el-input>
          </el-col>
        </el-form-item>

        <el-form-item label="仓库" prop="storageId">
          <el-col :span="20">
            <el-select v-model="form.storageId" placeholder="请选择仓库" style="margin-left: 5px">
              <el-option
                  v-for="item in storageData"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
              </el-option>
            </el-select>
          </el-col>


        </el-form-item>
        <el-form-item label="分类" prop="goodsTypeId">
          <el-col :span="20">
            <el-select v-model="form.goodsTypeId" placeholder="请选择分类" style="margin-left: 5px">
              <el-option
                  v-for="item in goodsTypeData"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
              </el-option>
            </el-select>
          </el-col>
        </el-form-item>
        <el-form-item label="数量" prop="count">
          <el-col :span="20">
            <el-input v-model="form.count"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-col :span="20">
            <el-input v-model="form.remark" type="textarea"></el-input>
          </el-col>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="centerDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="save">确 定</el-button>
  </span>
    </el-dialog>
    <!--    入库界面-->
    <el-dialog
        title="出入库"
        :visible.sync="inDialogVisible"
        width="30%"
        center>

        <el-dialog
            width="55%"
            title="选择用户"
            :visible.sync="innerVisible"
            append-to-body>
          <selectUser @doSelectUser="doSelectUser"></selectUser>
          <div slot="footer" class="dialog-footer">
            <el-button @click="inDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="confirmUser">确定</el-button>
          </div>
        </el-dialog>


      <el-form ref="form1" :model="form1" :rules="rules1" label-width="80px">
        <el-form-item label="物品名">
          <el-col :span="20">
            <el-input v-model="form1.goodsName" readonly></el-input>
          </el-col>
        </el-form-item>

        <el-form-item label="数量" prop="count">
          <el-col :span="20">
            <el-input v-model="form1.count"></el-input>
          </el-col>
        </el-form-item>

        <el-form-item label="申请人" prop="count">
          <el-col :span="20">
            <el-input v-model="tempUser.name" @click.native="selectUser"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-col :span="20">
            <el-input v-model="form1.remark" type="textarea"> </el-input>
          </el-col>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="inDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="doInGoods">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>

<script>
import SelectUser from "@/components/user/SelectUser";
export default {
  name: "GoodsManage",
  components: {SelectUser},
  data() {
    let checkCount = (rule, value, callback) => {
      if (value > 9999) {
        callback(new Error('数量输入过大'));
      } else {
        callback();
      }
    };
    return {
      tempUser: {},
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
      innerVisible: false,
      centerDialogVisible: false,
      inDialogVisible: false,
      currentRow: {},
      form: {
        id: '',
        name: '',
        storageId: '',
        goodsTypeId: '',
        count: 0,
        remark: '',
      },
      form1: {
        goodsId: '',
        userId: 13,
        adminId: '',
        goodsName: '',
        count: '',
        remark: '',
        action: 1
      },
      rules: {
        name: [
          {required: true, message: '请输入物品名', trigger: 'blur'},
        ],
        goodsTypeId: [
          {required: true, message: '请选择分类', trigger: 'blur'},
        ],
        storageId: [
          {required: true, message: '请选择仓库', trigger: 'blur'},
        ],
        count: [
          {required: true, message: '请输入数量', trigger: 'blur'},
          {pattern: /^[1-9][0-9*]{1,4}$/, message: '数量必须为正整数', trigger: 'blur'},
          {validator: checkCount, trigger: 'blur'}
        ]
      },
      rules1: {

      }
    }
  },
  methods: {
    doSelectUser(val) {
      console.log(val)
      this.tempUser = val
    },
    confirmUser() {
      this.form1.userId = this.tempUser.id
      this.innerVisible = false
    },
    selectUser() {
      this.innerVisible = true
    },
    selectCurrentChange(val) {
      this.currentRow = val
    },
    inGoods() {
      if(!this.currentRow.id) {
        alert("请选择记录");
        return;
      }
      this.inDialogVisible = true
      // 异步，要先等表单创建出来再刷新值
      this.$nextTick(() => {
        this.resetInForm()
      })
      this.form1.goodsName = this.currentRow.name
      this.form1.goodsId = this.currentRow.id
      this.form1.adminId = this.user.id
      this.form1.action = 1
    },
    outGoods() {
      if(!this.currentRow.id) {
        alert("请选择记录");
        return;
      }
      this.inDialogVisible = true
      // 异步，要先等表单创建出来再刷新值
      this.$nextTick(() => {
        this.resetInForm()
      })
      this.form1.goodsName = this.currentRow.name
      this.form1.goodsId = this.currentRow.id
      this.form1.adminId = this.user.id
      this.form1.action = 2
    },
    formatStorage(row) {
      let temp = this.storageData.find(item => {

        return item.id == row.storageId
      })
      return temp.name
    },
    formatType(row) {
      let temp = this.goodsTypeData.find(item => {

        return item.id == row.goodsTypeId
      })
      return temp.name
    },
    loadPost() {
      this.$axios.post(this.$httpUrl + '/goods/listPage', {
        pageSize: this.pageSize,
        pageNum: this.pageNum,
        param: {
          name: this.name,
          storageId: this.storageId,
          goodsTypeId: this.goodsTypeId
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
    add() {
      this.centerDialogVisible = true
      // 异步，要先等表单创建出来再刷新值
      this.$nextTick(() => {
        this.resetForm()
        this.form.id = ''
      })
    },
    mod(row) {
      // 展示表单
      this.centerDialogVisible = true
      this.$nextTick(() => {
        // 赋值到表单
        this.form.id = row.id
        this.form.name = row.name
        this.form.storage = row.storage
        this.form.goodsType = row.goodsType
        this.form.count = row.count
        this.form.remark = row.remark
      })
    },
    del(id) {
      this.$axios.get(this.$httpUrl + '/goods/delete?id=' + id)
          .then(res => res.data)
          .then(res => {
            console.log(res)
            if (res.code == 200) {
              this.$message({
                message: '操作成功',
                type: 'success'
              });
              this.loadPost()
            } else {
              this.$message({
                message: '操作失败',
                type: 'error'
              });
            }
          })
    },
    resetForm() {
      this.$refs.form.resetFields();
    },
    resetInForm() {
      this.$refs.form1.resetFields();
    },
    doSave() {
      this.$axios.post(this.$httpUrl + '/goods/save', this.form)
          .then(res => res.data)
          .then(res => {
            console.log(res)
            if (res.code == 200) {
              this.$message({
                message: '操作成功',
                type: 'success'
              });
              this.centerDialogVisible = false
              this.loadPost()
            } else {
              this.$message({
                message: '操作失败',
                type: 'error'
              });
            }
          })
    },

    doMod() {
      this.$axios.post(this.$httpUrl + '/goods/mod', this.form)
          .then(res => res.data)
          .then(res => {
            console.log(res)
            if (res.code == 200) {
              this.$message({
                message: '操作成功',
                type: 'success'
              });
              this.centerDialogVisible = false
              this.loadPost()
            } else {
              this.$message({
                message: '操作失败',
                type: 'error'
              });
            }
          })
    },
    save() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          if (this.form.id) {
            this.doMod();
          } else {
            this.doSave();
          }

        } else {
          console.log('error submit!!');
          return false;
        }
      });

    },
    doInGoods() {
      this.$axios.post(this.$httpUrl + '/record/save', this.form1)
          .then(res => res.data)
          .then(res => {
            console.log(res)
            if (res.code == 200) {
              this.$message({
                message: '操作成功',
                type: 'success'
              });
              this.inDialogVisible = false
              this.loadPost()
            } else {
              this.$message({
                message: '操作失败',
                type: 'error'
              });
            }
          })
    },
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
    }
  },
  beforeMount() {
    this.loadStorage()
    this.loadGoodsType()
    this.loadPost()
  }
}
</script>