import { Request, Response, NextFunction } from 'express';
import { AchievementUnlockedService } from '../../services/achievement/AchievementUnlockedService';
import { AppError } from '../../utils/AppError';

export class AchievementUnlockedController {
    private achievementUnlockedService: AchievementUnlockedService;

    constructor() {
        this.achievementUnlockedService = new AchievementUnlockedService();
    }

    handleAchievementUnlocked = async (req: Request, res: Response, next: NextFunction) => {
        try {
            const { userId, email, logroId, nombreLogro, requisitoEntrenamientos, recompensa } = req.body;

            if (!userId || !email || !nombreLogro) {
                throw new AppError('Se requiere userId, email y nombreLogro', 400);
            }

            const result = await this.achievementUnlockedService.handleAchievementUnlocked({
                userId,
                email,
                logroId,
                nombreLogro,
                requisitoEntrenamientos,
                recompensa
            });

            res.status(201).json({
                status: 'success',
                data: result
            });
        } catch (error) {
            next(error);
        }
    };
}