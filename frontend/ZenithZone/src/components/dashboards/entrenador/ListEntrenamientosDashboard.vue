<template>
  <div class="list-entrenamientos">
    <h2>Lista de Entrenamientos</h2>
    <!-- Mostrar las tarjetas de los entrenamientos -->
    <CardEntrenamientosDashboard 
      v-for="entrenamiento in state.entrenamientos" 
      :key="entrenamiento.id" 
      :entrenamiento="entrenamiento" 
    />
  </div>
</template>

<script>
import { reactive, computed } from 'vue';
import { useStore } from 'vuex';
import Constant from '../../../Constant';
import CardEntrenamientosDashboard from './CardEntrenamientosDashboard.vue';

export default {
  components: {
    CardEntrenamientosDashboard,
  },
  setup() {
    const store = useStore();

    store.dispatch(`entrenadorDashboard/${Constant.INITIALIZE_ENTRENAMIENTO}`);

    const state = reactive({
      entrenamientos: computed(() => store.getters['entrenadorDashboard/GetEntrenamientos']),
    });

    return { state };
  },
};
</script>

<style scoped>
.list-entrenamientos {
  margin-top: 20px;
}

.text-muted {
  font-style: italic;
  color: #6c757d;
}
</style>
