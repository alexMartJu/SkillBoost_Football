<!-- components/dashboards/admin/ListarAdmin.vue -->
<template>
    <div class = listar-admin>
      <h2>Listar Deportes y Pistas</h2>
      <div v-if="deportes && deportes.length">
        <h3>Deportes</h3>
        <DeporteList :deportes="deportes" />
      </div>
      <div v-else>
        <p>No hay deportes disponibles.</p>
      </div>
      <div v-if="pistas && pistas.length">
        <h3>Pistas</h3>
        <PistaList :pistas="pistas" />
      </div>
      <div v-else>
        <p>No hay pistas disponibles.</p>
      </div>
    </div>
  </template>
  
  <script>
  import { mapGetters } from 'vuex';
  import DeporteList from '@/components/dashboards/admin/DeporteList.vue';
  import PistaList from '@/components/dashboards/admin/PistaList.vue';
  
  export default {
    name: "ListarAdmin",
    components: {
      DeporteList,
      PistaList,
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

/* Estilo para listas de deportes y pistas */
.deporte-list, .pista-list {
  margin-top: 30px;
  padding: 0;
  list-style-type: none;
  display: flex;
  flex-wrap: wrap;
  gap: 20px; /* Espaciado entre los elementos */
}

.deporte-list li, .pista-list li {
  background-color: #fff;
  padding: 10px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  width: calc(33% - 20px); /* Tres elementos por fila */
  transition: transform 0.2s ease, box-shadow 0.2s ease;
  cursor: pointer;
}

.deporte-list li:hover, .pista-list li:hover {
  transform: translateY(-5px); /* Efecto al pasar el mouse */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.deporte-list li p, .pista-list li p {
  margin: 0;
}

/* Pista List: Mostrar deportes asociados */
.pista-list .deportes-asociados {
  margin-top: 10px;
  font-size: 1em;
  color: #666;
  padding-left: 20px;
}

.pista-list .deportes-asociados ul {
  padding-left: 20px;
  list-style-type: none;
}

.pista-list .deportes-asociados li {
  font-size: 0.9em;
  color: #333;
}


.pista-list, .deporte-list {
  max-height: 400px;
  overflow-y: auto;
  margin-bottom: 20px;
}


@media (max-width: 768px) {
  .deporte-list li, .pista-list li {
    width: calc(50% - 20px); 
  }
}


@media (max-width: 480px) {
  .deporte-list li, .pista-list li {
    width: 100%; 
  }
}
</style>
