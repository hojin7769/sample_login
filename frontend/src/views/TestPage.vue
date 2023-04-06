<script setup>
import { inject, ref } from 'vue'

const axios = inject('axios')
const value = ref()
const testFn = () => {
  axios.post('/api/test/test').then((res) => {
    value.value = res.data
  })
}
testFn()
</script>
<template>
  <div>ewe</div>
</template>
<style scoped>
* {
  justify-items: center;
  align-items: center;
}
</style>
