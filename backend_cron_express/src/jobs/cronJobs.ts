import cron from 'node-cron';
import { AppError } from '../utils/AppError';
import { checkExpirationController } from '../controllers/verificationController';

//Ejecutar cada 3 minutos
cron.schedule('*/3 * * * *', async () => {
    console.log('Ejecutando verificación de caducidad...');
    try {
      await checkExpirationController();
      console.log('Verificación completada exitosamente.');
    } catch (error) {
      if (error instanceof Error) {
        throw new AppError(error.message, 500);
      } else {
        throw new AppError(String(error), 500);
      }
    }
  });