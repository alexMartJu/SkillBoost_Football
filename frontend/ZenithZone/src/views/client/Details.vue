<template>
    <main class="main-details">
        <section class="container">
            <div class="card shadow-sm p-3 mb-5 bg-white rounded">
                <div class="row g-0">
                    <div class="col-md-6">
                        <DetailsInfo :isEntrenamiento="isEntrenamiento" :isSubtipoTecnificacion="isSubtipoTecnificacion"
                            :state="state" />
                    </div>
                    <div class="col-md-6 d-flex align-items-center justify-content-center">
                        <DetailsCarousel
                            :pistas="isEntrenamiento ? state.entrenamiento.pistaPrivada : (isSubtipoTecnificacion ? state.subtipoTecnificacion : state.pista)" />
                    </div>
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
            pista: computed(() => store.getters['pistas/GetOnePista']),
            subtipoTecnificacion: computed(() => store.getters['subtiposTecnificacion/GetOneSubtipoTecnificacion'])
        });

        const isEntrenamiento = route.path.includes('entrenamiento');
        const isSubtipoTecnificacion = route.path.includes('subtipo');

        const fetchData = () => {
            if (isEntrenamiento) {
                store.dispatch(`entrenamientos/${Constant.INITIALIZE_ONE_STATE_ENTRENAMIENTO}`, slug);
            } else if (isSubtipoTecnificacion) {
                store.dispatch(`subtiposTecnificacion/${Constant.INITIALIZE_ONE_STATE_SUBTIPO_TECNIFICACION}`, slug);
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

        return { state, isEntrenamiento, isSubtipoTecnificacion };
    }
}
</script>

<style scoped>
.main-details {
    padding-top: 50px;
}
</style>