<template>
  <el-menu
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
      default-active="/HomePage"
      :collapse="isCollapse"
      :collapse-transition=false
      router
      style="height: 100vh; overflow: hidden;">
    <el-menu-item index="/HomePage">
      <i class="el-icon-s-home"></i>
      <span slot="title">首页</span>
    </el-menu-item>

    <el-menu-item :index="'/' + item.menuClick" v-for="(item, i) in menu" :key="i">
      <i :class="item.menuIcon"></i>
      <span slot="title">{{item.menuName}}</span>
    </el-menu-item>




  </el-menu>
</template>

<script>
  export default {
    name: "WebAside",

    data() {
      return {
        menu: [
          {
            menuClick: 'AdminManage',
            menuName: '管理员管理',
            menuIcon: 'el-icon-s-custom'
          },
          {
            menuClick: 'UserManage',
            menuName: '用户管理',
            menuIcon: 'el-icon-user'
          }
        ]
      }
    },
    props: {
      isCollapse: Boolean
    }
  }
</script>