<template>
  <div class="home">
    <a-button type="primary">Primary Button</a-button>
    <img alt="Vue logo" src="../assets/logo.png">
    <!--<HelloWorld msg="Welcome to Your Vue.js + TypeScript App"/>-->
    <pre>
      {{ebooks}}
      {{ebooks2}}
    </pre>
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, ref, reactive, toRef } from 'vue';
import axios  from "axios";
export default defineComponent({
  name: 'Home',
  setup(){
    console.log("setup");
    const ebooks = ref();  //responsible data of UI element
    const ebooks1 = reactive({books:[]});

    onMounted(()=>{
      console.log("onMounted");
      axios.get("http://localhost:8880/ebook/list?name=Spring")
          .then(function(response){
            const data = response.data;
            ebooks.value = data.content;
            ebooks1.books = data.content;
            console.log(response);
          });
    });

    return {
      ebooks,
      ebooks2: toRef(ebooks1, "books")
    };
  },
  components: {
  },
});
</script>
