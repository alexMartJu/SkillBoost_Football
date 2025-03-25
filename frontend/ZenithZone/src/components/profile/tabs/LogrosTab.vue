<template>
  <div class="logros-tab p-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
      <h4 class="mb-0">Mis Logros</h4>
    </div>

    <div v-if="loading" class="text-center my-5">
      <div class="spinner-border text-primary" role="status">
        <span class="visually-hidden">Cargando...</span>
      </div>
    </div>

    <div v-else-if="logros.length === 0" class="text-center my-5 py-5">
      <i class="bi bi-trophy text-muted" style="font-size: 3rem;"></i>
      <p class="mt-3 text-muted">No tienes logros disponibles</p>
      <p class="text-muted small">¡Participa en entrenamientos para desbloquear logros!</p>
    </div>

    <div v-else>
      <div class="row">
        <div v-for="logro in logros" :key="logro.id" class="col-md-6 col-lg-4 mb-4">
          <div class="card h-100 shadow-sm border-0 rounded-3 hover-card">
            <div class="card-body text-center">
              <div class="logro-progress mb-3">
                <v-progress-circular :model-value="calculateProgress(logro)" :rotate="360" :size="100" :width="15"
                  :color="getColorByDifficulty(logro.nivelDificultad)">
                  <template v-slot:default>{{ calculateProgress(logro).toFixed(0) }}%</template>
                </v-progress-circular>
              </div>

              <h5 class="card-title fw-bold mb-2">{{ logro.nombre }}</h5>
              <p class="card-text text-muted mb-3">{{ logro.descripcion }}</p>
              <div class="logro-details">
                <div class="d-flex justify-content-between">
                  <span><i class="bi bi-bar-chart-fill me-1"></i>Dificultad:</span>
                  <span class="badge" :class="getBadgeClass(logro.nivelDificultad)">{{ logro.nivelDificultad }}</span>
                </div>
              </div>

              <div class="mt-3">
                <span v-if="logro.completado" class="badge bg-success px-3 py-2">
                  <i class="bi bi-check-circle-fill me-1"></i>Completado
                  <small v-if="logro.fechaLogro" class="d-block mt-1">{{ formatDate(logro.fechaLogro) }}</small>
                </span>
                <span v-else class="badge bg-secondary px-3 py-2">En progreso</span>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- Información adicional sobre los logros -->
      <div class="card mt-4 shadow-sm border-0 rounded-3">
        <div class="card-body">
          <h5 class="card-title mb-3"><i class="bi bi-info-circle-fill me-2 text-primary"></i>Información sobre los
            logros
          </h5>
          <p class="card-text">Los logros son una forma de reconocer tu dedicación y progreso en SkillBoost Football.
            Completa
            entrenamientos para avanzar en los siguientes niveles:</p>

          <div class="row mt-4">
            <div v-for="logro in logros" :key="`info-${logro.id}`" class="col-md-4 mb-3">
              <div class="achievement-info p-3 bg-light rounded">
                <h6 :class="`text-${getColorByDifficulty(logro.nivelDificultad)}`">
                  <i class="bi bi-trophy-fill me-2"></i>{{ logro.nombre }}
                </h6>
                <p class="small mb-1">Completa {{ logro.requisitoEntrenamientos }} entrenamientos</p>
                <p class="small text-muted">Recompensa: {{ logro.recompensa }}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, computed, onMounted } from 'vue';
import { useStore } from 'vuex';
import Constant from '@/Constant';

export default {
  name: 'LogrosTab',
  setup() {
    const store = useStore();
    const loading = ref(true);

    //Obtener logros del store
    const logros = computed(() => {
      return store.getters['profile/GetLogros'] || [];
    });

    //Calcular el porcentaje de progreso exacto
    const calculateProgress = (logro) => {
      if (logro.completado) {
        return 100;
      }

      const entrenamientosCompletados = logro.progreso || 0;
      const requisitoEntrenamientos = logro.requisitoEntrenamientos || 1;
      // Calcular el porcentaje exacto
      return Math.min((entrenamientosCompletados / requisitoEntrenamientos) * 100, 99);
    };

    //Formatear fecha
    const formatDate = (dateString) => {
      if (!dateString) return '';

      const date = new Date(dateString);
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, '0');
      const day = String(date.getDate()).padStart(2, '0');

      return `${year}-${month}-${day}`;
    };

    //Obtener color según dificultad
    const getColorByDifficulty = (difficulty) => {
      switch (difficulty?.toLowerCase()) {
        case 'fácil':
        case 'facil':
          return 'success';
        case 'medio':
          return 'warning';
        case 'difícil':
        case 'dificil':
          return 'danger';
        default:
          return 'primary';
      }
    };

    //Obtener clase de badge según dificultad
    const getBadgeClass = (difficulty) => {
      switch (difficulty?.toLowerCase()) {
        case 'fácil':
        case 'facil':
          return 'bg-success';
        case 'medio':
          return 'bg-warning text-dark';
        case 'difícil':
        case 'dificil':
          return 'bg-danger';
        default:
          return 'bg-primary';
      }
    };

    const loadLogros = async () => {
      loading.value = true;
      try {
        await store.dispatch(`profile/${Constant.INITIALIZE_LOGROS}`);
      } catch (error) {
        console.error("Error al cargar los logros:", error);
      } finally {
        loading.value = false;
      }
    };

    onMounted(() => {
      loadLogros();
    });

    return {
      loading,
      logros,
      calculateProgress,
      formatDate,
      getColorByDifficulty,
      getBadgeClass
    };
  }
};
</script>

<style scoped>
.logros-tab {
  width: 100%;
}

.hover-card {
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.hover-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1) !important;
}

.logro-progress {
  display: flex;
  justify-content: center;
  margin-bottom: 1.5rem;
}

.logro-details {
  background-color: #f8f9fa;
  border-radius: 8px;
  padding: 1rem;
  margin-top: 1rem;
  text-align: left;
}

.achievement-info {
  height: 100%;
  transition: transform 0.3s ease;
}

.achievement-info:hover {
  transform: translateY(-3px);
}

/* Ajustes para dispositivos móviles */
@media (max-width: 768px) {
  .col-md-6 {
    margin-bottom: 1rem;
  }
}
</style>
