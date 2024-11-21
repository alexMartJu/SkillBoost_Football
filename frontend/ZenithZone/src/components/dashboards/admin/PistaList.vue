<template>
    <div>
      <ul>
        <li v-for="pista in pistas" :key="pista.id">
          <p>{{ pista.nombre }}</p>
        </li>
      </ul>
    </div>
  </template>
  
  <script>
  export default {
    name: 'PistaList',
    props: {
      pistas: Array
    }
  };
  </script>
  