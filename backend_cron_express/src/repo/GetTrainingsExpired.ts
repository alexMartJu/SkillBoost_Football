import { PrismaClient } from '@prisma/client';
const prisma = new PrismaClient();

// Obtiene los entrenamientos que ya han finalizado (fecha_fin en el pasado)
// pero que todavía no han sido marcados como expirados en el sistema.
export async function getExpiredTrainings() {
    return prisma.horarios_pista.findMany({
        where: {
            fecha_fin: {
                lt: new Date() //Fecha fin menor que la fecha actual
            },
            entrenamiento_id: {
                not: null
            },
            deleted_at: null,
            entrenamientos: {
                status: {
                    not: 'expired'
                }
            }
        },
        include: {
            entrenamientos: true
        }
    });
}