<template>
  <el-container style="height: 100%; border: 1px solid #eee">
    <el-aside :width="aside_width" style="background-color: rgb(238, 241, 246); height: 100%; margin-left: -1px">
      <WebAside :isCollapse="isCollapse"></WebAside>
    </el-aside>

    <el-container style="height: 100%">
      <el-header style="text-align: right; font-size: 12px; height: 100%; border-bottom: darkgray 1px solid">
        <WebHeader @doCollapse="doCollapse"></WebHeader>
      </el-header>

      <el-main style="height: 100%">
          <WebMain></WebMain>
      </el-main>
    </el-container>
  </el-container>

</template>


<style>
.el-header {
  color: #333;
  line-height: 60px;
}

.el-main {
  padding: 5px;
}

.el-aside {
  color: #333;
}
</style>

<script>
import WebAside from "@/components/WebAside";
import WebHeader from "@/components/WebHeader";
import WebMain from "@/components/WebMain";
export default {
  name: "WebIndex",
  components: {WebMain, WebHeader, WebAside},
  methods: {
    doCollapse() {
      this.isCollapse = !this.isCollapse
      if(!this.isCollapse) { // 展开
        this.aside_width = '200px'
      } else {
        this.aside_width = '64px'
      }
    }
  },
  data() {
    return {
      isCollapse: false,
      aside_width: '200px'
    }
  }
};
</script>