import express from 'express';
import { SubscriptionPaymentController } from '../../controllers/subscription/SubscriptionPaymentController';

const router = express.Router();
const subscriptionPaymentController = new SubscriptionPaymentController();

router.post('/payment', subscriptionPaymentController.handleSubscriptionPayment);

export default router;