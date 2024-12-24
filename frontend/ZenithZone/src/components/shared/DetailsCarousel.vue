<template>
    <Splide :options="splideOptions">
        <SplideSlide>
            <img src="https://via.placeholder.com/500" class="d-block w-100 h-100 object-fit-cover" alt="" />
        </SplideSlide>
        <SplideSlide>
            <img src="https://via.placeholder.com/500" class="d-block w-100 h-100 object-fit-cover" alt="" />
        </SplideSlide>
        <SplideSlide>
            <img src="https://via.placeholder.com/500" class="d-block w-100 h-100 object-fit-cover" alt="" />
        </SplideSlide>
    </Splide>

    <!-- Este es el carrusel si hay imagenes -->
    <!-- <Splide :options="splideOptions">
        <SplideSlide v-for="pista in pistas.images" :key="pista.id">
            <img :src="`/assets/pistas/${pista.imageUrl}`" class="d-block w-100 h-100 object-fit-cover" alt="" />
        </SplideSlide>
    </Splide> -->
</template>


<script>
import { Splide, SplideSlide } from "@splidejs/vue-splide";
import "@splidejs/splide/dist/css/splide.min.css";

export default {
    props: {
        pistas: {
            type: Object,
            required: true,
        },
    },
    
    components: {
        Splide,
        SplideSlide,
    },

    data() {
        return {
            splideOptions: {
                type: "loop",
                autoplay: false,
                height: "100%",
                arrows: true,
                pagination: true,
            },
        };
    }
};
</script>

<style scoped>
</style>