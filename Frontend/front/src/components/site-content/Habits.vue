<template>
  <div class="main-gradient-1"></div>
  <img class="insane_image"
       src="https://images.pexels.com/photos/512229/pexels-photo-512229.jpeg?cs=srgb&dl=pexels-pixabay-512229.jpg&fm=jpg">
  <h1 class="main-text bolder cool-text-color">Improve your Habitats</h1>
  <h2 class="current">Your currently habits, which have been worked on:</h2>
  <div class="habits_table">
    <div v-for="habit in habits">
      <h1 class="bolder cool-text-color white-color">{{ habit.name }}</h1>
      <h1 v-if="habit.completed" class="bolder cool-text-color white-color">Current status(completed): Yes</h1>
      <h1 v-if="!habit.completed" class="bolder cool-text-color white-color">Current status(completed): No</h1>
      <button @click="showHistory(habit.id)" class="form-button check_history">Show History</button>
      <i @click="hideEditMenu(habit.id)" class="edit fa-solid fa-edit"></i>
      <div class="today_markup">
        <h2 class="bolder cool-text-color white-color">Today duty markup</h2>
        <p class="white-color">Attention! You may only mark today task. If you don't do this, then that'll be
          authomatickly marked as incomplete</p>
        <div class="tracks h" v-for="track in habit.tracks">
          <div></div>
          <i v-on:click="mark(habit.id, track.id)" v-if="track.status==='today'&&track.marked===false"
             class="fa-solid fa-circle circle_2 today"></i>
          <i v-on:click="unmark(habit.id, track.id)" v-if="track.status==='today'&&track.marked===true"
             class="fa-solid fa-circle circle_1 today"></i>

        </div>
        <p class="white-color warn-text"> ----- Click here to mark it! </p>
      </div>
      <div v-if="getFromMap(habit.id)" class="track-block">
        <div class="tracks" v-for="track in habit.tracks">
          <i v-if="track.status==='before'&&track.marked===true" class="fa-solid fa-circle circle_1"></i>
          <i v-if="track.status==='before'&&track.marked===false" class="fa-solid fa-circle circle_4"></i>
          <i v-if="track.status==='today'&&track.marked===true"
             class="fa-solid fa-circle circle_1"></i>
          <i v-if="track.status==='today'&&track.marked===false"
             class="fa-solid fa-circle circle_2"></i>
          <i v-if="track.status==='after'" class="fa-solid fa-circle circle_3"></i>
          <p class="warn-color t_date">{{ track.marking_day }}</p>
        </div>
      </div>
      <div v-if="getFromEditMap(habit.id)" class="edition-menu">
        <form class="login-form" action="">
          <label class="labels">
            <p class="input-name">Name</p>
            <input id="email" class="loh-input input" v-model="HabitDTO.name">
          </label>
          <label class="labels">
            <p class="input-name">Days</p>
            <input id="email" class="loh-input input" v-model="HabitDTO.day_count">
          </label>
          <label class="labels">
            <p class="input-name">Description</p>
            <input id="email" class="loh-input input" v-model="HabitDTO.description">
          </label>
          <button @click="create" class="form-button create_but">Create</button>
        </form>
      </div>
    </div>
    <div class="creation">
      <div v-if="adding===false" v-on:click="hide" class="add">
        <i class="fa-solid fa-plus plus"></i>
        <h2 class="bolder cool-text-color white-color task-text add-text">Add</h2>
      </div>
      <div v-if="adding===true" v-on:click="hide" class="add">
        <i class="fa-solid fa-minus plus"></i>
        <h2 class="bolder cool-text-color white-color task-text add-text">Hide</h2>
      </div>
      <form v-if="adding===true" class="login-form" action="">
        <label class="labels">
          <p class="input-name">Name</p>
          <input id="email" class="loh-input input" v-model="HabitDTO.name">
        </label>
        <label class="labels">
          <p class="input-name">Days</p>
          <input id="email" class="loh-input input" v-model="HabitDTO.day_count">
        </label>
        <label class="labels">
          <p class="input-name">Description</p>
          <input id="email" class="loh-input input" v-model="HabitDTO.description">
        </label>
        <button @click="create" class="form-button create_but">Create</button>
      </form>
    </div>
  </div>
</template>

<script>

import HabitsService from "@/services/HabitsService";

