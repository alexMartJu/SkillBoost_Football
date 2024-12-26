<template>
    <main class="main-profile">
        <div class="container">
            <ProfileInfo :profile="state.Profile" :isCurrentUser="isCurrentUser"/>
        </div>
        <ProfileNav v-if="isCurrentUser && state.Profile?.numeroSocio" :profile="state.Profile" />
        <div class="d-flex justify-content-center mt-4">
            <button v-if="isCurrentUser && state.Profile?.numeroEntrenador" @click="redirects.dashboardEntrenador"
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
import { reactive, computed, watch } from 'vue';
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
        const data = {
            numeroEntrenador: route.params.numeroEntrenador,
            numeroSocio: route.params.numeroSocio
        }

        const redirects = {
            dashboardEntrenador: () => router.push({ name: 'DashboardEntrenador' }),
        };      

        const fetchData = () => {
            store.dispatch(`user/${Constant.INITIALIZE_PROFILE}`, data);
            if (localStorage.getItem('token')) {
                store.dispatch(`user/${Constant.INITIALIZE_USER}`, {"token": localStorage.getItem('token')});
            } else if (localStorage.getItem('entrenadorToken')) {
                store.dispatch(`user/${Constant.INITIALIZE_USER}`, {"entrenadorToken": localStorage.getItem('entrenadorToken')});
            }
        };        

        const state = reactive({
            Profile: computed(() => store.getters['user/GetProfile']),
            CurrentUser: computed(() => store.getters['user/GetCurrentUser']),
        });

        const isCurrentUser = computed(() => {
            return state.Profile.numeroSocio === state.CurrentUser.numeroSocio;
        });

        watch(
            () => [route.params.numeroSocio || route.params.numeroEntrenador],
            fetchData(),
        );

        return { isCurrentUser, state, redirects };
    },
};
</script>

<style>
    .main-profile {
        margin-top: 50px;
    }
</style>