<template>
  <div class="main-gradient-1"></div>
  <img class="insane_image" src="https://images.wallpaperscraft.com/image/single/dark_forest_road_167292_3840x2160.jpg">
  <h1 class="main-text bolder cool-text-color">Your Profile </h1>
  <h2 class="congratulations">Hi! {{UserDTO.profile_name}} {{ UserDTO.surname}}</h2>
</template>

<script>
import ProfileService from "@/services/ProfileService";

export default {
  name: "Profile",
  data: () => ({
    UserDTO: {
      email: "",
      password: "",
      profile_name: "You just need to authorize!",
      surname: "",
      phone: "",
      date_of_birth: "",
      role: ""
    }
  }),
  methods: {
    getUser() {
      ProfileService.getProfile().then((response) => this.UserDTO = response.data)
    }
  },

  created() {
    this.getUser()
  }
}
</script>

<style scoped>
.main-text {
  position: absolute;
  color: white;
  font-size: 60px;
  z-index: 1;
  top: 8.7%;
  left: 38%;
}

.congratulations {
  position: absolute;
  color: white;
  font-size: 60px;
  z-index: 1;
  top: 15.5%;
  left: 5%;
}
</style>