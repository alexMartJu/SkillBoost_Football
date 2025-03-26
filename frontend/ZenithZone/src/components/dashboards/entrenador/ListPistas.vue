<template>
    <div class="container py-4">
        <div class="card shadow-sm border-0">
            <div class="card-body p-4">
                <h2 class="display-6 text-center mb-4">Seleccionar Pista</h2>

                <div v-if="loading" class="text-center my-5">
                    <div class="spinner-border text-primary" role="status">
                        <span class="visually-hidden">Cargando...</span>
                    </div>
                    <p class="mt-2">Cargando pistas...</p>
                </div>

                <div v-else-if="error" class="alert alert-danger" role="alert">
                    {{ error }}
                </div>

                <div v-else-if="pistas.length === 0" class="text-center my-5">
                    <i class="bi bi-exclamation-circle fs-1 text-muted"></i>
                    <p class="mt-2">No hay pistas disponibles</p>
                </div>

                <div v-else class="row row-cols-1 row-cols-md-3 g-4">
                    <div v-for="pista in pistas" :key="pista.id" class="col">
                        <div class="card h-100 hover-card" @click="selectPista(pista)">
                            <div v-if="pista.images && pista.images.length > 0" class="card-img-top-container">
                                <img :src="`/assets/pistas/${pista.images[0].imageUrl}`" class="card-img-top" alt="Imagen de pista">
                            </div>
                            <div v-else class="card-img-top-placeholder">
                                <i class="bi bi-image fs-1 text-muted"></i>
                            </div>
                            <div class="card-body">
                                <h5 class="card-title">{{ pista.nombre }}</h5>
                                <p class="card-text text-muted">{{ pista.tipo }}</p>
                                <p class="card-text small">{{ pista.descripcion }}</p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <span class="badge bg-primary">Capacidad: {{ pista.capacidad }}</span>
                                    <button class="btn btn-outline-primary btn-sm" @click.stop="selectPista(pista)">
                                        <i class="bi bi-calendar-check me-1"></i> Ver horarios
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { computed, onMounted } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';
import Constant from '@/Constant';

export default {
    name: 'ListPistas',
    setup() {
        const store = useStore();
        const router = useRouter();

        //Cargar pistas al montar el componente
        onMounted(() => {
            store.dispatch(`entrenadorDashboard/${Constant.FETCH_PISTAS}`);
        });

        const pistas = computed(() => store.getters['entrenadorDashboard/getPistas']);
        const loading = computed(() => store.getters['entrenadorDashboard/isLoading']);
        const error = computed(() => store.getters['entrenadorDashboard/getError']);

        const selectPista = (pista) => {
            store.dispatch(`entrenadorDashboard/${Constant.SET_SELECTED_PISTA}`, pista);
            router.push('/dashboardEntrenador/horarios-pista');
        };

        return {
            pistas,
            loading,
            error,
            selectPista
        };
    }
};
</script>

<style scoped>
.hover-card {
    transition: transform 0.3s ease, box-shadow 0.3s ease;
    cursor: pointer;
}

.hover-card:hover {
    transform: translateY(-5px);
    box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
}

.card-img-top-container {
    height: 180px;
    overflow: hidden;
}

.card-img-top {
    width: 100%;
    height: 100%;
    object-fit: cover;
}

.card-img-top-placeholder {
    height: 180px;
    display: flex;
    align-items: center;
    justify-content: center;
    background-color: #f8f9fa;
}
</style>
