<template>
    <div class="container py-4">
        <div class="card shadow-sm border-0">
            <div class="card-body p-4">
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <h2 class="display-6 mb-0">Horarios: {{ selectedPista ? selectedPista.nombre : 'Seleccione una pista' }}</h2>
                    <button class="btn btn-outline-secondary" @click="volver">
                        <i class="bi bi-arrow-left me-1"></i> Volver a pistas
                    </button>
                </div>

                <div v-if="!selectedPista" class="alert alert-warning" role="alert">
                    No hay pista seleccionada. Por favor, seleccione una pista primero.
                </div>

                <template v-else>
                    <div class="mb-4">
                        <label for="fecha" class="form-label">Seleccione una fecha:</label>
                        <input type="date" id="fecha" class="form-control" v-model="selectedDate" :min="minDate">
                    </div>

                    <div v-if="loading" class="text-center my-5">
                        <div class="spinner-border text-primary" role="status">
                            <span class="visually-hidden">Cargando...</span>
                        </div>
                        <p class="mt-2">Cargando horarios...</p>
                    </div>

                    <div v-else-if="error" class="alert alert-danger" role="alert">
                        {{ error }}
                    </div>

                    <div v-else>
                        <div class="card mb-4">
                            <div class="card-header bg-primary text-white">
                                <h5 class="mb-0">Horarios Ocupados</h5>
                            </div>
                            <div class="card-body">
                                <div v-if="horariosOcupados.length === 0" class="text-center py-4">
                                    <i class="bi bi-calendar-check fs-1 text-success"></i>
                                    <p class="mt-2">No hay horarios ocupados para esta fecha. ¡Toda la pista está
                                        disponible!</p>
                                </div>
                                <ul v-else class="list-group">
                                    <li v-for="horario in horariosOcupados" :key="horario.id" class="list-group-item">
                                        <div class="d-flex justify-content-between align-items-center">
                                            <div>
                                                <i class="bi bi-clock me-2 text-danger"></i>
                                                <span class="fw-bold">{{ formatDateTime(horario.fechaInicio) }}</span>
                                                <span class="mx-2">a</span>
                                                <span class="fw-bold">{{ formatDateTime(horario.fechaFin) }}</span>
                                            </div>
                                            <span class="badge bg-danger">Ocupado</span>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>

                        <div class="d-grid">
                            <button class="btn btn-primary btn-lg" @click="crearEntrenamiento">
                                <i class="bi bi-plus-circle me-2"></i> Crear Entrenamiento
                            </button>
                        </div>
                    </div>
                </template>
            </div>
        </div>
    </div>
</template>

<script>
import { ref, computed, watch, onMounted } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import Constant from '@/Constant';

export default {
    name: 'HorariosPista',
    setup() {
        const store = useStore();
        const router = useRouter();

        //Estado local
        const selectedDate = ref(new Date().toISOString().split('T')[0]); //Fecha actual en formato YYYY-MM-DD

        //Computed properties
        const selectedPista = computed(() => store.getters['entrenadorDashboard/getSelectedPista']);
        const horariosOcupados = computed(() => store.getters['entrenadorDashboard/getHorariosOcupados']);
        const loading = computed(() => store.getters['entrenadorDashboard/isLoading']);
        const error = computed(() => store.getters['entrenadorDashboard/getError']);
        const minDate = computed(() => new Date().toISOString().split('T')[0]); //No permitir fechas pasadas

        //Cargar horarios cuando cambia la fecha o al montar el componente
        const loadHorarios = () => {
            if (selectedPista.value) {
                store.dispatch(`entrenadorDashboard/${Constant.FETCH_HORARIOS_OCUPADOS}`, {
                    pistaSlug: selectedPista.value.slug,
                    fecha: selectedDate.value
                });
            }
        };

        watch(selectedDate, loadHorarios);

        onMounted(() => {
            if (!selectedPista.value) {
                router.push('/dashboardEntrenador/listar');
            } else {
                loadHorarios();
            }
        });

        const formatDateTime = (dateTimeString) => {
            const date = new Date(dateTimeString);
            return date.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
        };

        const volver = () => {
            router.push('/dashboardEntrenador/listar');
        };

        const crearEntrenamiento = () => {
            router.push({
                name: 'entrenadorCrearEntrenamiento',
                params: { pistaSlug: selectedPista.value.slug, fecha: selectedDate.value }
            });
        };

        return {
            selectedPista,
            selectedDate,
            horariosOcupados,
            loading,
            error,
            minDate,
            formatDateTime,
            volver,
            crearEntrenamiento
        };
    }
};
</script>

<style scoped>
.card {
    transition: all 0.3s ease;
}

.card:hover {
    box-shadow: 0 .5rem 1rem rgba(0, 0, 0, .15) !important;
}
</style>
