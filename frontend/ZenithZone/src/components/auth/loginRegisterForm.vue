<template>
    <div class="LoginRegisterForm">
        <main class="main py-5">
            <div class="container">
                <!-- Form Wrapper -->
                <div class="row justify-content-center">
                    <div class="col-md-6 col-lg-4">
                        <section class="wrapper">
                            <div class="text-center mb-4" v-if="isLogin">
                                <h1 class="h3 mb-3 fw-normal">Login</h1>
                                <p class="text-muted">New user? <span><a href="javascript:void(0)" class="text-primary"
                                            @click="redirect.register()">Create an
                                            account</a></span></p>
                            </div>
                            <div class="text-center mb-4" v-else>
                                <h1 class="h3 mb-3 fw-normal">Register</h1>
                                <p class="text-muted">Have an account? <span><a href="javascript:void(0)"
                                            class="text-primary" @click="redirect.login()">Sign in</a></span></p>
                            </div>
                            <form name="signin" class="needs-validation" @submit.prevent="handleSubmit" @keyup.enter="handleSubmit">
                                <div v-if="!isLogin" class="form-floating mb-3">
                                    <input type="text" name="nombre" id="nombre" class="form-control"
                                        placeholder="Nombre" v-model="state.nombre" required />
                                    <label for="nombre">Nombre</label>
                                    <div v-if="x$.nombre.$dirty && x$.nombre.$invalid && state.nombre.length > 0" class="text-danger small">
                                        Invalid nombre.
                                    </div>
                                </div>
                                <div v-if="!isLogin" class="form-floating mb-3">
                                    <input type="text" name="apellidos" id="apellidos" class="form-control"
                                        placeholder="Apellidos" v-model="state.apellidos" required />
                                    <label for="apellidos">Apellidos</label>
                                    <div v-if="x$.apellidos.$invalid && state.apellidos.length > 0" class="text-danger small">
                                        Invalid apellidos.
                                    </div>
                                </div>
                                <div class="form-floating mb-3">
                                    <input type="email" name="email" id="email" class="form-control"
                                        placeholder="Email Address" v-model="state.email" required />
                                    <label for="email">Email Address</label>
                                    <div v-if="x$.email.$invalid && state.email.length > 0" class="text-danger small">
                                        Invalid EMAIL.
                                    </div>
                                </div>
                                <div class="form-floating mb-3">
                                    <input type="password" name="password" id="password" class="form-control"
                                        placeholder="Password" v-model="state.password" required />
                                    <label for="password">Password</label>
                                    <div v-if="x$.password.$dirty && x$.password.$invalid && state.password.length > 0" class="text-danger small">
                                        Invalid Password.
                                    </div>
                                </div>
                                <div v-if="!isLogin" class="form-floating mb-3">
                                    <input type="password" name="password2" id="password2" class="form-control"
                                        placeholder="Repeat Password" v-model="state.password2" required />
                                    <label for="password2">Repeat Password</label>
                                    <div v-if="x$.password2.$dirty && x$.password2.$invalid && state.password2.length > 0" class="text-danger small">
                                        Invalid Password.
                                    </div>
                                    <div v-if="!isLogin && state.password !== state.password2 && state.password2.length > 0" class="text-danger small">
                                        Passwords do not match.
                                    </div>
                                </div>
                                <div class="alert alert-danger" v-if="errorMessage">
                                    {{ errorMessage }}
                                </div>
                                <div v-if="!isLogin">
                                    <div v-if="registerSuccesful" class="alert alert-success text-center">usuario registrado correctamente</div>
                                </div>
                                <div class="d-grid">
                                    <button v-if="isLogin" type="submit" class="btn btn-primary"
                                        :disabled="v$.email.$invalid || v$.password.$invalid">Login</button>
                                    <button v-if="!isLogin" type="submit" class="btn btn-primary"
                                        :disabled="x$.nombre.$invalid || x$.apellidos.$invalid || x$.email.$invalid || x$.password.$invalid || x$.password2.$invalid || state.password2 !== state.password">Register</button>
                                </div>
                            </form>
                        </section>
                    </div>
                </div>
            </div>
        </main>
    </div>
</template>

<script>
import { useRouter } from 'vue-router';
import { getCurrentInstance, reactive, computed, watch } from 'vue';
import { useVuelidate } from '@vuelidate/core'
import { required, minLength, email, alphaNum } from '@vuelidate/validators'

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
            };

            emit('send', data);
        }

        const state = reactive({
            nombre: '',
            apellidos: '',
            email: '',
            password: '',
            password2: '',
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
            }
        });

        return { redirect, login, register, state, v$, x$, handleSubmit }
    }
}
</script>