<template>
  <div class="main-gradient-1"></div>
  <img class="insane_image" src="https://rare-gallery.com/uploads/posts/922893-landscape-farm-field.jpg">
  <h1 class="main-text bolder cool-text-color">Your Tasks</h1>
  <h2 v-if="isToday()===true" class="current">Your currently tasks, for today:</h2>
  <h2 v-if="isToday()===false" class="current">Your currently tasks, for {{ today.toDateString() }}</h2>

  <div class="tasks_table">
    <div class="tasks" v-for="task in tasks">
      <h1 class="bolder cool-text-color white-color task-text">{{ task.name }}</h1>
      <i v-on:click="unmark(task.daily_id)" v-if="task.done===true&&change===0"
         class="fa-solid fa-circle circle_1 circle"></i>
      <i v-on:click="mark(task.daily_id)" v-if="task.done===false&&change===0"
         class="fa-solid fa-circle circle_2 circle"></i>
      <i v-if="task.done===true&&change!==0" class="fa-solid fa-circle circle_1 n_circle"></i>
      <i v-if="task.done===false&&change>0" class="fa-solid fa-circle circle_4 n_circle"></i>
      <i v-if="task.done===false&&change<0" class="fa-solid fa-circle circle_3 n_circle"></i>

      <i v-on:click="deleteTask(task.id)"
         class="fa-solid fa-minus plus delete"></i>
      <i @click="hideEditMenu(task.id)" class="edit fa-solid fa-edit"></i>

      <div v-if="getFromEditMap(task.id)" class="edition-menu">
        <form class="login-form" action="">
          <label class="labels">
            <p class="input-name">Name</p>
            <input id="email" class="loh-input input" v-model="TaskDTO.name">
          </label>
          <button @click="update(task.id)" class="form-button create_but">Update</button>
        </form>
      </div>

      <div v-for="subtask in task.subtasks">
        <i v-on:click="unmarkSub(subtask.id)" v-if="subtask.done===true&&change===0"
           class="fa-solid fa-circle circle_1 sub-circle"></i>
        <i v-on:click="markSub(subtask.id)" v-if="subtask.done===false&&change===0"
           class="fa-solid fa-circle circle_2 sub-circle"></i>
        <i v-if="subtask.done===true&&change!==0"
           class="fa-solid fa-circle circle_1 n_sub-circle"></i>
        <i v-if="subtask.done===false&&change>0"
           class="fa-solid fa-circle circle_4 n_sub-circle"></i>
        <i v-if="subtask.done===false&&change<0"
           class="fa-solid fa-circle circle_3 n_sub-circle"></i>
        <i v-on:click="deleteSubTask(subtask.id)"
           class="fa-solid fa-minus sub-minus sub-delete"></i>
        <i @click="hideSubEditMenu(subtask.id)" class="sub-edit sub-edit-block fa-solid fa-edit"></i>
        <h2 class="bolder cool-text-color white-color sub-task-text ">{{ subtask.name }}</h2>
      </div>

      <div class="edition-menus-block" v-for="subtask in task.subtasks">
        <div v-if="getFromSubEditMap(subtask.id)" class="sub-edition-menu">
          <form class="login-form" action="">
            <label class="labels">
              <p class="input-name sub-input-name">Name</p>
              <input id="email" class="loh-input input sub-input" v-model="subtaskDTO.name">
            </label>
            <button @click="updateSubtask(subtask.id)" class="form-button sub-form-button">Update</button>
          </form>
        </div>
      </div>

      <div v-if="getFromMap(task.id)===false" v-on:click="subhide(task.id)" class="sub-add">
        <i class="fa-solid fa-plus sub-plus"></i>
        <h2 class="bolder cool-text-color white-color task-text sub-add-text">Add Subtask</h2>
      </div>
      <div v-if="getFromMap(task.id)===true" v-on:click="subhide(task.id)" class="sub-add">
        <i class="fa-solid fa-minus sub-plus"></i>
        <h2 class="bolder cool-text-color white-color task-text sub-add-text">Hide</h2>
      </div>
      <div v-if="getFromMap(task.id)===true&&change>=0" class="sub-adding-form">
        <form class="login-form" action="">
          <label class="labels">
            <p class="input-name">Name</p>
            <input id="email" class="loh-input input" v-model="subtaskDTO.name">
          </label>
          <button @click="createSubTask(task.id)" class="form-button">Add</button>
        </form>
      </div>
      <div v-if="getFromMap(task.id)===true&&change<0" class="sub-adding-form">
        <h1 class="bolder cool-text-color white-color n_text">Creating new tasks isn't permitted here</h1>
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
    <div v-if="adding===true&&change>=0" class="adding-form">
      <form class="login-form" action="">
        <label class="labels">
          <p class="input-name">Name</p>
          <input id="email" class="loh-input input" v-model="TaskDTO.name">
        </label>
        <button @click="createTask" class="form-button">Add</button>
      </form>
    </div>
    <div class="adding-form" v-if="adding===true&&change<0">
      <h1 class="bolder cool-text-color white-color n_text">Creating new tasks isn't permitted here</h1>
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
    today: null,
    change: 0,
    editing_hide_map: new Map(),
    sub_editing_hide_map: new Map()
  }),
  methods: {
    getAllDailyTasks() {
      if (this.isToday()) {
        TaskService.getAllTodayTasks().then((response) => {
          this.tasks = response.data
          this.tasks.forEach(task => this.sub_adding.set(task.id, false))
          this.tasks.forEach(task => this.editing_hide_map.set(task.id, false))
          this.tasks.forEach(task => task.subtasks.forEach(subtask => this.sub_editing_hide_map.set(subtask.id, false)))
          console.log(this.editing_hide_map)
        })
      } else {
        let month = this.today.getMonth() + 1
        TaskService.getAllTasksByDate(this.today.getFullYear().toString() + '-' + month.toString() + '-' + this.today.getDate().toString()).then((response) => {
          this.tasks = response.data
          this.tasks.forEach(task => this.sub_adding.set(task.id, false))
          this.tasks.forEach(task => this.editing_hide_map.set(task.id, false))
        })
      }
      this.tasks.forEach(task => this.sub_adding.set(task.id, false))
      return this.tasks
    },
    mark(id) {
      TaskService.markTask(id).then(() => {
        this.getAllDailyTasks()
        this.$router.push('/daily')
      })
    },
    unmark(id) {
      TaskService.unmarkTask(id).then(() => {
        this.getAllDailyTasks()
        this.$router.push('/daily')
      })
    },
    markSub(id) {
      TaskService.markSubTask(id).then(() => {
        this.getAllDailyTasks()
        this.$router.push('/daily')
      })
    },
    unmarkSub(id) {
      TaskService.unmarkSubTask(id).then(() => {
        this.getAllDailyTasks()
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
      if (!this.isToday()) {
        this.TaskDTO.today = this.today
      }
      TaskService.createTask(this.TaskDTO).then(() => {
        this.getAllDailyTasks()
        this.$router.push('/daily')
      })
      this.adding = !this.adding;
    },

    createSubTask(id) {
      TaskService.createSubTask(this.subtaskDTO, id).then(() => {
        this.getAllDailyTasks()
        this.$router.push('/daily')
      })
      if (this.sub_adding.get(id)) {
        this.sub_adding.set(id, false)
      } else {
        this.sub_adding.set(id, true)
      }
    },

    isToday() {
      this.initDate()
      return this.today.getDate() === new Date().getDate()
    },

    deleteTask(id) {
      TaskService.deleteTask(id).then(() => {
        this.getAllDailyTasks()
        this.$router.push('/daily')
      })

    },
    deleteSubTask(id) {
      TaskService.deleteSubTask(id).then(() => {
        this.getAllDailyTasks()
        this.$router.push('/daily')
      })
    },
    goRight() {
      this.change += 1
      this.getAllDailyTasks()
    },
    goLeft() {
      this.change -= 1
      this.getAllDailyTasks()
    },
    initDate() {
      this.today = new Date()
      this.today.setDate(this.today.getDate() + this.change)
      return this.today
    },
    getFromEditMap(id) {
      return this.editing_hide_map.get(id)
    },
    hideEditMenu(id) {
      if (this.editing_hide_map.get(id)) {
        this.editing_hide_map.set(id, false)
      } else {
        this.editing_hide_map.set(id, true)
      }
    },
    getFromSubEditMap(id) {
      return this.sub_editing_hide_map.get(id)
    },

    hideSubEditMenu(id) {
      if (this.sub_editing_hide_map.get(id)) {
        this.sub_editing_hide_map.set(id, false)
      } else {
        this.sub_editing_hide_map.set(id, true)
      }
    },

    update(id) {
      TaskService.updateTask(id, this.TaskDTO).then(() => {
        this.getAllDailyTasks()
        this.$router.push('/daily')
      })
    },
    updateSubtask(id) {
      TaskService.updateSubTask(this.subtaskDTO, id).then(() => {
        this.getAllDailyTasks()
        this.$router.push('/daily')
      })
    }
  },

  created() {
    this.initDate()
    this.getAllDailyTasks()
  }
}
</script>

<style scoped>
.main-text {
  position: absolute;
  color: white;
  font-size: 4lvw;
  z-index: 1;
  top: 8.7%;
  left: 30%;
}

.current {
  position: absolute;
  color: white;
  font-size: 2.275lvw;
  z-index: 1;
  top: 20.5%;
  left: 5vw;
}

.tasks_table {
  position: absolute;
  top: 13.5vw;
  padding: 5vw;
  overflow-y: auto;
  overflow-x: hidden;
  max-height: 32.5vw;
  min-height: 12.5vw;
  min-width: 25vw;
}

.navigation {
  position: absolute;
  top: 13.5vw;
  left: 75vw;
  justify-content: center;
  place-content: center;
}

.navigation-text {
  left: 4.25vw;
  font-size: 2.5lvw;
}

.navigation-left {
  left: 2.75vw;
  font-size: 2.275lvw;
  z-index: 2;
}

.navigation-right {
  left: 14.5vw;
  font-size: 2.275lvw;
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

.circle_1 {
  color: darkgreen;
}

.circle_2 {
  color: gray;
}

.circle_3 {
  color: darkred;
}

.circle_4 {
  font-size: 2.025lvw;
  color: rgba(155, 155, 155, 0.34);
}

.circle {
  font-size: 2.025lvw;
  bottom: 2.5vw;
  border: 0.140vw solid black;
  border-radius: 50%;
}

.n_circle {
  font-size: 2.025lvw;
  bottom: 2.5vw;
  border: 0.140vw solid black;
  border-radius: 50%;
}

.sub-circle {
  font-size: 1.52lvw;
  bottom: 1vw;
  left: 5vw;
  border: 0.096lvw solid black;
  border-radius: 50%;
}

.edit {
  color: white;
  left: 35vw;
  font-size: 3.25lvw;
  bottom: 1.75vw;
}

.sub-edit-block {
  max-width: 2.5lvw;
  z-index: 2;
  left: 36vw;
  bottom: 1.025vw;
}

.sub-edit {
  color: white;
  font-size: 1.875lvw;
}

.sub-circle:hover {
  border: 2px solid goldenrod;
  border-radius: 50%;
  color: greenyellow;
  opacity: 30%;
}

.n_sub-circle {
  font-size: 1.52lvw;
  bottom: 1vw;
  left: 5vw;
  border: 0.096lvw solid black;
  border-radius: 50%;
}

.n_sub-circle:hover {
  border: 0.128lvw solid goldenrod;
  border-radius: 50%;
  color: darkred;
  opacity: 30%;

}

.task-text {
  font-size: 2lvw;
  top: 0.975vw;
  left: 5vw;
  width: 50vw; /* Ширина блока */
  padding-right: 10vw;
  word-wrap: break-word; /* Перенос слов */
}

.circle:hover {
  border: 0.196lvw solid goldenrod;
  border-radius: 50%;
  color: greenyellow;
  opacity: 30%;
}

.n_circle:hover {
  border: 0.196lvw solid goldenrod;
  border-radius: 50%;
  color: darkred;
  opacity: 30%;
}

.plus {
  top: 1vw;
  font-size: 3.25lvw;
}

.sub-plus {
  top: 1vw;
  font-size: 1.875lvw;
  margin: 0;
  padding: 0;
}

.sub-minus {
  color: white;
  font-size: 1.875lvw;
  margin: 0;
  padding: 0;
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
  bottom: 2.5vw;
  font-size: 2.25lvw;
}

.labels {
  right: 1vw;
  display: flex;
  padding: 2vw;
}

.loh-input {
  width: 15vw;
  height: 2.75vw;
}

.form-button {
  width: 10vw;
  height: 3.25vw;
  top: 3.5vw;
  left: 3vw;
}

.sub-task-text {
  font-size: 1.32vw;
  left: 7.5vw;
  bottom: 3vw;
}

.sub-add {
  padding-left: 1vw;
  max-width: 15vw;
  max-height: 3vw;
  color: white;
  left: 4vw;
  bottom: 2vw;
}

.sub-add:hover {
  background-color: rgba(0, 0, 0, 0.3);
  border-color: rgba(0, 0, 0, 0);
  transition: 1s;
}

.sub-add-text {
  bottom: 1.1vw;
  font-size: 1.32lvw;
  right: 5vw;
}

.sub-adding-form {
  left: 3.5vw;
  bottom: 3vw;
}

.delete {
  color: white;
  position: absolute;
  left: 50vw;
  bottom: 13vw;
}

.sub-delete {
  z-index: 2;
  position: absolute;
  left: 32.5vw;
  bottom: 3.2525vw;
}

.n_text {
  top: 2vw;
}

.edition-menu {
  left: 32.25vw;
  bottom: 2.5vw;
  padding-bottom: 5vh;
}

.sub-edition-menu {
  left: 36vw;
  top:3.75vw;
  z-index: 3;
}

.edition-menus-block {
  left: 4vw;
  bottom: 15vw;
  padding-bottom: 1vw;
}

.sub-input-name{
  font-size: 1.15lvw;
}

.sub-input{
  left: 1.125lvw;
  height: 2vw;
  width: 10vw;
}

.sub-form-button{
  width: 9vw;
  height: 2.625vw;
  font-size: 1.15lvw;
}

</style>