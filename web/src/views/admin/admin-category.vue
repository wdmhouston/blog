<template>
  <a-layout>
    <a-layout-content
        :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >
      <p>
        <a-form layout="inline" :model="param">
          <a-form-item>
            <a-input v-model:value="param.name" placeholder="名称">
            </a-input>
          </a-form-item>
          <a-form-item>
            <a-button type="primary" @click="handleQuery()">
              查询
            </a-button>
          </a-form-item>
          <a-form-item>
            <a-button type="primary" @click="add()">
              新增
            </a-button>
          </a-form-item>
        </a-form>
      </p>
      <a-table
          :columns="columns"
          :row-key="record => record.id"
          :data-source="categorys"
          :loading="loading"
          :pagination="false"
      >
        <template #cover="{ text: cover }">
          <img v-if="cover" :src="cover" alt="avatar" />
        </template>
        <template v-slot:category="">
          <span>Category</span>
        </template>
        <template v-slot:action="{ text, record }">
          <a-space size="small">

            <a-button type="primary">
              文档管理
            </a-button>
            <a-button type="primary" @click="edit(record)">
              编辑
            </a-button>
            <a-popconfirm
                title="删除后不可恢复，确认删除?"
                ok-text="是"
                cancel-text="否"
                @confirm="handleDelete(record.id)"
            >
              <a-button type="primary" danger>
                删除
              </a-button>
            </a-popconfirm>
          </a-space>
        </template>
      </a-table>
    </a-layout-content>
  </a-layout>

  <a-modal
      title="Category表单"
      v-model:visible="modalVisible"
      :confirm-loading="modalLoading"
      @ok="handleModalOk"
  >
    <a-form :model="category" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
      <a-form-item label="Name">
        <a-input v-model:value="category.name" />
      </a-form-item>
      <a-form-item label="Parent Category">
        <a-input v-model:value="category.parent" />
      </a-form-item>
      <a-form-item label="Sort">
        <a-input v-model:value="category.sort" />
      </a-form-item>

    </a-form>
  </a-modal>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref } from 'vue';
import axios from 'axios';
import { message } from 'ant-design-vue';
import {Tool} from "@/util/tool";

export default defineComponent({
  name: 'AdminCategory',
  setup() {
    const param = ref();
    param.value = {};
    const categorys = ref();

    const loading = ref(false);
    const columns = [
      {
        title: 'Name',
        dataIndex: 'name'
      },
      {
        title: 'Parent Category',
        key: 'parent',
        dataIndex: 'parent'
      },
      {
        title: 'Sort',
        dataIndex: 'sort'
      },
      {
        title: 'Action',
        key: 'action',
        slots: { customRender: 'action' }
      }

    ];
    // -------- 表单 ---------
    /**
     * 数组，[100, 101]对应：前端开发 / Vue
     */
    const category = ref({});
    const modalVisible = ref(false);
    const modalLoading = ref(false);
    const handleModalOk = () => {
      console.log("call handleModelOk--------------------->");
      modalLoading.value = true;
      //setTimeout(()=>{
      //    modalVisible.value = false;
      //    modalLoading.value = false;
      //  },2000);
      axios.post("/category/save", category.value).then((response) => {
        modalLoading.value = false;
        const data = response.data;
        if (data.success) {
          modalVisible.value = false;
          modalLoading.value = false;
          // 重新加载列表
          handleQuery();
        } else {
          message.error(data.message);
        }
      });
      console.log("call handleModelOk done--------------------->");
    };

    /**
     * 编辑
     */
    const edit = (record: any) => {
      modalVisible.value = true;
      //category.value = record;
      category.value = Tool.copy(record);
    };

    /**
     * 新增
     */
    const add = () => {
      modalVisible.value = true;
      category.value = {};
    };

    const handleDelete = (id: number) => {
      axios.delete("/category/delete/" + id).then((response) => {
        const data = response.data; // data = commonResp
        if (data.success) {
          // 重新加载列表
          handleQuery();
        } else {
          message.error(data.message);
        }
      });
    };

    const level1 =  ref();

    const handleQuery = () =>{
      console.log("handleQuery calling----------------------->");
      loading.value = true;
      axios.get("/category/all").then((response)=>{
        console.log("handleQuery calling returned----------------------->");
        console.log(response);
        loading.value = false;
        const data = response.data;
        if (data.success) {
          categorys.value = data.content;
        }else{
          message.error(data.message);
        }

      });
    };

    onMounted(() => {
      //handleQueryCategory();
      console.log("onMount  ");
      handleQuery();
    });

    return {
      param,
      categorys,
      columns,
      loading,
      handleQuery,

      edit,
      add,

      category,
      modalVisible,
      modalLoading,
      handleModalOk,
      level1,

      handleDelete
    }
  }
});
</script>

<style scoped>
img {
  width: 50px;
  height: 50px;
}
</style>
