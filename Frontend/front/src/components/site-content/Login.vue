<template>
  <h1 class="title">Login</h1>
  <div class="main-gradient-1"></div>
  <img class="insane_image" src="https://upload.wikimedia.org/wikipedia/commons/2/26/Burg_Eltz_HDR.jpg">
  <form class="login-form" action="">
    <label class="labels">
      <p class="input-name">Email</p>
      <input id="email" class="login-input input" v-model="jwt_request.login">
    </label>
    <label class="labels">
      <p class="input-name">Password</p>
      <input id="pas" class="login-input input" v-model="jwt_request.password">
    </label>
    <button @click="login" class="form-button">Login</button>

  </form>


</template>

<script>

import AuthService from "@/services/AuthService";

export default {
  name: "Login",
  data: () => ({
    jwt_request: {
      login: "",
      password: ""
    }
  }),
  methods: {
    login() {
      AuthService.login(this.jwt_request)
      this.$router.push('/profile')
      // GroupService.addGroup(this.group).then(() => {
      //   this.$router.push('/main')
      // })
      //     .catch(err => {
      //       this.showError = true
      //       this.errorText = crudErrorHandler.catchError(err)
      //     });
    }
  }
}
</script>

<style scoped>
.login-form {
  position: absolute;
  top: 12vw;
  left: 35vw;
}

.title {
  color: white;
  position: absolute;
  z-index: 2;
  top: 10%;
  left: 41.75vw;
  font-size: 4lvw;
  font-weight: 700;
}

.labels {
  display: flex;
  padding: 2vw;
}

.error-name {
  color: red;
}

.error-input {
  color: red;
  border-color: red;
}

.form-button{
  top: 5vw;
  left: 6vw;
}
</style>