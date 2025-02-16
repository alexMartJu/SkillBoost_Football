<template>
  <div class="container mt-5">
    <h2 class="display-6 text-center mb-4">{{ formMode === 'create' ? 'Crear Pista' : 'Editar Pista' }}</h2>
    <div class="row justify-content-center">
      <div class="col-md-8">
        <form @submit.prevent="formMode === 'create' ? crearPista() : actualizarPista()" class="shadow-sm p-4 bg-white rounded">
          <div class="mb-4">
            <input v-model="currentPista.nombre" placeholder="Nombre de la pista" required class="form-control form-control-lg" />
          </div>
          
          <h3 class="h5 mb-3">Seleccione los deportes asociados:</h3>
          <div class="row g-3 mb-4">
            <div v-for="deporte in deportes" :key="deporte.id" class="col-md-4">
              <div class="form-check">
                <input class="form-check-input" type="checkbox" :value="deporte.id" v-model="currentPista.deportes" :id="'deporte-'+deporte.id" />
                <label class="form-check-label" :for="'deporte-'+deporte.id">{{ deporte.nombre }}</label>
              </div>
            </div>
          </div>
          
          <button type="submit" class="btn btn-primary btn-lg w-100">
            <i :class="formMode === 'create' ? 'bi bi-plus-circle' : 'bi bi-pencil-square'"></i>
            {{ formMode === 'create' ? 'Crear Pista' : 'Actualizar Pista' }}
          </button>
        </form>
      </div>
    </div>
  </div>
</template>
  
  <script>
  import Constant from '../../../Constant';
  export default {
    name: 'FormPistasPrivadas',
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
            await this.$store.dispatch(`adminDashboard/${Constant.CREATE_ONE_PISTA_PRIVADA}`, this.currentPista);
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
            await this.$store.dispatch(`adminDashboard/${Constant.UPDATE_ONE_PISTA_PRIVADA}`, { slug: this.currentPistaSlug, data: this.currentPista });
            this.currentPista.nombre = '';  // Reset form after submission
            this.currentPista.deportes = []; // Clear selected sports
            this.$router.push('/admin');
          } catch (error) {
            console.error('Error actualizando pista:', error);
          }
        }
      }
    }
  };
  </script>
  
<style scoped>
.form-control:focus {
  border-color: var(--bs-primary);
  box-shadow: 0 0 0 0.25rem rgba(var(--bs-primary-rgb), 0.25);
}

.btn-primary {
  transition: all 0.3s ease;
}

.btn-primary:hover {
  transform: translateY(-1px);
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.form-check-input:checked {
  background-color: var(--bs-primary);
  border-color: var(--bs-primary);
}
</style>
