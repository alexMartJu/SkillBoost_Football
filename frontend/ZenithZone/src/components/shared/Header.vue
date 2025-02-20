<template>
    <header class="header fixed-top shadow">
        <div class="container-fluid">
            <nav class="navbar navbar-expand-lg py-3">
                <!-- Logo -->
                <div class="navbar-brand" @click="redirects.home">
                    <img src="/assets/logo_2.png" alt="Logo" class="logo-modern">
                </div>

                <!-- Hamburger Menu for Mobile -->
                <button class="navbar-toggler navbar-dark" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <!-- Navigation Links -->
                <div class="collapse navbar-collapse" id="navbarContent">
                    <ul class="navbar-nav ms-auto align-items-center">
                        <li v-if="!state.isAdmin && !state.isEntrenador" class="nav-item">
                            <a @click="redirects.home" class="nav-link modern-link" :class="{ 'active-link': isHome }">
                                <i class="bi bi-house-door me-1"></i>Home
                            </a>
                        </li>
                        <li v-if="!state.isAdmin && !state.isEntrenador" class="nav-item">
                            <a @click="redirects.instalaciones" class="nav-link modern-link" :class="{ 'active-link': isInstalaciones }">
                                <i class="bi bi-building me-1"></i>Instalaciones
                            </a>
                        </li>
                        <li v-if="!state.isAdmin && !state.isEntrenador" class="nav-item">
                            <a @click="redirects.servicios" class="nav-link modern-link" :class="{ 'active-link': isServicios }">
                                <i class="bi bi-gear me-1"></i>Servicios
                            </a>
                        </li>
                        <li v-if="!state.isAdmin && !state.isEntrenador" class="nav-item">
                            <a @click="redirects.entrenadores" class="nav-link modern-link" :class="{ 'active-link': isEntrenadores }">
                                <i class="bi bi-people me-1"></i>Entrenadores
                            </a>
                        </li>

                        <!-- Dashboards -->
                        <li v-if="state.isAdmin" class="nav-item">
                            <a @click="redirects.dashboardAdmin" class="nav-link modern-link" :class="{ 'active-link': isDashboard }">
                                <i class="bi bi-speedometer2 me-1"></i>Dashboard Admin
                            </a>
                        </li>
                        <li v-if="state.isEntrenador" class="nav-item">
                            <a @click="redirects.dashboardEntrenador" class="nav-link modern-link" :class="{ 'active-link': isDashboard }">
                                <i class="bi bi-speedometer2 me-1"></i>Dashboard Entrenador
                            </a>
                        </li>

                        <!-- User Profile -->
                        <li v-if="state.user.numeroSocio" class="nav-item ms-lg-3">
                            <div class="d-flex align-items-center">
                                <div class="position-relative">
                                    <img :src="state.user.image" alt="" class="profile-pic">
                                    <span class="position-absolute top-0 start-100 translate-middle p-1 bg-success border border-light rounded-circle">
                                        <span class="visually-hidden">Online</span>
                                    </span>
                                </div>
                                <a @click="redirects.profile" class="nav-link modern-link ms-2" :class="{ 'active-link': isProfile }">
                                    {{ state.user.nombre }}
                                </a>
                            </div>
                        </li>

                        <!-- Auth Buttons -->
                        <li v-if="!state.isLogged" class="nav-item ms-lg-3">
                            <a @click="redirects.login" class="btn btn-modern-primary" :class="{ 'active-link': isLogin }">
                                Unirse al club
                            </a>
                        </li>
                        <li v-if="state.isLogged" class="nav-item ms-lg-3">
                            <a @click="logout" class="btn btn-modern-secondary">
                                Cerrar sesión
                            </a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>
    </header>
</template>


