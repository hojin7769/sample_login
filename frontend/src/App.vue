<script setup>
import { RouterView, RouterLink } from 'vue-router'
import { useAuthStore } from '@/stores/Auth'
import Login from '@/views/Login.vue'
import HelloWorld from './components/HelloWorld.vue'

const auth = useAuthStore()
</script>

<template>
  <div class="d-flex align-center flex-column" style="height: 100vh">
    <div class="router-page" v-if="!auth.isLogin">
      <Login></Login>
    </div>
    <div class="router-page" v-if="auth.isLogin">
      <RouterView />
    </div>
  </div>
</template>

<style scoped>
.router-page {
  display: flex;
  justify-content: center;
  position: absolute;
  top: 50%;
  transform: translate(0, -50%);
}
</style>
