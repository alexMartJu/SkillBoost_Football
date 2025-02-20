<template>
  <div class="card-content">
    <h5 class="h5 fw-bold mb-3">{{ entrenamiento.nombre }}</h5>
    <p class="text-muted small mb-3">{{ entrenamiento.descripcion }}</p>
    <div class="d-flex gap-3">
      <span class="badge bg-light text-dark">
        <i class="bi bi-clock me-1"></i>
        {{ entrenamiento.duracion }}min
      </span>
      <span class="badge bg-light text-dark">
        <i class="bi bi-people me-1"></i>
        {{ entrenamiento.max_plazas }}
      </span>
      <span class="badge bg-light text-dark">
        <i class="bi bi-currency-euro me-1"></i>
        {{ entrenamiento.precio }}
      </span>
    </div>
  </div>
</template>

  
  <script>
  export default {
    props: {
      entrenamiento: {
        type: Object,
        required: true,
      },
    },
  };
  </script>
  
  <style scoped>

  </style>