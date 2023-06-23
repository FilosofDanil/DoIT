<template>
  <div class="main-gradient-1"></div>
  <img class="insane_image" src="https://images.pexels.com/photos/512229/pexels-photo-512229.jpeg?cs=srgb&dl=pexels-pixabay-512229.jpg&fm=jpg">
  <h1 class="main-text bolder cool-text-color">Improve your Habitats</h1>
  <h2 class="current">Your currently habits, which have been worked on:</h2>
  <div class="habits_table">
    <div v-for="habit in habits">
      <h1 class="bolder cool-text-color white-color">{{ habit.name }}</h1>
      <h1 v-if="habit.completed" class="bolder cool-text-color white-color">Current status(completed): Yes</h1>
      <h1 v-if="!habit.completed" class="bolder cool-text-color white-color">Current status(completed): No</h1>
      <button @click="showHistory(habit.id)" class="form-button check_history">Show History</button>
      <i class="edit fa-solid fa-edit"></i>
      <div class="today_markup">
        <h2 class="bolder cool-text-color white-color">Today duty markup</h2>
      </div>
      <div v-if="getFromMap(habit.id)" class="track-block">
        <div class="tracks" v-for="track in getAllHabitsTracks(habit.id)">
          <i v-if="track.status==='before'&&track.marked===true" class="fa-solid fa-circle circle_1"></i>
          <i v-if="track.status==='before'&&track.marked===false" class="fa-solid fa-circle circle_4"></i>
          <i v-on:click="unmark(track.id)" v-if="track.status==='today'&&track.marked===true"
             class="fa-solid fa-circle circle_1 today"></i>
          <i v-on:click="mark(track.id)" v-if="track.status==='today'&&track.marked===false"
             class="fa-solid fa-circle circle_2 today"></i>
          <i v-if="track.status==='after'" class="fa-solid fa-circle circle_3"></i>
        </div>
      </div>


      <div class="creation">
        <form class="login-form" action="">
          <label class="labels">
            <p class="input-name">Name</p>
            <input id="email" class="loh-input input">
          </label>
          <button @click="" class="form-button create_but">Create</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>

import HabitsService from "@/services/HabitsService";

export default {
  name: "Habits",
  data: () => ({
    habits: [],
    tracks: [],
    track_map: new Map(),
  }),
  methods: {
    getAllHabits() {
      HabitsService.getAll().then((response) => {this.habits = response.data
        this.habits.forEach(habit => this.track_map.set(habit.id, false))
      })
    },
    getAllHabitsTracks(id) {
      HabitsService.getAllTracks(id).then((response) => this.tracks = response.data)
      return this.tracks
    },
    mark(id) {
      HabitsService.mark(id).then(() => {
        this.$router.push('/habits')
      })
    },
    unmark(id) {
      HabitsService.unmark(id).then(() => {
        this.$router.push('/habits')
      })
    },
    getFromMap(id) {
      return this.track_map.get(id)
    },
    showHistory(id){
      if (this.track_map.get(id)) {
        this.track_map.set(id, false)
      } else {
        this.track_map.set(id, true)
      }
    }
  },

  created() {
    this.getAllHabits()
  }
}
</script>

<style scoped>
.habits_table {
  position: absolute;
  top: 27vh;
  padding: 5vh;
}

.main-text {
  position: absolute;
  color: white;
  font-size: 60px;
  z-index: 1;
  top: 8.7%;
  left: 30%;
}

.current {
  position: absolute;
  color: white;
  font-size: 42px;
  z-index: 1;
  top: 24vh;
  left: 5vh;
}

.circle_1 {
  font-size: 1.77em;
  color: darkgreen;
}

.circle_2 {
  font-size: 1.77em;
  color: gray;
}

.today:hover {
  border: 3px solid goldenrod;
  border-radius: 50%;
  color: greenyellow;
  opacity: 30%;
}

.circle_3 {
  font-size: 1.77em;
  color: rgba(155, 155, 155, 0.34);
}

.circle_4 {
  font-size: 1.77em;
  color: darkred;
}

.track-block{
  max-width: 80vh;
  left:70vh;
}

.today_markup{
  bottom: 7vh;
  max-width: 50vh;
}

.tracks {
  display: inline-grid;
  grid-template-columns: repeat(3, 1fr);
  grid-template-rows: repeat(1.2, 1fr);
  grid-gap: 1em;
}

.main-gradient-1 {
  position: absolute;
  top: 3.8rem;
  width: 100vw;
  height: 130vh;
  background: linear-gradient(180deg, #090B13 0%, rgba(9, 11, 19, 0.2) 100%);
  opacity: 60%;
  z-index: 0;
}
.creation{
  top:27.5vh;
  min-height: 20vh;
}

.create_but{
  top:20vh;
  right:40vh;
}

.check_history{
  bottom:6.5vh;
  left:80vh;
}

.edit{
  color:white;
  left:150vh;
  font-size: 2.5em;
  bottom:5vh;
}
</style>