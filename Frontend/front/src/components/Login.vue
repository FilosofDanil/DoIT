<template>
  <h1 class="title">Login</h1>
  <div class="main-gradient-1"></div>
  <img class="insane_image" src="https://upload.wikimedia.org/wikipedia/commons/2/26/Burg_Eltz_HDR.jpg">
  <form class="login-form">
    <label class="labels">
      <p class="input-name">Email</p>
      <input id="email" class="login-input input">
    </label>
    <label class="labels">
      <p class="input-name">Password</p>
      <input id="pas" class="login-input input">
    </label>
    <button @click ="check_it" class="form-button">Login</button>
  </form>



</template>

<script>

export default {
  name: "Login",
  data: () => ({
  }),
  methods: {

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
  font-size: 40px;
  font-weight: 700;
}

.labels {
  display: flex;
  padding: 2rem;
}

.error-name{
  color: red;
}

.error-input{
  color: red;
  border-color: red;
}
</style>