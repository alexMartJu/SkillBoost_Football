<template>
  <div class="bg-light py-5">
    <div class="container-xxl">
      <div class="row g-5 align-items-center">
        <!-- Gallery Section -->
        <div class="col-lg-8">
          <div class="position-relative rounded-4 overflow-hidden shadow-lg">
            <div style="height: 600px;">
              <img src="/assets/home/olympics.webp" alt="Olympics" class="w-100 h-100 object-fit-cover" />
              <div class="position-absolute bottom-0 start-0 w-100 p-4 overlay-gradient">
                <h3 class="display-6 text-white mb-2">Formación Olímpica</h3>
                <p class="text-white-50 fs-5 mb-0">Preparación de atletas de élite</p>
              </div>
            </div>
          </div>
        </div>

        <!-- Text Section -->
        <div class="col-lg-4">
          <div class="p-4">
            <h2 class="display-4 fw-bold gradient-text mb-3">Creamos Leyendas</h2>
            <p class="fs-4 text-secondary mb-4">Donde nacen los campeones olímpicos</p>
            
            <div class="d-flex flex-column gap-4">
              <div class="d-flex align-items-center gap-3">
                <i class="bi bi-trophy fs-4 text-primary"></i>
                <span class="fs-5">Entrenamiento de Alto Rendimiento</span>
              </div>
              <div class="d-flex align-items-center gap-3">
                <i class="bi bi-star fs-4 text-primary"></i>
                <span class="fs-5">Preparación Olímpica</span>
              </div>
              <div class="d-flex align-items-center gap-3">
                <i class="bi bi-award fs-4 text-primary"></i>
                <span class="fs-5">Excelencia Deportiva</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Olympics"
};
</script>

<style scoped>
.gradient-text {
  background: linear-gradient(45deg, var(--bs-primary), #141414f2);
  -webkit-background-clip: text;
  background-clip: text;
  color: transparent;
}

.overlay-gradient {
  background: linear-gradient(to top, rgba(0,0,0,0.8), transparent);
}

.object-fit-cover {
  object-fit: cover;
}

@media (max-width: 992px) {
  .row {
    flex-direction: column-reverse;
  }
}
</style>
