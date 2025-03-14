import { PrismaClient } from '@prisma/client';
import { AppError } from '../../utils/AppError';

const prisma = new PrismaClient();

export class TrainingRepository {
    async getTrainingDetails(trainingId: number): Promise<any | null> {
        try {
            return await prisma.entrenamientos.findUnique({
                where: {
                    id: BigInt(trainingId)
                }
            });
        } catch (error) {
            console.error('Error fetching training details:', error);
            throw new AppError('Error al obtener los detalles del entrenamiento', 500);
        }
    }
}