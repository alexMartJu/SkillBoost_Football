<template>
    <div class="card-entrenador">
      <div class="entrenador-info">
        <img 
          v-if="entrenador.imagenes.length > 0"
          :src="`/assets/entrenadores/${entrenador.imagenes[0].image_url}`" 
          alt="Imagen del entrenador" 
          class="entrenador-imagen" 
        />
        <div class="entrenador-detalles">
          <h3>{{ entrenador.nombre }} {{ entrenador.apellidos }}</h3>
          <p>{{ entrenador.email }}</p>
        </div>
      </div>
      <button @click="confirmarEliminar(entrenador.numeroEntrenador)">Eliminar</button>
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
.card-entrenador {
  background-color: #f8f8f8;
  border-radius: 8px;
  padding: 10px;
  margin: 10px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.entrenador-info {
  display: flex;
  align-items: center;
  justify-content: flex-start;
}

.entrenador-imagen {
  width: 40px; /* Imagen muy pequeña */
  height: 40px; /* Imagen muy pequeña */
  border-radius: 50%; /* Imagen circular */
  object-fit: cover; /* Ajuste para que la imagen se vea bien */
  margin-right: 15px; /* Espacio entre la imagen y el texto */
}

.entrenador-detalles h3 {
  font-size: 1rem;
  color: #333;
  margin: 0;
}

.entrenador-detalles p {
  color: #777;
  font-size: 0.9rem;
  margin: 5px 0 0 0;
}

button {
  background-color: #ff5b5b;
  color: white;
  border: none;
  padding: 8px 16px;
  border-radius: 5px;
  cursor: pointer;
  font-size: 1rem;
}

button:hover {
  background-color: #ff4040;
}
</style>    