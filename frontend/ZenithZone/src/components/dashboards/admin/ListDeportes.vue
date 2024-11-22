<!-- <template>
  <div>
    <ul class="deporte-list">
      <li v-for="deporte in deportes" :key="deporte.id">
        <p>{{ deporte.nombre }}</p>
        <button @click="editarDeporte(deporte)">Editar</button>
        <button @click="eliminarDeporte(deporte.id)">Eliminar</button>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'ListDeportes',
  props: {
    deportes: Array,
  },
  methods: {
    editarDeporte(deporte) {
      this.$emit('editarDeporte', deporte); 
    },
    eliminarDeporte(deporteId) {
      this.$emit('eliminarDeporte', deporteId); 
    },
  },
};
</script>
  
<style scoped>
.deporte-list {
  padding: 0;
  list-style-type: none;
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
}

.deporte-list li {
  background-color: #fff;
  padding: 10px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  width: calc(33% - 20px);
  transition: transform 0.2s ease, box-shadow 0.2s ease;
  cursor: pointer;
}

.deporte-list li:hover {
  transform: translateY(-5px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

/* Responsividad */
@media (max-width: 768px) {
  .deporte-list li {
    width: calc(50% - 20px); /* Dos columnas en pantallas medianas */
  }
}

@media (max-width: 480px) {
  .deporte-list li {
    width: 100%; /* Una columna en pantallas muy pequeñas */
  }
}
</style> -->

<template>
  <div class="deporte-list">
    <CardDeportes
      v-for="deporte in deportes"
      :key="deporte.id"
      :deporte="deporte"
      @editarDeporte="editarDeporte"
      @eliminarDeporte="eliminarDeporte"
    />
  </div>
</template>

<script>
import CardDeportes from './CardDeportes.vue'; 

export default {
  name: 'ListDeportes',
  components: {
    CardDeportes
  },
  props: {
    deportes: Array,
  },
  methods: {
    editarDeporte(deporte) {
      this.$emit('editarDeporte', deporte); 
    },
    eliminarDeporte(deporteId) {
      this.$emit('eliminarDeporte', deporteId); 
    },
  },
};
</script>

<style scoped>
/* Estilo para listas de deportes */
.deporte-list {
  margin-top: 30px;
  padding: 0;
  list-style-type: none;
  display: flex;
  flex-wrap: wrap; /* Permite que los elementos se ajusten en varias filas */
  gap: 20px; /* Espaciado entre los elementos */
}

.deporte-list li {
  background-color: #fff;
  padding: 15px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  width: calc(33.33% - 20px); /* 3 elementos por fila en pantallas grandes */
  transition: transform 0.2s ease, box-shadow 0.2s ease;
  cursor: pointer;
}

.deporte-list li:hover {
  transform: translateY(-5px); /* Efecto de elevación al pasar el mouse */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.deporte-list li p {
  margin: 0;
}

/* Responsividad: 2 columnas en pantallas medianas (menos de 768px) */
@media (max-width: 768px) {
  .deporte-list li {
    width: calc(50% - 20px); /* 2 elementos por fila */
  }
}

/* Responsividad: 1 columna en pantallas pequeñas (menos de 480px) */
@media (max-width: 480px) {
  .deporte-list li {
    width: 100%; /* 1 elemento por fila */
  }
}
</style>