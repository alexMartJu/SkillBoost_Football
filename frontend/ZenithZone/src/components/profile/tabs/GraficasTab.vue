<template>
  <div class="graficas-tab p-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
      <h4 class="mb-0">Mis Gráficas</h4>
      <div class="btn-group" role="group" v-if="hasGraficasAccess">
        <button type="button" class="btn btn-outline-primary" :class="{ 'active': selectedYear === currentYear }"
          @click="changeYear(currentYear)">
          {{ currentYear }}
        </button>
        <button type="button" class="btn btn-outline-primary" :class="{ 'active': selectedYear === currentYear - 1 }"
          @click="changeYear(currentYear - 1)">
          {{ currentYear - 1 }}
        </button>
      </div>
    </div>

    <div v-if="loading" class="text-center my-5">
      <div class="spinner-border text-primary" role="status">
        <span class="visually-hidden">Cargando...</span>
      </div>
    </div>

    <div v-else-if="!hasGraficasAccess" class="text-center my-5 py-5">
      <i class="bi bi-lock-fill text-muted" style="font-size: 3rem;"></i>
      <p class="mt-3 text-muted">No tienes acceso a las gráficas de rendimiento</p>
      <p class="text-muted small">Contacta con tu entrenador para más información</p>
    </div>

    <div v-else-if="graficas.length === 0" class="text-center my-5 py-5">
      <i class="bi bi-bar-chart-line text-muted" style="font-size: 3rem;"></i>
      <p class="mt-3 text-muted">No hay datos disponibles para el año {{ selectedYear }}</p>
    </div>

    <div v-else>
      <div class="row">
        <!-- Primera fila: Gráfica -->
        <div class="col-12 mb-4">
          <div class="graph-container">
            <Graphics :sets="graficas" />
          </div>
        </div>

        <!-- Segunda fila: Estadísticas y acordeón -->
        <div class="col-12">
          <div class="row">
            <div class="col-md-6 mb-4">
              <div class="analytics-info">
                <p class="info-text">Para un mayor nivel de detalle, contacta con tus entrenadores</p>
                <div class="stats-grid">
                  <div class="stat-card">
                    <span class="stat-value">{{ graficas.length }}</span>
                    <span class="stat-label">Meses registrados</span>
                  </div>
                  <div class="stat-card">
                    <span class="stat-value">{{ getAverageScore() }}</span>
                    <span class="stat-label">Promedio general</span>
                  </div>
                </div>
              </div>
            </div>

            <!-- Columna del acordeón -->
            <div class="col-md-6 mb-4">
              <div class="categories-info">
                <h5 class="mb-3">Categorías evaluadas</h5>
                <div class="accordion" id="accordionCategories">
                  <div class="accordion-item border-0 mb-2">
                    <h2 class="accordion-header" id="headingTecnica">
                      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                        data-bs-target="#collapseTecnica" aria-expanded="false" aria-controls="collapseTecnica">
                        <span class="category-dot" style="background-color: rgba(0, 162, 80, 1)"></span>
                        Técnica
                      </button>
                    </h2>
                    <div id="collapseTecnica" class="accordion-collapse collapse" aria-labelledby="headingTecnica"
                      data-bs-parent="#accordionCategories">
                      <div class="accordion-body">
                        Para evaluar las habilidades técnicas como control de balón, pase, regate, tiro y juego aéreo.
                      </div>
                    </div>
                  </div>

                  <div class="accordion-item border-0 mb-2">
                    <h2 class="accordion-header" id="headingTactica">
                      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                        data-bs-target="#collapseTactica" aria-expanded="false" aria-controls="collapseTactica">
                        <span class="category-dot" style="background-color: rgba(23, 162, 80, 1)"></span>
                        Táctica
                      </button>
                    </h2>
                    <div id="collapseTactica" class="accordion-collapse collapse" aria-labelledby="headingTactica"
                      data-bs-parent="#accordionCategories">
                      <div class="accordion-body">
                        Para medir el posicionamiento, lectura de juego, toma de decisiones y comprensión táctica.
                      </div>
                    </div>
                  </div>

                  <div class="accordion-item border-0 mb-2">
                    <h2 class="accordion-header" id="headingFisico">
                      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                        data-bs-target="#collapseFisico" aria-expanded="false" aria-controls="collapseFisico">
                        <span class="category-dot" style="background-color: rgba(46, 162, 80, 1)"></span>
                        Físico
                      </button>
                    </h2>
                    <div id="collapseFisico" class="accordion-collapse collapse" aria-labelledby="headingFisico"
                      data-bs-parent="#accordionCategories">
                      <div class="accordion-body">
                        Para evaluar aspectos como velocidad, resistencia, agilidad, potencia y coordinación.
                      </div>
                    </div>
                  </div>

                  <div class="accordion-item border-0 mb-2">
                    <h2 class="accordion-header" id="headingMental">
                      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                        data-bs-target="#collapseMental" aria-expanded="false" aria-controls="collapseMental">
                        <span class="category-dot" style="background-color: rgba(69, 162, 80, 1)"></span>
                        Mental
                      </button>
                    </h2>
                    <div id="collapseMental" class="accordion-collapse collapse" aria-labelledby="headingMental"
                      data-bs-parent="#accordionCategories">
                      <div class="accordion-body">
                        Para medir concentración, confianza, liderazgo, gestión de presión y capacidad de aprendizaje.
                      </div>
                    </div>
                  </div>

                  <div class="accordion-item border-0">
                    <h2 class="accordion-header" id="headingAplicacion">
                      <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                        data-bs-target="#collapseAplicacion" aria-expanded="false" aria-controls="collapseAplicacion">
                        <span class="category-dot" style="background-color: rgba(92, 162, 80, 1)"></span>
                        Aplicación en juego
                      </button>
                    </h2>
                    <div id="collapseAplicacion" class="accordion-collapse collapse" aria-labelledby="headingAplicacion"
                      data-bs-parent="#accordionCategories">
                      <div class="accordion-body">
                        Para evaluar cómo el jugador aplica sus habilidades en situaciones reales de juego,
                        adaptabilidad y rendimiento en competición.
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, computed, onMounted, watch } from 'vue';
import { useStore } from 'vuex';
import Graphics from '@/components/home/Graphics.vue';
import Constant from '@/Constant';

