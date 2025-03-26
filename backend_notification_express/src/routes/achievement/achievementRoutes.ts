import express from 'express';
import { AchievementUnlockedController } from '../../controllers/achievement/AchievementUnlockedController';

const router = express.Router();
const achievementUnlockedController = new AchievementUnlockedController();

router.post('/unlocked', achievementUnlockedController.handleAchievementUnlocked);

export default router;