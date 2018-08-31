<template>
  <v-layout>
    <v-flex>
      <v-toolbar flat class="orange elevation-2" dark>
        <v-toolbar-title class="text-capitalize">
          {{titulo}}
        </v-toolbar-title>
      </v-toolbar>

      <div class="white elevation-2 pa-4">
        <slot name="slot_one">
          1 sin contenido
        </slot>

        <slot name="slot_two">
          2 sin contenido
        </slot>
      </div>
    </v-flex>
  </v-layout>
</template>

<script>
  export default {
    name: 'SongPanel',
    props: [
      'titulo'
    ]
  }
</script>

<style scoped>

</style>
