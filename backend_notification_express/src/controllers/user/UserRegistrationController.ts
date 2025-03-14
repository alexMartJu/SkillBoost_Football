import { Request, Response, NextFunction } from 'express';
import { UserRegistrationService } from '../../services/user/UserRegistrationService';
import { AppError } from '../../utils/AppError';

export class UserRegistrationController {
    private userRegistrationService: UserRegistrationService;

    constructor() {
        this.userRegistrationService = new UserRegistrationService();
    }

    handleUserRegistration = async (req: Request, res: Response, next: NextFunction) => {
        try {
            const { userId, email, nombre } = req.body;

            if (!userId || !email) {
                throw new AppError('Se requiere userId y email', 400);
            }

            const result = await this.userRegistrationService.handleUserRegistration({
                userId,
                email,
                nombre
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