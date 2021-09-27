<template>
  <div class="home">
    <a-button type="primary">Primary Button</a-button>
    <img alt="Vue logo" src="../assets/logo.png">
    <!--<HelloWorld msg="Welcome to Your Vue.js + TypeScript App"/>-->
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import axios  from "axios";
export default defineComponent({
  name: 'Home',
  setup(){
    console.log("setup");
    axios.get("http://localhost:8880/ebook/list?name=Spring")
        .then(function(response){
          console.log(response);
        });
  },
  components: {
  },
});
</script>
