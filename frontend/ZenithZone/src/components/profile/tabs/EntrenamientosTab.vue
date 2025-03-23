<template>
    <div class="entrenamientos-tab p-4">
      <div class="d-flex justify-content-between align-items-center mb-4">
        <h4 class="mb-0">Mis Entrenamientos</h4>
        <div class="btn-group" role="group">
          <button type="button" class="btn btn-outline-primary" 
                  :class="{ 'active': activeFilter === 'activos' }"
                  @click="setFilter('activos')">
            Activos
          </button>
          <button type="button" class="btn btn-outline-primary" 
                  :class="{ 'active': activeFilter === 'expirados' }"
                  @click="setFilter('expirados')">
            Expirados
          </button>
        </div>
      </div>
  
      <div v-if="loading" class="text-center my-5">
        <div class="spinner-border text-primary" role="status">
          <span class="visually-hidden">Cargando...</span>
        </div>
      </div>
  
      <div v-else-if="currentEntrenamientos.length === 0" class="text-center my-5 py-5">
        <i class="bi bi-calendar-x text-muted" style="font-size: 3rem;"></i>
        <p class="mt-3 text-muted">No tienes entrenamientos {{ activeFilter === 'activos' ? 'activos' : 'expirados' }}</p>
      </div>
  
      <div v-else class="row">
        <div v-for="reserva in currentEntrenamientos" :key="reserva.id" class="col-md-6 mb-4">
          <div class="card h-100 shadow-sm border-0 rounded-3 hover-card">
            <div class="card-header bg-white border-bottom-0 pt-3">
              <div class="d-flex justify-content-between align-items-center">
                <h5 class="card-title mb-0 fw-bold">{{ reserva.entrenamiento.nombre }}</h5>
                <span class="badge bg-primary">{{ reserva.entrenamiento.tecnificacion?.nombre || 'Sin categoría' }}</span>
              </div>
            </div>
            <div class="card-body">
              <div class="mb-3">
                <p class="card-text text-muted">{{ reserva.entrenamiento.descripcion || 'Sin descripción' }}</p>
              </div>
              <div class="d-flex justify-content-between align-items-center mb-2">
                <div>
                  <i class="bi bi-calendar-date me-1"></i>
                  <span>{{ formatDate(reserva.fecha) }}</span>
                </div>
                <div>
                  <i class="bi bi-clock me-1"></i>
                  <span>{{ formatTime(reserva.entrenamiento.horarioPista?.fechaInicio) }} - {{ formatTime(reserva.entrenamiento.horarioPista?.fechaFin) }}</span>
                </div>
              </div>
              <div class="d-flex justify-content-between align-items-center mb-2">
                <div>
                  <i class="bi bi-person-fill me-1"></i>
                  <span>{{ reserva.entrenamiento.entrenador?.nombre }} {{ reserva.entrenamiento.entrenador?.apellidos }}</span>
                </div>
                <div>
                  <i class="bi bi-geo-alt-fill me-1"></i>
                  <span>{{ reserva.entrenamiento.horarioPista.pista?.nombre || 'Sin ubicación' }}</span>
                </div>
              </div>
              <div v-if="reserva.entrenamiento.nivel" class="d-flex align-items-center mb-2">
                <i class="bi bi-bar-chart-fill me-1"></i>
                <span>Nivel: {{ reserva.entrenamiento.nivel }}</span>
              </div>
              <div v-if="reserva.entrenamiento.maxPlazas" class="d-flex align-items-center">
                <i class="bi bi-people-fill me-1"></i>
                <span>Plazas: {{ reserva.entrenamiento.maxPlazas }}</span>
              </div>
            </div>
            <div class="card-footer bg-white border-top-0">
              <div class="d-flex justify-content-between align-items-center">
                <div>
                  <span v-if="activeFilter === 'activos'" class="badge bg-success">Activo</span>
                  <span v-else class="badge bg-secondary">Expirado</span>
                </div>
                <button v-if="activeFilter === 'activos'" 
                        @click="cancelarEntrenamiento(reserva.entrenamiento.slug)"
                        class="btn btn-sm btn-outline-danger">
                  <i class="bi bi-x-circle me-1"></i>Cancelar
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
  
      <!-- Toast para notificaciones -->
      <div class="position-fixed bottom-0 end-0 p-3" style="z-index: 11">
        <div id="entrenamientoToast" class="toast" :class="{ 'show': showToast }" role="alert" aria-live="assertive" aria-atomic="true">
          <div class="toast-header" :class="toastType === 'success' ? 'bg-success text-white' : 'bg-danger text-white'">
            <i :class="toastType === 'success' ? 'bi bi-check-circle-fill me-2' : 'bi bi-x-circle-fill me-2'"></i>
            <strong class="me-auto">Notificación</strong>
            <button type="button" class="btn-close btn-close-white" @click="showToast = false" aria-label="Close"></button>
          </div>
          <div class="toast-body">
            {{ toastMessage }}
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
    name: 'EntrenamientosTab',
    setup() {
      const store = useStore();
      const loading = ref(true);
      const activeFilter = ref('activos');
      const showToast = ref(false);
      const toastMessage = ref('');
      const toastType = ref('success');
  
      // Obtener entrenamientos del store
      const entrenamientosActivos = computed(() => {
        return store.getters['profile/GetReservasActivas'];
      });
  
      const entrenamientosExpirados = computed(() => {
        return store.getters['profile/GetReservasExpiradas'];
      });
  
      const currentEntrenamientos = computed(() => {
        return activeFilter.value === 'activos' ? entrenamientosActivos.value : entrenamientosExpirados.value;
      });
  
      const setFilter = (filter) => {
        activeFilter.value = filter;
      };
  
      const formatDate = (dateString) => {
        if (!dateString) return 'Fecha no disponible';
        
        const date = new Date(dateString);
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const day = String(date.getDate()).padStart(2, '0');
        
        return `${year}-${month}-${day}`;
      };
  
      const formatTime = (dateString) => {
        if (!dateString) return 'Hora no disponible';
        
        const date = new Date(dateString);
        const hours = String(date.getHours()).padStart(2, '0');
        const minutes = String(date.getMinutes()).padStart(2, '0');
        
        return `${hours}:${minutes}`;
      };
  
      const loadEntrenamientos = async () => {
        loading.value = true;
        try {
          // Cargar entrenamientos activos y expirados usando el store
          await Promise.all([
            store.dispatch(`profile/${Constant.INITIALIZE_RESERVAS_ACTIVAS}`),
            store.dispatch(`profile/${Constant.INITIALIZE_RESERVAS_EXPIRADAS}`)
          ]);
        } catch (error) {
          console.error('Error al cargar los entrenamientos:', error);
          showToast.value = true;
          toastMessage.value = 'Error al cargar los entrenamientos';
          toastType.value = 'error';
        } finally {
          loading.value = false;
        }
      };
  
      const cancelarEntrenamiento = async (slug) => {
        try {
          await store.dispatch(`profile/${Constant.CANCELAR_ENTRENAMIENTO}`, slug);
          
          // Mostrar mensaje de éxito
          showToast.value = true;
          toastMessage.value = 'Entrenamiento cancelado correctamente';
          toastType.value = 'success';
          
          // Ocultar el toast después de 3 segundos
          setTimeout(() => {
            showToast.value = false;
          }, 3000);
        } catch (error) {
          console.error('Error al cancelar el entrenamiento:', error);
          showToast.value = true;
          toastMessage.value = 'Error al cancelar el entrenamiento';
          toastType.value = 'error';
          
          // Ocultar el toast después de 3 segundos
          setTimeout(() => {
            showToast.value = false;
          }, 3000);
        }
      };
  
      onMounted(() => {
        loadEntrenamientos();
      });
  
      return {
        loading,
        activeFilter,
        currentEntrenamientos,
        setFilter,
        cancelarEntrenamiento,
        showToast,
        toastMessage,
        toastType,
        formatDate,
        formatTime
      };
    }
  };
  </script>
  
  <style scoped>
  .hover-card {
    transition: transform 0.3s ease, box-shadow 0.3s ease;
  }
  
  .hover-card:hover {
    transform: translateY(-5px);
    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1) !important;
  }
  
  .btn-outline-primary {
    color: #ff6600;
    border-color: #ff6600;
  }
  
  .btn-outline-primary:hover, .btn-outline-primary.active {
    background-color: #ff6600;
    border-color: #ff6600;
    color: white;
  }
  
  .btn-outline-danger {
    color: #dc3545;
    border-color: #dc3545;
  }
  
  .btn-outline-danger:hover {
    background-color: #dc3545;
    border-color: #dc3545;
    color: white;
  }
  
  .toast {
    transition: opacity 0.3s ease;
  }
  
  .toast.show {
    opacity: 1;
  }
  </style>
  