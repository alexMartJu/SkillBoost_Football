import { AppError } from '../utils/AppError';
import { getExpiredSubscriptions } from '../repo/GetSubscriptionsExpired';
import { updateExpiredSubscription } from '../repo/UpdateExpiredSubscriptions';

export async function checkSubscriptionsExpired() {
  const expiredSubscriptions = await getExpiredSubscriptions();
  console.log("expiredSubscriptions", expiredSubscriptions);

  if (expiredSubscriptions.length > 0) {
    for (const subscription of expiredSubscriptions) {
      try {
        await updateExpiredSubscription(subscription.id, subscription.profile_id);
        console.log(`Suscripción con ID ${subscription.id} del usuario ${subscription.profile_id} marcada como expirada`);
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