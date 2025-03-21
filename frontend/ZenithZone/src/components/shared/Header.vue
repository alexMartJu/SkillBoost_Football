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
                            <a @click="redirects.recursos" class="nav-link modern-link" :class="{ 'active-link': isRecursos }">
                                <i class="bi bi-journal-text me-1"></i>Recursos                             
                            </a>
                        </li>
                        <li v-if="!state.isAdmin && !state.isEntrenador" class="nav-item">
                            <a @click="redirects.entrena" class="nav-link modern-link" :class="{ 'active-link': isEntrena }">
                                <i class="bi bi-person-arms-up me-1"></i>Entrena                            
                            </a>
                        </li>
                        <li v-if="!state.isAdmin && !state.isEntrenador" class="nav-item">
                            <a @click="redirects.apoyo" class="nav-link modern-link" :class="{ 'active-link': isApoyo }">
                                <i class="bi bi-hand-thumbs-up me-1"></i>Apoyo                                                        
                            </a>
                        </li>
                        <li v-if="!state.isAdmin && !state.isEntrenador" class="nav-item">
                            <a @click="redirects.planes" class="nav-link modern-link" :class="{ 'active-link': isPlan }">
                                <i class="bi bi-credit-card me-1"></i>Planes                                                       
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
                        <li v-if="state.isLogged && state.user.profile" class="nav-item ms-lg-3">
                            <div class="d-flex align-items-center">
                                <div class="position-relative">
                                    <img :src="state.user.profile.image || '/assets/default-profile.png'" alt="" class="profile-pic">
                                    <span class="position-absolute top-0 start-100 translate-middle p-1 bg-success border border-light rounded-circle">
                                        <span class="visually-hidden">Online</span>
                                    </span>
                                </div>
                                <a @click="redirectToProfile" class="nav-link modern-link ms-2" :class="{ 'active-link': isProfile }">
                                    {{ state.user.profile.nombre || state.user.email }}
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
        isRecursos() {
            return this.$route.path.startsWith('/recursos');
        },
        isEntrena() {
            return this.$route.path.startsWith('/entrena');
        },
        isApoyo() {
            return this.$route.name === 'apoyo';
        },
        isPlan() {
            return this.$route.name === 'planes';
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
            isAuth: computed(() => store.getters['user/GetIsAuth']),
            isLogged: false
        });

        const redirects = {
            home: () => router.push({ name: 'home' }),
            recursos: () => router.push({ name: 'recursos' }),
            entrena: () => router.push({ name: 'entrena' }),
            apoyo: () => router.push({ name: 'apoyo' }),
            planes: () => router.push({ name: 'planes' }),
            login: () => router.push({ name: 'login' }),
            dashboardAdmin: () => router.push({ name: 'DashboardAdmin' }),
            dashboardEntrenador: () => router.push({ name: 'DashboardEntrenador' }),
        };

        const redirectToProfile = () => {
            //Si es admin, no redirigir a ningún perfil
            if (state.isAdmin) {
                return; // No hacer nada o redirigir a otra página específica para admins
            }
            //Si es entrenador con numeroEntrenador, redirigir al perfil de entrenador
            else if (state.isEntrenador && state.user.profile?.numeroEntrenador) {
                router.push({ 
                    name: 'profileEntrenador', 
                    params: { numeroEntrenador: state.user.profile.numeroEntrenador } 
                });
            } 
            //Para usuarios normales con numeroSocio
            else if (state.user.profile?.numeroSocio) {
                router.push({ 
                    name: 'profile', 
                    params: { numeroSocio: state.user.profile.numeroSocio } 
                });
            }
        };

        watch(
            () => [state.isAdmin, state.isEntrenador, state.isAuth],
            () => {
                state.isLogged = state.isAdmin || state.isEntrenador || state.isAuth;
            },
            { immediate: true }
        );

        const logout = () => {
            const refreshToken = { refreshToken: localStorage.getItem('refreshToken') };
            store.dispatch(`user/${Constant.LOGOUT}`, refreshToken);
        };

        //Inicializar usuario si hay un token
        const accessToken = localStorage.getItem('accessToken');
        if (accessToken) {
            store.dispatch(`user/${Constant.INITIALIZE_USER}`, { accessToken });
        }

        return { redirects, state, logout, redirectToProfile };
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