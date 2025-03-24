<template>
    <div class="card h-100 shadow-sm border-0 alumno-card">
        <div class="card-body">
            <!-- Avatar y nombre -->
            <div class="d-flex align-items-center mb-3">
                <div class="avatar-container me-3">
                    <img v-if="inscripcion.profile.image" :src="inscripcion.profile.image" class="avatar"
                        :alt="inscripcion.profile.nombre">
                    <div v-else class="avatar-placeholder d-flex align-items-center justify-content-center bg-light">
                        <i class="bi bi-person text-muted"></i>
                    </div>
                </div>
                <div>
                    <h5 class="card-title mb-0">{{ inscripcion.profile.nombre }} {{ inscripcion.profile.apellidos }}
                    </h5>
                    <p class="text-muted small mb-0">{{ inscripcion.profile.numeroSocio }}</p>
                </div>
            </div>

            <!-- Información del alumno -->
            <div class="details-list mb-3">
                <div class="detail-item">
                    <i class="bi bi-calendar-check me-2 text-primary"></i>
                    <span>{{ inscripcion.profile.edad }} años</span>
                </div>
                <div v-if="inscripcion.profile.posicionPreferida" class="detail-item">
                    <i class="bi bi-trophy me-2 text-primary"></i>
                    <span>{{ inscripcion.profile.posicionPreferida }}</span>
                </div>
                <div v-if="inscripcion.profile.clubOrigen" class="detail-item">
                    <i class="bi bi-building me-2 text-primary"></i>
                    <span>{{ inscripcion.profile.clubOrigen }}</span>
                </div>
                <div v-if="inscripcion.profile.organizacionOrigen" class="detail-item">
                    <i class="bi bi-people me-2 text-primary"></i>
                    <span>{{ inscripcion.profile.organizacionOrigen }}</span>
                </div>
            </div>

            <!-- Información de suscripción -->
            <div class="subscription-info p-2 rounded mb-3" :class="subscriptionClass">
                <div class="d-flex align-items-center">
                    <i class="bi me-2" :class="subscriptionIcon"></i>
                    <div>
                        <p class="mb-0 small fw-bold">{{ inscripcion.profile.suscripcionNombre }}</p>
                        <p class="mb-0 small">Hasta: {{ formatDate(inscripcion.profile.fechaFinSuscripcion) }}</p>
                    </div>
                </div>
            </div>

            <!-- Botones de acción -->
            <div class="d-flex gap-2">
                <button class="btn btn-sm flex-grow-1"
                    :class="[inscripcion.profile.incluyeEvaluacion ? 'btn-primary' : 'btn-secondary disabled']"
                    @click="irAEvaluacion" :disabled="!inscripcion.profile.incluyeEvaluacion">
                    <i class="bi bi-clipboard-check me-1"></i> Evaluación
                </button>
                <button class="btn btn-sm flex-grow-1"
                    :class="[inscripcion.profile.incluyeGraficas ? 'btn-outline-primary' : 'btn-outline-secondary disabled']"
                    @click="irAGraficas" :disabled="!inscripcion.profile.incluyeGraficas">
                    <i class="bi bi-graph-up me-1"></i> Gráficas
                </button>
            </div>
        </div>
    </div>
</template>

<script>
import { computed } from 'vue';
import { useRouter } from 'vue-router';
import { format, parseISO } from 'date-fns';
import { es } from 'date-fns/locale';

export default {
    name: 'AlumnoCard',
    props: {
        inscripcion: {
            type: Object,
            required: true
        },
        entrenamientoSlug: {
            type: String,
            required: true
        }
    },
    setup(props) {
        const router = useRouter();

        const subscriptionClass = computed(() => {
            const status = props.inscripcion.profile.suscripcionStatus;
            return {
                'bg-success-subtle': status === 'active',
                'bg-warning-subtle': status === 'pending',
                'bg-danger-subtle': status === 'expired'
            };
        });

        const subscriptionIcon = computed(() => {
            const status = props.inscripcion.profile.suscripcionStatus;
            return {
                'bi-check-circle-fill text-success': status === 'active',
                'bi-exclamation-circle-fill text-warning': status === 'pending',
                'bi-x-circle-fill text-danger': status === 'expired'
            };
        });

        const formatDate = (dateString) => {
            if (!dateString) return 'No disponible';
            try {
                const date = parseISO(dateString);
                return format(date, 'dd/MM/yyyy', { locale: es });
            } catch (error) {
                console.error('Error al formatear fecha:', error);
                return 'Fecha inválida';
            }
        };

        const irAEvaluacion = () => {
            if (props.inscripcion.profile.incluyeEvaluacion) {
                router.push({
                    name: 'entrenadorEvaluacionAlumno',
                    params: {
                        numeroSocio: props.inscripcion.profile.numeroSocio,
                        slug: props.entrenamientoSlug
                    }
                });
            }
        };

        const irAGraficas = () => {
            if (props.inscripcion.profile.incluyeGraficas) {
                router.push({
                    name: 'entrenadorGraficasAlumno',
                    params: {
                        numeroSocio: props.inscripcion.profile.numeroSocio
                    }
                });
            }
        };

        return {
            subscriptionClass,
            subscriptionIcon,
            formatDate,
            irAEvaluacion,
            irAGraficas
        };
    }
};
</script>

<style scoped>
.alumno-card {
    transition: transform 0.2s, box-shadow 0.2s;
}

.alumno-card:hover {
    transform: translateY(-5px);
    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1) !important;
}

.avatar-container {
    width: 50px;
    height: 50px;
    border-radius: 50%;
    overflow: hidden;
}

.avatar {
    width: 100%;
    height: 100%;
    object-fit: cover;
}

.avatar-placeholder {
    width: 100%;
    height: 100%;
    border-radius: 50%;
    background-color: #f8f9fa;
    display: flex;
    align-items: center;
    justify-content: center;
}

.avatar-placeholder i {
    font-size: 1.5rem;
}

.details-list {
    margin-bottom: 1rem;
}

.detail-item {
    display: flex;
    align-items: center;
    margin-bottom: 0.5rem;
    font-size: 0.875rem;
}

.subscription-info {
    border-left: 3px solid;
}

.bg-success-subtle {
    border-left-color: var(--bs-success);
}

.bg-warning-subtle {
    border-left-color: var(--bs-warning);
}

.bg-danger-subtle {
    border-left-color: var(--bs-danger);
}
</style>
