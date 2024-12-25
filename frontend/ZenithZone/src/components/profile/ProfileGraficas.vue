<template>
    <div class="graph-main ms-5">
        <div class="section-graph d-flex">
            <div class="landing-section d-flex">
                <div class="carousel-section">
                    <div class="">
                        <Graphics class="me-5" :sets="sets"/>
                    </div>
                </div>
            </div>

            <div class="text-section d-flex flex-column justify-content-center">
                <div class="text-container-graph">
                    <p class="fst-italic ms-5">Evolución mensual</p>
                </div>
                <p class="text-end fs-3 fst-italic me-5 pe-4">Para un mayor nivel de detalle, ponte en contacto con tus entrenadores</p>
            </div>
        </div>
    </div>
    
</template>

<script>
import Graphics from '@/components/home/Graphics.vue';

export default {
    components: {
        Graphics
    },

    setup() {
        const sets = [
            [60, 70, 90, 43, 56],
            [69, 79, 93, 50, 45]
        ];

        return {sets};
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
