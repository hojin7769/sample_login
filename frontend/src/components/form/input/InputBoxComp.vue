<template>
  <v-text-field
    :label="label"
    required
    :model-value="model"
    @update:model-value="
      (value) => {
        $emit('update:Model', value)
      }
    "
  ></v-text-field>
</template>
<script setup>
defineProps(['model', 'label'])
defineEmits(['update:Model'])
</script>
