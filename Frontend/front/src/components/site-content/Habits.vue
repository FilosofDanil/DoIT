<template>
  <div class="main-gradient-1"></div>
  <img class="insane_image"
       src="https://images.pexels.com/photos/512229/pexels-photo-512229.jpeg?cs=srgb&dl=pexels-pixabay-512229.jpg&fm=jpg">
  <h1 class="main-text bolder cool-text-color">Improve your Habitats</h1>
  <h2 class="current">Your currently habits, which have been worked on:</h2>
  <div class="habits_table">
    <div v-for="habit in habits">
      <h1 class="bolder cool-text-color white-color kkk">{{ habit.name }}</h1>
      <h1 v-if="habit.completed" class="bolder cool-text-color white-color kkk">Current status(completed): Yes</h1>
      <h1 v-if="!habit.completed" class="bolder cool-text-color white-color kkk">Current status(completed): No</h1>
      <button @click="showHistory(habit.id)" class="form-button check_history">Show History</button>
      <i @click="hideEditMenu(habit.id)" class="edit fa-solid fa-edit"></i>
      <div class="today_markup">
        <h2 class="bolder cool-text-color white-color lll">Today duty markup</h2>
        <p class="white-color ppp">Attention! You may only mark today task. If you don't do this, then that'll be
          automatically marked as incomplete</p>
        <div class="tracks h" v-for="track in habit.tracks">
          <div></div>
          <i v-on:click="mark(habit.id, track.id)" v-if="track.status==='today'&&track.marked===false"
             class="fa-solid fa-circle circle_2 today"></i>
          <i v-on:click="unmark(habit.id, track.id)" v-if="track.status==='today'&&track.marked===true"
             class="fa-solid fa-circle circle_1 today"></i>
          <p v-if="track.status==='today'" class="white-color warn-text"> ----- Click here to mark it! </p>
        </div>

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
          <button @click="update(habit.id)" class="form-button create_but">Update</button>
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
    update(id) {
      HabitsService.update(this.HabitDTO, id).then(() => {
        this.getAllHabits()
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
  top: 16vw;
  padding: 2.5vw;
  width: 95%;
  height: 50vw;
  overflow-y: auto;
  overflow-x: hidden;
}

.main-text {
  position: absolute;
  color: white;
  font-size: 4lvw;
  z-index: 1;
  top: 7.7%;
  left: 30%;
}

.current {
  position: absolute;
  color: white;
  font-size: 2.85lvw;
  z-index: 1;
  top: 11vw;
  left: 2.125vw;
}

.circle_1 {
  font-size: 2.025lvw;
  color: darkgreen;
}

.circle_2 {
  font-size: 2.025lvw;
  color: gray;
}

.today {
  font-size: 4.2lvw;
  right: 7vw;
  top: 1.65vw;
}

.today:hover {
  max-width: 4.56vw;
  max-height: 4.5vw;
  border: 0.242lvw solid goldenrod;
  border-radius: 50%;
  color: greenyellow;
  opacity: 30%;
}

.circle_3 {
  font-size: 2.025lvw;
  color: rgba(155, 155, 155, 0.34);
}

.circle_4 {
  font-size: 2.025lvw;
  color: darkred;
}

.track-block {
  margin: 0;
  padding: 0;
  max-width: 60vw;
  max-height: 20vw;
  overflow-y: scroll;
  left: 31vw;
  bottom: 10vw;
}

.today_markup {
  bottom: 3.5vw;
  max-width: 25vw;
}

.tracks {
  display: inline-grid;
  grid-template-columns: repeat(3, 1fr);
  grid-template-rows: repeat(1.2, 1fr);
}

.main-gradient-1 {
  position: absolute;
  top: 3.8vw;
  width: 100vw;
  height: 70vw;
  background: linear-gradient(180deg, #090B13 0%, rgba(9, 11, 19, 0.2) 100%);
  opacity: 60%;
  z-index: 0;
}

.creation {
  bottom: 1.25vw;
  min-height: 10vw;
}

.create_but {
  top: 5.25vw;
  left: 6vw;
}

.check_history {
  bottom: 3.25vw;
  left: 40vw;
}

.edit {
  color: white;
  left: 71.25vw;
  font-size: 3.25lvw;
  bottom: 2.5vw;
}

.edition-menu {
  left: 67.785vw;
  bottom: 13.75vw;
}

.warn-text {
  top: 2.625vw;
  width: 15vw;
  right: 7vw;
  font-size: 1lvw;
}

.t_date {
  font-size: 1lvw;
  right: 2.5vw;
  color: white;
}

.plus {
  top: 1vw;
  font-size: 3.5vw;
  color: white;
}

.add {
  padding-left: 1vw;
  max-width: 13.5vw;
  max-height: 4vw;
  color: white;
}

.add:hover {
  background-color: rgba(0, 0, 0, 0.3);
  border-color: rgba(0, 0, 0, 0);
  transition: 1s;
}

.add-text {
  bottom: 2.56vw;
  left: 4vw;
  font-size: 2.4lvw;
}

::-webkit-scrollbar {
  width: 1.28lvw;
}

/* Track */
::-webkit-scrollbar-track {
  box-shadow: inset 0 0 5px grey;
  border-radius: 0.64lvw;
}

/* Handle */
::-webkit-scrollbar-thumb {
  background: rgba(0, 0, 0, 0.1);
  opacity: 10%;
  border-radius: 0.64lvw;
}

/* Handle on hover */
::-webkit-scrollbar-thumb:hover {
  background: rgba(0, 0, 0, 0.5);
}

.h {
  left: 2.5vw;
}

.lll{
  font-size: 2lvw;
}

.ppp{
  font-size: 1lvw;
}

.kkk{
  font-size: 2.25lvw;
}
</style>