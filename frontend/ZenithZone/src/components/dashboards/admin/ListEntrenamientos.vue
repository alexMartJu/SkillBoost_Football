  <template>
    <div class="entrenamiento-list">
      <h1>Lista de Entrenamientos</h1>
  
      <!-- Lista de Entrenamientos Pendientes -->
      <h2>Entrenamientos Pendientes</h2>
      <div v-if="pendientes.length > 0">
        <CardEntrenamientos
          v-for="entrenamiento in pendientes"
          :key="entrenamiento.slug"
          :entrenamiento="entrenamiento"
          @cambiarEstado="cambiarEstado"
          @eliminarEntrenamiento="eliminarEntrenamiento"
        />
      </div>
      <p v-else>No hay entrenamientos pendientes.</p>
  
      <!-- Lista de Entrenamientos Aceptados o Denegados -->
      <h2>Entrenamientos Aceptados / Denegados</h2>
      <div v-if="aceptadosOdenegados.length > 0">
        <CardEntrenamientos
          v-for="entrenamiento in aceptadosOdenegados"
          :key="entrenamiento.slug"
          :entrenamiento="entrenamiento"
          @cambiarEstado="cambiarEstado"
          @eliminarEntrenamiento="eliminarEntrenamiento"
        />
      </div>
      <p v-else>No hay entrenamientos aceptados o denegados.</p>
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
  .entrenamiento-list {
    display: flex;
    flex-direction: column;
    gap: 10px;
  }
  </style>
  