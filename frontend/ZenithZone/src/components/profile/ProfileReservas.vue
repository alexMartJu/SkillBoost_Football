<template>
    <p>{{ }}</p>
    <div class="row g-4 justify-content-center">
        <div v-for="reservas in state.reservas" :reservas="reservas" class="col-md-6">
            <h2 class="text-center">Reservas {{ reservas.nombre }}</h2>
            <CardReservas v-for="reserva in reservas.reservas" :key="reserva.id" :reserva="reserva"
                :slug="reservas.slug" />
        </div>
    </div>
</template>

<script>
import { useStore } from 'vuex';
import CardReservas from '../shared/CardReservas.vue';
import Constant from '@/Constant';
import { reactive, watchEffect } from 'vue';
import { computed } from 'vue';

export default {
    components: {
        CardReservas
    },

    setup() {
        const store = useStore();

        store.dispatch(`profile/${Constant.INITIALIZE_RESERVA_PROFILE}`);

        const state = reactive({
            reservas: computed(() => store.getters['profile/GetReservasProfile'])
        });

        watchEffect(() => { });

        return { state };
    }
}
</script>