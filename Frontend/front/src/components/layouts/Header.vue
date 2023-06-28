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
      <form action="/signup">
        <button class="try_for_free_button ">
          Try For Free
        </button>
      </form>
    </div>
    <div class="header-part">
      <form action="/login">
        <button class="login_button ">
          Login
        </button>
      </form>

    </div>
    <div class="header-part">
      <router-link class="profile_link" to="/profile"><i class="user-icon fa-solid fa-user-circle fa-3x"></i>
      </router-link>

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

export default {
  name: "Header",
  components: {},
  data: () => ({}),
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

.user-icon {
  top: 0.75vb;
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

.try_for_free_button {
  right: 10vh;
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
  font-weight: bolder;
}

.login_button {
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
  font-weight: bolder;
}

.try_for_free_button:hover {
  background-color: rgba(0, 0, 0, 0.3);
  border-color: rgba(0, 0, 0, 0);
  transition: 1s;
}

.login_button:hover {
  background-color: rgba(0, 0, 0, 0.3);
  border-color: rgba(0, 0, 0, 0);
  transition: 1s;
}

.user-icon:hover {
  color: rgba(0, 0, 0, 70%);
  transition: 0.5s;
}

.header-logo {
  font-size: 1.77em;
}

.profile_link{
  left: 42.5vh;
  width: 100vh;
}

</style>