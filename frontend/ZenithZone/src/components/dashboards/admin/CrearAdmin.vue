<template>
    <div class="create-admin">
      <!-- Deporte Form -->
      <FormDeportes v-if="formMode === 'create' || currentDeporteSlug"
                   :formMode="formMode"
                   :currentDeporteSlug="currentDeporteSlug" />
      
      <!-- Pista Form -->
      <FormPistas v-if="formMode === 'create' || currentPistaSlug"
                 :formMode="formMode"
                 :currentPistaSlug="currentPistaSlug"
                 :deportes="deportes" 
                 :pistaData="pistaData"/>
    </div>
  </template>
  
  <script>
  import { mapGetters } from 'vuex';
  import FormDeportes from './FormDeportes.vue';
  import FormPistas from './FormPistas.vue';
  
  export default {
    name: 'CrearAdmin',
    components: {
      FormDeportes,
      FormPistas
    },
    data() {
      return {
        formMode: 'create', // Indica si estamos en modo creación o actualización
        currentDeporteSlug: null,
        currentPistaSlug: null,
        pistaData: {}
      };
    },
    computed: {
      ...mapGetters({
        deportes: 'adminDashboard/GetDeportes'
      })
    },
    created() {
      this.checkSlug();
    },
    watch: {
      '$route.params.slug': 'checkSlug'
    },
    methods: {
      checkSlug() {
        if (this.$route.params.slug) {
            console.log(this.$route.params.slug+"slug");
          this.currentPistaSlug = this.$route.params.slug;
          this.formMode = 'edit';  // Switch to edit mode
          this.loadPistaData(this.currentPistaSlug);
        }
      },
      async loadPistaData(slug) {
        try {
          const pistaData = await this.$store.dispatch('adminDashboard/FetchPistaBySlug', slug);
          if (pistaData) {
            // this.currentPistaSlug = pistaData.slug;
            this.pistaData = pistaData;
          }
        } catch (error) {
          console.error('Error fetching pista data:', error);
        }
      }
    }
  };
  </script>

  <!-- <template>
    <div class="create-admin">
      
      <div v-if="formMode === 'create' || currentDeporteSlug">
        <h2>{{ formMode === 'create' ? 'Crear Deporte' : 'Editar Deporte' }}</h2>
        <form @submit.prevent="formMode === 'create' ? crearDeporte() : actualizarDeporte()">
          <input v-model="nuevoDeporte.nombre" placeholder="Nombre del deporte" required />
          <button type="submit">{{ formMode === 'create' ? 'Crear Deporte' : 'Actualizar Deporte' }}</button>
        </form>
      </div>
  
      
      <div v-if="formMode === 'create' || currentPistaSlug">
        <h2>{{ formMode === 'create' ? 'Crear Pista' : 'Editar Pista' }}</h2>
        <form @submit.prevent="formMode === 'create' ? crearPista() : actualizarPista()">
          <input v-model="currentPista.nombre" placeholder="Nombre de la pista" required />
          <h3>Seleccione los deportes asociados:</h3>
          <div class="checkbox-group">
            <div v-for="deporte in deportes" :key="deporte.id">
              <input type="checkbox" :value="deporte.id" v-model="currentPista.deportes" />
              <label>{{ deporte.nombre }}</label>
            </div>
          </div>
          <button type="submit">{{ formMode === 'create' ? 'Crear Pista' : 'Actualizar Pista' }}</button>
        </form>
      </div>
    </div>
  </template> -->
  
  <!-- <script> -->
<!-- //   import { mapGetters } from 'vuex';
  
//   export default {
//     name: "CrearAdmin",
//     data() {
//       return {
//         formMode: 'create', // Indica si estamos en modo creación o actualización
//         nuevoDeporte: { nombre: '' },
//         currentPista: { nombre: '', deportes: [] },
//         currentDeporteSlug: null,
//         currentPistaSlug: null,
//       };
//     },
//     computed: {
//       ...mapGetters({
//         deportes: 'adminDashboard/GetDeportes',
//         currentPista: 'adminDashboard/GetCurrentPista',
//       }),
//     },
//     created() {
//       // Dispatch action to load deportes on component mount
//       this.$store.dispatch('adminDashboard/InitializeDeporte');
//       this.checkSlug();
//     },
//     watch: {
//       // Watch the route changes to check for slug changes
//       '$route.params.slug': 'checkSlug',
//     },
//     methods: {
//       checkSlug() {
//         console.log("hai "+this.$route.params.slug);
//         // Check if the slug exists in the route params for "editar"
//         if (this.$route.params.slug) {
//           this.currentPistaSlug = this.$route.params.slug;
//           this.formMode = 'edit';  // Switch to edit mode
//           this.loadPistaData(this.currentPistaSlug);
//         }
//       },
//       async loadPistaData(slug) {
//         // Fetch the pista data based on the slug
//         console.log("slug "+slug);
//         try {
//           const pistaData = await this.$store.dispatch('adminDashboard/FetchPistaBySlug', slug);
//           console.log("pistadata"+pistaData);
//           if (pistaData) {
//             console.log("hola"+pistaData);
//             this.currentPista = {
//                 ...pistaData,
//                 deportes: pistaData.deportes.map(deporte => deporte.id) // Mapea solo los ids de los deportes
//             }; // Populate the form with existing data
//             this.currentPistaSlug = pistaData.slug;
//           }
//         } catch (error) {
//             console.log("error");
//           console.error('Error fetching pista data:', error);
//         }
//       },
//       async crearDeporte() {
//         if (this.nuevoDeporte.nombre) {
//           try {
//             await this.$store.dispatch('adminDashboard/CreateOneDeporte', this.nuevoDeporte);
//             this.nuevoDeporte.nombre = ''; // Reset form after submission
//             this.$store.dispatch('adminDashboard/InitializeDeporte');
//           } catch (error) {
//             console.error('Error creando deporte:', error);
//           }
//         }
//       },
  
