<template>
  <div class="about">
    <h1>This is an about page</h1>
    <a-button type="primary" :size="size" @click="test()">Primary</a-button>
  </div>
</template>


<script lang="ts">
import { defineComponent, onMounted, ref, reactive, toRef } from 'vue';
import axios from 'axios';
import { message } from 'ant-design-vue';
import {Tool} from "@/util/tool";
import TheWelcome from '@/components/the-welcome.vue';

export default defineComponent({
  name: 'about',
  components: {
  },
  setup() {

    const test = () => {
      window.location.href = "http://www.google.com";
    };

    return {
      test
    }
  }
});
</script>

