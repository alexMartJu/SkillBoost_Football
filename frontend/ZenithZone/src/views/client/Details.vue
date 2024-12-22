<template>
    <main class="main-details">
        <section class="container">
            <h1>{{ state.entrenamientos.nombre }}</h1>
            <div class="entrenamiento-info">
                <p>{{ state.entrenamientos.descripcion }}</p>
                <p>{{ state.entrenamientos.dia }}</p>
                <p>{{ state.entrenamientos.duracion }} minutos</p>
                <p>Plazas máximas: {{ state.entrenamientos.maxPlazas }}</p>
                <p>Precio: {{ state.entrenamientos.precio }}€</p>
            </div>

            <div>
                <h2>Pistas de {{ state.entrenamientos.deporte.nombre }}</h2>
                <ul v-for="pista in state.entrenamientos.deporte.pistas">
                    <li>{{ pista.nombre }}</li>
                </ul>
            </div>

            <button class="btn btn-primary">Botón para las reservas</button>
        </section>
    </main>
</template>

<script>
import Constant from '@/Constant';
import { computed } from 'vue';
import { reactive } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { useStore } from 'vuex';

export default {
    setup() {
        const store = useStore();
        const route = useRoute();
        const router = useRouter();
        const slug = route.params.slug;

        store.dispatch(`entrenamientos/${Constant.INITIALIZE_ONE_STATE_ENTRENAMIENTO}`, slug);

        const state = reactive({
            entrenamientos: computed(() => store.getters['entrenamientos/GetOneEntrenamiento'])
        });

        return { state };
    }
}
</script>

<style scoped>
.main-details {
    padding-top: 50px;
}
</style>
