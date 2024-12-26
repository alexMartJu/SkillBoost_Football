<template>
    <div class="row g-4 justify-content-center">
        <CardReservas v-for="reserva in state.reservas" :key="reserva.id" :reserva="reserva" class="col-md-6"/>
    </div>
</template>

<script>
import { useStore } from 'vuex';
import CardReservas from '../shared/CardReservas.vue';
import { useRoute } from 'vue-router';
import Constant from '@/Constant';
import { reactive } from 'vue';
import { computed } from 'vue';

export default {
    components: {
        CardReservas
    },

    setup() {
        const store = useStore();
        const route = useRoute();

        // store.dispatch(`profile/${Constant.INITIALIZE_RESERVA_PROFILE}`, route.params.numeroSocio);

        // const state = reactive({
        //     reservas: computed(() => store.getters['profile/GetReservasProfile'])
        // });

        // return { state }

        // =================================================

        store.dispatch(`profile/${Constant.INITIALIZE_ENTRENAMIENTO_PROFILE}`);

        const state = reactive({
            reservas: computed(() => store.getters['profile/GetEntrenamientosProfile'])
        });

        return { state };
    }
}
</script>