import { PrismaClient } from '@prisma/client';
import { AppError } from '../../utils/AppError';

const prisma = new PrismaClient();

export class SubscriptionRepository {
  async getSubscriptionDetails(subscriptionId: number): Promise<any | null> {
    try {
      return await prisma.suscripciones.findUnique({
        where: {
          id: BigInt(subscriptionId)
        }
      });
    } catch (error) {
      console.error('Error fetching subscription details:', error);
      throw new AppError('Error al obtener los detalles de la suscripción', 500);
    }
  }
}