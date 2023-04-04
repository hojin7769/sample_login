<template>
  <v-container fluid>
    <v-row justify="center">
      <v-col cols="12" md="6" lg="12">
        <v-card>
          <v-card-text>
            <v-sheet width="400" class="mx-auto">
              <v-form fast-fail @submit.prevent="login">
                <v-text-field
                  variant="underlined"
                  v-model="user.id"
                  label="User Name"
                ></v-text-field>

                <v-text-field
                  variant="underlined"
                  v-model="user.pw"
                  label="password"
                ></v-text-field>
                <a href="#" class="text-body-2 font-weight-regular">Forgot Password?</a>

                <v-btn type="submit" variant="outlined" color="primary" block class="mt-2"
                  >Sign in</v-btn
                >
              </v-form>
              <div class="mt-2">
                <p class="text-body-2">
                  Don't have an account? <a href="#" @click="join()">Sign Up</a>
                </p>
              </div>
            </v-sheet>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
    <join-comp
      v-show="false"
      :dialog="dialogData"
      @dialogClose="
        (value) => {
          dialogData = value
        }
      "
    ></join-comp>
  </v-container>
</template>

<script setup>
import { reactive, ref } from 'vue'
import { useAuthStore } from '@/stores/Auth'
import JoinComp from '@/components/join/JoinComp.vue'

const auth = useAuthStore()
const dialogData = ref(false)
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
  dialogData.value = true
}
</script>
<style>
/* 가운데 정렬 */
.text-center {
  text-align: center;
  width: 100vw;
}

/* 카드 테두리 스타일 */
.v-card {
  border-radius: 8px;
  box-shadow: 0px 3px 6px rgba(0, 0, 0, 0.16);
}
</style>
