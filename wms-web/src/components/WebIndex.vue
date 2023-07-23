<template>
  <el-container style="height: 100%; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246); height: 100%">
      <WebAside></WebAside>
    </el-aside>

    <el-container style="height: 100%">
      <el-header style="text-align: right; font-size: 12px">
        <WebHeader></WebHeader>
      </el-header>

      <el-main style="height: 100%">
          <WebMain></WebMain>
      </el-main>
    </el-container>
  </el-container>

</template>


<style>
.el-header {
  background-color: #B3C0D1;
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
  data() {

  }
};
</script>