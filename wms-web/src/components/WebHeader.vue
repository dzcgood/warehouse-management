<template>
  <div style="display: flex; line-height: 60px">
    <div>
      <i :class="icon_name" style="font-size: 20px;line-height: 100%; cursor: pointer" @click="collapse"></i>
    </div>
    <div style="flex: 1;text-align: center;font-size: 34px">
      <span>欢迎来到仓库管理系统</span>
    </div>
    <span style="font-size: 15px">{{ user.name }}</span>
    <el-dropdown>
      <i class="el-icon-arrow-down" style="margin-left: 5px; font-size: 15px"></i>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item @click.native="toUser">个人中心</el-dropdown-item>
        <el-dropdown-item @click.native="logOut">退出登录</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>

  </div>
</template>

<script>
export default {
  name: "WebHeader",

  methods: {
    toUser() {
      this.$router.push("/HomePage")
    },

    logOut() {
      console.log("logOut....")
      this.$confirm('您确定要退出登录吗？', '提示', {
        confirmButtonText: '确定',
        type: 'warning',
        center: true
      })
          .then(() => {
            this.$router.push("/")
            sessionStorage.clear()
            this.$message({
              type: 'success',
              message: '退出登录成功'
            })
          })
          .catch(() => {
            // 什么都不做
          })
    },

    collapse() {
      if (this.icon_name === 'el-icon-s-fold') {
        this.icon_name = 'el-icon-s-unfold'
      } else {
        this.icon_name = 'el-icon-s-fold'
      }
      this.$emit('doCollapse')
    }
  },

  created() {
    this.$router.push('/HomePage')
  },

  data() {
    return {
      icon_name: 'el-icon-s-fold',
      user: JSON.parse(sessionStorage.getItem('CurUser'))
    }
  }


}
</script>