<template>
    <div>
      <ul  class="deporte-list">
        <li v-for="deportes in deportes" :key="deportes.id">
          <p>{{ deportes.nombre }}</p>
          <p>Slug: {{ deportes.slug }}</p>
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  export default {
    name: 'DeporteList',
    props: {
      deportes: Array
    },
    methods: {
     
    
    }
   
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
</style>