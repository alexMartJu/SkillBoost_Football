<template>
  <div class="dashboard-container">
    <!-- Sidebar -->
    <div class="sidebar bg-dark text-white">
      <div class="sidebar-header p-3 d-flex align-items-center">
        <i class="bi bi-dribbble fs-4 text-primary me-3"></i>
        <h5 class="text-white mb-0">Panel de Administración</h5>
      </div>
      <hr class="border-light opacity-25 my-0">
      <div class="d-flex flex-column py-3">
        <!-- Sección destacada para entrenamientos pendientes -->
        <div class="sidebar-section mb-3">
          <router-link to="/dashboardAdmin/entrenamientos-pendientes" class="nav-link sidebar-link highlight-link">
            <div class="d-flex align-items-center">
              <i class="bi bi-hourglass-split me-2"></i>
              <span>Entrenamientos Pendientes</span>
              <span v-if="pendingCount > 0" class="ms-auto badge rounded-pill bg-danger">
                {{ pendingCount }}
              </span>
            </div>
          </router-link>
        </div>

        <router-link to="/dashboardAdmin/tecnificaciones" class="nav-link sidebar-link">
          <i class="bi bi-lightning-charge me-2"></i> Tecnificaciones
        </router-link>
        <router-link to="/dashboardAdmin/subtipos-tecnificaciones" class="nav-link sidebar-link">
          <i class="bi bi-list-nested me-2"></i> Subtipos de Tecnificación
        </router-link>
        <router-link to="/dashboardAdmin/pistas" class="nav-link sidebar-link">
          <i class="bi bi-geo-alt me-2"></i> Campos
        </router-link>
        
        <router-link to="/dashboardAdmin/suscripciones" class="nav-link sidebar-link">
          <i class="bi bi-credit-card me-2"></i> Suscripciones
        </router-link>
        <router-link to="/dashboardAdmin/organizaciones" class="nav-link sidebar-link">
          <i class="bi bi-building me-2"></i> Organizaciones
        </router-link>
        <router-link to="/dashboardAdmin/entrenadores" class="nav-link sidebar-link">
          <i class="bi bi-person-badge-fill me-2"></i> Entrenadores
        </router-link>
        <!-- Sección de usuarios mejorada -->
        <div class="sidebar-section">
          <div class="sidebar-link" @click="toggleUserMenu">
            <i class="bi bi-people me-2"></i> Usuarios
            <i class="bi" :class="userMenuOpen ? 'bi-chevron-down' : 'bi-chevron-right'" style="float: right;"></i>
          </div>
          
          <div class="submenu" :class="{ 'submenu-open': userMenuOpen }">
            <router-link to="/dashboardAdmin/usuarios/registro" class="nav-link sidebar-sublink">
              <i class="bi bi-person-plus-fill me-2"></i> Registrar Usuarios
            </router-link>
            <router-link to="/dashboardAdmin/usuarios" class="nav-link sidebar-sublink">
              <i class="bi bi-people-fill me-2"></i> Todos los usuarios
            </router-link>
            <router-link to="/dashboardAdmin/usuarios/tutores" class="nav-link sidebar-sublink">
              <i class="bi bi-person-badge me-2"></i> Tutores
            </router-link>
            <router-link to="/dashboardAdmin/usuarios/jugadores-club" class="nav-link sidebar-sublink">
              <i class="bi bi-trophy me-2"></i> Jugadores de Club
            </router-link>
            <router-link to="/dashboardAdmin/usuarios/jugadores" class="nav-link sidebar-sublink">
              <i class="bi bi-person me-2"></i> Jugadores
            </router-link>
            <router-link to="/dashboardAdmin/usuarios/jugadores-sociales" class="nav-link sidebar-sublink">
              <i class="bi bi-heart me-2"></i> Jugadores Sociales
            </router-link>
          </div>
        </div>
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

<script setup>
import { ref, watch, onMounted, computed } from 'vue';
import { useRoute } from 'vue-router';
import { useStore } from 'vuex';
import Constant from '@/Constant';

const userMenuOpen = ref(false);
const route = useRoute();
const store = useStore();
const pendingCount = computed(() => {
  const pendingEntrenamientos = store.getters['adminDashboard/getPendingEntrenamientos'];
  return pendingEntrenamientos ? pendingEntrenamientos.length : 0;
});

// Métodos
const toggleUserMenu = () => {
  userMenuOpen.value = !userMenuOpen.value;
};

//Cargar entrenamientos pendientes al montar el componente
const loadPendingEntrenamientos = async () => {
  await store.dispatch(`adminDashboard/${Constant.FETCH_PENDING_ENTRENAMIENTOS}`);
};

// Observar cambios en la ruta
watch(() => route.path, (newPath) => {
  // Abrir automáticamente el menú de usuarios si la ruta actual está relacionada con usuarios
  if (newPath.includes('/usuarios')) {
    userMenuOpen.value = true;
  }
}, { immediate: true });

// Inicializar el estado del menú basado en la ruta actual
onMounted(async () => {
  if (route.path.includes('/usuarios')) {
    userMenuOpen.value = true;
  }

  //Cargar entrenamientos pendientes
  await loadPendingEntrenamientos();

  //Configurar intervalo para actualizar entrenamientos pendientes cada 5 minutos
  const interval = setInterval(loadPendingEntrenamientos, 5 * 60 * 1000);

  //Limpiar intervalo al desmontar
  return () => clearInterval(interval);
});
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
  cursor: pointer;
}

.sidebar-link:hover,
.sidebar-link.router-link-active {
  background-color: rgba(var(--bs-primary-rgb), 0.1);
  color: var(--bs-primary);
  border-left: 4px solid var(--bs-primary);
}

/* Estilo para el enlace destacado de entrenamientos pendientes */
.highlight-link {
  background-color: rgba(var(--bs-primary-rgb), 0.15);
  margin: 0 0.5rem;
  border-radius: 0.5rem;
  border-left: none;
}

.highlight-link:hover,
.highlight-link.router-link-active {
  background-color: rgba(var(--bs-primary-rgb), 0.25);
  border-left: none;
  transform: translateY(-2px);
}

.sidebar-section {
  display: flex;
  flex-direction: column;
}

.submenu {
  max-height: 0;
  overflow: hidden;
  transition: max-height 0.3s ease;
}

.submenu-open {
  max-height: 300px;
}

.sidebar-sublink {
  color: rgba(255, 255, 255, 0.8);
  padding: 0.6rem 1rem 0.6rem 3rem;
  text-decoration: none;
  transition: all 0.3s ease;
  font-size: 0.9rem;
}

.sidebar-sublink:hover,
.sidebar-sublink.router-link-active {
  background-color: rgba(var(--bs-primary-rgb), 0.1);
  color: var(--bs-primary);
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
