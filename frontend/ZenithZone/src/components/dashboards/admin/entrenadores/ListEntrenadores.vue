<template>
    <div class="entrenadores-list">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="mb-0">
                <i class="bi bi-people-fill me-2"></i>
                Gestión de Entrenadores
            </h2>
        </div>

        <!-- Alerta de error -->
        <div v-if="errorEntrenadores" class="alert alert-danger" role="alert">
            <i class="bi bi-exclamation-triangle-fill me-2"></i>
            {{ errorEntrenadores }}
        </div>

        <!-- Loader -->
        <div v-if="loadingEntrenadores" class="text-center my-5">
            <div class="spinner-border text-primary" role="status">
                <span class="visually-hidden">Cargando...</span>
            </div>
            <p class="mt-2">Cargando entrenadores...</p>
        </div>

        <!-- Sin resultados -->
        <div v-else-if="entrenadores.length === 0" class="text-center my-5">
            <i class="bi bi-search fs-1 text-muted"></i>
            <p class="mt-3">No se encontraron entrenadores registrados</p>
        </div>

        <!-- Lista de entrenadores -->
        <div v-else class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
            <div v-for="entrenador in entrenadores" :key="entrenador.id" class="col">
                <entrenador-card :entrenador="entrenador" @eliminar="eliminarEntrenador" />
            </div>
        </div>
    </div>
</template>

<script setup>
import { computed, onMounted } from 'vue';
import { useStore } from 'vuex';
import EntrenadorCard from './EntrenadorCard.vue';
import Swal from 'sweetalert2';
import Constant from '@/Constant';

const store = useStore();
const loadingEntrenadores = computed(() => store.getters['adminDashboard/isLoadingEntrenadores']);
const errorEntrenadores = computed(() => store.getters['adminDashboard/getErrorEntrenadores']);
const entrenadores = computed(() => store.getters['adminDashboard/getEntrenadores']);

onMounted(async () => {
    await cargarEntrenadores();
});

const cargarEntrenadores = async () => {
    await store.dispatch(`adminDashboard/${Constant.ADMIN_INITIALIZE_ENTRENADORES}`);
};

const eliminarEntrenador = async (numeroEntrenador) => {
    const resultado = await store.dispatch(`adminDashboard/${Constant.ADMIN_DELETE_ENTRENADOR}`, numeroEntrenador);

    if (resultado) {
        Swal.fire({
            title: '¡Eliminado!',
            text: 'El entrenador ha sido eliminado correctamente',
            icon: 'success',
            confirmButtonText: 'Aceptar'
        });
    } else {
        Swal.fire({
            title: 'Error',
            text: 'No se pudo eliminar el entrenador',
            icon: 'error',
            confirmButtonText: 'Aceptar'
        });
    }
};
</script>

<style scoped>
.entrenadores-list {
    padding: 1rem;
}
</style>