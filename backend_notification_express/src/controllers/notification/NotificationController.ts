import { Request, Response, NextFunction } from 'express';
import { NotificationService } from '../../services/notification/NotificationService';
import { AppError } from '../../utils/AppError';

export class NotificationController {
    private notificationService: NotificationService;

    constructor() {
        this.notificationService = new NotificationService();
    }

    getUserNotifications = async (req: Request, res: Response, next: NextFunction) => {
        try {
            const userId = parseInt(req.params.userId);

            if (isNaN(userId)) {
                throw new AppError('ID de usuario inválido', 400);
            }

            const notifications = await this.notificationService.getUserNotifications(userId);

            res.status(200).json({
                status: 'success',
                data: notifications
            });
        } catch (error) {
            next(error);
        }
    };

    markNotificationAsRead = async (req: Request, res: Response, next: NextFunction) => {
        try {
            const notificationId = parseInt(req.params.id);

            if (isNaN(notificationId)) {
                throw new AppError('ID de notificación inválido', 400);
            }

            const result = await this.notificationService.markNotificationAsRead(notificationId);

            res.status(200).json({
                status: 'success',
                data: result
            });
        } catch (error) {
            next(error);
        }
    };

    getUnreadBellNotifications = async (req: Request, res: Response, next: NextFunction) => {
        try {
            const userId = parseInt(req.params.userId);
    
            if (isNaN(userId)) {
                throw new AppError('ID de usuario inválido', 400);
            }
    
            const notifications = await this.notificationService.getUnreadBellNotifications(userId);
    
            res.status(200).json({
                status: 'success',
                data: notifications
            });
        } catch (error) {
            next(error);
        }
    };
}