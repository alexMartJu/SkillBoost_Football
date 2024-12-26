<template>
    <div class="card">
      <div class="card-header">
        <h5 class="card-title mb-0">{{ entrenamiento.nombre }}</h5>
      </div>
      <div class="card-body">
        <p class="card-text text-muted mb-2">{{ entrenamiento.descripcion }}</p>
        <div class="card-info">
          <span>⏱️ {{ entrenamiento.duracion }} min</span>
          <span>👥 Máx: {{ entrenamiento.max_plazas }}</span>
          <span>💰 {{ entrenamiento.precio }}€</span>
        </div>
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
  .card {
    border: 1px solid #ddd;
    border-radius: 8px;
    transition: transform 0.2s, box-shadow 0.2s;
    overflow: hidden;
    width: 100%; /* Ocupa todo el ancho disponible */
    margin-bottom: 15px; /* Espacio entre tarjetas */
    box-shadow: 0 1px 4px rgba(0, 0, 0, 0.1);
    background-color: #fff;
  }
  
  .card:hover {
    transform: scale(1.02);
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  }
  
  .card-header {
    background-color: #f8f9fa;
    padding: 10px 15px;
    border-bottom: 1px solid #ddd;
    font-weight: bold;
    color: #343a40;
  }
  
  .card-title {
    font-size: 1.2rem;
    margin: 0;
  }
  
  .card-body {
    padding: 15px;
  }
  
  .card-text {
    font-size: 0.9rem;
    margin: 0 0 8px;
  }
  
  .card-info {
    display: flex;
    flex-wrap: wrap;
    gap: 10px;
    font-size: 0.85rem;
    color: #6c757d;
  }
  
  .card-info span {
    display: flex;
    align-items: center;
    gap: 5px;
    padding: 5px 10px;
    background-color: #f1f3f5;
    border-radius: 20px;
  }
  
  .card-info span:hover {
    background-color: #e9ecef;
  }
  </style>
  