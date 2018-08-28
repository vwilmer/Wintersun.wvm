<template>
  <v-toolbar color="primary" dark>
    <v-toolbar-side-icon></v-toolbar-side-icon>
    <v-toolbar-title class="manito"
      @click="navigateTo({name: 'HelloWorld'})">Ovejas</v-toolbar-title>
    <v-spacer></v-spacer>
    <v-toolbar-items class="hidden-sm-and-down">
      <v-btn flat
             v-if="!$store.state.isUserLoggedIn"
             @click="navigateTo({name: 'Login'})">Ingresar</v-btn>

      <v-btn flat
             v-if="!$store.state.isUserLoggedIn"
             @click="navigateTo({name: 'Register'})">Crear Cuenta
      </v-btn>

    </v-toolbar-items>
  </v-toolbar>
</template>

<script>
  export default {
    name: "Header",
    methods: {
      navigateTo(route) {
        this.$router.push(route)
      }
    }
  }
</script>

<style scoped>
.manito {
  cursor: pointer;
}
</style>
