<script setup>
import { ref } from 'vue'
import JoinFormComp from '@/components/join/JoinFormComp.vue'
import { useAuthStore } from '@/stores/Auth'

const props = defineProps(['dialog'])
const auth = useAuthStore()

defineEmits(['dialogClose'])
const model = ref()
</script>
<template>
  <v-row justify="center">
    <v-dialog v-model="props.dialog" persistent width="500px">
      <template v-slot:activator="{ props }"> </template>
      <JoinFormComp
        @dialogClose="
          (value) => {
            $emit('dialogClose', value)
          }
        "
        @join="
          (value) => {
            auth.join(value)
            $emit('dialogClose', false)
          }
        "
      />
    </v-dialog>
  </v-row>
</template>
