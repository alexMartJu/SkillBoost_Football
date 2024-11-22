<template>
    <div class="card-deporte">
      <h3>{{ deporte.nombre }}</h3>
      <button @click="editarDeporte(deporte)">Editar</button>
      <button @click="eliminarDeporte(deporte.id)">Eliminar</button>
    </div>
  </template>
  
  <script>
  export default {
    name: 'CardDeportes',
    props: {
      deporte: Object
    },
    methods: {
      editarDeporte(deporte) {
        this.$emit('editarDeporte', deporte);
      },
      eliminarDeporte(deporteId) {
        this.$emit('eliminarDeporte', deporteId);
      }
    }
  };
  </script>
  
  <style scoped>
.card-deporte {
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  transition: transform 0.2s ease, box-shadow 0.2s ease;
  cursor: pointer;
}

.card-deporte:hover {
  transform: translateY(-5px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.card-deporte button {
  margin-top: 10px;
  padding: 5px 10px;
  border: none;
  background-color: #007bff;
  color: white;
  border-radius: 4px;
  cursor: pointer;
}

.card-deporte button:hover {
  background-color: #0056b3;
}
</style>