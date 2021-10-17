<template>
  <a-layout>
    <a-layout-content
        :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }">

      <a-row justify="center" align="top">
          <a-card hoverable style="width: 350px;margin: 10px; ">
            <template #cover>
              <a-image src="/image/case1.jpg" />
            </template>
            <template class="ant-card-actions" #actions>
              <router-link to="/case1"><CaretRightOutlined /></router-link>
              <a href="https://sc24613.readthedocs.io/en/main/" target="_blank"> <ReadOutlined /> </a>
              <a href="https://github.com/wdmhouston/SC24.6.13" target="_blank"> <GithubOutlined /> </a>
            </template>
            <a-card-meta title="Image Classification" description="Image Classification Example for fruit predication.">
              <template #avatar>
                <a-avatar src="image/tf.jpg" />
              </template>
            </a-card-meta>
          </a-card>

      </a-row>

    </a-layout-content>
  </a-layout>
</template>


<script lang="ts">
import {defineComponent} from 'vue';
import {GithubOutlined, ReadOutlined, CaretRightOutlined} from "@ant-design/icons-vue";

export default defineComponent({
  name: 'showcase',
  components: {
      GithubOutlined,
      ReadOutlined,
      CaretRightOutlined
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

