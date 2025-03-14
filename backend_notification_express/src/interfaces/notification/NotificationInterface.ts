export enum NotificationType {
    BELL = 'bell',
    EMAIL = 'email'
}

export interface INotification {
    id_usuario: number;
    mensaje: string;
    tipo_notificacion: string;
    isRead?: boolean;
}

export interface IEmailNotification {
    to: string;
    subject: string;
    html: string;
}