<template>
    <div class="pending-entrenamientos-container">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="mb-0">
                <i class="bi bi-hourglass-split text-primary me-2"></i>
                Entrenamientos Pendientes de Aprobación
            </h2>
            <button @click="refreshEntrenamientos" class="btn btn-outline-primary">
                <i class="bi bi-arrow-clockwise me-1"></i> Actualizar
            </button>
        </div>
        <!-- Notificaciones -->
        <div v-if="notification.show" :class="`alert alert-${notification.type} alert-dismissible fade show`"
            role="alert">
            <i :class="`bi ${notification.icon} me-2`"></i>
            {{ notification.message }}
            <button type="button" class="btn-close" @click="closeNotification" aria-label="Close"></button>
        </div>
        <!-- Alerta de error -->
        <div v-if="error" class="alert alert-danger" role="alert">
            <i class="bi bi-exclamation-triangle-fill me-2"></i>
            {{ error }}
        </div>
        <!-- Loader -->
        <div v-if="loading" class="text-center py-5">
            <div class="spinner-border text-primary" role="status">
                <span class="visually-hidden">Cargando...</span>
            </div>
            <p class="mt-2 text-muted">Cargando entrenamientos pendientes...</p>
        </div>
        <!-- Sin entrenamientos -->
        <div v-else-if="!loading && pendingEntrenamientos.length === 0" class="text-center py-5">
            <i class="bi bi-check-circle text-success" style="font-size: 3rem;"></i>
            <h4 class="mt-3">¡No hay entrenamientos pendientes!</h4>
            <p class="text-muted">Todos los entrenamientos han sido revisados.</p>
        </div>
        <!-- Lista de entrenamientos -->
        <div v-else class="row g-4">
            <div v-for="entrenamiento in pendingEntrenamientos" :key="entrenamiento.id" class="col-12">
                <div class="card shadow-sm border-0 h-100">
                    <div class="card-body">
                        <div class="row">
                            <!-- Información principal -->
                            <div class="col-md-8">
                                <h4 class="card-title text-primary">{{ entrenamiento.nombre }}</h4>
                                <div class="mb-3">
                                    <span class="badge bg-info me-2">{{ entrenamiento.tecnificacion?.nombre }}</span>
                                    <span class="badge bg-secondary me-2">{{ entrenamiento.subtipoTecnificacion?.nombre
                                        }}</span>
                                    <span class="badge bg-warning text-dark">Nivel: {{ entrenamiento.nivel }}</span>
                                </div>
                                <p class="card-text">{{ entrenamiento.descripcion }}</p>

                                <div class="mt-3">
                                    <div class="d-flex align-items-center mb-2">
                                        <i class="bi bi-person-badge me-2 text-secondary"></i>
                                        <span><strong>Entrenador:</strong> {{ entrenamiento.entrenador?.nombre }} {{
                                            entrenamiento.entrenador?.apellidos }}</span>
                                    </div>
                                    <div class="d-flex align-items-center mb-2">
                                        <i class="bi bi-geo-alt me-2 text-secondary"></i>
                                        <span><strong>Pista:</strong> {{ entrenamiento.horarioPista?.pista?.nombre
                                            }}</span>
                                    </div>
                                    <div class="d-flex align-items-center mb-2">
                                        <i class="bi bi-calendar-event me-2 text-secondary"></i>
                                        <span><strong>Horario:</strong> {{
                                            formatDate(entrenamiento.horarioPista?.fechaInicio) }} -
                                            {{ formatTime(entrenamiento.horarioPista?.fechaInicio) }} a {{
                                                formatTime(entrenamiento.horarioPista?.fechaFin) }}</span>
                                    </div>
                                </div>
                            </div>

                            <!-- Detalles adicionales -->
                            <div class="col-md-4 border-start">
                                <div class="ps-md-3">
                                    <div class="mb-3">
                                        <h6 class="text-muted mb-2">Detalles del entrenamiento</h6>
                                        <div class="d-flex justify-content-between mb-1">
                                            <span>Edad:</span>
                                            <span>{{ entrenamiento.edadMinima }} - {{ entrenamiento.edadMaxima }}
                                                años</span>
                                        </div>
                                        <div class="d-flex justify-content-between mb-1">
                                            <span>Plazas:</span>
                                            <span>{{ entrenamiento.maxPlazas }}</span>
                                        </div>
                                        <div class="d-flex justify-content-between mb-1">
                                            <span>Duración:</span>
                                            <span>{{ entrenamiento.duracionMinutos }} minutos</span>
                                        </div>
                                    </div>
                                    <!-- Acciones -->
                                    <div class="d-grid gap-2 mt-4">
                                        <button @click="approveEntrenamiento(entrenamiento.slug)"
                                            class="btn btn-success" :disabled="processingAction">
                                            <i class="bi bi-check-circle me-1"></i> Aprobar
                                        </button>
                                        <button @click="denyEntrenamiento(entrenamiento.slug)" class="btn btn-danger"
                                            :disabled="processingAction">
                                            <i class="bi bi-x-circle me-1"></i> Denegar
                                        </button>
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

