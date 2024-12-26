<template>
    <div class="row justify-content-center">
        <CardEntrenamientos 
            v-for="entrenamiento in state.entrenamientos" 
            :key="entrenamiento.id" 
            :entrenamiento="entrenamiento" 
            class="col-md-6" />
    </div>
</template>

<script>
import { reactive } from 'vue';
import CardEntrenamientos from '../shared/CardEntrenamientos.vue';
import { useStore } from 'vuex';
import { computed } from 'vue';
import Constant from '@/Constant';
import { useRoute } from 'vue-router';

export default {
    components: {
        CardEntrenamientos,
    },
    
    setup() {
        const store = useStore();
        const route = useRoute();

        store.dispatch(`profile/${Constant.INITIALIZE_ENTRENAMIENTO_PROFILE}`);
        // store.dispatch(`profile/${Constant.INITIALIZE_ENTRENAMIENTO_PROFILE}`, route.params.numeroSocio);

        const state = reactive({
            entrenamientos: computed(() => store.getters['profile/GetEntrenamientosProfile'])
            // entrenamientos: computed(() => store.getters['entrenamientos/GetEntrenamientos'])
        });

        return { state };
    }
};
</script>