<template>
  <div style="min-height: 538px;
    background-image: url(/image/bg.jpg);
    background-repeat: no-repeat;
    width: 100%;">
    <a-layout style="background: none;text-align: center">
      <a-layout-content>
        <a-image
            :width="100"
            src="/image/me.jpg"
            style="border-radius: 100px;border: 4px solid white;margin: 50px 0px 10px 0px;"
        />
        <a-typography-title :level="3" style="color: white; margin:0px;">Hi, I'm</a-typography-title>
        <a-typography-title :level="2" style="color: white; margin:0px;">Deming</a-typography-title>
        <a-typography-title :level="4" style="color: white; margin-top:15px;text-decoration: overline;color: #FDD05A;">Senior Reservoir Engineer, Machine Learning Engineer, Developer</a-typography-title>

        <div class="icons-list">
          <a href="https://www.linkedin.com/in/deming-wang-16011611" target="_blank"><LinkedinOutlined /></a>
          <a href="https://github.com/wdmhouston?tab=repositories" target="_blank"><GithubOutlined /></a>
        </div>
      </a-layout-content>
    </a-layout>
  </div>

</template>

<script lang="ts">
import { defineComponent} from 'vue';
import { GithubOutlined,LinkedinOutlined } from '@ant-design/icons-vue'

export default defineComponent({
  name: 'Home',
  components: {
    GithubOutlined,
    LinkedinOutlined
  },
  setup() {

    return {
      GithubOutlined,
      LinkedinOutlined
    }
  }
});
</script>

<style scoped>
.icons-list{
  margin-top:100px;;
  color: #FDD05A;
  margin-right: 16px;
  font-size: 36px;
}

.icons-list a{
  color: white;
  margin:15px;
}
</style>
