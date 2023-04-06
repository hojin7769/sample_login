<script setup>
import { RouterView, RouterLink } from 'vue-router'
import { useAuthStore } from '@/stores/Auth'
import Login from '@/views/Login.vue'
import HelloWorld from './components/HelloWorld.vue'
import { ref } from 'vue'

const auth = useAuthStore()
const drawer = ref()

if (auth.user != null) {
  auth.check(() => {
    // console.log("check");
  })
} else {
  auth.isLogin = false
}
</script>

<template>
  <v-app id="inspire">
    <v-navigation-drawer v-model="drawer">
      <!--  -->
    </v-navigation-drawer>

    <v-app-bar>
      <v-app-bar-nav-icon @click="drawer = !drawer"> </v-app-bar-nav-icon>

      <v-toolbar-title>Application</v-toolbar-title>
    </v-app-bar>

    <v-main>
      <!--  -->
      <RouterView />
    </v-main>
  </v-app>
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
