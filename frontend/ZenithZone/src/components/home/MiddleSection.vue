<template>
  <div class="facilities-section py-5">
    <div class="container-xxl">
      <div class="row g-5">
        <!-- Text Section -->
        <div class="col-lg-4">
          <div class="d-flex flex-column justify-content-center h-100 p-4">
            <h2 class="display-4 fw-bold gradient-text mb-3">Instalaciones de Elite</h2>
            <p class="fs-4 text-secondary mb-4">Donde el rendimiento encuentra la excelencia</p>
            <div class="d-flex flex-column gap-4">
              <div class="d-flex align-items-center gap-3">
                <i class="bi bi-award fs-4 text-primary"></i>
                <span class="fs-5">Tecnología de Última Generación</span>
              </div>
              <div class="d-flex align-items-center gap-3">
                <i class="bi bi-lightning fs-4 text-primary"></i>
                <span class="fs-5">Equipamiento Profesional</span>
              </div>
              <div class="d-flex align-items-center gap-3">
                <i class="bi bi-shield-check fs-4 text-primary"></i>
                <span class="fs-5">Personal Certificado</span>
              </div>
            </div>
          </div>
        </div>

        <!-- Gallery Section -->
        <div class="col-lg-8">
          <div class="gallery-wrapper rounded-4 shadow-lg overflow-hidden">
            <Splide :options="splideOptions" class="gallery-slider">
              <SplideSlide v-for="(image, index) in images" :key="index">
                <div class="position-relative" style="height: 600px;">
                  <img :src="image.src" :alt="image.alt" class="w-100 h-100 object-fit-cover" />
                  <div class="position-absolute bottom-0 start-0 end-0 p-4 overlay-gradient">
                    <h3 class="display-6 text-white mb-2">{{ image.title }}</h3>
                    <p class="fs-5 text-white-75">{{ image.description }}</p>
                  </div>
                </div>
              </SplideSlide>
            </Splide>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { Splide, SplideSlide } from "@splidejs/vue-splide";
import "@splidejs/splide/dist/css/splide.min.css";

export default {
  name: "MiddleSection",
  components: { Splide, SplideSlide },
  data() {
    return {
      splideOptions: {
        type: "fade",
        rewind: true,
        autoplay: true,
        interval: 4000,
        arrows: true,
        pagination: true,
        speed: 1500,
        pauseOnHover: true,
      },
      images: [
        {
          src: "/assets/home/pista_atletismo.jpg",
          alt: "Pista de Atletismo",
          title: "Pista Olímpica",
          description: "Superficie profesional certificada IAAF"
        },
        {
          src: "/assets/home/piscina.jpg",
          alt: "Piscina Olímpica",
          title: "Centro Acuático",
          description: "Piscina olímpica climatizada"
        },
        {
          src: "/assets/home/pista_ciclismo.jpg",
          alt: "Velódromo",
          title: "Velódromo Indoor",
          description: "Pista de ciclismo de clase mundial"
        },
        {
          src: "/assets/home/rocodromo.jpg",
          alt: "Rocódromo",
          title: "Muro de Escalada",
          description: "15 metros de altura con rutas variables"
        },
        {
          src: "/assets/home/pista_padel.jpg",
          alt: "Pista de Pádel",
          title: "Complejo de Pádel",
          description: "Pistas cubiertas con iluminación LED"
        }
      ]
    };
  }
};
</script>

<style scoped>
.facilities-section {
  background: linear-gradient(135deg, #f8f9fa 0%, #e9ecef 100%);
}

.gradient-text {
  background: linear-gradient(45deg, var(--bs-primary), #141414f2);
  -webkit-background-clip: text;
  background-clip: text;
  color: transparent;
}

.overlay-gradient {
  background: linear-gradient(to top, rgba(0, 0, 0, 0.8), transparent);
}

.text-white-75 {
  opacity: 0.9;
  color: white;
}

@media (max-width: 992px) {
  .facilities-section .col-lg-5 {
    text-align: center;
  }

  .facilities-section .d-flex {
    justify-content: center;
  }
}
</style>