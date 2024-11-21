<!-- components/d shboards/admin/CrearAdmin.vue -->
<template>
    <div class= "create-admin">
      <h2>Crear Deporte</h2>
      <form @submit.prevent="crearDeporte">
        <input v-model="nuevoDeporte.nombre" placeholder="Nombre del deporte" required />
        <button type="submit">Crear Deporte</button>
      </form>
  
      <h2>Crear Pista</h2>
      <form @submit.prevent="crearPista">
        <input v-model="nuevaPista.nombre" placeholder="Nombre de la pista" required />
        <h3>Seleccione los deportes asociados:</h3>

        <div class="checkbox-group">
            <div v-for="deporte in deportes" :key="deporte.id">
                <input type="checkbox" :value="deporte.id" v-model="nuevaPista.deportes" />
                <label>{{ deporte.nombre }}</label>
            </div>
        </div>
        <button type="submit">Crear Pista</button>
      </form>
    </div>
  </template>
  
  <script>
  import { mapGetters } from 'vuex';
  
  export default {
    name: "CrearAdmin",
    data() {
      return {
        nuevoDeporte: { nombre: '' },
        nuevaPista: { nombre: '', deportes: [] },
      };
    },
    computed: {
      ...mapGetters({
        deportes: 'adminDashboard/GetDeportes',
      }),
    },
    created() {
      this.$store.dispatch('adminDashboard/InitializeDeporte');
    },
    methods: {
      async crearDeporte() {
        if (this.nuevoDeporte.nombre) {
          try {
            await this.$store.dispatch('adminDashboard/CreateOneDeporte', this.nuevoDeporte);
            this.nuevoDeporte.nombre = ''; 
            this.$store.dispatch('adminDashboard/InitializeDeporte');
          } catch (error) {
            console.error('Error creando deporte:', error);
          }
        }
      },
      async crearPista() {
        if (this.nuevaPista.nombre) {
          try {
            await this.$store.dispatch('adminDashboard/CreateOnePista', this.nuevaPista);
            this.nuevaPista.nombre = '';  
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
  .create-admin {
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
  
  /* Estilos para el grupo de checkboxes usando CSS Grid */
  .checkbox-group {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(200px, 1fr)); /* Cada columna tendrá al menos 200px y se ajustará automáticamente */
    gap: 15px; /* Espaciado entre los checkboxes */
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
  }
  </style>