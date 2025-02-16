<template>
  <div class="container py-4">
    <h1 class="display-5 fw-bold text-primary mb-4">Gestión de Entrenamientos</h1>
    
    <!-- Sección de Entrenamientos Pendientes -->
    <div class="card shadow-sm mb-4">
      <div class="card-header bg-white">
        <h2 class="h4 mb-0 text-primary">Entrenamientos Pendientes</h2>
      </div>
      <div class="card-body">
        <div class="row g-3">
          <template v-if="pendientes.length > 0">
            <div class="col-12 col-md-6 col-lg-4" v-for="entrenamiento in pendientes" :key="entrenamiento.slug">
              <CardEntrenamientos
                :entrenamiento="entrenamiento"
                @cambiarEstado="cambiarEstado"
                @eliminarEntrenamiento="eliminarEntrenamiento"
              />
            </div>
          </template>
          <div v-else class="col-12">
            <p class="text-muted mb-0">No hay entrenamientos pendientes</p>
          </div>
        </div>
      </div>
    </div>

    <!-- Sección de Entrenamientos Aceptados/Denegados -->
    <div class="card shadow-sm">
      <div class="card-header bg-white">
        <h2 class="h4 mb-0 text-primary">Historial de Entrenamientos</h2>
      </div>
      <div class="card-body">
        <div class="row g-3">
          <template v-if="aceptadosOdenegados.length > 0">
            <div class="col-12 col-md-6 col-lg-4" v-for="entrenamiento in aceptadosOdenegados" :key="entrenamiento.slug">
              <CardEntrenamientos
                :entrenamiento="entrenamiento"
                @cambiarEstado="cambiarEstado"
                @eliminarEntrenamiento="eliminarEntrenamiento"
              />
            </div>
          </template>
          <div v-else class="col-12">
            <p class="text-muted mb-0">No hay entrenamientos procesados</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

  
  <script>
  import { mapGetters } from 'vuex';
  import CardEntrenamientos from './CardEntrenamientos.vue';
  
  export default {
    name: 'ListEntrenamientos',
    components: {
      CardEntrenamientos,
    },
    computed: {
      ...mapGetters({
        entrenamientos: 'adminDashboard/GetEntrenamientos', 
      }),
      pendientes() {
      return this.entrenamientos.filter(entrenamiento => entrenamiento.status === 'pending');
    },
        aceptadosOdenegados() {
        return this.entrenamientos.filter(entrenamiento => 
            entrenamiento.status === 'accepted' || entrenamiento.status === 'completed' || entrenamiento.status === 'denied'||  !entrenamiento.status);
        },

    },
    methods: {
     
        cambiarEstado(slug, status) {
       
            // const status = payload.status;

            
            // if (status === 'accepted' || status === 'denied') {
            this.$store.dispatch('adminDashboard/cambiarEstadoEntrenamiento', { slug, status })
                .then(() => {
                console.log("Estado actualizado con éxito");
                })
                .catch(error => {
                console.error("Error al cambiar el estado del entrenamiento:", error);
                });
            // } else {
            // console.error("Estado no válido:", status);
            // }
        },
     
      eliminarEntrenamiento(slug) {
        this.$store.dispatch('adminDashboard/eliminarEntrenamiento', slug)
          .then(() => {
            console.log("Entrenamiento eliminado con éxito");
          })
          .catch(error => {
            console.error("Error al eliminar el entrenamiento:", error);
          });
      },
    },
    created() {
      
      this.$store.dispatch('adminDashboard/InitializeEntrenamiento');
    },
  };
  </script>
  
<style scoped>
</style>
  