<!-- <template>
    <div class="admin-dashboard">
      <h1>Dashboard Admin</h1>
  
      
      <div v-if="deportes.length > 0">
        <h2>Deportes</h2>
        <DeporteList :deportes="deportes" />
      </div>

      <div v-else>
        <p>No hay deportes disponibles.</p>
      </div>
  
     
      <div v-if="pistas.length > 0">
        <h2>Pistas</h2>
        <PistaList :pistas="pistas" />
      </div>
      <div v-else>
        <p>No hay pistas disponibles.</p>
      </div>
    </div>
  </template>
   -->
   <!-- <template>
    <div class="admin-dashboard">
      <h1>Dashboard Admin</h1>
  
      <div v-if="deportes.data.length > 0">
        <h2>Deportes</h2>
        <DeporteList :deportes="deportes" />
      </div>
      <div v-else>
        <p>No hay deportes disponibles.</p>
      </div>
  
      <div v-if="pistas.length > 0">
        <h2>Pistas</h2>
        <PistaList :pistas="pistas" />
      </div>
      <div v-else>
        <p>No hay pistas disponibles.</p>
      </div>
    </div>
  </template> -->

  <template>
    <div class="admin-dashboard">
      <h1>Dashboard Admin</h1>
  
      <!-- Verificar si 'deportes' y 'deportes.data' existen y si hay elementos -->
      <div v-if="deportes && deportes.data && deportes.data.length > 0">
        <h2>Deportes</h2>
        <DeporteList :deportes="deportes.data" />
      </div>
      <div v-else>
        <p>No hay deportes disponibles.</p>
      </div>
  
      <div v-if="pistas.length > 0">
        <h2>Pistas</h2>
        <PistaList :pistas="pistas" />
      </div>
      <div v-else>
        <p>No hay pistas disponibles.</p>
      </div>
    </div>
  </template>
  <script>
  import { mapGetters } from 'vuex';
  import DeporteList from '@/components/admin/DeporteList.vue';
  import PistaList from '@/components/admin/PistaList.vue';
  
  export default {
    name: 'DashboardAdmin',
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
      console.log(this.$store.state.adminDashboard); 
      this.$store.dispatch('adminDashboard/InitializeDeporte');
      this.$store.dispatch('adminDashboard/INITIALIZE_PISTA');

      // console.log("deportes"+this.deportes.data.length);
      console.log('Deportes en Vuex:', this.deportes);
      console.log('Pistas en Vuex:', this.pistas);
    },
  };
  </script>
  
  <style scoped>
  .admin-dashboard {
    padding: 20px;
  }
  
  h1 {
    margin-bottom: 20px;
  }
  </style>
  