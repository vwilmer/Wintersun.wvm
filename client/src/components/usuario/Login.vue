<template>
  <v-container grid-list-md text-xs-center>
      <v-layout row wrap>
        <v-flex xs6 offset-sm3 mt-2>
          <form @submit.prevent="login"
                autocomplete="off">
            <v-layout column>
              <v-flex>
                <v-text-field
                  name="email"
                  label="Usuario"
                  id="email"
                  type="text"
                  v-model="email"
                  :rules="userNameRules"
                  color="info"
                  required

                  @keyup.enter="login"/>
              </v-flex>
              <v-flex>
                <v-text-field
                  name="password"
                  label="Contraseña"
                  id="password"
                  type="password"
                  v-model="password"
                  :rules="passwordRules"
                  required
                  color="info"

                  @keyup.enter="login"

                  :append-icon="passwordVisible ? 'visibility_off' : 'visibility'"
                  :type="passwordVisible ? 'text' : 'password'"
                  @click:append="passwordVisible = !passwordVisible"/>
              </v-flex>

              <v-alert
                :value="problema"

                type="error">{{problema}}
              </v-alert>

              <v-flex class="text-xs-center" mt-3>
                <v-btn color="primary" type="submit"
                       :disabled="!validar"
                       >
                  <v-icon>info</v-icon>Entrar
                </v-btn>
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
        problema: null,

        // test values
        valid: false,
        userNameRules: [v => !!v || "Escriba el nombre de usuario"],
        passwordRules: [v => !!v || "Escriba la contraseña del usuario"],
        passwordVisible: false,
        loading: false
      }
    },
    methods: {
      async login() {
        try {
          const res = await AuthenticationService.login({
            email: this.email,
            password: this.password
          });

          if (res.data !== undefined) {
            const token = res.data.token;
            console.log(token);
            // set token in state
            this.$store.dispatch('setToken', token);
            // set user in state
            this.$store.dispatch('setUser', res.data.user);
          }

          this.loading = true;
        } catch (e) {
          if (res.data !== undefined) {
            this.problema = e.response.data.error
          }
          this.loading = false;
        }
      }
    },
    computed: {
      validar() {
        if (this.email.length > 0 && this.password.length > 0) {
          // disable
          return this.valid = true
        }  else {
          return this.valid = false
        }
      }
    }
  }
</script>

<style scoped>

</style>
