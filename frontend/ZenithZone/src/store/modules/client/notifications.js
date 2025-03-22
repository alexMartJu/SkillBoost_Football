import notificationsService from '../../../services/client/notifications.service';
import Constant from '../../../Constant';

export const notifications = {
    namespaced: true,

    state: {
        notifications: [],
        unreadBellNotifications: []
    },

    actions: {
        [Constant.INITIALIZE_NOTIFICATIONS]: async (store, userId) => {
            try {
                const { data } = await notificationsService.GetUserNotifications(userId);
                store.commit(Constant.SET_CURRENT_NOTIFICATIONS, data.data);
            } catch (error) {
                console.error("Error al cargar las notificaciones:", error);
            }
        },

        [Constant.INITIALIZE_UNREAD_BELL_NOTIFICATIONS]: async (store, userId) => {
            try {
                const { data } = await notificationsService.GetUnreadBellNotifications(userId);
                store.commit(Constant.INITIALIZE_UNREAD_BELL_NOTIFICATIONS, data.data);
            } catch (error) {
                console.error("Error al cargar las notificaciones no leídas:", error);
            }
        },

        [Constant.MARK_NOTIFICATION_AS_READ]: async (store, notificationId) => {
            try {
                await notificationsService.MarkNotificationAsRead(notificationId);
                //Actualizar el estado local
                store.commit(Constant.MARK_NOTIFICATION_AS_READ, notificationId);
            } catch (error) {
                console.error("Error al marcar la notificación como leída:", error);
            }
        }
    },

    mutations: {
        [Constant.SET_CURRENT_NOTIFICATIONS](state, payload) {
            if (payload) {
                state.notifications = payload;
            }
        },
        [Constant.INITIALIZE_UNREAD_BELL_NOTIFICATIONS](state, payload) {
            if (payload) {
                state.unreadBellNotifications = payload;
            }
        },
        [Constant.MARK_NOTIFICATION_AS_READ](state, notificationId) {
            //Actualizar en unreadBellNotifications
            state.unreadBellNotifications = state.unreadBellNotifications.filter(
                notification => notification.id.toString() !== notificationId.toString()
            );
            
            //Actualizar en notifications
            state.notifications = state.notifications.map(notification => {
                if (notification.id.toString() === notificationId.toString()) {
                    return { ...notification, isRead: true };
                }
                return notification;
            });
        }
    },

    getters: {
        GetNotifications(state) {
            return state.notifications;
        },
        GetUnreadBellNotifications(state) {
            return state.unreadBellNotifications;
        },
        GetUnreadBellNotificationsCount(state) {
            return state.unreadBellNotifications.length;
        }
    }
};
