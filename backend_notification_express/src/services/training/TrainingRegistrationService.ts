import { ITrainingRegistrationData } from '../../interfaces/training/TrainingRegistrationInterface';
import { INotification, NotificationType } from '../../interfaces/notification/NotificationInterface';
import { NotificationRepository } from '../../repositories/notification/NotificationRepository';
import { TrainingRepository } from '../../repositories/training/TrainingRepository';
import { AppError } from '../../utils/AppError';

export class TrainingRegistrationService {
    private notificationRepo: NotificationRepository;
    private trainingRepo: TrainingRepository;

    constructor() {
        this.notificationRepo = new NotificationRepository();
        this.trainingRepo = new TrainingRepository();
    }

    async handleTrainingRegistration(data: ITrainingRegistrationData): Promise<any> {
        try {
            if (!data.userId || !data.entrenamientoId) {
                throw new AppError('Datos de entrenamiento incompletos para la notificación', 400);
            }

            //Obtener detalles del entrenamiento si no se proporcionaron
            let nombreEntrenamiento = data.nombreEntrenamiento;
            if (!nombreEntrenamiento) {
                const entrenamiento = await this.trainingRepo.getTrainingDetails(data.entrenamientoId);
                nombreEntrenamiento = entrenamiento?.nombre || 'entrenamiento';
            }

            //Crear notificación en la base de datos (tipo bell)
            const notification: INotification = {
                id_usuario: data.userId,
                mensaje: `Te has inscrito correctamente al entrenamiento: ${nombreEntrenamiento}`,
                tipo_notificacion: NotificationType.BELL
            };

            return await this.notificationRepo.createNotification(notification);
        } catch (error) {
            console.error('Error handling training registration notification:', error);
            throw error instanceof AppError ? error : new AppError('Error al procesar la notificación de entrenamiento', 500);
        }
    }
}