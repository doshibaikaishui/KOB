<template>
  <div>
      <div>Bot名称：{{bot_name}}</div>
      <div>Bot攻击力：{{bot_attack}}</div>
      <div>Bot版本：{{bot_version}}</div>
  </div>
  <router-view></router-view>
</template>

<script>
import $ from 'jquery'
import { ref } from 'vue'

    export default{
        name: "App",
        setup: () => {
          let bot_name = ref("");
          let bot_attack = ref("");
          let bot_version = ref("");

          $.ajax({
            url: "http://localhost:3000/pk/getBotInfo/",
            type: "get",
            success: resp => {
              bot_name.value = resp.botName;
              bot_attack.value = resp.attack;
              bot_version.value = resp.botVersion;
              console.log(resp);
            }
          });

          return {
            bot_name,
            bot_attack,
            bot_version
          }
        }
    }
</script>


<style>
body {
  background-image: url("@/assets/background.png");
  background-size: cover;
}
</style>
