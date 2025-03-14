import express from 'express';
import { NotificationController } from '../../controllers/notification/NotificationController';

const router = express.Router();
const notificationController = new NotificationController();

router.get('/user/:userId', notificationController.getUserNotifications);
router.patch('/:id/read', notificationController.markNotificationAsRead);

export default router;