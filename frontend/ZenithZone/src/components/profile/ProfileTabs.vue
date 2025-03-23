<template>
    <div class="profile-tabs mt-4">
      <div class="card shadow-sm border-0 rounded-3">
        <div class="card-header bg-white p-0">
          <ul class="nav nav-tabs border-0" id="profileTabs" role="tablist">
            <li class="nav-item" role="presentation">
              <button class="nav-link px-4 py-3" 
                      :class="{ 'active': activeTab === 'notifications' }"
                      @click="setActiveTab('notifications')" 
                      id="notifications-tab" 
                      type="button" 
                      role="tab" 
                      aria-controls="notifications" 
                      aria-selected="true">
                <i class="bi bi-bell-fill me-2"></i>Mis Notificaciones
              </button>
            </li>
            <li class="nav-item" role="presentation">
              <button class="nav-link px-4 py-3" 
                      :class="{ 'active': activeTab === 'entrenamientos' }"
                      @click="setActiveTab('entrenamientos')" 
                      id="entrenamientos-tab" 
                      type="button" 
                      role="tab" 
                      aria-controls="entrenamientos" 
                      aria-selected="false">
                <i class="bi bi-calendar-check-fill me-2"></i>Mis Entrenamientos
              </button>
            </li>
            <li class="nav-item" role="presentation">
              <button class="nav-link px-4 py-3" 
                      :class="{ 'active': activeTab === 'calendario' }"
                      @click="setActiveTab('calendario')" 
                      id="calendario-tab" 
                      type="button" 
                      role="tab" 
                      aria-controls="calendario" 
                      aria-selected="false">
                <i class="bi bi-calendar-week-fill me-2"></i>Mi Calendario
              </button>
            </li>
            <!-- Aquí se pueden añadir más tabs en el futuro -->
          </ul>
        </div>
        <div class="card-body p-0">
          <div class="tab-content" id="profileTabsContent">
            <div class="tab-pane fade" 
                 :class="{ 'show active': activeTab === 'notifications' }"
                 id="notifications" 
                 role="tabpanel" 
                 aria-labelledby="notifications-tab">
              <NotificationsTab />
            </div>
            <div class="tab-pane fade" 
                 :class="{ 'show active': activeTab === 'entrenamientos' }"
                 id="entrenamientos" 
                 role="tabpanel" 
                 aria-labelledby="entrenamientos-tab">
              <EntrenamientosTab />
            </div>
            <div class="tab-pane fade" 
                 :class="{ 'show active': activeTab === 'calendario' }"
                 id="calendario" 
                 role="tabpanel" 
                 aria-labelledby="calendario-tab">
              <CalendarioTab />
            </div>
            <!-- Aquí se pueden añadir más contenidos de tabs en el futuro -->
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import { ref } from 'vue';
  import NotificationsTab from './tabs/NotificationsTab.vue';
  import EntrenamientosTab from './tabs/EntrenamientosTab.vue';
  import CalendarioTab from './tabs/CalendarioTab.vue';
  
  export default {
    name: 'ProfileTabs',
    components: {
      NotificationsTab,
      EntrenamientosTab,
      CalendarioTab
    },
    setup() {
      const activeTab = ref('notifications');
  
      const setActiveTab = (tabName) => {
        activeTab.value = tabName;
      };
  
      return {
        activeTab,
        setActiveTab
      };
    }
  };
  </script>
  
  <style scoped>
  .nav-tabs .nav-link {
    color: #6c757d;
    border: none;
    border-bottom: 3px solid transparent;
    font-weight: 500;
    transition: all 0.2s ease;
  }
  
  .nav-tabs .nav-link.active {
    color: #ff6600;
    border-bottom: 3px solid #ff6600;
    background-color: transparent;
  }
  
  .nav-tabs .nav-link:hover:not(.active) {
    color: #495057;
    border-bottom: 3px solid #dee2e6;
  }
  
  .tab-content {
    min-height: auto; /* Cambiado de 700px a auto */
    height: auto !important;
    width: 100%;
    overflow: visible; /* Permitir que el contenido sea visible incluso si se desborda */
  }
  
  .tab-pane {
    height: auto !important;
    width: 100%;
    overflow: visible;
  }
  
  .card-body {
    overflow: visible; /* Permitir que el contenido sea visible */
  }
  
  .card {
    overflow: visible; /* Permitir que el contenido sea visible */
  }
  </style>
  