<template>
  <div class="card h-100 border-0 shadow-sm">
    <div class="card-body">
      <h3 class="h5 card-title text-primary mb-3">{{ entrenamiento.nombre }}</h3>
      
      <div class="mb-3">
        <div class="d-flex align-items-center mb-2">
          <i class="bi bi-person-circle me-2"></i>
          <span class="text-muted">{{ entrenamiento.entrenador.nombre }} {{ entrenamiento.entrenador.apellidos }}</span>
        </div>
        
        <div class="d-flex align-items-center">
          <i class="bi bi-circle-fill me-2" :class="{
            'text-success': entrenamiento.status === 'accepted' || entrenamiento.status === 'completed',
            'text-danger': entrenamiento.status === 'denied',
            'text-warning': entrenamiento.status === 'pending'
          }"></i>
          <span class="text-capitalize">{{ entrenamiento.status }}</span>
        </div>
      </div>

      <div class="collapse" :id="'descripcion-' + entrenamiento.slug">
        <p class="card-text text-muted mb-3">{{ entrenamiento.descripcion }}</p>
      </div>

      <button class="btn btn-link btn-sm text-decoration-none p-0 mb-3" 
              @click="toggleDescripcion" 
              :data-bs-target="'#descripcion-' + entrenamiento.slug"
              data-bs-toggle="collapse">
        {{ mostrarDescripcion ? 'Ocultar detalles' : 'Ver detalles' }}
      </button>

      <div class="d-flex gap-2">
        <button 
          @click="cambiarEstado('accepted')" 
          :disabled="entrenamiento.status !== 'pending'"
          class="btn btn-sm"
          :class="[
            entrenamiento.status === 'pending' ? 'btn-outline-success' : 'btn-success',
            {'disabled': entrenamiento.status !== 'pending'}
          ]">
          <i class="bi bi-check-lg me-1"></i>Aceptar
        </button>

        <button 
          @click="cambiarEstado('denied')" 
          :disabled="entrenamiento.status !== 'pending'"
          class="btn btn-sm"
          :class="[
            entrenamiento.status === 'pending' ? 'btn-outline-danger' : 'btn-danger',
            {'disabled': entrenamiento.status !== 'pending'}
          ]">
          <i class="bi bi-x-lg me-1"></i>Denegar
        </button>
      </div>
    </div>
  </div>
</template>
 
  
  <script>
  export default {
    name: 'CardEntrenamientos',
    props: {
      entrenamiento: Object,
    },
    data() {
      return {
        mostrarDescripcion: false, // Estado para controlar si la descripción está visible o no
      };
    },
    methods: {
      // Emitir el evento para cambiar el estado del entrenamiento
      cambiarEstado(status) {
        console.log(status);    
        //  this.$emit('cambiarEstado', this.entrenamiento.slug, status);
         this.$emit('cambiarEstado', this.entrenamiento.slug, { status: status })
      },
      // Emitir el evento para eliminar el entrenamiento
      confirmarEliminar() {
        this.$emit('eliminarEntrenamiento', this.entrenamiento.slug);
      },
      // Método para alternar la visibilidad de la descripción
      toggleDescripcion() {
        this.mostrarDescripcion = !this.mostrarDescripcion;
      },
    },
  };
  </script>
  
 
<style scoped>
.card {
  transition: transform 0.2s ease-in-out;
}

.card:hover {
  transform: translateY(-2px);
}

.btn-sm {
  padding: 0.4rem 0.8rem;
}

.disabled {
  opacity: 0.65;
  cursor: not-allowed;
}
</style>