<!-- components/dashboards/admin/ListarAdmin.vue -->
<template>
    <div class="listar-admin container text-center">
      <h2 class="mb-4">Listar Deportes y Pistas</h2>
      
      <div class="btn-group mb-4" role="group" aria-label="Selector de vista">
        <button 
          @click="activeView = 'deportes'" 
          :class="['btn', activeView === 'deportes' ? 'btn-primary' : 'btn-outline-primary']"
          style="width: 120px"
        >
          Deportes
        </button>
        <button 
          @click="activeView = 'pistas'" 
          :class="['btn', activeView === 'pistas' ? 'btn-primary' : 'btn-outline-primary']"
          style="width: 120px"
        >
          Pistas
        </button>
      </div>

      <div v-if="activeView === 'deportes'">
        <div v-if="deportes && deportes.length">
          <h3>Deportes</h3>
          <ListDeportes :deportes="deportes" />
        </div>
        <div v-else class="alert alert-info">
          <p class="mb-0">No hay deportes disponibles.</p>
        </div>
      </div>

      <div v-if="activeView === 'pistas'">
        <div v-if="pistas && pistas.length">
          <h3>Pistas</h3>
          <ListPistas 
            :pistas="pistas" 
            :isPrivate="false"
          />
        </div>
        <div v-else class="alert alert-info">
          <p class="mb-0">No hay pistas disponibles.</p>
        </div>
      </div>
    </div>
</template>
  
<script>
import { mapGetters } from 'vuex';
import ListDeportes from '@/components/dashboards/admin/ListDeportes.vue';
import ListPistas from '@/components/dashboards/admin/ListPistas.vue';

export default {
  name: "ListarAdmin",
  components: {
    ListDeportes,
    ListPistas,
  },
  data() {
    return {
      activeView: 'deportes'
    }
  },
  computed: {
    ...mapGetters({
      deportes: 'adminDashboard/GetDeportes',
      pistas: 'adminDashboard/GetPistas',
    }),
  },
  created() {
    this.$store.dispatch('adminDashboard/InitializeDeporte');
    this.$store.dispatch('adminDashboard/InitializePista');
  },
};
</script>

<style scoped>
.listar-admin {
  padding: 2rem 0;
}

.btn-group {
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
}

.btn {
  padding: 0.75rem 2rem;
  font-weight: 500;
  transition: all 0.3s ease;
}
</style>