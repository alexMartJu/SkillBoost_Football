import secrets from "@/secrets"
import Api from "../Api"

export default {
    //Obtener todas las notificaciones de un usuario
    GetUserNotifications(userId) {
        return Api(secrets.URL_PROXY).get(`notifications/user/${userId}`);
    },

    //Obtener notificaciones no leídas de tipo "bell" de un usuario
    GetUnreadBellNotifications(userId) {
        return Api(secrets.URL_PROXY).get(`notifications/user/${userId}/unread-bell`);
    },

    //Marcar una notificación como leída
    MarkNotificationAsRead(notificationId) {
        return Api(secrets.URL_PROXY).patch(`notifications/${notificationId}/read`);
    }
}
