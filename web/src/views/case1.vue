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
                action="http://35.202.95.240:8880/file/upload"
                list-type="picture-card"
                v-model:file-list="fileList"
                :multiple="true"
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

          <a-divider style="height: 1px; background-color: #7cb305" />

          <a-timeline :reverse="true" style="margin:0px 0px 0px 3px">
            <a-timeline-item v-for="item in userPredictList" :value="item.image" :key="item.image" color="red">
              <template #dot><clock-circle-outlined style="font-size: 16px" /></template>
              <p>{{item.image}}</p>
              <a-alert v-bind:message=item.predict type="success" />
              <a-image :width="200" v-bind:src=item.image_url />
              <a-collapse accordion>
                <a-collapse-panel header="View json data returned from Api">
                  <span v-html="item.content"></span>
                </a-collapse-panel>
              </a-collapse>
            </a-timeline-item>
          </a-timeline>
        </a-tab-pane>
        <a-tab-pane key="3">
          <template #tab>
        <span>
          Predict History
        </span>
          </template>
          <a-timeline :reverse="true" style="margin:0px 0px 0px 3px">
            <a-timeline-item v-for="item in userPredictHistoryList" :value="item.image" :key="item.image" color="red">
              <template #dot><clock-circle-outlined style="font-size: 16px" /></template>
              <p>{{item.image}}
                  <LaptopOutlined twoToneColor="#eb2f96" /> {{item.ip}}
                  <ClockCircleOutlined twoToneColor="#eb2f96" /> {{item.timestampStr}}
              </p>
              <a-alert v-bind:message=item.message type="success" />
              <a-image :width="200" v-bind:src=item.imageUrl />
              <a-collapse accordion>
                <a-collapse-panel header="View json data returned from Api">
                  {{item.content}}
                </a-collapse-panel>
              </a-collapse>
            </a-timeline-item>
          </a-timeline>
          <a-pagination v-model:current="pagination.current"
                        :total="pagination.total"
                        :pageSize="pagination.pageSize"
                        @change="userPredictHistoryListChange"
                        show-less-items style="text-align: right"/>
        </a-tab-pane>
      </a-tabs>
    </a-layout-content>
  </a-layout>
</template>
<script lang="ts">
import{PlusOutlined,LoadingOutlined, ClockCircleOutlined, LaptopOutlined} from '@ant-design/icons-vue';
import{ message } from 'ant-design-vue';
import {defineComponent, onMounted, ref} from 'vue';
import axios from "axios";

function getBase64(file: File) {
  return new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => resolve(reader.result);
    reader.onerror = error => reject(error);
  });
}

interface FileInfo {
  file: FileItem;
  fileList: FileItem[];
}

interface FileItem {
  uid: string;
  name?: string;
  status?: string;
  response?: FileUploadMsg;
  percent?: number;
  url?: string;
  preview?: string;
  originFileObj?: any;
}

interface FileUploadMsg{
  success: boolean,
  message: string,
  content: FileUploadMsgContent,
}

interface FileUploadMsgContent{
  name: string,
  nameOri: string,
  nameUrl: string,
  content: FruitClassificationMsg
}

interface FruitClassificationMsg {
  code: number,
  message: string,
  data: FruitClassificationData
}

interface FruitClassificationData {
  image: string,
  result: string,
  probability: string
}

interface FruitClassificationDataHist {
  timestamp: number,
  timestampStr: string,
  image: string,
  imageUrl: string,
  result: string,
  probability: number,
  ip: string,
  content: string,
  message: string
}

export default defineComponent({
  components: {
    PlusOutlined,
    ClockCircleOutlined,
    LaptopOutlined,
  },
  setup() {
    //-----------------------File Upload----------------------
    const previewVisible = ref<boolean>(false);
    const previewImage = ref<string | undefined>('');

    const fileList = ref<FileItem[]>([
    ]);

    const key = 'updatable';

    const handleCancel = () => {
      previewVisible.value = false;
    };
    const handlePreview = async (file: FileItem) => {
      if (!file.url && !file.preview) {
        file.preview = (await getBase64(file.originFileObj)) as string;
      }
      previewImage.value = file.url || file.preview;
      previewVisible.value = true;
    };
    const handleChange = ({ fileList: newFileList, file: newFile }: FileInfo) => {
      if (newFile.status == 'uploading'){
        message.loading({content: 'uploading ' + newFile.name, key});
        return;
      }
      if (newFile.status == 'error'){
        message.error({content: 'uploading fail ' + newFile.name, key});
        return;
      }

      if (newFile.status == 'done'){
        if (newFile.response?.success){
          message.success({content: 'uploaded ' + newFile.name, key, duration:2});
          fileList.value = newFileList;
          const jsonResultStr = JSON.stringify(newFile.response.content.content, null, 2);
          console.log(jsonResultStr);
          userPredictListAdd(newFile.response?.content.nameOri,
               newFile.response?.content.nameUrl,
               newFile.response?.content.content.data.result,
               newFile.response?.content.content.data.probability,
               "<pre>" + jsonResultStr + "</pre>"
          );
          handleQueryPredictHistory({
            page: pagination.value.current,
            size: pagination.value.pageSize,
          });
        }else{
          message.success({content: 'upload fail ' + newFile.response?.message, key, duration:2});
        }
        return;
      }
    };

    //-----------------------Timeline of Current Predict----------------------
    const userPredictList = ref();
    userPredictList.value = [];

    const userPredictListAdd = (image: string, image_url: string, result: string, probability: string, content: string) => {
      console.log("userPredictListAdd called");
      userPredictList.value.push({
        image:image,
        image_url:image_url,
        predict:result.concat(" probability: ").concat(probability),
        content:content});
    };

    //-----------------------Timeline of Current Predict History----------------------
    const userPredictHistoryList = ref();
    const pagination = ref({
      current: 1,
      pageSize: 10,
      total: 0
    });
    const param = ref();
    userPredictHistoryList.value = [];
    const handleQueryPredictHistory = (params: any) => {
      axios.get("/mlfruit/list", {
        params: {
          page: params.page,
          size: params.size,
        }
      }).then((response) => {
        console.log("mlfruit list returned")
        const data = response.data;
        console.log(data);
        if (data.success) {
          data.content.list.forEach((item: FruitClassificationDataHist)=>{
            console.log(item.content);
            item.message = item.result.concat(" probability: ").concat(item.probability.toString());
            let date = new Date(item.timestamp*1000);
            item.timestampStr = date.toLocaleString();
            //item.content = `<pre>${item.content}</pre>`;
          });
          userPredictHistoryList.value = data.content.list;

          pagination.value.current = params.page;
          pagination.value.total = data.content.total;
          console.log("userPredictHistoryList=");
          console.log(userPredictHistoryList);
        } else {
          message.error(data.message);
        }
      });
    };

    const userPredictHistoryListChange = (page: any, pageSize: any) => {
      console.log(pagination);
      handleQueryPredictHistory({
        page: page,
        size: pageSize
      });
    };

    onMounted(() => {
      console.log("onMounted called");
      handleQueryPredictHistory({
        page: pagination.value.current,
        size: pagination.value.pageSize,
      });
    });

    return {
      previewVisible,
      previewImage,
      fileList,
      handleCancel,
      handlePreview,
      handleChange,
      activeKey: ref('2'),
      userPredictList,
      pagination,
      userPredictHistoryList,
      handleQueryPredictHistory,
      userPredictHistoryListChange,
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