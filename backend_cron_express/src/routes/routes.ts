import express from 'express';
import { checkExpirationController } from '../controllers/verificationController';

const router = express.Router();

router.post('/expiring', checkExpirationController);

export default router;