import { AppError } from '../utils/AppError';
import { getExpiringSubscriptions } from '../repo/GetSubscriptionsExpiring';

export async function checkSubscriptionsExpiring() {
  const expiringSubscriptions = await getExpiringSubscriptions();
  console.log("expiringSubscriptions", expiringSubscriptions);

  if (expiringSubscriptions.length > 0) {
    for (const subscription of expiringSubscriptions) {
      try {
        const profileName = subscription.profiles ?
          `${subscription.profiles.nombre || ''} ${subscription.profiles.apellidos || ''}`.trim() : 'Usuario desconocido';

        const subscriptionName = subscription.suscripciones ? subscription.suscripciones.nombre : 'Suscripción desconocida';
        console.log(`ALERTA: La suscripción ${subscriptionName} del usuario ${profileName} (ID: ${subscription.profile_id}) expirará en 3 días (${subscription.fecha_fin})`);
      } catch (error) {
        if (error instanceof Error) {
          throw new AppError(error.message, 500);
        } else {
          throw new AppError(String(error), 500);
        }
      }
    }
    return true;
  }
  return false;
}