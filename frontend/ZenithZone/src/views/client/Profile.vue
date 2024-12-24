<template>
    <main class="main-profile">
        <div class="container">
            <ProfileInfo :profile="state.Profile" :isCurrentUser="isCurrentUser"/>
        </div>
        <ProfileNav :profile="state.Profile" :isCurrentUser="isCurrentUser" />
    </main>

</template>

<script>
import ProfileInfo from '@/components/profile/ProfileInfo.vue';
import ProfileNav from '@/components/profile/ProfileNav.vue';
import Constant from '@/Constant';
import { reactive, computed, watch } from 'vue';
import { useRoute } from 'vue-router';
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

        const fetchData = () => {
            store.dispatch(`user/${Constant.INITIALIZE_PROFILE}`, route.params.numeroSocio)
        };

        const state = reactive({
            Profile: computed(() => store.getters['user/GetProfile']),
            CurrentUser: computed(() => store.getters['user/GetCurrentUser']),
        });

        let isCurrentUser = false
        if (state.Profile.numeroSocio === state.CurrentUser.numeroSocio) {
            isCurrentUser = true;
        }

        watch(
            () => [route.params.numeroSocio],
            fetchData(),
        );

        return { isCurrentUser, state };
    },
};
</script>

<style>
    .main-profile {
        margin-top: 50px;
    }
</style>