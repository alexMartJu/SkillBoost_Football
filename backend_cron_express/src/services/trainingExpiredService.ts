import { AppError } from '../utils/AppError';
import { getExpiredTrainings } from '../repo/GetTrainingsExpired';
import { updateExpiredTraining } from '../repo/UpdateExpiredTrainings';

export async function checkTrainingsExpired() {
  const expiredTrainings = await getExpiredTrainings();
  console.log("expiredTrainings", expiredTrainings);

  if (expiredTrainings.length > 0) {
    for (const schedule of expiredTrainings) {
      try {
        if (schedule.entrenamiento_id) {
          await updateExpiredTraining(schedule.entrenamiento_id);
          console.log(`Entrenamiento con ID ${schedule.entrenamiento_id} marcado como expirado`);
        }
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