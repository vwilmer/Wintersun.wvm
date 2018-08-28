<template>
  <v-app>
    <Header></Header>
    <router-view/>
  </v-app>
</template>

<script>
  import Header from './components/header/Header'
export default {
  name: 'App',
  components: {
    Header
  }
}
</script>

<style>

</style>
