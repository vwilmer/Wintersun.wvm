<template>
  <v-container grid-list-md text-xs-center>
      <v-layout row wrap>
        <v-flex xs12 class="text-xs-center">
          <h1 class="red--text">Ingresar</h1>
        </v-flex>

        <v-flex xs6 offset-sm3 mt-2>
          <form @submit.prevent="login" autocomplete="off">
            <v-layout column>

              <v-flex>
                <v-text-field
                  name="email"
                  label="Correo Electrónico"
                  id="email"
                  type="email"
                  v-model="email"
                  required/>
              </v-flex>
              <v-flex>
                <v-text-field
                  name="password"
                  label="Contraseña"
                  id="password"
                  type="password"
                  v-model="password"
                  required/>
              </v-flex>

              <v-alert
                :value="problema"

                type="error">{{problema}}
              </v-alert>

              <v-flex class="text-xs-center" mt-5>
                <v-btn color="primary" type="submit"><v-icon>info</v-icon>Entrar</v-btn>
              </v-flex>

            </v-layout>
          </form>
        </v-flex>
      </v-layout>
  </v-container>
</template>

<script>
  import AuthenticationService from '../../services/AuthenticationService'

  export default {
    name: 'Login',
    data() {
      return {
        email: '',
        password: '',
        problema: null
      }
    },
    methods: {
      async login() {
        try {
          const res = await AuthenticationService.login({
            email: this.email,
            password: this.password
          });
          const token = res.data.token;
          console.log(token)
          // set token in state
          this.$store.dispatch('setToken', token);
          // set user in state
          this.$store.dispatch('setUser', res.data.user);
        } catch (e) {
          this.problema = e.response.data.error
        }
      }
    }
  }
</script>

<style scoped>

</style>
