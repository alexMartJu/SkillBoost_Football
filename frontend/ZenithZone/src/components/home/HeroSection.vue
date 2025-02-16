<template>
  <div class="hero-section vh-100 position-relative overflow-hidden">
    <!-- Image Background con Parallax -->
    <div class="position-absolute w-100 h-100">
      <img class="w-100 h-100 object-fit-cover zoom-effect" src="/assets/home/atletismo.jpg" alt="Atletismo">
      <div class="gradient-overlay position-absolute top-0 start-0 w-100 h-100"></div>
    </div>

    <!-- Hero Content -->
    <div class="position-relative h-100 d-flex align-items-center justify-content-center text-center text-white">
      <div class="container px-4">
        <img src="/assets/logo_3.png" alt="ZenithZone Logo" class="logo-animation mb-4">
        <h1 class="display-1 fw-bold text-uppercase mb-4 custom-title">Alcanza tu Zénit</h1>
        <p class="display-6 fw-light custom-subtitle">Donde la excelencia deportiva encuentra su hogar</p>
      </div>
    </div>

    <!-- Indicador Scroll -->
    <div class="position-absolute bottom-0 start-50 translate-middle-x text-white pb-5">
      <div class="custom-mouse mx-auto mb-2"></div>
      <p class="mb-0">Descubre más</p>
    </div>
  </div>
</template>

<script></script>

<style scoped>
.hero-section {
  margin-top: -87px;
}

.zoom-effect {
  animation: slowZoom 20s infinite alternate;
}

.gradient-overlay {
  background: linear-gradient(135deg,
      rgba(0, 0, 0, 0.85) 0%,
      rgba(0, 0, 0, 0.6) 50%,
      rgba(0, 0, 0, 0.4) 100%);
}

.logo-animation {
  width: 250px;
  animation: floatAnimation 3s ease-in-out infinite;
  filter: drop-shadow(0 0 20px rgba(var(--bs-primary-rgb), 0.3));
}

.custom-title {
  letter-spacing: 3px;
  text-shadow:
    3px 3px 0 var(--bs-primary),
    -1px -1px 0 var(--bs-primary),
    1px -1px 0 var(--bs-primary),
    -1px 1px 0 var(--bs-primary),
    1px 1px 0 var(--bs-primary);
}

.custom-subtitle {
  letter-spacing: 1px;
}

.custom-mouse {
  width: 30px;
  height: 50px;
  border: 2px solid white;
  border-radius: 20px;
  position: relative;
}

.custom-mouse::before {
  content: '';
  position: absolute;
  top: 10px;
  left: 50%;
  transform: translateX(-50%);
  width: 6px;
  height: 6px;
  background: white;
  border-radius: 50%;
  animation: scrollAnimation 2s infinite;
}

@keyframes slowZoom {
  from {
    transform: scale(1.1);
  }

  to {
    transform: scale(1.2);
  }
}

@keyframes floatAnimation {

  0%,
  100% {
    transform: translateY(0);
  }

  50% {
    transform: translateY(-20px);
  }
}

@keyframes scrollAnimation {
  0% {
    opacity: 1;
    transform: translate(-50%, 0);
  }

  100% {
    opacity: 0;
    transform: translate(-50%, 20px);
  }
}

@media (max-width: 768px) {
  .logo-animation {
    width: 180px;
  }

  .custom-title {
    font-size: 3.5rem !important;
  }

  .custom-subtitle {
    font-size: 1.4rem !important;
  }
}
</style>
