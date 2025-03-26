import { PrismaClient } from '@prisma/client';
import { INotification } from '../../interfaces/notification/NotificationInterface';
import { AppError } from '../../utils/AppError';

const prisma = new PrismaClient();

export class NotificationRepository {
    async createNotification(notification: INotification): Promise<any> {
        try {
            const result = await prisma.notificaciones.create({
                data: {
                    id_usuario: BigInt(notification.id_usuario),
                    mensaje: notification.mensaje,
                    tipo_notificacion: notification.tipo_notificacion,
                    isRead: notification.isRead || false
                }
            });

            //Transformar el resultado para evitar problemas de serialización
            return {
                id: result.id.toString(),
                id_usuario: result.id_usuario.toString(),
                mensaje: result.mensaje,
                tipo_notificacion: result.tipo_notificacion,
                isRead: result.isRead,
                createdAt: result.createdAt,
                updatedAt: result.updatedAt
            };
        } catch (error) {
            console.error('Error creating notification:', error);
            throw new AppError('Error al crear la notificación', 500);
        }
    }

    async getNotificationsByUserId(userId: number): Promise<any[]> {
        try {
            return await prisma.notificaciones.findMany({
                where: {
                    id_usuario: BigInt(userId)
                },
                orderBy: {
                    createdAt: 'desc'
                }
            });
        } catch (error) {
            console.error('Error fetching notifications:', error);
            throw new AppError('Error al obtener las notificaciones', 500);
        }
    }

    async markNotificationAsRead(notificationId: number): Promise<any> {
        try {
            return await prisma.notificaciones.update({
                where: {
                    id: BigInt(notificationId)
                },
                data: {
                    isRead: true
                }
            });
        } catch (error) {
            console.error('Error marking notification as read:', error);
            throw new AppError('Error al marcar la notificación como leída', 500);
        }
    }

    async getUnreadBellNotificationsByUserId(userId: number): Promise<any[]> {
        try {
            return await prisma.notificaciones.findMany({
                where: {
                    id_usuario: BigInt(userId),
                    isRead: false,
                    tipo_notificacion: 'bell'
                },
                orderBy: {
                    createdAt: 'desc'
                }
            });
        } catch (error) {
            console.error('Error fetching unread bell notifications:', error);
            throw new AppError('Error al obtener las notificaciones no leídas', 500);
        }
    }
}