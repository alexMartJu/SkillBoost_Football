import Api from '@/services/Api';
import secrets from '@/secrets';

const api = Api(secrets.URL_PROXY);

export default {
  /**
   * Inicia un proceso de pago
   * @param {string} paymentMethod - Método de pago (stripe/paypal)
   * @param {number} amount - Monto a pagar
   * @returns {Promise} - Promesa con la respuesta del proceso de pago
   */
  initiatePayment(paymentMethod, amount) {
    return api.post('payment/process', {
      paymentMethod,
      amount
    });
  },

  /**
   * Captura un pago de PayPal
   * @param {string} orderId - ID de la orden de PayPal
   * @returns {Promise} - Promesa con la respuesta de la captura
   */
  capturePayPalPayment(orderId) {
    return api.post(`payment/capture/${orderId}`);
  },

  /**
   * Guarda un pago en el backend principal
   * @param {Object} pagoData - Datos del pago
   * @returns {Promise} - Promesa con la respuesta del guardado
   */
  savePago(pagoData) {
    return api.post('main/pagos/suscripciones', pagoData);
  }
};