export default {
  name: 'GraficasTab',
  components: {
    Graphics
  },
  setup() {
    const store = useStore();
    const loading = ref(true);
    const currentYear = new Date().getFullYear();
    const selectedYear = ref(currentYear);

    //Verificamos si el usuario tiene acceso a las gráficas
    const hasGraficasAccess = computed(() => {
      const user = store.getters['user/GetCurrentUser'];
      return user.profile?.incluyeGraficas === true;
    });

    //Obtener las gráficas del store
    const graficas = computed(() => {
      return store.getters['profile/GetGraficasProfile'] || [];
    });

    //Calcular el promedio de todas las puntuaciones
    const getAverageScore = () => {
      if (graficas.value.length === 0) return 0;

      let totalPoints = 0;
      let totalValues = 0;

      graficas.value.forEach(grafica => {
        if (grafica.secciones && Array.isArray(grafica.secciones)) {
          grafica.secciones.forEach(seccion => {
            totalPoints += seccion.nivel || 0;
            totalValues++;
          });
        }
      });

      return totalValues > 0 ? (totalPoints / totalValues).toFixed(1) : 0;
    };

    //Cambiar el año seleccionado y cargar los datos correspondientes
    const changeYear = (year) => {
      selectedYear.value = year;
      loadGraficas(year);
    };

    //Cargar las gráficas del año seleccionado
    const loadGraficas = async (year) => {
      if (!hasGraficasAccess.value) {
        loading.value = false;
        return;
      }

      loading.value = true;
      try {
        await store.dispatch(`profile/${Constant.INITIALIZE_GRAFICA_PROFILE}`, year);
      } catch (error) {
        console.error("Error al cargar las gráficas:", error);
      } finally {
        loading.value = false;
      }
    };
    onMounted(() => {
      if (hasGraficasAccess.value) {
        loadGraficas(selectedYear.value);
      } else {
        loading.value = false;
      }
    });

    return {
      loading,
      currentYear,
      selectedYear,
      graficas,
      hasGraficasAccess,
      changeYear,
      getAverageScore
    };
  }
};
</script>

<style scoped>
.graficas-tab {
  width: 100%;
}

.graph-container {
  background: white;
  border-radius: 15px;
  padding: 2rem;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
  height: 500px;
  width: 100%;
  position: relative;
  overflow: hidden;
}

.analytics-info {
  background: white;
  border-radius: 15px;
  padding: 1.5rem;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.05);
  height: 100%;
}

.info-text {
  font-size: 1.1rem;
  color: #2c3e50;
  line-height: 1.6;
}

.stats-grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 1rem;
  margin-top: 1rem;
}

.stat-card {
  background: #f8f9fa;
  padding: 1rem;
  border-radius: 10px;
  text-align: center;
}

.stat-value {
  font-size: 1.8rem;
  font-weight: 700;
  color: var(--bs-primary);
  display: block;
}

.stat-label {
  color: #7f8c8d;
  font-size: 0.9rem;
  margin-top: 0.5rem;
}

.btn-outline-primary {
  color: #ff6600;
  border-color: #ff6600;
}

.btn-outline-primary:hover,
.btn-outline-primary.active {
  background-color: #ff6600;
  border-color: #ff6600;
  color: white;
}

/* Estilos para el acordeón */
.categories-info {
  background: white;
  border-radius: 15px;
  padding: 1.5rem;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.05);
  height: 100%;
}

.accordion-button {
  background-color: transparent;
  box-shadow: none;
  padding: 0.75rem 1rem;
  font-weight: 500;
  color: #2c3e50;
  border-radius: 8px !important;
}

.accordion-button:not(.collapsed) {
  background-color: rgba(255, 102, 0, 0.1);
  color: #ff6600;
}

.accordion-button:focus {
  box-shadow: none;
  border-color: rgba(255, 102, 0, 0.25);
}

.accordion-body {
  padding: 0.75rem 1rem;
  color: #6c757d;
  font-size: 0.95rem;
}

.category-dot {
  display: inline-block;
  width: 12px;
  height: 12px;
  border-radius: 50%;
  margin-right: 10px;
}

/* Ajustes para móviles */
@media (max-width: 768px) {
  .stats-grid {
    grid-template-columns: 1fr;
  }

  .graph-container {
    height: 400px;
  }
}
</style>