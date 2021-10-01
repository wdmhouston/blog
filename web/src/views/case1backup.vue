<template>
  <a-layout>
    <a-layout-content :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }">
      <a-typography-title :level="3">Fruit Prediction with image classification using deep learning</a-typography-title>
      <a-tabs v-model:activeKey="activeKey">
        <a-tab-pane key="1">
          <template #tab>
        <span>
          Intro
        </span>
          </template>

          <a-image src="/image/case1.jpg" />
          <p>
            Fruit Identification Web Api is a tool for predicting fruit type from image using deep learning(TensorFlow and Keras).
          </p>
          <p>
            This tool provdes a standard restful api for prediction. It can also be used to train with new images.
          </p>
          <a-divider orientation="left">Documentation</a-divider>
          <p>
            Online docs are available online: <a href="https://sc24613.readthedocs.io/en/main/" target="_blank">link</a>
          </p>
          <a-divider orientation="left">Source Code</a-divider>
          <p>
            Source Code is shared through github: <a href="https://github.com/wdmhouston/SC24.6.13" target="_blank">link</a>
          </p>
        </a-tab-pane>
        <a-tab-pane key="2">
          <template #tab>
             <span>Try</span>
          </template>
          <div class="clearfix">
            <a-upload
                action="http://127.0.0.1:8880/file/upload"
                list-type="picture-card"
                :multiple="true"
                v-model:file-list="fileList"
                @preview="handlePreview"
                @change="handleChange"
                >
              <div v-if="fileList.length < 8">
                <plus-outlined />
                <div class="ant-upload-text">Upload</div>
              </div>
            </a-upload>
            <a-modal :visible="previewVisible" :footer="null" @cancel="handleCancel">
              <img alt="example" style="width: 100%" :src="previewImage" />
            </a-modal>
          </div>
          </a-tab-pane>
        <a-tab-pane key="3">
          <template #tab>
        <span>
          Predict History
        </span>
          </template>
          <a-timeline>
            <a-timeline-item color="green">Create a services site 2015-09-01</a-timeline-item>
            <a-timeline-item color="green">Create a services site 2015-09-01</a-timeline-item>
            <a-timeline-item color="red">
              <p>Solve initial network problems 1</p>
              <p>Solve initial network problems 2</p>
              <p>Solve initial network problems 3 2015-09-01</p>
            </a-timeline-item>
            <a-timeline-item>
              <p>Technical testing 1</p>
              <p>Technical testing 2</p>
              <p>Technical testing 3 2015-09-01</p>
            </a-timeline-item>
            <a-timeline-item color="gray">
              <p>Technical testing 1</p>
              <p>Technical testing 2</p>
              <p>Technical testing 3 2015-09-01</p>
            </a-timeline-item>
            <a-timeline-item color="gray">
              <p>Technical testing 1</p>
              <p>Technical testing 2</p>
              <p>Technical testing 3 2015-09-01</p>
            </a-timeline-item>
          </a-timeline>
        </a-tab-pane>
      </a-tabs>
    </a-layout-content>
  </a-layout>
</template>
<script lang="ts">
import { PlusOutlined } from '@ant-design/icons-vue';
import { defineComponent, ref } from 'vue';
import { message } from 'ant-design-vue';

function getBase64(file: File) {
  return new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => resolve(reader.result);
    reader.onerror = error => reject(error);
  });
}

interface FileItem {
  uid: string;
  name?: string;
  status?: string;
  response?: string;
  percent?: number;
  url?: string;
  preview?: string;
  originFileObj?: any;
}

interface FileInfo {
  file: FileItem;
  fileList: FileItem[];
}

export default defineComponent({
  components: {
    PlusOutlined,
  },
  setup() {
    const previewVisible = ref<boolean>(false);
    const previewImage = ref<string | undefined>('');

    const fileList = ref<FileItem[]>([
    ]);

    const handleCancel = () => {
      previewVisible.value = false;
    };
    const handlePreview = async (file: FileItem) => {
      if (!file.url && !file.preview) {
        file.preview = (await getBase64(file.originFileObj)) as string;
        console.log('handlePreview called 2');
      }
      previewImage.value = file.url || file.preview;
      previewVisible.value = true;
      console.log('handlePreview called  ');
    };
    const handleChange = ({ fileList: newFileList }: FileInfo) => {
      console.log('handleChange called');
      fileList.value = newFileList;
      console.log(newFileList);

    };

    return {
      previewVisible,
      previewImage,
      fileList,
      handleCancel,
      handlePreview,
      handleChange,
      activeKey: ref('2'),
    };
  },
});
</script>

<style>
/* you can make up upload button and sample style by using stylesheets */
.ant-upload-select-picture-card i {
  font-size: 32px;
  color: #999;
}

.ant-upload-select-picture-card .ant-upload-text {
  margin-top: 8px;
  color: #666;
}
</style>