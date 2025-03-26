<template>
    <div class="entrenamientos-container">
        <h2 class="mb-4 fw-bold text-primary">Mis Entrenamientos</h2>

        <!-- Alertas de error -->
        <div v-if="error" class="alert alert-danger" role="alert">
            {{ error }}
        </div>

        <!-- Tabs para los diferentes estados -->
        <ul class="nav nav-tabs mb-4">
            <li class="nav-item">
                <a class="nav-link" :class="{ active: activeTab === 'pending' }" @click.prevent="activeTab = 'pending'"
                    href="#">
                    <i class="bi bi-hourglass me-1"></i> Pendientes
                    <span class="badge bg-warning ms-1">{{ pendingEntrenamientos.length }}</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" :class="{ active: activeTab === 'approved' }"
                    @click.prevent="activeTab = 'approved'" href="#">
                    <i class="bi bi-check-circle me-1"></i> Aprobados
                    <span class="badge bg-success ms-1">{{ approvedEntrenamientos.length }}</span>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" :class="{ active: activeTab === 'denied' }" @click.prevent="activeTab = 'denied'"
                    href="#">
                    <i class="bi bi-x-circle me-1"></i> Denegados
                    <span class="badge bg-danger ms-1">{{ deniedEntrenamientos.length }}</span>
                </a>
            </li>
        </ul>

        <!-- Spinner de carga -->
        <div v-if="loading" class="text-center py-5">
            <div class="spinner-border text-primary" role="status">
                <span class="visually-hidden">Cargando...</span>
            </div>
            <p class="mt-2">Cargando entrenamientos...</p>
        </div>

        <!-- Contenido de los tabs -->
        <div v-else class="tab-content">
            <!-- Tab de entrenamientos pendientes -->
            <div v-if="activeTab === 'pending'" class="tab-pane fade show active">
                <entrenamiento-list :entrenamientos="pendingEntrenamientos" estado="pending"
                    mensaje-vacio="No tienes entrenamientos pendientes de aprobación." />
            </div>

            <!-- Tab de entrenamientos aprobados -->
            <div v-if="activeTab === 'approved'" class="tab-pane fade show active">
                <entrenamiento-list :entrenamientos="approvedEntrenamientos" estado="approved"
                    mensaje-vacio="No tienes entrenamientos aprobados." />
            </div>

            <!-- Tab de entrenamientos denegados -->
            <div v-if="activeTab === 'denied'" class="tab-pane fade show active">
                <entrenamiento-list :entrenamientos="deniedEntrenamientos" estado="denied"
                    mensaje-vacio="No tienes entrenamientos denegados." />
            </div>
        </div>
    </div>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
import EntrenamientoList from './EntrenamientoList.vue';

export default {
    name: 'MisEntrenamientos',
    components: {
        EntrenamientoList
    },
    data() {
        return {
            activeTab: 'pending'
        };
    },
    computed: {
        ...mapGetters('entrenadorDashboard', [
            'getPendingEntrenamientos',
            'getApprovedEntrenamientos',
            'getDeniedEntrenamientos',
            'isLoading',
            'getError'
        ]),
        pendingEntrenamientos() {
            return this.getPendingEntrenamientos;
        },
        approvedEntrenamientos() {
            return this.getApprovedEntrenamientos;
        },
        deniedEntrenamientos() {
            return this.getDeniedEntrenamientos;
        },
        loading() {
            return this.isLoading;
        },
        error() {
            return this.getError;
        }
    },
    methods: {
        ...mapActions('entrenadorDashboard', [
            'fetchPendingEntrenamientos',
            'fetchApprovedEntrenamientos',
            'fetchDeniedEntrenamientos'
        ]),
        loadEntrenamientos() {
            this.fetchPendingEntrenamientos();
            this.fetchApprovedEntrenamientos();
            this.fetchDeniedEntrenamientos();
        }
    },
    created() {
        this.loadEntrenamientos();
    },
    watch: {
        activeTab() {
        }
    }
};
</script>

<style scoped>
.nav-tabs .nav-link {
    cursor: pointer;
    color: #6c757d;
    font-weight: 500;
}

.nav-tabs .nav-link.active {
    color: var(--bs-primary);
    font-weight: 600;
    border-bottom: 2px solid var(--bs-primary);
}

.tab-content {
    min-height: 300px;
}
</style>