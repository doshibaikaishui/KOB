<template>
  <NavBar />
  <router-view></router-view>
</template>

<script>
import NavBar from './components/NavBar.vue'
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap"
/* import $ from 'jquery'; */


export default {
  components: {
    NavBar
  },
  setup() {
    

    

    /* $.ajax({
      url:"http://127.0.0.1:3000/user/account/register/",
      type:"post",
      data: {
        username: "mjr2",
        password: "1234",
        confirmPassword: "1234",
      },
      success(resp) {
        console.log(resp);
      },
      error(resp) {
        console.log(resp);
      }
    }) */
}
}
</script>


<style>
body {
  background: linear-gradient(45deg, #2196f3, #ff4685);
  min-height: 100vh;

}
</style>
