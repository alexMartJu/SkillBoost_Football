
  <template>
    <div class="admin-dashboard">
      <h1>Dashboard Admin</h1>
  
      <!-- Crear deporte -->
      <div class="create-form">
        <h2>Crear Deporte</h2>
        <form @submit.prevent="crearDeporte">
          <input v-model="nuevoDeporte.nombre" placeholder="Nombre del deporte" required />
          <button type="submit">Crear Deporte</button>
        </form>
      </div>
  
      <!-- Mostrar deportes -->
      <div v-if="deportes && deportes.length > 0">
        <h2>Deportes</h2>
        <DeporteList :deportes="deportes" />
      </div>
      <div v-else>
        <p>No hay deportes disponibles.</p>
      </div>
  
      <!-- Crear pista -->
      <div class="create-form">
        <h2>Crear Pista</h2>
        <form @submit.prevent="crearPista">
          <input v-model="nuevaPista.nombre" placeholder="Nombre de la pista" required />

          <h3>Seleccione los deportes asociados:</h3>
        <div v-for="deporte in deportes" :key="deporte.id">
          <input
            type="checkbox"
            :value="deporte.id"
            v-model="nuevaPista.deportes"
          />
          <label>{{ deporte.nombre }}</label>
        </div>
          <button type="submit">Crear Pista</button>
        </form>
      </div>
  
      <!-- Mostrar pistas -->
      <div v-if="pistas && pistas.length > 0">
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
import DeporteList from '@/components/dashboards/admin/DeporteList.vue';
import PistaList from '@/components/dashboards/admin/PistaList.vue';

export default {
  name: 'DashboardAdmin',
  components: {
    DeporteList,
    PistaList,
  },
  data() {
    return {
      // Datos para los formularios de creación
      nuevoDeporte: {
        nombre: '',
      },
      nuevaPista: {
        nombre: '',
        deportes: []
      },
    };
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
    this.$store.dispatch('adminDashboard/InitializePista');

    console.log('Deportes en Vuex:', this.deportes);
    console.log('Pistas en Vuex:', this.pistas);
  },
  methods: {
    // Método para crear un nuevo deporte
    async crearDeporte() {
      if (this.nuevoDeporte.nombre) {
        try {
          console.log(this.nuevoDeporte.nombre);
          await this.$store.dispatch('adminDashboard/CreateOneDeporte', this.nuevoDeporte);
          this.nuevoDeporte.nombre = ''; 
          this.$store.dispatch('adminDashboard/InitializeDeporte');
        } catch (error) {
          console.error('Error creando deporte:', error);
        }
      }
    },
    // Método para crear una nueva pista
    async crearPista() {
      if (this.nuevaPista.nombre) {
        try {
          await this.$store.dispatch('adminDashboard/CreateOnePista', this.nuevaPista);
          this.nuevaPista.nombre = '';  // Limpiar el formulario
          this.nuevaPista.deportes = [];  
          this.$store.dispatch('adminDashboard/InitializePista');
        } catch (error) {
          console.error('Error creando pista:', error);
        }
      }
    },
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
  