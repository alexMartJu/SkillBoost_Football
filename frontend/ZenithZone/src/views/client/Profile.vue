<template>
    <main class="main-profile">
        <div class="container" v-if="dataLoaded">
            <ProfileInfo 
                :profile="state.Profile" 
                :isCurrentUser="isCurrentUser"
            />
            
        </div>
        <ProfileNav v-if="isCurrentUser && state.Profile?.numeroSocio" :profile="state.Profile" />
            <div class="d-flex justify-content-center mt-4">
                <button v-if="isCurrentUser && state.Profile?.numeroentrenador" 
                    @click="redirects.dashboardEntrenador"
                    class="btn btn-lg btn-primary">
                    Accede a tu Dashboard
                </button>
            </div>
    </main>
</template>

<script>
import ProfileInfo from '@/components/profile/ProfileInfo.vue';
import ProfileNav from '@/components/profile/ProfileNav.vue';
import Constant from '@/Constant';
import { reactive, computed, watch, watchEffect, ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { useStore } from 'vuex';

export default {
    name: 'Profile',
    components: {
        ProfileInfo,
        ProfileNav
    },

    setup() {
        const route = useRoute();
        const store = useStore();
        const router = useRouter();
        const dataLoaded = ref(false);

        const data = {
            numeroentrenador: route.params.numeroentrenador,
            numeroSocio: route.params.numeroSocio
        }
        const año = new Date().getFullYear();

        const redirects = {
            dashboardEntrenador: () => router.push({ name: 'DashboardEntrenador' }),
        };      

        const fetchData = async () => {
            try {
                await Promise.all([
                    store.dispatch(`user/${Constant.INITIALIZE_PROFILE}`, data),
                    store.dispatch(`profile/${Constant.INITIALIZE_GRAFICA_PROFILE}`, año),
                    localStorage.getItem('token') 
                        ? store.dispatch(`user/${Constant.INITIALIZE_USER}`, {"token": localStorage.getItem('token')})
                        : store.dispatch(`user/${Constant.INITIALIZE_USER}`, {"entrenadorToken": localStorage.getItem('entrenadorToken')})
                ]);
                dataLoaded.value = true;
            } catch (error) {
                console.error('Error cargando datos:', error);
            }
        };

        onMounted(() => {
            fetchData();
        });

        const state = reactive({
            Profile: computed(() => store.getters['user/GetProfile']),
            CurrentUser: computed(() => store.getters['user/GetCurrentUser']),
        });

        const isCurrentUser = computed(() => {
            return state.Profile?.numeroSocio === state.CurrentUser?.numeroSocio;
        });

        watch(
            () => route.params,
            () => {
                dataLoaded.value = false;
                fetchData();
            }
        );

        return { 
            isCurrentUser, 
            state, 
            redirects,
            dataLoaded 
        };
    },
};
</script>

<style>
    .main-profile {
        margin-top: 50px;
    }

    body {
        margin-top: 80px;
        background-color: rgb(245, 245, 245);
        font-family: 'Roboto', sans-serif;
    }
</style>