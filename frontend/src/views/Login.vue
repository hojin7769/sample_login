<template>
  <div>
    <h2>Login</h2>
    <form>
      <div>
        <label>Username:</label>
        <input type="text" v-model="user.id" />
      </div>
      <div>
        <label>Password:</label>
        <input type="password" name="password" autocomplete="on" v-model="user.pw" />
      </div>
      <div>
        <button type="submit" @click.prevent="login">Login</button>
        <button type="submit" @click.prevent="join">Join</button>
      </div>
    </form>
  </div>
  {{ auth.isLogin }}
</template>

<script setup>
import { reactive } from 'vue'
import { useAuthStore } from '@/stores/Auth'

const auth = useAuthStore()
const user = reactive({
  id: '',
  pw: ''
})
function login() {
  const data = {
    idUser: user.id,
    pwUser: user.pw
  }
  auth.login(data, null, null)
}

function join() {
  const data = {
    idUser: user.id,
    pwUser: user.pw
  }
  auth.join(data)
}
</script>
