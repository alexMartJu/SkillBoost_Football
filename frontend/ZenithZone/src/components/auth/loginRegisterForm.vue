<template>
    <div class="auth-container bg-light">
      <div class="container">
        <div class="row justify-content-center align-items-center min-vh-100">
          <div class="col-12 col-md-6 col-lg-5">
            <div class="card shadow-lg border-0 rounded-4 p-4">
              <div class="card-body">
                <div class="text-center mb-4">
                  <img src="/assets/logo_3.png" alt="ZenithZone" class="img-fluid mb-3" style="width: 80px;" />
                  <h1 class="h3 fw-bold text-dark">{{ isLogin ? 'Bienvenido de nuevo' : 'Únete a ZenithZone' }}</h1>
                  <p class="text-muted">
                    {{ isLogin ? 'Inicia sesión para continuar' : 'Crea tu cuenta' }}
                  </p>
                </div>
  
                <form @submit.prevent="handleSubmit">
                  <!-- Campos para Register -->
                  <div v-if="!isLogin" class="row g-3 mb-3">
                    <div class="col-md-6">
                      <div class="form-group">
                        <label for="nombre" class="form-label fw-medium">Nombre</label>
                        <input 
                          type="text" 
                          id="nombre" 
                          v-model="state.nombre"
                          class="form-control form-control-lg"
                          :class="{'is-invalid': x$.nombre.$invalid && state.nombre.length > 0}"
                        />
                        <div class="invalid-feedback" v-if="x$.nombre.$invalid && state.nombre.length > 0">
                          Nombre inválido (min 2 caracteres)
                        </div>
                      </div>
                    </div>
  
                    <div class="col-md-6">
                      <div class="form-group">
                        <label for="apellidos" class="form-label fw-medium">Apellidos</label>
                        <input 
                          type="text" 
                          id="apellidos" 
                          v-model="state.apellidos"
                          class="form-control form-control-lg"
                          :class="{'is-invalid': x$.apellidos.$invalid && state.apellidos.length > 0}"
                        />
                        <div class="invalid-feedback" v-if="x$.apellidos.$invalid && state.apellidos.length > 0">
                          Apellido inválido (min 2 caracteres)
                        </div>
                      </div>
                    </div>
                    
                    <!-- Edad -->
                    <div class="col-md-12">
                      <div class="form-group">
                        <label for="edad" class="form-label fw-medium">Edad</label>
                        <input 
                          type="number" 
                          id="edad" 
                          v-model.number="state.edad"
                          class="form-control form-control-lg"
                          :class="{'is-invalid': x$.edad.$invalid && state.edad !== null}"
                          min="4"
                          max="120"
                        />
                        <div class="invalid-feedback" v-if="x$.edad.$invalid && state.edad !== null">
                          La edad debe ser mayor a 3 años
                        </div>
                      </div>
                    </div>
                  </div>
  
                  <!-- Campos comunes -->
                  <div class="form-group mb-3">
                    <label for="email" class="form-label fw-medium">Email</label>
                    <div class="input-group input-group-lg has-validation">
                      <span class="input-group-text bg-light">
                        <i class="bi bi-envelope"></i>
                      </span>
                      <input 
                        type="email" 
                        id="email" 
                        v-model="state.email"
                        class="form-control"
                        :class="{'is-invalid': x$.email.$invalid && state.email.length > 0}"
                      />
                      <div class="invalid-feedback" v-if="x$.email.$invalid && state.email.length > 0">
                        Email inválido
                      </div>
                    </div>
                  </div>
  
                  <div class="form-group mb-3">
                    <label for="password" class="form-label fw-medium">Contraseña</label>
                    <div class="input-group input-group-lg has-validation">
                      <span class="input-group-text bg-light">
                        <i class="bi bi-lock"></i>
                      </span>
                      <input 
                        :type="showPassword ? 'text' : 'password'"
                        id="password" 
                        v-model="state.password"
                        class="form-control"
                        :class="{'is-invalid': x$.password.$invalid && state.password.length > 0}"
                      />
                      <div class="invalid-feedback" v-if="x$.password.$invalid && state.password.length > 0">
                        Contraseña inválida
                      </div>
                    </div>
                  </div>
  
                  <!-- Confirmar Contraseña para el Register -->
                  <div v-if="!isLogin" class="form-group mb-3">
                    <label for="password2" class="form-label fw-medium">Confirmar Contraseña</label>
                    <div class="input-group input-group-lg has-validation">
                      <span class="input-group-text bg-light">
                        <i class="bi bi-lock"></i>
                      </span>
                      <input 
                        :type="showPassword ? 'text' : 'password'"
                        id="password2" 
                        v-model="state.password2"
                        class="form-control"
                        :class="{'is-invalid': (x$.password2.$invalid && state.password2.length > 0) || (state.password2 !== state.password && state.password2.length > 0)}"
                      />
                      <div class="invalid-feedback" v-if="x$.password2.$invalid && state.password2.length > 0">
                        Contraseña inválida (min 3 caracteres)
                      </div>
                      <div class="invalid-feedback" v-if="state.password2 !== state.password && state.password2.length > 0">
                        Las contraseñas no coinciden
                      </div>
                    </div>
                  </div>
  
                  <!-- Mensajes de error -->
                  <div class="alert alert-danger" v-if="errorMessage">
                    {{ errorMessage }}
                  </div>
                  <div class="alert alert-success" v-if="!isLogin && registerSuccesful">
                    ¡Registro exitoso!
                  </div>
  
                  <!-- Submit Button -->
                  <button 
                    type="submit" 
                    class="btn btn-primary btn-lg w-100 mb-3"
                    :disabled="isLogin ? v$.email.$invalid || v$.password.$invalid : 
                              x$.nombre.$invalid || x$.apellidos.$invalid || x$.email.$invalid || 
                              x$.password.$invalid || x$.password2.$invalid || x$.edad.$invalid || 
                              state.password2 !== state.password"
                  >
                    {{ isLogin ? 'Iniciar Sesión' : 'Crear Cuenta' }}
                  </button>
  
                  <!-- Toggle Login/Register -->
                  <p class="text-center text-muted mb-0">
                    {{ isLogin ? "¿No tienes una cuenta?" : '¿Ya tienes una cuenta?' }}
                    <a href="#" class="text-primary fw-bold ms-1" @click.prevent="isLogin ? redirect.register() : redirect.login()">
                      {{ isLogin ? 'Registrarse' : 'Iniciar Sesión' }}
                    </a>
                  </p>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>

