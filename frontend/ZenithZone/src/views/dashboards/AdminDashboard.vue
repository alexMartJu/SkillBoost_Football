
<template>
    <div class="admin-dashboard container">
      <h1>Dashboard Admin</h1>
  
      <!-- Barra de navegación interna -->
      <div class="btn-group" role="group">
        <router-link to="/dashboardAdmin/listar" class="btn btn-primary">
          Listar
        </router-link>
        <router-link to="/dashboardAdmin/crear" class="btn btn-secondary">
          Crear
        </router-link>
        <router-link to="/dashboardAdmin/entrenador" class="btn btn-secondary">
          Registrar Entrenador
        </router-link>
      </div>
  
      <!-- Cargar vistas de listado o creación según la ruta -->
      <router-view></router-view>
    </div>
  </template>
  
  <script>
  export default {
    name: "AdminDashboard",
  };
  </script>
  
  <style scoped>
  .admin-dashboard {
    margin-top: 130px;
  }
  .btn-group .btn {
    margin-right: 10px;
  }
  </style>