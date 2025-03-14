import { Request, Response, NextFunction } from 'express';
import { TrainingRegistrationService } from '../../services/training/TrainingRegistrationService';
import { AppError } from '../../utils/AppError';

export class TrainingRegistrationController {
    private trainingRegistrationService: TrainingRegistrationService;

    constructor() {
        this.trainingRegistrationService = new TrainingRegistrationService();
    }

    handleTrainingRegistration = async (req: Request, res: Response, next: NextFunction) => {
        try {
            const { userId, entrenamientoId, nombreEntrenamiento } = req.body;

            if (!userId || !entrenamientoId) {
                throw new AppError('Se requiere userId y entrenamientoId', 400);
            }

            const result = await this.trainingRegistrationService.handleTrainingRegistration({
                userId,
                entrenamientoId,
                nombreEntrenamiento
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