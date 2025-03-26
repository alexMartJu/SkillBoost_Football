import { PrismaClient } from '@prisma/client';
const prisma = new PrismaClient();

// Busca y devuelve suscripciones que vencerán exactamente dentro de 3 días,
// permitiendo enviar notificaciones de advertencia previas a su expiración
export async function getExpiringSubscriptions() {
    //Calcular fecha exactamente 3 días después
    const threeDay = new Date();
    threeDay.setDate(threeDay.getDate() + 3);
    threeDay.setHours(0, 0, 0, 0);

    const fourDay = new Date(threeDay);
    fourDay.setDate(fourDay.getDate() + 1);

    return prisma.profile_suscripciones.findMany({
        where: {
            fecha_fin: {
                gte: threeDay,
                lt: fourDay
            },
            status: {
                not: 'expired'
            }
        },
        include: {
            profiles: true,
            suscripciones: true
        }
    });
}