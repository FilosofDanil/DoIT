<template>
  <div class="main-gradient-1"></div>
  <img class="insane_image" src="https://images.wallpaperscraft.com/image/single/street_city_autumn_131015_3840x2160.jpg">
  <h1 class="main-text bolder cool-text-color">Your Profile </h1>
  <h2 class="congratulations">Hi! {{ UserDTO.profile_name }} {{ UserDTO.surname }}</h2>
  <h2 v-if="UserDTO.profile_name!=='You just need to authorize or verify your account or refresh the page!'" class="bolder cool-text-color reg-mail">Your current mailbox: {{UserDTO.email}}</h2>
  <h2 v-if="UserDTO.profile_name!=='You just need to authorize or verify your account or refresh the page!'" class="bolder cool-text-color reg-phone">Your current phone: {{UserDTO.phone}}</h2>
  <h2 v-if="UserDTO.profile_name!=='You just need to authorize or verify your account or refresh the page!'" class="bolder cool-text-color reg-age">Your age: {{age}}</h2>
  <button v-if="UserDTO.profile_name!=='You just need to authorize or verify your account or refresh the page!'" class="change change1">
    Change
  </button>

  <button v-if="UserDTO.profile_name!=='You just need to authorize or verify your account or refresh the page!'" class="change change2">
    Change
  </button>

  <button v-if="UserDTO.profile_name!=='You just need to authorize or verify your account or refresh the page!'" class="change change3">
    Change Password
  </button>

  <div v-if="UserDTO.profile_name!=='You just need to authorize or verify your account or refresh the page!'" class="statistics">
    <div class="stats">
      <h2 class="bolder cool-text-color  stat-text">Completed Tasks</h2>
      <h1 class="bolder cool-text-color stat_nums">{{ calc_stats_c_tasks }}</h1>
    </div>
    <div class="stats">
      <h2 class="bolder cool-text-color stat-text">Uncompleted Tasks</h2>
      <h1 class="bolder cool-text-color stat_nums">{{ calc_stats_u_tasks }}</h1>
    </div>
    <div class="stats">
      <h2 class="bolder cool-text-color stat-text">Formed habit</h2>
      <h1 class="bolder cool-text-color stat_nums">{{ calc_stats_c_habits }}</h1>
    </div>
    <div class="stats">
      <h2 class="bolder cool-text-color stat-text">Unformed habit</h2>
      <h1 class="bolder cool-text-color stat_nums">{{ calc_stats_u_habits }}</h1>
    </div>
  </div>
</template>

<script>
import ProfileService from "@/services/ProfileService";

export default {
  name: "Profile",
  data: () => ({
    UserDTO: {
      email: "",
      password: "",
      profile_name: "You just need to authorize or verify your account or refresh the page!",
      surname: "",
      phone: "",
      date_of_birth: "",
      role: ""
    },
    calc_stats_c_tasks: 0,
    calc_stats_u_tasks: 0,
    calc_stats_c_habits: 0,
    calc_stats_u_habits: 0,
    age:0
  }),
  methods: {
    getUser() {
      ProfileService.getProfile().then((response) => this.UserDTO = response.data)
    },
    getStats() {
      ProfileService.getCompletedTasksStats().then((response) => this.calc_stats_c_tasks = response.data)
      ProfileService.getUncompletedTasksStats().then((response) => this.calc_stats_u_tasks = response.data)
      ProfileService.getCompletedHabitsStats().then((response) => this.calc_stats_c_habits = response.data)
      ProfileService.getUncompletedHabitsStats().then((response) => this.calc_stats_u_habits = response.data)
    },
    getAge(){
      ProfileService.getAge().then((response) => this.age = response.data)
    }
  },

  created() {
    this.getUser()
    this.getStats()
    this.getAge()
  }
}
</script>

<style scoped>
.statistics {
  position: absolute;
  top: 60%;
  display: grid;
  grid-template-rows: 1fr 1fr;
  grid-template-columns: 1fr 1fr;
}

.stat_nums{
  bottom: 3.6vw;
  left: 22.5vw;
  font-size: 2.25lvw;
}

.stats{
  padding-right: 28vw;
  padding-left: 2.5vw;
  color: white;
  white-space: nowrap;

}

.stat-text{
  font-size: 2.25lvw;
}

.main-text {
  position: absolute;
  color: white;
  font-size: 4lvw;
  z-index: 1;
  top: 8.7%;
  left: 35%;
}

.congratulations {
  position: absolute;
  color: white;
  font-size: 4lvw;
  z-index: 1;
  top: 15.5%;
  left: 2.4vw;
}

.reg-mail{
  font-size: 2lvw;
  position: absolute;
  color: white;
  top: 27%;
  left: 2.5vw;
}

.reg-phone{
  font-size: 2lvw;
  position: absolute;
  color: white;
  top: 35%;
  left: 2.5vw;
}

.reg-age{
  font-size: 2lvw;
  position: absolute;
  color: white;
  top: 43%;
  left: 2.5vw;
}

.change{
  position: absolute;
  left: 65vw;
  top: 5px;
  padding: 0.5vw;
  color: rgba(255, 255, 255, 80%);
  width: 11vw;
  height: 3.125vw;
  text-align: center;
  opacity: 100%;
  border-radius: 45%;
  background-color: transparent;
  border-color: white;
  border-width: 0.32vw;
  font-size: 1vw;
  font-weight: 750;
}

.change1{
  top:28%;
}

.change2{
  top:36%;
}

.change3{
  top:44%;
}

.change:hover{
  background-color: rgba(0, 0, 0, 0.3);
  border-color: rgba(0, 0, 0, 0);
  transition: 1s;
}


</style>