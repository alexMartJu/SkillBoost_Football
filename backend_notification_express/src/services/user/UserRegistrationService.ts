import { IUserRegistrationData } from '../../interfaces/user/UserRegistrationInterface';
import { INotification, NotificationType } from '../../interfaces/notification/NotificationInterface';
import { NotificationRepository } from '../../repositories/notification/NotificationRepository';
import { UserRepository } from '../../repositories/user/UserRepository';
import { EmailService } from '../email/EmailService';
import { AppError } from '../../utils/AppError';

export class UserRegistrationService {
    private notificationRepo: NotificationRepository;
    private userRepo: UserRepository;
    private emailService: EmailService;

    constructor() {
        this.notificationRepo = new NotificationRepository();
        this.userRepo = new UserRepository();
        this.emailService = new EmailService();
    }

    async handleUserRegistration(data: IUserRegistrationData): Promise<any> {
        try {
            if (!data.userId || !data.email) {
                throw new AppError('Datos de usuario incompletos para la notificación', 400);
            }

            //Enviar email de bienvenida
            const emailData = this.emailService.createUserRegistrationEmail(data.email, data.nombre);
            await this.emailService.sendEmail(emailData);

            //Crear notificación en la base de datos
            const notification: INotification = {
                id_usuario: data.userId,
                mensaje: `¡Bienvenido a SkillBoost Football! Tu cuenta ha sido creada exitosamente.`,
                tipo_notificacion: NotificationType.EMAIL
            };

            return await this.notificationRepo.createNotification(notification);
        } catch (error) {
            console.error('Error handling user registration notification:', error);
            throw error instanceof AppError ? error : new AppError('Error al procesar la notificación de registro', 500);
        }
    }
}