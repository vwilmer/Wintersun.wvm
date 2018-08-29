<template>
  <v-toolbar color="primary" dark>
    <v-toolbar-side-icon></v-toolbar-side-icon>
    <v-toolbar-title class="manito"
      @click="navigateTo({name: 'HelloWorld'})">Ovejas</v-toolbar-title>

    <v-toolbar-items class="hidden-sm-and-down">
      <v-btn flat
             @click="navigateTo({name: 'Song'})">Explorar
      </v-btn>
    </v-toolbar-items>

    <v-spacer></v-spacer>
    <v-toolbar-items class="hidden-sm-and-down">
      <v-btn flat
             v-if="!$store.state.isUserLoggedIn"
             @click="navigateTo({name: 'Login'})">Ingresar</v-btn>

      <v-btn flat
             v-if="!$store.state.isUserLoggedIn"
             @click="navigateTo({name: 'Register'})">Crear Cuenta
      </v-btn>

      <v-btn flat
             v-if="$store.state.isUserLoggedIn"
             @click="logout">Salir
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
      },
      logout() {
        // set token null
        this.$store.dispatch('setToken', null);
        // set user null
        this.$store.dispatch('setUser', null);

        // redirect to
        this.$router.push({
          name: 'HelloWorld'
        });
        // does not the same
        // this.$router.push('HelloWorld');
      }
    }
  }
</script>

<style scoped>
.manito {
  cursor: pointer;
}
</style>
