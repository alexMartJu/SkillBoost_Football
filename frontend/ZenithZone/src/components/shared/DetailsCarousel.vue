<template>
    <div class="container-fluid p-0">
        <div class="row">
            <div class="col-12">
                <Splide :options="splideOptions">
                    <SplideSlide v-for="(image, index) in getImages" :key="index" class="ratio ratio-16x9">
                        <img :src="getImagePath(image)" class="img-fluid w-100 h-100 object-fit-cover" alt="" />
                    </SplideSlide>
                </Splide>
            </div>
        </div>
    </div>
</template>

<script>
import { Splide, SplideSlide } from "@splidejs/vue-splide";
import "@splidejs/splide/dist/css/splide.min.css";
import { computed } from "vue";

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

    setup(props) {
        const getImages = computed(() => {
            if (!props.pistas) return [];
            return props.pistas.images || [];
        });

        const getImagePath = (image) => {
            if (!image) return '';
            
            // Determine the correct path based on the type of content
            if (props.pistas.tipoEntrenamiento) {
                // It's a subtipo tecnificacion
                return `/assets/subtipos/${image.imageUrl}`;
            } else {
                // It's a pista or something else
                return `/assets/pistas/${image.imageUrl}`;
            }
        };

        return {
            getImages,
            getImagePath,
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
