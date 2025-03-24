<template>
    <div class="alumnos-container">
        <!-- Cabecera con información del entrenamiento -->
        <div class="d-flex justify-content-between align-items-center mb-4">
            <div>
                <h2 class="mb-1 fw-bold text-primary">Alumnos Inscritos</h2>
                <p class="text-muted mb-0" v-if="currentEntrenamiento">
                    {{ currentEntrenamiento.nombre }} - {{ formatDate(currentEntrenamiento.horarioPista?.fechaInicio) }}
                </p>
            </div>
            <button class="btn btn-outline-secondary" @click="volver">
                <i class="bi bi-arrow-left me-1"></i> Volver
            </button>
        </div>

        <!-- Alertas -->
        <div v-if="error" class="alert alert-danger" role="alert">
            {{ error }}
        </div>

        <!-- Spinner de carga -->
        <div v-if="loading" class="text-center py-5">
            <div class="spinner-border text-primary" role="status">
                <span class="visually-hidden">Cargando...</span>
            </div>
            <p class="mt-2">Cargando alumnos inscritos...</p>
        </div>

        <!-- Mensaje cuando no hay alumnos -->
        <div v-else-if="inscripciones.length === 0" class="text-center py-5">
            <i class="bi bi-people text-muted" style="font-size: 3rem;"></i>
            <p class="mt-3 text-muted">No hay alumnos inscritos en este entrenamiento.</p>
        </div>

        <!-- Lista de alumnos -->
        <div v-else class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
            <div v-for="inscripcion in inscripciones" :key="inscripcion.id" class="col">
                <alumno-card :inscripcion="inscripcion" :entrenamiento-slug="slug" />
            </div>
        </div>
    </div>
</template>

<script>
import { computed, onMounted, ref } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import { format } from 'date-fns';
import { es } from 'date-fns/locale';
import AlumnoCard from './AlumnoCard.vue';
import Constant from '../../../Constant';

export default {
    name: 'AlumnosEntrenamiento',
    components: {
        AlumnoCard
    },
    props: {
        slug: {
            type: String,
            required: true
        }
    },
    setup(props) {
        const store = useStore();
        const router = useRouter();

        const inscripciones = computed(() => store.getters['entrenadorDashboard/getInscripciones']);
        const loading = computed(() => store.getters['entrenadorDashboard/isLoading']);
        const error = computed(() => store.getters['entrenadorDashboard/getError']);
        const currentEntrenamiento = computed(() => {
            if (inscripciones.value.length > 0) {
                return inscripciones.value[0].entrenamiento;
            }
            return null;
        });

        const formatDate = (dateString) => {
            if (!dateString) return 'Fecha no disponible';
            try {
                const date = new Date(dateString);
                return format(date, 'dd MMMM yyyy', { locale: es });
            } catch (error) {
                console.error('Error al formatear fecha:', error);
                return 'Fecha inválida';
            }
        };

        const volver = () => {
            router.push({ name: 'entrenadorMisEntrenamientos' });
        };

        onMounted(() => {
            store.dispatch(`entrenadorDashboard/${Constant.FETCH_INSCRIPCIONES_BY_ENTRENAMIENTO}`, props.slug);
        });

        return {
            inscripciones,
            loading,
            error,
            currentEntrenamiento,
            formatDate,
            volver
        };
    }
};
</script>

<style scoped>
.alumnos-container {
    padding: 1rem;
}
</style>
