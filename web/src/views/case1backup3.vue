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
                v-model:file-list="fileList"
                name="file"
                list-type="picture-card"
                class="avatar-uploader"
                :show-upload-list="false"
                action="http://127.0.0.1:8880/file/upload"

                @change="handleChange"
            >
              <img v-if="imageUrl" :src="imageUrl" alt="avatar"/>
              <div v-else>
                <loading-outlined v-if="loading"></loading-outlined>
                <plus-outlined v-else></plus-outlined>
                <div class="ant-upload-text">Upload</div>
              </div>
            </a-upload>
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
import{PlusOutlined,LoadingOutlined} from '@ant-design/icons-vue';
import{ message } from 'ant-design-vue';
import{ defineComponent, ref } from 'vue';


interface FileItem{
  uid: string;
  name?: string;
  status?: string;
  response: FileUploadMsg;
  url?: string;
  type?: string;
  size: number;
  originFileObj: any;
}

interface FileUploadMsg{
  success: boolean,
  message: string,
  content: any,
}

interface FileInfo{
  file:FileItem;
  fileList:FileItem[];
}
function getBase64(img:Blob, callback:(base64Url: string)=>void){
  const reader = new FileReader();
  reader.addEventListener('load',()=> callback(reader.result as string));
  reader.readAsDataURL(img);
}

export default defineComponent({
  components:{
    LoadingOutlined,
    PlusOutlined,
  },
  setup(){
    const fileList = ref([]);
    const loading = ref<boolean>(false);
    const imageUrl = ref<string>('');
    const key = 'updatable';
    const handleChange =(info:FileInfo)=>{
      console.log('handleChange called');
      console.log(info);
      if(info.file.status ==='uploading'){
        message.success({ content: 'Uploading!', key});
        console.log('uploading');
        loading.value =true;
        return;
      }
      if(info.file.status ==='done'){
        console.log('done');
        message.success({ content: info.file.response.message, key, duration: 2 });
        // Get this url from response in real world.
        getBase64(info.file.originFileObj,(base64Url: string)=>{
          imageUrl.value = base64Url;
          loading.value =false;
        });
      }
      if(info.file.status ==='error'){
        console.log('error');
        loading.value =false;
        message.error('upload error');
      }
    };
    const beforeUpload =(file:FileItem)=>{
      const isJpgOrPng = file.type ==='image/jpeg'|| file.type ==='image/png';
      if(!isJpgOrPng){
        message.error('You can only upload JPG file!');
      }
      const isLt2M = file.size /1024/1024<10;
      if(!isLt2M){
        message.error('Image must smaller than 10MB!');
      }
      return isJpgOrPng && isLt2M;
    };
    return{
      fileList,
      loading,
      imageUrl,
      handleChange,
      beforeUpload,
      activeKey: ref('2'),
    };
  },
});
</script>
<style>
.avatar-uploader >.ant-upload {
  width:128px;
  height:128px;
}
.ant-upload-select-picture-card i {
  font-size:32px;
  color:#999;
}
.ant-upload-select-picture-card .ant-upload-text {
  margin-top:8px;
  color:#666;
}
</style>



