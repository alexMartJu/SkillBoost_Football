<template>
    <section class="mb-4">
        <div class="card shadow-sm h-100">
            <img v-if="entrenamiento.subtipoTecnificacion && entrenamiento.subtipoTecnificacion.images && entrenamiento.subtipoTecnificacion.images.length > 0"
                :src="`/assets/tecnificaciones/${entrenamiento.subtipoTecnificacion.images[0].imageUrl}`"
                class="card-img-top img-fluid object-fit-cover"
                :style="isServiciosView ? 'height: 275px' : 'height: 300px'" alt="Imagen de tecnificación">
            <img v-else src="/assets/pistas/arco.jpg" class="card-img-top img-fluid object-fit-cover"
                :style="isServiciosView ? 'height: 275px' : 'height: 300px'" alt="Imagen por defecto">
            <div class="card-body">
                <div class="row">
                    <h4 class="card-title fw-bold mb-3 text-truncate" style="max-width: 100%;"
                        :title="entrenamiento.nombre">
                        {{ entrenamiento.nombre }}
                    </h4>
                    <div class="col-lg-8">
                        <div class="mb-4">
                            <!-- Tecnificación y subtipo -->
                            <p v-if="entrenamiento.tecnificacion && entrenamiento.subtipoTecnificacion"
                                class="card-text mb-2">
                                <i class="bi bi-lightning-charge me-2"></i>
                                <span class="fw-semibold">{{ entrenamiento.tecnificacion.nombre }}</span> -
                                {{ entrenamiento.subtipoTecnificacion.nombre }}
                            </p>

                            <!-- Horario -->
                            <p v-if="entrenamiento.horarioPista" class="card-text mb-2">
                                <i class="bi bi-calendar-event me-2"></i>
                                {{ formatDate(entrenamiento.horarioPista.fechaInicio) }} -
                                {{ formatTime(entrenamiento.horarioPista.fechaInicio) }} a
                                {{ formatTime(entrenamiento.horarioPista.fechaFin) }}
                            </p>

                            <div class="d-flex flex-wrap gap-3 mt-3">
                                <span class="badge bg-primary-subtle text-primary p-3 fs-6">
                                    <i class="bi bi-people me-1"></i>{{ entrenamiento.maxPlazas }} plazas
                                </span>
                                <span class="badge bg-success-subtle text-success p-3 fs-6">
                                    <i class="bi bi-person-badge me-1"></i>{{ entrenamiento.edadMinima }}-{{
                                        entrenamiento.edadMaxima }} años
                                </span>
                                <span v-if="entrenamiento.pista" class="badge bg-info-subtle text-info p-3 fs-6">
                                    <i class="bi bi-geo-alt me-1"></i>{{ entrenamiento.pista.nombre }}
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="d-flex flex-column gap-2 h-100">
                            <button class="btn btn-outline-primary w-100" @click="details(entrenamiento.slug)">
                                <i class="bi bi-info-circle me-2"></i>Info
                            </button>
                            <UnirseEntrenamientoButton v-if="!isProfile" :slug="entrenamiento.slug"
                                :isSuscribed="isSuscribed" class="rounded-pill" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</template>

<script>
import { useRouter } from 'vue-router';
import { useStore } from 'vuex';
import UnirseEntrenamientoButton from './buttons/UnirseEntrenamientoButton.vue';
import { reactive } from 'vue';
import { computed } from 'vue';

export default {
    props: {
        entrenamiento: {
            type: Object,
            required: true
        },
        isProfile: {
            type: Boolean,
            required: true,
            default: false
        },
        isSuscribed: {
            type: Boolean,
            required: false,
            default: false
        },
        isServiciosView: {
            type: Boolean,
            default: false
        }
    },

    components: {
        UnirseEntrenamientoButton
    },

    setup() {
        const store = useStore();
        const router = useRouter();

        const state = reactive({
            isLogged: computed(() => store.getters['user/GetIsAuth']),
        });

        const details = (slug) => {
            router.push({ name: 'detailsEntrenamiento', params: { slug } });
        }

        const formatDate = (dateString) => {
            if (!dateString) return '';
            const date = new Date(dateString);
            return date.toLocaleDateString('es-ES', {
                day: '2-digit',
                month: '2-digit',
                year: 'numeric'
            });
        }

        const formatTime = (dateString) => {
            if (!dateString) return '';
            const date = new Date(dateString);
            return date.toLocaleTimeString('es-ES', {
                hour: '2-digit',
                minute: '2-digit'
            });
        }

        return {
            details,
            state,
            formatDate,
            formatTime
        }
    }
}
</script>

<style scoped>
.card {
    border-radius: 1rem;
    transition: transform 0.3s ease, box-shadow 0.3s ease;
    height: 100%;
}

.card:hover {
    transform: translateY(-5px);
    box-shadow: 0 1rem 3rem rgba(0, 0, 0, .175) !important;
}

.text-truncate-container {
    display: -webkit-box;
    overflow: hidden;
    height: 4.5em;
}

.btn {
    letter-spacing: 0.5px;
    padding: 0.5rem 1rem;
}

.btn:hover {
    transform: translateY(-1px);
}

/* Responsive adjustments */
@media (max-width: 1199.98px) {
    .badge {
        padding: 0.5rem !important;
        font-size: 0.85rem !important;
    }
}

@media (max-width: 991.98px) {

    .col-lg-8,
    .col-lg-4 {
        width: 100%;
    }

    .col-lg-4 {
        margin-top: 1rem;
    }

    .d-flex.flex-column.gap-2 {
        flex-direction: row !important;
    }

    .d-flex.flex-column.gap-2>* {
        flex: 1;
    }
}

@media (max-width: 767.98px) {
    .d-flex.flex-wrap.gap-3 {
        gap: 0.5rem !important;
    }

    .badge {
        font-size: 0.75rem !important;
    }
}
</style>
