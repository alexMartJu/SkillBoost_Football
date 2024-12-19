<template>
  <div class="card-entrenamiento">
    <div class="entrenamiento-info">
      <div class="entrenamiento-detalles">
        <h3>{{ entrenamiento.nombre }}</h3>
        <p><strong>Entrenador:</strong> {{ entrenamiento.entrenador.nombre }} {{ entrenamiento.entrenador.apellidos }}</p>
        <p><strong>Estado:</strong> 
          <span :class="{
              'text-success': entrenamiento.estado === 'Aceptado',
              'text-danger': entrenamiento.estado === 'Denegado',
              'text-warning': entrenamiento.estado === 'Pendiente'
          }">
            {{ entrenamiento.estado }}
          </span>
        </p>
        <!-- Descripción del entrenamiento, que estará oculta inicialmente -->
        <div v-if="mostrarDescripcion" class="descripcion">
          <p>{{ entrenamiento.descripcion }}</p>
        </div>
        <button @click="toggleDescripcion">
          {{ mostrarDescripcion ? 'Ocultar descripción' : 'Ver descripción' }}
        </button>
      </div>
    </div>

    <div class="botones">
      <!-- Botones de acción para aceptar, denegar y borrar -->
      <button @click="cambiarEstado('Aceptado')" :disabled="entrenamiento.estado !== 'Pendiente'">Aceptar</button>
      <button @click="cambiarEstado('Denegado')" :disabled="entrenamiento.estado !== 'Pendiente'">Denegar</button>

      <!-- Botón de Borrar (comentado) -->
      <!-- <button @click="confirmarEliminar">Borrar</button> -->
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
      cambiarEstado(estado) {
        this.$emit('cambiarEstado', this.entrenamiento.id, estado);
      },
      // Emitir el evento para eliminar el entrenamiento
      confirmarEliminar() {
        this.$emit('eliminarEntrenamiento', this.entrenamiento.id);
      },
      // Método para alternar la visibilidad de la descripción
      toggleDescripcion() {
        this.mostrarDescripcion = !this.mostrarDescripcion;
      },
    },
  };
  </script>
  
 
<style scoped>
.card-entrenamiento {
  background-color: #f8f8f8;
  border-radius: 8px;
  padding: 15px;
  margin: 10px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  display: flex;
  flex-direction: column;
  gap: 10px;
}

.entrenamiento-info {
  display: flex;
  flex-direction: column;
}

.entrenamiento-detalles h3 {
  font-size: 1.2rem;
  color: #333;
  margin: 0;
}

.entrenamiento-detalles p {
  color: #777;
  font-size: 1rem;
}

.descripcion {
  margin-top: 10px;
  font-size: 0.9rem;
  color: #333;
}

.botones {
  display: flex;
  gap: 10px;
}

button {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 8px 16px;
  border-radius: 5px;
  cursor: pointer;
  font-size: 1rem;
}

button:hover {
  background-color: #0056b3;
}

button:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}
</style>