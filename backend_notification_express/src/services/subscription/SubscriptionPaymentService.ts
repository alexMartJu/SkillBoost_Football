import { ISubscriptionPaymentData } from '../../interfaces/subscription/SubscriptionPaymentInterface';
import { INotification, NotificationType } from '../../interfaces/notification/NotificationInterface';
import { NotificationRepository } from '../../repositories/notification/NotificationRepository';
import { SubscriptionRepository } from '../../repositories/subscription/SubscriptionRepository';
import { EmailService } from '../email/EmailService';
import { AppError } from '../../utils/AppError';

export class SubscriptionPaymentService {
    private notificationRepo: NotificationRepository;
    private subscriptionRepo: SubscriptionRepository;
    private emailService: EmailService;

    constructor() {
        this.notificationRepo = new NotificationRepository();
        this.subscriptionRepo = new SubscriptionRepository();
        this.emailService = new EmailService();
    }

    async handleSubscriptionPayment(data: ISubscriptionPaymentData): Promise<any> {
        try {
            //Verificar que tenemos los datos necesarios
            if (!data.userId || !data.email || !data.suscripcionId) {
                throw new AppError('Datos de suscripción incompletos para la notificación', 400);
            }

            //Obtener detalles de la suscripción si no se proporcionaron
            let nombreSuscripcion = data.nombreSuscripcion;
            if (!nombreSuscripcion) {
                const suscripcion = await this.subscriptionRepo.getSubscriptionDetails(data.suscripcionId);
                nombreSuscripcion = suscripcion?.nombre || 'suscripción';
            }

            //Enviar email de confirmación de pago
            const emailData = this.emailService.createSubscriptionPaymentEmail(data.email, {
                nombreSuscripcion,
                monto: data.monto,
                metodoPago: data.metodoPago
            });
            await this.emailService.sendEmail(emailData);

            //Crear notificación en la base de datos
            const notification: INotification = {
                id_usuario: data.userId,
                mensaje: `Tu pago para la suscripción ${nombreSuscripcion} ha sido procesado correctamente.`,
                tipo_notificacion: NotificationType.EMAIL
            };

            return await this.notificationRepo.createNotification(notification);
        } catch (error) {
            console.error('Error handling subscription payment notification:', error);
            throw error instanceof AppError ? error : new AppError('Error al procesar la notificación de pago', 500);
        }
    }
}