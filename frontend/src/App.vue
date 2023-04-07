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
  <div class="d-flex align-center flex-column" style="height: 100vh" v-if="!auth.isLogin">
    <div class="router-page">
      <Login></Login>
    </div>
  </div>
  <v-app id="inspire" v-if="auth.isLogin">
    <v-system-bar>
      <v-spacer></v-spacer>

      <v-icon>mdi-square</v-icon>

      <v-icon>mdi-circle</v-icon>

      <v-icon>mdi-triangle</v-icon>
    </v-system-bar>

    <v-app-bar>
      <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>

      <v-toolbar-title>Application</v-toolbar-title>
    </v-app-bar>

    <v-navigation-drawer v-model="drawer" temporary>
      <v-list color="transparent">
        <v-list-item prepend-icon="mdi-view-dashboard" title="Dashboard"></v-list-item>
        <v-list-item prepend-icon="mdi-account-box" title="Account"></v-list-item>
        <v-list-item prepend-icon="mdi-gavel" title="Admin"></v-list-item>
      </v-list>

      <template v-slot:append>
        <div class="pa-2">
          <v-btn block @click="auth.logout()"> Logout </v-btn>
        </div>
      </template>
      <!--  -->
    </v-navigation-drawer>

    <v-main class="bg-grey-lighten-2">
      <v-container>
        <RouterView />
      </v-container>
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
