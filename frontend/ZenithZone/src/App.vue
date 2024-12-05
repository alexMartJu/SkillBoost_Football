<script setup>
import Header from './components/shared/Header.vue';
import Footer from './components/shared/Footer.vue';
</script>


<template>
  <div id="app" class="d-flex flex-column min-vh-100">
    <Header />
    <div class="flex-grow-1">
      <router-view />
    </div>
    <Footer />
  </div>
</template>

<style>
/* Aseguramos que el body ocupe el 100% de la altura y el footer se mantenga al final */
#app {
  display: flex;
  flex-direction: column;
  min-height: 100vh;  /* Esto asegura que el footer se quede al final */
}

.flex-grow-1 {
  flex-grow: 1; /* Esto hace que el contenido del router-view ocupe el espacio disponible */
}
</style>
