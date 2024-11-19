<template>
  <div class="list-clases">
    <h2>Lista de Clases</h2>
    <!-- Mostrar las tarjetas de las clases -->
    <CardClasesDashboard 
      v-for="clase in state.clases" 
      :key="clase.id" 
      :clase="clase" 
    />
  </div>
</template>

<script>
import { reactive, computed } from 'vue';
import { useStore } from 'vuex';
import Constant from '../../Constant';
import CardClasesDashboard from './CardClasesDashboard.vue';

export default {
  components: {
    CardClasesDashboard,
  },
  setup() {
    const store = useStore();

    store.dispatch(`monitorDashboard/${Constant.INITIALIZE_CLASE}`);

    const state = reactive({
      clases: computed(() => store.getters['monitorDashboard/GetClases']),
    });

    return { state };
  },
};
</script>

<style scoped>
.list-clases {
  margin-top: 20px;
}

.text-muted {
  font-style: italic;
  color: #6c757d;
}
</style>
