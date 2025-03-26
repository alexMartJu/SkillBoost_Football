<template>
    <div class="notifications-tab p-4">
      <h4 class="mb-4">Mis Notificaciones</h4>
  
      <div v-if="loading" class="text-center my-5">
        <div class="spinner-border text-primary" role="status">
          <span class="visually-hidden">Cargando...</span>
        </div>
      </div>
  
      <div v-else-if="sortedNotifications.length === 0" class="text-center my-5 py-5">
        <i class="bi bi-bell-slash text-muted" style="font-size: 3rem;"></i>
        <p class="mt-3 text-muted">No tienes notificaciones</p>
      </div>
  
      <div v-else class="notification-list">
        <div v-for="notification in sortedNotifications" :key="notification.id"
             class="notification-item card mb-3 border-0 shadow-sm">
          <div class="card-body p-3">
            <div class="d-flex align-items-start">
              <div class="notification-icon me-3">
                <div class="icon-circle" :class="getIconClass(notification.tipo_notificacion)">
                  <i :class="getIcon(notification.tipo_notificacion)"></i>
                </div>
              </div>
              <div class="notification-content flex-grow-1">
                <div class="d-flex justify-content-between align-items-start mb-2">
                  <p class="notification-text mb-0">{{ notification.mensaje }}</p>
                  <small class="text-muted ms-2">{{ formatDate(notification.createdAt) }}</small>
                </div>
                <span class="badge" :class="getTypeBadgeClass(notification.tipo_notificacion)">
                  {{ getTypeLabel(notification.tipo_notificacion) }}
                </span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import { ref, computed, onMounted } from 'vue';
  import { useStore } from 'vuex';
  import Constant from '@/Constant';
  
  export default {
    name: 'NotificationsTab',
    setup() {
      const store = useStore();
      const loading = ref(true);
  
      const notifications = computed(() => {
        return store.getters['notifications/GetNotifications'];
      });
  
      // Ordenar notificaciones de más recientes a menos recientes
      const sortedNotifications = computed(() => {
        return [...notifications.value].sort((a, b) => {
          return new Date(b.createdAt) - new Date(a.createdAt);
        });
      });
  
      const getIcon = (type) => {
        switch (type) {
          case 'bell': return 'bi bi-bell-fill';
          case 'email': return 'bi bi-envelope-fill';
          default: return 'bi bi-info-circle-fill';
        }
      };
  
      const getIconClass = (type) => {
        switch (type) {
          case 'bell': return 'bg-primary bg-opacity-10';
          case 'email': return 'bg-info bg-opacity-10';
          default: return 'bg-secondary bg-opacity-10';
        }
      };
  
      const getTypeBadgeClass = (type) => {
        switch (type) {
          case 'bell': return 'bg-primary text-white';
          case 'email': return 'bg-info text-white';
          default: return 'bg-secondary text-white';
        }
      };
  
      const getTypeLabel = (type) => {
        switch (type) {
          case 'bell': return 'Notificación';
          case 'email': return 'Email';
          default: return 'Mensaje';
        }
      };
  
      const formatDate = (dateString) => {
        const date = new Date(dateString);
        
        // Formato YYYY-MM-DD HH:MM:SS
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const day = String(date.getDate()).padStart(2, '0');
        const hours = String(date.getHours()).padStart(2, '0');
        const minutes = String(date.getMinutes()).padStart(2, '0');
        const seconds = String(date.getSeconds()).padStart(2, '0');
        
        return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
      };
  
      onMounted(() => {
        const userId = store.getters['user/GetCurrentUser']?.id;
        if (userId) {
          store.dispatch(`notifications/${Constant.INITIALIZE_NOTIFICATIONS}`, userId)
            .finally(() => {
              loading.value = false;
            });
        } else {
          loading.value = false;
        }
      });
  
      return {
        loading,
        sortedNotifications,
        getIcon,
        getIconClass,
        getTypeBadgeClass,
        getTypeLabel,
        formatDate
      };
    }
  };
  </script>
  
  <style scoped>
  .notification-item {
    transition: all 0.2s ease;
  }
  
  .notification-item:hover {
    transform: translateX(5px);
  }
  
  .icon-circle {
    width: 40px;
    height: 40px;
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 1.2rem;
  }
  
  .notification-text {
    color: #212529;
    font-size: 0.95rem;
  }
  
  .text-muted {
    font-size: 0.8rem;
    white-space: nowrap;
  }
  </style>
  