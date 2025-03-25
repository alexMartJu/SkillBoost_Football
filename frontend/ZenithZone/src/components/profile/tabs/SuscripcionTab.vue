<template>
    <div class="suscripcion-tab p-4">
        <h4 class="mb-4">Mi Suscripción</h4>

        <div v-if="loading" class="text-center my-5">
            <div class="spinner-border text-primary" role="status">
                <span class="visually-hidden">Cargando...</span>
            </div>
        </div>

        <div v-else-if="!suscripcion" class="text-center my-5 py-5">
            <i class="bi bi-credit-card text-muted" style="font-size: 3rem;"></i>
            <p class="mt-3 text-muted">No tienes ninguna suscripción activa</p>
            <router-link to="/planes" class="btn btn-primary mt-3">
                Ver planes disponibles
            </router-link>
        </div>

        <div v-else class="row">
            <div class="col-md-8 mx-auto">
                <div class="card shadow-sm border-0 rounded-3 hover-card">
                    <div class="card-header bg-primary text-white py-3">
                        <h5 class="mb-0 fw-bold">{{ suscripcion.nombre }}</h5>
                    </div>
                    <div class="card-body p-4">
                        <div class="d-flex justify-content-between align-items-center mb-4">
                            <h3 class="mb-0 fw-bold text-primary">{{ formatPrice(suscripcion.precio) }}</h3>
                            <span class="badge bg-success px-3 py-2">Activa</span>
                        </div>

                        <div class="row mb-4">
                            <div class="col-md-6">
                                <div class="feature-item mb-3">
                                    <i class="bi bi-calendar-check-fill text-primary me-2"></i>
                                    <span>{{ suscripcion.entrenamientosTotales }} entrenamientos</span>
                                </div>
                                <div class="feature-item mb-3">
                                    <i class="bi bi-graph-up-arrow text-primary me-2"></i>
                                    <span>{{ suscripcion.incluyeGraficas ? 'Incluye gráficas' : 'Sin gráficas' }}</span>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="feature-item mb-3">
                                    <i class="bi bi-clipboard-check-fill text-primary me-2"></i>
                                    <span>{{ suscripcion.incluyeEvaluacion ? 'Incluye evaluación' : 'Sin evaluación' }}</span>
                                </div>
                            </div>
                        </div>

                        <div class="benefits-section">
                            <h6 class="fw-bold mb-3">Ventajas incluidas:</h6>
                            <ul class="list-group list-group-flush">
                                <li v-if="suscripcion.ventaja1" class="list-group-item border-0 ps-0">
                                    <i class="bi bi-check-circle-fill text-success me-2"></i>
                                    {{ suscripcion.ventaja1 }}
                                </li>
                                <li v-if="suscripcion.ventaja2" class="list-group-item border-0 ps-0">
                                    <i class="bi bi-check-circle-fill text-success me-2"></i>
                                    {{ suscripcion.ventaja2 }}
                                </li>
                                <li v-if="suscripcion.ventaja3" class="list-group-item border-0 ps-0">
                                    <i class="bi bi-check-circle-fill text-success me-2"></i>
                                    {{ suscripcion.ventaja3 }}
                                </li>
                                <li v-if="suscripcion.ventaja4" class="list-group-item border-0 ps-0">
                                    <i class="bi bi-check-circle-fill text-success me-2"></i>
                                    {{ suscripcion.ventaja4 }}
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="card-footer bg-light p-3">
                        <div class="d-flex justify-content-between align-items-center">
                            <small class="text-muted">Puedes cambiar tu plan en cualquier momento</small>
                            <router-link to="/planes" class="btn btn-outline-primary btn-sm">
                                Cambiar plan
                            </router-link>
                        </div>
                    </div>
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
    name: 'SuscripcionTab',
    setup() {
        const store = useStore();
        const loading = ref(true);

        const suscripcion = computed(() => {
            return store.getters['profile/GetSuscripcionActiva'];
        });

        const formatPrice = (price) => {
            if (!price) return '€0.00';
            return `€${parseFloat(price).toFixed(2)}`;
        };

        const loadSuscripcion = async () => {
            loading.value = true;
            try {
                await store.dispatch(`profile/${Constant.INITIALIZE_SUSCRIPCION_ACTIVA}`);
            } catch (error) {
                console.error("Error al cargar la suscripción:", error);
            } finally {
                loading.value = false;
            }
        };

        onMounted(() => {
            loadSuscripcion();
        });

        return {
            loading,
            suscripcion, 
            formatPrice
        };
    }
};
</script>

<style scoped>
.suscripcion-tab {
    width: 100%;
}

.hover-card {
    transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.hover-card:hover {
    transform: translateY(-5px);
    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1) !important;
}

.feature-item {
    display: flex;
    align-items: center;
    font-size: 1rem;
    color: #495057;
}

.benefits-section {
    border-top: 1px solid #e9ecef;
    padding-top: 1.5rem;
    margin-top: 1rem;
}

@media (max-width: 768px) {
    .feature-item {
        font-size: 0.9rem;
    }
}
</style>
