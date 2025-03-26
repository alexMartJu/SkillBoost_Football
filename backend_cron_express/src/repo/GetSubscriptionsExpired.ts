import { PrismaClient } from '@prisma/client';
const prisma = new PrismaClient();

// Busca y devuelve todas las suscripciones que han expirado (fecha_fin anterior a hoy)
// pero que aún no han sido marcadas con status 'expired' en el sistema.
export async function getExpiredSubscriptions() {
    const today = new Date();
    today.setHours(0, 0, 0, 0);

    return prisma.profile_suscripciones.findMany({
        where: {
            fecha_fin: {
                lt: today
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