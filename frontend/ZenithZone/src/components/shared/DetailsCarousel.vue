<template>
    <!-- <Splide :options="splideOptions">
        <SplideSlide>
            <img src="https://placehold.co/600x400" class="d-block w-100 h-100 object-fit-cover" alt="" />
        </SplideSlide>
        <SplideSlide>
            <img src="https://placehold.co/600x400/png" class="d-block w-100 h-100 object-fit-cover" alt="" />
        </SplideSlide>
        <SplideSlide>
            <img src="https://placehold.co/600x400/jpeg" class="d-block w-100 h-100 object-fit-cover" alt="" />
        </SplideSlide>
    </Splide> -->
    
    <!-- Este es el carrusel si hay imagenes -->
    <div class="container-fluid p-0">
        <div class="row">
            <div class="col-12">
                <Splide :options="splideOptions">
                    <SplideSlide v-for="pista in pistas.images" :key="pista.id" class="ratio ratio-16x9">
                        <img :src="`/assets/pistas/${pista.imageUrl}`" class="img-fluid w-100 h-100 object-fit-cover" alt="" />
                    </SplideSlide>
                </Splide>
            </div>
        </div>
    </div>
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
                height: "auto",
                width: "100%",
                arrows: true,
                pagination: true,
                breakpoints: {
                    576: {
                        arrows: false,
                    },
                    768: {
                        arrows: true,
                    }
                },
                gap: '1rem',
                rewind: true,
                perPage: 1,
                perMove: 1,
            },
        };
    }
};
</script>

<style scoped>
:deep(.splide__arrow) {
    background: rgba(0, 0, 0, 0.5);
}

:deep(.splide__pagination__page) {
    background: rgba(0, 0, 0, 0.5);
}

:deep(.splide__pagination__page.is-active) {
    background: rgba(0, 0, 0, 0.8);
}
</style>