<template>
  <div class="main-gradient-1"></div>
  <img class="insane_image" src="https://upload.wikimedia.org/wikipedia/commons/2/26/Burg_Eltz_HDR.jpg">
  <h1 class="title">SignUp</h1>
  <form class="login-form">
    <label class="labels">
      <p class="input-name">Email</p>
      <input id="email" class="login-input input" v-model="user.email">
    </label>
    <label class="labels">
      <p class="input-name">Name</p>
      <input id="pas" class="login-input input" v-model="user.profile_name">
    </label>
    <label class="labels">
      <p class="input-name">Surname</p>
      <input id="pas" class="login-input input" v-model="user.surname">
    </label>
    <label class="labels">
      <p class="input-name">Phone</p>
      <input id="pas" class="login-input input" v-model="user.phone">
    </label>
    <label class="labels">
      <p class="input-name">Password</p>
      <input id="pas" class="login-input input" v-model="user.password">
    </label>
    <label class="labels">
      <p class="input-name">Date Of Birth</p>
      <input id="pas" class="login-input input" type="date" v-model="user.date_of_birth">
    </label>
    <button @click ="signUp" class="form-button">SignUp</button>
  </form>
</template>

<script>
import AuthService from "@/services/AuthService";

export default {
  name: "SignUp",
  data: () => ({
    user: {
      email: "",
      password: "",
      profile_name: "",
      surname: "",
      date_of_birth: "",
      phone: "",
      role: "USER"
    },
    showError: false,
    errorText: ""
  }),
  methods:{
    signUp() {
      AuthService.signUp(this.user).then(() => {
        this.$router.push('/profile')
      })
          //.catch(err => {
            //this.showError = true
            //this.errorText = crudErrorHandler.catchError(err)
          // });
    }
  }
}
</script>

<style scoped>
.login-form {
  position: absolute;
  top: 20%;
  left: 65vh;
}

.title {
  color: white;
  position: absolute;
  z-index: 2;
  top: 10%;
  left: 83.25vh;
  font-size: 32px;
  font-weight: 700;
}
.labels {
  display: flex;
  padding: 2rem;
}
.form-button{
  top: 10vh;
  left: 12vh;
}
</style>