//       async actualizarDeporte() {
//         if (this.nuevoDeporte.nombre && this.currentDeporteSlug) {
//           try {
//             await this.$store.dispatch('adminDashboard/UpdateOneDeporte', { slug: this.currentDeporteSlug, data: this.nuevoDeporte });
//             this.nuevoDeporte.nombre = ''; // Reset form
//             this.currentDeporteSlug = null;
//             this.formMode = 'create'; // Switch to create mode after update
//             this.$store.dispatch('adminDashboard/InitializeDeporte');
//           } catch (error) {
//             console.error('Error actualizando deporte:', error);
//           }
//         }
//       },
  
//       async crearPista() {
//         if (this.nuevaPista.nombre) {
//           try {
//             await this.$store.dispatch('adminDashboard/CreateOnePista', this.nuevaPista);
//             this.currentPista.nombre = '';  // Reset form after submission
//             this.currentPista.deportes = []; // Clear selected sports
//             this.$store.dispatch('adminDashboard/InitializePista');
//           } catch (error) {
//             console.error('Error creando pista:', error);
//           }
//         }
//       },
  
//       async actualizarPista() {
//         if (this.currentPista.nombre && this.currentPistaSlug) {
//           try {
//             await this.$store.dispatch('adminDashboard/UpdateOnePista', { slug: this.currentPistaSlug, data: this.nuevaPista });
//             this.currentPista.nombre = '';  // Reset form after submission
//             this.currentPista.deportes = []; // Clear selected sports
//             this.currentPistaSlug = null;
//             this.formMode = 'create'; // Switch to create mode after update
//             this.$store.dispatch('adminDashboard/InitializePista');
//           } catch (error) {
//             console.error('Error actualizando pista:', error);
//           }
//         }
//       },
  
//       editarDeporte(deporte) {
//         this.formMode = 'edit'; // Switch to edit mode
//         this.nuevoDeporte = { ...deporte }; // Pre-fill form with existing data
//         this.currentDeporteSlug = deporte.slug;
//       },
  
//       editarPista(pista) {
//         this.formMode = 'edit'; // Switch to edit mode
//         this.nuevaPista = { ...pista }; // Pre-fill form with existing data
//         this.currentPistaSlug = pista.slug;
//       },
  
//       eliminarDeporte(deporteSlug) {
//         if (confirm('¿Estás seguro de que deseas eliminar este deporte?')) {
//           this.$store.dispatch('adminDashboard/DeleteDeporte', deporteSlug);
//         }
//       },
  
//       eliminarPista(pistaSlug) {
//         if (confirm('¿Estás seguro de que deseas eliminar esta pista?')) {
//           this.$store.dispatch('adminDashboard/DeletePista', pistaSlug);
//         }
//       },
//     },
//   }; -->
  <!-- </script> -->
  
  <!-- <style scoped> -->
  <!-- /* .create-admin {
    padding: 20px;
    max-width: 800px;
    margin: 0 auto;
    font-family: Arial, sans-serif;
  }
  
  h2 {
    font-size: 2em;
    color: #333;
    margin-bottom: 20px;
  }
  
  form {
    display: flex;
    flex-direction: column;
    gap: 20px;
  }
  
  input {
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 1em;
  }
  
  button {
    padding: 10px 20px;
    background-color: #4CAF50;
    color: white;
    border: none;
    border-radius: 5px;
    font-size: 1em;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }
  
  button:hover {
    background-color: #45a049;
  }
  
  
  .checkbox-group {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(200px, 1fr)); 
    gap: 15px;
    padding: 10px 0;
  }
  
  .checkbox-group label {
    font-size: 1em;
    color: #333;
  }
  
  .checkbox-group input {
    margin-right: 10px;
  }
  
  .checkbox-group div {
    display: flex;
    align-items: center;
    gap: 10px;
  } */ -->
  <!-- </style> -->