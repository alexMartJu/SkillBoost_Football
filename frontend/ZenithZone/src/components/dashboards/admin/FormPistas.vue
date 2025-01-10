<template>
    <div>
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
  </template>
  
  <script>
  export default {
    name: 'FormPistas',
    props: {
      formMode: {
        type: String,
        required: true
      },
      currentPistaSlug: {
        type: String,
        required: false
      },
      deportes: {
        type: Array,
        required: true
      },
      pistaData: { 
      type: Object,
      required: false
    }
    },watch: {
    pistaData: {  
      immediate: true,
      handler(newData) {
        console.log("newData"+JSON.stringify(newData.nombre));
        if (newData) {
            console.log("newData"+newData);
            if (Array.isArray(newData.deportes)) {
            this.currentPista.nombre =newData.nombre;
            this.currentPista.deportes = newData.deportes.map(deporte => deporte.id);
          }
        }
      }
    }
  },

    data() {
      return {
        currentPista: { nombre: '', deportes: [] }
      };
    },
    methods: {
      async crearPista() {
        if (this.currentPista.nombre) {
          try {
            await this.$store.dispatch('adminDashboard/CreateOnePista', this.currentPista);
            this.currentPista.nombre = '';  // Reset form after submission
            this.currentPista.deportes = []; // Clear selected sports
          } catch (error) {
            console.error('Error creando pista:', error);
          }
        }
      },
  
      async actualizarPista() {
        if (this.currentPista.nombre && this.currentPistaSlug) {
          try {
            await this.$store.dispatch('adminDashboard/UpdateOnePista', { slug: this.currentPistaSlug, data: this.currentPista });
            this.currentPista.nombre = '';  // Reset form after submission
            this.currentPista.deportes = []; // Clear selected sports
            this.$router.push('/dashboardAdmin/listar');
          } catch (error) {
            console.error('Error actualizando pista:', error);
          }
        }
      }
    }
  };
  </script>
<style scoped>
/* General container layout */
div {
  max-width: 700px;
  margin: 0 auto;
  padding: 20px;
}

/* Header styling */
h2 {
  font-size: 1.8em;
  margin-bottom: 15px;
  color: #333;
  text-align: center;
}

/* Form field styling */
form {
  display: flex;
  flex-direction: column;
}

input[type="text"] {
  padding: 10px;
  margin-bottom: 20px;
  font-size: 1em;
  width: 100%;
  box-sizing: border-box;
  border: 1px solid #ddd;
  border-radius: 4px;
}

/* Section for sports selection */
h3 {
  font-size: 1.2em;
  margin-top: 20px;
  margin-bottom: 10px;
  color: #555;
}

.checkbox-group {
  display: flex;
  flex-wrap: wrap;
  gap: 55px; 
} 

.checkbox-group div {
  display: flex;
  align-items: center;
  width: 18%; 
}

input[type="checkbox"] {
  margin-right: 8px;
  transform: scale(1.2); 
}


button[type="submit"] {
  padding: 12px;
  font-size: 1em;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  margin-top: 20px;
}

button[type="submit"]:hover {
  background-color: #0056b3;
}
</style>
