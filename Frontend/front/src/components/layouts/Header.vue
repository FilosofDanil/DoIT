<template>
  <header>
    <div class="header-part">
      <div class="header-link header-logo"><i class="fa-solid fa-tree"></i></div>
      <router-link class="header-link" to="/main">Home</router-link>
      <router-link class="header-link" to="/aboutus">About Us</router-link>
      <router-link class="header-link" to="/faq">FAQ</router-link>
      <router-link class="header-link" to="/donate">Donate</router-link>
    </div>
    <div class="header-part">
      <button class="try_for_free_button ">
        Try For Free
      </button>
    </div>
    <div class="header-part">
      <button class="login_button ">
        Login
      </button>
    </div>
    <div class="header-part">
      <i class = "user-icon fa-solid fa-user-circle fa-3x"></i>
    </div>
<!--    <div class="sidebar-menu">-->
<!--      <i class="fa-solid fa-bars header-link burger" @click="this.showSidebar=!this.showSidebar"></i>-->
<!--      <Transition name="slide-fade">-->
<!--        <Sidebar @click="this.showSidebar=!this.showSidebar"-->
<!--                 :routelinks="this.routeLinks"-->
<!--                 v-if="this.showSidebar"></Sidebar>-->
<!--      </Transition>-->
<!--      <div v-if="this.showSidebar" @click="this.showSidebar=!this.showSidebar" class="sidebar-dark"></div>-->
<!--      -->
<!--    </div>-->
  </header>
</template>

<script>
import Sidebar from "./Sidebar.vue";

export default {
  name: "Header",
  components: {
    Sidebar
  },
  data: () => ({
    showSidebar: false,
    routeLinks: [
      {'link': "/faculty", 'name': 'Faculty'},
      {'link': "/department", 'name': 'Departments'},
      {'link': "/group", 'name': 'Groups'},
      {'link': "/student", 'name': 'Students'},
      {'link': "/teacher", 'name': 'Teachers'},
      {'link': "/new", 'name': 'News'},
      {'link': "/schedule", 'name': 'Schedule'},
      {'link': "/discipline", 'name': 'Disciplines'}
    ]
  }),
  methods: () => ({})


}
</script>


<style>

header {
  width: 100vw;
  position: fixed;
  display: flex;
  justify-content: space-between;
  margin-bottom: 1rem;
  box-shadow: 3px 7px 3px rgba(0, 0, 0, 0.2);
  z-index: 2;
}

.user-icon{
  right:10vh;
  top:0.75vb;
  color: white;
}

.header-part {
  display: flex;
  justify-content: space-between;
}

.header-link {
  display: flex;
  padding: 1rem;
  align-items: center;
  justify-content: center;
  color: white;
  margin-right: 10vh;
  font-weight: 700;
}

.try_for_free_button{
  right:10vh;
  top: 5px;
  padding: 0.5rem;
  color: rgba(255,255,255,80%);
  width: 19vh;
  height: 6.25vb;
  text-align: center;
  opacity: 100%;
  border-radius: 45%;
  background-color: transparent;
  border-color: white;
  border-width: 5px;
  font-weight: bolder;
}

.login_button{
  right:15vh;
  top: 5px;
  padding: 0.5rem;
  color: rgba(255,255,255,80%);
  width: 19vh;
  height: 6.25vb;
  text-align: center;
  opacity: 100%;
  border-radius: 45%;
  background-color: transparent;
  border-color: white;
  border-width: 5px;
  font-weight: bolder;
}

.try_for_free_button:hover{
  background-color: rgba(0, 0, 0, 0.3);
  border-color: rgba(0, 0, 0, 0);
  transition: 1s;
}
.login_button:hover{
  background-color: rgba(0, 0, 0, 0.3);
  border-color: rgba(0, 0, 0, 0);
  transition: 1s;
}

.user-icon:hover{
  color: rgba(0,0,0,70%);
  transition: 0.5s;
}

.header-logo{
  font-size: 1.77em;
}

</style>