import { checkTrainingsExpired } from '../services/trainingExpiredService';
import { checkSubscriptionsExpired } from '../services/subscriptionExpiredService';
import { checkSubscriptionsExpiring } from '../services/subscriptionExpiringService';
import { AppError } from '../utils/AppError';

export async function checkExpirationController() {
  try {
    console.log('Verificando caducidad de entrenamientos y suscripciones...');
    await checkTrainingsExpired();
    await checkSubscriptionsExpired();
    await checkSubscriptionsExpiring();
    console.log('Verificación completada.');
  } catch (error) {
    if (error instanceof Error) {
      throw new AppError(error.message, 500);
    }
    throw new AppError('Error desconocido', 500);
  }
}