<template>
  <a-layout-header class="header" :style="{padding: '0px', textAlign: 'center',}" >
    <a-menu
        theme="dark"
        mode="horizontal"
        :style="{ lineHeight: '64px', width: '1024px'}"
    >
      <a-menu-item key="/"><router-link to="/">Home</router-link></a-menu-item>
      <a-menu-item key="/showcase"><router-link to="/showcase">Showcase</router-link></a-menu-item>
      <a-menu-item key="/about"><router-link to="/about">About Me</router-link></a-menu-item>
      <!--<a-menu-item key="/admin/ebook"><router-link to="/admin/ebook">Ebook Admin</router-link></a-menu-item>-->
      <!--<a-menu-item key="/admin/category"><router-link to="/admin/category">Category Admin</router-link></a-menu-item>-->
    </a-menu>
  </a-layout-header>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
export default defineComponent({
  name: 'the-header'
});
</script>