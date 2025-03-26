<template>
    <div class="calendario-tab">
      <h4 class="mb-4">Mi Calendario de Entrenamientos</h4>
  
      <div v-if="loading" class="text-center my-5">
        <div class="spinner-border text-primary" role="status">
          <span class="visually-hidden">Cargando...</span>
        </div>
      </div>
  
      <div v-else-if="events.length === 0" class="text-center my-5 py-5">
        <i class="bi bi-calendar-x text-muted" style="font-size: 3rem;"></i>
        <p class="mt-3 text-muted">No tienes entrenamientos programados</p>
      </div>
  
      <div v-else>
        <v-sheet>
          <v-calendar
            ref="calendar"
            v-model="today"
            :events="events"
            color="primary"
            type="month"
            :weekdays="[1, 2, 3, 4, 5, 6, 0]"
            :short-weekdays="false"
            :month-format="formatMonth"
            :day-format="formatDay"
            locale="es"
          ></v-calendar>
        </v-sheet>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, computed, onMounted } from 'vue';
  import { useStore } from 'vuex';
  import Constant from '@/Constant';
  
  const store = useStore();
  const calendar = ref();
  const today = ref(new Date());
  const loading = ref(true);
  
  // Formateadores para traducir el calendario
  const formatMonth = (date) => {
    const months = ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'];
    return months[date.getMonth()];
  };
  
  const formatDay = (date) => {
    const days = ['Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado'];
    return days[date.getDay()];
  };
  
  // Obtener entrenamientos del store
  const entrenamientosActivos = computed(() => {
    return store.getters['profile/GetReservasActivas'] || [];
  });
  
  const entrenamientosExpirados = computed(() => {
    return store.getters['profile/GetReservasExpiradas'] || [];
  });
  
  // Convertir entrenamientos a eventos para el calendario
  const events = computed(() => {
    const activos = entrenamientosActivos.value.map(reserva => {
      const fechaInicio = new Date(reserva.entrenamiento.horarioPista?.fechaInicio || reserva.fecha);
      const fechaFin = new Date(reserva.entrenamiento.horarioPista?.fechaFin || reserva.fecha);
      
      // Si no hay fechaFin, establecer 1 hora después de fechaInicio
      if (!reserva.entrenamiento.horarioPista?.fechaFin) {
        fechaFin.setHours(fechaFin.getHours() + 1);
      }
      
      return {
        title: reserva.entrenamiento.nombre,
        start: fechaInicio,
        end: fechaFin,
        color: 'green',
        timed: true
      };
    });
  
    const expirados = entrenamientosExpirados.value.map(reserva => {
      const fechaInicio = new Date(reserva.entrenamiento.horarioPista?.fechaInicio || reserva.fecha);
      const fechaFin = new Date(reserva.entrenamiento.horarioPista?.fechaFin || reserva.fecha);
      
      // Si no hay fechaFin, establecer 1 hora después de fechaInicio
      if (!reserva.entrenamiento.horarioPista?.fechaFin) {
        fechaFin.setHours(fechaFin.getHours() + 1);
      }
      
      return {
        title: reserva.entrenamiento.nombre,
        start: fechaInicio,
        end: fechaFin,
        color: 'grey',
        timed: true
      };
    });
  
    return [...activos, ...expirados];
  });
  
  const loadEntrenamientos = async () => {
    loading.value = true;
    try {
      // Cargar entrenamientos activos y expirados usando el store
      await Promise.all([
        store.dispatch(`profile/${Constant.INITIALIZE_RESERVAS_ACTIVAS}`),
        store.dispatch(`profile/${Constant.INITIALIZE_RESERVAS_EXPIRADAS}`)
      ]);
    } catch (error) {
      console.error("Error al cargar los entrenamientos:", error);
    } finally {
      loading.value = false;
    }
  };
  
  onMounted(() => {
    loadEntrenamientos();
  });
  </script>
  
  <style scoped>
  /* Estilos para el calendario */
  .calendario-tab {
    width: 100%;
    padding: 1rem;
    box-sizing: border-box;
  }
  
  .calendar-wrapper {
    width: 100%;
    max-width: 100%;
    box-sizing: border-box;
    padding-bottom: 1rem;
  }
  
  .calendar-sheet {
    width: 100%;
    max-width: 100%;
  }
  
  :deep(.v-calendar) {
    border-radius: 8px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
    font-family: inherit;
    width: 100%;
    max-width: 100%;
  }
  
  :deep(.v-calendar-weekly__head-weekday) {
    font-weight: bold;
    color: #495057;
  }
  
  /* Ajustes para dispositivos móviles */
  @media (max-width: 768px) {
    :deep(.v-calendar-weekly__head-weekday) {
      font-size: 0.8rem;
    }
    
    :deep(.v-calendar-weekly__day-label) {
      font-size: 0.8rem;
    }
    
    :deep(.v-event-timed) {
      font-size: 0.8rem;
    }
  }
  </style>
  