<template>
  <div class="image-central-element">
    <div class="main-gradient-1"></div>

<!--    <img src="https://images.fineartamerica.com/images/artworkimages/mediumlarge/2/montana-mountains-mvr-creative.jpg"-->
<!--         class="insane_image ">-->
    <img src ="https://discover.rbcroyalbank.com/wp-content/uploads/banner-small-best-fall-foliage_402x.jpg"
    class ="insane_image">
    <h1 class="main-text1 bolder underlined_cool_text cool-text-color">Find your own destiny </h1>
    <h1 class="main-text2 bolder underlined_cool_text cool-text-color">Manage your time efficent</h1>
    <h2 class="main-text3 bolder cool-text-color">What can we help with today?</h2>
    <h2 class="main-text4 bolder cool-text-color">What advantages you'll get with us?</h2>
    <div class="blocks">
      <div class="block">
        <div class="punkts_group">
          <div class="punkts">
            <i class="fa-solid fa-chess-knight punkt-icon"></i>
            <p class="cool-text-color punkt-text">More self-performance strategies</p>
          </div>
          <div class="punkts">
            <i class="fa-solid fa-water punkt-icon"></i>
            <p class="cool-text-color punkt-text">Reduce your stress and anxiety</p>
          </div>
          <div class="punkts">
            <i class="fa-solid fa-bed punkt-icon"></i>
            <p class="cool-text-color punkt-text">Improve sleep quality</p>
          </div>
          <div class="punkts">
            <i class="fa-solid fa-flag punkt-icon"></i>
            <p class="cool-text-color punkt-text">Set goals and reach it</p>
          </div>
          <div class="punkts">
            <i class="fa-solid fa-leaf punkt-icon"></i>
            <p class="cool-text-color punkt-text">Decombine your big projects or plans into small pieces</p>
          </div>
          <div class="punkts">
            <i class="fa-solid fa-road punkt-icon"></i>
            <p class="cool-text-color punkt-text">Track your habits and your daily</p>
          </div>
        </div>

      </div>

      <div class="block">
        <div class="empty-block"></div>
      </div>
      <div class="block">
        <div class="empty-block"></div>
      </div>
      <div class="block">
        <div class="empty-block"></div>
      </div>
<!--      <div class="block">-->
<!--        <div class="empty-block"></div>-->
<!--      </div>-->
      <div class="block">
        <div class="punkts_group">
          <div class="punkts">
            <i class="fa-solid fa-calendar punkt-icon"></i>
            <p class="cool-text-color punkt-text">You'll improve your time-management</p>

          </div>
          <div class="punkts">
            <i class="fa-brands fa-tiktok punkt-icon"></i>
            <p class="cool-text-color punkt-text">You're available to clear your life from bad habits </p>
          </div>
          <div class="punkts">
            <i class="fa-solid fa-baby punkt-icon"></i>
            <p class="cool-text-color punkt-text">100% Work/Life Balance</p>
          </div>
          <div class="punkts">
            <i class="fa-solid fa-smile punkt-icon"></i>
            <p class="cool-text-color punkt-text">You'll be happier</p>
          </div>
          <div class="punkts">
            <i class="fa-solid fa-brain punkt-icon"></i>
            <p class="cool-text-color punkt-text">You'll clear your minds from unnecessary things</p>
          </div>
        </div>

      </div>
    </div>

  </div>

</template>

<script>
export default {
  name: "MainPage",
  head: {
    meta: [
      // creates a meta description tag in header.
      { name: 'viewport', content: 'width=device-width, initial-scale=1.0' }
    ]
  }
}

</script>

<style scoped>

.image-central-element {
  z-index: -1;
}

.underlined_cool_text {
  text-decoration: underline;
  font-size: 4lvw;
  z-index: 1;
}

.main-text1 {
  position: absolute;
  top: 7.7%;
  left: 1.7%;
}

.main-text2 {
  position: absolute;
  top: 20.7%;
  right: 1.7%;
}

.main-text3 {
  padding-left: 2.5vw;
  font-size: 2.25lvw;
  position: absolute;
  top: 37.5%;
  left: 1.7%;
  color: white;
}

.cool-text-color {
  font-family: "Dubai Medium", serif;
  color: rgba(220, 220, 220, 80);
}

.main-text4 {
  font-size: 2.25lvw;
  position: absolute;
  top: 37.8%;
  right: 6.2%;
  color: white;
}

.punkts_group {
  position: absolute;
  top: 0;
}

.punkts {
  display: flex;
  justify-content: space-between;
  flex-direction: row;
  padding: 0.8vw;
  margin: 1.6vw;
  height: auto;
}

.blocks {
  position: absolute;
  top: 42%;
  left: 3.7vw;
  padding-left: 1.4vw;
}

.block {
  text-align: right;
  top: 45%;
  width: 13.65vw;
  display: inline-flex;
  justify-content: space-between;
  height: 50vw;
}

.empty-block {
  width: 100vw;
  height: 0;
  margin: 0;
  padding: 0;
}

.punkt-icon {
  right: 7vw;
  width: 7vw;
  font-size: 2.025lvw;
  color: white;
}

.punkt-text {
  right: 2.5vw;
  bottom: 0.1vb;
  font-size: 1.2lvw;
  white-space: nowrap;
  text-align: center;
  font-weight: 700;
  color: white;
  display: flex;
  width: 10vw;
}

.main-gradient-1{
  height: 130vh;
}
</style>