import axios from 'axios'
import { useAuthStore } from '@/stores/Auth'

const setup = () => {
  const instance = axios.create()
  const auth = useAuthStore()

  instance.interceptors.request.use(
    function (config) {
      const token = auth.user.accessToken
      try {
        config.headers['Authorization'] = `Bearer ${token}`
        config.headers['UserId'] = auth.user.idUser
        return config
      } catch (err) {
        console.error('[_axios.interceptors.request] config : ' + err)
      }
      return config
    },
    function (error) {
      return Promise.reject(error)
    }
  )
  instance.interceptors.response.use(
    function (response) {
      return response
    },
    function (error) {
      /*
              http status가 200이 아닌 경우
              응답 에러 직전 호출됩니다.
              .catch() 으로 이어집니다.
          */
      return Promise.reject(error)
    }
  )
  return instance
}

export default setup
