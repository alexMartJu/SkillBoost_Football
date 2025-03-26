<template>
  <div class="p-3">
    <div class="card border-0 rounded-4 shadow overflow-hidden h-100 coach-card">
      <!-- Contenedor de imagen -->
      <div class="position-relative coach-image-container">
        <img v-if="entrenador.image" :src="`${entrenador.image}`"
          class="w-100 h-100 coach-image" alt="Foto de perfil">
        <img v-else src="https://static.productionready.io/images/smiley-cyrus.jpg"
          class="w-100 h-100 coach-image" alt="Foto de perfil">
        
        <!-- Badge con icono aleatorio -->
        <div class="position-absolute bottom-0 end-0 m-3 bg-primary coach-badge d-flex align-items-center justify-content-center">
          <i :class="randomIcon"></i>
        </div>
      </div>
      
      <!-- Información del entrenador -->
      <div class="card-body text-center p-4">
        <h5 class="fw-bold mb-3">{{ entrenador.nombre }} {{ entrenador.apellidos }}</h5>
        
        <div class="mb-3">
          <span class="badge rounded-pill experience-badge">
            {{ entrenador.experienciaAnios || '0' }} años de experiencia
          </span>
        </div>
        
        <div class="d-flex align-items-center justify-content-center gap-2 bg-light p-3 rounded-3 mb-3">
          <i class="bi bi-envelope-fill text-primary"></i>
          <span>{{ entrenador.email }}</span>
        </div>
        
        <button @click="redirects.profileEntrenador" class="btn btn-primary w-100 view-profile-btn d-flex align-items-center justify-content-center">
          Ver perfil
          <i class="bi bi-arrow-right-circle ms-2"></i>
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import router from '@/router/router';
import { useRouter } from 'vue-router';
import { ref, onMounted } from 'vue';

export default {
  props: {
    entrenador: {
      type: Object,
      required: true,
    },
  },

  setup(props) {
    const router = useRouter();
    const randomIcon = ref('bi bi-trophy-fill text-white');

    // Lista de iconos posibles
    const icons = [
      'bi bi-trophy-fill text-white',
      'bi bi-award-fill text-white',
      'bi bi-star-fill text-white',
      'bi bi-person-badge-fill text-white'
    ];

    // Seleccionar un icono aleatorio al montar el componente
    onMounted(() => {
      const randomIndex = Math.floor(Math.random() * icons.length);
      randomIcon.value = icons[randomIndex];
    });

    const redirects = {
      profileEntrenador: () => router.push({ name: 'profileEntrenador', params: { numeroentrenador: props.entrenador.numeroEntrenador } }),
    };

    return { redirects, randomIcon }
  }
}
</script>

<style scoped>
.coach-card {
  max-width: 320px;
  margin: 0 auto;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.coach-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 15px 35px rgba(0, 98, 204, 0.15) !important;
}

.coach-image-container {
  height: 240px;
  overflow: hidden;
}

.coach-image {
  object-fit: cover;
  transition: transform 0.5s ease;
}

.coach-card:hover .coach-image {
  transform: scale(1.05);
}

.coach-badge {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  border: 3px solid white;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.experience-badge {
  background-color: rgba(0, 98, 204, 0.1);
  color: var(--bs-primary);
  padding: 0.5rem 1rem;
  font-size: 0.9rem;
  font-weight: 600;
}

.view-profile-btn {
  padding: 0.7rem 1.5rem;
  border-radius: 8px;
  font-weight: 600;
  transition: all 0.2s ease;
}

.view-profile-btn:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 8px rgba(0, 98, 204, 0.2);
}
</style>