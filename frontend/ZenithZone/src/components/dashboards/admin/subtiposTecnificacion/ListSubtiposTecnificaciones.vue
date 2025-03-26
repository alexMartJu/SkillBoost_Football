<template>
    <div class="container py-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="mb-0">Gestión de Subtipos de Tecnificación</h2>
            <router-link to="/dashboardAdmin/subtipos-tecnificaciones/crear" class="btn btn-primary">
                <i class="bi bi-plus-circle me-2"></i>Nuevo Subtipo
            </router-link>
        </div>

        <div v-if="loading" class="text-center py-5">
            <div class="spinner-border text-primary" role="status">
                <span class="visually-hidden">Cargando...</span>
            </div>
        </div>

        <div v-else-if="subtiposTecnificacion.length === 0" class="alert alert-info">
            No hay subtipos de tecnificación disponibles. ¡Crea uno nuevo!
        </div>

        <div v-else class="row g-4">
            <div class="col-12 col-md-6 col-lg-4" v-for="subtipo in subtiposTecnificacion" :key="subtipo.id">
                <CardSubtiposTecnificaciones :subtipo="subtipo" @subtipoEliminado="cargarSubtipos" class="h-100" />
            </div>
        </div>
    </div>
</template>

<script>
import CardSubtiposTecnificaciones from './CardSubtiposTecnificaciones.vue';
import { mapGetters } from 'vuex';

export default {
    name: 'ListSubtiposTecnificaciones',
    components: {
        CardSubtiposTecnificaciones
    },
    data() {
        return {
            loading: true
        };
    },
    computed: {
        ...mapGetters('adminDashboard', ['GetSubtiposTecnificacion']),
        subtiposTecnificacion() {
            return this.GetSubtiposTecnificacion || [];
        }
    },
    created() {
        this.cargarSubtipos();
    },
    methods: {
        cargarSubtipos() {
            this.loading = true;
            this.$store.dispatch('adminDashboard/InitializeSubtipoTecnificacionAdmin')
                .then(() => {
                    this.loading = false;
                })
                .catch(error => {
                    console.error("Error al cargar subtipos de tecnificación:", error);
                    this.loading = false;
                });
        }
    }
};
</script>

<style scoped>
.container {
    background-color: #f8f9fa;
}

.col-12 {
    transition: transform 0.3s ease;
}

.col-12:hover {
    transform: translateY(-5px);
}
</style>