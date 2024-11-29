<template>
    <div class="graph-main ms-5">
        <div class="section-graph d-flex">
            <div class="landing-section d-flex">
                <div class="carousel-section col-md-6 p-0">
                    <div class="">
                        <Graphics class="m-5" />
                    </div>
                </div>
            </div>

            <div class="text-section col-md-6 d-flex flex-column justify-content-center px-4">
                <div class="text-container-graph">
                    <p class="fst-italic ms-5">ANALISIS DE RENDIMIENTO COMPLETO</p>
                </div>
                <p class="text-end fs-3 fst-italic me-5 pe-4">"Sigue el progreso del próximo campeón"</p>
            </div>
        </div>
    </div>
    
</template>

<script>
import Graphics from '@/components/home/Graphics.vue';

export default {
    components: {
        Graphics
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
