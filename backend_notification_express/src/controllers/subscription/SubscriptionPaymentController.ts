import { Request, Response, NextFunction } from 'express';
import { SubscriptionPaymentService } from '../../services/subscription/SubscriptionPaymentService';
import { AppError } from '../../utils/AppError';

export class SubscriptionPaymentController {
    private subscriptionPaymentService: SubscriptionPaymentService;

    constructor() {
        this.subscriptionPaymentService = new SubscriptionPaymentService();
    }

    handleSubscriptionPayment = async (req: Request, res: Response, next: NextFunction) => {
        try {
            const { userId, email, suscripcionId, nombreSuscripcion, monto, metodoPago } = req.body;

            if (!userId || !email || !suscripcionId) {
                throw new AppError('Se requiere userId, email y suscripcionId', 400);
            }

            const result = await this.subscriptionPaymentService.handleSubscriptionPayment({
                userId,
                email,
                suscripcionId,
                nombreSuscripcion,
                monto,
                metodoPago
            });

            res.status(201).json({
                status: 'success',
                data: result
            });
        } catch (error) {
            next(error);
        }
    };
}