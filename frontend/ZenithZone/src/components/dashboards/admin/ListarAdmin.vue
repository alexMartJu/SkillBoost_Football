<!-- components/dashboards/admin/ListarAdmin.vue -->
<template>
    <div class = listar-admin>
      <h2>Listar Deportes y Pistas</h2>
      <div v-if="deportes && deportes.length">
        <h3>Deportes</h3>
        <ListDeportes :deportes="deportes" />
      </div>
      <div v-else>
        <p>No hay deportes disponibles.</p>
      </div>
      <div v-if="pistas && pistas.length">
        <h3>Pistas</h3>
        <ListPistas 
        :pistas="pistas" 
        :isPrivate="false"
      />
      </div>
      <div v-else>
        <p>No hay pistas disponibles.</p>
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
/* Estilos para ListarAdmin.vue */
.listar-admin {
  padding: 20px;
  font-family: Arial, sans-serif;
  background-color: #f9f9f9;
}

h2 {
  font-size: 2.5em;
  color: #333;
  margin-bottom: 20px;
}

h3 {
  font-size: 2em;
  color: #4CAF50;
  margin-bottom: 15px;
}

.no-data {
  font-size: 1.2em;
  color: #999;
  margin-top: 20px;
}
</style>