<template>
  <a-layout>
    <a-layout-content>
      <a-list item-layout="vertical" size="large" :grid="{gutter: 20, column:4}"  :data-source="ebooks">
      <template #renderItem="{ item }">
        <a-list-item key="item.name">
          <template #actions>
          <span v-for="{ type, text } in actions" :key="type">
            <component v-bind:is="type" style="margin-right: 8px" />
            {{ text }}
          </span>
          </template>
          <a-list-item-meta :description="item.description">
            <template #title>
              <a :href="item.href">{{ item.name }}</a>
            </template>
            <template #avatar><a-avatar :src="item.cover" /></template>
          </a-list-item-meta>
        </a-list-item>
      </template>
    </a-list>
    </a-layout-content>
  </a-layout>
</template>

<script lang="ts">
import { StarOutlined, LikeOutlined, MessageOutlined } from '@ant-design/icons-vue';
import { defineComponent, onMounted, ref } from 'vue';

import axios  from "axios";

export default defineComponent({
  name: 'Home',
  components: {
    StarOutlined,
    LikeOutlined,
    MessageOutlined,
  },
  setup(){
    console.log("setup");
    const ebooks = ref();  //responsible data of UI element

    onMounted(()=>{
      console.log("onMounted");
      axios.get("/ebook/list")
          .then(function(response){
            const data = response.data;
            ebooks.value = data.content;
          });
    });

    const pagination = {
      onChange: (page: number) => {
        console.log(page);
      },
      pageSize: 3,
    };
    const actions: Record<string, string>[] = [
      { type: 'StarOutlined', text: '156' },
      { type: 'LikeOutlined', text: '156' },
      { type: 'MessageOutlined', text: '2' },
    ];

    return {
      ebooks,
      pagination,
      actions,
    };
  },
});
</script>

<style scoped>
  .ant-avatar {
    width: 50px;
    height: 50px;
    line-height: 50px;
    border-radius: 8%;
    margin: 5px 0;
  }
</style>