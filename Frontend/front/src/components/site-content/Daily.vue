<template>
  <div class="main-gradient-1"></div>
  <img class="insane_image" src="https://rare-gallery.com/uploads/posts/922893-landscape-farm-field.jpg">
  <h1 class="main-text bolder cool-text-color">Your Tasks</h1>
  <h2 class="current">Your currently tasks, for today:</h2>
  <div class="tasks_table">
    <div class="tasks" v-for="task in getAllDailyTasks()">
      <h1 class="bolder cool-text-color white-color task-text">{{ task.name }}</h1>
      <i v-on:click="unmark(task.daily_id)" v-if="task.done===true" class="fa-solid fa-circle circle_1 circle"></i>
      <i v-on:click="mark(task.daily_id)" v-if="task.done===false" class="fa-solid fa-circle circle_2 circle"></i>
    </div>
    <div v-if="adding===false" v-on:click="hide" class="add">
      <i class="fa-solid fa-plus plus"></i>
      <h2 class="bolder cool-text-color white-color task-text add-text">Add</h2>
    </div>
    <div v-if="adding===true" v-on:click="hide" class="add">
      <i class="fa-solid fa-minus plus"></i>
      <h2 class="bolder cool-text-color white-color task-text add-text">Hide</h2>
    </div>
    <div v-if="adding===true" class="adding-form">
      <form class="login-form" action="">
        <label class="labels">
          <p class="input-name">Name</p>
          <input id="email" class="loh-input input">
        </label>
        <button @click="" class="form-button">Add</button>
      </form>
    </div>
  </div>
</template>

<script>
import TaskService from "@/services/TaskService";

export default {
  name: "Daily",
  data: () => ({
    tasks: [],
    adding: false
  }),
  methods: {
    getAllDailyTasks() {
      TaskService.getAllTodayTasks().then((response) => this.tasks = response.data)
      return this.tasks
    },
    mark(id) {
      TaskService.markTask(id).then(() => {
        this.$router.push('/daily')
      })
    },
    unmark(id) {
      TaskService.unmarkTask(id).then(() => {
        this.$router.push('/daily')
      })
    },
    hide() {
      if(this.adding){
        this.adding = false
      }else{
        this.adding = true
      }

    }
  },

  created() {

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

.tasks_table {
  position: absolute;
  top: 27vh;
  padding: 5vh;
}

.circle_1 {
  color: darkgreen;
}

.circle_2 {
  color: gray;
}

.circle_3 {
  color: darkred;
}

.circle {
  font-size: 1.77em;
  bottom: 5vh;
  border: 2.2px solid black;
  border-radius: 50%;
}

.task-text {
  left: 10vh;
}

.circle:hover {
  border: 3px solid goldenrod;
  border-radius: 50%;
  color: greenyellow;
  opacity: 30%;
}

.plus {
  top: 2vh;
  font-size: 2.25em;
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
  bottom: 5vh;
  font-size: 40px;
}

.labels {
  right: 2vh;
  display: flex;
  padding: 2rem;
}

.loh-input {
  width: 30vh;
  height: 5.5vh;
}

.form-button {
  width: 20vh;
  height: 6.5vh;
  top: 7vh;
  left: 6vh;
}

</style>