import { PrismaClient } from '@prisma/client';
import { AppError } from '../../utils/AppError';

const prisma = new PrismaClient();

export class UserRepository {
    async getUserEmail(userId: number): Promise<string | null> {
        try {
            const user = await prisma.usuarios.findUnique({
                where: {
                    id: BigInt(userId)
                },
                select: {
                    email: true
                }
            });
            return user ? user.email : null;
        } catch (error) {
            console.error('Error fetching user email:', error);
            throw new AppError('Error al obtener el email del usuario', 500);
        }
    }

    async getUserProfile(userId: number): Promise<any | null> {
        try {
            return await prisma.profiles.findUnique({
                where: {
                    id: BigInt(userId)
                }
            });
        } catch (error) {
            console.error('Error fetching user profile:', error);
            throw new AppError('Error al obtener el perfil del usuario', 500);
        }
    }
}