<template>
  <div class="hero-section vh-100 position-relative overflow-hidden">
    <!-- Video Background -->
    <div class="video-container position-absolute w-100 h-100">
      <video autoplay muted loop playsinline class="w-100 h-100 object-fit-cover">
        <source src="/assets/football-training.mp4" type="video/mp4">
      </video>
      <div class="gradient-overlay position-absolute top-0 start-0 w-100 h-100"></div>
    </div>

    <!-- Hero Content -->
    <div class="position-relative h-100 d-flex align-items-center justify-content-center">
      <div class="container px-4">
        <div class="row">
          <div class="col-lg-7 text-white hero-content">
            <div class="logo-container mb-4">
              <img src="/assets/logo_3.png" alt="SkillBoost Football" class="logo-animation">
            </div>
            <h1 class="display-1 fw-bold mb-3 text-uppercase hero-title">
              <span class="text-reveal">Eleva tu</span>
              <span class="gradient-text">Potencial</span>
            </h1>
            <p class="lead mb-5 hero-subtitle">Tecnificación de élite para el futbolista del mañana</p>
            
            <div class="d-flex flex-wrap gap-3 mt-4 hero-buttons">
              <button class="btn btn-primary btn-lg pulse-button" @click="$router.push({ name: 'entrena' })">Descubre nuestros programas</button>
              <button class="btn btn-outline-light btn-lg" @click="$router.push({ name: 'recursos' })">Conócenos</button>
            </div>
          </div>
          
          <div class="col-lg-5 d-none d-lg-flex align-items-center">
            <div class="stats-container">
              <div class="stat-card" v-for="(stat, index) in stats" :key="index">
                <div class="stat-number">{{ stat.number }}</div>
                <div class="stat-label">{{ stat.label }}</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Scroll Indicator -->
    <div class="scroll-indicator position-absolute bottom-0 start-50 translate-middle-x text-white pb-5">
      <div class="scroll-mouse mx-auto mb-2"></div>
      <p class="mb-0 scroll-text">Explora nuestro mundo</p>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      stats: [
        { number: '95%', label: 'Tasa de mejora técnica' },
        { number: '+500', label: 'Jugadores formados' },
        { number: '12', label: 'Entrenadores de élite' }
      ]
    }
  }
}
</script>

<style scoped>
.hero-section {
  margin-top: -87px;
  position: relative;
}

.video-container {
  z-index: -1;
}

.gradient-overlay {
  background: linear-gradient(135deg, rgba(0, 0, 0, 0.85) 0%, rgba(0, 0, 0, 0.6) 50%, rgba(0, 0, 0, 0.3) 100%);
  z-index: 1;
}

.hero-content {
  z-index: 2;
  animation: fadeIn 1.5s ease-out;
}

.logo-animation {
  width: 180px;
  filter: drop-shadow(0 0 20px rgba(var(--bs-primary-rgb), 0.5));
  animation: floatAnimation 4s ease-in-out infinite;
}

.hero-title {
  font-weight: 900;
  letter-spacing: 2px;
  line-height: 1.1;
}

.text-reveal {
  display: block;
  overflow: hidden;
  animation: revealText 1.2s cubic-bezier(0.77, 0, 0.175, 1);
}

.gradient-text {
  display: block;
  background: linear-gradient(90deg, #4CAF50, #2196F3);
  -webkit-background-clip: text;
  background-clip: text;
  color: transparent;
  font-size: 1.2em;
  animation: gradientFlow 3s linear infinite;
}

.hero-subtitle {
  font-weight: 300;
  letter-spacing: 1px;
  opacity: 0;
  animation: fadeInUp 1s ease-out 0.5s forwards;
}

.hero-buttons {
  opacity: 0;
  animation: fadeInUp 1s ease-out 0.8s forwards;
}

.pulse-button {
  position: relative;
  box-shadow: 0 0 0 0 rgba(var(--bs-primary-rgb), 0.7);
  animation: pulse 2s infinite;
}

.stats-container {
  display: grid;
  grid-template-columns: repeat(1, 1fr);
  gap: 20px;
  opacity: 0;
  animation: fadeInRight 1s ease-out 1s forwards;
}

.stat-card {
  cursor: pointer;
  background: rgba(255, 255, 255, 0.1);
  backdrop-filter: blur(10px);
  border-radius: 15px;
  padding: 20px;
  text-align: center;
  color: white;
  border: 1px solid rgba(255, 255, 255, 0.2);
  transition: transform 0.3s, box-shadow 0.3s;
}

.stat-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
}

.stat-number {
  font-size: 2.5rem;
  font-weight: 700;
  margin-bottom: 5px;
  background: linear-gradient(90deg, #4CAF50, #2196F3);
  -webkit-background-clip: text;
  background-clip: text;
  color: transparent;
}

.stat-label {
  font-size: 1rem;
  font-weight: 300;
}

.scroll-indicator {
  z-index: 2;
  opacity: 0;
  animation: fadeIn 1s ease-out 1.5s forwards;
}

.scroll-mouse {
  width: 30px;
  height: 50px;
  border: 2px solid white;
  border-radius: 20px;
  position: relative;
}

.scroll-mouse::before {
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

.scroll-text {
  font-size: 0.9rem;
  letter-spacing: 1px;
}

@keyframes floatAnimation {
  0%, 100% {
    transform: translateY(0);
  }
  50% {
    transform: translateY(-15px);
  }
}

@keyframes fadeIn {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}

@keyframes fadeInUp {
  from {
    opacity: 0;
    transform: translateY(20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

@keyframes fadeInRight {
  from {
    opacity: 0;
    transform: translateX(50px);
  }
  to {
    opacity: 1;
    transform: translateX(0);
  }
}

@keyframes revealText {
  0% {
    transform: translateY(100%);
  }
  100% {
    transform: translateY(0);
  }
}

@keyframes gradientFlow {
  0% {
    background-position: 0% 50%;
  }
  50% {
    background-position: 100% 50%;
  }
  100% {
    background-position: 0% 50%;
  }
}

@keyframes pulse {
  0% {
    box-shadow: 0 0 0 0 rgba(var(--bs-primary-rgb), 0.7);
  }
  70% {
    box-shadow: 0 0 0 10px rgba(var(--bs-primary-rgb), 0);
  }
  100% {
    box-shadow: 0 0 0 0 rgba(var(--bs-primary-rgb), 0);
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

@media (max-width: 992px) {
  .hero-title {
    font-size: 3.5rem !important;
  }
  
  .gradient-text {
    font-size: 1em;
  }
  
  .hero-subtitle {
    font-size: 1.2rem !important;
  }
}

@media (max-width: 768px) {
  .hero-title {
    font-size: 2.8rem !important;
  }
  
  .hero-buttons {
    flex-direction: column;
  }
  
  .btn {
    width: 100%;
    margin-bottom: 10px;
  }
}
</style>
