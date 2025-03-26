import express from 'express';
import userRoutes from './user/userRoutes';
import trainingRoutes from './training/trainingRoutes';
import subscriptionRoutes from './subscription/subscriptionRoutes';
import notificationRoutes from './notification/notificationRoutes';
import achievementRoutes from './achievement/achievementRoutes';

const router = express.Router();

//Rutas para cada tipo de notificación
router.use('/user', userRoutes);
router.use('/training', trainingRoutes);
router.use('/subscription', subscriptionRoutes);
router.use('/notifications', notificationRoutes);
router.use('/achievement', achievementRoutes);

export default router;