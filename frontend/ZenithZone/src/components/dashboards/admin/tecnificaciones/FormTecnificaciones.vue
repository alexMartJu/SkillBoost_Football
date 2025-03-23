<template>
  <div class="container py-4">
    <div class="card shadow-sm border-0 bg-white">
      <div class="card-body p-4">
        <h2 class="display-6 text-center mb-4">{{ isEditing ? 'Editar Tecnificación' : 'Crear Tecnificación' }}</h2>

        <div v-if="loading" class="text-center py-3">
          <div class="spinner-border text-primary" role="status">
            <span class="visually-hidden">Cargando...</span>
          </div>
        </div>

        <form v-else @submit.prevent="submitForm" class="needs-validation">
          <div class="mb-3">
            <label for="nombre" class="form-label">Nombre <span class="text-danger">*</span></label>
            <input v-model="tecnificacion.nombre" id="nombre" class="form-control form-control-lg"
              :class="{ 'is-invalid': v$.tecnificacion.nombre.$error }" placeholder="Nombre de la tecnificación"
              @blur="v$.tecnificacion.nombre.$touch()" />
            <div class="invalid-feedback" v-if="v$.tecnificacion.nombre.$error">
              <span v-if="v$.tecnificacion.nombre.required.$invalid">El nombre es obligatorio.</span>
              <span v-else-if="v$.tecnificacion.nombre.minLength.$invalid">El nombre debe tener al menos 3
                caracteres.</span>
              <span v-else-if="v$.tecnificacion.nombre.maxLength.$invalid">El nombre no puede exceder los 100
                caracteres.</span>
            </div>
          </div>

          <div class="mb-4">
            <label for="descripcion" class="form-label">Descripción</label>
            <textarea v-model="tecnificacion.descripcion" id="descripcion" class="form-control form-control-lg"
              :class="{ 'is-invalid': v$.tecnificacion.descripcion.$error }" placeholder="Descripción de la tecnificación"
              rows="4" @blur="v$.tecnificacion.descripcion.$touch()"></textarea>
            <div class="invalid-feedback" v-if="v$.tecnificacion.descripcion.$error">
              <span v-if="v$.tecnificacion.descripcion.maxLength.$invalid">La descripción no puede exceder los 1000
                caracteres.</span>
            </div>
          </div>

          <div class="d-grid gap-2 col-lg-6 mx-auto">
            <button type="submit" class="btn btn-primary btn-lg" :disabled="submitting">
              <i :class="isEditing ? 'bi bi-pencil-square' : 'bi bi-plus-circle'"></i>
              {{ isEditing ? 'Actualizar Tecnificación' : 'Crear Tecnificación' }}
            </button>
            <button type="button" @click="cancelar" class="btn btn-outline-secondary btn-lg">
              <i class="bi bi-x-circle"></i> Cancelar
            </button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import { useVuelidate } from '@vuelidate/core';
import { required, minLength, maxLength } from '@vuelidate/validators';

export default {
  name: 'FormTecnificaciones',
  props: {
    slug: {
      type: String,
      required: false
    }
  },
  data() {
    return {
      tecnificacion: {
        nombre: '',
        descripcion: ''
      },
      loading: false,
      submitting: false,
      v$: useVuelidate()
    };
  },
  validations() {
    return {
      tecnificacion: {
        nombre: {
          required,
          minLength: minLength(3),
          maxLength: maxLength(100)
        },
        descripcion: {
          maxLength: maxLength(1000)
        }
      }
    };
  },
  computed: {
    isEditing() {
      return !!this.slug;
    }
  },
  created() {
    if (this.isEditing) {
      this.cargarTecnificacion();
    }
  },
  methods: {
    cargarTecnificacion() {
      this.loading = true;
      this.$store.dispatch('adminDashboard/FetchTecnificacionBySlugAdmin', this.slug)
        .then(data => {
          if (data) {
            this.tecnificacion = {
              nombre: data.nombre,
              descripcion: data.descripcion
            };
          }
          this.loading = false;
        })
        .catch(error => {
          console.error("Error al cargar la tecnificación:", error);
          this.loading = false;
          this.$router.push('/dashboardAdmin/tecnificaciones');
        });
    },
    async submitForm() {
      const isFormValid = await this.v$.$validate();
      if (!isFormValid) {
        return;
      }

      this.submitting = true;

      const action = this.isEditing
        ? 'adminDashboard/UpdateOneTecnificacionAdmin'
        : 'adminDashboard/CreateOneTecnificacionAdmin';

      const payload = this.isEditing
        ? { slug: this.slug, data: this.tecnificacion }
        : this.tecnificacion;

      this.$store.dispatch(action, payload)
        .then(() => {
          this.submitting = false;
          this.$router.push('/dashboardAdmin/tecnificaciones');
        })
        .catch(error => {
          console.error(`Error al ${this.isEditing ? 'actualizar' : 'crear'} la tecnificación:`, error);
          this.submitting = false;

          // Manejar errores del servidor
          if (error.response && error.response.data) {
            const serverErrors = error.response.data;
            // Aquí podrías manejar los errores específicos del servidor
            console.error("Errores del servidor:", serverErrors);
          }
        });
    },
    cancelar() {
      this.$router.push('/dashboardAdmin/tecnificaciones');
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
  box-shadow: 0 .5rem 1rem rgba(0, 0, 0, .15) !important;
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

.invalid-feedback {
  display: block;
}
</style>
