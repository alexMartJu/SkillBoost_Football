<template>
    <div class="card-pista">
      <h3>{{ pista.nombre }}</h3>
      <div v-if="pista.deportes && pista.deportes.length">
        <div class="deportes-asociados">
          <p><strong>Deportes Asociados:</strong></p>
          <ul>
            <li v-for="deporte in pista.deportes" :key="deporte.id">{{ deporte.nombre }}</li>
          </ul>
        </div>
      </div>
      <button @click="editarPista(pista)">Editar</button>
      <button @click="confirmarEliminar(pista.slug)">Eliminar</button>
    </div>
  </template>
  
  <script>
  export default {
    name: 'CardPistas',
    props: {
      pista: Object
    },
    methods: {
      editarPista(pista) {
        console.log("editar", pista);
        this.$router.push({ name: 'adminEditar', params: { slug: pista.slug, type:'pista' } });
      },
      confirmarEliminar(pistaSlug) {
        if (confirm("¿Estás seguro de que deseas eliminar esta pista?")) {
            this.eliminarDeporte(pistaSlug);
        }
        },
        eliminarDeporte(pistaSlug) {
            this.$store.dispatch('adminDashboard/DeteleOnePista', pistaSlug   )
            .then(() => console.log("Deporte eliminado correctamente"))
            .catch(error => console.error("Error al eliminar el deporte:", error));
        }
    }
  };
  </script>
  
  <style scoped>
 /* Estilo para las tarjetas de pista (CardPistas.vue) */
 .card-pista {
  background-color: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  width: 100%; /* Ocupa todo el espacio disponible */
  transition: transform 0.2s ease, box-shadow 0.2s ease;
  cursor: pointer;
}

.card-pista:hover {
  transform: translateY(-5px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.card-pista button {
  margin-top: 10px;
  padding: 5px 10px;
  border: none;
  background-color: #007bff;
  color: white;
  border-radius: 4px;
  cursor: pointer;
}

.card-pista button:hover {
  background-color: #0056b3;
}

.deportes-asociados {
  margin-top: 10px;
  font-size: 1em;
  color: #666;
  padding-left: 20px;
}

.deportes-asociados ul {
  padding-left: 20px;
  list-style-type: none;
}

.deportes-asociados li {
  font-size: 0.9em;
  color: #333;
}
  </style>
  