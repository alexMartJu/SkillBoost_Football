<template>
    <div class="container">
        <div class="row g-4 justify-content-center">
            <CardClases v-for="entrenamiento in state.entrenamientos" :key="entrenamiento.id" :entrenamiento="entrenamiento" class="col-md-3" />
        </div>
    </div>
</template>

<script>
import { reactive, computed, onMounted } from 'vue';
import { useStore } from 'vuex';
import Constant from '../../Constant';
import CardClases from './CardEntrenamientos.vue';

export default {
    components: {
        CardClases
    },
    setup() {
        const store = useStore();

        store.dispatch(`entrenamientos/${Constant.INITIALIZE_ENTRENAMIENTO}`);

        const state = reactive({
            entrenamientos: computed(() => store.getters['entrenamientos/GetEntrenamientos'])
        });

        return { state };
    }
};
</script>

<style>
.deportes-list {
    display: flex;
    flex-wrap: wrap;
    gap: 10px;
}
</style>