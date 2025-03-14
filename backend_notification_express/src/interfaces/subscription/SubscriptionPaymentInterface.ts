export interface ISubscriptionPaymentData {
    userId: number;
    email: string;
    suscripcionId: number;
    nombreSuscripcion?: string;
    monto?: number;
    metodoPago?: string;
}