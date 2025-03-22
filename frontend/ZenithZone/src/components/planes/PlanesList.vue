<template>
    <div>
        <div class="row mb-5">
            <div class="col-12 text-center">
                <h1 class="display-4 fw-bold">Planes de Suscripción</h1>
                <p class="lead text-muted">Elige el plan que mejor se adapte a tus necesidades y objetivos deportivos
                </p>
            </div>
        </div>

        <div class="row">
            <PlanesCards :suscripciones="suscripciones" @seleccionarPlan="seleccionarPlan" />
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
</template>

<script>
import { useStore } from 'vuex';
import { computed } from 'vue';
import { useRouter } from 'vue-router';
import Constant from '@/Constant';
import PlanesCards from '@/components/planes/PlanesCards.vue';

export default {
    name: 'PlanesList',
    components: {
        PlanesCards
    },
    setup() {
        const store = useStore();
        const router = useRouter();
        const suscripciones = computed(() => store.getters['suscripciones/GetSuscripciones']);

        const seleccionarPlan = (plan) => {
            router.push({ name: 'procesosPago', params: { slug: plan.slug } });
        };

        //Cargar las suscripciones al montar el componente
        store.dispatch(`suscripciones/${Constant.INITIALIZE_SUSCRIPCION}`);

        return {
            suscripciones,
            seleccionarPlan
        };
    }
}
</script>