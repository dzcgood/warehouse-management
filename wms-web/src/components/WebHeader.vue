<template>
  <div style="display: flex; line-height: 60px">
    <div>
      <i :class="icon_name" style="font-size: 20px;line-height: 100%; cursor: pointer" @click="collapse"></i>
    </div>
    <div style="flex: 1;text-align: center;font-size: 34px">
      <span>欢迎来到仓库管理系统</span>
    </div>
    <span style="font-size: 15px">王小虎</span>
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
        console.log("toUser....")
      },

      logOut() {
        console.log("logOut....")
      },

      collapse() {
        if(this.icon_name === 'el-icon-s-fold') {
          this.icon_name = 'el-icon-s-unfold'
        } else {
          this.icon_name = 'el-icon-s-fold'
        }
        this.$emit('doCollapse')
      }
    },

    data() {
      return {
        icon_name: 'el-icon-s-fold'
      }
    }


  }
</script>