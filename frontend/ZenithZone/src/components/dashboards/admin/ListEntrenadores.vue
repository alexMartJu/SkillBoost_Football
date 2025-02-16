<template>
  <div class="container py-5">
    <div v-if="showAlert" class="alert alert-success alert-dismissible fade show" role="alert">
      Entrenador eliminado correctamente
      <button type="button" class="btn-close" @click="showAlert = false" aria-label="Close"></button>
    </div>
    <div class="row">
      <div class="col-12">
        <h1 class="display-4 mb-4 text-primary fw-bold text-center">Lista de Entrenadores</h1>
        <div class="row g-4">
          <div class="col-12 col-md-6 col-lg-4" v-for="entrenador in entrenadores.data" :key="entrenador.numeroentrenador">
            <CardEntrenadores
              :entrenador="entrenador"
              @eliminarEntrenador="eliminarEntrenador"
              class="h-100"
            />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import CardEntrenadores from './CardEntrenadores.vue'; 

export default {
  name: 'ListEntrenadores',
  components: {
    CardEntrenadores,
  },
  data() {
    return {
      showAlert: false,
      showErrorAlert: false
    }
  },
  computed:{
    ...mapGetters({
      entrenadores: 'adminDashboard/GetEntrenadores',
    }),
  },
  props: {

  },
  methods: {
    eliminarEntrenador(numeroentrenador) {
      this.$store.dispatch('adminDashboard/deleteOneEntrenador', numeroentrenador)
        .then(() => {
          this.showAlert = true;
          setTimeout(() => {
            this.showAlert = false;
          }, 3000);
        })
        .catch(error => {
          console.error("Error al eliminar el entrenador:", error);
          this.showErrorAlert = true;
          setTimeout(() => {
            this.showErrorAlert = false;
          }, 3000);
        });
    },
  },
  created() {
    this.$store.dispatch('adminDashboard/initializeEntrenador');
  },
};
</script>