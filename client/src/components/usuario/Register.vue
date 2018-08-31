<template>
  <!--<v-app>-->
  <v-container grid-list-md text-xs-center>
    <v-layout row wrap>
      <v-flex xs12>
        <v-card dark color="primary">
          <v-card-text class="px-0">12</v-card-text>
        </v-card>
      </v-flex>

      <v-flex xs6>
        <v-layout row wrap>

          <v-flex xs12 mt-2>
            <!--xs12 sm6 offset-sm3-->
            <form @submit.prevent="userSignUp">
              <v-layout column>
                <v-flex>
                  <v-alert type="error" dismissible v-model="alert">
                    {{ error }}
                  </v-alert>
                </v-flex>

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
                <v-flex>
                  <v-text-field
                    name="confirmPassword"
                    label="Confirmar Contraseña"
                    id="confirmPassword"
                    type="password"
                    required
                    v-model="passwordConfirm"
                    :rules="[comparePasswords]"/>
                </v-flex>

                <v-alert
                  :value="problema"

                  type="error">{{problema}}
                </v-alert>

                <v-flex class="text-xs-center" mt-5>
                  <v-btn color="primary" type="submit" :disabled="loading">Registrarte</v-btn>
                </v-flex>

              </v-layout>
            </form>
          </v-flex>
        </v-layout>
      </v-flex>

      <v-flex xs6 style="display: none;">
        <v-layout row wrap>
          <v-flex xs12 class="text-xs-center">
            <h1 class="cyan--text">Formulario</h1>
          </v-flex>

          <v-flex xs12 mt-2>
            <form>
              <v-layout column>
                <v-flex>
                  <v-alert type="error" dismissible v-model="alert">
                    {{ error }}
                  </v-alert>
                </v-flex>

                <v-flex>
                  <v-text-field
                    :rules="[isDisabled]"
                    :disabled="enabledInput1"
                    label="Número de Caso"
                    type="text"
                    v-model="nroCaso"
                    required/>
                </v-flex>
                <v-flex>
                  <v-text-field
                    :rules="[isDisabled]"
                    :disabled="enabledInput2"
                    label="Nombre y Apellidos"
                    type="text"
                    v-model="nombreCompleto"
                    required/>
                </v-flex>

                <v-flex class="text-xs-center" mt-5>
                  <v-btn color="primary" type="submit" :disabled="loading">Registrarte</v-btn>
                </v-flex>

              </v-layout>
            </form>
          </v-flex>
        </v-layout>
      </v-flex>

    </v-layout>
  </v-container>
  <!--</v-app>-->
</template>

<script>
  import AuthenticationService from '../../services/AuthenticationService'

  export default {
    name: 'Register',
    data() {
      return {
        email: '',
        password: '',
        problema: null,
        passwordConfirm: '',
        alert: false,
        nroCaso: '',
        nombreCompleto: '',
        enabledInput1: false,
        enabledInput2: false
      }
    },
    computed: {
      comparePasswords() {
        return this.password === this.passwordConfirm ? true : 'Las contraseñas no coinciden'
      },
      error() {
        // return this.$store.state.error
      },
      loading() {
        // return this.$store.state.loading
      },
      isDisabled() {
        // evaluate whatever you need to determine disabled here...
        if (this.nroCaso.length > 0) {
          // disable
          return this.enabledInput2 = true
        } else if (this.nombreCompleto.length > 0) {
          return this.enabledInput1 = true
        } else if (this.nroCaso.length === 0 || this.nombreCompleto.length === 0) {
          // enable both buttons
          this.enabledInput1 = false
          this.enabledInput2 = false
          return true
        } else {
          return false
        }

      }
    },
    methods: {
      async userSignUp() {
        if (this.comparePasswords !== true) {
          return
        }
        // const res = await AuthenticationService.register({
        //   email: this.email,
        //   password: this.password
        // });
        //
        // console.log(res.data)

        try {
          await AuthenticationService.register({
            email: this.email,
            password: this.password
          });
        } catch (e) {
          this.problema = e.response.data.error
        }
      }
    },
    watch: {
      error(value) {
        if (value) {
          this.alert = true
        }
      },
      alert(value) {
        if (!value) {
          // this.$store.commit('setError', null)
          console.log('error')
        }
      }
    }
  }
</script>

<style scoped>

  @media only screen and (min-width: 960px)

  .container {
    max-width: 1000px;
  }

  v-alert {
    color: #e91e63 !important;
  }

</style>
