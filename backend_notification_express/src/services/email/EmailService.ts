import { Resend } from 'resend';
import { IEmailNotification } from '../../interfaces/notification/NotificationInterface';
import { AppError } from '../../utils/AppError';

export class EmailService {
  private resend: Resend;
  private devEmail: string;

  constructor() {
    this.resend = new Resend(process.env.RESEND_API_KEY);
    this.devEmail = process.env.EMAIL || '';
  }

  async sendEmail(emailData: IEmailNotification): Promise<any> {
    try {
      const { subject, html } = emailData;
      
      //Usar la dirección de correo configurada en .env como destinatario
      const recipient = this.devEmail;
      
      const { data, error } = await this.resend.emails.send({
        from: 'Resend <onboarding@resend.dev>',
        to: [recipient],
        subject: subject,
        html: html
      });
  
      if (error) {
        console.error('Error sending email:', error);
        throw new AppError(`Error al enviar el email: ${error.message}`, 500);
      }
  
      console.log(`Email enviado desde onboarding@resend.dev a ${recipient}`);
      return data;
    } catch (error) {
      console.error('Error in email service:', error);
      throw new AppError('Error en el servicio de email', 500);
    }
  }

  createUserRegistrationEmail(email: string, nombre?: string): IEmailNotification {
    const username = nombre || email.split('@')[0];
    
    return {
      to: email,
      subject: '¡Bienvenido a SkillBoost Football!',
      html: `
        <div style="font-family: Arial, sans-serif; max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #e0e0e0; border-radius: 5px;">
          <h2 style="color: #333;">¡Bienvenido a SkillBoost Football, ${username}!</h2>
          <p>Gracias por registrarte en nuestra plataforma. Estamos emocionados de tenerte con nosotros.</p>
          <p>Con tu cuenta podrás:</p>
          <ul>
            <li>Acceder a entrenamientos exclusivos</li>
            <li>Gestionar tus suscripciones</li>
            <li>Seguir tu progreso y rendimiento</li>
          </ul>
          <p>Si tienes alguna pregunta, no dudes en contactarnos.</p>
          <p>¡Esperamos que disfrutes de la experiencia SkillBoost Football!</p>
          <div style="margin-top: 20px; padding-top: 20px; border-top: 1px solid #e0e0e0; text-align: center; color: #777; font-size: 12px;">
            <p>Este es un correo automático, por favor no responda a este mensaje.</p>
          </div>
        </div>
      `
    };
  }

  createSubscriptionPaymentEmail(email: string, subscriptionData: any): IEmailNotification {
    return {
      to: email,
      subject: 'Confirmación de Pago de Suscripción - SkillBoost Football',
      html: `
        <div style="font-family: Arial, sans-serif; max-width: 600px; margin: 0 auto; padding: 20px; border: 1px solid #e0e0e0; border-radius: 5px;">
          <h2 style="color: #333;">¡Pago Confirmado!</h2>
          <p>Hemos recibido tu pago para la suscripción <strong>${subscriptionData.nombreSuscripcion || 'Premium'}</strong>.</p>
          <div style="background-color: #f9f9f9; padding: 15px; border-radius: 5px; margin: 15px 0;">
            <h3 style="margin-top: 0; color: #555;">Detalles del pago:</h3>
            <p><strong>Monto:</strong> ${subscriptionData.monto || '0.00'} €</p>
            <p><strong>Método de pago:</strong> ${subscriptionData.metodoPago || 'Tarjeta'}</p>
            <p><strong>Fecha:</strong> ${new Date().toLocaleDateString()}</p>
          </div>
          <p>Tu suscripción ya está activa y puedes disfrutar de todos sus beneficios.</p>
          <p>Gracias por confiar en SkillBoost Football.</p>
          <div style="margin-top: 20px; padding-top: 20px; border-top: 1px solid #e0e0e0; text-align: center; color: #777; font-size: 12px;">
            <p>Este es un correo automático, por favor no responda a este mensaje.</p>
          </div>
        </div>
      `
    };
  }
}