export default {
  name: "Habits",
  data: () => ({
    habits: [],
    track_map: new Map(),
    adding: false,
    HabitDTO: {
      name: "",
      day_count: 0,
      description: ""
    },
    editing_hide_map: new Map(),
  }),
  methods: {
    getAllHabits() {
      HabitsService.getAll().then((response) => {
        this.habits = response.data
        this.habits.forEach(habit => {
          this.track_map.set(habit.id, false)
          this.editing_hide_map.set(habit.id, false)
        })
      })
    },
    create() {
      HabitsService.create(this.HabitDTO)
    },
    hideEditMenu(id) {
      if (this.editing_hide_map.get(id)) {
        this.editing_hide_map.set(id, false)
      } else {
        this.editing_hide_map.set(id, true)
      }
    },
    mark(habit_id, id) {
      HabitsService.mark(id).then(() => {
        this.getAllHabits()

      })
    },
    unmark(habit_id, id) {
      HabitsService.unmark(id).then(() => {
        this.getAllHabits()
        this.$router.push('/habits')
      })
    },
    getFromMap(id) {
      return this.track_map.get(id)
    },
    showHistory(id) {
      if (this.track_map.get(id)) {
        this.track_map.set(id, false)
      } else {
        this.track_map.set(id, true)
      }
    },
    hide() {
      this.adding = !this.adding;
    },
    subhide(id) {
      if (this.sub_adding.get(id)) {
        this.sub_adding.set(id, false)
      } else {
        this.sub_adding.set(id, true)
      }
    },
    getFromEditMap(id) {
      return this.editing_hide_map.get(id)
    },
  },

  created() {
    this.getAllHabits()
  }
}
</script>

<style scoped>
.habits_table {
  position: absolute;
  top: 32vh;
  padding: 5vh;
  width: 95%;
  height: 100vh;
  overflow-y: auto;
  overflow-x: hidden;
}

.main-text {
  position: absolute;
  color: white;
  font-size: 60px;
  z-index: 1;
  top: 7.7%;
  left: 30%;
}

.current {
  position: absolute;
  color: white;
  font-size: 42px;
  z-index: 1;
  top: 22vh;
  left: 4.25vh;
}

.circle_1 {
  font-size: 1.77em;
  color: darkgreen;
}

.circle_2 {
  font-size: 1.77em;
  color: gray;
}

.today {
  font-size: 3em;
  right: 12vh;
  top: 3.3vh;
}

.today:hover {
  border: 4px solid goldenrod;
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

.track-block {
  margin: 0;
  padding: 0;
  max-width: 120vh;
  max-height: 40vh;
  left: 62vh;
  bottom: 27.5vh;
}

.today_markup {
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
  height: 140vh;
  background: linear-gradient(180deg, #090B13 0%, rgba(9, 11, 19, 0.2) 100%);
  opacity: 60%;
  z-index: 0;
}

.creation {
  bottom: 2.5vh;
  min-height: 20vh;
}

.create_but {
  top: 10.5vh;
  left: 12vh;
}

.check_history {
  bottom: 6.5vh;
  left: 80vh;
}

.edit {
  color: white;
  left: 142.5vh;
  font-size: 2.5em;
  bottom: 5vh;
}

.edition-menu {
  left: 127.5vh;
  bottom: 38.5vh;
}

.warn-text {
  bottom: 5vh;
  left: 10vh;
}

.t_date {
  right: 5vh;
  color: white;
}

.plus {
  top: 2vh;
  font-size: 2.25em;
  color: white;
}

.add {
  padding-left: 2vh;
  max-width: 27vh;
  max-height: 8vh;
  color: white;
}

.add:hover {
  background-color: rgba(0, 0, 0, 0.3);
  border-color: rgba(0, 0, 0, 0);
  transition: 1s;
}

.add-text {
  bottom: 5.15vh;
  left: 8vh;
  font-size: 36px;
}

::-webkit-scrollbar {
  width: 20px;
}

/* Track */
::-webkit-scrollbar-track {
  box-shadow: inset 0 0 5px grey;
  border-radius: 10px;
}

/* Handle */
::-webkit-scrollbar-thumb {
  background: rgba(0, 0, 0, 0.1);
  opacity: 10%;
  border-radius: 10px;
}

/* Handle on hover */
::-webkit-scrollbar-thumb:hover {
  background: rgba(0, 0, 0, 0.5);
}

.h {
  left: 5vh;
}
</style>