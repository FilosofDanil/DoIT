<template>
  <div class="main-gradient-1"></div>
  <img class="insane_image" src="https://images.wallpaperscraft.com/image/single/street_city_autumn_131015_3840x2160.jpg">
  <h1 class="main-text bolder cool-text-color">Your Profile </h1>
  <h2 class="congratulations">Hi! {{ UserDTO.profile_name }} {{ UserDTO.surname }}</h2>
  <h2 v-if="UserDTO.profile_name!=='You just need to authorize!'" class="bolder cool-text-color reg-mail">Your current mailbox: {{UserDTO.email}}</h2>
  <h2 v-if="UserDTO.profile_name!=='You just need to authorize!'" class="bolder cool-text-color reg-phone">Your current phone: {{UserDTO.phone}}</h2>
  <h2 v-if="UserDTO.profile_name!=='You just need to authorize!'" class="bolder cool-text-color reg-age">Your age: {{age}}</h2>
  <button v-if="UserDTO.profile_name!=='You just need to authorize!'" class="change change1">
    Change
  </button>

  <button v-if="UserDTO.profile_name!=='You just need to authorize!'" class="change change2">
    Change
  </button>

  <button v-if="UserDTO.profile_name!=='You just need to authorize!'" class="change change3">
    Change Password
  </button>

  <div v-if="UserDTO.profile_name!=='You just need to authorize!'" class="statistics">
    <div class="stats">
      <h2 class="bolder cool-text-color">Completed Tasks</h2>
      <h1 class="bolder cool-text-color stat_nums">{{ calc_stats_c_tasks }}</h1>
    </div>
    <div class="stats">
      <h2 class="bolder cool-text-color">Uncompleted Tasks</h2>
      <h1 class="bolder cool-text-color stat_nums">{{ calc_stats_u_tasks }}</h1>
    </div>
    <div class="stats">
      <h2 class="bolder cool-text-color">Formed habit</h2>
      <h1 class="bolder cool-text-color stat_nums">{{ calc_stats_c_habits }}</h1>
    </div>
    <div class="stats">
      <h2 class="bolder cool-text-color">Unformed habit</h2>
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
      profile_name: "You just need to authorize!",
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
  bottom: 7.2vh;
  left: 43vb;
}

.stats{
  padding-right: 60vh;
  padding-left: 5vh;
  color: white;
  white-space: nowrap;

}

.main-text {
  position: absolute;
  color: white;
  font-size: 60px;
  z-index: 1;
  top: 8.7%;
  left: 35%;
}

.congratulations {
  position: absolute;
  color: white;
  font-size: 60px;
  z-index: 1;
  top: 15.5%;
  left: 5vh;
}

.reg-mail{
  position: absolute;
  color: white;
  top: 27%;
  left: 5vh;
}

.reg-phone{
  position: absolute;
  color: white;
  top: 35%;
  left: 5vh;
}

.reg-age{
  position: absolute;
  color: white;
  top: 43%;
  left: 5vh;
}

.change{
  position: absolute;
  left: 130vh;
  right: 15vh;
  top: 5px;
  padding: 0.5rem;
  color: rgba(255, 255, 255, 80%);
  width: 19vh;
  height: 6.25vb;
  text-align: center;
  opacity: 100%;
  border-radius: 45%;
  background-color: transparent;
  border-color: white;
  border-width: 5px;
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