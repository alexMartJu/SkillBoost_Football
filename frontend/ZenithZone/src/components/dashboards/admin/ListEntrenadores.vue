<template>
  <div class="Entrenador-list">
    <h1>Lista de Entrenadores</h1>
    <CardEntrenadores
      v-for="entrenador in entrenadores.data"
      :key="entrenador.numeroentrenador"
      :entrenador="entrenador"
      @eliminarEntrenador="eliminarEntrenador"
    />
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
          console.log("Entrenador eliminado con éxito");
        })
        .catch(error => {
          console.error("Error al eliminar el entrenador:", error);
        });
      // this.$emit('eliminarEntrenador', entrenadorId);
    },
  },
  created() {
    this.$store.dispatch('adminDashboard/initializeEntrenador');
  },
};
</script>