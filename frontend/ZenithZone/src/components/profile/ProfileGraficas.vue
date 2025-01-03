<template>
    <div class="graph-main ms-5">
        <div class="section-graph d-flex">
            <div class="landing-section d-flex">
                <div class="carousel-section">
                    <div class="">
                        <Graphics class="me-5" :graficas="state.graficaValues" />
                    </div>
                    <div></div>
                </div>
            </div>

            <div class="text-section d-flex flex-column justify-content-center">
                <div class="text-container-graph">
                    <p class="fst-italic ms-5">Evolución mensual {{ currentYear }}</p>
                </div>
                <p class="text-end fs-3 fst-italic me-5 pe-4">Para un mayor nivel de detalle, ponte en contacto con tus
                    entrenadores</p>
            </div>
        </div>
    </div>

</template>

<script>
import Graphics from '@/components/home/Graphics.vue';
import Constant from '@/Constant';
import { computed, watchEffect } from 'vue';
import { reactive } from 'vue';
import { useRoute } from 'vue-router';
import { useStore } from 'vuex';

export default {
    components: {
        Graphics
    },

    setup() {
        const store = useStore();
        const currentYear = new Date().getFullYear();

        const state = reactive({
            graficas: store.getters['profile/GetGraficasProfile'],
            graficaValues: []
        });

        const valuesGrafica = () => {
            let data = [];

            state.graficas.forEach(values => {
                data.push(values);
            });

            return data;
        }

        watchEffect(() => {
            state.graficaValues = valuesGrafica();
        });

        return { state, currentYear };
    }
};
</script>

<style>
.graph-main {
    padding-left: 50px;
    z-index: -1;
}

.section-graph {
    height: 70vh;
    overflow: hidden;
}

.text-section-graph {
    color: white;
    width: 60%;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-left: 5%;
}

.text-container-graph {
    font-family: 'Roboto', sans-serif;
    color: black;
    display: flex;
    justify-content: center;
    text-align: justify;
    align-items: center;
    font-size: 42px;
}

.carousel-section {
    position: relative;
    width: 50%;
    z-index: 1;
}

.object-fit-cover {
    object-fit: cover;
}
</style>
