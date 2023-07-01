<template>
  <header>
    <div class="header-part">
      <div class="header-link header-logo"><i class="fa-solid fa-tree"></i></div>
      <router-link class="header-link" to="/main">Home</router-link>
      <router-link class="header-link" to="/aboutus">About Us</router-link>
      <router-link class="header-link" to="/faq">FAQ</router-link>
      <router-link class="header-link" to="/donate">Donate</router-link>
    </div>
    <div class="header-part but_part">
      <form action="/signup">
        <button class="try_for_free_button ">
          Try For Free
        </button>
      </form>
    </div>
    <div class="header-part but_part">
      <form action="/login">
        <button class="login_button ">
          Login
        </button>
      </form>

    </div>
    <div class="header-part">
      <router-link class="profile_link" to="/profile"><i class="user-icon fa-solid fa-user-circle fa-x3"></i>
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
  height:4vw;
  position: fixed;
  display: flex;
  justify-content: space-between;
  margin-bottom: 1vw;
  box-shadow: 3px 7px 3px rgba(0, 0, 0, 0.2);
  z-index: 2;
}

.user-icon {
  top: 0.375vw;
  color: white;
}

.header-part {
  display: flex;
  justify-content: space-between;
}

.but_part{
  padding-top: 0.7vw;
}

.header-link {
  display: flex;
  font-size: 1vw;
  padding: 1vw;
  align-items: center;
  justify-content: center;
  color: white;
  margin-right: 5vw;
  font-weight: 700;
}

.try_for_free_button {
  font-size: 1vw;
  right: 5vw;
  bottom: 7.5px;
  color: rgba(255, 255, 255, 80%);
  width: 9.5vw;
  height: 3.125vw;
  text-align: center;
  opacity: 100%;
  border-radius: 45%;
  background-color: transparent;
  border-color: white;
  border-width: 0.32vw;
  font-weight: bolder;
}

.login_button {
  font-size: 1vw;
  right: 7.5vw;
  bottom: 7.5px;
  color: rgba(255, 255, 255, 80%);
  width: 9.5vw;
  height: 3.125vw;
  text-align: center;
  opacity: 100%;
  border-radius: 45%;
  background-color: transparent;
  border-color: white;
  border-width: 0.32vw;
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
  font-size: 2.025lvw;
}

.profile_link{
  right: 9vw;
  bottom: 1.7px;
}

.fa-x3{
  font-size: 3vw;
}

</style>