<template>
    <div class="card shadow-lg border-0 mb-3">
      <div class="card-body d-flex justify-content-between align-items-center p-4">
        <div class="d-flex align-items-center">
          <div>
            <img 
              v-if="entrenador.imagenes.length > 0"
              :src="`/assets/entrenadores/${entrenador.imagenes[0].image_url}`" 
              alt="Imagen del entrenador" 
              class="rounded-circle me-3 border border-white shadow-sm" 
              style="width: 80px; height: 80px; object-fit: cover;"
            />
          </div>
          <div>
            <h6 class="mb-0 fw-bold text-dark fs-5">{{ entrenador.nombre }} {{ entrenador.apellidos }}</h6>
            <small class="text-muted opacity-75">{{ entrenador.email }}</small>
          </div>
        </div>
        <button 
          @click="confirmarEliminar(entrenador.numeroentrenador)" 
          class="btn btn-danger btn-sm rounded-3 px-3 py-2"
        >
          <i class="bi bi-trash"></i>
        </button>
      </div>
    </div>
</template>
  
<script>
export default {
  name: 'CardEntrenadores',
  props: {
    entrenador: Object,
  },
  methods: {
    confirmarEliminar(id) {
      this.$emit('eliminarEntrenador', id);
    },
  },
};
</script>

<style scoped>
.card {
  border-radius: 15px;
  transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
}

.card:hover {
  transform: translateY(-5px);
  box-shadow: 0 1rem 3rem rgba(0,0,0,.175)!important;
}

.btn-danger {
  transition: transform 0.3s ease;
}

.btn-danger:hover {
  transform: scale(1.05);
}
</style>