<template>
    <div class="apoyo-container">
        <section class="hero-section py-5 bg-light">
            <div class="container">
                <div class="row">
                    <div class="col-lg-10 mx-auto text-center">
                        <h1 class="display-4 fw-bold mb-4">Compromiso Social a través del Fútbol</h1>
                        <p class="lead mb-4">
                            En SkillBoost Football creemos firmemente que el deporte es una poderosa herramienta de
                            transformación social. Nuestro compromiso va más allá de la tecnificación deportiva:
                            buscamos crear oportunidades
                            para niños y niñas en situación de exclusión social, utilizando el fútbol como vehículo para
                            desarrollar
                            valores, habilidades sociales y confianza en sí mismos.
                        </p>
                        <p class="fs-5 mb-5">
                            <strong>Cada pase, cada gol, cada entrenamiento es una oportunidad para cambiar
                                vidas.</strong>
                            Colaboramos con organizaciones comprometidas que comparten nuestra visión de un mundo más
                            inclusivo
                            y justo a través del deporte.
                        </p>
                    </div>
                </div>
            </div>
        </section>

        <OrganizacionesList />
    </div>
</template>

<script>
import { onMounted } from 'vue';
import { useStore } from 'vuex';
import OrganizacionesList from '@/components/apoyo/OrganizacionesList.vue';
import Constant from '@/Constant';

export default {
    name: 'Apoyo',
    components: {
        OrganizacionesList
    },
    setup() {
        const store = useStore();

        onMounted(() => {
            store.dispatch('organizaciones/' + Constant.INITIALIZE_ORGANIZACION);
        });

        return {};
    }
}
</script>

<style scoped>
.hero-section {
    background-color: #f8f9fa;
    border-bottom: 1px solid #e9ecef;
}

.apoyo-container {
    min-height: 100vh;
}
</style>