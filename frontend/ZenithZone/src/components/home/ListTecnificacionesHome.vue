<template>
    <section class="tecnificaciones-section py-5">
        <div class="container-xxl">
            <!-- Header Section -->
            <div class="row mb-5">
                <div class="col-lg-8 mx-auto text-center">
                    <h2 class="display-4 fw-bold gradient-text mb-4">Nuestras Tecnificaciones</h2>
                    <p class="fs-5 mb-0">Programas especializados para potenciar tu rendimiento deportivo</p>
                </div>
            </div>

            <!-- Carousel Section -->
            <div class="tecnificaciones-carousel">
                <Splide :options="splideOptions" class="tecnificaciones-slider">
                    <SplideSlide v-for="tecnificacion in state.tecnificaciones.slice(0, 8)" :key="tecnificacion.id">
                        <div class="px-2">
                            <CardTecnificaciones :tecnificacion="tecnificacion" />
                        </div>
                    </SplideSlide>
                </Splide>
            </div>
        </div>
    </section>
</template>

<script>
import { reactive, computed } from 'vue';
import { useStore } from 'vuex';
import Constant from '../../Constant';
import CardTecnificaciones from './CardTecnificacionesHome.vue';
import { Splide, SplideSlide } from "@splidejs/vue-splide";
import "@splidejs/splide/dist/css/splide.min.css";

export default {
    components: {
        CardTecnificaciones,
        Splide,
        SplideSlide
    },
    setup() {
        const store = useStore();

        store.dispatch(`tecnificaciones/${Constant.INITIALIZE_TECNIFICACION}`);

        const state = reactive({
            tecnificaciones: computed(() => store.getters['tecnificaciones/GetTecnificaciones'])
        });

        // Configuración del carrusel
        const splideOptions = {
            type: 'loop',
            perPage: 4,
            gap: '1.5rem',
            pagination: false,
            arrows: true,
            autoplay: false,
            padding: '1rem',
            focus: 'center',
            trimSpace: false,
            breakpoints: {
                1200: {
                    perPage: 3,
                },
                992: {
                    perPage: 3,
                },
                768: {
                    perPage: 2,
                },
                576: {
                    perPage: 1,
                }
            }
        };

        return {
            state,
            splideOptions
        };
    }
};
</script>

<style scoped>
.tecnificaciones-section {
    background: rgba(var(--bs-light-rgb), var(--bs-bg-opacity)) !important;
    padding: 80px 0;
}

.gradient-text {
    background: linear-gradient(45deg, var(--bs-primary), #141414f2);
    -webkit-background-clip: text;
    background-clip: text;
    color: transparent;
}

.text-white-75 {
    opacity: 0.75;
    color: white;
}

.tecnificaciones-carousel {
    margin: 0 -1rem;
}

/* Personalización de los controles del carrusel */
:deep(.splide__arrow) {
    background: rgba(255, 255, 255, 0.9);
    width: 3rem;
    height: 3rem;
    opacity: 1;
    transition: all 0.3s ease;
}

:deep(.splide__arrow:hover) {
    background: var(--bs-primary);
}

:deep(.splide__arrow svg) {
    fill: #333;
    width: 1.2rem;
    height: 1.2rem;
}

:deep(.splide__arrow:hover svg) {
    fill: white;
}

:deep(.splide__arrow--prev) {
    left: 1rem;
}

:deep(.splide__arrow--next) {
    right: 1rem;
}

@media (max-width: 768px) {
    .tecnificaciones-section {
        padding: 60px 0;
    }

    .gradient-text {
        font-size: 2.5rem;
    }

    :deep(.splide__arrow) {
        width: 2.5rem;
        height: 2.5rem;
    }
}
</style>