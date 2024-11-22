
<template>
  <div class="pista-list">
    <CardPistas
      v-for="pista in pistas"
      :key="pista.id"
      :pista="pista"
      @editarPista="editarPista"
      @eliminarPista="eliminarPista"
    />
  </div>
</template>

<script>
import CardPistas from '@/components/dashboards/admin/CardPistas.vue';

export default {
  name: 'ListPistas',
  components: {
    CardPistas
  },
  props: {
    pistas: Array,
  },
  methods: {
    editarPista(pista) {
      console.log("editar", pista);
      this.$router.push({ name: 'adminEditarPista', params: { slug: pista.slug } });
    },
    eliminarPista(pistaId) {
      this.$emit('eliminarPista', pistaId);
    },
  },
};
</script>
<style scoped>
/* Estilo para listas de pistas */
.pista-list {
  display: flex;
  flex-wrap: wrap; /* Permite que los elementos se ajusten en varias filas */
  gap: 20px; /* Espaciado entre los elementos */
  justify-content: space-between; /* Asegura que las columnas ocupen el espacio disponible */
}

.pista-list > div {
  background-color: #fff;
  padding: 15px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  width: calc(33.33% - 20px); /* 3 elementos por fila en pantallas grandes */
  transition: transform 0.2s ease, box-shadow 0.2s ease;
  cursor: pointer;
}

.pista-list > div:hover {
  transform: translateY(-5px); /* Efecto de elevación al pasar el mouse */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

/* Responsividad: 2 columnas en pantallas medianas (menos de 768px) */
@media (max-width: 768px) {
  .pista-list > div {
    width: calc(50% - 20px); /* 2 elementos por fila */
  }
}

/* Responsividad: 1 columna en pantallas pequeñas (menos de 480px) */
@media (max-width: 480px) {
  .pista-list > div {
    width: 100%; /* 1 elemento por fila */
  }
}
</style>

<!-- <template>
  <div>
    <ul class="pista-list">
      <li v-for="pista in pistas" :key="pista.id">
        <p>{{ pista.nombre }}</p>
        <div v-if="pista.deportes && pista.deportes.length">
          <div class="deportes-asociados">
            <p><strong>Deportes Asociados:</strong></p>
            <ul>
              <li v-for="deporte in pista.deportes" :key="deporte.id">{{ deporte.nombre }}</li>
            </ul>
          </div>
        </div>
        <button @click="editarPista(pista)">Editar</button>
        <button @click="eliminarPista(pista.id)">Eliminar</button>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'ListPistas',
  props: {
    pistas: Array,
  },
  methods: {
    editarPista(pista) {
      console.log("editar"+JSON.stringify(pista));
      // this.$emit('editarPista', pista);
      this.$router.push({ name: 'adminEditarPista', params: { slug: pista.slug } });
    },
    eliminarPista(pistaId) {
      this.$emit('eliminarPista', pistaId); 
    },
  },
};
</script>
  <style scoped>
  /* Estilos para la lista de pistas */
  .pista-list {
    padding: 0;
    list-style-type: none;
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
    margin-top: 20px;
    max-height: 500px; 
    overflow-y: auto;  
  }
  
  .pista-list li {
    background-color: #fff;
    padding: 15px;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    width: calc(33% - 20px); 
    transition: transform 0.2s ease, box-shadow 0.2s ease;
    cursor: pointer;
  }
  
  .pista-list li:hover {
    transform: translateY(-5px);
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  }
  
  .pista-name {
    font-size: 1.2em;
    font-weight: bold;
    color: #333;
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
  
  .pista-list li p {
    margin: 0;
    padding: 0;
  }
  
  
  @media (max-width: 768px) {
    .pista-list li {
      width: calc(50% - 20px); 
    }
  }
  
  
  @media (max-width: 480px) {
    .pista-list li {
      width: 100%; 
    }
  }
  </style>
   -->