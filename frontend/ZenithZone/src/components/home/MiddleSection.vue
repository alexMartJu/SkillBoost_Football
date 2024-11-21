<template>
<div class="landing-section d-flex">
    <!-- Sección del texto -->
    <div class="text-section col-md-6 d-flex flex-column justify-content-center px-4">
        <div class="text-container">
            <p class="fw-bold text-center">
                LAS MEJORES INSTALACIONES
            </p>
        </div>
    </div>

    <!-- Sección del carrusel -->
    <div class="carousel-section col-md-6 p-0">
        <Splide :options="splideOptions" class="h-100">
            <SplideSlide>
                <img src="../../assets/home/pista_1.jpg" class="d-block w-100 h-100 object-fit-cover" alt="Imagen 1" />
            </SplideSlide>
            <SplideSlide>
                <img src="../../assets/home/pista_2.jpg" class="d-block w-100 h-100 object-fit-cover" alt="Imagen 2" />
            </SplideSlide>
            <SplideSlide>
                <img src="../../assets/home/pista_3.jpg" class="d-block w-100 h-100 object-fit-cover" alt="Imagen 3" />
            </SplideSlide>
            <SplideSlide>
                <img src="../../assets/home/pista_4.jpg" class="d-block w-100 h-100 object-fit-cover" alt="Imagen 3" />
            </SplideSlide>
            <SplideSlide>
                <img src="../../assets/home/pista_5.jpg" class="d-block w-100 h-100 object-fit-cover" alt="Imagen 3" />
            </SplideSlide>
        </Splide>
    </div>
</div>
</template>


<script>
import { Splide, SplideSlide } from "@splidejs/vue-splide";
import "@splidejs/splide/dist/css/splide.min.css";

export default {
    name: "MiddleSection",
    components: {
        Splide,
        SplideSlide,
    },
    data() {
        return {
            splideOptions: {
                type: "loop",
                autoplay: true,
                interval: 5000,
                height: "100%",
                arrows: false,
                pagination: true,
            },
        };
    },
};
</script>

<style scoped>
.landing-section {
    height: 70vh;
    overflow: hidden;
}

.text-section {
    background: linear-gradient(to bottom right, #ff5328, #ffdd62);
    color: white;
    width: 35%;
    /* Ancho del texto */
    display: flex;
    justify-content: center;
    align-items: center;
    box-shadow: 0 0 15px 10px rgba(0, 0, 0, 0.5);
    z-index: 5;
}

.text-container{
    background: linear-gradient(to bottom right, #ffde65, #ff8363);
    color: white;
    height: 400px;
    width: 400px;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 44px;
    font-family: 'Roboto', sans-serif;
    box-shadow: 20px 20px 0 5px rgba(255, 255, 255, 0.4);
}

.carousel-section {
    position: relative;
    width: 65%;
    z-index: 1;
}

.object-fit-cover {
    object-fit: cover;
}
</style>
