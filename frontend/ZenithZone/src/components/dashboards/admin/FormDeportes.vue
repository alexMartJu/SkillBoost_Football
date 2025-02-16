<template>
  <div class="container py-4">
    <div class="card shadow-sm border-0 bg-white">
      <div class="card-body p-4">
        <h2 class="display-6 text-center mb-4">{{ formMode === 'create' ? 'Crear Deporte' : 'Editar Deporte' }}</h2>
        <form @submit.prevent="formMode === 'create' ? crearDeporte() : actualizarDeporte()" class="needs-validation">
          <div class="mb-3">
            <input 
              v-model="nuevoDeporte.nombre" 
              class="form-control form-control-lg" 
              placeholder="Nombre del deporte" 
              required 
            />
          </div>
          <div class="mb-4">
            <input 
              v-model="nuevoDeporte.images" 
              class="form-control form-control-lg" 
              placeholder="URL de la imagen" 
            />
          </div>
          <div class="d-grid gap-2 col-lg-6 mx-auto">
            <button type="submit" class="btn btn-primary btn-lg">
              <i :class="formMode === 'create' ? 'bi bi-plus-circle' : 'bi bi-pencil-square'"></i>
              {{ formMode === 'create' ? 'Crear Deporte' : 'Actualizar Deporte' }}
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'FormDeportes.vue',
  props: {
    formMode: {
      type: String,
      required: true
    },
    currentDeporteSlug: {
      type: String,
      required: false
    },
    deporteData: { 
      type: Object,
      required: false
    }
  },
  watch: {
    deporteData: {  
      immediate: true,
      handler(newData){
        if (newData) {
          console.log("newdata"+JSON.stringify(newData));
          this.nuevoDeporte.nombre = newData.nombre
          this.nuevoDeporte.images = newData.images && newData.images.length > 0 ? newData.images[0].image_url : '';
        }
      }
    }
  },
  data() {
    return {
      nuevoDeporte: { nombre: '' }
    };
  },
  methods: {
    async crearDeporte() {
      if (this.nuevoDeporte.nombre) {
        try {
          await this.$store.dispatch('adminDashboard/CreateOneDeporte', this.nuevoDeporte);
          this.nuevoDeporte.nombre = '';
        } catch (error) {
          console.error('Error creando deporte:', error);
        }
      }
    },
    async actualizarDeporte() {
      if (this.nuevoDeporte.nombre && this.currentDeporteSlug) {
        try {
          await this.$store.dispatch('adminDashboard/UpdateOneDeporte', { slug: this.currentDeporteSlug, data: this.nuevoDeporte });
          this.nuevoDeporte.nombre = ''; 
          this.$router.push('/dashboardAdmin/listar');
        } catch (error) {
          console.error('Error actualizando deporte:', error);
        }
      }
    }
  }
};
</script>

<style scoped>
.card {
  transition: all 0.3s ease;
}

.card:hover {
  transform: translateY(-2px);
  box-shadow: 0 .5rem 1rem rgba(0,0,0,.15)!important;
}

.form-control:focus {
  border-color: var(--bs-primary);
  box-shadow: 0 0 0 0.25rem rgba(var(--bs-primary-rgb), 0.25);
}

.btn-primary {
  transition: all 0.3s ease;
}

.btn-primary:hover {
  transform: translateY(-1px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}
</style>