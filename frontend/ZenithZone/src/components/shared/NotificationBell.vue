<template>
    <div class="position-relative" ref="notificationBellRef">
        <div class="notification-bell d-flex align-items-center justify-content-center rounded-circle"
            @click="toggleNotificationMenu">
            <i class="bi bi-bell-fill fs-5"></i>
            <span v-if="unreadNotificationsCount > 0"
                class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
                {{ unreadNotificationsCount > 9 ? '9+' : unreadNotificationsCount }}
                <span class="visually-hidden">notificaciones no leídas</span>
            </span>
        </div>

        <!-- Menú desplegable de notificaciones -->
        <div v-if="showMenu" class="dropdown-menu dropdown-menu-end p-0 shadow show notification-dropdown"
            style="width: 300px;">
            <div class="bg-primary p-3 rounded-top">
                <h6 class="m-0 fw-bold text-white">Notificaciones</h6>
            </div>
            <div class="overflow-auto" style="max-height: 300px;">
                <div v-if="unreadNotifications.length === 0" class="p-4 text-center text-muted fst-italic">
                    No tienes notificaciones nuevas
                </div>
                <div v-else class="list-group list-group-flush">
                    <div v-for="notification in unreadNotifications" :key="notification.id"
                        class="list-group-item list-group-item-action d-flex justify-content-between align-items-start p-md-3 p-2 border-0">
                        <div class="ms-2 me-auto">
                            <div class="text-wrap">{{ notification.mensaje }}</div>
                            <small class="text-muted">{{ formatDate(notification.createdAt) }}</small>
                        </div>
                        <button @click.stop="markAsRead(notification.id)" class="btn btn-sm text-primary border-0 p-1"
                            title="Marcar como leída">
                            <i class="bi bi-check-circle fs-5"></i>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { ref, computed, onMounted, onUnmounted, watch } from 'vue';
import { useStore } from 'vuex';
import Constant from '@/Constant';

export default {
    name: 'NotificationBell',

    props: {
        userId: {
            type: [Number, String],
            required: true
        }
    },

    setup(props) {
        
        const store = useStore();
        const showMenu = ref(false);
        const notificationBellRef = ref(null);

        //Intervalo para actualizar notificaciones
        let notificationInterval = null;

        const unreadNotifications = computed(() =>
            store.getters['notifications/GetUnreadBellNotifications']
        );

        const unreadNotificationsCount = computed(() =>
            store.getters['notifications/GetUnreadBellNotificationsCount']
        );

        //Formatear fecha para las notificaciones
        const formatDate = (dateString) => {
            const date = new Date(dateString);
            return date.toLocaleString('es-ES', {
                day: '2-digit',
                month: '2-digit',
                year: 'numeric',
                hour: '2-digit',
                minute: '2-digit'
            });
        };

        //Mostrar/ocultar menú de notificaciones
        const toggleNotificationMenu = () => {
            showMenu.value = !showMenu.value;
        };

        //Marcar notificación como leída
        const markAsRead = (notificationId) => {
            store.dispatch(`notifications/${Constant.MARK_NOTIFICATION_AS_READ}`, notificationId);
        };

        //Cerrar menú de notificaciones al hacer clic fuera
        const handleClickOutside = (event) => {
            if (notificationBellRef.value && !notificationBellRef.value.contains(event.target)) {
                showMenu.value = false;
            }
        };

        //Cargar notificaciones
        const loadNotifications = () => {
            if (props.userId) {
                store.dispatch(`notifications/${Constant.INITIALIZE_UNREAD_BELL_NOTIFICATIONS}`, props.userId);
            }
        };

        //Observar cambios en el userId para cargar notificaciones
        watch(() => props.userId, (newUserId) => {
            if (newUserId) {
                loadNotifications();
            }
        });

        onMounted(() => {
            window.addEventListener('click', handleClickOutside);
            loadNotifications();

            //Configurado intervalo para actualizar notificaciones cada 30 segundos
            notificationInterval = setInterval(loadNotifications, 30000);
        });

        onUnmounted(() => {
            window.removeEventListener('click', handleClickOutside);
            if (notificationInterval) {
                clearInterval(notificationInterval);
            }
        });

        return {
            showMenu,
            unreadNotifications,
            unreadNotificationsCount,
            toggleNotificationMenu,
            markAsRead,
            formatDate,
            notificationBellRef
        };
    }
};
</script>

<style scoped>
.notification-bell {
    color: var(--bs-primary);
    cursor: pointer;
    padding: 8px;
    transition: all 0.3s ease;
}

.notification-bell:hover {
    background-color: rgba(var(--bs-primary-rgb), 0.1);
    transform: translateY(-2px);
}

.notification-dropdown {
    margin-top: 0.5rem;
    border-radius: 0.75rem !important;
    border: none;
    overflow: hidden;
}

.btn:hover {
    transform: scale(1.2);
}

@media (max-width: 991.98px) {
    .notification-dropdown {
        right: 0;
        width: 280px !important;
    }
}
</style>