<script setup>
import { ref, computed, onMounted, reactive } from 'vue';
import { useStore } from 'vuex';
import Constant from '@/Constant';

const store = useStore();
const processingAction = ref(false);

//Sistema de notificaciones
const notification = reactive({
    show: false,
    type: 'success',
    message: '',
    icon: 'bi-check-circle-fill',
    timeout: null
});

const showNotification = (message, type = 'success') => {
    if (notification.timeout) {
        clearTimeout(notification.timeout);
    }

    //Configurar notificación
    notification.show = true;
    notification.message = message;
    notification.type = type;

    //Configurar icono según tipo
    notification.icon = type === 'success' ? 'bi-check-circle-fill' : type === 'danger' ? 'bi-exclamation-triangle-fill' : 'bi-info-circle-fill';

    //Auto-cerrar después de 5 segundos
    notification.timeout = setTimeout(() => {
        notification.show = false;
    }, 5000);
};

const closeNotification = () => {
    notification.show = false;
    if (notification.timeout) {
        clearTimeout(notification.timeout);
    }
};

const pendingEntrenamientos = computed(() => store.getters['adminDashboard/getPendingEntrenamientos']);
const loading = computed(() => store.getters['adminDashboard/isLoadingEntrenamientos']);
const error = computed(() => store.getters['adminDashboard/getErrorEntrenamientos']);
const refreshEntrenamientos = async () => {
    await fetchPendingEntrenamientos();
    showNotification('Lista de entrenamientos actualizada', 'info');
};

const fetchPendingEntrenamientos = async () => {
    await store.dispatch(`adminDashboard/${Constant.FETCH_PENDING_ENTRENAMIENTOS}`);
};

const approveEntrenamiento = async (slug) => {
    try {
        processingAction.value = true;
        const success = await store.dispatch(`adminDashboard/${Constant.APPROVE_ENTRENAMIENTO}`, slug);

        if (success) {
            showNotification("Entrenamiento aprobado correctamente", "success");
        } else {
            showNotification("No se pudo aprobar el entrenamiento", "danger");
        }
    } catch (error) {
        showNotification("Error al aprobar el entrenamiento", "danger");
        console.error(error);
    } finally {
        processingAction.value = false;
    }
};

const denyEntrenamiento = async (slug) => {
    try {
        processingAction.value = true;
        const success = await store.dispatch(`adminDashboard/${Constant.DENY_ENTRENAMIENTO}`, slug);

        if (success) {
            showNotification("Entrenamiento denegado correctamente", "success");
        } else {
            showNotification("No se pudo denegar el entrenamiento", "danger");
        }
    } catch (error) {
        showNotification("Error al denegar el entrenamiento", "danger");
        console.error(error);
    } finally {
        processingAction.value = false;
    }
};

const formatDate = (dateString) => {
    if (!dateString) return 'Fecha no disponible';

    const options = { year: 'numeric', month: 'long', day: 'numeric' };
    return new Date(dateString).toLocaleDateString('es-ES', options);
};
const formatTime = (dateString) => {
    if (!dateString) return '--:--';

    const options = { hour: '2-digit', minute: '2-digit' };
    return new Date(dateString).toLocaleTimeString('es-ES', options);
};

onMounted(async () => {
    await fetchPendingEntrenamientos();
});
</script>

<style scoped>
.pending-entrenamientos-container {
    padding: 1rem;
}

.card {
    transition: transform 0.2s, box-shadow 0.2s;
    border-radius: 0.5rem;
}

.card:hover {
    transform: translateY(-5px);
    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1) !important;
}

.badge {
    font-weight: 500;
    padding: 0.5em 0.8em;
    border-radius: 0.25rem;
}

.btn {
    border-radius: 0.25rem;
    padding: 0.5rem 1rem;
    font-weight: 500;
}

.btn-outline-primary {
    border-width: 2px;
}

.spinner-border {
    width: 3rem;
    height: 3rem;
}

@media (max-width: 768px) {
    .col-md-4 {
        border-left: none !important;
        border-top: 1px solid #dee2e6;
        padding-top: 1rem;
        margin-top: 1rem;
    }

    .ps-md-3 {
        padding-left: 0 !important;
    }
}
</style>