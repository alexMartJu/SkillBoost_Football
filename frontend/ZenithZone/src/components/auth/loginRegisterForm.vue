<template>
    <div class="LoginRegisterForm">
        <main class="main">
            <div class="container">
                <section class="wrapper">
                    <div class="heading" v-if="isLogin">
                        <h1 class="text text-large">Login</h1>
                        <p class="text text-normal">New user? <span><a class="text text-links"
                                    @click="redirect.register()">Create an
                                    account</a></span>
                        </p>
                    </div>
                    <div class="heading" v-else>
                        <h1 class="text text-large">Register</h1>
                        <p class="text text-normal">Have an account? <span><a class="text text-links"
                                    @click="redirect.login()">Sign up</a></span>
                        </p>
                    </div>
                    <div name="signin" class="form">
                        <div class="input-control">
                            <label for="username" class="input-label">Username</label>
                            <input type="username" name="username" id="username" class="input-field"
                                placeholder="Username" v-model="state.username">
                        </div>
                        <div v-if="x$.username.$invalid">
                            <label class="error">Invalid Username.</label>
                            <br>
                            <br>
                        </div>
                        <div class="input-control" v-if="!isLogin">
                            <label for="email" class="input-label">Email Address</label>
                            <input type="email" name="email" id="email" class="input-field" placeholder="Email Address"
                                v-model="state.email">
                        </div>
                        <div v-if="x$.email.$invalid && !isLogin">
                            <label class="error">Invalid EMAIL.</label>
                            <br>
                            <br>
                        </div>
                        <div class="input-control">
                            <label for="password" class="input-label">Password</label>
                            <input type="password" name="password" id="password" class="input-field"
                                placeholder="Password" v-model="state.password">
                        </div>
                        <div v-if="x$.password.$invalid">
                            <label class="error">Invalid Password.</label>
                            <br>
                            <br>
                        </div>
                        <div class="input-control" v-if="!isLogin">
                            <label for="password" class="input-label">Repeat Password</label>
                            <input type="password" name="password2" id="password2" class="input-field"
                                placeholder="Repeat Password" v-model="state.password2">
                        </div>
                        <div v-if="!isLogin && state.password != state.password2">
                            <label class="error">Passwords do not match</label>
                            <br>
                            <br>
                        </div>
                        <div v-if="x$.password2.$invalid && !isLogin">
                            <label class="error">Need confirm password</label>
                            <br>
                            <br>
                        </div>
                        <div>
                            <div class="input-control" v-if="isLogin">
                                <button @click="login()" class="input-submit"
                                    :disabled="v$.username.$invalid || v$.password.$invalid">Login</button>
                            </div>
                        </div>

                        <div>
                            <div class="input-control" v-if="!isLogin">
                                <button @click="register()" class="input-submit"
                                    :disabled="x$.username.$invalid || x$.password.$invalid || x$.email.$invalid || x$.password2.$invalid || state.password2 !== state.password">Register</button>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        </main>
    </div>
</template>

<script>
import { useRouter } from 'vue-router';
import { getCurrentInstance, reactive, computed } from 'vue';
import { useVuelidate } from '@vuelidate/core'
import { required, minLength, email, alphaNum } from '@vuelidate/validators'
export default {
    props: {
        isLogin: Boolean
    },
    emits: {
        send: Object
    },
    setup() {
        const { emit } = getCurrentInstance();
        const router = useRouter();

        const redirect = {
            register: () => router.push({ name: 'register' }),
            login: () => router.push({ name: 'login' }),
        };

        const register = () => {
            const data = {
                username: state.username,
                email: state.email,
                password: state.password,
                password2: state.password2
            };

            emit('send', data);
        }

        const state = reactive({
            username: '',
            email: '',
            password: '',
            password2: '',
        });

        const rules_login = computed(() => ({
            username: {
                required,
                minLength: minLength(2),
                alphaNum
            },
            password: {
                required,
                minLength: minLength(4),
            },
        }))

        const rules_register = computed(() => ({
            username: {
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
                minLength: minLength(4),
            },
            password2: {
                required,
                minLength: minLength(4),
            },
        }))

        //login
        const v$ = useVuelidate(rules_login, state);
        //register
        const x$ = useVuelidate(rules_register, state);

        const login = () => {
            const data = {
                username: state.username,
                password: state.password
            };
            emit('send', data);
        }

        return { redirect, login, register, state, v$, x$ }
    }
}
</script>