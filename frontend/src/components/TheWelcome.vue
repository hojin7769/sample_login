<script setup></script>

<template>
  <div class="checkBoxArea">
    <span>권한선택:</span>
    <div class="checkBoxSubArea">
      <input id="Customer" type="radio" name="value" value="Customer" />
      <label for="Customer">Customer</label>
    </div>
    <div class="checkBoxSubArea">
      <input id="Admin" type="radio" name="value" value="Customer" />
      <label for="Admin">Admin</label>
    </div>
  </div>
</template>
<style scoped>
.checkBoxSubArea {
  display: flex;
}
.checkBoxArea {
  display: flex;
  width: 1000px;
  flex-direction: row;
  column-gap: 10px;
}
.checkBoxSubArea > input {
}
</style>
