<template>
  <div class="dashboard-container">
    <!-- Sidebar -->
    <div class="sidebar bg-dark text-white">
      <div class="sidebar-header p-3 d-flex align-items-center">
        <i class="bi bi-dribbble fs-4 text-primary me-3"></i>
        <h5 class="text-white mb-0">Panel de Entrenador</h5>
      </div>
      <hr class="border-light opacity-25 my-0">
      <div class="d-flex flex-column py-3">
        <router-link to="/dashboardEntrenador/listar" class="nav-link sidebar-link">
          <i class="bi bi-list-ul me-2"></i> Listar Pistas
        </router-link>
        <router-link to="/dashboardEntrenador/crear-entrenamiento" class="nav-link sidebar-link">
          <i class="bi bi-plus-circle me-2"></i> Crear Entrenamiento
        </router-link>
        <router-link to="/dashboardEntrenador/mis-entrenamientos" class="nav-link sidebar-link">
          <i class="bi bi-calendar-check me-2"></i> Mis Entrenamientos
        </router-link>
        <router-link to="/dashboardEntrenador/mi-calendario" class="nav-link sidebar-link">
          <i class="bi bi-calendar-week me-2"></i> Mi Calendario
        </router-link>
      </div>
    </div>

    <!-- Main Content -->
    <div class="main-content bg-light">
      <div class="p-4">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>
  
<script>
export default {
  name: "EntrenadorDashboard"
};
</script>

<style scoped>
.dashboard-container {
  display: flex;
  min-height: 100vh;
}

.sidebar {
  width: 280px;
  box-shadow: var(--bs-box-shadow);
}

.sidebar-link {
  color: white;
  padding: 0.8rem 1.5rem;
  text-decoration: none;
  border-left: 4px solid transparent;
  transition: all 0.3s ease;
}

.sidebar-link:hover, .sidebar-link.router-link-active {
  background-color: rgba(var(--bs-primary-rgb), 0.1);
  color: var(--bs-primary);
  border-left: 4px solid var(--bs-primary);
}

.main-content {
  flex: 1;
}

@media (max-width: 768px) {
  .sidebar {
    width: 0;
    transform: translateX(-100%);
  }
}
</style>
