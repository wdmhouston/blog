<template>
  <a-layout>
    <a-layout-content
        :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }">

      <a-row type="flex" justify="center" align="top" :gutter="[16,16]">
        <a-col >
          <a-card hoverable style="width: 350px">
            <template #cover>
              <a-image src="/image/case1.jpg" />
            </template>
            <template class="ant-card-actions" #actions>
              <router-link to="/case1"><CaretRightOutlined /></router-link>
              <ReadOutlined />
              <GithubOutlined />
              <MoreOutlined />
            </template>
            <a-card-meta title="Image Classification" description="Image Classification Example for fruit type predication.">
              <template #avatar>
                <a-avatar src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png" />
              </template>
            </a-card-meta>
          </a-card>
        </a-col>
      </a-row>

    </a-layout-content>
  </a-layout>
</template>


<script lang="ts">
import { defineComponent} from 'vue';
import {MoreOutlined, GithubOutlined, ReadOutlined, CaretRightOutlined} from "@ant-design/icons-vue";

export default defineComponent({
  name: 'showcase',
  components: {
      MoreOutlined,
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

