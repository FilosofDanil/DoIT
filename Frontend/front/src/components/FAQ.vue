<template>
  <div class="main-gradient-1"></div>
  <h1 class="main-text bolder cool-text-color">Frequently asked questions </h1>
  <img class="insane_image" src="https://d39raawggeifpx.cloudfront.net/styles/16_9_desktop/s3/articleimages/0918_KAZAKHSTAN_near_Kokshetau_grain_fields_BNE_Generic_Agriculture_wheat_Farming_Cropped_0.jpg">
  <div class="question-blocks">
    <div class="question-block">
      <h3 class="question-title">Question1</h3>
      <p class="answer">FEfewfewfwef fewfewfwef wefwefewfwefew fewfewfewfwe fewfwefwefwef ewfwfewfwef fewfewfewfew fewfwefewf ewfewfwefwefew fwefewfewfwefw efewfewfwefewfwe fwefwefwef</p>
    </div>
    <div class="question-block">
      <h3 class="question-title">Question2</h3>
      <p class="answer">FEfewfewfwef fewfewfwef wefwefewfwefew fewfewfewfwe fewfwefwefwef ewfwfewfwef fewfewfewfew fewfwefewf ewfewfwefwefew fwefewfewfwefw efewfewfwefewfwe fwefwefwef</p>
    </div>
    <div class="question-block">
      <h3 class="question-title">Question3</h3>
      <p class="answer">FEfewfewfwef fewfewfwef wefwefewfwefew fewfewfewfwe fewfwefwefwef ewfwfewfwef fewfewfewfew fewfwefewf ewfewfwefwefew fwefewfewfwefw efewfewfwefewfwe fwefwefwef</p>
    </div>
    <div class="question-block">
      <h3 class="question-title">Question4</h3>
      <p class="answer">FEfewfewfwef fewfewfwef wefwefewfwefew fewfewfewfwe fewfwefwefwef ewfwfewfwef fewfewfewfew fewfwefewf ewfewfwefwefew fwefewfewfwefw efewfewfwefewfwe fwefwefwef</p>
    </div>
    <div class="question-block">
      <h3 class="question-title">Question5</h3>
      <p class="answer">FEfewfewfwef fewfewfwef wefwefewfwefew fewfewfewfwe fewfwefwefwef ewfwfewfwef fewfewfewfew fewfwefewf ewfewfwefwefew fwefewfewfwefw efewfewfwefewfwe fwefwefwef</p>
    </div>
    <div class="question-block">
      <h3 class="question-title">Question6</h3>
      <p class="answer">FEfewfewfwef fewfewfwef wefwefewfwefew fewfewfewfwe fewfwefwefwef ewfwfewfwef fewfewfewfew fewfwefewf ewfewfwefwefew fwefewfewfwefw efewfewfwefewfwe fwefwefwef</p>
    </div>

  </div>
</template>

<script>
export default {
  name: "FAQ"
}
</script>

<style scoped>

.main-text {
  position: absolute;
  color: white;
  font-size: 60px;
  z-index: 1;
  top: 8.7%;
  left: 22.5%;
}
.question-blocks{
  position: absolute;
  top: 20%;
  left:5%;
  width: 180vh;
  height: 80vh;
  display: inline-grid;
  direction: initial;
  gap: 1rem;
  column-gap: 1rem;
  row-gap: 1rem;

  grid-template-columns: 1fr 1fr 1fr;
}

.question-block{
 border: 3px solid goldenrod;
  /*border-color: goldenrod;*/
  /*border-width: 5px;*/
  border-radius: 10%;
  text-align: left;
  width: 60vh;
  height: 33vh;
  transition: 0.5s;
  background: rgba(28, 28, 28, 0.4);
}

.question-block:hover{
  border: hidden;
  transition: 0.5s;
  transform: scale(1.25);
  background: rgba(28, 28, 28, 0.9);
  z-index: 2;
}
.question-title{
  color: white;
  text-align: center;
  font-weight: 700;
}
.answer{
  color: white;
  text-align: center;
  font-size: 18px;
  font-weight: 400;
}
</style>