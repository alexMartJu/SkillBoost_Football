<template>
    <div>
      <ul>
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
    // console.log("slugs"+deportes.data.slug );
  };
  </script>
  