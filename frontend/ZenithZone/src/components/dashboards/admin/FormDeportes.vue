<template>
  <div>
    <h2>{{ formMode === 'create' ? 'Crear Deporte' : 'Editar Deporte' }}</h2>
    <form @submit.prevent="formMode === 'create' ? crearDeporte() : actualizarDeporte()">
      <input v-model="nuevoDeporte.nombre" placeholder="Nombre del deporte" required />
      <button type="submit">{{ formMode === 'create' ? 'Crear Deporte' : 'Actualizar Deporte' }}</button>
    </form>
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
          this.nuevoDeporte.nombre = ''; // Reset form after submission
        } catch (error) {
          console.error('Error creando deporte:', error);
        }
      }
    },

    async actualizarDeporte() {
      if (this.nuevoDeporte.nombre && this.currentDeporteSlug) {
        try {
          await this.$store.dispatch('adminDashboard/UpdateOneDeporte', { slug: this.currentDeporteSlug, data: this.nuevoDeporte });
          this.nuevoDeporte.nombre = ''; // Reset form
        } catch (error) {
          console.error('Error actualizando deporte:', error);
        }
      }
    }
  }
};
</script>