<template>
  <el-menu
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
      default-active="/Home"
      router="true"
      style="height: 100vh; overflow: hidden;">
    <el-menu-item index="/Home">
      <i class="el-icon-s-home"></i>
      <span>首页</span>
    </el-menu-item>

    <el-menu-item index="/One">
      <i class="el-icon-s-flag"></i>
      <span>导航一</span>
    </el-menu-item>

    <el-menu-item index="/Two">
      <i class="el-icon-s-opportunity"></i>
      <span>导航二</span>
    </el-menu-item>
  </el-menu>
</template>

<script>
  export default {
    name: "WebAside",

  }
</script>