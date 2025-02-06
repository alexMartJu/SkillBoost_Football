<template>
    <div class="row justify-content-center">
        <CardEntrenamientos v-for="entrenamiento in state.entrenamientos" :key="entrenamiento.id"
            :entrenamiento="entrenamiento" :isProfile="true" class="col-md-4" />
    </div>
</template>

<script>
import { reactive, watchEffect } from 'vue';
import CardEntrenamientos from '../shared/CardEntrenamientos.vue';
import { useStore } from 'vuex';
import { computed } from 'vue';
import Constant from '@/Constant';

export default {
    components: {
        CardEntrenamientos,
    },

    setup() {
        const store = useStore();

        store.dispatch(`profile/${Constant.INITIALIZE_ENTRENAMIENTO_PROFILE}`);

        const state = reactive({
            entrenamientos: computed(() => store.getters['profile/GetEntrenamientosProfile'])
        });

        watchEffect(() => {
            store.dispatch(`profile/${Constant.INITIALIZE_ENTRENAMIENTO_PROFILE}`);
        });

        return { state };
    }
};
</script>