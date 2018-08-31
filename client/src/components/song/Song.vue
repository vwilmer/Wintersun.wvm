<template>
  <v-layout row wrap>
    <v-flex xs6 offset-sm3 mt-2>
      <SongPanel titulo="ovejas">
        <!-- call to slot 1 -->
        <div slot="slot_one">
          <h3>YO</h3>
          <p>tag</p>
        </div>
      </SongPanel>
    </v-flex>
  </v-layout>
</template>

<script>
  import SongPanel from './SongPanel'

  export default {
    name: 'Song',
    components: {
      SongPanel
    }
  }
</script>

<style scoped>

</style>
