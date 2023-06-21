<template>
  <div class="main-gradient-1"></div>
  <img class="insane_image" src="https://rare-gallery.com/uploads/posts/922893-landscape-farm-field.jpg">
  <h1 class="main-text bolder cool-text-color">Your Tasks</h1>
  <h2 v-if="isToday()===true" class="current">Your currently tasks, for today:</h2>
  <h2 v-if="isToday()===false" class="current">Your currently tasks, for {{today.toDateString()}}</h2>

  <div class="tasks_table">
    <div class="tasks" v-for="task in getAllDailyTasks()">
      <h1 class="bolder cool-text-color white-color task-text">{{ task.name }}</h1>
      <i v-on:click="unmark(task.daily_id)" v-if="task.done===true" class="fa-solid fa-circle circle_1 circle"></i>
      <i v-on:click="mark(task.daily_id)" v-if="task.done===false" class="fa-solid fa-circle circle_2 circle"></i>
      <i v-on:click="deleteTask(task.id)"
         class="fa-solid fa-minus plus delete"></i>
      <div v-for="subtask in task.subtasks">
        <i v-on:click="unmarkSub(subtask.id)" v-if="subtask.done===true"
           class="fa-solid fa-circle circle_1 sub-circle"></i>
        <i v-on:click="markSub(subtask.id)" v-if="subtask.done===false"
           class="fa-solid fa-circle circle_2 sub-circle"></i>
        <i v-on:click="deleteSubTask(subtask.id)"
           class="fa-solid fa-minus sub-minus sub-delete"></i>
        <h2 class="bolder cool-text-color white-color sub-task-text ">{{ subtask.name }}</h2>
      </div>
      <div v-if="getFromMap(task.id)===false" v-on:click="subhide(task.id)" class="sub-add">
        <i class="fa-solid fa-plus sub-plus"></i>
        <h2 class="bolder cool-text-color white-color task-text sub-add-text">Add Subtask</h2>
      </div>
      <div v-if="getFromMap(task.id)===true" v-on:click="subhide(task.id)" class="sub-add">
        <i class="fa-solid fa-minus sub-plus"></i>
        <h2 class="bolder cool-text-color white-color task-text sub-add-text">Hide</h2>
      </div>
      <div v-if="getFromMap(task.id)===true" class="sub-adding-form">
        <form class="login-form" action="">
          <label class="labels">
            <p class="input-name">Name</p>
            <input id="email" class="loh-input input" v-model="subtaskDTO.name">
          </label>
          <button @click="createSubTask(task.id)" class="form-button">Add</button>
        </form>
      </div>
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
          <input id="email" class="loh-input input" v-model="TaskDTO.name">
        </label>
        <button @click="createTask" class="form-button">Add</button>
      </form>
    </div>
  </div>
  <div class="navigation">
    <h1 class="bolder cool-text-color white-color navigation-text">Navigation</h1>
    <i v-on:click="goLeft" class="fa-solid fa-arrow-left navigation-left"></i>
    <i v-on:click="goRight" class="fa-solid fa-arrow-right navigation-right"></i>
  </div>
</template>

<script>
import TaskService from "@/services/TaskService";

export default {
  name: "Daily",
  data: () => ({
    tasks: [],
    adding: false,
    sub_adding: new Map(),
    TaskDTO: {
      name: "",
      today: ""
    },
    subtaskDTO: {name: ""},
    today: new Date()
  }),
  methods: {
    getAllDailyTasks() {
      if (this.isToday()) {
        TaskService.getAllTodayTasks().then((response) => this.tasks = response.data)
      }
      if (this.sub_adding.size === 0) {
        this.tasks.forEach(task => this.sub_adding.set(task.id, false))
      }
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
    markSub(id) {
      TaskService.markSubTask(id).then(() => {
        this.$router.push('/daily')
      })
    },
    unmarkSub(id) {
      TaskService.unmarkSubTask(id).then(() => {
        this.$router.push('/daily')
      })
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

    getFromMap(id) {
      return this.sub_adding.get(id)
    },

    createTask() {
      // this.TaskDTO.today.setDate(this.TaskDTO.today.getDate()+2)
      TaskService.createTask(this.TaskDTO)
      this.adding = !this.adding;
    },

    createSubTask(id) {
      TaskService.createSubTask(this.subtaskDTO, id)
      if (this.sub_adding.get(id)) {
        this.sub_adding.set(id, false)
      } else {
        this.sub_adding.set(id, true)
      }
    },

    isToday() {
      return this.today.getDate()===new Date().getDate()
    },

    deleteTask(id) {
      TaskService.deleteTask(id)
    },
    deleteSubTask(id) {
      TaskService.deleteSubTask(id)
    },
    goRight() {
      this.today.setDate(this.today.getDate() + 1)
      this.$router.push('/daily')
      return this.today
    },
    goLeft() {
      this.today.setDate(this.today.getDate() - 1)
      this.$router.push('/daily')
      return this.today
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
  overflow-y: auto;
  overflow-x: hidden;
  max-height: 75vh;
  min-height: 25vh;
  min-width: 50vh;
}

.navigation {
  position: absolute;
  top: 27vh;
  left: 150vh;
  justify-content: center;
  place-content: center;
}

.navigation-text {
  left: 10vh;
}

.navigation-left {
  left: 5.5vh;
  font-size: 2em;
  z-index: 2;
}

.navigation-right {
  left: 29vh;
  font-size: 2em;
  z-index: 2;
}

.navigation-left:hover {
  background-color: rgba(0, 0, 0, 0.3);
  border-color: rgba(0, 0, 0, 0);
  transition: 1s;
}

.navigation-right:hover {
  background-color: rgba(0, 0, 0, 0.3);
  border-color: rgba(0, 0, 0, 0);
  transition: 1s;
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

.sub-circle {
  font-size: 1.2em;
  bottom: 2vh;
  left: 10vh;
  border: 1.5px solid black;
  border-radius: 50%;
}

.sub-circle:hover {
  border: 2px solid goldenrod;
  border-radius: 50%;
  color: greenyellow;
  opacity: 30%;

}

.task-text {
  left: 10vh;
  width: 100vh; /* Ширина блока */
  padding-right: 20vh;
  word-wrap: break-word; /* Перенос слов */
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

.sub-plus {
  top: 2vh;
  font-size: 1.5em;
  margin: 0;
  padding: 0;
}

.sub-minus {
  font-size: 1.5em;
  margin: 0;
  padding: 0;
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

.sub-task-text {
  font-size: 20px;
  left: 15vh;
  bottom: 6vh;
}

.sub-add {
  padding-left: 2vh;
  max-width: 30vh;
  max-height: 6vh;
  color: white;
  left: 8vh;
  bottom: 4vh;
}

.sub-add:hover {
  background-color: rgba(0, 0, 0, 0.3);
  border-color: rgba(0, 0, 0, 0);
  transition: 1s;
}

.sub-add-text {
  bottom: 2.2vh;
  font-size: 20px;
  right: 10vh;
}

.sub-adding-form {
  left: 7vh;
  bottom: 6vh;
}

.delete {
  position: absolute;
  left: 90vh;
  bottom: 26vh;
}

.sub-delete {
  z-index: 2;
  position: absolute;
  left: 45vh;
  bottom: 6.55vh;
}


</style>