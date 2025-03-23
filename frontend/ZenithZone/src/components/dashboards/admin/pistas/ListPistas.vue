<template>
    <div class="container py-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="mb-0">Gestión de Pistas</h2>
            <router-link to="/dashboardAdmin/pistas/crear" class="btn btn-primary">
                <i class="bi bi-plus-circle me-2"></i>Nueva Pista
            </router-link>
        </div>

        <div v-if="loading" class="text-center py-5">
            <div class="spinner-border text-primary" role="status">
                <span class="visually-hidden">Cargando...</span>
            </div>
        </div>

        <div v-else-if="pistas.length === 0" class="alert alert-info">
            No hay pistas disponibles. ¡Crea una nueva!
        </div>

        <div v-else class="row g-4">
            <div class="col-12 col-md-6 col-lg-4" v-for="pista in pistas" :key="pista.id">
                <CardPistas :pista="pista" @pistaEliminada="cargarPistas" class="h-100" />
            </div>
        </div>
    </div>
</template>

<script>
import CardPistas from './CardPistas.vue';
import { mapGetters } from 'vuex';

export default {
    name: 'ListPistas',
    components: {
        CardPistas
    },
    data() {
        return {
            loading: true
        };
    },
    computed: {
        ...mapGetters('adminDashboard', ['GetPistas']),
        pistas() {
            return this.GetPistas || [];
        }
    },
    created() {
        this.cargarPistas();
    },
    methods: {
        cargarPistas() {
            this.loading = true;
            this.$store.dispatch('adminDashboard/InitializePistaAdmin')
                .then(() => {
                    this.loading = false;
                })
                .catch(error => {
                    console.error("Error al cargar pistas:", error);
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