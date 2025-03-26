import { NotificationRepository } from '../../repositories/notification/NotificationRepository';
import { AppError } from '../../utils/AppError';

export class NotificationService {
    private notificationRepo: NotificationRepository;

    constructor() {
        this.notificationRepo = new NotificationRepository();
    }

    async getUserNotifications(userId: number): Promise<any[]> {
        try {
            return await this.notificationRepo.getNotificationsByUserId(userId);
        } catch (error) {
            console.error('Error getting user notifications:', error);
            throw error instanceof AppError ? error : new AppError('Error al obtener las notificaciones del usuario', 500);
        }
    }

    async markNotificationAsRead(notificationId: number): Promise<any> {
        try {
            return await this.notificationRepo.markNotificationAsRead(notificationId);
        } catch (error) {
            console.error('Error marking notification as read:', error);
            throw error instanceof AppError ? error : new AppError('Error al marcar la notificación como leída', 500);
        }
    }

    async getUnreadBellNotifications(userId: number): Promise<any[]> {
        try {
            return await this.notificationRepo.getUnreadBellNotificationsByUserId(userId);
        } catch (error) {
            console.error('Error getting unread bell notifications:', error);
            throw error instanceof AppError ? error : new AppError('Error al obtener las notificaciones no leídas', 500);
        }
    }
}