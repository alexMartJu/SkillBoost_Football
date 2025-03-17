<template>
  <section class="tecnificaciones-section py-5">
    <div class="container-xxl position-relative">
      <!-- Header Section -->
      <div class="row mb-5">
        <div class="col-lg-8 mx-auto text-center">
          <h2 class="display-4 fw-bold gradient-text mb-4">Nuestras Tecnificaciones</h2>
          <p class="fs-5 mb-0">Programas especializados para potenciar tu rendimiento deportivo</p>
        </div>
      </div>

      <!-- Carousel Section con contenedor para posicionamiento -->
      <div class="carousel-container">
        <!-- Navegación fuera del swiper pero dentro del contenedor -->
        <div class="swiper-button-prev custom-prev-button"></div>
        <div class="swiper-button-next custom-next-button"></div>

        <div class="tecnificaciones-carousel">
          <swiper :modules="modules" :slides-per-view="1" :space-between="20" :loop="true"
            :pagination="{ clickable: true, el: '.swiper-pagination' }" :navigation="{
              nextEl: '.custom-next-button',
              prevEl: '.custom-prev-button',
            }" :breakpoints="{
                1400: { slidesPerView: 4 },
                1200: { slidesPerView: 3 },
                992: { slidesPerView: 3 },
                768: { slidesPerView: 2 },
                576: { slidesPerView: 1 }
              }" class="swiper-container">
            <swiper-slide v-for="tecnificacion in state.tecnificaciones.slice(0, 8)" :key="tecnificacion.id">
              <div class="px-2">
                <CardTecnificaciones :tecnificacion="tecnificacion" />
              </div>
            </swiper-slide>
          </swiper>
        </div>
      </div>

      <!-- Paginación debajo del swiper -->
      <div class="swiper-pagination mt-4"></div>
    </div>
  </section>
</template>

<script>
import { reactive, computed } from 'vue';
import { useStore } from 'vuex';
import Constant from '../../Constant';
import CardTecnificaciones from './CardTecnificacionesHome.vue';
//Importaciones de Swiper
import { Swiper, SwiperSlide } from 'swiper/vue';
import { Navigation, Pagination, Autoplay } from 'swiper/modules';
import 'swiper/css';
import 'swiper/css/navigation';
import 'swiper/css/pagination';

export default {
  components: {
    CardTecnificaciones,
    Swiper,
    SwiperSlide
  },
  setup() {
    const store = useStore();

    store.dispatch(`tecnificaciones/${Constant.INITIALIZE_TECNIFICACION}`);

    const state = reactive({
      tecnificaciones: computed(() => store.getters['tecnificaciones/GetTecnificaciones'])
    });

    //Módulos de Swiper
    const modules = [Navigation, Pagination, Autoplay];

    return {
      state,
      modules
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

/* Contenedor para posicionamiento relativo */
.carousel-container {
  position: relative;
  padding: 0 20px;
}

.tecnificaciones-carousel {
  margin: 0;
  padding: 0;
  overflow: hidden;
}

.swiper-container {
  overflow: hidden;
  padding: 0 10px;
}

/* Botones de navegación personalizados */
.custom-prev-button,
.custom-next-button {
  position: absolute;
  width: 3rem;
  height: 3rem;
  border-radius: 50%;
  background: rgba(255, 255, 255, 0.9);
  color: #333;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  z-index: 10;
  transition: all 0.3s ease;
}

.custom-prev-button {
  left: -20px;
}

.custom-next-button {
  right: -20px;
}

.custom-prev-button:hover,
.custom-next-button:hover {
  background: var(--bs-primary);
  color: white;
}

.custom-prev-button::after,
.custom-next-button::after {
  font-size: 1.2rem;
  font-family: 'swiper-icons';
}

.custom-prev-button::after {
  content: 'prev';
}

.custom-next-button::after {
  content: 'next';
}

/* Paginación */
:deep(.swiper-pagination) {
  position: static;
  margin-top: 20px;
}

:deep(.swiper-pagination-bullet) {
  width: 10px;
  height: 10px;
  margin: 0 5px;
}

:deep(.swiper-pagination-bullet-active) {
  background: var(--bs-primary);
}

@media (max-width: 768px) {
  .tecnificaciones-section {
    padding: 60px 0;
  }

  .gradient-text {
    font-size: 2.5rem;
  }

  .custom-prev-button,
  .custom-next-button {
    width: 2.5rem;
    height: 2.5rem;
  }
}
</style>