import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import AlertPage from '@/js/AlertPage'

export const useAuthStore = defineStore({
  id: 'auth',
  state: () => ({
    user: JSON.parse(localStorage.getItem('user')),
    isLogin: null,
    reissue: false
  }),
  getters: {},
  actions: {
    setUser(user) {
      this.user = user
      localStorage.setItem('user', JSON.stringify(user))
      if (user != null) {
        this.isLogin = true
      } else {
        this.isLogin = false
      }
    },
    login(data, onSuccess, onError) {
      axios
        .post('/api/auth/login', data)
        .then((res) => {
          console.log(res)
          // const user = res.data.result[0];
          const user = res.data.result[0]
          this.setUser(user)

          // onSuccess(res);
        })
        .catch((err) => {
          // onError(err);
          console.log(err)
        })
    },
    join(data) {
      axios.post('/api/auth/join', data).then((res) => {
        console.log(res)
      })
    },
    check() {
      const data = {
        accessToken: this.user.accessToken,
        refreshToken: this.user.refreshToken
      }
      axios
        .post('/api/auth/check', data)
        .then((res) => {
          this.user.accessToken = res.data.result[0].accessToken
          this.user.refreshToken = res.data.result[0].refreshToken
          this.setUser(this.user)
        })
        .catch((err) => {
          this.isLogin = false
        })
    },
    logout() {
      this.setUser(null)
    }
  }
})
