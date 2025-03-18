<template>
    <main>
        <section class="container">
            <h1 class="text-center pt-5 pb-3">Conoce a nuestro equipo</h1>
            <div class="row g-4 justify-content-center">
                <CardEntrenador v-for="entrenador in state.Entrenadores" :key="entrenador.id" :entrenador="entrenador" class="col-md-6 g-3"/>
            </div>            
        </section>
    </main>
</template>

<script>
import { computed } from 'vue';
import { reactive } from 'vue';
import { useStore } from 'vuex';
import CardEntrenador from '@/components/shared/CardEntrenador.vue';
import Constant from '@/Constant';

export default {
    components: {
        CardEntrenador
    },

    setup()  {
        const store = useStore()

        store.dispatch(`entrenadores/${Constant.INITIALIZE_ENTRENADOR}`);

        const state = reactive({
            Entrenadores: computed(() => store.getters['entrenadores/GetEntrenadores']),
        });

        return {state}
    }
};
</script>

<style scoped>
</style>