<template>
    <div class="container py-4">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="mb-0">Gestión de Suscripciones</h2>
        </div>
        <div v-if="loading" class="text-center py-5">
            <div class="spinner-border text-primary" role="status">
                <span class="visually-hidden">Cargando...</span>
            </div>
        </div>
        <div v-else-if="suscripciones.length === 0" class="alert alert-info">
            No hay suscripciones disponibles.
        </div>
        <div v-else class="row g-4">
            <div class="col-12 col-md-6 col-lg-4" v-for="suscripcion in suscripciones" :key="suscripcion.slug">
                <CardSuscripciones :suscripcion="suscripcion" @precioActualizado="cargarSuscripciones" class="h-100" />
            </div>
        </div>
    </div>
</template>

<script>
import CardSuscripciones from './CardSuscripciones.vue';
import { mapGetters } from 'vuex';

export default {
    name: 'ListSuscripciones',
    components: {
        CardSuscripciones
    },
    data() {
        return {
            loading: true
        };
    },
    computed: {
        ...mapGetters('adminDashboard', ['GetSuscripciones']),
        suscripciones() {
            return this.GetSuscripciones || [];
        }
    },
    created() {
        this.cargarSuscripciones();
    },
    methods: {
        cargarSuscripciones() {
            this.loading = true;
            this.$store.dispatch('adminDashboard/InitializeSuscripcionesAdmin')
                .then(() => {
                    this.loading = false;
                })
                .catch(error => {
                    console.error("Error al cargar suscripciones:", error);
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