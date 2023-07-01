<template>
  <footer>
    <div class="footer-block">
      <h3 class="block-name">Navigation</h3>
      <div class="footer-links">
        <a class="footer-link" href="/profile">Profile</a>
        <a class="footer-link" href="/daily">Your Task List</a>
        <a class="footer-link" href="/habits">Your Daily</a>
        <a class="footer-link" href="/login">Login</a>
        <a class="footer-link" href="/signup">Registration</a>
      </div>
    </div>
    <div class="footer-block">
      <h3 class="block-name">Support Us</h3>
      <div class="footer-links">
        <a class="footer-link" href="/donate">Donate</a>
        <a class="footer-link" href="mailto:random@gmail.com">Write Us</a>
      </div>
    </div>
    <div class="footer-block">
      <h3 class="block-name">More Info</h3>
      <div class="footer-links">
        <a class="footer-link" href="/aboutus">About Us</a>
        <a class="footer-link" href="">Terms</a>
        <a class="footer-link" href="">Privacy</a>
        <a class="footer-link" href="/faq">FAQ</a>
        <a class="footer-link" href="">Contact Us</a>
      </div>
    </div>
    <div class="footer-block">
      <h3 class="block-name">Useful Links:</h3>
      <div class="footer-icons">
        <a href="mailto:random@gmail.com">
          <i class="fa-solid fa-envelope"></i>
        </a>
        <a href="https://t.me/dwarf_strong">
          <i class="fa-solid fa-paper-plane"></i>
        </a>
        <a href="">
          <i class="fa-brands fa-instagram"></i>
        </a>
        <a href="">
          <i class="fa-brands fa-linkedin"></i>
        </a>
        <a href="">
          <i class="fa-brands fa-github"></i>
        </a>
      </div>
    </div>
  </footer>
</template>

<script>
export default {
  name: "Footer"
}
</script>

<style>
footer {

  --borderWidth: 2px;
  background: #1D1F20;
  position: relative;
  border-radius: var(--borderWidth);

  bottom: 0;
  width: 100vw;
  min-height: 5.5vw;
  background: linear-gradient(to bottom, #1c1c1c, var(--color-background));
  display: flex;
}

/*footer:after {*/
/*  content: '';*/
/*  position: absolute;*/
/*  top: calc(-1 * var(--borderWidth));*/
/*  left: calc(-1 * var(--borderWidth));*/
/*  height: calc(100% + var(--borderWidth));*/
/*  width: calc(100% + var(--borderWidth));*/
/*  background: linear-gradient(60deg, #525252, #3ad59f, #9a9a9a, rgba(32, 178, 170, 0.45));*/
/*  border-radius: calc(2 * var(--borderWidth));*/
/*  z-index: -1;*/
/*  animation: animatedgradient 3s ease alternate infinite;*/
/*  background-size: 300% 300%;*/
/*}*/


/*@keyframes animatedgradient {*/
/*  0% {*/
/*    background-position: 0 50%;*/
/*  }*/
/*  50% {*/
/*    background-position: 100% 50%;*/
/*  }*/
/*  100% {*/
/*    background-position: 0 50%;*/
/*  }*/
/*}*/

.footer-icons a {
  color: white;
}

.footer-icons {
  display: flex;
  gap: .7vw;
  justify-content: center;
  height: 3lvw;
  top: 2vw;
  right: 8vw;
  font-size: 1.45vw;
  justify-items: center;
}

.footer-block {
  display: flex;
  width: 44vw;
  height: 37vw;
  padding: 0.9vw;
  margin: 0.7vw;
  z-index: 10;
  justify-content: center;
  bottom: 1.2vw;
}

.block-name {
  color: white;
  margin-bottom: 4vw;
  font-size: 1.25vw;
  right: 1%;
}

.footer-link{
  color: white;
  padding-top: 1vw;
  display: flex;
  flex-direction: row;
  text-align: center;
}
.footer-links{
  font-size: 1vw;
  padding-top: 2vw;
  right: 5.1vw;
}

.main-footer-block-content {

}

</style>