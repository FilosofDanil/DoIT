<template>
  <div class="main-gradient-1"></div>
  <img class="insane_image" src="https://rare-gallery.com/uploads/posts/123427-central-park-infrared-lake-manhattan-new-york-city-4k.jpg">
  <div v-if="!verified" class="lds-spinner"><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div><div></div></div>
  <h1 v-if="!verified" class="main-text bolder cool-text-color">Waiting for server response...</h1>
  <h1 v-if="verified" class="main-text bolder cool-text-color">Your Mail has successfully verified!</h1>
  <i v-if="verified" class="fa-solid fa-check success "></i>
  <router-link v-if="verified" class="profile_link" to="/login">Now you could login and enjoy our service
  </router-link>

</template>

<script>

import AuthService from "@/services/AuthService";

export default {
  name: "Verified",
  props: ['verification'],
  data: () => ({
    code: "",
    verified:false
  }),
  methods: {
    verify(){
      AuthService.activate(this.code).then((response) => this.verified = response.data)
    }
  },

  created() {
    this.code = window.location.search.replace('?verification=', '')
    this.verify()
  }
}
</script>

<style scoped>
.success{
  color: green;
  font-size: 4em;
  position: absolute;
  top:50.5vh;
  right:73vh;
  width: 80px;
}
.lds-spinner {
  color: white;
  display: inline-block;
  position: absolute;
  top:50vh;
  right:73vh;
  width: 80px;
  height: 80px;
}
.lds-spinner div {
  transform-origin: 40px 40px;
  animation: lds-spinner 1.2s linear infinite;
}
.lds-spinner div:after {
  content: " ";
  display: block;
  position: absolute;
  top: 3px;
  left: 37px;
  width: 6px;
  height: 18px;
  border-radius: 20%;
  background: #fff;
}
.lds-spinner div:nth-child(1) {
  transform: rotate(0deg);
  animation-delay: -1.1s;
}
.lds-spinner div:nth-child(2) {
  transform: rotate(30deg);
  animation-delay: -1s;
}
.lds-spinner div:nth-child(3) {
  transform: rotate(60deg);
  animation-delay: -0.9s;
}
.lds-spinner div:nth-child(4) {
  transform: rotate(90deg);
  animation-delay: -0.8s;
}
.lds-spinner div:nth-child(5) {
  transform: rotate(120deg);
  animation-delay: -0.7s;
}
.lds-spinner div:nth-child(6) {
  transform: rotate(150deg);
  animation-delay: -0.6s;
}
.lds-spinner div:nth-child(7) {
  transform: rotate(180deg);
  animation-delay: -0.5s;
}
.lds-spinner div:nth-child(8) {
  transform: rotate(210deg);
  animation-delay: -0.4s;
}
.lds-spinner div:nth-child(9) {
  transform: rotate(240deg);
  animation-delay: -0.3s;
}
.lds-spinner div:nth-child(10) {
  transform: rotate(270deg);
  animation-delay: -0.2s;
}
.lds-spinner div:nth-child(11) {
  transform: rotate(300deg);
  animation-delay: -0.1s;
}
.lds-spinner div:nth-child(12) {
  transform: rotate(330deg);
  animation-delay: 0s;
}
@keyframes lds-spinner {
  0% {
    opacity: 1;
  }
  100% {
    opacity: 0;
  }
}

.main-text{
  position: absolute;
  color: white;
  top:52vh;
  right:90vh;
}

.profile_link{
  position: absolute;
  color: white;
  top:60vh;
  right:124vh;
  font-size: 32px;
  font-weight: 700;
}
</style>