import { PrismaClient } from '@prisma/client';
const prisma = new PrismaClient();

// Actualiza una suscripción a estado 'expired' y resetea los entrenamientos
// disponibles del perfil asociado a cero
export async function updateExpiredSubscription(id: bigint, profileId: bigint) {
    //Actualizar la suscripción
    const subscription = await prisma.profile_suscripciones.update({
        where: {
            id: id,
        },
        data: {
            status: 'expired',
            updated_at: new Date()
        },
    });

    //Actualizar el perfil del usuario
    await prisma.profiles.update({
        where: {
            id: profileId,
        },
        data: {
            entrenamientos_disponibles: 0,
            updated_at: new Date()
        },
    });

    return subscription;
}