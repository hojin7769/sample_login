import { createApp } from 'vue'
import { createPinia } from 'pinia'
import customAxios from '@/interceptors/Axios'

import App from './App.vue'

import './assets/main.css'
import router from '@/router'

// Vuetify
import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import axios from 'axios'

const vuetify = createVuetify({
  components,
  directives
})

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.use(vuetify)

app.config.globalProperties.$axios = axios
app.provide('axios', customAxios())

app.mount('#app')
