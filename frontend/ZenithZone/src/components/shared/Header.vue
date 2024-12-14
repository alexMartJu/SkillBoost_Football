<template>
    <header class="header fixed-top shadow-sm">
        <div class="container-fluid">
            <div class="d-flex justify-content-between align-items-center p-3">
                <!-- Logo or App Name -->
                <div class="text-color fw-bold h2 mb-0 ms-3" @click="redirects.home">
                    <img src="/assets/logo_2.png" alt="Logo" class="logo">
                </div>

                <!-- Navigation Links -->
                <nav>
                    <ul class="nav me-5">
                        <li class="nav-item me-2">
                            <a @click="redirects.home" class="nav-link text-color fw-bold fs-5" 
                                :class="{ isActive: isHome }">
                                Home
                            </a>
                        </li>
                        <li class="nav-item me-2">
                            <a @click="redirects.instalaciones" class="nav-link text-color fw-bold fs-5" 
                                :class="{ isActive: isInstalaciones }">
                                Instalaciones
                            </a>
                        </li>
                        <li class="nav-item me-2">
                            <a @click="redirects.servicios" class="nav-link text-color fw-bold fs-5" 
                                :class="{ isActive: isServicios }">
                                Servicios
                            </a>
                        </li>
                        <li class="nav-item me-2">
                            <a @click="redirects.entrenadores" class="nav-link text-color fw-bold fs-5" 
                                :class="{ isActive: isEntrenadores }">
                                Entrenadores
                            </a>
                        </li>
                        <li v-if="state.user.nombre" class="nav-item d-flex align-items-center ms-5">
                            <img :src="state.user.image" alt="" class="profile-image">
                            <a @click="redirects.entrenadores" class="nav-link text-color fw-bold fs-5" 
                                :class="{ isActive: isProfile }">
                                {{ state.user.nombre }}
                            </a>
                        </li>
                        <li v-if="!state.isLogged" class="nav-item ms-4">
                            <a @click="redirects.login" class="nav-link auth fw-bold fs-5" 
                                :class="{ isActive: isLogin }">
                                Unirse al club
                            </a>
                        </li>
                        <li v-if="state.isLogged" class="nav-item ms-4">
                            <a @click="logout" class="nav-link auth fw-bold fs-5">
                                Cerrar sesión
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </header>
</template>


<script>
import Constant from '@/Constant';
import { computed, onMounted, watch } from 'vue';
import { reactive } from 'vue';
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';

export default {
    name: "Header",

    computed: {
        isHome() {
            return this.$route.name === 'home';
        },
        isInstalaciones() {
            return this.$route.name === 'instalaciones';
        },
        isServicios() {
            return this.$route.path.startsWith('/servicios');
        },
        isEntrenadores() {
            return this.$route.name === 'entrenadores';
        },
        isLogin() {
            return ['/login', '/register'].includes(this.$route.path);
        },
        isProfile() {
            return this.$route.path.startsWith('/profile');
        }
    },

    setup() {
        const router = useRouter();
        const store = useStore();

        const redirects = {
            home: () => router.push({ name: 'home' }),
            instalaciones: () => router.push({ name: 'instalaciones' }),
            servicios: () => router.push({ name: 'serviciosEntrenamientos' }),
            entrenadores: () => router.push({ name: 'entrenadores' }),
            login: () => router.push({ name: 'login' })
        };

        const state = reactive({
            user: computed(() => store.getters['user/GetProfile']),
            isAdmin: computed(() => store.getters['user/GetIsAdmin']),
            isEntrenador: computed(() => store.getters['user/GetIsEntrenador']),
            isLogged: computed(() => store.getters['user/GetIsAuth']),
        });

        const logout = () => {
            store.dispatch(`user/${Constant.LOGOUT}`);
            router.push({ name: 'home' });
        };

        const token = localStorage.getItem('token');
        const tokenAdmin = localStorage.getItem('tokenAdmin');
        const tokenEntrenador = localStorage.getItem('tokenEntrenador');
        if (token) {
            store.dispatch(`user/${Constant.INITIALIZE_USER}`, token);
        } else if (tokenAdmin) {
            store.dispatch(`user/${Constant.INITIALIZE_USER}`, tokenAdmin);
        } else if (tokenEntrenador) {
            store.dispatch(`user/${Constant.INITIALIZE_USER}`, tokenEntrenador);
        }

        return { redirects, state, logout };
    }
};
</script>


<style scoped>
.isActive {
    background-color: #ff6600;
    color: black;
    border-radius: 5px;
}

.text-color {
    color: #ff6600;
    transition: all 0.3s;

    &:hover {
        color: white;
    }
}

.auth {
    background-color: white;
    border-radius: 10px;
    color: #ff6600;
    transition: all 0.3s;

    &:hover {
        color: black;
        background-color: #ff6600;
    }
}

.header {
    background-color: rgb(20, 20, 20);
}

.logo {
    max-height: 50px;
    width: auto;
    transition: transform 0.3s;
    cursor: pointer;

    &:hover {
        transform: scale(1.1);
    }
}

.nav-link {
    cursor: pointer;
}

.nav-link.isActive {
    color: #000;
}

.profile-image {
    border-radius: 50%;
    width: 45px;
    height: 45px;
    border: 3px solid white;
    object-fit: cover;
}
</style>
