import { IAchievementUnlockedData } from '../../interfaces/achievement/AchievementUnlockedInterface';
import { INotification, NotificationType } from '../../interfaces/notification/NotificationInterface';
import { NotificationRepository } from '../../repositories/notification/NotificationRepository';
import { EmailService } from '../email/EmailService';
import { AppError } from '../../utils/AppError';

export class AchievementUnlockedService {
    private notificationRepo: NotificationRepository;
    private emailService: EmailService;

    constructor() {
        this.notificationRepo = new NotificationRepository();
        this.emailService = new EmailService();
    }

    async handleAchievementUnlocked(data: IAchievementUnlockedData): Promise<any> {
        try {
            if (!data.userId || !data.email || !data.nombreLogro) {
                throw new AppError('Datos de logro incompletos para la notificación', 400);
            }

            //Enviar email de felicitación por el logro
            const emailData = this.createAchievementEmail(data);
            await this.emailService.sendEmail(emailData);

            //Crear notificación en la base de datos (tipo bell)
            const notification: INotification = {
                id_usuario: data.userId,
                mensaje: `¡Felicidades! Has desbloqueado el logro: ${data.nombreLogro}. Tu recompensa: ${data.recompensa}`,
                tipo_notificacion: NotificationType.BELL
            };

            return await this.notificationRepo.createNotification(notification);
        } catch (error) {
            console.error('Error handling achievement notification:', error);
            throw error instanceof AppError ? error : new AppError('Error al procesar la notificación de logro', 500);
        }
    }

    private createAchievementEmail(data: IAchievementUnlockedData) {
        return {
            to: data.email,
            subject: `¡Felicidades! Has desbloqueado un logro en SkillBoost Football`,
            html: `
                <div style="font-family: Arial, sans-serif; max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #e0e0e0; border-radius: 5px;">
                    <h2 style="color: #333;">¡Enhorabuena por tu logro!</h2>
                    <p>Has alcanzado un nuevo hito en SkillBoost Football:</p>
                    <div style="background-color: #f9f9f9; padding: 15px; border-radius: 5px; margin: 15px 0;">
                        <h3 style="margin-top: 0; color: #555;">${data.nombreLogro}</h3>
                        <p>Has completado <strong>${data.requisitoEntrenamientos} entrenamientos</strong>.</p>
                        <p style="font-size: 18px; font-weight: bold; color: #4CAF50;">Tu recompensa: ${data.recompensa}</p>
                    </div>
                    <p>Sigue entrenando y desbloqueando más logros. ¡Gracias por ser parte de SkillBoost Football!</p>
                    <p>Nos pondremos en contacto contigo para coordinar la entrega de tu recompensa.</p>
                    <div style="margin-top: 20px; padding-top: 20px; border-top: 1px solid #e0e0e0; text-align: center; color: #777; font-size: 12px;">
                        <p>Este es un correo automático, por favor no responda a este mensaje.</p>
                    </div>
                </div>
            `
        };
    }
}