<template>
    <div class="planes-container">
        <div class="container py-5">
            <div class="row mb-5">
                <div class="col-12 text-center">
                    <h1 class="display-4 fw-bold">Planes de Suscripción</h1>
                    <p class="lead text-muted">Elige el plan que mejor se adapte a tus necesidades y objetivos deportivos</p>
                </div>
            </div>

            <div class="row">
                <PlanesCards :suscripciones="suscripciones" />
            </div>

            <div class="row mt-5">
                <div class="col-12 text-center">
                    <p class="text-muted small">
                        <i class="bi bi-heart-fill text-danger me-1"></i>
                        El 5% de los ingresos por suscripciones se dona a organizaciones deportivas sin fines de lucro
                        para apoyar a jóvenes deportistas.
                    </p>
                    <p class="text-muted small">
                        <i class="bi bi-info-circle-fill text-primary me-1"></i>
                        Si renuevas tu suscripción antes de que finalice, mantendrás los días restantes y los entrenamientos
                        se sumarán a los que ya tienes disponibles.
                    </p>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { useStore } from 'vuex';
import { computed, onMounted } from 'vue';
import Constant from '@/Constant';
import PlanesCards from '@/components/planes/PlanesCards.vue';

export default {
    name: 'Planes',
    components: {
        PlanesCards
    },
    setup() {
        
        const store = useStore();
        const suscripciones = computed(() => store.getters['suscripciones/GetSuscripciones']);

        onMounted(() => {
            store.dispatch(`suscripciones/${Constant.INITIALIZE_SUSCRIPCION}`);
        });

        return {
            suscripciones
        };
    }
}
</script>

<style scoped>
.planes-container {
    background-color: #f8f9fa;
    min-height: 100vh;
}
</style>