import express from 'express';
import { UserRegistrationController } from '../../controllers/user/UserRegistrationController';

const router = express.Router();
const userRegistrationController = new UserRegistrationController();

router.post('/registration', userRegistrationController.handleUserRegistration);

export default router;