<script>
import { useRouter } from 'vue-router';
import { getCurrentInstance, reactive, computed, watch, ref } from 'vue';
import { useVuelidate } from '@vuelidate/core'
import { required, minLength, email, alphaNum, minValue } from '@vuelidate/validators'

export default {
    props: {
        isLogin: Boolean,
        errorMessage: String,
        registerSuccesful: Boolean
    },
    emits: {
        send: Object
    },
    setup(props) {
        const { emit } = getCurrentInstance();
        const router = useRouter();
        const showPassword = ref(false);

        const redirect = {
            register: () => router.push({ name: 'register' }),
            login: () => router.push({ name: 'login' }),
        };

        const register = () => {
            const data = {
                nombre: state.nombre,
                apellidos: state.apellidos,
                email: state.email,
                password: state.password,
                edad: state.edad
            };

            emit('send', data);
        }

        const state = reactive({
            nombre: '',
            apellidos: '',
            email: '',
            password: '',
            password2: '',
            edad: null
        });

        const rules_login = computed(() => ({
            email: {
                required,
                minLength: minLength(2),
                email
            },
            password: {
                required,
                minLength: minLength(3),
            },
        }))

        const rules_register = computed(() => ({
            nombre: {
                required,
                minLength: minLength(2),
            },
            apellidos: {
                required,
                minLength: minLength(2),
            },
            email: {
                required,
                minLength: minLength(2),
                email
            },
            password: {
                required,
                minLength: minLength(3),
            },
            password2: {
                required,
                minLength: minLength(3),
            },
            edad: {
                required,
                minValue: minValue(4)
            }
        }))

        //login
        const v$ = useVuelidate(rules_login, state);
        //register
        const x$ = useVuelidate(rules_register, state);

        const login = () => {
            const data = {
                email: state.email,
                password: state.password
            };
            emit('send', data);
        }

        const handleSubmit = () => {
            if (props.isLogin) {
                if (!v$.value.$invalid) {
                    login();
                }
            } else {
                if (!x$.value.$invalid && state.password === state.password2) {
                    register();
                }
            }
        }

        watch(() => props.registerSuccesful, (Reset) => {
            if (Reset) {
                state.nombre = '';
                state.apellidos = '';
                state.email = '';
                state.password = '';
                state.password2 = '';
                state.edad = null;
            }
        });

        return { redirect, login, register, state, v$, x$, handleSubmit, showPassword }
    }
}
</script>


<style scoped>
.auth-container {
  min-height: 100vh;
  background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
}

.card {
  transition: all 0.3s ease;
}

.form-control:focus {
  box-shadow: 0 0 0 0.25rem rgba(var(--bs-primary-rgb), 0.25);
}
</style>
