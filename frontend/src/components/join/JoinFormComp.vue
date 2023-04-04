<script setup>
import InputBoxComp from '@/components/form/input/InputBoxComp.vue'
import { reactive, ref } from 'vue'
import axios from 'axios'

const joinData = reactive({
  idUser: '',
  pwUser: '',
  pwcheck: '',
  nmUser: ''
})

const pwCheck = () => {}
defineExpose({ joinData })
defineEmits(['dialogClose', 'join'])
</script>
<template>
  <v-card>
    <v-card-title>
      <span class="text-h5">회원가입</span>
    </v-card-title>
    <v-card-text>
      <v-container>
        <v-row>
          <v-col cols="12">
            <InputBoxComp
              :model="joinData.idUser"
              label="ID"
              @update:Model="
                (value) => {
                  joinData.idUser = value
                }
              "
            ></InputBoxComp>
          </v-col>
          <v-col cols="12">
            <InputBoxComp
              :model="joinData.pwUser"
              label="PW"
              @update:Model="
                (value) => {
                  joinData.pwUser = value
                }
              "
            ></InputBoxComp>
          </v-col>
          <v-col cols="12">
            <InputBoxComp
              :model="joinData.pwcheck"
              label="PW 확인"
              @update:Model="
                (value) => {
                  joinData.pwcheck = value
                }
              "
              @focusout="pwCheck()"
            ></InputBoxComp>
          </v-col>
          <v-col cols="12">
            <InputBoxComp
              :model="joinData.nmUser"
              label="이름"
              @update:Model="
                (value) => {
                  joinData.nmUser = value
                }
              "
            ></InputBoxComp>
          </v-col>
        </v-row>
      </v-container>
      <small>*indicates required field</small>
    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn
        color="blue-darken-1"
        variant="text"
        @click="
          () => {
            $emit('dialogClose', false)
          }
        "
      >
        Close</v-btn
      >
      <v-btn
        color="blue-darken-1"
        variant="text"
        @click="
          () => {
            $emit('join', joinData)
          }
        "
      >
        Save</v-btn
      >
    </v-card-actions>
  </v-card>
</template>
