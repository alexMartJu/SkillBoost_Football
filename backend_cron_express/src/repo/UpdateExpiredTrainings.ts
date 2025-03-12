import { PrismaClient } from '@prisma/client';
const prisma = new PrismaClient();

// Actualiza el estado de un entrenamiento a 'expired' cuando ha finalizado.
export async function updateExpiredTraining(id: bigint) {
    return prisma.entrenamientos.update({
        where: {
            id: id,
        },
        data: {
            status: 'expired',
            updated_at: new Date()
        },
    });
}