<script>
import Constant from '@/Constant';
import { computed, watch } from 'vue';
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
        },
        isDashboard() {
            return this.$route.path.startsWith('/dashboard');
        }
    },

    setup() {
        const router = useRouter();
        const store = useStore();

        const state = reactive({
            user: computed(() => store.getters['user/GetCurrentUser']),
            isAdmin: computed(() => store.getters['user/GetIsAdmin']),
            isEntrenador: computed(() => store.getters['user/GetIsEntrenador']),
            isUser: computed(() => store.getters['user/GetIsAuth']),
            isLogged: false
        });

        const redirects = {
            home: () => router.push({ name: 'home' }),
            instalaciones: () => router.push({ name: 'instalaciones' }),
            servicios: () => router.push({ name: 'serviciosEntrenamientos' }),
            entrenadores: () => router.push({ name: 'entrenadores' }),
            profile: () => router.push({ name: 'profile', params: { numeroSocio: state.user.numeroSocio } }),
            profileEntrenador: () => router.push({ name: 'profileEntrenador', params: { numeroentrenador: state.user.numeroentrenador } }),
            login: () => router.push({ name: 'login' }),
            dashboardAdmin: () => router.push({ name: 'DashboardAdmin' }),
            dashboardEntrenador: () => router.push({ name: 'DashboardEntrenador' }),
        };

        watch(
            () => state.user.nombre,
            (newValue) => {
                state.isLogged = !!newValue;
            },
            { immediate: true }
        );

        const logout = () => {
            const refreshToken = { refreshToken: localStorage.getItem('refreshToken') };
            store.dispatch(`user/${Constant.LOGOUT}`, refreshToken);
            router.push({ name: 'home' });
        };

        const token = localStorage.getItem('token');
        const tokenAdmin = localStorage.getItem('tokenAdmin');
        const entrenadorToken = localStorage.getItem('entrenadorToken');
        if (token) {
            store.dispatch(`user/${Constant.INITIALIZE_USER}`, { "token": token });
        } else if (tokenAdmin) {
            console.log(`checkea admin`);
            store.dispatch(`user/${Constant.INITIALIZE_USER}`, { "tokenAdmin": tokenAdmin });
        } else if (entrenadorToken) {
            // console.log(`checkea entrenador`, entrenadorToken);
            store.dispatch(`user/${Constant.INITIALIZE_USER}`, { "entrenadorToken": entrenadorToken });
        }

        return { redirects, state, logout };
    }
};
</script>


<style scoped>
.header {
    background-color: rgba(20, 20, 20, 0.95);
    backdrop-filter: blur(10px);
}

.logo-modern {
    height: 45px;
    width: auto;
    transition: transform 0.3s ease;
    cursor: pointer;
}

.logo-modern:hover {
    transform: scale(1.05);
}

.modern-link {
    color: #ff6600;
    font-weight: 500;
    padding: 0.5rem 1rem;
    margin: 0 0.2rem;
    border-radius: 8px;
    transition: all 0.3s ease;
}

.modern-link:hover {
    color: white;
    background-color: rgba(255, 102, 0, 0.1);
    transform: translateY(-2px);
}

.active-link {
    background-color: #ff6600;
    color: white !important;
    box-shadow: 0 4px 15px rgba(255, 102, 0, 0.2);
}

.btn-modern-primary {
    background-color: #ff6600;
    color: white;
    border-radius: 10px;
    padding: 0.5rem 1.5rem;
    font-weight: 600;
    transition: all 0.3s ease;
    border: 2px solid #ff6600;
}

.btn-modern-primary:hover {
    background-color: transparent;
    color: #ff6600;
    transform: translateY(-2px);
}

.btn-modern-secondary {
    background-color: transparent;
    color: #ff6600;
    border: 2px solid #ff6600;
    border-radius: 10px;
    padding: 0.5rem 1.5rem;
    font-weight: 600;
    transition: all 0.3s ease;
}

.btn-modern-secondary:hover {
    background-color: #ff6600;
    color: white;
    transform: translateY(-2px);
}

.profile-pic {
    width: 40px;
    height: 40px;
    border-radius: 50%;
    border: 2px solid #ff6600;
    object-fit: cover;
    box-shadow: 0 4px 15px rgba(255, 102, 0, 0.2);
}

.nav-link{
    cursor: pointer;
}

@media (max-width: 991.98px) {
    .navbar-nav {
        padding: 1rem 0;
    }
    
    .nav-item {
        margin: 0.5rem 0;
    }
}
</style>