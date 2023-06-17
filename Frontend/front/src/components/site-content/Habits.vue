<template>
  <div class="main-gradient-1"></div>
  <img class="insane_image" src="https://images.wallpaperscraft.com/image/single/dark_forest_road_167292_3840x2160.jpg">
  <h1 class="main-text bolder cool-text-color">Improve your Habitats</h1>
  <h2 class="current">Your currently habits, which have been worked on:</h2>
  <div class="habits_table">
    <div v-for="habit in habits">
      <h1 class="bolder cool-text-color white-color">{{ habit.name }}</h1>
      <h1 v-if="habit.completed" class="bolder cool-text-color white-color">Current status(completed): Yes</h1>
      <h1 v-if="!habit.completed" class="bolder cool-text-color white-color">Current status(completed): No</h1>
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
  </div>
</template>

<script>

import HabitsService from "@/services/HabitsService";

export default {
  name: "Habits",
  data: () => ({
    habits: [],
    tracks: []
  }),
  methods: {
    getAllHabits() {
      HabitsService.getAll().then((response) => this.habits = response.data)
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
  top: 20.5%;
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

.tracks {
  display: inline-grid;
  grid-template-columns: repeat(3, 1fr);
  grid-template-rows: repeat(1.2, 1fr);
  grid-gap: 1em;
}
</style>