<template>
  <div class="container py-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
      <h2 class="mb-0">Gestión de Tecnificaciones</h2>
      <router-link to="/dashboardAdmin/tecnificaciones/crear" class="btn btn-primary">
        <i class="bi bi-plus-circle me-2"></i>Nueva Tecnificación
      </router-link>
    </div>

    <div v-if="loading" class="text-center py-5">
      <div class="spinner-border text-primary" role="status">
        <span class="visually-hidden">Cargando...</span>
      </div>
    </div>

    <div v-else-if="tecnificaciones.length === 0" class="alert alert-info">
      No hay tecnificaciones disponibles. ¡Crea una nueva!
    </div>

    <div v-else class="row g-4">
      <div class="col-12 col-md-6 col-lg-4" v-for="tecnificacion in tecnificaciones" :key="tecnificacion.id">
        <CardTecnificaciones :tecnificacion="tecnificacion" @tecnificacionEliminada="cargarTecnificaciones"
          class="h-100" />
      </div>
    </div>
  </div>
</template>

<script>
import CardTecnificaciones from './CardTecnificaciones.vue';
import { mapGetters } from 'vuex';

export default {
  name: 'ListTecnificaciones',
  components: {
    CardTecnificaciones
  },
  data() {
    return {
      loading: true
    };
  },
  computed: {
    ...mapGetters('adminDashboard', ['GetTecnificaciones']),
    tecnificaciones() {
      return this.GetTecnificaciones || [];
    }
  },
  created() {
    this.cargarTecnificaciones();
  },
  methods: {
    cargarTecnificaciones() {
      this.loading = true;
      this.$store.dispatch('adminDashboard/InitializeTecnificacionAdmin')
        .then(() => {
          this.loading = false;
        })
        .catch(error => {
          console.error("Error al cargar tecnificaciones:", error);
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