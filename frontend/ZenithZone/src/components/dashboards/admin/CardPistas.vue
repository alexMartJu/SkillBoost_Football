<template>
  <div class="card shadow-sm border-0 h-100">
      <img v-if="pista.images && pista.images.length > 0" 
          :src="'/assets/pistas/' + pista.images[0].image_url" 
          alt="Imagen de Pista" 
          class="card-img-top img-fluid" />
      <div class="card-body d-flex flex-column">
          <h5 class="card-title mb-3">{{ pista.nombre }}</h5>
          <div v-if="pista.deportes && pista.deportes.length" class="deportes-asociados mb-3">
              <p><strong>Deportes Asociados:</strong></p>
              <ul>
                  <li v-for="deporte in pista.deportes" :key="deporte.id">
                      <i class="bi bi-trophy me-2"></i>{{ deporte.nombre }}
                  </li>
              </ul>
          </div>
          <div class="mt-auto">
              <button v-if="!isPrivate" @click="editarPista(pista)" class="btn btn-outline-primary me-2">
                  <i class="bi bi-pencil-square"></i> Editar
              </button>
              <button @click="confirmarEliminar(pista.slug)" class="btn btn-outline-danger">
                  <i class="bi bi-trash"></i> Eliminar
              </button>
          </div>
      </div>
  </div>
</template>
  
  <script>
  export default {
    name: 'CardPistas',
    props: {
      pista: Object,
      isPrivate: Boolean,
    },
    methods: {
      editarPista(pista) {
        console.log("editar", pista);
        this.$router.push({ name: 'adminEditar', params: { slug: pista.slug, type:'pista' } });
      },
      confirmarEliminar(pistaSlug) {
        if (confirm("¿Estás seguro de que deseas eliminar esta pista?")) {
            this.eliminarPista(pistaSlug);
        }
        },
        eliminarPista(pistaSlug) {
          const action = this.isPrivate  
          ? 'adminDashboard/DeleteOnePistaPrivada'  
          : 'adminDashboard/DeteleOnePista';      

  
          this.$store.dispatch(action, pistaSlug)
              .then(() => console.log("Pista eliminada correctamente"))
              .catch(error => console.error("Error al eliminar la Pista:", error));
        }
        
    }
  };
  </script>
  
  <style scoped>
  .card {
      transition: transform 0.2s ease-in-out;
  }
  
  .card:hover {
      transform: translateY(-5px);
  }
  
  .card-img-top {
      height: 200px;
      object-fit: cover;
  }
  
  .btn {
      text-transform: uppercase;
      font-size: 0.875rem;
      letter-spacing: 0.5px;
  }
  
  .deportes-asociados ul {
      padding-left: 20px;
      list-style-type: none;
  }
  
  .deportes-asociados li {
      font-size: 0.9em;
      color: #333;
      display: flex;
      align-items: center;
  }

  .deportes-asociados i {
      color: var(--bs-primary);
  }
  </style>
