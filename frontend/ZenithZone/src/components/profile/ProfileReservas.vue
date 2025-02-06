<template>
    <p>{{ }}</p>
    <div class="profile-reservas py-5">
        <div class="container">
            <h2 class="text-primary text-center mb-4">Mis Reservas</h2>
            <div class="row g-4">
                <template v-for="pista in state.reservas" :key="pista.id">
                    <CardReservas 
                        v-for="reserva in pista.reservas" 
                        :key="reserva.id"
                        :reserva="{...reserva, pistaNombre: pista.nombre}" 
                        :slug="pista.slug" 
                    />
                </template>
            </div>
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