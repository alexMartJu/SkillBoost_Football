<template>
    <div class="calendario-container">
        <h2 class="mb-4 fw-bold text-primary">Mi Calendario de Entrenamientos</h2>

        <!-- Alertas de error -->
        <div v-if="error" class="alert alert-danger" role="alert">
            {{ error }}
        </div>

        <!-- Spinner de carga -->
        <div v-if="loading" class="text-center py-5">
            <div class="spinner-border text-primary" role="status">
                <span class="visually-hidden">Cargando...</span>
            </div>
            <p class="mt-2">Cargando calendario...</p>
        </div>

        <!-- Calendario -->
        <div v-else class="calendar-wrapper">
            <div class="card shadow-sm p-3 mb-4">
                <v-row class="fill-height">
                    <v-col>
                        <v-sheet>
                            <v-calendar
                                ref="calendar"
                                v-model="today"
                                :events="calendarEvents"
                                color="primary"
                            >
                                <!-- Personalización del evento -->
                                <template v-slot:event="{ event }">
                                    <div class="custom-event-content">
                                        <v-tooltip activator="parent" location="top">
                                            <template v-slot:default>
                                                <div class="event-tooltip">
                                                    <div class="event-tooltip-title">{{ event.title }}</div>
                                                    <div class="event-tooltip-time">
                                                        {{ formatTime(event.start) }} - {{ formatTime(event.end) }}
                                                    </div>
                                                    <div class="event-tooltip-location">
                                                        <i class="bi bi-geo-alt me-1"></i> {{ event.pista }}
                                                    </div>
                                                    <div class="event-tooltip-type">
                                                        <i class="bi bi-tag me-1"></i> {{ event.tecnificacion }}
                                                    </div>
                                                </div>
                                            </template>
                                        </v-tooltip>
                                        <div class="event-title">{{ event.title }}</div>
                                        <div class="event-time">{{ formatTime(event.start) }}</div>
                                    </div>
                                </template>
                            </v-calendar>
                        </v-sheet>
                    </v-col>
                </v-row>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue';
import { useStore } from 'vuex';
import { format } from 'date-fns';
import { es } from 'date-fns/locale';

const store = useStore();

const calendar = ref(null);
const today = ref(new Date());
const loading = computed(() => store.getters['entrenadorDashboard/isLoading']);
const error = computed(() => store.getters['entrenadorDashboard/getError']);
const approvedEntrenamientos = computed(() => store.getters['entrenadorDashboard/getApprovedEntrenamientos']);

//Eventos del calendario
const calendarEvents = computed(() => {
    return approvedEntrenamientos.value.map((entrenamiento) => {
        const fechaInicio = new Date(entrenamiento.horarioPista?.fechaInicio);
        const fechaFin = new Date(entrenamiento.horarioPista?.fechaFin);
        
        return {
            title: entrenamiento.nombre,
            start: fechaInicio,
            end: fechaFin,
            timed: true,
            //Datos adicionales para mostrar en el tooltip
            pista: entrenamiento.horarioPista?.pista?.nombre,
            tecnificacion: entrenamiento.tecnificacion?.nombre
        };
    });
});

//Métodos para formateo de fechas
function formatTime(date) {
    return format(date, 'HH:mm');
}

//Cargar datos al montar el componente
onMounted(() => {
    store.dispatch('entrenadorDashboard/fetchApprovedEntrenamientos');
});
</script>

<style scoped>
.calendario-container {
    max-width: 1200px;
    margin: 0 auto;
}

:deep(.v-calendar) {
    border-radius: 8px;
}

:deep(.v-event) {
    cursor: pointer !important;
    border-radius: 4px;
    font-weight: 500;
    transition: transform 0.2s;
    overflow: visible !important; 
}

:deep(.v-event:hover) {
    transform: scale(1.02);
    z-index: 10;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

/* Estilos para el contenido personalizado del evento */
.custom-event-content {
    padding: 2px 4px;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
}

.event-title {
    font-weight: 500;
    white-space: normal; /* Permite saltos de línea */
    overflow: hidden;
    display: -webkit-box;
    -webkit-line-clamp: 2; /* Limita a 2 líneas */
    -webkit-box-orient: vertical;
    line-height: 1.2;
    font-size: 0.85rem;
}

.event-time {
    font-size: 0.75rem;
    opacity: 0.8;
}

/* Estilos para el tooltip */
.event-tooltip {
    padding: 8px;
    max-width: 300px;
}

.event-tooltip-title {
    font-weight: bold;
    margin-bottom: 5px;
    font-size: 1rem;
}

.event-tooltip-time,
.event-tooltip-location,
.event-tooltip-type {
    font-size: 0.85rem;
    margin-bottom: 3px;
}

/* Ajustes para diferentes vistas */
:deep(.v-calendar-daily) .custom-event-content {
    padding: 4px 8px;
}

:deep(.v-calendar-daily) .event-title {
    font-size: 0.95rem;
    -webkit-line-clamp: 3;
}

:deep(.v-calendar-weekly) .custom-event-content {
    padding: 3px 6px;
}
</style>
