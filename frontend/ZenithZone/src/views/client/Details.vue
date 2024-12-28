<template>
    <main class="main-details">
        <section class="container">
            <div class="row">
                <div class="col-md-6">
                    <DetailsInfo :isEntrenamiento="isEntrenamiento" :state="state"/>
                </div>
                <!-- CARRUSEL -->
                <div class="col-md-6">
                    <DetailsCarousel :pistas="isEntrenamiento ? state.entrenamiento.deporte?.pistas : state.pista"/>
                </div>
            </div>
        </section>
    </main>
</template>

<script>
import DetailsCarousel from '@/components/shared/DetailsCarousel.vue';
import DetailsInfo from '@/components/shared/DetailsInfo.vue';
import Constant from '@/Constant';
import { computed, watch } from 'vue';
import { reactive } from 'vue';
import { useRoute } from 'vue-router';
import { useStore } from 'vuex';

export default {
    components: {
        DetailsCarousel,
        DetailsInfo
    },

    setup() {
        const store = useStore();
        const route = useRoute();
        const slug = route.params.slug;

        const state = reactive({
            entrenamiento: computed(() => store.getters['entrenamientos/GetOneEntrenamiento']),
            pista: computed(() => store.getters['pistas/GetOnePista'])
        });

        const isEntrenamiento = route.path.includes('entrenamiento');

        const fetchData = () => {
            if (isEntrenamiento) {
                store.dispatch(`entrenamientos/${Constant.INITIALIZE_ONE_STATE_ENTRENAMIENTO}`, slug);
            } else {
                store.dispatch(`pistas/${Constant.INITIALIZE_ONE_STATE_PISTA}`, slug);
            }
        };

        fetchData();

        watch(
            () => route.params.slug,
            () => {
                console.log(`cambia slug`);
                fetchData();
            }
        )

        return { state, isEntrenamiento };
    }
}
</script>

<style scoped>
.main-details {
    padding-top: 50px;
}

body {
    margin-top: 80px;
    background-color: rgb(245, 245, 245);
    font-family: 'Roboto', sans-serif;
}
</style>