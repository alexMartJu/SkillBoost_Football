<template>
    <main class="main-details">
        <section class="container">
            <div class="row">
                <div class="col-md-6">
                    <div class="entrenamiento-info" v-if="state.entrenamientos">
                        <h1>{{ state.entrenamientos.nombre }}</h1>
                        <p>{{ state.entrenamientos.descripcion }}</p>
                        <p>{{ state.entrenamientos.dia }}</p>
                        <p>{{ state.entrenamientos.duracion }} minutos</p>
                        <p>Plazas máximas: {{ state.entrenamientos.maxPlazas }}</p>
                        <p>Precio: {{ state.entrenamientos.precio }}€</p>
                        <div>
                            <h2>Pistas de {{ state.entrenamientos.deporte?.nombre }}</h2>
                            <ul v-for="pista in state.entrenamientos.deporte?.pistas" :key="pista.id">
                                <li>{{ pista.nombre }}</li>
                            </ul>
                        </div>
                        <button class="btn btn-primary">Botón para las reservas</button>
                    </div>
                    <div v-else>
                        <p>Cargando información de entrenamiento...</p>
                    </div>
                </div>
                <!-- Carousel -->
                <div class="col-md-6">
                    <!-- <CarouselDetails /> -->
                    <CarouselDetails :pistas="state.entrenamientos.deporte.pistas"/>
                </div>
            </div>
        </section>
    </main>
</template>


<script>
import CarouselDetails from '@/components/shared/carouselDetails.vue';
import Constant from '@/Constant';
import { computed } from 'vue';
import { reactive } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { useStore } from 'vuex';

export default {
    components: {
        CarouselDetails
    },

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
