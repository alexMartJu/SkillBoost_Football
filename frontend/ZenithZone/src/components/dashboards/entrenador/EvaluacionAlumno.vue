<template>
    <div class="evaluacion-container">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <div>
                <h2 class="mb-1 fw-bold text-primary">Evaluación de Rendimiento</h2>
                <p class="text-muted mb-0" v-if="alumnoInfo">
                    {{ alumnoInfo.nombre }} {{ alumnoInfo.apellidos }} - {{ alumnoInfo.numeroSocio }}
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
        <div v-if="successMessage" class="alert alert-success" role="alert">
            {{ successMessage }}
        </div>

        <!-- Spinner de carga -->
        <div v-if="loading" class="text-center py-5">
            <div class="spinner-border text-primary" role="status">
                <span class="visually-hidden">Cargando...</span>
            </div>
            <p class="mt-2">Cargando evaluación...</p>
        </div>

        <!-- Formulario de evaluación -->
        <div v-else class="card shadow-sm border-0">
            <div class="card-body">
                <form @submit.prevent="guardarEvaluacion">
                    <div class="mb-4">
                        <label for="puntuacion" class="form-label fw-bold">Puntuación Global</label>
                        <div class="d-flex align-items-center gap-3">
                            <input type="range" class="form-range flex-grow-1" id="puntuacion"
                                v-model.number="evaluacionForm.puntuacion" min="1" max="100">
                            <span class="badge bg-primary" style="width: 50px; font-size: 1rem;">{{
                                evaluacionForm.puntuacion }}</span>
                        </div>
                        <div class="progress mt-2" style="height: 8px;">
                            <div class="progress-bar" role="progressbar"
                                :style="{ width: `${evaluacionForm.puntuacion}%` }" :class="progressBarClass"
                                :aria-valuenow="evaluacionForm.puntuacion" aria-valuemin="0" aria-valuemax="100"></div>
                        </div>
                        <div class="d-flex justify-content-between mt-1">
                            <small class="text-muted">Bajo</small>
                            <small class="text-muted">Medio</small>
                            <small class="text-muted">Alto</small>
                        </div>
                    </div>

                    <div class="mb-4">
                        <label for="comentarios" class="form-label fw-bold">Comentarios</label>
                        <textarea class="form-control" id="comentarios" v-model="evaluacionForm.comentarios" rows="4"
                            placeholder="Escribe tus comentarios sobre el rendimiento del alumno..."
                            required></textarea>
                    </div>

                    <div class="mb-4">
                        <label for="areasMejora" class="form-label fw-bold">Áreas de Mejora</label>
                        <textarea class="form-control" id="areasMejora" v-model="evaluacionForm.areasMejora" rows="4"
                            placeholder="Indica las áreas en las que el alumno debe mejorar..." required></textarea>
                    </div>

                    <div class="d-flex justify-content-end gap-2">
                        <button type="button" class="btn btn-outline-secondary" @click="volver">Cancelar</button>
                        <button type="submit" class="btn btn-primary" :disabled="loading">
                            <i class="bi" :class="evaluacionExistente ? 'bi-pencil-square' : 'bi-plus-circle'"></i>
                            {{ evaluacionExistente ? 'Actualizar Evaluación' : 'Crear Evaluación' }}
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
import { ref, reactive, computed, onMounted } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import Constant from '@/Constant';

export default {
    name: 'EvaluacionAlumno',
    props: {
        numeroSocio: {
            type: String,
            required: true
        },
        slug: {
            type: String,
            required: true
        }
    },
    setup(props) {
        const store = useStore();
        const router = useRouter();

        const evaluacionForm = reactive({
            puntuacion: 50,
            comentarios: '',
            areasMejora: ''
        });

        const successMessage = ref(null);
        const alumnoInfo = ref(null);

        const loading = computed(() => store.getters['entrenadorDashboard/isLoading']);
        const error = computed(() => store.getters['entrenadorDashboard/getError']);
        const evaluacionExistente = computed(() => store.getters['entrenadorDashboard/getEvaluacion'] !== null);

        const progressBarClass = computed(() => {
            const puntuacion = evaluacionForm.puntuacion;
            if (puntuacion < 40) return 'bg-danger';
            if (puntuacion < 70) return 'bg-warning';
            return 'bg-success';
        });

        const cargarEvaluacion = async () => {
            try {
                const evaluacion = await store.dispatch(`entrenadorDashboard/${Constant.FETCH_EVALUACION_ALUMNO}`, {
                    numeroSocio: props.numeroSocio,
                    slug: props.slug
                });

                if (evaluacion) {
                    evaluacionForm.puntuacion = evaluacion.puntuacion;
                    evaluacionForm.comentarios = evaluacion.comentarios;
                    evaluacionForm.areasMejora = evaluacion.areasMejora;
                }
            } catch (error) {
                console.error('Error al cargar evaluación:', error);
            }
        };

        const cargarInfoAlumno = async () => {
            try {
                const inscripciones = store.getters['entrenadorDashboard/getInscripciones'];
                if (inscripciones.length === 0) {
                    await store.dispatch(`entrenadorDashboard/${Constant.FETCH_INSCRIPCIONES_BY_ENTRENAMIENTO}`, props.slug);
                }

                //Buscamos la inscripción del alumno actual
                const inscripcionesActualizadas = store.getters['entrenadorDashboard/getInscripciones'];
                const inscripcion = inscripcionesActualizadas.find(
                    insc => insc.profile.numeroSocio === props.numeroSocio
                );

                if (inscripcion) {
                    alumnoInfo.value = inscripcion.profile;
                }
            } catch (error) {
                console.error('Error al cargar información del alumno:', error);
            }
        };

        const guardarEvaluacion = async () => {
            try {
                const evaluacionData = {
                    puntuacion: evaluacionForm.puntuacion,
                    comentarios: evaluacionForm.comentarios,
                    areasMejora: evaluacionForm.areasMejora
                };

                if (evaluacionExistente.value) {
                    await store.dispatch(`entrenadorDashboard/${Constant.UPDATE_EVALUACION_ALUMNO}`, {
                        numeroSocio: props.numeroSocio,
                        slug: props.slug,
                        evaluacion: evaluacionData
                    });
                    successMessage.value = 'Evaluación actualizada correctamente';
                } else {
                    await store.dispatch(`entrenadorDashboard/${Constant.CREATE_EVALUACION_ALUMNO}`, {
                        numeroSocio: props.numeroSocio,
                        slug: props.slug,
                        evaluacion: evaluacionData
                    });
                    successMessage.value = 'Evaluación creada correctamente';
                }

                setTimeout(() => {
                    successMessage.value = null;
                }, 3000);
            } catch (error) {
                console.error('Error al guardar evaluación:', error);
            }
        };

        const volver = () => {
            router.push({
                name: 'entrenadorAlumnos',
                params: { slug: props.slug }
            });
        };

        onMounted(async () => {
            await cargarEvaluacion();
            await cargarInfoAlumno();
        });

        return {
            evaluacionForm,
            successMessage,
            alumnoInfo,
            loading,
            error,
            evaluacionExistente,
            progressBarClass,
            guardarEvaluacion,
            volver
        };
    }
};
</script>

<style scoped>
.evaluacion-container {
    padding: 1rem;
}

.form-range::-webkit-slider-thumb {
    background: var(--bs-primary);
}

.form-range::-webkit-slider-runnable-track {
    background: #dee2e6;
}
</style>
