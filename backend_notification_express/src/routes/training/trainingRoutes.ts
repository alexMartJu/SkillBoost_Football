import express from 'express';
import { TrainingRegistrationController } from '../../controllers/training/TrainingRegistrationController';

const router = express.Router();
const trainingRegistrationController = new TrainingRegistrationController();

router.post('/registration', trainingRegistrationController.handleTrainingRegistration);

export default router;